package com.herdal.deezerapp.data.datasource

import com.herdal.deezerapp.data.remote.dto.track.TrackResponse

interface TrackDataSource {
    interface Remote {
        suspend fun fetchTracksByAlbum(albumId: Int): TrackResponse
    }
}