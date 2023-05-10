package com.herdal.deezerapp.data.remote.datasource

import com.herdal.deezerapp.data.datasource.TrackDataSource
import com.herdal.deezerapp.data.remote.dto.track.TrackResponse
import com.herdal.deezerapp.data.remote.service.TrackService
import com.herdal.deezerapp.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TrackRemoteDataSource @Inject constructor(
    private val trackService: TrackService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : TrackDataSource.Remote {
    override suspend fun fetchTracksByAlbum(albumId: Int): TrackResponse =
        withContext(ioDispatcher) {
            trackService.fetchTracksByAlbum(albumId = albumId)
        }
}