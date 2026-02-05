# Date field

The form date field. Depending on configuration, this allows typing in a box and selecting a date from a calendar dialog

## Validation

The `stringRepresenation` received by any validators will be the current value of the text field

The `value` will be `null` if the current input cannot be parsed using the `LocalDateFormat.current`, 
or the parsing result if a valid date is in the text field

### Common Validators

- `DateBeforeValidator`
- `DateAfterValidator`

## Default usage

The default implementation of the date field consists of a [text field](./TextField.md) with a calendar button
as the trailing icon. Clicking the calendar will bring up a calendar picker.

Along with [common fields](Common.md) the default date field includes multiple styling
options:


| Field              | Type                                                                  | Default                   | Description                                                                                                                                                                                                             | Notes                                                                                       |
|--------------------|-----------------------------------------------------------------------|---------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------|
| invalidDateMessage | Any                                                                   | Invalid date format       | The error to display if a user has entered an invalid date into the box                                                                                                                                                 | Will be converted to String using `asDisplayString`                                         | 
| cleanDate          | (String) -> String                                                    | `sanitizeDate`            | Transform to apply to the incoming text field value to format it as a date                                                                                                                                              | The default will try to format the date as 'dd/MM/yyyy' by adding '/' in the correct places | 
| hint               | Any?                                                                  | `null`                    | The hint field is displayed continuously no matter the value of the form field. It should give the user a prompt as to what to enter                                                                                    | Will be converted to String using `asDisplayString`                                         |
| modifier           | Modifier                                                              | `Modifier.fillMaxWidth()` | Modifier to apply to the date field.                                                                                                                                                                                    |                                                                                             |
| placeholder        | Any?                                                                  | `null`                    | Value to display in the field if no value is populated.                                                                                                                                                                 | Will be converted to String using `asDisplayString`                                         |
| trailingIcon       | (@Composable ()->Unit)?                                               | `null`                    | Icon to display at the end of the form field.                                                                                                                                                                           |                                                                                             |
| isLast             | Boolean                                                               | `false`                   | If this text field is the last field in the form in the UI. When this is passed as false, an IME action on the keyboard will allow advance to the next text based field, otherwise a done IME action will be displayed. |                                                                                             |
| datePickerState    | DatePickerState                                                       | `rememberDatePickerState` | State of a date picker dialog to allow state hoisting                                                                                                                                                                   |                                                                                             |
| allowTyping        | Boolean                                                               | `true`                    | If true, the box allows typing, otherwise if false, dates can only be selected using the dialog                                                                                                                         |                                                                                             |
| entry              | @Composable FormBox<*, TextFieldValue>.(DatePickerController) -> Unit | `DefaultTextEntry`        | The entry box to use for the field                                                                                                                                                                                      |                                                                                             |
| dialog             | @Composable FormBox<*, TextFieldValue>.(DatePickerController) -> Unit | `DefaultDatePickerDialog` | The dialog to use when picking dates                                                                                                                                                                                    |                                                                                             |
