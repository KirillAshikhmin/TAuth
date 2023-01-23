package ru.kirillashikhmin.tauth.features.main

import ru.kirillashikhmin.tauth.core.states.stateModels.ListNormalStateModel

data class MainState(
    override val data: List<Account>,
    override val itemClick: suspend (Account) -> Unit,
) : ListNormalStateModel<Account>(data, itemClick)


