package com.whoanimal.core.designsystem.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Flip
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Park
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.whoanimal.core.designsystem.R
import com.whoanimal.core.designsystem.theme.WhoAnimalPalette
import com.whoanimal.core.designsystem.theme.WhoAnimalTheme

data class CardPresentationModel(
    val id: String,
    val commonName: String,
    val scientificName: String,
    val familyOrOrder: String,
    val dateText: String,
    val timeText: String,
    val iucnStatusCode: String,
    val iucnStatusName: String,
    val biologyNotes: String,
    val fieldExperienceNotes: String,
    val locationProtectedText: String,
    val loreTitle: String,
    val loreStory: String
)

@Composable
fun NaturalistCard(
    cardModel: CardPresentationModel,
    modifier: Modifier = Modifier,
    isInitiallyFlipped: Boolean = false,
    onFlipChanged: (Boolean) -> Unit = {}
) {
    var isFlipped by remember { mutableStateOf(isInitiallyFlipped) }

    Box(
        modifier = modifier
            .aspectRatio(5f / 7f)
            .shadow(
                elevation = 6.dp,
                shape = WhoAnimalTheme.shapes.card,
                ambientColor = WhoAnimalPalette.ForestDark.copy(alpha = 0.20f),
                spotColor = WhoAnimalPalette.ForestDark.copy(alpha = 0.15f)
            )
            .clip(WhoAnimalTheme.shapes.card)
            .background(WhoAnimalTheme.colors.surfaceCard)
            .border(
                width = 1.dp,
                color = WhoAnimalPalette.Stone.copy(alpha = 0.30f),
                shape = WhoAnimalTheme.shapes.card
            )
    ) {
        AnimatedContent(
            targetState = isFlipped,
            transitionSpec = {
                fadeIn(animationSpec = spring(stiffness = Spring.StiffnessMediumLow)) togetherWith
                        fadeOut(animationSpec = spring(stiffness = Spring.StiffnessMediumLow))
            },
            label = "card_flip_transition",
            modifier = Modifier.fillMaxSize()
        ) { flipped ->
            if (!flipped) {
                CardFrontView(
                    cardModel = cardModel,
                    onInspectClick = {
                        isFlipped = true
                        onFlipChanged(true)
                    }
                )
            } else {
                CardReverseView(
                    cardModel = cardModel,
                    onBackClick = {
                        isFlipped = false
                        onFlipChanged(false)
                    }
                )
            }
        }
    }
}

@Composable
private fun CardFrontView(
    cardModel: CardPresentationModel,
    onInspectClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(14.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Passe-partout photographic frame (8dp margin)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .clip(RoundedCornerShape(10.dp))
                .background(WhoAnimalPalette.Sage.copy(alpha = 0.18f))
                .border(
                    width = 0.5.dp,
                    color = WhoAnimalPalette.Sage.copy(alpha = 0.40f),
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Rounded.Park,
                    contentDescription = null,
                    tint = WhoAnimalTheme.colors.actionPrimaryFill,
                    modifier = Modifier.size(54.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                TaxonomyChip(text = cardModel.familyOrOrder)
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Species Names Block
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = cardModel.commonName,
                style = WhoAnimalTheme.typography.headlineMedium,
                color = WhoAnimalTheme.colors.textPrimary
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = cardModel.scientificName,
                style = WhoAnimalTheme.typography.scientific,
                color = WhoAnimalTheme.colors.textSecondary
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Field Metadata
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${stringResource(R.string.card_sighting_date_prefix)} ${cardModel.dateText}",
                style = WhoAnimalTheme.typography.caption,
                color = WhoAnimalTheme.colors.textTertiary
            )
            Text(
                text = cardModel.timeText,
                style = WhoAnimalTheme.typography.caption,
                color = WhoAnimalTheme.colors.textTertiary
            )
        }

        Spacer(modifier = Modifier.height(6.dp))

        // Flip button
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            TextButton(
                onClick = onInspectClick,
                colors = ButtonDefaults.textButtonColors(
                    contentColor = WhoAnimalTheme.colors.actionAccentFocus
                )
            ) {
                Icon(
                    imageVector = Icons.Rounded.Flip,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = stringResource(id = R.string.card_action_flip_to_details),
                    style = WhoAnimalTheme.typography.caption
                )
            }
        }
    }
}

