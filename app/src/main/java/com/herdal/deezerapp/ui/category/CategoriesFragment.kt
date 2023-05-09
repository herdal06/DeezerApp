package com.herdal.deezerapp.ui.category

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.herdal.deezerapp.databinding.FragmentCategoriesBinding
import com.herdal.deezerapp.ui.category.adapter.CategoryAdapter
import com.herdal.deezerapp.utils.extensions.collectLatestLifecycleFlow
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoriesFragment : Fragment() {
    private var _binding: FragmentCategoriesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CategoriesViewModel by viewModels()

    private lateinit var categoryAdapter: CategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerViewAdapters()
        collectCategories()
    }

    private fun collectCategories() {
        viewModel.onEvent(CategoriesUiEvent.GetCategories)
        collectLatestLifecycleFlow(viewModel.uiState) { state ->
            state.categories?.let { categories ->
                categoryAdapter.categories = categories
            }
        }
    }

    private fun initRecyclerViewAdapters() {
        categoryAdapter = CategoryAdapter()
        setupRecyclerViews()
    }

    private fun setupRecyclerViews() = with(binding) {
        rvCategories.adapter = categoryAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}