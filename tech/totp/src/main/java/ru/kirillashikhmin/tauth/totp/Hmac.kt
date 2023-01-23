package ru.kirillashikhmin.tauth.totp

import java.nio.ByteBuffer
import java.security.InvalidKeyException
import java.security.NoSuchAlgorithmException
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec


internal class Hmac(
    private val hash: Totp.Hash,
    private val secret: ByteArray,
    private val currentInterval: Long,
) {
    val ALGORITHM = "RAW"

    @Throws(NoSuchAlgorithmException::class, InvalidKeyException::class)
    fun digest(): ByteArray {
        val challenge = ByteBuffer.allocate(8).putLong(currentInterval).array()
        val mac = Mac.getInstance(hash.toString())
        val macKey = SecretKeySpec(secret, ALGORITHM)
        mac.init(macKey)
        return mac.doFinal(challenge)
    }
}
