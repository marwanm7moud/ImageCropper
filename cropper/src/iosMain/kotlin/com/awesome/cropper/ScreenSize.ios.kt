package com.awesome.cropper

import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Size
import platform.UIKit.UIScreen


@Composable
actual fun getScreenSize(): Size {
    val size = UIScreen.mainScreen.bounds.size
    return Size(width = size.width.toFloat() , height = size.height.toFloat())
}