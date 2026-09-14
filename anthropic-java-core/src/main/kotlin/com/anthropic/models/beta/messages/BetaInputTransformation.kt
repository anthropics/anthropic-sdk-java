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

@JsonDeserialize(using = BetaInputTransformation.Deserializer::class)
@JsonSerialize(using = BetaInputTransformation.Serializer::class)
class BetaInputTransformation
private constructor(
    private val thinkingDropped: BetaThinkingDroppedInputTransformation? = null,
    private val thinkingMismatchAllowed: BetaThinkingMismatchAllowedInputTransformation? = null,
    private val _json: JsonValue? = null,
) {

    fun type(): Type =
        accept(
            object : Visitor<Type> {
                override fun visitThinkingDropped(
                    thinkingDropped: BetaThinkingDroppedInputTransformation
                ): Type = Type.THINKING_DROPPED

                override fun visitThinkingMismatchAllowed(
                    thinkingMismatchAllowed: BetaThinkingMismatchAllowedInputTransformation
                ): Type = Type.THINKING_MISMATCH_ALLOWED

                override fun unknown(json: JsonValue?): Type =
                    Type.of(json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
            }
        )

    fun path(): String =
        accept(
            object : Visitor<String> {
                override fun visitThinkingDropped(
                    thinkingDropped: BetaThinkingDroppedInputTransformation
                ): String = thinkingDropped.path()

                override fun visitThinkingMismatchAllowed(
                    thinkingMismatchAllowed: BetaThinkingMismatchAllowedInputTransformation
                ): String = thinkingMismatchAllowed.path()

                override fun unknown(json: JsonValue?): String =
                    json.getProperty<String>("path").getRequired("path")
            }
        )

    fun thinkingDropped(): Optional<BetaThinkingDroppedInputTransformation> =
        Optional.ofNullable(thinkingDropped)

    fun thinkingMismatchAllowed(): Optional<BetaThinkingMismatchAllowedInputTransformation> =
        Optional.ofNullable(thinkingMismatchAllowed)

    fun isThinkingDropped(): Boolean = thinkingDropped != null

    fun isThinkingMismatchAllowed(): Boolean = thinkingMismatchAllowed != null

    fun asThinkingDropped(): BetaThinkingDroppedInputTransformation =
        thinkingDropped.getOrThrow("thinkingDropped")

    fun asThinkingMismatchAllowed(): BetaThinkingMismatchAllowedInputTransformation =
        thinkingMismatchAllowed.getOrThrow("thinkingMismatchAllowed")

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
     * Optional<String> result = betaInputTransformation.accept(new BetaInputTransformation.Visitor<Optional<String>>() {
     *     @Override
     *     public Optional<String> visitThinkingDropped(BetaThinkingDroppedInputTransformation thinkingDropped) {
     *         return Optional.of(thinkingDropped.toString());
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
            thinkingDropped != null -> visitor.visitThinkingDropped(thinkingDropped)
            thinkingMismatchAllowed != null ->
                visitor.visitThinkingMismatchAllowed(thinkingMismatchAllowed)
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
    fun validate(): BetaInputTransformation = apply {
        if (validated) {
            return@apply
        }

        accept(
            object : Visitor<Unit> {
                override fun visitThinkingDropped(
                    thinkingDropped: BetaThinkingDroppedInputTransformation
                ) {
                    thinkingDropped.validate()
                }

                override fun visitThinkingMismatchAllowed(
                    thinkingMismatchAllowed: BetaThinkingMismatchAllowedInputTransformation
                ) {
                    thinkingMismatchAllowed.validate()
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
                override fun visitThinkingDropped(
                    thinkingDropped: BetaThinkingDroppedInputTransformation
                ) = thinkingDropped.validity()

                override fun visitThinkingMismatchAllowed(
                    thinkingMismatchAllowed: BetaThinkingMismatchAllowedInputTransformation
                ) = thinkingMismatchAllowed.validity()

                override fun unknown(json: JsonValue?) = 0
            }
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaInputTransformation &&
            thinkingDropped == other.thinkingDropped &&
            thinkingMismatchAllowed == other.thinkingMismatchAllowed
    }

    override fun hashCode(): Int = Objects.hash(thinkingDropped, thinkingMismatchAllowed)

    override fun toString(): String =
        when {
            thinkingDropped != null -> "BetaInputTransformation{thinkingDropped=$thinkingDropped}"
            thinkingMismatchAllowed != null ->
                "BetaInputTransformation{thinkingMismatchAllowed=$thinkingMismatchAllowed}"
            _json != null -> "BetaInputTransformation{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaInputTransformation")
        }

    companion object {

        @JvmStatic
        fun ofThinkingDropped(thinkingDropped: BetaThinkingDroppedInputTransformation) =
            BetaInputTransformation(thinkingDropped = thinkingDropped)

        @JvmStatic
        fun ofThinkingMismatchAllowed(
            thinkingMismatchAllowed: BetaThinkingMismatchAllowedInputTransformation
        ) = BetaInputTransformation(thinkingMismatchAllowed = thinkingMismatchAllowed)
    }

    /**
     * An interface that defines how to map each variant of [BetaInputTransformation] to a value of
     * type [T].
     */
    interface Visitor<out T> {

        fun visitThinkingDropped(thinkingDropped: BetaThinkingDroppedInputTransformation): T

        fun visitThinkingMismatchAllowed(
            thinkingMismatchAllowed: BetaThinkingMismatchAllowedInputTransformation
        ): T

        /**
         * Maps an unknown variant of [BetaInputTransformation] to a value of type [T].
         *
         * An instance of [BetaInputTransformation] can contain an unknown variant if it was
         * deserialized from data that doesn't match any known variant. For example, if the SDK is
         * on an older version than the API, then the API may respond with new variants that the SDK
         * is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown BetaInputTransformation: $json")
        }
    }

    internal class Deserializer :
        BaseDeserializer<BetaInputTransformation>(BetaInputTransformation::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): BetaInputTransformation {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "thinking_dropped" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaThinkingDroppedInputTransformation>(),
                        )
                        ?.let { BetaInputTransformation(thinkingDropped = it, _json = json) }
                        ?: BetaInputTransformation(_json = json)
                }
                "thinking_mismatch_allowed" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaThinkingMismatchAllowedInputTransformation>(),
                        )
                        ?.let {
                            BetaInputTransformation(thinkingMismatchAllowed = it, _json = json)
                        } ?: BetaInputTransformation(_json = json)
                }
            }

            return BetaInputTransformation(_json = json)
        }
    }

    internal class Serializer :
        BaseSerializer<BetaInputTransformation>(BetaInputTransformation::class) {

        override fun serialize(
            value: BetaInputTransformation,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.thinkingDropped != null -> generator.writeObject(value.thinkingDropped)
                value.thinkingMismatchAllowed != null ->
                    generator.writeObject(value.thinkingMismatchAllowed)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BetaInputTransformation")
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

            @JvmField val THINKING_DROPPED = of("thinking_dropped")

            @JvmField val THINKING_MISMATCH_ALLOWED = of("thinking_mismatch_allowed")

            @JvmStatic fun of(value: String) = Type(JsonField.of(value))

            @JvmSynthetic
            internal fun of(value: JsonField<String>): Type =
                value.asString().getOrNull()?.let { of(it) } ?: Type(value)
        }

        /** An enum containing [Type]'s known values. */
        enum class Known {
            THINKING_DROPPED,
            THINKING_MISMATCH_ALLOWED,
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
            THINKING_DROPPED,
            THINKING_MISMATCH_ALLOWED,
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
                THINKING_DROPPED -> Value.THINKING_DROPPED
                THINKING_MISMATCH_ALLOWED -> Value.THINKING_MISMATCH_ALLOWED
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
                THINKING_DROPPED -> Known.THINKING_DROPPED
                THINKING_MISMATCH_ALLOWED -> Known.THINKING_MISMATCH_ALLOWED
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
