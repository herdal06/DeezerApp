package com.herdal.deezerapp.data.repository

import com.herdal.deezerapp.data.datasource.TrackDataSource
import com.herdal.deezerapp.domain.mapper.TrackDtoMapper
import com.herdal.deezerapp.domain.repository.TrackRepository
import com.herdal.deezerapp.domain.uimodel.Track
import javax.inject.Inject

class TrackRepositoryImpl @Inject constructor(
    private val remote: TrackDataSource.Remote,
    private val trackDtoMapper: TrackDtoMapper
) : TrackRepository {
    override suspend fun fetchTracksByAlbum(albumId: Int): List<Track> {
        val remote = remote.fetchTracksByAlbum(albumId = albumId)
        return trackDtoMapper.toDomainList(remote.tracks)
    }
}