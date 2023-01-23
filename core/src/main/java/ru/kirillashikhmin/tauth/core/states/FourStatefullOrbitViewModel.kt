package ru.kirillashikhmin.tauth.core.states

import ru.kirillashikhmin.tauth.core.states.viewState.IStateModel
import ru.kirillashikhmin.tauth.core.states.viewState.StateVariant

abstract class FourStatefullOrbitViewModel<
        Normal : IStateModel,
        Loading : IStateModel,
        Error : IStateModel,
        NoData : IStateModel,
        SIDE_EFFECT : Any>(initialState : IStateModel) :
    ThreeStatefullOrbitViewModel<Normal, Loading, Error, SIDE_EFFECT>(initialState) {

    val noDataState get() = getState<NoData>(StateVariant.NoData)

    abstract fun createNoDataState(): NoData

    fun setNoDataState(block: (NoData.() -> Unit)? = null) =
        setState(StateVariant.NoData, block)

    @Suppress("UNCHECKED_CAST")
    override fun <T : IStateModel> getState(variant: StateVariant): T {
        val state = if (statesMap.containsKey(variant)) {
            this.statesMap[variant] as T
        } else {
            when (variant) {
                StateVariant.NoData -> createNoDataState() as T
                else -> super.getState(variant)
            }
        }
        return saveState(state)
    }

}
