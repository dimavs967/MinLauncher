package com.launcher.min.feature.home.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.launcher.min.core.models.AppModel
import com.launcher.min.core.utils.launchApp

@SuppressLint("NewApi")
@Composable
fun HomeScreen() {

    val context = LocalContext.current
    val flags = PackageManager.ResolveInfoFlags.of(PackageManager.MATCH_ALL.toLong())
    val intent = Intent(Intent.ACTION_MAIN)

    intent.addCategory(Intent.CATEGORY_LAUNCHER)

    val installedApps = context.packageManager
        .queryIntentActivities(intent, flags)
        .map { resolveInfo ->
            AppModel(
                name = resolveInfo.loadLabel(context.packageManager).toString(),
                packageName = resolveInfo.activityInfo.packageName,
                icon = resolveInfo.loadIcon(context.packageManager)
            )
        }

    Content(installedApps) {
        context.launchApp(it.packageName)
    }
}

@Composable
fun Content(
    data: List<AppModel>,
    onClickListener: (AppModel) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 50.dp)
    ) {
        items(data.size) { i ->
            AppItem(
                item = data[i],
                onClickListener = onClickListener
            )
        }
    }
}

@Composable
fun AppItem(
    item: AppModel,
    onClickListener: (AppModel) -> Unit
) {

    Text(
        text = item.name,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClickListener(item) }
            .wrapContentWidth(align = Alignment.End),
    )

}