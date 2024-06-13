package com.example.wallet.data.response

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "people_details")
data class WalletDetailResponse(
 @PrimaryKey
    val id: Long,
 val nombre: String,
 val pais: String,
 @SerializedName("imagenLink")
    val imagenLink: String,
 val cuenta: String,
 val saldo: Double,
 val depositos: Boolean
) {}


