package com.awesome.cropper

actual fun getPlatform(): Platform {
   return DesktopPlatform()
}

class DesktopPlatform : Platform {
    override val name: String = "Desktop"
}