package com.awesome.cropper

import androidx.compose.ui.graphics.ImageBitmap

expect class CroppingManager() {
    fun cropImage(filePath: String, x: Int, y: Int, width: Int, height: Int): ImageBitmap
}