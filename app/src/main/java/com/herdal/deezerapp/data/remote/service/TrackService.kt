package com.herdal.deezerapp.data.remote.service

import com.herdal.deezerapp.data.remote.dto.track.TrackResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface TrackService {

    @GET("album/{album_id}/tracks")
    suspend fun fetchTracksByAlbum(
        @Path("album_id") albumId: Int
    ): TrackResponse
}