package com.whoanimal.core.domain.service

/**
 * Pure Kotlin contract for camera hardware capture operations.
 * Declared in Domain; implemented in :data:camera.
 * Zero dependencies on Android, CameraX, Bitmap, ImageProxy, or Uri.
 */
interface CameraCaptureService {
    suspend fun capturePhoto(): Result<String>
}
