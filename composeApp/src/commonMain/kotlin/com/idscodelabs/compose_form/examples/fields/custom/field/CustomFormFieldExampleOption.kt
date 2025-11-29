package com.idscodelabs.compose_form.examples.fields.custom.field

import com.idscodelabs.compose_form.form.fields.core.base.ListDisplayable

class CustomFormFieldExampleOption(
    override val key: Any,
    override val label: Any,
    override val position: Int,
) : ListDisplayable {
    override fun toString(): String = label.toString()

    companion object Companion {
        private const val ALPHA = "abcdefghijklmnopqrstuvwxyz"
        val options =
            ALPHA.flatMap { c1 ->
                ALPHA.flatMap { c2 ->
                    ALPHA.map { c3 ->
                        val v = "$c1$c2$c3"
                        CustomFormFieldExampleOption(
                            v,
                            v,
                            0,
                        )
                    }
                }
            }
    }
}
