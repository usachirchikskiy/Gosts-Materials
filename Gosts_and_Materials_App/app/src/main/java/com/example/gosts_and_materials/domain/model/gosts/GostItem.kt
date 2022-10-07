package com.example.gosts_and_materials.domain.model.gosts

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GostItem(
    val id: Int,
    val name: String,
    val items: List<GostItemInfo>
):Parcelable
