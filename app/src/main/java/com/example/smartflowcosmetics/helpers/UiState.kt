package com.example.smartflowcosmetics.helpers

sealed class UiState<out T> {
    object Loading: UiState<Nothing>()
    object CancelPayment: UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    data class DisplayData<T>(val data: T) : UiState<T>()
    data class DisplayError(val error: String) : UiState<Nothing>()
}