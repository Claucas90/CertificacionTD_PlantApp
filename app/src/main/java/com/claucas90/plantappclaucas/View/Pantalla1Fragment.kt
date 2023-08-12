package com.claucas90.plantappclaucas.View

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.claucas90.plantappclaucas.R
import com.claucas90.plantappclaucas.View.ViewModel.PlantViewModel
import com.claucas90.plantappclaucas.databinding.FragmentPantalla1Binding


class Pantalla1Fragment : Fragment() {

    private var _binding: FragmentPantalla1Binding? = null
    private val binding get() = _binding!!

    private val viewModel: PlantViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentPantalla1Binding.inflate(inflater, container, false)
        return binding.root
    }

    //
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btcerrar.setOnClickListener {
            requireActivity().finishAffinity()
        }

        val adapter = AdapterPlant()
        binding.rv1.adapter = adapter
        binding.rv1.layoutManager = GridLayoutManager(context, 2)
        binding.rv1.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )

        viewModel.getPlantList().observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.updateData(it)
            }
        })

        adapter.elementoSeleccionado().observe(viewLifecycleOwner, Observer {
            it?.let {
                Log.d("Id", it.id.toString())
            }
            val bundle = Bundle().apply {
                putString("plantid", it.id.toString())
            }
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}