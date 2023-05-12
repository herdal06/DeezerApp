package com.herdal.deezerapp.domain.uimodel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Genre(
    val id: Int? = null,
    val name: String? = null,
    val picture: String? = null,
) : Parcelable