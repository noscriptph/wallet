package com.example.wallet.presentation.viewmodel.detailwallet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wallet.domain.WalletUseCase

/**
 * Fábrica de ViewModels para la vista de detalles de la billetera.
 *
 * @param walletUseCase Caso de uso de la billetera que se inyectará en el ViewModel.
 */
class ViewModelDetailFactory(private val walletUseCase: WalletUseCase) : ViewModelProvider.Factory {

    /**
     * Crea una instancia del ViewModel.
     *
     * @param modelClass Clase del ViewModel que se desea crear.
     * @return Una instancia del ViewModel solicitado.
     * @throws IllegalArgumentException Si la clase del ViewModel no es reconocida.
     */
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // Verifica si la clase del ViewModel es asignable a DetailViewModel
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            // Retorna una nueva instancia de DetailViewModel con el caso de uso inyectado
            return DetailViewModel(walletUseCase) as T
        }
        // Lanza una excepción si la clase del ViewModel no es reconocida
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}