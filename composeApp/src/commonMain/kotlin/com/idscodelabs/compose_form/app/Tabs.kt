package com.idscodelabs.compose_form.app

enum class Tabs(
    val displayName: String,
    val examples: List<Example>
){
    FIELDS("Fields", ExampleField.entries),
    VALIDATORS("Validators", ExampleValidator.entries),
    PLATFORM("Platform", platformExamples()),

}