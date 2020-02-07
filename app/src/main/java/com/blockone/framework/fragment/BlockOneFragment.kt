package com.blockone.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.blockone.MainActivity
import com.blockone.R

abstract class BlockOneFragment<T : ViewDataBinding> : Fragment() {

    lateinit var binding: T

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(this.activity),
            getLayout(),
            container,
            false
        )
        initViews()

        (activity as MainActivity).binding?.toolbar?.navigationIcon =
            activity?.getDrawable(R.drawable.ic_close_black)

        (activity as MainActivity).binding?.toolbar?.visibility = hideToolBar()
        return binding.root
    }

    abstract fun getLayout(): Int

    abstract fun initViews()

    abstract fun hideToolBar(): Int

}



