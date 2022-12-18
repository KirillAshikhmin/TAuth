package ru.kirillashikhmin.tauth.core.helpers

import android.animation.Animator
import android.app.Activity
import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import android.view.View
import androidx.annotation.IdRes
import androidx.lifecycle.LifecycleCoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import me.aartikov.sesame.localizedstring.LocalizedString
import ru.kirillashikhmin.tauth.common.Logger
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.nio.charset.Charset
import java.util.*

fun <T> Flow<T>.onEachInScope(scope: LifecycleCoroutineScope, action: suspend (T) -> Unit) {
    scope.launchWhenStarted {
        this@onEachInScope.onEach(action).launchIn(scope)
    }
}


fun InputStream.readTextAndClose(charset: Charset = Charsets.UTF_8): String {
    return this.bufferedReader(charset).use { it.readText() }
}

val Int.localized: LocalizedString
    get() = LocalizedString.resource(this)

fun Int.localized(vararg args: Any): LocalizedString = LocalizedString.resource(this, *args)

val CharSequence.localized: LocalizedString
    get() = LocalizedString.raw(this)

val LocalDateTime.Companion.current: LocalDateTime
    get() = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())

val Uri.extension: String
    get() = path!!.substringAfterLast('.', "")

val Uri.fileName: String
    get() = path!!.substringAfterLast('/', "")

val File.fileName: String
    get() = absolutePath.substringAfterLast('/', "")

fun Uri.getOriginalFileName(context: Context): String? {
    return try {
        context.contentResolver.query(this, null, null, null, null)?.use {
            val nameColumnIndex = it.getColumnIndex(OpenableColumns.DISPLAY_NAME)
            it.moveToFirst()
            it.getString(nameColumnIndex)
        }
    } catch (t: Throwable) {
        path!!.substringAfterLast('/', "")
    }
}


fun File.writeInputString(ins: InputStream) {
    try {
        FileOutputStream(this).use { os ->
            val buffer = ByteArray(4096)
            var length: Int
            while (ins.read(buffer).also { length = it } > 0) {
                os.write(buffer, 0, length)
            }
            os.flush()
        }
    } catch (ex: java.lang.Exception) {
        Logger.e("Save File", ex)
        ex.printStackTrace()
    } finally {
        ins.close()
    }
}

fun <E> Iterable<E>.replace(old: E, new: E) = map { if (it == old) new else it }
