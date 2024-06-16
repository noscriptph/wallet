package com.example.wallet.presentation.view.listvg

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wallet.R
import com.example.wallet.data.response.WalletResponse
import com.example.wallet.databinding.WalletItemBinding
import com.squareup.picasso.Picasso

/**
 * Adaptador para la lista de Wallets en un RecyclerView.
 */
class WalletAdapter: RecyclerView.Adapter<WalletAdapter.ViewHolder>() {

    /**
     * Listener para manejar los clics en los elementos de la lista.
     */
    lateinit var onItemClickListener: (WalletResponse) -> Unit

    /**
     * Lista mutable de objetos WalletResponse que se mostrarán en el RecyclerView.
     */
    var people = mutableListOf<WalletResponse>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            this.notifyDataSetChanged()
        }

    /**
     * Crea un nuevo ViewHolder cuando no hay suficientes ViewHolders existentes que puedan ser reutilizados.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = WalletItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    /**
     * Vincula los datos de un WalletResponse a un ViewHolder.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val people = people[position]
        holder.bindWallet(people)
    }

    /**
     * Devuelve el número total de elementos en la lista.
     */
    override fun getItemCount(): Int {
        return people.size
    }

    /**
     * ViewHolder que contiene la vista de un elemento de la lista.
     */
    inner class ViewHolder(private var binding: WalletItemBinding): RecyclerView.ViewHolder(binding.root) {

        /**
         * Vincula los datos de un WalletResponse a las vistas del ViewHolder.
         */
        fun bindWallet(people: WalletResponse) {
            // Limpia la imagen anterior
            binding.imgPerson.setImageResource(0)

            // Carga la nueva imagen usando Picasso
            Picasso.get()
                .load(people.imagenLink)
                .centerCrop()
                .fit()
                .into(binding.imgPerson)

            // Asigna los valores a las vistas correspondientes
            binding.nombre.text = people.nombre
            binding.paisId.text = people.pais
            binding.cuenta.text = people.cuenta
            binding.saldo.text = people.saldo.toString()

            // Muestra u oculta las vistas de depósitos según el valor de people.depositos
            if (people.depositos == true) {
                binding.aceptaDepositos.visibility = View.VISIBLE
                binding.noAceptaDepositos.visibility = View.GONE
            } else {
                binding.aceptaDepositos.visibility = View.GONE
                binding.noAceptaDepositos.visibility = View.VISIBLE
            }

            // Asegúrate de que los parámetros de layout no cambien inesperadamente
            val layoutParams = binding.root.layoutParams as RecyclerView.LayoutParams
            layoutParams.height = RecyclerView.LayoutParams.WRAP_CONTENT
            binding.root.layoutParams = layoutParams

            // Configura el listener para el clic en el elemento
            clickPeopleListener(people)
        }

        /**
         * Configura el listener para manejar los clics en el elemento.
         */
        private fun clickPeopleListener(people: WalletResponse) {
            binding.root.setOnClickListener {
                if (::onItemClickListener.isInitialized)
                    onItemClickListener(people)
                else
                    Log.e("WalletAdapter", "Listener not initialized")
            }
        }
    }
}