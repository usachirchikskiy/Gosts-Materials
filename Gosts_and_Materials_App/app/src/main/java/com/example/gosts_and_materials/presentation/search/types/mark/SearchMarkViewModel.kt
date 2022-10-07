package com.example.gosts_and_materials.presentation.search.types.mark

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gosts_and_materials.domain.repository.Repository
import com.example.gosts_and_materials.presentation.materials.MaterialsState
import com.example.gosts_and_materials.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchMarkViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    var state by mutableStateOf(SearchMarkState())
    private var searchJob: Job? = null

    fun searchMark(query:String) {
        state = state.copy(searchQuery = query)
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            when(val result = repository.searchByMark(state.searchQuery)){
                is Resource.Loading -> {
                    state = state.copy(isLoading = result.isLoading)
                }
                is Resource.Success ->{
                    result.data?.let { listings ->
                        state = state.copy(
                            materials = listings
                        )
                    }
                }
                is Resource.Error -> Unit
            }
        }
    }
}