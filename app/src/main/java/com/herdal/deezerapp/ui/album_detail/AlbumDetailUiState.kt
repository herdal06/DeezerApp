package com.herdal.deezerapp.ui.album_detail

import com.herdal.deezerapp.domain.uimodel.Track

data class AlbumDetailUiState(
    val loading: Boolean? = false,
    val error: String? = null,
    val tracks: List<Track>? = null
)