package com.example.practico3tindermoviles1fabianeduardogilcespedes.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.practico3tindermoviles1fabianeduardogilcespedes.databinding.ItemLikedPersonBinding
import com.example.practico3tindermoviles1fabianeduardogilcespedes.Persona

class LikesAdapter(private val personasLikeLista: List<Persona>) :
    RecyclerView.Adapter<LikesAdapter.LikesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LikesViewHolder {
        val binding = ItemLikedPersonBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return LikesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LikesViewHolder, position: Int) {
        val person = personasLikeLista[position]
        holder.bind(person)
    }

    override fun getItemCount(): Int = personasLikeLista.size

    inner class LikesViewHolder(private val binding: ItemLikedPersonBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(persona: Persona) {
            binding.txtNombre.text = persona.nombre
            val firstImage = persona.imagen.firstOrNull() ?: ""
            Glide.with(binding.imgPerfil.context)
                .load(firstImage)
                .into(binding.imgPerfil)
        }
    }
}
