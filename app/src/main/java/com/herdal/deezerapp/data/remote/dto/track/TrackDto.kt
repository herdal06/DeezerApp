package com.herdal.deezerapp.data.remote.dto.track

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TrackDto(
    @Json(name = "id")
    val id: Long? = null,
    @Json(name = "title")
    val title: String? = null,
    @Json(name = "duration")
    val duration: Int? = null,
    @Json(name = "md5_image")
    val image: String? = null,
    @Json(name = "preview")
    val preview: String? = null,
)