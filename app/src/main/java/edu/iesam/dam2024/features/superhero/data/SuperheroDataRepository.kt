package edu.iesam.dam2024.features.superhero.data

import edu.iesam.dam2024.features.superhero.data.local.SuperheroXmlLocalDataSource
import edu.iesam.dam2024.features.superhero.data.remote.SuperheroApiRemoteDataSource
import edu.iesam.dam2024.features.superhero.data.remote.SuperheroMockRemoteDataSource
import edu.iesam.dam2024.features.superhero.domain.Superhero
import edu.iesam.dam2024.features.superhero.domain.SuperheroRepository

class SuperheroDataRepository(
    private val remoteDataSource: SuperheroApiRemoteDataSource,
    private val local: SuperheroXmlLocalDataSource
    //private val remoteDataSource: SuperheroMockRemoteDataSource
) : SuperheroRepository {

    override suspend fun getSuperheroes(): List<Superhero> {
        val superheroesFromLocal = local.findAll()
        if (superheroesFromLocal.isEmpty()) {
            val superheroesFromRemote = remoteDataSource.getSuperheroes()
            local.saveAll(superheroesFromRemote)
            return superheroesFromRemote
        } else {
            return superheroesFromLocal
        }
    }

    override suspend fun getSuperhero(superheroId: String): Superhero? {
        val localSuperhero = local.findById(superheroId)
        if (localSuperhero == null) {
            remoteDataSource.getSuperhero(superheroId)?.let {
                local.save(it)
                return it
            }
        }
        return localSuperhero
    }
    /* override fun getSuperheroes(): List<Superhero> {
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
        val localSuperhero = local.findById(superheroId)
        if (localSuperhero == null) {
            remoteDataSource.getSuperhero(superheroId)?.let {
                local.save(it)
                return it
            }
        }
        return localSuperhero
    } */

}