package dev.ymuratov.feature.productdetail.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.ymuratov.core.models.ReviewModel
import dev.ymuratov.core.ui.theme.AppTheme

@Composable
fun ProductReviewsPager(pagerState: PagerState, reviews: List<ReviewModel>, modifier: Modifier = Modifier) {
    HorizontalPager(
        state = pagerState,
        pageSpacing = 8.dp,
        contentPadding = PaddingValues(horizontal = 32.dp),
        modifier = modifier.fillMaxWidth()
    ) { page ->
        val review = reviews[page]

        Box(
            Modifier
                .fillMaxWidth()
                .height(120.dp)
                .background(color = AppTheme.colors.backgroundPrimary, shape = RoundedCornerShape(16.dp))
                .padding(16.dp)
        ) {
            Column {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = review.reviewerName, color = AppTheme.colors.textPrimary, style = AppTheme.typography.bodyMedium
                    )
                    Spacer(Modifier.weight(1f))
                    Text(
                        text = "⭐${review.rating}", color = AppTheme.colors.textSecondary, style = AppTheme.typography.bodyMedium
                    )
                }
                Spacer(Modifier.size(4.dp))
                Text(text = review.date, color = AppTheme.colors.textSecondary, style = AppTheme.typography.bodySmall)
                Spacer(Modifier.size(8.dp))
                Text(text = review.comment, color = AppTheme.colors.textPrimary, style = AppTheme.typography.bodyRegular)
            }
        }
    }
}