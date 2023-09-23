package com.awesome.cropper

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.ImageBitmap

expect class CroppingManager() {
    fun cropImageByImageBitmap(
        image: ImageBitmap,
        cropPosition:Offset,
        cropSize: Size,
        windowSize:Size,
    ): ImageBitmap
}