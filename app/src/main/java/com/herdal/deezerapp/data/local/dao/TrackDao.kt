package com.herdal.deezerapp.data.local.dao

import androidx.room.*
import com.herdal.deezerapp.data.local.entity.track.TrackEntity

@Dao
interface TrackDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(track: TrackEntity)

    @Delete
    suspend fun delete(track: TrackEntity)

    @Query("SELECT * FROM tracks WHERE id=:id")
    suspend fun isTrackFavorite(id: Int): TrackEntity?
}