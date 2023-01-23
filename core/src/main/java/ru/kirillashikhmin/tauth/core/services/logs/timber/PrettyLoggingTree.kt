package ru.kirillashikhmin.tauth.core.services.logs.timber

import android.util.Log
import ru.kirillashikhmin.tauth.core.services.logs.PrettyLogFormatter
import timber.log.Timber

internal class PrettyLoggingTree : Timber.Tree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        val messages = PrettyLogFormatter.log(tag, message, t)
        messages.forEach { msg ->
            Log.println(priority, tag, msg)
        }
    }
}
