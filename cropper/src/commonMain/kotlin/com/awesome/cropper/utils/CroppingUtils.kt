package com.awesome.cropper.utils

import androidx.compose.ui.geometry.Offset
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

    fun PointerInputScope.movingOffsetWhileTouching(
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
                croppingRectPosition.x.dp.roundToPx().toFloat(),
                croppingRectPosition.y.dp.roundToPx().toFloat()
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
        val widthRatio = image.width.toFloat() / windowSize.width.toFloat()
        val heightRatio = image.height.toFloat() / windowSize.height.toFloat()

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
}