package com.awesome.cropper.cropper

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.toSize
import com.awesome.cropper.shapes.rectangleShape
import com.awesome.cropper.utils.CroppingUtils.calculateCroppingShapePositionWhenWindowResized
import com.awesome.cropper.utils.CroppingUtils.calculateCroppingShapeSizeWhenWindowResized
import com.awesome.cropper.utils.CroppingUtils.checkIfTouchInCroppingShape
import com.awesome.cropper.utils.CroppingUtils.detectTouchedSide
import com.awesome.cropper.utils.CroppingUtils.resizeShapeWhenDrag
import com.awesome.cropper.utils.TouchedSide

@Composable
fun CroppingShape(
    aspectRatio: Float = 1f,
    croppingShapeStrokeWidth: Float = 10f,
    croppingShapeStrokeColor: Color = Color.White,
    gridStrokeWidth: Float = 2f,
    gridStrokeColor: Color = Color.White,
    showGridLines: Boolean = true,
    onChangeWindowSize: (croppingRectSize: Size, croppingRectPosition: Offset, windowSize: Size) -> Unit
) {
    var croppingRectSize by remember { mutableStateOf(Size(200f, 200f)) }
    var croppingRectPosition by remember { mutableStateOf(Offset(0f, 0f)) }
    var isTouchingTheCroppingShape by remember { mutableStateOf(false) }
    var previousWindowSize by remember { mutableStateOf(Size(0f, 0f)) }

    var touchedSide by remember { mutableStateOf(TouchedSide.NONE) }

    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .aspectRatio(aspectRatio)
            .pointerInput(Unit) {
                detectTransformGestures { _, pan, _, _ ->
                    val resizedArgs = resizeShapeWhenDrag(
                    touchedSide,
                    croppingRectSize,
                    croppingRectPosition,
                    croppingShapeStrokeWidth,
                    Size(size.width.toFloat() , size.height.toFloat()),
                    pan
                )
                    if (isTouchingTheCroppingShape) {
                        croppingRectSize = resizedArgs.first
                        croppingRectPosition = resizedArgs.second
                    }
                }
            }
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        isTouchingTheCroppingShape =
                            checkIfTouchInCroppingShape(it, croppingRectSize, croppingRectPosition)

                        // Determine which side is touched based on the touch point
                        val touchX = it.x - croppingRectPosition.x
                        val touchY = it.y - croppingRectPosition.y
                        touchedSide = detectTouchedSide(
                            touchX,
                            touchY,
                            croppingRectSize,
                            croppingShapeStrokeWidth
                        )
                        println(touchedSide)
                        awaitRelease()
                        touchedSide = TouchedSide.NONE
                    }
                )
            }
    ) {
        if (previousWindowSize == Size(0f, 0f)) previousWindowSize = size
        if (previousWindowSize != size) {
            croppingRectSize =
                calculateCroppingShapeSizeWhenWindowResized(
                    previousWindowSize,
                    size,
                    croppingRectSize
                )
            croppingRectPosition =
                calculateCroppingShapePositionWhenWindowResized(
                    previousWindowSize,
                    size,
                    croppingRectPosition
                )
            previousWindowSize = size

        }
        onChangeWindowSize(
            croppingRectSize,
            croppingRectPosition,
            size
        )
        rectangleShape(
            croppingShapeSize = croppingRectSize,
            croppingShapePosition = croppingRectPosition,
            croppingShapeStrokeWidth = croppingShapeStrokeWidth,
            croppingShapeStrokeColor = croppingShapeStrokeColor,
            showGridLines = showGridLines,
            gridStrokeWidth = gridStrokeWidth,
            gridStrokeColor = gridStrokeColor
        )
    }
}