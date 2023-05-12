package com.herdal.deezerapp.data.remote.datasource

import com.herdal.deezerapp.data.datasource.AlbumDataSource
import com.herdal.deezerapp.data.remote.dto.album.AlbumResponse
import com.herdal.deezerapp.data.remote.service.AlbumService
import com.herdal.deezerapp.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AlbumRemoteDataSource @Inject constructor(
    private val albumService: AlbumService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : AlbumDataSource.Remote {
    override suspend fun fetchArtistAlbums(artistId: Int): AlbumResponse =
        withContext(ioDispatcher) {
            albumService.fetchArtistAlbums(artistId = artistId)
        }
}