package com.herdal.deezerapp.ui.album_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.herdal.deezerapp.core.Response
import com.herdal.deezerapp.domain.uimodel.Track
import com.herdal.deezerapp.domain.usecase.AddOrRemoveTrackFromFavoriteUseCase
import com.herdal.deezerapp.domain.usecase.GetTracksByAlbumUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumDetailViewModel @Inject constructor(
    private val getTracksByAlbumUseCase: GetTracksByAlbumUseCase,
    private val addOrRemoveTrackFromFavoriteUseCase: AddOrRemoveTrackFromFavoriteUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(AlbumDetailUiState())
    val uiState: StateFlow<AlbumDetailUiState> = _uiState.asStateFlow()

    fun onEvent(event: AlbumDetailUiEvent) {
        when (event) {
            is AlbumDetailUiEvent.GetTracksByAlbum -> getTracksByAlbum(event.albumId)
            is AlbumDetailUiEvent.FavoriteIconClicked -> favoriteIconClicked(event.track)
        }
    }

    private fun getTracksByAlbum(albumId: Int) = viewModelScope.launch {
        getTracksByAlbumUseCase.invoke(albumId = albumId).collect { response ->
            when (response) {
                is Response.Error -> _uiState.update { it.copy(error = response.message) }
                is Response.Loading -> _uiState.update { it.copy(loading = true) }
                is Response.Success -> _uiState.update { it.copy(tracks = response.data) }
            }
        }
    }

    private fun favoriteIconClicked(track: Track) = viewModelScope.launch {
        val newTrack = track.copy(isFavorite = !track.isFavorite!!)
        addOrRemoveTrackFromFavoriteUseCase.execute(newTrack)
        _uiState.value = _uiState.value.copy(tracks = _uiState.value.tracks?.map {
            if (it.id == track.id) {
                newTrack
            } else {
                it
            }
        })
    }
}