// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.compliancesettings

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

@JsonDeserialize(using = BetaComplianceSettingsState.Deserializer::class)
@JsonSerialize(using = BetaComplianceSettingsState.Serializer::class)
class BetaComplianceSettingsState
private constructor(
    private val enabled: BetaComplianceSettingsStateEnabled? = null,
    private val disabled: BetaComplianceSettingsStateDisabled? = null,
    private val _json: JsonValue? = null,
) {

    fun type(): Type =
        accept(
            object : Visitor<Type> {
                override fun visitEnabled(enabled: BetaComplianceSettingsStateEnabled): Type =
                    Type.ENABLED

                override fun visitDisabled(disabled: BetaComplianceSettingsStateDisabled): Type =
                    Type.DISABLED

                override fun unknown(json: JsonValue?): Type =
                    Type.of(json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
            }
        )

    fun enabled(): Optional<BetaComplianceSettingsStateEnabled> = Optional.ofNullable(enabled)

    fun disabled(): Optional<BetaComplianceSettingsStateDisabled> = Optional.ofNullable(disabled)

    fun isEnabled(): Boolean = enabled != null

    fun isDisabled(): Boolean = disabled != null

    fun asEnabled(): BetaComplianceSettingsStateEnabled = enabled.getOrThrow("enabled")

    fun asDisabled(): BetaComplianceSettingsStateDisabled = disabled.getOrThrow("disabled")

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
     * Optional<String> result = betaComplianceSettingsState.accept(new BetaComplianceSettingsState.Visitor<Optional<String>>() {
     *     @Override
     *     public Optional<String> visitEnabled(BetaComplianceSettingsStateEnabled enabled) {
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
    fun validate(): BetaComplianceSettingsState = apply {
        if (validated) {
            return@apply
        }

        accept(
            object : Visitor<Unit> {
                override fun visitEnabled(enabled: BetaComplianceSettingsStateEnabled) {
                    enabled.validate()
                }

                override fun visitDisabled(disabled: BetaComplianceSettingsStateDisabled) {
                    disabled.validate()
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
                override fun visitEnabled(enabled: BetaComplianceSettingsStateEnabled) =
                    enabled.validity()

                override fun visitDisabled(disabled: BetaComplianceSettingsStateDisabled) =
                    disabled.validity()

                override fun unknown(json: JsonValue?) = 0
            }
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaComplianceSettingsState &&
            enabled == other.enabled &&
            disabled == other.disabled
    }

    override fun hashCode(): Int = Objects.hash(enabled, disabled)

    override fun toString(): String =
        when {
            enabled != null -> "BetaComplianceSettingsState{enabled=$enabled}"
            disabled != null -> "BetaComplianceSettingsState{disabled=$disabled}"
            _json != null -> "BetaComplianceSettingsState{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaComplianceSettingsState")
        }

    companion object {

        @JvmStatic
        fun ofEnabled(enabled: BetaComplianceSettingsStateEnabled) =
            BetaComplianceSettingsState(enabled = enabled)

        @JvmStatic
        fun ofDisabled(disabled: BetaComplianceSettingsStateDisabled) =
            BetaComplianceSettingsState(disabled = disabled)
    }

    /**
     * An interface that defines how to map each variant of [BetaComplianceSettingsState] to a value
     * of type [T].
     */
    interface Visitor<out T> {

        fun visitEnabled(enabled: BetaComplianceSettingsStateEnabled): T

        fun visitDisabled(disabled: BetaComplianceSettingsStateDisabled): T

        /**
         * Maps an unknown variant of [BetaComplianceSettingsState] to a value of type [T].
         *
         * An instance of [BetaComplianceSettingsState] can contain an unknown variant if it was
         * deserialized from data that doesn't match any known variant. For example, if the SDK is
         * on an older version than the API, then the API may respond with new variants that the SDK
         * is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown BetaComplianceSettingsState: $json")
        }
    }

    internal class Deserializer :
        BaseDeserializer<BetaComplianceSettingsState>(BetaComplianceSettingsState::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): BetaComplianceSettingsState {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "enabled" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaComplianceSettingsStateEnabled>(),
                        )
                        ?.let { BetaComplianceSettingsState(enabled = it, _json = json) }
                        ?: BetaComplianceSettingsState(_json = json)
                }
                "disabled" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaComplianceSettingsStateDisabled>(),
                        )
                        ?.let { BetaComplianceSettingsState(disabled = it, _json = json) }
                        ?: BetaComplianceSettingsState(_json = json)
                }
            }

            return BetaComplianceSettingsState(_json = json)
        }
    }

    internal class Serializer :
        BaseSerializer<BetaComplianceSettingsState>(BetaComplianceSettingsState::class) {

        override fun serialize(
            value: BetaComplianceSettingsState,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.enabled != null -> generator.writeObject(value.enabled)
                value.disabled != null -> generator.writeObject(value.disabled)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BetaComplianceSettingsState")
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

            @JvmField val ENABLED = of("enabled")

            @JvmField val DISABLED = of("disabled")

            @JvmStatic fun of(value: String) = Type(JsonField.of(value))

            @JvmSynthetic
            internal fun of(value: JsonField<String>): Type =
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
