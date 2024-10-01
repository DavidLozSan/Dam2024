package edu.iesam.dam2024.features.superhero.presentation

import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import edu.iesam.dam2024.R
import edu.iesam.dam2024.features.superhero.data.local.SuperheroXmlLocalDataSource
import edu.iesam.dam2024.features.superhero.domain.Superhero

class SuperHeroActivity : AppCompatActivity() {

    private lateinit var superheroFactory: SuperheroFactory
    private lateinit var viewModel: SuperheroViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_superheroes)

        superheroFactory = SuperheroFactory(this)
        viewModel = superheroFactory.buildViewModel()

        val superheroes = viewModel.viewCreated()
        binData(superheroes)

        testXml()
        testListXml()
        testFindById()
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

    private fun testXml() {
        val xmlLocalDataSource = SuperheroXmlLocalDataSource(this)
        xmlLocalDataSource.delete()
        val superhero = viewModel.itemSelected("1")
        superhero?.let {
            xmlLocalDataSource.save(it)
        }
        val superheroSaved = xmlLocalDataSource.find()
        Log.d("@hero", superheroSaved.toString())

        xmlLocalDataSource.delete()
    }

    private fun testListXml() {
        val superheroes = viewModel.viewCreated()
        val xmlLocalDataSource = SuperheroXmlLocalDataSource(this)
        xmlLocalDataSource.saveAll(superheroes)

        val superheroesFromXml = xmlLocalDataSource.findAll()
        Log.d("@hero", superheroesFromXml.toString())
    }

    private fun testFindById() {
        val superhero = viewModel.itemSelected("2")
        Log.d("@hero", superhero.toString())
    }
}
