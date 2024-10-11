package edu.iesam.dam2024.features.superhero.presentation

import android.content.Context
import edu.iesam.dam2024.features.superhero.data.SuperheroDataRepository
import edu.iesam.dam2024.features.superhero.data.local.SuperheroXmlLocalDataSource
import edu.iesam.dam2024.features.superhero.data.remote.SuperheroMockRemoteDataSource
import edu.iesam.dam2024.features.superhero.domain.GetSuperheroUseCase
import edu.iesam.dam2024.features.superhero.domain.GetSuperheroesUseCase


class SuperheroFactory(private val context: Context) {

    private val superheroRemote = SuperheroMockRemoteDataSource()
    private val superheroLocal = SuperheroXmlLocalDataSource(context)
    private val superheroDataRepository = SuperheroDataRepository(superheroLocal, superheroRemote)
    private val getSuperheroUseCase = GetSuperheroUseCase(superheroDataRepository)
    private val getSuperheroesUseCase = GetSuperheroesUseCase(superheroDataRepository)

    fun buildSuperheroListViewModel(): SuperheroListViewModel {
        return SuperheroListViewModel(getSuperheroesUseCase)
    }

    fun buildSuperheroDetailViewModel(): SuperheroDetailViewModel {
        return SuperheroDetailViewModel(getSuperheroUseCase)
    }
}