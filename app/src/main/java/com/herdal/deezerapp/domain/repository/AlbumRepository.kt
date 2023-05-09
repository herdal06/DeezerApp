package com.herdal.deezerapp.domain.repository

import com.herdal.deezerapp.domain.uimodel.Album

interface AlbumRepository {
    suspend fun fetchArtistAlbums(artistId: Int): List<Album>
}