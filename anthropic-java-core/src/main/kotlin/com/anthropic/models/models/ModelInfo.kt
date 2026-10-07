package com.anthropic.models.models

import com.anthropic.core.Enum
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
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

class ModelInfo
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val id: JsonField<String>,
    private val capabilities: JsonField<ModelCapabilities>,
    private val createdAt: JsonField<OffsetDateTime>,
    private val deprecatedAt: JsonField<OffsetDateTime>,
    private val displayName: JsonField<String>,
    private val lifecycle: JsonField<Lifecycle>,
    private val line: JsonField<ModelLine>,
    private val maxInputTokens: JsonField<Long>,
    private val maxTokens: JsonField<Long>,
    private val retiresAt: JsonField<OffsetDateTime>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("capabilities")
        @ExcludeMissing
        capabilities: JsonField<ModelCapabilities> = JsonMissing.of(),
        @JsonProperty("created_at")
        @ExcludeMissing
        createdAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("deprecated_at")
        @ExcludeMissing
        deprecatedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("display_name")
        @ExcludeMissing
        displayName: JsonField<String> = JsonMissing.of(),
        @JsonProperty("lifecycle")
        @ExcludeMissing
        lifecycle: JsonField<Lifecycle> = JsonMissing.of(),
        @JsonProperty("line") @ExcludeMissing line: JsonField<ModelLine> = JsonMissing.of(),
        @JsonProperty("max_input_tokens")
        @ExcludeMissing
        maxInputTokens: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("max_tokens") @ExcludeMissing maxTokens: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("retires_at")
        @ExcludeMissing
        retiresAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(
        id,
        capabilities,
        createdAt,
        deprecatedAt,
        displayName,
        lifecycle,
        line,
        maxInputTokens,
        maxTokens,
        retiresAt,
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
     * Object mapping capability names to their support details. Keys are always present for all
     * known capabilities.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun capabilities(): Optional<ModelCapabilities> = capabilities.getOptional("capabilities")

    /**
     * RFC 3339 datetime string representing the time at which the model was released. May be set to
     * an epoch value if the release date is unknown.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun createdAt(): OffsetDateTime = createdAt.getRequired("created_at")

    /**
     * RFC 3339 datetime string representing the time of the model's most recent deprecation.
     * Populated for `deprecated` and `retired` models; `null` while the model is `active`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun deprecatedAt(): Optional<OffsetDateTime> = deprecatedAt.getOptional("deprecated_at")

    /**
     * A human-readable name for the model.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun displayName(): String = displayName.getRequired("display_name")

    /**
     * The model's current lifecycle stage.
     * - `active`: The model is available for use, open to new adopters, and not scheduled for
     *   retirement.
     * - `deprecated`: The model remains callable for organizations with existing access, but is
     *   headed for retirement and closed to new adopters.
     * - `retired`: The model is no longer available for use; inference requests naming it fail. It
     *   remains in the catalogue as the historical record of its retirement.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun lifecycle(): Lifecycle = lifecycle.getRequired("lifecycle")

    /**
     * The model line this model belongs to, such as `opus` for both Claude Opus 4.5 and Claude Opus
     * 4.6. More lines may be added. `null` when the model belongs to no line; do not infer a line
     * from the `id`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun line(): Optional<ModelLine> = line.getOptional("line")

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
     * RFC 3339 datetime string representing the model's currently scheduled retirement date. The
     * schedule can be revised until retirement occurs; `null` while the model is `active` or while
     * no retirement is scheduled. A past date on a `deprecated` model means retirement is overdue,
     * not that it has occurred: `lifecycle` is the retirement signal.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun retiresAt(): Optional<OffsetDateTime> = retiresAt.getOptional("retires_at")

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
    fun _capabilities(): JsonField<ModelCapabilities> = capabilities

    /**
     * Returns the raw JSON value of [createdAt].
     *
     * Unlike [createdAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("created_at")
    @ExcludeMissing
    fun _createdAt(): JsonField<OffsetDateTime> = createdAt

    /**
     * Returns the raw JSON value of [deprecatedAt].
     *
     * Unlike [deprecatedAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("deprecated_at")
    @ExcludeMissing
    fun _deprecatedAt(): JsonField<OffsetDateTime> = deprecatedAt

    /**
     * Returns the raw JSON value of [displayName].
     *
     * Unlike [displayName], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("display_name")
    @ExcludeMissing
    fun _displayName(): JsonField<String> = displayName

    /**
     * Returns the raw JSON value of [lifecycle].
     *
     * Unlike [lifecycle], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("lifecycle") @ExcludeMissing fun _lifecycle(): JsonField<Lifecycle> = lifecycle

    /**
     * Returns the raw JSON value of [line].
     *
     * Unlike [line], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("line") @ExcludeMissing fun _line(): JsonField<ModelLine> = line

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

    /**
     * Returns the raw JSON value of [retiresAt].
     *
     * Unlike [retiresAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("retires_at")
    @ExcludeMissing
    fun _retiresAt(): JsonField<OffsetDateTime> = retiresAt

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
         * Returns a mutable builder for constructing an instance of [ModelInfo].
         *
         * The following fields are required:
         * ```java
         * .id()
         * .capabilities()
         * .createdAt()
         * .deprecatedAt()
         * .displayName()
         * .lifecycle()
         * .line()
         * .maxInputTokens()
         * .maxTokens()
         * .retiresAt()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [ModelInfo]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var capabilities: JsonField<ModelCapabilities>? = null
        private var createdAt: JsonField<OffsetDateTime>? = null
        private var deprecatedAt: JsonField<OffsetDateTime>? = null
        private var displayName: JsonField<String>? = null
        private var lifecycle: JsonField<Lifecycle>? = null
        private var line: JsonField<ModelLine>? = null
        private var maxInputTokens: JsonField<Long>? = null
        private var maxTokens: JsonField<Long>? = null
        private var retiresAt: JsonField<OffsetDateTime>? = null
        private var type: JsonValue = JsonValue.from("model")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(modelInfo: ModelInfo) = apply {
            id = modelInfo.id
            capabilities = modelInfo.capabilities
            createdAt = modelInfo.createdAt
            deprecatedAt = modelInfo.deprecatedAt
            displayName = modelInfo.displayName
            lifecycle = modelInfo.lifecycle
            line = modelInfo.line
            maxInputTokens = modelInfo.maxInputTokens
            maxTokens = modelInfo.maxTokens
            retiresAt = modelInfo.retiresAt
            type = modelInfo.type
            additionalProperties = modelInfo.additionalProperties.toMutableMap()
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

        /**
         * Object mapping capability names to their support details. Keys are always present for all
         * known capabilities.
         */
        fun capabilities(capabilities: ModelCapabilities?) =
            capabilities(JsonField.ofNullable(capabilities))

        /** Alias for calling [Builder.capabilities] with `capabilities.orElse(null)`. */
        fun capabilities(capabilities: Optional<ModelCapabilities>) =
            capabilities(capabilities.getOrNull())

        /**
         * Sets [Builder.capabilities] to an arbitrary JSON value.
         *
         * You should usually call [Builder.capabilities] with a well-typed [ModelCapabilities]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun capabilities(capabilities: JsonField<ModelCapabilities>) = apply {
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

        /**
         * RFC 3339 datetime string representing the time of the model's most recent deprecation.
         * Populated for `deprecated` and `retired` models; `null` while the model is `active`.
         */
        fun deprecatedAt(deprecatedAt: OffsetDateTime?) =
            deprecatedAt(JsonField.ofNullable(deprecatedAt))

        /** Alias for calling [Builder.deprecatedAt] with `deprecatedAt.orElse(null)`. */
        fun deprecatedAt(deprecatedAt: Optional<OffsetDateTime>) =
            deprecatedAt(deprecatedAt.getOrNull())

        /**
         * Sets [Builder.deprecatedAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.deprecatedAt] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun deprecatedAt(deprecatedAt: JsonField<OffsetDateTime>) = apply {
            this.deprecatedAt = deprecatedAt
        }

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

        /**
         * The model's current lifecycle stage.
         * - `active`: The model is available for use, open to new adopters, and not scheduled for
         *   retirement.
         * - `deprecated`: The model remains callable for organizations with existing access, but is
         *   headed for retirement and closed to new adopters.
         * - `retired`: The model is no longer available for use; inference requests naming it fail.
         *   It remains in the catalogue as the historical record of its retirement.
         */
        fun lifecycle(lifecycle: Lifecycle) = lifecycle(JsonField.of(lifecycle))

        /**
         * Sets [Builder.lifecycle] to an arbitrary JSON value.
         *
         * You should usually call [Builder.lifecycle] with a well-typed [Lifecycle] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun lifecycle(lifecycle: JsonField<Lifecycle>) = apply { this.lifecycle = lifecycle }

        /**
         * The model line this model belongs to, such as `opus` for both Claude Opus 4.5 and Claude
         * Opus 4.6. More lines may be added. `null` when the model belongs to no line; do not infer
         * a line from the `id`.
         */
        fun line(line: ModelLine?) = line(JsonField.ofNullable(line))

        /** Alias for calling [Builder.line] with `line.orElse(null)`. */
        fun line(line: Optional<ModelLine>) = line(line.getOrNull())

        /**
         * Sets [Builder.line] to an arbitrary JSON value.
         *
         * You should usually call [Builder.line] with a well-typed [ModelLine] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun line(line: JsonField<ModelLine>) = apply { this.line = line }

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
         * RFC 3339 datetime string representing the model's currently scheduled retirement date.
         * The schedule can be revised until retirement occurs; `null` while the model is `active`
         * or while no retirement is scheduled. A past date on a `deprecated` model means retirement
         * is overdue, not that it has occurred: `lifecycle` is the retirement signal.
         */
        fun retiresAt(retiresAt: OffsetDateTime?) = retiresAt(JsonField.ofNullable(retiresAt))

        /** Alias for calling [Builder.retiresAt] with `retiresAt.orElse(null)`. */
        fun retiresAt(retiresAt: Optional<OffsetDateTime>) = retiresAt(retiresAt.getOrNull())

        /**
         * Sets [Builder.retiresAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.retiresAt] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun retiresAt(retiresAt: JsonField<OffsetDateTime>) = apply { this.retiresAt = retiresAt }

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
         * Returns an immutable instance of [ModelInfo].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .id()
         * .capabilities()
         * .createdAt()
         * .deprecatedAt()
         * .displayName()
         * .lifecycle()
         * .line()
         * .maxInputTokens()
         * .maxTokens()
         * .retiresAt()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): ModelInfo =
            ModelInfo(
                checkRequired("id", id),
                checkRequired("capabilities", capabilities),
                checkRequired("createdAt", createdAt),
                checkRequired("deprecatedAt", deprecatedAt),
                checkRequired("displayName", displayName),
                checkRequired("lifecycle", lifecycle),
                checkRequired("line", line),
                checkRequired("maxInputTokens", maxInputTokens),
                checkRequired("maxTokens", maxTokens),
                checkRequired("retiresAt", retiresAt),
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
    fun validate(): ModelInfo = apply {
        if (validated) {
            return@apply
        }

        id()
        capabilities().ifPresent { it.validate() }
        createdAt()
        deprecatedAt()
        displayName()
        lifecycle().validate()
        line().ifPresent { it.validate() }
        maxInputTokens()
        maxTokens()
        retiresAt()
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
    @JvmSynthetic
    internal fun validity(): Int =
        (if (id.asKnown().isPresent) 1 else 0) +
            (capabilities.asKnown().getOrNull()?.validity() ?: 0) +
            (if (createdAt.asKnown().isPresent) 1 else 0) +
            (if (deprecatedAt.asKnown().isPresent) 1 else 0) +
            (if (displayName.asKnown().isPresent) 1 else 0) +
            (lifecycle.asKnown().getOrNull()?.validity() ?: 0) +
            (line.asKnown().getOrNull()?.validity() ?: 0) +
            (if (maxInputTokens.asKnown().isPresent) 1 else 0) +
            (if (maxTokens.asKnown().isPresent) 1 else 0) +
            (if (retiresAt.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("model")) 1 else 0 }

    /**
     * The model's current lifecycle stage.
     * - `active`: The model is available for use, open to new adopters, and not scheduled for
     *   retirement.
     * - `deprecated`: The model remains callable for organizations with existing access, but is
     *   headed for retirement and closed to new adopters.
     * - `retired`: The model is no longer available for use; inference requests naming it fail. It
     *   remains in the catalogue as the historical record of its retirement.
     */
    class Lifecycle private constructor(private val value: JsonField<String>) : Enum {

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

            @JvmField val ACTIVE = Lifecycle(JsonField.of("active"))

            @JvmField val DEPRECATED = Lifecycle(JsonField.of("deprecated"))

            @JvmField val RETIRED = Lifecycle(JsonField.of("retired"))

            @JvmStatic
            fun of(value: String): Lifecycle =
                // Intern known values so `==` works
                when (value) {
                    "active" -> ACTIVE
                    "deprecated" -> DEPRECATED
                    "retired" -> RETIRED
                    else -> Lifecycle(JsonField.of(value))
                }

            @JsonCreator
            @JvmStatic
            fun of(value: JsonField<String>): Lifecycle =
                value.asString().getOrNull()?.let { of(it) } ?: Lifecycle(value)
        }

        /** An enum containing [Lifecycle]'s known values. */
        enum class Known {
            ACTIVE,
            DEPRECATED,
            RETIRED,
        }

        /**
         * An enum containing [Lifecycle]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [Lifecycle] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            ACTIVE,
            DEPRECATED,
            RETIRED,
            /**
             * An enum member indicating that [Lifecycle] was instantiated with an unknown value.
             */
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
                ACTIVE -> Value.ACTIVE
                DEPRECATED -> Value.DEPRECATED
                RETIRED -> Value.RETIRED
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
                ACTIVE -> Known.ACTIVE
                DEPRECATED -> Known.DEPRECATED
                RETIRED -> Known.RETIRED
                else -> throw AnthropicInvalidDataException("Unknown Lifecycle: $value")
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
        fun validate(): Lifecycle = apply {
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

            return other is Lifecycle && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is ModelInfo &&
            id == other.id &&
            capabilities == other.capabilities &&
            createdAt == other.createdAt &&
            deprecatedAt == other.deprecatedAt &&
            displayName == other.displayName &&
            lifecycle == other.lifecycle &&
            line == other.line &&
            maxInputTokens == other.maxInputTokens &&
            maxTokens == other.maxTokens &&
            retiresAt == other.retiresAt &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            id,
            capabilities,
            createdAt,
            deprecatedAt,
            displayName,
            lifecycle,
            line,
            maxInputTokens,
            maxTokens,
            retiresAt,
            type,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "ModelInfo{id=$id, capabilities=$capabilities, createdAt=$createdAt, deprecatedAt=$deprecatedAt, displayName=$displayName, lifecycle=$lifecycle, line=$line, maxInputTokens=$maxInputTokens, maxTokens=$maxTokens, retiresAt=$retiresAt, type=$type, additionalProperties=$additionalProperties}"
}
