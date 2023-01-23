package ru.kirillashikhmin.tauth.core.states

import androidx.annotation.MainThread
import me.aartikov.sesame.property.state
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.viewmodel.container
import ru.kirillashikhmin.tauth.core.internal.BaseOrbitViewModel
import ru.kirillashikhmin.tauth.core.states.stateModels.CleanViewStateModel
import ru.kirillashikhmin.tauth.core.states.viewState.IStateModel
import ru.kirillashikhmin.tauth.core.states.viewState.StateVariant

abstract class BaseStatefullViewModel<STATE : IStateModel, SIDE_EFFECT : Any>(initialState : IStateModel) : BaseOrbitViewModel<IStateModel, SIDE_EFFECT>(initialState) {

    internal val statesMap = mutableMapOf<StateVariant, IStateModel>()

    var state by state<IStateModel>(initialState)
        private set

    override val container = container<IStateModel, SIDE_EFFECT>(initialState)

    open fun createState(variant: StateVariant): IStateModel =
        throw NotImplementedError("You're should override createState function")


    @Suppress("UNCHECKED_CAST")
    open fun <T : IStateModel> getState(variant: StateVariant): T {
        val state = if (statesMap.containsKey(variant))
            statesMap[variant] as T
        else
            createState(variant) as T
        return saveState(state)
    }

    @Suppress("UNCHECKED_CAST")
    open fun <T : IStateModel> getExistState(variant: StateVariant): T? {
        return if (statesMap.containsKey(variant))
            statesMap[variant] as T
        else
            null
    }


    fun showState(state: StateVariant) {
        if (statesMap.containsKey(state))
            this.state = statesMap[state]!!
        else throw IllegalStateException("State ${state.state} not initialized")
    }

    fun <T : IStateModel> setState(
        state: StateVariant,
        block: (T.() -> Unit)? = null
    ): T {
        val savedState = saveState<T>(getState(state))
        block?.invoke(savedState)
        applyState(savedState)
        return savedState
    }

    protected fun <T : IStateModel> saveState(viewState: T): T {
        statesMap.putIfAbsent(viewState.state, viewState)
        return viewState
    }

    private fun <T : IStateModel> applyState(viewState: T): T {
        this.state = viewState
        return viewState
    }

    @MainThread
    protected fun updateState(block: IStateModel.() -> IStateModel) {
        state = block.invoke(state)
    }

    @MainThread
    fun stateApply(block: IStateModel.() -> Unit) {
        @Suppress("UNCHECKED_CAST")
        state.apply(block)
    }

}
