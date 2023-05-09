package com.herdal.deezerapp.domain.usecase

import com.herdal.deezerapp.core.Response
import com.herdal.deezerapp.domain.repository.AlbumRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetArtistAlbumsUseCase @Inject constructor(
    private val albumRepository: AlbumRepository
) {
    operator fun invoke(artistId: Int) = flow {
        try {
            emit(Response.Loading())
            val albums = albumRepository.fetchArtistAlbums(artistId = artistId)
            emit(Response.Success(data = albums))
        } catch (e: HttpException) {
            emit(Response.Error(message = e.message))
        } catch (e: IOException) {
            emit(Response.Error(message = e.message))
        }
    }
}