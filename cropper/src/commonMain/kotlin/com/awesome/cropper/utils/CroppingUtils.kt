package com.awesome.cropper.utils

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.input.pointer.PointerInputScope
import androidx.compose.ui.unit.dp

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
            imageSize.width.dp.roundToPx() - croppingRectSize.width.dp.roundToPx()
        val maxY =
            imageSize.width.dp.roundToPx() - croppingRectSize.height.dp.roundToPx()

        val newX =
            (croppingRectPosition.x + pan.x).coerceIn(0f, maxX.toFloat())
        val newY =
            (croppingRectPosition.y + pan.y).coerceIn(0f, maxY.toFloat())
        return Offset(newX, newY)
    }
}