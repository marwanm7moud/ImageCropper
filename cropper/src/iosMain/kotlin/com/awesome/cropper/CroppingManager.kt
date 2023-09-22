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
    actual fun cropImageByFile(file: File, x: Int, y: Int, width: Int, height: Int): ImageBitmap{
        //todo NEED TO EDIT

        val uiImage = UIImage.imageWithContentsOfFile(file)
        val cgImage = uiImage.CGImage

        val rect = CGRectMake(x.toDouble(), y.toDouble(), width.toDouble(), height.toDouble())
        val croppedCgImage = cgImage?.cropping(toRect = rect)

        val croppedUiImage = UIImage.imageWithCGImage(croppedCgImage, scale = uiImage.scale, orientation = uiImage.imageOrientation)

        val data = croppedUiImage.pngData()
        val length = data.length.toLong()
        val byteArray = data.toByteArray()

        return byteArray
    }
}