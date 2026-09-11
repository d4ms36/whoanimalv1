package com.whoanimal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
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
import com.whoanimal.feature.playground.DesignPlaygroundScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WhoAnimalTheme {
                WhoAnimalApp()
            }
        }
    }
}

@Composable
fun WhoAnimalApp() {
    var isPlaygroundOpen by remember { mutableStateOf(false) }

    // Design Playground accessible strictly in DEBUG builds
    if (BuildConfig.DEBUG && isPlaygroundOpen) {
        DesignPlaygroundScreen(
            onNavigateBack = { isPlaygroundOpen = false }
        )
    } else {
        BaseCampHomeScreen(
            onOpenPlayground = {
                if (BuildConfig.DEBUG) {
                    isPlaygroundOpen = true
                }
            }
        )
    }
}

@Composable
private fun BaseCampHomeScreen(
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
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
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

            // ESTRATO PECHO: Corazón de Descubrimiento (Hero CTA)
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp)
            ) {
                HeroDiscoverButton(
                    onClick = {}
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
                JournalSnippet(
                    commonName = "Mirlo Común",
                    scientificName = "Turdus merula",
                    timeOrDateText = sampleTime,
                    onClick = {}
                )
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
                    modifier = Modifier.fillMaxWidth().height(48.dp)
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
