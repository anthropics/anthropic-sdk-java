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
 * Per-plugin install + invocation activity for a given day.
 *
 * With `group_by[]=user_id` / `rbac_group_id` / `product` (`cowork` / `claude_code` only on this
 * endpoint) each row is one (plugin, user), (plugin, group), or (plugin, product) cut: the flat
 * `user_id` / `rbac_group_id` / `product` keys carry the cut and the counts are scoped to it.
 */
class BetaAnalyticsPluginActivity
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val claudeCodeMetrics: JsonField<BetaAnalyticsPluginClaudeCodeMetrics>,
    private val coworkMetrics: JsonField<BetaAnalyticsPluginCoworkMetrics>,
    private val distinctUserCount: JsonField<Long>,
    private val installCount: JsonField<Long>,
    private val invocationCount: JsonField<Long>,
    private val pluginName: JsonField<String>,
    private val pluginId: JsonField<String>,
    private val product: JsonField<String>,
    private val rbacGroupId: JsonField<String>,
    private val rbacGroupName: JsonField<String>,
    private val userId: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("claude_code_metrics")
        @ExcludeMissing
        claudeCodeMetrics: JsonField<BetaAnalyticsPluginClaudeCodeMetrics> = JsonMissing.of(),
        @JsonProperty("cowork_metrics")
        @ExcludeMissing
        coworkMetrics: JsonField<BetaAnalyticsPluginCoworkMetrics> = JsonMissing.of(),
        @JsonProperty("distinct_user_count")
        @ExcludeMissing
        distinctUserCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("install_count")
        @ExcludeMissing
        installCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("invocation_count")
        @ExcludeMissing
        invocationCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("plugin_name")
        @ExcludeMissing
        pluginName: JsonField<String> = JsonMissing.of(),
        @JsonProperty("plugin_id") @ExcludeMissing pluginId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("product") @ExcludeMissing product: JsonField<String> = JsonMissing.of(),
        @JsonProperty("rbac_group_id")
        @ExcludeMissing
        rbacGroupId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("rbac_group_name")
        @ExcludeMissing
        rbacGroupName: JsonField<String> = JsonMissing.of(),
        @JsonProperty("user_id") @ExcludeMissing userId: JsonField<String> = JsonMissing.of(),
    ) : this(
        claudeCodeMetrics,
        coworkMetrics,
        distinctUserCount,
        installCount,
        invocationCount,
        pluginName,
        pluginId,
        product,
        rbacGroupId,
        rbacGroupName,
        userId,
        mutableMapOf(),
    )

    /**
     * Claude Code activity metrics for a single plugin on a given day.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun claudeCodeMetrics(): BetaAnalyticsPluginClaudeCodeMetrics =
        claudeCodeMetrics.getRequired("claude_code_metrics")

    /**
     * Cowork activity metrics for a single plugin on a given day.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun coworkMetrics(): BetaAnalyticsPluginCoworkMetrics =
        coworkMetrics.getRequired("cowork_metrics")

    /**
     * Number of distinct users with recorded install or invocation activity for the plugin on the
     * requested day (install-only users count), or, in date-range mode, over the requested window —
     * recomputed as an exact distinct count over the window's per-member daily rows, never a sum of
     * per-day values.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun distinctUserCount(): Long = distinctUserCount.getRequired("distinct_user_count")

    /**
     * Number of distinct users who installed the plugin on the requested day, or, in date-range
     * mode, over the requested window — recomputed as an exact distinct count over the window's
     * per-member daily rows, never a sum of per-day values.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun installCount(): Optional<Long> = installCount.getOptional("install_count")

    /**
     * Number of plugin invocations on the requested day
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun invocationCount(): Long = invocationCount.getRequired("invocation_count")

    /**
     * Name of the plugin
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun pluginName(): String = pluginName.getRequired("plugin_name")

    /**
     * Stable plugin identifier when available (e.g. `serena@claude-plugins-official`). Null for
     * third-party Claude Code plugins (redacted at the source) and Cowork slash commands that carry
     * only a hashed id.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun pluginId(): Optional<String> = pluginId.getOptional("plugin_id")

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
     * Returns the raw JSON value of [claudeCodeMetrics].
     *
     * Unlike [claudeCodeMetrics], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("claude_code_metrics")
    @ExcludeMissing
    fun _claudeCodeMetrics(): JsonField<BetaAnalyticsPluginClaudeCodeMetrics> = claudeCodeMetrics

    /**
     * Returns the raw JSON value of [coworkMetrics].
     *
     * Unlike [coworkMetrics], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("cowork_metrics")
    @ExcludeMissing
    fun _coworkMetrics(): JsonField<BetaAnalyticsPluginCoworkMetrics> = coworkMetrics

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
     * Returns the raw JSON value of [installCount].
     *
     * Unlike [installCount], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("install_count")
    @ExcludeMissing
    fun _installCount(): JsonField<Long> = installCount

    /**
     * Returns the raw JSON value of [invocationCount].
     *
     * Unlike [invocationCount], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("invocation_count")
    @ExcludeMissing
    fun _invocationCount(): JsonField<Long> = invocationCount

    /**
     * Returns the raw JSON value of [pluginName].
     *
     * Unlike [pluginName], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("plugin_name") @ExcludeMissing fun _pluginName(): JsonField<String> = pluginName

    /**
     * Returns the raw JSON value of [pluginId].
     *
     * Unlike [pluginId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("plugin_id") @ExcludeMissing fun _pluginId(): JsonField<String> = pluginId

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
         * Returns a mutable builder for constructing an instance of [BetaAnalyticsPluginActivity].
         *
         * The following fields are required:
         * ```java
         * .claudeCodeMetrics()
         * .coworkMetrics()
         * .distinctUserCount()
         * .installCount()
         * .invocationCount()
         * .pluginName()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaAnalyticsPluginActivity]. */
    class Builder internal constructor() {

        private var claudeCodeMetrics: JsonField<BetaAnalyticsPluginClaudeCodeMetrics>? = null
        private var coworkMetrics: JsonField<BetaAnalyticsPluginCoworkMetrics>? = null
        private var distinctUserCount: JsonField<Long>? = null
        private var installCount: JsonField<Long>? = null
        private var invocationCount: JsonField<Long>? = null
        private var pluginName: JsonField<String>? = null
        private var pluginId: JsonField<String> = JsonMissing.of()
        private var product: JsonField<String> = JsonMissing.of()
        private var rbacGroupId: JsonField<String> = JsonMissing.of()
        private var rbacGroupName: JsonField<String> = JsonMissing.of()
        private var userId: JsonField<String> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaAnalyticsPluginActivity: BetaAnalyticsPluginActivity) = apply {
            claudeCodeMetrics = betaAnalyticsPluginActivity.claudeCodeMetrics
            coworkMetrics = betaAnalyticsPluginActivity.coworkMetrics
            distinctUserCount = betaAnalyticsPluginActivity.distinctUserCount
            installCount = betaAnalyticsPluginActivity.installCount
            invocationCount = betaAnalyticsPluginActivity.invocationCount
            pluginName = betaAnalyticsPluginActivity.pluginName
            pluginId = betaAnalyticsPluginActivity.pluginId
            product = betaAnalyticsPluginActivity.product
            rbacGroupId = betaAnalyticsPluginActivity.rbacGroupId
            rbacGroupName = betaAnalyticsPluginActivity.rbacGroupName
            userId = betaAnalyticsPluginActivity.userId
            additionalProperties = betaAnalyticsPluginActivity.additionalProperties.toMutableMap()
        }

        /** Claude Code activity metrics for a single plugin on a given day. */
        fun claudeCodeMetrics(claudeCodeMetrics: BetaAnalyticsPluginClaudeCodeMetrics) =
            claudeCodeMetrics(JsonField.of(claudeCodeMetrics))

        /**
         * Sets [Builder.claudeCodeMetrics] to an arbitrary JSON value.
         *
         * You should usually call [Builder.claudeCodeMetrics] with a well-typed
         * [BetaAnalyticsPluginClaudeCodeMetrics] value instead. This method is primarily for
         * setting the field to an undocumented or not yet supported value.
         */
        fun claudeCodeMetrics(claudeCodeMetrics: JsonField<BetaAnalyticsPluginClaudeCodeMetrics>) =
            apply {
                this.claudeCodeMetrics = claudeCodeMetrics
            }

        /** Cowork activity metrics for a single plugin on a given day. */
        fun coworkMetrics(coworkMetrics: BetaAnalyticsPluginCoworkMetrics) =
            coworkMetrics(JsonField.of(coworkMetrics))

        /**
         * Sets [Builder.coworkMetrics] to an arbitrary JSON value.
         *
         * You should usually call [Builder.coworkMetrics] with a well-typed
         * [BetaAnalyticsPluginCoworkMetrics] value instead. This method is primarily for setting
         * the field to an undocumented or not yet supported value.
         */
        fun coworkMetrics(coworkMetrics: JsonField<BetaAnalyticsPluginCoworkMetrics>) = apply {
            this.coworkMetrics = coworkMetrics
        }

        /**
         * Number of distinct users with recorded install or invocation activity for the plugin on
         * the requested day (install-only users count), or, in date-range mode, over the requested
         * window — recomputed as an exact distinct count over the window's per-member daily rows,
         * never a sum of per-day values.
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

        /**
         * Number of distinct users who installed the plugin on the requested day, or, in date-range
         * mode, over the requested window — recomputed as an exact distinct count over the window's
         * per-member daily rows, never a sum of per-day values.
         */
        fun installCount(installCount: Long?) = installCount(JsonField.ofNullable(installCount))

        /**
         * Alias for [Builder.installCount].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun installCount(installCount: Long) = installCount(installCount as Long?)

        /** Alias for calling [Builder.installCount] with `installCount.orElse(null)`. */
        fun installCount(installCount: Optional<Long>) = installCount(installCount.getOrNull())

        /**
         * Sets [Builder.installCount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.installCount] with a well-typed [Long] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun installCount(installCount: JsonField<Long>) = apply { this.installCount = installCount }

        /** Number of plugin invocations on the requested day */
        fun invocationCount(invocationCount: Long) = invocationCount(JsonField.of(invocationCount))

        /**
         * Sets [Builder.invocationCount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.invocationCount] with a well-typed [Long] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun invocationCount(invocationCount: JsonField<Long>) = apply {
            this.invocationCount = invocationCount
        }

        /** Name of the plugin */
        fun pluginName(pluginName: String) = pluginName(JsonField.of(pluginName))

        /**
         * Sets [Builder.pluginName] to an arbitrary JSON value.
         *
         * You should usually call [Builder.pluginName] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun pluginName(pluginName: JsonField<String>) = apply { this.pluginName = pluginName }

        /**
         * Stable plugin identifier when available (e.g. `serena@claude-plugins-official`). Null for
         * third-party Claude Code plugins (redacted at the source) and Cowork slash commands that
         * carry only a hashed id.
         */
        fun pluginId(pluginId: String?) = pluginId(JsonField.ofNullable(pluginId))

        /** Alias for calling [Builder.pluginId] with `pluginId.orElse(null)`. */
        fun pluginId(pluginId: Optional<String>) = pluginId(pluginId.getOrNull())

        /**
         * Sets [Builder.pluginId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.pluginId] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun pluginId(pluginId: JsonField<String>) = apply { this.pluginId = pluginId }

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
         * Returns an immutable instance of [BetaAnalyticsPluginActivity].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .claudeCodeMetrics()
         * .coworkMetrics()
         * .distinctUserCount()
         * .installCount()
         * .invocationCount()
         * .pluginName()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaAnalyticsPluginActivity =
            BetaAnalyticsPluginActivity(
                checkRequired("claudeCodeMetrics", claudeCodeMetrics),
                checkRequired("coworkMetrics", coworkMetrics),
                checkRequired("distinctUserCount", distinctUserCount),
                checkRequired("installCount", installCount),
                checkRequired("invocationCount", invocationCount),
                checkRequired("pluginName", pluginName),
                pluginId,
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
    fun validate(): BetaAnalyticsPluginActivity = apply {
        if (validated) {
            return@apply
        }

        claudeCodeMetrics().validate()
        coworkMetrics().validate()
        distinctUserCount()
        installCount()
        invocationCount()
        pluginName()
        pluginId()
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
        (claudeCodeMetrics.asKnown().getOrNull()?.validity() ?: 0) +
            (coworkMetrics.asKnown().getOrNull()?.validity() ?: 0) +
            (if (distinctUserCount.asKnown().isPresent) 1 else 0) +
            (if (installCount.asKnown().isPresent) 1 else 0) +
            (if (invocationCount.asKnown().isPresent) 1 else 0) +
            (if (pluginName.asKnown().isPresent) 1 else 0) +
            (if (pluginId.asKnown().isPresent) 1 else 0) +
            (if (product.asKnown().isPresent) 1 else 0) +
            (if (rbacGroupId.asKnown().isPresent) 1 else 0) +
            (if (rbacGroupName.asKnown().isPresent) 1 else 0) +
            (if (userId.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaAnalyticsPluginActivity &&
            claudeCodeMetrics == other.claudeCodeMetrics &&
            coworkMetrics == other.coworkMetrics &&
            distinctUserCount == other.distinctUserCount &&
            installCount == other.installCount &&
            invocationCount == other.invocationCount &&
            pluginName == other.pluginName &&
            pluginId == other.pluginId &&
            product == other.product &&
            rbacGroupId == other.rbacGroupId &&
            rbacGroupName == other.rbacGroupName &&
            userId == other.userId &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            claudeCodeMetrics,
            coworkMetrics,
            distinctUserCount,
            installCount,
            invocationCount,
            pluginName,
            pluginId,
            product,
            rbacGroupId,
            rbacGroupName,
            userId,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaAnalyticsPluginActivity{claudeCodeMetrics=$claudeCodeMetrics, coworkMetrics=$coworkMetrics, distinctUserCount=$distinctUserCount, installCount=$installCount, invocationCount=$invocationCount, pluginName=$pluginName, pluginId=$pluginId, product=$product, rbacGroupId=$rbacGroupId, rbacGroupName=$rbacGroupName, userId=$userId, additionalProperties=$additionalProperties}"
}
