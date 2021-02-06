package com.example.appclima.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.appclima.Model.Nota
import com.example.appclima.R
import com.example.appclima.databinding.FragmentHomeBinding
import com.example.appclima.ui.adapter.NotaAdapter
import com.example.appclima.viewModel.HomeViewModel

class HomeFragment : Fragment() {
    private lateinit var _binding : FragmentHomeBinding
    val binding get() = _binding
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false)
        _binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val displaymetrix = requireContext().resources.displayMetrics
        val dpWith = displaymetrix.widthPixels/displaymetrix.density
        val nColumnas = (dpWith/180).toInt()

        val dataset = listOf<Nota>(
            Nota("Primera Nota", "Esta es la primera nota de la aplicacion", true, android.R.color.holo_blue_light),
            Nota("segunda Nota", "Esta es la primera nota de la aplicacion", false, android.R.color.holo_green_light),
            Nota("tercera Nota", "Esta es la primera nota de la apfredsalicacion", false, android.R.color.holo_red_light),
            Nota("cuarta Nota", "Esta es la primera nota de la aplicacion", true, android.R.color.holo_purple),
            Nota("quinta Nota", "Esta es la primera nota de lasdfsduodbaudbibsdfdslbflhsda aplicacion", false, android.R.color.holo_orange_dark),
            Nota("sexta Nota", "Esta es la primera nota de la aplicacion", false, android.R.color.holo_red_dark),
            Nota("setima Nota", "Esta es la dfadsnfuiuabsdprimera nota de la aplicacion", false, android.R.color.holo_green_light),
            Nota("octava Nota", "Esta es la primera nota de la aplicacion", false, android.R.color.holo_red_dark),
            Nota("novena Nota", "Esta es la prifdsahfuidafyiaiydfusdabfdsbfdbshjfvydyvfmera nota de la aplicacion", true, android.R.color.holo_orange_dark),
        )
        val adapter = NotaAdapter(this.requireContext(), dataset)
        Log.d("Contenido", "${adapter.itemCount}")
        binding.notasrecycler.adapter = adapter
        binding.notasrecycler.layoutManager = StaggeredGridLayoutManager(nColumnas,StaggeredGridLayoutManager.VERTICAL )
        binding.notasrecycler.setHasFixedSize(false)
    }
}