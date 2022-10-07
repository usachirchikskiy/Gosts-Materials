package com.example.gosts_and_materials.presentation.search.types.resultPage

import com.example.gosts_and_materials.domain.model.search.SearchItem

data class ResultMechState(
    val isLoading:Boolean = false,
    val results: List<SearchItem?> = emptyList()
)