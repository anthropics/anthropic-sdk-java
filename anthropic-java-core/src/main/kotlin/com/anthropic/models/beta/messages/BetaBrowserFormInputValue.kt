package com.anthropic.models.beta.messages

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.JsonValue
import com.anthropic.core.allMaxBy
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

@JsonDeserialize(using = BetaBrowserFormInputValue.Deserializer::class)
@JsonSerialize(using = BetaBrowserFormInputValue.Serializer::class)
class BetaBrowserFormInputValue
private constructor(
    private val string: String? = null,
    private val number: Double? = null,
    private val bool: Boolean? = null,
    private val _json: JsonValue? = null,
) {

    fun string(): Optional<String> = Optional.ofNullable(string)

    fun number(): Optional<Double> = Optional.ofNullable(number)

    fun bool(): Optional<Boolean> = Optional.ofNullable(bool)

    fun isString(): Boolean = string != null

    fun isNumber(): Boolean = number != null

    fun isBool(): Boolean = bool != null

    fun asString(): String = string.getOrThrow("string")

    fun asNumber(): Double = number.getOrThrow("number")

    fun asBool(): Boolean = bool.getOrThrow("bool")

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
     * Optional<String> result = betaBrowserFormInputValue.accept(new BetaBrowserFormInputValue.Visitor<Optional<String>>() {
     *     @Override
     *     public Optional<String> visitString(String string) {
     *         return Optional.of(string.toString());
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
            string != null -> visitor.visitString(string)
            number != null -> visitor.visitNumber(number)
            bool != null -> visitor.visitBool(bool)
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
    fun validate(): BetaBrowserFormInputValue = apply {
        if (validated) {
            return@apply
        }

        when {
            string != null -> {}
            number != null -> {}
            bool != null -> {}
            else -> throw AnthropicInvalidDataException("Unknown BetaBrowserFormInputValue: $_json")
        }
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
        when {
            string != null -> 1
            number != null -> 1
            bool != null -> 1
            else -> 0
        }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaBrowserFormInputValue &&
            string == other.string &&
            number == other.number &&
            bool == other.bool
    }

    override fun hashCode(): Int = Objects.hash(string, number, bool)

    override fun toString(): String =
        when {
            string != null -> "BetaBrowserFormInputValue{string=$string}"
            number != null -> "BetaBrowserFormInputValue{number=$number}"
            bool != null -> "BetaBrowserFormInputValue{bool=$bool}"
            _json != null -> "BetaBrowserFormInputValue{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaBrowserFormInputValue")
        }

    companion object {

        @JvmStatic fun ofString(string: String) = BetaBrowserFormInputValue(string = string)

        @JvmStatic fun ofNumber(number: Double) = BetaBrowserFormInputValue(number = number)

        @JvmStatic fun ofBool(bool: Boolean) = BetaBrowserFormInputValue(bool = bool)
    }

    /**
     * An interface that defines how to map each variant of [BetaBrowserFormInputValue] to a value
     * of type [T].
     */
    interface Visitor<out T> {

        fun visitString(string: String): T

        fun visitNumber(number: Double): T

        fun visitBool(bool: Boolean): T

        /**
         * Maps an unknown variant of [BetaBrowserFormInputValue] to a value of type [T].
         *
         * An instance of [BetaBrowserFormInputValue] can contain an unknown variant if it was
         * deserialized from data that doesn't match any known variant. For example, if the SDK is
         * on an older version than the API, then the API may respond with new variants that the SDK
         * is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown BetaBrowserFormInputValue: $json")
        }
    }

    internal class Deserializer :
        BaseDeserializer<BetaBrowserFormInputValue>(BetaBrowserFormInputValue::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): BetaBrowserFormInputValue {
            val json = JsonValue.fromJsonNode(node)

            val bestMatches =
                sequenceOf(
                        tryDeserialize(node, jacksonTypeRef<String>())?.let {
                            BetaBrowserFormInputValue(string = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<Double>())?.let {
                            BetaBrowserFormInputValue(number = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<Boolean>())?.let {
                            BetaBrowserFormInputValue(bool = it, _json = json)
                        },
                    )
                    .filterNotNull()
                    .allMaxBy { it.validity() }
                    .toList()
            return when (bestMatches.size) {
                // This can happen if what we're deserializing is completely incompatible with all
                // the possible variants (e.g. deserializing from object).
                0 -> BetaBrowserFormInputValue(_json = json)
                1 -> bestMatches.single()
                // If there's more than one match with the highest validity, then use the first
                // completely valid match, or simply the first match if none are completely valid.
                else -> bestMatches.firstOrNull { it.isValid() } ?: bestMatches.first()
            }
        }
    }

    internal class Serializer :
        BaseSerializer<BetaBrowserFormInputValue>(BetaBrowserFormInputValue::class) {

        override fun serialize(
            value: BetaBrowserFormInputValue,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.string != null -> generator.writeObject(value.string)
                value.number != null -> generator.writeObject(value.number)
                value.bool != null -> generator.writeObject(value.bool)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BetaBrowserFormInputValue")
            }
        }
    }
}
