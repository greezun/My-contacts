package com.mycontacts

import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.mycontacts.R


val GLIDE_OPTIONS = RequestOptions()
    .centerCrop()
    .circleCrop()
    .placeholder(R.drawable.ic_launcher_foreground)
    .error(R.drawable.ic_launcher_foreground)
    .diskCacheStrategy(DiskCacheStrategy.ALL)
    .priority(Priority.HIGH)


fun AppCompatImageView.setContactPhoto(contactPhotoUrl: String) {
    Glide.with(context)
        .load(contactPhotoUrl)
        .apply(GLIDE_OPTIONS)
        .into(this)
}