package com.app.examen.domain.repository

import com.app.examen.domain.model.Country

interface CountryRepository {
    suspend fun getAllCountries(): List<Country>

    suspend fun getCountryByName(name: String): Country

    suspend fun searchCountries(query: String): List<Country>
}
