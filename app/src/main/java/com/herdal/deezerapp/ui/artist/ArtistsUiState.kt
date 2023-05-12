package com.herdal.deezerapp.ui.artist

import com.herdal.deezerapp.domain.uimodel.Artist

data class ArtistsUiState(
    val loading: Boolean = false,
    val error: String? = null,
    val artists: List<Artist>? = null
)
