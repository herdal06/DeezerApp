package com.herdal.deezerapp.domain.uimodel

data class Track(
    val id: Long? = null,
    val title: String? = null,
    val duration: String? = null,
    val image: String? = null,
    val preview: String? = null,
    val isFavorite: Boolean = false
)