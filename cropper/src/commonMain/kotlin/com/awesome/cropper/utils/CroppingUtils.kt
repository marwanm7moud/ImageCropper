package com.awesome.cropper.utils

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.input.pointer.PointerInputScope
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import com.awesome.cropper.CroppingManager
import org.jetbrains.skia.Bitmap

object CroppingUtils {
    fun checkIfTouchInCroppingRectangle(touchOffset: Offset, croppingRectSize: Size): Boolean {
        return touchOffset.x >= 0 && touchOffset.x <= croppingRectSize.width &&
                touchOffset.y >= 0 && touchOffset.y <= croppingRectSize.height
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
        //Calculate the cropping rectangle position in pixels
        val position = density.run {
            Pair(croppingRectPosition.x.dp.roundToPx(), croppingRectPosition.y.dp.roundToPx())
        }
        // Calculate the cropping rectangle dimensions in pixels
        val size = density.run {
            Pair(
                croppingRectSize.width.dp.roundToPx(),
                croppingRectSize.height.dp.roundToPx()
            )
        }
        return CroppingManager().cropImageByImageBitmap(
            image,
            cropPosition = Offset(
                position.first.toFloat(),
                position.second.toFloat()
            ) * ((size.first / size.second).toFloat()),
            cropSize = Size(size.first.toFloat(), size.second.toFloat()),
            windowSize = windowSize,
        )
    }
}