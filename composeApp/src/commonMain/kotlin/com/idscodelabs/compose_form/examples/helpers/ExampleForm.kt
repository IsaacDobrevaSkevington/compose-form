package com.idscodelabs.compose_form.examples.helpers

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.navigationevent.NavigationEventInfo
import androidx.navigationevent.compose.NavigationBackHandler
import androidx.navigationevent.compose.rememberNavigationEventState
import com.idscodelabs.compose_form.form.core.controller.FormController
import com.idscodelabs.compose_form.form.core.ui.Form

val LocalExampleFormEnabled = compositionLocalOf { true }

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun <Model : ExampleModel<*>> ExampleForm(
    emptyModel: () -> Model,
    contents: @Composable FormController<Model>.() -> Unit = {},
) {
    val (result, setResult) =
        remember {
            mutableStateOf<Model?>(null)
        }
    NavigationBackHandler(
        isBackEnabled = result != null,
        state = rememberNavigationEventState(currentInfo = NavigationEventInfo.None),
    ) {
        setResult(null)
    }
    if (result == null) {
        Form(emptyModel = emptyModel, enabled = LocalExampleFormEnabled.current) {
            contents()
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    submit { setResult(it) }
                },
            ) {
                Text("Submit")
            }
        }
    } else {
        Text(text = "Result is ${result.value}")
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun <Model : ExampleModel<*>> ExampleForm(
    controller: FormController<Model>,
    contents: @Composable FormController<Model>.() -> Unit = {},
) {
    val (result, setResult) =
        remember {
            mutableStateOf<Model?>(null)
        }
    NavigationBackHandler(
        isBackEnabled = result != null,
        state = rememberNavigationEventState(currentInfo = NavigationEventInfo.None),
    ) {
        setResult(null)
    }
    if (result == null) {
        Form(controller = controller, enabled = LocalExampleFormEnabled.current) {
            contents()
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    submit { setResult(it) }
                },
            ) {
                Text("Submit")
            }
        }
    } else {
        Text(text = "Result is ${result.value}")
    }
}
