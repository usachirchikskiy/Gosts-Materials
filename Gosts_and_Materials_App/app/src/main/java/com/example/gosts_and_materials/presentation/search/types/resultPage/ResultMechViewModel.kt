package com.example.gosts_and_materials.presentation.search.types.resultPage

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gosts_and_materials.domain.repository.Repository
import com.example.gosts_and_materials.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ResultMechViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    val repository: Repository
) : ViewModel() {

    var state by mutableStateOf(ResultMechState())

    init {
        viewModelScope.launch {
            val bodyString = savedStateHandle.get<String>("bodyString") ?: return@launch
            repository.searchAdvanced(bodyString).collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        state = state.copy(isLoading = result.isLoading)
                    }
                    is Resource.Success -> {
                        result.data?.let { listings ->
                            state = state.copy(
                                results = listings
                            )
                        }
                    }
                    is Resource.Error -> Unit
                }
            }
        }
    }

    fun changeItemValue(
        index: Int,
        expanded: Boolean
    ) {
        val list = state.results.toMutableList()
        list[index] = list[index]!!.copy(isExpanded = expanded)
        state = state.copy(results = list.toList())
    }
}
