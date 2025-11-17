package dev.ymuratov.feature.productdetail.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import dev.ymuratov.core.ui.theme.AppTheme

@Composable
fun AppPagerIndicator(pageCount: Int, selectedPage: Int, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .wrapContentSize()
            .background(color = AppTheme.colors.backgroundPrimary.copy(alpha = 0.5f), shape = RoundedCornerShape(100.dp))
            .padding(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(pageCount) { index ->
            val isSelected = selectedPage == index
            Box(
                modifier = Modifier
                    .size(if (isSelected) 6.dp else 4.dp)
                    .clip(CircleShape)
                    .background(
                        if (isSelected) AppTheme.colors.textPrimary
                        else AppTheme.colors.textSecondary
                    )
            )
        }
    }
}