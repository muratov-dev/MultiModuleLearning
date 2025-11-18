package dev.ymuratov.feature.categoryproducts.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import dev.ymuratov.core.models.ProductModel
import dev.ymuratov.core.ui.R
import dev.ymuratov.core.ui.theme.AppTheme

@Composable
fun PriceBlock(product: ProductModel) {
    val hasDiscount = product.discountPercentage > 0

    if (!hasDiscount) {
        Text(
            text = stringResource(R.string.price_placeholder, product.price),
            style = AppTheme.typography.titleLarge,
            color = AppTheme.colors.textPrimary
        )
    } else {
        val finalPrice = product.price - (product.price * (product.discountPercentage / 100.0))
        val originalPrice = product.price

        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(
                text = stringResource(R.string.price_placeholder, finalPrice),
                style = AppTheme.typography.titleLarge,
                color = AppTheme.colors.textPrimary
            )
            Text(
                text = stringResource(R.string.price_placeholder, originalPrice),
                style = AppTheme.typography.bodySmall,
                color = AppTheme.colors.textSecondary,
                textDecoration = TextDecoration.LineThrough
            )
        }
    }
}
