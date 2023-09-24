package com.awesome.cropper

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.ImageBitmap
import platform.UIKit.UIImage
import platform.UIKit.imageWithCGImage
import platform.CoreGraphics.CGRect
import platform.CoreGraphics.CGSizeMake
import platform.CoreGraphics.CGImage

actual class CroppingManager actual constructor() {
    actual fun cropImageByImageBitmap(
        image: ImageBitmap,
        cropPosition:Offset,
        cropSize: Size,
        windowSize:Size,
    ): ImageBitmap {
        try {
            val nsImage = image.nsImage.freeze() // Convert ImageBitmap to NSImage
            val cgImage = nsImage.CGImage

            val heightRatio =
                (image.height / windowSize.height)
            val widthRatio = (image.width / windowSize.width)

            // Create a CGRect representing the crop area
            val cropRect = CGRect(
                origin = CGPointMake((cropPosition.x * widthRatio).toInt(), (cropPosition.y * heightRatio).toInt()),
                size = CGSizeMake((cropSize.width * widthRatio).toInt(),(cropSize.height * heightRatio).toInt())
            )

            // Crop the image using CGImageCreateWithImageInRect
            val croppedCGImage = cgImage?.let { CGImageCreateWithImageInRect(it, cropRect) }

            // Create a new UIImage from the cropped CGImage
            val croppedUIImage = croppedCGImage?.let { UIImage.imageWithCGImage(it) }

            // Convert UIImage back to ImageBitmap
            return croppedUIImage?.toImageBitmap() ?: ImageBitmap(0, 0)
        } catch (e: Exception) {
            e.printStackTrace()
            return ImageBitmap(0, 0)
        }
    }
}