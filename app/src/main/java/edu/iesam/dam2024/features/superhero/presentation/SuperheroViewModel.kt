package edu.iesam.dam2024.features.superhero.presentation

import androidx.lifecycle.ViewModel
import edu.iesam.dam2024.features.superhero.domain.GetSuperheroesUseCase
import edu.iesam.dam2024.features.superhero.domain.Superhero

class SuperheroViewModel(
    private val getSuperheroesUseCase: GetSuperheroesUseCase
) : ViewModel() {

    fun viewCreated(): List<Superhero> {
        return getSuperheroesUseCase.invoke()
    }
}