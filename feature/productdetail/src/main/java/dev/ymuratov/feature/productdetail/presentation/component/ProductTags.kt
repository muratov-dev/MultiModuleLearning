package dev.ymuratov.feature.productdetail.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.ymuratov.core.ui.theme.AppTheme

@Composable
fun ProductTags(tags: List<String>, modifier: Modifier = Modifier) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(tags) {
            Text(
                text = it,
                color = AppTheme.colors.textSecondary,
                style = AppTheme.typography.bodySmall,
                modifier = Modifier
                    .wrapContentSize()
                    .background(AppTheme.colors.backgroundPrimary, shape = RoundedCornerShape(100.dp))
                    .padding(horizontal = 6.dp, vertical = 4.dp)
            )
        }
    }
}