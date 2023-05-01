package com.example.smartflowcosmetics.model

import android.os.Parcelable
import java.io.Serializable

data class ProductItem(
    var api_featured_image: String?,
    var brand: String?,
    var category: String?,
    var created_at: String?,
    var currency: String?,
    var description: String?,
    var id: Int?,
    var image_link: String?,
    var name: String?,
    var price: String?,
    var price_sign: String?
):Serializable