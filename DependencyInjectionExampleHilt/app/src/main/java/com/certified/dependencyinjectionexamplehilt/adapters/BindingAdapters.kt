package com.certified.dependencyinjectionexamplehilt.adapters

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.certified.dependencyinjectionexamplehilt.R

@BindingAdapter("image")
fun bindImage(imgView: ImageView, image: Uri?) {
    if (image == null)
        Glide.with(imgView.context)
            .load(R.drawable.no_profile_image)
            .into(imgView)
    else
        Glide.with(imgView.context)
            .load(image)
            .into(imgView)
}