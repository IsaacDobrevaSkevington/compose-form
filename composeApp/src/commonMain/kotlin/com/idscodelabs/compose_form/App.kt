package com.idscodelabs.compose_form

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.backhandler.BackHandler
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.idscodelabs.compose_form.app.Example
import com.idscodelabs.compose_form.app.ExampleField
import com.idscodelabs.compose_form.app.ExampleValidator
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
@Preview
fun App() {
    MaterialTheme {
        var currentContent by remember { mutableStateOf<Example?>(null) }
        var selectedDestination by rememberSaveable { mutableIntStateOf(0) }

        BackHandler(enabled = currentContent != null) {
            currentContent = null
        }

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
                    .padding(horizontal = 8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                PrimaryTabRow(selectedTabIndex = selectedDestination) {
                    Tab(
                        selected = selectedDestination == 0,
                        onClick = {
                            selectedDestination = 0
                        },
                        text = {
                            Text(
                                text = "Fields",
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis,
                            )
                        },
                    )
                    Tab(
                        selected = selectedDestination == 1,
                        onClick = {
                            selectedDestination = 1
                        },
                        text = {
                            Text(
                                text = "Validators",
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis,
                            )
                        },
                    )
                }

                Column(
                    Modifier.weight(1f).verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    val options = if (selectedDestination == 0) ExampleField.entries else ExampleValidator.entries
                    options.sortedBy { it.displayName }.forEach { entry ->
                        Button(onClick = { currentContent = entry }, modifier = Modifier.fillMaxWidth()) {
                            Text(entry.displayName)
                        }
                    }
                }
            }
        }
    }
}
