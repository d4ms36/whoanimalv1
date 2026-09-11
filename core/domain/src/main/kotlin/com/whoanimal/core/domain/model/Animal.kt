package com.whoanimal.core.domain.model

enum class ConservationStatus(val code: String, val displayName: String) {
    LEAST_CONCERN("LC", "Least Concern / Preocupación Menor"),
    NEAR_THREATENED("NT", "Near Threatened / Casi Amenazado"),
    VULNERABLE("VU", "Vulnerable"),
    ENDANGERED("EN", "Endangered / En Peligro"),
    CRITICALLY_ENDANGERED("CR", "Critically Endangered / En Peligro Crítico"),
    EXTINCT_IN_THE_WILD("EW", "Extinct in the Wild / Extinto en Estado Silvestre"),
    EXTINCT("EX", "Extinct / Extinto");

    val isVulnerableOrHigher: Boolean
        get() = this in listOf(VULNERABLE, ENDANGERED, CRITICALLY_ENDANGERED, EXTINCT_IN_THE_WILD, EXTINCT)
}

data class Taxonomy(
    val kingdom: String = "Animalia",
    val phylum: String = "Chordata",
    val clazz: String,
    val order: String,
    val family: String,
    val genus: String
)

data class BiologyProfile(
    val diet: String,
    val habitat: String,
    val behavior: String
)

data class CautionProfile(
    val hasNaturalDefenses: Boolean,
    val safeDistanceMeters: Int,
    val adviceNotes: String
)

data class Animal(
    val id: String,
    val commonName: String,
    val scientificName: String,
    val taxonomy: Taxonomy,
    val iucnStatus: ConservationStatus,
    val biologyProfile: BiologyProfile,
    val cautionProfile: CautionProfile
)
