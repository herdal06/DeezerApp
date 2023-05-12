package com.herdal.deezerapp.ui.genre.adapter

import com.herdal.deezerapp.domain.uimodel.Genre

interface GenreClickListener {
    fun onGenreClick(genre: Genre)
}