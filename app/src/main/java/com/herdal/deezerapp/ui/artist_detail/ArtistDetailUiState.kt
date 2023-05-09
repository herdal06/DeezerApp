package com.herdal.deezerapp.ui.artist_detail

import com.herdal.deezerapp.domain.uimodel.Artist

data class ArtistDetailUiState(
    val loading: Boolean? = false,
    val error: String? = null,
    val artist: Artist? = null
)