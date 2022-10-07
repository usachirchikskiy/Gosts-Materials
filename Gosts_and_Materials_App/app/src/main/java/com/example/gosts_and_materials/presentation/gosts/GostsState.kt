package com.example.gosts_and_materials.presentation.gosts

import com.example.gosts_and_materials.domain.model.gosts.Gost

data class GostsState(
    val gosts: List<Gost> = emptyList(),
    val isLoading: Boolean = false
)
