package com.herdal.deezerapp.di

import com.herdal.deezerapp.data.datasource.AlbumDataSource
import com.herdal.deezerapp.data.datasource.ArtistDataSource
import com.herdal.deezerapp.data.datasource.GenreDataSource
import com.herdal.deezerapp.data.datasource.TrackDataSource
import com.herdal.deezerapp.data.local.datasource.TrackLocalDataSource
import com.herdal.deezerapp.data.remote.datasource.AlbumRemoteDataSource
import com.herdal.deezerapp.data.remote.datasource.ArtistRemoteDataSource
import com.herdal.deezerapp.data.remote.datasource.GenreRemoteDataSource
import com.herdal.deezerapp.data.remote.datasource.TrackRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@[Module InstallIn(SingletonComponent::class)]
abstract class DataSourceModule {

    @Binds
    abstract fun bindGenreRemoteDataSource(genreRemoteDataSource: GenreRemoteDataSource): GenreDataSource.Remote

    @Binds
    abstract fun bindArtistRemoteDataSource(artistRemoteDataSource: ArtistRemoteDataSource): ArtistDataSource.Remote

    @Binds
    abstract fun bindAlbumRemoteDataSource(albumRemoteDataSource: AlbumRemoteDataSource): AlbumDataSource.Remote

    @Binds
    abstract fun bindTrackRemoteDataSource(trackRemoteDataSource: TrackRemoteDataSource): TrackDataSource.Remote

    @Binds
    abstract fun bindTrackLocalDataSource(trackLocalDataSource: TrackLocalDataSource): TrackDataSource.Local
}