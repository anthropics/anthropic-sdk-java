package com.anthropic.models.beta.compliance.review.cases

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

/** The status of a review case. */
@JsonDeserialize(using = BetaComplianceReviewCaseStatus.Deserializer::class)
@JsonSerialize(using = BetaComplianceReviewCaseStatus.Serializer::class)
class BetaComplianceReviewCaseStatus
private constructor(
    private val open: BetaComplianceReviewCaseOpen? = null,
    private val closed: BetaComplianceReviewCaseClosed? = null,
    private val _json: JsonValue? = null,
) {

    fun type(): Type =
        when {
            open != null -> Type.OPEN
            closed != null -> Type.CLOSED
            else -> Type.of(_json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
        }

    /** Anthropic is waiting for a response to the case. */
    fun open(): Optional<BetaComplianceReviewCaseOpen> = Optional.ofNullable(open)

    /** Anthropic has stopped waiting for a response. A closed case stays closed. */
    fun closed(): Optional<BetaComplianceReviewCaseClosed> = Optional.ofNullable(closed)

    fun isOpen(): Boolean = open != null

    fun isClosed(): Boolean = closed != null

    /** Anthropic is waiting for a response to the case. */
    fun asOpen(): BetaComplianceReviewCaseOpen = open.getOrThrow("open")

    /** Anthropic has stopped waiting for a response. A closed case stays closed. */
    fun asClosed(): BetaComplianceReviewCaseClosed = closed.getOrThrow("closed")

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
     * Optional<String> result = betaComplianceReviewCaseStatus.accept(new BetaComplianceReviewCaseStatus.Visitor<Optional<String>>() {
     *     @Override
     *     public Optional<String> visitOpen(BetaComplianceReviewCaseOpen open) {
     *         return Optional.of(open.toString());
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
            open != null -> visitor.visitOpen(open)
            closed != null -> visitor.visitClosed(closed)
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
    fun validate(): BetaComplianceReviewCaseStatus = apply {
        if (validated) {
            return@apply
        }

        when {
            open != null -> open.validate()
            closed != null -> closed.validate()
            else ->
                throw AnthropicInvalidDataException(
                    "Unknown BetaComplianceReviewCaseStatus: $_json"
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
            open != null -> open.validity()
            closed != null -> closed.validity()
            else -> 0
        }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaComplianceReviewCaseStatus &&
            open == other.open &&
            closed == other.closed
    }

    override fun hashCode(): Int = Objects.hash(open, closed)

    override fun toString(): String =
        when {
            open != null -> "BetaComplianceReviewCaseStatus{open=$open}"
            closed != null -> "BetaComplianceReviewCaseStatus{closed=$closed}"
            _json != null -> "BetaComplianceReviewCaseStatus{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaComplianceReviewCaseStatus")
        }

    companion object {

        /** Anthropic is waiting for a response to the case. */
        @JvmStatic
        fun ofOpen(open: BetaComplianceReviewCaseOpen) = BetaComplianceReviewCaseStatus(open = open)

        /** Anthropic has stopped waiting for a response. A closed case stays closed. */
        @JvmStatic
        fun ofClosed(closed: BetaComplianceReviewCaseClosed) =
            BetaComplianceReviewCaseStatus(closed = closed)

        /**
         * Returns an immutable instance of [BetaComplianceReviewCaseStatus] whose [ofClosed]
         * variant is built from the given required [reason].
         */
        @JvmStatic
        fun ofClosed(reason: BetaComplianceReviewCaseCloseReason) =
            ofClosed(BetaComplianceReviewCaseClosed.of(reason))
    }

    /**
     * An interface that defines how to map each variant of [BetaComplianceReviewCaseStatus] to a
     * value of type [T].
     */
    interface Visitor<out T> {

        /** Anthropic is waiting for a response to the case. */
        fun visitOpen(open: BetaComplianceReviewCaseOpen): T

        /** Anthropic has stopped waiting for a response. A closed case stays closed. */
        fun visitClosed(closed: BetaComplianceReviewCaseClosed): T

        /**
         * Maps an unknown variant of [BetaComplianceReviewCaseStatus] to a value of type [T].
         *
         * An instance of [BetaComplianceReviewCaseStatus] can contain an unknown variant if it was
         * deserialized from data that doesn't match any known variant. For example, if the SDK is
         * on an older version than the API, then the API may respond with new variants that the SDK
         * is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown BetaComplianceReviewCaseStatus: $json")
        }
    }

    internal class Deserializer :
        BaseDeserializer<BetaComplianceReviewCaseStatus>(BetaComplianceReviewCaseStatus::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): BetaComplianceReviewCaseStatus {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "open" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaComplianceReviewCaseOpen>())
                        ?.let { BetaComplianceReviewCaseStatus(open = it, _json = json) }
                        ?: BetaComplianceReviewCaseStatus(_json = json)
                }
                "closed" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaComplianceReviewCaseClosed>())
                        ?.let { BetaComplianceReviewCaseStatus(closed = it, _json = json) }
                        ?: BetaComplianceReviewCaseStatus(_json = json)
                }
            }

            return BetaComplianceReviewCaseStatus(_json = json)
        }
    }

    internal class Serializer :
        BaseSerializer<BetaComplianceReviewCaseStatus>(BetaComplianceReviewCaseStatus::class) {

        override fun serialize(
            value: BetaComplianceReviewCaseStatus,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.open != null -> generator.writeObject(value.open)
                value.closed != null -> generator.writeObject(value.closed)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BetaComplianceReviewCaseStatus")
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

            @JvmField val OPEN = Type(JsonField.of("open"))

            @JvmField val CLOSED = Type(JsonField.of("closed"))

            @JvmStatic
            fun of(value: String): Type =
                // Intern known values so `==` works
                when (value) {
                    "open" -> OPEN
                    "closed" -> CLOSED
                    else -> Type(JsonField.of(value))
                }

            @JsonCreator
            @JvmStatic
            fun of(value: JsonField<String>): Type =
                value.asString().getOrNull()?.let { of(it) } ?: Type(value)
        }

        /** An enum containing [Type]'s known values. */
        enum class Known {
            OPEN,
            CLOSED,
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
            OPEN,
            CLOSED,
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
                OPEN -> Value.OPEN
                CLOSED -> Value.CLOSED
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
                OPEN -> Known.OPEN
                CLOSED -> Known.CLOSED
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
