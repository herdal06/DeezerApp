package com.herdal.deezerapp.data.remote.service

import com.herdal.deezerapp.data.remote.dto.artist.ArtistResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ArtistService {

    @GET("genre/{genre_id}/artists")
    suspend fun fetchArtistsByGenre(
        @Path("genre_id") genreId: Int
    ): ArtistResponse
}