package com.example.gosts_and_materials.domain.model.gosts

data class Gost(
    val id:Int,
    val name:String,
    val items:List<GostItem>,
    val expanded:Boolean = false
    )