package ru.kirillashikhmin.tauth.core.states.viewState

abstract class BaseStateModel(override val state: StateVariant) : IStateModel() {
    override fun toString() = "State: $state"
}

