package com.example.appclima.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.appclima.R
import com.example.appclima.databinding.LoginBinding

class BlankFragment : Fragment() {
    private lateinit var _bindig : LoginBinding
    val binding get()  = _bindig
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.materialButton.setOnClickListener {
           val action = BlankFragmentDirections.actionBlankFragmentToNotaActivity()
            findNavController().navigate(action)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _bindig = DataBindingUtil.inflate(inflater, R.layout.login,container,false)
        _bindig.lifecycleOwner =viewLifecycleOwner
        return binding.root
    }
}