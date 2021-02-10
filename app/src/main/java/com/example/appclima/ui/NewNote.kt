package com.example.appclima.ui

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.appclima.R
import com.example.appclima.databinding.ActivityNewNoteBinding
import com.example.appclima.viewModel.NewNoteViewModel

class NewNote : DialogFragment() {
    private val viewModel : NewNoteViewModel by viewModels()
    private lateinit var _binding : ActivityNewNoteBinding
    val binding : ActivityNewNoteBinding get() = _binding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = DataBindingUtil.inflate(LayoutInflater.from(requireContext()),R.layout.activity_new_note,null,false)
        _binding.viewModel = viewModel
        return activity?.let {
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
            builder.setView(binding.root)
                .setPositiveButton(R.string.save) { dialog, id ->
                    val title = binding.noteTitle.text.toString()
                    if (title == null || title == ""){
                        Toast.makeText(requireContext(), "Especifique el Titulo de la nota", Toast.LENGTH_SHORT)
                    }
                    else {
                        val noteDetail = binding.noteDetail.text.toString()
                        if (noteDetail == null || noteDetail == ""){
                            Toast.makeText(requireContext(), "Especifique el contenido de la nota", Toast.LENGTH_SHORT)
                        }
                        else{
                            viewModel.title.value = title
                            viewModel.description.value = noteDetail
                            viewModel.saveNote()
                            dialog.dismiss()
                        }
                    }
                }
                .setNegativeButton(R.string.cancel) { dialog, id ->
                        dialog.dismiss()
                }
            // Create the AlertDialog object and return it
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}