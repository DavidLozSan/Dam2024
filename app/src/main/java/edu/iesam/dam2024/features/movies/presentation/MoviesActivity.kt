package edu.iesam.dam2024.features.movies.presentation

import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import edu.iesam.dam2024.R
import edu.iesam.dam2024.app.domain.ErrorApp
import edu.iesam.dam2024.features.movies.domain.Movie

class MoviesActivity : AppCompatActivity() {

    private lateinit var movieFactory: MovieFactory
    private lateinit var viewModel: MoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        movieFactory = MovieFactory(this)
        viewModel = movieFactory.buildViewModel()

        setupObserver()

        viewModel.viewCreated()
    }

    private fun setupObserver() {
        val nameObserver = Observer<MoviesViewModel.UiState> { uiState ->
            uiState.movies?.let {
                binData(it)
            }
            uiState.errorApp?.let {
                //pinto el erro
            }
            if (uiState.isLoading) {
                Log.d("@dev","Cargando...")
            } else {
                //oculto el cargando...
            }
        }
        viewModel.uiState.observe(this, nameObserver)
    }

    private fun binData(movies: List<Movie>) {
        findViewById<TextView>(R.id.movie_id_1).text = movies[0].id
        findViewById<TextView>(R.id.movie_title_1).text = movies[0].title
        findViewById<LinearLayout>(R.id.layout_1).setOnClickListener {
            navigateToMovieDetail(movies[0].id)
        }

        findViewById<TextView>(R.id.movie_id_2).text = movies[1].id
        findViewById<TextView>(R.id.movie_title_2).text = movies[1].title
        findViewById<LinearLayout>(R.id.layout_2).setOnClickListener {
            navigateToMovieDetail(movies[1].id)
        }

        findViewById<TextView>(R.id.movie_id_3).text = movies[2].id
        findViewById<TextView>(R.id.movie_title_3).text = movies[2].title
        findViewById<LinearLayout>(R.id.layout_3).setOnClickListener {
            navigateToMovieDetail(movies[2].id)
        }

        findViewById<TextView>(R.id.movie_id_4).text = movies[3].id
        findViewById<TextView>(R.id.movie_title_4).text = movies[3].title
        findViewById<LinearLayout>(R.id.layout_4).setOnClickListener {
            navigateToMovieDetail(movies[3].id)
        }
    }

    private fun showError(error: ErrorApp) {
        when(error) {
            ErrorApp.DataErrorApp -> TODO()
            ErrorApp.InternetErrorApp -> TODO()
            ErrorApp.ServerErrorApp -> TODO()
            ErrorApp.UnknowErrorApp -> TODO()
        }
    }

    private fun navigateToMovieDetail(movieId: String) {
        startActivity(MovieDetailActivity.getIntent(this, movieId))
    }

}