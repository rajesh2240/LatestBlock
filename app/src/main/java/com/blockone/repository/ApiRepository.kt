package com.blockone.repository

import com.blockone.api.ApiInterface
import com.blockone.data.Block
import com.blockone.data.EachBlockInfo
import com.blockone.data.RequestBlockNum

interface ApiRepository {
    suspend fun getLatestBlockRepo(): Block
    suspend fun getBlockRepo(previousId: String): EachBlockInfo
}

class ApiRepositoryImpl(private val apiInterface: ApiInterface) : ApiRepository {

    override suspend fun getLatestBlockRepo() = apiInterface.getLatestBlock()

    override suspend fun getBlockRepo(previousId: String): EachBlockInfo =
        apiInterface.getBlockInfo(RequestBlockNum(previousId))

}

