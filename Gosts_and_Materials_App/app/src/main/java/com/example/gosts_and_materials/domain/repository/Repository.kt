package com.example.gosts_and_materials.domain.repository

import com.example.gosts_and_materials.domain.model.gosts.Gost
import com.example.gosts_and_materials.domain.model.materials.Material
import com.example.gosts_and_materials.domain.model.materials.MaterialItemInfo
import com.example.gosts_and_materials.domain.model.search.SearchItem
import com.example.gosts_and_materials.utils.Resource
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun getGosts(): Flow<Resource<List<Gost>>>

    suspend fun getMaterials():Flow<Resource<List<Material>>>

    suspend fun searchAdvanced(search:String):Flow<Resource<List<SearchItem?>>>

    suspend fun searchByMark(mark:String):Resource<List<MaterialItemInfo?>>

}