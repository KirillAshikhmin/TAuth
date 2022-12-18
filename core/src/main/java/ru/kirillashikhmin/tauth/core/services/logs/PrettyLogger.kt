package ru.kirillashikhmin.tauth.core.services.logs

import android.util.Log
import ru.kirillashikhmin.tauth.common.ILoggable
import ru.kirillashikhmin.tauth.core.services.logs.PrettyLogFormatter
import timber.log.Timber


internal class PrettyLogger  : ILoggable {

    private fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        val messages = PrettyLogFormatter.log(tag, message, t)
        messages.forEach { msg ->
            Log.println(priority, tag, msg)
        }
    }

    override fun d(tag: String, message: String) {
        log(Log.DEBUG, tag, message, null)
    }

    override fun i(tag: String, message: String) {
        log(Log.INFO, tag, message, null)
    }

    override fun w(tag: String, message: String) {
        log(Log.WARN, tag, message, null)
    }

    override fun w(tag: String, message: String, throwable: Throwable) {
        log(Log.WARN, tag, message, null)
    }

    override fun e(tag: String, throwable: Throwable) {
        log(Log.ERROR, tag, "", throwable)
    }

    override fun e(tag: String, throwable: Throwable, message: String) {
        log(Log.ERROR, tag, message, throwable)
    }

    override fun e(tag: String, message: String) {
        log(Log.ERROR, tag, message, null)
    }
}
