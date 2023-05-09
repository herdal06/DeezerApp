package com.herdal.deezerapp.domain.mapper

import com.herdal.deezerapp.core.mapper.DtoMapper
import com.herdal.deezerapp.data.remote.dto.artist.ArtistDto
import com.herdal.deezerapp.domain.uimodel.Artist

class ArtistDtoMapper : DtoMapper<ArtistDto, Artist> {
    override fun toDomain(dto: ArtistDto): Artist =
        Artist(
            id = dto.id,
            name = dto.name,
            picture = dto.picture
        )

    override fun fromDomain(domain: Artist): ArtistDto =
        ArtistDto(
            id = domain.id,
            name = domain.name,
            picture = domain.picture
        )

    fun toDomainList(dtoList: List<ArtistDto>): List<Artist> =
        dtoList.map { toDomain(it) }
}