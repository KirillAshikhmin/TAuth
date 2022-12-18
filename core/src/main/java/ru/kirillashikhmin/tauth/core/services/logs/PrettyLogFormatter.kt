package ru.kirillashikhmin.tauth.core.services.logs

/**
 * Draws borders around the given log message along with additional information
 * Big thanks to Orhan Obut for his Logger lib https://github.com/orhanobut/logger
 */
object PrettyLogFormatter {

    /**
     * Android's max limit for a log entry is ~4076 bytes,
     * so 4000 bytes is used as chunk size since default charset
     * is UTF-8
     */
    private const val CHUNK_SIZE = 4000

    /**
     * Drawing toolbox
     */
    private const val TOP_LEFT_CORNER = '┌'
    private const val BOTTOM_LEFT_CORNER = '└'
    private const val MIDDLE_CORNER = '├'
    private const val HORIZONTAL_LINE = '│'
    private const val DOUBLE_DIVIDER =
        "────────────────────────────────────────────────────────"
    private const val SINGLE_DIVIDER =
        "┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄"
    private const val TOP_BORDER = TOP_LEFT_CORNER.toString() + DOUBLE_DIVIDER + DOUBLE_DIVIDER
    private const val BOTTOM_BORDER =
        BOTTOM_LEFT_CORNER.toString() + DOUBLE_DIVIDER + DOUBLE_DIVIDER
    private const val MIDDLE_BORDER = MIDDLE_CORNER.toString() + SINGLE_DIVIDER + SINGLE_DIVIDER

    private var lineSeparator: String = System.getProperty("line.separator") ?: "\n"


    fun log(tag: String?, message: String, throwable: Throwable?): List<String> {
        val chunks = mutableListOf<String>()
        chunks.add(TOP_BORDER)
        tag?.let {
            chunks.add(makeLine(tag))
            chunks.add(MIDDLE_BORDER)
        }

        //get bytes of message with system's default charset (which is UTF-8 for Android)
        val bytes = message.toByteArray()
        val length = bytes.size
        if (length <= CHUNK_SIZE) {
            chunks.addAll(makeContentLines(message))
        } else {
            var i = 0
            while (i < length) {
                val count = (length - i).coerceAtMost(CHUNK_SIZE)
                //create a new String with system's default charset (which is UTF-8 for Android)
                chunks.addAll(makeContentLines(String(bytes, i, count)))
                i += CHUNK_SIZE
            }
        }

        if (throwable != null) {
            chunks.add(MIDDLE_BORDER)
            chunks.add(makeLine(throwable.localizedMessage ?: throwable.message ?: ""))
            chunks.addAll(makeContentLines(throwable.stackTraceToString()))
        }

        chunks.add(BOTTOM_BORDER)
        return chunks.toList()
    }

    private fun makeContentLines(chunk: String): List<String> {
        val lines = chunk.split(lineSeparator.toRegex())
        return lines.map(::makeLine)
    }

    private fun makeLine(value: String) =
        StringBuilder().append(HORIZONTAL_LINE).append(' ').append(value).toString()
}
