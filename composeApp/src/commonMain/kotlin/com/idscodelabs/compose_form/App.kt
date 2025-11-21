package com.idscodelabs.compose_form

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.idscodelabs.compose_form.app.ExampleScreenDisplay
import compose_form.composeapp.generated.resources.Res
import compose_form.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun App() {
    MaterialTheme {
        var currentContent by remember { mutableStateOf<ExampleScreenDisplay?>(null) }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(currentContent?.displayName?.let { "$it Example" } ?: "Compose Form Example (${getPlatform().name})") },
                    navigationIcon = {
                        if (currentContent != null) {
                            IconButton(onClick = { currentContent = null }) {
                                Icon(Icons.AutoMirrored.Outlined.ArrowBack, contentDescription = "Back Button")
                            }
                        }
                    },
                )
            },
        ) { paddingValues ->
            currentContent?.let {
                Box(Modifier.padding(paddingValues).fillMaxSize()) {
                    it.contents()
                }
            } ?: Column(
                Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .padding(horizontal = 8.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                ExampleScreenDisplay.entries.sortedBy { it.displayName }.forEach { entry ->
                    Button(onClick = { currentContent = entry }, modifier = Modifier.fillMaxWidth()) {
                        Text(entry.displayName)
                    }
                }
            }
        }
    }
}
