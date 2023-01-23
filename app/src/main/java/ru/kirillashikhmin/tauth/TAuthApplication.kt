package ru.kirillashikhmin.tauth

import dagger.hilt.android.HiltAndroidApp
import ru.kirillashikhmin.tauth.common.Logger
import ru.kirillashikhmin.tauth.core.BaseApplication

@HiltAndroidApp
class TAuthApplication : BaseApplication() {

    override fun onCreate() {
        super.onCreate()
        val tag = "TEST LOG"
        Logger.i(tag, "TEST\nAAA")
        Logger.d(tag, "TEST\nAAA")
        Logger.w(tag, "TEST\nAAA")
        Logger.e(tag, IllegalArgumentException("Something went wrong"),"TEST\nAAA")
    }
}
