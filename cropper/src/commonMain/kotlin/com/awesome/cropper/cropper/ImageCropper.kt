package com.awesome.cropper.cropper

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
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

    var croppedImage by remember { mutableStateOf<ImageBitmap?>(null) }

    val imageSize = 800.dp

    Box(
        modifier = Modifier.fillMaxSize().background(Color.Red)
    ) {
        KamelImage(
            resource = asyncPainterResource(data = File(imagePath)),
            contentDescription = null,
            modifier = Modifier.size(imageSize),
            contentScale = ContentScale.Crop
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

                                val maxX =
                                    imageSize.roundToPx() - croppingRectSize.width.dp.roundToPx()
                                val maxY =
                                    imageSize.roundToPx() - croppingRectSize.height.dp.roundToPx()

                                // Check if the new position is within the boundaries
                                val newX =
                                    (croppingRectPosition.x + pan.x).coerceIn(0f, maxX.toFloat())
                                val newY =
                                    (croppingRectPosition.y + pan.y).coerceIn(0f, maxY.toFloat())

                                croppingRectPosition = Offset(newX, newY)
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