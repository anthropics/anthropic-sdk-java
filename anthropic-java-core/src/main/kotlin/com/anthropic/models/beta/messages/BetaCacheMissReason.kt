package com.anthropic.models.beta.messages

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.Enum
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.getOrThrow
import com.anthropic.core.getProperty
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

@JsonDeserialize(using = BetaCacheMissReason.Deserializer::class)
@JsonSerialize(using = BetaCacheMissReason.Serializer::class)
class BetaCacheMissReason
private constructor(
    private val modelChanged: BetaCacheMissModelChanged? = null,
    private val systemChanged: BetaCacheMissSystemChanged? = null,
    private val toolsChanged: BetaCacheMissToolsChanged? = null,
    private val messagesChanged: BetaCacheMissMessagesChanged? = null,
    private val previousMessageNotFound: BetaCacheMissPreviousMessageNotFound? = null,
    private val unavailable: BetaCacheMissUnavailable? = null,
    private val _json: JsonValue? = null,
) {

    fun type(): Type =
        accept(
            object : Visitor<Type> {
                override fun visitModelChanged(modelChanged: BetaCacheMissModelChanged): Type =
                    Type.MODEL_CHANGED

                override fun visitSystemChanged(systemChanged: BetaCacheMissSystemChanged): Type =
                    Type.SYSTEM_CHANGED

                override fun visitToolsChanged(toolsChanged: BetaCacheMissToolsChanged): Type =
                    Type.TOOLS_CHANGED

                override fun visitMessagesChanged(
                    messagesChanged: BetaCacheMissMessagesChanged
                ): Type = Type.MESSAGES_CHANGED

                override fun visitPreviousMessageNotFound(
                    previousMessageNotFound: BetaCacheMissPreviousMessageNotFound
                ): Type = Type.PREVIOUS_MESSAGE_NOT_FOUND

                override fun visitUnavailable(unavailable: BetaCacheMissUnavailable): Type =
                    Type.UNAVAILABLE

                override fun unknown(json: JsonValue?): Type =
                    Type.of(json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
            }
        )

    fun cacheMissedInputTokens(): Optional<Long> =
        accept(
            object : Visitor<Optional<Long>> {
                override fun visitModelChanged(
                    modelChanged: BetaCacheMissModelChanged
                ): Optional<Long> = Optional.of(modelChanged.cacheMissedInputTokens())

                override fun visitSystemChanged(
                    systemChanged: BetaCacheMissSystemChanged
                ): Optional<Long> = Optional.of(systemChanged.cacheMissedInputTokens())

                override fun visitToolsChanged(
                    toolsChanged: BetaCacheMissToolsChanged
                ): Optional<Long> = Optional.of(toolsChanged.cacheMissedInputTokens())

                override fun visitMessagesChanged(
                    messagesChanged: BetaCacheMissMessagesChanged
                ): Optional<Long> = Optional.of(messagesChanged.cacheMissedInputTokens())

                override fun visitPreviousMessageNotFound(
                    previousMessageNotFound: BetaCacheMissPreviousMessageNotFound
                ): Optional<Long> = Optional.empty()

                override fun visitUnavailable(
                    unavailable: BetaCacheMissUnavailable
                ): Optional<Long> = Optional.empty()

                override fun unknown(json: JsonValue?): Optional<Long> =
                    json.getProperty<Long>("cache_missed_input_tokens").asKnown()
            }
        )

    fun modelChanged(): Optional<BetaCacheMissModelChanged> = Optional.ofNullable(modelChanged)

    fun systemChanged(): Optional<BetaCacheMissSystemChanged> = Optional.ofNullable(systemChanged)

    fun toolsChanged(): Optional<BetaCacheMissToolsChanged> = Optional.ofNullable(toolsChanged)

    fun messagesChanged(): Optional<BetaCacheMissMessagesChanged> =
        Optional.ofNullable(messagesChanged)

    fun previousMessageNotFound(): Optional<BetaCacheMissPreviousMessageNotFound> =
        Optional.ofNullable(previousMessageNotFound)

    fun unavailable(): Optional<BetaCacheMissUnavailable> = Optional.ofNullable(unavailable)

    fun isModelChanged(): Boolean = modelChanged != null

    fun isSystemChanged(): Boolean = systemChanged != null

    fun isToolsChanged(): Boolean = toolsChanged != null

    fun isMessagesChanged(): Boolean = messagesChanged != null

    fun isPreviousMessageNotFound(): Boolean = previousMessageNotFound != null

    fun isUnavailable(): Boolean = unavailable != null

    fun asModelChanged(): BetaCacheMissModelChanged = modelChanged.getOrThrow("modelChanged")

    fun asSystemChanged(): BetaCacheMissSystemChanged = systemChanged.getOrThrow("systemChanged")

    fun asToolsChanged(): BetaCacheMissToolsChanged = toolsChanged.getOrThrow("toolsChanged")

    fun asMessagesChanged(): BetaCacheMissMessagesChanged =
        messagesChanged.getOrThrow("messagesChanged")

    fun asPreviousMessageNotFound(): BetaCacheMissPreviousMessageNotFound =
        previousMessageNotFound.getOrThrow("previousMessageNotFound")

    fun asUnavailable(): BetaCacheMissUnavailable = unavailable.getOrThrow("unavailable")

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
     * Optional<String> result = betaCacheMissReason.accept(new BetaCacheMissReason.Visitor<Optional<String>>() {
     *     @Override
     *     public Optional<String> visitModelChanged(BetaCacheMissModelChanged modelChanged) {
     *         return Optional.of(modelChanged.toString());
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
            modelChanged != null -> visitor.visitModelChanged(modelChanged)
            systemChanged != null -> visitor.visitSystemChanged(systemChanged)
            toolsChanged != null -> visitor.visitToolsChanged(toolsChanged)
            messagesChanged != null -> visitor.visitMessagesChanged(messagesChanged)
            previousMessageNotFound != null ->
                visitor.visitPreviousMessageNotFound(previousMessageNotFound)
            unavailable != null -> visitor.visitUnavailable(unavailable)
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
    fun validate(): BetaCacheMissReason = apply {
        if (validated) {
            return@apply
        }

        accept(
            object : Visitor<Unit> {
                override fun visitModelChanged(modelChanged: BetaCacheMissModelChanged) {
                    modelChanged.validate()
                }

                override fun visitSystemChanged(systemChanged: BetaCacheMissSystemChanged) {
                    systemChanged.validate()
                }

                override fun visitToolsChanged(toolsChanged: BetaCacheMissToolsChanged) {
                    toolsChanged.validate()
                }

                override fun visitMessagesChanged(messagesChanged: BetaCacheMissMessagesChanged) {
                    messagesChanged.validate()
                }

                override fun visitPreviousMessageNotFound(
                    previousMessageNotFound: BetaCacheMissPreviousMessageNotFound
                ) {
                    previousMessageNotFound.validate()
                }

                override fun visitUnavailable(unavailable: BetaCacheMissUnavailable) {
                    unavailable.validate()
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
                override fun visitModelChanged(modelChanged: BetaCacheMissModelChanged) =
                    modelChanged.validity()

                override fun visitSystemChanged(systemChanged: BetaCacheMissSystemChanged) =
                    systemChanged.validity()

                override fun visitToolsChanged(toolsChanged: BetaCacheMissToolsChanged) =
                    toolsChanged.validity()

                override fun visitMessagesChanged(messagesChanged: BetaCacheMissMessagesChanged) =
                    messagesChanged.validity()

                override fun visitPreviousMessageNotFound(
                    previousMessageNotFound: BetaCacheMissPreviousMessageNotFound
                ) = previousMessageNotFound.validity()

                override fun visitUnavailable(unavailable: BetaCacheMissUnavailable) =
                    unavailable.validity()

                override fun unknown(json: JsonValue?) = 0
            }
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaCacheMissReason &&
            modelChanged == other.modelChanged &&
            systemChanged == other.systemChanged &&
            toolsChanged == other.toolsChanged &&
            messagesChanged == other.messagesChanged &&
            previousMessageNotFound == other.previousMessageNotFound &&
            unavailable == other.unavailable
    }

    override fun hashCode(): Int =
        Objects.hash(
            modelChanged,
            systemChanged,
            toolsChanged,
            messagesChanged,
            previousMessageNotFound,
            unavailable,
        )

    override fun toString(): String =
        when {
            modelChanged != null -> "BetaCacheMissReason{modelChanged=$modelChanged}"
            systemChanged != null -> "BetaCacheMissReason{systemChanged=$systemChanged}"
            toolsChanged != null -> "BetaCacheMissReason{toolsChanged=$toolsChanged}"
            messagesChanged != null -> "BetaCacheMissReason{messagesChanged=$messagesChanged}"
            previousMessageNotFound != null ->
                "BetaCacheMissReason{previousMessageNotFound=$previousMessageNotFound}"
            unavailable != null -> "BetaCacheMissReason{unavailable=$unavailable}"
            _json != null -> "BetaCacheMissReason{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaCacheMissReason")
        }

    companion object {

        @JvmStatic
        fun ofModelChanged(modelChanged: BetaCacheMissModelChanged) =
            BetaCacheMissReason(modelChanged = modelChanged)

        /**
         * Returns an immutable instance of [BetaCacheMissReason] whose [ofModelChanged] variant is
         * built from the given required [cacheMissedInputTokens].
         */
        @JvmStatic
        fun ofModelChanged(cacheMissedInputTokens: Long) =
            ofModelChanged(BetaCacheMissModelChanged.of(cacheMissedInputTokens))

        @JvmStatic
        fun ofSystemChanged(systemChanged: BetaCacheMissSystemChanged) =
            BetaCacheMissReason(systemChanged = systemChanged)

        /**
         * Returns an immutable instance of [BetaCacheMissReason] whose [ofSystemChanged] variant is
         * built from the given required [cacheMissedInputTokens].
         */
        @JvmStatic
        fun ofSystemChanged(cacheMissedInputTokens: Long) =
            ofSystemChanged(BetaCacheMissSystemChanged.of(cacheMissedInputTokens))

        @JvmStatic
        fun ofToolsChanged(toolsChanged: BetaCacheMissToolsChanged) =
            BetaCacheMissReason(toolsChanged = toolsChanged)

        /**
         * Returns an immutable instance of [BetaCacheMissReason] whose [ofToolsChanged] variant is
         * built from the given required [cacheMissedInputTokens].
         */
        @JvmStatic
        fun ofToolsChanged(cacheMissedInputTokens: Long) =
            ofToolsChanged(BetaCacheMissToolsChanged.of(cacheMissedInputTokens))

        @JvmStatic
        fun ofMessagesChanged(messagesChanged: BetaCacheMissMessagesChanged) =
            BetaCacheMissReason(messagesChanged = messagesChanged)

        /**
         * Returns an immutable instance of [BetaCacheMissReason] whose [ofMessagesChanged] variant
         * is built from the given required [cacheMissedInputTokens].
         */
        @JvmStatic
        fun ofMessagesChanged(cacheMissedInputTokens: Long) =
            ofMessagesChanged(BetaCacheMissMessagesChanged.of(cacheMissedInputTokens))

        @JvmStatic
        fun ofPreviousMessageNotFound(
            previousMessageNotFound: BetaCacheMissPreviousMessageNotFound
        ) = BetaCacheMissReason(previousMessageNotFound = previousMessageNotFound)

        @JvmStatic
        fun ofUnavailable(unavailable: BetaCacheMissUnavailable) =
            BetaCacheMissReason(unavailable = unavailable)
    }

    /**
     * An interface that defines how to map each variant of [BetaCacheMissReason] to a value of type
     * [T].
     */
    interface Visitor<out T> {

        fun visitModelChanged(modelChanged: BetaCacheMissModelChanged): T

        fun visitSystemChanged(systemChanged: BetaCacheMissSystemChanged): T

        fun visitToolsChanged(toolsChanged: BetaCacheMissToolsChanged): T

        fun visitMessagesChanged(messagesChanged: BetaCacheMissMessagesChanged): T

        fun visitPreviousMessageNotFound(
            previousMessageNotFound: BetaCacheMissPreviousMessageNotFound
        ): T

        fun visitUnavailable(unavailable: BetaCacheMissUnavailable): T

        /**
         * Maps an unknown variant of [BetaCacheMissReason] to a value of type [T].
         *
         * An instance of [BetaCacheMissReason] can contain an unknown variant if it was
         * deserialized from data that doesn't match any known variant. For example, if the SDK is
         * on an older version than the API, then the API may respond with new variants that the SDK
         * is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown BetaCacheMissReason: $json")
        }
    }

    internal class Deserializer :
        BaseDeserializer<BetaCacheMissReason>(BetaCacheMissReason::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): BetaCacheMissReason {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "model_changed" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaCacheMissModelChanged>())?.let {
                        BetaCacheMissReason(modelChanged = it, _json = json)
                    } ?: BetaCacheMissReason(_json = json)
                }
                "system_changed" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaCacheMissSystemChanged>())?.let {
                        BetaCacheMissReason(systemChanged = it, _json = json)
                    } ?: BetaCacheMissReason(_json = json)
                }
                "tools_changed" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaCacheMissToolsChanged>())?.let {
                        BetaCacheMissReason(toolsChanged = it, _json = json)
                    } ?: BetaCacheMissReason(_json = json)
                }
                "messages_changed" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaCacheMissMessagesChanged>())
                        ?.let { BetaCacheMissReason(messagesChanged = it, _json = json) }
                        ?: BetaCacheMissReason(_json = json)
                }
                "previous_message_not_found" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaCacheMissPreviousMessageNotFound>(),
                        )
                        ?.let { BetaCacheMissReason(previousMessageNotFound = it, _json = json) }
                        ?: BetaCacheMissReason(_json = json)
                }
                "unavailable" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaCacheMissUnavailable>())?.let {
                        BetaCacheMissReason(unavailable = it, _json = json)
                    } ?: BetaCacheMissReason(_json = json)
                }
            }

            return BetaCacheMissReason(_json = json)
        }
    }

    internal class Serializer : BaseSerializer<BetaCacheMissReason>(BetaCacheMissReason::class) {

        override fun serialize(
            value: BetaCacheMissReason,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.modelChanged != null -> generator.writeObject(value.modelChanged)
                value.systemChanged != null -> generator.writeObject(value.systemChanged)
                value.toolsChanged != null -> generator.writeObject(value.toolsChanged)
                value.messagesChanged != null -> generator.writeObject(value.messagesChanged)
                value.previousMessageNotFound != null ->
                    generator.writeObject(value.previousMessageNotFound)
                value.unavailable != null -> generator.writeObject(value.unavailable)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BetaCacheMissReason")
            }
        }
    }

    class Type private constructor(private val value: JsonField<String>) : Enum {

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

            @JvmField val MODEL_CHANGED = Type(JsonField.of("model_changed"))

            @JvmField val SYSTEM_CHANGED = Type(JsonField.of("system_changed"))

            @JvmField val TOOLS_CHANGED = Type(JsonField.of("tools_changed"))

            @JvmField val MESSAGES_CHANGED = Type(JsonField.of("messages_changed"))

            @JvmField
            val PREVIOUS_MESSAGE_NOT_FOUND = Type(JsonField.of("previous_message_not_found"))

            @JvmField val UNAVAILABLE = Type(JsonField.of("unavailable"))

            @JvmStatic
            fun of(value: String): Type =
                // Intern known values so `==` works
                when (value) {
                    "model_changed" -> MODEL_CHANGED
                    "system_changed" -> SYSTEM_CHANGED
                    "tools_changed" -> TOOLS_CHANGED
                    "messages_changed" -> MESSAGES_CHANGED
                    "previous_message_not_found" -> PREVIOUS_MESSAGE_NOT_FOUND
                    "unavailable" -> UNAVAILABLE
                    else -> Type(JsonField.of(value))
                }

            @JsonCreator
            @JvmStatic
            fun of(value: JsonField<String>): Type =
                value.asString().getOrNull()?.let { of(it) } ?: Type(value)
        }

        /** An enum containing [Type]'s known values. */
        enum class Known {
            MODEL_CHANGED,
            SYSTEM_CHANGED,
            TOOLS_CHANGED,
            MESSAGES_CHANGED,
            PREVIOUS_MESSAGE_NOT_FOUND,
            UNAVAILABLE,
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
            MODEL_CHANGED,
            SYSTEM_CHANGED,
            TOOLS_CHANGED,
            MESSAGES_CHANGED,
            PREVIOUS_MESSAGE_NOT_FOUND,
            UNAVAILABLE,
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
                MODEL_CHANGED -> Value.MODEL_CHANGED
                SYSTEM_CHANGED -> Value.SYSTEM_CHANGED
                TOOLS_CHANGED -> Value.TOOLS_CHANGED
                MESSAGES_CHANGED -> Value.MESSAGES_CHANGED
                PREVIOUS_MESSAGE_NOT_FOUND -> Value.PREVIOUS_MESSAGE_NOT_FOUND
                UNAVAILABLE -> Value.UNAVAILABLE
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
                MODEL_CHANGED -> Known.MODEL_CHANGED
                SYSTEM_CHANGED -> Known.SYSTEM_CHANGED
                TOOLS_CHANGED -> Known.TOOLS_CHANGED
                MESSAGES_CHANGED -> Known.MESSAGES_CHANGED
                PREVIOUS_MESSAGE_NOT_FOUND -> Known.PREVIOUS_MESSAGE_NOT_FOUND
                UNAVAILABLE -> Known.UNAVAILABLE
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
