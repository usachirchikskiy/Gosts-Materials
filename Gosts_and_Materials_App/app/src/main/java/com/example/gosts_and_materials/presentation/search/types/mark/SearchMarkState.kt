package com.example.gosts_and_materials.presentation.search.types.mark

import com.example.gosts_and_materials.domain.model.materials.Material
import com.example.gosts_and_materials.domain.model.materials.MaterialItemInfo

data class SearchMarkState(
    val materials: List<MaterialItemInfo?> = emptyList(),
    val isLoading: Boolean = false,
    val searchQuery: String = ""
)
