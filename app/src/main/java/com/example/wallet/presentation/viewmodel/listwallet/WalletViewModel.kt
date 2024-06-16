package com.example.wallet.presentation.viewmodel.listwallet

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wallet.data.response.WalletResponse
import com.example.wallet.domain.WalletUseCase
import kotlinx.coroutines.launch

/**
 * ViewModel para manejar la lógica de la lista de personas en la billetera.
 * @property useCase Caso de uso para manejar las operaciones de la billetera.
 */
class WalletViewModel(private val useCase: WalletUseCase): ViewModel() {

    // LiveData que contiene la lista de personas.
    private var peopleList = MutableLiveData<MutableList<WalletResponse>>()
    val peopleLV get() = peopleList

    /**
     * Obtiene todas las personas del servidor y las guarda en la base de datos local.
     * Si no hay conexión de red, obtiene las personas de la base de datos local.
     */
    fun getAllPeopleFromServer() {
        viewModelScope.launch {
            try {
                // Obtiene la respuesta del servidor.
                val response = useCase.getAllPeople()

                // Si la respuesta no está vacía, guarda los datos en la base de datos.
                if (response.isNotEmpty()) {
                    useCase.saveAllPeopleDB(response)
                    response.forEach { people ->
                        val detailResponse = useCase.getPeopleById(people.id)
                        useCase.saveDetailPeopleDB(detailResponse)
                    }
                }

                // Actualiza el LiveData con la respuesta obtenida.
                peopleList.value = response
            } catch (e: Exception) {
                // En caso de excepción, loguea el error y obtiene los datos de la base de datos local.
                Log.e("MainActivity", "Not Network Connection")
                peopleList.value = useCase.getAllPeopleDB()
            }
        }
    }
}