@Composable
private fun CardReverseView(
    cardModel: CardPresentationModel,
    onBackClick: () -> Unit
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(14.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        // Return button & Title
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.card_proportion_label),
                style = WhoAnimalTheme.typography.caption,
                color = WhoAnimalTheme.colors.textTertiary
            )
            TextButton(
                onClick = onBackClick,
                colors = ButtonDefaults.textButtonColors(
                    contentColor = WhoAnimalTheme.colors.actionAccentFocus
                )
            ) {
                Icon(
                    imageVector = Icons.Rounded.Flip,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = stringResource(id = R.string.card_action_flip_to_front),
                    style = WhoAnimalTheme.typography.caption
                )
            }
        }

        // PILAR I: INFORMACIÓN CIENTÍFICA
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(WhoAnimalTheme.shapes.badge)
                .background(WhoAnimalPalette.Sage.copy(alpha = 0.15f))
                .padding(10.dp)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = stringResource(id = R.string.card_pillar_scientific_title),
                    style = WhoAnimalTheme.typography.titleMedium,
                    color = WhoAnimalTheme.colors.textPrimary
                )
                Text(
                    text = "${cardModel.iucnStatusCode} · ${cardModel.iucnStatusName}",
                    style = WhoAnimalTheme.typography.caption,
                    color = WhoAnimalTheme.colors.textSecondary
                )
                Text(
                    text = cardModel.biologyNotes,
                    style = WhoAnimalTheme.typography.bodyMedium,
                    color = WhoAnimalTheme.colors.textPrimary
                )
            }
        }

        // PILAR II: EXPERIENCIA DEL ENCUENTRO
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(WhoAnimalTheme.shapes.badge)
                .background(WhoAnimalPalette.Mist)
                .padding(10.dp)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = stringResource(id = R.string.card_pillar_experience_title),
                    style = WhoAnimalTheme.typography.titleMedium,
                    color = WhoAnimalTheme.colors.textPrimary
                )
                Text(
                    text = cardModel.fieldExperienceNotes,
                    style = WhoAnimalTheme.typography.fieldNote,
                    color = WhoAnimalTheme.colors.textPrimary
                )
                Text(
                    text = cardModel.locationProtectedText,
                    style = WhoAnimalTheme.typography.caption,
                    color = WhoAnimalTheme.colors.textTertiary
                )
            }
        }

        // PILAR III: LORE Y MITOS (Con advertencia visible obligatoria)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(WhoAnimalTheme.shapes.badge)
                .background(WhoAnimalPalette.Cream)
                .border(
                    width = 1.dp,
                    color = WhoAnimalTheme.colors.cautionBorder,
                    shape = WhoAnimalTheme.shapes.badge
                )
                .padding(10.dp)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = stringResource(id = R.string.card_pillar_lore_title),
                    style = WhoAnimalTheme.typography.titleMedium,
                    color = WhoAnimalTheme.colors.textPrimary
                )

                // Mandatory Ludic Fiction Warning
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(WhoAnimalTheme.colors.cautionFill)
                        .padding(6.dp)
                ) {
                    Row(verticalAlignment = Alignment.Top) {
                        Icon(
                            imageVector = Icons.Rounded.Info,
                            contentDescription = null,
                            tint = WhoAnimalTheme.colors.cautionText,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = stringResource(id = R.string.card_lore_disclaimer),
                            style = WhoAnimalTheme.typography.caption,
                            color = WhoAnimalTheme.colors.cautionText
                        )
                    }
                }

                Text(
                    text = cardModel.loreTitle,
                    style = WhoAnimalTheme.typography.bodyLarge,
                    color = WhoAnimalTheme.colors.textPrimary
                )
                Text(
                    text = cardModel.loreStory,
                    style = WhoAnimalTheme.typography.fieldNote,
                    color = WhoAnimalTheme.colors.textSecondary
                )
            }
        }
    }
}
