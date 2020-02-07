package com.blockone.framework.okhttp

import com.blockone.api.ApiInterface
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BlockOneService {

    private var apiService: ApiInterface? = null
    private var okHttpClient: OkHttpClient? = null

    private const val gatewayUrl =
        "https://api.eosnewyork.io/v1/"

    /*
     * Returns the ApiInterface reference
     */
    fun apiInterface(): ApiInterface {
        return apiService ?: Retrofit.Builder()
            .baseUrl(gatewayUrl)
            .client(httpClient())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(ApiInterface::class.java)
    }

    /*
     * Returns the OkHttpClient instance
     * Here you can Customise the Interceptor
     */
    private fun httpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return okHttpClient ?: OkHttpClient().newBuilder()
            .addInterceptor(BlockOneInterceptor())
            .addInterceptor(logging)
            .build()
    }

}

