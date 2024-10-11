package edu.iesam.dam2024.features.superhero.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import edu.iesam.dam2024.app.domain.ErrorApp
import edu.iesam.dam2024.databinding.FragmentSuperheroListBinding
import edu.iesam.dam2024.features.superhero.domain.Superhero

class SuperHeroListFragment : Fragment() {

    private var _binding: FragmentSuperheroListBinding? = null
    private val binding get() = _binding!!

    private lateinit var superheroFactory: SuperheroFactory
    private lateinit var viewModel: SuperheroListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSuperheroListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        superheroFactory = SuperheroFactory(requireContext())
        viewModel = superheroFactory.buildSuperheroListViewModel()
        setupObserver()
        viewModel.loadSuperheroes()

    }

    private fun setupObserver() {
        val nameObserver = Observer<SuperheroListViewModel.UiState> { uiState ->
            uiState.superHeroes?.let {
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
                Log.d("@hero", "Loaded.")
            }
        }
        viewModel.uiState.observe(viewLifecycleOwner, nameObserver)
    }

    private fun binData(superheroes: List<Superhero>) {
        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.adapter = SuperheroAdapter(superheroes) { superheroId ->
                navigateToDetails(superheroId)
            }
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

    private fun navigateToDetails(superheroId: String) {
        findNavController().navigate(
            SuperHeroListFragmentDirections.actionFromSuperheroToSuperheroDetail(
                superheroId
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}