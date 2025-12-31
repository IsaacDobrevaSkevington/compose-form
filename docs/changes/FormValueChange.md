# Form Value Changes

One common use case in forms is being able to subscribe to form value change events.

There are multiple ways to do this in the form library

## Subscribing using an Effect in composables

To perform an action in a Composable when the value of the form changes, use `ValueChangedEffect`
This works in a similar way to `LaunchedEffect` and as such receives a context parameter to alter the scope
in which the observation takes place. Also similar to `LaunchedEffect`, subscriptions will be cancelled when this
Composable leaves the composition.

### Using value changes

The new value of the form will be provided to the function, and this function will be called
whenever the value changes. 

Note this may not be a full `value` as some of the fields may be invalid or empty.
For this reason, do not rely on any validation logic (in contrast to submission) and instead 
implement robust error handling where complex logic is required.

### Debounce

It is common that when a user is typing, the value may change very quickly.
In these cases, to avoid the `block` being called and cancelled repeatedly (note this uses `Flow.collectLatest` behind the scenes)
a `debounce` parameter is provided.

Where this is specified as >0, the `block` will only be called this number of milliseconds after the last value change. 
This allows, for example to only execute a network call using the value once the value has settled.

### Example

```kotlin
Form(::FormTextFieldExampleModel) {
    ValueChangedEffect(debounce = 500) {
        Analytics.log(key = "TextFieldSettled", value = it.value ?: "")
    }
    FormTextField(
        modelProperty = FormTextFieldExampleModel::value,
        validator = NotEmptyValidator(),
        updateModel = { value = it },
        hint = "Value"
    )
}
```

## Subscribing using state in composables

Using the `ValueChangedEffect` function can provide a handy method to execute logic in another architectural layer,
however there are cases which demand having effects on the UI itself.
Instead of having to keep state in the UI (in the form of `remember`), or ViewModel (in the form of `StateFlow`)
the value of the form can be collected directly in the composable using `collectValueAsState()`

### Using value changes

When using the `collectValueAsState()` function, the result will need to be delegated using `by`.
The composable will then recompose on each change of the value.

This can be used easily to change validations on form boxes, or have dynamic
form fields based on the value

### Example
```kotlin
// Collect the value
val value by collectValueAsState()

FormDropdownField(
    modelProperty = FormValueChangeExampleModel::option,
    validator = NotEmptyValidator(),
    updateModel = { option = it },
    options = FormValueChangeExampleOption.entries,
    hint = "Select Field Type"
)

// Use the value
when (value.option) {
    FormValueChangeExampleOption.DATE_FIELD -> {
        FormDateField(
            modelProperty = FormValueChangeExampleModel::date,
            validator = NotEmptyValidator(),
            updateModel = { date = it },
            hint = "Date"
        )
    }

    FormValueChangeExampleOption.TIME_FIELD -> {
        FormTimeField(
            modelProperty = FormValueChangeExampleModel::time,
            validator = NotEmptyValidator(),
            updateModel = { time = it },
            hint = "Time"
        )
    }
    else -> {}
}
```


## Subscribing in the ViewModel, or other asynchronous contexts

The suspend function `onValueChanged` is provided to allow subscriptions outside composable contexts.

Similar to the `ValueChangedEffect`, a debounce is provided to allow the value to settle before observing.

The value is provided as the first parameter in the callback.

One example application of this is an address search field. 
The ViewModel can observe the value changes, and then send off an API request to fetch addresses 
based on the search field value. Finally results can be provided back to the UI via the state.

Note for this to be most effective, two recommendations are provided:

- The `onValueChanged` function should be called in the viewModelScope, to ensure subscriptions are
cancelled once the ViewModel is destroyed.
- The ViewModel should implement the `FormControllerViewModel` to avoid having
to pass the `FormController` from the UI to the `ViewModel`


## Transforming the value before observation

The value can also be observed via the `valueFlow` field on the `FormController`

Then, standard `Flow` operators can be used, such as:

- `mapLatest` to transform the value
- `debounce` to allow values to settle
- `combine` to merge the value with other running flows
- `filter` to only process particular values

