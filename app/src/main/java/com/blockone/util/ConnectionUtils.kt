package com.walmart.logistics.catalyst.mobility.framework.util

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.NetworkInfo


object ConnectionUtils {

    internal fun isConnected(context: Context): Boolean {
        val connMgr = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo: NetworkInfo? = connMgr.activeNetworkInfo
        return networkInfo?.isConnected == true
    }

}