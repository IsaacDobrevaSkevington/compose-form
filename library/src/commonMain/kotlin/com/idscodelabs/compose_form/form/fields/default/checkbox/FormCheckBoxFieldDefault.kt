package com.idscodelabs.compose_form.form.fields.default.checkbox

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.idscodelabs.compose_form.form.core.FormScope
import com.idscodelabs.compose_form.form.fields.core.checkbox.FormCheckBoxField
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
 * @param errorModifier The [Modifier] applied to the error
 * @sample com.idscodelabs.compose_form.examples.fields.checkbox.FormCheckBoxFieldSample
 */
@Composable
fun <Model> FormScope<Model>.FormCheckBoxField(
    modelProperty: KProperty<*>,
    hint: Any,
    updateModel: Model.(Boolean?) -> Unit,
    initialValue: Boolean? = null,
    validator: Validator? = null,
    enabled: Boolean = true,
    modifier: Modifier = Modifier.fillMaxWidth(),
    checkboxModifier: Modifier = Modifier.minimumInteractiveComponentSize(),
    textModifier: Modifier = Modifier.minimumInteractiveComponentSize(),
    errorModifier: Modifier = Modifier.fillMaxWidth(),
) = FormCheckBoxField(
    modelProperty,
    updateModel,
    initialValue,
    validator,
    enabled
){
    DefaultFormCheckBoxEntry(
        hint,
        modifier,
        checkboxModifier,
        textModifier,
        errorModifier,
    )
}
