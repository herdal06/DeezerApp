package com.herdal.deezerapp.data.remote.datasource

import com.herdal.deezerapp.data.datasource.CategoryDataSource
import com.herdal.deezerapp.data.remote.dto.category.CategoryResponse
import com.herdal.deezerapp.data.remote.service.CategoryService
import com.herdal.deezerapp.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CategoryRemoteDataSource @Inject constructor(
    private val categoryService: CategoryService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : CategoryDataSource.Remote {
    override suspend fun fetchCategories(): CategoryResponse =
        withContext(ioDispatcher) {
            categoryService.fetchCategories()
        }
}