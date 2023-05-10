package com.herdal.deezerapp.core.binding_adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("app:loadTrackImage")
fun loadTrackImage(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(view.context)
            .load("https://e-cdns-images.dzcdn.net/images/cover/${imageUrl}/112x112-000000-80-0-0.jpg")
            .centerCrop()
            .into(view)
    }
}