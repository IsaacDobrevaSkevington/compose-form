# Checkbox

The form checkbox field is a `Boolean` type field. It should present the 
user with a checkbox, which can be either checked, or unchecked.

- The checked state corresponds to a value of `true`
- The unchecked state corresponds to a value of `false`

## Validation

The `stringRepresenation` received by any validators will be `"true"` or `"false"`

### Common Validators

- `MustBeTickedValidator`

## Default usage

The default implementation of the checkbox consists of a Row with a checkbox, and `hint` text to the right of the box.
Any error will appear below the box in the format described by `errorDisplay`.

Along with [common fields](Common.md) the default checkbox field includes multiple styling
options:


| Field            | Type                                            | Default                                      | Description                                                                                                                                                                                                             | Notes                                                                                            |
|------------------|-------------------------------------------------|----------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------|
| hint             | Any?                                            | `null`                                       | The hint field is displayed continuously no matter the value of the form field. It should give the user a prompt as to what they are ticking                                                                            | Will be converted to String using `asDisplayString`                                              |
| modifier         | Modifier                                        | `Modifier.fillMaxWidth()`                    | Modifier to apply to the whole checkbox field.                                                                                                                                                                          |                                                                                                  |
| checkboxModifier | Modifier                                        | `Modifier.minimumInteractiveComponentSize()` | Modifier to apply to the tick box of the checkbox.                                                                                                                                                                      |                                                                                                  |
| textModifier     | Modifier                                        | `Modifier.minimumInteractiveComponentSize()` | Modifier to apply to the text part of the checkbox                                                                                                                                                                      |                                                                                                  |
| errorDisplay     | @Composable FormBox<*, Boolean>.(error: String) | `StandardErrorDisplay`                       | The display the error should use - appears under the checkbox layout                                                                                                                                                    | Error to display is received as a parameter, FormBox is provided as the received for ease of use |