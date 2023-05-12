package com.herdal.deezerapp.domain.usecase

import com.herdal.deezerapp.core.Response
import com.herdal.deezerapp.domain.repository.ArtistRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetArtistsByGenreUseCase @Inject constructor(
    private val artistRepository: ArtistRepository
) {
    operator fun invoke(genreId: Int) = flow {
        try {
            emit(Response.Loading())
            val artists = artistRepository.fetchArtistsByGenre(genreId = genreId)
            emit(Response.Success(data = artists))
        } catch (e: HttpException) {
            emit(Response.Error(message = e.message))
        } catch (e: IOException) {
            emit(Response.Error(message = e.message))
        }
    }
}