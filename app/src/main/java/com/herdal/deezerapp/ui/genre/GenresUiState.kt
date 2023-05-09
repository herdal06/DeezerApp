package com.herdal.deezerapp.ui.genre

import com.herdal.deezerapp.domain.uimodel.Genre

data class GenresUiState(
    val loading: Boolean? = false,
    val error: String? = null,
    val genres: List<Genre>? = null
)