package com.awesome.cropper

import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.ImageBitmap

@Composable
expect fun getImageByFilePath(filePath:String): ImageBitmap?