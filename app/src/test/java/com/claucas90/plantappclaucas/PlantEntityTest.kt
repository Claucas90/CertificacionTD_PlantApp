package com.claucas90.plantappclaucas;

import com.claucas90.plantappclaucas.Model.Local.Entities.PlantEntity;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


@RunWith(JUnit4::class)
class PlantEntityTest {

    private lateinit var PlantEntity : PlantEntity

    @Before
    fun setup(){

        PlantEntity = PlantEntity(
                id = 1,
                nombre = "unit test",
                tipo = "type",
                imagen = "Testing plant entity",
                descripcion = "test description"
        )
    }

    @After
    fun tearDown(){
    }

    @Test
    fun testPlant(){
        assert(PlantEntity.id == 1)
        assert(PlantEntity.nombre == "unit test")
        assert(PlantEntity.tipo == "type")
        assert(PlantEntity.imagen == "Testing plant entity")
        assert(PlantEntity.descripcion == "test description")
    }
}