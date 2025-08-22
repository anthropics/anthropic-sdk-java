// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkKnown
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

class BetaRequestMcpServerToolConfiguration
private constructor(
    private val allowedTools: JsonField<List<String>>,
    private val enabled: JsonField<Boolean>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("allowed_tools")
        @ExcludeMissing
        allowedTools: JsonField<List<String>> = JsonMissing.of(),
        @JsonProperty("enabled") @ExcludeMissing enabled: JsonField<Boolean> = JsonMissing.of(),
    ) : this(allowedTools, enabled, mutableMapOf())

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun allowedTools(): Optional<List<String>> = allowedTools.getOptional("allowed_tools")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun enabled(): Optional<Boolean> = enabled.getOptional("enabled")

    /**
     * Returns the raw JSON value of [allowedTools].
     *
     * Unlike [allowedTools], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("allowed_tools")
    @ExcludeMissing
    fun _allowedTools(): JsonField<List<String>> = allowedTools

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
        Collections.unmodifiableMap(additionalProperties)

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of
         * [BetaRequestMcpServerToolConfiguration].
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaRequestMcpServerToolConfiguration]. */
    class Builder internal constructor() {

        private var allowedTools: JsonField<MutableList<String>>? = null
        private var enabled: JsonField<Boolean> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaRequestMcpServerToolConfiguration: BetaRequestMcpServerToolConfiguration
        ) = apply {
            allowedTools =
                betaRequestMcpServerToolConfiguration.allowedTools.map { it.toMutableList() }
            enabled = betaRequestMcpServerToolConfiguration.enabled
            additionalProperties =
                betaRequestMcpServerToolConfiguration.additionalProperties.toMutableMap()
        }

        fun allowedTools(allowedTools: List<String>?) =
            allowedTools(JsonField.ofNullable(allowedTools))

        /** Alias for calling [Builder.allowedTools] with `allowedTools.orElse(null)`. */
        fun allowedTools(allowedTools: Optional<List<String>>) =
            allowedTools(allowedTools.getOrNull())

        /**
         * Sets [Builder.allowedTools] to an arbitrary JSON value.
         *
         * You should usually call [Builder.allowedTools] with a well-typed `List<String>` value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun allowedTools(allowedTools: JsonField<List<String>>) = apply {
            this.allowedTools = allowedTools.map { it.toMutableList() }
        }

        /**
         * Adds a single [String] to [allowedTools].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addAllowedTool(allowedTool: String) = apply {
            allowedTools =
                (allowedTools ?: JsonField.of(mutableListOf())).also {
                    checkKnown("allowedTools", it).add(allowedTool)
                }
        }

        fun enabled(enabled: Boolean?) = enabled(JsonField.ofNullable(enabled))

        /**
         * Alias for [Builder.enabled].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun enabled(enabled: Boolean) = enabled(enabled as Boolean?)

        /** Alias for calling [Builder.enabled] with `enabled.orElse(null)`. */
        fun enabled(enabled: Optional<Boolean>) = enabled(enabled.getOrNull())

        /**
         * Sets [Builder.enabled] to an arbitrary JSON value.
         *
         * You should usually call [Builder.enabled] with a well-typed [Boolean] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun enabled(enabled: JsonField<Boolean>) = apply { this.enabled = enabled }

        fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
            this.additionalProperties.clear()
            putAlladditional_properties(additionalProperties)
        }

        fun putAdditionalProperty(key: String, value: JsonValue) = apply {
            additionalProperties.put(key, value)
        }

        fun putAlladditional_properties(additionalProperties: Map<String, JsonValue>) = apply {
            this.additionalProperties.putAll(additionalProperties)
        }

        fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

        fun removeAlladditional_properties(keys: Set<String>) = apply {
            keys.forEach(::removeAdditionalProperty)
        }

        /**
         * Returns an immutable instance of [BetaRequestMcpServerToolConfiguration].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): BetaRequestMcpServerToolConfiguration =
            BetaRequestMcpServerToolConfiguration(
                (allowedTools ?: JsonMissing.of()).map { it.toImmutable() },
                enabled,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): BetaRequestMcpServerToolConfiguration = apply {
        if (validated) {
            return@apply
        }

        allowedTools()
        enabled()
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
        (allowedTools.asKnown().getOrNull()?.size ?: 0) +
            (if (enabled.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaRequestMcpServerToolConfiguration &&
            allowedTools == other.allowedTools &&
            enabled == other.enabled &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(allowedTools, enabled, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaRequestMcpServerToolConfiguration{allowedTools=$allowedTools, enabled=$enabled, additionalProperties=$additionalProperties}"
}
