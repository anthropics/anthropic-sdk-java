// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.events

import com.anthropic.core.Params
import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import com.anthropic.core.toImmutable
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.sessions.BetaManagedAgentsDeltaType
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** Stream Events */
class EventStreamParams
private constructor(
    private val sessionId: String?,
    private val eventDeltas: List<BetaManagedAgentsDeltaType>?,
    private val betas: List<AnthropicBeta>?,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    fun sessionId(): Optional<String> = Optional.ofNullable(sessionId)

    /**
     * When set, this connection also receives streaming deltas (`event_start`, `event_delta`) while
     * an event is being produced, before the event itself arrives. Deltas are best-effort; when the
     * final event is produced it carries the complete content. A model request that ends early (an
     * error or interrupt) produces no final event — its terminal `span.model_request_end` closes
     * the preview. Accepts one or more event types to preview and may be repeated: `agent.message`
     * streams `content_delta` fragments; `agent.thinking` is start-only — a signal that the agent
     * has begun extended thinking, concluded by the `agent.thinking` event itself. Only previews of
     * the requested event types are sent.
     */
    fun eventDeltas(): Optional<List<BetaManagedAgentsDeltaType>> = Optional.ofNullable(eventDeltas)

    /** Optional header to specify the beta version(s) you want to use. */
    fun betas(): Optional<List<AnthropicBeta>> = Optional.ofNullable(betas)

    /** Additional headers to send with the request. */
    fun _additionalHeaders(): Headers = additionalHeaders

    /** Additional query param to send with the request. */
    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun none(): EventStreamParams = builder().build()

        /** Returns a mutable builder for constructing an instance of [EventStreamParams]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [EventStreamParams]. */
    class Builder internal constructor() {

        private var sessionId: String? = null
        private var eventDeltas: MutableList<BetaManagedAgentsDeltaType>? = null
        private var betas: MutableList<AnthropicBeta>? = null
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(eventStreamParams: EventStreamParams) = apply {
            sessionId = eventStreamParams.sessionId
            eventDeltas = eventStreamParams.eventDeltas?.toMutableList()
            betas = eventStreamParams.betas?.toMutableList()
            additionalHeaders = eventStreamParams.additionalHeaders.toBuilder()
            additionalQueryParams = eventStreamParams.additionalQueryParams.toBuilder()
        }

        fun sessionId(sessionId: String?) = apply { this.sessionId = sessionId }

        /** Alias for calling [Builder.sessionId] with `sessionId.orElse(null)`. */
        fun sessionId(sessionId: Optional<String>) = sessionId(sessionId.getOrNull())

        /**
         * When set, this connection also receives streaming deltas (`event_start`, `event_delta`)
         * while an event is being produced, before the event itself arrives. Deltas are
         * best-effort; when the final event is produced it carries the complete content. A model
         * request that ends early (an error or interrupt) produces no final event — its terminal
         * `span.model_request_end` closes the preview. Accepts one or more event types to preview
         * and may be repeated: `agent.message` streams `content_delta` fragments; `agent.thinking`
         * is start-only — a signal that the agent has begun extended thinking, concluded by the
         * `agent.thinking` event itself. Only previews of the requested event types are sent.
         */
        fun eventDeltas(eventDeltas: List<BetaManagedAgentsDeltaType>?) = apply {
            this.eventDeltas = eventDeltas?.toMutableList()
        }

        /** Alias for calling [Builder.eventDeltas] with `eventDeltas.orElse(null)`. */
        fun eventDeltas(eventDeltas: Optional<List<BetaManagedAgentsDeltaType>>) =
            eventDeltas(eventDeltas.getOrNull())

        /**
         * Adds a single [BetaManagedAgentsDeltaType] to [eventDeltas].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addEventDelta(eventDelta: BetaManagedAgentsDeltaType) = apply {
            eventDeltas = (eventDeltas ?: mutableListOf()).apply { add(eventDelta) }
        }

        /** Optional header to specify the beta version(s) you want to use. */
        fun betas(betas: List<AnthropicBeta>?) = apply { this.betas = betas?.toMutableList() }

        /** Alias for calling [Builder.betas] with `betas.orElse(null)`. */
        fun betas(betas: Optional<List<AnthropicBeta>>) = betas(betas.getOrNull())

        /**
         * Adds a single [AnthropicBeta] to [betas].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addBeta(beta: AnthropicBeta) = apply {
            betas = (betas ?: mutableListOf()).apply { add(beta) }
        }

        /**
         * Sets [addBeta] to an arbitrary [String].
         *
         * You should usually call [addBeta] with a well-typed [AnthropicBeta] constant instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun addBeta(value: String) = addBeta(AnthropicBeta.of(value))

        fun additionalHeaders(additionalHeaders: Headers) = apply {
            this.additionalHeaders.clear()
            putAllAdditionalHeaders(additionalHeaders)
        }

        fun additionalHeaders(additionalHeaders: Map<String, Iterable<String>>) = apply {
            this.additionalHeaders.clear()
            putAllAdditionalHeaders(additionalHeaders)
        }

        fun putAdditionalHeader(name: String, value: String) = apply {
            additionalHeaders.put(name, value)
        }

        fun putAdditionalHeaders(name: String, values: Iterable<String>) = apply {
            additionalHeaders.put(name, values)
        }

        fun putAllAdditionalHeaders(additionalHeaders: Headers) = apply {
            this.additionalHeaders.putAll(additionalHeaders)
        }

        fun putAllAdditionalHeaders(additionalHeaders: Map<String, Iterable<String>>) = apply {
            this.additionalHeaders.putAll(additionalHeaders)
        }

        fun replaceAdditionalHeaders(name: String, value: String) = apply {
            additionalHeaders.replace(name, value)
        }

        fun replaceAdditionalHeaders(name: String, values: Iterable<String>) = apply {
            additionalHeaders.replace(name, values)
        }

        fun replaceAllAdditionalHeaders(additionalHeaders: Headers) = apply {
            this.additionalHeaders.replaceAll(additionalHeaders)
        }

        fun replaceAllAdditionalHeaders(additionalHeaders: Map<String, Iterable<String>>) = apply {
            this.additionalHeaders.replaceAll(additionalHeaders)
        }

        fun removeAdditionalHeaders(name: String) = apply { additionalHeaders.remove(name) }

        fun removeAllAdditionalHeaders(names: Set<String>) = apply {
            additionalHeaders.removeAll(names)
        }

        fun additionalQueryParams(additionalQueryParams: QueryParams) = apply {
            this.additionalQueryParams.clear()
            putAllAdditionalQueryParams(additionalQueryParams)
        }

        fun additionalQueryParams(additionalQueryParams: Map<String, Iterable<String>>) = apply {
            this.additionalQueryParams.clear()
            putAllAdditionalQueryParams(additionalQueryParams)
        }

        fun putAdditionalQueryParam(key: String, value: String) = apply {
            additionalQueryParams.put(key, value)
        }

        fun putAdditionalQueryParams(key: String, values: Iterable<String>) = apply {
            additionalQueryParams.put(key, values)
        }

        fun putAllAdditionalQueryParams(additionalQueryParams: QueryParams) = apply {
            this.additionalQueryParams.putAll(additionalQueryParams)
        }

        fun putAllAdditionalQueryParams(additionalQueryParams: Map<String, Iterable<String>>) =
            apply {
                this.additionalQueryParams.putAll(additionalQueryParams)
            }

        fun replaceAdditionalQueryParams(key: String, value: String) = apply {
            additionalQueryParams.replace(key, value)
        }

        fun replaceAdditionalQueryParams(key: String, values: Iterable<String>) = apply {
            additionalQueryParams.replace(key, values)
        }

        fun replaceAllAdditionalQueryParams(additionalQueryParams: QueryParams) = apply {
            this.additionalQueryParams.replaceAll(additionalQueryParams)
        }

        fun replaceAllAdditionalQueryParams(additionalQueryParams: Map<String, Iterable<String>>) =
            apply {
                this.additionalQueryParams.replaceAll(additionalQueryParams)
            }

        fun removeAdditionalQueryParams(key: String) = apply { additionalQueryParams.remove(key) }

        fun removeAllAdditionalQueryParams(keys: Set<String>) = apply {
            additionalQueryParams.removeAll(keys)
        }

        /**
         * Returns an immutable instance of [EventStreamParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): EventStreamParams =
            EventStreamParams(
                sessionId,
                eventDeltas?.toImmutable(),
                betas?.toImmutable(),
                additionalHeaders.build(),
                additionalQueryParams.build(),
            )
    }

    fun _pathParam(index: Int): String =
        when (index) {
            0 -> sessionId ?: ""
            else -> ""
        }

    override fun _headers(): Headers =
        Headers.builder()
            .apply {
                betas?.forEach { put("anthropic-beta", it.toString()) }
                putAll(additionalHeaders)
            }
            .build()

    override fun _queryParams(): QueryParams =
        QueryParams.builder()
            .apply {
                eventDeltas?.forEach { put("event_deltas[]", it.toString()) }
                putAll(additionalQueryParams)
            }
            .build()

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is EventStreamParams &&
            sessionId == other.sessionId &&
            eventDeltas == other.eventDeltas &&
            betas == other.betas &&
            additionalHeaders == other.additionalHeaders &&
            additionalQueryParams == other.additionalQueryParams
    }

    override fun hashCode(): Int =
        Objects.hash(sessionId, eventDeltas, betas, additionalHeaders, additionalQueryParams)

    override fun toString() =
        "EventStreamParams{sessionId=$sessionId, eventDeltas=$eventDeltas, betas=$betas, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
