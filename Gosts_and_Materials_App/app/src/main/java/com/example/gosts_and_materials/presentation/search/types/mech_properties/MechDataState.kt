package com.example.gosts_and_materials.presentation.search.types.mech_properties

import com.example.gosts_and_materials.domain.model.search.SearchItem

data class MechDataState(
    val f_kod: String = "16",
    val type_id: String = "-1",
    val sigma_b1: String = "",
    val sigma_b2: String = "",
    val sigma_02_1: String = "",
    val sigma_02_2: String = "",
    val delta_5_1: String = "",
    val delta_5_2: String = "",
    val fi1: String = "",
    val fi2: String = "",
    val kcu1: String = "",
    val kcu2: String = "",
//    val bodyString: String = ""
    //val results : List<SearchItem?> = emptyList()
)