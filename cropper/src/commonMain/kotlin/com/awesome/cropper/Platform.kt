package com.awesome.cropper

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform