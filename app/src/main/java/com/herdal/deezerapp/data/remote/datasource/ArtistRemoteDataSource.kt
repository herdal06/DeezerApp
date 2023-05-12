package com.herdal.deezerapp.data.remote.datasource

import com.herdal.deezerapp.data.datasource.ArtistDataSource
import com.herdal.deezerapp.data.remote.dto.artist.ArtistDto
import com.herdal.deezerapp.data.remote.dto.artist.ArtistResponse
import com.herdal.deezerapp.data.remote.service.ArtistService
import com.herdal.deezerapp.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ArtistRemoteDataSource @Inject constructor(
    private val artistService: ArtistService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ArtistDataSource.Remote {
    override suspend fun fetchArtistsByGenre(genreId: Int): ArtistResponse =
        withContext(ioDispatcher) {
            artistService.fetchArtistsByGenre(genreId = genreId)
        }

    override suspend fun getArtistById(id: Int): ArtistDto =
        withContext(ioDispatcher) {
            artistService.getArtistById(id = id)
        }
}