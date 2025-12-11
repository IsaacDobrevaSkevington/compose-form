package com.idscodelabs.compose_form.form.fields.default.dropdown

import com.idscodelabs.compose_form.form.fields.core.base.ListDisplayable
import com.idscodelabs.compose_form.validators.core.Validator
import kotlin.reflect.KProperty

/**
 * A dropdown form field
 *
 * @param Model The Model of the form
 * @param modelProperty The [KProperty] of the [Model] which this field updates.
 * @param initialValue The initial value for this field
 * @param enabled Whether the field is enabled
 * @param validator The [Validator] for this field
 * @param updateModel Function called which should set the correct property on the model. Most often should be `{ <variable> = it }`
 * @param implementation The implementation of the form field UI
 * @param invalidOptionError The error to be displayed if an invalid option is entered into the box
 * @see [ListDisplayable]
 * @sample com.idscodelabs.compose_form.examples.fields.dropdown.large.FormDropdownFieldExampleLarge
 * @sample com.idscodelabs.compose_form.examples.fields.dropdown.large.FormAutocompleteDropdownFieldExampleLarge
 */
/*
@Composable
fun <Model, Item : ListDisplayable> FormScope<Model>.FormDropdownField(
    modelProperty: KProperty<*>,
    updateModel: Model.(Item?) -> Unit,
    options: List<Item>,
    initialValue: Item? = null,
    validator: Validator<Item>? = null,
    enabled: Boolean = true,
    invalidOptionError: Any = "Invalid Option",
    textFieldModifier: Modifier = Modifier.fillMaxWidth(),
    exposedDropdownBoxModifier: Modifier = Modifier,
    exposedDropdownMenuModifier: Modifier = Modifier,
    hint: Any? = null,
    placeholder: Any? = hint,
    isLast: Boolean = false,
    leadingIcon: (@Composable DropdownFormBox<Model, Item>.() -> Unit)? = null,
    clearIcon: (DropdownFormBox<Model, Item>.(onClick: () -> Unit) -> IconParams)? = {
        IconParams(
            Icons.Filled.Close,
            onClick = it,
        )
    },
    expandIcon: DropdownFormBox<Model, Item>.(expanded: Boolean) -> IconParams = {
        IconParams(
            Icons.Filled.ArrowDropDown,
            if (it) 180f else 0f,
        ) {}
    },
    menuItem: @Composable DropdownFormBox<Model, Item>.(item: DisplayableOption<Item>, setExpanded: (Boolean) -> Unit) -> Unit = {item, setExpanded ->
        DefaultDropdownMenuItem(item, setExpanded)
    },
) = FormDropdownField(
    modelProperty = modelProperty,
    updateModel = updateModel,

)
*/
