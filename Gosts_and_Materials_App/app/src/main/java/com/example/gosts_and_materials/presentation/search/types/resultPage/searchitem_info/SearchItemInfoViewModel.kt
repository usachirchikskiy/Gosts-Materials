package com.example.gosts_and_materials.presentation.search.types.resultPage.searchitem_info

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gosts_and_materials.domain.model.materials.MaterialItemInfo
import com.example.gosts_and_materials.domain.repository.Repository
import com.example.gosts_and_materials.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchItemInfoViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    val repository: Repository
) : ViewModel() {

    var state by mutableStateOf(MaterialItemInfo(-1,"","",""))

    init {
        viewModelScope.launch {
            val mark = savedStateHandle.get<String>("mark") ?: return@launch
            val result = repository.searchByMark(mark)
            when (result) {
                is Resource.Success -> {
                    result.data?.let { listings ->
                        for(i in listings){
                            if (i!!.material==mark){
                                state = i
                                break
                            }
                        }
                    }
                }
                is Resource.Error -> Unit
            }
        }
    }

}