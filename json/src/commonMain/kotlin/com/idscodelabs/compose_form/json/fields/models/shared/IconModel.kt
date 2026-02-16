package com.idscodelabs.compose_form.json.fields.models.shared

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorProducer
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable


interface IconComposable{

    val name: String

    @Composable
    fun Render(contentDescription: String?, tint: ColorProducer?)
}

data class AvailableIcons(
    val iconList: List<IconComposable>
)

val LocalIcons = compositionLocalOf { AvailableIcons(emptyList()) }

@Serializable
class IconModel(
    val icon: String,
    val tint: String? = null,
    val contentDescription: String? = null
) {

    @Composable
    fun Render(){
        val icon = LocalIcons.current.iconList.firstOrNull { it.name == icon }
        icon?.let {
            it.Render(contentDescription, tint?.toLong(radix = 16)?.let{t-> {Color(t)}})
        }
    }



}