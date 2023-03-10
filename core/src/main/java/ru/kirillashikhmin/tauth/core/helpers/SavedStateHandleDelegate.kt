package ru.kirillashikhmin.tauth.core.helpers

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import kotlin.properties.ReadOnlyProperty
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

inline fun <reified T> SavedStateHandle.delegate(key: String? = null): ReadWriteProperty<Any, T?> = object :
    ReadWriteProperty<Any, T?> {
    override fun getValue(thisRef: Any, property: KProperty<*>): T? {
        val stateKey = key ?: property.name
        return this@delegate[stateKey]
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: T?) {
        val stateKey = key ?: property.name
        this@delegate[stateKey] = value
    }
}

inline fun <reified T> SavedStateHandle.liveData(initialValue: T? = null) =
    ReadOnlyProperty<Any, MutableLiveData<T?>> { _, property ->
        val stateKey = property.name
        if (initialValue == null) {
            this@liveData.getLiveData(stateKey)
        } else {
            this@liveData.getLiveData(stateKey, initialValue)
        }
    }
