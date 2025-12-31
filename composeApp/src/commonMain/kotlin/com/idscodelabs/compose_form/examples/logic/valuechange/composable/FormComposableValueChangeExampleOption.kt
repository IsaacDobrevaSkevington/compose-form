package com.idscodelabs.compose_form.examples.logic.valuechange.composable

import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import com.idscodelabs.compose_form.form.fields.core.base.ListDisplayable

enum class FormComposableValueChangeExampleOption : ListDisplayable {
    DATE_FIELD,
    TIME_FIELD,
    ;

    override val key: Any
        get() = ordinal

    override val label: Any
        get() = name.replace("_", " ").lowercase().capitalize(Locale.current)

    override val position: Int
        get() = ordinal
}
