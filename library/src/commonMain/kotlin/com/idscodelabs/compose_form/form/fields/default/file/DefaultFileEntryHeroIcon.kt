package com.idscodelabs.compose_form.form.fields.default.file

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.idscodelabs.compose_form.form.icons.Icons
import com.idscodelabs.compose_form.form.model.LocalFormBox

@Composable
fun DefaultFileEntryHeroIcon() {
    Icon(
        Icons.File,
        null,
        modifier = Modifier.size(48.dp),
        tint =
            if (LocalFormBox.current.error ==
                null
            ) {
                MaterialTheme.colorScheme.primary.copy(alpha = 0.7f)
            } else {
                MaterialTheme.colorScheme.error
            },
    )
}
