package com.example.wallet.data.repository

import com.example.wallet.data.response.WalletDetailResponse
import com.example.wallet.data.response.WalletResponse

interface WalletRepository {

    /**
     * Estos son los métodos para poder trabajar con el servicio de API REST
     */
    suspend fun fetchPeople(): MutableList<WalletResponse>
    suspend fun fetchPeopleById(idPeopleService: Long) : WalletDetailResponse

    /**
     * Estos son los métodos para poder trabajar con la base de datos y que la app
     * se pueda utilizar sin conexión
     */
    suspend fun saveAllPeopleOnDB(people: MutableList<WalletResponse>)
    suspend fun getAllPeopleFromDB(): MutableList<WalletResponse>
    suspend fun saveDetailPeopleOnDB(detailWalletDetailResponse: WalletDetailResponse)
    suspend fun getDetailPeopleFromDB(idPeople: Long): WalletDetailResponse

}