package edu.iesam.dam2024.features.superhero.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import edu.iesam.dam2024.databinding.FragmentSuperheroListBinding

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
                // binData(it)
            }
            uiState.errorApp?.let {
                // showError(it)
            }
            if (uiState.isLoading) {
                Log.d("@dev", "Loading...")
            } else {
                //hide loading
            }
        }
        viewModel.uiState.observe(viewLifecycleOwner, nameObserver)
    }
}