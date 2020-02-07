package com.blockone.data

/*
 * Response POJO
 */
data class Block(val last_irreversible_block_id: String)

data class EachBlockInfo(
    val producer: String,
    val previous: String,
    val id: String,
    val timestamp: String,
    val producer_signature: String,
    val transactions: List<Transaction>
)

data class Transaction(val status: String)

/*
 * Request POJO
 */
data class RequestBlockNum(val block_num_or_id: String)