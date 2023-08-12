package com.claucas90.plantappclaucas.View.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.claucas90.plantappclaucas.Model.Local.DB.PlantDatabase
import com.claucas90.plantappclaucas.Model.Local.Entities.*
import com.claucas90.plantappclaucas.Model.Repository.PlantRepository
import kotlinx.coroutines.launch

class PlantViewModel(application: Application): AndroidViewModel(application) {
    private val repo : PlantRepository
    private val plantDetailLiveData = MutableLiveData<PlantDetailEntity>()
    private var idSelected : String = "-1"

    init {
        val plantDAO = PlantDatabase.getDataBase(application).getPlantDao()
        repo = PlantRepository(plantDAO)

        viewModelScope.launch {
            repo.fetchPlant()
        }
    }

    fun getPlantList() : LiveData<List<PlantEntity>> = repo.plantListLiveData

    fun getPlantDetail(): LiveData<PlantDetailEntity> = plantDetailLiveData

    fun getPlantDetailByIdFromInternet(id : String) = viewModelScope.launch {
        val plantDetail = repo.fetchPlantDetail(id)
        plantDetail?.let {
            plantDetailLiveData.postValue(it)
        }
    }
}