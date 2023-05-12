package com.herdal.deezerapp.data.remote.service

import com.herdal.deezerapp.data.remote.dto.artist.ArtistDto
import com.herdal.deezerapp.data.remote.dto.artist.ArtistResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ArtistService {

    @GET("genre/{genre_id}/artists")
    suspend fun fetchArtistsByGenre(
        @Path("genre_id") genreId: Int
    ): ArtistResponse

    @GET("artist/{artist_id}")
    suspend fun getArtistById(
        @Path("artist_id")id:Int
    ): ArtistDto
}