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
import java.time.OffsetDateTime
import java.util.Collections
import java.util.Objects
import kotlin.jvm.optionals.getOrNull

class BetaSkill
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val id: JsonField<String>,
    private val createdAt: JsonField<OffsetDateTime>,
    private val displayName: JsonField<String>,
    private val latestVersionId: JsonField<String>,
    private val source: JsonField<BetaSkillSource>,
    private val type: JsonValue,
    private val updatedAt: JsonField<OffsetDateTime>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("created_at")
        @ExcludeMissing
        createdAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("display_name")
        @ExcludeMissing
        displayName: JsonField<String> = JsonMissing.of(),
        @JsonProperty("latest_version_id")
        @ExcludeMissing
        latestVersionId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("source")
        @ExcludeMissing
        source: JsonField<BetaSkillSource> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("updated_at")
        @ExcludeMissing
        updatedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
    ) : this(id, createdAt, displayName, latestVersionId, source, type, updatedAt, mutableMapOf())

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
    fun createdAt(): OffsetDateTime = createdAt.getRequired("created_at")

    /**
     * Human-readable, single-line label for the Skill. Maximum 255 characters. Always set: derived
     * from the SKILL.md frontmatter `name` when omitted at creation. Not unique.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun displayName(): String = displayName.getRequired("display_name")

    /**
     * ID of the newest Skill Version — what `latest` references resolve to. Always set: a Skill
     * holds at least one version.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun latestVersionId(): String = latestVersionId.getRequired("latest_version_id")

    /**
     * Where the Skill comes from.
     *
     * Possible values:
     * * `"custom"`: authored by the platform user; private to their workspace
     * * `"anthropic"`: published by Anthropic; shared and read-only
     * * `"anthropic_example"`: Anthropic-published sample Skill
     * * `"plugin"`: resolved from an installed plugin
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun source(): BetaSkillSource = source.getRequired("source")

    /**
     * Object type.
     *
     * For Skills, this is always `"skill"`.
     *
     * Expected to always return the following:
     * ```java
     * JsonValue.from("skill")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * ISO 8601 timestamp of when the skill was last updated.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun updatedAt(): OffsetDateTime = updatedAt.getRequired("updated_at")

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
     * Returns the raw JSON value of [latestVersionId].
     *
     * Unlike [latestVersionId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("latest_version_id")
    @ExcludeMissing
    fun _latestVersionId(): JsonField<String> = latestVersionId

    /**
     * Returns the raw JSON value of [source].
     *
     * Unlike [source], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("source") @ExcludeMissing fun _source(): JsonField<BetaSkillSource> = source

    /**
     * Returns the raw JSON value of [updatedAt].
     *
     * Unlike [updatedAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("updated_at")
    @ExcludeMissing
    fun _updatedAt(): JsonField<OffsetDateTime> = updatedAt

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
         * Returns a mutable builder for constructing an instance of [BetaSkill].
         *
         * The following fields are required:
         * ```java
         * .id()
         * .createdAt()
         * .displayName()
         * .latestVersionId()
         * .source()
         * .updatedAt()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaSkill]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var createdAt: JsonField<OffsetDateTime>? = null
        private var displayName: JsonField<String>? = null
        private var latestVersionId: JsonField<String>? = null
        private var source: JsonField<BetaSkillSource>? = null
        private var type: JsonValue = JsonValue.from("skill")
        private var updatedAt: JsonField<OffsetDateTime>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaSkill: BetaSkill) = apply {
            id = betaSkill.id
            createdAt = betaSkill.createdAt
            displayName = betaSkill.displayName
            latestVersionId = betaSkill.latestVersionId
            source = betaSkill.source
            type = betaSkill.type
            updatedAt = betaSkill.updatedAt
            additionalProperties = betaSkill.additionalProperties.toMutableMap()
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
         * Human-readable, single-line label for the Skill. Maximum 255 characters. Always set:
         * derived from the SKILL.md frontmatter `name` when omitted at creation. Not unique.
         */
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
         * ID of the newest Skill Version — what `latest` references resolve to. Always set: a Skill
         * holds at least one version.
         */
        fun latestVersionId(latestVersionId: String) =
            latestVersionId(JsonField.of(latestVersionId))

        /**
         * Sets [Builder.latestVersionId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.latestVersionId] with a well-typed [String] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun latestVersionId(latestVersionId: JsonField<String>) = apply {
            this.latestVersionId = latestVersionId
        }

        /**
         * Where the Skill comes from.
         *
         * Possible values:
         * * `"custom"`: authored by the platform user; private to their workspace
         * * `"anthropic"`: published by Anthropic; shared and read-only
         * * `"anthropic_example"`: Anthropic-published sample Skill
         * * `"plugin"`: resolved from an installed plugin
         */
        fun source(source: BetaSkillSource) = source(JsonField.of(source))

        /**
         * Sets [Builder.source] to an arbitrary JSON value.
         *
         * You should usually call [Builder.source] with a well-typed [BetaSkillSource] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun source(source: JsonField<BetaSkillSource>) = apply { this.source = source }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("skill")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        /** ISO 8601 timestamp of when the skill was last updated. */
        fun updatedAt(updatedAt: OffsetDateTime) = updatedAt(JsonField.of(updatedAt))

        /**
         * Sets [Builder.updatedAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.updatedAt] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun updatedAt(updatedAt: JsonField<OffsetDateTime>) = apply { this.updatedAt = updatedAt }

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
         * Returns an immutable instance of [BetaSkill].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .id()
         * .createdAt()
         * .displayName()
         * .latestVersionId()
         * .source()
         * .updatedAt()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaSkill =
            BetaSkill(
                checkRequired("id", id),
                checkRequired("createdAt", createdAt),
                checkRequired("displayName", displayName),
                checkRequired("latestVersionId", latestVersionId),
                checkRequired("source", source),
                type,
                checkRequired("updatedAt", updatedAt),
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
    fun validate(): BetaSkill = apply {
        if (validated) {
            return@apply
        }

        id()
        createdAt()
        displayName()
        latestVersionId()
        source().validate()
        _type().let {
            if (it != JsonValue.from("skill")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
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
            (if (displayName.asKnown().isPresent) 1 else 0) +
            (if (latestVersionId.asKnown().isPresent) 1 else 0) +
            (source.asKnown().getOrNull()?.validity() ?: 0) +
            type.let { if (it == JsonValue.from("skill")) 1 else 0 } +
            (if (updatedAt.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaSkill &&
            id == other.id &&
            createdAt == other.createdAt &&
            displayName == other.displayName &&
            latestVersionId == other.latestVersionId &&
            source == other.source &&
            type == other.type &&
            updatedAt == other.updatedAt &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            id,
            createdAt,
            displayName,
            latestVersionId,
            source,
            type,
            updatedAt,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaSkill{id=$id, createdAt=$createdAt, displayName=$displayName, latestVersionId=$latestVersionId, source=$source, type=$type, updatedAt=$updatedAt, additionalProperties=$additionalProperties}"
}
