package com.whoanimal.core.domain.usecase

import com.whoanimal.core.domain.model.EnvironmentalContext
import com.whoanimal.core.domain.model.LightCondition
import com.whoanimal.core.domain.model.Observation
import com.whoanimal.core.domain.repository.ObservationRepository
import com.whoanimal.core.domain.service.CameraCaptureService
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class CaptureObservationUseCaseTest {

    private class FakeObservationRepository : ObservationRepository {
        val list = mutableListOf<Observation>()
        override suspend fun saveObservation(observation: Observation): Result<Unit> {
            list.add(observation)
            return Result.success(Unit)
        }
        override suspend fun getObservationById(id: String): Observation? = list.find { it.id == id }
        override suspend fun getAllObservations(): List<Observation> = list
    }

    private class FakeCameraCaptureService(
        private val resultToReturn: Result<String>
    ) : CameraCaptureService {
        override suspend fun capturePhoto(): Result<String> = resultToReturn
    }

    @Test
    fun `when camera capture succeeds then observation is registered`() = runBlocking {
        val repo = FakeObservationRepository()
        val recordUseCase = RecordObservationUseCase(repo)
        val cameraService = FakeCameraCaptureService(Result.success("/local/storage/obs_test.jpg"))

        val captureObservationUseCase = CaptureObservationUseCase(cameraService, recordUseCase)
        val env = EnvironmentalContext(lightCondition = LightCondition.DUSK)

        val result = captureObservationUseCase(env)

        assertTrue(result.isSuccess)
        val observation = result.getOrNull()
        assertNotNull(observation)
        assertEquals("/local/storage/obs_test.jpg", observation?.imageReference)
        assertEquals(LightCondition.DUSK, observation?.environmentalContext?.lightCondition)
        assertEquals(1, repo.list.size)
    }

    @Test
    fun `when camera capture fails then observation is not registered`() = runBlocking {
        val repo = FakeObservationRepository()
        val recordUseCase = RecordObservationUseCase(repo)
        val cameraService = FakeCameraCaptureService(Result.failure(RuntimeException("Hardware error")))

        val captureObservationUseCase = CaptureObservationUseCase(cameraService, recordUseCase)
        val env = EnvironmentalContext(lightCondition = LightCondition.DAY)

        val result = captureObservationUseCase(env)

        assertTrue(result.isFailure)
        assertEquals(0, repo.list.size)
    }
}
