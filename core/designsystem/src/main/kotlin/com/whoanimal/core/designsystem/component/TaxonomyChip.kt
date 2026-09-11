package com.whoanimal.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.whoanimal.core.designsystem.theme.WhoAnimalPalette
import com.whoanimal.core.designsystem.theme.WhoAnimalTheme

@Composable
fun TaxonomyChip(
    text: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(28.dp)
            .clip(WhoAnimalTheme.shapes.pill)
            .background(WhoAnimalPalette.Sage.copy(alpha = 0.20f))
            .border(
                width = 0.5.dp,
                color = WhoAnimalPalette.Sage.copy(alpha = 0.35f),
                shape = WhoAnimalTheme.shapes.pill
            )
            .padding(horizontal = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = WhoAnimalTheme.typography.caption,
            color = WhoAnimalTheme.colors.textPrimary
        )
    }
}
