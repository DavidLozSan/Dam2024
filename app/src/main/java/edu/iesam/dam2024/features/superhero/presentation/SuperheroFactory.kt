package edu.iesam.dam2024.features.superhero.presentation

import edu.iesam.dam2024.features.superhero.data.SuperheroDataRepository
import edu.iesam.dam2024.features.superhero.data.remote.SuperheroMockRemoteDataSource
import edu.iesam.dam2024.features.superhero.domain.GetSuperheroUseCase
import edu.iesam.dam2024.features.superhero.domain.GetSuperheroesUseCase


class SuperheroFactory {

    private val instance: SuperheroDataRepository =
        SuperheroDataRepository(SuperheroMockRemoteDataSource())

    fun buildViewModel(): SuperheroViewModel {
        return SuperheroViewModel(
            GetSuperheroesUseCase(instance),
            GetSuperheroUseCase(instance)
        )
    }
}