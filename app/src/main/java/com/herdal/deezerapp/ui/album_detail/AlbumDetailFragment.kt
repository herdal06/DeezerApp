package com.herdal.deezerapp.ui.album_detail

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.herdal.deezerapp.databinding.FragmentAlbumDetailBinding
import com.herdal.deezerapp.domain.uimodel.Track
import com.herdal.deezerapp.ui.album_detail.adapter.TrackAdapter
import com.herdal.deezerapp.ui.album_detail.adapter.TrackClickListener
import com.herdal.deezerapp.utils.extensions.collectLatestLifecycleFlow
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumDetailFragment : Fragment() {
    private var _binding: FragmentAlbumDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AlbumDetailViewModel by viewModels()

    private val navigationArgs: AlbumDetailFragmentArgs by navArgs()

    private fun getAlbumIdByArgs() = navigationArgs.albumId

    private lateinit var trackAdapter: TrackAdapter

    private var mediaPlayer: MediaPlayer? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAlbumDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerViewAdapters()
        collectTracks(getAlbumIdByArgs())
    }

    private fun collectTracks(albumId: Int) {
        viewModel.onEvent(AlbumDetailUiEvent.GetTracksByAlbum(albumId))
        collectLatestLifecycleFlow(viewModel.uiState) { state ->
            state.tracks?.let { tracks ->
                trackAdapter.tracks = tracks
            }
        }
    }

    private fun initRecyclerViewAdapters() {
        trackAdapter = TrackAdapter(object : TrackClickListener {
            override fun onFavoriteTrackClick(track: Track) {
                onFavoriteIconClicked(track)
            }

            override fun onTrackClick(preview: String?) {
                playTrackPreview(preview)
            }
        })
        setupRecyclerViews()
    }

    private fun playTrackPreview(preview: String?) {
        mediaPlayer?.apply {
            try {
                if (isPlaying) {
                    stop()
                }
                reset()
                setDataSource(preview)
                prepareAsync()
                setOnPreparedListener {
                    start()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        } ?: run {
            mediaPlayer = MediaPlayer().apply {
                try {
                    setDataSource(preview)
                    prepareAsync()
                    setOnPreparedListener {
                        start()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    private fun onFavoriteIconClicked(track: Track) {
        viewModel.onEvent(AlbumDetailUiEvent.FavoriteIconClicked(track))
    }

    private fun setupRecyclerViews() = with(binding) {
        rvTracks.adapter = trackAdapter
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