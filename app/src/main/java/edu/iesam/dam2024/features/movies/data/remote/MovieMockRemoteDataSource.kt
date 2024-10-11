package edu.iesam.dam2024.features.movies.data.remote

import edu.iesam.dam2024.features.movies.domain.Movie

class MovieMockRemoteDataSource {

    private val movies = listOf(
        Movie(
            "1",
            "Inception",
            "https://m.media-amazon.com/images/I/714Mwnmg2mL._AC_UF1000,1000_QL80_.jpg"
        ),
        Movie(
            "2", " The Godfather",
            "https://i.ebayimg.com/images/g/kWQAAOSwArteJsIw/s-l1200.jpg"
        ),
        Movie(
            "3",
            "Parasite",
            "https://i.etsystatic.com/18242346/r/il/87bc12/2184703308/il_fullxfull.2184703308_jpnu.jpg"
        ),
        Movie(title = "title4", poster = "poster4", id = "4")
    )

    fun getMovies(): List<Movie> {
        return movies
    }

    fun getMovieById(movieId: String): Movie? {
        return getMovies().firstOrNull { movie -> movie.id == movieId }
    }
}