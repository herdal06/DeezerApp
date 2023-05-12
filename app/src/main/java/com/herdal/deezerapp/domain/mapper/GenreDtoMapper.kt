package com.herdal.deezerapp.domain.mapper

import com.herdal.deezerapp.core.mapper.DtoMapper
import com.herdal.deezerapp.data.remote.dto.genre.GenreDto
import com.herdal.deezerapp.domain.uimodel.Genre

class GenreDtoMapper : DtoMapper<GenreDto, Genre> {
    override fun toDomain(dto: GenreDto): Genre =
        Genre(
            id = dto.id,
            name = dto.name,
            picture = dto.picture
        )

    override fun fromDomain(domain: Genre): GenreDto =
        GenreDto(
            id = domain.id,
            name = domain.name,
            picture = domain.picture
        )

    fun toDomainList(dtoList: List<GenreDto>): List<Genre> =
        dtoList.map { toDomain(it) }

    fun fromDomainList(domainList: List<Genre>): List<GenreDto> =
        domainList.map { fromDomain(it) }
}