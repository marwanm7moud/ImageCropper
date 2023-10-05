# Kotlin Multiplatform Image Cropper (Beta)

[![API](https://img.shields.io/badge/API-21%2B-green.svg?style=flat)](https://android-arsenal.com/api?level=21)
[![linkedin](https://img.shields.io/badge/linkedin-blue.svg?style=flat)](https://www.linkedin.com/in/marwanm7moud/)
![](https://img.shields.io/github/actions/workflow/status/patrykandpatrick/vico/build-debug-apk.yml?branch=master)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

Kotlin Multiplatform Image Cropper is a robust, easy-to-use library designed for image cropping across Android, iOS, and other platforms using the Kotlin Multiplatform Mobile (KMM) technology.

## Features
* Crop images on Android and Desktop with a single codebase.
* Easy-to-use UI.
* Supported platforms: Android, Desktop.

## How to use?

Add dependency in your module `build.gradle`

```kotlin
dependencies {
    implementation("io.github.marwanm7moud:cropper:$latest_release")
}
```

## Examples

1. Use Image Cropper with string File Path direct

```kotlin
@Composable
fun ImageCropperSample(){
    var crop by remember { mutableStateOf(false) }
    var isCropping by remember { mutableStateOf(false) }
    var croppedImage by remember { mutableStateOf<ImageBitmap?>(null) }
    var filePath = "" //add here your file path
    Box(
        modifier = Modifier.fillMaxSize().background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        ImageCropper(
            modifier = Modifier.fillMaxSize(0.7f),
            imagePath = filePath,
            onCropStart = {
                isCropping = true
            },
            onCropSuccess = {
                croppedImage = it
                isCropping = false
                crop = false
            },
            crop = crop
        )
        Button(
            onClick = {
                crop = true
            },
            modifier = Modifier
                .padding(16.dp).align(Alignment.TopEnd)
        ) {
            Text("Crop Image")
        }

        // Display the cropped image if available
        croppedImage?.let { cropped ->
            Image(
                bitmap = cropped,
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp, 200.dp).align(Alignment.TopStart) // Adjust the size as needed
            )
        }
    }
}
```

2. You can use Image Bitmap direct
``` kotlin
@Composable
fun ImageCropperSample(image:ImageBitmap){
    var crop by remember { mutableStateOf(false) }
    var isCropping by remember { mutableStateOf(false) }
    var croppedImage by remember { mutableStateOf<ImageBitmap?>(null) }
    Box(
        modifier = Modifier.fillMaxSize().background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        ImageCropper(
            modifier = Modifier.fillMaxSize(0.7f),
            image = image,
            onCropStart = {
                isCropping = true
            },
            onCropSuccess = {
                croppedImage = it
                isCropping = false
                crop = false
            },
            crop = crop
        )
        Button(
            onClick = {
                crop = true
            },
            modifier = Modifier
                .padding(16.dp).align(Alignment.TopEnd)
        ) {
            Text("Crop Image")
        }

        // Display the cropped image if available
        croppedImage?.let { cropped ->
            Image(
                bitmap = cropped,
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp, 200.dp).align(Alignment.TopStart) // Adjust the size as needed
            )
        }
    }
}
```
![crop](/assets/imageCropper.gif)
