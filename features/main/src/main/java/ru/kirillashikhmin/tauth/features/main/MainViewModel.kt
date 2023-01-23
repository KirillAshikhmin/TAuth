package ru.kirillashikhmin.tauth.features.main

import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.viewmodel.container
import ru.kirillashikhmin.tauth.core.states.StatefullOrbitViewModel
import ru.kirillashikhmin.tauth.core.states.stateModels.LoadingViewStateModel
import ru.kirillashikhmin.tauth.core.states.stateModels.NoDataViewStateModel
import ru.kirillashikhmin.tauth.core.states.viewState.IStateModel
import javax.inject.Inject


sealed class MainSideEffect {
    data class Toast(val text: String) : MainSideEffect()
}


@HiltViewModel
class MainViewModel  @Inject constructor(): StatefullOrbitViewModel<MainState, MainSideEffect>(initialState) {

    companion object {
        private val initialState = LoadingViewStateModel()
    }


    override fun createNormalState(): MainState {
        TODO("Not yet implemented")
    }
}
