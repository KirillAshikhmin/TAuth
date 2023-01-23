package ru.kirillashikhmin.tauth.core.states.stateModels

import androidx.annotation.DrawableRes
import me.aartikov.sesame.localizedstring.LocalizedString
import ru.kirillashikhmin.tauth.core.R
import ru.kirillashikhmin.tauth.core.states.viewState.BaseStateModel
import ru.kirillashikhmin.tauth.core.states.viewState.StateVariant

open class NoDataViewStateModel(
    val loadingText: LocalizedString = LocalizedString.resource(R.string.no_data),
    @DrawableRes val noDataImage: Int = com.google.android.material.R.drawable.abc_list_focused_holo,
) :
    BaseStateModel(StateVariant.NoData)
