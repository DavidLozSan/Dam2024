package edu.iesam.dam2024.features.superhero.data.remote

import edu.iesam.dam2024.features.superhero.domain.Superhero

class SuperheroApiRemoteDataSource(private val superheroService: SuperheroService) {

    suspend fun getSuperheroes(): List<Superhero> {
        superheroService.requestSuperheroes().body()

        return emptyList()
    }

    suspend fun getSuperhero(superheroId: String): Superhero? {
        return superheroService.requestSuperhero(superheroId).body()
    }
}