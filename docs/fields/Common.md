# Form Fields

## Common properties

All form fields have the following parameters

### `modelProperty`

This is the parameter in the form's model which this property relates to.
This is used behind the scenes as a unique key for the form field to be stored against,
so it is generally not advised to have 2 or more fields referring to the same property

The easiest way to provide this value is obtaining a reference to the property with
`::`, for instance:
```kotlin
SomeFormField(
    modelProperty = SomeModel::someValue
)
```

### `updateModel`

This is the function which should be used to set the 
value of the property on the Model. The incoming receiver 
is the Model which is to be updated, and the value passed
to the function is the value which needs to be set.

In most use cases, the following should suffice:
```kotlin
SomeFormField(
    updateModel = { someValue = it}
)
```

### `initialValue`

This is the initial value the form field should take.

If this changes during the lifecycle of the form field, the value will
be overwritten with the new initial value.
For this reason, any non-primitives should be data classes or override the `equals` method
explicitly to avoid the value being overwritten when the reference to the 
object changes.

### `validator`

To validate form fields, this value can be passed. 

The value is a single validator, but applying multiple validators can
be achieved by using the `+` operator, or the `and` infix function. This is to avoid
having complex nested lists of validators.

For more information, see [Form Validators](../validators/Validators.md)

### `enabled`

This determines if the field is enabled or not, and can be derived from other
state properties. For example, in a screen with a loading state, the following might
be required
```kotlin
    val uiState by viewModel.uiState.collectAsState()
    
    SomeFormField(
        enabled = !uiState.loading
    )
```

Note that the enabled value will stop values being set on the field,
as well as being provided to the implementation to allow disabling of UI components

## UI

### Default

To use the default UI, each of the Form Field functions has an overload
which also contains all the properties of the default implementation of that form field. This is stored in the
`default` package along with the default UI
For instance with a text field

```kotlin
c.i.c.f.f.core.text.FormTextField(
    modelProperty = FormTextFieldExampleModel::value,
    validator = NotEmptyValidator(),
    updateModel = { value = it },
) {
    DefaultTextEntry(hint = "Value")
}
```

is the same as

```kotlin
c.i.c.f.f.default.text.FormTextField(
    modelProperty = FormTextFieldExampleModel::value,
    validator = NotEmptyValidator(),
    updateModel = { value = it },
    hint = "Value"
)
```

(Note package names abbreviated for conciseness)

### Custom

Custom UI can be implemented to suit the needs of your project. 
For more info see [Custom UI](CustomUI.md)



