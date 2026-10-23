package com.anthropic.models.beta.organization.claudetag.slackchannels

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkRequired
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.beta.organization.claudetag.BetaClaudeTagSettingSources
import com.anthropic.models.beta.organization.claudetag.BetaClaudeTagSettings
import com.anthropic.models.beta.organization.claudetag.BetaClaudeTagSlackChannelIdentifiers
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.OffsetDateTime
import java.util.Collections
import java.util.Objects
import kotlin.jvm.optionals.getOrNull

/**
 * A Slack channel set up for Claude Tag, with the settings it stores or, with `include_inherited`,
 * the settings that apply in it.
 */
class BetaClaudeTagSlackChannel
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val id: JsonField<String>,
    private val createdAt: JsonField<OffsetDateTime>,
    private val settings: JsonField<BetaClaudeTagSettings>,
    private val slack: JsonField<BetaClaudeTagSlackChannelIdentifiers>,
    private val sources: JsonField<BetaClaudeTagSettingSources>,
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
        @JsonProperty("settings")
        @ExcludeMissing
        settings: JsonField<BetaClaudeTagSettings> = JsonMissing.of(),
        @JsonProperty("slack")
        @ExcludeMissing
        slack: JsonField<BetaClaudeTagSlackChannelIdentifiers> = JsonMissing.of(),
        @JsonProperty("sources")
        @ExcludeMissing
        sources: JsonField<BetaClaudeTagSettingSources> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("updated_at")
        @ExcludeMissing
        updatedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
    ) : this(id, createdAt, settings, slack, sources, type, updatedAt, mutableMapOf())

    /**
     * The channel's identifier. Starts with `slch_`. It does not change for as long as the channel
     * is set up, with one exception: a channel that is not configured gets a new one when claude.ai
     * replaces its record, which happens only while the organization's read-only mode is off. A
     * channel that is removed and set up again also has a new one. The old one keeps returning 404;
     * look the channel up by its Slack identifiers for the new one.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun id(): String = id.getRequired("id")

    /**
     * Time the channel was set up, by whichever route. It does not change for as long as `id` does
     * not.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun createdAt(): OffsetDateTime = createdAt.getRequired("created_at")

    /**
     * What the channel itself stores or, with `include_inherited`, what applies in it. `sources`
     * says which.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun settings(): BetaClaudeTagSettings = settings.getRequired("settings")

    /**
     * Slack's own identifiers for the channel.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun slack(): BetaClaudeTagSlackChannelIdentifiers = slack.getRequired("slack")

    /**
     * For each setting, the settings level its value in `settings` comes from.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun sources(): BetaClaudeTagSettingSources = sources.getRequired("sources")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("slack_channel")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Time Claude Tag's record of the channel last changed. It moves on every change to a setting,
     * and can also move when something no response holds changes, so a later value says to read the
     * channel again, not that a setting changed. Attaching or detaching a Bundle does not by itself
     * move it.
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
     * Returns the raw JSON value of [settings].
     *
     * Unlike [settings], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("settings")
    @ExcludeMissing
    fun _settings(): JsonField<BetaClaudeTagSettings> = settings

    /**
     * Returns the raw JSON value of [slack].
     *
     * Unlike [slack], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("slack")
    @ExcludeMissing
    fun _slack(): JsonField<BetaClaudeTagSlackChannelIdentifiers> = slack

    /**
     * Returns the raw JSON value of [sources].
     *
     * Unlike [sources], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("sources")
    @ExcludeMissing
    fun _sources(): JsonField<BetaClaudeTagSettingSources> = sources

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
         * Returns a mutable builder for constructing an instance of [BetaClaudeTagSlackChannel].
         *
         * The following fields are required:
         * ```java
         * .id()
         * .createdAt()
         * .settings()
         * .slack()
         * .sources()
         * .updatedAt()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaClaudeTagSlackChannel]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var createdAt: JsonField<OffsetDateTime>? = null
        private var settings: JsonField<BetaClaudeTagSettings>? = null
        private var slack: JsonField<BetaClaudeTagSlackChannelIdentifiers>? = null
        private var sources: JsonField<BetaClaudeTagSettingSources>? = null
        private var type: JsonValue = JsonValue.from("slack_channel")
        private var updatedAt: JsonField<OffsetDateTime>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaClaudeTagSlackChannel: BetaClaudeTagSlackChannel) = apply {
            id = betaClaudeTagSlackChannel.id
            createdAt = betaClaudeTagSlackChannel.createdAt
            settings = betaClaudeTagSlackChannel.settings
            slack = betaClaudeTagSlackChannel.slack
            sources = betaClaudeTagSlackChannel.sources
            type = betaClaudeTagSlackChannel.type
            updatedAt = betaClaudeTagSlackChannel.updatedAt
            additionalProperties = betaClaudeTagSlackChannel.additionalProperties.toMutableMap()
        }

        /**
         * The channel's identifier. Starts with `slch_`. It does not change for as long as the
         * channel is set up, with one exception: a channel that is not configured gets a new one
         * when claude.ai replaces its record, which happens only while the organization's read-only
         * mode is off. A channel that is removed and set up again also has a new one. The old one
         * keeps returning 404; look the channel up by its Slack identifiers for the new one.
         */
        fun id(id: String) = id(JsonField.of(id))

        /**
         * Sets [Builder.id] to an arbitrary JSON value.
         *
         * You should usually call [Builder.id] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun id(id: JsonField<String>) = apply { this.id = id }

        /**
         * Time the channel was set up, by whichever route. It does not change for as long as `id`
         * does not.
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
         * What the channel itself stores or, with `include_inherited`, what applies in it.
         * `sources` says which.
         */
        fun settings(settings: BetaClaudeTagSettings) = settings(JsonField.of(settings))

        /**
         * Sets [Builder.settings] to an arbitrary JSON value.
         *
         * You should usually call [Builder.settings] with a well-typed [BetaClaudeTagSettings]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun settings(settings: JsonField<BetaClaudeTagSettings>) = apply {
            this.settings = settings
        }

        /** Slack's own identifiers for the channel. */
        fun slack(slack: BetaClaudeTagSlackChannelIdentifiers) = slack(JsonField.of(slack))

        /**
         * Sets [Builder.slack] to an arbitrary JSON value.
         *
         * You should usually call [Builder.slack] with a well-typed
         * [BetaClaudeTagSlackChannelIdentifiers] value instead. This method is primarily for
         * setting the field to an undocumented or not yet supported value.
         */
        fun slack(slack: JsonField<BetaClaudeTagSlackChannelIdentifiers>) = apply {
            this.slack = slack
        }

        /** For each setting, the settings level its value in `settings` comes from. */
        fun sources(sources: BetaClaudeTagSettingSources) = sources(JsonField.of(sources))

        /**
         * Sets [Builder.sources] to an arbitrary JSON value.
         *
         * You should usually call [Builder.sources] with a well-typed [BetaClaudeTagSettingSources]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun sources(sources: JsonField<BetaClaudeTagSettingSources>) = apply {
            this.sources = sources
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("slack_channel")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        /**
         * Time Claude Tag's record of the channel last changed. It moves on every change to a
         * setting, and can also move when something no response holds changes, so a later value
         * says to read the channel again, not that a setting changed. Attaching or detaching a
         * Bundle does not by itself move it.
         */
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
         * Returns an immutable instance of [BetaClaudeTagSlackChannel].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .id()
         * .createdAt()
         * .settings()
         * .slack()
         * .sources()
         * .updatedAt()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaClaudeTagSlackChannel =
            BetaClaudeTagSlackChannel(
                checkRequired("id", id),
                checkRequired("createdAt", createdAt),
                checkRequired("settings", settings),
                checkRequired("slack", slack),
                checkRequired("sources", sources),
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
    fun validate(): BetaClaudeTagSlackChannel = apply {
        if (validated) {
            return@apply
        }

        id()
        createdAt()
        settings().validate()
        slack().validate()
        sources().validate()
        _type().let {
            if (it != JsonValue.from("slack_channel")) {
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
            (settings.asKnown().getOrNull()?.validity() ?: 0) +
            (slack.asKnown().getOrNull()?.validity() ?: 0) +
            (sources.asKnown().getOrNull()?.validity() ?: 0) +
            type.let { if (it == JsonValue.from("slack_channel")) 1 else 0 } +
            (if (updatedAt.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaClaudeTagSlackChannel &&
            id == other.id &&
            createdAt == other.createdAt &&
            settings == other.settings &&
            slack == other.slack &&
            sources == other.sources &&
            type == other.type &&
            updatedAt == other.updatedAt &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(id, createdAt, settings, slack, sources, type, updatedAt, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaClaudeTagSlackChannel{id=$id, createdAt=$createdAt, settings=$settings, slack=$slack, sources=$sources, type=$type, updatedAt=$updatedAt, additionalProperties=$additionalProperties}"
}
