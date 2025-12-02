# Compose Form

---
A lightweight and customisable Form builder DSL for Compose Multiplatform

## Features
___

- Cross-platform support
- Build compose forms in a quick and reusable way
- Built in validation on a field and form wide level
- Access current form value statefully and at the ViewModel level
- Observe value changes
- Out of the box input fields
- Implement custom fields to match your design


## Quick Start

### Setup

#### Android

`implementation`

### Basic Usage

```kotlin

// Define your model
data class FormTextFieldExampleModel(
    var value: String? = null,
)

...

// Create the form
Form(emptyModel = ::FormTextFieldExampleModel) {
    // Add fields
    FormTextField(
        modelProperty = FormTextFieldExampleModel::value,
        validator = NotEmptyValidator(), // Add validation
        updateModel = { value = it },
    ) {
        // Use default UI, or implement your own
        DefaultTextEntry(hint = "Value")
    }
    // Submit the form
    Button(onClick = {
        submit {
            // Do something with the result
        }
    }){
        Text("Submit")
    }
}

```





