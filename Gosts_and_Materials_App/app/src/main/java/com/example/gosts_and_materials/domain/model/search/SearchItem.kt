package com.example.gosts_and_materials.domain.model.search

data class SearchItem(
    val name:String,
    val items:List<String>,
    val isExpanded:Boolean = false
)
