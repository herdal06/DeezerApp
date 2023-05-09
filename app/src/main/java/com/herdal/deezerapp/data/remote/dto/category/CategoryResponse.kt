package com.herdal.deezerapp.data.remote.dto.category

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CategoryResponse(
    @Json(name = "data")
    val categories: List<CategoryDto>
)