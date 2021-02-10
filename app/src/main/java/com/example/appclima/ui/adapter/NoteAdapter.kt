package com.example.appclima.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.appclima.R
import com.example.appclima.repository.database.model.Note

class NoteAdapter(private val onClick: (Note) -> Unit) :
    ListAdapter<Note, NoteAdapter.ItemViewHolder>(NoteComparator()) {

    class ItemViewHolder(itemView: View, val onClick: (Note) -> Unit) : RecyclerView.ViewHolder(itemView){
        private val noteName : TextView = itemView.findViewById(R.id.id_item_nota)
        private val noteDescription : TextView = itemView.findViewById(R.id.contenido)
        private val favorite : ImageView = itemView.findViewById(R.id.favorite)
        //private val note : ConstraintLayout = itemView.findViewById(R.id.note)
        private var currentNota : Note? = null

        init {
            favorite.setOnClickListener{
                currentNota?.let {
                    onClick(it)
                }
            }
        }

        fun bind(note: Note){
            currentNota = note
            noteName.text = note.title
            noteDescription.text = note.comment
            favorite.setImageResource(
                    if(note.favorite){
                        R.drawable.ic_baseline_star_24
                    }
                    else{
                        R.drawable.ic_baseline_star_border_24
                    }
            )
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view =LayoutInflater.from(parent.context)
                .inflate(R.layout.item_nota,parent,false)
        return ItemViewHolder(view,onClick)
    }
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val note  = getItem(position)
        holder.bind(note)
    }


    class NoteComparator : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id
        }
    }
}