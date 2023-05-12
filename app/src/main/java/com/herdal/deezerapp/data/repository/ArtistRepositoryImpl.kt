package com.herdal.deezerapp.data.repository

import com.herdal.deezerapp.data.datasource.ArtistDataSource
import com.herdal.deezerapp.domain.mapper.ArtistDtoMapper
import com.herdal.deezerapp.domain.repository.ArtistRepository
import com.herdal.deezerapp.domain.uimodel.Artist
import javax.inject.Inject

class ArtistRepositoryImpl @Inject constructor(
    private val remote: ArtistDataSource.Remote,
    private val artistDtoMapper: ArtistDtoMapper
) : ArtistRepository {
    override suspend fun fetchArtistsByGenre(genreId: Int): List<Artist> {
        val remoteArtists = remote.fetchArtistsByGenre(genreId = genreId)
        return artistDtoMapper.toDomainList(remoteArtists.artists)
    }

    override suspend fun getArtistById(id: Int): Artist {
        return artistDtoMapper.toDomain(remote.getArtistById(id = id))
    }
}