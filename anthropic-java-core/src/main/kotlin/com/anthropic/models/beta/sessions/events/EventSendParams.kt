// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.events

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.Params
import com.anthropic.core.checkKnown
import com.anthropic.core.checkRequired
import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.beta.AnthropicBeta
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** Send Events */
class EventSendParams
private constructor(
    private val sessionId: String?,
    private val betas: List<AnthropicBeta>?,
    private val body: Body,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    fun sessionId(): Optional<String> = Optional.ofNullable(sessionId)

    /** Optional header to specify the beta version(s) you want to use. */
    fun betas(): Optional<List<AnthropicBeta>> = Optional.ofNullable(betas)

    /**
     * Events to send to the `session`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun events(): List<BetaManagedAgentsEventParams> = body.events()

    /**
     * Returns the raw JSON value of [events].
     *
     * Unlike [events], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _events(): JsonField<List<BetaManagedAgentsEventParams>> = body._events()

    fun _additionalBodyProperties(): Map<String, JsonValue> = body._additionalProperties()

    /** Additional headers to send with the request. */
    fun _additionalHeaders(): Headers = additionalHeaders

    /** Additional query param to send with the request. */
    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [EventSendParams].
         *
         * The following fields are required:
         * ```java
         * .events()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [EventSendParams]. */
    class Builder internal constructor() {

        private var sessionId: String? = null
        private var betas: MutableList<AnthropicBeta>? = null
        private var body: Body.Builder = Body.builder()
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(eventSendParams: EventSendParams) = apply {
            sessionId = eventSendParams.sessionId
            betas = eventSendParams.betas?.toMutableList()
            body = eventSendParams.body.toBuilder()
            additionalHeaders = eventSendParams.additionalHeaders.toBuilder()
            additionalQueryParams = eventSendParams.additionalQueryParams.toBuilder()
        }

        fun sessionId(sessionId: String?) = apply { this.sessionId = sessionId }

        /** Alias for calling [Builder.sessionId] with `sessionId.orElse(null)`. */
        fun sessionId(sessionId: Optional<String>) = sessionId(sessionId.getOrNull())

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

        /**
         * Sets the entire request body.
         *
         * This is generally only useful if you are already constructing the body separately.
         * Otherwise, it's more convenient to use the top-level setters instead:
         * - [events]
         */
        fun body(body: Body) = apply { this.body = body.toBuilder() }

        /** Events to send to the `session`. */
        fun events(events: List<BetaManagedAgentsEventParams>) = apply { body.events(events) }

        /**
         * Sets [Builder.events] to an arbitrary JSON value.
         *
         * You should usually call [Builder.events] with a well-typed
         * `List<BetaManagedAgentsEventParams>` value instead. This method is primarily for setting
         * the field to an undocumented or not yet supported value.
         */
        fun events(events: JsonField<List<BetaManagedAgentsEventParams>>) = apply {
            body.events(events)
        }

        /**
         * Adds a single [BetaManagedAgentsEventParams] to [events].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addEvent(event: BetaManagedAgentsEventParams) = apply { body.addEvent(event) }

        /**
         * Alias for calling [addEvent] with
         * `BetaManagedAgentsEventParams.ofUserMessage(userMessage)`.
         */
        fun addEvent(userMessage: BetaManagedAgentsUserMessageEventParams) = apply {
            body.addEvent(userMessage)
        }

        /**
         * Alias for calling [addEvent] with the following:
         * ```java
         * BetaManagedAgentsUserMessageEventParams.builder()
         *     .type(BetaManagedAgentsUserMessageEventParams.Type.USER_MESSAGE)
         *     .content(content)
         *     .build()
         * ```
         */
        fun addUserMessageEvent(content: List<BetaManagedAgentsUserMessageEventParams.Content>) =
            apply {
                body.addUserMessageEvent(content)
            }

        /**
         * Alias for calling [addEvent] with
         * `BetaManagedAgentsEventParams.ofUserInterrupt(userInterrupt)`.
         */
        fun addEvent(userInterrupt: BetaManagedAgentsUserInterruptEventParams) = apply {
            body.addEvent(userInterrupt)
        }

        /**
         * Alias for calling [addEvent] with
         * `BetaManagedAgentsEventParams.ofUserToolConfirmation(userToolConfirmation)`.
         */
        fun addEvent(userToolConfirmation: BetaManagedAgentsUserToolConfirmationEventParams) =
            apply {
                body.addEvent(userToolConfirmation)
            }

        /**
         * Alias for calling [addEvent] with
         * `BetaManagedAgentsEventParams.ofUserCustomToolResult(userCustomToolResult)`.
         */
        fun addEvent(userCustomToolResult: BetaManagedAgentsUserCustomToolResultEventParams) =
            apply {
                body.addEvent(userCustomToolResult)
            }

        /**
         * Alias for calling [addEvent] with the following:
         * ```java
         * BetaManagedAgentsUserCustomToolResultEventParams.builder()
         *     .type(BetaManagedAgentsUserCustomToolResultEventParams.Type.USER_CUSTOM_TOOL_RESULT)
         *     .customToolUseId(customToolUseId)
         *     .build()
         * ```
         */
        fun addUserCustomToolResultEvent(customToolUseId: String) = apply {
            body.addUserCustomToolResultEvent(customToolUseId)
        }

        /**
         * Alias for calling [addEvent] with
         * `BetaManagedAgentsEventParams.ofUserDefineOutcome(userDefineOutcome)`.
         */
        fun addEvent(userDefineOutcome: BetaManagedAgentsUserDefineOutcomeEventParams) = apply {
            body.addEvent(userDefineOutcome)
        }

        fun additionalBodyProperties(additionalBodyProperties: Map<String, JsonValue>) = apply {
            body.additionalProperties(additionalBodyProperties)
        }

        fun putAdditionalBodyProperty(key: String, value: JsonValue) = apply {
            body.putAdditionalProperty(key, value)
        }

        fun putAllAdditionalBodyProperties(additionalBodyProperties: Map<String, JsonValue>) =
            apply {
                body.putAllAdditionalProperties(additionalBodyProperties)
            }

        fun removeAdditionalBodyProperty(key: String) = apply { body.removeAdditionalProperty(key) }

        fun removeAllAdditionalBodyProperties(keys: Set<String>) = apply {
            body.removeAllAdditionalProperties(keys)
        }

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
         * Returns an immutable instance of [EventSendParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .events()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): EventSendParams =
            EventSendParams(
                sessionId,
                betas?.toImmutable(),
                body.build(),
                additionalHeaders.build(),
                additionalQueryParams.build(),
            )
    }

    fun _body(): Body = body

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

    override fun _queryParams(): QueryParams = additionalQueryParams

    /** Request parameters for sending events to a `session`. */
    class Body
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val events: JsonField<List<BetaManagedAgentsEventParams>>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("events")
            @ExcludeMissing
            events: JsonField<List<BetaManagedAgentsEventParams>> = JsonMissing.of()
        ) : this(events, mutableMapOf())

        /**
         * Events to send to the `session`.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
         *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
         */
        fun events(): List<BetaManagedAgentsEventParams> = events.getRequired("events")

        /**
         * Returns the raw JSON value of [events].
         *
         * Unlike [events], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("events")
        @ExcludeMissing
        fun _events(): JsonField<List<BetaManagedAgentsEventParams>> = events

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

            /**
             * Returns a mutable builder for constructing an instance of [Body].
             *
             * The following fields are required:
             * ```java
             * .events()
             * ```
             */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Body]. */
        class Builder internal constructor() {

            private var events: JsonField<MutableList<BetaManagedAgentsEventParams>>? = null
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(body: Body) = apply {
                events = body.events.map { it.toMutableList() }
                additionalProperties = body.additionalProperties.toMutableMap()
            }

            /** Events to send to the `session`. */
            fun events(events: List<BetaManagedAgentsEventParams>) = events(JsonField.of(events))

            /**
             * Sets [Builder.events] to an arbitrary JSON value.
             *
             * You should usually call [Builder.events] with a well-typed
             * `List<BetaManagedAgentsEventParams>` value instead. This method is primarily for
             * setting the field to an undocumented or not yet supported value.
             */
            fun events(events: JsonField<List<BetaManagedAgentsEventParams>>) = apply {
                this.events = events.map { it.toMutableList() }
            }

            /**
             * Adds a single [BetaManagedAgentsEventParams] to [events].
             *
             * @throws IllegalStateException if the field was previously set to a non-list.
             */
            fun addEvent(event: BetaManagedAgentsEventParams) = apply {
                events =
                    (events ?: JsonField.of(mutableListOf())).also {
                        checkKnown("events", it).add(event)
                    }
            }

            /**
             * Alias for calling [addEvent] with
             * `BetaManagedAgentsEventParams.ofUserMessage(userMessage)`.
             */
            fun addEvent(userMessage: BetaManagedAgentsUserMessageEventParams) =
                addEvent(BetaManagedAgentsEventParams.ofUserMessage(userMessage))

            /**
             * Alias for calling [addEvent] with the following:
             * ```java
             * BetaManagedAgentsUserMessageEventParams.builder()
             *     .type(BetaManagedAgentsUserMessageEventParams.Type.USER_MESSAGE)
             *     .content(content)
             *     .build()
             * ```
             */
            fun addUserMessageEvent(
                content: List<BetaManagedAgentsUserMessageEventParams.Content>
            ) =
                addEvent(
                    BetaManagedAgentsUserMessageEventParams.builder()
                        .type(BetaManagedAgentsUserMessageEventParams.Type.USER_MESSAGE)
                        .content(content)
                        .build()
                )

            /**
             * Alias for calling [addEvent] with
             * `BetaManagedAgentsEventParams.ofUserInterrupt(userInterrupt)`.
             */
            fun addEvent(userInterrupt: BetaManagedAgentsUserInterruptEventParams) =
                addEvent(BetaManagedAgentsEventParams.ofUserInterrupt(userInterrupt))

            /**
             * Alias for calling [addEvent] with
             * `BetaManagedAgentsEventParams.ofUserToolConfirmation(userToolConfirmation)`.
             */
            fun addEvent(userToolConfirmation: BetaManagedAgentsUserToolConfirmationEventParams) =
                addEvent(BetaManagedAgentsEventParams.ofUserToolConfirmation(userToolConfirmation))

            /**
             * Alias for calling [addEvent] with
             * `BetaManagedAgentsEventParams.ofUserCustomToolResult(userCustomToolResult)`.
             */
            fun addEvent(userCustomToolResult: BetaManagedAgentsUserCustomToolResultEventParams) =
                addEvent(BetaManagedAgentsEventParams.ofUserCustomToolResult(userCustomToolResult))

            /**
             * Alias for calling [addEvent] with the following:
             * ```java
             * BetaManagedAgentsUserCustomToolResultEventParams.builder()
             *     .type(BetaManagedAgentsUserCustomToolResultEventParams.Type.USER_CUSTOM_TOOL_RESULT)
             *     .customToolUseId(customToolUseId)
             *     .build()
             * ```
             */
            fun addUserCustomToolResultEvent(customToolUseId: String) =
                addEvent(
                    BetaManagedAgentsUserCustomToolResultEventParams.builder()
                        .type(
                            BetaManagedAgentsUserCustomToolResultEventParams.Type
                                .USER_CUSTOM_TOOL_RESULT
                        )
                        .customToolUseId(customToolUseId)
                        .build()
                )

            /**
             * Alias for calling [addEvent] with
             * `BetaManagedAgentsEventParams.ofUserDefineOutcome(userDefineOutcome)`.
             */
            fun addEvent(userDefineOutcome: BetaManagedAgentsUserDefineOutcomeEventParams) =
                addEvent(BetaManagedAgentsEventParams.ofUserDefineOutcome(userDefineOutcome))

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
             * Returns an immutable instance of [Body].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             *
             * The following fields are required:
             * ```java
             * .events()
             * ```
             *
             * @throws IllegalStateException if any required field is unset.
             */
            fun build(): Body =
                Body(
                    checkRequired("events", events).map { it.toImmutable() },
                    additionalProperties.toMutableMap(),
                )
        }

        private var validated: Boolean = false

        /**
         * Validates that the types of all values in this object match their expected types
         * recursively.
         *
         * This method is _not_ forwards compatible with new types from the API for existing fields.
         *
         * @throws AnthropicInvalidDataException if any value type in this object doesn't match its
         *   expected type.
         */
        fun validate(): Body = apply {
            if (validated) {
                return@apply
            }

            events().forEach { it.validate() }
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
         * Returns a score indicating how many valid values are contained in this object
         * recursively.
         *
         * Used for best match union deserialization.
         */
        @JvmSynthetic
        internal fun validity(): Int =
            (events.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Body &&
                events == other.events &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy { Objects.hash(events, additionalProperties) }

        override fun hashCode(): Int = hashCode

        override fun toString() = "Body{events=$events, additionalProperties=$additionalProperties}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is EventSendParams &&
            sessionId == other.sessionId &&
            betas == other.betas &&
            body == other.body &&
            additionalHeaders == other.additionalHeaders &&
            additionalQueryParams == other.additionalQueryParams
    }

    override fun hashCode(): Int =
        Objects.hash(sessionId, betas, body, additionalHeaders, additionalQueryParams)

    override fun toString() =
        "EventSendParams{sessionId=$sessionId, betas=$betas, body=$body, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
