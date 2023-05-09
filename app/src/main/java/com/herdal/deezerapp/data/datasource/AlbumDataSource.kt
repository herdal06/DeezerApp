package com.herdal.deezerapp.data.datasource

import com.herdal.deezerapp.data.remote.dto.album.AlbumResponse

interface AlbumDataSource {
    interface Remote {
        suspend fun fetchArtistAlbums(artistId: Int): AlbumResponse
    }
}