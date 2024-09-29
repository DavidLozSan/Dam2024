package edu.iesam.dam2024.features.superhero.data.local

import android.content.Context
import com.google.gson.Gson
import edu.iesam.dam2024.R
import edu.iesam.dam2024.features.superhero.domain.Appearance
import edu.iesam.dam2024.features.superhero.domain.Height
import edu.iesam.dam2024.features.superhero.domain.PowerStats
import edu.iesam.dam2024.features.superhero.domain.Superhero
import edu.iesam.dam2024.features.superhero.domain.SuperheroBiography
import edu.iesam.dam2024.features.superhero.domain.SuperheroConnection
import edu.iesam.dam2024.features.superhero.domain.SuperheroPrincipalData
import edu.iesam.dam2024.features.superhero.domain.SuperheroWork
import edu.iesam.dam2024.features.superhero.domain.Weight


class SuperheroXmlLocalDataSource(private val context: Context) {
    private val sharedPref = context.getSharedPreferences(
        context.getString(R.string.name_file_superhero_xml), Context.MODE_PRIVATE
    )

    private val gson = Gson()

    fun save(superhero: Superhero) {
        sharedPref.edit().apply {
            putString("id", superhero.principalData.id)
            putString("name", superhero.principalData.name)
            putString("slug", superhero.principalData.slug)
            putString("imageUrl", superhero.principalData.imageUrl)
            putInt("intelligence", superhero.principalData.stats.intelligence)
            putInt("strength", superhero.principalData.stats.strength)
            putInt("speed", superhero.principalData.stats.speed)
            putInt("durability", superhero.principalData.stats.durability)
            putInt("power", superhero.principalData.stats.power)
            putInt("combat", superhero.principalData.stats.combat)
            putString("gender", superhero.principalData.appearance.gender)
            putString("race", superhero.principalData.appearance.race)
            putString("cm", superhero.principalData.appearance.height.cm)
            putString("inches", superhero.principalData.appearance.height.inches)
            putString("kg", superhero.principalData.appearance.weight.kg)
            putString("pounds", superhero.principalData.appearance.weight.pounds)
            putString("eyeColor", superhero.principalData.appearance.eyeColor)
            putString("hairColor", superhero.principalData.appearance.hairColor)
            putString("fullName", superhero.biography.fullName)
            putString("alterEgos", superhero.biography.alterEgos)
            putString("aliases", superhero.biography.aliases.toString())
            putString("placeOfBirth", superhero.biography.placeOfBirth)
            putString("publisher", superhero.biography.publisher)
            putString("alignment", superhero.biography.alignment)
            putString("occupation", superhero.work.occupation)
            putString("base", superhero.work.base)
            putString("groupAffiliation", superhero.connections.groupAffiliation)
            putString("relatives", superhero.connections.relatives)
            putString("images", superhero.images.toString())
            apply()
        }
    }

    fun find(): Superhero {
        sharedPref.apply {
            return Superhero(
                SuperheroPrincipalData(
                    getString("id", "")!!,
                    getString("name", "")!!,
                    getString("slug", "")!!,
                    getString("imageUrl", "")!!,
                    PowerStats(
                        getInt("intelligence", 0),
                        getInt("strength", 0),
                        getInt("speed", 0),
                        getInt("durability", 0),
                        getInt("power", 0),
                        getInt("combat", 0)
                    ),
                    Appearance(
                        getString("gender", "")!!,
                        getString("race", "")!!,
                        Height(getString("cm", "")!!, getString("inches", "")!!),
                        Weight(getString("kg", "")!!, getString("pounds", "")!!),
                        getString("eyeColor", "")!!,
                        getString("hairColor", "")!!
                    )
                ), SuperheroBiography(
                    getString("fullName", "")!!,
                    getString("alterEgos", "")!!,
                    listOf(getString("aliases", "")!!),
                    getString("placeOfBirth", "")!!,
                    getString("firstAppearance", "")!!,
                    getString("publisher", "")!!,
                    getString("alignment", "")!!
                ), SuperheroWork(
                    getString("occupation", "")!!,
                    getString("base", "")!!,
                ), SuperheroConnection(
                    getString("groupAffiliation", "")!!,
                    getString("relatives", "")!!
                ), listOf(getString("images", "")!!)
            )
        }
    }

    fun delete() {
        sharedPref.edit().clear().apply()
    }

    fun saveAll(superheroes: List<Superhero>) {
        val editor = sharedPref.edit()
        superheroes.forEach { superhero ->
            editor.putString(superhero.principalData.id, gson.toJson(superhero))
        }
        editor.apply()
    }

    fun findAll(): List<Superhero> {
        val superheroes = ArrayList<Superhero>()
        val mapSuperhero = sharedPref.all
        mapSuperhero.values.forEach { jsonSuperhero ->
            val superhero = gson.fromJson(jsonSuperhero as String, Superhero::class.java)
            superheroes.add(superhero)
        }
        return superheroes
    }
}

