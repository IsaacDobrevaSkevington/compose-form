package com.idscodelabs.compose_form.form.model

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filterNotNull

typealias MutableFormBoxFlow<Model, Value> = MutableStateFlow<FormBox<Model, Value>?>
typealias FormBoxFlow<Model, Value> = StateFlow<FormBox<Model, Value>?>

@OptIn(ExperimentalCoroutinesApi::class)
suspend fun <Value> FormBoxFlow<*, Value>.onFieldValueChanged(
    debounceMills: Long = 0,
    block: suspend Value.() -> Unit,
) {
    filterNotNull().collectLatest {
        it.onFieldValueChanged(debounceMills) {
            block()
        }
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
suspend fun FormBoxFlow<*, *>.onFieldStringValueChanged(
    debounceMills: Long = 0,
    block: suspend String?.() -> Unit,
) {
    filterNotNull().collectLatest {
        it.onFieldStringValueChanged(debounceMills) {
            block()
        }
    }
}
