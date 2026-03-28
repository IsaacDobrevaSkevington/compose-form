package com.idscodelabs.compose_form

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigationevent.NavigationEventInfo
import androidx.navigationevent.compose.NavigationBackHandler
import androidx.navigationevent.compose.rememberNavigationEventState
import com.idscodelabs.compose_form.app.BackIcon
import com.idscodelabs.compose_form.app.Example
import com.idscodelabs.compose_form.app.Tabs
import com.idscodelabs.compose_form.examples.helpers.LocalExampleFormEnabled

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
@Preview
fun App() {
    MaterialTheme {
        var currentContent by remember { mutableStateOf<Example?>(null) }
        var selectedDestination by remember { mutableStateOf(Tabs.FIELDS) }
        val (enabledState, setEnabledState) = remember { mutableStateOf(true) }

        NavigationBackHandler(
            isBackEnabled = currentContent != null,
            state = rememberNavigationEventState(currentInfo = NavigationEventInfo.None),
        ) {
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
                    actions = {
                        Switch(checked = enabledState, onCheckedChange = setEnabledState)
                    },
                )
            },
        ) { paddingValues ->
            currentContent?.let {
                Box(Modifier.padding(paddingValues).fillMaxSize()) {
                    CompositionLocalProvider(LocalExampleFormEnabled provides enabledState) {
                        it.contents()
                    }
                }
            } ?: Column(
                Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .padding(horizontal = 8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                PrimaryTabRow(selectedTabIndex = selectedDestination.ordinal) {
                    Tabs.entries.filterNot { it.examples.isEmpty() }.forEach {
                        Tab(
                            selected = selectedDestination == it,
                            onClick = {
                                selectedDestination = it
                            },
                            text = {
                                Text(
                                    text = it.displayName,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis,
                                )
                            },
                        )
                    }
                }

                Column(
                    Modifier.weight(1f).verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    selectedDestination.examples.sortedBy { it.displayName }.forEach { entry ->
                        Button(onClick = { currentContent = entry }, modifier = Modifier.fillMaxWidth()) {
                            Text(entry.displayName)
                        }
                    }
                }
            }
        }
    }
}
