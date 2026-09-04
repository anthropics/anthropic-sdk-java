// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.events

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.Enum
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkKnown
import com.anthropic.core.getOrThrow
import com.anthropic.core.getProperty
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.beta.sessions.BetaManagedAgentsSystemMessageEvent
import com.anthropic.models.beta.sessions.BetaManagedAgentsUserToolResultEvent
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.ObjectCodec
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.time.OffsetDateTime
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** Events that were successfully sent to the session. */
class BetaManagedAgentsSendSessionEvents
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val data: JsonField<List<Data>>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("data") @ExcludeMissing data: JsonField<List<Data>> = JsonMissing.of()
    ) : this(data, mutableMapOf())

    /**
     * Sent events
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun data(): Optional<List<Data>> = data.getOptional("data")

    /**
     * Returns the raw JSON value of [data].
     *
     * Unlike [data], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("data") @ExcludeMissing fun _data(): JsonField<List<Data>> = data

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
         * Returns a mutable builder for constructing an instance of
         * [BetaManagedAgentsSendSessionEvents].
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaManagedAgentsSendSessionEvents]. */
    class Builder internal constructor() {

        private var data: JsonField<MutableList<Data>>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaManagedAgentsSendSessionEvents: BetaManagedAgentsSendSessionEvents) =
            apply {
                data =
                    betaManagedAgentsSendSessionEvents.data
                        .map { it.toMutableList() }
                        .takeUnless { it.isMissing() }
                additionalProperties =
                    betaManagedAgentsSendSessionEvents.additionalProperties.toMutableMap()
            }

        /** Sent events */
        fun data(data: List<Data>) = data(JsonField.of(data))

        /**
         * Sets [Builder.data] to an arbitrary JSON value.
         *
         * You should usually call [Builder.data] with a well-typed `List<Data>` value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun data(data: JsonField<List<Data>>) = apply {
            this.data = data.map { it.toMutableList() }
        }

        /**
         * Adds a single [Data] to [Builder.data].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addData(data: Data) = apply {
            this.data =
                (this.data ?: JsonField.of(mutableListOf())).also {
                    checkKnown("data", it).add(data)
                }
        }

        /** Alias for calling [addData] with `Data.ofUserMessage(userMessage)`. */
        fun addData(userMessage: BetaManagedAgentsUserMessageEvent) =
            addData(Data.ofUserMessage(userMessage))

        /** Alias for calling [addData] with `Data.ofUserInterrupt(userInterrupt)`. */
        fun addData(userInterrupt: BetaManagedAgentsUserInterruptEvent) =
            addData(Data.ofUserInterrupt(userInterrupt))

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

        /** Alias for calling [addData] with `Data.ofUserToolConfirmation(userToolConfirmation)`. */
        fun addData(userToolConfirmation: BetaManagedAgentsUserToolConfirmationEvent) =
            addData(Data.ofUserToolConfirmation(userToolConfirmation))

        /** Alias for calling [addData] with `Data.ofUserCustomToolResult(userCustomToolResult)`. */
        fun addData(userCustomToolResult: BetaManagedAgentsUserCustomToolResultEvent) =
            addData(Data.ofUserCustomToolResult(userCustomToolResult))

        /** Alias for calling [addData] with `Data.ofUserDefineOutcome(userDefineOutcome)`. */
        fun addData(userDefineOutcome: BetaManagedAgentsUserDefineOutcomeEvent) =
            addData(Data.ofUserDefineOutcome(userDefineOutcome))

        /** Alias for calling [addData] with `Data.ofUserToolResult(userToolResult)`. */
        fun addData(userToolResult: BetaManagedAgentsUserToolResultEvent) =
            addData(Data.ofUserToolResult(userToolResult))

        /** Alias for calling [addData] with `Data.ofSystemMessage(systemMessage)`. */
        fun addData(systemMessage: BetaManagedAgentsSystemMessageEvent) =
            addData(Data.ofSystemMessage(systemMessage))

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
         * Returns an immutable instance of [BetaManagedAgentsSendSessionEvents].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): BetaManagedAgentsSendSessionEvents =
            BetaManagedAgentsSendSessionEvents(
                (data ?: JsonMissing.of()).map { it.toImmutable() },
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
    fun validate(): BetaManagedAgentsSendSessionEvents = apply {
        if (validated) {
            return@apply
        }

        data().ifPresent { it.forEach { it.validate() } }
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
        (data.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0)

    /** Union type for events that can be sent to a session. */
    @JsonDeserialize(using = Data.Deserializer::class)
    @JsonSerialize(using = Data.Serializer::class)
    class Data
    private constructor(
        private val userMessage: BetaManagedAgentsUserMessageEvent? = null,
        private val userInterrupt: BetaManagedAgentsUserInterruptEvent? = null,
        private val userToolConfirmation: BetaManagedAgentsUserToolConfirmationEvent? = null,
        private val userCustomToolResult: BetaManagedAgentsUserCustomToolResultEvent? = null,
        private val userDefineOutcome: BetaManagedAgentsUserDefineOutcomeEvent? = null,
        private val userToolResult: BetaManagedAgentsUserToolResultEvent? = null,
        private val systemMessage: BetaManagedAgentsSystemMessageEvent? = null,
        private val _json: JsonValue? = null,
    ) {

        fun type(): Type =
            accept(
                object : Visitor<Type> {
                    override fun visitUserMessage(
                        userMessage: BetaManagedAgentsUserMessageEvent
                    ): Type = Type.USER_MESSAGE

                    override fun visitUserInterrupt(
                        userInterrupt: BetaManagedAgentsUserInterruptEvent
                    ): Type = Type.USER_INTERRUPT

                    override fun visitUserToolConfirmation(
                        userToolConfirmation: BetaManagedAgentsUserToolConfirmationEvent
                    ): Type = Type.USER_TOOL_CONFIRMATION

                    override fun visitUserCustomToolResult(
                        userCustomToolResult: BetaManagedAgentsUserCustomToolResultEvent
                    ): Type = Type.USER_CUSTOM_TOOL_RESULT

                    override fun visitUserDefineOutcome(
                        userDefineOutcome: BetaManagedAgentsUserDefineOutcomeEvent
                    ): Type = Type.USER_DEFINE_OUTCOME

                    override fun visitUserToolResult(
                        userToolResult: BetaManagedAgentsUserToolResultEvent
                    ): Type = Type.USER_TOOL_RESULT

                    override fun visitSystemMessage(
                        systemMessage: BetaManagedAgentsSystemMessageEvent
                    ): Type = Type.SYSTEM_MESSAGE

                    override fun unknown(json: JsonValue?): Type =
                        Type.of(json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
                }
            )

        fun id(): String =
            accept(
                object : Visitor<String> {
                    override fun visitUserMessage(
                        userMessage: BetaManagedAgentsUserMessageEvent
                    ): String = userMessage.id()

                    override fun visitUserInterrupt(
                        userInterrupt: BetaManagedAgentsUserInterruptEvent
                    ): String = userInterrupt.id()

                    override fun visitUserToolConfirmation(
                        userToolConfirmation: BetaManagedAgentsUserToolConfirmationEvent
                    ): String = userToolConfirmation.id()

                    override fun visitUserCustomToolResult(
                        userCustomToolResult: BetaManagedAgentsUserCustomToolResultEvent
                    ): String = userCustomToolResult.id()

                    override fun visitUserDefineOutcome(
                        userDefineOutcome: BetaManagedAgentsUserDefineOutcomeEvent
                    ): String = userDefineOutcome.id()

                    override fun visitUserToolResult(
                        userToolResult: BetaManagedAgentsUserToolResultEvent
                    ): String = userToolResult.id()

                    override fun visitSystemMessage(
                        systemMessage: BetaManagedAgentsSystemMessageEvent
                    ): String = systemMessage.id()

                    override fun unknown(json: JsonValue?): String =
                        json.getProperty<String>("id").getRequired("id")
                }
            )

        fun processedAt(): Optional<OffsetDateTime> =
            accept(
                object : Visitor<Optional<OffsetDateTime>> {
                    override fun visitUserMessage(
                        userMessage: BetaManagedAgentsUserMessageEvent
                    ): Optional<OffsetDateTime> = userMessage.processedAt()

                    override fun visitUserInterrupt(
                        userInterrupt: BetaManagedAgentsUserInterruptEvent
                    ): Optional<OffsetDateTime> = userInterrupt.processedAt()

                    override fun visitUserToolConfirmation(
                        userToolConfirmation: BetaManagedAgentsUserToolConfirmationEvent
                    ): Optional<OffsetDateTime> = userToolConfirmation.processedAt()

                    override fun visitUserCustomToolResult(
                        userCustomToolResult: BetaManagedAgentsUserCustomToolResultEvent
                    ): Optional<OffsetDateTime> = userCustomToolResult.processedAt()

                    override fun visitUserDefineOutcome(
                        userDefineOutcome: BetaManagedAgentsUserDefineOutcomeEvent
                    ): Optional<OffsetDateTime> = Optional.of(userDefineOutcome.processedAt())

                    override fun visitUserToolResult(
                        userToolResult: BetaManagedAgentsUserToolResultEvent
                    ): Optional<OffsetDateTime> = userToolResult.processedAt()

                    override fun visitSystemMessage(
                        systemMessage: BetaManagedAgentsSystemMessageEvent
                    ): Optional<OffsetDateTime> = systemMessage.processedAt()

                    override fun unknown(json: JsonValue?): Optional<OffsetDateTime> =
                        json.getProperty<OffsetDateTime>("processed_at").asKnown()
                }
            )

        fun sessionThreadId(): Optional<String> =
            accept(
                object : Visitor<Optional<String>> {
                    override fun visitUserMessage(
                        userMessage: BetaManagedAgentsUserMessageEvent
                    ): Optional<String> = Optional.empty()

                    override fun visitUserInterrupt(
                        userInterrupt: BetaManagedAgentsUserInterruptEvent
                    ): Optional<String> = userInterrupt.sessionThreadId()

                    override fun visitUserToolConfirmation(
                        userToolConfirmation: BetaManagedAgentsUserToolConfirmationEvent
                    ): Optional<String> = userToolConfirmation.sessionThreadId()

                    override fun visitUserCustomToolResult(
                        userCustomToolResult: BetaManagedAgentsUserCustomToolResultEvent
                    ): Optional<String> = userCustomToolResult.sessionThreadId()

                    override fun visitUserDefineOutcome(
                        userDefineOutcome: BetaManagedAgentsUserDefineOutcomeEvent
                    ): Optional<String> = Optional.empty()

                    override fun visitUserToolResult(
                        userToolResult: BetaManagedAgentsUserToolResultEvent
                    ): Optional<String> = userToolResult.sessionThreadId()

                    override fun visitSystemMessage(
                        systemMessage: BetaManagedAgentsSystemMessageEvent
                    ): Optional<String> = Optional.empty()

                    override fun unknown(json: JsonValue?): Optional<String> =
                        json.getProperty<String>("session_thread_id").asKnown()
                }
            )

        fun toolUseId(): Optional<String> =
            accept(
                object : Visitor<Optional<String>> {
                    override fun visitUserMessage(
                        userMessage: BetaManagedAgentsUserMessageEvent
                    ): Optional<String> = Optional.empty()

                    override fun visitUserInterrupt(
                        userInterrupt: BetaManagedAgentsUserInterruptEvent
                    ): Optional<String> = Optional.empty()

                    override fun visitUserToolConfirmation(
                        userToolConfirmation: BetaManagedAgentsUserToolConfirmationEvent
                    ): Optional<String> = Optional.of(userToolConfirmation.toolUseId())

                    override fun visitUserCustomToolResult(
                        userCustomToolResult: BetaManagedAgentsUserCustomToolResultEvent
                    ): Optional<String> = Optional.empty()

                    override fun visitUserDefineOutcome(
                        userDefineOutcome: BetaManagedAgentsUserDefineOutcomeEvent
                    ): Optional<String> = Optional.empty()

                    override fun visitUserToolResult(
                        userToolResult: BetaManagedAgentsUserToolResultEvent
                    ): Optional<String> = Optional.of(userToolResult.toolUseId())

                    override fun visitSystemMessage(
                        systemMessage: BetaManagedAgentsSystemMessageEvent
                    ): Optional<String> = Optional.empty()

                    override fun unknown(json: JsonValue?): Optional<String> =
                        json.getProperty<String>("tool_use_id").asKnown()
                }
            )

        fun isError(): Optional<Boolean> =
            accept(
                object : Visitor<Optional<Boolean>> {
                    override fun visitUserMessage(
                        userMessage: BetaManagedAgentsUserMessageEvent
                    ): Optional<Boolean> = Optional.empty()

                    override fun visitUserInterrupt(
                        userInterrupt: BetaManagedAgentsUserInterruptEvent
                    ): Optional<Boolean> = Optional.empty()

                    override fun visitUserToolConfirmation(
                        userToolConfirmation: BetaManagedAgentsUserToolConfirmationEvent
                    ): Optional<Boolean> = Optional.empty()

                    override fun visitUserCustomToolResult(
                        userCustomToolResult: BetaManagedAgentsUserCustomToolResultEvent
                    ): Optional<Boolean> = userCustomToolResult.isError()

                    override fun visitUserDefineOutcome(
                        userDefineOutcome: BetaManagedAgentsUserDefineOutcomeEvent
                    ): Optional<Boolean> = Optional.empty()

                    override fun visitUserToolResult(
                        userToolResult: BetaManagedAgentsUserToolResultEvent
                    ): Optional<Boolean> = userToolResult.isError()

                    override fun visitSystemMessage(
                        systemMessage: BetaManagedAgentsSystemMessageEvent
                    ): Optional<Boolean> = Optional.empty()

                    override fun unknown(json: JsonValue?): Optional<Boolean> =
                        json.getProperty<Boolean>("is_error").asKnown()
                }
            )

        /** A user message event in the session conversation. */
        fun userMessage(): Optional<BetaManagedAgentsUserMessageEvent> =
            Optional.ofNullable(userMessage)

        /** An interrupt event that pauses agent execution and returns control to the user. */
        fun userInterrupt(): Optional<BetaManagedAgentsUserInterruptEvent> =
            Optional.ofNullable(userInterrupt)

        /** A tool confirmation event that approves or denies a pending tool execution. */
        fun userToolConfirmation(): Optional<BetaManagedAgentsUserToolConfirmationEvent> =
            Optional.ofNullable(userToolConfirmation)

        /** Event sent by the client providing the result of a custom tool execution. */
        fun userCustomToolResult(): Optional<BetaManagedAgentsUserCustomToolResultEvent> =
            Optional.ofNullable(userCustomToolResult)

        /**
         * Echo of a `user.define_outcome` input event. Carries the server-generated `outcome_id`
         * that subsequent `span.outcome_evaluation_*` events reference.
         */
        fun userDefineOutcome(): Optional<BetaManagedAgentsUserDefineOutcomeEvent> =
            Optional.ofNullable(userDefineOutcome)

        /**
         * Event sent by the client providing the result of an agent-toolset tool execution. Only
         * valid on `self_hosted` environments, where sandbox-routed tools are executed by the
         * client rather than the server.
         */
        fun userToolResult(): Optional<BetaManagedAgentsUserToolResultEvent> =
            Optional.ofNullable(userToolResult)

        /**
         * A mid-conversation system message event. Carries system-role content that is appended to
         * the session as a `role: "system"` turn.
         */
        fun systemMessage(): Optional<BetaManagedAgentsSystemMessageEvent> =
            Optional.ofNullable(systemMessage)

        fun isUserMessage(): Boolean = userMessage != null

        fun isUserInterrupt(): Boolean = userInterrupt != null

        fun isUserToolConfirmation(): Boolean = userToolConfirmation != null

        fun isUserCustomToolResult(): Boolean = userCustomToolResult != null

        fun isUserDefineOutcome(): Boolean = userDefineOutcome != null

        fun isUserToolResult(): Boolean = userToolResult != null

        fun isSystemMessage(): Boolean = systemMessage != null

        /** A user message event in the session conversation. */
        fun asUserMessage(): BetaManagedAgentsUserMessageEvent =
            userMessage.getOrThrow("userMessage")

        /** An interrupt event that pauses agent execution and returns control to the user. */
        fun asUserInterrupt(): BetaManagedAgentsUserInterruptEvent =
            userInterrupt.getOrThrow("userInterrupt")

        /** A tool confirmation event that approves or denies a pending tool execution. */
        fun asUserToolConfirmation(): BetaManagedAgentsUserToolConfirmationEvent =
            userToolConfirmation.getOrThrow("userToolConfirmation")

        /** Event sent by the client providing the result of a custom tool execution. */
        fun asUserCustomToolResult(): BetaManagedAgentsUserCustomToolResultEvent =
            userCustomToolResult.getOrThrow("userCustomToolResult")

        /**
         * Echo of a `user.define_outcome` input event. Carries the server-generated `outcome_id`
         * that subsequent `span.outcome_evaluation_*` events reference.
         */
        fun asUserDefineOutcome(): BetaManagedAgentsUserDefineOutcomeEvent =
            userDefineOutcome.getOrThrow("userDefineOutcome")

        /**
         * Event sent by the client providing the result of an agent-toolset tool execution. Only
         * valid on `self_hosted` environments, where sandbox-routed tools are executed by the
         * client rather than the server.
         */
        fun asUserToolResult(): BetaManagedAgentsUserToolResultEvent =
            userToolResult.getOrThrow("userToolResult")

        /**
         * A mid-conversation system message event. Carries system-role content that is appended to
         * the session as a `role: "system"` turn.
         */
        fun asSystemMessage(): BetaManagedAgentsSystemMessageEvent =
            systemMessage.getOrThrow("systemMessage")

        fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

        /**
         * Maps this instance's current variant to a value of type [T] using the given [visitor].
         *
         * Note that this method is _not_ forwards compatible with new variants from the API, unless
         * [visitor] overrides [Visitor.unknown]. To handle variants not known to this version of
         * the SDK gracefully, consider overriding [Visitor.unknown]:
         * ```java
         * import com.anthropic.core.JsonValue;
         * import java.util.Optional;
         *
         * Optional<String> result = data.accept(new Data.Visitor<Optional<String>>() {
         *     @Override
         *     public Optional<String> visitUserMessage(BetaManagedAgentsUserMessageEvent userMessage) {
         *         return Optional.of(userMessage.toString());
         *     }
         *
         *     // ...
         *
         *     @Override
         *     public Optional<String> unknown(JsonValue json) {
         *         // Or inspect the `json`.
         *         return Optional.empty();
         *     }
         * });
         * ```
         *
         * @throws AnthropicInvalidDataException if [Visitor.unknown] is not overridden in [visitor]
         *   and the current variant is unknown.
         */
        fun <T> accept(visitor: Visitor<T>): T =
            when {
                userMessage != null -> visitor.visitUserMessage(userMessage)
                userInterrupt != null -> visitor.visitUserInterrupt(userInterrupt)
                userToolConfirmation != null ->
                    visitor.visitUserToolConfirmation(userToolConfirmation)
                userCustomToolResult != null ->
                    visitor.visitUserCustomToolResult(userCustomToolResult)
                userDefineOutcome != null -> visitor.visitUserDefineOutcome(userDefineOutcome)
                userToolResult != null -> visitor.visitUserToolResult(userToolResult)
                systemMessage != null -> visitor.visitSystemMessage(systemMessage)
                else -> visitor.unknown(_json)
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
        fun validate(): Data = apply {
            if (validated) {
                return@apply
            }

            accept(
                object : Visitor<Unit> {
                    override fun visitUserMessage(userMessage: BetaManagedAgentsUserMessageEvent) {
                        userMessage.validate()
                    }

                    override fun visitUserInterrupt(
                        userInterrupt: BetaManagedAgentsUserInterruptEvent
                    ) {
                        userInterrupt.validate()
                    }

                    override fun visitUserToolConfirmation(
                        userToolConfirmation: BetaManagedAgentsUserToolConfirmationEvent
                    ) {
                        userToolConfirmation.validate()
                    }

                    override fun visitUserCustomToolResult(
                        userCustomToolResult: BetaManagedAgentsUserCustomToolResultEvent
                    ) {
                        userCustomToolResult.validate()
                    }

                    override fun visitUserDefineOutcome(
                        userDefineOutcome: BetaManagedAgentsUserDefineOutcomeEvent
                    ) {
                        userDefineOutcome.validate()
                    }

                    override fun visitUserToolResult(
                        userToolResult: BetaManagedAgentsUserToolResultEvent
                    ) {
                        userToolResult.validate()
                    }

                    override fun visitSystemMessage(
                        systemMessage: BetaManagedAgentsSystemMessageEvent
                    ) {
                        systemMessage.validate()
                    }
                }
            )
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
            accept(
                object : Visitor<Int> {
                    override fun visitUserMessage(userMessage: BetaManagedAgentsUserMessageEvent) =
                        userMessage.validity()

                    override fun visitUserInterrupt(
                        userInterrupt: BetaManagedAgentsUserInterruptEvent
                    ) = userInterrupt.validity()

                    override fun visitUserToolConfirmation(
                        userToolConfirmation: BetaManagedAgentsUserToolConfirmationEvent
                    ) = userToolConfirmation.validity()

                    override fun visitUserCustomToolResult(
                        userCustomToolResult: BetaManagedAgentsUserCustomToolResultEvent
                    ) = userCustomToolResult.validity()

                    override fun visitUserDefineOutcome(
                        userDefineOutcome: BetaManagedAgentsUserDefineOutcomeEvent
                    ) = userDefineOutcome.validity()

                    override fun visitUserToolResult(
                        userToolResult: BetaManagedAgentsUserToolResultEvent
                    ) = userToolResult.validity()

                    override fun visitSystemMessage(
                        systemMessage: BetaManagedAgentsSystemMessageEvent
                    ) = systemMessage.validity()

                    override fun unknown(json: JsonValue?) = 0
                }
            )

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Data &&
                userMessage == other.userMessage &&
                userInterrupt == other.userInterrupt &&
                userToolConfirmation == other.userToolConfirmation &&
                userCustomToolResult == other.userCustomToolResult &&
                userDefineOutcome == other.userDefineOutcome &&
                userToolResult == other.userToolResult &&
                systemMessage == other.systemMessage
        }

        override fun hashCode(): Int =
            Objects.hash(
                userMessage,
                userInterrupt,
                userToolConfirmation,
                userCustomToolResult,
                userDefineOutcome,
                userToolResult,
                systemMessage,
            )

        override fun toString(): String =
            when {
                userMessage != null -> "Data{userMessage=$userMessage}"
                userInterrupt != null -> "Data{userInterrupt=$userInterrupt}"
                userToolConfirmation != null -> "Data{userToolConfirmation=$userToolConfirmation}"
                userCustomToolResult != null -> "Data{userCustomToolResult=$userCustomToolResult}"
                userDefineOutcome != null -> "Data{userDefineOutcome=$userDefineOutcome}"
                userToolResult != null -> "Data{userToolResult=$userToolResult}"
                systemMessage != null -> "Data{systemMessage=$systemMessage}"
                _json != null -> "Data{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid Data")
            }

        companion object {

            /** A user message event in the session conversation. */
            @JvmStatic
            fun ofUserMessage(userMessage: BetaManagedAgentsUserMessageEvent) =
                Data(userMessage = userMessage)

            /** An interrupt event that pauses agent execution and returns control to the user. */
            @JvmStatic
            fun ofUserInterrupt(userInterrupt: BetaManagedAgentsUserInterruptEvent) =
                Data(userInterrupt = userInterrupt)

            /**
             * Returns an immutable instance of [Data] whose [ofUserInterrupt] variant is built from
             * the given required [id].
             */
            @JvmStatic
            fun ofUserInterrupt(id: String) =
                ofUserInterrupt(
                    BetaManagedAgentsUserInterruptEvent.builder()
                        .type(BetaManagedAgentsUserInterruptEvent.Type.USER_INTERRUPT)
                        .id(id)
                        .build()
                )

            /** A tool confirmation event that approves or denies a pending tool execution. */
            @JvmStatic
            fun ofUserToolConfirmation(
                userToolConfirmation: BetaManagedAgentsUserToolConfirmationEvent
            ) = Data(userToolConfirmation = userToolConfirmation)

            /** Event sent by the client providing the result of a custom tool execution. */
            @JvmStatic
            fun ofUserCustomToolResult(
                userCustomToolResult: BetaManagedAgentsUserCustomToolResultEvent
            ) = Data(userCustomToolResult = userCustomToolResult)

            /**
             * Echo of a `user.define_outcome` input event. Carries the server-generated
             * `outcome_id` that subsequent `span.outcome_evaluation_*` events reference.
             */
            @JvmStatic
            fun ofUserDefineOutcome(userDefineOutcome: BetaManagedAgentsUserDefineOutcomeEvent) =
                Data(userDefineOutcome = userDefineOutcome)

            /**
             * Event sent by the client providing the result of an agent-toolset tool execution.
             * Only valid on `self_hosted` environments, where sandbox-routed tools are executed by
             * the client rather than the server.
             */
            @JvmStatic
            fun ofUserToolResult(userToolResult: BetaManagedAgentsUserToolResultEvent) =
                Data(userToolResult = userToolResult)

            /**
             * A mid-conversation system message event. Carries system-role content that is appended
             * to the session as a `role: "system"` turn.
             */
            @JvmStatic
            fun ofSystemMessage(systemMessage: BetaManagedAgentsSystemMessageEvent) =
                Data(systemMessage = systemMessage)
        }

        /** An interface that defines how to map each variant of [Data] to a value of type [T]. */
        interface Visitor<out T> {

            /** A user message event in the session conversation. */
            fun visitUserMessage(userMessage: BetaManagedAgentsUserMessageEvent): T

            /** An interrupt event that pauses agent execution and returns control to the user. */
            fun visitUserInterrupt(userInterrupt: BetaManagedAgentsUserInterruptEvent): T

            /** A tool confirmation event that approves or denies a pending tool execution. */
            fun visitUserToolConfirmation(
                userToolConfirmation: BetaManagedAgentsUserToolConfirmationEvent
            ): T

            /** Event sent by the client providing the result of a custom tool execution. */
            fun visitUserCustomToolResult(
                userCustomToolResult: BetaManagedAgentsUserCustomToolResultEvent
            ): T

            /**
             * Echo of a `user.define_outcome` input event. Carries the server-generated
             * `outcome_id` that subsequent `span.outcome_evaluation_*` events reference.
             */
            fun visitUserDefineOutcome(
                userDefineOutcome: BetaManagedAgentsUserDefineOutcomeEvent
            ): T

            /**
             * Event sent by the client providing the result of an agent-toolset tool execution.
             * Only valid on `self_hosted` environments, where sandbox-routed tools are executed by
             * the client rather than the server.
             */
            fun visitUserToolResult(userToolResult: BetaManagedAgentsUserToolResultEvent): T

            /**
             * A mid-conversation system message event. Carries system-role content that is appended
             * to the session as a `role: "system"` turn.
             */
            fun visitSystemMessage(systemMessage: BetaManagedAgentsSystemMessageEvent): T

            /**
             * Maps an unknown variant of [Data] to a value of type [T].
             *
             * An instance of [Data] can contain an unknown variant if it was deserialized from data
             * that doesn't match any known variant. For example, if the SDK is on an older version
             * than the API, then the API may respond with new variants that the SDK is unaware of.
             *
             * @throws AnthropicInvalidDataException in the default implementation.
             */
            fun unknown(json: JsonValue?): T {
                throw AnthropicInvalidDataException("Unknown Data: $json")
            }
        }

        internal class Deserializer : BaseDeserializer<Data>(Data::class) {

            override fun ObjectCodec.deserialize(node: JsonNode): Data {
                val json = JsonValue.fromJsonNode(node)
                val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

                when (type) {
                    "user.message" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaManagedAgentsUserMessageEvent>(),
                            )
                            ?.let { Data(userMessage = it, _json = json) } ?: Data(_json = json)
                    }
                    "user.interrupt" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaManagedAgentsUserInterruptEvent>(),
                            )
                            ?.let { Data(userInterrupt = it, _json = json) } ?: Data(_json = json)
                    }
                    "user.tool_confirmation" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaManagedAgentsUserToolConfirmationEvent>(),
                            )
                            ?.let { Data(userToolConfirmation = it, _json = json) }
                            ?: Data(_json = json)
                    }
                    "user.custom_tool_result" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaManagedAgentsUserCustomToolResultEvent>(),
                            )
                            ?.let { Data(userCustomToolResult = it, _json = json) }
                            ?: Data(_json = json)
                    }
                    "user.define_outcome" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaManagedAgentsUserDefineOutcomeEvent>(),
                            )
                            ?.let { Data(userDefineOutcome = it, _json = json) }
                            ?: Data(_json = json)
                    }
                    "user.tool_result" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaManagedAgentsUserToolResultEvent>(),
                            )
                            ?.let { Data(userToolResult = it, _json = json) } ?: Data(_json = json)
                    }
                    "system.message" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaManagedAgentsSystemMessageEvent>(),
                            )
                            ?.let { Data(systemMessage = it, _json = json) } ?: Data(_json = json)
                    }
                }

                return Data(_json = json)
            }
        }

        internal class Serializer : BaseSerializer<Data>(Data::class) {

            override fun serialize(
                value: Data,
                generator: JsonGenerator,
                provider: SerializerProvider,
            ) {
                when {
                    value.userMessage != null -> generator.writeObject(value.userMessage)
                    value.userInterrupt != null -> generator.writeObject(value.userInterrupt)
                    value.userToolConfirmation != null ->
                        generator.writeObject(value.userToolConfirmation)
                    value.userCustomToolResult != null ->
                        generator.writeObject(value.userCustomToolResult)
                    value.userDefineOutcome != null ->
                        generator.writeObject(value.userDefineOutcome)
                    value.userToolResult != null -> generator.writeObject(value.userToolResult)
                    value.systemMessage != null -> generator.writeObject(value.systemMessage)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid Data")
                }
            }
        }

        class Type @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

            /**
             * Returns this class instance's raw value.
             *
             * This is usually only useful if this instance was deserialized from data that doesn't
             * match any known member, and you want to know that value. For example, if the SDK is
             * on an older version than the API, then the API may respond with new members that the
             * SDK is unaware of.
             */
            @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

            companion object {

                @JvmField val USER_MESSAGE = of("user.message")

                @JvmField val USER_INTERRUPT = of("user.interrupt")

                @JvmField val USER_TOOL_CONFIRMATION = of("user.tool_confirmation")

                @JvmField val USER_CUSTOM_TOOL_RESULT = of("user.custom_tool_result")

                @JvmField val USER_DEFINE_OUTCOME = of("user.define_outcome")

                @JvmField val USER_TOOL_RESULT = of("user.tool_result")

                @JvmField val SYSTEM_MESSAGE = of("system.message")

                @JvmStatic fun of(value: String) = Type(JsonField.of(value))

                @JvmSynthetic
                internal fun of(value: JsonField<String>): Type =
                    value.asString().getOrNull()?.let { of(it) } ?: Type(value)
            }

            /** An enum containing [Type]'s known values. */
            enum class Known {
                USER_MESSAGE,
                USER_INTERRUPT,
                USER_TOOL_CONFIRMATION,
                USER_CUSTOM_TOOL_RESULT,
                USER_DEFINE_OUTCOME,
                USER_TOOL_RESULT,
                SYSTEM_MESSAGE,
            }

            /**
             * An enum containing [Type]'s known values, as well as an [_UNKNOWN] member.
             *
             * An instance of [Type] can contain an unknown value in a couple of cases:
             * - It was deserialized from data that doesn't match any known member. For example, if
             *   the SDK is on an older version than the API, then the API may respond with new
             *   members that the SDK is unaware of.
             * - It was constructed with an arbitrary value using the [of] method.
             */
            enum class Value {
                USER_MESSAGE,
                USER_INTERRUPT,
                USER_TOOL_CONFIRMATION,
                USER_CUSTOM_TOOL_RESULT,
                USER_DEFINE_OUTCOME,
                USER_TOOL_RESULT,
                SYSTEM_MESSAGE,
                /** An enum member indicating that [Type] was instantiated with an unknown value. */
                _UNKNOWN,
            }

            /**
             * Returns an enum member corresponding to this class instance's value, or
             * [Value._UNKNOWN] if the class was instantiated with an unknown value.
             *
             * Use the [known] method instead if you're certain the value is always known or if you
             * want to throw for the unknown case.
             */
            fun value(): Value =
                when (this) {
                    USER_MESSAGE -> Value.USER_MESSAGE
                    USER_INTERRUPT -> Value.USER_INTERRUPT
                    USER_TOOL_CONFIRMATION -> Value.USER_TOOL_CONFIRMATION
                    USER_CUSTOM_TOOL_RESULT -> Value.USER_CUSTOM_TOOL_RESULT
                    USER_DEFINE_OUTCOME -> Value.USER_DEFINE_OUTCOME
                    USER_TOOL_RESULT -> Value.USER_TOOL_RESULT
                    SYSTEM_MESSAGE -> Value.SYSTEM_MESSAGE
                    else -> Value._UNKNOWN
                }

            /**
             * Returns an enum member corresponding to this class instance's value.
             *
             * Use the [value] method instead if you're uncertain the value is always known and
             * don't want to throw for the unknown case.
             *
             * @throws AnthropicInvalidDataException if this class instance's value is a not a known
             *   member.
             */
            fun known(): Known =
                when (this) {
                    USER_MESSAGE -> Known.USER_MESSAGE
                    USER_INTERRUPT -> Known.USER_INTERRUPT
                    USER_TOOL_CONFIRMATION -> Known.USER_TOOL_CONFIRMATION
                    USER_CUSTOM_TOOL_RESULT -> Known.USER_CUSTOM_TOOL_RESULT
                    USER_DEFINE_OUTCOME -> Known.USER_DEFINE_OUTCOME
                    USER_TOOL_RESULT -> Known.USER_TOOL_RESULT
                    SYSTEM_MESSAGE -> Known.SYSTEM_MESSAGE
                    else -> throw AnthropicInvalidDataException("Unknown Type: $value")
                }

            /**
             * Returns this class instance's primitive wire representation.
             *
             * This differs from the [toString] method because that method is primarily for
             * debugging and generally doesn't throw.
             *
             * @throws AnthropicInvalidDataException if this class instance's value does not have
             *   the expected primitive type.
             */
            fun asString(): String =
                _value().asString().orElseThrow {
                    AnthropicInvalidDataException("Value is not a String")
                }

            private var validated: Boolean = false

            /**
             * Validates that the types of all values in this object match their expected types
             * recursively.
             *
             * This method is _not_ forwards compatible with new types from the API for existing
             * fields.
             *
             * @throws AnthropicInvalidDataException if any value type in this object doesn't match
             *   its expected type.
             */
            fun validate(): Type = apply {
                if (validated) {
                    return@apply
                }

                known()
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
            @JvmSynthetic internal fun validity(): Int = if (value() == Value._UNKNOWN) 0 else 1

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is Type && value == other.value
            }

            override fun hashCode() = value.hashCode()

            override fun toString() = value.toString()
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsSendSessionEvents &&
            data == other.data &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(data, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsSendSessionEvents{data=$data, additionalProperties=$additionalProperties}"
}
