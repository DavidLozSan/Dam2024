package edu.iesam.dam2024.features.movies.presentation

import android.content.Context
import edu.iesam.dam2024.features.movies.data.MovieDataRepository
import edu.iesam.dam2024.features.movies.data.local.MovieXmlLocalDataSource
import edu.iesam.dam2024.features.movies.data.remote.MovieMockRemoteDataSource
import edu.iesam.dam2024.features.movies.domain.GetMovieUseCase
import edu.iesam.dam2024.features.movies.domain.GetMoviesUseCase

class MovieFactory(private val context: Context) {

    private val movieRemote = MovieMockRemoteDataSource()
    private val movieLocal = MovieXmlLocalDataSource(context)
    private val movieDataRepository = MovieDataRepository(movieLocal, movieRemote)
    private val getMovieUseCase = GetMovieUseCase(movieDataRepository)
    private val getMoviesUseCase = GetMoviesUseCase(movieDataRepository)

    fun buildViewModel(): MovieViewModel {
        return MovieViewModel(getMoviesUseCase, getMovieUseCase)
    }
}