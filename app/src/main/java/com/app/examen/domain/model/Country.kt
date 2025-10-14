package com.app.examen.domain.model

data class Country(
    val name: String,
    val capital: String?,
    val region: String,
    val population: Long,
    val area: Double?,
    val languages: List<String>,
    val currencies: List<String>,
    val flag: String,
    val coatOfArms: String?,
    val timezones: List<String>,
)
