// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.models

import kotlinx.kmp.util.core.ExcludeMissing
import kotlinx.kmp.util.core.JsonField
import kotlinx.kmp.util.core.JsonMissing
import kotlinx.kmp.util.core.JsonValue
import kotlinx.kmp.util.core.checkRequired
import kotlinx.kmp.util.core.errors.ApiInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.kmp.util.core.contentHash

/** Supported thinking type configurations. */
class ThinkingTypes
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val adaptive: JsonField<CapabilitySupport>,
    private val enabled: JsonField<CapabilitySupport>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("adaptive")
        @ExcludeMissing
        adaptive: JsonField<CapabilitySupport> = JsonMissing.of(),
        @JsonProperty("enabled")
        @ExcludeMissing
        enabled: JsonField<CapabilitySupport> = JsonMissing.of(),
    ) : this(adaptive, enabled, mutableMapOf())

    /**
     * Whether the model supports thinking with type 'adaptive' (auto).
     *
     * @throws ApiInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun adaptive(): CapabilitySupport = adaptive.getRequired("adaptive")

    /**
     * Whether the model supports thinking with type 'enabled'.
     *
     * @throws ApiInvalidDataException if the JSON field has an unexpected type or is
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
        additionalProperties.toMap()

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [ThinkingTypes].
         *
         * The following fields are required:
         * ```java
         * .adaptive()
         * .enabled()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [ThinkingTypes]. */
    class Builder internal constructor() {

        private var adaptive: JsonField<CapabilitySupport>? = null
        private var enabled: JsonField<CapabilitySupport>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic internal fun from(thinkingTypes: ThinkingTypes) = apply {
            adaptive = thinkingTypes.adaptive
            enabled = thinkingTypes.enabled
            additionalProperties = thinkingTypes.additionalProperties.toMutableMap()
        }

        /** Whether the model supports thinking with type 'adaptive' (auto). */
        fun adaptive(adaptive: CapabilitySupport) = adaptive(JsonField.of(adaptive))

        /**
         * Sets [Builder.adaptive] to an arbitrary JSON value.
         *
         * You should usually call [Builder.adaptive] with a well-typed [CapabilitySupport] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun adaptive(adaptive: JsonField<CapabilitySupport>) = apply { this.adaptive = adaptive }

        /** Whether the model supports thinking with type 'enabled'. */
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
         * .enabled()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): ThinkingTypes =
            ThinkingTypes(
                checkRequired("adaptive", adaptive),
                checkRequired("enabled", enabled),
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): ThinkingTypes = apply {
        if (validated) {
            return@apply
        }

        adaptive().validate()
        enabled().validate()
        validated = true
    }

    fun isValid(): Boolean =
        try {
            validate()
            true
        } catch (e: ApiInvalidDataException) {
            false
        }

    /**
     * Returns a score indicating how many valid values are contained in this object recursively.
     *
     * Used for best match union deserialization.
     */
    internal fun validity(): Int =
        (adaptive.asKnown()?.validity() ?: 0) +
            (enabled.asKnown()?.validity() ?: 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is ThinkingTypes &&
            adaptive == other.adaptive &&
            enabled == other.enabled &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { contentHash(adaptive, enabled, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "ThinkingTypes{adaptive=$adaptive, enabled=$enabled, additionalProperties=$additionalProperties}"
}
