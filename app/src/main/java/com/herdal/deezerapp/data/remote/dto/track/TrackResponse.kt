package com.herdal.deezerapp.data.remote.dto.track

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TrackResponse(
    @Json(name = "data")
    val tracks: List<TrackDto>,
)