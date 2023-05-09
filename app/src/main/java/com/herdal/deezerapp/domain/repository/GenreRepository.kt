package com.herdal.deezerapp.domain.repository

import com.herdal.deezerapp.domain.uimodel.Genre

interface GenreRepository {
    suspend fun getCategories(): List<Genre>
}