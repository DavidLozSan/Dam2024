package edu.iesam.dam2024.features.movies.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import edu.iesam.dam2024.app.domain.ErrorApp
import edu.iesam.dam2024.databinding.FragmentMoviesBinding
import edu.iesam.dam2024.features.movies.domain.Movie

class MoviesFragment : Fragment() {

    private lateinit var movieFactory: MovieFactory
    private lateinit var viewModel: MoviesViewModel

    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
        movieFactory = MovieFactory(requireContext())
        viewModel = movieFactory.buildViewModel()
        viewModel.viewCreated()
    }

    private fun setupObserver() {
        val nameObserver = Observer<MoviesViewModel.UiState> { uiState ->
            uiState.movies?.let {
                binData(it)
            }
            uiState.errorApp?.let {
                showError(it)
            }
            if (uiState.isLoading) {
                Log.d("@dev", "Cargando...")
            } else {
                //oculto el cargando...
            }
        }
        viewModel.uiState.observe(viewLifecycleOwner, nameObserver)
    }

    private fun binData(movies: List<Movie>) {
        binding.movieId1.text = movies[0].id
        binding.movieTitle1.text = movies[0].title
        binding.layout1.setOnClickListener {
            findNavController()
            //navigateToMovieDetail(movies[0].id)
        }

        binding.movieId2.text = movies[1].id
        binding.movieTitle2.text = movies[1].title
        binding.layout2.setOnClickListener {
            navigateToMovieDetail(movies[1].id)
        }

        binding.movieId3.text = movies[0].id
        binding.movieTitle3.text = movies[0].title
        binding.layout3.setOnClickListener {
            navigateToMovieDetail(movies[2].id)
        }

        binding.movieId4.text = movies[0].id
        binding.movieTitle4.text = movies[0].title
        binding.layout4.setOnClickListener {
            navigateToMovieDetail(movies[3].id)
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

    private fun navigateToMovieDetail(movieId: String) {
        startActivity(MovieDetailFragment.getIntent(requireContext(), movieId))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}