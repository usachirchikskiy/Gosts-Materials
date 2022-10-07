package com.example.gosts_and_materials.data.remote

import com.example.gosts_and_materials.domain.model.gosts.Gost
import com.example.gosts_and_materials.domain.model.materials.Material
import com.example.gosts_and_materials.domain.model.materials.MaterialItemInfo
import com.example.gosts_and_materials.domain.model.search.SearchItem
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface GostsMaterialsApi{

    @GET("gosts")
    suspend fun getGosts():List<Gost>

    @GET("materials")
    suspend fun getMaterials():List<Material>

    @Headers("Content-Type: application/json")
    @POST("searchMark")
    suspend fun searchMark(
        @Body mark: String
    ): List<MaterialItemInfo?>

    @Headers("Content-Type: application/json")
    @POST("searchAdvanced")
    suspend fun searchAdvanced(
        @Body search: String
    ): List<SearchItem?>

    companion object {
        const val BASE_URL = "http://192.168.43.47:5000/"
    }
}
