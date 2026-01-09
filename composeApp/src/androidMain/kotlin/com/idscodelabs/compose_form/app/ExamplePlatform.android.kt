package com.idscodelabs.compose_form.app

import androidx.compose.runtime.Composable
import com.idscodelabs.compose_form.examples.reflect.annotations.FormTextFieldAnnotationExample
import com.idscodelabs.compose_form.examples.reflect.reflectionSetter.FormTextFieldReflectionExample

enum class ExamplePlatform(
    override val displayName: String,
    override val contents: @Composable () -> Unit,
) : Example {
    REFLECTION("Reflection", { FormTextFieldReflectionExample() }),
    ANNOTATIONS("Annotations", { FormTextFieldAnnotationExample() })
}

actual fun platformExamples(): List<Example> = ExamplePlatform.entries

