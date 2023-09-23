package com.awesome.cropper

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toAwtImage
import androidx.compose.ui.graphics.toComposeImageBitmap
import java.io.IOException
import javax.imageio.ImageIO

actual class CroppingManager actual constructor() {
    actual fun cropImageByImageBitmap(
        image: ImageBitmap,
        x: Int,
        y: Int,
        width: Int,
        height: Int
    ): ImageBitmap {
        try {
            // Convert the ImageBitmap to a BufferedImage
            val originalImage = image.toAwtImage()

            // Crop the BufferedImage
            val croppedImage = originalImage.getSubimage(x, y, width, height)

            // Convert the cropped BufferedImage back to an ImageBitmap
            return croppedImage.toComposeImageBitmap()
        } catch (e: IOException) {
            e.printStackTrace()
            return ImageBitmap(0, 0)
        }
    }
}