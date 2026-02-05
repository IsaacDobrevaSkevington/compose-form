# Form Validation

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
- `MustBeTickedValidator` This is used for a Boolean form (most commonly Checkboxes) to validate that the value is `true`
- `NotEmptyIfOtherNotPopulatedValidator` This allows logic for at least 1 of 2 form fields to be populated
- `NotEmptyIfOtherPopulatedValidator` This allows logic for 1 field with a value mandating that another field has a value
- `NotEmptyValidator` This ensures a field is populated
- `NumberOnlyValidator` This ensures a text field only has number characters
- `RegexValidator` The ensures the value in a text field matches a regular expression
- `TimeAfterValidator` This checks that a time in a form box is after a specific date
- `TimeBeforeValidator` This checks that a time in a form box is before a specific date
- `TimeValidator` This checks that a time in a form box is in a valid format

Most of these have a custom error message which can be provided. 
This can be:

- `String`
- `StringResource`
- `StringResourceWithPlaceholders`
- `Pair<StringResource, Array<String>>`
- Any other class implementing a custom `toString` method

## Custom validators

The `Validator` interface is easy to implement for your own validation logic.

Quick inline validation can be done using the SAM constructor:

```kotlin
val validator = Validator<String> {value, _ ->
    return if(value?.split(",")?.size == 3){
        null
    } else {
        "4 Comma Separated Values Required."
    }
}
```

Alternatively validation classes can be created for reuse:

```kotlin
class CSVValidator(
    private val commaCount: Int = 3
): Validator<String> {
    override fun validate(value: String?, stringRepresentation: String?): Any? {
        return if(value?.split(",")?.size == commaCount){
            null
        } else {
            "${commaCount + 1} Comma Separated Values Required."
        }
    }
}
```

The `value` parameter will contain the value which the form field currently holds, in a type safe way.
For example, a field which has type `LocalDate` will pass this value as the date in the box, if it is in a valid format (e.g. 01/01/2000)
or null otherwise (e.g. 30/30/2000 or 30/05/)

The `stringRepresentation` parameter will contain the raw string value of the field. This must be parsed carefully to ignore
errors if the validator wishes to use the raw format. For example, a date text field validator could
use this to ensure that the format of the date is correct before passing it down to specific value validators.

## Manual Validation

Usually, the form validation will be called on submission of the form, and error messages set automatically.
However, sometimes it may be preferable to validate the form box (and hence display the error) before submission has been performed.

For example, an application may wish to display validation as soon as the box is populated so the user is aware that their current input is invalid.
This can be achieved using form field value change subscription, combined with the `FormBox` `validate` function:

```kotlin
FormTextField(
    modelProperty = FormTextFieldExampleModel::value,
    validator = NumberOnlyValidator(),
    updateModel = { value = it },
) {
    FieldValueChangedEffect {
        if(text.isNotEmpty()){
            validate()
        }
    }
    DefaultTextEntry(hint = "Value")
}
```

Similarly, the entire form can be validated with the `validate` function on the `FormController`





