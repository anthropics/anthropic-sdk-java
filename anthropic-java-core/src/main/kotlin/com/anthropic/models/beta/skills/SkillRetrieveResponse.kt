// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.skills

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
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

class SkillRetrieveResponse
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val id: JsonField<String>,
    private val createdAt: JsonField<String>,
    private val displayTitle: JsonField<String>,
    private val latestVersion: JsonField<String>,
    private val source: JsonField<String>,
    private val type: JsonField<String>,
    private val updatedAt: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("created_at") @ExcludeMissing createdAt: JsonField<String> = JsonMissing.of(),
        @JsonProperty("display_title")
        @ExcludeMissing
        displayTitle: JsonField<String> = JsonMissing.of(),
        @JsonProperty("latest_version")
        @ExcludeMissing
        latestVersion: JsonField<String> = JsonMissing.of(),
        @JsonProperty("source") @ExcludeMissing source: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonField<String> = JsonMissing.of(),
        @JsonProperty("updated_at") @ExcludeMissing updatedAt: JsonField<String> = JsonMissing.of(),
    ) : this(id, createdAt, displayTitle, latestVersion, source, type, updatedAt, mutableMapOf())

    /**
     * Unique identifier for the skill.
     *
     * The format and length of IDs may change over time.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun id(): String = id.getRequired("id")

    /**
     * ISO 8601 timestamp of when the skill was created.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun createdAt(): String = createdAt.getRequired("created_at")

    /**
     * Display title for the skill.
     *
     * This is a human-readable label that is not included in the prompt sent to the model.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun displayTitle(): Optional<String> = displayTitle.getOptional("display_title")

    /**
     * The latest version identifier for the skill.
     *
     * This represents the most recent version of the skill that has been created.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun latestVersion(): Optional<String> = latestVersion.getOptional("latest_version")

    /**
     * Source of the skill.
     *
     * This may be one of the following values:
     * * `"custom"`: the skill was created by a user
     * * `"anthropic"`: the skill was created by Anthropic
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun source(): String = source.getRequired("source")

    /**
     * Object type.
     *
     * For Skills, this is always `"skill"`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun type(): String = type.getRequired("type")

    /**
     * ISO 8601 timestamp of when the skill was last updated.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun updatedAt(): String = updatedAt.getRequired("updated_at")

    /**
     * Returns the raw JSON value of [id].
     *
     * Unlike [id], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

    /**
     * Returns the raw JSON value of [createdAt].
     *
     * Unlike [createdAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("created_at") @ExcludeMissing fun _createdAt(): JsonField<String> = createdAt

    /**
     * Returns the raw JSON value of [displayTitle].
     *
     * Unlike [displayTitle], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("display_title")
    @ExcludeMissing
    fun _displayTitle(): JsonField<String> = displayTitle

    /**
     * Returns the raw JSON value of [latestVersion].
     *
     * Unlike [latestVersion], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("latest_version")
    @ExcludeMissing
    fun _latestVersion(): JsonField<String> = latestVersion

    /**
     * Returns the raw JSON value of [source].
     *
     * Unlike [source], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("source") @ExcludeMissing fun _source(): JsonField<String> = source

    /**
     * Returns the raw JSON value of [type].
     *
     * Unlike [type], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonField<String> = type

    /**
     * Returns the raw JSON value of [updatedAt].
     *
     * Unlike [updatedAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("updated_at") @ExcludeMissing fun _updatedAt(): JsonField<String> = updatedAt

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
         * Returns a mutable builder for constructing an instance of [SkillRetrieveResponse].
         *
         * The following fields are required:
         * ```java
         * .id()
         * .createdAt()
         * .displayTitle()
         * .latestVersion()
         * .source()
         * .type()
         * .updatedAt()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [SkillRetrieveResponse]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var createdAt: JsonField<String>? = null
        private var displayTitle: JsonField<String>? = null
        private var latestVersion: JsonField<String>? = null
        private var source: JsonField<String>? = null
        private var type: JsonField<String>? = null
        private var updatedAt: JsonField<String>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(skillRetrieveResponse: SkillRetrieveResponse) = apply {
            id = skillRetrieveResponse.id
            createdAt = skillRetrieveResponse.createdAt
            displayTitle = skillRetrieveResponse.displayTitle
            latestVersion = skillRetrieveResponse.latestVersion
            source = skillRetrieveResponse.source
            type = skillRetrieveResponse.type
            updatedAt = skillRetrieveResponse.updatedAt
            additionalProperties = skillRetrieveResponse.additionalProperties.toMutableMap()
        }

        /**
         * Unique identifier for the skill.
         *
         * The format and length of IDs may change over time.
         */
        fun id(id: String) = id(JsonField.of(id))

        /**
         * Sets [Builder.id] to an arbitrary JSON value.
         *
         * You should usually call [Builder.id] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun id(id: JsonField<String>) = apply { this.id = id }

        /** ISO 8601 timestamp of when the skill was created. */
        fun createdAt(createdAt: String) = createdAt(JsonField.of(createdAt))

        /**
         * Sets [Builder.createdAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.createdAt] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun createdAt(createdAt: JsonField<String>) = apply { this.createdAt = createdAt }

        /**
         * Display title for the skill.
         *
         * This is a human-readable label that is not included in the prompt sent to the model.
         */
        fun displayTitle(displayTitle: String?) = displayTitle(JsonField.ofNullable(displayTitle))

        /** Alias for calling [Builder.displayTitle] with `displayTitle.orElse(null)`. */
        fun displayTitle(displayTitle: Optional<String>) = displayTitle(displayTitle.getOrNull())

        /**
         * Sets [Builder.displayTitle] to an arbitrary JSON value.
         *
         * You should usually call [Builder.displayTitle] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun displayTitle(displayTitle: JsonField<String>) = apply {
            this.displayTitle = displayTitle
        }

        /**
         * The latest version identifier for the skill.
         *
         * This represents the most recent version of the skill that has been created.
         */
        fun latestVersion(latestVersion: String?) =
            latestVersion(JsonField.ofNullable(latestVersion))

        /** Alias for calling [Builder.latestVersion] with `latestVersion.orElse(null)`. */
        fun latestVersion(latestVersion: Optional<String>) =
            latestVersion(latestVersion.getOrNull())

        /**
         * Sets [Builder.latestVersion] to an arbitrary JSON value.
         *
         * You should usually call [Builder.latestVersion] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun latestVersion(latestVersion: JsonField<String>) = apply {
            this.latestVersion = latestVersion
        }

        /**
         * Source of the skill.
         *
         * This may be one of the following values:
         * * `"custom"`: the skill was created by a user
         * * `"anthropic"`: the skill was created by Anthropic
         */
        fun source(source: String) = source(JsonField.of(source))

        /**
         * Sets [Builder.source] to an arbitrary JSON value.
         *
         * You should usually call [Builder.source] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun source(source: JsonField<String>) = apply { this.source = source }

        /**
         * Object type.
         *
         * For Skills, this is always `"skill"`.
         */
        fun type(type: String) = type(JsonField.of(type))

        /**
         * Sets [Builder.type] to an arbitrary JSON value.
         *
         * You should usually call [Builder.type] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun type(type: JsonField<String>) = apply { this.type = type }

        /** ISO 8601 timestamp of when the skill was last updated. */
        fun updatedAt(updatedAt: String) = updatedAt(JsonField.of(updatedAt))

        /**
         * Sets [Builder.updatedAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.updatedAt] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun updatedAt(updatedAt: JsonField<String>) = apply { this.updatedAt = updatedAt }

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
         * Returns an immutable instance of [SkillRetrieveResponse].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .id()
         * .createdAt()
         * .displayTitle()
         * .latestVersion()
         * .source()
         * .type()
         * .updatedAt()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): SkillRetrieveResponse =
            SkillRetrieveResponse(
                checkRequired("id", id),
                checkRequired("createdAt", createdAt),
                checkRequired("displayTitle", displayTitle),
                checkRequired("latestVersion", latestVersion),
                checkRequired("source", source),
                checkRequired("type", type),
                checkRequired("updatedAt", updatedAt),
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): SkillRetrieveResponse = apply {
        if (validated) {
            return@apply
        }

        id()
        createdAt()
        displayTitle()
        latestVersion()
        source()
        type()
        updatedAt()
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
            (if (createdAt.asKnown().isPresent) 1 else 0) +
            (if (displayTitle.asKnown().isPresent) 1 else 0) +
            (if (latestVersion.asKnown().isPresent) 1 else 0) +
            (if (source.asKnown().isPresent) 1 else 0) +
            (if (type.asKnown().isPresent) 1 else 0) +
            (if (updatedAt.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is SkillRetrieveResponse &&
            id == other.id &&
            createdAt == other.createdAt &&
            displayTitle == other.displayTitle &&
            latestVersion == other.latestVersion &&
            source == other.source &&
            type == other.type &&
            updatedAt == other.updatedAt &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            id,
            createdAt,
            displayTitle,
            latestVersion,
            source,
            type,
            updatedAt,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "SkillRetrieveResponse{id=$id, createdAt=$createdAt, displayTitle=$displayTitle, latestVersion=$latestVersion, source=$source, type=$type, updatedAt=$updatedAt, additionalProperties=$additionalProperties}"
}
