package com.whoanimal.feature.capture

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Camera
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.ErrorOutline
import androidx.compose.material.icons.rounded.PhotoCamera
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.whoanimal.core.common.format.RegionalFormatters
import com.whoanimal.core.designsystem.R
import com.whoanimal.core.designsystem.component.FieldViewfinder
import com.whoanimal.core.designsystem.theme.WhoAnimalPalette
import com.whoanimal.core.designsystem.theme.WhoAnimalTheme
import com.whoanimal.core.domain.model.EnvironmentalContext
import com.whoanimal.core.domain.model.LightCondition
import com.whoanimal.core.domain.model.Observation
import com.whoanimal.core.domain.usecase.CaptureObservationUseCase
import kotlinx.coroutines.launch
import java.time.LocalTime

/**
 * CaptureScreen — Presentation layer (feature:capture).
 *
 * Architectural contract:
 * - Zero dependencies on :data:camera.
 * - Zero direct imports of CameraX (camera-core, camera-view, etc.).
 * - Capture is fully delegated to [CaptureObservationUseCase] (domain).
 * - The camera viewfinder is received as a composable slot [cameraPreviewSlot],
 *   provided by :app (composition root) using CameraXPreviewView from :data:camera.
 *   This keeps feature:capture decoupled from the concrete camera implementation.
 */
@Composable
fun CaptureScreen(
    captureObservationUseCase: CaptureObservationUseCase,
    /**
     * Composable slot that renders the camera viewfinder.
     * Injected from :app using CameraXPreviewView (data:camera).
     * Receives [onReady] callback (camera bound and streaming) and
     * [onError] callback (initialization failed with message).
     */
    cameraPreviewSlot: @Composable (
        onReady: () -> Unit,
        onError: (String) -> Unit,
        modifier: Modifier
    ) -> Unit,
    onNavigateBack: () -> Unit,
    onObservationCreated: (Observation) -> Unit = {},
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    var uiState by remember { mutableStateOf<CaptureUiState>(CaptureUiState.CheckingPermission) }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        uiState = if (isGranted) {
            CaptureUiState.CameraInitializing
        } else {
            CaptureUiState.PermissionDenied
        }
    }

    // Check camera permission on entry
    LaunchedEffect(Unit) {
        val currentPermission = ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
        uiState = if (currentPermission == PackageManager.PERMISSION_GRANTED) {
            CaptureUiState.CameraInitializing
        } else {
            CaptureUiState.PermissionRequired
        }
    }

    fun determineLightCondition(): LightCondition {
        val hour = LocalTime.now().hour
        return when (hour) {
            in 6..8 -> LightCondition.DAWN
            in 9..17 -> LightCondition.DAY
            in 18..20 -> LightCondition.DUSK
            else -> LightCondition.NIGHT
        }
    }

    fun captureObservation() {
        if (uiState != CaptureUiState.Ready) return
        uiState = CaptureUiState.Capturing

        coroutineScope.launch {
            val envContext = EnvironmentalContext(
                lightCondition = determineLightCondition(),
                ambientNotes = "Campo al aire libre"
            )
            // Domain use case fully orchestrates: camera capture + observation recording.
            // feature:capture has zero knowledge of CameraXManager or CameraX internals.
            val result = captureObservationUseCase(environmentalContext = envContext)
            result.fold(
                onSuccess = { observation ->
                    uiState = CaptureUiState.ObservationCreated(observation)
                    onObservationCreated(observation)
                },
                onFailure = { error ->
                    uiState = CaptureUiState.CameraError(
                        error.localizedMessage ?: context.getString(R.string.capture_error_generic)
                    )
                }
            )
        }
    }

    Scaffold(
        containerColor = Color.Black,
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (val state = uiState) {
                CaptureUiState.CheckingPermission,
                CaptureUiState.CameraInitializing,
                CaptureUiState.Ready,
                CaptureUiState.Capturing -> {
                    // Camera preview is provided as an injected slot from :app.
                    // feature:capture has no import of CameraX or :data:camera here.
                    if (state == CaptureUiState.CameraInitializing || state == CaptureUiState.Ready || state == CaptureUiState.Capturing) {
                        // Positional invocation required: Kotlin prohibits named args for function types.
                        cameraPreviewSlot(
                            {
                                if (uiState == CaptureUiState.CameraInitializing) {
                                    uiState = CaptureUiState.Ready
                                }
                            },
                            { errorMsg ->
                                uiState = CaptureUiState.CameraError(errorMsg)
                            },
                            Modifier.fillMaxSize()
                        )
                    }

                    // Quiet Naturalist Viewfinder Overlay
                    FieldViewfinder(modifier = Modifier.fillMaxSize())

                    // Top Bar: Back Button and Title
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 20.dp)
                            .align(Alignment.TopCenter),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = onNavigateBack,
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(WhoAnimalPalette.ForestDark.copy(alpha = 0.50f))
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                                contentDescription = stringResource(id = R.string.common_action_back),
                                tint = WhoAnimalPalette.Cream
                            )
                        }

                        Text(
                            text = stringResource(id = R.string.capture_title),
                            style = WhoAnimalTheme.typography.titleMedium,
                            color = WhoAnimalPalette.Cream
                        )

                        Spacer(modifier = Modifier.size(48.dp))
                    }

                    // Bottom Bar: Naturalist Shutter Trigger Button
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 36.dp)
                            .align(Alignment.BottomCenter),
                        contentAlignment = Alignment.Center
                    ) {
                        if (state == CaptureUiState.Capturing) {
                            CircularProgressIndicator(
                                color = WhoAnimalPalette.Cream,
                                modifier = Modifier.size(64.dp)
                            )
                        } else {
                            ShutterTriggerButton(
                                onClick = { captureObservation() },
                                enabled = state == CaptureUiState.Ready
                            )
                        }
                    }
                }

                CaptureUiState.PermissionRequired -> {
                    PermissionCard(
                        title = stringResource(id = R.string.capture_permission_title),
                        description = stringResource(id = R.string.capture_permission_rationale),
                        buttonText = stringResource(id = R.string.capture_permission_grant_button),
                        onButtonClick = { permissionLauncher.launch(Manifest.permission.CAMERA) },
                        onBackClick = onNavigateBack
                    )
                }

                CaptureUiState.PermissionDenied -> {
                    PermissionCard(
                        title = stringResource(id = R.string.capture_permission_title),
                        description = stringResource(id = R.string.capture_permission_denied_message),
                        buttonText = stringResource(id = R.string.common_action_explore),
                        isSettingsAction = true,
                        onButtonClick = {
                            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                                data = Uri.fromParts("package", context.packageName, null)
                            }
                            context.startActivity(intent)
                        },
                        onBackClick = onNavigateBack
                    )
                }

                is CaptureUiState.CameraError -> {
                    ErrorCard(
                        errorMessage = state.errorMessage,
                        onRetry = { uiState = CaptureUiState.CameraInitializing },
                        onBack = onNavigateBack
                    )
                }

                is CaptureUiState.ObservationCreated -> {
                    ObservationCreatedCard(
                        observation = state.observation,
                        onContinue = onNavigateBack,
                        onRetake = { uiState = CaptureUiState.CameraInitializing }
                    )
                }
            }
        }
    }
}

