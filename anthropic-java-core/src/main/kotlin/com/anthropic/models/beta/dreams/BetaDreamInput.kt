// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.dreams

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.JsonValue
import com.anthropic.core.getOrThrow
import com.anthropic.errors.AnthropicInvalidDataException
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

/** An input memory store the dream reads from. The dream never mutates this store. */
@JsonDeserialize(using = BetaDreamInput.Deserializer::class)
@JsonSerialize(using = BetaDreamInput.Serializer::class)
class BetaDreamInput
private constructor(
    private val memoryStore: BetaDreamMemoryStoreInput? = null,
    private val sessions: BetaDreamSessionsInput? = null,
    private val _json: JsonValue? = null,
) {

    /** An input memory store the dream reads from. The dream never mutates this store. */
    fun memoryStore(): Optional<BetaDreamMemoryStoreInput> = Optional.ofNullable(memoryStore)

    /** Input session transcripts the dream reads. */
    fun sessions(): Optional<BetaDreamSessionsInput> = Optional.ofNullable(sessions)

    fun isMemoryStore(): Boolean = memoryStore != null

    fun isSessions(): Boolean = sessions != null

    /** An input memory store the dream reads from. The dream never mutates this store. */
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

        /** An input memory store the dream reads from. The dream never mutates this store. */
        @JvmStatic
        fun ofMemoryStore(memoryStore: BetaDreamMemoryStoreInput) =
            BetaDreamInput(memoryStore = memoryStore)

        /** Input session transcripts the dream reads. */
        @JvmStatic
        fun ofSessions(sessions: BetaDreamSessionsInput) = BetaDreamInput(sessions = sessions)
    }

    /**
     * An interface that defines how to map each variant of [BetaDreamInput] to a value of type [T].
     */
    interface Visitor<out T> {

        /** An input memory store the dream reads from. The dream never mutates this store. */
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
}
