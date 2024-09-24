package edu.iesam.dam2024.features.superhero.presentation

import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import edu.iesam.dam2024.R
import edu.iesam.dam2024.features.superhero.domain.Superhero

class SuperHeroActivity : AppCompatActivity() {

    private val superheroFactory: SuperheroFactory = SuperheroFactory()
    private val viewModel = superheroFactory.buildViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_superhero)
        val superheroes = viewModel.viewCreated()
        binData(superheroes)
    }

    private fun binData(superheroes: List<Superhero>) {
        findViewById<TextView>(R.id.superhero_id_1).text = superheroes[0].principalData.id
        findViewById<TextView>(R.id.superhero_name_1).text = superheroes[0].principalData.name
        findViewById<LinearLayout>(R.id.layout_1).setOnClickListener {
            val superhero: Superhero? = viewModel.itemSelected(superheroes[0].principalData.id)
            superhero?.let {
                Log.d("@hero", "Superheroe seleccionado: ${it.principalData.name}")
            }
        }

    }
}
