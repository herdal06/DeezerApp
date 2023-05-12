package com.herdal.deezerapp.ui.artist

sealed class ArtistsUiEvent {
    data class GetArtistsByGenre(val genreId: Int) : ArtistsUiEvent()
}