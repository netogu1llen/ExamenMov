package com.app.examen.presentation.screens.home

import com.app.examen.domain.model.Country

data class HomeUiState(
    val countryList: List<Country> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
)
