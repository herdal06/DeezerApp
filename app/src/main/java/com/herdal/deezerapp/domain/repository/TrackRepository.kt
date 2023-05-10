package com.herdal.deezerapp.domain.repository

import com.herdal.deezerapp.domain.uimodel.Track

interface TrackRepository {
    suspend fun fetchTracksByAlbum(albumId: Int): List<Track>
    suspend fun addTrackToFavorite(track: Track)
    suspend fun deleteTrackFromFavorite(track: Track)
    suspend fun isTrackFavorite(id: Int): Boolean
}