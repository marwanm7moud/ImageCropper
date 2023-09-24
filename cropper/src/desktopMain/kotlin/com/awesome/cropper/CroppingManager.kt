package com.awesome.cropper

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toAwtImage
import androidx.compose.ui.graphics.toComposeImageBitmap
import org.jetbrains.skiko.toImage
import java.awt.Image
import java.io.IOException

actual class CroppingManager actual constructor() {
    actual fun cropImageByImageBitmap(
        image: ImageBitmap,
        cropPosition: Offset,
        cropSize: Size,
        windowSize: Size,
    ): ImageBitmap {
        try {
            // Convert the ImageBitmap to a BufferedImage
            val originalImage = image.toAwtImage()
            // Crop the BufferedImage
            val croppedImage = originalImage.getSubimage(
                cropPosition.x.toInt(),
                cropPosition.y.toInt(),
                cropSize.width.toInt(),
                cropSize.height.toInt()
            )

            // Convert the cropped BufferedImage back to an ImageBitmap
            return croppedImage.toComposeImageBitmap()
        } catch (e: IOException) {
            e.printStackTrace()
            return ImageBitmap(0, 0)
        }
    }
}