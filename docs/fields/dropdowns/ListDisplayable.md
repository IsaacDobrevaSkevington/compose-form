# List Displayable

The `ListDisplayable` interface is key to many of the item selection form fields.

This allows items to be selected in the UI, without the need for the implementing application to 
decode the visible string or have overly simplified items

## Required fields

### `key`

The key of the item is used to identify the selected item within the list of available items. This key
must be unique in order to work correctly. However, the key is of `Any` type, allowing the use of

- Integers - for example constructing items using a `forEachIndexed` loop, or the `ordinal` field of an enum
- Strings - for example where strings displayed to the user are unique
- Enums - for example where items may each be a different type
- A mix of types, for example where multiple different items are being combined in a selection

### `label`

This is the label for the item which will be displayed to the user.
For example, a row in a dropdown, or the content next to a radio button.

As with most displayable text in the form library, this is of type `Any` and is converted to 
a user displayable string with `asDisplayString`, so can be a plain `String`, `StringResource`, or any other
type implementing a user readable `toString` method

While it is recommended that this be unique, for best UX, there may be
use cases where the same label is required for different items.
For example where there might be groups of items in a dropdown, and items in different groups have the same label.

### `position`

This is optionally overridable, and if not overriden,
the order of items will default to the order they are provided in an options list.

Otherwise, items with a lower position will appear higher in the selection list, and options
with the same position will appear in the same order relative to one another as in the 
options list.


## On-the-fly construction

Sometimes, an application may wish not to have a class implementing the `ListDisplayable` interface,
for this purpose, the `ListDisplayable` function is available. This allows for on-the-fly construction
of `ListDisplayable` objects, without the need for a specific backing class.

The implementation provided is a data class which takes `key`, `label` and `position` into account 
for equality. This means recompositions of composables using the result of this function will only be triggered
if one of these changes.