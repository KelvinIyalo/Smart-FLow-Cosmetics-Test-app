package com.example.smartflowcosmetics.repository

import com.example.smartflowcosmetics.helpers.RepositoryResponse
import com.example.smartflowcosmetics.helpers.Utility.NETWORK_EXCEPTION
import com.example.smartflowcosmetics.model.ProductsModel
import com.example.smartflowcosmetics.remote.CosmeticServices
import retrofit2.Response
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

class Repository @Inject constructor(private val cosmeticServices: CosmeticServices) {

    suspend fun getCosmeticBrands(): RepositoryResponse<ProductsModel> {
        return try {
            val result = cosmeticServices.getCosmeticBrands()
            getCallResponseState(result)
        } catch (exception: Exception) {
            return exceptionHandler(exception)
        }

    }

    suspend fun getBrandCosmeticProducts(brand:String): RepositoryResponse<ProductsModel> {
        return try {
            val result = cosmeticServices.getCosmeticsAccordingToBrand(brand)
            getCallResponseState(result)
        } catch (exception: Exception) {
            return exceptionHandler(exception)
        }

    }

    private fun <T : Any> getCallResponseState(result: Response<T>): RepositoryResponse<T> {
        return when {
            result.isSuccessful && result.body() != null -> {
                Timber.d("Repository data: ${result.body()}")
                RepositoryResponse.Success(result.body() as T)
            }
            else -> {
                Timber.d("Repository Error: ${result.body()}")
                RepositoryResponse.Error(result.message())
            }

        }
    }

    private fun <T> exceptionHandler(exception: Exception): RepositoryResponse<T> {
        return when (exception) {
            is IOException -> {
                Timber.e("Repository 1nd catch: $exception")
                RepositoryResponse.Error(NETWORK_EXCEPTION)
            }
            else -> {
                Timber.e("Repository 2nd catch: $exception")
                RepositoryResponse.Error(exception.localizedMessage)
            }
        }
    }

}