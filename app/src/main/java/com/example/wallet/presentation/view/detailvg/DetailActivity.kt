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

/**
 * Activity que muestra los detalles de una persona específica.
 */
class DetailActivity : AppCompatActivity() {

    // Enlace a la vista de detalle
    private lateinit var bindingDetail: ActivityDetailBinding

    /**
     * Método que se llama cuando se crea la actividad.
     * @param savedInstanceState Estado anterior de la actividad, si existe.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflar el layout de la actividad
        bindingDetail = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(bindingDetail.root)

        // Obtener el ID de la persona desde el Intent
        val idPeople = intent.getLongExtra("ID_PEOPLE", -1)
        if (idPeople == -1L) {
            finish()
        }

        // Inicializar los componentes necesarios para el ViewModel
        val apiService = RetrofitHelper.getRetrofit().create(WalletService::class.java)
        val dataBase = AppDataBase.getDatabase(application)
        val repository = WalletImpl(apiService, dataBase.peopleDAO())
        val useCase = WalletUseCase(repository)
        val viewModelFactory = ViewModelDetailFactory(useCase)
        val viewModel = ViewModelProvider(this, viewModelFactory)[DetailViewModel::class.java]

        // Obtener los detalles de la persona por ID
        viewModel.getDetailPeopleById(idPeople)

        // Observador para actualizar la UI con los detalles de la persona
        viewModel.peopleDetailLV.observe(this) {

            with(it) {
                // Actualizar los campos de la UI con los datos de la persona
                bindingDetail.nombre.text = nombre
                bindingDetail.paisId.text = cuenta
                bindingDetail.tipoDeCuenta.text = pais
                bindingDetail.saldo.text = saldo.toString()
                if (depositos == true) {
                    bindingDetail.aceptaDepositos.visibility = View.VISIBLE
                    bindingDetail.noAceptaDepositos.visibility = View.GONE
                } else {
                    bindingDetail.aceptaDepositos.visibility = View.GONE
                    bindingDetail.noAceptaDepositos.visibility = View.VISIBLE
                }

                // Cargar la imagen de la persona usando Picasso
                Picasso
                    .get()
                    .load(imagenLink)
                    .into(bindingDetail.imgPeople)

                // Configurar el botón para enviar un correo electrónico
                bindingDetail.btnSendEmail.setOnClickListener {
                    sendEmailWithPeople(nombre)
                }
            }
        }
    }

    /**
     * Método para enviar un correo electrónico con los detalles de la persona.
     * @param namePeople Nombre de la persona.
     */
    private fun sendEmailWithPeople(namePeople: String) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "message/rfc822"
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("agus.romero.salazar@gmail.com"))
        intent.putExtra(Intent.EXTRA_SUBJECT, "Formulario de Contacto " + namePeople)
        intent.putExtra(
            Intent.EXTRA_TEXT, "Hola\n" +
                    "Somos parte del equipo de contacto de Wallet, Te animas a que podamos\n" +
                    "contactarte, para poder recibir información importante.\n" +
                    "Número de Contacto: _________\n" +
                    "Gracias"
        )

        // Verificar si hay una aplicación de correo instalada
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(Intent.createChooser(intent, "Enviar por correo"))
        } else {
            Toast.makeText(
                this,
                "Debes tener instalada una aplicación de correo",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}