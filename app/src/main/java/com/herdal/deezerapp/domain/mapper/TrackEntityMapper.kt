package com.herdal.deezerapp.domain.mapper

import com.herdal.deezerapp.core.mapper.EntityMapper
import com.herdal.deezerapp.data.local.entity.track.TrackEntity
import com.herdal.deezerapp.domain.uimodel.Track

class TrackEntityMapper : EntityMapper<TrackEntity, Track> {
    override fun toDomain(entity: TrackEntity): Track =
        Track(
            id = entity.id,
            title = entity.title,
            duration = entity.duration,
            image = entity.image,
            preview = entity.preview,
            isFavorite = entity.isFavorite
        )

    override fun fromDomain(domain: Track): TrackEntity =
        TrackEntity(
            id = domain.id,
            title = domain.title,
            duration = domain.duration,
            image = domain.image,
            preview = domain.preview,
            isFavorite = domain.isFavorite
        )
}