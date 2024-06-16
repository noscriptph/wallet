package com.example.wallet.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.wallet.data.local.dao.WalletDao
import com.example.wallet.data.response.WalletDetailResponse
import com.example.wallet.data.response.WalletResponse

/**
 * AppDataBase es la clase principal de la base de datos Room.
 * Define las entidades y la versión de la base de datos.
 */
@Database(entities = [WalletResponse::class, WalletDetailResponse::class], version = 1, exportSchema = false)
abstract class AppDataBase: RoomDatabase() {

    /**
     * Método abstracto que proporciona el DAO para la entidad Wallet.
     * @return WalletDao
     */
    abstract fun peopleDAO(): WalletDao

    companion object {
        @Volatile
        private var INSTANCE: AppDataBase? = null

        /**
         * Obtiene una instancia de la base de datos.
         * Si la instancia no existe, se crea una nueva.
         * @param context Contexto de la aplicación.
         * @return Instancia de AppDataBase.
         */
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