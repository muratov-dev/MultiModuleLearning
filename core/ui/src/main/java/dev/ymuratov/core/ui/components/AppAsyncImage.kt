package dev.ymuratov.core.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.CachePolicy
import coil.request.ImageRequest

@Composable
fun AppAsyncImage(
    modifier: Modifier = Modifier,
    data: Any? = null,
    contentDescription: String? = null,
    @DrawableRes placeholder: Int? = null,
    contentScale: ContentScale = ContentScale.Crop
) {
    val image = ImageRequest.Builder(LocalContext.current).data(data).crossfade(true).build()
    SubcomposeAsyncImage(
        model = image, contentDescription = contentDescription, contentScale = contentScale, modifier = modifier
    ) {
        when (painter.state) {
            is AsyncImagePainter.State.Success -> SubcomposeAsyncImageContent()
            is AsyncImagePainter.State.Loading -> LoadingView(modifier = modifier)
            else -> ImagePlaceholder(placeholder = placeholder)
        }
    }
}