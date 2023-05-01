package com.example.smartflowcosmetics.ui

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.smartflowcosmetics.R
import com.example.smartflowcosmetics.databinding.FragmentProductDetailsBinding
import com.example.smartflowcosmetics.helpers.getProgressDrawable
import com.example.smartflowcosmetics.helpers.loadImage
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class ProductDetailsFragment : Fragment() {
    lateinit var binding: FragmentProductDetailsBinding
    private val args: ProductDetailsFragmentArgs by navArgs()
    private val navController by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductDetailsBinding.inflate(layoutInflater)
        val result = args.productsItem
        Timber.d(result.toString())
        binding.productImage.loadImage(
            args.productsItem.image_link,
            getProgressDrawable(requireContext())
        )
        binding.productPrice.text = "${args.productsItem.price_sign}${args.productsItem.price}"
        binding.productDescription.text = args.productsItem.description
        binding.productTitle.text = args.productsItem.name
        binding.backBtn.setOnClickListener {
            navController.popBackStack()
        }
        return binding.root
    }


}