package com.herdal.deezerapp.data.datasource

import com.herdal.deezerapp.data.remote.dto.artist.ArtistDto
import com.herdal.deezerapp.data.remote.dto.artist.ArtistResponse

interface ArtistDataSource {
    interface Remote {
        suspend fun fetchArtistsByGenre(genreId: Int): ArtistResponse
        suspend fun getArtistById(id: Int): ArtistDto
    }
}