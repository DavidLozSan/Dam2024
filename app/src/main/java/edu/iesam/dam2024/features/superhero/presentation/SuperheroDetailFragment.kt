package edu.iesam.dam2024.features.superhero.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import edu.iesam.dam2024.app.domain.ErrorApp
import edu.iesam.dam2024.app.extensions.loadUrl
import edu.iesam.dam2024.databinding.FragmentSuperheroDetailBinding
import edu.iesam.dam2024.features.superhero.domain.Superhero

class SuperheroDetailFragment : Fragment() {

    private var _binding: FragmentSuperheroDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var superheroFactory: SuperheroFactory
    private lateinit var viewModel: SuperheroDetailViewModel

    private val superheroArgs: SuperheroDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSuperheroDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        superheroFactory = SuperheroFactory(requireContext())
        viewModel = superheroFactory.buildSuperheroDetailViewModel()
        setupObserver()
        viewModel.loadSuperhero(superheroArgs.superheroId)
    }

    private fun setupObserver() {
        val nameObserver = Observer<SuperheroDetailViewModel.UiState> { uiState ->
            uiState.superhero?.let {
                binData(it)
            }
            uiState.errorApp?.let {
                showError(it)
            } ?: run {
                //hide error
            }
            if (uiState.isLoading) {
                Log.d("@hero", "Loading...")
            } else {
                Log.d("@hero", "Loaded")
            }
        }
        viewModel.uiState.observe(viewLifecycleOwner, nameObserver)
    }

    private fun binData(superhero: Superhero) {
        binding.apply {
            superheroImage.loadUrl(superhero.principalData.imageUrl)
            superheroName.text = superhero.principalData.name
            superheroAlignment.text = superhero.biography.alignment
            superheroGender.text = superhero.principalData.appearance.gender
            superheroRace.text = superhero.principalData.appearance.race
            superheroHeight.text = superhero.principalData.appearance.height.toString()
            superheroWeight.text = superhero.principalData.appearance.weight.toString()
            superheroINT.text = superhero.principalData.stats.intelligence.toString()
            superheroSTR.text = superhero.principalData.stats.strength.toString()
            superheroSPD.text = superhero.principalData.stats.speed.toString()
            superheroDUR.text = superhero.principalData.stats.durability.toString()
            superheroPWR.text = superhero.principalData.stats.power.toString()
            superheroCBT.text = superhero.principalData.stats.combat.toString()
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
}