package com.example.wallet.data.repository

import com.example.wallet.data.response.WalletDetailResponse
import com.example.wallet.data.response.WalletResponse

/**
 * Repositorio de Wallet para manejar las operaciones relacionadas con el servicio de API REST
 * y la base de datos local.
 */
interface WalletRepository {

    /**
     * Estos son los métodos para poder trabajar con el servicio de API REST
     */

    /**
     * Obtiene una lista de personas desde el servicio de API REST.
     * @return MutableList<WalletResponse> Lista de respuestas de Wallet.
     */
    suspend fun fetchPeople(): MutableList<WalletResponse>

    /**
     * Obtiene el detalle de una persona específica desde el servicio de API REST.
     * @param idPeopleService ID del servicio de la persona.
     * @return WalletDetailResponse Respuesta detallada de Wallet.
     */
    suspend fun fetchPeopleById(idPeopleService: Long): WalletDetailResponse

    /**
     * Estos son los métodos para poder trabajar con la base de datos y que la app
     * se pueda utilizar sin conexión
     */

    /**
     * Guarda una lista de personas en la base de datos local.
     * @param people Lista de respuestas de Wallet a guardar.
     */
    suspend fun saveAllPeopleOnDB(people: MutableList<WalletResponse>)

    /**
     * Obtiene una lista de todas las personas desde la base de datos local.
     * @return MutableList<WalletResponse> Lista de respuestas de Wallet desde la base de datos.
     */
    suspend fun getAllPeopleFromDB(): MutableList<WalletResponse>

    /**
     * Guarda el detalle de una persona en la base de datos local.
     * @param detailWalletDetailResponse Detalle de la respuesta de Wallet a guardar.
     */
    suspend fun saveDetailPeopleOnDB(detailWalletDetailResponse: WalletDetailResponse)

    /**
     * Obtiene el detalle de una persona específica desde la base de datos local.
     * @param idPeople ID de la persona.
     * @return WalletDetailResponse Respuesta detallada de Wallet desde la base de datos.
     */
    suspend fun getDetailPeopleFromDB(idPeople: Long): WalletDetailResponse
}