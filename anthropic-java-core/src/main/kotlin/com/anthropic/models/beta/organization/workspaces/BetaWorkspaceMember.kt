// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.workspaces

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

class BetaWorkspaceMember
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val type: JsonValue,
    private val userId: JsonField<String>,
    private val workspaceId: JsonField<String>,
    private val workspaceRole: JsonField<BetaWorkspaceRole>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("user_id") @ExcludeMissing userId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("workspace_id")
        @ExcludeMissing
        workspaceId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("workspace_role")
        @ExcludeMissing
        workspaceRole: JsonField<BetaWorkspaceRole> = JsonMissing.of(),
    ) : this(type, userId, workspaceId, workspaceRole, mutableMapOf())

    /**
     * Object type.
     *
     * For Workspace Members, this is always `"workspace_member"`.
     *
     * Expected to always return the following:
     * ```java
     * JsonValue.from("workspace_member")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * ID of the User.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun userId(): String = userId.getRequired("user_id")

    /**
     * ID of the Workspace.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun workspaceId(): String = workspaceId.getRequired("workspace_id")

    /**
     * Role of the Workspace Member.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun workspaceRole(): BetaWorkspaceRole = workspaceRole.getRequired("workspace_role")

    /**
     * Returns the raw JSON value of [userId].
     *
     * Unlike [userId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("user_id") @ExcludeMissing fun _userId(): JsonField<String> = userId

    /**
     * Returns the raw JSON value of [workspaceId].
     *
     * Unlike [workspaceId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("workspace_id")
    @ExcludeMissing
    fun _workspaceId(): JsonField<String> = workspaceId

    /**
     * Returns the raw JSON value of [workspaceRole].
     *
     * Unlike [workspaceRole], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("workspace_role")
    @ExcludeMissing
    fun _workspaceRole(): JsonField<BetaWorkspaceRole> = workspaceRole

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
         * Returns a mutable builder for constructing an instance of [BetaWorkspaceMember].
         *
         * The following fields are required:
         * ```java
         * .userId()
         * .workspaceId()
         * .workspaceRole()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaWorkspaceMember]. */
    class Builder internal constructor() {

        private var type: JsonValue = JsonValue.from("workspace_member")
        private var userId: JsonField<String>? = null
        private var workspaceId: JsonField<String>? = null
        private var workspaceRole: JsonField<BetaWorkspaceRole>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaWorkspaceMember: BetaWorkspaceMember) = apply {
            type = betaWorkspaceMember.type
            userId = betaWorkspaceMember.userId
            workspaceId = betaWorkspaceMember.workspaceId
            workspaceRole = betaWorkspaceMember.workspaceRole
            additionalProperties = betaWorkspaceMember.additionalProperties.toMutableMap()
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("workspace_member")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        /** ID of the User. */
        fun userId(userId: String) = userId(JsonField.of(userId))

        /**
         * Sets [Builder.userId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.userId] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun userId(userId: JsonField<String>) = apply { this.userId = userId }

        /** ID of the Workspace. */
        fun workspaceId(workspaceId: String) = workspaceId(JsonField.of(workspaceId))

        /**
         * Sets [Builder.workspaceId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.workspaceId] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun workspaceId(workspaceId: JsonField<String>) = apply { this.workspaceId = workspaceId }

        /** Role of the Workspace Member. */
        fun workspaceRole(workspaceRole: BetaWorkspaceRole) =
            workspaceRole(JsonField.of(workspaceRole))

        /**
         * Sets [Builder.workspaceRole] to an arbitrary JSON value.
         *
         * You should usually call [Builder.workspaceRole] with a well-typed [BetaWorkspaceRole]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun workspaceRole(workspaceRole: JsonField<BetaWorkspaceRole>) = apply {
            this.workspaceRole = workspaceRole
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
         * Returns an immutable instance of [BetaWorkspaceMember].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .userId()
         * .workspaceId()
         * .workspaceRole()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaWorkspaceMember =
            BetaWorkspaceMember(
                type,
                checkRequired("userId", userId),
                checkRequired("workspaceId", workspaceId),
                checkRequired("workspaceRole", workspaceRole),
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
    fun validate(): BetaWorkspaceMember = apply {
        if (validated) {
            return@apply
        }

        _type().let {
            if (it != JsonValue.from("workspace_member")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        userId()
        workspaceId()
        workspaceRole().validate()
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
        type.let { if (it == JsonValue.from("workspace_member")) 1 else 0 } +
            (if (userId.asKnown().isPresent) 1 else 0) +
            (if (workspaceId.asKnown().isPresent) 1 else 0) +
            (workspaceRole.asKnown().getOrNull()?.validity() ?: 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaWorkspaceMember &&
            type == other.type &&
            userId == other.userId &&
            workspaceId == other.workspaceId &&
            workspaceRole == other.workspaceRole &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(type, userId, workspaceId, workspaceRole, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaWorkspaceMember{type=$type, userId=$userId, workspaceId=$workspaceId, workspaceRole=$workspaceRole, additionalProperties=$additionalProperties}"
}
