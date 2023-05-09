package com.herdal.deezerapp.data.remote.service

import com.herdal.deezerapp.data.remote.dto.category.CategoryResponse
import retrofit2.http.GET

interface CategoryService {

    @GET("genre")
    suspend fun fetchCategories(): CategoryResponse
}