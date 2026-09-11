package com.whoanimal.core.common

import com.whoanimal.core.common.format.RegionalFormatters
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.util.Locale

class RegionalFormattersTest {

    @Test
    fun `test format coordinates formatting`() {
        val formatted = RegionalFormatters.formatCoordinates(40.4168, -3.7038, Locale.US)
        assertTrue(formatted.contains("40.42° N"))
        assertTrue(formatted.contains("3.7° W") || formatted.contains("3.70° W"))
    }

    @Test
    fun `test format integer formatting in US and Spanish locales`() {
        val usFormatted = RegionalFormatters.formatInteger(1500L, Locale.US)
        assertEquals("1,500", usFormatted)

        val esFormatted = RegionalFormatters.formatInteger(1500L, Locale.forLanguageTag("es-ES"))
        // Spanish thousands separator is non-breaking space or dot depending on JDK version
        assertTrue(esFormatted.contains("1") && esFormatted.contains("500"))
    }
}
