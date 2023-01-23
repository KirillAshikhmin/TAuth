package ru.kirillashikhmin.tauth.core.services.logs

import timber.log.Timber
import ru.kirillashikhmin.tauth.common.ILoggable
import ru.kirillashikhmin.tauth.core.services.logs.timber.CrashReportingTree
import ru.kirillashikhmin.tauth.core.services.logs.timber.PrettyLoggingTree

internal class TimberLogger : ILoggable {
    companion object {
        init {
            Timber.plant(CrashReportingTree())
            Timber.plant(PrettyLoggingTree())
        }
    }

    override fun d(tag: String, message: String) {
        Timber.tag(tag).d(message)
    }

    override fun i(tag: String, message: String) {
        Timber.tag(tag).i(message)
    }

    override fun w(tag: String, message: String) {
        Timber.tag(tag).w(message)
    }

    override fun w(tag: String, message: String, throwable: Throwable) {
        Timber.tag(tag).w(throwable, message)
    }

    override fun e(tag: String, throwable: Throwable) {
        Timber.tag(tag).e(throwable)
    }

    override fun e(tag: String, throwable: Throwable, message: String) {
        Timber.tag(tag).e(throwable, message)
    }

    override fun e(tag: String, message: String) {
        Timber.tag(tag).e(message)
    }
}
