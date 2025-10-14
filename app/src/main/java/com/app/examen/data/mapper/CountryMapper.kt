package com.app.examen.data.mapper

import com.app.examen.data.remote.dto.CountryDto
import com.app.examen.domain.model.Country

fun CountryDto.toDomain(): Country =
    Country(
        name = name.common,
        capital = capital?.firstOrNull(),
        region = region,
        population = population,
        area = area,
        languages = languages?.values?.toList() ?: emptyList(),
        currencies = currencies?.values?.map { "${it.name} (${it.symbol ?: ""})" } ?: emptyList(),
        flag = flags.png,
        coatOfArms = coatOfArms?.png,
        maps = maps.googleMaps,
        timezones = timezones,
    )
