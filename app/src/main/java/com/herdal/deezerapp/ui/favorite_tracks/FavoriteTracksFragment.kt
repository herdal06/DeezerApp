package com.herdal.deezerapp.ui.favorite_tracks

import android.media.MediaPlayer
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

    private var mediaPlayer: MediaPlayer? = null

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

            override fun onTrackClick(preview: String?) {
                playTrackPreview(preview)
            }
        })
        setupRecyclerViews()
    }

    private fun setupRecyclerViews() = with(binding) {
        rvFavTracks.adapter = favoriteTracksAdapter
    }

    private var isPlaying: Boolean = false

    private fun setPlaying(isPlaying: Boolean) {
        this.isPlaying = isPlaying
    }

    private fun playTrackPreview(preview: String?) {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = MediaPlayer().apply {
            setDataSource(preview)
            prepare()
            start()
            setOnCompletionListener {
                setPlaying(false)
            }
        }
        setPlaying(true)
    }

    private fun onFavoriteIconClicked(track: Track) {
        viewModel.onEvent(FavoriteTracksUiEvent.FavoriteIconClicked(track))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        mediaPlayer?.apply {
            reset()
            stop()
            release()
        }
        mediaPlayer = null
    }
}