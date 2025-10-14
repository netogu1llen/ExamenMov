package com.app.examen.data.local.model

import com.app.examen.domain.model.Country

data class CountryCache(
    val countryList: List<Country>,
    val lastUpdate: Long,
)
