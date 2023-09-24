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
    imagePath: String
) {
    val image = getImageByFilePath(imagePath)
    var croppingRectSize by remember { mutableStateOf(Size(200f, 200f)) }
    var croppingRectPosition by remember { mutableStateOf(Offset(0f, 0f)) }
    var windowSize by remember { mutableStateOf(Size(0f, 0f)) }
    val density = LocalDensity.current

    Box(
        modifier = Modifier.fillMaxSize().background(Color.Red),
        contentAlignment = Alignment.Center
    ) {
        if (image != null) {
            Image(
                bitmap = image,
                contentDescription = null,
                modifier = Modifier.fillMaxSize().aspectRatio(image.width / image.height.toFloat()),
                contentScale = ContentScale.Fit
            )
            CroppingRectangle(
                aspectRatio = (image.width / image.height.toFloat())
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

}