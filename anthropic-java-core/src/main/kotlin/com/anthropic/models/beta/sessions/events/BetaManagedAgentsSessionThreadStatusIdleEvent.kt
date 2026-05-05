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
import java.time.OffsetDateTime
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/**
 * A session thread has yielded and is awaiting input. Emitted on the thread's own stream and
 * cross-posted to the primary stream for child threads.
 */
class BetaManagedAgentsSessionThreadStatusIdleEvent
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val id: JsonField<String>,
    private val agentName: JsonField<String>,
    private val processedAt: JsonField<OffsetDateTime>,
    private val sessionThreadId: JsonField<String>,
    private val stopReason: JsonField<StopReason>,
    private val type: JsonField<Type>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("agent_name") @ExcludeMissing agentName: JsonField<String> = JsonMissing.of(),
        @JsonProperty("processed_at")
        @ExcludeMissing
        processedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("session_thread_id")
        @ExcludeMissing
        sessionThreadId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("stop_reason")
        @ExcludeMissing
        stopReason: JsonField<StopReason> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonField<Type> = JsonMissing.of(),
    ) : this(id, agentName, processedAt, sessionThreadId, stopReason, type, mutableMapOf())

    /**
     * Unique identifier for this event.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun id(): String = id.getRequired("id")

    /**
     * Name of the agent the thread runs.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun agentName(): String = agentName.getRequired("agent_name")

    /**
     * A timestamp in RFC 3339 format
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun processedAt(): OffsetDateTime = processedAt.getRequired("processed_at")

    /**
     * Public sthr_ ID of the thread that went idle.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun sessionThreadId(): String = sessionThreadId.getRequired("session_thread_id")

    /**
     * The agent completed its turn naturally and is ready for the next user message.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun stopReason(): StopReason = stopReason.getRequired("stop_reason")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun type(): Type = type.getRequired("type")

    /**
     * Returns the raw JSON value of [id].
     *
     * Unlike [id], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

    /**
     * Returns the raw JSON value of [agentName].
     *
     * Unlike [agentName], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("agent_name") @ExcludeMissing fun _agentName(): JsonField<String> = agentName

    /**
     * Returns the raw JSON value of [processedAt].
     *
     * Unlike [processedAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("processed_at")
    @ExcludeMissing
    fun _processedAt(): JsonField<OffsetDateTime> = processedAt

    /**
     * Returns the raw JSON value of [sessionThreadId].
     *
     * Unlike [sessionThreadId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("session_thread_id")
    @ExcludeMissing
    fun _sessionThreadId(): JsonField<String> = sessionThreadId

    /**
     * Returns the raw JSON value of [stopReason].
     *
     * Unlike [stopReason], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("stop_reason")
    @ExcludeMissing
    fun _stopReason(): JsonField<StopReason> = stopReason

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
         * [BetaManagedAgentsSessionThreadStatusIdleEvent].
         *
         * The following fields are required:
         * ```java
         * .id()
         * .agentName()
         * .processedAt()
         * .sessionThreadId()
         * .stopReason()
         * .type()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaManagedAgentsSessionThreadStatusIdleEvent]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var agentName: JsonField<String>? = null
        private var processedAt: JsonField<OffsetDateTime>? = null
        private var sessionThreadId: JsonField<String>? = null
        private var stopReason: JsonField<StopReason>? = null
        private var type: JsonField<Type>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaManagedAgentsSessionThreadStatusIdleEvent:
                BetaManagedAgentsSessionThreadStatusIdleEvent
        ) = apply {
            id = betaManagedAgentsSessionThreadStatusIdleEvent.id
            agentName = betaManagedAgentsSessionThreadStatusIdleEvent.agentName
            processedAt = betaManagedAgentsSessionThreadStatusIdleEvent.processedAt
            sessionThreadId = betaManagedAgentsSessionThreadStatusIdleEvent.sessionThreadId
            stopReason = betaManagedAgentsSessionThreadStatusIdleEvent.stopReason
            type = betaManagedAgentsSessionThreadStatusIdleEvent.type
            additionalProperties =
                betaManagedAgentsSessionThreadStatusIdleEvent.additionalProperties.toMutableMap()
        }

        /** Unique identifier for this event. */
        fun id(id: String) = id(JsonField.of(id))

        /**
         * Sets [Builder.id] to an arbitrary JSON value.
         *
         * You should usually call [Builder.id] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun id(id: JsonField<String>) = apply { this.id = id }

        /** Name of the agent the thread runs. */
        fun agentName(agentName: String) = agentName(JsonField.of(agentName))

        /**
         * Sets [Builder.agentName] to an arbitrary JSON value.
         *
         * You should usually call [Builder.agentName] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun agentName(agentName: JsonField<String>) = apply { this.agentName = agentName }

        /** A timestamp in RFC 3339 format */
        fun processedAt(processedAt: OffsetDateTime) = processedAt(JsonField.of(processedAt))

        /**
         * Sets [Builder.processedAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.processedAt] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun processedAt(processedAt: JsonField<OffsetDateTime>) = apply {
            this.processedAt = processedAt
        }

        /** Public sthr_ ID of the thread that went idle. */
        fun sessionThreadId(sessionThreadId: String) =
            sessionThreadId(JsonField.of(sessionThreadId))

        /**
         * Sets [Builder.sessionThreadId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.sessionThreadId] with a well-typed [String] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun sessionThreadId(sessionThreadId: JsonField<String>) = apply {
            this.sessionThreadId = sessionThreadId
        }

        /** The agent completed its turn naturally and is ready for the next user message. */
        fun stopReason(stopReason: StopReason) = stopReason(JsonField.of(stopReason))

        /**
         * Sets [Builder.stopReason] to an arbitrary JSON value.
         *
         * You should usually call [Builder.stopReason] with a well-typed [StopReason] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun stopReason(stopReason: JsonField<StopReason>) = apply { this.stopReason = stopReason }

        /** Alias for calling [stopReason] with `StopReason.ofEndTurn(endTurn)`. */
        fun stopReason(endTurn: BetaManagedAgentsSessionEndTurn) =
            stopReason(StopReason.ofEndTurn(endTurn))

        /** Alias for calling [stopReason] with `StopReason.ofRequiresAction(requiresAction)`. */
        fun stopReason(requiresAction: BetaManagedAgentsSessionRequiresAction) =
            stopReason(StopReason.ofRequiresAction(requiresAction))

        /**
         * Alias for calling [stopReason] with the following:
         * ```java
         * BetaManagedAgentsSessionRequiresAction.builder()
         *     .type(BetaManagedAgentsSessionRequiresAction.Type.REQUIRES_ACTION)
         *     .eventIds(eventIds)
         *     .build()
         * ```
         */
        fun requiresActionStopReason(eventIds: List<String>) =
            stopReason(
                BetaManagedAgentsSessionRequiresAction.builder()
                    .type(BetaManagedAgentsSessionRequiresAction.Type.REQUIRES_ACTION)
                    .eventIds(eventIds)
                    .build()
            )

        /**
         * Alias for calling [stopReason] with `StopReason.ofRetriesExhausted(retriesExhausted)`.
         */
        fun stopReason(retriesExhausted: BetaManagedAgentsSessionRetriesExhausted) =
            stopReason(StopReason.ofRetriesExhausted(retriesExhausted))

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
         * Returns an immutable instance of [BetaManagedAgentsSessionThreadStatusIdleEvent].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .id()
         * .agentName()
         * .processedAt()
         * .sessionThreadId()
         * .stopReason()
         * .type()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaManagedAgentsSessionThreadStatusIdleEvent =
            BetaManagedAgentsSessionThreadStatusIdleEvent(
                checkRequired("id", id),
                checkRequired("agentName", agentName),
                checkRequired("processedAt", processedAt),
                checkRequired("sessionThreadId", sessionThreadId),
                checkRequired("stopReason", stopReason),
                checkRequired("type", type),
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
    fun validate(): BetaManagedAgentsSessionThreadStatusIdleEvent = apply {
        if (validated) {
            return@apply
        }

        id()
        agentName()
        processedAt()
        sessionThreadId()
        stopReason().validate()
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
        (if (id.asKnown().isPresent) 1 else 0) +
            (if (agentName.asKnown().isPresent) 1 else 0) +
            (if (processedAt.asKnown().isPresent) 1 else 0) +
            (if (sessionThreadId.asKnown().isPresent) 1 else 0) +
            (stopReason.asKnown().getOrNull()?.validity() ?: 0) +
            (type.asKnown().getOrNull()?.validity() ?: 0)

    /** The agent completed its turn naturally and is ready for the next user message. */
    @JsonDeserialize(using = StopReason.Deserializer::class)
    @JsonSerialize(using = StopReason.Serializer::class)
    class StopReason
    private constructor(
        private val endTurn: BetaManagedAgentsSessionEndTurn? = null,
        private val requiresAction: BetaManagedAgentsSessionRequiresAction? = null,
        private val retriesExhausted: BetaManagedAgentsSessionRetriesExhausted? = null,
        private val _json: JsonValue? = null,
    ) {

        /** The agent completed its turn naturally and is ready for the next user message. */
        fun endTurn(): Optional<BetaManagedAgentsSessionEndTurn> = Optional.ofNullable(endTurn)

        /**
         * The agent is idle waiting on one or more blocking user-input events (tool confirmation,
         * custom tool result, etc.). Resolving all of them transitions the session back to running.
         */
        fun requiresAction(): Optional<BetaManagedAgentsSessionRequiresAction> =
            Optional.ofNullable(requiresAction)

        /**
         * The turn ended because the retry budget was exhausted (`max_iterations` hit or an error
         * escalated to `retry_status: 'exhausted'`).
         */
        fun retriesExhausted(): Optional<BetaManagedAgentsSessionRetriesExhausted> =
            Optional.ofNullable(retriesExhausted)

        fun isEndTurn(): Boolean = endTurn != null

        fun isRequiresAction(): Boolean = requiresAction != null

        fun isRetriesExhausted(): Boolean = retriesExhausted != null

        /** The agent completed its turn naturally and is ready for the next user message. */
        fun asEndTurn(): BetaManagedAgentsSessionEndTurn = endTurn.getOrThrow("endTurn")

        /**
         * The agent is idle waiting on one or more blocking user-input events (tool confirmation,
         * custom tool result, etc.). Resolving all of them transitions the session back to running.
         */
        fun asRequiresAction(): BetaManagedAgentsSessionRequiresAction =
            requiresAction.getOrThrow("requiresAction")

        /**
         * The turn ended because the retry budget was exhausted (`max_iterations` hit or an error
         * escalated to `retry_status: 'exhausted'`).
         */
        fun asRetriesExhausted(): BetaManagedAgentsSessionRetriesExhausted =
            retriesExhausted.getOrThrow("retriesExhausted")

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
         * Optional<String> result = stopReason.accept(new StopReason.Visitor<Optional<String>>() {
         *     @Override
         *     public Optional<String> visitEndTurn(BetaManagedAgentsSessionEndTurn endTurn) {
         *         return Optional.of(endTurn.toString());
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
                endTurn != null -> visitor.visitEndTurn(endTurn)
                requiresAction != null -> visitor.visitRequiresAction(requiresAction)
                retriesExhausted != null -> visitor.visitRetriesExhausted(retriesExhausted)
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
        fun validate(): StopReason = apply {
            if (validated) {
                return@apply
            }

            accept(
                object : Visitor<Unit> {
                    override fun visitEndTurn(endTurn: BetaManagedAgentsSessionEndTurn) {
                        endTurn.validate()
                    }

                    override fun visitRequiresAction(
                        requiresAction: BetaManagedAgentsSessionRequiresAction
                    ) {
                        requiresAction.validate()
                    }

                    override fun visitRetriesExhausted(
                        retriesExhausted: BetaManagedAgentsSessionRetriesExhausted
                    ) {
                        retriesExhausted.validate()
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
                    override fun visitEndTurn(endTurn: BetaManagedAgentsSessionEndTurn) =
                        endTurn.validity()

                    override fun visitRequiresAction(
                        requiresAction: BetaManagedAgentsSessionRequiresAction
                    ) = requiresAction.validity()

                    override fun visitRetriesExhausted(
                        retriesExhausted: BetaManagedAgentsSessionRetriesExhausted
                    ) = retriesExhausted.validity()

                    override fun unknown(json: JsonValue?) = 0
                }
            )

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is StopReason &&
                endTurn == other.endTurn &&
                requiresAction == other.requiresAction &&
                retriesExhausted == other.retriesExhausted
        }

        override fun hashCode(): Int = Objects.hash(endTurn, requiresAction, retriesExhausted)

        override fun toString(): String =
            when {
                endTurn != null -> "StopReason{endTurn=$endTurn}"
                requiresAction != null -> "StopReason{requiresAction=$requiresAction}"
                retriesExhausted != null -> "StopReason{retriesExhausted=$retriesExhausted}"
                _json != null -> "StopReason{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid StopReason")
            }

        companion object {

            /** The agent completed its turn naturally and is ready for the next user message. */
            @JvmStatic
            fun ofEndTurn(endTurn: BetaManagedAgentsSessionEndTurn) = StopReason(endTurn = endTurn)

            /**
             * The agent is idle waiting on one or more blocking user-input events (tool
             * confirmation, custom tool result, etc.). Resolving all of them transitions the
             * session back to running.
             */
            @JvmStatic
            fun ofRequiresAction(requiresAction: BetaManagedAgentsSessionRequiresAction) =
                StopReason(requiresAction = requiresAction)

            /**
             * The turn ended because the retry budget was exhausted (`max_iterations` hit or an
             * error escalated to `retry_status: 'exhausted'`).
             */
            @JvmStatic
            fun ofRetriesExhausted(retriesExhausted: BetaManagedAgentsSessionRetriesExhausted) =
                StopReason(retriesExhausted = retriesExhausted)
        }

        /**
         * An interface that defines how to map each variant of [StopReason] to a value of type [T].
         */
        interface Visitor<out T> {

            /** The agent completed its turn naturally and is ready for the next user message. */
            fun visitEndTurn(endTurn: BetaManagedAgentsSessionEndTurn): T

            /**
             * The agent is idle waiting on one or more blocking user-input events (tool
             * confirmation, custom tool result, etc.). Resolving all of them transitions the
             * session back to running.
             */
            fun visitRequiresAction(requiresAction: BetaManagedAgentsSessionRequiresAction): T

            /**
             * The turn ended because the retry budget was exhausted (`max_iterations` hit or an
             * error escalated to `retry_status: 'exhausted'`).
             */
            fun visitRetriesExhausted(retriesExhausted: BetaManagedAgentsSessionRetriesExhausted): T

            /**
             * Maps an unknown variant of [StopReason] to a value of type [T].
             *
             * An instance of [StopReason] can contain an unknown variant if it was deserialized
             * from data that doesn't match any known variant. For example, if the SDK is on an
             * older version than the API, then the API may respond with new variants that the SDK
             * is unaware of.
             *
             * @throws AnthropicInvalidDataException in the default implementation.
             */
            fun unknown(json: JsonValue?): T {
                throw AnthropicInvalidDataException("Unknown StopReason: $json")
            }
        }

        internal class Deserializer : BaseDeserializer<StopReason>(StopReason::class) {

            override fun ObjectCodec.deserialize(node: JsonNode): StopReason {
                val json = JsonValue.fromJsonNode(node)
                val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

                when (type) {
                    "end_turn" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaManagedAgentsSessionEndTurn>(),
                            )
                            ?.let { StopReason(endTurn = it, _json = json) }
                            ?: StopReason(_json = json)
                    }
                    "requires_action" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaManagedAgentsSessionRequiresAction>(),
                            )
                            ?.let { StopReason(requiresAction = it, _json = json) }
                            ?: StopReason(_json = json)
                    }
                    "retries_exhausted" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaManagedAgentsSessionRetriesExhausted>(),
                            )
                            ?.let { StopReason(retriesExhausted = it, _json = json) }
                            ?: StopReason(_json = json)
                    }
                }

                return StopReason(_json = json)
            }
        }

        internal class Serializer : BaseSerializer<StopReason>(StopReason::class) {

            override fun serialize(
                value: StopReason,
                generator: JsonGenerator,
                provider: SerializerProvider,
            ) {
                when {
                    value.endTurn != null -> generator.writeObject(value.endTurn)
                    value.requiresAction != null -> generator.writeObject(value.requiresAction)
                    value.retriesExhausted != null -> generator.writeObject(value.retriesExhausted)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid StopReason")
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

            @JvmField val SESSION_THREAD_STATUS_IDLE = of("session.thread_status_idle")

            @JvmStatic fun of(value: String) = Type(JsonField.of(value))
        }

        /** An enum containing [Type]'s known values. */
        enum class Known {
            SESSION_THREAD_STATUS_IDLE
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
            SESSION_THREAD_STATUS_IDLE,
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
                SESSION_THREAD_STATUS_IDLE -> Value.SESSION_THREAD_STATUS_IDLE
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
                SESSION_THREAD_STATUS_IDLE -> Known.SESSION_THREAD_STATUS_IDLE
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

        /**
         * Validates that the types of all values in this object match their expected types
         * recursively.
         *
         * This method is _not_ forwards compatible with new types from the API for existing fields.
         *
         * @throws AnthropicInvalidDataException if any value type in this object doesn't match its
         *   expected type.
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

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsSessionThreadStatusIdleEvent &&
            id == other.id &&
            agentName == other.agentName &&
            processedAt == other.processedAt &&
            sessionThreadId == other.sessionThreadId &&
            stopReason == other.stopReason &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            id,
            agentName,
            processedAt,
            sessionThreadId,
            stopReason,
            type,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsSessionThreadStatusIdleEvent{id=$id, agentName=$agentName, processedAt=$processedAt, sessionThreadId=$sessionThreadId, stopReason=$stopReason, type=$type, additionalProperties=$additionalProperties}"
}
