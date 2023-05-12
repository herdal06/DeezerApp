package com.herdal.deezerapp.data.datasource

import com.herdal.deezerapp.data.remote.dto.genre.GenreResponse

interface GenreDataSource {
    interface Remote {
        suspend fun fetchCategories(): GenreResponse
    }
}