package com.herdal.deezerapp.domain.usecase

import com.herdal.deezerapp.core.Response
import com.herdal.deezerapp.domain.repository.TrackRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetTracksByAlbumUseCase @Inject constructor(
    private val trackRepository: TrackRepository
) {
    operator fun invoke(albumId: Int) = flow {
        try {
            emit(Response.Loading())
            val tracks = trackRepository.fetchTracksByAlbum(albumId = albumId)
            emit(Response.Success(data = tracks))
        } catch (e: HttpException) {
            emit(Response.Error(message = e.message))
        } catch (e: IOException) {
            emit(Response.Error(message = e.message))
        }
    }
}