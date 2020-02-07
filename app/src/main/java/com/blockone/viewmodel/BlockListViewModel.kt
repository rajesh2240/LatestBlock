package com.blockone.viewmodel

import androidx.lifecycle.*
import com.blockone.data.Block
import com.blockone.data.EachBlockInfo
import com.blockone.repository.ApiRepository
import com.blockone.response.Response
import com.blockone.util.Constants
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


class BlockListViewModel(private val apiRepository: ApiRepository) :
    ViewModel() {

    private val liveDataBlockInfo = MutableLiveData<Block>()
    private val eachBlockLiveData: MutableLiveData<EachBlockInfo> = MutableLiveData()
    private val mediatorLiveData = MediatorLiveData<Response>()
    private var list: MutableList<EachBlockInfo> = ArrayList()

    fun getLiveDataBlock(): LiveData<Block> = liveDataBlockInfo

    fun getLiveDataBlockList(): LiveData<Response> = mediatorLiveData


    fun getLiveDataEachBlock(): LiveData<EachBlockInfo> = eachBlockLiveData

    private var indexBlock = 0

    init {
        mediatorLiveData.addSource(eachBlockLiveData, ::onChange)
    }

    private fun onChange(it: EachBlockInfo) {
        list.add(it)
        indexBlock += 1
        if (indexBlock < Constants.BLOCKSCOUNT) {
            getLatestBlockList(it.previous)
        } else {
            mediatorLiveData.postValue(Response.ResponseList(list))
        }
    }

    /**
     * Handle the Exception here
     */
    private val handler = CoroutineExceptionHandler { _, exception ->
        exception.printStackTrace()
        mediatorLiveData.postValue(Response.Failure)
    }


    fun getLatestBlock() = viewModelScope.launch(handler) {
        mediatorLiveData.postValue(Response.Loading)
        val latestBlockId = async { apiRepository.getLatestBlockRepo() }
        liveDataBlockInfo.postValue(latestBlockId.await())
    }


    fun getLatestBlockList(previousId: String) {
        viewModelScope.launch(handler) {
            val blockInfo = async { apiRepository.getBlockRepo(previousId) }
            eachBlockLiveData.value = (blockInfo.await())

        }
    }

}






