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

/** The value comes from a Slack workspace of the organization. */
class BetaClaudeTagSlackWorkspaceSettingSource
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val slackWorkspaceId: JsonField<String>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("slack_workspace_id")
        @ExcludeMissing
        slackWorkspaceId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(slackWorkspaceId, type, mutableMapOf())

    /**
     * The workspace's identifier, starting with `slws_`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun slackWorkspaceId(): String = slackWorkspaceId.getRequired("slack_workspace_id")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("slack_workspace")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Returns the raw JSON value of [slackWorkspaceId].
     *
     * Unlike [slackWorkspaceId], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("slack_workspace_id")
    @ExcludeMissing
    fun _slackWorkspaceId(): JsonField<String> = slackWorkspaceId

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
         * [BetaClaudeTagSlackWorkspaceSettingSource].
         *
         * The following fields are required:
         * ```java
         * .slackWorkspaceId()
         * ```
         */
        @JvmStatic fun builder() = Builder()

        /**
         * Returns an immutable instance of [BetaClaudeTagSlackWorkspaceSettingSource] with the
         * required [slackWorkspaceId] set to the given value.
         */
        @JvmStatic
        fun of(slackWorkspaceId: String) = builder().slackWorkspaceId(slackWorkspaceId).build()
    }

    /** A builder for [BetaClaudeTagSlackWorkspaceSettingSource]. */
    class Builder internal constructor() {

        private var slackWorkspaceId: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("slack_workspace")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaClaudeTagSlackWorkspaceSettingSource: BetaClaudeTagSlackWorkspaceSettingSource
        ) = apply {
            slackWorkspaceId = betaClaudeTagSlackWorkspaceSettingSource.slackWorkspaceId
            type = betaClaudeTagSlackWorkspaceSettingSource.type
            additionalProperties =
                betaClaudeTagSlackWorkspaceSettingSource.additionalProperties.toMutableMap()
        }

        /** The workspace's identifier, starting with `slws_`. */
        fun slackWorkspaceId(slackWorkspaceId: String) =
            slackWorkspaceId(JsonField.of(slackWorkspaceId))

        /**
         * Sets [Builder.slackWorkspaceId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.slackWorkspaceId] with a well-typed [String] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun slackWorkspaceId(slackWorkspaceId: JsonField<String>) = apply {
            this.slackWorkspaceId = slackWorkspaceId
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("slack_workspace")
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
         * Returns an immutable instance of [BetaClaudeTagSlackWorkspaceSettingSource].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .slackWorkspaceId()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaClaudeTagSlackWorkspaceSettingSource =
            BetaClaudeTagSlackWorkspaceSettingSource(
                checkRequired("slackWorkspaceId", slackWorkspaceId),
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
    fun validate(): BetaClaudeTagSlackWorkspaceSettingSource = apply {
        if (validated) {
            return@apply
        }

        slackWorkspaceId()
        _type().let {
            if (it != JsonValue.from("slack_workspace")) {
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
        (if (slackWorkspaceId.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("slack_workspace")) 1 else 0 }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaClaudeTagSlackWorkspaceSettingSource &&
            slackWorkspaceId == other.slackWorkspaceId &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(slackWorkspaceId, type, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaClaudeTagSlackWorkspaceSettingSource{slackWorkspaceId=$slackWorkspaceId, type=$type, additionalProperties=$additionalProperties}"
}
