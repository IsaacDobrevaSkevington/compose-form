package com.idscodelabs.compose_form.form.fields.default.slider

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.idscodelabs.compose_form.form.fields.strings.asDisplayString
import com.idscodelabs.compose_form.form.model.FormBox
import com.idscodelabs.compose_form.form.model.LocalFormBox
import com.idscodelabs.compose_form.styles.LocalFormStyle
import com.idscodelabs.compose_form.utils.StandardErrorDisplay
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormBox<*, Int>.DefaultFormSliderEntry(
    start: Int = 0,
    end: Int = 100,
    hint: Any? = null,
    slider: @Composable (value: Int, setValue: (Int) -> Unit) -> Unit = { value, setValue ->
        Slider(
            value = value.coerceIn(start, end).toFloat(),
            onValueChange = {
                setValue(it.roundToInt())
            },
            valueRange = start.toFloat()..end.toFloat(),
            enabled = LocalFormBox.current.enabled,
        )
    },
    errorDisplay: @Composable (error: String) -> Unit = {
        StandardErrorDisplay(it)
    },
) {
    Column(verticalArrangement = Arrangement.spacedBy(LocalFormStyle.current.fieldColumnSpacing)) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(LocalFormStyle.current.fieldRowSpacing),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            hint?.let {
                Text(hint.asDisplayString(), modifier = Modifier.weight(1f))
            } ?: Spacer(Modifier.weight(1f))
            Text(value.toString())
        }
        Box(Modifier.primaryFocusable()) {
            slider(value, ::setValue)
        }

        error?.let {
            errorDisplay(it)
        }
    }
}
