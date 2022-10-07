package com.example.gosts_and_materials.presentation.gosts

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
class GostsViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    var state by mutableStateOf(GostsState())

    init {
        getGosts()
    }

    fun changeItemValue(
        index: Int,
        expanded: Boolean
    ) {
        val list = state.gosts.toMutableList()
        list[index] = list[index].copy(expanded = expanded)
        state = state.copy(gosts = list.toList())
    }

    private fun getGosts() {
        viewModelScope.launch {
            repository
                .getGosts()
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.let { listings ->
                                state = state.copy(
                                    gosts = listings
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