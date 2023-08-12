package com.claucas90.plantappclaucas.Model.Local.DB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.claucas90.plantappclaucas.Model.Local.Entities.*
import com.claucas90.plantappclaucas.Model.Local.PlantDao

@Database(entities = [PlantEntity::class, PlantDetailEntity::class], version = 1, exportSchema = false)
abstract class PlantDatabase: RoomDatabase() {

    abstract fun getPlantDao(): PlantDao

    companion object {
        @Volatile
        private var
                INSTANCE: PlantDatabase? = null

        fun getDataBase(context: Context): PlantDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PlantDatabase::class.java, "plantApp_Database"
                )
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}