@Composable
private fun ShutterTriggerButton(
    onClick: () -> Unit,
    enabled: Boolean,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.94f else 1.0f,
        animationSpec = spring(
            dampingRatio = 0.85f,
            stiffness = Spring.StiffnessMedium
        ),
        label = "shutter_scale"
    )

    Box(
        modifier = modifier
            .scale(scale)
            .size(76.dp)
            .clip(CircleShape)
            .background(WhoAnimalPalette.Cream.copy(alpha = 0.35f))
            .border(2.dp, WhoAnimalPalette.Cream, CircleShape)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                enabled = enabled,
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .background(if (enabled) WhoAnimalPalette.Forest else WhoAnimalPalette.Stone)
        ) {
            Icon(
                imageVector = Icons.Rounded.PhotoCamera,
                contentDescription = stringResource(id = R.string.capture_button_description),
                tint = WhoAnimalPalette.Cream,
                modifier = Modifier
                    .size(28.dp)
                    .align(Alignment.Center)
            )
        }
    }
}

@Composable
private fun ObservationCreatedCard(
    observation: Observation,
    onContinue: () -> Unit,
    onRetake: () -> Unit
) {
    val dateMedium = RegionalFormatters.formatDateMedium(System.currentTimeMillis())
    val timeShort = RegionalFormatters.formatTimeShort(System.currentTimeMillis())

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(WhoAnimalPalette.ForestDark.copy(alpha = 0.85f))
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Card(
            colors = CardDefaults.cardColors(containerColor = WhoAnimalTheme.colors.surfaceCard),
            shape = WhoAnimalTheme.shapes.card,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Rounded.CheckCircle,
                    contentDescription = null,
                    tint = WhoAnimalTheme.colors.actionPrimaryFill,
                    modifier = Modifier.size(54.dp)
                )

                Text(
                    text = stringResource(id = R.string.capture_observation_success_title),
                    style = WhoAnimalTheme.typography.headlineMedium,
                    color = WhoAnimalTheme.colors.textPrimary
                )

                Text(
                    text = stringResource(id = R.string.capture_observation_success_desc),
                    style = WhoAnimalTheme.typography.bodyMedium,
                    color = WhoAnimalTheme.colors.textSecondary
                )

                // Observation Details
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(WhoAnimalTheme.shapes.snippet)
                        .background(WhoAnimalPalette.Sage.copy(alpha = 0.15f))
                        .padding(14.dp)
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Text(
                            text = "ID: ${observation.id.take(8)}…",
                            style = WhoAnimalTheme.typography.caption,
                            color = WhoAnimalTheme.colors.textTertiary
                        )
                        Text(
                            text = "$dateMedium · $timeShort",
                            style = WhoAnimalTheme.typography.caption,
                            color = WhoAnimalTheme.colors.textSecondary
                        )
                        Text(
                            text = "Atmósfera: ${observation.environmentalContext.lightCondition.name}",
                            style = WhoAnimalTheme.typography.caption,
                            color = WhoAnimalTheme.colors.textPrimary
                        )
                    }
                }

                // Actions
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(
                        onClick = onContinue,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = WhoAnimalTheme.colors.actionPrimaryFill,
                            contentColor = WhoAnimalTheme.colors.actionPrimaryText
                        ),
                        shape = WhoAnimalTheme.shapes.pill,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.capture_action_continue),
                            style = WhoAnimalTheme.typography.labelLarge
                        )
                    }

                    OutlinedButton(
                        onClick = onRetake,
                        shape = WhoAnimalTheme.shapes.pill,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.capture_action_retake),
                            style = WhoAnimalTheme.typography.bodyLarge,
                            color = WhoAnimalTheme.colors.textPrimary
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun PermissionCard(
    title: String,
    description: String,
    buttonText: String,
    onButtonClick: () -> Unit,
    onBackClick: () -> Unit,
    isSettingsAction: Boolean = false
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(WhoAnimalPalette.Mist)
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Card(
            colors = CardDefaults.cardColors(containerColor = WhoAnimalTheme.colors.surfaceCard),
            shape = WhoAnimalTheme.shapes.card,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Icon(
                    imageVector = if (isSettingsAction) Icons.Rounded.Settings else Icons.Rounded.Camera,
                    contentDescription = null,
                    tint = WhoAnimalTheme.colors.actionPrimaryFill,
                    modifier = Modifier.size(54.dp)
                )

                Text(
                    text = title,
                    style = WhoAnimalTheme.typography.headlineMedium,
                    color = WhoAnimalTheme.colors.textPrimary
                )

                Text(
                    text = description,
                    style = WhoAnimalTheme.typography.bodyLarge,
                    color = WhoAnimalTheme.colors.textSecondary
                )

                Button(
                    onClick = onButtonClick,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = WhoAnimalTheme.colors.actionPrimaryFill,
                        contentColor = WhoAnimalTheme.colors.actionPrimaryText
                    ),
                    shape = WhoAnimalTheme.shapes.pill,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                ) {
                    Text(
                        text = buttonText,
                        style = WhoAnimalTheme.typography.labelLarge
                    )
                }

                OutlinedButton(
                    onClick = onBackClick,
                    shape = WhoAnimalTheme.shapes.pill,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(id = R.string.common_action_back),
                        style = WhoAnimalTheme.typography.bodyLarge,
                        color = WhoAnimalTheme.colors.textPrimary
                    )
                }
            }
        }
    }
}

