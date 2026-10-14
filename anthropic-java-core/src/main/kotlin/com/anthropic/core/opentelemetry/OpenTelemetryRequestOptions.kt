package com.anthropic.core.opentelemetry

import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** The `openTelemetry` request option. */
class OpenTelemetryRequestOptions private constructor(private val conversationId: String?) {

    /**
     * An ID that links this request's span to the other requests of one conversation. The SDK sets
     * it as `gen_ai.conversation.id`, and does not send it to the API.
     */
    fun conversationId(): Optional<String> = Optional.ofNullable(conversationId)

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

        private var conversationId: String? = null

        @JvmSynthetic
        internal fun from(openTelemetryRequestOptions: OpenTelemetryRequestOptions) = apply {
            conversationId = openTelemetryRequestOptions.conversationId
        }

        /**
         * An ID that links this request's span to the other requests of one conversation. The SDK
         * sets it as `gen_ai.conversation.id`, and does not send it to the API.
         */
        fun conversationId(conversationId: String?) = apply { this.conversationId = conversationId }

        /** Alias for calling [Builder.conversationId] with `conversationId.orElse(null)`. */
        fun conversationId(conversationId: Optional<String>) =
            conversationId(conversationId.getOrNull())

        /**
         * Returns an immutable instance of [OpenTelemetryRequestOptions].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): OpenTelemetryRequestOptions = OpenTelemetryRequestOptions(conversationId)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is OpenTelemetryRequestOptions && conversationId == other.conversationId
    }

    override fun hashCode(): Int = Objects.hash(conversationId)

    override fun toString() =
        "OpenTelemetryRequestOptions{${conversationId?.let { "conversationId=$it" }.orEmpty()}}"
}
