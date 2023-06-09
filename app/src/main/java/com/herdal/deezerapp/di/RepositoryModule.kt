package com.herdal.deezerapp.di

import com.herdal.deezerapp.data.repository.AlbumRepositoryImpl
import com.herdal.deezerapp.data.repository.ArtistRepositoryImpl
import com.herdal.deezerapp.data.repository.GenreRepositoryImpl
import com.herdal.deezerapp.data.repository.TrackRepositoryImpl
import com.herdal.deezerapp.domain.repository.AlbumRepository
import com.herdal.deezerapp.domain.repository.ArtistRepository
import com.herdal.deezerapp.domain.repository.GenreRepository
import com.herdal.deezerapp.domain.repository.TrackRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@[Module InstallIn(SingletonComponent::class)]
abstract class RepositoryModule {

    @Binds
    abstract fun bindGenreRepository(genreRepositoryImpl: GenreRepositoryImpl): GenreRepository

    @Binds
    abstract fun bindArtistRepository(artistRepositoryImpl: ArtistRepositoryImpl): ArtistRepository

    @Binds
    abstract fun bindAlbumRepository(albumRepositoryImpl: AlbumRepositoryImpl): AlbumRepository

    @Binds
    abstract fun bindTrackRepository(trackRepositoryImpl: TrackRepositoryImpl): TrackRepository
}