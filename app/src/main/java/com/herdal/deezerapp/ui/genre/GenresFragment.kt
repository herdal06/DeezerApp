package com.herdal.deezerapp.ui.genre

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.herdal.deezerapp.databinding.FragmentGenresBinding
import com.herdal.deezerapp.ui.genre.adapter.GenreAdapter
import com.herdal.deezerapp.utils.extensions.collectLatestLifecycleFlow
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
        collectCategories()
    }

    private fun collectCategories() {
        viewModel.onEvent(GenresUiEvent.GetGenres)
        collectLatestLifecycleFlow(viewModel.uiState) { state ->
            state.genres?.let { categories ->
                genreAdapter.categories = categories
            }
        }
    }

    private fun initRecyclerViewAdapters() {
        genreAdapter = GenreAdapter()
        setupRecyclerViews()
    }

    private fun setupRecyclerViews() = with(binding) {
        rvCategories.adapter = genreAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}