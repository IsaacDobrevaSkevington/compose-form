# Setup

The reflect part of library provides easier ways of calling form fields when working solely in JVM or Android
Two functionalities are added

- [Reflection](./reflection.md) in form fields to allow the default field setter to be called instead of having the boilerplate
`updateModel = {value = it}` in every form field. The only caveat is properties need to be of type `KMutableProperty` with a specific type
as opposed to any property. For this reason, use of `VirtualKProperty` is strongly discouraged
- Model level [annotations](./annotations.md) for validation and hints. This reduces the need for specifying validators in the UI
leading to cleaner code.

## Adding the deoendency

#### build.gradle.kts

```kotlin 
dependencies{
    ...
    implementation("io.github.idscodelabs:compose-form-reflect:$version")
}
```

#### Version catalog

##### TOML
```toml
[versions]
compose-form = "<version>"

[libraries]
compose-form = { module = "io.github.idscodelabs:compose-form-reflect", version.ref = "compose-form" }
```
