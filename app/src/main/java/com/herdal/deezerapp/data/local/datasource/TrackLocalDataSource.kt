package com.herdal.deezerapp.data.local.datasource

import com.herdal.deezerapp.data.datasource.TrackDataSource
import com.herdal.deezerapp.data.local.dao.TrackDao
import com.herdal.deezerapp.data.local.entity.track.TrackEntity
import com.herdal.deezerapp.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TrackLocalDataSource @Inject constructor(
    private val trackDao: TrackDao,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : TrackDataSource.Local {
    override suspend fun addToFavorite(track: TrackEntity) =
        withContext(ioDispatcher) {
            trackDao.insert(track)
        }

    override suspend fun deleteFromFavorite(track: TrackEntity) =
        withContext(ioDispatcher) {
            trackDao.delete(track)
        }

    override suspend fun isTrackFavorite(id: Int): Boolean =
        withContext(ioDispatcher) {
            trackDao.isTrackFavorite(id) != null
        }
}