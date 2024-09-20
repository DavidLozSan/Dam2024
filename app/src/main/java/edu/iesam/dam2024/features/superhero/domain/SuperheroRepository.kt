package edu.iesam.dam2024.features.superhero.domain

interface SuperheroRepository {

    fun getSuperheroes(): List<Superhero>
}