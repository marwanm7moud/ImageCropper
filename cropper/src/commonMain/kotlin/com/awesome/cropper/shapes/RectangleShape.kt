package com.awesome.cropper.shapes

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.ClipOp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipPath
import com.awesome.cropper.shapes.utils.drawRectangleGrid

internal fun DrawScope.rectangleShape(
    croppingShapePosition: Offset,
    croppingShapeSize: Size,
    croppingShapeStrokeWidth: Float,
    croppingShapeStrokeColor: Color,
    gridStrokeWidth: Float,
    gridStrokeColor: Color,
    showGridLines: Boolean,
    backGroundAlpha:Float
) {
    val rectPath = Path().apply {
        addRect(Rect(croppingShapePosition, Size(croppingShapeSize.width, croppingShapeSize.height)))
    }
    clipPath(rectPath, clipOp = ClipOp.Difference) {
        drawRect(
            SolidColor(Color.Black.copy(alpha = backGroundAlpha))
        )
    }
    drawRect(
        color = Color.Transparent, // Make the inside transparent
        topLeft = croppingShapePosition,
        size = Size(croppingShapeSize.width, croppingShapeSize.height)
    )
    if (croppingShapeStrokeWidth > 0) {
        drawRect(
            color = croppingShapeStrokeColor, // Add a white border
            topLeft = croppingShapePosition,
            size = Size(croppingShapeSize.width, croppingShapeSize.height),
            style = Stroke(croppingShapeStrokeWidth) // Adjust the border width as needed
        )
    }

    if (showGridLines)
        drawRectangleGrid(
            croppingShapeSize = croppingShapeSize,
            linesColor = gridStrokeColor,
            croppingRectPosition = croppingShapePosition,
            strokeWidth = gridStrokeWidth
        )
}