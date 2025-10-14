package com.app.examen.presentation.screens.detail

import com.app.examen.domain.model.Country

data class CountryDetailUiState(
    val country: Country? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
)
