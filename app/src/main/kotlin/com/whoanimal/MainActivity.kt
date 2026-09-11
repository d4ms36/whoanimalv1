package com.whoanimal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.BugReport
import androidx.compose.material.icons.rounded.Landscape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.whoanimal.core.common.format.RegionalFormatters
import com.whoanimal.core.designsystem.R
import com.whoanimal.core.designsystem.component.HeroDiscoverButton
import com.whoanimal.core.designsystem.component.JournalSnippet
import com.whoanimal.core.designsystem.theme.WhoAnimalPalette
import com.whoanimal.core.designsystem.theme.WhoAnimalTheme
import com.whoanimal.core.domain.model.Observation
import com.whoanimal.core.domain.usecase.CaptureObservationUseCase
import com.whoanimal.core.domain.usecase.RecordObservationUseCase
import com.whoanimal.data.camera.CameraXManager
import com.whoanimal.data.camera.CameraXPreviewView
import com.whoanimal.data.camera.InMemoryObservationRepository
import com.whoanimal.feature.capture.CaptureScreen
import com.whoanimal.feature.playground.DesignPlaygroundScreen

enum class AppDestination {
    BASE_CAMP,
    CAPTURE,
    PLAYGROUND
}

/**
 * MainActivity — Composition Root.
 *
 * Architectural responsibility:
 * - Instantiates concrete implementations from :data:camera.
 * - Wires domain use cases with their data implementations (poor man's DI).
 * - Provides CameraXPreviewView as a composable slot to CaptureScreen,
 *   keeping :feature:capture free of CameraX and :data:camera imports.
 *
 * Dependency graph wired here:
 *   CameraXManager (CameraCaptureService impl)
 *       └──► CaptureObservationUseCase
 *                └──► RecordObservationUseCase
 *                         └──► InMemoryObservationRepository (ObservationRepository impl)
 */
class MainActivity : ComponentActivity() {

    private val observationRepository by lazy { InMemoryObservationRepository() }
    private val recordObservationUseCase by lazy { RecordObservationUseCase(observationRepository) }

    // CameraXManager is the concrete CameraCaptureService implementation.
    // Lives in :data:camera; wired here at the composition root.
    private val cameraXManager by lazy { CameraXManager(this) }

    private val captureObservationUseCase by lazy {
        CaptureObservationUseCase(
            cameraCaptureService = cameraXManager,
            recordObservationUseCase = recordObservationUseCase
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WhoAnimalTheme {
                WhoAnimalApp(
                    captureObservationUseCase = captureObservationUseCase,
                    cameraXManager = cameraXManager
                )
            }
        }
    }
}

@Composable
fun WhoAnimalApp(
    captureObservationUseCase: CaptureObservationUseCase,
    cameraXManager: CameraXManager
) {
    var currentDestination by remember { mutableStateOf(AppDestination.BASE_CAMP) }
    var latestObservation by remember { mutableStateOf<Observation?>(null) }

    when (currentDestination) {
        AppDestination.PLAYGROUND -> {
            if (BuildConfig.DEBUG) {
                DesignPlaygroundScreen(
                    onNavigateBack = { currentDestination = AppDestination.BASE_CAMP }
                )
            } else {
                currentDestination = AppDestination.BASE_CAMP
            }
        }

        AppDestination.CAPTURE -> {
            CaptureScreen(
                captureObservationUseCase = captureObservationUseCase,
                // CameraXPreviewView is provided here as a composable slot.
                // feature:capture receives a generic @Composable lambda — it has
                // zero knowledge of CameraX or CameraXPreviewView internals.
                cameraPreviewSlot = { onReady, onError, slotModifier ->
                    CameraXPreviewView(
                        cameraXManager = cameraXManager,
                        modifier = slotModifier,
                        onReady = onReady,
                        onError = onError
                    )
                },
                onNavigateBack = { currentDestination = AppDestination.BASE_CAMP },
                onObservationCreated = { observation ->
                    latestObservation = observation
                }
            )
        }

        AppDestination.BASE_CAMP -> {
            BaseCampHomeScreen(
                latestObservation = latestObservation,
                onNavigateToCapture = { currentDestination = AppDestination.CAPTURE },
                onOpenPlayground = {
                    if (BuildConfig.DEBUG) {
                        currentDestination = AppDestination.PLAYGROUND
                    }
                }
            )
        }
    }
}

