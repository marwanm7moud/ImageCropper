package com.awesome.cropper.cropper

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.round
import com.awesome.cropper.utils.CroppingUtils
import com.awesome.cropper.utils.CroppingUtils.movingOffsetWhileTouching

@Composable
fun CroppingRectangle(
    imageSize: Dp,
    showGridLines: Boolean = true
) {
    var croppingRectSize by remember { mutableStateOf(Size(200f, 200f)) }
    var croppingRectPosition by remember { mutableStateOf(Offset(100f, 100f)) }

    var isCroppingRectMoving by remember { mutableStateOf(false) }
    val touchOffset by remember { mutableStateOf(Offset(0f, 0f)) }
    Canvas(
        modifier = Modifier
            .size(croppingRectSize.width.dp, croppingRectSize.height.dp)
            .offset { croppingRectPosition.round() }
            .background(Color.Transparent)
            .border(2.dp, Color.White)
            .pointerInput(Unit) {
                detectTransformGestures { _, pan, _, _ ->
                    when {
                        // Check if touch is within the cropping rectangle
                        CroppingUtils.checkIfTouchInCroppingRectangle(touchOffset, croppingRectSize) -> {
                            croppingRectPosition = movingOffsetWhileTouching(Size(imageSize.value , imageSize.value) ,croppingRectSize ,croppingRectPosition , pan )
                            isCroppingRectMoving = true
                        }
                        // The user is not touching the cropping rectangle
                        else -> isCroppingRectMoving = false
                    }
                }
            }
    ) {
        drawRect(
            color = Color.Transparent,
            topLeft = Offset(0f, 0f),
            size = size
        )
        if (showGridLines)
            drawGrid(
                croppingShapeSize = croppingRectSize,
                linesColor = Color.White
            )
    }
}