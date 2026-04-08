package com.app.profile.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp

@Composable
fun ProfileImage(image: String) {
    val painter = rememberAssetPainter(image)
    Image(
        painter = painter,
        contentDescription = "Alex Rivera Profile",
        modifier = Modifier
            .size(280.dp)
            .clip(CircleShape)
            .border(6.dp, Color.White, CircleShape),
        contentScale = ContentScale.Crop,
    )
}
