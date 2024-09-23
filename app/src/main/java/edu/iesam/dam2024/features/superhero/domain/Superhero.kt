package edu.iesam.dam2024.features.superhero.domain

data class Superhero(
    val principalData: SuperheroPrincipalData,
    val biography: SuperheroBiography,
    val work: SuperheroWork,
    val connections: SuperheroConnection,
    val images: List<String>
)

data class SuperheroPrincipalData(
    val id: String,
    val name: String,
    val slug: String,
    val imageUrl: String,
    val stats: PowerStats,
    val appearance: Appearance
)

data class PowerStats(
    val intelligence: Int,
    val strength: Int,
    val speed: Int,
    val durability: Int,
    val power: Int,
    val combat: Int
)

data class Appearance(
    val gender: String,
    val race: String,
    val height: Height,
    val weight: Weight,
    val eyeColor: String,
    val hairColor: String
)

data class Height(
    val cm: String,
    val inches: String
)

data class Weight(
    val kg: String,
    val pounds: String
)

data class SuperheroBiography(
    val fullName: String,
    val alterEgos: String,
    val aliases: List<String>,
    val placeOfBirth: String,
    val firstAppearance: String,
    val publisher: String,
    val alignment: String
)

data class SuperheroWork(
    val occupation: String,
    val base: String
)

data class SuperheroConnection(
    val groupAffiliation: String,
    val relatives: String,
)