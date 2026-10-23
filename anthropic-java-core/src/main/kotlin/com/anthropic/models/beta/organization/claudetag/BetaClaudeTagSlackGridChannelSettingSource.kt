package com.anthropic.models.beta.organization.claudetag

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
 * The value comes from the addressed channel as it is set up under another workspace of its
 * Enterprise Grid: a channel shared across the Grid keeps one Slack channel identifier in every
 * workspace that sees it.
 */
class BetaClaudeTagSlackGridChannelSettingSource
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val slack: JsonField<BetaClaudeTagSlackChannelIdentifiers>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("slack")
        @ExcludeMissing
        slack: JsonField<BetaClaudeTagSlackChannelIdentifiers> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(slack, type, mutableMapOf())

    /**
     * Slack's own identifiers for the channel under that other workspace.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun slack(): BetaClaudeTagSlackChannelIdentifiers = slack.getRequired("slack")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("slack_grid_channel")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Returns the raw JSON value of [slack].
     *
     * Unlike [slack], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("slack")
    @ExcludeMissing
    fun _slack(): JsonField<BetaClaudeTagSlackChannelIdentifiers> = slack

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
         * [BetaClaudeTagSlackGridChannelSettingSource].
         *
         * The following fields are required:
         * ```java
         * .slack()
         * ```
         */
        @JvmStatic fun builder() = Builder()

        /**
         * Returns an immutable instance of [BetaClaudeTagSlackGridChannelSettingSource] with the
         * required [slack] set to the given value.
         */
        @JvmStatic
        fun of(slack: BetaClaudeTagSlackChannelIdentifiers) = builder().slack(slack).build()
    }

    /** A builder for [BetaClaudeTagSlackGridChannelSettingSource]. */
    class Builder internal constructor() {

        private var slack: JsonField<BetaClaudeTagSlackChannelIdentifiers>? = null
        private var type: JsonValue = JsonValue.from("slack_grid_channel")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaClaudeTagSlackGridChannelSettingSource: BetaClaudeTagSlackGridChannelSettingSource
        ) = apply {
            slack = betaClaudeTagSlackGridChannelSettingSource.slack
            type = betaClaudeTagSlackGridChannelSettingSource.type
            additionalProperties =
                betaClaudeTagSlackGridChannelSettingSource.additionalProperties.toMutableMap()
        }

        /** Slack's own identifiers for the channel under that other workspace. */
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

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("slack_grid_channel")
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
         * Returns an immutable instance of [BetaClaudeTagSlackGridChannelSettingSource].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .slack()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaClaudeTagSlackGridChannelSettingSource =
            BetaClaudeTagSlackGridChannelSettingSource(
                checkRequired("slack", slack),
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
    fun validate(): BetaClaudeTagSlackGridChannelSettingSource = apply {
        if (validated) {
            return@apply
        }

        slack().validate()
        _type().let {
            if (it != JsonValue.from("slack_grid_channel")) {
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
        (slack.asKnown().getOrNull()?.validity() ?: 0) +
            type.let { if (it == JsonValue.from("slack_grid_channel")) 1 else 0 }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaClaudeTagSlackGridChannelSettingSource &&
            slack == other.slack &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(slack, type, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaClaudeTagSlackGridChannelSettingSource{slack=$slack, type=$type, additionalProperties=$additionalProperties}"
}
