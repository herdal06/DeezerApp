package com.herdal.deezerapp.data.remote.dto.genre

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GenreResponse(
    @Json(name = "data")
    val categories: List<GenreDto>
)