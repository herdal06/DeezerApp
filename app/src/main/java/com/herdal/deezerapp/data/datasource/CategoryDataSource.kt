package com.herdal.deezerapp.data.datasource

import com.herdal.deezerapp.data.remote.dto.category.CategoryResponse

interface CategoryDataSource {
    interface Remote {
        suspend fun fetchCategories(): CategoryResponse
    }
}