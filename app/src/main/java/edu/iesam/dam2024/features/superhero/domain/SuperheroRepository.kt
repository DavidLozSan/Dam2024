package edu.iesam.dam2024.features.superhero.domain

interface SuperheroRepository {

    suspend fun getSuperheroes(): List<Superhero>

    suspend fun getSuperhero(superheroId: String): Superhero?
}