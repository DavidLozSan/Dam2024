package edu.iesam.dam2024.features.superhero.presentation

import androidx.lifecycle.ViewModel
import edu.iesam.dam2024.features.superhero.domain.GetSuperheroUseCase
import edu.iesam.dam2024.features.superhero.domain.GetSuperheroesUseCase
import edu.iesam.dam2024.features.superhero.domain.Superhero

class SuperheroViewModel(
    private val getSuperheroesUseCase: GetSuperheroesUseCase,
    private val getSuperheroUseCase: GetSuperheroUseCase
) : ViewModel() {

    fun viewCreated(): List<Superhero> {
        return getSuperheroesUseCase.invoke()
    }

    fun itemSelected(superheroId: String): Superhero? {
        return getSuperheroUseCase.invoke(superheroId)
    }
}