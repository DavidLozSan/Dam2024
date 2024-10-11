package edu.iesam.dam2024.features.superhero.data.local

import android.content.Context
import com.google.gson.Gson
import edu.iesam.dam2024.R
import edu.iesam.dam2024.features.superhero.domain.Superhero

class SuperheroXmlLocalDataSource(private val context: Context) {

    private val sharedPref = context.getSharedPreferences(
        context.getString(R.string.name_file_superhero_xml), Context.MODE_PRIVATE
    )

    private val gson = Gson()

    fun save(superhero: Superhero) {
        val editor = sharedPref.edit()
        editor.putString(superhero.principalData.id, gson.toJson(superhero))
        editor.apply()
    }

    fun saveAll(superheroes: List<Superhero>) {
        val editor = sharedPref.edit()
        superheroes.forEach { superhero ->
            editor.putString(superhero.principalData.id, gson.toJson(superhero))
        }
        editor.apply()
    }

    fun findById(id: String): Superhero? {
        return sharedPref.getString(id, null)?.let { superhero ->
            gson.fromJson(superhero, Superhero::class.java)
        }
    }

    fun findAll(): List<Superhero> {
        val superheroes = mutableListOf<Superhero>()
        val mapSuperheroes = sharedPref.all
        mapSuperheroes.values.forEach { jsonSuperhero ->
            val superhero = gson.fromJson(jsonSuperhero as String, Superhero::class.java)
            superheroes.add(superhero)
        }
        return superheroes
    }

    fun delete() {
        sharedPref.edit().clear().apply()
    }

    fun deleteById(id: String) {
        sharedPref.edit().remove(id).apply()
    }
}