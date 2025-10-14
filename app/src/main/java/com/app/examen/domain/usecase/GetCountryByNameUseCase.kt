package com.app.examen.domain.usecase

import com.app.examen.domain.common.Result
import com.app.examen.domain.model.Country
import com.app.examen.domain.repository.CountryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCountryByNameUseCase
    @Inject
    constructor(
        private val repository: CountryRepository,
    ) {
        operator fun invoke(name: String): Flow<Result<Country>> =
            flow {
                try {
                    emit(Result.Loading)
                    val country = repository.getCountryByName(name)
                    emit(Result.Success(country))
                } catch (e: Exception) {
                    emit(Result.Error(e))
                }
            }
    }
