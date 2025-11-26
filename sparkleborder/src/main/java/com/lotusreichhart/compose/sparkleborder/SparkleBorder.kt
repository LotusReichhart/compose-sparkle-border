package com.lotusreichhart.compose.sparkleborder

import androidx.compose.animation.core.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Adds an sparkle rotating border to the component.
 *
 * This modifier draws a rotating gradient brush behind the content and masks it to create
 * a border effect. Ideally used for highlighting UI elements like buttons, cards, or profile pictures.
 *
 * @param brush The [Brush] to be used for the rotating border (e.g., a linear or sweep gradient).
 * @param backgroundColor The [Color] of the inner content background to mask the center of the brush.
 * @param shape The [Shape] of the component. Defaults to a RoundedCornerShape with 12.dp radius.
 * @param borderWidth The thickness of the border in [Dp]. Defaults to 2.dp.
 * @param animationDurationInMillis The duration of one full rotation in milliseconds. Defaults to 2000ms.
 *
 * @return A [Modifier] with the sparkle border effect applied.
 */
fun Modifier.sparkleBorder(
    brush: Brush,
    backgroundColor: Color,
    shape: Shape = RoundedCornerShape(12.dp),
    borderWidth: Dp = 2.dp,
    animationDurationInMillis: Int = 2000
): Modifier = composed {
    val infiniteTransition = rememberInfiniteTransition(label = "BorderRotationAnimation")
    val angle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = animationDurationInMillis, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "AngleValue"
    )

    this
        .clip(shape)
        .drawWithContent {
            val strokeWidthPx = borderWidth.toPx()
            val sizeMax = maxOf(size.width, size.height) * 2f

            // 1. Draw the rotating gradient background
            rotate(angle) {
                drawCircle(
                    brush = brush,
                    radius = sizeMax / 2,
                    center = center
                )
            }

            // 2. Draw a mask to create the border effect
            when (val outline = shape.createOutline(size, layoutDirection, this)) {
                is Outline.Rounded -> {
                    // Extract the corner radius from the shape
                    val r = outline.roundRect.bottomLeftCornerRadius.x

                    // Calculate the inner radius to ensure concentricity
                    val innerRadius = (r - strokeWidthPx).coerceAtLeast(0f)

                    drawRoundRect(
                        color = backgroundColor,
                        topLeft = Offset(strokeWidthPx, strokeWidthPx),
                        size = Size(
                            width = size.width - strokeWidthPx * 2,
                            height = size.height - strokeWidthPx * 2
                        ),
                        cornerRadius = CornerRadius(innerRadius)
                    )
                }
                is Outline.Rectangle -> {
                    drawRect(
                        color = backgroundColor,
                        topLeft = Offset(strokeWidthPx, strokeWidthPx),
                        size = Size(
                            width = size.width - strokeWidthPx * 2,
                            height = size.height - strokeWidthPx * 2
                        )
                    )
                }
                is Outline.Generic -> {
                    // Generic shapes (e.g., CutCornerShape or custom Paths) are complex to mask
                    // using this simple technique. Fallback or advanced path operations required here.
                }
            }

            // 3. Draw the actual content
            drawContent()
        }
}