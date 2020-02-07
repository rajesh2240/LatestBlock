package com.blockone.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.blockone.data.EachBlockInfo
import com.blockone.databinding.AdapterLatestblocksrowBinding

class BlockHolder(private val binding: AdapterLatestblocksrowBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bindView(eachBlockInfo: EachBlockInfo) {
        binding.block = eachBlockInfo
        binding.executePendingBindings()
    }
}