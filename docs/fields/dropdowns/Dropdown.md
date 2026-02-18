# Dropdown Field

The dropdown field allows the user to select a value from a predefined list of options.
Dropdown options must implement [`ListDisplayable`](./ListDisplayable.md) to provide a displayable value.

## Validation

The `stringRepresenation` received by any validators will be the current value of the text field

The `value` will be `null` if no value is currently selected, or the `ListDisplayable` currently selected otherwise.

If the user enters a value that does not match a valid option, an error will be displayed
using `invalidOptionError`.

## Common Validators
`NotEmptyValidator`

## Default usage


### Selection only

Along with [common fields](../Common.md), the default dropdown field includes multiple
styling and behavior options:

| Field                       | Type                                     | Default                   | Description                                                    | Notes                             |
|-----------------------------|------------------------------------------|---------------------------|----------------------------------------------------------------|-----------------------------------|
| textFieldModifier           | Modifier                                 | `Modifier.fillMaxWidth()` | Modifier applied to the text field inside the dropdown         |                                   |
| exposedDropdownBoxModifier  | Modifier                                 | `Modifier`                | Modifier applied to the overall exposed dropdown box           |                                   |
| exposedDropdownMenuModifier | Modifier                                 | `Modifier`                | Modifier applied to the dropdown menu                          |                                   |
| hint                        | Any?                                     | `null`                    | Hint displayed continuously in the text field                  | Converted using `asDisplayString` |
| placeholder                 | Any?                                     | `null`                    | Placeholder text shown when no value is selected               | Converted using `asDisplayString` |
| isLast                      | Boolean                                  | `false`                   | Whether this field is the last field in the form               | Controls IME action               |
| leadingIcon                 | (@Composable () -> Unit)?                | `null`                    | Icon displayed at the start of the text field                  |                                   |
| clearIcon                   | (@Composable () -> Unit)?                | Close Icon                | Icon allowing the user to fully clear the text field           |                                   |
| expandIcon                  | (@Composable () -> Unit)?                | Caret arrow               | Icon allowing the user to expand or collapse the dropdown menu |                                   |
| menuItem                    | (@Composable (ListDisplayable) -> Unit)? | `DefaultDropdownMenuItem` | Composable used to render each menu item                       | Requires `ListDisplayable`        |
| invalidOptionError          | Any?                                     | "Invalid Option"          | Error displayed when the user enters an invalid option         | Converted using `asDisplayString` |

### Autocompletion

The dropdown can also be used for autocomplete, by selecting the `DefaultAutocompleteFormDropdownEntry` or using `FormAutocompleteField`

The parameters are the same, except for a `filterFunction` which:

- Takes the current `item` being checked as the first parameter (as its display name)
- Takes the current `value` of the text field
- Returns `true` if the `item` should be included in the filtered dropdown, or false otherwise

This has a default implementation of `item.startsWith(value, ignoreCase = true)`