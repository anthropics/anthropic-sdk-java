// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.events

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.Enum
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkRequired
import com.anthropic.core.getOrThrow
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

/** Failed to connect to an MCP server. */
class BetaManagedAgentsMcpConnectionFailedError
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val mcpServerName: JsonField<String>,
    private val message: JsonField<String>,
    private val retryStatus: JsonField<RetryStatus>,
    private val type: JsonField<Type>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("mcp_server_name")
        @ExcludeMissing
        mcpServerName: JsonField<String> = JsonMissing.of(),
        @JsonProperty("message") @ExcludeMissing message: JsonField<String> = JsonMissing.of(),
        @JsonProperty("retry_status")
        @ExcludeMissing
        retryStatus: JsonField<RetryStatus> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonField<Type> = JsonMissing.of(),
    ) : this(mcpServerName, message, retryStatus, type, mutableMapOf())

    /**
     * Name of the MCP server that failed to connect.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun mcpServerName(): String = mcpServerName.getRequired("mcp_server_name")

    /**
     * Human-readable error description.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun message(): String = message.getRequired("message")

    /**
     * What the client should do next in response to this error.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun retryStatus(): RetryStatus = retryStatus.getRequired("retry_status")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun type(): Type = type.getRequired("type")

    /**
     * Returns the raw JSON value of [mcpServerName].
     *
     * Unlike [mcpServerName], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("mcp_server_name")
    @ExcludeMissing
    fun _mcpServerName(): JsonField<String> = mcpServerName

    /**
     * Returns the raw JSON value of [message].
     *
     * Unlike [message], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("message") @ExcludeMissing fun _message(): JsonField<String> = message

    /**
     * Returns the raw JSON value of [retryStatus].
     *
     * Unlike [retryStatus], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("retry_status")
    @ExcludeMissing
    fun _retryStatus(): JsonField<RetryStatus> = retryStatus

    /**
     * Returns the raw JSON value of [type].
     *
     * Unlike [type], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonField<Type> = type

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
         * [BetaManagedAgentsMcpConnectionFailedError].
         *
         * The following fields are required:
         * ```java
         * .mcpServerName()
         * .message()
         * .retryStatus()
         * .type()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaManagedAgentsMcpConnectionFailedError]. */
    class Builder internal constructor() {

        private var mcpServerName: JsonField<String>? = null
        private var message: JsonField<String>? = null
        private var retryStatus: JsonField<RetryStatus>? = null
        private var type: JsonField<Type>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaManagedAgentsMcpConnectionFailedError: BetaManagedAgentsMcpConnectionFailedError
        ) = apply {
            mcpServerName = betaManagedAgentsMcpConnectionFailedError.mcpServerName
            message = betaManagedAgentsMcpConnectionFailedError.message
            retryStatus = betaManagedAgentsMcpConnectionFailedError.retryStatus
            type = betaManagedAgentsMcpConnectionFailedError.type
            additionalProperties =
                betaManagedAgentsMcpConnectionFailedError.additionalProperties.toMutableMap()
        }

        /** Name of the MCP server that failed to connect. */
        fun mcpServerName(mcpServerName: String) = mcpServerName(JsonField.of(mcpServerName))

        /**
         * Sets [Builder.mcpServerName] to an arbitrary JSON value.
         *
         * You should usually call [Builder.mcpServerName] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun mcpServerName(mcpServerName: JsonField<String>) = apply {
            this.mcpServerName = mcpServerName
        }

        /** Human-readable error description. */
        fun message(message: String) = message(JsonField.of(message))

        /**
         * Sets [Builder.message] to an arbitrary JSON value.
         *
         * You should usually call [Builder.message] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun message(message: JsonField<String>) = apply { this.message = message }

        /** What the client should do next in response to this error. */
        fun retryStatus(retryStatus: RetryStatus) = retryStatus(JsonField.of(retryStatus))

        /**
         * Sets [Builder.retryStatus] to an arbitrary JSON value.
         *
         * You should usually call [Builder.retryStatus] with a well-typed [RetryStatus] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun retryStatus(retryStatus: JsonField<RetryStatus>) = apply {
            this.retryStatus = retryStatus
        }

        /** Alias for calling [retryStatus] with `RetryStatus.ofRetrying(retrying)`. */
        fun retryStatus(retrying: BetaManagedAgentsRetryStatusRetrying) =
            retryStatus(RetryStatus.ofRetrying(retrying))

        /** Alias for calling [retryStatus] with `RetryStatus.ofExhausted(exhausted)`. */
        fun retryStatus(exhausted: BetaManagedAgentsRetryStatusExhausted) =
            retryStatus(RetryStatus.ofExhausted(exhausted))

        /** Alias for calling [retryStatus] with `RetryStatus.ofTerminal(terminal)`. */
        fun retryStatus(terminal: BetaManagedAgentsRetryStatusTerminal) =
            retryStatus(RetryStatus.ofTerminal(terminal))

        fun type(type: Type) = type(JsonField.of(type))

        /**
         * Sets [Builder.type] to an arbitrary JSON value.
         *
         * You should usually call [Builder.type] with a well-typed [Type] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun type(type: JsonField<Type>) = apply { this.type = type }

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
         * Returns an immutable instance of [BetaManagedAgentsMcpConnectionFailedError].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .mcpServerName()
         * .message()
         * .retryStatus()
         * .type()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaManagedAgentsMcpConnectionFailedError =
            BetaManagedAgentsMcpConnectionFailedError(
                checkRequired("mcpServerName", mcpServerName),
                checkRequired("message", message),
                checkRequired("retryStatus", retryStatus),
                checkRequired("type", type),
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): BetaManagedAgentsMcpConnectionFailedError = apply {
        if (validated) {
            return@apply
        }

        mcpServerName()
        message()
        retryStatus().validate()
        type().validate()
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
        (if (mcpServerName.asKnown().isPresent) 1 else 0) +
            (if (message.asKnown().isPresent) 1 else 0) +
            (retryStatus.asKnown().getOrNull()?.validity() ?: 0) +
            (type.asKnown().getOrNull()?.validity() ?: 0)

    /** What the client should do next in response to this error. */
    @JsonDeserialize(using = RetryStatus.Deserializer::class)
    @JsonSerialize(using = RetryStatus.Serializer::class)
    class RetryStatus
    private constructor(
        private val retrying: BetaManagedAgentsRetryStatusRetrying? = null,
        private val exhausted: BetaManagedAgentsRetryStatusExhausted? = null,
        private val terminal: BetaManagedAgentsRetryStatusTerminal? = null,
        private val _json: JsonValue? = null,
    ) {

        /**
         * The server is retrying automatically. Client should wait; the same error type may fire
         * again as retrying, then once as exhausted when the retry budget runs out.
         */
        fun retrying(): Optional<BetaManagedAgentsRetryStatusRetrying> =
            Optional.ofNullable(retrying)

        /**
         * This turn is dead; queued inputs are flushed and the session returns to idle. Client may
         * send a new prompt.
         */
        fun exhausted(): Optional<BetaManagedAgentsRetryStatusExhausted> =
            Optional.ofNullable(exhausted)

        /** The session encountered a terminal error and will transition to `terminated` state. */
        fun terminal(): Optional<BetaManagedAgentsRetryStatusTerminal> =
            Optional.ofNullable(terminal)

        fun isRetrying(): Boolean = retrying != null

        fun isExhausted(): Boolean = exhausted != null

        fun isTerminal(): Boolean = terminal != null

        /**
         * The server is retrying automatically. Client should wait; the same error type may fire
         * again as retrying, then once as exhausted when the retry budget runs out.
         */
        fun asRetrying(): BetaManagedAgentsRetryStatusRetrying = retrying.getOrThrow("retrying")

        /**
         * This turn is dead; queued inputs are flushed and the session returns to idle. Client may
         * send a new prompt.
         */
        fun asExhausted(): BetaManagedAgentsRetryStatusExhausted = exhausted.getOrThrow("exhausted")

        /** The session encountered a terminal error and will transition to `terminated` state. */
        fun asTerminal(): BetaManagedAgentsRetryStatusTerminal = terminal.getOrThrow("terminal")

        fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

        fun <T> accept(visitor: Visitor<T>): T =
            when {
                retrying != null -> visitor.visitRetrying(retrying)
                exhausted != null -> visitor.visitExhausted(exhausted)
                terminal != null -> visitor.visitTerminal(terminal)
                else -> visitor.unknown(_json)
            }

        private var validated: Boolean = false

        fun validate(): RetryStatus = apply {
            if (validated) {
                return@apply
            }

            accept(
                object : Visitor<Unit> {
                    override fun visitRetrying(retrying: BetaManagedAgentsRetryStatusRetrying) {
                        retrying.validate()
                    }

                    override fun visitExhausted(exhausted: BetaManagedAgentsRetryStatusExhausted) {
                        exhausted.validate()
                    }

                    override fun visitTerminal(terminal: BetaManagedAgentsRetryStatusTerminal) {
                        terminal.validate()
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
                    override fun visitRetrying(retrying: BetaManagedAgentsRetryStatusRetrying) =
                        retrying.validity()

                    override fun visitExhausted(exhausted: BetaManagedAgentsRetryStatusExhausted) =
                        exhausted.validity()

                    override fun visitTerminal(terminal: BetaManagedAgentsRetryStatusTerminal) =
                        terminal.validity()

                    override fun unknown(json: JsonValue?) = 0
                }
            )

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is RetryStatus &&
                retrying == other.retrying &&
                exhausted == other.exhausted &&
                terminal == other.terminal
        }

        override fun hashCode(): Int = Objects.hash(retrying, exhausted, terminal)

        override fun toString(): String =
            when {
                retrying != null -> "RetryStatus{retrying=$retrying}"
                exhausted != null -> "RetryStatus{exhausted=$exhausted}"
                terminal != null -> "RetryStatus{terminal=$terminal}"
                _json != null -> "RetryStatus{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid RetryStatus")
            }

        companion object {

            /**
             * The server is retrying automatically. Client should wait; the same error type may
             * fire again as retrying, then once as exhausted when the retry budget runs out.
             */
            @JvmStatic
            fun ofRetrying(retrying: BetaManagedAgentsRetryStatusRetrying) =
                RetryStatus(retrying = retrying)

            /**
             * This turn is dead; queued inputs are flushed and the session returns to idle. Client
             * may send a new prompt.
             */
            @JvmStatic
            fun ofExhausted(exhausted: BetaManagedAgentsRetryStatusExhausted) =
                RetryStatus(exhausted = exhausted)

            /**
             * The session encountered a terminal error and will transition to `terminated` state.
             */
            @JvmStatic
            fun ofTerminal(terminal: BetaManagedAgentsRetryStatusTerminal) =
                RetryStatus(terminal = terminal)
        }

        /**
         * An interface that defines how to map each variant of [RetryStatus] to a value of type
         * [T].
         */
        interface Visitor<out T> {

            /**
             * The server is retrying automatically. Client should wait; the same error type may
             * fire again as retrying, then once as exhausted when the retry budget runs out.
             */
            fun visitRetrying(retrying: BetaManagedAgentsRetryStatusRetrying): T

            /**
             * This turn is dead; queued inputs are flushed and the session returns to idle. Client
             * may send a new prompt.
             */
            fun visitExhausted(exhausted: BetaManagedAgentsRetryStatusExhausted): T

            /**
             * The session encountered a terminal error and will transition to `terminated` state.
             */
            fun visitTerminal(terminal: BetaManagedAgentsRetryStatusTerminal): T

            /**
             * Maps an unknown variant of [RetryStatus] to a value of type [T].
             *
             * An instance of [RetryStatus] can contain an unknown variant if it was deserialized
             * from data that doesn't match any known variant. For example, if the SDK is on an
             * older version than the API, then the API may respond with new variants that the SDK
             * is unaware of.
             *
             * @throws AnthropicInvalidDataException in the default implementation.
             */
            fun unknown(json: JsonValue?): T {
                throw AnthropicInvalidDataException("Unknown RetryStatus: $json")
            }
        }

        internal class Deserializer : BaseDeserializer<RetryStatus>(RetryStatus::class) {

            override fun ObjectCodec.deserialize(node: JsonNode): RetryStatus {
                val json = JsonValue.fromJsonNode(node)
                val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

                when (type) {
                    "retrying" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaManagedAgentsRetryStatusRetrying>(),
                            )
                            ?.let { RetryStatus(retrying = it, _json = json) }
                            ?: RetryStatus(_json = json)
                    }
                    "exhausted" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaManagedAgentsRetryStatusExhausted>(),
                            )
                            ?.let { RetryStatus(exhausted = it, _json = json) }
                            ?: RetryStatus(_json = json)
                    }
                    "terminal" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaManagedAgentsRetryStatusTerminal>(),
                            )
                            ?.let { RetryStatus(terminal = it, _json = json) }
                            ?: RetryStatus(_json = json)
                    }
                }

                return RetryStatus(_json = json)
            }
        }

        internal class Serializer : BaseSerializer<RetryStatus>(RetryStatus::class) {

            override fun serialize(
                value: RetryStatus,
                generator: JsonGenerator,
                provider: SerializerProvider,
            ) {
                when {
                    value.retrying != null -> generator.writeObject(value.retrying)
                    value.exhausted != null -> generator.writeObject(value.exhausted)
                    value.terminal != null -> generator.writeObject(value.terminal)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid RetryStatus")
                }
            }
        }
    }

    class Type @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

        /**
         * Returns this class instance's raw value.
         *
         * This is usually only useful if this instance was deserialized from data that doesn't
         * match any known member, and you want to know that value. For example, if the SDK is on an
         * older version than the API, then the API may respond with new members that the SDK is
         * unaware of.
         */
        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            @JvmField val MCP_CONNECTION_FAILED_ERROR = of("mcp_connection_failed_error")

            @JvmStatic fun of(value: String) = Type(JsonField.of(value))
        }

        /** An enum containing [Type]'s known values. */
        enum class Known {
            MCP_CONNECTION_FAILED_ERROR
        }

        /**
         * An enum containing [Type]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [Type] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            MCP_CONNECTION_FAILED_ERROR,
            /** An enum member indicating that [Type] was instantiated with an unknown value. */
            _UNKNOWN,
        }

        /**
         * Returns an enum member corresponding to this class instance's value, or [Value._UNKNOWN]
         * if the class was instantiated with an unknown value.
         *
         * Use the [known] method instead if you're certain the value is always known or if you want
         * to throw for the unknown case.
         */
        fun value(): Value =
            when (this) {
                MCP_CONNECTION_FAILED_ERROR -> Value.MCP_CONNECTION_FAILED_ERROR
                else -> Value._UNKNOWN
            }

        /**
         * Returns an enum member corresponding to this class instance's value.
         *
         * Use the [value] method instead if you're uncertain the value is always known and don't
         * want to throw for the unknown case.
         *
         * @throws AnthropicInvalidDataException if this class instance's value is a not a known
         *   member.
         */
        fun known(): Known =
            when (this) {
                MCP_CONNECTION_FAILED_ERROR -> Known.MCP_CONNECTION_FAILED_ERROR
                else -> throw AnthropicInvalidDataException("Unknown Type: $value")
            }

        /**
         * Returns this class instance's primitive wire representation.
         *
         * This differs from the [toString] method because that method is primarily for debugging
         * and generally doesn't throw.
         *
         * @throws AnthropicInvalidDataException if this class instance's value does not have the
         *   expected primitive type.
         */
        fun asString(): String =
            _value().asString().orElseThrow {
                AnthropicInvalidDataException("Value is not a String")
            }

        private var validated: Boolean = false

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

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsMcpConnectionFailedError &&
            mcpServerName == other.mcpServerName &&
            message == other.message &&
            retryStatus == other.retryStatus &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(mcpServerName, message, retryStatus, type, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsMcpConnectionFailedError{mcpServerName=$mcpServerName, message=$message, retryStatus=$retryStatus, type=$type, additionalProperties=$additionalProperties}"
}
