package dev.ymuratov.feature.productdetail.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.ymuratov.core.models.ProductModel
import dev.ymuratov.core.ui.theme.AppTheme
import dev.ymuratov.feature.productdetail.R

@Composable
fun ProductReviews(product: ProductModel, reviewsPagerState: PagerState, modifier: Modifier = Modifier) {
    with(product) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = AppTheme.colors.backgroundSecondary, shape = RoundedCornerShape(24.dp))
                .padding(vertical = 16.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.reviews_title_placeholder, reviews.size),
                    color = AppTheme.colors.textPrimary,
                    style = AppTheme.typography.bodyMedium
                )
                Spacer(Modifier.weight(1f))
                Text(text = "⭐${product.rating}", color = AppTheme.colors.textSecondary, style = AppTheme.typography.bodyMedium)
            }
            Spacer(Modifier.size(16.dp))
            ProductReviewsPager(pagerState = reviewsPagerState, reviews = reviews)
        }
    }
}