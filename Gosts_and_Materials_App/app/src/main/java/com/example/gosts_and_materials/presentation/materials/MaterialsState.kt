package com.example.gosts_and_materials.presentation.materials

import com.example.gosts_and_materials.domain.model.materials.Material

data class MaterialsState(
    val materials: List<Material> = emptyList(),
    val isLoading: Boolean = false
)