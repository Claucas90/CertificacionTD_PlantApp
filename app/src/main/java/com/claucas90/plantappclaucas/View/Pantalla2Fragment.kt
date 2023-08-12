package com.claucas90.plantappclaucas.View

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.claucas90.plantappclaucas.R
import com.claucas90.plantappclaucas.View.ViewModel.PlantViewModel
import com.claucas90.plantappclaucas.databinding.FragmentPantalla2Binding

class Pantalla2Fragment : Fragment() {

    private var _binding: FragmentPantalla2Binding? = null
    private val binding get() = _binding!!
    private val viewModel: PlantViewModel by activityViewModels()
    private var plantId: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentPantalla2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let { bundle ->
            plantId = bundle.getString("plantid")
            Log.d("received", plantId.toString())

        }

        plantId?.let { id ->
            viewModel.getPlantDetailByIdFromInternet(id)
        }

        viewModel.getPlantDetail().observe(viewLifecycleOwner, Observer {

            Glide.with(binding.imageView2).load(it.imagen).into(binding.imageView2)
            binding.nombre.text = "Nombre Planta   : " + it.nombre
            binding.tipo.text = "Tipo                      : " + it.tipo
            binding.descripcion.text = "Descripción        : " + it.descripcion

            var name = it.nombre

            binding.btfab.setOnClickListener {

                val intent = Intent(Intent.ACTION_SEND)
                intent.data = Uri.parse("mailto")
                intent.type = "text/plain"

                intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("lucy@plantapp.cl"))
                intent.putExtra(
                    Intent.EXTRA_SUBJECT,
                    "Consulta por producto " + name
                )
                intent.putExtra(
                    Intent.EXTRA_TEXT, "Hola\n" +
                            "Vi el producto " + name + " y me gustaria que me contactaran a este correo o al\n" +
                            "siguiente número:_____\n" +
                            "Quedo atento"
                )
                startActivity(intent)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}