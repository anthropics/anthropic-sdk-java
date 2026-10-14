@file:JvmName("TelemetryConfig")

package com.anthropic.internal.telemetry

import com.anthropic.core.ClientOptions
import com.anthropic.core.LogLevel
import com.anthropic.core.opentelemetry.OpenTelemetryContentMode

/** Whether the spans of [clientOptions] hold the content of the requests and responses. */
@JvmSynthetic
internal fun recordsContent(clientOptions: ClientOptions): Boolean =
    clientOptions.openTelemetry.traces().contentMode() == OpenTelemetryContentMode.CONTENT

/**
 * The most that the content attributes of one span of [clientOptions] hold together: its
 * `maxContentBytes`, or [CONTENT_LIMIT] when it sets none, in bytes of UTF-8. The content's size is
 * counted as an [Int], so a larger bound is no bound.
 */
@JvmSynthetic
internal fun contentLimit(clientOptions: ClientOptions): Int =
    clientOptions.openTelemetry
        .traces()
        .maxContentBytes()
        .map { minOf(it, Int.MAX_VALUE.toLong()).toInt() }
        .orElse(CONTENT_LIMIT)

/**
 * Reports, at the `debug` log level, that recording telemetry failed with [error]. The SDK catches
 * such a failure so that it never fails the operation being recorded. Only the error's class is
 * logged: its message can quote the request or the response, which the application may not have
 * opted into recording.
 */
@JvmSynthetic
internal fun logTelemetryFailure(clientOptions: ClientOptions, message: String, error: Exception) {
    if (clientOptions.logLevel.shouldLog(LogLevel.DEBUG)) {
        System.err.println("$message: ${error.javaClass.name}")
    }
}
