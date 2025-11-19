package com.idscodelabs.compose_form

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform