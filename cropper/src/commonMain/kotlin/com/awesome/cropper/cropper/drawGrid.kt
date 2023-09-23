package com.awesome.cropper.cropper

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
fun DrawScope.drawGrid(
    croppingShapeSize: Size,
    linesColor: Color,
    croppingRectPosition: Offset // The position of the cropping rectangle
) {
    // Draw horizontal lines
    val horizontalSpacing = croppingShapeSize.height / 3
    val verticalSpacing = croppingShapeSize.width / 3

    for (i in 1 until 3) {
        val y = croppingRectPosition.y + i * horizontalSpacing
        drawLine(
            color = linesColor,
            start = Offset(croppingRectPosition.x, y),
            end = Offset(croppingRectPosition.x + croppingShapeSize.width, y),
            strokeWidth = 1f
        )
    }

    // Draw vertical lines
    for (i in 1 until 3) {
        val x = croppingRectPosition.x + i * verticalSpacing
        drawLine(
            color = linesColor,
            start = Offset(x, croppingRectPosition.y),
            end = Offset(x, croppingRectPosition.y + croppingShapeSize.height),
            strokeWidth = 1f
        )
    }
}
