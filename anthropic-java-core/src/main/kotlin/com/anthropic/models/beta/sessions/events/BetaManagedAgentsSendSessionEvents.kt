// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.events

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkKnown
import com.anthropic.core.getOrThrow
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
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
                data = betaManagedAgentsSendSessionEvents.data.map { it.toMutableList() }
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
        private val _json: JsonValue? = null,
    ) {

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

        fun isUserMessage(): Boolean = userMessage != null

        fun isUserInterrupt(): Boolean = userInterrupt != null

        fun isUserToolConfirmation(): Boolean = userToolConfirmation != null

        fun isUserCustomToolResult(): Boolean = userCustomToolResult != null

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

        fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

        fun <T> accept(visitor: Visitor<T>): T =
            when {
                userMessage != null -> visitor.visitUserMessage(userMessage)
                userInterrupt != null -> visitor.visitUserInterrupt(userInterrupt)
                userToolConfirmation != null ->
                    visitor.visitUserToolConfirmation(userToolConfirmation)
                userCustomToolResult != null ->
                    visitor.visitUserCustomToolResult(userCustomToolResult)
                else -> visitor.unknown(_json)
            }

        private var validated: Boolean = false

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
                userCustomToolResult == other.userCustomToolResult
        }

        override fun hashCode(): Int =
            Objects.hash(userMessage, userInterrupt, userToolConfirmation, userCustomToolResult)

        override fun toString(): String =
            when {
                userMessage != null -> "Data{userMessage=$userMessage}"
                userInterrupt != null -> "Data{userInterrupt=$userInterrupt}"
                userToolConfirmation != null -> "Data{userToolConfirmation=$userToolConfirmation}"
                userCustomToolResult != null -> "Data{userCustomToolResult=$userCustomToolResult}"
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
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid Data")
                }
            }
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
