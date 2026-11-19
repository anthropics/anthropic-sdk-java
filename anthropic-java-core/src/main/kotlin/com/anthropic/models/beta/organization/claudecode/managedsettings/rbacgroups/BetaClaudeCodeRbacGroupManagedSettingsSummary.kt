package com.anthropic.models.beta.organization.claudecode.managedsettings.rbacgroups

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

class BetaClaudeCodeRbacGroupManagedSettingsSummary
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val createdAt: JsonField<OffsetDateTime>,
    private val rbacGroupId: JsonField<String>,
    private val rbacGroupStatus: JsonField<BetaClaudeCodeRbacGroupStatus>,
    private val type: JsonValue,
    private val updatedAt: JsonField<OffsetDateTime>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("created_at")
        @ExcludeMissing
        createdAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("rbac_group_id")
        @ExcludeMissing
        rbacGroupId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("rbac_group_status")
        @ExcludeMissing
        rbacGroupStatus: JsonField<BetaClaudeCodeRbacGroupStatus> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("updated_at")
        @ExcludeMissing
        updatedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
    ) : this(createdAt, rbacGroupId, rbacGroupStatus, type, updatedAt, mutableMapOf())

    /**
     * When the settings document this group uses was first stored, in RFC 3339 format. This can be
     * earlier than when the group got its settings, and it changes if the group gets a different
     * document.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun createdAt(): OffsetDateTime = createdAt.getRequired("created_at")

    /**
     * The RBAC Group's ID, prefixed `rbac_group_`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun rbacGroupId(): String = rbacGroupId.getRequired("rbac_group_id")

    /**
     * Whether the group is still one of the organization's RBAC Groups. Its `type` is `active` if
     * it is, `deleted` if it is not, and `unknown` if that cannot be checked right now. A deleted
     * group's settings stay in `rbac_group_order` but match no member.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun rbacGroupStatus(): BetaClaudeCodeRbacGroupStatus =
        rbacGroupStatus.getRequired("rbac_group_status")

    /**
     * Always `claude_code_rbac_group_managed_settings_summary`.
     *
     * Expected to always return the following:
     * ```java
     * JsonValue.from("claude_code_rbac_group_managed_settings_summary")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * When this group's settings or its position in the order last changed, in RFC 3339 format. Its
     * position changes whenever the number of groups ahead of it changes.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun updatedAt(): OffsetDateTime = updatedAt.getRequired("updated_at")

    /**
     * Returns the raw JSON value of [createdAt].
     *
     * Unlike [createdAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("created_at")
    @ExcludeMissing
    fun _createdAt(): JsonField<OffsetDateTime> = createdAt

    /**
     * Returns the raw JSON value of [rbacGroupId].
     *
     * Unlike [rbacGroupId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("rbac_group_id")
    @ExcludeMissing
    fun _rbacGroupId(): JsonField<String> = rbacGroupId

    /**
     * Returns the raw JSON value of [rbacGroupStatus].
     *
     * Unlike [rbacGroupStatus], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("rbac_group_status")
    @ExcludeMissing
    fun _rbacGroupStatus(): JsonField<BetaClaudeCodeRbacGroupStatus> = rbacGroupStatus

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
         * Returns a mutable builder for constructing an instance of
         * [BetaClaudeCodeRbacGroupManagedSettingsSummary].
         *
         * The following fields are required:
         * ```java
         * .createdAt()
         * .rbacGroupId()
         * .rbacGroupStatus()
         * .updatedAt()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaClaudeCodeRbacGroupManagedSettingsSummary]. */
    class Builder internal constructor() {

        private var createdAt: JsonField<OffsetDateTime>? = null
        private var rbacGroupId: JsonField<String>? = null
        private var rbacGroupStatus: JsonField<BetaClaudeCodeRbacGroupStatus>? = null
        private var type: JsonValue =
            JsonValue.from("claude_code_rbac_group_managed_settings_summary")
        private var updatedAt: JsonField<OffsetDateTime>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaClaudeCodeRbacGroupManagedSettingsSummary:
                BetaClaudeCodeRbacGroupManagedSettingsSummary
        ) = apply {
            createdAt = betaClaudeCodeRbacGroupManagedSettingsSummary.createdAt
            rbacGroupId = betaClaudeCodeRbacGroupManagedSettingsSummary.rbacGroupId
            rbacGroupStatus = betaClaudeCodeRbacGroupManagedSettingsSummary.rbacGroupStatus
            type = betaClaudeCodeRbacGroupManagedSettingsSummary.type
            updatedAt = betaClaudeCodeRbacGroupManagedSettingsSummary.updatedAt
            additionalProperties =
                betaClaudeCodeRbacGroupManagedSettingsSummary.additionalProperties.toMutableMap()
        }

        /**
         * When the settings document this group uses was first stored, in RFC 3339 format. This can
         * be earlier than when the group got its settings, and it changes if the group gets a
         * different document.
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

        /** The RBAC Group's ID, prefixed `rbac_group_`. */
        fun rbacGroupId(rbacGroupId: String) = rbacGroupId(JsonField.of(rbacGroupId))

        /**
         * Sets [Builder.rbacGroupId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.rbacGroupId] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun rbacGroupId(rbacGroupId: JsonField<String>) = apply { this.rbacGroupId = rbacGroupId }

        /**
         * Whether the group is still one of the organization's RBAC Groups. Its `type` is `active`
         * if it is, `deleted` if it is not, and `unknown` if that cannot be checked right now. A
         * deleted group's settings stay in `rbac_group_order` but match no member.
         */
        fun rbacGroupStatus(rbacGroupStatus: BetaClaudeCodeRbacGroupStatus) =
            rbacGroupStatus(JsonField.of(rbacGroupStatus))

        /**
         * Sets [Builder.rbacGroupStatus] to an arbitrary JSON value.
         *
         * You should usually call [Builder.rbacGroupStatus] with a well-typed
         * [BetaClaudeCodeRbacGroupStatus] value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun rbacGroupStatus(rbacGroupStatus: JsonField<BetaClaudeCodeRbacGroupStatus>) = apply {
            this.rbacGroupStatus = rbacGroupStatus
        }

        /**
         * Alias for calling [rbacGroupStatus] with
         * `BetaClaudeCodeRbacGroupStatus.ofActive(active)`.
         */
        fun rbacGroupStatus(active: BetaClaudeCodeRbacGroupStatusActive) =
            rbacGroupStatus(BetaClaudeCodeRbacGroupStatus.ofActive(active))

        /**
         * Alias for calling [rbacGroupStatus] with
         * `BetaClaudeCodeRbacGroupStatus.ofDeleted(deleted)`.
         */
        fun rbacGroupStatus(deleted: BetaClaudeCodeRbacGroupStatusDeleted) =
            rbacGroupStatus(BetaClaudeCodeRbacGroupStatus.ofDeleted(deleted))

        /**
         * Alias for calling [rbacGroupStatus] with
         * `BetaClaudeCodeRbacGroupStatus.ofUnknown(unknown)`.
         */
        fun rbacGroupStatus(unknown: BetaClaudeCodeRbacGroupStatusUnknown) =
            rbacGroupStatus(BetaClaudeCodeRbacGroupStatus.ofUnknown(unknown))

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("claude_code_rbac_group_managed_settings_summary")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        /**
         * When this group's settings or its position in the order last changed, in RFC 3339 format.
         * Its position changes whenever the number of groups ahead of it changes.
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
         * Returns an immutable instance of [BetaClaudeCodeRbacGroupManagedSettingsSummary].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .createdAt()
         * .rbacGroupId()
         * .rbacGroupStatus()
         * .updatedAt()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaClaudeCodeRbacGroupManagedSettingsSummary =
            BetaClaudeCodeRbacGroupManagedSettingsSummary(
                checkRequired("createdAt", createdAt),
                checkRequired("rbacGroupId", rbacGroupId),
                checkRequired("rbacGroupStatus", rbacGroupStatus),
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
    fun validate(): BetaClaudeCodeRbacGroupManagedSettingsSummary = apply {
        if (validated) {
            return@apply
        }

        createdAt()
        rbacGroupId()
        rbacGroupStatus().validate()
        _type().let {
            if (it != JsonValue.from("claude_code_rbac_group_managed_settings_summary")) {
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
        (if (createdAt.asKnown().isPresent) 1 else 0) +
            (if (rbacGroupId.asKnown().isPresent) 1 else 0) +
            (rbacGroupStatus.asKnown().getOrNull()?.validity() ?: 0) +
            type.let {
                if (it == JsonValue.from("claude_code_rbac_group_managed_settings_summary")) 1
                else 0
            } +
            (if (updatedAt.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaClaudeCodeRbacGroupManagedSettingsSummary &&
            createdAt == other.createdAt &&
            rbacGroupId == other.rbacGroupId &&
            rbacGroupStatus == other.rbacGroupStatus &&
            type == other.type &&
            updatedAt == other.updatedAt &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(createdAt, rbacGroupId, rbacGroupStatus, type, updatedAt, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaClaudeCodeRbacGroupManagedSettingsSummary{createdAt=$createdAt, rbacGroupId=$rbacGroupId, rbacGroupStatus=$rbacGroupStatus, type=$type, updatedAt=$updatedAt, additionalProperties=$additionalProperties}"
}
