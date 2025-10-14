package com.app.examen.data.repository

import com.app.examen.data.local.preferences.CountryPreferences
import com.app.examen.data.mapper.toDomain
import com.app.examen.data.remote.api.CountryApi
import com.app.examen.domain.model.Country
import com.app.examen.domain.repository.CountryRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CountryRepositoryImpl
    @Inject
    constructor(
        private val api: CountryApi,
        private val preferences: CountryPreferences,
    ) : CountryRepository {
        override suspend fun getAllCountries(): List<Country> {
            // Intentar obtener del caché primero
            preferences.getCountryCache()?.let { cache ->
                if (preferences.isCacheValid()) {
                    return cache.countryList
                }
            }

            return try {
                val response = api.getAllCountries()
                val countries = response.map { it.toDomain() }

                // Guardar en caché
                preferences.saveCountryList(countries)

                countries
            } catch (e: Exception) {
                // Si hay error, usar caché aunque haya expirado
                preferences.getCountryCache()?.countryList ?: throw e
            }
        }

        override suspend fun getCountryByName(name: String): Country {
            // Intentar buscar en caché primero
            preferences.getCountryCache()?.let { cache ->
                if (preferences.isCacheValid()) {
                    cache.countryList
                        .find {
                            it.name.equals(name, ignoreCase = true)
                        }?.let { return it }
                }
            }

            return try {
                val response = api.getCountryByName(name)
                response.firstOrNull()?.toDomain()
                    ?: throw Exception("Country not found")
            } catch (e: Exception) {
                preferences.getCountryCache()?.countryList?.find {
                    it.name.equals(name, ignoreCase = true)
                } ?: throw e
            }
        }

        override suspend fun searchCountries(query: String): List<Country> {
            val allCountries = getAllCountries()
            return allCountries.filter { country ->
                country.name.contains(query, ignoreCase = true) ||
                    country.capital?.contains(query, ignoreCase = true) == true
            }
        }
    }
