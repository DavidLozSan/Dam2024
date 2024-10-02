package edu.iesam.dam2024.features.superhero.presentation

import androidx.lifecycle.ViewModel
import edu.iesam.dam2024.features.superhero.domain.GetSuperheroUseCase
import edu.iesam.dam2024.features.superhero.domain.Superhero

class SuperheroDetailViewModel(
    private val getSuperheroUseCase: GetSuperheroUseCase
) : ViewModel() {

    fun viewCreated(superheroId: String): Superhero? {
        return getSuperheroUseCase.invoke(superheroId)
    }
}