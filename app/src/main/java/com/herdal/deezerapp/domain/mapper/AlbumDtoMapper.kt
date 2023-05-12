package com.herdal.deezerapp.domain.mapper

import com.herdal.deezerapp.core.mapper.DtoMapper
import com.herdal.deezerapp.data.remote.dto.album.AlbumDto
import com.herdal.deezerapp.domain.uimodel.Album
import com.herdal.deezerapp.utils.extensions.toFormattedDate

class AlbumDtoMapper : DtoMapper<AlbumDto, Album> {
    override fun toDomain(dto: AlbumDto): Album =
        Album(
            id = dto.id,
            title = dto.title,
            cover = dto.cover,
            releaseDate = dto.releaseDate.toFormattedDate()
        )

    override fun fromDomain(domain: Album): AlbumDto =
        AlbumDto(
            id = domain.id,
            title = domain.title,
            cover = domain.cover,
            releaseDate = domain.releaseDate
        )

    fun toDomainList(dtoList: List<AlbumDto>): List<Album> =
        dtoList.map { toDomain(it) }
}