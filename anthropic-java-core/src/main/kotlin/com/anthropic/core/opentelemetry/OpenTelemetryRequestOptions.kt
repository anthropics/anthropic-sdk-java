package com.anthropic.core.opentelemetry

import java.util.Objects

/** The `openTelemetry` request option. */
class OpenTelemetryRequestOptions private constructor() {

    fun toBuilder() = Builder().from(this)

    companion object {

        private val NONE = builder().build()

        /**
         * Returns an instance of [OpenTelemetryRequestOptions] with every option at its default.
         */
        @JvmStatic fun none() = NONE

        /**
         * Returns a mutable builder for constructing an instance of [OpenTelemetryRequestOptions].
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [OpenTelemetryRequestOptions]. */
    class Builder internal constructor() {

        @JvmSynthetic
        internal fun from(openTelemetryRequestOptions: OpenTelemetryRequestOptions) = apply {}

        /**
         * Returns an immutable instance of [OpenTelemetryRequestOptions].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): OpenTelemetryRequestOptions = OpenTelemetryRequestOptions()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is OpenTelemetryRequestOptions
    }

    override fun hashCode(): Int = Objects.hash()

    override fun toString() = "OpenTelemetryRequestOptions{}"
}
