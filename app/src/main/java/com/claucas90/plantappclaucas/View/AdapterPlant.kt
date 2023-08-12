package com.claucas90.plantappclaucas.View

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.claucas90.plantappclaucas.Model.Local.Entities.PlantEntity
import com.claucas90.plantappclaucas.databinding.ItemListBinding

class AdapterPlant: RecyclerView.Adapter<AdapterPlant.PlantViewHolder>(){

    private var plantList= listOf<PlantEntity>()
    private val selectedPlant= MutableLiveData<PlantEntity>()

    inner class PlantViewHolder(private val binding: ItemListBinding):
        RecyclerView.ViewHolder(binding.root),

        View.OnClickListener{
        fun bind(plant: PlantEntity){
            Glide.with(binding.imageView).load(plant.imagen).into(binding.imageView)
            binding.tv2.text=plant.nombre
            itemView.setOnClickListener(this)

        }

        override fun onClick(v: View?) {
            selectedPlant.value=plantList[adapterPosition]
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantViewHolder {
        val binding= ItemListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PlantViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return plantList.size
    }

    override fun onBindViewHolder(holder: PlantViewHolder, position: Int) {
        val selectedPlant=plantList[position]
        holder.bind(selectedPlant)
    }

    fun elementoSeleccionado(): LiveData<PlantEntity> = selectedPlant

    fun updateData(list: List<PlantEntity>){
        plantList=list
        notifyDataSetChanged()
    }


}