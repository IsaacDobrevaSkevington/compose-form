package com.idscodelabs.compose_form.helpers

import androidx.compose.ui.focus.FocusRequester
import com.idscodelabs.compose_form.form.core.controller.BasicFormController
import com.idscodelabs.compose_form.form.core.controller.FormController
import com.idscodelabs.compose_form.form.model.FormBox
import com.idscodelabs.compose_form.utils.VirtualKProperty
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.TestScope
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

internal data class TestModel(
    var value: String?
)

internal fun TestScope.testFormController(configure: FormController<TestModel>.()->Unit = {}) = BasicFormController(
    { TestModel(null) },
    this
).apply(configure)
@OptIn(ExperimentalUuidApi::class)
fun randomProperty() = VirtualKProperty(Uuid.random().toString())


internal fun testFormBox(initialValue: String = "") = FormBox<TestModel, String>(
    MutableStateFlow(true),
    null,
    { value = it },
    { it },
    MutableStateFlow(initialValue),
    MutableStateFlow(null),
    FocusRequester(),
    {it},
)