# Form Validators

Form validators can be passed into the form field on creation.
They can be dynamic (i.e. derived from state) to allow validation change
depending on current form state or external logic.

Validators are type safe (they must be used on the correct type of field), but a string representation is 
also provided in the case that the raw value needs validating.

Validators are also ordered using a `order` field. Lower values have a higher priority. 
There is no guarantee on the order 2 validators with the same `order` value will be called in.

## Built in validators

A number of common validators are provided:

- `DateAfterValidator` This checks that a date in a form box is after a specific date
- `DateBeforeValidator` This checks that a date in a form box is before a specific date
- `DateValidator` This checks that a date in a form box is in a valid format
- `EveryCharacterValidator` This checks that every character in a text-based form box matches a condition
- `InvalidOptionValidator` This checks that an item selected in a form box which has a list of discrete selectable items matches the list.
For example in a dropdown
- `MaxLengthValidator` This checks that the value in a text-based form box is not longer than a certain number of characters
- `MinLengthValidator` This checks that the value in a text-based form box is not shorter than a certain number of characters
- `MultipleValidator` This combines validators using a list, and is used in the backing of the `+` and `and` operators