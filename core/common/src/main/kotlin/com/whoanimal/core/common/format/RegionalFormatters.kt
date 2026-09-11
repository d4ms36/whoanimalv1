package com.whoanimal.core.common.format

import java.text.DateFormat
import java.text.NumberFormat
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Date
import java.util.Locale

object RegionalFormatters {

    fun formatDateMedium(epochMillis: Long, locale: Locale = Locale.getDefault()): String {
        val date = Date(epochMillis)
        val df = DateFormat.getDateInstance(DateFormat.MEDIUM, locale)
        return df.format(date)
    }

    fun formatDateMedium(isoDateString: String, locale: Locale = Locale.getDefault()): String {
        return try {
            val instant = Instant.parse(isoDateString)
            val zonedDateTime = instant.atZone(ZoneId.systemDefault())
            val formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).withLocale(locale)
            formatter.format(zonedDateTime)
        } catch (_: Exception) {
            isoDateString
        }
    }

    fun formatTimeShort(epochMillis: Long, locale: Locale = Locale.getDefault()): String {
        val date = Date(epochMillis)
        val df = DateFormat.getTimeInstance(DateFormat.SHORT, locale)
        return df.format(date)
    }

    fun formatInteger(number: Long, locale: Locale = Locale.getDefault()): String {
        val nf = NumberFormat.getIntegerInstance(locale)
        return nf.format(number)
    }

    fun formatDecimal(number: Double, maxDecimals: Int = 2, locale: Locale = Locale.getDefault()): String {
        val nf = NumberFormat.getNumberInstance(locale).apply {
            maximumFractionDigits = maxDecimals
            minimumFractionDigits = 0
        }
        return nf.format(number)
    }

    fun formatCoordinates(latitude: Double?, longitude: Double?, locale: Locale = Locale.getDefault()): String {
        if (latitude == null || longitude == null) return "—"
        val latStr = formatDecimal(latitude, 2, locale)
        val lonStr = formatDecimal(longitude, 2, locale)
        val latDir = if (latitude >= 0) "N" else "S"
        val lonDir = if (longitude >= 0) "E" else "W"
        return "$latStr° $latDir, $lonStr° $lonDir"
    }
}
