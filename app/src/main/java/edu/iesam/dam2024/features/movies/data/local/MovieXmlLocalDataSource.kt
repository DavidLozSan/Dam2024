package edu.iesam.dam2024.features.movies.data.local

import com.google.gson.Gson
import android.content.Context
import edu.iesam.dam2024.R
import edu.iesam.dam2024.features.movies.domain.Movie
import edu.iesam.dam2024.features.superhero.domain.Superhero

class MovieXmlLocalDataSource(private val context: Context) {
    private val sharedPref = context.getSharedPreferences(
        context.getString(R.string.name_file_xml), Context.MODE_PRIVATE
    )

    private val gson = Gson()

    fun save(movie: Movie) {
        val editor = sharedPref.edit()
        editor.putString(movie.id, gson.toJson(movie))
        editor.apply()
    }

    fun findById(movieId: String): Movie? {
        return sharedPref.getString(movieId, null)?.let { movie ->
            gson.fromJson(movie, Movie::class.java)
        }
    }

    fun delete() {
        sharedPref.edit().clear().apply()
    }

    fun saveAll(movies: List<Movie>) {
        val editor = sharedPref.edit()
        movies.forEach { movie ->
            editor.putString(movie.id, gson.toJson(movie))
        }
        editor.apply()
    }

    fun findAll(): List<Movie> {
        val movies = ArrayList<Movie>()
        val mapMovie = sharedPref.all //as Map<String, String>
        mapMovie.values.forEach { jsonMovie ->
            val movie = gson.fromJson(jsonMovie as String, Movie::class.java)
            movies.add(movie)
        }
        return movies
    }

    fun deleteById(id: String) {
        sharedPref.edit().remove(id).apply()
    }
}