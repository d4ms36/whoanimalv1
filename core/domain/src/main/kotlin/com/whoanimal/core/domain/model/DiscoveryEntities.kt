package com.whoanimal.core.domain.model

enum class LightCondition {
    DAWN,
    DAY,
    DUSK,
    NIGHT
}

data class EnvironmentalContext(
    val lightCondition: LightCondition,
    val weatherDescription: String = "",
    val ambientNotes: String = ""
)

data class Observation(
    val id: String,
    val imageReference: String,
    val timestampIso: String,
    val environmentalContext: EnvironmentalContext
)

enum class CertaintyLevel {
    HIGH,
    MODERATE,
    UNCERTAIN
}

data class IdentificationCandidate(
    val animalId: String,
    val commonName: String,
    val scientificName: String,
    val matchConfidence: Float
)

data class IdentificationResult(
    val observationId: String,
    val primaryCandidate: IdentificationCandidate,
    val certaintyLevel: CertaintyLevel,
    val alternatives: List<IdentificationCandidate> = emptyList(),
    val diagnostics: String = ""
)

enum class DecisionType {
    CONFIRMED_PRIMARY,
    SELECTED_ALTERNATIVE,
    MARKED_UNCATALOGUED,
    DISCARDED
}

data class IdentificationDecision(
    val observationId: String,
    val chosenAnimalId: String?,
    val decisionType: DecisionType,
    val decidedAtIso: String
)

data class ProtectedLocation(
    val regionName: String,
    val country: String,
    val approximateLatitude: Double? = null,
    val approximateLongitude: Double? = null,
    val isCoarseResolution: Boolean = true
)

data class Capture(
    val id: String,
    val animalId: String,
    val observationId: String,
    val fieldNotes: String,
    val locationContext: ProtectedLocation,
    val capturedAtIso: String
)

data class ScientificPillar(
    val animalId: String,
    val commonName: String,
    val scientificName: String,
    val taxonomy: Taxonomy,
    val iucnStatus: ConservationStatus,
    val biologyProfile: BiologyProfile
)

data class ExperiencePillar(
    val userPhotoUri: String,
    val timestampIso: String,
    val fieldNotes: String,
    val locationSummary: String
)

data class LorePillar(
    val narrativeTitle: String,
    val storyText: String,
    val fictionalNotice: String = "FICCION LUDICA / LUDIC FICTION"
)

data class Card(
    val id: String,
    val captureId: String,
    val cardProportion: String = "5:7",
    val scientificPillar: ScientificPillar,
    val experiencePillar: ExperiencePillar,
    val lorePillar: LorePillar,
    val isFavorite: Boolean = false
)

data class Collection(
    val cards: List<Card> = emptyList(),
    val lastUpdatedIso: String
) {
    val totalSpeciesDiscovered: Int
        get() = cards.map { it.scientificPillar.animalId }.distinct().size

    val totalCardsCount: Int
        get() = cards.size
}
