package com.awesome.cropper

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import java.awt.image.BufferedImage
import java.io.File
import java.io.IOException
import javax.imageio.ImageIO

@Composable
actual fun getImageByFilePath(filePath: String): ImageBitmap? {
    try {
        val file = File(filePath)
        if (file.exists()) {
            val bufferedImage: BufferedImage = ImageIO.read(file)
            return bufferedImage.toComposeImageBitmap()
        }
    } catch (e: IOException) {
        e.printStackTrace()
    }
    return null
}