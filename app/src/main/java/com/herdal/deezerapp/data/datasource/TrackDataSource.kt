package com.herdal.deezerapp.data.datasource

import com.herdal.deezerapp.data.local.entity.track.TrackEntity
import com.herdal.deezerapp.data.remote.dto.track.TrackResponse

interface TrackDataSource {
    interface Remote {
        suspend fun fetchTracksByAlbum(albumId: Int): TrackResponse
    }

    interface Local {
        suspend fun addToFavorite(track: TrackEntity)
        suspend fun deleteFromFavorite(track: TrackEntity)
        suspend fun isTrackFavorite(id: Int): Boolean
    }
}