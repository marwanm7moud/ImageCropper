package com.awesome.cropper

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import java.io.File
import java.io.FileInputStream
import java.io.IOException

@Composable
actual fun getImageByFilePath(filePath:String): ImageBitmap? {
    try {
        val file = File(filePath)
        if (file.exists()) {
            val options = BitmapFactory.Options()
            options.inPreferredConfig = Bitmap.Config.ARGB_8888
            val bitmap = BitmapFactory.decodeFile(file.absolutePath, options)
            return bitmap.asImageBitmap()
        }
    } catch (e: IOException) {
        Log.e("ImageBitmap", "Error loading image from file: ${e.message}")
    }
    return null
}