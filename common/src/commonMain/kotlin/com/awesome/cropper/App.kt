package com.awesome.cropper

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.unit.dp
import com.awesome.cropper.cropper.ImageCropper


@Composable
fun App() {
    var crop by remember { mutableStateOf(false) }
    var isCropping by remember { mutableStateOf(false) }
    var croppedImage by remember { mutableStateOf<ImageBitmap?>(null) }
    var filePath = "C:\\Users\\hp\\Desktop\\4t0oBFrJyweYPt0hocW6RUa0b6H.jpg" //add here your file path
    Box(
        modifier = Modifier.fillMaxSize().background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        ImageCropper(
            modifier = Modifier.fillMaxSize(0.7f),
            imagePath = filePath,
            onCropStart = {
                isCropping = true
            },
            onCropSuccess = {
                croppedImage = it
                isCropping = false
                crop = false
            },
            crop = crop
        )
        Button(
            onClick = {
                crop = true
            },
            modifier = Modifier
                .padding(16.dp).align(Alignment.TopEnd)
        ) {
            Text("Crop Image")
        }

        // Display the cropped image if available
        croppedImage?.let { cropped ->
            Image(
                bitmap = cropped,
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp, 200.dp).align(Alignment.TopStart) // Adjust the size as needed
            )
        }

    }
}