package com.whoanimal.data.camera

import androidx.camera.view.PreviewView
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.LocalLifecycleOwner
import kotlinx.coroutines.launch

/**
 * Encapsulates the CameraX PreviewView AndroidView completely within :data:camera.
 * Presentation layers receive this composable as a pluggable slot without importing CameraX.
 */
@Composable
fun CameraXPreviewView(
    cameraXManager: CameraXManager,
    modifier: Modifier = Modifier,
    onReady: () -> Unit = {},
    onError: (String) -> Unit = {}
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val coroutineScope = rememberCoroutineScope()

    DisposableEffect(Unit) {
        onDispose {
            cameraXManager.stopCamera()
        }
    }

    AndroidView(
        factory = { context ->
            PreviewView(context).apply {
                scaleType = PreviewView.ScaleType.FILL_CENTER
            }
        },
        update = { previewView ->
            coroutineScope.launch {
                val startResult = cameraXManager.startCamera(
                    lifecycleOwner = lifecycleOwner,
                    previewView = previewView
                )
                startResult.fold(
                    onSuccess = { onReady() },
                    onFailure = { error -> onError(error.localizedMessage ?: "Camera initialization failed") }
                )
            }
        },
        modifier = modifier
    )
}
