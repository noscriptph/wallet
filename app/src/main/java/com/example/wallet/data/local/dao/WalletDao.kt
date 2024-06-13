package com.example.wallet.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.wallet.data.response.WalletDetailResponse
import com.example.wallet.data.response.WalletResponse

@Dao
interface WalletDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPeople(walletResponse: MutableList<WalletResponse>)
    @Query("SELECT * FROM people")
    suspend fun getAllPeople(): MutableList<WalletResponse>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPeopleDetail(peopleDetail: WalletDetailResponse)
    @Query("SELECT * FROM people_details WHERE id = :idPeople")
    suspend fun getPeopleDetailById(idPeople: Long): WalletDetailResponse

}