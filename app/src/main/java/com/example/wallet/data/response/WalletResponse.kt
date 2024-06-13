package com.example.wallet.data.response

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "people")
data class WalletResponse(
    @PrimaryKey
    val id: Long,
    val nombre: String,
    val pais: String,
    val imagenLink: String,
    val cuenta: String,
    val saldo: Double,
    val depositos: Boolean
) {}