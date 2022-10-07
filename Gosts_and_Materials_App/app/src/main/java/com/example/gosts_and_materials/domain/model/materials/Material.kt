package com.example.gosts_and_materials.domain.model.materials

data class Material(
    val id:Int,
    val name:String,
    val items:List<MaterialItem>,
    val expanded:Boolean = false
    )
