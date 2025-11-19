package com.idscodelabs.compose_form.form.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.ImageVector
import com.idscodelabs.compose_form.form.model.FormBox
import kotlin.reflect.KProperty

open class FormScope<Model> {
    /**
     * Icon parameters for forms
     *
     * @property icon The icon vector
     * @property rotation The rotation of the icon
     * @property onClick Action to perform when the icon is clicked
     * @constructor Create empty Icon params
     */
    class IconParams(
        val icon: ImageVector,
        val rotation: Float = 0f,
        val onClick: () -> Unit,
    )

    private val boxes: MutableMap<String, FormBox<Model>> = mutableMapOf()
    lateinit var emptyModel: () -> Model

    /**
     * Clear all the form's values and boxes
     */
    open fun clear() {
        boxes.clear()
    }

    /**
     * Clear a subset of the form's values and boxes
     *
     * @param fields The fields to clear
     */
    fun clear(vararg fields: KProperty<*>) {
        fields.forEach {
            boxes.remove(it.name)
        }
    }

    /**
     * Add a box to the form
     *
     * @param property The unique property of this form field
     */
    fun FormBox<Model>.addToForm(property: KProperty<*>) {
        boxes[property.name] = this
    }

    /**
     * Validate the form
     * @return true if the form was validated successfully, otherwise false
     *
     * This function will handle setting and clearing errors on the correct boxes
     */
    private fun validate(): List<FormBox<Model>> {
        val values = boxes.map { it.key to it.value.getFieldValue() }.toMap()
        return boxes
            .filter { (k, v) ->
                val text: String? = values[k]
                val error = v.validator?.validate(text?.trim(), values)
                v.setError(error)
                error != null
            }.map { it.value }
    }

    val value: Model
        get() {
            val updateRequest = emptyModel()
            boxes.forEach { (k, v) ->
                try {
                    val currentValue = v.getFieldValue()
                    v.setModelProperty(updateRequest, currentValue)
                } catch (e: Throwable) {
                    println(e.message ?: "Unknown error while parsing field $k")
                }
            }
            return updateRequest
        }

    open fun submit(
        onFailure: (List<FormBox<Model>>) -> Unit = {},
        onError: (Throwable) -> Unit = {},
        onSuccess: (Model) -> Unit = {},
    ) {
        try {
            val failedBoxes = validate()
            if (failedBoxes.isEmpty()) {
                onSuccess(value)
            } else {
                onFailure(failedBoxes)
            }
        } catch (e: Throwable) {
            onError(e)
        }
    }
}

@Composable
fun <Model> rememberFormScope(key: Any? = Unit): FormScope<Model> =
    remember(key) {
        FormScope()
    }
