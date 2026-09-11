package com.whoanimal.core.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

object WhoAnimalPalette {
    // Official Nuclear Palette (docs/design/COLOR_SYSTEM.md)
    val Mist = Color(0xFFEBF0ED)
    val Sage = Color(0xFF8EA89D)
    val DeepTeal = Color(0xFF1A4240)
    val Forest = Color(0xFF233D34)
    val Stone = Color(0xFF7B857F)
    val Cream = Color(0xFFF7F5F0)

    // Semantic Text & Legibility
    val ForestDark = Color(0xFF14221D)      // Primary text on light surfaces (WCAG AAA)
    val StoneDark = Color(0xFF4F5752)       // Secondary text (scientific names, dates)
    val StoneSubtle = Color(0xFF7B857F)     // Tertiary text & borders

    // Circadian Ambient Atmosphere
    val DawnMist = Color(0xFFF3ECE6)
    val DayMist = Color(0xFFEBF0ED)
    val DuskGold = Color(0xFFECE4D8)
    val NightForest = Color(0xFF121917)
    val NightCream = Color(0xFFE2DFD8)

    // Responsible Caution (100% Pet Friendly - Mineral Ochre / Earth Amber)
    val CautionFill = Color(0x2EB8860B)     // Muted mineral ochre at ~18% alpha
    val CautionBorder = Color(0x66B8860B)
    val CautionText = Color(0xFF6B4E03)     // Earth amber dark
}

@Immutable
data class WhoAnimalColors(
    val backgroundApp: Color = WhoAnimalPalette.Mist,
    val surfaceCard: Color = WhoAnimalPalette.Cream,
    val surfaceContainerSubtle: Color = WhoAnimalPalette.Sage.copy(alpha = 0.20f),
    val actionPrimaryFill: Color = WhoAnimalPalette.Forest,
    val actionPrimaryText: Color = WhoAnimalPalette.Cream,
    val actionSecondaryStroke: Color = WhoAnimalPalette.Sage,
    val actionAccentFocus: Color = WhoAnimalPalette.DeepTeal,
    val textPrimary: Color = WhoAnimalPalette.ForestDark,
    val textSecondary: Color = WhoAnimalPalette.StoneDark,
    val textTertiary: Color = WhoAnimalPalette.StoneSubtle,
    val textLink: Color = WhoAnimalPalette.DeepTeal,
    val cautionFill: Color = WhoAnimalPalette.CautionFill,
    val cautionBorder: Color = WhoAnimalPalette.CautionBorder,
    val cautionText: Color = WhoAnimalPalette.CautionText
)

val LocalWhoAnimalColors = staticCompositionLocalOf { WhoAnimalColors() }
