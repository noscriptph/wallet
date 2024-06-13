package com.example.wallet.presentation.viewmodel.listwallet

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wallet.data.response.WalletResponse
import com.example.wallet.domain.WalletUseCase
import kotlinx.coroutines.launch

class WalletViewModel(private val useCase: WalletUseCase): ViewModel() {

    private var peopleList = MutableLiveData<MutableList<WalletResponse>>()

    val peopleLV
        get() = peopleList


    fun getAllPeopleFromServer(){
       viewModelScope.launch {
            try {
                val response = useCase.getAllPeople()
                if( response.isNotEmpty()){
                    useCase.saveAllPeopleDB(response)
                    response.forEach { people ->
                        val detailResponse = useCase.getPeopleById(people.id)
                        useCase.saveDetailPeopleDB(detailResponse)
                    }
                }
                peopleList.value = response
            } catch (e: Exception){
                Log.e("MainActivity", "Not Network Connecction")
                peopleList.value = useCase.getAllPeopleDB()
            }
       }
    }
}