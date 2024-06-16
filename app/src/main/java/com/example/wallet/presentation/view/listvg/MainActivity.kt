package com.example.wallet.presentation.view.listvg

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wallet.data.local.database.AppDataBase
import com.example.wallet.data.network.api.WalletService
import com.example.wallet.data.network.retrofit.RetrofitHelper
import com.example.wallet.data.repository.WalletImpl
import com.example.wallet.databinding.ActivityMainBinding
import com.example.wallet.domain.WalletUseCase
import com.example.wallet.presentation.view.detailvg.DetailActivity
import com.example.wallet.presentation.viewmodel.listwallet.WalletViewModel
import com.example.wallet.presentation.viewmodel.listwallet.ViewModelFactory

/**
 * MainActivity es la actividad principal de la aplicación que muestra una lista de personas.
 * Esta actividad se encarga de inicializar el ViewModel, configurar el RecyclerView y manejar
 * la navegación a la página de detalles de una persona.
 */
class MainActivity : AppCompatActivity() {

    // Enlace a la vista de la actividad principal
    private lateinit var binding: ActivityMainBinding

    /**
     * Método onCreate que se llama cuando la actividad es creada.
     * @param savedInstanceState Estado guardado de la actividad.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicialización del servicio API y la base de datos
        val apiService = RetrofitHelper.getRetrofit().create(WalletService::class.java)
        val dataBase = AppDataBase.getDatabase(application)

        // Creación del repositorio y el caso de uso
        val repository = WalletImpl(apiService, dataBase.peopleDAO())
        val useCase = WalletUseCase(repository)

        // Configuración del ViewModel
        val viewModelFactory = ViewModelFactory(useCase)
        val viewModel = ViewModelProvider(this, viewModelFactory)[WalletViewModel::class.java]

        // Llamada para obtener todas las personas del servidor
        viewModel.getAllPeopleFromServer()

        // Configuración del adaptador y el RecyclerView
        val adapterPeople = WalletAdapter()
        binding.vgRecycler.adapter = adapterPeople
        binding.vgRecycler.layoutManager = LinearLayoutManager(this)

        // Observador para actualizar la lista de personas en el adaptador
        viewModel.peopleLV.observe(this) {
            adapterPeople.people = it
        }

        // Configuración del listener para manejar el clic en un elemento de la lista
        adapterPeople.onItemClickListener = { people ->
            val idPeople = people.id
            //TODO Hacer que esto vaya a mi segunda actividad o fragmento
            goToPeopleDetailPage(idPeople)
        }
    }

    /**
     * Método para navegar a la página de detalles de una persona.
     * @param idPeople ID de la persona seleccionada.
     */
    private fun goToPeopleDetailPage(idPeople: Long) {
        val intent = Intent(this, DetailActivity::class.java).apply {
            putExtra("ID_PEOPLE", idPeople)
        }
        startActivity(intent)
    }
}