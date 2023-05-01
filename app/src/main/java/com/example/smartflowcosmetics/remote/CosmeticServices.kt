package com.example.smartflowcosmetics.remote

import com.example.smartflowcosmetics.helpers.Utility.COSMETICS
import com.example.smartflowcosmetics.model.ProductItem
import com.example.smartflowcosmetics.model.ProductsModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CosmeticServices {

    @GET(COSMETICS)
    suspend fun getCosmeticBrands(): Response<ProductsModel>

    @GET(COSMETICS)
    suspend fun getCosmeticsAccordingToBrand(
        @Query("brand") queryBrand: String
    ): Response<ProductsModel>
}