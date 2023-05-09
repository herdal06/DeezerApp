package com.herdal.deezerapp.di

import com.herdal.deezerapp.data.datasource.AlbumDataSource
import com.herdal.deezerapp.data.datasource.ArtistDataSource
import com.herdal.deezerapp.data.datasource.GenreDataSource
import com.herdal.deezerapp.data.remote.datasource.AlbumRemoteDataSource
import com.herdal.deezerapp.data.remote.datasource.ArtistRemoteDataSource
import com.herdal.deezerapp.data.remote.datasource.GenreRemoteDataSource
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
}