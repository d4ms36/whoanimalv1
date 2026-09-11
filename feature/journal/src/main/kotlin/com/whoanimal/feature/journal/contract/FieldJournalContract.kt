package com.whoanimal.feature.journal.contract

import com.whoanimal.core.domain.model.EnvironmentalContext
import com.whoanimal.core.domain.model.LightCondition

/**
 * Architectural contract for future Field Journal Edition screen (Fase 4 / Fase 5).
 * Defines State, Intents, and Actions without premature UI implementation.
 */
data class FieldJournalUiState(
    val observationId: String = "",
    val animalCommonName: String = "",
    val animalScientificName: String = "",
    val fieldNotesDraft: String = "",
    val lightCondition: LightCondition = LightCondition.DAY,
    val weatherDescription: String = "",
    val hasCautionWarning: Boolean = false,
    val cautionAdvice: String = "",
    val isSaving: Boolean = false,
    val isSavedSuccess: Boolean = false,
    val errorMessage: String? = null
)

sealed interface FieldJournalIntent {
    data class UpdateNotes(val notes: String) : FieldJournalIntent
    data class UpdateWeather(val weather: String) : FieldJournalIntent
    data class ChangeLightCondition(val condition: LightCondition) : FieldJournalIntent
    data object SaveEntry : FieldJournalIntent
    data object DismissError : FieldJournalIntent
    data object Cancel : FieldJournalIntent
}

sealed interface FieldJournalNavigationEvent {
    data class EntrySaved(val captureId: String) : FieldJournalNavigationEvent
    data object NavigateBack : FieldJournalNavigationEvent
}
