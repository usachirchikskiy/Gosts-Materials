package com.example.gosts_and_materials.presentation.materials

import com.example.gosts_and_materials.presentation.gosts.GostsState


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gosts_and_materials.domain.repository.Repository
import com.example.gosts_and_materials.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MaterialsViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    var state by mutableStateOf(MaterialsState())

    init {
        getMaterials()
    }

    fun changeItemValue(
        index: Int,
        expanded: Boolean
    ) {
        val list = state.materials.toMutableList()
        list[index] = list[index].copy(expanded = expanded)
        state = state.copy(materials = list.toList())
    }

    private fun getMaterials() {
        viewModelScope.launch {
            repository
                .getMaterials()
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.let { listings ->
                                state = state.copy(
                                    materials = listings
                                )
                            }
                        }
                        is Resource.Error -> Unit
                        is Resource.Loading -> {
                            state = state.copy(isLoading = result.isLoading)
                        }
                    }
                }
        }
    }
}