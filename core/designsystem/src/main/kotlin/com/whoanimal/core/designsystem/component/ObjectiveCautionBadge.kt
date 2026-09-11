package com.whoanimal.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Shield
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.whoanimal.core.designsystem.R
import com.whoanimal.core.designsystem.theme.WhoAnimalTheme

@Composable
fun ObjectiveCautionBadge(
    safeDistanceMeters: Int,
    adviceNotes: String,
    modifier: Modifier = Modifier,
    title: String = stringResource(id = R.string.common_caution_badge_title)
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(WhoAnimalTheme.shapes.badge)
            .background(WhoAnimalTheme.colors.cautionFill)
            .border(
                width = 1.dp,
                color = WhoAnimalTheme.colors.cautionBorder,
                shape = WhoAnimalTheme.shapes.badge
            )
            .padding(14.dp)
    ) {
        Row(
            verticalAlignment = Alignment.Top,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Rounded.Shield,
                contentDescription = null,
                tint = WhoAnimalTheme.colors.cautionText,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = title,
                    style = WhoAnimalTheme.typography.bodyLarge,
                    color = WhoAnimalTheme.colors.cautionText
                )
                if (safeDistanceMeters > 0) {
                    Text(
                        text = stringResource(
                            id = R.string.common_caution_distance_format,
                            safeDistanceMeters
                        ),
                        style = WhoAnimalTheme.typography.bodyMedium,
                        color = WhoAnimalTheme.colors.cautionText
                    )
                }
                if (adviceNotes.isNotBlank()) {
                    Text(
                        text = adviceNotes,
                        style = WhoAnimalTheme.typography.caption,
                        color = WhoAnimalTheme.colors.cautionText
                    )
                }
            }
        }
    }
}
