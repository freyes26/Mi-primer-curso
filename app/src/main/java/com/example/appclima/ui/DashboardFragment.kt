package com.example.appclima.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.appclima.BR
import com.example.appclima.R
import com.example.appclima.databinding.FragmentDashboardBinding
import com.example.appclima.repository.database.model.Note
import com.example.appclima.ui.adapter.NoteAdapter
import com.example.appclima.viewModel.DashboardViewModel

class DashboardFragment : Fragment() {

    private lateinit var _binding: FragmentDashboardBinding
    val binding get() = _binding

    private val dashboardViewModel: DashboardViewModel by viewModels()
    private lateinit var adapter: NoteAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dashboard, container, false)
        adapter = NoteAdapter { note -> favoriteOnClick(note) }
        _binding.lifecycleOwner = viewLifecycleOwner
        makeApiCall()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.setVariable(BR.viewModel, dashboardViewModel)
        binding.executePendingBindings()
        if (binding.favoriteerecycler != null) {
            binding.favoriteerecycler!!.adapter = adapter
            binding.favoriteerecycler!!.layoutManager = LinearLayoutManager(requireContext())
            binding.favoriteerecycler!!.setHasFixedSize(false)
        } else {
            val numColumns = getNumColumns()
            binding.favoriteerecyclerlan!!.adapter = adapter
            binding.favoriteerecyclerlan!!.layoutManager = StaggeredGridLayoutManager(numColumns, StaggeredGridLayoutManager.VERTICAL)
            binding.favoriteerecyclerlan!!.setHasFixedSize(false)
        }
    }

    private fun makeApiCall(){
        dashboardViewModel.allNote.observe(requireActivity(), {
            it?.let {
                adapter.submitList(it as MutableList<Note>)
            }
        })
    }

    private fun getNumColumns(): Int {
        val displaymetrix = requireContext().resources.displayMetrics
        val dpWith = displaymetrix.widthPixels / displaymetrix.density
        return (dpWith / 180).toInt()
    }

    private fun favoriteOnClick(nota: Note) {
        val newNote = Note(nota.id, nota.title, nota.comment, !nota.favorite, nota.color)
        dashboardViewModel.updateNote(newNote)
    }
}