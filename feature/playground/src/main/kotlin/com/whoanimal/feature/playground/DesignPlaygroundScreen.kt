package com.whoanimal.feature.playground

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.whoanimal.core.common.format.RegionalFormatters
import com.whoanimal.core.designsystem.R
import com.whoanimal.core.designsystem.component.CardPresentationModel
import com.whoanimal.core.designsystem.component.HeroDiscoverButton
import com.whoanimal.core.designsystem.component.JournalSnippet
import com.whoanimal.core.designsystem.component.NaturalistCard
import com.whoanimal.core.designsystem.component.ObjectiveCautionBadge
import com.whoanimal.core.designsystem.component.TaxonomyChip
import com.whoanimal.core.designsystem.theme.WhoAnimalPalette
import com.whoanimal.core.designsystem.theme.WhoAnimalTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Locale

private enum class PlaygroundTab {
    COMPONENTS,
    PALETTE,
    TYPOGRAPHY,
    MOTION,
    BILINGUAL
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DesignPlaygroundScreen(
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedTab by remember { mutableStateOf(PlaygroundTab.COMPONENTS) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = stringResource(id = R.string.playground_title),
                            style = WhoAnimalTheme.typography.titleMedium,
                            color = WhoAnimalTheme.colors.textPrimary
                        )
                        Text(
                            text = stringResource(id = R.string.playground_subtitle),
                            style = WhoAnimalTheme.typography.caption,
                            color = WhoAnimalTheme.colors.textSecondary
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = stringResource(id = R.string.common_action_back),
                            tint = WhoAnimalTheme.colors.textPrimary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = WhoAnimalTheme.colors.backgroundApp
                )
            )
        },
        containerColor = WhoAnimalTheme.colors.backgroundApp,
        modifier = modifier
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Horizontal Navigation Tabs
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                PlaygroundTabChip(
                    text = stringResource(id = R.string.playground_tab_components),
                    isSelected = selectedTab == PlaygroundTab.COMPONENTS,
                    onClick = { selectedTab = PlaygroundTab.COMPONENTS }
                )
                PlaygroundTabChip(
                    text = stringResource(id = R.string.playground_tab_motion),
                    isSelected = selectedTab == PlaygroundTab.MOTION,
                    onClick = { selectedTab = PlaygroundTab.MOTION }
                )
                PlaygroundTabChip(
                    text = stringResource(id = R.string.playground_tab_palette),
                    isSelected = selectedTab == PlaygroundTab.PALETTE,
                    onClick = { selectedTab = PlaygroundTab.PALETTE }
                )
                PlaygroundTabChip(
                    text = stringResource(id = R.string.playground_tab_typography),
                    isSelected = selectedTab == PlaygroundTab.TYPOGRAPHY,
                    onClick = { selectedTab = PlaygroundTab.TYPOGRAPHY }
                )
                PlaygroundTabChip(
                    text = stringResource(id = R.string.playground_tab_bilingual),
                    isSelected = selectedTab == PlaygroundTab.BILINGUAL,
                    onClick = { selectedTab = PlaygroundTab.BILINGUAL }
                )
            }

            // Tab Content
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                when (selectedTab) {
                    PlaygroundTab.COMPONENTS -> ComponentsTab()
                    PlaygroundTab.MOTION -> MotionTab()
                    PlaygroundTab.PALETTE -> PaletteTab()
                    PlaygroundTab.TYPOGRAPHY -> TypographyTab()
                    PlaygroundTab.BILINGUAL -> BilingualTab()
                }
            }
        }
    }
}

@Composable
private fun PlaygroundTabChip(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    FilterChip(
        selected = isSelected,
        onClick = onClick,
        label = { Text(text = text, style = WhoAnimalTheme.typography.caption) },
        colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = WhoAnimalTheme.colors.actionPrimaryFill,
            selectedLabelColor = WhoAnimalTheme.colors.actionPrimaryText,
            containerColor = WhoAnimalTheme.colors.surfaceCard,
            labelColor = WhoAnimalTheme.colors.textPrimary
        ),
        border = FilterChipDefaults.filterChipBorder(
            enabled = true,
            selected = isSelected,
            borderColor = WhoAnimalPalette.Sage.copy(alpha = 0.4f),
            selectedBorderColor = WhoAnimalTheme.colors.actionPrimaryFill
        )
    )
}

