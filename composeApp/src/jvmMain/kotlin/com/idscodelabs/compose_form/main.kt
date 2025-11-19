package com.idscodelabs.compose_form

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "compose_form",
    ) {
        App()
    }
}