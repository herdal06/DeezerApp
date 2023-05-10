package com.herdal.deezerapp.domain.repository

import com.herdal.deezerapp.domain.uimodel.Track

interface TrackRepository {
    suspend fun fetchTracksByAlbum(albumId: Int): List<Track>
}