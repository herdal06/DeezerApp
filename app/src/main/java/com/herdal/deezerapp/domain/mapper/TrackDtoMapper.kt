package com.herdal.deezerapp.domain.mapper

import com.herdal.deezerapp.core.mapper.DtoMapper
import com.herdal.deezerapp.data.remote.dto.track.TrackDto
import com.herdal.deezerapp.domain.uimodel.Track
import com.herdal.deezerapp.utils.extensions.toSeconds
import com.herdal.deezerapp.utils.extensions.toTimeString

class TrackDtoMapper : DtoMapper<TrackDto, Track> {
    override fun toDomain(dto: TrackDto): Track =
        Track(
            id = dto.id,
            title = dto.title,
            duration = dto.duration?.toTimeString(),
            image = dto.image,
            preview = dto.preview
        )

    override fun fromDomain(domain: Track): TrackDto =
        TrackDto(
            id = domain.id,
            title = domain.title,
            duration = domain.duration?.toSeconds(),
            image = domain.image,
            preview = domain.preview
        )

    fun toDomainList(dtoList: List<TrackDto>): List<Track> =
        dtoList.map { toDomain(it) }
}