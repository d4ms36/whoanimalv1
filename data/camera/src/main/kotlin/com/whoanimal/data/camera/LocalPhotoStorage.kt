package com.whoanimal.data.camera

import android.content.Context
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class LocalPhotoStorage(private val context: Context) {

    private val observationsDir: File by lazy {
        File(context.filesDir, "observations").apply {
            if (!exists()) {
                mkdirs()
            }
        }
    }

    fun createObservationPhotoFile(): File {
        val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss_SSS", Locale.US).format(Date())
        return File(observationsDir, "OBS_$timestamp.jpg")
    }

    fun getPhotoFileReference(file: File): String {
        return file.absolutePath
    }
}
