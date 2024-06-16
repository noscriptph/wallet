package com.example.wallet.data.repository

import com.example.wallet.data.local.dao.WalletDao
import com.example.wallet.data.network.api.WalletService
import com.example.wallet.data.response.WalletDetailResponse
import com.example.wallet.data.response.WalletResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Implementación del repositorio de Wallet.
 * Este repositorio maneja tanto las llamadas a la API como las consultas a la base de datos.
 */
class WalletImpl(
    private var apiservice: WalletService,
    private var daoDB: WalletDao
) : WalletRepository {

    /**
     * Obtiene una lista de personas desde la API.
     * @return MutableList<WalletResponse> Lista de personas.
     */
    override suspend fun fetchPeople(): MutableList<WalletResponse> {
        return withContext(Dispatchers.IO) {
            val listPeople = apiservice.getAllPeople()
            listPeople
        }
    }

    /**
     * Obtiene el detalle de una persona por su ID desde la API.
     * @param idPeopleService ID de la persona.
     * @return WalletDetailResponse Detalle de la persona.
     */
    override suspend fun fetchPeopleById(idPeopleService: Long): WalletDetailResponse {
        return withContext(Dispatchers.IO) {
            val peopleDetail = apiservice.getPeopleById(idPeopleService)
            peopleDetail
        }
    }

    /**
     * Guarda una lista de personas en la base de datos.
     * @param people Lista de personas a guardar.
     */
    override suspend fun saveAllPeopleOnDB(people: MutableList<WalletResponse>) {
        return withContext(Dispatchers.IO) {
            daoDB.insertPeople(people)
        }
    }

    /**
     * Obtiene una lista de personas desde la base de datos.
     * @return MutableList<WalletResponse> Lista de personas.
     */
    override suspend fun getAllPeopleFromDB(): MutableList<WalletResponse> {
        return withContext(Dispatchers.IO) {
            val response = daoDB.getAllPeople()
            response
        }
    }

    /**
     * Guarda el detalle de una persona en la base de datos.
     * @param detailWalletDetailResponse Detalle de la persona a guardar.
     */
    override suspend fun saveDetailPeopleOnDB(detailWalletDetailResponse: WalletDetailResponse) {
        return withContext(Dispatchers.IO) {
            daoDB.insertPeopleDetail(detailWalletDetailResponse)
        }
    }

    /**
     * Obtiene el detalle de una persona por su ID desde la base de datos.
     * @param idPeople ID de la persona.
     * @return WalletDetailResponse Detalle de la persona.
     */
    override suspend fun getDetailPeopleFromDB(idPeople: Long): WalletDetailResponse {
        return withContext(Dispatchers.IO) {
            val response = daoDB.getPeopleDetailById(idPeople)
            response
        }
    }
}