package com.anthropic.models.models

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

/**
 * Which `thinking.type` values the model accepts on requests. Read each key on its own: for
 * example, `enabled` can be false while `disabled` is true.
 */
class ThinkingTypes
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val adaptive: JsonField<CapabilitySupport>,
    private val disabled: JsonField<CapabilitySupport>,
    private val enabled: JsonField<CapabilitySupport>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("adaptive")
        @ExcludeMissing
        adaptive: JsonField<CapabilitySupport> = JsonMissing.of(),
        @JsonProperty("disabled")
        @ExcludeMissing
        disabled: JsonField<CapabilitySupport> = JsonMissing.of(),
        @JsonProperty("enabled")
        @ExcludeMissing
        enabled: JsonField<CapabilitySupport> = JsonMissing.of(),
    ) : this(adaptive, disabled, enabled, mutableMapOf())

    /**
     * Whether the model accepts thinking with type 'adaptive' (the model decides whether and how
     * much to think).
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun adaptive(): CapabilitySupport = adaptive.getRequired("adaptive")

    /**
     * Whether the model accepts thinking with type 'disabled' (thinking turned off). False exactly
     * when a request that sends it gets a 400 from this model. True on a model that does not
     * support thinking.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun disabled(): CapabilitySupport = disabled.getRequired("disabled")

    /**
     * Whether the model accepts thinking with type 'enabled' (extended thinking with a caller-set
     * `budget_tokens`).
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun enabled(): CapabilitySupport = enabled.getRequired("enabled")

    /**
     * Returns the raw JSON value of [adaptive].
     *
     * Unlike [adaptive], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("adaptive")
    @ExcludeMissing
    fun _adaptive(): JsonField<CapabilitySupport> = adaptive

    /**
     * Returns the raw JSON value of [disabled].
     *
     * Unlike [disabled], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("disabled")
    @ExcludeMissing
    fun _disabled(): JsonField<CapabilitySupport> = disabled

    /**
     * Returns the raw JSON value of [enabled].
     *
     * Unlike [enabled], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("enabled") @ExcludeMissing fun _enabled(): JsonField<CapabilitySupport> = enabled

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
         * Returns a mutable builder for constructing an instance of [ThinkingTypes].
         *
         * The following fields are required:
         * ```java
         * .adaptive()
         * .disabled()
         * .enabled()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [ThinkingTypes]. */
    class Builder internal constructor() {

        private var adaptive: JsonField<CapabilitySupport>? = null
        private var disabled: JsonField<CapabilitySupport>? = null
        private var enabled: JsonField<CapabilitySupport>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(thinkingTypes: ThinkingTypes) = apply {
            adaptive = thinkingTypes.adaptive
            disabled = thinkingTypes.disabled
            enabled = thinkingTypes.enabled
            additionalProperties = thinkingTypes.additionalProperties.toMutableMap()
        }

        /**
         * Whether the model accepts thinking with type 'adaptive' (the model decides whether and
         * how much to think).
         */
        fun adaptive(adaptive: CapabilitySupport) = adaptive(JsonField.of(adaptive))

        /**
         * Sets [Builder.adaptive] to an arbitrary JSON value.
         *
         * You should usually call [Builder.adaptive] with a well-typed [CapabilitySupport] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun adaptive(adaptive: JsonField<CapabilitySupport>) = apply { this.adaptive = adaptive }

        /**
         * Whether the model accepts thinking with type 'disabled' (thinking turned off). False
         * exactly when a request that sends it gets a 400 from this model. True on a model that
         * does not support thinking.
         */
        fun disabled(disabled: CapabilitySupport) = disabled(JsonField.of(disabled))

        /**
         * Sets [Builder.disabled] to an arbitrary JSON value.
         *
         * You should usually call [Builder.disabled] with a well-typed [CapabilitySupport] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun disabled(disabled: JsonField<CapabilitySupport>) = apply { this.disabled = disabled }

        /**
         * Whether the model accepts thinking with type 'enabled' (extended thinking with a
         * caller-set `budget_tokens`).
         */
        fun enabled(enabled: CapabilitySupport) = enabled(JsonField.of(enabled))

        /**
         * Sets [Builder.enabled] to an arbitrary JSON value.
         *
         * You should usually call [Builder.enabled] with a well-typed [CapabilitySupport] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun enabled(enabled: JsonField<CapabilitySupport>) = apply { this.enabled = enabled }

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
         * Returns an immutable instance of [ThinkingTypes].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .adaptive()
         * .disabled()
         * .enabled()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): ThinkingTypes =
            ThinkingTypes(
                checkRequired("adaptive", adaptive),
                checkRequired("disabled", disabled),
                checkRequired("enabled", enabled),
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
    fun validate(): ThinkingTypes = apply {
        if (validated) {
            return@apply
        }

        adaptive().validate()
        disabled().validate()
        enabled().validate()
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
        (adaptive.asKnown().getOrNull()?.validity() ?: 0) +
            (disabled.asKnown().getOrNull()?.validity() ?: 0) +
            (enabled.asKnown().getOrNull()?.validity() ?: 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is ThinkingTypes &&
            adaptive == other.adaptive &&
            disabled == other.disabled &&
            enabled == other.enabled &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(adaptive, disabled, enabled, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "ThinkingTypes{adaptive=$adaptive, disabled=$disabled, enabled=$enabled, additionalProperties=$additionalProperties}"
}
