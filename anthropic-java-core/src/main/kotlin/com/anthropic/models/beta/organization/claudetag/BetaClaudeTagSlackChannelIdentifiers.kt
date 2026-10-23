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

/** The identifiers Slack shows for a channel. They are Slack's own, not identifiers of this API. */
class BetaClaudeTagSlackChannelIdentifiers
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val channelId: JsonField<String>,
    private val workspaceId: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("channel_id") @ExcludeMissing channelId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("workspace_id")
        @ExcludeMissing
        workspaceId: JsonField<String> = JsonMissing.of(),
    ) : this(channelId, workspaceId, mutableMapOf())

    /**
     * Slack's identifier for the channel, starting with `C` or `G`, such as `C0456EFGH`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun channelId(): String = channelId.getRequired("channel_id")

    /**
     * Slack's identifier for the workspace the channel is in, starting with `T`, such as
     * `T0123ABCD`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun workspaceId(): String = workspaceId.getRequired("workspace_id")

    /**
     * Returns the raw JSON value of [channelId].
     *
     * Unlike [channelId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("channel_id") @ExcludeMissing fun _channelId(): JsonField<String> = channelId

    /**
     * Returns the raw JSON value of [workspaceId].
     *
     * Unlike [workspaceId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("workspace_id")
    @ExcludeMissing
    fun _workspaceId(): JsonField<String> = workspaceId

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
         * [BetaClaudeTagSlackChannelIdentifiers].
         *
         * The following fields are required:
         * ```java
         * .channelId()
         * .workspaceId()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaClaudeTagSlackChannelIdentifiers]. */
    class Builder internal constructor() {

        private var channelId: JsonField<String>? = null
        private var workspaceId: JsonField<String>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaClaudeTagSlackChannelIdentifiers: BetaClaudeTagSlackChannelIdentifiers
        ) = apply {
            channelId = betaClaudeTagSlackChannelIdentifiers.channelId
            workspaceId = betaClaudeTagSlackChannelIdentifiers.workspaceId
            additionalProperties =
                betaClaudeTagSlackChannelIdentifiers.additionalProperties.toMutableMap()
        }

        /** Slack's identifier for the channel, starting with `C` or `G`, such as `C0456EFGH`. */
        fun channelId(channelId: String) = channelId(JsonField.of(channelId))

        /**
         * Sets [Builder.channelId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.channelId] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun channelId(channelId: JsonField<String>) = apply { this.channelId = channelId }

        /**
         * Slack's identifier for the workspace the channel is in, starting with `T`, such as
         * `T0123ABCD`.
         */
        fun workspaceId(workspaceId: String) = workspaceId(JsonField.of(workspaceId))

        /**
         * Sets [Builder.workspaceId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.workspaceId] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun workspaceId(workspaceId: JsonField<String>) = apply { this.workspaceId = workspaceId }

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
         * Returns an immutable instance of [BetaClaudeTagSlackChannelIdentifiers].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .channelId()
         * .workspaceId()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaClaudeTagSlackChannelIdentifiers =
            BetaClaudeTagSlackChannelIdentifiers(
                checkRequired("channelId", channelId),
                checkRequired("workspaceId", workspaceId),
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
    fun validate(): BetaClaudeTagSlackChannelIdentifiers = apply {
        if (validated) {
            return@apply
        }

        channelId()
        workspaceId()
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
        (if (channelId.asKnown().isPresent) 1 else 0) +
            (if (workspaceId.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaClaudeTagSlackChannelIdentifiers &&
            channelId == other.channelId &&
            workspaceId == other.workspaceId &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(channelId, workspaceId, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaClaudeTagSlackChannelIdentifiers{channelId=$channelId, workspaceId=$workspaceId, additionalProperties=$additionalProperties}"
}
