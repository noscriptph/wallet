package com.example.wallet.presentation.viewmodel.detailwallet

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wallet.data.response.WalletDetailResponse
import com.example.wallet.domain.WalletUseCase
import kotlinx.coroutines.launch

/**
 * ViewModel para manejar los detalles de la billetera.
 * @property useCase Caso de uso para obtener los detalles de la billetera.
 */
class DetailViewModel(private val useCase: WalletUseCase): ViewModel() {

    // LiveData privada para almacenar los detalles de la billetera.
    private val _peopleDetail = MutableLiveData<WalletDetailResponse>()

    // LiveData pública para acceder a los detalles de la billetera.
    val peopleDetailLV: MutableLiveData<WalletDetailResponse>
        get() = _peopleDetail

    /**
     * Obtiene los detalles de una persona por su ID.
     * @param idPeople ID de la persona cuyos detalles se desean obtener.
     */
    fun getDetailPeopleById(idPeople: Long) {
        viewModelScope.launch {
            try {
                // Intenta obtener los detalles de la persona desde la base de datos.
                val people = useCase.getDetailPeopleDB(idPeople)
                _peopleDetail.value = people
            } catch (e: Exception) {
                // En caso de error, registra el error y obtiene los detalles de la persona desde la base de datos.
                Log.e("Detail Activity", "No hay conexión de red")
                _peopleDetail.value = useCase.getDetailPeopleDB(idPeople)
            }
        }
    }
}