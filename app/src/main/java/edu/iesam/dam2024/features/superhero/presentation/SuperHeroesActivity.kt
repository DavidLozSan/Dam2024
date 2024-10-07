package edu.iesam.dam2024.features.superhero.presentation

import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import edu.iesam.dam2024.R
import edu.iesam.dam2024.app.domain.ErrorApp
import edu.iesam.dam2024.features.superhero.domain.Superhero

class SuperHeroActivity : AppCompatActivity() {

    private lateinit var superheroFactory: SuperheroFactory
    private lateinit var viewModel: SuperheroViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_superheroes)

        superheroFactory = SuperheroFactory(this)
        viewModel = superheroFactory.buildViewModel()

        viewModel.viewCreated()

        setupObserver()
    }

    private fun setupObserver() {
        val nameObserver = Observer<SuperheroViewModel.UiState> { uiState ->
            uiState.superheroes?.let {
                binData(it)
            }
            uiState.errorApp?.let {
                showError(it)
            }
            if (uiState.isLoading) {
                Log.d("@hero", "Loading...")
            } else {
                //hide loading
            }
        }
        viewModel.uiState.observe(this, nameObserver)
    }

    private fun binData(superheroes: List<Superhero>) {
        findViewById<TextView>(R.id.superhero_id_1).text = superheroes[0].principalData.id
        findViewById<TextView>(R.id.superhero_name_1).text = superheroes[0].principalData.name
        findViewById<LinearLayout>(R.id.layout_1).setOnClickListener {
            navigateToSuperheroDetail(superheroes[0].principalData.id)
        }

        findViewById<TextView>(R.id.superhero_id_2).text = superheroes[1].principalData.id
        findViewById<TextView>(R.id.superhero_name_2).text = superheroes[1].principalData.name
        findViewById<LinearLayout>(R.id.layout_2).setOnClickListener {
            navigateToSuperheroDetail(superheroes[1].principalData.id)
        }

        findViewById<TextView>(R.id.superhero_id_3).text = superheroes[2].principalData.id
        findViewById<TextView>(R.id.superhero_name_3).text = superheroes[2].principalData.name
        findViewById<LinearLayout>(R.id.layout_3).setOnClickListener {
            navigateToSuperheroDetail(superheroes[2].principalData.id)
        }

        findViewById<TextView>(R.id.superhero_id_4).text = superheroes[3].principalData.id
        findViewById<TextView>(R.id.superhero_name_4).text = superheroes[3].principalData.name
        findViewById<LinearLayout>(R.id.layout_4).setOnClickListener {
            navigateToSuperheroDetail(superheroes[3].principalData.id)
        }
    }

    private fun showError(error: ErrorApp) {
        when (error) {
            ErrorApp.DataErrorApp -> TODO()
            ErrorApp.InternetErrorApp -> TODO()
            ErrorApp.ServerErrorApp -> TODO()
            ErrorApp.UnknowErrorApp -> TODO()
        }
    }

    private fun navigateToSuperheroDetail(superheroId: String) {
        startActivity(SuperheroDetailActivity.getIntent(this, superheroId))
    }
}
