package com.herdal.deezerapp.di

import com.herdal.deezerapp.domain.mapper.GenreDtoMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
object MapperModule {

    @Provides
    @Singleton
    fun provideGenreDtoMapper(): GenreDtoMapper = GenreDtoMapper()
}