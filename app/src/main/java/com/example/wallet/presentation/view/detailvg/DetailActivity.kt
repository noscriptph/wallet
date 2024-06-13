package com.example.wallet.presentation.view.detailvg

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.wallet.data.local.database.AppDataBase
import com.example.wallet.data.network.api.WalletService
import com.example.wallet.data.network.retrofit.RetrofitHelper
import com.example.wallet.data.repository.WalletImpl
import com.example.wallet.databinding.ActivityDetailBinding
import com.example.wallet.domain.WalletUseCase
import com.example.wallet.presentation.viewmodel.detailwallet.DetailViewModel
import com.example.wallet.presentation.viewmodel.detailwallet.ViewModelDetailFactory
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity() {

    private lateinit var bindingDetail: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindingDetail = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(bindingDetail.root)

        val idPeople  = intent.getLongExtra("ID_PEOPLE", -1)
        if( idPeople == -1L){
            finish()
        }

        val apiService = RetrofitHelper.getRetrofit().create(WalletService::class.java)
        val dataBase = AppDataBase.getDatabase(application)
        val repository = WalletImpl(apiService,dataBase.peopleDAO())
        val useCase = WalletUseCase(repository)
        val viewModelFactory = ViewModelDetailFactory(useCase)
        val viewModel = ViewModelProvider(this, viewModelFactory)[DetailViewModel::class.java]


        viewModel.getDetailPeopleById(idPeople)

        viewModel.peopleDetailLV.observe(this){

            with(it){
                bindingDetail.nombre.text = nombre
                bindingDetail.paisId.text = cuenta
                bindingDetail.tipoDeCuenta.text = pais
                bindingDetail.saldo.text = saldo.toString()
                if(depositos==true) {
                    bindingDetail.aceptaDepositos.visibility = View.VISIBLE
                    bindingDetail.noAceptaDepositos.visibility = View.GONE
                }else{
                    bindingDetail.aceptaDepositos.visibility = View.GONE
                    bindingDetail.noAceptaDepositos.visibility = View.VISIBLE}



                Picasso
                    .get()
                    .load(imagenLink)
                    .into(bindingDetail.imgPeople)

                bindingDetail.btnSendEmail.setOnClickListener {
                    sendEmailWithPeople(nombre)
                }
            }
        }
    }
    private fun sendEmailWithPeople(namePeople: String){
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "message/rfc822"
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("agus.romero.salazar@gmail.com"))
        intent.putExtra(Intent.EXTRA_SUBJECT, "Formulario de Contacto " +namePeople)
        intent.putExtra(Intent.EXTRA_TEXT,"Hola\n" +
                "Somos parte del equipo de contacto de Wallet, Te animas a que podamos\n" +
                "contactarte, para poder recibir información importante.\n" +
                "Número de Contacto: _________\n" +
                "Gracias")

        if(intent.resolveActivity(packageManager) != null ){

            startActivity(Intent.createChooser(intent, "Enviar por correo"))
        } else
            Toast.makeText(
                this,
                "Debes tener instalada una aplicación de correo",
                Toast.LENGTH_LONG
            ).show()

    }
}