@Composable
private fun BaseCampHomeScreen(
    latestObservation: Observation?,
    onNavigateToCapture: () -> Unit,
    onOpenPlayground: () -> Unit
) {
    val scrollState = rememberScrollState()
    val sampleTime = RegionalFormatters.formatTimeShort(System.currentTimeMillis())

    Scaffold(
        containerColor = WhoAnimalTheme.colors.backgroundApp
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(scrollState)
                .padding(horizontal = 20.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // ESTRATO TECHO: Atmósfera y Cabecera
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                Icon(
                    imageVector = Icons.Rounded.Landscape,
                    contentDescription = null,
                    tint = WhoAnimalTheme.colors.actionPrimaryFill,
                    modifier = Modifier.size(36.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(id = R.string.common_app_name),
                    style = WhoAnimalTheme.typography.displayLarge,
                    color = WhoAnimalTheme.colors.textPrimary
                )
                Text(
                    text = stringResource(id = R.string.common_app_tagline),
                    style = WhoAnimalTheme.typography.caption,
                    color = WhoAnimalTheme.colors.textSecondary
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = stringResource(id = R.string.home_atmosphere_day),
                    style = WhoAnimalTheme.typography.caption,
                    color = WhoAnimalTheme.colors.textTertiary
                )
            }

            // ESTRATO PECHO: Corazón de Descubrimiento (Hero CTA -> Viewfinder)
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp)
            ) {
                HeroDiscoverButton(
                    onClick = onNavigateToCapture
                )
            }

            // ESTRATO ABDOMEN: Bitácora Reciente
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.home_recent_sightings_title),
                    style = WhoAnimalTheme.typography.titleMedium,
                    color = WhoAnimalTheme.colors.textPrimary
                )

                if (latestObservation != null) {
                    JournalSnippet(
                        commonName = stringResource(id = R.string.capture_observation_success_title),
                        scientificName = "ID: ${latestObservation.id.take(8)}…",
                        timeOrDateText = sampleTime,
                        onClick = onNavigateToCapture
                    )
                } else {
                    JournalSnippet(
                        commonName = "Mirlo Común",
                        scientificName = "Turdus merula",
                        timeOrDateText = sampleTime,
                        onClick = onNavigateToCapture
                    )
                }
            }

            // ESTRATO PELVIS: Mochila del Explorador (Acceso y Contador)
            Card(
                colors = CardDefaults.cardColors(containerColor = WhoAnimalTheme.colors.surfaceCard),
                shape = WhoAnimalTheme.shapes.snippet,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.home_backpack_label),
                        style = WhoAnimalTheme.typography.titleMedium,
                        color = WhoAnimalTheme.colors.textPrimary
                    )
                    Text(
                        text = stringResource(id = R.string.collection_empty_description),
                        style = WhoAnimalTheme.typography.caption,
                        color = WhoAnimalTheme.colors.textSecondary
                    )
                }
            }

            // ESTRATO PIES: Modo Desarrollo — Acceso exclusivo al Design Playground
            if (BuildConfig.DEBUG) {
                Button(
                    onClick = onOpenPlayground,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = WhoAnimalPalette.Sage.copy(alpha = 0.35f),
                        contentColor = WhoAnimalTheme.colors.textPrimary
                    ),
                    shape = WhoAnimalTheme.shapes.pill,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.BugReport,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = stringResource(id = R.string.home_view_playground),
                        style = WhoAnimalTheme.typography.caption
                    )
                }
            }
        }
    }
}
