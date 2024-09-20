package edu.iesam.dam2024.features.superhero.domain

data class Superhero(
    val principalData: SuperheroPrincipalData,
    val biography: SuperheroBiography,
    val work: SuperheroWork
)

data class SuperheroPrincipalData(
    val id: String,
    val name: String,
    val imageUrl: String,
    val stats: List<String>
)

data class SuperheroBiography (
    val fullName:String,
    val alignment:String
)

data class SuperheroWork (
    val occupation:String
)