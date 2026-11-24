package com.anthropic.models.beta.compliance.review.items

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

/** What is particular to one kind of review item. */
@JsonDeserialize(using = BetaComplianceReviewItemDetails.Deserializer::class)
@JsonSerialize(using = BetaComplianceReviewItemDetails.Serializer::class)
class BetaComplianceReviewItemDetails
private constructor(
    private val message: BetaComplianceReviewMessageDetails? = null,
    private val _json: JsonValue? = null,
) {

    fun type(): Type =
        when {
            message != null -> Type.MESSAGE
            else -> Type.of(_json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
        }

    /** A message sent to Claude. */
    fun message(): Optional<BetaComplianceReviewMessageDetails> = Optional.ofNullable(message)

    fun isMessage(): Boolean = message != null

    /** A message sent to Claude. */
    fun asMessage(): BetaComplianceReviewMessageDetails = message.getOrThrow("message")

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
     * Optional<String> result = betaComplianceReviewItemDetails.accept(new BetaComplianceReviewItemDetails.Visitor<Optional<String>>() {
     *     @Override
     *     public Optional<String> visitMessage(BetaComplianceReviewMessageDetails message) {
     *         return Optional.of(message.toString());
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
            message != null -> visitor.visitMessage(message)
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
    fun validate(): BetaComplianceReviewItemDetails = apply {
        if (validated) {
            return@apply
        }

        when {
            message != null -> message.validate()
            else ->
                throw AnthropicInvalidDataException(
                    "Unknown BetaComplianceReviewItemDetails: $_json"
                )
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
            message != null -> message.validity()
            else -> 0
        }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaComplianceReviewItemDetails && message == other.message
    }

    override fun hashCode(): Int = Objects.hash(message)

    override fun toString(): String =
        when {
            message != null -> "BetaComplianceReviewItemDetails{message=$message}"
            _json != null -> "BetaComplianceReviewItemDetails{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaComplianceReviewItemDetails")
        }

    companion object {

        /** A message sent to Claude. */
        @JvmStatic
        fun ofMessage(message: BetaComplianceReviewMessageDetails) =
            BetaComplianceReviewItemDetails(message = message)
    }

    /**
     * An interface that defines how to map each variant of [BetaComplianceReviewItemDetails] to a
     * value of type [T].
     */
    interface Visitor<out T> {

        /** A message sent to Claude. */
        fun visitMessage(message: BetaComplianceReviewMessageDetails): T

        /**
         * Maps an unknown variant of [BetaComplianceReviewItemDetails] to a value of type [T].
         *
         * An instance of [BetaComplianceReviewItemDetails] can contain an unknown variant if it was
         * deserialized from data that doesn't match any known variant. For example, if the SDK is
         * on an older version than the API, then the API may respond with new variants that the SDK
         * is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown BetaComplianceReviewItemDetails: $json")
        }
    }

    internal class Deserializer :
        BaseDeserializer<BetaComplianceReviewItemDetails>(BetaComplianceReviewItemDetails::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): BetaComplianceReviewItemDetails {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            if (type == "message") {
                return tryDeserialize(node, jacksonTypeRef<BetaComplianceReviewMessageDetails>())
                    ?.let { BetaComplianceReviewItemDetails(message = it, _json = json) }
                    ?: BetaComplianceReviewItemDetails(_json = json)
            }

            return BetaComplianceReviewItemDetails(_json = json)
        }
    }

    internal class Serializer :
        BaseSerializer<BetaComplianceReviewItemDetails>(BetaComplianceReviewItemDetails::class) {

        override fun serialize(
            value: BetaComplianceReviewItemDetails,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.message != null -> generator.writeObject(value.message)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BetaComplianceReviewItemDetails")
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

            @JvmField val MESSAGE = Type(JsonField.of("message"))

            @JvmStatic
            fun of(value: String): Type =
                // Intern known values so `==` works
                when (value) {
                    "message" -> MESSAGE
                    else -> Type(JsonField.of(value))
                }

            @JsonCreator
            @JvmStatic
            fun of(value: JsonField<String>): Type =
                value.asString().getOrNull()?.let { of(it) } ?: Type(value)
        }

        /** An enum containing [Type]'s known values. */
        enum class Known {
            MESSAGE
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
            MESSAGE,
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
                MESSAGE -> Value.MESSAGE
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
                MESSAGE -> Known.MESSAGE
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
