package com.example.wallet.data.repository

import com.example.wallet.data.local.dao.WalletDao
import com.example.wallet.data.network.api.WalletService
import com.example.wallet.data.response.WalletDetailResponse
import com.example.wallet.data.response.WalletResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WalletImpl(
    private var apiservice:WalletService,
    private var daoDB: WalletDao
    ): WalletRepository {


    /**
     * Implementación de la API REST de videojuegos
     */
    override suspend fun fetchPeople(): MutableList<WalletResponse> {
       return withContext(Dispatchers.IO){
             val listPeople = apiservice.getAllPeople()
             listPeople
       }
    }

    override suspend fun fetchPeopleById(idPeopleService: Long): WalletDetailResponse {
        return withContext(Dispatchers.IO){
            val peopleDetail = apiservice.getPeopleById(idPeopleService)
            peopleDetail
        }
    }

    /**
     * Implementación de las consultas a la base de datos a través de un DAO
     */

    override suspend fun saveAllPeopleOnDB(people: MutableList<WalletResponse>) {
        return withContext(Dispatchers.IO){
            daoDB.insertPeople(people)
        }
    }

    override suspend fun getAllPeopleFromDB(): MutableList<WalletResponse> {
        return withContext(Dispatchers.IO){
            val response = daoDB.getAllPeople()
            response
        }

    }

    override suspend fun saveDetailPeopleOnDB(detailWalletDetailResponse: WalletDetailResponse) {
        return withContext(Dispatchers.IO){
            daoDB.insertPeopleDetail(detailWalletDetailResponse)
        }
    }

    override suspend fun getDetailPeopleFromDB(idPeople: Long): WalletDetailResponse {
        return withContext(Dispatchers.IO){
            val response = daoDB.getPeopleDetailById(idPeople)
            response
        }

    }
}