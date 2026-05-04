package com.anthropic.core.auth

import java.time.Duration
import java.time.Instant

internal class AccessToken(
    @get:JvmName("token") val token: String,
    @get:JvmName("expiresAt") val expiresAt: Instant? = null,
) {
    fun isExpired(): Boolean = expiresAt?.isBefore(Instant.now()) ?: false

    fun expiresWithin(duration: Duration): Boolean {
        val expiry = expiresAt ?: return false
        return expiry.isBefore(Instant.now().plus(duration))
    }
}
