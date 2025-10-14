package com.app.examen.presentation.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.examen.domain.common.Result
import com.app.examen.domain.usecase.GetCountryByNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryDetailViewModel
    @Inject
    constructor(
        private val getCountryByNameUseCase: GetCountryByNameUseCase,
    ) : ViewModel() {
        private val _uiState = MutableStateFlow(CountryDetailUiState())
        val uiState: StateFlow<CountryDetailUiState> = _uiState.asStateFlow()

        fun getCountry(name: String) {
            viewModelScope.launch {
                getCountryByNameUseCase(name).collect { result ->
                    _uiState.update { state ->
                        when (result) {
                            is Result.Loading -> state.copy(isLoading = true)
                            is Result.Success ->
                                state.copy(
                                    country = result.data,
                                    isLoading = false,
                                    error = null,
                                )
                            is Result.Error ->
                                state.copy(
                                    error = result.exception.message,
                                    isLoading = false,
                                )
                            else -> state
                        }
                    }
                }
            }
        }
    }
