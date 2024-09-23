package edu.iesam.dam2024.features.superhero.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import edu.iesam.dam2024.R

class SuperHeroActivity : AppCompatActivity() {

    private val superheroFactory: SuperheroFactory = SuperheroFactory()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_superhero)
        val viewModel = superheroFactory.buildViewModel()
        val superhero = viewModel.viewCreated()
        Log.d("@hero", superhero.toString())
    }
}
