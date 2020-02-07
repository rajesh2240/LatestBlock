package com.blockone.fragments

import android.view.View
import androidx.navigation.fragment.findNavController
import com.blockone.R
import com.blockone.databinding.FragmentSplashBinding

class SplashFragment : BlockOneFragment<FragmentSplashBinding>() {


    override fun getLayout(): Int = R.layout.fragment_splash

    override fun initViews() {
        latestBlockButtonClick()
    }

    /*
     * onclick for Latest Blocks
     */
    private fun latestBlockButtonClick() {
         binding.button.setOnClickListener{
             findNavController().navigate(R.id.action_splashFragment_to_latestBlocksFragment)
         }
    }

    override fun hideToolBar(): Int = View.GONE

}
