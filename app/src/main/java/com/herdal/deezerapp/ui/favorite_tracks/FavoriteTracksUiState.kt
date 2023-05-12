package com.herdal.deezerapp.ui.favorite_tracks

import com.herdal.deezerapp.domain.uimodel.Track
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class FavoriteTracksUiState(
    val loading: Boolean? = false,
    val error: String? = null,
    val tracks: Flow<List<Track>>? = emptyFlow(),
)