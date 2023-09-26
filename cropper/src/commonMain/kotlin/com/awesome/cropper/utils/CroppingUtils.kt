package com.awesome.cropper.utils

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.input.pointer.PointerInputScope
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import com.awesome.cropper.CroppingManager

object CroppingUtils {
    fun checkIfTouchInCroppingShape(
        touchOffset: Offset,
        croppingRectSize: Size,
        croppingRectPosition: Offset
    ): Boolean {
        val x = croppingRectPosition.x
        val y = croppingRectPosition.y
        return return touchOffset.x >= x && touchOffset.x <= x + croppingRectSize.width &&
                touchOffset.y >= y && touchOffset.y <= y + croppingRectSize.height
    }

    fun movingOffsetWhileTouching(
        imageSize: Size,
        croppingRectSize: Size,
        croppingRectPosition: Offset,
        pan: Offset
    ): Offset {
        val maxX =
            imageSize.width - croppingRectSize.width
        val maxY =
            imageSize.height - croppingRectSize.height

        val newX =
            (croppingRectPosition.x + pan.x).coerceIn(0f, maxX)
        val newY =
            (croppingRectPosition.y + pan.y).coerceIn(0f, maxY)
        return Offset(newX, newY)
    }

    fun cropAndShowImage(
        image: ImageBitmap,
        croppingRectSize: Size,
        croppingRectPosition: Offset,
        windowSize: Size,
        density: Density,
    ): ImageBitmap {
        // Calculate the cropping rectangle position in pixels
        val position = density.run {
            Offset(
                croppingRectPosition.x.dp.toPx(),
                croppingRectPosition.y.dp.toPx()
            )
        }
        // Calculate the cropping rectangle dimensions in pixels
        val size = density.run {
            Size(
                croppingRectSize.width.dp.toPx(),
                croppingRectSize.height.dp.toPx()
            )
        }

        // Calculate width and height ratios
        val widthRatio = image.width / windowSize.width
        val heightRatio = image.height / windowSize.height

        return CroppingManager().cropImageByImageBitmap(
            image,
            cropPosition = Offset(
                position.x * widthRatio,
                position.y * heightRatio
            ),
            cropSize = Size(size.width * widthRatio, size.height * heightRatio),
            windowSize = windowSize,
        )
    }

    fun calculateCroppingShapePositionWhenWindowResized(
        previousWindowSize: Size,
        windowSize: Size,
        croppingShapePosition: Offset
    ): Offset {
        val ratioX = windowSize.width / previousWindowSize.width
        val ratioY = windowSize.height / previousWindowSize.height
        return Offset(
            croppingShapePosition.x * ratioX,
            croppingShapePosition.y * ratioY
        )
    }
    fun calculateCroppingShapeSizeWhenWindowResized(
        previousSize: Size,
        newSize: Size,
        croppingRectSize: Size
    ): Size {
        val widthRatio = newSize.width / previousSize.width
        val heightRatio = newSize.height / previousSize.height
        val newWidth = croppingRectSize.width * widthRatio
        val newHeight = croppingRectSize.height * heightRatio
        return Size(newWidth, newHeight)
    }
    // Function to detect which side is being touched
    fun detectTouchedSide(
        touchX: Float,
        touchY: Float,
        rectSize: Size,
        strokeWidth: Float
    ): TouchedSide {
        val sides = calculateSidesSizes(rectSize, strokeWidth)
        val sideThreshold = strokeWidth // Use the stroke width as the threshold

        // Check if the touch is within the threshold of the left side
        if (touchX >= sides.left - sideThreshold && touchX <= sides.left + sideThreshold) {
            return TouchedSide.LEFT
        }

        // Check if the touch is within the threshold of the right side
        if (touchX >= sides.right - sideThreshold && touchX <= sides.right + sideThreshold) {
            return TouchedSide.RIGHT
        }

        // Check if the touch is within the threshold of the top side
        if (touchY >= sides.top - sideThreshold && touchY <= sides.top + sideThreshold) {
            return TouchedSide.TOP
        }

        // Check if the touch is within the threshold of the bottom side
        if (touchY >= sides.bottom - sideThreshold && touchY <= sides.bottom + sideThreshold) {
            return TouchedSide.BOTTOM
        }

        return TouchedSide.NONE
    }
    private fun calculateSidesSizes(rectSize: Size, strokeWidth: Float): Rect {
        val halfStroke = strokeWidth / 2f
        val left = halfStroke
        val top = halfStroke
        val right = rectSize.width - halfStroke
        val bottom = rectSize.height - halfStroke

        return Rect(left, top, right, bottom)
    }
}