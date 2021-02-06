package com.example.appclima.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.appclima.Model.Nota
import com.example.appclima.R

class NotaAdapter(private val context: Context, private val dataset : List<Nota>) : RecyclerView.Adapter<NotaAdapter.ItemViewHolder>(){


    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val noteName : TextView = view.findViewById(R.id.id_item_nota)
        val noteDescription : TextView = view.findViewById(R.id.contenido)
        val favorite : ImageView = view.findViewById(R.id.favorite)
        val note : ConstraintLayout = view.findViewById(R.id.note)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterlayout =LayoutInflater.from(parent.context)
            .inflate(R.layout.item_nota,parent,false)
        return ItemViewHolder(adapterlayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]
        holder.noteName.text = item.titulo
        holder.noteDescription.text = item.comentario
        holder.favorite.setImageResource(
            if(item.favorita){
                R.drawable.ic_baseline_star_24
            }
            else{
                R.drawable.ic_baseline_star_border_24
            }
        )
        holder.favorite.setOnClickListener {
            //item.favorita = !item.favorita
            //actualizar la base de datos
        }
    }

    override fun getItemCount() = dataset.size
}