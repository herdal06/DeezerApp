package com.herdal.deezerapp.domain.repository

import com.herdal.deezerapp.domain.uimodel.Track
import kotlinx.coroutines.flow.Flow

interface TrackRepository {
    suspend fun fetchTracksByAlbum(albumId: Int): List<Track>
    suspend fun addTrackToFavorite(track: Track)
    suspend fun deleteTrackFromFavorite(track: Track)
    suspend fun isTrackFavorite(id: Long): Boolean
    fun getAllFavoriteTracks(): Flow<List<Track>>
    suspend fun updateTrack(track: Track)
}