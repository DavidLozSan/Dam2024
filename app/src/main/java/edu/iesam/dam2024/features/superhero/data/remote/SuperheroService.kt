package edu.iesam.dam2024.features.superhero.data.remote

import edu.iesam.dam2024.features.superhero.domain.Superhero
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SuperheroService {

    //REST API: POST, GET, DELETE, PUT, PATCH
    @GET("all.json")
    suspend fun requestSuperheroes(): Response<List<Superhero>>

    @GET("id/{superheroId}.json")
    suspend fun requestSuperhero(@Path("superheroId") superheroId: String): Response<Superhero>
}