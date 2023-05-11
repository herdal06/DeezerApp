package com.herdal.deezerapp.domain.uimodel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Album(
    val id: Int,
    val title: String,
    val cover: String,
    val releaseDate: String,
) : Parcelable