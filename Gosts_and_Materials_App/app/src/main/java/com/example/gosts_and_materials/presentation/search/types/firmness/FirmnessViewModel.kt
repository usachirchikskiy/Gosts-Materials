package com.example.gosts_and_materials.presentation.search.types.firmness

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.gosts_and_materials.presentation.search.types.mech_properties.MechDataState
import dagger.hilt.android.lifecycle.HiltViewModel
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class FirmnessViewModel  @Inject constructor() : ViewModel(){

    var state by mutableStateOf(FirmnessState())

    fun changeHB1(input: String) {
        state = state.copy(hb1 = input)
    }

    fun changeHB2(input: String) {
        state = state.copy(hb2 = input)
    }

    fun makeBody():String {
        val bodyRequest = JSONObject()
        bodyRequest.put("f_kod", state.f_kod)
        bodyRequest.put("type_id", state.type_id)
        bodyRequest.put("hb1", state.hb1)
        bodyRequest.put("hb2", state.hb2)
        return bodyRequest.toString()
    }
}

/*
f_kod "14"
type_id	"-1"
hb1	"100"
hb2	"200"
 */