package com.herdal.deezerapp.data.repository

import com.herdal.deezerapp.data.datasource.AlbumDataSource
import com.herdal.deezerapp.domain.mapper.AlbumDtoMapper
import com.herdal.deezerapp.domain.repository.AlbumRepository
import com.herdal.deezerapp.domain.uimodel.Album
import javax.inject.Inject

class AlbumRepositoryImpl @Inject constructor(
    private val remote: AlbumDataSource.Remote,
    private val albumDtoMapper: AlbumDtoMapper
) : AlbumRepository {
    override suspend fun fetchArtistAlbums(artistId: Int): List<Album> {
        val remote = remote.fetchArtistAlbums(artistId = artistId)
        return albumDtoMapper.toDomainList(remote.albums)
    }
}