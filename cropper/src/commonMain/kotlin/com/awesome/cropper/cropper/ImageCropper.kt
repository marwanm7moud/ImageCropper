package com.awesome.cropper.cropper

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.unit.dp
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.round
import com.awesome.cropper.CroppingManager
import com.awesome.cropper.getScreenSize
import io.kamel.core.getOrNull
import io.kamel.core.utils.File
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource


@Composable
fun ImageCropper(
    imagePath: String
) {
    var croppingRectSize by remember { mutableStateOf(Size(200f, 200f)) }
    var croppingRectPosition by remember { mutableStateOf(Offset(100f, 100f)) }

    var isCroppingRectMoving by remember { mutableStateOf(false) }
    val touchOffset by remember { mutableStateOf(Offset(0f, 0f)) }

    Box(
        modifier = Modifier.fillMaxSize().background(Color.Red)
    ) {
        KamelImage(
            resource = asyncPainterResource(data = File(imagePath)),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Fit
        )

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
                            touchOffset.x >= 0 && touchOffset.x <= croppingRectSize.width &&
                                    touchOffset.y >= 0 && touchOffset.y <= croppingRectSize.height -> {
                                // The user is touching and dragging the cropping rectangle
                                croppingRectPosition = Offset(
                                    croppingRectPosition.x + pan.x,
                                    croppingRectPosition.y + pan.y
                                )
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
        }
    }
}