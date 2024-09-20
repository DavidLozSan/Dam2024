package edu.iesam.dam2024.features.superhero.domain

class GetSuperheroesUseCase(private val superheroRepository: SuperheroRepository) {

    operator fun invoke():List<Superhero> {
        return superheroRepository.getSuperheroes()
    }
}