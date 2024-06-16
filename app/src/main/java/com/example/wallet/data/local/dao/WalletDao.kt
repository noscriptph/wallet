package com.example.wallet.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.wallet.data.response.WalletDetailResponse
import com.example.wallet.data.response.WalletResponse

/**
 * Interfaz DAO para acceder a la base de datos de la billetera.
 * Proporciona métodos para insertar y consultar datos de personas y detalles de personas.
 */
@Dao
interface WalletDao {

    /**
     * Inserta una lista de respuestas de billetera en la base de datos.
     * Si hay un conflicto, reemplaza los datos existentes.
     *
     * @param walletResponse Lista de respuestas de billetera a insertar.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPeople(walletResponse: MutableList<WalletResponse>)

    /**
     * Consulta todas las respuestas de billetera de la tabla 'people'.
     *
     * @return Lista de respuestas de billetera.
     */
    @Query("SELECT * FROM people")
    suspend fun getAllPeople(): MutableList<WalletResponse>

    /**
     * Inserta un detalle de persona en la base de datos.
     * Si hay un conflicto, reemplaza los datos existentes.
     *
     * @param peopleDetail Detalle de persona a insertar.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPeopleDetail(peopleDetail: WalletDetailResponse)

    /**
     * Consulta el detalle de una persona por su ID de la tabla 'people_details'.
     *
     * @param idPeople ID de la persona a consultar.
     * @return Detalle de la persona correspondiente al ID proporcionado.
     */
    @Query("SELECT * FROM people_details WHERE id = :idPeople")
    suspend fun getPeopleDetailById(idPeople: Long): WalletDetailResponse
}