package ru.kirillashikhmin.tauth.core.states.viewState

import kotlinx.coroutines.CoroutineScope
import me.aartikov.sesame.property.PropertyHost

abstract class PropertyViewState(private val viewModelScope: CoroutineScope, override val state: StateVariant) : BaseStateModel(state),
    PropertyHost {

    override val propertyHostScope: CoroutineScope get() = viewModelScope

}

