package com.launcher.min.core.utils

import android.content.Context

fun Context.launchApp(packageName: String) {
    val intent = packageManager.getLaunchIntentForPackage(packageName) ?: return
    startActivity(intent)
}
