package edu.iesam.dam2024.features.movies.data.remote

import edu.iesam.dam2024.features.movies.domain.Movie

class MovieMockRemoteDataSource {

    private val movies = listOf(
        Movie("1", "title1", "poster1"),
        Movie("2", "title2", "poster2"),
        Movie("3", "title3", "https://www.soydemadrid.com/images/thumbs/origenes-secretosla-nueva-pelicula-de-netflix-con--0051968.jpeg"),
        Movie(title = "title4", poster = "poster4", id = "4")
    )

    fun getMovies(): List<Movie> {
        return movies
    }

    fun getMovieById(movieId: String): Movie? {
        return getMovies().firstOrNull { movie -> movie.id == movieId }
    }
}