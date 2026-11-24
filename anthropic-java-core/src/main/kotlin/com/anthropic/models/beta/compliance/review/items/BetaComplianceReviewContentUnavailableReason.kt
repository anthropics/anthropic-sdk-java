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

/** Why a message's content isn't returned. */
@JsonDeserialize(using = BetaComplianceReviewContentUnavailableReason.Deserializer::class)
@JsonSerialize(using = BetaComplianceReviewContentUnavailableReason.Serializer::class)
class BetaComplianceReviewContentUnavailableReason
private constructor(
    private val notCaptured: BetaComplianceReviewContentNotCaptured? = null,
    private val storedCopyUnavailable: BetaComplianceReviewStoredCopyUnavailable? = null,
    private val _json: JsonValue? = null,
) {

    fun type(): Type =
        when {
            notCaptured != null -> Type.NOT_CAPTURED
            storedCopyUnavailable != null -> Type.STORED_COPY_UNAVAILABLE
            else -> Type.of(_json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
        }

    /** No copy of the message was stored. */
    fun notCaptured(): Optional<BetaComplianceReviewContentNotCaptured> =
        Optional.ofNullable(notCaptured)

    /** A copy of the message was stored and can't be returned. */
    fun storedCopyUnavailable(): Optional<BetaComplianceReviewStoredCopyUnavailable> =
        Optional.ofNullable(storedCopyUnavailable)

    fun isNotCaptured(): Boolean = notCaptured != null

    fun isStoredCopyUnavailable(): Boolean = storedCopyUnavailable != null

    /** No copy of the message was stored. */
    fun asNotCaptured(): BetaComplianceReviewContentNotCaptured =
        notCaptured.getOrThrow("notCaptured")

    /** A copy of the message was stored and can't be returned. */
    fun asStoredCopyUnavailable(): BetaComplianceReviewStoredCopyUnavailable =
        storedCopyUnavailable.getOrThrow("storedCopyUnavailable")

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
     * Optional<String> result = betaComplianceReviewContentUnavailableReason.accept(new BetaComplianceReviewContentUnavailableReason.Visitor<Optional<String>>() {
     *     @Override
     *     public Optional<String> visitNotCaptured(BetaComplianceReviewContentNotCaptured notCaptured) {
     *         return Optional.of(notCaptured.toString());
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
            notCaptured != null -> visitor.visitNotCaptured(notCaptured)
            storedCopyUnavailable != null ->
                visitor.visitStoredCopyUnavailable(storedCopyUnavailable)
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
    fun validate(): BetaComplianceReviewContentUnavailableReason = apply {
        if (validated) {
            return@apply
        }

        when {
            notCaptured != null -> notCaptured.validate()
            storedCopyUnavailable != null -> storedCopyUnavailable.validate()
            else ->
                throw AnthropicInvalidDataException(
                    "Unknown BetaComplianceReviewContentUnavailableReason: $_json"
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
            notCaptured != null -> notCaptured.validity()
            storedCopyUnavailable != null -> storedCopyUnavailable.validity()
            else -> 0
        }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaComplianceReviewContentUnavailableReason &&
            notCaptured == other.notCaptured &&
            storedCopyUnavailable == other.storedCopyUnavailable
    }

    override fun hashCode(): Int = Objects.hash(notCaptured, storedCopyUnavailable)

    override fun toString(): String =
        when {
            notCaptured != null ->
                "BetaComplianceReviewContentUnavailableReason{notCaptured=$notCaptured}"
            storedCopyUnavailable != null ->
                "BetaComplianceReviewContentUnavailableReason{storedCopyUnavailable=$storedCopyUnavailable}"
            _json != null -> "BetaComplianceReviewContentUnavailableReason{_unknown=$_json}"
            else ->
                throw IllegalStateException("Invalid BetaComplianceReviewContentUnavailableReason")
        }

    companion object {

        /** No copy of the message was stored. */
        @JvmStatic
        fun ofNotCaptured(notCaptured: BetaComplianceReviewContentNotCaptured) =
            BetaComplianceReviewContentUnavailableReason(notCaptured = notCaptured)

        /** A copy of the message was stored and can't be returned. */
        @JvmStatic
        fun ofStoredCopyUnavailable(
            storedCopyUnavailable: BetaComplianceReviewStoredCopyUnavailable
        ) =
            BetaComplianceReviewContentUnavailableReason(
                storedCopyUnavailable = storedCopyUnavailable
            )
    }

    /**
     * An interface that defines how to map each variant of
     * [BetaComplianceReviewContentUnavailableReason] to a value of type [T].
     */
    interface Visitor<out T> {

        /** No copy of the message was stored. */
        fun visitNotCaptured(notCaptured: BetaComplianceReviewContentNotCaptured): T

        /** A copy of the message was stored and can't be returned. */
        fun visitStoredCopyUnavailable(
            storedCopyUnavailable: BetaComplianceReviewStoredCopyUnavailable
        ): T

        /**
         * Maps an unknown variant of [BetaComplianceReviewContentUnavailableReason] to a value of
         * type [T].
         *
         * An instance of [BetaComplianceReviewContentUnavailableReason] can contain an unknown
         * variant if it was deserialized from data that doesn't match any known variant. For
         * example, if the SDK is on an older version than the API, then the API may respond with
         * new variants that the SDK is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException(
                "Unknown BetaComplianceReviewContentUnavailableReason: $json"
            )
        }
    }

    internal class Deserializer :
        BaseDeserializer<BetaComplianceReviewContentUnavailableReason>(
            BetaComplianceReviewContentUnavailableReason::class
        ) {

        override fun ObjectCodec.deserialize(
            node: JsonNode
        ): BetaComplianceReviewContentUnavailableReason {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "not_captured" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaComplianceReviewContentNotCaptured>(),
                        )
                        ?.let {
                            BetaComplianceReviewContentUnavailableReason(
                                notCaptured = it,
                                _json = json,
                            )
                        } ?: BetaComplianceReviewContentUnavailableReason(_json = json)
                }
                "stored_copy_unavailable" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaComplianceReviewStoredCopyUnavailable>(),
                        )
                        ?.let {
                            BetaComplianceReviewContentUnavailableReason(
                                storedCopyUnavailable = it,
                                _json = json,
                            )
                        } ?: BetaComplianceReviewContentUnavailableReason(_json = json)
                }
            }

            return BetaComplianceReviewContentUnavailableReason(_json = json)
        }
    }

    internal class Serializer :
        BaseSerializer<BetaComplianceReviewContentUnavailableReason>(
            BetaComplianceReviewContentUnavailableReason::class
        ) {

        override fun serialize(
            value: BetaComplianceReviewContentUnavailableReason,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.notCaptured != null -> generator.writeObject(value.notCaptured)
                value.storedCopyUnavailable != null ->
                    generator.writeObject(value.storedCopyUnavailable)
                value._json != null -> generator.writeObject(value._json)
                else ->
                    throw IllegalStateException(
                        "Invalid BetaComplianceReviewContentUnavailableReason"
                    )
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

            @JvmField val NOT_CAPTURED = Type(JsonField.of("not_captured"))

            @JvmField val STORED_COPY_UNAVAILABLE = Type(JsonField.of("stored_copy_unavailable"))

            @JvmStatic
            fun of(value: String): Type =
                // Intern known values so `==` works
                when (value) {
                    "not_captured" -> NOT_CAPTURED
                    "stored_copy_unavailable" -> STORED_COPY_UNAVAILABLE
                    else -> Type(JsonField.of(value))
                }

            @JsonCreator
            @JvmStatic
            fun of(value: JsonField<String>): Type =
                value.asString().getOrNull()?.let { of(it) } ?: Type(value)
        }

        /** An enum containing [Type]'s known values. */
        enum class Known {
            NOT_CAPTURED,
            STORED_COPY_UNAVAILABLE,
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
            NOT_CAPTURED,
            STORED_COPY_UNAVAILABLE,
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
                NOT_CAPTURED -> Value.NOT_CAPTURED
                STORED_COPY_UNAVAILABLE -> Value.STORED_COPY_UNAVAILABLE
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
                NOT_CAPTURED -> Known.NOT_CAPTURED
                STORED_COPY_UNAVAILABLE -> Known.STORED_COPY_UNAVAILABLE
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
