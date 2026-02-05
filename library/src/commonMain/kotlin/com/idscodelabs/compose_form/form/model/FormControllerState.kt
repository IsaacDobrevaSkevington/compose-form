package com.idscodelabs.compose_form.form.model

import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow

interface FormControllerState<Model> {
    /**
     * A map of the form boxes. The key should identify a specific field
     */
    val boxes: MutableMap<String, FormBox<Model, *>>

    /**
     * A flow of the current form box state
     */
    val boxFlows: MutableMap<String, MutableFormBoxFlow<Model, *>>

    /**
     * Observer jobs a list of jobs for observing field changes
     */
    val observerJobs: MutableMap<String, Job>

    /**
     * A flow of the current value of the form
     */
    val valueFlow: MutableStateFlow<Model>
}

fun <Model> FormControllerState(initialValue: Model) =
    object : FormControllerState<Model> {
        override val boxes: MutableMap<String, FormBox<Model, *>> = mutableMapOf()
        override val boxFlows: MutableMap<String, MutableFormBoxFlow<Model, *>> = mutableMapOf()
        override val observerJobs: MutableMap<String, Job> = mutableMapOf()
        override val valueFlow: MutableStateFlow<Model> = MutableStateFlow(initialValue)
    }
