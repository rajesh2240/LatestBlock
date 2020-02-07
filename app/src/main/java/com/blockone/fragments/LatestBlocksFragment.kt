package com.blockone.fragments

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.blockone.R
import com.blockone.adapter.BlocksAdapter
import com.blockone.adapter.OnClickListenerAlias
import com.blockone.data.EachBlockInfo
import com.blockone.databinding.FragmentLatestblocksBinding
import com.blockone.framework.viewmodel.factory.BlockOneViewModelFactory
import com.blockone.response.Response
import com.blockone.viewmodel.BlockListViewModel
import com.walmart.logistics.catalyst.mobility.framework.util.ConnectionUtils

class LatestBlocksFragment : BlockOneFragment<FragmentLatestblocksBinding>(), OnClickListenerAlias {


    private lateinit var blockListViewModel: BlockListViewModel

    private var adapter: BlocksAdapter? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        blockListViewModel =
            ViewModelProvider(this, BlockOneViewModelFactory()).get(
                BlockListViewModel::class.java
            )
        latestBlockLiveData()
        listOfLiveData()
    }

    override fun getLayout(): Int = R.layout.fragment_latestblocks

    override fun initViews() {
        settingLayoutManager()
        settingAdapter()

        this.activity?.let {
            if (ConnectionUtils.isConnected(it.applicationContext))
                blockListViewModel.getLatestBlock()
            else
                displayError()
        }

    }

    private fun displayError() {
        Toast.makeText(context, "Please check your internet settings", Toast.LENGTH_LONG).show()
    }

    private fun settingAdapter() {
        adapter = BlocksAdapter()
        adapter?.onClickListener = this
        binding.recyclerviewBlocksList.adapter = adapter
    }

    private fun settingLayoutManager() {
        val layoutManager = LinearLayoutManager(this.activity)
        binding.recyclerviewBlocksList.layoutManager = layoutManager
    }

    /*
     * observer on latest block livedata
     */
    private fun latestBlockLiveData() {
        blockListViewModel.getLiveDataBlock().observe(this, Observer {
            blockListViewModel.getLatestBlockList(it.last_irreversible_block_id)
        })
    }

    /*
     * observer on latest block livedata
     */
    private fun listOfLiveData() {
        blockListViewModel.getLiveDataBlockList().observe(this, Observer {
            when (it) {
                is Response.ResponseList -> notifyAdapter(it.list)
                is Response.Loading -> binding.progressBar.visibility = View.VISIBLE
                is Response.Failure -> binding.progressBar.visibility = View.GONE
            }
        })
    }


    private fun notifyAdapter(list: List<EachBlockInfo>) {
        adapter?.listOfBlocks = list
        adapter?.notifyDataSetChanged()
        binding.progressBar.visibility = View.GONE
    }

    override fun invoke(eachBlockInfo: EachBlockInfo) {
        Log.e("EachBlockInfo", "$eachBlockInfo")
        findNavController().navigate(
            LatestBlocksFragmentDirections.actionLatestBlocksFragmentToBlockDetailsFragment(
                eachBlockInfo.id
            )
        )
    }

    override fun hideToolBar(): Int = View.GONE

}
