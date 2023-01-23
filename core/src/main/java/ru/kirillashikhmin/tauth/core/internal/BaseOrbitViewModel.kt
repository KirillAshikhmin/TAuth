package ru.kirillashikhmin.tauth.core.internal

import me.aartikov.sesame.property.PropertyHost
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import ru.kirillashikhmin.tauth.core.states.viewState.IStateModel


@Suppress("UNCHECKED_CAST")
abstract class BaseOrbitViewModel<STATE : IStateModel, SIDE_EFFECT : Any>(initialState : IStateModel)  : ContainerHost<IStateModel, SIDE_EFFECT>, BaseViewModel(), PropertyHost {
    override val container: Container<IStateModel, SIDE_EFFECT> = container(initialState)
}

