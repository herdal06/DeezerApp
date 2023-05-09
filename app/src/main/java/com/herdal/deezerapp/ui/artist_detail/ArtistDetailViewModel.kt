package com.herdal.deezerapp.ui.artist_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.herdal.deezerapp.core.Response
import com.herdal.deezerapp.domain.usecase.GetArtistAlbumsUseCase
import com.herdal.deezerapp.domain.usecase.GetArtistByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtistDetailViewModel @Inject constructor(
    private val getArtistByIdUseCase: GetArtistByIdUseCase,
    private val getArtistAlbumsUseCase: GetArtistAlbumsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ArtistDetailUiState())
    val uiState: StateFlow<ArtistDetailUiState> = _uiState.asStateFlow()

    fun onEvent(event: ArtistDetailUiEvent) {
        when (event) {
            is ArtistDetailUiEvent.GetArtistById -> getArtistById(event.id)
            is ArtistDetailUiEvent.GetArtistAlbums -> getArtistAlbums(event.artistId)
        }
    }

    private fun getArtistById(id: Int) = viewModelScope.launch {
        getArtistByIdUseCase.invoke(id = id).collect { response ->
            when (response) {
                is Response.Error -> _uiState.update { it.copy(error = response.message) }
                is Response.Loading -> _uiState.update { it.copy(loading = true) }
                is Response.Success -> _uiState.update { it.copy(artist = response.data) }
            }
        }
    }

    private fun getArtistAlbums(artistId: Int) = viewModelScope.launch {
        getArtistAlbumsUseCase.invoke(artistId = artistId).collect { response ->
            when (response) {
                is Response.Error -> _uiState.update { it.copy(error = response.message) }
                is Response.Loading -> _uiState.update { it.copy(loading = true) }
                is Response.Success -> _uiState.update { it.copy(albums = response.data) }
            }
        }
    }
}