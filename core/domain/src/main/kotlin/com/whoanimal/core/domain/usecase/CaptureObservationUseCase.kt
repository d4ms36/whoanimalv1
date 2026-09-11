package com.whoanimal.core.domain.usecase

import com.whoanimal.core.domain.model.EnvironmentalContext
import com.whoanimal.core.domain.model.Observation
import com.whoanimal.core.domain.service.CameraCaptureService

/**
 * Coordinates capturing a photograph through the decoupled CameraCaptureService
 * and registering an empirical Observation in the local repository.
 */
class CaptureObservationUseCase(
    private val cameraCaptureService: CameraCaptureService,
    private val recordObservationUseCase: RecordObservationUseCase
) {
    suspend operator fun invoke(environmentalContext: EnvironmentalContext): Result<Observation> {
        val captureResult = cameraCaptureService.capturePhoto()
        return captureResult.fold(
            onSuccess = { imageReference ->
                recordObservationUseCase(
                    imageReference = imageReference,
                    environmentalContext = environmentalContext
                )
            },
            onFailure = { error ->
                Result.failure(error)
            }
        )
    }
}
