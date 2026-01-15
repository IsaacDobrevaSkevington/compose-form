package com.idscodelabs.compose_form.form.fields.default.dropdown

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun <Item> LazyDropdownColumn(
    items: List<Item>,
    scope: LazyDropdownScope,
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    item: @Composable (Item) -> Unit,
) {
    if (items.size > 10) {
        LazyColumn(
            modifier = modifier.lazyDropdownColumn(scope),
            verticalArrangement = verticalArrangement,
            horizontalAlignment = horizontalAlignment,
        ) {
            items(items) {
                item(it)
            }
        }
    } else {
        Column(
            modifier = modifier,
            verticalArrangement = verticalArrangement,
            horizontalAlignment = horizontalAlignment,
        ) {
            items.forEach {
                item(it)
            }
        }
    }
}
