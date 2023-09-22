package com.awesome.cropper.cropper

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
fun DrawScope.drawGrid(
    croppingShapeSize : Size,
    linesColor: Color
){
    // Draw horizontal lines
    val horizontalSpacing = croppingShapeSize.height.dp.roundToPx().toFloat() / 3
    val verticalSpacing = croppingShapeSize.width.dp.roundToPx().toFloat() / 3

    for (i in 1 until 3) {
        val y = i * horizontalSpacing
        drawLine(
            color = linesColor,
            start = Offset(0f, y),
            end = Offset(croppingShapeSize.width.dp.roundToPx().toFloat(), y),
            strokeWidth = 1f
        )
    }

    // Draw vertical lines

    for (i in 1 until 3) {
        val x = i * verticalSpacing
        drawLine(
            color = linesColor,
            start = Offset(x, 0f),
            end = Offset(x, croppingShapeSize.height.dp.roundToPx().toFloat()),
            strokeWidth = 1f
        )
    }
}