package com.anthropic.models.beta.organization.telemetrydestinations

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

/** The outcome of a test: `success`, or `failure` with the reason. */
@JsonDeserialize(using = BetaTelemetryDestinationTestResult.Deserializer::class)
@JsonSerialize(using = BetaTelemetryDestinationTestResult.Serializer::class)
class BetaTelemetryDestinationTestResult
private constructor(
    private val success: BetaTelemetryDestinationTestSuccess? = null,
    private val failure: BetaTelemetryDestinationTestFailure? = null,
    private val _json: JsonValue? = null,
) {

    fun type(): Type =
        when {
            success != null -> Type.SUCCESS
            failure != null -> Type.FAILURE
            else -> Type.of(_json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
        }

    /** The destination answered the test span with a 2xx status. */
    fun success(): Optional<BetaTelemetryDestinationTestSuccess> = Optional.ofNullable(success)

    /** The test span was not delivered. */
    fun failure(): Optional<BetaTelemetryDestinationTestFailure> = Optional.ofNullable(failure)

    fun isSuccess(): Boolean = success != null

    fun isFailure(): Boolean = failure != null

    /** The destination answered the test span with a 2xx status. */
    fun asSuccess(): BetaTelemetryDestinationTestSuccess = success.getOrThrow("success")

    /** The test span was not delivered. */
    fun asFailure(): BetaTelemetryDestinationTestFailure = failure.getOrThrow("failure")

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
     * Optional<String> result = betaTelemetryDestinationTestResult.accept(new BetaTelemetryDestinationTestResult.Visitor<Optional<String>>() {
     *     @Override
     *     public Optional<String> visitSuccess(BetaTelemetryDestinationTestSuccess success) {
     *         return Optional.of(success.toString());
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
            success != null -> visitor.visitSuccess(success)
            failure != null -> visitor.visitFailure(failure)
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
    fun validate(): BetaTelemetryDestinationTestResult = apply {
        if (validated) {
            return@apply
        }

        when {
            success != null -> success.validate()
            failure != null -> failure.validate()
            else ->
                throw AnthropicInvalidDataException(
                    "Unknown BetaTelemetryDestinationTestResult: $_json"
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
            success != null -> success.validity()
            failure != null -> failure.validity()
            else -> 0
        }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaTelemetryDestinationTestResult &&
            success == other.success &&
            failure == other.failure
    }

    override fun hashCode(): Int = Objects.hash(success, failure)

    override fun toString(): String =
        when {
            success != null -> "BetaTelemetryDestinationTestResult{success=$success}"
            failure != null -> "BetaTelemetryDestinationTestResult{failure=$failure}"
            _json != null -> "BetaTelemetryDestinationTestResult{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaTelemetryDestinationTestResult")
        }

    companion object {

        /** The destination answered the test span with a 2xx status. */
        @JvmStatic
        fun ofSuccess(success: BetaTelemetryDestinationTestSuccess) =
            BetaTelemetryDestinationTestResult(success = success)

        /** The test span was not delivered. */
        @JvmStatic
        fun ofFailure(failure: BetaTelemetryDestinationTestFailure) =
            BetaTelemetryDestinationTestResult(failure = failure)

        /**
         * Returns an immutable instance of [BetaTelemetryDestinationTestResult] whose [ofFailure]
         * variant is built from the given required [error].
         */
        @JvmStatic
        fun ofFailure(error: String) = ofFailure(BetaTelemetryDestinationTestFailure.of(error))
    }

    /**
     * An interface that defines how to map each variant of [BetaTelemetryDestinationTestResult] to
     * a value of type [T].
     */
    interface Visitor<out T> {

        /** The destination answered the test span with a 2xx status. */
        fun visitSuccess(success: BetaTelemetryDestinationTestSuccess): T

        /** The test span was not delivered. */
        fun visitFailure(failure: BetaTelemetryDestinationTestFailure): T

        /**
         * Maps an unknown variant of [BetaTelemetryDestinationTestResult] to a value of type [T].
         *
         * An instance of [BetaTelemetryDestinationTestResult] can contain an unknown variant if it
         * was deserialized from data that doesn't match any known variant. For example, if the SDK
         * is on an older version than the API, then the API may respond with new variants that the
         * SDK is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown BetaTelemetryDestinationTestResult: $json")
        }
    }

    internal class Deserializer :
        BaseDeserializer<BetaTelemetryDestinationTestResult>(
            BetaTelemetryDestinationTestResult::class
        ) {

        override fun ObjectCodec.deserialize(node: JsonNode): BetaTelemetryDestinationTestResult {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "success" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaTelemetryDestinationTestSuccess>(),
                        )
                        ?.let { BetaTelemetryDestinationTestResult(success = it, _json = json) }
                        ?: BetaTelemetryDestinationTestResult(_json = json)
                }
                "failure" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaTelemetryDestinationTestFailure>(),
                        )
                        ?.let { BetaTelemetryDestinationTestResult(failure = it, _json = json) }
                        ?: BetaTelemetryDestinationTestResult(_json = json)
                }
            }

            return BetaTelemetryDestinationTestResult(_json = json)
        }
    }

    internal class Serializer :
        BaseSerializer<BetaTelemetryDestinationTestResult>(
            BetaTelemetryDestinationTestResult::class
        ) {

        override fun serialize(
            value: BetaTelemetryDestinationTestResult,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.success != null -> generator.writeObject(value.success)
                value.failure != null -> generator.writeObject(value.failure)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BetaTelemetryDestinationTestResult")
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

            @JvmField val SUCCESS = Type(JsonField.of("success"))

            @JvmField val FAILURE = Type(JsonField.of("failure"))

            @JvmStatic
            fun of(value: String): Type =
                // Intern known values so `==` works
                when (value) {
                    "success" -> SUCCESS
                    "failure" -> FAILURE
                    else -> Type(JsonField.of(value))
                }

            @JsonCreator
            @JvmStatic
            fun of(value: JsonField<String>): Type =
                value.asString().getOrNull()?.let { of(it) } ?: Type(value)
        }

        /** An enum containing [Type]'s known values. */
        enum class Known {
            SUCCESS,
            FAILURE,
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
            SUCCESS,
            FAILURE,
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
                SUCCESS -> Value.SUCCESS
                FAILURE -> Value.FAILURE
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
                SUCCESS -> Known.SUCCESS
                FAILURE -> Known.FAILURE
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
