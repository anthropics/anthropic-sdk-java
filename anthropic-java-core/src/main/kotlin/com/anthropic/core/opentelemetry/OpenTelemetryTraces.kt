package com.anthropic.core.opentelemetry

import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** The `traces` field of the `openTelemetry` client option. */
class OpenTelemetryTraces
private constructor(
    private val enabled: Boolean,
    private val contentMode: OpenTelemetryContentMode,
    private val maxContentBytes: Long?,
) {

    /**
     * Whether the client emits spans. Defaults to `true`. When `false`, the other settings are not
     * used.
     */
    fun enabled(): Boolean = enabled

    /**
     * Whether the client's spans hold the content of its requests and responses. Defaults to
     * [OpenTelemetryContentMode.METADATA_ONLY].
     */
    fun contentMode(): OpenTelemetryContentMode = contentMode

    /**
     * The most content that one span holds in [OpenTelemetryContentMode.CONTENT] mode. Content over
     * it is removed from the span, which then has the `anthropic.content.truncated` attribute.
     * Defaults to 0.75 MB.
     */
    fun maxContentBytes(): Optional<Long> = Optional.ofNullable(maxContentBytes)

    fun toBuilder() = Builder().from(this)

    companion object {

        /** Returns a mutable builder for constructing an instance of [OpenTelemetryTraces]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [OpenTelemetryTraces]. */
    class Builder internal constructor() {

        private var enabled: Boolean = true
        private var contentMode: OpenTelemetryContentMode = OpenTelemetryContentMode.METADATA_ONLY
        private var maxContentBytes: Long? = null

        @JvmSynthetic
        internal fun from(openTelemetryTraces: OpenTelemetryTraces) = apply {
            enabled = openTelemetryTraces.enabled
            contentMode = openTelemetryTraces.contentMode
            maxContentBytes = openTelemetryTraces.maxContentBytes
        }

        /**
         * Whether the client emits spans. Defaults to `true`. When `false`, the other settings are
         * not used.
         */
        fun enabled(enabled: Boolean) = apply { this.enabled = enabled }

        /**
         * Whether the client's spans hold the content of its requests and responses. Defaults to
         * [OpenTelemetryContentMode.METADATA_ONLY].
         */
        fun contentMode(contentMode: OpenTelemetryContentMode) = apply {
            this.contentMode = contentMode
        }

        /**
         * The most content, in bytes of UTF-8, that one span holds in
         * [OpenTelemetryContentMode.CONTENT] mode. Content over it is removed from the span, which
         * then has the `anthropic.content.truncated` attribute. Defaults to 0.75 MB.
         *
         * @throws IllegalArgumentException if [maxContentBytes] is less than 1.
         */
        fun maxContentBytes(maxContentBytes: Long?) = apply {
            if (maxContentBytes != null) {
                require(maxContentBytes >= 1) {
                    "`maxContentBytes` must be at least 1, but was $maxContentBytes"
                }
            }
            this.maxContentBytes = maxContentBytes
        }

        /** Alias for [Builder.maxContentBytes] that takes an unboxed primitive. */
        fun maxContentBytes(maxContentBytes: Long) = maxContentBytes(maxContentBytes as Long?)

        /** Alias for calling [Builder.maxContentBytes] with `maxContentBytes.orElse(null)`. */
        fun maxContentBytes(maxContentBytes: Optional<Long>) =
            maxContentBytes(maxContentBytes.getOrNull())

        /**
         * Returns an immutable instance of [OpenTelemetryTraces].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): OpenTelemetryTraces =
            OpenTelemetryTraces(enabled, contentMode, maxContentBytes)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is OpenTelemetryTraces &&
            enabled == other.enabled &&
            contentMode == other.contentMode &&
            maxContentBytes == other.maxContentBytes
    }

    override fun hashCode(): Int = Objects.hash(enabled, contentMode, maxContentBytes)

    override fun toString() =
        "OpenTelemetryTraces{enabled=$enabled, contentMode=$contentMode${maxContentBytes?.let { ", maxContentBytes=$it" }.orEmpty()}}"
}
