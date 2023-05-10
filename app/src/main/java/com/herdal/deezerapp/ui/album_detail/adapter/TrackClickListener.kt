package com.herdal.deezerapp.ui.album_detail.adapter

import com.herdal.deezerapp.domain.uimodel.Track

interface TrackClickListener {
    fun onFavoriteTrackClick(track: Track)
}