package com.awesome.cropper

import androidx.compose.ui.graphics.ImageBitmap
import io.kamel.core.utils.File
import platform.UIKit.UIImage
import platform.UIKit.imageWithCGImage
import platform.CoreGraphics.CGRect
import platform.CoreGraphics.CGRectMake
import platform.CoreGraphics.CGSize
import platform.CoreGraphics.CGSizeMake
import platform.CoreGraphics.CGImage
import platform.Foundation.NSData
import platform.Foundation.dataWithBytesNoCopy
import platform.Foundation.length
import platform.Foundation.NSDataReadingMappedAlways
import platform.Foundation.NSDataWritingWithoutOverwriting

actual class CroppingManager actual constructor() {
    actual fun cropImageByImageBitmap(
        image: ImageBitmap, x: Int, y: Int, width: Int, height: Int
    ): ImageBitmap {
        try {
            val nsImage = image.nsImage.freeze() // Convert ImageBitmap to NSImage
            val cgImage = nsImage.CGImage

            // Create a CGRect representing the crop area
            val cropRect = CGRect(
                origin = CGPointMake(x.toDouble(), y.toDouble()),
                size = CGSizeMake(width.toDouble(), height.toDouble())
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