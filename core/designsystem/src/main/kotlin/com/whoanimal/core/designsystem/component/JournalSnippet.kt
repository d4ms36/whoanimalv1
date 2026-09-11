package com.whoanimal.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Nature
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.whoanimal.core.designsystem.theme.WhoAnimalPalette
import com.whoanimal.core.designsystem.theme.WhoAnimalTheme

@Composable
fun JournalSnippet(
    commonName: String,
    scientificName: String,
    timeOrDateText: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(72.dp)
            .clip(WhoAnimalTheme.shapes.snippet)
            .background(WhoAnimalTheme.colors.surfaceCard)
            .border(
                width = 1.dp,
                color = WhoAnimalPalette.Stone.copy(alpha = 0.25f),
                shape = WhoAnimalTheme.shapes.snippet
            )
            .clickable(onClick = onClick)
            .padding(horizontal = 12.dp, vertical = 8.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            // Square thumbnail with natural styling
            Box(
                modifier = Modifier
                    .size(52.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(WhoAnimalPalette.Sage.copy(alpha = 0.25f))
                    .border(0.5.dp, WhoAnimalPalette.Sage.copy(alpha = 0.4f), RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Rounded.Nature,
                    contentDescription = null,
                    tint = WhoAnimalTheme.colors.actionAccentFocus,
                    modifier = Modifier.size(26.dp)
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = commonName,
                    style = WhoAnimalTheme.typography.bodyLarge,
                    color = WhoAnimalTheme.colors.textPrimary
                )
                Text(
                    text = scientificName,
                    style = WhoAnimalTheme.typography.scientific,
                    color = WhoAnimalTheme.colors.textSecondary
                )
            }

            Text(
                text = timeOrDateText,
                style = WhoAnimalTheme.typography.caption,
                color = WhoAnimalTheme.colors.textTertiary
            )
        }
    }
}
