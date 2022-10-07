package com.example.gosts_and_materials.domain.model.materials

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MaterialItem(
    val id:Int,
    val name:String,
    val items:List<MaterialItemInfo>
): Parcelable