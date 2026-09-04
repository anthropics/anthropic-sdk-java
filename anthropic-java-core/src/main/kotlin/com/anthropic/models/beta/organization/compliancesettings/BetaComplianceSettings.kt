// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.compliancesettings

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkRequired
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Collections
import java.util.Objects
import kotlin.jvm.optionals.getOrNull

class BetaComplianceSettings
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val state: JsonField<BetaComplianceSettingsState>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("state")
        @ExcludeMissing
        state: JsonField<BetaComplianceSettingsState> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(state, type, mutableMapOf())

    /**
     * Whether the Compliance API is enabled for this organization.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun state(): BetaComplianceSettingsState = state.getRequired("state")

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
    @JsonProperty("state")
    @ExcludeMissing
    fun _state(): JsonField<BetaComplianceSettingsState> = state

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
        @JvmStatic fun of(state: BetaComplianceSettingsState) = builder().state(state).build()
    }

    /** A builder for [BetaComplianceSettings]. */
    class Builder internal constructor() {

        private var state: JsonField<BetaComplianceSettingsState>? = null
        private var type: JsonValue = JsonValue.from("compliance_settings")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaComplianceSettings: BetaComplianceSettings) = apply {
            state = betaComplianceSettings.state
            type = betaComplianceSettings.type
            additionalProperties = betaComplianceSettings.additionalProperties.toMutableMap()
        }

        /** Whether the Compliance API is enabled for this organization. */
        fun state(state: BetaComplianceSettingsState) = state(JsonField.of(state))

        /**
         * Sets [Builder.state] to an arbitrary JSON value.
         *
         * You should usually call [Builder.state] with a well-typed [BetaComplianceSettingsState]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun state(state: JsonField<BetaComplianceSettingsState>) = apply { this.state = state }

        /** Alias for calling [state] with `BetaComplianceSettingsState.ofEnabled(enabled)`. */
        fun state(enabled: BetaComplianceSettingsStateEnabled) =
            state(BetaComplianceSettingsState.ofEnabled(enabled))

        /** Alias for calling [state] with `BetaComplianceSettingsState.ofDisabled(disabled)`. */
        fun state(disabled: BetaComplianceSettingsStateDisabled) =
            state(BetaComplianceSettingsState.ofDisabled(disabled))

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
