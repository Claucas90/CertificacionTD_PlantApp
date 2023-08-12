package com.claucas90.plantappclaucas.Model

import com.claucas90.plantappclaucas.Model.Local.Entities.*
import com.claucas90.plantappclaucas.Model.Remote.FromNet.*

fun fromInternetPlantEntity(plantList : List<Plant>) : List<PlantEntity>{
    return plantList.map {
        PlantEntity(
            id = it.id,
            nombre = it.nombre,
            tipo = it.tipo,
            descripcion = it.descripcion,
            imagen = it.imagen
        )
    }
}

fun fromInternetPantDetailEntity(plant : PlantDetail) : PlantDetailEntity{
    return PlantDetailEntity(
        id = plant.id,
        nombre = plant.nombre,
        tipo = plant.tipo,
        descripcion = plant.descripcion,
        imagen = plant.imagen
    )
}