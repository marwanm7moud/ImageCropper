package com.awesome.cropper.cropper

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.unit.dp
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import com.awesome.cropper.CroppingManager
import com.awesome.cropper.getImageByFilePath
import com.awesome.cropper.utils.CroppingUtils.cropAndShowImage


@Composable
fun ImageCropper(
    imagePath: String,
) {
    val image = getImageByFilePath(imagePath)
    var croppingRectSize by remember { mutableStateOf(Size(1f, 1f)) }
    var croppingRectPosition by remember { mutableStateOf(Offset(0f, 0f)) }
    var windowSize by remember { mutableStateOf(Size(0f, 0f)) }
    val density = LocalDensity.current

    Box(
        modifier = Modifier.fillMaxSize()//.background(Color.Red),
        ,contentAlignment = Alignment.Center
    ) {
        if (image != null) {
            Image(
                bitmap = image,
                contentDescription = null,
                modifier = Modifier.fillMaxSize().aspectRatio(image.width / image.height.toFloat())
                ,contentScale = ContentScale.Fit
            )
            CroppingShape(
                aspectRatio = (image.width.toFloat() / image.height.toFloat())
            ) { size, offset, window ->
                croppingRectSize = size
                croppingRectPosition = offset
                density.run {
                    windowSize = Size(
                        window.width.dp.roundToPx().toFloat(),
                        window.height.dp.roundToPx().toFloat()
                    )
                }
            }
        }
    }

    var croppedImage by remember { mutableStateOf<ImageBitmap?>(null) }

    Button(
        onClick = {
            croppedImage = cropAndShowImage(
                image!!,
                croppingRectSize,
                croppingRectPosition, windowSize, density
            )
        },
        modifier = Modifier
            .padding(16.dp)
    ) {
        Text("Crop Image")
    }

    // Display the cropped image if available
    croppedImage?.let { cropped ->
        Image(
            bitmap = cropped,
            contentDescription = null,
            modifier = Modifier
                .size(200.dp, 200.dp) // Adjust the size as needed
        )
    }

}