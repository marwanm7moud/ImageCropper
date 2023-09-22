package com.awesome.cropper

import androidx.compose.ui.graphics.ImageBitmap
import io.kamel.core.utils.File

expect class CroppingManager() {
    fun cropImageByFile(file: File, x: Int, y: Int, width: Int, height: Int): ImageBitmap
}