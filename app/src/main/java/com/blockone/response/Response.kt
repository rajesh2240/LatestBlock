package com.blockone.response

import com.blockone.data.EachBlockInfo

sealed class Response {

    object Loading : Response()
    object Failure : Response()
    class ResponseList(val list: List<EachBlockInfo>) : Response()
}
