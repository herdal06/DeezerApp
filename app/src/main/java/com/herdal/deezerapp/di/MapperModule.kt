package com.herdal.deezerapp.di

import com.herdal.deezerapp.domain.mapper.CategoryDtoMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
object MapperModule {

    @Provides
    @Singleton
    fun provideCategoryDtoMapper(): CategoryDtoMapper = CategoryDtoMapper()
}