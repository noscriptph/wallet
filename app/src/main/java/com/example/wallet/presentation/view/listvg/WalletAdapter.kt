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

class WalletAdapter: RecyclerView.Adapter<WalletAdapter.ViewHolder>() {

    lateinit var onItemClickListener: (WalletResponse) -> Unit


    var people = mutableListOf<WalletResponse>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            this.notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = WalletItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val people = people[position]
        holder.bindWallet(people)
    }

    override fun getItemCount(): Int {
        return  people.size
    }

    inner class ViewHolder(private var binding: WalletItemBinding): RecyclerView.ViewHolder(binding.root) {
          fun bindWallet(people: WalletResponse){
              binding.imgPerson.setImageResource(0)
              Picasso.get()
                  .load(people.imagenLink)
                  .centerCrop()
                  .fit()
                  .into(binding.imgPerson)

              binding.nombre.text = people.nombre
              binding.paisId.text = people.pais
              binding.cuenta.text = people.cuenta
              binding.saldo.text = people.saldo.toString()
              if(people.depositos==true) {
                  binding.aceptaDepositos.visibility = View.VISIBLE
                  binding.noAceptaDepositos.visibility = View.GONE
              }else{
                  binding.aceptaDepositos.visibility = View.GONE
                  binding.noAceptaDepositos.visibility = View.VISIBLE}


              // Asegúrate de que los parámetros de layout no cambien inesperadamente
              val layoutParams = binding.root.layoutParams as RecyclerView.LayoutParams
              layoutParams.height = RecyclerView.LayoutParams.WRAP_CONTENT
              binding.root.layoutParams = layoutParams

              clickPeopleListener(people)

          }

        private fun clickPeopleListener(people: WalletResponse) {
            binding.root.setOnClickListener {
                if(::onItemClickListener.isInitialized)
                    onItemClickListener(people)
                else
                    Log.e("WalletAdapter","Listener not initialized")
            }
        }
    }

}

