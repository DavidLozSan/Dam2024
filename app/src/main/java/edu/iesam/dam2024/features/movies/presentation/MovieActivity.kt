package edu.iesam.dam2024.features.movies.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import edu.iesam.dam2024.R

class MovieActivity : AppCompatActivity() {

    private val movieFactory: MovieFactory = MovieFactory()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewModel = movieFactory.buildViewModel()
        val movies = viewModel.viewCreated()
        Log.d("@dev", movies.toString())
    }

    override fun onResume() {
        super.onResume()
        Log.d("@dev", "@onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("@dev", "@onPause")
    }

    override fun onStart() {
        super.onStart()
        Log.d("@dev", "@onStart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("@dev", "@onDestroy")
    }
//viewModel.itemSelected()
}