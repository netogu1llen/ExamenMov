package com.app.examen.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CountryDto(
    @SerializedName("name") val name: NameDto,
    @SerializedName("capital") val capital: List<String>?,
    @SerializedName("region") val region: String,
    @SerializedName("population") val population: Long,
    @SerializedName("area") val area: Double?,
    @SerializedName("languages") val languages: Map<String, String>?,
    @SerializedName("currencies") val currencies: Map<String, CurrencyDto>?,
    @SerializedName("flags") val flags: FlagsDto,
    @SerializedName("coatOfArms") val coatOfArms: CoatOfArmsDto?,
    @SerializedName("maps") val maps: MapsDto,
    @SerializedName("timezones") val timezones: List<String>,
) {
    data class NameDto(
        @SerializedName("common") val common: String,
        @SerializedName("official") val official: String,
    )

    data class CurrencyDto(
        @SerializedName("name") val name: String,
        @SerializedName("symbol") val symbol: String?,
    )

    data class FlagsDto(
        @SerializedName("png") val png: String,
        @SerializedName("svg") val svg: String,
    )

    data class CoatOfArmsDto(
        @SerializedName("png") val png: String?,
        @SerializedName("svg") val svg: String?,
    )

    data class MapsDto(
        @SerializedName("googleMaps") val googleMaps: String,
        @SerializedName("openStreetMaps") val openStreetMaps: String,
    )
}
