package com.herdal.deezerapp.data.remote.dto.album

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AlbumResponse(
    @Json(name = "data")
    val albums: List<AlbumDto>,
)