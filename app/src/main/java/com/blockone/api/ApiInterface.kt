package com.blockone.api

import com.blockone.data.Block
import com.blockone.data.EachBlockInfo
import com.blockone.data.RequestBlockNum
import com.blockone.util.Constants
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {

    @GET(Constants.INFO_PATH)
    suspend fun getLatestBlock(): Block

    @POST(Constants.EACH_BLOCK_PATH)
    suspend fun getBlockInfo(@Body requestBlockNum: RequestBlockNum): EachBlockInfo

}