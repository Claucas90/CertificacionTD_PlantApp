package com.claucas90.plantappclaucas.Model.Repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.claucas90.plantappclaucas.Model.Local.PlantDao
import com.claucas90.plantappclaucas.Model.Remote.RetrofitClient
import com.claucas90.plantappclaucas.Model.Local.Entities.PlantDetailEntity
import com.claucas90.plantappclaucas.Model.fromInternetPantDetailEntity
import com.claucas90.plantappclaucas.Model.fromInternetPlantEntity

class PlantRepository(private val PlantDao: PlantDao) {
    private val retrofitClient= RetrofitClient.retrofitInstance()
    val plantListLiveData= PlantDao.getAllPlants()
    val plantDetailListLiveData= MutableLiveData<PlantDetailEntity>()

    suspend fun fetchPlant(){
        val service= kotlin.runCatching { retrofitClient.fetchPlantList() }

        println("3")
        service.onSuccess {
            when (it.code()){
                in 200..299 ->it.body()?.let {
                    PlantDao.insertAllPlants(fromInternetPlantEntity(it))
                }
                else-> Log.d("Repository error : ","${it.code()}-${it.errorBody()}")
            }
            service.onFailure {
                Log.e("Error : ", "${it.message}")
            }
        }
    }

    suspend fun fetchPlantDetail(id: String): PlantDetailEntity?{
        val service= kotlin.runCatching { retrofitClient.fetchPlantDetail(id) }
        return service.getOrNull()?.body()?.let {
                plantDetail ->
            val plantDetailEntity= fromInternetPantDetailEntity(plantDetail)
            PlantDao.insertPlantDetail(plantDetailEntity)
            plantDetailEntity
        }
    }
}