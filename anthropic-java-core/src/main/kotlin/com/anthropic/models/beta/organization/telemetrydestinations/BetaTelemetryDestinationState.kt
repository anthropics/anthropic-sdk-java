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

/**
 * Whether a telemetry destination receives deliveries.
 *
 * A caller can set either state. The API also disables an enabled destination on its own in some
 * cases. The `reason` of the disabled state says whether a caller or the API disabled the
 * destination and, when the API did, why.
 *
 * See the
 * [OpenTelemetry export guide](https://platform.claude.com/docs/en/manage-claude/opentelemetry#delivery-and-health)
 * to learn when the API disables a destination.
 */
@JsonDeserialize(using = BetaTelemetryDestinationState.Deserializer::class)
@JsonSerialize(using = BetaTelemetryDestinationState.Serializer::class)
class BetaTelemetryDestinationState
private constructor(
    private val enabled: BetaTelemetryDestinationStateEnabled? = null,
    private val disabled: BetaTelemetryDestinationStateDisabled? = null,
    private val _json: JsonValue? = null,
) {

    fun type(): Type =
        when {
            enabled != null -> Type.ENABLED
            disabled != null -> Type.DISABLED
            else -> Type.of(_json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
        }

    /** The telemetry destination receives the spans of the workspaces that it covers. */
    fun enabled(): Optional<BetaTelemetryDestinationStateEnabled> = Optional.ofNullable(enabled)

    /**
     * The API doesn't deliver anything to the telemetry destination.
     *
     * Spans produced while it is disabled aren't delivered later.
     */
    fun disabled(): Optional<BetaTelemetryDestinationStateDisabled> = Optional.ofNullable(disabled)

    fun isEnabled(): Boolean = enabled != null

    fun isDisabled(): Boolean = disabled != null

    /** The telemetry destination receives the spans of the workspaces that it covers. */
    fun asEnabled(): BetaTelemetryDestinationStateEnabled = enabled.getOrThrow("enabled")

    /**
     * The API doesn't deliver anything to the telemetry destination.
     *
     * Spans produced while it is disabled aren't delivered later.
     */
    fun asDisabled(): BetaTelemetryDestinationStateDisabled = disabled.getOrThrow("disabled")

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
     * Optional<String> result = betaTelemetryDestinationState.accept(new BetaTelemetryDestinationState.Visitor<Optional<String>>() {
     *     @Override
     *     public Optional<String> visitEnabled(BetaTelemetryDestinationStateEnabled enabled) {
     *         return Optional.of(enabled.toString());
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
            enabled != null -> visitor.visitEnabled(enabled)
            disabled != null -> visitor.visitDisabled(disabled)
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
    fun validate(): BetaTelemetryDestinationState = apply {
        if (validated) {
            return@apply
        }

        when {
            enabled != null -> enabled.validate()
            disabled != null -> disabled.validate()
            else ->
                throw AnthropicInvalidDataException("Unknown BetaTelemetryDestinationState: $_json")
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
            enabled != null -> enabled.validity()
            disabled != null -> disabled.validity()
            else -> 0
        }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaTelemetryDestinationState &&
            enabled == other.enabled &&
            disabled == other.disabled
    }

    override fun hashCode(): Int = Objects.hash(enabled, disabled)

    override fun toString(): String =
        when {
            enabled != null -> "BetaTelemetryDestinationState{enabled=$enabled}"
            disabled != null -> "BetaTelemetryDestinationState{disabled=$disabled}"
            _json != null -> "BetaTelemetryDestinationState{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaTelemetryDestinationState")
        }

    companion object {

        /** The telemetry destination receives the spans of the workspaces that it covers. */
        @JvmStatic
        fun ofEnabled(enabled: BetaTelemetryDestinationStateEnabled) =
            BetaTelemetryDestinationState(enabled = enabled)

        /**
         * The API doesn't deliver anything to the telemetry destination.
         *
         * Spans produced while it is disabled aren't delivered later.
         */
        @JvmStatic
        fun ofDisabled(disabled: BetaTelemetryDestinationStateDisabled) =
            BetaTelemetryDestinationState(disabled = disabled)

        /**
         * Returns an immutable instance of [BetaTelemetryDestinationState] whose [ofDisabled]
         * variant is built from the given required [reason].
         */
        @JvmStatic
        fun ofDisabled(reason: BetaTelemetryDestinationDisabledReason) =
            ofDisabled(BetaTelemetryDestinationStateDisabled.of(reason))

        /**
         * Alias for calling [ofDisabled] with
         * `BetaTelemetryDestinationDisabledReason.ofCaller(caller)`.
         */
        @JvmStatic
        fun ofDisabled(caller: BetaTelemetryDestinationDisabledReasonCaller) =
            ofDisabled(BetaTelemetryDestinationDisabledReason.ofCaller(caller))

        /**
         * Alias for calling [ofDisabled] with
         * `BetaTelemetryDestinationDisabledReason.ofSustainedFailure(sustainedFailure)`.
         */
        @JvmStatic
        fun ofDisabled(sustainedFailure: BetaTelemetryDestinationDisabledReasonSustainedFailure) =
            ofDisabled(BetaTelemetryDestinationDisabledReason.ofSustainedFailure(sustainedFailure))

        /**
         * Alias for calling [ofDisabled] with
         * `BetaTelemetryDestinationDisabledReason.ofInvalidAddress(invalidAddress)`.
         */
        @JvmStatic
        fun ofDisabled(invalidAddress: BetaTelemetryDestinationDisabledReasonInvalidAddress) =
            ofDisabled(BetaTelemetryDestinationDisabledReason.ofInvalidAddress(invalidAddress))

        /**
         * Alias for calling [ofDisabled] with
         * `BetaTelemetryDestinationDisabledReason.ofRedirect(redirect)`.
         */
        @JvmStatic
        fun ofDisabled(redirect: BetaTelemetryDestinationDisabledReasonRedirect) =
            ofDisabled(BetaTelemetryDestinationDisabledReason.ofRedirect(redirect))
    }

    /**
     * An interface that defines how to map each variant of [BetaTelemetryDestinationState] to a
     * value of type [T].
     */
    interface Visitor<out T> {

        /** The telemetry destination receives the spans of the workspaces that it covers. */
        fun visitEnabled(enabled: BetaTelemetryDestinationStateEnabled): T

        /**
         * The API doesn't deliver anything to the telemetry destination.
         *
         * Spans produced while it is disabled aren't delivered later.
         */
        fun visitDisabled(disabled: BetaTelemetryDestinationStateDisabled): T

        /**
         * Maps an unknown variant of [BetaTelemetryDestinationState] to a value of type [T].
         *
         * An instance of [BetaTelemetryDestinationState] can contain an unknown variant if it was
         * deserialized from data that doesn't match any known variant. For example, if the SDK is
         * on an older version than the API, then the API may respond with new variants that the SDK
         * is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown BetaTelemetryDestinationState: $json")
        }
    }

    internal class Deserializer :
        BaseDeserializer<BetaTelemetryDestinationState>(BetaTelemetryDestinationState::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): BetaTelemetryDestinationState {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "enabled" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaTelemetryDestinationStateEnabled>(),
                        )
                        ?.let { BetaTelemetryDestinationState(enabled = it, _json = json) }
                        ?: BetaTelemetryDestinationState(_json = json)
                }
                "disabled" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaTelemetryDestinationStateDisabled>(),
                        )
                        ?.let { BetaTelemetryDestinationState(disabled = it, _json = json) }
                        ?: BetaTelemetryDestinationState(_json = json)
                }
            }

            return BetaTelemetryDestinationState(_json = json)
        }
    }

    internal class Serializer :
        BaseSerializer<BetaTelemetryDestinationState>(BetaTelemetryDestinationState::class) {

        override fun serialize(
            value: BetaTelemetryDestinationState,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.enabled != null -> generator.writeObject(value.enabled)
                value.disabled != null -> generator.writeObject(value.disabled)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BetaTelemetryDestinationState")
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

            @JvmField val ENABLED = Type(JsonField.of("enabled"))

            @JvmField val DISABLED = Type(JsonField.of("disabled"))

            @JvmStatic
            fun of(value: String): Type =
                // Intern known values so `==` works
                when (value) {
                    "enabled" -> ENABLED
                    "disabled" -> DISABLED
                    else -> Type(JsonField.of(value))
                }

            @JsonCreator
            @JvmStatic
            fun of(value: JsonField<String>): Type =
                value.asString().getOrNull()?.let { of(it) } ?: Type(value)
        }

        /** An enum containing [Type]'s known values. */
        enum class Known {
            ENABLED,
            DISABLED,
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
            ENABLED,
            DISABLED,
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
                ENABLED -> Value.ENABLED
                DISABLED -> Value.DISABLED
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
                ENABLED -> Known.ENABLED
                DISABLED -> Known.DISABLED
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
