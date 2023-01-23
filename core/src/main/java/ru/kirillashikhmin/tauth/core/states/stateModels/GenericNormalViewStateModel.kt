package ru.kirillashikhmin.tauth.core.states.stateModels

import ru.kirillashikhmin.tauth.core.states.viewState.BaseStateModel
import ru.kirillashikhmin.tauth.core.states.viewState.StateVariant

open class GenericNormalViewStateModel<T : Any>(val data : T) : BaseStateModel(StateVariant.Normal)
