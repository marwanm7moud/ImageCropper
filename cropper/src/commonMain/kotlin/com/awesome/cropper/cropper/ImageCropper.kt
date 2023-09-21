package com.awesome.cropper.cropper

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
    imagePath:String = "C:\\Users\\hp\\Desktop\\4t0oBFrJyweYPt0hocW6RUa0b6H.jpg"
) {
    var croppingRectSize by remember { mutableStateOf(Size(200f, 200f)) }
    var croppingRectPosition by remember { mutableStateOf(Offset(50f, 50f)) }

    var isCroppingRectMoving by remember { mutableStateOf(false) }
    var touchOffset by remember { mutableStateOf(Offset(0f, 0f)) }

    // Create a mutable state to hold the cropped image
    var croppedImage by remember { mutableStateOf<ImageBitmap?>(null) }

    // Function to crop the image
    fun cropAndShowImage() {
        // Calculate the cropping rectangle dimensions in pixels
        val x = croppingRectPosition.x.toInt()
        val y = croppingRectPosition.y.toInt()
        val width = croppingRectSize.width.toInt()
        val height = croppingRectSize.height.toInt()
        
        val croppedImageBitmap = CroppingManager().cropImage(imagePath, x, y, width, height)
        croppedImage = croppedImageBitmap
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        KamelImage(
            resource = asyncPainterResource(data = File(imagePath)),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        // Overlay cropping rectangles
        Box(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectTransformGestures { _, pan, zoom, _ ->
                        // Handle zooming gestures
                        // Update cropping rectangle size accordingly
                        croppingRectSize *= zoom
                    }
                }
        ) {
            // Draw cropping rectangles
            Box(
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
            )
        }
    }
}

