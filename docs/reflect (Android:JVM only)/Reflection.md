# Reflection

## Usage

The reflection part of the library provides the ability to avoid boilerplate `updateModel` and `emptyModel` functions

Instead, the `emptyModel` function is provided by finding a zero argument constructor of the type provided to the form

The `updateModel` uses the `KMutableProperty` supplied to the form field to perform updates on the model directly

Note:
- The Model must have a zero argument constructor (or all default values in constructor)
- `VirtualKProperty` must not be used
- The `KMutableProperty` must have the same type as the form field

## Extension

If you wish to extend the reflection, for example, when implementing custom form fields
You can use the `KMutableProperty.updateModel` extension in your implementation


