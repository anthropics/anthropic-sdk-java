package com.anthropic.core

import java.net.URLEncoder

internal actual fun urlEncode(value: String): String = URLEncoder.encode(value, "UTF-8")

actual typealias PlatformInputStream = java.io.InputStream
actual typealias PlatformOutputStream = java.io.OutputStream
actual typealias PlatformCloseable = java.lang.AutoCloseable
actual typealias PlatformFuture<T> = java.util.concurrent.CompletableFuture<T>
actual typealias PlatformDuration = java.time.Duration

actual fun durationOfMinutes(minutes: Long): PlatformDuration = java.time.Duration.ofMinutes(minutes)
actual fun durationOfSeconds(seconds: Long): PlatformDuration = java.time.Duration.ofSeconds(seconds)
actual fun durationZero(): PlatformDuration = java.time.Duration.ZERO

internal fun RequestOptions.Companion.from(clientOptions: ClientOptions): RequestOptions =
    RequestOptions.builder()
        .responseValidation(clientOptions.responseValidation)
        .timeout(clientOptions.timeout)
        .build()
