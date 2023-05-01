package com.example.smartflowcosmetics.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.smartflowcosmetics.adapter.ProductAdapter
import com.example.smartflowcosmetics.databinding.FragmentProductsBinding
import com.example.smartflowcosmetics.helpers.UiState
import com.example.smartflowcosmetics.helpers.showLoading
import com.example.smartflowcosmetics.helpers.showMessage
import com.example.smartflowcosmetics.viewmodel.ProductViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class ProductsFragment : Fragment() {
    lateinit var binding: FragmentProductsBinding
    private val args: ProductsFragmentArgs by navArgs()
    private val viewModel: ProductViewModel by viewModels()
    private val navController by lazy { findNavController() }
    private val productAdapter by lazy { ProductAdapter(requireContext()) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductsBinding.inflate(layoutInflater)
        binding.backBtn.setOnClickListener {
            navController.popBackStack()
        }

        setUpAdapter()
        observeData()
        productAdapter.setOnItemClickListener {
            val direction =
                ProductsFragmentDirections.actionProductsFragmentToProductDetailsFragment(it)
            navController.navigate(direction)
        }
        return binding.root
    }


    private fun observeData() {
        viewModel.getProducts(args.brandName).observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is UiState.Loading -> {
                    Timber.d("Loading")
                    showLoading(true, binding.progressBar)
                }
                is UiState.Success -> {
                    Timber.d("Success -> ${response.data}")
                    showLoading(false, binding.progressBar)
                    productAdapter.differ.submitList(response.data.sortedBy { it.category })

                }
                is UiState.DisplayError -> {
                    Timber.d("Error -> ${response.error}")
                    showLoading(false, binding.progressBar)
                    showMessage(response.error, binding.root)
                }
            }
        })
    }

    private fun setUpAdapter() {
        binding.brandProducts.apply {
            adapter = productAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }
}