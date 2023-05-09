package com.herdal.deezerapp.domain.mapper

import com.herdal.deezerapp.core.mapper.DtoMapper
import com.herdal.deezerapp.data.remote.dto.category.CategoryDto
import com.herdal.deezerapp.domain.uimodel.Category

class CategoryDtoMapper : DtoMapper<CategoryDto, Category> {
    override fun toDomain(dto: CategoryDto): Category =
        Category(
            id = dto.id,
            name = dto.name,
            picture = dto.picture
        )

    override fun fromDomain(domain: Category): CategoryDto =
        CategoryDto(
            id = domain.id,
            name = domain.name,
            picture = domain.picture
        )

    fun toDomainList(dtoList: List<CategoryDto>): List<Category> =
        dtoList.map { toDomain(it) }

    fun fromDomainList(domainList: List<Category>): List<CategoryDto> =
        domainList.map { fromDomain(it) }
}