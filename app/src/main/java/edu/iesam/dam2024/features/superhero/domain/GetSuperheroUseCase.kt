package edu.iesam.dam2024.features.superhero.domain

class GetSuperheroUseCase(private val superheroRepository: SuperheroRepository) {

    operator suspend fun invoke(superheroId: String): Superhero? {
        return superheroRepository.getSuperhero(superheroId)
    }
}