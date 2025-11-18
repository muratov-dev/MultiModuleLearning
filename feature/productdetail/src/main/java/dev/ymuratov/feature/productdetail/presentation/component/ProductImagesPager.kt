package dev.ymuratov.feature.productdetail.presentation.component

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.ymuratov.core.ui.components.image.AppAsyncImage

@Composable
fun ProductImagesPager(pagerState: PagerState, images: List<String>, modifier: Modifier = Modifier) {
    HorizontalPager(state = pagerState, pageSpacing = 16.dp, modifier = modifier.fillMaxWidth()) { page ->
        AppAsyncImage(
            data = images[page], modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f / 0.8f)
        )
    }
}