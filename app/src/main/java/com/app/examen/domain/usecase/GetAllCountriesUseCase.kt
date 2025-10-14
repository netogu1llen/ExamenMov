package com.app.examen.domain.usecase

import com.app.examen.domain.common.Result
import com.app.examen.domain.model.Country
import com.app.examen.domain.repository.CountryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllCountriesUseCase
    @Inject
    constructor(
        private val repository: CountryRepository,
    ) {
        operator fun invoke(): Flow<Result<List<Country>>> =
            flow {
                try {
                    emit(Result.Loading)
                    val countries = repository.getAllCountries()
                    emit(Result.Success(countries))
                } catch (e: Exception) {
                    emit(Result.Error(e))
                }
            }
    }
