package com.example.networkmodule

import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    var context: Context? = null

    private fun getBaseUrl(context: Context?): String {
        val applicationInfo = context?.packageManager?.getApplicationInfo(
            context.packageName,
            PackageManager.GET_META_DATA
        )
        val bundle = applicationInfo?.metaData
        return bundle?.getString("com.androhub.retrofit.baseurl", "no-host") ?: "no-host"
    }

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(getBaseUrl(context))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun printBaseUrl() {
        Log.d("RetrofitClient", "BaseUrl: ${retrofit.baseUrl()}")
    }
}