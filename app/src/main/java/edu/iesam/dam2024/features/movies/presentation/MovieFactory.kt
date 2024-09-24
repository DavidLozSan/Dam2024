package edu.iesam.dam2024.features.movies.presentation

import edu.iesam.dam2024.features.movies.data.MovieDataRepository
import edu.iesam.dam2024.features.movies.data.remote.MovieMockRemoteDataSource
import edu.iesam.dam2024.features.movies.domain.GetMovieByIdUseCase
import edu.iesam.dam2024.features.movies.domain.GetMoviesUseCase

class MovieFactory {

    private val instance: MovieDataRepository = MovieDataRepository(MovieMockRemoteDataSource())

    fun buildViewModel(): MovieViewModel {
        
        return MovieViewModel(
            GetMoviesUseCase(instance),
            GetMovieByIdUseCase(instance)
        )
    }
}