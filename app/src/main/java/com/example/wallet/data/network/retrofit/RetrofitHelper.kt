package com.example.wallet.data.network.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    fun getRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://wallet-afuno76sn-talento-projects.vercel.app/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}