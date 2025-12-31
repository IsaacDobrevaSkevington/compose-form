# Field Value Changes

Specific field value changes can be observed

## Obtaining the field

The reference to the `FormBox` for the field can be obtained using the `field` function of the `FormController`,
both in a Composable, and at the ViewModel layer. This takes a `KProperty` (as with the `modelProperty`) field,
which identifies the Form Field to obtain.

## Subscribing using an Effect in composables

To perform an action in a Composable when the value of the form changes, use `FieldValueChangedEffect`
This works in a similar way to `LaunchedEffect` and as such receives a context parameter to alter the scope
in which the observation takes place. Also similar to `LaunchedEffect`, subscriptions will be cancelled when this
Composable leaves the composition.

### Using field value changes

The new value of the field will be provided to the function, and this function will be called
whenever the value changes.

### Debounce

It is common that when a user is typing, the value may change very quickly.
In these cases, to avoid the `block` being called and cancelled repeatedly (note this uses `Flow.collectLatest` behind the scenes)
a `debounce` parameter is provided.

Where this is specified as >0, the `block` will only be called this number of milliseconds after the last value change. 
This allows, for example to only execute a network call using the value once the value has settled.

### Example

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

## Subscribing in the ViewModel, or other asynchronous contexts

The suspend function `onFieldValueChanged` is provided to allow subscriptions outside composable contexts.

Similar to the `FieldValueChangedEffect`, a debounce is provided to allow the value to settle before observing.

The value is provided as the first parameter in the callback.

One example application of this is an address search field. 
The ViewModel can observe the value changes, and then send off an API request to fetch addresses 
based on the search field value. Finally results can be provided back to the UI via the state.

Note for this to be most effective, two recommendations are provided:

- The `onFieldValueChanged` function should be called in the viewModelScope, to ensure subscriptions are
cancelled once the ViewModel is destroyed.
- The ViewModel should implement the `FormControllerViewModel` to avoid having
to pass the `FormController` from the UI to the `ViewModel`

### Example

```kotlin

class MyFormViewModel(
    val addressService: AddressService
): FormControllerViewModel(){
    
    val uiState: MutableStateFlow<List<AddressItem>> = MutableStateFlow(emptyList())
    
    init {
        subscribeToAddressChanges()
    }
    fun subscribeToAddressChanges() = viewModelScope.launch {
        field(MyModel::address).onFieldStringValueChanged(debounceMillis = 300) {
            uiState = addressService.search(it)
        }
    }
}
```


## Transforming the value before observation

The value can also be observed via the `valueFlow` field

Then, standard `Flow` operators can be used, such as:

- `mapLatest` to transform the value
- `debounce` to allow values to settle
- `combine` to merge the value with other running flows
- `filter` to only process particular values

