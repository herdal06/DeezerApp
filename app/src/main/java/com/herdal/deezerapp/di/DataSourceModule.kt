package com.herdal.deezerapp.di

import com.herdal.deezerapp.data.datasource.CategoryDataSource
import com.herdal.deezerapp.data.remote.datasource.CategoryRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@[Module InstallIn(SingletonComponent::class)]
abstract class DataSourceModule {

    @Binds
    abstract fun bindCategoryRemoteDataSource(categoryRemoteDataSource: CategoryRemoteDataSource): CategoryDataSource.Remote
}