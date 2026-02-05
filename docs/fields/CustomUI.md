# Custom UI

Custom UI can be implemented by leveraging the `implementation`
parameter of the Form Fields. This allows the following:

### Obtaining field specific data in UI

For example a list of options in a dropdown

```kotlin
FormDropdownField(
    ...
    options = <options>,
) {
    // Use options here directly from the DropdownFormBox
    options.forEach{
        Text(it.label)
    }
}
```
This allows you to write module code with a suite of your own UI components, for example using the function signature
```kotlin
fun <Item : ListDisplayable> DropdownFormBox<*, Item>.MyBrandedDropdownEntry(hint: Any?){
    // Something using options directly
}
```
without having to pass values down the Composable hierarchy


### Working with value direct from the FormBox

The `FormBox` provides methods to find the current value.

For example a custom text field could look like:

```kotlin
fun FormBox<*, TextFieldValue>.MyBrandedTextEntry(hint: Any?){
    BasicTextField(
        value = value,
        onValueChange = ::setValue,
        disabled = !enabled,
        ...Custom Styling...
    )
}
```

For convenience, the field value can also be
- Collected directly using `collectValueAsState()`
- Observed in composables using `FieldValueChangeEffect()` which works similar to `LaunchedEffect`
- Observed in ViewModels or other asynchronous code using `FormBox.onFieldValueChanged()`
- Obtained one off in synchronous non-compose code using `valueSnapshot`

### Handling validation errors

The `FormBox` also provides an observable error.

This can be obtained either by 
- Calling `error` directly, which will provide a `String` error obtained using `asDisplayString()`
- Collecting the value in the UI using `collectErrorAsState` which will provide the original `Any` returned
as an error from the `Validator`

Note that the error may be null if the field is not currently in error state.
The field can be validated at any time by calling the `validate` function,
which will automatically set the error.
The error will also be cleared whenever the value changes


### Checking if the field is enabled

If your application needs different styles for fields which are disabled,
this can be obtained using the `collectEnabledAsState()` function or `enabled` value


### Form field focus

There are many scenarios where a field may be required to 
be brought into focus during use of the application. For example,
it may be required that in a scrollable form, the box with an error 
is brought into focus on submission (see [Submission Failure](../Submission.md#failure))

For this application, the modifier `primaryFocusable` is provided. This should be 
added to the element of your field you wish to gain focus when that FormBox requests focus.
Please note in future releases the focus requester may also be used internally, so it is 
important to ensure it is implemented on custom UI implementations.
