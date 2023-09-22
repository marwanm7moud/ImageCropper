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
import com.awesome.cropper.getFileByPath
import com.awesome.cropper.getScreenSize
import io.kamel.core.getOrNull
import io.kamel.core.utils.File
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource


@Composable
fun ImageCropper(
    imagePath: String
) {
    val imageSize = 800.dp

    Box(
        modifier = Modifier.fillMaxSize().background(Color.Red)
    ) {
        KamelImage(
            resource = asyncPainterResource(data = getFileByPath(imagePath)),
            contentDescription = null,
            modifier = Modifier.size(imageSize),
            contentScale = ContentScale.Crop
        )
        CroppingRectangle(imageSize)
    }
}