package com.anthropic.models.beta.organization.analytics

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

/** Per-project activity data for a given day. */
class BetaAnalyticsProjectActivity
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val distinctUserCount: JsonField<Long>,
    private val messageCount: JsonField<Long>,
    private val projectId: JsonField<String>,
    private val projectName: JsonField<String>,
    private val createdAt: JsonField<OffsetDateTime>,
    private val createdBy: JsonField<BetaAnalyticsUser>,
    private val distinctConversationCount: JsonField<Long>,
    private val product: JsonField<String>,
    private val rbacGroupId: JsonField<String>,
    private val rbacGroupName: JsonField<String>,
    private val userId: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("distinct_user_count")
        @ExcludeMissing
        distinctUserCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("message_count")
        @ExcludeMissing
        messageCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("project_id") @ExcludeMissing projectId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("project_name")
        @ExcludeMissing
        projectName: JsonField<String> = JsonMissing.of(),
        @JsonProperty("created_at")
        @ExcludeMissing
        createdAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("created_by")
        @ExcludeMissing
        createdBy: JsonField<BetaAnalyticsUser> = JsonMissing.of(),
        @JsonProperty("distinct_conversation_count")
        @ExcludeMissing
        distinctConversationCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("product") @ExcludeMissing product: JsonField<String> = JsonMissing.of(),
        @JsonProperty("rbac_group_id")
        @ExcludeMissing
        rbacGroupId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("rbac_group_name")
        @ExcludeMissing
        rbacGroupName: JsonField<String> = JsonMissing.of(),
        @JsonProperty("user_id") @ExcludeMissing userId: JsonField<String> = JsonMissing.of(),
    ) : this(
        distinctUserCount,
        messageCount,
        projectId,
        projectName,
        createdAt,
        createdBy,
        distinctConversationCount,
        product,
        rbacGroupId,
        rbacGroupName,
        userId,
        mutableMapOf(),
    )

    /**
     * Number of distinct users who used the project on the requested day, or, in date-range mode,
     * over the requested window — recomputed as an exact distinct count over the window's
     * per-member daily rows, never a sum of per-day values.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun distinctUserCount(): Long = distinctUserCount.getRequired("distinct_user_count")

    /**
     * Number of messages sent in the project on the requested day
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun messageCount(): Long = messageCount.getRequired("message_count")

    /**
     * Tagged project identifier (e.g. `claude_proj_...`)
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun projectId(): String = projectId.getRequired("project_id")

    /**
     * Name of the project
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun projectName(): String = projectName.getRequired("project_name")

    /**
     * Project creation timestamp in RFC 3339 format. Null if the project was deleted before
     * attribution was recorded.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun createdAt(): Optional<OffsetDateTime> = createdAt.getOptional("created_at")

    /**
     * User who created the project. Null if the project was deleted before attribution was
     * recorded, or if the creator's account no longer exists.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun createdBy(): Optional<BetaAnalyticsUser> = createdBy.getOptional("created_by")

    /**
     * Number of distinct conversations in the project. Null on aggregated rows where a distinct
     * count cannot be computed.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun distinctConversationCount(): Optional<Long> =
        distinctConversationCount.getOptional("distinct_conversation_count")

    /**
     * Product that produced this row's activity: one of `chat`, `claude_code`, `cowork`, or
     * `office_agent` (the canonical Cost & Usage product naming; an `office_agent` row's
     * per-surface breakdown is in its `office_metrics`). On `/plugins` only `cowork` and
     * `claude_code` occur (the only surfaces with plugin attribution); on `/artifacts` only `chat`,
     * `claude_code`, and `cowork` occur (the surfaces that create artifacts); `/apps/chat/projects`
     * does not support the product dimension (a `product` entry in `group_by[]` or `filter[]` there
     * is rejected). Present only when the request grouped by `product`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun product(): Optional<String> = product.getOptional("product")

    /**
     * Tagged RBAC group identifier (`rbac_group_...`), matching the spend-limits API spelling.
     * Present only when the request grouped by `rbac_group_id`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun rbacGroupId(): Optional<String> = rbacGroupId.getOptional("rbac_group_id")

    /**
     * Resolved RBAC group display name, alongside `rbac_group_id` when name resolution is
     * available. Null if the group has been deleted or its name could not be resolved;
     * `rbac_group_id` remains the stable key.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun rbacGroupName(): Optional<String> = rbacGroupName.getOptional("rbac_group_name")

    /**
     * Tagged user identifier (e.g. `user_...`). Present only when the request grouped by `user_id`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun userId(): Optional<String> = userId.getOptional("user_id")

    /**
     * Returns the raw JSON value of [distinctUserCount].
     *
     * Unlike [distinctUserCount], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("distinct_user_count")
    @ExcludeMissing
    fun _distinctUserCount(): JsonField<Long> = distinctUserCount

    /**
     * Returns the raw JSON value of [messageCount].
     *
     * Unlike [messageCount], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("message_count")
    @ExcludeMissing
    fun _messageCount(): JsonField<Long> = messageCount

    /**
     * Returns the raw JSON value of [projectId].
     *
     * Unlike [projectId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("project_id") @ExcludeMissing fun _projectId(): JsonField<String> = projectId

    /**
     * Returns the raw JSON value of [projectName].
     *
     * Unlike [projectName], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("project_name")
    @ExcludeMissing
    fun _projectName(): JsonField<String> = projectName

    /**
     * Returns the raw JSON value of [createdAt].
     *
     * Unlike [createdAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("created_at")
    @ExcludeMissing
    fun _createdAt(): JsonField<OffsetDateTime> = createdAt

    /**
     * Returns the raw JSON value of [createdBy].
     *
     * Unlike [createdBy], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("created_by")
    @ExcludeMissing
    fun _createdBy(): JsonField<BetaAnalyticsUser> = createdBy

    /**
     * Returns the raw JSON value of [distinctConversationCount].
     *
     * Unlike [distinctConversationCount], this method doesn't throw if the JSON field has an
     * unexpected type.
     */
    @JsonProperty("distinct_conversation_count")
    @ExcludeMissing
    fun _distinctConversationCount(): JsonField<Long> = distinctConversationCount

    /**
     * Returns the raw JSON value of [product].
     *
     * Unlike [product], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("product") @ExcludeMissing fun _product(): JsonField<String> = product

    /**
     * Returns the raw JSON value of [rbacGroupId].
     *
     * Unlike [rbacGroupId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("rbac_group_id")
    @ExcludeMissing
    fun _rbacGroupId(): JsonField<String> = rbacGroupId

    /**
     * Returns the raw JSON value of [rbacGroupName].
     *
     * Unlike [rbacGroupName], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("rbac_group_name")
    @ExcludeMissing
    fun _rbacGroupName(): JsonField<String> = rbacGroupName

    /**
     * Returns the raw JSON value of [userId].
     *
     * Unlike [userId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("user_id") @ExcludeMissing fun _userId(): JsonField<String> = userId

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
         * Returns a mutable builder for constructing an instance of [BetaAnalyticsProjectActivity].
         *
         * The following fields are required:
         * ```java
         * .distinctUserCount()
         * .messageCount()
         * .projectId()
         * .projectName()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaAnalyticsProjectActivity]. */
    class Builder internal constructor() {

        private var distinctUserCount: JsonField<Long>? = null
        private var messageCount: JsonField<Long>? = null
        private var projectId: JsonField<String>? = null
        private var projectName: JsonField<String>? = null
        private var createdAt: JsonField<OffsetDateTime> = JsonMissing.of()
        private var createdBy: JsonField<BetaAnalyticsUser> = JsonMissing.of()
        private var distinctConversationCount: JsonField<Long> = JsonMissing.of()
        private var product: JsonField<String> = JsonMissing.of()
        private var rbacGroupId: JsonField<String> = JsonMissing.of()
        private var rbacGroupName: JsonField<String> = JsonMissing.of()
        private var userId: JsonField<String> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaAnalyticsProjectActivity: BetaAnalyticsProjectActivity) = apply {
            distinctUserCount = betaAnalyticsProjectActivity.distinctUserCount
            messageCount = betaAnalyticsProjectActivity.messageCount
            projectId = betaAnalyticsProjectActivity.projectId
            projectName = betaAnalyticsProjectActivity.projectName
            createdAt = betaAnalyticsProjectActivity.createdAt
            createdBy = betaAnalyticsProjectActivity.createdBy
            distinctConversationCount = betaAnalyticsProjectActivity.distinctConversationCount
            product = betaAnalyticsProjectActivity.product
            rbacGroupId = betaAnalyticsProjectActivity.rbacGroupId
            rbacGroupName = betaAnalyticsProjectActivity.rbacGroupName
            userId = betaAnalyticsProjectActivity.userId
            additionalProperties = betaAnalyticsProjectActivity.additionalProperties.toMutableMap()
        }

        /**
         * Number of distinct users who used the project on the requested day, or, in date-range
         * mode, over the requested window — recomputed as an exact distinct count over the window's
         * per-member daily rows, never a sum of per-day values.
         */
        fun distinctUserCount(distinctUserCount: Long) =
            distinctUserCount(JsonField.of(distinctUserCount))

        /**
         * Sets [Builder.distinctUserCount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.distinctUserCount] with a well-typed [Long] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun distinctUserCount(distinctUserCount: JsonField<Long>) = apply {
            this.distinctUserCount = distinctUserCount
        }

        /** Number of messages sent in the project on the requested day */
        fun messageCount(messageCount: Long) = messageCount(JsonField.of(messageCount))

        /**
         * Sets [Builder.messageCount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.messageCount] with a well-typed [Long] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun messageCount(messageCount: JsonField<Long>) = apply { this.messageCount = messageCount }

        /** Tagged project identifier (e.g. `claude_proj_...`) */
        fun projectId(projectId: String) = projectId(JsonField.of(projectId))

        /**
         * Sets [Builder.projectId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.projectId] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun projectId(projectId: JsonField<String>) = apply { this.projectId = projectId }

        /** Name of the project */
        fun projectName(projectName: String) = projectName(JsonField.of(projectName))

        /**
         * Sets [Builder.projectName] to an arbitrary JSON value.
         *
         * You should usually call [Builder.projectName] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun projectName(projectName: JsonField<String>) = apply { this.projectName = projectName }

        /**
         * Project creation timestamp in RFC 3339 format. Null if the project was deleted before
         * attribution was recorded.
         */
        fun createdAt(createdAt: OffsetDateTime?) = createdAt(JsonField.ofNullable(createdAt))

        /** Alias for calling [Builder.createdAt] with `createdAt.orElse(null)`. */
        fun createdAt(createdAt: Optional<OffsetDateTime>) = createdAt(createdAt.getOrNull())

        /**
         * Sets [Builder.createdAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.createdAt] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun createdAt(createdAt: JsonField<OffsetDateTime>) = apply { this.createdAt = createdAt }

        /**
         * User who created the project. Null if the project was deleted before attribution was
         * recorded, or if the creator's account no longer exists.
         */
        fun createdBy(createdBy: BetaAnalyticsUser?) = createdBy(JsonField.ofNullable(createdBy))

        /** Alias for calling [Builder.createdBy] with `createdBy.orElse(null)`. */
        fun createdBy(createdBy: Optional<BetaAnalyticsUser>) = createdBy(createdBy.getOrNull())

        /**
         * Sets [Builder.createdBy] to an arbitrary JSON value.
         *
         * You should usually call [Builder.createdBy] with a well-typed [BetaAnalyticsUser] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun createdBy(createdBy: JsonField<BetaAnalyticsUser>) = apply {
            this.createdBy = createdBy
        }

        /**
         * Number of distinct conversations in the project. Null on aggregated rows where a distinct
         * count cannot be computed.
         */
        fun distinctConversationCount(distinctConversationCount: Long?) =
            distinctConversationCount(JsonField.ofNullable(distinctConversationCount))

        /**
         * Alias for [Builder.distinctConversationCount].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun distinctConversationCount(distinctConversationCount: Long) =
            distinctConversationCount(distinctConversationCount as Long?)

        /**
         * Alias for calling [Builder.distinctConversationCount] with
         * `distinctConversationCount.orElse(null)`.
         */
        fun distinctConversationCount(distinctConversationCount: Optional<Long>) =
            distinctConversationCount(distinctConversationCount.getOrNull())

        /**
         * Sets [Builder.distinctConversationCount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.distinctConversationCount] with a well-typed [Long]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun distinctConversationCount(distinctConversationCount: JsonField<Long>) = apply {
            this.distinctConversationCount = distinctConversationCount
        }

        /**
         * Product that produced this row's activity: one of `chat`, `claude_code`, `cowork`, or
         * `office_agent` (the canonical Cost & Usage product naming; an `office_agent` row's
         * per-surface breakdown is in its `office_metrics`). On `/plugins` only `cowork` and
         * `claude_code` occur (the only surfaces with plugin attribution); on `/artifacts` only
         * `chat`, `claude_code`, and `cowork` occur (the surfaces that create artifacts);
         * `/apps/chat/projects` does not support the product dimension (a `product` entry in
         * `group_by[]` or `filter[]` there is rejected). Present only when the request grouped by
         * `product`.
         */
        fun product(product: String?) = product(JsonField.ofNullable(product))

        /** Alias for calling [Builder.product] with `product.orElse(null)`. */
        fun product(product: Optional<String>) = product(product.getOrNull())

        /**
         * Sets [Builder.product] to an arbitrary JSON value.
         *
         * You should usually call [Builder.product] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun product(product: JsonField<String>) = apply { this.product = product }

        /**
         * Tagged RBAC group identifier (`rbac_group_...`), matching the spend-limits API spelling.
         * Present only when the request grouped by `rbac_group_id`.
         */
        fun rbacGroupId(rbacGroupId: String?) = rbacGroupId(JsonField.ofNullable(rbacGroupId))

        /** Alias for calling [Builder.rbacGroupId] with `rbacGroupId.orElse(null)`. */
        fun rbacGroupId(rbacGroupId: Optional<String>) = rbacGroupId(rbacGroupId.getOrNull())

        /**
         * Sets [Builder.rbacGroupId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.rbacGroupId] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun rbacGroupId(rbacGroupId: JsonField<String>) = apply { this.rbacGroupId = rbacGroupId }

        /**
         * Resolved RBAC group display name, alongside `rbac_group_id` when name resolution is
         * available. Null if the group has been deleted or its name could not be resolved;
         * `rbac_group_id` remains the stable key.
         */
        fun rbacGroupName(rbacGroupName: String?) =
            rbacGroupName(JsonField.ofNullable(rbacGroupName))

        /** Alias for calling [Builder.rbacGroupName] with `rbacGroupName.orElse(null)`. */
        fun rbacGroupName(rbacGroupName: Optional<String>) =
            rbacGroupName(rbacGroupName.getOrNull())

        /**
         * Sets [Builder.rbacGroupName] to an arbitrary JSON value.
         *
         * You should usually call [Builder.rbacGroupName] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun rbacGroupName(rbacGroupName: JsonField<String>) = apply {
            this.rbacGroupName = rbacGroupName
        }

        /**
         * Tagged user identifier (e.g. `user_...`). Present only when the request grouped by
         * `user_id`.
         */
        fun userId(userId: String?) = userId(JsonField.ofNullable(userId))

        /** Alias for calling [Builder.userId] with `userId.orElse(null)`. */
        fun userId(userId: Optional<String>) = userId(userId.getOrNull())

        /**
         * Sets [Builder.userId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.userId] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun userId(userId: JsonField<String>) = apply { this.userId = userId }

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
         * Returns an immutable instance of [BetaAnalyticsProjectActivity].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .distinctUserCount()
         * .messageCount()
         * .projectId()
         * .projectName()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaAnalyticsProjectActivity =
            BetaAnalyticsProjectActivity(
                checkRequired("distinctUserCount", distinctUserCount),
                checkRequired("messageCount", messageCount),
                checkRequired("projectId", projectId),
                checkRequired("projectName", projectName),
                createdAt,
                createdBy,
                distinctConversationCount,
                product,
                rbacGroupId,
                rbacGroupName,
                userId,
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
    fun validate(): BetaAnalyticsProjectActivity = apply {
        if (validated) {
            return@apply
        }

        distinctUserCount()
        messageCount()
        projectId()
        projectName()
        createdAt()
        createdBy().ifPresent { it.validate() }
        distinctConversationCount()
        product()
        rbacGroupId()
        rbacGroupName()
        userId()
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
        (if (distinctUserCount.asKnown().isPresent) 1 else 0) +
            (if (messageCount.asKnown().isPresent) 1 else 0) +
            (if (projectId.asKnown().isPresent) 1 else 0) +
            (if (projectName.asKnown().isPresent) 1 else 0) +
            (if (createdAt.asKnown().isPresent) 1 else 0) +
            (createdBy.asKnown().getOrNull()?.validity() ?: 0) +
            (if (distinctConversationCount.asKnown().isPresent) 1 else 0) +
            (if (product.asKnown().isPresent) 1 else 0) +
            (if (rbacGroupId.asKnown().isPresent) 1 else 0) +
            (if (rbacGroupName.asKnown().isPresent) 1 else 0) +
            (if (userId.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaAnalyticsProjectActivity &&
            distinctUserCount == other.distinctUserCount &&
            messageCount == other.messageCount &&
            projectId == other.projectId &&
            projectName == other.projectName &&
            createdAt == other.createdAt &&
            createdBy == other.createdBy &&
            distinctConversationCount == other.distinctConversationCount &&
            product == other.product &&
            rbacGroupId == other.rbacGroupId &&
            rbacGroupName == other.rbacGroupName &&
            userId == other.userId &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            distinctUserCount,
            messageCount,
            projectId,
            projectName,
            createdAt,
            createdBy,
            distinctConversationCount,
            product,
            rbacGroupId,
            rbacGroupName,
            userId,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaAnalyticsProjectActivity{distinctUserCount=$distinctUserCount, messageCount=$messageCount, projectId=$projectId, projectName=$projectName, createdAt=$createdAt, createdBy=$createdBy, distinctConversationCount=$distinctConversationCount, product=$product, rbacGroupId=$rbacGroupId, rbacGroupName=$rbacGroupName, userId=$userId, additionalProperties=$additionalProperties}"
}
