package com.herdal.deezerapp.data.remote.datasource

import com.herdal.deezerapp.data.datasource.GenreDataSource
import com.herdal.deezerapp.data.remote.dto.genre.GenreResponse
import com.herdal.deezerapp.data.remote.service.GenreService
import com.herdal.deezerapp.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GenreRemoteDataSource @Inject constructor(
    private val genreService: GenreService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : GenreDataSource.Remote {
    override suspend fun fetchCategories(): GenreResponse =
        withContext(ioDispatcher) {
            genreService.fetchCategories()
        }
}