package com.idscodelabs.compose_form.examples.fields.suggestion

import com.idscodelabs.compose_form.form.fields.core.base.ListDisplayable

class FormSuggestionFieldExampleOption(
    override val key: Any,
    override val label: String,
    override val position: Int,
) : ListDisplayable {
    override fun toString(): String = label

    companion object Companion {
        private const val ALPHA = "abcdefghijklmnopqrstuvwxyz"
        val options =
            ALPHA.flatMap { c1 ->
                ALPHA.flatMap { c2 ->
                    ALPHA.map { c3 ->
                        val v = "$c1$c2$c3"
                        FormSuggestionFieldExampleOption(
                            v,
                            v,
                            0,
                        )
                    }
                }
            }
    }
}
