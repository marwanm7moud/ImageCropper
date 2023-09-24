package com.awesome.cropper.cropper

import androidx.compose.foundation.Canvas
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
import androidx.compose.ui.input.pointer.pointerInput
import com.awesome.cropper.shapes.rectangleShape
import com.awesome.cropper.utils.CroppingUtils.checkIfTouchInCroppingShape
import com.awesome.cropper.utils.CroppingUtils.movingOffsetWhileTouching

@Composable
fun CroppingShape(
    aspectRatio: Float = 1f, //use it when we use Scale Fit
    showGridLines: Boolean = true,
    onChange: (croppingRectSize: Size, croppingRectPosition: Offset, windowSize: Size) -> Unit
) {
    var croppingRectSize by remember { mutableStateOf(Size(0f, 0f)) }
    var croppingRectPosition by remember { mutableStateOf(Offset(0f, 0f)) }
    var isTouchingTheCroppingShape by remember { mutableStateOf(false) }

    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .aspectRatio(aspectRatio)
            .pointerInput(Unit) {
                detectTransformGestures { _, pan, _, _ ->
                    if (isTouchingTheCroppingShape)
                        croppingRectPosition = movingOffsetWhileTouching(
                            Size(
                                size.width.toFloat(),
                                size.height.toFloat()
                            ), croppingRectSize, croppingRectPosition, pan
                        )
                }
            }
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        isTouchingTheCroppingShape =
                            checkIfTouchInCroppingShape(it, croppingRectSize, croppingRectPosition)
                    }
                )
            }
    ) {
        croppingRectSize = size * 0.5f
        onChange(
            croppingRectSize,
            croppingRectPosition,
            size
        )
        rectangleShape(
            croppingRectSize = croppingRectSize,
            croppingRectPosition = croppingRectPosition,
            showGridLines = showGridLines
        )
    }
}