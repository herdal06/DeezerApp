package com.herdal.deezerapp.ui.artist_detail

sealed class ArtistDetailUiEvent {
    data class GetArtistById(val id: Int) : ArtistDetailUiEvent()
}