package ru.kirillashikhmin.tauth.core

import android.app.Application
import androidx.fragment.app.FragmentFactory
import ru.kirillashikhmin.tauth.common.Logger
import ru.kirillashikhmin.tauth.core.services.logs.LoggerImpl

abstract class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Logger.setImplementation(LoggerImpl)
    }

}
