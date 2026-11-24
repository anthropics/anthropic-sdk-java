package com.anthropic.models.beta.userprofiles.providers.google.datacontrols

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

/** The customer-managed encryption key control, discriminated by `type`. */
@JsonDeserialize(using = BetaGoogleManagedCmekControl.Deserializer::class)
@JsonSerialize(using = BetaGoogleManagedCmekControl.Serializer::class)
class BetaGoogleManagedCmekControl
private constructor(
    private val disabled: BetaGoogleManagedCmekDisabled? = null,
    private val enabled: BetaGoogleManagedCmekEnabled? = null,
    private val _json: JsonValue? = null,
) {

    fun type(): Type =
        when {
            disabled != null -> Type.DISABLED
            enabled != null -> Type.ENABLED
            else -> Type.of(_json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
        }

    fun retiredKeys(): List<BetaGoogleManagedRetiredKey> =
        when {
            disabled != null -> disabled.retiredKeys()
            enabled != null -> enabled.retiredKeys()
            else ->
                _json
                    .getProperty<List<BetaGoogleManagedRetiredKey>>("retired_keys")
                    .getRequired("retired_keys")
        }

    /** The control is off and has no keys. */
    fun disabled(): Optional<BetaGoogleManagedCmekDisabled> = Optional.ofNullable(disabled)

    /** The control is on and has a key for at least one region. */
    fun enabled(): Optional<BetaGoogleManagedCmekEnabled> = Optional.ofNullable(enabled)

    fun isDisabled(): Boolean = disabled != null

    fun isEnabled(): Boolean = enabled != null

    /** The control is off and has no keys. */
    fun asDisabled(): BetaGoogleManagedCmekDisabled = disabled.getOrThrow("disabled")

    /** The control is on and has a key for at least one region. */
    fun asEnabled(): BetaGoogleManagedCmekEnabled = enabled.getOrThrow("enabled")

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
     * Optional<String> result = betaGoogleManagedCmekControl.accept(new BetaGoogleManagedCmekControl.Visitor<Optional<String>>() {
     *     @Override
     *     public Optional<String> visitDisabled(BetaGoogleManagedCmekDisabled disabled) {
     *         return Optional.of(disabled.toString());
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
            disabled != null -> visitor.visitDisabled(disabled)
            enabled != null -> visitor.visitEnabled(enabled)
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
    fun validate(): BetaGoogleManagedCmekControl = apply {
        if (validated) {
            return@apply
        }

        when {
            disabled != null -> disabled.validate()
            enabled != null -> enabled.validate()
            else ->
                throw AnthropicInvalidDataException("Unknown BetaGoogleManagedCmekControl: $_json")
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
            disabled != null -> disabled.validity()
            enabled != null -> enabled.validity()
            else -> 0
        }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaGoogleManagedCmekControl &&
            disabled == other.disabled &&
            enabled == other.enabled
    }

    override fun hashCode(): Int = Objects.hash(disabled, enabled)

    override fun toString(): String =
        when {
            disabled != null -> "BetaGoogleManagedCmekControl{disabled=$disabled}"
            enabled != null -> "BetaGoogleManagedCmekControl{enabled=$enabled}"
            _json != null -> "BetaGoogleManagedCmekControl{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaGoogleManagedCmekControl")
        }

    companion object {

        /** The control is off and has no keys. */
        @JvmStatic
        fun ofDisabled(disabled: BetaGoogleManagedCmekDisabled) =
            BetaGoogleManagedCmekControl(disabled = disabled)

        /**
         * Returns an immutable instance of [BetaGoogleManagedCmekControl] whose [ofDisabled]
         * variant is built from the given required [retiredKeys].
         */
        @JvmStatic
        fun ofDisabled(retiredKeys: List<BetaGoogleManagedRetiredKey>) =
            ofDisabled(BetaGoogleManagedCmekDisabled.of(retiredKeys))

        /** The control is on and has a key for at least one region. */
        @JvmStatic
        fun ofEnabled(enabled: BetaGoogleManagedCmekEnabled) =
            BetaGoogleManagedCmekControl(enabled = enabled)
    }

    /**
     * An interface that defines how to map each variant of [BetaGoogleManagedCmekControl] to a
     * value of type [T].
     */
    interface Visitor<out T> {

        /** The control is off and has no keys. */
        fun visitDisabled(disabled: BetaGoogleManagedCmekDisabled): T

        /** The control is on and has a key for at least one region. */
        fun visitEnabled(enabled: BetaGoogleManagedCmekEnabled): T

        /**
         * Maps an unknown variant of [BetaGoogleManagedCmekControl] to a value of type [T].
         *
         * An instance of [BetaGoogleManagedCmekControl] can contain an unknown variant if it was
         * deserialized from data that doesn't match any known variant. For example, if the SDK is
         * on an older version than the API, then the API may respond with new variants that the SDK
         * is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown BetaGoogleManagedCmekControl: $json")
        }
    }

    internal class Deserializer :
        BaseDeserializer<BetaGoogleManagedCmekControl>(BetaGoogleManagedCmekControl::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): BetaGoogleManagedCmekControl {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "disabled" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaGoogleManagedCmekDisabled>())
                        ?.let { BetaGoogleManagedCmekControl(disabled = it, _json = json) }
                        ?: BetaGoogleManagedCmekControl(_json = json)
                }
                "enabled" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaGoogleManagedCmekEnabled>())
                        ?.let { BetaGoogleManagedCmekControl(enabled = it, _json = json) }
                        ?: BetaGoogleManagedCmekControl(_json = json)
                }
            }

            return BetaGoogleManagedCmekControl(_json = json)
        }
    }

    internal class Serializer :
        BaseSerializer<BetaGoogleManagedCmekControl>(BetaGoogleManagedCmekControl::class) {

        override fun serialize(
            value: BetaGoogleManagedCmekControl,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.disabled != null -> generator.writeObject(value.disabled)
                value.enabled != null -> generator.writeObject(value.enabled)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BetaGoogleManagedCmekControl")
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

            @JvmField val DISABLED = Type(JsonField.of("disabled"))

            @JvmField val ENABLED = Type(JsonField.of("enabled"))

            @JvmStatic
            fun of(value: String): Type =
                // Intern known values so `==` works
                when (value) {
                    "disabled" -> DISABLED
                    "enabled" -> ENABLED
                    else -> Type(JsonField.of(value))
                }

            @JsonCreator
            @JvmStatic
            fun of(value: JsonField<String>): Type =
                value.asString().getOrNull()?.let { of(it) } ?: Type(value)
        }

        /** An enum containing [Type]'s known values. */
        enum class Known {
            DISABLED,
            ENABLED,
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
            DISABLED,
            ENABLED,
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
                DISABLED -> Value.DISABLED
                ENABLED -> Value.ENABLED
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
                DISABLED -> Known.DISABLED
                ENABLED -> Known.ENABLED
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
