package ru.kirillashikhmin.tauth.totp

import ru.kirillashikhmin.tauth.common.Logger
import ru.kirillashikhmin.tauth.totp.Base32.Companion.decode
import java.lang.Exception

class Totp(secret: String) {
    private var base32: ByteArray = try {
        decode(secret)
    } catch (e: Base32.DecodingException) {
        byteArrayOf()
    }

    fun generate(): String? {
        return try {
            if (base32.isEmpty()) return null
            val hash = Hmac(Hash.SHA1, base32, System.currentTimeMillis() / 1000 / 30).digest()
            val offset: Int = hash[19].toInt() and 0xf
            var truncatedHash: Long = (hash[offset].toInt() and 0x7f).toLong()
            for (i in 1..3) {
                truncatedHash = truncatedHash shl 8
                truncatedHash = truncatedHash or (hash[offset + i].toLong() and 0xff)
            }
            truncatedHash %= 1000000
            String.format("%06d", truncatedHash)
        } catch (e: Exception) {
            Logger.e("unable calcutate", e)
            null
        }
    }

    enum class Hash(private val hash: String) {
        SHA1("HMACSHA1");

        override fun toString(): String {
            return hash
        }
    }
}
