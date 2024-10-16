package com.launcher.min.view.main

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import com.launcher.min.core.ui.theme.MinLauncherTheme

class MainActivity : ComponentActivity() {

    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_LAUNCHER)

        val flags = PackageManager.ResolveInfoFlags.of(PackageManager.MATCH_ALL.toLong())

        val activities = packageManager.queryIntentActivities(intent, flags)

        val installedApps = activities.map { resolveInfo ->
            App(
                name = resolveInfo.loadLabel(packageManager).toString(),
                packageName = resolveInfo.activityInfo.packageName,
                icon = resolveInfo.loadIcon(packageManager)
            )
        }

        setContent {
            MinLauncherTheme {
                Column {
                    installedApps.forEach {
                        Text(
                            text = it.name,
                            modifier = Modifier
                                .clickable {
                                    launchApp(it.packageName)
                                }
                        )
                    }
                }
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }
            }
        }
    }
}

//@Composable
//fun MessageList(messages: List<App>) {
//    Column {
//        messages.forEach {
//            Text(
//                text = it.name,
//                modifier = Modifier
//                    .clickable {
//                        it.launch()
//                    }
//            )
//        }
//    }
//}

fun Context.launchApp(packageName: String) {
    val intent = packageManager.getLaunchIntentForPackage(packageName) ?: return
    startActivity(intent)
}

data class App(
    val name: String,
    val packageName: String,
    val icon: Drawable?
)