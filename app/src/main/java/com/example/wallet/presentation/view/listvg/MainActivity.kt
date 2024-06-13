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

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val apiService = RetrofitHelper.getRetrofit().create(WalletService::class.java)
        val dataBase = AppDataBase.getDatabase(application)

        val repository = WalletImpl(apiService, dataBase.peopleDAO())
        val useCase = WalletUseCase(repository)

        val viewModelFactory = ViewModelFactory(useCase)
        val viewModel = ViewModelProvider(this,viewModelFactory)[WalletViewModel::class.java]

        viewModel.getAllPeopleFromServer()

        val adapterPeople = WalletAdapter()
        binding.vgRecycler.adapter = adapterPeople
        binding.vgRecycler.layoutManager = LinearLayoutManager(this)

        viewModel.peopleLV.observe(this){

            adapterPeople.people = it
        }

        adapterPeople.onItemClickListener = { people ->
            val idPeople = people.id
            //TODO Hacer que esto vaya a mi segunda actividd o fragmento
            goToPeopleDetailPage(idPeople)

        }
    }

    private fun goToPeopleDetailPage(idPeople: Long ){
        val intent = Intent(this, DetailActivity::class.java).apply{
            putExtra("ID_PEOPLE", idPeople)
        }
        startActivity(intent)
    }
}