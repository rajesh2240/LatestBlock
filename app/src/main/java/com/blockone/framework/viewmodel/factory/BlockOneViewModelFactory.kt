package com.blockone.framework.viewmodel.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.blockone.framework.okhttp.BlockOneService
import com.blockone.repository.ApiRepositoryImpl
import com.blockone.viewmodel.BlockListViewModel

class BlockOneViewModelFactory() : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(BlockListViewModel::class.java) -> {
                BlockListViewModel( ApiRepositoryImpl(BlockOneService.apiInterface())) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

}