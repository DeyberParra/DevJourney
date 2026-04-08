package com.app.profile.ui

import android.graphics.BitmapFactory
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext

@Composable
fun rememberAssetPainter(assetPath: String): Painter {
    val context = LocalContext.current
    val imageBitmap = remember(assetPath) {
        try {
            context.assets.open(assetPath).use { inputStream ->
                BitmapFactory.decodeStream(inputStream).asImageBitmap()
            }
        } catch (e: Exception) {
            null
        }
    }

    return if (imageBitmap != null) {
        BitmapPainter(imageBitmap)
    } else {
        ColorPainter(Color.LightGray)
    }
}