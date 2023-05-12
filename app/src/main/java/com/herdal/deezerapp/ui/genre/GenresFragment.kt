package com.herdal.deezerapp.ui.genre

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.herdal.deezerapp.databinding.FragmentGenresBinding
import com.herdal.deezerapp.domain.uimodel.Genre
import com.herdal.deezerapp.ui.genre.adapter.GenreAdapter
import com.herdal.deezerapp.ui.genre.adapter.GenreClickListener
import com.herdal.deezerapp.utils.extensions.collectLatestLifecycleFlow
import com.herdal.deezerapp.utils.extensions.hide
import com.herdal.deezerapp.utils.extensions.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GenresFragment : Fragment() {
    private var _binding: FragmentGenresBinding? = null
    private val binding get() = _binding!!

    private val viewModel: GenresViewModel by viewModels()

    private lateinit var genreAdapter: GenreAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGenresBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerViewAdapters()
        collectGenres()
    }

    private fun collectGenres() = with(binding) {
        viewModel.onEvent(GenresUiEvent.GetGenres)
        collectLatestLifecycleFlow(viewModel.uiState) { state ->
            state.genres?.let { categories ->
                pbGenres.hide()
                tvError.hide()
                genreAdapter.genres = categories
                rvGenres.show()
            }
            if (state.loading && state.genres.isNullOrEmpty()) {
                pbGenres.show()
                tvError.hide()
                rvGenres.hide()
            } else {
                pbGenres.hide()
                state.error?.let {
                    tvError.text = it
                    tvError.show()
                } ?: run {
                    tvError.hide()
                }
                if (state.genres.isNullOrEmpty()) {
                    rvGenres.hide()
                } else {
                    rvGenres.show()
                }
            }
        }
    }

    private fun initRecyclerViewAdapters() {
        genreAdapter = GenreAdapter(object : GenreClickListener {
            override fun onGenreClick(genre: Genre) {
                navigateToArtistList(genre)
            }
        })
        setupRecyclerViews()
    }

    private fun navigateToArtistList(genre: Genre) {
        val action = GenresFragmentDirections.actionGenresFragmentToArtistsFragment(genre)
        findNavController().navigate(action)
    }

    private fun setupRecyclerViews() = with(binding) {
        rvGenres.adapter = genreAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}