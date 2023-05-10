package com.herdal.deezerapp.ui.favorite_tracks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.herdal.deezerapp.databinding.FragmentFavoriteTracksBinding
import com.herdal.deezerapp.domain.uimodel.Track
import com.herdal.deezerapp.ui.album_detail.adapter.TrackAdapter
import com.herdal.deezerapp.ui.album_detail.adapter.TrackClickListener
import com.herdal.deezerapp.utils.extensions.collectLatestLifecycleFlow
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteTracksFragment : Fragment() {
    private var _binding: FragmentFavoriteTracksBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FavoriteTracksViewModel by viewModels()

    private lateinit var favoriteTracksAdapter: TrackAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteTracksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerViewAdapters()
        collectFavTracks()
    }

    private fun collectFavTracks() {
        viewModel.onEvent(FavoriteTracksUiEvent.GetFavoriteTracks)
        collectLatestLifecycleFlow(viewModel.uiState) { state ->
            state.tracks?.let { flow ->
                flow.collect { tracks ->
                    favoriteTracksAdapter.tracks = tracks
                }
            }
        }
    }

    private fun initRecyclerViewAdapters() {
        favoriteTracksAdapter = TrackAdapter(object : TrackClickListener {
            override fun onFavoriteTrackClick(track: Track) {
                onFavoriteIconClicked(track)
            }
        })
        setupRecyclerViews()
    }

    private fun setupRecyclerViews() = with(binding) {
        rvFavTracks.adapter = favoriteTracksAdapter
    }

    private fun onFavoriteIconClicked(track: Track) {
        viewModel.onEvent(FavoriteTracksUiEvent.FavoriteIconClicked(track))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}