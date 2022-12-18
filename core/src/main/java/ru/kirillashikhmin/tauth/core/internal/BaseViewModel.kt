package ru.kirillashikhmin.tauth.core.internal

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ru.kirillashikhmin.tauth.core.ui.dialogs.DialogConductor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import me.aartikov.sesame.property.PropertyHost

abstract class BaseViewModel : ViewModel(), PropertyHost {

    override val propertyHostScope: CoroutineScope get() = viewModelScope
    private var isInitialized: Boolean = false

    val dialog = DialogConductor(viewModelScope)


    var navigationParams: Map<String, Any> = mapOf()
    var resultKey: String? = null

    fun runInScope(run: suspend () -> Unit) {
        viewModelScope.launch {
            run.invoke()
        }
    }

    fun navigateBack() {
        close(true)
    }

    fun onViewCreated() {
        viewCreated()
        if (!isInitialized) {
            isInitialized = true
            onInitialized()
            viewModelScope.launch { loadData() }
        }
    }

    fun reloadData() {
        viewModelScope.launch { loadData() }
    }

    open fun onInitialized() {}
    open suspend fun loadData() {}
    open fun viewCreated() {}
    open fun viewClosed() {}

    fun resultReceived(result: Map<String, Any>) {
        onResultReceived(result)
    }

    open fun onResultReceived(result: Map<String, Any>) {

    }

    open fun close(back: Boolean = false): Boolean {
        return true
    }
}

