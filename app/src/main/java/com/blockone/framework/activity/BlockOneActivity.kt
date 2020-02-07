package com.blockone

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BlockOneActivity<T : ViewDataBinding> : AppCompatActivity() {

    var binding: T? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, getLayout())
        setContentView(binding?.root)
        initViews()
    }

    abstract fun getLayout(): Int

    abstract fun initViews()


}
