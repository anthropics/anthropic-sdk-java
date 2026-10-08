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
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/**
 * Artifact-creation activity for one (`artifact_type`, `is_shared`) bucket on a given day.
 *
 * Artifacts form a small finite cube — the canonical MIME type (8 values incl. `other`) crossed
 * with shared-vs-private — so the response is the full set of non-empty buckets, not a
 * ranked/paginated list. Claude Code and Cowork artifacts report under `text/html` and are counted
 * from 2026-08-17 onward; earlier days contain claude.ai chat artifacts only. With
 * `group_by[]=product` / `user_id` / `rbac_group_id` each row is further split by the flat group
 * keys and counts are scoped to that cut.
 */
class BetaAnalyticsArtifactActivity
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val artifactType: JsonField<String>,
    private val artifactsCreatedCount: JsonField<Long>,
    private val distinctUserCount: JsonField<Long>,
    private val isShared: JsonField<Boolean>,
    private val publishedArtifactsCreatedCount: JsonField<Long>,
    private val product: JsonField<String>,
    private val rbacGroupId: JsonField<String>,
    private val rbacGroupName: JsonField<String>,
    private val userId: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("artifact_type")
        @ExcludeMissing
        artifactType: JsonField<String> = JsonMissing.of(),
        @JsonProperty("artifacts_created_count")
        @ExcludeMissing
        artifactsCreatedCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("distinct_user_count")
        @ExcludeMissing
        distinctUserCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("is_shared") @ExcludeMissing isShared: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("published_artifacts_created_count")
        @ExcludeMissing
        publishedArtifactsCreatedCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("product") @ExcludeMissing product: JsonField<String> = JsonMissing.of(),
        @JsonProperty("rbac_group_id")
        @ExcludeMissing
        rbacGroupId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("rbac_group_name")
        @ExcludeMissing
        rbacGroupName: JsonField<String> = JsonMissing.of(),
        @JsonProperty("user_id") @ExcludeMissing userId: JsonField<String> = JsonMissing.of(),
    ) : this(
        artifactType,
        artifactsCreatedCount,
        distinctUserCount,
        isShared,
        publishedArtifactsCreatedCount,
        product,
        rbacGroupId,
        rbacGroupName,
        userId,
        mutableMapOf(),
    )

    /**
     * Canonical artifact MIME type (e.g. `text/markdown`, `application/vnd.ant.react`,
     * `image/svg+xml`), or `other`. Claude Code and Cowork artifacts report as `text/html`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun artifactType(): String = artifactType.getRequired("artifact_type")

    /**
     * Number of artifacts created in this bucket on the requested day
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun artifactsCreatedCount(): Long = artifactsCreatedCount.getRequired("artifacts_created_count")

    /**
     * Number of distinct users who created artifacts in this bucket on the requested day
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun distinctUserCount(): Long = distinctUserCount.getRequired("distinct_user_count")

    /**
     * Whether the artifacts in this bucket have ever been shared (a Claude Code / Cowork artifact
     * is shared once anyone beyond its creator may open it: named members, the whole organization,
     * or anyone with the link).
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun isShared(): Boolean = isShared.getRequired("is_shared")

    /**
     * Number of those artifacts that have been published (for Claude Code / Cowork artifacts: open
     * to anyone with the link); never exceeds `artifacts_created_count`
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun publishedArtifactsCreatedCount(): Long =
        publishedArtifactsCreatedCount.getRequired("published_artifacts_created_count")

    /**
     * Product that produced this row's activity: one of `chat`, `claude_code`, `cowork`,
     * `office_agent`, or `chat_cowork_unified` (Chat and Cowork unified). These are the canonical
     * Cost & Usage product names; an `office_agent` row's per-surface breakdown is in its
     * `office_metrics`. On `/plugins` only `cowork`, `claude_code` and `chat_cowork_unified` occur
     * (the only surfaces with plugin attribution); on `/artifacts` only `chat`, `claude_code`,
     * `cowork` and `chat_cowork_unified` occur (the surfaces that create artifacts);
     * `/apps/chat/projects` does not support the product dimension (a `product` entry in
     * `group_by[]` or `filter[]` there is rejected). Present only when the request grouped by
     * `product`.
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
     * Returns the raw JSON value of [artifactType].
     *
     * Unlike [artifactType], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("artifact_type")
    @ExcludeMissing
    fun _artifactType(): JsonField<String> = artifactType

    /**
     * Returns the raw JSON value of [artifactsCreatedCount].
     *
     * Unlike [artifactsCreatedCount], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("artifacts_created_count")
    @ExcludeMissing
    fun _artifactsCreatedCount(): JsonField<Long> = artifactsCreatedCount

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
     * Returns the raw JSON value of [isShared].
     *
     * Unlike [isShared], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("is_shared") @ExcludeMissing fun _isShared(): JsonField<Boolean> = isShared

    /**
     * Returns the raw JSON value of [publishedArtifactsCreatedCount].
     *
     * Unlike [publishedArtifactsCreatedCount], this method doesn't throw if the JSON field has an
     * unexpected type.
     */
    @JsonProperty("published_artifacts_created_count")
    @ExcludeMissing
    fun _publishedArtifactsCreatedCount(): JsonField<Long> = publishedArtifactsCreatedCount

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
         * Returns a mutable builder for constructing an instance of
         * [BetaAnalyticsArtifactActivity].
         *
         * The following fields are required:
         * ```java
         * .artifactType()
         * .artifactsCreatedCount()
         * .distinctUserCount()
         * .isShared()
         * .publishedArtifactsCreatedCount()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaAnalyticsArtifactActivity]. */
    class Builder internal constructor() {

        private var artifactType: JsonField<String>? = null
        private var artifactsCreatedCount: JsonField<Long>? = null
        private var distinctUserCount: JsonField<Long>? = null
        private var isShared: JsonField<Boolean>? = null
        private var publishedArtifactsCreatedCount: JsonField<Long>? = null
        private var product: JsonField<String> = JsonMissing.of()
        private var rbacGroupId: JsonField<String> = JsonMissing.of()
        private var rbacGroupName: JsonField<String> = JsonMissing.of()
        private var userId: JsonField<String> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaAnalyticsArtifactActivity: BetaAnalyticsArtifactActivity) = apply {
            artifactType = betaAnalyticsArtifactActivity.artifactType
            artifactsCreatedCount = betaAnalyticsArtifactActivity.artifactsCreatedCount
            distinctUserCount = betaAnalyticsArtifactActivity.distinctUserCount
            isShared = betaAnalyticsArtifactActivity.isShared
            publishedArtifactsCreatedCount =
                betaAnalyticsArtifactActivity.publishedArtifactsCreatedCount
            product = betaAnalyticsArtifactActivity.product
            rbacGroupId = betaAnalyticsArtifactActivity.rbacGroupId
            rbacGroupName = betaAnalyticsArtifactActivity.rbacGroupName
            userId = betaAnalyticsArtifactActivity.userId
            additionalProperties = betaAnalyticsArtifactActivity.additionalProperties.toMutableMap()
        }

        /**
         * Canonical artifact MIME type (e.g. `text/markdown`, `application/vnd.ant.react`,
         * `image/svg+xml`), or `other`. Claude Code and Cowork artifacts report as `text/html`.
         */
        fun artifactType(artifactType: String) = artifactType(JsonField.of(artifactType))

        /**
         * Sets [Builder.artifactType] to an arbitrary JSON value.
         *
         * You should usually call [Builder.artifactType] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun artifactType(artifactType: JsonField<String>) = apply {
            this.artifactType = artifactType
        }

        /** Number of artifacts created in this bucket on the requested day */
        fun artifactsCreatedCount(artifactsCreatedCount: Long) =
            artifactsCreatedCount(JsonField.of(artifactsCreatedCount))

        /**
         * Sets [Builder.artifactsCreatedCount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.artifactsCreatedCount] with a well-typed [Long] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun artifactsCreatedCount(artifactsCreatedCount: JsonField<Long>) = apply {
            this.artifactsCreatedCount = artifactsCreatedCount
        }

        /** Number of distinct users who created artifacts in this bucket on the requested day */
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

        /**
         * Whether the artifacts in this bucket have ever been shared (a Claude Code / Cowork
         * artifact is shared once anyone beyond its creator may open it: named members, the whole
         * organization, or anyone with the link).
         */
        fun isShared(isShared: Boolean) = isShared(JsonField.of(isShared))

        /**
         * Sets [Builder.isShared] to an arbitrary JSON value.
         *
         * You should usually call [Builder.isShared] with a well-typed [Boolean] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun isShared(isShared: JsonField<Boolean>) = apply { this.isShared = isShared }

        /**
         * Number of those artifacts that have been published (for Claude Code / Cowork artifacts:
         * open to anyone with the link); never exceeds `artifacts_created_count`
         */
        fun publishedArtifactsCreatedCount(publishedArtifactsCreatedCount: Long) =
            publishedArtifactsCreatedCount(JsonField.of(publishedArtifactsCreatedCount))

        /**
         * Sets [Builder.publishedArtifactsCreatedCount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.publishedArtifactsCreatedCount] with a well-typed [Long]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun publishedArtifactsCreatedCount(publishedArtifactsCreatedCount: JsonField<Long>) =
            apply {
                this.publishedArtifactsCreatedCount = publishedArtifactsCreatedCount
            }

        /**
         * Product that produced this row's activity: one of `chat`, `claude_code`, `cowork`,
         * `office_agent`, or `chat_cowork_unified` (Chat and Cowork unified). These are the
         * canonical Cost & Usage product names; an `office_agent` row's per-surface breakdown is in
         * its `office_metrics`. On `/plugins` only `cowork`, `claude_code` and
         * `chat_cowork_unified` occur (the only surfaces with plugin attribution); on `/artifacts`
         * only `chat`, `claude_code`, `cowork` and `chat_cowork_unified` occur (the surfaces that
         * create artifacts); `/apps/chat/projects` does not support the product dimension (a
         * `product` entry in `group_by[]` or `filter[]` there is rejected). Present only when the
         * request grouped by `product`.
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
         * Returns an immutable instance of [BetaAnalyticsArtifactActivity].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .artifactType()
         * .artifactsCreatedCount()
         * .distinctUserCount()
         * .isShared()
         * .publishedArtifactsCreatedCount()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaAnalyticsArtifactActivity =
            BetaAnalyticsArtifactActivity(
                checkRequired("artifactType", artifactType),
                checkRequired("artifactsCreatedCount", artifactsCreatedCount),
                checkRequired("distinctUserCount", distinctUserCount),
                checkRequired("isShared", isShared),
                checkRequired("publishedArtifactsCreatedCount", publishedArtifactsCreatedCount),
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
    fun validate(): BetaAnalyticsArtifactActivity = apply {
        if (validated) {
            return@apply
        }

        artifactType()
        artifactsCreatedCount()
        distinctUserCount()
        isShared()
        publishedArtifactsCreatedCount()
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
        (if (artifactType.asKnown().isPresent) 1 else 0) +
            (if (artifactsCreatedCount.asKnown().isPresent) 1 else 0) +
            (if (distinctUserCount.asKnown().isPresent) 1 else 0) +
            (if (isShared.asKnown().isPresent) 1 else 0) +
            (if (publishedArtifactsCreatedCount.asKnown().isPresent) 1 else 0) +
            (if (product.asKnown().isPresent) 1 else 0) +
            (if (rbacGroupId.asKnown().isPresent) 1 else 0) +
            (if (rbacGroupName.asKnown().isPresent) 1 else 0) +
            (if (userId.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaAnalyticsArtifactActivity &&
            artifactType == other.artifactType &&
            artifactsCreatedCount == other.artifactsCreatedCount &&
            distinctUserCount == other.distinctUserCount &&
            isShared == other.isShared &&
            publishedArtifactsCreatedCount == other.publishedArtifactsCreatedCount &&
            product == other.product &&
            rbacGroupId == other.rbacGroupId &&
            rbacGroupName == other.rbacGroupName &&
            userId == other.userId &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            artifactType,
            artifactsCreatedCount,
            distinctUserCount,
            isShared,
            publishedArtifactsCreatedCount,
            product,
            rbacGroupId,
            rbacGroupName,
            userId,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaAnalyticsArtifactActivity{artifactType=$artifactType, artifactsCreatedCount=$artifactsCreatedCount, distinctUserCount=$distinctUserCount, isShared=$isShared, publishedArtifactsCreatedCount=$publishedArtifactsCreatedCount, product=$product, rbacGroupId=$rbacGroupId, rbacGroupName=$rbacGroupName, userId=$userId, additionalProperties=$additionalProperties}"
}
