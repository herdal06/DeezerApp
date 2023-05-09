package com.herdal.deezerapp.data.remote.dto.artist

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ArtistResponse(
    @Json(name = "data")
    val artists: List<ArtistDto>
)