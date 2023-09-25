package com.awesome.cropper.shapes

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import com.awesome.cropper.shapes.utils.drawRectangleCorners
import com.awesome.cropper.shapes.utils.drawRectangleGrid

fun DrawScope.rectangleShape(
    croppingRectPosition: Offset,
    croppingRectSize: Size,
    showGridLines: Boolean,
    cornersLength: Float = 10f,
    cornersWidth: Float = 4f
) {
    drawRect(
        color = Color.Transparent, // Make the inside transparent
        topLeft = croppingRectPosition,
        size = Size(croppingRectSize.width, croppingRectSize.height)
    )
    drawRect(
        color = Color.White, // Add a white border
        topLeft = croppingRectPosition,
        size = Size(croppingRectSize.width, croppingRectSize.height),
        style = Stroke(2f) // Adjust the border width as needed
    )
    drawRectangleCorners(
        croppingRectSize = croppingRectSize,
        croppingRectPosition = croppingRectPosition,
        cornersLength = cornersLength,
        cornersWidth = cornersWidth,
        cornersColor = Color.Green
    )
    if (showGridLines)
        drawRectangleGrid(
            croppingShapeSize = croppingRectSize,
            linesColor = Color.White,
            croppingRectPosition = croppingRectPosition
        )
}