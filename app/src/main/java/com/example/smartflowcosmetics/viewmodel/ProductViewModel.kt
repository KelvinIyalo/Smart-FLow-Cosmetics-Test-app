package com.example.smartflowcosmetics.viewmodel

import androidx.lifecycle.*
import com.example.smartflowcosmetics.helpers.RepositoryResponse
import com.example.smartflowcosmetics.helpers.UiState
import com.example.smartflowcosmetics.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    fun getProducts(brand:String) = liveData {
        emit(UiState.Loading)
        when (val response = repository.getBrandCosmeticProducts(brand)) {
            is RepositoryResponse.Success -> {
                emit(UiState.Success(response.data))
            }
            is RepositoryResponse.Error -> {
                emit(UiState.DisplayError(response.error))
            }
        }
    }
}