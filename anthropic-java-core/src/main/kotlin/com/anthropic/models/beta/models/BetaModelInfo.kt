// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.models
import com.anthropic.core.getOptional

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
import java.time.OffsetDateTime
import com.anthropic.core.contentHash
import com.anthropic.core.Optional
import com.anthropic.core.getOrNull

class BetaModelInfo
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val id: JsonField<String>,
    private val capabilities: JsonField<BetaModelCapabilities>,
    private val createdAt: JsonField<OffsetDateTime>,
    private val displayName: JsonField<String>,
    private val maxInputTokens: JsonField<Long>,
    private val maxTokens: JsonField<Long>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("capabilities")
        @ExcludeMissing
        capabilities: JsonField<BetaModelCapabilities> = JsonMissing.of(),
        @JsonProperty("created_at")
        @ExcludeMissing
        createdAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("display_name")
        @ExcludeMissing
        displayName: JsonField<String> = JsonMissing.of(),
        @JsonProperty("max_input_tokens")
        @ExcludeMissing
        maxInputTokens: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("max_tokens") @ExcludeMissing maxTokens: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(
        id,
        capabilities,
        createdAt,
        displayName,
        maxInputTokens,
        maxTokens,
        type,
        mutableMapOf(),
    )

    /**
     * Unique model identifier.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun id(): String = id.getRequired("id")

    /**
     * Model capability information.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun capabilities(): Optional<BetaModelCapabilities> = capabilities.getOptional("capabilities")

    /**
     * RFC 3339 datetime string representing the time at which the model was released. May be set to
     * an epoch value if the release date is unknown.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun createdAt(): OffsetDateTime = createdAt.getRequired("created_at")

    /**
     * A human-readable name for the model.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun displayName(): String = displayName.getRequired("display_name")

    /**
     * Maximum input context window size in tokens for this model.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun maxInputTokens(): Optional<Long> = maxInputTokens.getOptional("max_input_tokens")

    /**
     * Maximum value for the `max_tokens` parameter when using this model.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun maxTokens(): Optional<Long> = maxTokens.getOptional("max_tokens")

    /**
     * Object type.
     *
     * For Models, this is always `"model"`.
     *
     * Expected to always return the following:
     * ```java
     * JsonValue.from("model")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Returns the raw JSON value of [id].
     *
     * Unlike [id], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

    /**
     * Returns the raw JSON value of [capabilities].
     *
     * Unlike [capabilities], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("capabilities")
    @ExcludeMissing
    fun _capabilities(): JsonField<BetaModelCapabilities> = capabilities

    /**
     * Returns the raw JSON value of [createdAt].
     *
     * Unlike [createdAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("created_at")
    @ExcludeMissing
    fun _createdAt(): JsonField<OffsetDateTime> = createdAt

    /**
     * Returns the raw JSON value of [displayName].
     *
     * Unlike [displayName], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("display_name")
    @ExcludeMissing
    fun _displayName(): JsonField<String> = displayName

    /**
     * Returns the raw JSON value of [maxInputTokens].
     *
     * Unlike [maxInputTokens], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("max_input_tokens")
    @ExcludeMissing
    fun _maxInputTokens(): JsonField<Long> = maxInputTokens

    /**
     * Returns the raw JSON value of [maxTokens].
     *
     * Unlike [maxTokens], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("max_tokens") @ExcludeMissing fun _maxTokens(): JsonField<Long> = maxTokens

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
         * Returns a mutable builder for constructing an instance of [BetaModelInfo].
         *
         * The following fields are required:
         * ```java
         * .id()
         * .capabilities()
         * .createdAt()
         * .displayName()
         * .maxInputTokens()
         * .maxTokens()
         * ```
         */
        fun builder() = Builder()
    }

    /** A builder for [BetaModelInfo]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var capabilities: JsonField<BetaModelCapabilities>? = null
        private var createdAt: JsonField<OffsetDateTime>? = null
        private var displayName: JsonField<String>? = null
        private var maxInputTokens: JsonField<Long>? = null
        private var maxTokens: JsonField<Long>? = null
        private var type: JsonValue = JsonValue.from("model")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        internal fun from(betaModelInfo: BetaModelInfo) = apply {
            id = betaModelInfo.id
            capabilities = betaModelInfo.capabilities
            createdAt = betaModelInfo.createdAt
            displayName = betaModelInfo.displayName
            maxInputTokens = betaModelInfo.maxInputTokens
            maxTokens = betaModelInfo.maxTokens
            type = betaModelInfo.type
            additionalProperties = betaModelInfo.additionalProperties.toMutableMap()
        }

        /** Unique model identifier. */
        fun id(id: String) = id(JsonField.of(id))

        /**
         * Sets [Builder.id] to an arbitrary JSON value.
         *
         * You should usually call [Builder.id] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun id(id: JsonField<String>) = apply { this.id = id }

        /** Model capability information. */
        fun capabilities(capabilities: BetaModelCapabilities?) =
            capabilities(JsonField.ofNullable(capabilities))

        /** Alias for calling [Builder.capabilities] with `capabilities.orElse(null)`. */
        fun capabilities(capabilities: Optional<BetaModelCapabilities>) =
            capabilities(capabilities.getOrNull())

        /**
         * Sets [Builder.capabilities] to an arbitrary JSON value.
         *
         * You should usually call [Builder.capabilities] with a well-typed [BetaModelCapabilities]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun capabilities(capabilities: JsonField<BetaModelCapabilities>) = apply {
            this.capabilities = capabilities
        }

        /**
         * RFC 3339 datetime string representing the time at which the model was released. May be
         * set to an epoch value if the release date is unknown.
         */
        fun createdAt(createdAt: OffsetDateTime) = createdAt(JsonField.of(createdAt))

        /**
         * Sets [Builder.createdAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.createdAt] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun createdAt(createdAt: JsonField<OffsetDateTime>) = apply { this.createdAt = createdAt }

        /** A human-readable name for the model. */
        fun displayName(displayName: String) = displayName(JsonField.of(displayName))

        /**
         * Sets [Builder.displayName] to an arbitrary JSON value.
         *
         * You should usually call [Builder.displayName] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun displayName(displayName: JsonField<String>) = apply { this.displayName = displayName }

        /** Maximum input context window size in tokens for this model. */
        fun maxInputTokens(maxInputTokens: Long?) =
            maxInputTokens(JsonField.ofNullable(maxInputTokens))

        /**
         * Alias for [Builder.maxInputTokens].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun maxInputTokens(maxInputTokens: Long) = maxInputTokens(maxInputTokens as Long?)

        /** Alias for calling [Builder.maxInputTokens] with `maxInputTokens.orElse(null)`. */
        fun maxInputTokens(maxInputTokens: Optional<Long>) =
            maxInputTokens(maxInputTokens.getOrNull())

        /**
         * Sets [Builder.maxInputTokens] to an arbitrary JSON value.
         *
         * You should usually call [Builder.maxInputTokens] with a well-typed [Long] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun maxInputTokens(maxInputTokens: JsonField<Long>) = apply {
            this.maxInputTokens = maxInputTokens
        }

        /** Maximum value for the `max_tokens` parameter when using this model. */
        fun maxTokens(maxTokens: Long?) = maxTokens(JsonField.ofNullable(maxTokens))

        /**
         * Alias for [Builder.maxTokens].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun maxTokens(maxTokens: Long) = maxTokens(maxTokens as Long?)

        /** Alias for calling [Builder.maxTokens] with `maxTokens.orElse(null)`. */
        fun maxTokens(maxTokens: Optional<Long>) = maxTokens(maxTokens.getOrNull())

        /**
         * Sets [Builder.maxTokens] to an arbitrary JSON value.
         *
         * You should usually call [Builder.maxTokens] with a well-typed [Long] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun maxTokens(maxTokens: JsonField<Long>) = apply { this.maxTokens = maxTokens }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("model")
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
         * Returns an immutable instance of [BetaModelInfo].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .id()
         * .capabilities()
         * .createdAt()
         * .displayName()
         * .maxInputTokens()
         * .maxTokens()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaModelInfo =
            BetaModelInfo(
                checkRequired("id", id),
                checkRequired("capabilities", capabilities),
                checkRequired("createdAt", createdAt),
                checkRequired("displayName", displayName),
                checkRequired("maxInputTokens", maxInputTokens),
                checkRequired("maxTokens", maxTokens),
                type,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): BetaModelInfo = apply {
        if (validated) {
            return@apply
        }

        id()
        capabilities().ifPresent { it.validate() }
        createdAt()
        displayName()
        maxInputTokens()
        maxTokens()
        _type().let {
            if (it != JsonValue.from("model")) {
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
    internal fun validity(): Int =
        (if (id.asKnown() != null) 1 else 0) +
            (capabilities.asKnown()?.validity() ?: 0) +
            (if (createdAt.asKnown() != null) 1 else 0) +
            (if (displayName.asKnown() != null) 1 else 0) +
            (if (maxInputTokens.asKnown() != null) 1 else 0) +
            (if (maxTokens.asKnown() != null) 1 else 0) +
            type.let { if (it == JsonValue.from("model")) 1 else 0 }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaModelInfo &&
            id == other.id &&
            capabilities == other.capabilities &&
            createdAt == other.createdAt &&
            displayName == other.displayName &&
            maxInputTokens == other.maxInputTokens &&
            maxTokens == other.maxTokens &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        contentHash(
            id,
            capabilities,
            createdAt,
            displayName,
            maxInputTokens,
            maxTokens,
            type,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaModelInfo{id=$id, capabilities=$capabilities, createdAt=$createdAt, displayName=$displayName, maxInputTokens=$maxInputTokens, maxTokens=$maxTokens, type=$type, additionalProperties=$additionalProperties}"
}
