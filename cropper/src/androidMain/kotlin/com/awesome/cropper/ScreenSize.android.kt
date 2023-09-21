package com.awesome.cropper

import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.platform.LocalView

@Composable
actual fun getScreenSize(): Size {
    val size = LocalView.current
    return Size(width = size.width.toFloat() , height = size.height.toFloat())
}