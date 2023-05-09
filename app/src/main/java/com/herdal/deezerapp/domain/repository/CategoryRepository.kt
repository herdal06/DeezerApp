package com.herdal.deezerapp.domain.repository

import com.herdal.deezerapp.domain.uimodel.Category

interface CategoryRepository {
    suspend fun getCategories(): List<Category>
}