package com.herdal.deezerapp.ui.album_detail

import com.herdal.deezerapp.domain.uimodel.Track

sealed class AlbumDetailUiEvent {
    data class GetTracksByAlbum(val albumId: Int) : AlbumDetailUiEvent()
    data class FavoriteIconClicked(val track: Track) : AlbumDetailUiEvent()
}