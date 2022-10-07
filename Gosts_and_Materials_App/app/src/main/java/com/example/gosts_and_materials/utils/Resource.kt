package com.example.gosts_and_materials.utils

import android.content.Context
import android.content.ContextWrapper
import androidx.appcompat.app.AppCompatActivity

sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T?): Resource<T>(data)
    class Error<T>(message: String, data: T? = null): Resource<T>(data, message)
    class Loading<T>(val isLoading: Boolean = true): Resource<T>(null)
}

//fun Context.findActivity(): AppCompatActivity? = when (this) {
//    is AppCompatActivity -> this
//    is ContextWrapper -> baseContext.findActivity()
//    else -> null
//}


