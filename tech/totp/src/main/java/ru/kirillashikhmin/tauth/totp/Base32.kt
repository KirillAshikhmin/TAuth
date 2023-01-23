package ru.kirillashikhmin.tauth.totp

import java.lang.Exception
import java.lang.StringBuilder
import java.security.SecureRandom
import java.util.*
import kotlin.experimental.and

/**
 * Encodes arbitrary byte arrays as case-insensitive base-32 strings.
 *
 *
 * The implementation is slightly different than in RFC 4648. During encoding,
 * padding is not added, and during decoding the last incomplete chunk is not
 * taken into account. The result is that multiple strings decode to the same
 * byte array, for example, string of sixteen 7s ("7...7") and seventeen 7s both
 * decode to the same byte array.
 * TODO(sarvar): Revisit this encoding and whether this ambiguity needs fixing.
 *
 * @author sweis@google.com (Steve Weis)
 * @author Neal Gafter
 */
internal class Base32 private constructor(
    // 32 alpha-numeric characters.
    private val ALPHABET: String
) {
    companion object {
        private const val SECRET_SIZE = 10
        private val RANDOM = SecureRandom()
        val instance = Base32("ABCDEFGHIJKLMNOPQRSTUVWXYZ234567") // RFC 4648/3548
        const val SEPARATOR = "-"

        @JvmStatic
        @Throws(DecodingException::class)
        fun decode(encoded: String): ByteArray {
            return instance.decodeInternal(encoded)
        }

        fun encode(data: ByteArray): String {
            return instance.encodeInternal(data)
        }

        fun random(): String {

            // Allocating the buffer
            val buffer = ByteArray(SECRET_SIZE)

            // Filling the buffer with random numbers.
            RANDOM.nextBytes(buffer)

            // Getting the key and converting it to Base32
            val secretKey = Arrays.copyOf(buffer, SECRET_SIZE)
            return encode(secretKey)
        }
    }


    private val DIGITS: CharArray
    private val MASK: Int
    private val SHIFT: Int
    private val CHAR_MAP: HashMap<Char, Int>

    init {
        DIGITS = ALPHABET.toCharArray()
        MASK = DIGITS.size - 1
        SHIFT = Integer.numberOfTrailingZeros(DIGITS.size)
        CHAR_MAP = HashMap()
        for (i in DIGITS.indices) {
            CHAR_MAP[DIGITS[i]] = i
        }
    }

    @Throws(DecodingException::class)
    protected fun decodeInternal(encoded: String): ByteArray {
        // Remove whitespace and separators
        var encoded = encoded
        encoded = encoded.trim { it <= ' ' }
            .replace(SEPARATOR.toRegex(), "").replace(" ".toRegex(), "")

        // Remove padding. Note: the padding is used as hint to determine how many
        // bits to decode from the last incomplete chunk (which is commented out
        // below, so this may have been wrong to start with).
        encoded = encoded.replaceFirst("[=]*$".toRegex(), "")

        // Canonicalize to all upper case
        encoded = encoded.uppercase()
        if (encoded.length == 0) {
            return ByteArray(0)
        }
        val encodedLength = encoded.length
        val outLength = encodedLength * SHIFT / 8
        val result = ByteArray(outLength)
        var buffer = 0
        var next = 0
        var bitsLeft = 0
        for (c in encoded.toCharArray()) {
            if (!CHAR_MAP.containsKey(c)) {
                throw DecodingException("Illegal character: $c")
            }
            buffer = buffer shl SHIFT
            buffer = buffer or (CHAR_MAP[c]!! and MASK)
            bitsLeft += SHIFT
            if (bitsLeft >= 8) {
                result[next++] = (buffer shr bitsLeft - 8).toByte()
                bitsLeft -= 8
            }
        }
        // We'll ignore leftover bits for now.
        //
        // if (next != outLength || bitsLeft >= SHIFT) {
        //  throw new DecodingException("Bits left: " + bitsLeft);
        // }
        return result
    }

    private fun encodeInternal(data: ByteArray): String {
        if (data.isEmpty()) {
            return ""
        }

        // SHIFT is the number of bits per output character, so the length of the
        // output is the length of the input multiplied by 8/SHIFT, rounded up.
        // The computation below will fail, so don't do it.
        require(data.size < 1 shl 28)
        val outputLength = (data.size * 8 + SHIFT - 1) / SHIFT
        val result = StringBuilder(outputLength)
        var buffer = data[0].toInt()
        var next = 1
        var bitsLeft = 8
        while (bitsLeft > 0 || next < data.size) {
            if (bitsLeft < SHIFT) {
                if (next < data.size) {
                    buffer = buffer shl 8
                    buffer = buffer or (data[next++].toInt() and 0xff)
                    bitsLeft += 8
                } else {
                    val pad = SHIFT - bitsLeft
                    buffer = buffer shl pad
                    bitsLeft += pad
                }
            }
            val index = MASK and (buffer shr bitsLeft - SHIFT)
            bitsLeft -= SHIFT
            result.append(DIGITS[index])
        }
        return result.toString()
    }

    class DecodingException(message: String?) : Exception(message)

}
