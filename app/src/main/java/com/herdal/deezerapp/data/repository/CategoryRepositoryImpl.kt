package com.herdal.deezerapp.data.repository

import com.herdal.deezerapp.data.datasource.CategoryDataSource
import com.herdal.deezerapp.domain.mapper.CategoryDtoMapper
import com.herdal.deezerapp.domain.repository.CategoryRepository
import com.herdal.deezerapp.domain.uimodel.Category
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val remote: CategoryDataSource.Remote,
    private val categoryDtoMapper: CategoryDtoMapper
) : CategoryRepository {
    override suspend fun getCategories(): List<Category> {
        val remoteCategories = remote.fetchCategories()
        return categoryDtoMapper.toDomainList(remoteCategories.categories)
    }
}