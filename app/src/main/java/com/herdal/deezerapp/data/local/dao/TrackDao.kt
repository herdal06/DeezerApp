package com.herdal.deezerapp.data.local.dao

import androidx.room.*
import com.herdal.deezerapp.data.local.entity.track.TrackEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TrackDao {

    @Query("SELECT * FROM tracks")
    fun getAll(): Flow<List<TrackEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(track: TrackEntity)

    @Delete
    suspend fun delete(track: TrackEntity)

    @Query("SELECT * FROM tracks WHERE id=:id")
    suspend fun isTrackFavorite(id: Int): TrackEntity?
}