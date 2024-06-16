package com.example.wallet.domain

import com.example.wallet.data.repository.WalletImpl
import com.example.wallet.data.response.WalletDetailResponse
import com.example.wallet.data.response.WalletResponse

/**
 * Caso de uso para manejar operaciones relacionadas con la billetera.
 * @param repository Implementación del repositorio de la billetera.
 */
class WalletUseCase(private val repository: WalletImpl) {

    /**
     * Obtiene todas las personas.
     * @return Lista mutable de respuestas de billetera.
     */
    suspend fun getAllPeople(): MutableList<WalletResponse> {
        return repository.fetchPeople()
    }

    /**
     * Obtiene los detalles de una persona por su ID.
     * @param idPeople ID de la persona.
     * @return Respuesta detallada de la billetera.
     */
    suspend fun getPeopleById(idPeople: Long): WalletDetailResponse {
        return repository.fetchPeopleById(idPeople)
    }

    /**
     * Guarda todas las personas en la base de datos.
     * @param people Lista mutable de respuestas de billetera.
     */
    suspend fun saveAllPeopleDB(people: MutableList<WalletResponse>) {
        return repository.saveAllPeopleOnDB(people)
    }

    /**
     * Obtiene todas las personas de la base de datos.
     * @return Lista mutable de respuestas de billetera.
     */
    suspend fun getAllPeopleDB(): MutableList<WalletResponse> {
        return repository.getAllPeopleFromDB()
    }

    /**
     * Guarda los detalles de una persona en la base de datos.
     * @param peopleDetail Respuesta detallada de la billetera.
     */
    suspend fun saveDetailPeopleDB(peopleDetail: WalletDetailResponse) {
        return repository.saveDetailPeopleOnDB(peopleDetail)
    }

    /**
     * Obtiene los detalles de una persona de la base de datos por su ID.
     * @param idpeople ID de la persona.
     * @return Respuesta detallada de la billetera.
     */
    suspend fun getDetailPeopleDB(idpeople: Long): WalletDetailResponse {
        return repository.getDetailPeopleFromDB(idpeople)
    }
}