@Composable
private fun ComponentsTab() {
    val scrollState = rememberScrollState()
    val sampleDate = RegionalFormatters.formatDateMedium(System.currentTimeMillis())
    val sampleTime = RegionalFormatters.formatTimeShort(System.currentTimeMillis())

    val sampleCard = remember(sampleDate, sampleTime) {
        CardPresentationModel(
            id = "demo-card-01",
            commonName = "Mirlo Común / Common Blackbird",
            scientificName = "Turdus merula",
            familyOrOrder = "Turdidae · Passeriformes",
            dateText = sampleDate,
            timeText = sampleTime,
            iucnStatusCode = "LC",
            iucnStatusName = "Least Concern",
            biologyNotes = "Ave paseriforme de plumaje oscuro y pico anaranjado brillante en machos. Canto melodioso territorial.",
            fieldExperienceNotes = "Avistado en el borde de un claro de robles al atardecer. Muy confiado entre la hojarasca.",
            locationProtectedText = "Bosque Caducifolio · Parcela Natural Protegida",
            loreTitle = "El Cantor de los Bosques Antiguos",
            loreStory = "Cuentan las leyendas celtas que el canto del mirlo abre las puertas del Otro Mundo para quienes saben escuchar con calma."
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(bottom = 32.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        // Section: Hero Discover Button
        Card(
            colors = CardDefaults.cardColors(containerColor = WhoAnimalTheme.colors.surfaceCard),
            shape = WhoAnimalTheme.shapes.snippet
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "HeroDiscoverButton [ DESCUBRIR ]",
                    style = WhoAnimalTheme.typography.titleMedium,
                    color = WhoAnimalTheme.colors.textPrimary
                )
                Spacer(modifier = Modifier.height(12.dp))
                HeroDiscoverButton(onClick = {})
            }
        }

        // Section: Taxonomy Chips
        Card(
            colors = CardDefaults.cardColors(containerColor = WhoAnimalTheme.colors.surfaceCard),
            shape = WhoAnimalTheme.shapes.snippet
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "TaxonomyChip",
                    style = WhoAnimalTheme.typography.titleMedium,
                    color = WhoAnimalTheme.colors.textPrimary
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    TaxonomyChip(text = "Aves")
                    TaxonomyChip(text = "Turdidae")
                    TaxonomyChip(text = "Chordata")
                    TaxonomyChip(text = "LC")
                }
            }
        }

        // Section: Objective Caution Badge
        Card(
            colors = CardDefaults.cardColors(containerColor = WhoAnimalTheme.colors.surfaceCard),
            shape = WhoAnimalTheme.shapes.snippet
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "ObjectiveCautionBadge (100% Pet Friendly)",
                    style = WhoAnimalTheme.typography.titleMedium,
                    color = WhoAnimalTheme.colors.textPrimary
                )
                Spacer(modifier = Modifier.height(10.dp))
                ObjectiveCautionBadge(
                    safeDistanceMeters = 15,
                    adviceNotes = "Especie con crías en madriguera. No emitir ruidos fuertes ni acercar animales de compañía."
                )
            }
        }

        // Section: Journal Snippet
        Card(
            colors = CardDefaults.cardColors(containerColor = WhoAnimalTheme.colors.surfaceCard),
            shape = WhoAnimalTheme.shapes.snippet
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "JournalSnippet (Bitácora Reciente)",
                    style = WhoAnimalTheme.typography.titleMedium,
                    color = WhoAnimalTheme.colors.textPrimary
                )
                Spacer(modifier = Modifier.height(10.dp))
                JournalSnippet(
                    commonName = "Zorro Rojo",
                    scientificName = "Vulpes vulpes",
                    timeOrDateText = sampleTime,
                    onClick = {}
                )
            }
        }

        // Section: Naturalist Card (Interactive 5:7 with 3 Pillars)
        Card(
            colors = CardDefaults.cardColors(containerColor = WhoAnimalTheme.colors.backgroundApp),
            shape = WhoAnimalTheme.shapes.snippet
        ) {
            Column(
                modifier = Modifier.padding(vertical = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "NaturalistCard (5:7 — 3 Pilares)",
                    style = WhoAnimalTheme.typography.titleMedium,
                    color = WhoAnimalTheme.colors.textPrimary
                )
                Spacer(modifier = Modifier.height(12.dp))
                Box(modifier = Modifier.width(300.dp)) {
                    NaturalistCard(cardModel = sampleCard)
                }
            }
        }
    }
}

