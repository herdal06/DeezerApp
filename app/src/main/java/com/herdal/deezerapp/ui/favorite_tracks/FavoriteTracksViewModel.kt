package com.herdal.deezerapp.ui.favorite_tracks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.herdal.deezerapp.core.Response
import com.herdal.deezerapp.domain.repository.TrackRepository
import com.herdal.deezerapp.domain.uimodel.Track
import com.herdal.deezerapp.domain.usecase.AddOrRemoveTrackFromFavoriteUseCase
import com.herdal.deezerapp.domain.usecase.GetFavoriteTracksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteTracksViewModel @Inject constructor(
    private val getFavoriteTracksUseCase: GetFavoriteTracksUseCase,
    private val addOrRemoveTrackFromFavoriteUseCase: AddOrRemoveTrackFromFavoriteUseCase,
    private val trackRepository: TrackRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(FavoriteTracksUiState())
    val uiState: StateFlow<FavoriteTracksUiState> = _uiState.asStateFlow()

    fun onEvent(event: FavoriteTracksUiEvent) {
        when (event) {
            is FavoriteTracksUiEvent.FavoriteIconClicked -> favoriteIconClicked(event.track)
            FavoriteTracksUiEvent.GetFavoriteTracks -> getTracksByAlbum()
        }
    }

    private fun getTracksByAlbum() = viewModelScope.launch {
        getFavoriteTracksUseCase.invoke().collect { response ->
            when (response) {
                is Response.Error -> _uiState.update { it.copy(error = response.message) }
                is Response.Loading -> _uiState.update { it.copy(loading = true) }
                is Response.Success -> _uiState.update { it.copy(tracks = response.data) }
            }
        }
    }

    private fun favoriteIconClicked(track: Track) = viewModelScope.launch {
        addOrRemoveTrackFromFavoriteUseCase.execute(track)

        // update cached tracks in database
        trackRepository.updateTrack(track)
    }
}