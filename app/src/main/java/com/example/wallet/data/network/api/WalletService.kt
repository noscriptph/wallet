package com.example.wallet.data.network.api

import com.example.wallet.data.response.WalletDetailResponse
import com.example.wallet.data.response.WalletResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface WalletService {
    @GET("wallet")
    suspend fun getAllPeople(): MutableList<WalletResponse>
    @GET("wallet/{id}")
    suspend fun getPeopleById(@Path("id") idPeople: Long ) : WalletDetailResponse
}