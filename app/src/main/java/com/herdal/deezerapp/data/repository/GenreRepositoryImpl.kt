package com.herdal.deezerapp.data.repository

import com.herdal.deezerapp.data.datasource.GenreDataSource
import com.herdal.deezerapp.domain.mapper.GenreDtoMapper
import com.herdal.deezerapp.domain.repository.GenreRepository
import com.herdal.deezerapp.domain.uimodel.Genre
import javax.inject.Inject

class GenreRepositoryImpl @Inject constructor(
    private val remote: GenreDataSource.Remote,
    private val genreDtoMapper: GenreDtoMapper
) : GenreRepository {
    override suspend fun getCategories(): List<Genre> {
        val remoteCategories = remote.fetchCategories()
        return genreDtoMapper.toDomainList(remoteCategories.categories)
    }
}