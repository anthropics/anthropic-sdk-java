// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.federation.rules

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

class BetaFederationRuleWorkspace
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val createdAt: JsonField<OffsetDateTime>,
    private val createdByActorId: JsonField<String>,
    private val federationRuleId: JsonField<String>,
    private val type: JsonValue,
    private val workspaceId: JsonField<String>,
    private val workspaceName: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("created_at")
        @ExcludeMissing
        createdAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("created_by_actor_id")
        @ExcludeMissing
        createdByActorId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("federation_rule_id")
        @ExcludeMissing
        federationRuleId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("workspace_id")
        @ExcludeMissing
        workspaceId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("workspace_name")
        @ExcludeMissing
        workspaceName: JsonField<String> = JsonMissing.of(),
    ) : this(
        createdAt,
        createdByActorId,
        federationRuleId,
        type,
        workspaceId,
        workspaceName,
        mutableMapOf(),
    )

    /**
     * When this workspace was enabled for the rule.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun createdAt(): OffsetDateTime = createdAt.getRequired("created_at")

    /**
     * Tagged ID (`user_...` or `svac_...`) of the actor that enabled this workspace for the rule,
     * if known.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun createdByActorId(): Optional<String> = createdByActorId.getOptional("created_by_actor_id")

    /**
     * Tagged ID of the federation rule.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun federationRuleId(): String = federationRuleId.getRequired("federation_rule_id")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("federation_rule_workspace")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Tagged ID of the workspace this rule is enabled for.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun workspaceId(): String = workspaceId.getRequired("workspace_id")

    /**
     * Workspace display name. Populated when listing; null in the enable response.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun workspaceName(): Optional<String> = workspaceName.getOptional("workspace_name")

    /**
     * Returns the raw JSON value of [createdAt].
     *
     * Unlike [createdAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("created_at")
    @ExcludeMissing
    fun _createdAt(): JsonField<OffsetDateTime> = createdAt

    /**
     * Returns the raw JSON value of [createdByActorId].
     *
     * Unlike [createdByActorId], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("created_by_actor_id")
    @ExcludeMissing
    fun _createdByActorId(): JsonField<String> = createdByActorId

    /**
     * Returns the raw JSON value of [federationRuleId].
     *
     * Unlike [federationRuleId], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("federation_rule_id")
    @ExcludeMissing
    fun _federationRuleId(): JsonField<String> = federationRuleId

    /**
     * Returns the raw JSON value of [workspaceId].
     *
     * Unlike [workspaceId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("workspace_id")
    @ExcludeMissing
    fun _workspaceId(): JsonField<String> = workspaceId

    /**
     * Returns the raw JSON value of [workspaceName].
     *
     * Unlike [workspaceName], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("workspace_name")
    @ExcludeMissing
    fun _workspaceName(): JsonField<String> = workspaceName

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
         * Returns a mutable builder for constructing an instance of [BetaFederationRuleWorkspace].
         *
         * The following fields are required:
         * ```java
         * .createdAt()
         * .createdByActorId()
         * .federationRuleId()
         * .workspaceId()
         * .workspaceName()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaFederationRuleWorkspace]. */
    class Builder internal constructor() {

        private var createdAt: JsonField<OffsetDateTime>? = null
        private var createdByActorId: JsonField<String>? = null
        private var federationRuleId: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("federation_rule_workspace")
        private var workspaceId: JsonField<String>? = null
        private var workspaceName: JsonField<String>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaFederationRuleWorkspace: BetaFederationRuleWorkspace) = apply {
            createdAt = betaFederationRuleWorkspace.createdAt
            createdByActorId = betaFederationRuleWorkspace.createdByActorId
            federationRuleId = betaFederationRuleWorkspace.federationRuleId
            type = betaFederationRuleWorkspace.type
            workspaceId = betaFederationRuleWorkspace.workspaceId
            workspaceName = betaFederationRuleWorkspace.workspaceName
            additionalProperties = betaFederationRuleWorkspace.additionalProperties.toMutableMap()
        }

        /** When this workspace was enabled for the rule. */
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
         * Tagged ID (`user_...` or `svac_...`) of the actor that enabled this workspace for the
         * rule, if known.
         */
        fun createdByActorId(createdByActorId: String?) =
            createdByActorId(JsonField.ofNullable(createdByActorId))

        /** Alias for calling [Builder.createdByActorId] with `createdByActorId.orElse(null)`. */
        fun createdByActorId(createdByActorId: Optional<String>) =
            createdByActorId(createdByActorId.getOrNull())

        /**
         * Sets [Builder.createdByActorId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.createdByActorId] with a well-typed [String] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun createdByActorId(createdByActorId: JsonField<String>) = apply {
            this.createdByActorId = createdByActorId
        }

        /** Tagged ID of the federation rule. */
        fun federationRuleId(federationRuleId: String) =
            federationRuleId(JsonField.of(federationRuleId))

        /**
         * Sets [Builder.federationRuleId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.federationRuleId] with a well-typed [String] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun federationRuleId(federationRuleId: JsonField<String>) = apply {
            this.federationRuleId = federationRuleId
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("federation_rule_workspace")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        /** Tagged ID of the workspace this rule is enabled for. */
        fun workspaceId(workspaceId: String) = workspaceId(JsonField.of(workspaceId))

        /**
         * Sets [Builder.workspaceId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.workspaceId] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun workspaceId(workspaceId: JsonField<String>) = apply { this.workspaceId = workspaceId }

        /** Workspace display name. Populated when listing; null in the enable response. */
        fun workspaceName(workspaceName: String?) =
            workspaceName(JsonField.ofNullable(workspaceName))

        /** Alias for calling [Builder.workspaceName] with `workspaceName.orElse(null)`. */
        fun workspaceName(workspaceName: Optional<String>) =
            workspaceName(workspaceName.getOrNull())

        /**
         * Sets [Builder.workspaceName] to an arbitrary JSON value.
         *
         * You should usually call [Builder.workspaceName] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun workspaceName(workspaceName: JsonField<String>) = apply {
            this.workspaceName = workspaceName
        }

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
         * Returns an immutable instance of [BetaFederationRuleWorkspace].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .createdAt()
         * .createdByActorId()
         * .federationRuleId()
         * .workspaceId()
         * .workspaceName()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaFederationRuleWorkspace =
            BetaFederationRuleWorkspace(
                checkRequired("createdAt", createdAt),
                checkRequired("createdByActorId", createdByActorId),
                checkRequired("federationRuleId", federationRuleId),
                type,
                checkRequired("workspaceId", workspaceId),
                checkRequired("workspaceName", workspaceName),
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
    fun validate(): BetaFederationRuleWorkspace = apply {
        if (validated) {
            return@apply
        }

        createdAt()
        createdByActorId()
        federationRuleId()
        _type().let {
            if (it != JsonValue.from("federation_rule_workspace")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        workspaceId()
        workspaceName()
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
        (if (createdAt.asKnown().isPresent) 1 else 0) +
            (if (createdByActorId.asKnown().isPresent) 1 else 0) +
            (if (federationRuleId.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("federation_rule_workspace")) 1 else 0 } +
            (if (workspaceId.asKnown().isPresent) 1 else 0) +
            (if (workspaceName.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaFederationRuleWorkspace &&
            createdAt == other.createdAt &&
            createdByActorId == other.createdByActorId &&
            federationRuleId == other.federationRuleId &&
            type == other.type &&
            workspaceId == other.workspaceId &&
            workspaceName == other.workspaceName &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            createdAt,
            createdByActorId,
            federationRuleId,
            type,
            workspaceId,
            workspaceName,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaFederationRuleWorkspace{createdAt=$createdAt, createdByActorId=$createdByActorId, federationRuleId=$federationRuleId, type=$type, workspaceId=$workspaceId, workspaceName=$workspaceName, additionalProperties=$additionalProperties}"
}
