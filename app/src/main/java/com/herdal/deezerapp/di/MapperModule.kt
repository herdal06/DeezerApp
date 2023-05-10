package com.herdal.deezerapp.di

import com.herdal.deezerapp.domain.mapper.AlbumDtoMapper
import com.herdal.deezerapp.domain.mapper.ArtistDtoMapper
import com.herdal.deezerapp.domain.mapper.GenreDtoMapper
import com.herdal.deezerapp.domain.mapper.TrackDtoMapper
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

    @Provides
    @Singleton
    fun provideArtistDtoMapper(): ArtistDtoMapper = ArtistDtoMapper()

    @Provides
    @Singleton
    fun provideAlbumDtoMapper(): AlbumDtoMapper = AlbumDtoMapper()

    @Provides
    @Singleton
    fun provideTrackDtoMapper(): TrackDtoMapper = TrackDtoMapper()
}