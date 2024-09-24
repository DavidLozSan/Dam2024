package edu.iesam.dam2024.features.superhero.data

import edu.iesam.dam2024.features.superhero.data.remote.SuperheroMockRemoteDataSource
import edu.iesam.dam2024.features.superhero.domain.Superhero
import edu.iesam.dam2024.features.superhero.domain.SuperheroRepository

class SuperheroDataRepository(
    private val mockRemoteDataSource: SuperheroMockRemoteDataSource
) : SuperheroRepository {

    override fun getSuperheroes(): List<Superhero> {
        return mockRemoteDataSource.getSuperheroes()
    }

    override fun getSuperhero(superheroId: String): Superhero? {
        return mockRemoteDataSource.getSuperhero(superheroId)
    }

}