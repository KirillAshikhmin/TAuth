package ru.kirillashikhmin.tauth.core.states

import ru.kirillashikhmin.tauth.core.states.viewState.IStateModel
import ru.kirillashikhmin.tauth.core.states.viewState.StateVariant

abstract class StatefullOrbitViewModel<Normal : IStateModel, SIDE_EFFECT : Any>(initialState : IStateModel) :
    BaseStatefullViewModel<Normal, SIDE_EFFECT>(initialState) {

    val normalState get() = getState<Normal>(StateVariant.Normal)

    abstract fun createNormalState(): Normal

    @Suppress("UNCHECKED_CAST")
    override fun <T : IStateModel> getState(variant: StateVariant): T {
        val state = if (statesMap.containsKey(variant)) {
            this.statesMap[variant] as T
        } else {
            when (variant) {
                StateVariant.Normal -> createNormalState() as T
                else -> createState(variant) as T
            }
        }
        return saveState(state)
    }

    fun setNormalState(block: (Normal.() -> Unit)? = null) =
        setState(StateVariant.Normal, block)
}
