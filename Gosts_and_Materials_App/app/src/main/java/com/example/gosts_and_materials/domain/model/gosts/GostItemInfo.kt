package com.example.gosts_and_materials.domain.model.gosts

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GostItemInfo(
    val id: Int,
    val name: String,
    val description: String,
    val downloadlink: String,
):Parcelable