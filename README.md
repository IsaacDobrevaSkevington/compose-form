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

```kotlin 
dependencies{
    ...
    implementation("io.github.idscodelabs:compose-form:$version")
}
```

#### Multiplatform
```kotlin 
commonMain.dependencies{
    ...
    implementation("io.github.idscodelabs:compose-form:$version")
}
```

#### Version catalog

##### TOML
```toml
[versions]
compose-form = "<version>"

[libraries]
compose-form = { module = "io.github.idscodelabs:compose-form", version.ref = "compose-form" }
```

##### build.gradle.kts
```kotlin
commonMain.dependencies{
    ...
    implementation(libs.compose.form)
}
```

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
        hint = "Value"
    )
    
    // Submit the form
    Button(onClick = submitFunction {
        // Do something with the result
    }) {
        Text("Submit")
    }
}

```

### [<u>Documentation</u>](https://isaacdobrevaskevington.github.io/compose-form/)
### [<u>Changelog</u>](./CHANGELOG.md)





