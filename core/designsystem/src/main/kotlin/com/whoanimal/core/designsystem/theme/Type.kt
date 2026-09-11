package com.whoanimal.core.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Immutable
data class WhoAnimalTypography(
    // Display Large: 32sp / 40sp, Bold, Serif (display & app title)
    val displayLarge: TextStyle = TextStyle(
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        color = WhoAnimalPalette.ForestDark
    ),
    // Headline Medium: 24sp / 32sp, SemiBold, Serif (common animal names)
    val headlineMedium: TextStyle = TextStyle(
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        color = WhoAnimalPalette.ForestDark
    ),
    // Title Medium: 18sp / 26sp, Medium, Serif (taxonomic families, sections)
    val titleMedium: TextStyle = TextStyle(
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp,
        lineHeight = 26.sp,
        color = WhoAnimalPalette.ForestDark
    ),
    // Body Large: 16sp / 24sp, Regular, Sans-serif (educational text & brief bios)
    val bodyLarge: TextStyle = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        color = WhoAnimalPalette.ForestDark
    ),
    // Body Medium: 14sp / 22sp, Regular, Sans-serif (biological descriptions)
    val bodyMedium: TextStyle = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 22.sp,
        color = WhoAnimalPalette.StoneDark
    ),
    // Label Large: 14sp / 20sp, SemiBold, Sans-serif (Hero button [ DESCUBRIR ], chips)
    val labelLarge: TextStyle = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 1.2.sp,
        color = WhoAnimalPalette.Cream
    ),
    // Caption: 12sp / 16sp, Regular, Sans-serif (dates, credits, micro-labels)
    val caption: TextStyle = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        color = WhoAnimalPalette.StoneDark
    ),
    // Scientific: 15sp / 22sp, Italic (Binomial Genus species)
    val scientific: TextStyle = TextStyle(
        fontFamily = FontFamily.Serif,
        fontStyle = FontStyle.Italic,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        lineHeight = 22.sp,
        color = WhoAnimalPalette.StoneDark
    ),
    // Field Note: 13sp / 20sp, Italic (Field notes & Lore)
    val fieldNote: TextStyle = TextStyle(
        fontFamily = FontFamily.Serif,
        fontStyle = FontStyle.Italic,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp,
        lineHeight = 20.sp,
        color = WhoAnimalPalette.ForestDark
    )
)

val LocalWhoAnimalTypography = staticCompositionLocalOf { WhoAnimalTypography() }
