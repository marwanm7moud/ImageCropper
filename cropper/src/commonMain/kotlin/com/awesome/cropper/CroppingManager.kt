package com.awesome.cropper

import androidx.compose.ui.graphics.ImageBitmap

expect class CroppingManager() {
    fun cropImageByImageBitmap(image: ImageBitmap, x: Int, y: Int, width: Int, height: Int): ImageBitmap
}