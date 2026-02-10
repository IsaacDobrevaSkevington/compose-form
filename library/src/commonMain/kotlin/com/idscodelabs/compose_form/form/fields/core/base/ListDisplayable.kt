package com.idscodelabs.compose_form.form.fields.core.base

import com.idscodelabs.compose_form.form.fields.strings.asDisplayString

/**
 * Interface for objects that can be displayed in a list such as a picker or spinner.
 *
 * @see com.idscodelabs.compose_form.form.fields.core.dropdown.FormDropdownField
 * @see com.idscodelabs.compose_form.form.fields.core.radio.FormRadioField
 */

interface ListDisplayable {
    /**
     * The key of the [ListDisplayable]. This should be unique to this instance, such that
     * `l1.key == l2.key` is equal to `l1 == l2`
     */
    val key: Any

    /**
     * The position of the [ListDisplayable] in a list in the UI. Does not need to be unique.
     *
     * Items with the same position will retain their original position relative to each other in the
     * options list.
     */
    val position: Int get() = 0

    /**
     * The label for the item. This is of type [Any], such that [asDisplayString] can convert it to
     * a [String]
     */
    val label: Any
}

private data class ListDisplayableImpl(
    override val key: Any,
    override val label: Any,
    override val position: Int,
): ListDisplayable

fun ListDisplayable(
    key: Any,
    label: Any,
    position: Int = 0,
): ListDisplayable = ListDisplayableImpl(
    key = key,
    label = label,
    position = position
)
