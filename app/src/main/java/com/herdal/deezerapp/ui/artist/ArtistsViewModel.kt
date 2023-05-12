package com.herdal.deezerapp.ui.artist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.herdal.deezerapp.core.Response
import com.herdal.deezerapp.domain.usecase.GetArtistsByGenreUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtistsViewModel @Inject constructor(
    private val getArtistsByGenreUseCase: GetArtistsByGenreUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ArtistsUiState())
    val uiState: StateFlow<ArtistsUiState> = _uiState.asStateFlow()

    fun onEvent(event: ArtistsUiEvent) {
        when (event) {
            is ArtistsUiEvent.GetArtistsByGenre -> getArtistsByGenre(event.genreId)
        }
    }

    private fun getArtistsByGenre(genreId: Int) = viewModelScope.launch {
        getArtistsByGenreUseCase.invoke(genreId).collect { response ->
            when (response) {
                is Response.Error -> _uiState.update { it.copy(error = response.message) }
                is Response.Loading -> _uiState.update { it.copy(loading = true) }
                is Response.Success -> _uiState.update { it.copy(artists = response.data) }
            }
        }
    }
}