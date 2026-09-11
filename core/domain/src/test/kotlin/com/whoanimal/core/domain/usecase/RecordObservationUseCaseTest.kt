package com.whoanimal.core.domain.usecase

import com.whoanimal.core.domain.model.EnvironmentalContext
import com.whoanimal.core.domain.model.LightCondition
import com.whoanimal.core.domain.model.Observation
import com.whoanimal.core.domain.repository.ObservationRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class RecordObservationUseCaseTest {

    private class FakeObservationRepository : ObservationRepository {
        val savedObservations = mutableListOf<Observation>()

        override suspend fun saveObservation(observation: Observation): Result<Unit> {
            savedObservations.add(observation)
            return Result.success(Unit)
        }

        override suspend fun getObservationById(id: String): Observation? {
            return savedObservations.find { it.id == id }
        }

        override suspend fun getAllObservations(): List<Observation> {
            return savedObservations.toList()
        }
    }

    @Test
    fun `when image reference is valid then observation is created and saved`() = runBlocking {
        val repo = FakeObservationRepository()
        val useCase = RecordObservationUseCase(repo)

        val env = EnvironmentalContext(
            lightCondition = LightCondition.DAY,
            weatherDescription = "Despejado",
            ambientNotes = "Sendero de pinar"
        )

        val result = useCase(
            imageReference = "file:///app_storage/obs_123.jpg",
            environmentalContext = env
        )

        assertTrue(result.isSuccess)
        val observation = result.getOrNull()
        assertNotNull(observation)
        assertEquals("file:///app_storage/obs_123.jpg", observation?.imageReference)
        assertEquals(LightCondition.DAY, observation?.environmentalContext?.lightCondition)
        assertEquals(1, repo.savedObservations.size)

        // Strict ontological check: Observation is Observation, not Card or Animal
        assertTrue(observation is Observation)
    }

    @Test
    fun `when image reference is blank then usecase returns failure`() = runBlocking {
        val repo = FakeObservationRepository()
        val useCase = RecordObservationUseCase(repo)

        val env = EnvironmentalContext(lightCondition = LightCondition.DAWN)
        val result = useCase(
            imageReference = "   ",
            environmentalContext = env
        )

        assertTrue(result.isFailure)
        assertEquals(0, repo.savedObservations.size)
    }
}
