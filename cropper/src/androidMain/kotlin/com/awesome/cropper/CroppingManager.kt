package com.awesome.cropper

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException

actual class CroppingManager actual constructor() {
    actual fun cropImageByFile(file: File, x: Int, y: Int, width: Int, height: Int): ImageBitmap{
        val options = BitmapFactory.Options()
        options.inPreferredConfig = Bitmap.Config.ARGB_8888
        val bitmap = BitmapFactory.decodeFile(file.absolutePath, options)
        val croppedBitmap = Bitmap.createBitmap(bitmap, x, y, width, height)
        val outputStream = ByteArrayOutputStream()
        croppedBitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        return BitmapFactory.decodeByteArray(outputStream.toByteArray(), 0, outputStream.toByteArray().size).asImageBitmap()
    }
}