package com.anthropic.models.beta.organization.claudetag.bundles.targets

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

/**
 * One Bundle attached to one Slack workspace. The Bundle can be used in the workspace and in every
 * channel in it.
 */
class BetaClaudeTagSlackWorkspaceBundleTarget
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val id: JsonField<String>,
    private val createdAt: JsonField<OffsetDateTime>,
    private val slackWorkspaceId: JsonField<String>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("created_at")
        @ExcludeMissing
        createdAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("slack_workspace_id")
        @ExcludeMissing
        slackWorkspaceId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(id, createdAt, slackWorkspaceId, type, mutableMapOf())

    /**
     * The Bundle Target's identifier. Starts with `btgt_`. No two Bundle Targets have the same one,
     * and the same Bundle and target always give the same one, also after a detach and a new
     * attach. It is opaque.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun id(): String = id.getRequired("id")

    /**
     * Time the Bundle was attached.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun createdAt(): OffsetDateTime = createdAt.getRequired("created_at")

    /**
     * The identifier of the Slack workspace the Bundle is attached to, starting with `slws_`.
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
         * [BetaClaudeTagSlackWorkspaceBundleTarget].
         *
         * The following fields are required:
         * ```java
         * .id()
         * .createdAt()
         * .slackWorkspaceId()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaClaudeTagSlackWorkspaceBundleTarget]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var createdAt: JsonField<OffsetDateTime>? = null
        private var slackWorkspaceId: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("slack_workspace")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaClaudeTagSlackWorkspaceBundleTarget: BetaClaudeTagSlackWorkspaceBundleTarget
        ) = apply {
            id = betaClaudeTagSlackWorkspaceBundleTarget.id
            createdAt = betaClaudeTagSlackWorkspaceBundleTarget.createdAt
            slackWorkspaceId = betaClaudeTagSlackWorkspaceBundleTarget.slackWorkspaceId
            type = betaClaudeTagSlackWorkspaceBundleTarget.type
            additionalProperties =
                betaClaudeTagSlackWorkspaceBundleTarget.additionalProperties.toMutableMap()
        }

        /**
         * The Bundle Target's identifier. Starts with `btgt_`. No two Bundle Targets have the same
         * one, and the same Bundle and target always give the same one, also after a detach and a
         * new attach. It is opaque.
         */
        fun id(id: String) = id(JsonField.of(id))

        /**
         * Sets [Builder.id] to an arbitrary JSON value.
         *
         * You should usually call [Builder.id] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun id(id: JsonField<String>) = apply { this.id = id }

        /** Time the Bundle was attached. */
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
         * The identifier of the Slack workspace the Bundle is attached to, starting with `slws_`.
         */
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
         * Returns an immutable instance of [BetaClaudeTagSlackWorkspaceBundleTarget].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .id()
         * .createdAt()
         * .slackWorkspaceId()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaClaudeTagSlackWorkspaceBundleTarget =
            BetaClaudeTagSlackWorkspaceBundleTarget(
                checkRequired("id", id),
                checkRequired("createdAt", createdAt),
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
    fun validate(): BetaClaudeTagSlackWorkspaceBundleTarget = apply {
        if (validated) {
            return@apply
        }

        id()
        createdAt()
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
        (if (id.asKnown().isPresent) 1 else 0) +
            (if (createdAt.asKnown().isPresent) 1 else 0) +
            (if (slackWorkspaceId.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("slack_workspace")) 1 else 0 }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaClaudeTagSlackWorkspaceBundleTarget &&
            id == other.id &&
            createdAt == other.createdAt &&
            slackWorkspaceId == other.slackWorkspaceId &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(id, createdAt, slackWorkspaceId, type, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaClaudeTagSlackWorkspaceBundleTarget{id=$id, createdAt=$createdAt, slackWorkspaceId=$slackWorkspaceId, type=$type, additionalProperties=$additionalProperties}"
}
