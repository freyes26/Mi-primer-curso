package com.example.appclima.ui

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.appclima.BR
import com.example.appclima.R
import com.example.appclima.databinding.FragmentHomeBinding
import com.example.appclima.repository.database.model.Note
import com.example.appclima.ui.adapter.NoteAdapter
import com.example.appclima.viewModel.HomeViewModel

class HomeFragment : Fragment() {
    private lateinit var _binding: FragmentHomeBinding
    val binding get() = _binding

    private val homeViewModel: HomeViewModel by viewModels()

    private lateinit var adapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        adapter = NoteAdapter { note -> favoriteOnClick(note) }
        _binding.lifecycleOwner = viewLifecycleOwner
        makeApiCall()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.setVariable(BR.viewModel, homeViewModel)
        binding.executePendingBindings()
        if (binding.notaerecycler != null) {
            binding.notaerecycler!!.adapter = adapter
            binding.notaerecycler!!.layoutManager = LinearLayoutManager(requireContext())
            binding.notaerecycler!!.setHasFixedSize(false)
        } else {
            val numColumns = getNumColumns()
            binding.notasrecyclerlan!!.adapter = adapter
            binding.notasrecyclerlan!!.layoutManager = StaggeredGridLayoutManager(numColumns, StaggeredGridLayoutManager.VERTICAL)
            binding.notasrecyclerlan!!.setHasFixedSize(false)
        }
    }

    fun makeApiCall() {
        homeViewModel.allNote.observe(requireActivity(), {
            it?.let {
                adapter.submitList(it as MutableList<Note>)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.option_menu_home_fragment, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_new_note -> {
                showDialogAddNote()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    fun showDialogAddNote() {
        val newNote = NewNote()
        newNote.show(requireActivity().supportFragmentManager, "Tag")
    }

    private fun getNumColumns(): Int {
        val displaymetrix = requireContext().resources.displayMetrics
        val dpWith = displaymetrix.widthPixels / displaymetrix.density
        return (dpWith / 180).toInt()
    }

    private fun favoriteOnClick(nota: Note) {
        val newNote = Note(nota.id, nota.title, nota.comment, !nota.favorite, nota.color)
        homeViewModel.updateNote(newNote)
    }
}