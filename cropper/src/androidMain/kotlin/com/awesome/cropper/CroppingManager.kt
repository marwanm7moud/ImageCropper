package com.awesome.cropper

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.asImageBitmap
import java.io.ByteArrayOutputStream

actual class CroppingManager actual constructor() {
    actual fun cropImageByImageBitmap(
        image: ImageBitmap,
        cropPosition: Offset,
        cropSize: Size,
        windowSize: Size,
    ): ImageBitmap {
        val bitmap = image.asAndroidBitmap()
        val croppedBitmap = Bitmap.createBitmap(
            bitmap,
            cropPosition.x.toInt(),
            cropPosition.y.toInt(),
            cropSize.width.toInt(),
            cropSize.height.toInt()
        )
        val outputStream = ByteArrayOutputStream()
        croppedBitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        return BitmapFactory.decodeByteArray(
            outputStream.toByteArray(),
            0,
            outputStream.toByteArray().size
        ).asImageBitmap()
    }
}