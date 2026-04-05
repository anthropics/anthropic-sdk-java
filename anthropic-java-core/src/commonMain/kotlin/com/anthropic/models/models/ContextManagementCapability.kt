// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.models
import kotlinx.kmp.util.core.getOptional

import kotlinx.kmp.util.core.ExcludeMissing
import kotlinx.kmp.util.core.JsonField
import kotlinx.kmp.util.core.JsonMissing
import kotlinx.kmp.util.core.JsonValue
import kotlinx.kmp.util.core.checkRequired
import kotlinx.kmp.util.core.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.kmp.util.core.contentHash
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** Context management capability details. */
class ContextManagementCapability
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val clearThinking20251015: JsonField<CapabilitySupport>,
    private val clearToolUses20250919: JsonField<CapabilitySupport>,
    private val compact20260112: JsonField<CapabilitySupport>,
    private val supported: JsonField<Boolean>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("clear_thinking_20251015")
        @ExcludeMissing
        clearThinking20251015: JsonField<CapabilitySupport> = JsonMissing.of(),
        @JsonProperty("clear_tool_uses_20250919")
        @ExcludeMissing
        clearToolUses20250919: JsonField<CapabilitySupport> = JsonMissing.of(),
        @JsonProperty("compact_20260112")
        @ExcludeMissing
        compact20260112: JsonField<CapabilitySupport> = JsonMissing.of(),
        @JsonProperty("supported") @ExcludeMissing supported: JsonField<Boolean> = JsonMissing.of(),
    ) : this(
        clearThinking20251015,
        clearToolUses20250919,
        compact20260112,
        supported,
        mutableMapOf(),
    )

    /**
     * Indicates whether a capability is supported.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun clearThinking20251015(): Optional<CapabilitySupport> =
        clearThinking20251015.getOptional("clear_thinking_20251015")

    /**
     * Indicates whether a capability is supported.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun clearToolUses20250919(): Optional<CapabilitySupport> =
        clearToolUses20250919.getOptional("clear_tool_uses_20250919")

    /**
     * Indicates whether a capability is supported.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun compact20260112(): Optional<CapabilitySupport> =
        compact20260112.getOptional("compact_20260112")

    /**
     * Whether this capability is supported by the model.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun supported(): Boolean = supported.getRequired("supported")

    /**
     * Returns the raw JSON value of [clearThinking20251015].
     *
     * Unlike [clearThinking20251015], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("clear_thinking_20251015")
    @ExcludeMissing
    fun _clearThinking20251015(): JsonField<CapabilitySupport> = clearThinking20251015

    /**
     * Returns the raw JSON value of [clearToolUses20250919].
     *
     * Unlike [clearToolUses20250919], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("clear_tool_uses_20250919")
    @ExcludeMissing
    fun _clearToolUses20250919(): JsonField<CapabilitySupport> = clearToolUses20250919

    /**
     * Returns the raw JSON value of [compact20260112].
     *
     * Unlike [compact20260112], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("compact_20260112")
    @ExcludeMissing
    fun _compact20260112(): JsonField<CapabilitySupport> = compact20260112

    /**
     * Returns the raw JSON value of [supported].
     *
     * Unlike [supported], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("supported") @ExcludeMissing fun _supported(): JsonField<Boolean> = supported

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
         * Returns a mutable builder for constructing an instance of [ContextManagementCapability].
         *
         * The following fields are required:
         * ```java
         * .clearThinking20251015()
         * .clearToolUses20250919()
         * .compact20260112()
         * .supported()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [ContextManagementCapability]. */
    class Builder internal constructor() {

        private var clearThinking20251015: JsonField<CapabilitySupport>? = null
        private var clearToolUses20250919: JsonField<CapabilitySupport>? = null
        private var compact20260112: JsonField<CapabilitySupport>? = null
        private var supported: JsonField<Boolean>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic internal fun from(contextManagementCapability: ContextManagementCapability) = apply {
            clearThinking20251015 = contextManagementCapability.clearThinking20251015
            clearToolUses20250919 = contextManagementCapability.clearToolUses20250919
            compact20260112 = contextManagementCapability.compact20260112
            supported = contextManagementCapability.supported
            additionalProperties = contextManagementCapability.additionalProperties.toMutableMap()
        }

        /** Indicates whether a capability is supported. */
        fun clearThinking20251015(clearThinking20251015: CapabilitySupport?) =
            clearThinking20251015(JsonField.ofNullable(clearThinking20251015))

        /**
         * Alias for calling [Builder.clearThinking20251015] with
         * `clearThinking20251015.orElse(null)`.
         */
        fun clearThinking20251015(clearThinking20251015: Optional<CapabilitySupport>) =
            clearThinking20251015(clearThinking20251015.getOrNull())

        /**
         * Sets [Builder.clearThinking20251015] to an arbitrary JSON value.
         *
         * You should usually call [Builder.clearThinking20251015] with a well-typed
         * [CapabilitySupport] value instead. This method is primarily for setting the field to an
         * undocumented or not yet supported value.
         */
        fun clearThinking20251015(clearThinking20251015: JsonField<CapabilitySupport>) = apply {
            this.clearThinking20251015 = clearThinking20251015
        }

        /** Indicates whether a capability is supported. */
        fun clearToolUses20250919(clearToolUses20250919: CapabilitySupport?) =
            clearToolUses20250919(JsonField.ofNullable(clearToolUses20250919))

        /**
         * Alias for calling [Builder.clearToolUses20250919] with
         * `clearToolUses20250919.orElse(null)`.
         */
        fun clearToolUses20250919(clearToolUses20250919: Optional<CapabilitySupport>) =
            clearToolUses20250919(clearToolUses20250919.getOrNull())

        /**
         * Sets [Builder.clearToolUses20250919] to an arbitrary JSON value.
         *
         * You should usually call [Builder.clearToolUses20250919] with a well-typed
         * [CapabilitySupport] value instead. This method is primarily for setting the field to an
         * undocumented or not yet supported value.
         */
        fun clearToolUses20250919(clearToolUses20250919: JsonField<CapabilitySupport>) = apply {
            this.clearToolUses20250919 = clearToolUses20250919
        }

        /** Indicates whether a capability is supported. */
        fun compact20260112(compact20260112: CapabilitySupport?) =
            compact20260112(JsonField.ofNullable(compact20260112))

        /** Alias for calling [Builder.compact20260112] with `compact20260112.orElse(null)`. */
        fun compact20260112(compact20260112: Optional<CapabilitySupport>) =
            compact20260112(compact20260112.getOrNull())

        /**
         * Sets [Builder.compact20260112] to an arbitrary JSON value.
         *
         * You should usually call [Builder.compact20260112] with a well-typed [CapabilitySupport]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun compact20260112(compact20260112: JsonField<CapabilitySupport>) = apply {
            this.compact20260112 = compact20260112
        }

        /** Whether this capability is supported by the model. */
        fun supported(supported: Boolean) = supported(JsonField.of(supported))

        /**
         * Sets [Builder.supported] to an arbitrary JSON value.
         *
         * You should usually call [Builder.supported] with a well-typed [Boolean] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun supported(supported: JsonField<Boolean>) = apply { this.supported = supported }

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
         * Returns an immutable instance of [ContextManagementCapability].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .clearThinking20251015()
         * .clearToolUses20250919()
         * .compact20260112()
         * .supported()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): ContextManagementCapability =
            ContextManagementCapability(
                checkRequired("clearThinking20251015", clearThinking20251015),
                checkRequired("clearToolUses20250919", clearToolUses20250919),
                checkRequired("compact20260112", compact20260112),
                checkRequired("supported", supported),
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): ContextManagementCapability = apply {
        if (validated) {
            return@apply
        }

        clearThinking20251015().ifPresent { it.validate() }
        clearToolUses20250919().ifPresent { it.validate() }
        compact20260112().ifPresent { it.validate() }
        supported()
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
    internal fun validity(): Int =
        (clearThinking20251015.asKnown()?.validity() ?: 0) +
            (clearToolUses20250919.asKnown()?.validity() ?: 0) +
            (compact20260112.asKnown()?.validity() ?: 0) +
            (if (supported.asKnown() != null) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is ContextManagementCapability &&
            clearThinking20251015 == other.clearThinking20251015 &&
            clearToolUses20250919 == other.clearToolUses20250919 &&
            compact20260112 == other.compact20260112 &&
            supported == other.supported &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        contentHash(
            clearThinking20251015,
            clearToolUses20250919,
            compact20260112,
            supported,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "ContextManagementCapability{clearThinking20251015=$clearThinking20251015, clearToolUses20250919=$clearToolUses20250919, compact20260112=$compact20260112, supported=$supported, additionalProperties=$additionalProperties}"
}
