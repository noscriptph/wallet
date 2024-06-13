package com.example.wallet.presentation.viewmodel.detailwallet

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wallet.data.response.WalletDetailResponse
import com.example.wallet.domain.WalletUseCase
import kotlinx.coroutines.launch

class DetailViewModel(private val useCase: WalletUseCase): ViewModel() {

    private val _peopleDetail = MutableLiveData<WalletDetailResponse>()
            val peopleDetailLV: MutableLiveData<WalletDetailResponse>
                get() = _peopleDetail

    fun getDetailPeopleById(idPeople: Long){
        viewModelScope.launch {

            try{
                val people = useCase.getDetailPeopleDB(idPeople)
                _peopleDetail.value = people

            } catch (e: Exception){
                Log.e("Detail Activity", "Not Network Connecction\"")
                _peopleDetail.value = useCase.getDetailPeopleDB(idPeople)
            }

        }
    }

}