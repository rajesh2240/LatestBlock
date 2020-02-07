package com.blockone

import androidx.navigation.findNavController
import com.blockone.databinding.ActivityLatestblocksBinding


class MainActivity : BlockOneActivity<ActivityLatestblocksBinding>() {


    override fun getLayout(): Int = R.layout.activity_latestblocks

    override fun initViews() {
        setSupportActionBar(binding?.toolbar)
        binding?.toolbar?.setNavigationOnClickListener {
            findNavController(R.id.main_nav_host_fragment).popBackStack()
        }
    }

}
