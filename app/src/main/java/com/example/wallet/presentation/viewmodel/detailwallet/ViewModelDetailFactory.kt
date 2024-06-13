package com.example.wallet.presentation.viewmodel.detailwallet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wallet.domain.WalletUseCase

class ViewModelDetailFactory(private val peopleUseCase: WalletUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(peopleUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}