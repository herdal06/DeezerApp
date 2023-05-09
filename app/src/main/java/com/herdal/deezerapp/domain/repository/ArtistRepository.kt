package com.herdal.deezerapp.domain.repository

import com.herdal.deezerapp.domain.uimodel.Artist

interface ArtistRepository {
    suspend fun fetchArtistsByGenre(genreId: Int): List<Artist>
    suspend fun getArtistById(id: Int): Artist
}