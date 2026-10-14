@file:JvmName("TelemetryConfig")

package com.anthropic.internal.telemetry

import com.anthropic.core.ClientOptions
import com.anthropic.core.LogLevel

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
