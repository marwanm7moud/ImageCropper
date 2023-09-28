package com.awesome.cropper.utils

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import com.awesome.cropper.CroppingManager

internal object CroppingUtils {
    fun checkIfTouchInCroppingShape(
        touchOffset: Offset,
        croppingRectSize: Size,
        croppingRectPosition: Offset
    ): Boolean {
        val x = croppingRectPosition.x
        val y = croppingRectPosition.y
        return touchOffset.x >= x && touchOffset.x <= x + croppingRectSize.width &&
                touchOffset.y >= y && touchOffset.y <= y + croppingRectSize.height
    }

    private fun movingOffsetWhileTouching(
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
                croppingRectSize.width.dp.roundToPx().toFloat(),
                croppingRectSize.height.dp.roundToPx().toFloat()
            )
        }

        // Calculate width and height ratios
        val widthRatio = image.width / windowSize.width
        val heightRatio = image.height / windowSize.height
        println(": ${ Size(size.width * widthRatio, size.height * heightRatio) }"  )

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
        val modifiedStrokeWidth = strokeWidth
        val sides = calculateSidesSizes(rectSize, modifiedStrokeWidth)

        val expandedStrokeWidth = modifiedStrokeWidth * 4  // Triple the stroke width

        // Check if the touch is within the threshold of the left side
        if (touchX >= sides.left - expandedStrokeWidth && touchX <= sides.left + expandedStrokeWidth) {
            return TouchedSide.LEFT
        }

        // Check if the touch is within the threshold of the right side
        if (touchX >= sides.right - expandedStrokeWidth && touchX <= sides.right + expandedStrokeWidth) {
            return TouchedSide.RIGHT
        }

        // Check if the touch is within the threshold of the top side
        if (touchY >= sides.top - expandedStrokeWidth && touchY <= sides.top + expandedStrokeWidth) {
            return TouchedSide.TOP
        }

        // Check if the touch is within the threshold of the bottom side
        if (touchY >= sides.bottom - expandedStrokeWidth && touchY <= sides.bottom + expandedStrokeWidth) {
            return TouchedSide.BOTTOM
        }

        return TouchedSide.NONE
    }

    private fun calculateSidesSizes(rectSize: Size, strokeWidth: Float): Rect {
        val left = -strokeWidth / 2f
        val top = -strokeWidth / 2f
        val right = rectSize.width + strokeWidth / 2f
        val bottom = rectSize.height + strokeWidth / 2f

        return Rect(left, top, right, bottom)
    }

    fun resizeShapeWhenDrag(
        touchedSide: TouchedSide,
        croppingShapeSize: Size,
        croppingShapePosition: Offset,
        croppingShapeStrokeWidth: Float,
        windowSize: Size,
        pan: Offset
    ): Pair<Size, Offset> {
        return when (touchedSide) {
            TouchedSide.LEFT -> {
                val newWidth = croppingShapeSize.width - pan.x
                val maxX = windowSize.width // Adjust with stroke width
                val newCroppingRectPositionX = croppingShapePosition.x + pan.x

                if (newWidth > croppingShapeStrokeWidth &&
                    newCroppingRectPositionX >= 0 &&
                    newCroppingRectPositionX + newWidth <= maxX
                ) {
                    return Pair(
                        Size(newWidth, croppingShapeSize.height),
                        Offset(newCroppingRectPositionX, croppingShapePosition.y)
                    )
                }
                return Pair(croppingShapeSize, croppingShapePosition)
            }

            TouchedSide.RIGHT -> {
                val newWidth = croppingShapeSize.width + pan.x
                val maxX = windowSize.width // Adjust with stroke width

                if (newWidth > croppingShapeStrokeWidth && croppingShapePosition.x + newWidth <= maxX) {
                    return Pair(Size(newWidth, croppingShapeSize.height), croppingShapePosition)
                }
                return Pair(croppingShapeSize, croppingShapePosition)
            }

            TouchedSide.TOP -> {
                val newHeight = croppingShapeSize.height - pan.y
                val maxY = windowSize.height // Adjust with stroke width
                val newCroppingRectPositionY = croppingShapePosition.y + pan.y

                if (newHeight > croppingShapeStrokeWidth &&
                    newCroppingRectPositionY >= 0 &&
                    newCroppingRectPositionY + newHeight <= maxY
                ) {
                    return Pair(
                        Size(croppingShapeSize.width, newHeight),
                        Offset(croppingShapePosition.x, newCroppingRectPositionY)
                    )
                }
                return Pair(croppingShapeSize, croppingShapePosition)
            }

            TouchedSide.BOTTOM -> {
                val newHeight = croppingShapeSize.height + pan.y
                val maxY = windowSize.height // Adjust with stroke width

                if (newHeight > croppingShapeStrokeWidth && croppingShapePosition.y + newHeight <= maxY) {
                    return Pair(Size(croppingShapeSize.width, newHeight), croppingShapePosition)
                }
                return Pair(croppingShapeSize, croppingShapePosition)
            }

            else -> {
                return Pair(
                    croppingShapeSize, movingOffsetWhileTouching(
                        Size(
                            windowSize.width,
                            windowSize.height
                        ), croppingShapeSize, croppingShapePosition, pan
                    )
                )
            }
        }
    }
}