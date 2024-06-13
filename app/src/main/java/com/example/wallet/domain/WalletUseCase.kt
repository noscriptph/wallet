package com.example.wallet.domain

import com.example.wallet.data.repository.WalletImpl
import com.example.wallet.data.response.WalletDetailResponse
import com.example.wallet.data.response.WalletResponse


class WalletUseCase(private val repository: WalletImpl) {
    suspend fun getAllPeople(): MutableList<WalletResponse>{
        return repository.fetchPeople()
    }
    suspend fun getPeopleById(idPeople: Long): WalletDetailResponse{
        return repository.fetchPeopleById(idPeople)
    }


    suspend fun saveAllPeopleDB(people: MutableList<WalletResponse>) {
            return repository.saveAllPeopleOnDB(people)
    }

    suspend fun getAllPeopleDB(): MutableList<WalletResponse>{
        return repository.getAllPeopleFromDB()
    }

    suspend fun saveDetailPeopleDB(peopleDetail: WalletDetailResponse){
        return repository.saveDetailPeopleOnDB(peopleDetail)
    }

    suspend fun getDetailPeopleDB(idpeople: Long): WalletDetailResponse{
        return repository.getDetailPeopleFromDB(idpeople)
    }

}