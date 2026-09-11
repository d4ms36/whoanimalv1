package com.whoanimal.core.domain

import com.whoanimal.core.domain.model.Animal
import com.whoanimal.core.domain.model.BiologyProfile
import com.whoanimal.core.domain.model.Card
import com.whoanimal.core.domain.model.CautionProfile
import com.whoanimal.core.domain.model.Collection
import com.whoanimal.core.domain.model.ConservationStatus
import com.whoanimal.core.domain.model.ExperiencePillar
import com.whoanimal.core.domain.model.LorePillar
import com.whoanimal.core.domain.model.ScientificPillar
import com.whoanimal.core.domain.model.Taxonomy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class DomainModelTest {

    @Test
    fun `test conservation status vulnerability check`() {
        assertFalse(ConservationStatus.LEAST_CONCERN.isVulnerableOrHigher)
        assertFalse(ConservationStatus.NEAR_THREATENED.isVulnerableOrHigher)
        assertTrue(ConservationStatus.VULNERABLE.isVulnerableOrHigher)
        assertTrue(ConservationStatus.ENDANGERED.isVulnerableOrHigher)
        assertTrue(ConservationStatus.CRITICALLY_ENDANGERED.isVulnerableOrHigher)
    }

    @Test
    fun `test animal domain model creation`() {
        val animal = Animal(
            id = "animal-01",
            commonName = "Lince Ibérico",
            scientificName = "Lynx pardinus",
            taxonomy = Taxonomy(
                clazz = "Mammalia",
                order = "Carnivora",
                family = "Felidae",
                genus = "Lynx"
            ),
            iucnStatus = ConservationStatus.ENDANGERED,
            biologyProfile = BiologyProfile(
                diet = "Conejo de monte",
                habitat = "Monte mediterráneo",
                behavior = "Solitario y crepuscular"
            ),
            cautionProfile = CautionProfile(
                hasNaturalDefenses = true,
                safeDistanceMeters = 25,
                adviceNotes = "No interferir en su corredor de caza."
            )
        )

        assertEquals("Lince Ibérico", animal.commonName)
        assertEquals("Lynx pardinus", animal.scientificName)
        assertTrue(animal.iucnStatus.isVulnerableOrHigher)
    }

    @Test
    fun `test collection statistics without competitive metrics`() {
        val card1 = createTestCard("card-01", "animal-01")
        val card2 = createTestCard("card-02", "animal-01") // Multiple captures of same species
        val card3 = createTestCard("card-03", "animal-02")

        val collection = Collection(
            cards = listOf(card1, card2, card3),
            lastUpdatedIso = "2026-09-11T08:00:00Z"
        )

        assertEquals(3, collection.totalCardsCount)
        assertEquals(2, collection.totalSpeciesDiscovered)
    }

    private fun createTestCard(cardId: String, animalId: String): Card {
        return Card(
            id = cardId,
            captureId = "capture-$cardId",
            scientificPillar = ScientificPillar(
                animalId = animalId,
                commonName = "Common Name",
                scientificName = "Scientific name",
                taxonomy = Taxonomy(clazz = "Aves", order = "Passeriformes", family = "Turdidae", genus = "Turdus"),
                iucnStatus = ConservationStatus.LEAST_CONCERN,
                biologyProfile = BiologyProfile("Diet", "Habitat", "Behavior")
            ),
            experiencePillar = ExperiencePillar(
                userPhotoUri = "file:///photos/sample.jpg",
                timestampIso = "2026-09-11T08:00:00Z",
                fieldNotes = "Notes",
                locationSummary = "Location"
            ),
            lorePillar = LorePillar(
                narrativeTitle = "Lore Title",
                storyText = "Lore story"
            )
        )
    }
}
