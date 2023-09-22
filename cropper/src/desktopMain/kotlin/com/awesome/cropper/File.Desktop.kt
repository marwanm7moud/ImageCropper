package com.awesome.cropper

import androidx.compose.runtime.Composable
import io.kamel.core.utils.File

@Composable
actual fun getFileByPath(filePath: String): File {
    return File(filePath)
}