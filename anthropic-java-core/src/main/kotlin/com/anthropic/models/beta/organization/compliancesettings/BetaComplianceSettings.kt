// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.compliancesettings

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.Enum
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkRequired
import com.anthropic.core.getOrThrow
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.ObjectCodec
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

class BetaComplianceSettings
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val state: JsonField<State>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("state") @ExcludeMissing state: JsonField<State> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(state, type, mutableMapOf())

    /**
     * Whether the Compliance API is enabled for this organization.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun state(): State = state.getRequired("state")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("compliance_settings")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Returns the raw JSON value of [state].
     *
     * Unlike [state], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("state") @ExcludeMissing fun _state(): JsonField<State> = state

    @JsonAnySetter
    private fun putAdditionalProperty(key: String, value: JsonValue) {
        additionalProperties.put(key, value)
    }

    @JsonAnyGetter
    @ExcludeMissing
    fun _additionalProperties(): Map<String, JsonValue> =
        Collections.unmodifiableMap(additionalProperties)

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [BetaComplianceSettings].
         *
         * The following fields are required:
         * ```java
         * .state()
         * ```
         */
        @JvmStatic fun builder() = Builder()

        /**
         * Returns an immutable instance of [BetaComplianceSettings] with the required [state] set
         * to the given value.
         */
        @JvmStatic fun of(state: State) = builder().state(state).build()
    }

    /** A builder for [BetaComplianceSettings]. */
    class Builder internal constructor() {

        private var state: JsonField<State>? = null
        private var type: JsonValue = JsonValue.from("compliance_settings")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaComplianceSettings: BetaComplianceSettings) = apply {
            state = betaComplianceSettings.state
            type = betaComplianceSettings.type
            additionalProperties = betaComplianceSettings.additionalProperties.toMutableMap()
        }

        /** Whether the Compliance API is enabled for this organization. */
        fun state(state: State) = state(JsonField.of(state))

        /**
         * Sets [Builder.state] to an arbitrary JSON value.
         *
         * You should usually call [Builder.state] with a well-typed [State] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun state(state: JsonField<State>) = apply { this.state = state }

        /** Alias for calling [state] with `State.ofEnabled(enabled)`. */
        fun state(enabled: BetaComplianceSettingsStateEnabled) = state(State.ofEnabled(enabled))

        /** Alias for calling [state] with `State.ofDisabled(disabled)`. */
        fun state(disabled: BetaComplianceSettingsStateDisabled) = state(State.ofDisabled(disabled))

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("compliance_settings")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
            this.additionalProperties.clear()
            putAllAdditionalProperties(additionalProperties)
        }

        fun putAdditionalProperty(key: String, value: JsonValue) = apply {
            additionalProperties.put(key, value)
        }

        fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
            this.additionalProperties.putAll(additionalProperties)
        }

        fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

        fun removeAllAdditionalProperties(keys: Set<String>) = apply {
            keys.forEach(::removeAdditionalProperty)
        }

        /**
         * Returns an immutable instance of [BetaComplianceSettings].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .state()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaComplianceSettings =
            BetaComplianceSettings(
                checkRequired("state", state),
                type,
                additionalProperties.toMutableMap(),
            )
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
    fun validate(): BetaComplianceSettings = apply {
        if (validated) {
            return@apply
        }

        state().validate()
        _type().let {
            if (it != JsonValue.from("compliance_settings")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
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
        (state.asKnown().getOrNull()?.validity() ?: 0) +
            type.let { if (it == JsonValue.from("compliance_settings")) 1 else 0 }

    /** Whether the Compliance API is enabled for this organization. */
    @JsonDeserialize(using = State.Deserializer::class)
    @JsonSerialize(using = State.Serializer::class)
    class State
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

                    override fun visitDisabled(
                        disabled: BetaComplianceSettingsStateDisabled
                    ): Type = Type.DISABLED

                    override fun unknown(json: JsonValue?): Type =
                        Type.of(json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
                }
            )

        fun enabled(): Optional<BetaComplianceSettingsStateEnabled> = Optional.ofNullable(enabled)

        fun disabled(): Optional<BetaComplianceSettingsStateDisabled> =
            Optional.ofNullable(disabled)

        fun isEnabled(): Boolean = enabled != null

        fun isDisabled(): Boolean = disabled != null

        fun asEnabled(): BetaComplianceSettingsStateEnabled = enabled.getOrThrow("enabled")

        fun asDisabled(): BetaComplianceSettingsStateDisabled = disabled.getOrThrow("disabled")

        fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

        /**
         * Maps this instance's current variant to a value of type [T] using the given [visitor].
         *
         * Note that this method is _not_ forwards compatible with new variants from the API, unless
         * [visitor] overrides [Visitor.unknown]. To handle variants not known to this version of
         * the SDK gracefully, consider overriding [Visitor.unknown]:
         * ```java
         * import com.anthropic.core.JsonValue;
         * import java.util.Optional;
         *
         * Optional<String> result = state.accept(new State.Visitor<Optional<String>>() {
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
         * @throws AnthropicInvalidDataException if [Visitor.unknown] is not overridden in [visitor]
         *   and the current variant is unknown.
         */
        fun <T> accept(visitor: Visitor<T>): T =
            when {
                enabled != null -> visitor.visitEnabled(enabled)
                disabled != null -> visitor.visitDisabled(disabled)
                else -> visitor.unknown(_json)
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
        fun validate(): State = apply {
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
         * Returns a score indicating how many valid values are contained in this object
         * recursively.
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

            return other is State && enabled == other.enabled && disabled == other.disabled
        }

        override fun hashCode(): Int = Objects.hash(enabled, disabled)

        override fun toString(): String =
            when {
                enabled != null -> "State{enabled=$enabled}"
                disabled != null -> "State{disabled=$disabled}"
                _json != null -> "State{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid State")
            }

        companion object {

            @JvmStatic
            fun ofEnabled(enabled: BetaComplianceSettingsStateEnabled) = State(enabled = enabled)

            @JvmStatic
            fun ofDisabled(disabled: BetaComplianceSettingsStateDisabled) =
                State(disabled = disabled)
        }

        /** An interface that defines how to map each variant of [State] to a value of type [T]. */
        interface Visitor<out T> {

            fun visitEnabled(enabled: BetaComplianceSettingsStateEnabled): T

            fun visitDisabled(disabled: BetaComplianceSettingsStateDisabled): T

            /**
             * Maps an unknown variant of [State] to a value of type [T].
             *
             * An instance of [State] can contain an unknown variant if it was deserialized from
             * data that doesn't match any known variant. For example, if the SDK is on an older
             * version than the API, then the API may respond with new variants that the SDK is
             * unaware of.
             *
             * @throws AnthropicInvalidDataException in the default implementation.
             */
            fun unknown(json: JsonValue?): T {
                throw AnthropicInvalidDataException("Unknown State: $json")
            }
        }

        internal class Deserializer : BaseDeserializer<State>(State::class) {

            override fun ObjectCodec.deserialize(node: JsonNode): State {
                val json = JsonValue.fromJsonNode(node)
                val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

                when (type) {
                    "enabled" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaComplianceSettingsStateEnabled>(),
                            )
                            ?.let { State(enabled = it, _json = json) } ?: State(_json = json)
                    }
                    "disabled" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaComplianceSettingsStateDisabled>(),
                            )
                            ?.let { State(disabled = it, _json = json) } ?: State(_json = json)
                    }
                }

                return State(_json = json)
            }
        }

        internal class Serializer : BaseSerializer<State>(State::class) {

            override fun serialize(
                value: State,
                generator: JsonGenerator,
                provider: SerializerProvider,
            ) {
                when {
                    value.enabled != null -> generator.writeObject(value.enabled)
                    value.disabled != null -> generator.writeObject(value.disabled)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid State")
                }
            }
        }

        class Type @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

            /**
             * Returns this class instance's raw value.
             *
             * This is usually only useful if this instance was deserialized from data that doesn't
             * match any known member, and you want to know that value. For example, if the SDK is
             * on an older version than the API, then the API may respond with new members that the
             * SDK is unaware of.
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
             * - It was deserialized from data that doesn't match any known member. For example, if
             *   the SDK is on an older version than the API, then the API may respond with new
             *   members that the SDK is unaware of.
             * - It was constructed with an arbitrary value using the [of] method.
             */
            enum class Value {
                ENABLED,
                DISABLED,
                /** An enum member indicating that [Type] was instantiated with an unknown value. */
                _UNKNOWN,
            }

            /**
             * Returns an enum member corresponding to this class instance's value, or
             * [Value._UNKNOWN] if the class was instantiated with an unknown value.
             *
             * Use the [known] method instead if you're certain the value is always known or if you
             * want to throw for the unknown case.
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
             * Use the [value] method instead if you're uncertain the value is always known and
             * don't want to throw for the unknown case.
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
             * This differs from the [toString] method because that method is primarily for
             * debugging and generally doesn't throw.
             *
             * @throws AnthropicInvalidDataException if this class instance's value does not have
             *   the expected primitive type.
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
             * This method is _not_ forwards compatible with new types from the API for existing
             * fields.
             *
             * @throws AnthropicInvalidDataException if any value type in this object doesn't match
             *   its expected type.
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

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaComplianceSettings &&
            state == other.state &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(state, type, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaComplianceSettings{state=$state, type=$type, additionalProperties=$additionalProperties}"
}
