package dev.ymuratov.core.ui.components.image

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

@Composable
fun ImagePlaceholder(modifier: Modifier = Modifier, placeholder: Int? = null) {
    Box(modifier = modifier.background(color = Color.LightGray)) {
        placeholder?.let {
            Image(
                painter = painterResource(placeholder),
                contentDescription = null,
                modifier = Modifier.matchParentSize(),
                contentScale = ContentScale.Crop
            )
        }
    }
}