package com.blockone.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.blockone.R
import com.blockone.data.EachBlockInfo
import com.blockone.databinding.AdapterLatestblocksrowBinding
import com.blockone.viewholder.BlockHolder


typealias  OnClickListenerAlias = (EachBlockInfo) -> Unit

class BlocksAdapter : RecyclerView.Adapter<BlockHolder>() {

    var listOfBlocks: List<EachBlockInfo> = ArrayList()

    lateinit var binding: AdapterLatestblocksrowBinding

    var onClickListener: OnClickListenerAlias? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlockHolder {

        binding = AdapterLatestblocksrowBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return BlockHolder(binding)
    }

    override fun getItemCount(): Int = listOfBlocks.size

    override fun onBindViewHolder(holder: BlockHolder, position: Int) {
        holder.bindView(listOfBlocks[position])
        binding.root.setOnClickListener {
            onClickListener?.invoke(listOfBlocks[position])
        }
    }
}