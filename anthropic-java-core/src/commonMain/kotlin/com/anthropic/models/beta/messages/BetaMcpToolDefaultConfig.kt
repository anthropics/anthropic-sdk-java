// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages
import kotlinx.kmp.util.core.getOptional

import kotlinx.kmp.util.core.ExcludeMissing
import kotlinx.kmp.util.core.JsonField
import kotlinx.kmp.util.core.JsonMissing
import kotlinx.kmp.util.core.JsonValue
import kotlinx.kmp.util.core.errors.ApiInvalidDataException
import kotlinx.kmp.util.core.json.JsonAnyGetter
import kotlinx.kmp.util.core.json.JsonAnySetter
import kotlinx.kmp.util.core.json.JsonCreator
import kotlinx.kmp.util.core.json.JsonCreatorMode
import kotlinx.kmp.util.core.json.JsonProperty
import kotlinx.kmp.util.core.contentHash
import java.util.Optional

/** Default configuration for tools in an MCP toolset. */
class BetaMcpToolDefaultConfig
@JsonCreator(mode = JsonCreatorMode.DISABLED)
private constructor(
    private val deferLoading: JsonField<Boolean>,
    private val enabled: JsonField<Boolean>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("defer_loading")
        @ExcludeMissing
        deferLoading: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("enabled") @ExcludeMissing enabled: JsonField<Boolean> = JsonMissing.of(),
    ) : this(deferLoading, enabled, mutableMapOf())

    /**
     * @throws ApiInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun deferLoading(): Optional<Boolean> = deferLoading.getOptional("defer_loading")

    /**
     * @throws ApiInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun enabled(): Optional<Boolean> = enabled.getOptional("enabled")

    /**
     * Returns the raw JSON value of [deferLoading].
     *
     * Unlike [deferLoading], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("defer_loading")
    @ExcludeMissing
    fun _deferLoading(): JsonField<Boolean> = deferLoading

    /**
     * Returns the raw JSON value of [enabled].
     *
     * Unlike [enabled], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("enabled") @ExcludeMissing fun _enabled(): JsonField<Boolean> = enabled

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

        /** Returns a mutable builder for constructing an instance of [BetaMcpToolDefaultConfig]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaMcpToolDefaultConfig]. */
    class Builder internal constructor() {

        private var deferLoading: JsonField<Boolean> = JsonMissing.of()
        private var enabled: JsonField<Boolean> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic internal fun from(betaMcpToolDefaultConfig: BetaMcpToolDefaultConfig) = apply {
            deferLoading = betaMcpToolDefaultConfig.deferLoading
            enabled = betaMcpToolDefaultConfig.enabled
            additionalProperties = betaMcpToolDefaultConfig.additionalProperties.toMutableMap()
        }

        fun deferLoading(deferLoading: Boolean) = deferLoading(JsonField.of(deferLoading))

        /**
         * Sets [Builder.deferLoading] to an arbitrary JSON value.
         *
         * You should usually call [Builder.deferLoading] with a well-typed [Boolean] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun deferLoading(deferLoading: JsonField<Boolean>) = apply {
            this.deferLoading = deferLoading
        }

        fun enabled(enabled: Boolean) = enabled(JsonField.of(enabled))

        /**
         * Sets [Builder.enabled] to an arbitrary JSON value.
         *
         * You should usually call [Builder.enabled] with a well-typed [Boolean] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun enabled(enabled: JsonField<Boolean>) = apply { this.enabled = enabled }

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
         * Returns an immutable instance of [BetaMcpToolDefaultConfig].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): BetaMcpToolDefaultConfig =
            BetaMcpToolDefaultConfig(deferLoading, enabled, additionalProperties.toMutableMap())
    }

    private var validated: Boolean = false

    fun validate(): BetaMcpToolDefaultConfig = apply {
        if (validated) {
            return@apply
        }

        deferLoading()
        enabled()
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
        (if (deferLoading.asKnown() != null) 1 else 0) +
            (if (enabled.asKnown() != null) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaMcpToolDefaultConfig &&
            deferLoading == other.deferLoading &&
            enabled == other.enabled &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { contentHash(deferLoading, enabled, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaMcpToolDefaultConfig{deferLoading=$deferLoading, enabled=$enabled, additionalProperties=$additionalProperties}"
}
