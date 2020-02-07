package com.blockone.fragments

import android.content.Context
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.blockone.R
import com.blockone.databinding.FragmentBlackdetailBinding
import com.blockone.framework.viewmodel.factory.BlockOneViewModelFactory
import com.blockone.viewmodel.BlockListViewModel

class BlockDetailsFragment : BlockOneFragment<FragmentBlackdetailBinding>() {

    override fun getLayout(): Int = R.layout.fragment_blackdetail


    private val viewModelDetails: BlockListViewModel? by lazy {
        ViewModelProvider(this, BlockOneViewModelFactory()).get(BlockListViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        blockDetailsLiveData()

    }

    val blockId: String? by lazy {
        arguments?.getString("blockId", null)
    }

    override fun initViews() {
        Log.e("blockId:", "$blockId")
        updateUI()
        blockId?.let {
            viewModelDetails?.getLatestBlockList(it)
        }

    }

    private fun updateUI() {
        binding.txtIdValue.text = blockId
    }

    private fun blockDetailsLiveData() {

        viewModelDetails?.getLiveDataEachBlock()?.observe(this, Observer {
            binding.block = it

        })
    }

    override fun hideToolBar(): Int = View.VISIBLE

}