package com.example.smartflowcosmetics.viewmodel

import androidx.lifecycle.*
import com.example.smartflowcosmetics.helpers.RepositoryResponse
import com.example.smartflowcosmetics.helpers.UiState
import com.example.smartflowcosmetics.model.ProductItem
import com.example.smartflowcosmetics.model.ProductsModel
import com.example.smartflowcosmetics.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class TopBrandViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    fun getTopBrandCosmetics() = liveData {
        emit(UiState.Loading)
        when (val response = repository.getCosmeticBrands()) {
            is RepositoryResponse.Success -> {
                emit(UiState.Success(response.data))
            }
            is RepositoryResponse.Error -> {
                emit(UiState.DisplayError(response.error))
            }
        }
    }


}