package edu.iesam.dam2024.features.movies.data

import edu.iesam.dam2024.features.movies.data.local.MovieXmlLocalDataSource
import edu.iesam.dam2024.features.movies.data.remote.MovieMockRemoteDataSource
import edu.iesam.dam2024.features.movies.domain.Movie
import edu.iesam.dam2024.features.movies.domain.MovieRepository

class MovieDataRepository(
    private val local: MovieXmlLocalDataSource,
    private val mockRemoteDataSource: MovieMockRemoteDataSource
) : MovieRepository {

    override fun getMovies(): List<Movie> {
        val moviesFromLocal = local.findAll()
        if (moviesFromLocal.isEmpty()) {
            val moviesFromRemote = mockRemoteDataSource.getMovies()
            local.saveAll(moviesFromRemote)
            return moviesFromRemote
        } else {
            return moviesFromLocal
        }
    }

    override fun getMovieById(movieId: String): Movie? {
        val movieFromLocal = local.findById(movieId)
        if (movieFromLocal == null) {
            mockRemoteDataSource.getMovieById(movieId)?.let {
                local.save(it)
                return it
            }
        }
        return movieFromLocal
    }
}