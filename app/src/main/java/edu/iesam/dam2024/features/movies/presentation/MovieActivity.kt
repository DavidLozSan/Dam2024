package edu.iesam.dam2024.features.movies.presentation

import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import edu.iesam.dam2024.R
import edu.iesam.dam2024.features.movies.data.local.MovieXmlLocalDataSource
import edu.iesam.dam2024.features.movies.domain.Movie

class MovieActivity : AppCompatActivity() {

    private lateinit var movieFactory: MovieFactory
    private lateinit var viewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        movieFactory = MovieFactory(this)
        viewModel = movieFactory.buildViewModel()

        val movies = viewModel.viewCreated()
        binData(movies)
        testXml()
        testListXml()
        testFindById()
    }

    private fun binData(movies: List<Movie>) {
        findViewById<TextView>(R.id.movie_id_1).text = movies[0].id
        findViewById<TextView>(R.id.movie_title_1).text = movies[0].title
        findViewById<LinearLayout>(R.id.layout_1).setOnClickListener {
            val movie1: Movie? = viewModel.itemSelected(movies[0].id)
            movie1?.let {
                Log.d("@dev", "Pelicula seleccionada: ${it.title}")
            }
        }

        findViewById<TextView>(R.id.movie_id_2).text = movies[1].id
        findViewById<TextView>(R.id.movie_title_2).text = movies[1].title
        findViewById<LinearLayout>(R.id.layout_2).setOnClickListener {
            val movie2: Movie? = viewModel.itemSelected(movies[1].id)
            movie2?.let {
                Log.d("@dev", "Pelicula seleccionada: ${it.title}")
            }
        }

        findViewById<TextView>(R.id.movie_id_3).text = movies[2].id
        findViewById<TextView>(R.id.movie_title_3).text = movies[2].title
        findViewById<LinearLayout>(R.id.layout_3).setOnClickListener {
            val movie3: Movie? = viewModel.itemSelected(movies[2].id)
            movie3?.let {
                Log.d("@dev", "Pelicula seleccionada: ${it.title}")
            }
        }

        findViewById<TextView>(R.id.movie_id_4).text = movies[3].id
        findViewById<TextView>(R.id.movie_title_4).text = movies[3].title
        findViewById<LinearLayout>(R.id.layout_4).setOnClickListener {
            val movie4: Movie? = viewModel.itemSelected(movies[3].id)
            movie4?.let {
                Log.d("@dev", "Pelicula seleccionada: ${it.title}")
            }
        }
    }

    private fun testXml() {
        val xmlDataSource = MovieXmlLocalDataSource(this)
        xmlDataSource.delete()
        val movie = viewModel.itemSelected("1")
        movie?.let {
            xmlDataSource.save(it)
        }
        val movieSaved = xmlDataSource.find()
        Log.d("@dev", movieSaved.toString())

        xmlDataSource.delete()
    }

    private fun testListXml() {
        val movies = viewModel.viewCreated()
        val xmlDataSource = MovieXmlLocalDataSource(this)
        xmlDataSource.saveAll(movies)

        val moviesFromXml = xmlDataSource.findAll()
        Log.d("@dev", moviesFromXml.toString())
        xmlDataSource.delete()
    }

    private fun testFindById() {
        val movie = viewModel.itemSelected("3")
        Log.d("@dev", movie.toString())
    }

}