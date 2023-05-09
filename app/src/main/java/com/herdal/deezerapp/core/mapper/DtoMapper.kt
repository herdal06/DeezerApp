package com.herdal.deezerapp.core.mapper

interface DtoMapper<Dto, Domain> {
    fun toDomain(dto: Dto): Domain
    fun fromDomain(domain: Domain): Dto
}