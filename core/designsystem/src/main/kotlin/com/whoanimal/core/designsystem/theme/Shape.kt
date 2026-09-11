package com.whoanimal.core.designsystem.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp

@Immutable
data class WhoAnimalShapes(
    val pill: RoundedCornerShape = RoundedCornerShape(999.dp),
    val card: RoundedCornerShape = RoundedCornerShape(16.dp),
    val snippet: RoundedCornerShape = RoundedCornerShape(12.dp),
    val badge: RoundedCornerShape = RoundedCornerShape(8.dp),
    val viewfinder: RoundedCornerShape = RoundedCornerShape(20.dp)
)

val LocalWhoAnimalShapes = staticCompositionLocalOf { WhoAnimalShapes() }
