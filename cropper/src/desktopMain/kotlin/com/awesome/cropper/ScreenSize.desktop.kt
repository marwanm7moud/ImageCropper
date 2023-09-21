package com.awesome.cropper

import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Size
import java.awt.Toolkit

@Composable
actual fun getScreenSize(): Size {
    val size = Toolkit.getDefaultToolkit().screenSize
    return Size(width = size.width.toFloat() , height = size.height.toFloat())
}