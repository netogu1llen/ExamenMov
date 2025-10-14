package com.app.examen.data.remote.api

import com.app.examen.data.remote.dto.CountryDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CountryApi {
    // Usar el endpoint de región, no me sirvió get all
    @GET("region/americas")
    suspend fun getAmericasCountries(): List<CountryDto>

    @GET("region/europe")
    suspend fun getEuropeCountries(): List<CountryDto>

    @GET("region/africa")
    suspend fun getAfricaCountries(): List<CountryDto>

    @GET("region/asia")
    suspend fun getAsiaCountries(): List<CountryDto>

    @GET("region/oceania")
    suspend fun getOceaniaCountries(): List<CountryDto>

    @GET("name/{name}")
    suspend fun getCountryByName(
        @Path("name") name: String,
    ): List<CountryDto>
}
