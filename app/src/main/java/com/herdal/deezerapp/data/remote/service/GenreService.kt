package com.herdal.deezerapp.data.remote.service

import com.herdal.deezerapp.data.remote.dto.genre.GenreResponse
import retrofit2.http.GET

interface GenreService {

    @GET("genre")
    suspend fun fetchCategories(): GenreResponse
}