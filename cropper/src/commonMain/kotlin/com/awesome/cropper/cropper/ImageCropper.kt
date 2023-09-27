package com.awesome.cropper.cropper

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.unit.dp
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import com.awesome.cropper.CroppingManager
import com.awesome.cropper.getImageByFilePath
import com.awesome.cropper.utils.CroppingUtils.cropAndShowImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart


@Composable
fun ImageCropper(
    imagePath: String,
    onCropStart: () -> Unit,
    onCropSuccess: (ImageBitmap) -> Unit,
    croppingShapeStrokeWidth: Float = 2f,
    croppingShapeStrokeColor: Color = Color.White,
    gridStrokeWidth: Float = 1f,
    gridStrokeColor: Color = Color.White,
    showGridLines: Boolean = true,
    backGroundAlpha:Float = 0.7f,
    crop: Boolean = false,
    modifier: Modifier = Modifier,
) {
    val image = getImageByFilePath(imagePath)
    var croppingRectSize by remember { mutableStateOf(Size(1f, 1f)) }
    var croppingRectPosition by remember { mutableStateOf(Offset(0f, 0f)) }
    var windowSize by remember { mutableStateOf(Size(0f, 0f)) }
    val density = LocalDensity.current

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (image != null) {
            Image(
                bitmap = image,
                contentDescription = null,
                modifier = Modifier.fillMaxSize().aspectRatio(image.width / image.height.toFloat()),
                contentScale = ContentScale.Fit
            )
            if (crop) {
                Crop(
                    image,
                    croppingRectSize,
                    windowSize,
                    croppingRectPosition,
                    density,
                    crop, onCropStart, onCropSuccess
                )
            }
            CroppingShape(
                croppingShapeStrokeWidth = croppingShapeStrokeWidth,
                croppingShapeStrokeColor = croppingShapeStrokeColor,
                gridStrokeWidth = gridStrokeWidth,
                gridStrokeColor = gridStrokeColor,
                showGridLines = showGridLines,
                backGroundAlpha = backGroundAlpha,
                aspectRatio = (image.width.toFloat() / image.height.toFloat())
            ) { size, offset, window ->
                croppingRectSize = size
                croppingRectPosition = offset
                density.run {
                    windowSize = Size(
                        window.width.dp.roundToPx().toFloat(),
                        window.height.dp.roundToPx().toFloat()
                    )
                }
            }
        }
    }
}

@Composable
fun ImageCropper(
    image: ImageBitmap,
    onCropStart: () -> Unit,
    onCropSuccess: (ImageBitmap) -> Unit,
    croppingShapeStrokeWidth: Float = 2f,
    croppingShapeStrokeColor: Color = Color.White,
    gridStrokeWidth: Float = 1f,
    gridStrokeColor: Color = Color.White,
    showGridLines: Boolean = true,
    backGroundAlpha:Float = 0.7f,
    crop: Boolean = false,
    modifier: Modifier = Modifier
) {
    var croppingRectSize by remember { mutableStateOf(Size(1f, 1f)) }
    var croppingRectPosition by remember { mutableStateOf(Offset(0f, 0f)) }
    var windowSize by remember { mutableStateOf(Size(0f, 0f)) }
    val density = LocalDensity.current

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            bitmap = image,
            contentDescription = null,
            modifier = Modifier.fillMaxSize().aspectRatio(image.width / image.height.toFloat()),
            contentScale = ContentScale.Fit
        )
        if (crop) {
            Crop(
                image,
                croppingRectSize,
                windowSize,
                croppingRectPosition,
                density,
                crop, onCropStart, onCropSuccess
            )
        }
        CroppingShape(
            croppingShapeStrokeWidth = croppingShapeStrokeWidth,
            croppingShapeStrokeColor = croppingShapeStrokeColor,
            gridStrokeWidth = gridStrokeWidth,
            gridStrokeColor = gridStrokeColor,
            showGridLines = showGridLines,
            backGroundAlpha = backGroundAlpha,
            aspectRatio = (image.width.toFloat() / image.height.toFloat())
        ) { size, offset, window ->
            croppingRectSize = size
            croppingRectPosition = offset
            density.run {
                windowSize = Size(
                    window.width.dp.roundToPx().toFloat(),
                    window.height.dp.roundToPx().toFloat()
                )
            }
        }
    }
}

@Composable
private fun Crop(
    image: ImageBitmap,
    croppingShapeSize: Size,
    windowSize: Size,
    croppingShapePosition: Offset,
    density: Density,
    crop: Boolean,
    onCropStart: () -> Unit,
    onCropSuccess: (ImageBitmap) -> Unit,

    ) {
    LaunchedEffect(crop) {
        if (crop) {
            flow {
                val croppedImageBitmap = cropAndShowImage(
                    image,
                    croppingShapeSize,
                    croppingShapePosition,
                    windowSize, density
                )
                emit(croppedImageBitmap)
            }
                .flowOn(Dispatchers.Default)
                .onStart {
                    onCropStart()
                    delay(400)
                }
                .onEach {
                    onCropSuccess(it)
                }
                .launchIn(this)
        }
    }
}