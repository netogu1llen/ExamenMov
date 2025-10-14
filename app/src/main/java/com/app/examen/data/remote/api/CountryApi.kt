package com.app.examen.data.remote.api

import com.app.examen.data.remote.dto.CountryDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CountryApi {
    @GET("all")
    suspend fun getAllCountries(): List<CountryDto>

    @GET("name/{name}")
    suspend fun getCountryByName(
        @Path("name") name: String,
    ): List<CountryDto>
}
