package com.awesome.cropper.shapes.utils

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope

fun DrawScope.drawRectangleCorners(
    croppingRectPosition: Offset,
    croppingRectSize: Size,
    cornersLength: Float,
    cornersWidth: Float,
    cornersColor: Color
) {
    val topLeft = croppingRectPosition
    val topRight = Offset(croppingRectPosition.x + croppingRectSize.width, croppingRectPosition.y)
    val bottomLeft =
        Offset(croppingRectPosition.x, croppingRectPosition.y + croppingRectSize.height)
    val bottomRight = Offset(
        croppingRectPosition.x + croppingRectSize.width,
        croppingRectPosition.y + croppingRectSize.height
    )
    // Top left indicator
    drawRect(
        color = cornersColor,
        topLeft = Offset(topLeft.x, topLeft.y - cornersWidth / 2),
        size = Size(cornersLength, cornersWidth)
    )
    drawRect(
        color = cornersColor,
        topLeft = Offset(croppingRectPosition.x - cornersWidth / 2, croppingRectPosition.y),
        size = Size(cornersWidth, cornersLength)
    )

    // Top right indicator
    drawRect(
        color = cornersColor,
        topLeft = Offset(topRight.x - cornersLength, topRight.y - cornersWidth / 2),
        size = Size(cornersLength, cornersWidth)
    )
    drawRect(
        color = cornersColor,
        topLeft = Offset(topRight.x - cornersWidth / 2, topRight.y),
        size = Size(cornersWidth, cornersLength)
    )

    // Bottom left indicator
    drawRect(
        color = cornersColor,
        topLeft = Offset(bottomLeft.x, bottomLeft.y - cornersWidth / 2),
        size = Size(cornersLength, cornersWidth)
    )
    drawRect(
        color = cornersColor,
        topLeft = Offset(bottomLeft.x - cornersWidth / 2, bottomLeft.y - cornersLength),
        size = Size(cornersWidth, cornersLength)
    )

    // Bottom right indicator
    drawRect(
        color = cornersColor,
        topLeft = Offset(bottomRight.x - cornersLength, bottomRight.y - cornersWidth / 2),
        size = Size(cornersLength, cornersWidth)
    )
    drawRect(
        color = cornersColor,
        topLeft = Offset(bottomRight.x - cornersWidth / 2, bottomRight.y - cornersLength),
        size = Size(cornersWidth, cornersLength)
    )
}