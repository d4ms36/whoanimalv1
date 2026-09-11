package com.whoanimal.core.designsystem.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.whoanimal.core.designsystem.theme.WhoAnimalPalette

/**
 * FieldViewfinder — Official Naturalist Camera Viewfinder
 *
 * Spec: docs/design/COMPONENT_LIBRARY.md
 * Philosophy: A quiet window into nature during an expedition.
 * Not a tactical HUD, military crosshair, cyberpunk scanner, or AI overlay.
 * Geometry: Rectangular observation framing with 4 subtle Cream corners (1dp line, 60% alpha).
 */
@Composable
fun FieldViewfinder(
    modifier: Modifier = Modifier,
    bracketLength: Dp = 28.dp,
    strokeWidth: Dp = 1.dp,
    bracketColor: Color = WhoAnimalPalette.Cream.copy(alpha = 0.60f),
    content: @Composable () -> Unit = {}
) {
    Box(modifier = modifier) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .padding(28.dp)
        ) {
            val strokePx = strokeWidth.toPx()
            val armPx = bracketLength.toPx()
            val cornerRadiusPx = 10.dp.toPx()
            val w = size.width
            val h = size.height

            val stroke = Stroke(
                width = strokePx,
                cap = StrokeCap.Round
            )

            // Top-Left Corner
            val topLeftPath = Path().apply {
                moveTo(0f, armPx)
                lineTo(0f, cornerRadiusPx)
                arcTo(
                    rect = Rect(0f, 0f, cornerRadiusPx * 2, cornerRadiusPx * 2),
                    startAngleDegrees = 180f,
                    sweepAngleDegrees = 90f,
                    forceMoveTo = false
                )
                lineTo(armPx, 0f)
            }
            drawPath(topLeftPath, color = bracketColor, style = stroke)

            // Top-Right Corner
            val topRightPath = Path().apply {
                moveTo(w - armPx, 0f)
                lineTo(w - cornerRadiusPx, 0f)
                arcTo(
                    rect = Rect(w - cornerRadiusPx * 2, 0f, w, cornerRadiusPx * 2),
                    startAngleDegrees = 270f,
                    sweepAngleDegrees = 90f,
                    forceMoveTo = false
                )
                lineTo(w, armPx)
            }
            drawPath(topRightPath, color = bracketColor, style = stroke)

            // Bottom-Left Corner
            val bottomLeftPath = Path().apply {
                moveTo(0f, h - armPx)
                lineTo(0f, h - cornerRadiusPx)
                arcTo(
                    rect = Rect(0f, h - cornerRadiusPx * 2, cornerRadiusPx * 2, h),
                    startAngleDegrees = 180f,
                    sweepAngleDegrees = -90f,
                    forceMoveTo = false
                )
                lineTo(armPx, h)
            }
            drawPath(bottomLeftPath, color = bracketColor, style = stroke)

            // Bottom-Right Corner
            val bottomRightPath = Path().apply {
                moveTo(w - armPx, h)
                lineTo(w - cornerRadiusPx, h)
                arcTo(
                    rect = Rect(w - cornerRadiusPx * 2, h - cornerRadiusPx * 2, w, h),
                    startAngleDegrees = 90f,
                    sweepAngleDegrees = -90f,
                    forceMoveTo = false
                )
                lineTo(w, h - armPx)
            }
            drawPath(bottomRightPath, color = bracketColor, style = stroke)
        }

        content()
    }
}
