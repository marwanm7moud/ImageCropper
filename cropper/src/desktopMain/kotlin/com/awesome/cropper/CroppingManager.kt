package com.awesome.cropper

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import java.awt.image.BufferedImage
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import javax.imageio.ImageIO

actual class CroppingManager actual constructor() {
    actual fun cropImage(filePath: String, x: Int, y: Int, width: Int, height: Int): ImageBitmap {
        try {
            val originalImage = ImageIO.read(File(filePath))
            val croppedImage = originalImage.getSubimage(x, y, width, height)
            return croppedImage.toComposeImageBitmap()
        } catch (e: IOException) {
            e.printStackTrace()
            return ImageBitmap(0 , 0)
        }
    }
}