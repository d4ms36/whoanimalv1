package com.whoanimal.core.domain.repository

import com.whoanimal.core.domain.model.Observation

interface ObservationRepository {
    suspend fun saveObservation(observation: Observation): Result<Unit>
    suspend fun getObservationById(id: String): Observation?
    suspend fun getAllObservations(): List<Observation>
}
