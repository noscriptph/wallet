package com.example.wallet.presentation.viewmodel.listwallet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wallet.domain.WalletUseCase

/**
 * Fábrica de ViewModels para la aplicación de Wallet.
 *
 * @property walletUseCase Caso de uso para la gestión de Wallets.
 */
class ViewModelFactory(private val walletUseCase: WalletUseCase) : ViewModelProvider.Factory {

    /**
     * Crea una instancia del ViewModel solicitado.
     *
     * @param modelClass Clase del ViewModel que se desea crear.
     * @return Una instancia del ViewModel solicitado.
     * @throws IllegalArgumentException Si la clase del ViewModel no es reconocida.
     */
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // Verifica si la clase del ViewModel es asignable desde WalletViewModel
        if (modelClass.isAssignableFrom(WalletViewModel::class.java)) {
            // Retorna una nueva instancia de WalletViewModel
            return WalletViewModel(walletUseCase) as T
        }
        // Lanza una excepción si la clase del ViewModel no es reconocida
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}