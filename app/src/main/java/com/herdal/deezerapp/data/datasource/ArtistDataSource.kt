package com.herdal.deezerapp.data.datasource

import com.herdal.deezerapp.data.remote.dto.artist.ArtistResponse

interface ArtistDataSource {
    interface Remote {
        suspend fun fetchArtistsByGenre(genreId: Int): ArtistResponse
    }
}