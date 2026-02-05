# Form Submission

## Submitting the form
There are multiple helper functions to submit the form in different ways

### Simple submission

The `submit` function can be called from any location to submit the form immediately.
3 callbacks (onSuccess, onFailure, onError) are provided for the 3 different [results](#handling-the-results)

### Inline submission

To avoid callback, you can also obtain the Model directly from the return value

- `submitForModel` will return a Model if submission is successful, or throw a `FormSubmissionFailedError`
if the submission failed. Failing to catch this exception will lead to application crash
- `submitForModelOrNull` allows you to avoid having to use a try-catch block, instead returning `null` if the 
submission failed. Note that the actual exception is swallowed in this case

### UI submission

A common use case for form submission is on click of a button. In simple forms,
in order to avoid multiple levels of nesting, the `submitFunction` is provided.
This has the same signature as [`submit` with callbacks](#simple-submission) 
For example:

```kotlin
Button(
    onClick = submitFunction {
        // Do something with Model
    }
){
    Text(text = "Submit")
}

```

### Reusable submission

Often, logic may need to be reused between submissions, for example handling an error,
or scrolling to the invalid form box. For this, a `FormSubmissionResult` is provided.
Calling `submit` with no arguments will return a `FormSubmissionResult`

Using the methods of this object allows subscription to the success, failed and error events

```kotlin
Button(
    onClick = {
        submit()
            .onSuccess {
                // Do something with Model
            }.onFailure {
                it.firstOrNull()?.focusRequester?.requestFocus()
            }.onError {
                it.printStackTrace()
            }
    },
) {
    Text("Submit")
}
```

Extension methods can be built on top of this object to allow reuse of handling:

```kotlin
// FormSubmissionHandingExtensions.kt
fun <Model> FormSubmissionResult<Model>.handleErrors(success: (Model) -> Unit) =
    onSuccess(success)
        .onFailure { it.firstOrNull()?.focusRequester?.requestFocus() }
        .onError { it.printStackTrace() }

// Screen.kt
...
Button(
    onClick = { 
        submit().handleErrors {
            // Do something with Model
        }
    },
) {
    Text("Submit")
}
```

## Handling the results

### Success

The success case will always return a Model, populated with 
the result of the form submission


### Failure

This case occurs when one or more of the fields failed validation.

The list of FormBoxes which failed submission are provided:

- As a field of the thrown `FormSubmissionFailedError`
- As a parameter of the failure callback


### Error

This case occurs when the submission throws an error. For example, there is
an error in one of the validators

The `Throwable` is provided:

- As the `cause` field of the `FormSubmissionFailedError`
- As a parameter of the error callback
