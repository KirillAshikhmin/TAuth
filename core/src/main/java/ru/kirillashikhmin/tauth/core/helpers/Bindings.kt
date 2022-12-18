package ru.kirillashikhmin.tauth.core.helpers

import me.aartikov.sesame.property.PropertyHost


fun PropertyHost.reverseCommand(isEnabled: Boolean = true, action: suspend () -> Unit) =
    CoroutineCommand(action, this.propertyHostScope, isEnabled)

fun <T> PropertyHost.reverseCommandValue(action: suspend (T) -> Unit) =
    CoroutineCommandValue(action, this.propertyHostScope)
