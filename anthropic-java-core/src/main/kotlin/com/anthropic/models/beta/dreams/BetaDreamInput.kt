// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.dreams

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.Enum
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.getOrThrow
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.ObjectCodec
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/**
 * An input memory store the dream reads from. The dream never mutates this store unless it is also
 * the destination: with output_behavior {type: "update_existing"} the job consolidates this store
 * in place.
 */
@JsonDeserialize(using = BetaDreamInput.Deserializer::class)
@JsonSerialize(using = BetaDreamInput.Serializer::class)
class BetaDreamInput
private constructor(
    private val memoryStore: BetaDreamMemoryStoreInput? = null,
    private val sessions: BetaDreamSessionsInput? = null,
    private val _json: JsonValue? = null,
) {

    fun type(): Type =
        accept(
            object : Visitor<Type> {
                override fun visitMemoryStore(memoryStore: BetaDreamMemoryStoreInput): Type =
                    Type.MEMORY_STORE

                override fun visitSessions(sessions: BetaDreamSessionsInput): Type = Type.SESSIONS

                override fun unknown(json: JsonValue?): Type =
                    Type.of(json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
            }
        )

    /**
     * An input memory store the dream reads from. The dream never mutates this store unless it is
     * also the destination: with output_behavior {type: "update_existing"} the job consolidates
     * this store in place.
     */
    fun memoryStore(): Optional<BetaDreamMemoryStoreInput> = Optional.ofNullable(memoryStore)

    /** Input session transcripts the dream reads. */
    fun sessions(): Optional<BetaDreamSessionsInput> = Optional.ofNullable(sessions)

    fun isMemoryStore(): Boolean = memoryStore != null

    fun isSessions(): Boolean = sessions != null

    /**
     * An input memory store the dream reads from. The dream never mutates this store unless it is
     * also the destination: with output_behavior {type: "update_existing"} the job consolidates
     * this store in place.
     */
    fun asMemoryStore(): BetaDreamMemoryStoreInput = memoryStore.getOrThrow("memoryStore")

    /** Input session transcripts the dream reads. */
    fun asSessions(): BetaDreamSessionsInput = sessions.getOrThrow("sessions")

    fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

    /**
     * Maps this instance's current variant to a value of type [T] using the given [visitor].
     *
     * Note that this method is _not_ forwards compatible with new variants from the API, unless
     * [visitor] overrides [Visitor.unknown]. To handle variants not known to this version of the
     * SDK gracefully, consider overriding [Visitor.unknown]:
     * ```java
     * import com.anthropic.core.JsonValue;
     * import java.util.Optional;
     *
     * Optional<String> result = betaDreamInput.accept(new BetaDreamInput.Visitor<Optional<String>>() {
     *     @Override
     *     public Optional<String> visitMemoryStore(BetaDreamMemoryStoreInput memoryStore) {
     *         return Optional.of(memoryStore.toString());
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
     * @throws AnthropicInvalidDataException if [Visitor.unknown] is not overridden in [visitor] and
     *   the current variant is unknown.
     */
    fun <T> accept(visitor: Visitor<T>): T =
        when {
            memoryStore != null -> visitor.visitMemoryStore(memoryStore)
            sessions != null -> visitor.visitSessions(sessions)
            else -> visitor.unknown(_json)
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
    fun validate(): BetaDreamInput = apply {
        if (validated) {
            return@apply
        }

        accept(
            object : Visitor<Unit> {
                override fun visitMemoryStore(memoryStore: BetaDreamMemoryStoreInput) {
                    memoryStore.validate()
                }

                override fun visitSessions(sessions: BetaDreamSessionsInput) {
                    sessions.validate()
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
     * Returns a score indicating how many valid values are contained in this object recursively.
     *
     * Used for best match union deserialization.
     */
    @JvmSynthetic
    internal fun validity(): Int =
        accept(
            object : Visitor<Int> {
                override fun visitMemoryStore(memoryStore: BetaDreamMemoryStoreInput) =
                    memoryStore.validity()

                override fun visitSessions(sessions: BetaDreamSessionsInput) = sessions.validity()

                override fun unknown(json: JsonValue?) = 0
            }
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaDreamInput &&
            memoryStore == other.memoryStore &&
            sessions == other.sessions
    }

    override fun hashCode(): Int = Objects.hash(memoryStore, sessions)

    override fun toString(): String =
        when {
            memoryStore != null -> "BetaDreamInput{memoryStore=$memoryStore}"
            sessions != null -> "BetaDreamInput{sessions=$sessions}"
            _json != null -> "BetaDreamInput{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaDreamInput")
        }

    companion object {

        /**
         * An input memory store the dream reads from. The dream never mutates this store unless it
         * is also the destination: with output_behavior {type: "update_existing"} the job
         * consolidates this store in place.
         */
        @JvmStatic
        fun ofMemoryStore(memoryStore: BetaDreamMemoryStoreInput) =
            BetaDreamInput(memoryStore = memoryStore)

        /**
         * Returns an immutable instance of [BetaDreamInput] whose [ofMemoryStore] variant is built
         * from the given required [memoryStoreId].
         */
        @JvmStatic
        fun ofMemoryStore(memoryStoreId: String) =
            ofMemoryStore(
                BetaDreamMemoryStoreInput.builder()
                    .type(BetaDreamMemoryStoreInput.Type.MEMORY_STORE)
                    .memoryStoreId(memoryStoreId)
                    .build()
            )

        /** Input session transcripts the dream reads. */
        @JvmStatic
        fun ofSessions(sessions: BetaDreamSessionsInput) = BetaDreamInput(sessions = sessions)

        /**
         * Returns an immutable instance of [BetaDreamInput] whose [ofSessions] variant is built
         * from the given required [sessionIds].
         */
        @JvmStatic
        fun ofSessions(sessionIds: List<String>) =
            ofSessions(
                BetaDreamSessionsInput.builder()
                    .type(BetaDreamSessionsInput.Type.SESSIONS)
                    .sessionIds(sessionIds)
                    .build()
            )
    }

    /**
     * An interface that defines how to map each variant of [BetaDreamInput] to a value of type [T].
     */
    interface Visitor<out T> {

        /**
         * An input memory store the dream reads from. The dream never mutates this store unless it
         * is also the destination: with output_behavior {type: "update_existing"} the job
         * consolidates this store in place.
         */
        fun visitMemoryStore(memoryStore: BetaDreamMemoryStoreInput): T

        /** Input session transcripts the dream reads. */
        fun visitSessions(sessions: BetaDreamSessionsInput): T

        /**
         * Maps an unknown variant of [BetaDreamInput] to a value of type [T].
         *
         * An instance of [BetaDreamInput] can contain an unknown variant if it was deserialized
         * from data that doesn't match any known variant. For example, if the SDK is on an older
         * version than the API, then the API may respond with new variants that the SDK is unaware
         * of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown BetaDreamInput: $json")
        }
    }

    internal class Deserializer : BaseDeserializer<BetaDreamInput>(BetaDreamInput::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): BetaDreamInput {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "memory_store" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaDreamMemoryStoreInput>())?.let {
                        BetaDreamInput(memoryStore = it, _json = json)
                    } ?: BetaDreamInput(_json = json)
                }
                "sessions" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaDreamSessionsInput>())?.let {
                        BetaDreamInput(sessions = it, _json = json)
                    } ?: BetaDreamInput(_json = json)
                }
            }

            return BetaDreamInput(_json = json)
        }
    }

    internal class Serializer : BaseSerializer<BetaDreamInput>(BetaDreamInput::class) {

        override fun serialize(
            value: BetaDreamInput,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.memoryStore != null -> generator.writeObject(value.memoryStore)
                value.sessions != null -> generator.writeObject(value.sessions)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BetaDreamInput")
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

            @JvmField val MEMORY_STORE = of("memory_store")

            @JvmField val SESSIONS = of("sessions")

            @JvmStatic fun of(value: String) = Type(JsonField.of(value))

            @JvmSynthetic
            internal fun of(value: JsonField<String>): Type =
                value.asString().getOrNull()?.let { of(it) } ?: Type(value)
        }

        /** An enum containing [Type]'s known values. */
        enum class Known {
            MEMORY_STORE,
            SESSIONS,
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
            MEMORY_STORE,
            SESSIONS,
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
                MEMORY_STORE -> Value.MEMORY_STORE
                SESSIONS -> Value.SESSIONS
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
                MEMORY_STORE -> Known.MEMORY_STORE
                SESSIONS -> Known.SESSIONS
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
}
