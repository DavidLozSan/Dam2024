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
        val superheroesFromLocal = local.findAll()
        if (superheroesFromLocal.isEmpty()) {
            val superheroesFromRemote = remoteDataSource.getSuperheroes()
            local.saveAll(superheroesFromRemote)
            return superheroesFromRemote
        } else {
            return superheroesFromLocal
        }
    }

    override fun getSuperhero(superheroId: String): Superhero? {
        val superheroFromLocal = local.findById(superheroId)
        return if (superheroFromLocal != null) {
            superheroFromLocal
        } else {
            val superheroFromRemote = remoteDataSource.getSuperhero(superheroId)
            superheroFromRemote?.let {
                local.saveAll(remoteDataSource.getSuperheroes())
            }
            return superheroFromRemote
        }
    }

}