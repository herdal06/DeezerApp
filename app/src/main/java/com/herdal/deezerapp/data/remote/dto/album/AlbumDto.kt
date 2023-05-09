package com.herdal.deezerapp.data.remote.dto.album

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AlbumDto(
    @Json(name = "id")
    val id: Int,
    @Json(name = "title")
    val title: String,
    @Json(name = "cover")
    val cover: String,
    @Json(name = "release_date")
    val releaseDate: String,
)