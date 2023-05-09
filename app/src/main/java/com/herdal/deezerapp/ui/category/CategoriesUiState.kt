package com.herdal.deezerapp.ui.category

import com.herdal.deezerapp.domain.uimodel.Category

data class CategoriesUiState(
    val loading: Boolean? = false,
    val error: String? = null,
    val categories: List<Category>? = null
)