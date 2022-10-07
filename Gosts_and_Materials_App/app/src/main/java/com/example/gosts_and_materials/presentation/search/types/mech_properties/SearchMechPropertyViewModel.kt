package com.example.gosts_and_materials.presentation.search.types.mech_properties

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gosts_and_materials.domain.repository.Repository
import com.example.gosts_and_materials.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class SearchMechPropertyViewModel  @Inject constructor() : ViewModel() {

    var state by mutableStateOf(MechDataState())

    fun changeSigmaB1(input: String) {
        state = state.copy(sigma_b1 = input)
    }

    fun changeSigmaB2(input: String) {
        state = state.copy(sigma_b2 = input)
    }

    fun changeSigma_02_1(input: String) {
        state = state.copy(sigma_02_1 = input)
    }

    fun changeSigma_02_2(input: String) {
        state = state.copy(sigma_02_2 = input)
    }

    fun changeSigmaDelta1(input: String) {
        state = state.copy(delta_5_1 = input)
    }

    fun changeSigmaDelta2(input: String) {
        state = state.copy(delta_5_2 = input)
    }

    fun changeSigmaF1(input: String) {
        state = state.copy(fi1 = input)
    }

    fun changeSigmaF2(input: String) {
        state = state.copy(fi2 = input)
    }

    fun changeSigmaKsu1(input: String) {
        state = state.copy(kcu1 = input)
    }

    fun changeSigmaKsu2(input: String) {
        state = state.copy(kcu2 = input)
    }

    fun makeBody():String {
        val bodyRequest = JSONObject()
        bodyRequest.put("f_kod", state.f_kod)
        bodyRequest.put("type_id", state.type_id)
        bodyRequest.put("sigma_b1", state.sigma_b1)
        bodyRequest.put("sigma_b2", state.sigma_b2)
        bodyRequest.put("sigma_02_1", state.sigma_02_1)
        bodyRequest.put("sigma_02_2", state.sigma_02_2)
        bodyRequest.put("delta_5_1", state.delta_5_1)
        bodyRequest.put("delta_5_2", state.delta_5_2)
        bodyRequest.put("fi1", state.fi1)
        bodyRequest.put("fi2", state.fi2)
        bodyRequest.put("kcu1", state.kcu1)
        bodyRequest.put("kcu2", state.kcu2)
        return bodyRequest.toString()
//        state = state.copy(bodyString = bodyRequest.toString())
//        viewModelScope.launch {
//            repository
//                .searchAdvanced(bodyRequest.toString())
//                .collect { result ->
//                    when (result) {
//                        is Resource.Success -> {
//                            result.data?.let { listings ->
//                                state = state.copy(
//                                    results = listings
//                                )
//                            }
//                        }
//                        is Resource.Error -> Unit
//                    }
//                }
//
//        }
    }
}