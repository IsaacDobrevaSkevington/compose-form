package com.idscodelabs.compose_form

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.backhandler.BackHandler
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.idscodelabs.compose_form.app.BackIcon
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
                                Icon(BackIcon, contentDescription = "Back Button")
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
