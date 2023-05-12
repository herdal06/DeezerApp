package com.herdal.deezerapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.herdal.deezerapp.data.local.dao.TrackDao
import com.herdal.deezerapp.data.local.entity.track.TrackEntity

@Database(
    entities = [TrackEntity::class],
    version = 2,
    exportSchema = false
)
abstract class DeezerDatabase : RoomDatabase() {
    abstract fun trackDao(): TrackDao
}