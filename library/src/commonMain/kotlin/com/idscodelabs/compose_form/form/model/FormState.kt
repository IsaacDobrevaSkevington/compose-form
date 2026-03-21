package com.idscodelabs.compose_form.form.model

sealed interface FormState {
    object Enabled : FormState

    object Disabled : FormState

    data class Submitting(
        val previousState: FormState,
    ) : FormState
}

fun FormState.isEnabled(): Boolean = this is FormState.Enabled || this is FormState.Submitting && previousState is FormState.Disabled
