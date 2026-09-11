package com.whoanimal.core.domain.service

import com.whoanimal.core.domain.model.IdentificationResult
import com.whoanimal.core.domain.model.Observation

/**
 * Architectural contract for future zoological identification engine (Phase 5 / WHO-V1-005).
 * Represents the boundary between empirical observation and machine inference.
 * Strictly decoupled: no fake AI, mock probabilities, or hardcoded species in production.
 */
interface IdentificationEngineService {
    suspend fun identify(observation: Observation): Result<IdentificationResult>
}
