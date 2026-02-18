package com.idscodelabs.compose_form.form.model

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filterNotNull

/**
 * A flow of form boxes, mutable
 */
typealias MutableFormBoxFlow<Model, Value> = MutableStateFlow<FormBox<Model, Value>?>

/**
 * A flow of form boxes, immutable
 */
typealias FormBoxFlow<Model, Value> = StateFlow<FormBox<Model, Value>?>

/**
 * Subscribe to [FormBox] value changes
 *
 * This function should be used to observe value changes
 *
 * @param debounceMillis Debounce for the value to allow the value to settle
 * @param block Executed when a change is detected in [Value] assuming no further changes are detected for [debounceMillis]
 */
@OptIn(ExperimentalCoroutinesApi::class)
suspend fun <Value> FormBoxFlow<*, Value>.onFieldValueChanged(
    debounceMillis: Long = 0,
    block: suspend Value.() -> Unit,
) {
    filterNotNull().collectLatest {
        it.onFieldValueChanged(debounceMillis) {
            block()
        }
    }
}

/**
 * Subscribe to [FormBox] value changes
 *
 * This function should be used to observe value changes as their string value
 *
 * @param debounceMillis Debounce for the value to allow the value to settle
 * @param block Executed when a change is detected in the value assuming no further changes are detected for [debounceMillis]
 */
@OptIn(ExperimentalCoroutinesApi::class)
suspend fun FormBoxFlow<*, *>.onFieldStringValueChanged(
    debounceMillis: Long = 0,
    block: suspend String?.() -> Unit,
) {
    filterNotNull().collectLatest {
        it.onFieldStringValueChanged(debounceMillis) {
            block()
        }
    }
}
