package com.herdal.deezerapp.ui.artist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.herdal.deezerapp.databinding.FragmentArtistsBinding
import com.herdal.deezerapp.ui.artist.adapter.ArtistAdapter
import com.herdal.deezerapp.utils.extensions.collectLatestLifecycleFlow
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArtistsFragment : Fragment() {
    private var _binding: FragmentArtistsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ArtistsViewModel by viewModels()

    private lateinit var artistAdapter: ArtistAdapter

    private val navigationArgs: ArtistsFragmentArgs by navArgs()

    private fun getGenreIdByArgs() = navigationArgs.genreId

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArtistsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerViewAdapters()
        collectArtists(getGenreIdByArgs())
    }

    private fun collectArtists(genreId: Int) {
        viewModel.onEvent(ArtistsUiEvent.GetArtistsByGenre(genreId))
        collectLatestLifecycleFlow(viewModel.uiState) { state ->
            state.artists?.let { artists ->
                artistAdapter.artists = artists
            }
        }
    }

    private fun initRecyclerViewAdapters() {
        artistAdapter = ArtistAdapter()
        setupRecyclerViews()
    }

    private fun setupRecyclerViews() = with(binding) {
        rvArtists.adapter = artistAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}