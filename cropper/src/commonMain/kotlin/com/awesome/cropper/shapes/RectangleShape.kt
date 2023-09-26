package com.awesome.cropper.shapes

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import com.awesome.cropper.shapes.utils.drawRectangleGrid

fun DrawScope.rectangleShape(
    croppingShapePosition: Offset,
    croppingShapeSize: Size,
    croppingShapeStrokeWidth: Float,
    croppingShapeStrokeColor: Color,
    gridStrokeWidth: Float,
    gridStrokeColor: Color,
    showGridLines: Boolean,
) {
    drawRect(
        color = Color.Transparent, // Make the inside transparent
        topLeft = croppingShapePosition,
        size = Size(croppingShapeSize.width, croppingShapeSize.height)
    )
    drawRect(
        color = croppingShapeStrokeColor, // Add a white border
        topLeft = croppingShapePosition,
        size = Size(croppingShapeSize.width, croppingShapeSize.height),
        style = Stroke(croppingShapeStrokeWidth) // Adjust the border width as needed
    )

    if (showGridLines)
        drawRectangleGrid(
            croppingShapeSize = croppingShapeSize,
            linesColor = gridStrokeColor,
            croppingRectPosition = croppingShapePosition,
            strokeWidth = gridStrokeWidth
        )
}