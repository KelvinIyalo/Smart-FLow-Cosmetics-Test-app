package com.example.smartflowcosmetics.helpers

import retrofit2.Response

sealed class RepositoryResponse<out T> {
    class Success<out T>(val data:T):RepositoryResponse<T>()
    class Error(val error:String):RepositoryResponse<Nothing>()
}