package com.example.smartflowcosmetics.helpers

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.smartflowcosmetics.R
import com.google.android.material.snackbar.Snackbar

object Utility {

    const val COSMETICS = "/api/v1/products.json"
    const val NETWORK_EXCEPTION = "No Internet Connection"
    const val TYPE_HEADER = 0
    const val TYPE_ITEM = 1
}

fun getProgressDrawable(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 10f
        centerRadius = 50f
        start()
    }
}

fun ImageView.loadImage(uri: String?, progressDrawable: CircularProgressDrawable) {
    val option = RequestOptions()
        .placeholder(progressDrawable)
        .error(R.drawable.ic_launcher_foreground)
        Glide.with(context)
        .setDefaultRequestOptions(option)
        .load(uri)
        .into(this)
}

fun showMessage(message: String, view: View) {
    Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
}
fun showLoading(show: Boolean, progressBar: ProgressBar) {
    val otherVisibility = if (show) View.VISIBLE else View.INVISIBLE
    progressBar.visibility = otherVisibility
}