package com.herdal.deezerapp.data.repository

import com.herdal.deezerapp.data.datasource.TrackDataSource
import com.herdal.deezerapp.domain.mapper.TrackDtoMapper
import com.herdal.deezerapp.domain.mapper.TrackEntityMapper
import com.herdal.deezerapp.domain.repository.TrackRepository
import com.herdal.deezerapp.domain.uimodel.Track
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TrackRepositoryImpl @Inject constructor(
    private val remote: TrackDataSource.Remote,
    private val local: TrackDataSource.Local,
    private val trackDtoMapper: TrackDtoMapper,
    private val trackEntityMapper: TrackEntityMapper
) : TrackRepository {
    override suspend fun fetchTracksByAlbum(albumId: Int): List<Track> {
        val remote = remote.fetchTracksByAlbum(albumId = albumId)
        return trackDtoMapper.toDomainList(remote.tracks)
    }

    override suspend fun addTrackToFavorite(track: Track) {
        return local.addToFavorite(trackEntityMapper.fromDomain(track))
    }

    override suspend fun deleteTrackFromFavorite(track: Track) {
        return local.deleteFromFavorite(trackEntityMapper.fromDomain(track))
    }

    override suspend fun isTrackFavorite(id: Long): Boolean {
        return local.isTrackFavorite(id = id)
    }

    override fun getAllFavoriteTracks(): Flow<List<Track>> {
        return local.getAll().map { entities ->
            trackEntityMapper.toDomainList(entities)
        }
    }
}