// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.deployments

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

/** Why a deployment is paused. Non-null exactly when `status` is `paused`. */
@JsonDeserialize(using = BetaManagedAgentsDeploymentPausedReason.Deserializer::class)
@JsonSerialize(using = BetaManagedAgentsDeploymentPausedReason.Serializer::class)
class BetaManagedAgentsDeploymentPausedReason
private constructor(
    private val manual: BetaManagedAgentsManualDeploymentPausedReason? = null,
    private val error: BetaManagedAgentsErrorDeploymentPausedReason? = null,
    private val _json: JsonValue? = null,
) {

    fun type(): Type =
        accept(
            object : Visitor<Type> {
                override fun visitManual(
                    manual: BetaManagedAgentsManualDeploymentPausedReason
                ): Type = Type.MANUAL

                override fun visitError(error: BetaManagedAgentsErrorDeploymentPausedReason): Type =
                    Type.ERROR

                override fun unknown(json: JsonValue?): Type =
                    Type.of(json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
            }
        )

    /** The caller invoked the pause endpoint on the deployment. */
    fun manual(): Optional<BetaManagedAgentsManualDeploymentPausedReason> =
        Optional.ofNullable(manual)

    /** A scheduled fire recorded a failed run whose error auto-pauses the deployment. */
    fun error(): Optional<BetaManagedAgentsErrorDeploymentPausedReason> = Optional.ofNullable(error)

    fun isManual(): Boolean = manual != null

    fun isError(): Boolean = error != null

    /** The caller invoked the pause endpoint on the deployment. */
    fun asManual(): BetaManagedAgentsManualDeploymentPausedReason = manual.getOrThrow("manual")

    /** A scheduled fire recorded a failed run whose error auto-pauses the deployment. */
    fun asError(): BetaManagedAgentsErrorDeploymentPausedReason = error.getOrThrow("error")

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
     * Optional<String> result = betaManagedAgentsDeploymentPausedReason.accept(new BetaManagedAgentsDeploymentPausedReason.Visitor<Optional<String>>() {
     *     @Override
     *     public Optional<String> visitManual(BetaManagedAgentsManualDeploymentPausedReason manual) {
     *         return Optional.of(manual.toString());
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
            manual != null -> visitor.visitManual(manual)
            error != null -> visitor.visitError(error)
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
    fun validate(): BetaManagedAgentsDeploymentPausedReason = apply {
        if (validated) {
            return@apply
        }

        accept(
            object : Visitor<Unit> {
                override fun visitManual(manual: BetaManagedAgentsManualDeploymentPausedReason) {
                    manual.validate()
                }

                override fun visitError(error: BetaManagedAgentsErrorDeploymentPausedReason) {
                    error.validate()
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
                override fun visitManual(manual: BetaManagedAgentsManualDeploymentPausedReason) =
                    manual.validity()

                override fun visitError(error: BetaManagedAgentsErrorDeploymentPausedReason) =
                    error.validity()

                override fun unknown(json: JsonValue?) = 0
            }
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsDeploymentPausedReason &&
            manual == other.manual &&
            error == other.error
    }

    override fun hashCode(): Int = Objects.hash(manual, error)

    override fun toString(): String =
        when {
            manual != null -> "BetaManagedAgentsDeploymentPausedReason{manual=$manual}"
            error != null -> "BetaManagedAgentsDeploymentPausedReason{error=$error}"
            _json != null -> "BetaManagedAgentsDeploymentPausedReason{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaManagedAgentsDeploymentPausedReason")
        }

    companion object {

        /** The caller invoked the pause endpoint on the deployment. */
        @JvmStatic
        fun ofManual(manual: BetaManagedAgentsManualDeploymentPausedReason) =
            BetaManagedAgentsDeploymentPausedReason(manual = manual)

        /**
         * Returns an immutable instance of [BetaManagedAgentsDeploymentPausedReason] whose
         * [ofManual] variant is built from the given required [type].
         */
        @JvmStatic
        fun ofManual(type: BetaManagedAgentsManualDeploymentPausedReason.Type) =
            ofManual(BetaManagedAgentsManualDeploymentPausedReason.of(type))

        /** A scheduled fire recorded a failed run whose error auto-pauses the deployment. */
        @JvmStatic
        fun ofError(error: BetaManagedAgentsErrorDeploymentPausedReason) =
            BetaManagedAgentsDeploymentPausedReason(error = error)

        /**
         * Returns an immutable instance of [BetaManagedAgentsDeploymentPausedReason] whose
         * [ofError] variant is built from the given required [error].
         */
        @JvmStatic
        fun ofError(error: BetaManagedAgentsDeploymentPausedReasonError) =
            ofError(
                BetaManagedAgentsErrorDeploymentPausedReason.builder()
                    .type(BetaManagedAgentsErrorDeploymentPausedReason.Type.ERROR)
                    .error(error)
                    .build()
            )
    }

    /**
     * An interface that defines how to map each variant of
     * [BetaManagedAgentsDeploymentPausedReason] to a value of type [T].
     */
    interface Visitor<out T> {

        /** The caller invoked the pause endpoint on the deployment. */
        fun visitManual(manual: BetaManagedAgentsManualDeploymentPausedReason): T

        /** A scheduled fire recorded a failed run whose error auto-pauses the deployment. */
        fun visitError(error: BetaManagedAgentsErrorDeploymentPausedReason): T

        /**
         * Maps an unknown variant of [BetaManagedAgentsDeploymentPausedReason] to a value of type
         * [T].
         *
         * An instance of [BetaManagedAgentsDeploymentPausedReason] can contain an unknown variant
         * if it was deserialized from data that doesn't match any known variant. For example, if
         * the SDK is on an older version than the API, then the API may respond with new variants
         * that the SDK is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException(
                "Unknown BetaManagedAgentsDeploymentPausedReason: $json"
            )
        }
    }

    internal class Deserializer :
        BaseDeserializer<BetaManagedAgentsDeploymentPausedReason>(
            BetaManagedAgentsDeploymentPausedReason::class
        ) {

        override fun ObjectCodec.deserialize(
            node: JsonNode
        ): BetaManagedAgentsDeploymentPausedReason {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "manual" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsManualDeploymentPausedReason>(),
                        )
                        ?.let { BetaManagedAgentsDeploymentPausedReason(manual = it, _json = json) }
                        ?: BetaManagedAgentsDeploymentPausedReason(_json = json)
                }
                "error" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsErrorDeploymentPausedReason>(),
                        )
                        ?.let { BetaManagedAgentsDeploymentPausedReason(error = it, _json = json) }
                        ?: BetaManagedAgentsDeploymentPausedReason(_json = json)
                }
            }

            return BetaManagedAgentsDeploymentPausedReason(_json = json)
        }
    }

    internal class Serializer :
        BaseSerializer<BetaManagedAgentsDeploymentPausedReason>(
            BetaManagedAgentsDeploymentPausedReason::class
        ) {

        override fun serialize(
            value: BetaManagedAgentsDeploymentPausedReason,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.manual != null -> generator.writeObject(value.manual)
                value.error != null -> generator.writeObject(value.error)
                value._json != null -> generator.writeObject(value._json)
                else ->
                    throw IllegalStateException("Invalid BetaManagedAgentsDeploymentPausedReason")
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

            @JvmField val MANUAL = of("manual")

            @JvmField val ERROR = of("error")

            @JvmStatic fun of(value: String) = Type(JsonField.of(value))

            @JvmSynthetic
            internal fun of(value: JsonField<String>): Type =
                value.asString().getOrNull()?.let { of(it) } ?: Type(value)
        }

        /** An enum containing [Type]'s known values. */
        enum class Known {
            MANUAL,
            ERROR,
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
            MANUAL,
            ERROR,
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
                MANUAL -> Value.MANUAL
                ERROR -> Value.ERROR
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
                MANUAL -> Known.MANUAL
                ERROR -> Known.ERROR
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
