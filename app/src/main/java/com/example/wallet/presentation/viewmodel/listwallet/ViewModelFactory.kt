package com.example.wallet.presentation.viewmodel.listwallet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wallet.domain.WalletUseCase

class ViewModelFactory(private val peopleUseCase: WalletUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WalletViewModel::class.java)) {
            return WalletViewModel(peopleUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}