package com.awesome.cropper

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.ImageBitmap
import platform.UIKit.UIImage
import platform.UIKit.UIImagePNGRepresentation
import platform.Foundation.NSData
import platform.Foundation.NSFileManager
import platform.Foundation.NSURL
import platform.Foundation.dataWithContentsOfFile
import platform.Foundation.writeToURL
import platform.Foundation.URL
import platform.Foundation.data
import platform.Foundation.NSError
@Composable
actual fun getImageByFilePath(filePath:String): ImageBitmap? {
    val fileManager = NSFileManager.defaultManager
    val fileUrl = NSURL.fileURLWithPath(filePath)

    // Check if the file exists
    if (fileManager.fileExistsAtPath(fileUrl.path)) {
        try {
            val imageData = NSData.dataWithContentsOfFile(fileUrl)
            val uiImage = UIImage.imageWithData(imageData)

            // Convert UIImage to ImageBitmap
            val imageBitmap = ImageBitmap(uiImage)

            return imageBitmap
        } catch (e: NSError) {
            // Handle error if loading the image fails
            println("Error loading image: ${e.localizedDescription}")
        }
    }

    return null}