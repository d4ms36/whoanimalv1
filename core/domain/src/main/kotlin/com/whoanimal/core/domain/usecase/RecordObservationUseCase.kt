package com.whoanimal.core.domain.usecase

import com.whoanimal.core.domain.model.EnvironmentalContext
import com.whoanimal.core.domain.model.Observation
import com.whoanimal.core.domain.repository.ObservationRepository
import java.time.Instant
import java.util.UUID

class RecordObservationUseCase(
    private val observationRepository: ObservationRepository
) {
    suspend operator fun invoke(
        imageReference: String,
        environmentalContext: EnvironmentalContext,
        id: String = UUID.randomUUID().toString(),
        timestampIso: String = Instant.now().toString()
    ): Result<Observation> {
        if (imageReference.isBlank()) {
            return Result.failure(IllegalArgumentException("Image reference cannot be blank"))
        }

        val observation = Observation(
            id = id,
            imageReference = imageReference,
            timestampIso = timestampIso,
            environmentalContext = environmentalContext
        )

        return observationRepository.saveObservation(observation).map { observation }
    }
}
