package com.awesome.cropper.cropper

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.round
import androidx.compose.ui.unit.toSize
import com.awesome.cropper.utils.CroppingUtils
import com.awesome.cropper.utils.CroppingUtils.movingOffsetWhileTouching

@Composable
fun CroppingRectangle(
    //imageSize: Dp,
    showGridLines: Boolean = true
) {
    var croppingRectSize by remember { mutableStateOf(Size(200f, 200f)) }
    var croppingRectPosition by remember { mutableStateOf(Offset(100f, 100f)) }

    var isCroppingRectMoving by remember { mutableStateOf(false) }
    val touchOffset by remember { mutableStateOf(Offset(0f, 0f)) }
    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTransformGestures { _, pan, _, _ ->
                    when {
                        // Check if touch is within the cropping rectangle
                        CroppingUtils.checkIfTouchInCroppingRectangle(touchOffset, croppingRectSize) -> {
                            croppingRectPosition = movingOffsetWhileTouching(Size(size.width.toFloat() , size.height.toFloat()) ,croppingRectSize ,croppingRectPosition , pan )
                            isCroppingRectMoving = true
                        }
                        // The user is not touching the cropping rectangle
                        else -> isCroppingRectMoving = false
                    }
                }
            }
    ) {
        drawRect(
            color = Color.Transparent, // Make the inside transparent
            topLeft = croppingRectPosition,
            size = Size(croppingRectSize.width, croppingRectSize.height)
        )
        drawRect(
            color = Color.White, // Add a white border
            topLeft = croppingRectPosition,
            size = Size(croppingRectSize.width, croppingRectSize.height),
            style = Stroke(2f) // Adjust the border width as needed
        )
        if (showGridLines)
            drawGrid(
                croppingShapeSize = croppingRectSize,
                linesColor = Color.White,
                croppingRectPosition = croppingRectPosition
            )
    }
}