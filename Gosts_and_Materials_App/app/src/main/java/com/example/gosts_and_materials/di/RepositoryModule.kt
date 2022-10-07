package com.example.gosts_and_materials.di

import com.example.gosts_and_materials.data.repository.RepositoryImpl
import com.example.gosts_and_materials.domain.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindStockRepository(
        repositoryImpl: RepositoryImpl
    ): Repository

}