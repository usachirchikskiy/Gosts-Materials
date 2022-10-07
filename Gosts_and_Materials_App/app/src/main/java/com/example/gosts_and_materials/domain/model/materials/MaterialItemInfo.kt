package com.example.gosts_and_materials.domain.model.materials

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MaterialItemInfo(
    val id: Int,
    val chemical: String,
    val description: String,
    val material: String
):Parcelable