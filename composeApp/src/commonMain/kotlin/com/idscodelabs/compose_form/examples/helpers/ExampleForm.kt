package com.idscodelabs.compose_form.examples.helpers

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.backhandler.BackHandler
import com.idscodelabs.compose_form.form.core.Form
import com.idscodelabs.compose_form.form.core.FormScope

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun <Model : ExampleModel<*>> ExampleForm(
    emptyModel: () -> Model,
    contents: @Composable FormScope<Model>.() -> Unit = {},
) {
    val (result, setResult) =
        remember {
            mutableStateOf<Model?>(null)
        }
    BackHandler(enabled = result != null) {
        setResult(null)
    }
    if (result == null) {
        Form(emptyModel = emptyModel) {
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
