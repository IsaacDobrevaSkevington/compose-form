# Annotations

## Hint


### Usage
The hint field of a form field can be specified using the `@Hint` annotation. Note this only allows string values.

### Example

```kotlin
data class MyFormModel(
    @Hint("Value")
    var value: String? = null,
)

...

Form<MyFormModel> {
    
    // Displays a Text field with the 'Value' hint
    FormTextField(MyFormModel::value)
}
```


## Validators

### Pre-implemented

Pre-implemented validators, such as `NotEmptyValidator` all have sister annotations in the library. 
You can find this annotation by removing the `Validator` part of the class name.
For example, `NotEmptyValidator` -> `@NotEmpty`


Most validation annotations take an error message, and other validation parameters where possible.
They also often specify a default error message which is the same as their class counterparts. In some cases
this may appear empty, but this is only to use `Composable` functions when they are called 
(for example the `@DateBefore` requires access to the `LocalDateFormat` LocalComposition)

#### Example

```kotlin
data class MyFormModel(

    @Hint("Value")
    @NotEmpty(error = "Value is Required")
    var value: String? = null,
)

...

Form<MyFormModel> {
    
    // Displays a Text field which cannot be empty
    FormTextField(MyFormModel::value)
}
```

### Custom - Simple

You can also use your own validators in annotations, with the `@Validator` annotation

Note that this validator needs to have an empty constructor in order to be used with the annotation


#### Example

```kotlin
data class MyFormModel(

    @Hint("Value")
    @Validator<String>(EverySecondCharOneValidator::class)
    var value: String? = null,
)

...

Form<MyFormModel> {
    
    // Displays a Text field which is validated using EverySecondCharOneValidator
    FormTextField(MyFormModel::value)
}
```

### Custom - Compose with factory

Sometimes it may be preferable to call composable code (for example to get a `CompositionLocal` or string resource)
in a validator. For this reason, a `@ComposeValidator` annotation is provided which takes a `ComposeValidatorFactory`

Similar to `@Validator` the `ComposeValidatorFactory` must have a zero argument constructor.

By implementing the `validator` function of the `ComposeValidatorFactory`, a validator can be constructed in a composable context


#### Example

```kotlin
object NotEmptyWithComposableErrorFactory: ComposeValidatorFactory<String> {
    @Composable
    override fun validator(): CoreValidator<String> = NotEmptyValidator(
        stringResource(R.string.validator_not_empty)
    )
}

data class MyFormModel(

    @Hint("Value")
    @ComposeValidator<String>(NotEmptyWithComposableErrorFactory::class)
    var value: String? = null,
)

...

Form<MyFormModel> {
    
    // Displays a Text field which cannot be empty, and displays a string resource error
    FormTextField(MyFormModel::value)
}
```

