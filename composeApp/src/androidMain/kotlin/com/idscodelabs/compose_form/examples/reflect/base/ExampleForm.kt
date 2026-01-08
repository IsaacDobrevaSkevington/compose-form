package com.idscodelabs.compose_form.examples.reflect.base

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.backhandler.BackHandler
import com.idscodelabs.compose_form.examples.helpers.ExampleModel
import com.idscodelabs.compose_form.form.core.controller.FormController
import com.idscodelabs.compose_form.ui.Form

@OptIn(ExperimentalComposeUiApi::class)
@Composable
inline fun <reified Model : ExampleModel<*>> ExampleForm(
    crossinline contents: @Composable FormController<Model>.() -> Unit = {},
) {
    val (result, setResult) =
        remember {
            mutableStateOf<Model?>(null)
        }
    BackHandler(enabled = result != null) {
        setResult(null)
    }
    if (result == null) {
        Form<Model>{
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