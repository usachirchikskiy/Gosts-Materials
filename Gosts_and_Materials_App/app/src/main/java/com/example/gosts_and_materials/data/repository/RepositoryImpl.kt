package com.example.gosts_and_materials.data.repository

import com.example.gosts_and_materials.data.remote.GostsMaterialsApi
import com.example.gosts_and_materials.domain.model.gosts.Gost
import com.example.gosts_and_materials.domain.model.materials.Material
import com.example.gosts_and_materials.domain.model.materials.MaterialItemInfo
import com.example.gosts_and_materials.domain.model.search.SearchItem
import com.example.gosts_and_materials.domain.repository.Repository
import com.example.gosts_and_materials.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.json.JSONObject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryImpl @Inject constructor(
    private val api: GostsMaterialsApi
) : Repository {


    override suspend fun getGosts(): Flow<Resource<List<Gost>>> {
        return flow {
            emit(Resource.Loading(true))
            val result = try {
                api.getGosts()
            } catch (ex: Exception) {
                ex.printStackTrace()
                emit(Resource.Error("couldn`t Load Data"))
                null
            }
            result?.let { listings ->
                emit(
                    Resource.Success(
                        data = listings
                    )
                )
                emit(Resource.Loading(false))
                return@flow
            }
        }
    }

    override suspend fun getMaterials(): Flow<Resource<List<Material>>> {
        return flow {
            emit(Resource.Loading(true))
            val result = try {
                api.getMaterials()
            } catch (ex: Exception) {
                ex.printStackTrace()
                emit(Resource.Error("couldn`t Load Data"))
                null
            }
            result?.let { listings ->
                emit(
                    Resource.Success(
                        data = listings
                    )
                )
                emit(Resource.Loading(false))
                return@flow
            }
        }
    }

    override suspend fun searchAdvanced(search: String): Flow<Resource<List<SearchItem?>>> {
        return flow {
            emit(Resource.Loading(true))
            val result = try {
                api.searchAdvanced(search)
            } catch (ex: Exception) {
                ex.printStackTrace()
                emit(Resource.Error("couldn`t Load Data"))
                null
            }
            result?.let { listings ->
                emit(
                    Resource.Success(
                        data = listings
                    )
                )
                emit(Resource.Loading(false))
                return@flow
            }
        }
    }

    override suspend fun searchByMark(mark: String): Resource<List<MaterialItemInfo?>> {
        return try {
            val bodyRequest = JSONObject()
            bodyRequest.put("mark",mark)
            val result = api.searchMark(bodyRequest.toString())
            Resource.Success(
                data = result
            )
        } catch (ex: Exception) {
            ex.printStackTrace()
            Resource.Error("couldn`t Load Data")
        }
    }
}