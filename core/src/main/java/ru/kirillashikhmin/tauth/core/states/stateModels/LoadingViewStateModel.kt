package ru.kirillashikhmin.tauth.core.states.stateModels

import me.aartikov.sesame.localizedstring.LocalizedString
import ru.kirillashikhmin.tauth.core.R
import ru.kirillashikhmin.tauth.core.states.viewState.BaseStateModel
import ru.kirillashikhmin.tauth.core.states.viewState.StateVariant

open class LoadingViewStateModel(val loadingText: LocalizedString = LocalizedString.resource(R.string.loading)) :
    BaseStateModel(StateVariant.Loading)

