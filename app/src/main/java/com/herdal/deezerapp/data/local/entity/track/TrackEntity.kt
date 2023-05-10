package com.herdal.deezerapp.data.local.entity.track

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tracks")
data class TrackEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @ColumnInfo(name = "title")
    val title: String? = null,
    @ColumnInfo(name = "duration")
    val duration: String? = null,
    @ColumnInfo(name = "md5_image")
    val image: String? = null,
    @ColumnInfo(name = "preview")
    val preview: String? = null,
    @ColumnInfo(name = "is_favorite")
    val isFavorite: Boolean? = false
)