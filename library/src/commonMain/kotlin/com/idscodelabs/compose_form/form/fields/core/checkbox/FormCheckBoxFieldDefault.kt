package com.idscodelabs.compose_form.form.fields.core.checkbox

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.idscodelabs.compose_form.form.core.controller.FormController
import com.idscodelabs.compose_form.form.fields.core.base.FormFieldImplementation
import com.idscodelabs.compose_form.form.fields.default.checkbox.DefaultFormCheckBoxEntry
import com.idscodelabs.compose_form.form.model.FormBox
import com.idscodelabs.compose_form.utils.StandardErrorDisplay
import com.idscodelabs.compose_form.validators.core.Validator
import kotlin.reflect.KProperty

/**
 * A checkbox form field with default implementation
 *
 * @param Model The Model of the form
 * @param modelProperty The [KProperty] of the [Model] which this field updates.
 * @param initialValue The initial value for this field
 * @param enabled Whether the field is enabled
 * @param validator The [Validator] for this field
 * @param updateModel Function called which should set the correct property on the model. Most often should be `{ <variable> = it }`
 * @param hint The hint to display next to the checkbox
 * @param modifier The [Modifier] for the whole container
 * @param checkboxModifier The [Modifier] applied to the checkbox
 * @param textModifier The [Modifier] applied to the text
 * @param errorDisplay The way the error should be displayed. Error is received as as parameter.
 * This composable will only be displayed when the FormBox error is not null
 * @see [FormFieldImplementation]
 * @see [com.idscodelabs.compose_form.form.fields.default.checkbox.DefaultFormCheckBoxEntry]
 * @sample com.idscodelabs.compose_form.examples.fields.checkbox.FormCheckBoxFieldSample
 */
@Composable
fun <Model> FormController<Model>.FormCheckBoxField(
    modelProperty: KProperty<*>,
    hint: Any? = null,
    updateModel: Model.(Boolean?) -> Unit,
    initialValue: Boolean? = null,
    validator: Validator<Boolean>? = null,
    enabled: Boolean = true,
    errorDisplay: @Composable FormBox<*, Boolean>.(error: String) -> Unit = {
        StandardErrorDisplay(it)
    },
    modifier: Modifier = Modifier.fillMaxWidth(),
    checkboxModifier: Modifier = Modifier.minimumInteractiveComponentSize(),
    textModifier: Modifier = Modifier.minimumInteractiveComponentSize(),
) = FormCheckBoxField(
    modelProperty,
    updateModel,
    initialValue,
    validator,
    enabled,
) {
    DefaultFormCheckBoxEntry(
        hint,
        modifier,
        checkboxModifier,
        textModifier,
        errorDisplay = errorDisplay,
    )
}
