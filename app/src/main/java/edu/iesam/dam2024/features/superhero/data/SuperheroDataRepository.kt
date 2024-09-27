package edu.iesam.dam2024.features.superhero.data

import edu.iesam.dam2024.features.superhero.data.local.SuperheroXmlLocalDataSource
import edu.iesam.dam2024.features.superhero.data.remote.SuperheroMockRemoteDataSource
import edu.iesam.dam2024.features.superhero.domain.Superhero
import edu.iesam.dam2024.features.superhero.domain.SuperheroRepository

class SuperheroDataRepository(
    private val local: SuperheroXmlLocalDataSource,
    private val remoteDataSource: SuperheroMockRemoteDataSource
) : SuperheroRepository {

    override fun getSuperheroes(): List<Superhero> {
        return remoteDataSource.getSuperheroes()
    }

    override fun getSuperhero(superheroId: String): Superhero? {
        return remoteDataSource.getSuperhero(superheroId)
    }

}