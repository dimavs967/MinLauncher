package com.launcher.min.core.models

import android.graphics.drawable.Drawable

data class AppModel(
    val name: String,
    val packageName: String,
    val icon: Drawable?
)