@Composable
private fun MotionTab() {
    val coroutineScope = rememberCoroutineScope()
    var choreographyStep by remember { mutableIntStateOf(0) } // 0: Idle, 1: Focus (200ms), 2: Identity (300ms), 3: Materialize (350ms)
    var isRunning by remember { mutableStateOf(false) }

    val focusBlurAlpha = remember { Animatable(0f) }
    val cardScale = remember { Animatable(0.92f) }

    fun playChoreography() {
        if (isRunning) return
        isRunning = true
        choreographyStep = 0
        coroutineScope.launch {
            // Step 1: Pausa de enfoque (200ms)
            choreographyStep = 1
            focusBlurAlpha.animateTo(1f, tween(200, easing = FastOutSlowInEasing))
            delay(200)

            // Step 2: Revelación de identidad (300ms)
            choreographyStep = 2
            delay(300)

            // Step 3: Materialización táctil de la Card (350ms)
            choreographyStep = 3
            cardScale.animateTo(
                1f,
                spring(dampingRatio = 0.85f, stiffness = Spring.StiffnessMedium)
            )
            delay(350)
            isRunning = false
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(bottom = 32.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.motion_demo_title),
            style = WhoAnimalTheme.typography.headlineMedium,
            color = WhoAnimalTheme.colors.textPrimary
        )

        Button(
            onClick = { playChoreography() },
            enabled = !isRunning,
            colors = ButtonDefaults.buttonColors(
                containerColor = WhoAnimalTheme.colors.actionPrimaryFill,
                contentColor = WhoAnimalTheme.colors.actionPrimaryText
            ),
            shape = WhoAnimalTheme.shapes.pill,
            modifier = Modifier.fillMaxWidth().height(50.dp)
        ) {
            Icon(imageVector = Icons.Rounded.PlayArrow, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = stringResource(id = R.string.motion_action_trigger),
                style = WhoAnimalTheme.typography.labelLarge
            )
        }

        // Coreography Steps Status
        StepStatusCard(
            stepNumber = 1,
            title = stringResource(id = R.string.motion_step_1_title),
            desc = stringResource(id = R.string.motion_step_1_desc),
            isActive = choreographyStep >= 1
        )
        StepStatusCard(
            stepNumber = 2,
            title = stringResource(id = R.string.motion_step_2_title),
            desc = stringResource(id = R.string.motion_step_2_desc),
            isActive = choreographyStep >= 2
        )
        StepStatusCard(
            stepNumber = 3,
            title = stringResource(id = R.string.motion_step_3_title),
            desc = stringResource(id = R.string.motion_step_3_desc),
            isActive = choreographyStep >= 3
        )

        // Live Simulated Result Frame
        AnimatedVisibility(
            visible = choreographyStep >= 1,
            enter = fadeIn() + slideInVertically(),
            exit = fadeOut()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .scale(cardScale.value)
                    .clip(WhoAnimalTheme.shapes.card)
                    .background(WhoAnimalTheme.colors.surfaceCard)
                    .border(1.dp, WhoAnimalPalette.Stone.copy(alpha = 0.3f), WhoAnimalTheme.shapes.card)
                    .padding(20.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    if (choreographyStep >= 2) {
                        Text(
                            text = "Lince Ibérico",
                            style = WhoAnimalTheme.typography.headlineMedium,
                            color = WhoAnimalTheme.colors.textPrimary
                        )
                        Text(
                            text = "Lynx pardinus",
                            style = WhoAnimalTheme.typography.scientific,
                            color = WhoAnimalTheme.colors.textSecondary
                        )
                    }
                    if (choreographyStep >= 3) {
                        Spacer(modifier = Modifier.height(10.dp))
                        TaxonomyChip(text = "Felidae · Carnivora")
                    }
                }
            }
        }
    }
}

