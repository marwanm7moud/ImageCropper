package com.awesome.cropper

import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Size
import io.kamel.core.utils.File

@Composable
expect fun getFileByPath(filePath:String): File