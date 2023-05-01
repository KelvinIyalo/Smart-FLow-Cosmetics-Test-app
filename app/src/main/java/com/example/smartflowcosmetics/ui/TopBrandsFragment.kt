package com.example.smartflowcosmetics.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smartflowcosmetics.R
import com.example.smartflowcosmetics.adapter.TopBrandAdapter
import com.example.smartflowcosmetics.databinding.FragmentTopBrandsBinding
import com.example.smartflowcosmetics.helpers.UiState
import com.example.smartflowcosmetics.helpers.showLoading
import com.example.smartflowcosmetics.helpers.showMessage
import com.example.smartflowcosmetics.viewmodel.TopBrandViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopBrandsFragment : Fragment() {
    private lateinit var binding: FragmentTopBrandsBinding
    private val viewModel: TopBrandViewModel by viewModels()
    private lateinit var topBrandAdapter: TopBrandAdapter
    private val navController by lazy { findNavController() }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTopBrandsBinding.inflate(layoutInflater)
        viewModel.getTopBrandCosmetics()
        observeData()
        setUpAdapter()
        topBrandAdapter.setOnItemClickListener {
            val direction =
                TopBrandsFragmentDirections.actionTopBrandsFragmentToProductsFragment(it.brand!!)
            navController.navigate(direction)
        }

        return binding.root
    }


    private fun observeData() {
        viewModel.getTopBrandCosmetics().observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is UiState.Loading -> {
                    showLoading(true, binding.progressBar)
                }
                is UiState.Success -> {
                    showLoading(false, binding.progressBar)
                    topBrandAdapter.differ.submitList(response.data.distinctBy { it.brand })
                }
                is UiState.DisplayError -> {
                    showLoading(false, binding.progressBar)
                    showMessage(response.error, binding.root)
                }
            }
        })


    }

    private fun setUpAdapter() {
        topBrandAdapter = TopBrandAdapter()
        binding.topBrandsView.apply {
            adapter = topBrandAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

}