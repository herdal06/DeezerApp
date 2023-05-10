package com.herdal.deezerapp.ui.favorite_tracks

import com.herdal.deezerapp.domain.uimodel.Track

sealed class FavoriteTracksUiEvent {
    object GetFavoriteTracks : FavoriteTracksUiEvent()
    data class FavoriteIconClicked(val track: Track) : FavoriteTracksUiEvent()
}