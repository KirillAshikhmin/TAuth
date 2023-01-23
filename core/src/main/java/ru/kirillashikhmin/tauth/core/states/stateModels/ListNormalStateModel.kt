package ru.kirillashikhmin.tauth.core.states.stateModels

import ru.kirillashikhmin.tauth.core.states.viewState.BaseStateModel
import ru.kirillashikhmin.tauth.core.states.viewState.StateVariant

open class ListNormalStateModel<T : Any>(
    open val data: List<T>,
    open val itemClick: suspend (T) -> Unit,
) : BaseStateModel(StateVariant.Normal)
