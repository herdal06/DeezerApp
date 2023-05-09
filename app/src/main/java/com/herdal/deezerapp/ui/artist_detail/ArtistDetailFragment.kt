package com.herdal.deezerapp.ui.artist_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.herdal.deezerapp.databinding.FragmentArtistDetailBinding
import com.herdal.deezerapp.domain.uimodel.Artist
import com.herdal.deezerapp.utils.extensions.collectLatestLifecycleFlow
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArtistDetailFragment : Fragment() {
    private var _binding: FragmentArtistDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ArtistDetailViewModel by viewModels()

    private val navigationArgs: ArtistDetailFragmentArgs by navArgs()

    private fun getArtistIdByArgs() = navigationArgs.artistId

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArtistDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collectArtistDetail(getArtistIdByArgs())
    }

    private fun collectArtistDetail(artistId: Int) {
        viewModel.onEvent(ArtistDetailUiEvent.GetArtistById(artistId))
        collectLatestLifecycleFlow(viewModel.uiState) { state ->
            state.artist?.let { artist ->
                setupUI(artist)
            }
        }
    }

    private fun setupUI(artist: Artist) {
        binding.artist = artist

        setupActionBarTitle(artist.name)
    }

    private fun setupActionBarTitle(title: String?) {
        (activity as AppCompatActivity).supportActionBar?.title = title
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}