@Composable
private fun ErrorCard(
    errorMessage: String,
    onRetry: () -> Unit,
    onBack: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(WhoAnimalPalette.Mist)
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Card(
            colors = CardDefaults.cardColors(containerColor = WhoAnimalTheme.colors.surfaceCard),
            shape = WhoAnimalTheme.shapes.card,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Rounded.ErrorOutline,
                    contentDescription = null,
                    tint = WhoAnimalTheme.colors.cautionText,
                    modifier = Modifier.size(54.dp)
                )

                Text(
                    text = stringResource(id = R.string.capture_error_title),
                    style = WhoAnimalTheme.typography.headlineMedium,
                    color = WhoAnimalTheme.colors.textPrimary
                )

                Text(
                    text = errorMessage,
                    style = WhoAnimalTheme.typography.bodyMedium,
                    color = WhoAnimalTheme.colors.textSecondary
                )

                Button(
                    onClick = onRetry,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = WhoAnimalTheme.colors.actionPrimaryFill,
                        contentColor = WhoAnimalTheme.colors.actionPrimaryText
                    ),
                    shape = WhoAnimalTheme.shapes.pill,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(id = R.string.capture_action_retake),
                        style = WhoAnimalTheme.typography.labelLarge
                    )
                }

                OutlinedButton(
                    onClick = onBack,
                    shape = WhoAnimalTheme.shapes.pill,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(id = R.string.common_action_back),
                        style = WhoAnimalTheme.typography.bodyLarge,
                        color = WhoAnimalTheme.colors.textPrimary
                    )
                }
            }
        }
    }
}
