package com.whoanimal.data.camera

import com.whoanimal.core.domain.model.Observation
import com.whoanimal.core.domain.repository.ObservationRepository
import java.util.Collections

class InMemoryObservationRepository : ObservationRepository {

    private val observations = Collections.synchronizedList(mutableListOf<Observation>())

    override suspend fun saveObservation(observation: Observation): Result<Unit> {
        observations.add(0, observation)
        return Result.success(Unit)
    }

    override suspend fun getObservationById(id: String): Observation? {
        return observations.find { it.id == id }
    }

    override suspend fun getAllObservations(): List<Observation> {
        return observations.toList()
    }
}
