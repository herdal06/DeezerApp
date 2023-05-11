package com.herdal.deezerapp.domain.usecase

import com.herdal.deezerapp.domain.repository.TrackRepository
import com.herdal.deezerapp.domain.uimodel.Track
import javax.inject.Inject

class AddOrRemoveTrackFromFavoriteUseCase @Inject constructor(
    private val trackRepository: TrackRepository,
) {
    suspend fun execute(track: Track) {
        track.id?.let { id ->
            if (trackRepository.isTrackFavorite(id)) {
                trackRepository.deleteTrackFromFavorite(track.copy(isFavorite = false))
            } else {
                trackRepository.addTrackToFavorite(track.copy(isFavorite = true))
            }
        }
    }
}