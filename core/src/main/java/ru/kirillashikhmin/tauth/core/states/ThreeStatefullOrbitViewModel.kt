package ru.kirillashikhmin.tauth.core.states

import ru.kirillashikhmin.tauth.core.states.viewState.IStateModel
import ru.kirillashikhmin.tauth.core.states.viewState.StateVariant

abstract class ThreeStatefullOrbitViewModel<Normal : IStateModel, Loading : IStateModel, Error : IStateModel, SIDE_EFFECT : Any>(initialState : IStateModel) :
    StatefullOrbitViewModel<Normal, SIDE_EFFECT>(initialState) {

    val loadingState get() = getState<Loading>(StateVariant.Loading)
    val errorState get() = getState<Error>(StateVariant.Error)

    abstract fun createLoadingState(): Loading
    abstract fun createErrorState(): Error


    fun setLoadingState(block: (Loading.() -> Unit)? = null) =
        setState(StateVariant.Loading, block)

    fun setErrorState(block: (Error.() -> Unit)? = null) =
        setState(StateVariant.Error, block)

    @Suppress("UNCHECKED_CAST")
    override fun <T : IStateModel> getState(variant: StateVariant): T {
        val state = if (statesMap.containsKey(variant)) {
            this.statesMap[variant] as T
        } else {
            when (variant) {
                StateVariant.Loading -> createLoadingState() as T
                StateVariant.Error -> createErrorState() as T
                else -> super.getState(variant)
            }
        }
        return saveState(state)
    }

}