@Composable
private fun StepStatusCard(
    stepNumber: Int,
    title: String,
    desc: String,
    isActive: Boolean
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = if (isActive) WhoAnimalPalette.Sage.copy(alpha = 0.2f) else WhoAnimalTheme.colors.surfaceCard
        ),
        shape = WhoAnimalTheme.shapes.snippet,
        border = CardDefaults.outlinedCardBorder().copy(
            brush = androidx.compose.ui.graphics.SolidColor(
                if (isActive) WhoAnimalTheme.colors.actionAccentFocus else WhoAnimalPalette.Stone.copy(alpha = 0.2f)
            )
        )
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Rounded.CheckCircle,
                contentDescription = null,
                tint = if (isActive) WhoAnimalTheme.colors.actionAccentFocus else WhoAnimalPalette.Stone.copy(alpha = 0.4f),
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = title,
                    style = WhoAnimalTheme.typography.bodyLarge,
                    color = WhoAnimalTheme.colors.textPrimary
                )
                Text(
                    text = desc,
                    style = WhoAnimalTheme.typography.caption,
                    color = WhoAnimalTheme.colors.textSecondary
                )
            }
        }
    }
}

@Composable
private fun PaletteTab() {
    val colors = listOf(
        Triple("Mist", WhoAnimalPalette.Mist, "#EBF0ED · HSL 140°, 12%, 93% (Fondo Base Campamento)"),
        Triple("Sage", WhoAnimalPalette.Sage, "#8EA89D · HSL 156°, 14%, 61% (Follaje Suave / Chips)"),
        Triple("Deep Teal", WhoAnimalPalette.DeepTeal, "#1A4240 · HSL 176°, 43%, 18% (Agua Bosque / Foco)"),
        Triple("Forest", WhoAnimalPalette.Forest, "#233D34 · HSL 162°, 27%, 19% (Pinar Hero CTA [DESCUBRIR])"),
        Triple("Stone", WhoAnimalPalette.Stone, "#7B857F · HSL 146°, 4%, 50% (Roca / Filetes / Metadatos)"),
        Triple("Cream", WhoAnimalPalette.Cream, "#F7F5F0 · HSL 45°, 24%, 96% (Lámina de Papel Naturalista)")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(bottom = 32.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        colors.forEach { (name, color, description) ->
            Card(
                colors = CardDefaults.cardColors(containerColor = WhoAnimalTheme.colors.surfaceCard),
                shape = WhoAnimalTheme.shapes.snippet
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(54.dp)
                            .clip(CircleShape)
                            .background(color)
                            .border(1.dp, WhoAnimalPalette.Stone.copy(alpha = 0.3f), CircleShape)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(
                            text = name,
                            style = WhoAnimalTheme.typography.titleMedium,
                            color = WhoAnimalTheme.colors.textPrimary
                        )
                        Text(
                            text = description,
                            style = WhoAnimalTheme.typography.caption,
                            color = WhoAnimalTheme.colors.textSecondary
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun TypographyTab() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(bottom = 32.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Display Large (32sp Bold Serif):", style = WhoAnimalTheme.typography.caption)
        Text("WHO Animal v1", style = WhoAnimalTheme.typography.displayLarge)

        Text("Headline Medium (24sp SemiBold Serif):", style = WhoAnimalTheme.typography.caption)
        Text("Águila Imperial Ibérica", style = WhoAnimalTheme.typography.headlineMedium)

        Text("Scientific (15sp Italic Serif):", style = WhoAnimalTheme.typography.caption)
        Text("Aquila adalberti", style = WhoAnimalTheme.typography.scientific)

        Text("Title Medium (18sp Medium Serif):", style = WhoAnimalTheme.typography.caption)
        Text("Orden Accipitriformes · Familia Accipitridae", style = WhoAnimalTheme.typography.titleMedium)

        Text("Body Large (16sp Regular Sans):", style = WhoAnimalTheme.typography.caption)
        Text("Ave rapaz emblemática de la Península Ibérica, asociada a bosques mediterráneos y dehesas.", style = WhoAnimalTheme.typography.bodyLarge)

        Text("Body Medium (14sp Regular Sans):", style = WhoAnimalTheme.typography.caption)
        Text("Su dieta se basa principalmente en conejos y pequeños mamíferos.", style = WhoAnimalTheme.typography.bodyMedium)

        Text("Field Note (13sp Italic Serif):", style = WhoAnimalTheme.typography.caption)
        Text("«Planeo majestuoso observado a mediodía sobre la dehesa del encinar.»", style = WhoAnimalTheme.typography.fieldNote)

        Text("Caption (12sp Regular Sans):", style = WhoAnimalTheme.typography.caption)
        Text("11 de Septiembre · 08:30 · Estado UICN: Vulnerable (VU)", style = WhoAnimalTheme.typography.caption)
    }
}

@Composable
private fun BilingualTab() {
    val currentLocale = Locale.getDefault()
    val now = System.currentTimeMillis()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(bottom = 32.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Card(
            colors = CardDefaults.cardColors(containerColor = WhoAnimalTheme.colors.surfaceCard),
            shape = WhoAnimalTheme.shapes.snippet
        ) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "Configuración Regional Activa",
                    style = WhoAnimalTheme.typography.titleMedium,
                    color = WhoAnimalTheme.colors.textPrimary
                )
                Text(
                    text = "Locale: ${currentLocale.language}_${currentLocale.country} (${currentLocale.displayName})",
                    style = WhoAnimalTheme.typography.bodyMedium,
                    color = WhoAnimalTheme.colors.textSecondary
                )
                Text(
                    text = "Fecha formateada: ${RegionalFormatters.formatDateMedium(now, currentLocale)}",
                    style = WhoAnimalTheme.typography.bodyMedium,
                    color = WhoAnimalTheme.colors.textPrimary
                )
                Text(
                    text = "Hora formateada: ${RegionalFormatters.formatTimeShort(now, currentLocale)}",
                    style = WhoAnimalTheme.typography.bodyMedium,
                    color = WhoAnimalTheme.colors.textPrimary
                )
                Text(
                    text = "Número formateado: ${RegionalFormatters.formatInteger(1250L, currentLocale)}",
                    style = WhoAnimalTheme.typography.bodyMedium,
                    color = WhoAnimalTheme.colors.textPrimary
                )
            }
        }

        Card(
            colors = CardDefaults.cardColors(containerColor = WhoAnimalTheme.colors.surfaceCard),
            shape = WhoAnimalTheme.shapes.snippet
        ) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "Verificación de Plurales (ES / EN)",
                    style = WhoAnimalTheme.typography.titleMedium,
                    color = WhoAnimalTheme.colors.textPrimary
                )
                Text(
                    text = "1 carta: ${pluralStringResource(R.plurals.collection_cards_count, 1, 1)}",
                    style = WhoAnimalTheme.typography.bodyMedium,
                    color = WhoAnimalTheme.colors.textPrimary
                )
                Text(
                    text = "5 cartas: ${pluralStringResource(R.plurals.collection_cards_count, 5, 5)}",
                    style = WhoAnimalTheme.typography.bodyMedium,
                    color = WhoAnimalTheme.colors.textPrimary
                )
                Text(
                    text = "1 avistamiento: ${pluralStringResource(R.plurals.home_recent_count, 1, 1)}",
                    style = WhoAnimalTheme.typography.bodyMedium,
                    color = WhoAnimalTheme.colors.textPrimary
                )
                Text(
                    text = "3 avistamientos: ${pluralStringResource(R.plurals.home_recent_count, 3, 3)}",
                    style = WhoAnimalTheme.typography.bodyMedium,
                    color = WhoAnimalTheme.colors.textPrimary
                )
            }
        }
    }
}
