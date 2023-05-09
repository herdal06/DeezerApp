package com.herdal.deezerapp.data.remote.service

import com.herdal.deezerapp.data.remote.dto.album.AlbumResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface AlbumService {

    @GET("artist/{artist_id}/albums")
    suspend fun fetchArtistAlbums(
        @Path("artist_id") artistId: Int
    ): AlbumResponse
}