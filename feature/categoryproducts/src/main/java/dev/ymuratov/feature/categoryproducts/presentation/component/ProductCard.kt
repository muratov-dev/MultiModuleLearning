package dev.ymuratov.feature.categoryproducts.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import dev.ymuratov.core.models.ProductModel
import dev.ymuratov.core.ui.R
import dev.ymuratov.core.ui.components.image.AppAsyncImage
import dev.ymuratov.core.ui.theme.AppTheme

@Composable
fun ProductCard(product: ProductModel, modifier: Modifier = Modifier, onClick: (ProductModel) -> Unit = {}) {
    val shape = RoundedCornerShape(12.dp)
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = AppTheme.colors.backgroundSecondary, shape = shape)
            .clip(shape = shape)
            .clickable { onClick(product) }) {
        AppAsyncImage(
            data = product.thumbnail,
            contentDescription = product.title,
            placeholder = R.drawable.ic_placeholder,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
        )
        Column(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(8.dp), verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = product.title,
                style = AppTheme.typography.titleLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = AppTheme.colors.textPrimary
            )
            product.brand?.let {
                Text(
                    text = it,
                    style = AppTheme.typography.bodySmall,
                    color = AppTheme.colors.textSecondary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
            Spacer(Modifier.height(4.dp))
            PriceBlock(product)
        }
    }
}