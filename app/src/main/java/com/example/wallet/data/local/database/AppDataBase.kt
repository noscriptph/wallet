package com.example.wallet.data.local.database


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.wallet.data.local.dao.WalletDao
import com.example.wallet.data.response.WalletDetailResponse
import com.example.wallet.data.response.WalletResponse

@Database(entities = [WalletResponse::class, WalletDetailResponse::class], version = 1, exportSchema = false)
abstract class AppDataBase: RoomDatabase() {
    abstract fun peopleDAO(): WalletDao
    companion object {
        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getDatabase(context: Context): AppDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "wallet_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}