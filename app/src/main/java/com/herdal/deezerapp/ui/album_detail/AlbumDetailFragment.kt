package com.herdal.deezerapp.ui.album_detail

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.herdal.deezerapp.databinding.FragmentAlbumDetailBinding
import com.herdal.deezerapp.domain.uimodel.Track
import com.herdal.deezerapp.ui.album_detail.adapter.TrackAdapter
import com.herdal.deezerapp.ui.album_detail.adapter.TrackClickListener
import com.herdal.deezerapp.utils.extensions.collectLatestLifecycleFlow
import com.herdal.deezerapp.utils.extensions.hide
import com.herdal.deezerapp.utils.extensions.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumDetailFragment : Fragment() {
    private var _binding: FragmentAlbumDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AlbumDetailViewModel by viewModels()

    private val navigationArgs: AlbumDetailFragmentArgs by navArgs()

    private fun getAlbumIdByArgs() = navigationArgs.album.id
    private fun getAlbumTitleByArgs() = navigationArgs.album.title

    private lateinit var trackAdapter: TrackAdapter

    private var mediaPlayer: MediaPlayer? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAlbumDetailBinding.inflate(inflater, container, false)
        collectTracks(getAlbumIdByArgs())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerViewAdapters()
        setupActionBarTitle(getAlbumTitleByArgs())
    }

    private fun collectTracks(albumId: Int) = with(binding) {
        viewModel.onEvent(AlbumDetailUiEvent.GetTracksByAlbum(albumId))
        collectLatestLifecycleFlow(viewModel.uiState) { state ->
            state.tracks?.let { tracks ->
                pbTracks.hide()
                tvTrackError.hide()
                trackAdapter.tracks = tracks
                rvTracks.show()
            }
            if (state.loading && state.tracks.isNullOrEmpty()) {
                pbTracks.show()
                tvTrackError.hide()
                rvTracks.hide()
            } else {
                pbTracks.hide()
                state.error?.let {
                    tvTrackError.text = it
                    tvTrackError.show()
                } ?: run {
                    tvTrackError.hide()
                }
                if (state.tracks.isNullOrEmpty()) {
                    rvTracks.hide()
                } else {
                    rvTracks.show()
                }
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

    private fun setupActionBarTitle(title: String?) {
        (activity as AppCompatActivity).supportActionBar?.title = title
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