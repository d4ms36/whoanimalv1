package com.whoanimal.feature.capture

import com.whoanimal.core.domain.model.Observation

sealed interface CaptureUiState {
    data object CheckingPermission : CaptureUiState
    data object PermissionRequired : CaptureUiState
    data object PermissionDenied : CaptureUiState
    data object CameraInitializing : CaptureUiState
    data object Ready : CaptureUiState
    data object Capturing : CaptureUiState
    data class ObservationCreated(val observation: Observation) : CaptureUiState
    data class CameraError(val errorMessage: String) : CaptureUiState
}
