package com.anthropic.models.beta.organization.analytics.usercostreport

import com.anthropic.core.Enum
import com.anthropic.core.JsonField
import com.anthropic.core.Params
import com.anthropic.core.checkRequired
import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsClaudeTagCategory
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsContextWindow
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsInferenceGeoFilter
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsProductFilter
import com.fasterxml.jackson.annotation.JsonCreator
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/**
 * Get per-user cost in USD across a date range.
 *
 * Returns one row per user, ranked by spend. Use this to see which users account for the most cost.
 * Only cost attributable to a seat user is included; for organization-wide totals including direct
 * API-key and automation traffic, use the bucketed `/v1/organizations/analytics/cost_report`
 * endpoint. Available to organizations on a Claude Enterprise plan. Requires an API key with the
 * `read:analytics` scope.
 */
class UserCostReportListParams
private constructor(
    private val startingAt: OffsetDateTime,
    private val bucketWidth: BucketWidth?,
    private val claudeTagCategories: List<BetaAnalyticsClaudeTagCategory>?,
    private val claudeTagUserIds: List<String>?,
    private val contextWindows: List<BetaAnalyticsContextWindow>?,
    private val endingAt: OffsetDateTime?,
    private val excludeDeletedUsers: Boolean?,
    private val groupBy: List<GroupBy>?,
    private val inferenceGeos: List<BetaAnalyticsInferenceGeoFilter>?,
    private val limit: Long?,
    private val models: List<String>?,
    private val order: Order?,
    private val orderBy: OrderBy?,
    private val page: String?,
    private val products: List<BetaAnalyticsProductFilter>?,
    private val rbacGroupIds: List<String>?,
    private val slackChannelIds: List<String>?,
    private val speeds: List<Speed>?,
    private val userIds: List<String>?,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    /**
     * Start of range, inclusive. RFC 3339 tz-aware. Must be within the last 365 days and no earlier
     * than 2026-01-01T00:00:00Z.
     */
    fun startingAt(): OffsetDateTime = startingAt

    /**
     * Time-bucket granularity. When set, each row's `starting_at` and `ending_at` are populated and
     * one actor may span several rows (one per time bucket with usage). The time bucket counts
     * toward `limit`, so one page can return multiple rows for the same actor. `ending_at` is
     * required when `bucket_width` is set, and with `bucket_width="1m"` the range may span at most
     * 24 hours. When omitted, each row aggregates the full `[starting_at, ending_at)` range.
     */
    fun bucketWidth(): Optional<BucketWidth> = Optional.ofNullable(bucketWidth)

    /**
     * Filter to Claude Tag (Claude in Slack) usage in specific spend categories. Usage with no
     * category never matches. `dm` usage is reported under the user's product rather than
     * `claude-tag`, so combining this filter with `products[]=claude-tag` excludes it. Use
     * `group_by[]=claude_tag_category` to break out per-category values.
     */
    fun claudeTagCategories(): Optional<List<BetaAnalyticsClaudeTagCategory>> =
        Optional.ofNullable(claudeTagCategories)

    /**
     * Filter to Claude Tag (Claude in Slack) usage attributed to specific Slack users, by Slack
     * user ID (for example `U0123ABCDEF`), not claude.ai user ID. Usage that is not Claude Tag, and
     * Claude Tag usage not attributed to a single user, never matches. Use
     * `group_by[]=claude_tag_user_id` to break out per-user values.
     */
    fun claudeTagUserIds(): Optional<List<String>> = Optional.ofNullable(claudeTagUserIds)

    /**
     * Filter to specific context-window pricing tiers. Use `group_by[]=context_window` to break out
     * per-tier values.
     */
    fun contextWindows(): Optional<List<BetaAnalyticsContextWindow>> =
        Optional.ofNullable(contextWindows)

    /**
     * End of range, exclusive. When omitted, defaults to the earlier of now and `starting_at` + 31
     * days. The range may span at most 31 days.
     */
    fun endingAt(): Optional<OffsetDateTime> = Optional.ofNullable(endingAt)

    /**
     * If true, omit rows for users who are deleted (`deleted: true`). A page may contain fewer than
     * `limit` rows; use `has_more` and `next_page` to paginate as usual.
     */
    fun excludeDeletedUsers(): Optional<Boolean> = Optional.ofNullable(excludeDeletedUsers)

    /**
     * Break each actor's row out by the given dimensions. Accepts the same values as the bucketed
     * `/cost_report` endpoint. The `product`, `model`, `context_window`, `inference_geo`, and
     * `speed` dimensions — and the time bucket, when `bucket_width` is set — count toward `limit`.
     * `cost_type` and `token_type` do not: `cost_type` returns one row per cost component (tokens,
     * web search, code execution); `token_type` returns one row per token type, each with
     * `cost_type: "tokens"`; combining both returns the per-token-type rows plus the web-search and
     * code-execution rows. A page can therefore contain more rows than `limit` when `cost_type` or
     * `token_type` is requested.
     */
    fun groupBy(): Optional<List<GroupBy>> = Optional.ofNullable(groupBy)

    /**
     * Filter to specific inference regions. `not_available` matches rows where the region is unset.
     * Use `group_by[]=inference_geo` to break out per-region values.
     */
    fun inferenceGeos(): Optional<List<BetaAnalyticsInferenceGeoFilter>> =
        Optional.ofNullable(inferenceGeos)

    /**
     * Number of rows per page (1-1000, default 20). One row per actor unless `group_by[]` or
     * `bucket_width` splits an actor across rows; `cost_type`/`token_type` fan-out rows (cost
     * endpoint only) are the exception — they do not count toward this limit, so `data` can exceed
     * it.
     */
    fun limit(): Optional<Long> = Optional.ofNullable(limit)

    /**
     * Models to include. Defaults to all models. Use `group_by[]=model` to break out per-model
     * values.
     */
    fun models(): Optional<List<String>> = Optional.ofNullable(models)

    /** Sort direction. Defaults to `desc`. */
    fun order(): Optional<Order> = Optional.ofNullable(order)

    /** Metric to rank actors by. Defaults to `amount`. */
    fun orderBy(): Optional<OrderBy> = Optional.ofNullable(orderBy)

    /** Opaque cursor from a previous response's `next_page` field. */
    fun page(): Optional<String> = Optional.ofNullable(page)

    /** Product surfaces to include. Defaults to all products. */
    fun products(): Optional<List<BetaAnalyticsProductFilter>> = Optional.ofNullable(products)

    /**
     * Filter to usage attributed to specific RBAC groups. Accepts tagged RBAC group IDs
     * (`rbac_group_...`) or bare group UUIDs. A row matches when the user belonged to any of the
     * listed groups on the (UTC) day the usage occurred; usage with no group attribution never
     * matches.
     */
    fun rbacGroupIds(): Optional<List<String>> = Optional.ofNullable(rbacGroupIds)

    /**
     * Filter to usage originating from specific Slack channels. Use `group_by[]=slack_channel_id`
     * to break out per-channel values.
     */
    fun slackChannelIds(): Optional<List<String>> = Optional.ofNullable(slackChannelIds)

    /**
     * Filter to fast or standard inference mode. Use `group_by[]=speed` to break out per-mode
     * values.
     */
    fun speeds(): Optional<List<Speed>> = Optional.ofNullable(speeds)

    /** Filter to specific users by tagged user ID. */
    fun userIds(): Optional<List<String>> = Optional.ofNullable(userIds)

    /** Additional headers to send with the request. */
    fun _additionalHeaders(): Headers = additionalHeaders

    /** Additional query param to send with the request. */
    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [UserCostReportListParams].
         *
         * The following fields are required:
         * ```java
         * .startingAt()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [UserCostReportListParams]. */
    class Builder internal constructor() {

        private var startingAt: OffsetDateTime? = null
        private var bucketWidth: BucketWidth? = null
        private var claudeTagCategories: MutableList<BetaAnalyticsClaudeTagCategory>? = null
        private var claudeTagUserIds: MutableList<String>? = null
        private var contextWindows: MutableList<BetaAnalyticsContextWindow>? = null
        private var endingAt: OffsetDateTime? = null
        private var excludeDeletedUsers: Boolean? = null
        private var groupBy: MutableList<GroupBy>? = null
        private var inferenceGeos: MutableList<BetaAnalyticsInferenceGeoFilter>? = null
        private var limit: Long? = null
        private var models: MutableList<String>? = null
        private var order: Order? = null
        private var orderBy: OrderBy? = null
        private var page: String? = null
        private var products: MutableList<BetaAnalyticsProductFilter>? = null
        private var rbacGroupIds: MutableList<String>? = null
        private var slackChannelIds: MutableList<String>? = null
        private var speeds: MutableList<Speed>? = null
        private var userIds: MutableList<String>? = null
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(userCostReportListParams: UserCostReportListParams) = apply {
            startingAt = userCostReportListParams.startingAt
            bucketWidth = userCostReportListParams.bucketWidth
            claudeTagCategories = userCostReportListParams.claudeTagCategories?.toMutableList()
            claudeTagUserIds = userCostReportListParams.claudeTagUserIds?.toMutableList()
            contextWindows = userCostReportListParams.contextWindows?.toMutableList()
            endingAt = userCostReportListParams.endingAt
            excludeDeletedUsers = userCostReportListParams.excludeDeletedUsers
            groupBy = userCostReportListParams.groupBy?.toMutableList()
            inferenceGeos = userCostReportListParams.inferenceGeos?.toMutableList()
            limit = userCostReportListParams.limit
            models = userCostReportListParams.models?.toMutableList()
            order = userCostReportListParams.order
            orderBy = userCostReportListParams.orderBy
            page = userCostReportListParams.page
            products = userCostReportListParams.products?.toMutableList()
            rbacGroupIds = userCostReportListParams.rbacGroupIds?.toMutableList()
            slackChannelIds = userCostReportListParams.slackChannelIds?.toMutableList()
            speeds = userCostReportListParams.speeds?.toMutableList()
            userIds = userCostReportListParams.userIds?.toMutableList()
            additionalHeaders = userCostReportListParams.additionalHeaders.toBuilder()
            additionalQueryParams = userCostReportListParams.additionalQueryParams.toBuilder()
        }

        /**
         * Start of range, inclusive. RFC 3339 tz-aware. Must be within the last 365 days and no
         * earlier than 2026-01-01T00:00:00Z.
         */
        fun startingAt(startingAt: OffsetDateTime) = apply { this.startingAt = startingAt }

        /**
         * Time-bucket granularity. When set, each row's `starting_at` and `ending_at` are populated
         * and one actor may span several rows (one per time bucket with usage). The time bucket
         * counts toward `limit`, so one page can return multiple rows for the same actor.
         * `ending_at` is required when `bucket_width` is set, and with `bucket_width="1m"` the
         * range may span at most 24 hours. When omitted, each row aggregates the full
         * `[starting_at, ending_at)` range.
         */
        fun bucketWidth(bucketWidth: BucketWidth?) = apply { this.bucketWidth = bucketWidth }

        /** Alias for calling [Builder.bucketWidth] with `bucketWidth.orElse(null)`. */
        fun bucketWidth(bucketWidth: Optional<BucketWidth>) = bucketWidth(bucketWidth.getOrNull())

        /**
         * Filter to Claude Tag (Claude in Slack) usage in specific spend categories. Usage with no
         * category never matches. `dm` usage is reported under the user's product rather than
         * `claude-tag`, so combining this filter with `products[]=claude-tag` excludes it. Use
         * `group_by[]=claude_tag_category` to break out per-category values.
         */
        fun claudeTagCategories(claudeTagCategories: List<BetaAnalyticsClaudeTagCategory>?) =
            apply {
                this.claudeTagCategories = claudeTagCategories?.toMutableList()
            }

        /**
         * Alias for calling [Builder.claudeTagCategories] with `claudeTagCategories.orElse(null)`.
         */
        fun claudeTagCategories(
            claudeTagCategories: Optional<List<BetaAnalyticsClaudeTagCategory>>
        ) = claudeTagCategories(claudeTagCategories.getOrNull())

        /**
         * Adds a single [BetaAnalyticsClaudeTagCategory] to [claudeTagCategories].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addClaudeTagCategory(claudeTagCategory: BetaAnalyticsClaudeTagCategory) = apply {
            claudeTagCategories =
                (claudeTagCategories ?: mutableListOf()).apply { add(claudeTagCategory) }
        }

        /**
         * Filter to Claude Tag (Claude in Slack) usage attributed to specific Slack users, by Slack
         * user ID (for example `U0123ABCDEF`), not claude.ai user ID. Usage that is not Claude Tag,
         * and Claude Tag usage not attributed to a single user, never matches. Use
         * `group_by[]=claude_tag_user_id` to break out per-user values.
         */
        fun claudeTagUserIds(claudeTagUserIds: List<String>?) = apply {
            this.claudeTagUserIds = claudeTagUserIds?.toMutableList()
        }

        /** Alias for calling [Builder.claudeTagUserIds] with `claudeTagUserIds.orElse(null)`. */
        fun claudeTagUserIds(claudeTagUserIds: Optional<List<String>>) =
            claudeTagUserIds(claudeTagUserIds.getOrNull())

        /**
         * Adds a single [String] to [claudeTagUserIds].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addClaudeTagUserId(claudeTagUserId: String) = apply {
            claudeTagUserIds = (claudeTagUserIds ?: mutableListOf()).apply { add(claudeTagUserId) }
        }

        /**
         * Filter to specific context-window pricing tiers. Use `group_by[]=context_window` to break
         * out per-tier values.
         */
        fun contextWindows(contextWindows: List<BetaAnalyticsContextWindow>?) = apply {
            this.contextWindows = contextWindows?.toMutableList()
        }

        /** Alias for calling [Builder.contextWindows] with `contextWindows.orElse(null)`. */
        fun contextWindows(contextWindows: Optional<List<BetaAnalyticsContextWindow>>) =
            contextWindows(contextWindows.getOrNull())

        /**
         * Adds a single [BetaAnalyticsContextWindow] to [contextWindows].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addContextWindow(contextWindow: BetaAnalyticsContextWindow) = apply {
            contextWindows = (contextWindows ?: mutableListOf()).apply { add(contextWindow) }
        }

        /**
         * End of range, exclusive. When omitted, defaults to the earlier of now and `starting_at` +
         * 31 days. The range may span at most 31 days.
         */
        fun endingAt(endingAt: OffsetDateTime?) = apply { this.endingAt = endingAt }

        /** Alias for calling [Builder.endingAt] with `endingAt.orElse(null)`. */
        fun endingAt(endingAt: Optional<OffsetDateTime>) = endingAt(endingAt.getOrNull())

        /**
         * If true, omit rows for users who are deleted (`deleted: true`). A page may contain fewer
         * than `limit` rows; use `has_more` and `next_page` to paginate as usual.
         */
        fun excludeDeletedUsers(excludeDeletedUsers: Boolean?) = apply {
            this.excludeDeletedUsers = excludeDeletedUsers
        }

        /**
         * Alias for [Builder.excludeDeletedUsers].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun excludeDeletedUsers(excludeDeletedUsers: Boolean) =
            excludeDeletedUsers(excludeDeletedUsers as Boolean?)

        /**
         * Alias for calling [Builder.excludeDeletedUsers] with `excludeDeletedUsers.orElse(null)`.
         */
        fun excludeDeletedUsers(excludeDeletedUsers: Optional<Boolean>) =
            excludeDeletedUsers(excludeDeletedUsers.getOrNull())

        /**
         * Break each actor's row out by the given dimensions. Accepts the same values as the
         * bucketed `/cost_report` endpoint. The `product`, `model`, `context_window`,
         * `inference_geo`, and `speed` dimensions — and the time bucket, when `bucket_width` is set
         * — count toward `limit`. `cost_type` and `token_type` do not: `cost_type` returns one row
         * per cost component (tokens, web search, code execution); `token_type` returns one row per
         * token type, each with `cost_type: "tokens"`; combining both returns the per-token-type
         * rows plus the web-search and code-execution rows. A page can therefore contain more rows
         * than `limit` when `cost_type` or `token_type` is requested.
         */
        fun groupBy(groupBy: List<GroupBy>?) = apply { this.groupBy = groupBy?.toMutableList() }

        /** Alias for calling [Builder.groupBy] with `groupBy.orElse(null)`. */
        fun groupBy(groupBy: Optional<List<GroupBy>>) = groupBy(groupBy.getOrNull())

        /**
         * Adds a single [GroupBy] to [Builder.groupBy].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addGroupBy(groupBy: GroupBy) = apply {
            this.groupBy = (this.groupBy ?: mutableListOf()).apply { add(groupBy) }
        }

        /**
         * Filter to specific inference regions. `not_available` matches rows where the region is
         * unset. Use `group_by[]=inference_geo` to break out per-region values.
         */
        fun inferenceGeos(inferenceGeos: List<BetaAnalyticsInferenceGeoFilter>?) = apply {
            this.inferenceGeos = inferenceGeos?.toMutableList()
        }

        /** Alias for calling [Builder.inferenceGeos] with `inferenceGeos.orElse(null)`. */
        fun inferenceGeos(inferenceGeos: Optional<List<BetaAnalyticsInferenceGeoFilter>>) =
            inferenceGeos(inferenceGeos.getOrNull())

        /**
         * Adds a single [BetaAnalyticsInferenceGeoFilter] to [inferenceGeos].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addInferenceGeo(inferenceGeo: BetaAnalyticsInferenceGeoFilter) = apply {
            inferenceGeos = (inferenceGeos ?: mutableListOf()).apply { add(inferenceGeo) }
        }

        /**
         * Number of rows per page (1-1000, default 20). One row per actor unless `group_by[]` or
         * `bucket_width` splits an actor across rows; `cost_type`/`token_type` fan-out rows (cost
         * endpoint only) are the exception — they do not count toward this limit, so `data` can
         * exceed it.
         */
        fun limit(limit: Long?) = apply { this.limit = limit }

        /**
         * Alias for [Builder.limit].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun limit(limit: Long) = limit(limit as Long?)

        /** Alias for calling [Builder.limit] with `limit.orElse(null)`. */
        fun limit(limit: Optional<Long>) = limit(limit.getOrNull())

        /**
         * Models to include. Defaults to all models. Use `group_by[]=model` to break out per-model
         * values.
         */
        fun models(models: List<String>?) = apply { this.models = models?.toMutableList() }

        /** Alias for calling [Builder.models] with `models.orElse(null)`. */
        fun models(models: Optional<List<String>>) = models(models.getOrNull())

        /**
         * Adds a single [String] to [models].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addModel(model: String) = apply {
            models = (models ?: mutableListOf()).apply { add(model) }
        }

        /** Sort direction. Defaults to `desc`. */
        fun order(order: Order?) = apply { this.order = order }

        /** Alias for calling [Builder.order] with `order.orElse(null)`. */
        fun order(order: Optional<Order>) = order(order.getOrNull())

        /** Metric to rank actors by. Defaults to `amount`. */
        fun orderBy(orderBy: OrderBy?) = apply { this.orderBy = orderBy }

        /** Alias for calling [Builder.orderBy] with `orderBy.orElse(null)`. */
        fun orderBy(orderBy: Optional<OrderBy>) = orderBy(orderBy.getOrNull())

        /** Opaque cursor from a previous response's `next_page` field. */
        fun page(page: String?) = apply { this.page = page }

        /** Alias for calling [Builder.page] with `page.orElse(null)`. */
        fun page(page: Optional<String>) = page(page.getOrNull())

        /** Product surfaces to include. Defaults to all products. */
        fun products(products: List<BetaAnalyticsProductFilter>?) = apply {
            this.products = products?.toMutableList()
        }

        /** Alias for calling [Builder.products] with `products.orElse(null)`. */
        fun products(products: Optional<List<BetaAnalyticsProductFilter>>) =
            products(products.getOrNull())

        /**
         * Adds a single [BetaAnalyticsProductFilter] to [products].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addProduct(product: BetaAnalyticsProductFilter) = apply {
            products = (products ?: mutableListOf()).apply { add(product) }
        }

        /**
         * Filter to usage attributed to specific RBAC groups. Accepts tagged RBAC group IDs
         * (`rbac_group_...`) or bare group UUIDs. A row matches when the user belonged to any of
         * the listed groups on the (UTC) day the usage occurred; usage with no group attribution
         * never matches.
         */
        fun rbacGroupIds(rbacGroupIds: List<String>?) = apply {
            this.rbacGroupIds = rbacGroupIds?.toMutableList()
        }

        /** Alias for calling [Builder.rbacGroupIds] with `rbacGroupIds.orElse(null)`. */
        fun rbacGroupIds(rbacGroupIds: Optional<List<String>>) =
            rbacGroupIds(rbacGroupIds.getOrNull())

        /**
         * Adds a single [String] to [rbacGroupIds].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addRbacGroupId(rbacGroupId: String) = apply {
            rbacGroupIds = (rbacGroupIds ?: mutableListOf()).apply { add(rbacGroupId) }
        }

        /**
         * Filter to usage originating from specific Slack channels. Use
         * `group_by[]=slack_channel_id` to break out per-channel values.
         */
        fun slackChannelIds(slackChannelIds: List<String>?) = apply {
            this.slackChannelIds = slackChannelIds?.toMutableList()
        }

        /** Alias for calling [Builder.slackChannelIds] with `slackChannelIds.orElse(null)`. */
        fun slackChannelIds(slackChannelIds: Optional<List<String>>) =
            slackChannelIds(slackChannelIds.getOrNull())

        /**
         * Adds a single [String] to [slackChannelIds].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addSlackChannelId(slackChannelId: String) = apply {
            slackChannelIds = (slackChannelIds ?: mutableListOf()).apply { add(slackChannelId) }
        }

        /**
         * Filter to fast or standard inference mode. Use `group_by[]=speed` to break out per-mode
         * values.
         */
        fun speeds(speeds: List<Speed>?) = apply { this.speeds = speeds?.toMutableList() }

        /** Alias for calling [Builder.speeds] with `speeds.orElse(null)`. */
        fun speeds(speeds: Optional<List<Speed>>) = speeds(speeds.getOrNull())

        /**
         * Adds a single [Speed] to [speeds].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addSpeed(speed: Speed) = apply {
            speeds = (speeds ?: mutableListOf()).apply { add(speed) }
        }

        /** Filter to specific users by tagged user ID. */
        fun userIds(userIds: List<String>?) = apply { this.userIds = userIds?.toMutableList() }

        /** Alias for calling [Builder.userIds] with `userIds.orElse(null)`. */
        fun userIds(userIds: Optional<List<String>>) = userIds(userIds.getOrNull())

        /**
         * Adds a single [String] to [userIds].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addUserId(userId: String) = apply {
            userIds = (userIds ?: mutableListOf()).apply { add(userId) }
        }

        fun additionalHeaders(additionalHeaders: Headers) = apply {
            this.additionalHeaders.clear()
            putAllAdditionalHeaders(additionalHeaders)
        }

        fun additionalHeaders(additionalHeaders: Map<String, Iterable<String>>) = apply {
            this.additionalHeaders.clear()
            putAllAdditionalHeaders(additionalHeaders)
        }

        fun putAdditionalHeader(name: String, value: String) = apply {
            additionalHeaders.put(name, value)
        }

        fun putAdditionalHeaders(name: String, values: Iterable<String>) = apply {
            additionalHeaders.put(name, values)
        }

        fun putAllAdditionalHeaders(additionalHeaders: Headers) = apply {
            this.additionalHeaders.putAll(additionalHeaders)
        }

        fun putAllAdditionalHeaders(additionalHeaders: Map<String, Iterable<String>>) = apply {
            this.additionalHeaders.putAll(additionalHeaders)
        }

        fun replaceAdditionalHeaders(name: String, value: String) = apply {
            additionalHeaders.replace(name, value)
        }

        fun replaceAdditionalHeaders(name: String, values: Iterable<String>) = apply {
            additionalHeaders.replace(name, values)
        }

        fun replaceAllAdditionalHeaders(additionalHeaders: Headers) = apply {
            this.additionalHeaders.replaceAll(additionalHeaders)
        }

        fun replaceAllAdditionalHeaders(additionalHeaders: Map<String, Iterable<String>>) = apply {
            this.additionalHeaders.replaceAll(additionalHeaders)
        }

        fun removeAdditionalHeaders(name: String) = apply { additionalHeaders.remove(name) }

        fun removeAllAdditionalHeaders(names: Set<String>) = apply {
            additionalHeaders.removeAll(names)
        }

        fun additionalQueryParams(additionalQueryParams: QueryParams) = apply {
            this.additionalQueryParams.clear()
            putAllAdditionalQueryParams(additionalQueryParams)
        }

        fun additionalQueryParams(additionalQueryParams: Map<String, Iterable<String>>) = apply {
            this.additionalQueryParams.clear()
            putAllAdditionalQueryParams(additionalQueryParams)
        }

        fun putAdditionalQueryParam(key: String, value: String) = apply {
            additionalQueryParams.put(key, value)
        }

        fun putAdditionalQueryParams(key: String, values: Iterable<String>) = apply {
            additionalQueryParams.put(key, values)
        }

        fun putAllAdditionalQueryParams(additionalQueryParams: QueryParams) = apply {
            this.additionalQueryParams.putAll(additionalQueryParams)
        }

        fun putAllAdditionalQueryParams(additionalQueryParams: Map<String, Iterable<String>>) =
            apply {
                this.additionalQueryParams.putAll(additionalQueryParams)
            }

        fun replaceAdditionalQueryParams(key: String, value: String) = apply {
            additionalQueryParams.replace(key, value)
        }

        fun replaceAdditionalQueryParams(key: String, values: Iterable<String>) = apply {
            additionalQueryParams.replace(key, values)
        }

        fun replaceAllAdditionalQueryParams(additionalQueryParams: QueryParams) = apply {
            this.additionalQueryParams.replaceAll(additionalQueryParams)
        }

        fun replaceAllAdditionalQueryParams(additionalQueryParams: Map<String, Iterable<String>>) =
            apply {
                this.additionalQueryParams.replaceAll(additionalQueryParams)
            }

        fun removeAdditionalQueryParams(key: String) = apply { additionalQueryParams.remove(key) }

        fun removeAllAdditionalQueryParams(keys: Set<String>) = apply {
            additionalQueryParams.removeAll(keys)
        }

        /**
         * Returns an immutable instance of [UserCostReportListParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .startingAt()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): UserCostReportListParams =
            UserCostReportListParams(
                checkRequired("startingAt", startingAt),
                bucketWidth,
                claudeTagCategories?.toImmutable(),
                claudeTagUserIds?.toImmutable(),
                contextWindows?.toImmutable(),
                endingAt,
                excludeDeletedUsers,
                groupBy?.toImmutable(),
                inferenceGeos?.toImmutable(),
                limit,
                models?.toImmutable(),
                order,
                orderBy,
                page,
                products?.toImmutable(),
                rbacGroupIds?.toImmutable(),
                slackChannelIds?.toImmutable(),
                speeds?.toImmutable(),
                userIds?.toImmutable(),
                additionalHeaders.build(),
                additionalQueryParams.build(),
            )
    }

    override fun _headers(): Headers = additionalHeaders

    override fun _queryParams(): QueryParams =
        QueryParams.builder()
            .apply {
                put("starting_at", DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(startingAt))
                bucketWidth?.let { put("bucket_width", it.toString()) }
                claudeTagCategories?.forEach { put("claude_tag_categories[]", it.toString()) }
                claudeTagUserIds?.forEach { put("claude_tag_user_ids[]", it) }
                contextWindows?.forEach { put("context_windows[]", it.toString()) }
                endingAt?.let {
                    put("ending_at", DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(it))
                }
                excludeDeletedUsers?.let { put("exclude_deleted_users", it.toString()) }
                groupBy?.forEach { put("group_by[]", it.toString()) }
                inferenceGeos?.forEach { put("inference_geos[]", it.toString()) }
                limit?.let { put("limit", it.toString()) }
                models?.forEach { put("models[]", it) }
                order?.let { put("order", it.toString()) }
                orderBy?.let { put("order_by", it.toString()) }
                page?.let { put("page", it) }
                products?.forEach { put("products[]", it.toString()) }
                rbacGroupIds?.forEach { put("rbac_group_ids[]", it) }
                slackChannelIds?.forEach { put("slack_channel_ids[]", it) }
                speeds?.forEach { put("speeds[]", it.toString()) }
                userIds?.forEach { put("user_ids[]", it) }
                putAll(additionalQueryParams)
            }
            .build()

    /**
     * Time-bucket granularity. When set, each row's `starting_at` and `ending_at` are populated and
     * one actor may span several rows (one per time bucket with usage). The time bucket counts
     * toward `limit`, so one page can return multiple rows for the same actor. `ending_at` is
     * required when `bucket_width` is set, and with `bucket_width="1m"` the range may span at most
     * 24 hours. When omitted, each row aggregates the full `[starting_at, ending_at)` range.
     */
    class BucketWidth private constructor(private val value: JsonField<String>) : Enum {

        /**
         * Returns this class instance's raw value.
         *
         * This is usually only useful if this instance was deserialized from data that doesn't
         * match any known member, and you want to know that value. For example, if the SDK is on an
         * older version than the API, then the API may respond with new members that the SDK is
         * unaware of.
         */
        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            @JvmField val DAY = BucketWidth(JsonField.of("1d"))

            @JvmField val HOUR = BucketWidth(JsonField.of("1h"))

            @JvmField val MINUTE = BucketWidth(JsonField.of("1m"))

            @JvmStatic
            fun of(value: String): BucketWidth =
                // Intern known values so `==` works
                when (value) {
                    "1d" -> DAY
                    "1h" -> HOUR
                    "1m" -> MINUTE
                    else -> BucketWidth(JsonField.of(value))
                }

            @JsonCreator
            @JvmStatic
            fun of(value: JsonField<String>): BucketWidth =
                value.asString().getOrNull()?.let { of(it) } ?: BucketWidth(value)
        }

        /** An enum containing [BucketWidth]'s known values. */
        enum class Known {
            DAY,
            HOUR,
            MINUTE,
        }

        /**
         * An enum containing [BucketWidth]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [BucketWidth] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            DAY,
            HOUR,
            MINUTE,
            /**
             * An enum member indicating that [BucketWidth] was instantiated with an unknown value.
             */
            _UNKNOWN,
        }

        /**
         * Returns an enum member corresponding to this class instance's value, or [Value._UNKNOWN]
         * if the class was instantiated with an unknown value.
         *
         * Use the [known] method instead if you're certain the value is always known or if you want
         * to throw for the unknown case.
         */
        fun value(): Value =
            when (this) {
                DAY -> Value.DAY
                HOUR -> Value.HOUR
                MINUTE -> Value.MINUTE
                else -> Value._UNKNOWN
            }

        /**
         * Returns an enum member corresponding to this class instance's value.
         *
         * Use the [value] method instead if you're uncertain the value is always known and don't
         * want to throw for the unknown case.
         *
         * @throws AnthropicInvalidDataException if this class instance's value is a not a known
         *   member.
         */
        fun known(): Known =
            when (this) {
                DAY -> Known.DAY
                HOUR -> Known.HOUR
                MINUTE -> Known.MINUTE
                else -> throw AnthropicInvalidDataException("Unknown BucketWidth: $value")
            }

        /**
         * Returns this class instance's primitive wire representation.
         *
         * This differs from the [toString] method because that method is primarily for debugging
         * and generally doesn't throw.
         *
         * @throws AnthropicInvalidDataException if this class instance's value does not have the
         *   expected primitive type.
         */
        fun asString(): String =
            _value().asString().orElseThrow {
                AnthropicInvalidDataException("Value is not a String")
            }

        private var validated: Boolean = false

        /**
         * Validates that the types of all values in this object match their expected types
         * recursively.
         *
         * This method is _not_ forwards compatible with new types from the API for existing fields.
         *
         * @throws AnthropicInvalidDataException if any value type in this object doesn't match its
         *   expected type.
         */
        fun validate(): BucketWidth = apply {
            if (validated) {
                return@apply
            }

            known()
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
         * Returns a score indicating how many valid values are contained in this object
         * recursively.
         *
         * Used for best match union deserialization.
         */
        @JvmSynthetic internal fun validity(): Int = if (value() == Value._UNKNOWN) 0 else 1

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is BucketWidth && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    class GroupBy private constructor(private val value: JsonField<String>) : Enum {

        /**
         * Returns this class instance's raw value.
         *
         * This is usually only useful if this instance was deserialized from data that doesn't
         * match any known member, and you want to know that value. For example, if the SDK is on an
         * older version than the API, then the API may respond with new members that the SDK is
         * unaware of.
         */
        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            @JvmField val CLAUDE_TAG_CATEGORY = GroupBy(JsonField.of("claude_tag_category"))

            @JvmField val CLAUDE_TAG_USER_ID = GroupBy(JsonField.of("claude_tag_user_id"))

            @JvmField val CONTEXT_WINDOW = GroupBy(JsonField.of("context_window"))

            @JvmField val COST_TYPE = GroupBy(JsonField.of("cost_type"))

            @JvmField val INFERENCE_GEO = GroupBy(JsonField.of("inference_geo"))

            @JvmField val MODEL = GroupBy(JsonField.of("model"))

            @JvmField val PRODUCT = GroupBy(JsonField.of("product"))

            @JvmField val RBAC_GROUP_ID = GroupBy(JsonField.of("rbac_group_id"))

            @JvmField val SLACK_CHANNEL_ID = GroupBy(JsonField.of("slack_channel_id"))

            @JvmField val SPEED = GroupBy(JsonField.of("speed"))

            @JvmField val TOKEN_TYPE = GroupBy(JsonField.of("token_type"))

            @JvmStatic
            fun of(value: String): GroupBy =
                // Intern known values so `==` works
                when (value) {
                    "claude_tag_category" -> CLAUDE_TAG_CATEGORY
                    "claude_tag_user_id" -> CLAUDE_TAG_USER_ID
                    "context_window" -> CONTEXT_WINDOW
                    "cost_type" -> COST_TYPE
                    "inference_geo" -> INFERENCE_GEO
                    "model" -> MODEL
                    "product" -> PRODUCT
                    "rbac_group_id" -> RBAC_GROUP_ID
                    "slack_channel_id" -> SLACK_CHANNEL_ID
                    "speed" -> SPEED
                    "token_type" -> TOKEN_TYPE
                    else -> GroupBy(JsonField.of(value))
                }

            @JsonCreator
            @JvmStatic
            fun of(value: JsonField<String>): GroupBy =
                value.asString().getOrNull()?.let { of(it) } ?: GroupBy(value)
        }

        /** An enum containing [GroupBy]'s known values. */
        enum class Known {
            CLAUDE_TAG_CATEGORY,
            CLAUDE_TAG_USER_ID,
            CONTEXT_WINDOW,
            COST_TYPE,
            INFERENCE_GEO,
            MODEL,
            PRODUCT,
            RBAC_GROUP_ID,
            SLACK_CHANNEL_ID,
            SPEED,
            TOKEN_TYPE,
        }

        /**
         * An enum containing [GroupBy]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [GroupBy] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            CLAUDE_TAG_CATEGORY,
            CLAUDE_TAG_USER_ID,
            CONTEXT_WINDOW,
            COST_TYPE,
            INFERENCE_GEO,
            MODEL,
            PRODUCT,
            RBAC_GROUP_ID,
            SLACK_CHANNEL_ID,
            SPEED,
            TOKEN_TYPE,
            /** An enum member indicating that [GroupBy] was instantiated with an unknown value. */
            _UNKNOWN,
        }

        /**
         * Returns an enum member corresponding to this class instance's value, or [Value._UNKNOWN]
         * if the class was instantiated with an unknown value.
         *
         * Use the [known] method instead if you're certain the value is always known or if you want
         * to throw for the unknown case.
         */
        fun value(): Value =
            when (this) {
                CLAUDE_TAG_CATEGORY -> Value.CLAUDE_TAG_CATEGORY
                CLAUDE_TAG_USER_ID -> Value.CLAUDE_TAG_USER_ID
                CONTEXT_WINDOW -> Value.CONTEXT_WINDOW
                COST_TYPE -> Value.COST_TYPE
                INFERENCE_GEO -> Value.INFERENCE_GEO
                MODEL -> Value.MODEL
                PRODUCT -> Value.PRODUCT
                RBAC_GROUP_ID -> Value.RBAC_GROUP_ID
                SLACK_CHANNEL_ID -> Value.SLACK_CHANNEL_ID
                SPEED -> Value.SPEED
                TOKEN_TYPE -> Value.TOKEN_TYPE
                else -> Value._UNKNOWN
            }

        /**
         * Returns an enum member corresponding to this class instance's value.
         *
         * Use the [value] method instead if you're uncertain the value is always known and don't
         * want to throw for the unknown case.
         *
         * @throws AnthropicInvalidDataException if this class instance's value is a not a known
         *   member.
         */
        fun known(): Known =
            when (this) {
                CLAUDE_TAG_CATEGORY -> Known.CLAUDE_TAG_CATEGORY
                CLAUDE_TAG_USER_ID -> Known.CLAUDE_TAG_USER_ID
                CONTEXT_WINDOW -> Known.CONTEXT_WINDOW
                COST_TYPE -> Known.COST_TYPE
                INFERENCE_GEO -> Known.INFERENCE_GEO
                MODEL -> Known.MODEL
                PRODUCT -> Known.PRODUCT
                RBAC_GROUP_ID -> Known.RBAC_GROUP_ID
                SLACK_CHANNEL_ID -> Known.SLACK_CHANNEL_ID
                SPEED -> Known.SPEED
                TOKEN_TYPE -> Known.TOKEN_TYPE
                else -> throw AnthropicInvalidDataException("Unknown GroupBy: $value")
            }

        /**
         * Returns this class instance's primitive wire representation.
         *
         * This differs from the [toString] method because that method is primarily for debugging
         * and generally doesn't throw.
         *
         * @throws AnthropicInvalidDataException if this class instance's value does not have the
         *   expected primitive type.
         */
        fun asString(): String =
            _value().asString().orElseThrow {
                AnthropicInvalidDataException("Value is not a String")
            }

        private var validated: Boolean = false

        /**
         * Validates that the types of all values in this object match their expected types
         * recursively.
         *
         * This method is _not_ forwards compatible with new types from the API for existing fields.
         *
         * @throws AnthropicInvalidDataException if any value type in this object doesn't match its
         *   expected type.
         */
        fun validate(): GroupBy = apply {
            if (validated) {
                return@apply
            }

            known()
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
         * Returns a score indicating how many valid values are contained in this object
         * recursively.
         *
         * Used for best match union deserialization.
         */
        @JvmSynthetic internal fun validity(): Int = if (value() == Value._UNKNOWN) 0 else 1

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is GroupBy && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    /** Sort direction. Defaults to `desc`. */
    class Order private constructor(private val value: JsonField<String>) : Enum {

        /**
         * Returns this class instance's raw value.
         *
         * This is usually only useful if this instance was deserialized from data that doesn't
         * match any known member, and you want to know that value. For example, if the SDK is on an
         * older version than the API, then the API may respond with new members that the SDK is
         * unaware of.
         */
        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            @JvmField val ASC = Order(JsonField.of("asc"))

            @JvmField val DESC = Order(JsonField.of("desc"))

            @JvmStatic
            fun of(value: String): Order =
                // Intern known values so `==` works
                when (value) {
                    "asc" -> ASC
                    "desc" -> DESC
                    else -> Order(JsonField.of(value))
                }

            @JsonCreator
            @JvmStatic
            fun of(value: JsonField<String>): Order =
                value.asString().getOrNull()?.let { of(it) } ?: Order(value)
        }

        /** An enum containing [Order]'s known values. */
        enum class Known {
            ASC,
            DESC,
        }

        /**
         * An enum containing [Order]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [Order] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            ASC,
            DESC,
            /** An enum member indicating that [Order] was instantiated with an unknown value. */
            _UNKNOWN,
        }

        /**
         * Returns an enum member corresponding to this class instance's value, or [Value._UNKNOWN]
         * if the class was instantiated with an unknown value.
         *
         * Use the [known] method instead if you're certain the value is always known or if you want
         * to throw for the unknown case.
         */
        fun value(): Value =
            when (this) {
                ASC -> Value.ASC
                DESC -> Value.DESC
                else -> Value._UNKNOWN
            }

        /**
         * Returns an enum member corresponding to this class instance's value.
         *
         * Use the [value] method instead if you're uncertain the value is always known and don't
         * want to throw for the unknown case.
         *
         * @throws AnthropicInvalidDataException if this class instance's value is a not a known
         *   member.
         */
        fun known(): Known =
            when (this) {
                ASC -> Known.ASC
                DESC -> Known.DESC
                else -> throw AnthropicInvalidDataException("Unknown Order: $value")
            }

        /**
         * Returns this class instance's primitive wire representation.
         *
         * This differs from the [toString] method because that method is primarily for debugging
         * and generally doesn't throw.
         *
         * @throws AnthropicInvalidDataException if this class instance's value does not have the
         *   expected primitive type.
         */
        fun asString(): String =
            _value().asString().orElseThrow {
                AnthropicInvalidDataException("Value is not a String")
            }

        private var validated: Boolean = false

        /**
         * Validates that the types of all values in this object match their expected types
         * recursively.
         *
         * This method is _not_ forwards compatible with new types from the API for existing fields.
         *
         * @throws AnthropicInvalidDataException if any value type in this object doesn't match its
         *   expected type.
         */
        fun validate(): Order = apply {
            if (validated) {
                return@apply
            }

            known()
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
         * Returns a score indicating how many valid values are contained in this object
         * recursively.
         *
         * Used for best match union deserialization.
         */
        @JvmSynthetic internal fun validity(): Int = if (value() == Value._UNKNOWN) 0 else 1

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Order && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    /** Metric to rank actors by. Defaults to `amount`. */
    class OrderBy private constructor(private val value: JsonField<String>) : Enum {

        /**
         * Returns this class instance's raw value.
         *
         * This is usually only useful if this instance was deserialized from data that doesn't
         * match any known member, and you want to know that value. For example, if the SDK is on an
         * older version than the API, then the API may respond with new members that the SDK is
         * unaware of.
         */
        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            @JvmField val AMOUNT = OrderBy(JsonField.of("amount"))

            @JvmField val LIST_AMOUNT = OrderBy(JsonField.of("list_amount"))

            @JvmStatic
            fun of(value: String): OrderBy =
                // Intern known values so `==` works
                when (value) {
                    "amount" -> AMOUNT
                    "list_amount" -> LIST_AMOUNT
                    else -> OrderBy(JsonField.of(value))
                }

            @JsonCreator
            @JvmStatic
            fun of(value: JsonField<String>): OrderBy =
                value.asString().getOrNull()?.let { of(it) } ?: OrderBy(value)
        }

        /** An enum containing [OrderBy]'s known values. */
        enum class Known {
            AMOUNT,
            LIST_AMOUNT,
        }

        /**
         * An enum containing [OrderBy]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [OrderBy] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            AMOUNT,
            LIST_AMOUNT,
            /** An enum member indicating that [OrderBy] was instantiated with an unknown value. */
            _UNKNOWN,
        }

        /**
         * Returns an enum member corresponding to this class instance's value, or [Value._UNKNOWN]
         * if the class was instantiated with an unknown value.
         *
         * Use the [known] method instead if you're certain the value is always known or if you want
         * to throw for the unknown case.
         */
        fun value(): Value =
            when (this) {
                AMOUNT -> Value.AMOUNT
                LIST_AMOUNT -> Value.LIST_AMOUNT
                else -> Value._UNKNOWN
            }

        /**
         * Returns an enum member corresponding to this class instance's value.
         *
         * Use the [value] method instead if you're uncertain the value is always known and don't
         * want to throw for the unknown case.
         *
         * @throws AnthropicInvalidDataException if this class instance's value is a not a known
         *   member.
         */
        fun known(): Known =
            when (this) {
                AMOUNT -> Known.AMOUNT
                LIST_AMOUNT -> Known.LIST_AMOUNT
                else -> throw AnthropicInvalidDataException("Unknown OrderBy: $value")
            }

        /**
         * Returns this class instance's primitive wire representation.
         *
         * This differs from the [toString] method because that method is primarily for debugging
         * and generally doesn't throw.
         *
         * @throws AnthropicInvalidDataException if this class instance's value does not have the
         *   expected primitive type.
         */
        fun asString(): String =
            _value().asString().orElseThrow {
                AnthropicInvalidDataException("Value is not a String")
            }

        private var validated: Boolean = false

        /**
         * Validates that the types of all values in this object match their expected types
         * recursively.
         *
         * This method is _not_ forwards compatible with new types from the API for existing fields.
         *
         * @throws AnthropicInvalidDataException if any value type in this object doesn't match its
         *   expected type.
         */
        fun validate(): OrderBy = apply {
            if (validated) {
                return@apply
            }

            known()
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
         * Returns a score indicating how many valid values are contained in this object
         * recursively.
         *
         * Used for best match union deserialization.
         */
        @JvmSynthetic internal fun validity(): Int = if (value() == Value._UNKNOWN) 0 else 1

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is OrderBy && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    class Speed private constructor(private val value: JsonField<String>) : Enum {

        /**
         * Returns this class instance's raw value.
         *
         * This is usually only useful if this instance was deserialized from data that doesn't
         * match any known member, and you want to know that value. For example, if the SDK is on an
         * older version than the API, then the API may respond with new members that the SDK is
         * unaware of.
         */
        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            @JvmField val FAST = Speed(JsonField.of("fast"))

            @JvmField val STANDARD = Speed(JsonField.of("standard"))

            @JvmStatic
            fun of(value: String): Speed =
                // Intern known values so `==` works
                when (value) {
                    "fast" -> FAST
                    "standard" -> STANDARD
                    else -> Speed(JsonField.of(value))
                }

            @JsonCreator
            @JvmStatic
            fun of(value: JsonField<String>): Speed =
                value.asString().getOrNull()?.let { of(it) } ?: Speed(value)
        }

        /** An enum containing [Speed]'s known values. */
        enum class Known {
            FAST,
            STANDARD,
        }

        /**
         * An enum containing [Speed]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [Speed] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            FAST,
            STANDARD,
            /** An enum member indicating that [Speed] was instantiated with an unknown value. */
            _UNKNOWN,
        }

        /**
         * Returns an enum member corresponding to this class instance's value, or [Value._UNKNOWN]
         * if the class was instantiated with an unknown value.
         *
         * Use the [known] method instead if you're certain the value is always known or if you want
         * to throw for the unknown case.
         */
        fun value(): Value =
            when (this) {
                FAST -> Value.FAST
                STANDARD -> Value.STANDARD
                else -> Value._UNKNOWN
            }

        /**
         * Returns an enum member corresponding to this class instance's value.
         *
         * Use the [value] method instead if you're uncertain the value is always known and don't
         * want to throw for the unknown case.
         *
         * @throws AnthropicInvalidDataException if this class instance's value is a not a known
         *   member.
         */
        fun known(): Known =
            when (this) {
                FAST -> Known.FAST
                STANDARD -> Known.STANDARD
                else -> throw AnthropicInvalidDataException("Unknown Speed: $value")
            }

        /**
         * Returns this class instance's primitive wire representation.
         *
         * This differs from the [toString] method because that method is primarily for debugging
         * and generally doesn't throw.
         *
         * @throws AnthropicInvalidDataException if this class instance's value does not have the
         *   expected primitive type.
         */
        fun asString(): String =
            _value().asString().orElseThrow {
                AnthropicInvalidDataException("Value is not a String")
            }

        private var validated: Boolean = false

        /**
         * Validates that the types of all values in this object match their expected types
         * recursively.
         *
         * This method is _not_ forwards compatible with new types from the API for existing fields.
         *
         * @throws AnthropicInvalidDataException if any value type in this object doesn't match its
         *   expected type.
         */
        fun validate(): Speed = apply {
            if (validated) {
                return@apply
            }

            known()
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
         * Returns a score indicating how many valid values are contained in this object
         * recursively.
         *
         * Used for best match union deserialization.
         */
        @JvmSynthetic internal fun validity(): Int = if (value() == Value._UNKNOWN) 0 else 1

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Speed && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is UserCostReportListParams &&
            startingAt == other.startingAt &&
            bucketWidth == other.bucketWidth &&
            claudeTagCategories == other.claudeTagCategories &&
            claudeTagUserIds == other.claudeTagUserIds &&
            contextWindows == other.contextWindows &&
            endingAt == other.endingAt &&
            excludeDeletedUsers == other.excludeDeletedUsers &&
            groupBy == other.groupBy &&
            inferenceGeos == other.inferenceGeos &&
            limit == other.limit &&
            models == other.models &&
            order == other.order &&
            orderBy == other.orderBy &&
            page == other.page &&
            products == other.products &&
            rbacGroupIds == other.rbacGroupIds &&
            slackChannelIds == other.slackChannelIds &&
            speeds == other.speeds &&
            userIds == other.userIds &&
            additionalHeaders == other.additionalHeaders &&
            additionalQueryParams == other.additionalQueryParams
    }

    override fun hashCode(): Int =
        Objects.hash(
            startingAt,
            bucketWidth,
            claudeTagCategories,
            claudeTagUserIds,
            contextWindows,
            endingAt,
            excludeDeletedUsers,
            groupBy,
            inferenceGeos,
            limit,
            models,
            order,
            orderBy,
            page,
            products,
            rbacGroupIds,
            slackChannelIds,
            speeds,
            userIds,
            additionalHeaders,
            additionalQueryParams,
        )

    override fun toString() =
        "UserCostReportListParams{startingAt=$startingAt, bucketWidth=$bucketWidth, claudeTagCategories=$claudeTagCategories, claudeTagUserIds=$claudeTagUserIds, contextWindows=$contextWindows, endingAt=$endingAt, excludeDeletedUsers=$excludeDeletedUsers, groupBy=$groupBy, inferenceGeos=$inferenceGeos, limit=$limit, models=$models, order=$order, orderBy=$orderBy, page=$page, products=$products, rbacGroupIds=$rbacGroupIds, slackChannelIds=$slackChannelIds, speeds=$speeds, userIds=$userIds, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
