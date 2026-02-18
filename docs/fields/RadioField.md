# Radio Group

The radio group field allows the user to select a value from a predefined list of option, 
usually where the display text is larger than would fit in a dropdown.
Radio options must implement [`ListDisplayable`](./ListDisplayable.md) to provide a displayable value.

## Validation

The `stringRepresenation` received by any validators will be the index of the current item in the options list, or -1 otherwise

The `value` will be `null` if no value is currently selected, or the `ListDisplayable` currently selected otherwise.

## Common Validators
`NotEmptyValidator`

## Default usage

Along with [common fields](../Common.md), the default radio field includes multiple
styling and behavior options:

| Field        | Type                                                                     | Default                | Description                                                          | Notes                                                                                                             |
|--------------|--------------------------------------------------------------------------|------------------------|----------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------|
| hint         | Any?                                                                     | `null`                 | The hint field is displayed                                          | Will be converted to String using `asDisplayString`                                                               |
| radioButton  | @Composable RadioFormBox<*, Item>.(DisplayableOption<Item>, Int) -> Unit | `DefaultRadioButton`   | The view for the radio button                                        | Item to display and index in list are received as parameters, FormBox is provided as the received for ease of use |
| modifier     | Modifier                                                                 | `Modifier`             | Modifier to apply to the whole checkbox field.                       |                                                                                                                   |
| textModifier | Modifier                                                                 | `Modifier`             | Modifier to apply to the text part of the checkbox                   |                                                                                                                   |
| errorDisplay | @Composable FormBox<*, Boolean>.(error: String)                          | `StandardErrorDisplay` | The display the error should use - appears under the checkbox layout | Error to display is received as a parameter, FormBox is provided as the received for ease of use                  |