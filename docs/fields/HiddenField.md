# Hidden Field

It may sometimes be useful to have a hidden field in the form, 
for example to prevent a model value being overwritten,
in the case where models may be shared across many layers of the application

For this purpose, `FormHiddenField` is provided. It has 3 parameters

- `modelProperty` is the property of the model this hidden field stores
- `value` is the immutable value of the hidden field
- `updateModel` sets the value back on the model again