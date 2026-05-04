// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.events

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkKnown
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** Paginated list of events for a `session`. */
class EventListPageResponse
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val data: JsonField<List<BetaManagedAgentsSessionEvent>>,
    private val nextPage: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("data")
        @ExcludeMissing
        data: JsonField<List<BetaManagedAgentsSessionEvent>> = JsonMissing.of(),
        @JsonProperty("next_page") @ExcludeMissing nextPage: JsonField<String> = JsonMissing.of(),
    ) : this(data, nextPage, mutableMapOf())

    /**
     * Events for the session, ordered by `created_at`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun data(): Optional<List<BetaManagedAgentsSessionEvent>> = data.getOptional("data")

    /**
     * Opaque cursor for the next page. Null when no more results.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun nextPage(): Optional<String> = nextPage.getOptional("next_page")

    /**
     * Returns the raw JSON value of [data].
     *
     * Unlike [data], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("data")
    @ExcludeMissing
    fun _data(): JsonField<List<BetaManagedAgentsSessionEvent>> = data

    /**
     * Returns the raw JSON value of [nextPage].
     *
     * Unlike [nextPage], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("next_page") @ExcludeMissing fun _nextPage(): JsonField<String> = nextPage

    @JsonAnySetter
    private fun putAdditionalProperty(key: String, value: JsonValue) {
        additionalProperties.put(key, value)
    }

    @JsonAnyGetter
    @ExcludeMissing
    fun _additionalProperties(): Map<String, JsonValue> =
        Collections.unmodifiableMap(additionalProperties)

    fun toBuilder() = Builder().from(this)

    companion object {

        /** Returns a mutable builder for constructing an instance of [EventListPageResponse]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [EventListPageResponse]. */
    class Builder internal constructor() {

        private var data: JsonField<MutableList<BetaManagedAgentsSessionEvent>>? = null
        private var nextPage: JsonField<String> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(eventListPageResponse: EventListPageResponse) = apply {
            data = eventListPageResponse.data.map { it.toMutableList() }
            nextPage = eventListPageResponse.nextPage
            additionalProperties = eventListPageResponse.additionalProperties.toMutableMap()
        }

        /** Events for the session, ordered by `created_at`. */
        fun data(data: List<BetaManagedAgentsSessionEvent>) = data(JsonField.of(data))

        /**
         * Sets [Builder.data] to an arbitrary JSON value.
         *
         * You should usually call [Builder.data] with a well-typed
         * `List<BetaManagedAgentsSessionEvent>` value instead. This method is primarily for setting
         * the field to an undocumented or not yet supported value.
         */
        fun data(data: JsonField<List<BetaManagedAgentsSessionEvent>>) = apply {
            this.data = data.map { it.toMutableList() }
        }

        /**
         * Adds a single [BetaManagedAgentsSessionEvent] to [Builder.data].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addData(data: BetaManagedAgentsSessionEvent) = apply {
            this.data =
                (this.data ?: JsonField.of(mutableListOf())).also {
                    checkKnown("data", it).add(data)
                }
        }

        /**
         * Alias for calling [addData] with
         * `BetaManagedAgentsSessionEvent.ofUserMessage(userMessage)`.
         */
        fun addData(userMessage: BetaManagedAgentsUserMessageEvent) =
            addData(BetaManagedAgentsSessionEvent.ofUserMessage(userMessage))

        /**
         * Alias for calling [addData] with
         * `BetaManagedAgentsSessionEvent.ofUserInterrupt(userInterrupt)`.
         */
        fun addData(userInterrupt: BetaManagedAgentsUserInterruptEvent) =
            addData(BetaManagedAgentsSessionEvent.ofUserInterrupt(userInterrupt))

        /**
         * Alias for calling [addData] with the following:
         * ```java
         * BetaManagedAgentsUserInterruptEvent.builder()
         *     .type(BetaManagedAgentsUserInterruptEvent.Type.USER_INTERRUPT)
         *     .id(id)
         *     .build()
         * ```
         */
        fun addUserInterruptData(id: String) =
            addData(
                BetaManagedAgentsUserInterruptEvent.builder()
                    .type(BetaManagedAgentsUserInterruptEvent.Type.USER_INTERRUPT)
                    .id(id)
                    .build()
            )

        /**
         * Alias for calling [addData] with
         * `BetaManagedAgentsSessionEvent.ofUserToolConfirmation(userToolConfirmation)`.
         */
        fun addData(userToolConfirmation: BetaManagedAgentsUserToolConfirmationEvent) =
            addData(BetaManagedAgentsSessionEvent.ofUserToolConfirmation(userToolConfirmation))

        /**
         * Alias for calling [addData] with
         * `BetaManagedAgentsSessionEvent.ofUserCustomToolResult(userCustomToolResult)`.
         */
        fun addData(userCustomToolResult: BetaManagedAgentsUserCustomToolResultEvent) =
            addData(BetaManagedAgentsSessionEvent.ofUserCustomToolResult(userCustomToolResult))

        /**
         * Alias for calling [addData] with
         * `BetaManagedAgentsSessionEvent.ofAgentCustomToolUse(agentCustomToolUse)`.
         */
        fun addData(agentCustomToolUse: BetaManagedAgentsAgentCustomToolUseEvent) =
            addData(BetaManagedAgentsSessionEvent.ofAgentCustomToolUse(agentCustomToolUse))

        /**
         * Alias for calling [addData] with
         * `BetaManagedAgentsSessionEvent.ofAgentMessage(agentMessage)`.
         */
        fun addData(agentMessage: BetaManagedAgentsAgentMessageEvent) =
            addData(BetaManagedAgentsSessionEvent.ofAgentMessage(agentMessage))

        /**
         * Alias for calling [addData] with
         * `BetaManagedAgentsSessionEvent.ofAgentThinking(agentThinking)`.
         */
        fun addData(agentThinking: BetaManagedAgentsAgentThinkingEvent) =
            addData(BetaManagedAgentsSessionEvent.ofAgentThinking(agentThinking))

        /**
         * Alias for calling [addData] with
         * `BetaManagedAgentsSessionEvent.ofAgentMcpToolUse(agentMcpToolUse)`.
         */
        fun addData(agentMcpToolUse: BetaManagedAgentsAgentMcpToolUseEvent) =
            addData(BetaManagedAgentsSessionEvent.ofAgentMcpToolUse(agentMcpToolUse))

        /**
         * Alias for calling [addData] with
         * `BetaManagedAgentsSessionEvent.ofAgentMcpToolResult(agentMcpToolResult)`.
         */
        fun addData(agentMcpToolResult: BetaManagedAgentsAgentMcpToolResultEvent) =
            addData(BetaManagedAgentsSessionEvent.ofAgentMcpToolResult(agentMcpToolResult))

        /**
         * Alias for calling [addData] with
         * `BetaManagedAgentsSessionEvent.ofAgentToolUse(agentToolUse)`.
         */
        fun addData(agentToolUse: BetaManagedAgentsAgentToolUseEvent) =
            addData(BetaManagedAgentsSessionEvent.ofAgentToolUse(agentToolUse))

        /**
         * Alias for calling [addData] with
         * `BetaManagedAgentsSessionEvent.ofAgentToolResult(agentToolResult)`.
         */
        fun addData(agentToolResult: BetaManagedAgentsAgentToolResultEvent) =
            addData(BetaManagedAgentsSessionEvent.ofAgentToolResult(agentToolResult))

        /**
         * Alias for calling [addData] with
         * `BetaManagedAgentsSessionEvent.ofAgentThreadContextCompacted(agentThreadContextCompacted)`.
         */
        fun addData(
            agentThreadContextCompacted: BetaManagedAgentsAgentThreadContextCompactedEvent
        ) =
            addData(
                BetaManagedAgentsSessionEvent.ofAgentThreadContextCompacted(
                    agentThreadContextCompacted
                )
            )

        /**
         * Alias for calling [addData] with
         * `BetaManagedAgentsSessionEvent.ofSessionError(sessionError)`.
         */
        fun addData(sessionError: BetaManagedAgentsSessionErrorEvent) =
            addData(BetaManagedAgentsSessionEvent.ofSessionError(sessionError))

        /**
         * Alias for calling [addData] with
         * `BetaManagedAgentsSessionEvent.ofSessionStatusRescheduled(sessionStatusRescheduled)`.
         */
        fun addData(sessionStatusRescheduled: BetaManagedAgentsSessionStatusRescheduledEvent) =
            addData(
                BetaManagedAgentsSessionEvent.ofSessionStatusRescheduled(sessionStatusRescheduled)
            )

        /**
         * Alias for calling [addData] with
         * `BetaManagedAgentsSessionEvent.ofSessionStatusRunning(sessionStatusRunning)`.
         */
        fun addData(sessionStatusRunning: BetaManagedAgentsSessionStatusRunningEvent) =
            addData(BetaManagedAgentsSessionEvent.ofSessionStatusRunning(sessionStatusRunning))

        /**
         * Alias for calling [addData] with
         * `BetaManagedAgentsSessionEvent.ofSessionStatusIdle(sessionStatusIdle)`.
         */
        fun addData(sessionStatusIdle: BetaManagedAgentsSessionStatusIdleEvent) =
            addData(BetaManagedAgentsSessionEvent.ofSessionStatusIdle(sessionStatusIdle))

        /**
         * Alias for calling [addData] with
         * `BetaManagedAgentsSessionEvent.ofSessionStatusTerminated(sessionStatusTerminated)`.
         */
        fun addData(sessionStatusTerminated: BetaManagedAgentsSessionStatusTerminatedEvent) =
            addData(
                BetaManagedAgentsSessionEvent.ofSessionStatusTerminated(sessionStatusTerminated)
            )

        /**
         * Alias for calling [addData] with
         * `BetaManagedAgentsSessionEvent.ofSpanModelRequestStart(spanModelRequestStart)`.
         */
        fun addData(spanModelRequestStart: BetaManagedAgentsSpanModelRequestStartEvent) =
            addData(BetaManagedAgentsSessionEvent.ofSpanModelRequestStart(spanModelRequestStart))

        /**
         * Alias for calling [addData] with
         * `BetaManagedAgentsSessionEvent.ofSpanModelRequestEnd(spanModelRequestEnd)`.
         */
        fun addData(spanModelRequestEnd: BetaManagedAgentsSpanModelRequestEndEvent) =
            addData(BetaManagedAgentsSessionEvent.ofSpanModelRequestEnd(spanModelRequestEnd))

        /**
         * Alias for calling [addData] with
         * `BetaManagedAgentsSessionEvent.ofSessionDeleted(sessionDeleted)`.
         */
        fun addData(sessionDeleted: BetaManagedAgentsSessionDeletedEvent) =
            addData(BetaManagedAgentsSessionEvent.ofSessionDeleted(sessionDeleted))

        /** Opaque cursor for the next page. Null when no more results. */
        fun nextPage(nextPage: String?) = nextPage(JsonField.ofNullable(nextPage))

        /** Alias for calling [Builder.nextPage] with `nextPage.orElse(null)`. */
        fun nextPage(nextPage: Optional<String>) = nextPage(nextPage.getOrNull())

        /**
         * Sets [Builder.nextPage] to an arbitrary JSON value.
         *
         * You should usually call [Builder.nextPage] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun nextPage(nextPage: JsonField<String>) = apply { this.nextPage = nextPage }

        fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
            this.additionalProperties.clear()
            putAllAdditionalProperties(additionalProperties)
        }

        fun putAdditionalProperty(key: String, value: JsonValue) = apply {
            additionalProperties.put(key, value)
        }

        fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
            this.additionalProperties.putAll(additionalProperties)
        }

        fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

        fun removeAllAdditionalProperties(keys: Set<String>) = apply {
            keys.forEach(::removeAdditionalProperty)
        }

        /**
         * Returns an immutable instance of [EventListPageResponse].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): EventListPageResponse =
            EventListPageResponse(
                (data ?: JsonMissing.of()).map { it.toImmutable() },
                nextPage,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    /**
     * Validates that the types of all values in this object match their expected types recursively.
     *
     * This method is _not_ forwards compatible with new types from the API for existing fields.
     *
     * @throws AnthropicInvalidDataException if any value type in this object doesn't match its
     *   expected type.
     */
    fun validate(): EventListPageResponse = apply {
        if (validated) {
            return@apply
        }

        data().ifPresent { it.forEach { it.validate() } }
        nextPage()
        validated = true
    }

    fun isValid(): Boolean =
        try {
            validate()
            true
        } catch (e: AnthropicInvalidDataException) {
            false
        }

    /**
     * Returns a score indicating how many valid values are contained in this object recursively.
     *
     * Used for best match union deserialization.
     */
    @JvmSynthetic
    internal fun validity(): Int =
        (data.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
            (if (nextPage.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is EventListPageResponse &&
            data == other.data &&
            nextPage == other.nextPage &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(data, nextPage, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "EventListPageResponse{data=$data, nextPage=$nextPage, additionalProperties=$additionalProperties}"
}
