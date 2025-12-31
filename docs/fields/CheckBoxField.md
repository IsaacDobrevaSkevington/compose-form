# Checkbox

The form checkbox field is a `Boolean` type field. It should present the 
user with a checkbox, which can be either checked, or unchecked.

- The checked state corresponds to a value of `true`
- The unchecked state corresponds to a value of `false`

## Validation

The `stringRepresenation` received by any validators will be `"true"` or `"false"`

A common validator to use on this type of box is the `MustBeTickedValidator` which requires that the checkbox is checked.

## Default Implementation

The default implementation of the checkbox consists of a Row with a checkbox, and `hint` text to the right of the box. 
Any error will appear below the box in the format described by `errorDisplay`.

`Modifier`s are provided for:

- The whole field composable
- The hint text
- The checkbox