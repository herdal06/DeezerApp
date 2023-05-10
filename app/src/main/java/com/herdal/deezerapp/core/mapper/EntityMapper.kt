package com.herdal.deezerapp.core.mapper

interface EntityMapper<Entity, Domain> {
    fun toDomain(entity: Entity): Domain
    fun fromDomain(domain: Domain): Entity
}