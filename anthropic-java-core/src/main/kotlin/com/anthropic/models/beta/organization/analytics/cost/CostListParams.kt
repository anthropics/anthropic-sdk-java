package com.anthropic.models.beta.organization.analytics.cost

import com.anthropic.core.Params
import com.anthropic.core.checkRequired
import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import com.anthropic.core.opentelemetry.SpanData
import com.anthropic.core.toImmutable
import com.anthropic.models.beta.AnthropicBeta
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsActorType
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsClaudeTagCategory
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsContextWindow
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsInferenceGeoFilter
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsServiceTier
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsSpeed
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsTimeBucketWidth
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsUsageCostProductFilter
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsUsageCostSortOrder
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/**
 * Get cost in USD across a date range, broken out by dimension.
 *
 * Returns one row per combination of the requested `group_by[]` dimensions (and time bucket, when
 * `time_bucket_width` is set) that has cost in the range, ranked by spend or ordered by time
 * bucket; every combination is returned across pages. With no `group_by[]` and no
 * `time_bucket_width`, a single row aggregates the whole range. Includes all cost, whether or not
 * it is attributable to a seat user. Requires an API key with the `read:analytics` scope, or an
 * Admin API key.
 */
class CostListParams
private constructor(
    private val startingAt: OffsetDateTime,
    private val actorIds: List<String>?,
    private val actorTypes: List<BetaAnalyticsActorType>?,
    private val agentIds: List<String>?,
    private val apiKeyIds: List<String>?,
    private val claudeTagCategories: List<BetaAnalyticsClaudeTagCategory>?,
    private val claudeTagUserIds: List<String>?,
    private val contextWindows: List<BetaAnalyticsContextWindow>?,
    private val deploymentIds: List<String>?,
    private val endingAt: OffsetDateTime?,
    private val environmentIds: List<String>?,
    private val excludeDeletedUsers: Boolean?,
    private val groupBy: List<BetaAnalyticsCostGroupBy>?,
    private val inferenceGeos: List<BetaAnalyticsInferenceGeoFilter>?,
    private val limit: Long?,
    private val models: List<String>?,
    private val order: BetaAnalyticsUsageCostSortOrder?,
    private val orderBy: BetaAnalyticsCostOrderBy?,
    private val page: String?,
    private val products: List<BetaAnalyticsUsageCostProductFilter>?,
    private val rbacGroupIds: List<String>?,
    private val serviceTiers: List<BetaAnalyticsServiceTier>?,
    private val sessionIds: List<String>?,
    private val slackChannelIds: List<String>?,
    private val speeds: List<BetaAnalyticsSpeed>?,
    private val timeBucketWidth: BetaAnalyticsTimeBucketWidth?,
    private val userProfileIds: List<String>?,
    private val workspaceIds: List<String>?,
    private val betas: List<AnthropicBeta>?,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    /**
     * Start of range, inclusive. RFC 3339 tz-aware. Must be within the last 365 days and no earlier
     * than 2026-01-01T00:00:00Z.
     */
    fun startingAt(): OffsetDateTime = startingAt

    /**
     * Filter to usage attributed to specific identities, by tagged ID: a `user_...`, `svac_...`
     * (service account), or `apikey_...` ID. An `apikey_` entry matches only usage attributed to
     * the key itself, not usage on that key attributed to a user or service account (use
     * `api_key_ids[]` for every request a key authenticated). Federated identities have no tagged
     * ID; select them with `actor_types[]`. Use `group_by[]=actor` to break out per-identity
     * values.
     */
    fun actorIds(): Optional<List<String>> = Optional.ofNullable(actorIds)

    /**
     * Filter to usage attributed to specific kinds of identity. Use `group_by[]=actor_type` to
     * break out per-kind values.
     */
    fun actorTypes(): Optional<List<BetaAnalyticsActorType>> = Optional.ofNullable(actorTypes)

    /**
     * Filter to usage incurred by sessions of specific Claude Managed Agents agents. Use
     * `group_by[]=agent_id` to break out per-agent values. Claude Developer Platform organizations
     * only.
     */
    fun agentIds(): Optional<List<String>> = Optional.ofNullable(agentIds)

    /**
     * Filter to usage authenticated by specific API keys, by tagged API key ID (`apikey_...`),
     * whichever identity the usage is attributed to. Include an empty entry (`api_key_ids[]=`) to
     * also match usage that no API key authenticated. Use `group_by[]=api_key_id` to break out
     * per-key values; usage without a key has a null `api_key_id`. Claude Developer Platform
     * organizations only.
     */
    fun apiKeyIds(): Optional<List<String>> = Optional.ofNullable(apiKeyIds)

    /**
     * Filter to Claude Tag (Claude in Slack) usage in specific spend categories. Usage with no
     * category never matches. `dm` usage is reported under the user's product rather than
     * `claude_tag`, so combining this filter with `products[]=claude_tag` excludes it. Use
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
     * Filter to usage incurred by Claude Managed Agents sessions that specific deployments started.
     * Use `group_by[]=deployment_id` to break out per-deployment values. Claude Developer Platform
     * organizations only.
     */
    fun deploymentIds(): Optional<List<String>> = Optional.ofNullable(deploymentIds)

    /**
     * End of range, exclusive. Required when `time_bucket_width` is set. Without
     * `time_bucket_width`, defaults to the earlier of now and `starting_at` + 31 days. The range
     * may span at most 31 days.
     */
    fun endingAt(): Optional<OffsetDateTime> = Optional.ofNullable(endingAt)

    /**
     * Filter to usage incurred by Claude Managed Agents sessions that ran in specific environments.
     * Use `group_by[]=environment_id` to break out per-environment values. Claude Developer
     * Platform organizations only.
     */
    fun environmentIds(): Optional<List<String>> = Optional.ofNullable(environmentIds)

    /**
     * If true, omit rows whose `actor` is a `user_actor` with `deleted: true`. Requires
     * `group_by[]=actor`. Rows for other actor types, and rows whose `actor` is null, are always
     * kept. A page may contain fewer than `limit` rows; use `has_more` and `next_page` to paginate
     * as usual.
     */
    fun excludeDeletedUsers(): Optional<Boolean> = Optional.ofNullable(excludeDeletedUsers)

    /**
     * Dimensions to break cost out by (at most 5, not counting `cost_type` and `token_type`): one
     * row per combination of their values with cost in the range (and per time bucket, when
     * `time_bucket_width` is set), every combination returned across pages rather than a capped top
     * set. `cost_type` and `token_type` do not count toward `limit`: `cost_type` returns one row
     * per cost component (tokens, web search, code execution, and `session_usage`, Claude Managed
     * Agents session time); `token_type` returns one row per token type, each with `cost_type:
     * "tokens"`; combining both returns the per-token-type rows plus the web-search, code-execution
     * and session-usage rows. When omitted, a single row aggregates the whole range.
     */
    fun groupBy(): Optional<List<BetaAnalyticsCostGroupBy>> = Optional.ofNullable(groupBy)

    /**
     * Filter to specific inference regions. `not_available` matches rows where the region is unset.
     * Use `group_by[]=inference_geo` to break out per-region values.
     */
    fun inferenceGeos(): Optional<List<BetaAnalyticsInferenceGeoFilter>> =
        Optional.ofNullable(inferenceGeos)

    /**
     * Number of rows per page (1-1000, default 20). `cost_type`/`token_type` fan-out rows (cost
     * endpoint only) do not count toward this limit, so `data` can exceed it.
     */
    fun limit(): Optional<Long> = Optional.ofNullable(limit)

    /**
     * Models to include. Defaults to all models. Use `group_by[]=model` to break out per-model
     * values.
     */
    fun models(): Optional<List<String>> = Optional.ofNullable(models)

    /**
     * Sort direction of the `order_by` metric, or of the time buckets when `order_by` is `time` and
     * `time_bucket_width` is set. Defaults to `desc`.
     */
    fun order(): Optional<BetaAnalyticsUsageCostSortOrder> = Optional.ofNullable(order)

    /**
     * Row order. A metric ranks rows by that metric in the `order` direction. `time` orders rows by
     * time bucket in the `order` direction (newest first by default) and, within a bucket, by
     * `amount`, highest first. `time` takes effect only with `time_bucket_width`; without it,
     * `time` is the same as `amount`. Defaults to `amount`.
     */
    fun orderBy(): Optional<BetaAnalyticsCostOrderBy> = Optional.ofNullable(orderBy)

    /** Opaque cursor from a previous response's `next_page` field. */
    fun page(): Optional<String> = Optional.ofNullable(page)

    /** Product surfaces to include. Defaults to all products. */
    fun products(): Optional<List<BetaAnalyticsUsageCostProductFilter>> =
        Optional.ofNullable(products)

    /**
     * Filter to usage attributed to specific RBAC groups. Accepts tagged RBAC group IDs
     * (`rbac_group_...`) or bare group UUIDs. A row matches when the user belonged to any of the
     * listed groups on the (UTC) day the usage occurred; usage with no group attribution never
     * matches.
     */
    fun rbacGroupIds(): Optional<List<String>> = Optional.ofNullable(rbacGroupIds)

    /**
     * Filter to specific service tiers. Use `group_by[]=service_tier` to break out per-tier values.
     * Claude Developer Platform organizations only; matches nothing for a Claude Enterprise
     * organization.
     */
    fun serviceTiers(): Optional<List<BetaAnalyticsServiceTier>> = Optional.ofNullable(serviceTiers)

    /**
     * Filter to usage incurred by specific Claude Managed Agents sessions, by tagged session ID
     * (`sesn_...`). Use `group_by[]=session_id` to break out per-session values. Claude Developer
     * Platform organizations only.
     */
    fun sessionIds(): Optional<List<String>> = Optional.ofNullable(sessionIds)

    /**
     * Filter to usage originating from specific Slack channels. Use `group_by[]=slack_channel_id`
     * to break out per-channel values.
     */
    fun slackChannelIds(): Optional<List<String>> = Optional.ofNullable(slackChannelIds)

    /**
     * Filter to fast or standard inference mode. Use `group_by[]=speed` to break out per-mode
     * values.
     */
    fun speeds(): Optional<List<BetaAnalyticsSpeed>> = Optional.ofNullable(speeds)

    /**
     * Time-bucket granularity. When set, rows are returned per time bucket (one per bucket and
     * dimension combination with usage) and each row's `starting_at` and `ending_at` are that
     * bucket's bounds; the time bucket counts toward `limit`. `ending_at` is required when
     * `time_bucket_width` is set, and with `time_bucket_width="1m"` the range may span at most 24
     * hours. When omitted, each row aggregates the requested range with `starting_at` and
     * `ending_at` each rounded down to the hour.
     */
    fun timeBucketWidth(): Optional<BetaAnalyticsTimeBucketWidth> =
        Optional.ofNullable(timeBucketWidth)

    /**
     * Filter to usage attributed to specific user profiles, by tagged user profile ID
     * (`uprof_...`). Use `group_by[]=user_profile_id` to break out per-profile values. Claude
     * Developer Platform organizations only.
     */
    fun userProfileIds(): Optional<List<String>> = Optional.ofNullable(userProfileIds)

    /**
     * Filter to usage in specific workspaces, by tagged workspace ID (`wrkspc_...`). Use
     * `group_by[]=workspace_id` to break out per-workspace values. Claude Developer Platform
     * organizations only; matches nothing for a Claude Enterprise organization.
     */
    fun workspaceIds(): Optional<List<String>> = Optional.ofNullable(workspaceIds)

    /**
     * This endpoint is in beta: requests must send `analytics-usage-cost-2026-08-24` in this
     * header.
     */
    fun betas(): Optional<List<AnthropicBeta>> = Optional.ofNullable(betas)

    /** Additional headers to send with the request. */
    fun _additionalHeaders(): Headers = additionalHeaders

    /** Additional query param to send with the request. */
    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [CostListParams].
         *
         * The following fields are required:
         * ```java
         * .startingAt()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [CostListParams]. */
    class Builder internal constructor() {

        private var startingAt: OffsetDateTime? = null
        private var actorIds: MutableList<String>? = null
        private var actorTypes: MutableList<BetaAnalyticsActorType>? = null
        private var agentIds: MutableList<String>? = null
        private var apiKeyIds: MutableList<String>? = null
        private var claudeTagCategories: MutableList<BetaAnalyticsClaudeTagCategory>? = null
        private var claudeTagUserIds: MutableList<String>? = null
        private var contextWindows: MutableList<BetaAnalyticsContextWindow>? = null
        private var deploymentIds: MutableList<String>? = null
        private var endingAt: OffsetDateTime? = null
        private var environmentIds: MutableList<String>? = null
        private var excludeDeletedUsers: Boolean? = null
        private var groupBy: MutableList<BetaAnalyticsCostGroupBy>? = null
        private var inferenceGeos: MutableList<BetaAnalyticsInferenceGeoFilter>? = null
        private var limit: Long? = null
        private var models: MutableList<String>? = null
        private var order: BetaAnalyticsUsageCostSortOrder? = null
        private var orderBy: BetaAnalyticsCostOrderBy? = null
        private var page: String? = null
        private var products: MutableList<BetaAnalyticsUsageCostProductFilter>? = null
        private var rbacGroupIds: MutableList<String>? = null
        private var serviceTiers: MutableList<BetaAnalyticsServiceTier>? = null
        private var sessionIds: MutableList<String>? = null
        private var slackChannelIds: MutableList<String>? = null
        private var speeds: MutableList<BetaAnalyticsSpeed>? = null
        private var timeBucketWidth: BetaAnalyticsTimeBucketWidth? = null
        private var userProfileIds: MutableList<String>? = null
        private var workspaceIds: MutableList<String>? = null
        private var betas: MutableList<AnthropicBeta>? = null
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(costListParams: CostListParams) = apply {
            startingAt = costListParams.startingAt
            actorIds = costListParams.actorIds?.toMutableList()
            actorTypes = costListParams.actorTypes?.toMutableList()
            agentIds = costListParams.agentIds?.toMutableList()
            apiKeyIds = costListParams.apiKeyIds?.toMutableList()
            claudeTagCategories = costListParams.claudeTagCategories?.toMutableList()
            claudeTagUserIds = costListParams.claudeTagUserIds?.toMutableList()
            contextWindows = costListParams.contextWindows?.toMutableList()
            deploymentIds = costListParams.deploymentIds?.toMutableList()
            endingAt = costListParams.endingAt
            environmentIds = costListParams.environmentIds?.toMutableList()
            excludeDeletedUsers = costListParams.excludeDeletedUsers
            groupBy = costListParams.groupBy?.toMutableList()
            inferenceGeos = costListParams.inferenceGeos?.toMutableList()
            limit = costListParams.limit
            models = costListParams.models?.toMutableList()
            order = costListParams.order
            orderBy = costListParams.orderBy
            page = costListParams.page
            products = costListParams.products?.toMutableList()
            rbacGroupIds = costListParams.rbacGroupIds?.toMutableList()
            serviceTiers = costListParams.serviceTiers?.toMutableList()
            sessionIds = costListParams.sessionIds?.toMutableList()
            slackChannelIds = costListParams.slackChannelIds?.toMutableList()
            speeds = costListParams.speeds?.toMutableList()
            timeBucketWidth = costListParams.timeBucketWidth
            userProfileIds = costListParams.userProfileIds?.toMutableList()
            workspaceIds = costListParams.workspaceIds?.toMutableList()
            betas = costListParams.betas?.toMutableList()
            additionalHeaders = costListParams.additionalHeaders.toBuilder()
            additionalQueryParams = costListParams.additionalQueryParams.toBuilder()
        }

        /**
         * Start of range, inclusive. RFC 3339 tz-aware. Must be within the last 365 days and no
         * earlier than 2026-01-01T00:00:00Z.
         */
        fun startingAt(startingAt: OffsetDateTime) = apply { this.startingAt = startingAt }

        /**
         * Filter to usage attributed to specific identities, by tagged ID: a `user_...`, `svac_...`
         * (service account), or `apikey_...` ID. An `apikey_` entry matches only usage attributed
         * to the key itself, not usage on that key attributed to a user or service account (use
         * `api_key_ids[]` for every request a key authenticated). Federated identities have no
         * tagged ID; select them with `actor_types[]`. Use `group_by[]=actor` to break out
         * per-identity values.
         */
        fun actorIds(actorIds: List<String>?) = apply { this.actorIds = actorIds?.toMutableList() }

        /** Alias for calling [Builder.actorIds] with `actorIds.orElse(null)`. */
        fun actorIds(actorIds: Optional<List<String>>) = actorIds(actorIds.getOrNull())

        /**
         * Adds a single [String] to [actorIds].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addActorId(actorId: String) = apply {
            actorIds = (actorIds ?: mutableListOf()).apply { add(actorId) }
        }

        /**
         * Filter to usage attributed to specific kinds of identity. Use `group_by[]=actor_type` to
         * break out per-kind values.
         */
        fun actorTypes(actorTypes: List<BetaAnalyticsActorType>?) = apply {
            this.actorTypes = actorTypes?.toMutableList()
        }

        /** Alias for calling [Builder.actorTypes] with `actorTypes.orElse(null)`. */
        fun actorTypes(actorTypes: Optional<List<BetaAnalyticsActorType>>) =
            actorTypes(actorTypes.getOrNull())

        /**
         * Adds a single [BetaAnalyticsActorType] to [actorTypes].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addActorType(actorType: BetaAnalyticsActorType) = apply {
            actorTypes = (actorTypes ?: mutableListOf()).apply { add(actorType) }
        }

        /**
         * Filter to usage incurred by sessions of specific Claude Managed Agents agents. Use
         * `group_by[]=agent_id` to break out per-agent values. Claude Developer Platform
         * organizations only.
         */
        fun agentIds(agentIds: List<String>?) = apply { this.agentIds = agentIds?.toMutableList() }

        /** Alias for calling [Builder.agentIds] with `agentIds.orElse(null)`. */
        fun agentIds(agentIds: Optional<List<String>>) = agentIds(agentIds.getOrNull())

        /**
         * Adds a single [String] to [agentIds].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addAgentId(agentId: String) = apply {
            agentIds = (agentIds ?: mutableListOf()).apply { add(agentId) }
        }

        /**
         * Filter to usage authenticated by specific API keys, by tagged API key ID (`apikey_...`),
         * whichever identity the usage is attributed to. Include an empty entry (`api_key_ids[]=`)
         * to also match usage that no API key authenticated. Use `group_by[]=api_key_id` to break
         * out per-key values; usage without a key has a null `api_key_id`. Claude Developer
         * Platform organizations only.
         */
        fun apiKeyIds(apiKeyIds: List<String>?) = apply {
            this.apiKeyIds = apiKeyIds?.toMutableList()
        }

        /** Alias for calling [Builder.apiKeyIds] with `apiKeyIds.orElse(null)`. */
        fun apiKeyIds(apiKeyIds: Optional<List<String>>) = apiKeyIds(apiKeyIds.getOrNull())

        /**
         * Adds a single [String] to [apiKeyIds].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addApiKeyId(apiKeyId: String) = apply {
            apiKeyIds = (apiKeyIds ?: mutableListOf()).apply { add(apiKeyId) }
        }

        /**
         * Filter to Claude Tag (Claude in Slack) usage in specific spend categories. Usage with no
         * category never matches. `dm` usage is reported under the user's product rather than
         * `claude_tag`, so combining this filter with `products[]=claude_tag` excludes it. Use
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
         * Filter to usage incurred by Claude Managed Agents sessions that specific deployments
         * started. Use `group_by[]=deployment_id` to break out per-deployment values. Claude
         * Developer Platform organizations only.
         */
        fun deploymentIds(deploymentIds: List<String>?) = apply {
            this.deploymentIds = deploymentIds?.toMutableList()
        }

        /** Alias for calling [Builder.deploymentIds] with `deploymentIds.orElse(null)`. */
        fun deploymentIds(deploymentIds: Optional<List<String>>) =
            deploymentIds(deploymentIds.getOrNull())

        /**
         * Adds a single [String] to [deploymentIds].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addDeploymentId(deploymentId: String) = apply {
            deploymentIds = (deploymentIds ?: mutableListOf()).apply { add(deploymentId) }
        }

        /**
         * End of range, exclusive. Required when `time_bucket_width` is set. Without
         * `time_bucket_width`, defaults to the earlier of now and `starting_at` + 31 days. The
         * range may span at most 31 days.
         */
        fun endingAt(endingAt: OffsetDateTime?) = apply { this.endingAt = endingAt }

        /** Alias for calling [Builder.endingAt] with `endingAt.orElse(null)`. */
        fun endingAt(endingAt: Optional<OffsetDateTime>) = endingAt(endingAt.getOrNull())

        /**
         * Filter to usage incurred by Claude Managed Agents sessions that ran in specific
         * environments. Use `group_by[]=environment_id` to break out per-environment values. Claude
         * Developer Platform organizations only.
         */
        fun environmentIds(environmentIds: List<String>?) = apply {
            this.environmentIds = environmentIds?.toMutableList()
        }

        /** Alias for calling [Builder.environmentIds] with `environmentIds.orElse(null)`. */
        fun environmentIds(environmentIds: Optional<List<String>>) =
            environmentIds(environmentIds.getOrNull())

        /**
         * Adds a single [String] to [environmentIds].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addEnvironmentId(environmentId: String) = apply {
            environmentIds = (environmentIds ?: mutableListOf()).apply { add(environmentId) }
        }

        /**
         * If true, omit rows whose `actor` is a `user_actor` with `deleted: true`. Requires
         * `group_by[]=actor`. Rows for other actor types, and rows whose `actor` is null, are
         * always kept. A page may contain fewer than `limit` rows; use `has_more` and `next_page`
         * to paginate as usual.
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
         * Dimensions to break cost out by (at most 5, not counting `cost_type` and `token_type`):
         * one row per combination of their values with cost in the range (and per time bucket, when
         * `time_bucket_width` is set), every combination returned across pages rather than a capped
         * top set. `cost_type` and `token_type` do not count toward `limit`: `cost_type` returns
         * one row per cost component (tokens, web search, code execution, and `session_usage`,
         * Claude Managed Agents session time); `token_type` returns one row per token type, each
         * with `cost_type: "tokens"`; combining both returns the per-token-type rows plus the
         * web-search, code-execution and session-usage rows. When omitted, a single row aggregates
         * the whole range.
         */
        fun groupBy(groupBy: List<BetaAnalyticsCostGroupBy>?) = apply {
            this.groupBy = groupBy?.toMutableList()
        }

        /** Alias for calling [Builder.groupBy] with `groupBy.orElse(null)`. */
        fun groupBy(groupBy: Optional<List<BetaAnalyticsCostGroupBy>>) =
            groupBy(groupBy.getOrNull())

        /**
         * Adds a single [BetaAnalyticsCostGroupBy] to [Builder.groupBy].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addGroupBy(groupBy: BetaAnalyticsCostGroupBy) = apply {
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
         * Number of rows per page (1-1000, default 20). `cost_type`/`token_type` fan-out rows (cost
         * endpoint only) do not count toward this limit, so `data` can exceed it.
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

        /**
         * Sort direction of the `order_by` metric, or of the time buckets when `order_by` is `time`
         * and `time_bucket_width` is set. Defaults to `desc`.
         */
        fun order(order: BetaAnalyticsUsageCostSortOrder?) = apply { this.order = order }

        /** Alias for calling [Builder.order] with `order.orElse(null)`. */
        fun order(order: Optional<BetaAnalyticsUsageCostSortOrder>) = order(order.getOrNull())

        /**
         * Row order. A metric ranks rows by that metric in the `order` direction. `time` orders
         * rows by time bucket in the `order` direction (newest first by default) and, within a
         * bucket, by `amount`, highest first. `time` takes effect only with `time_bucket_width`;
         * without it, `time` is the same as `amount`. Defaults to `amount`.
         */
        fun orderBy(orderBy: BetaAnalyticsCostOrderBy?) = apply { this.orderBy = orderBy }

        /** Alias for calling [Builder.orderBy] with `orderBy.orElse(null)`. */
        fun orderBy(orderBy: Optional<BetaAnalyticsCostOrderBy>) = orderBy(orderBy.getOrNull())

        /** Opaque cursor from a previous response's `next_page` field. */
        fun page(page: String?) = apply { this.page = page }

        /** Alias for calling [Builder.page] with `page.orElse(null)`. */
        fun page(page: Optional<String>) = page(page.getOrNull())

        /** Product surfaces to include. Defaults to all products. */
        fun products(products: List<BetaAnalyticsUsageCostProductFilter>?) = apply {
            this.products = products?.toMutableList()
        }

        /** Alias for calling [Builder.products] with `products.orElse(null)`. */
        fun products(products: Optional<List<BetaAnalyticsUsageCostProductFilter>>) =
            products(products.getOrNull())

        /**
         * Adds a single [BetaAnalyticsUsageCostProductFilter] to [products].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addProduct(product: BetaAnalyticsUsageCostProductFilter) = apply {
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
         * Filter to specific service tiers. Use `group_by[]=service_tier` to break out per-tier
         * values. Claude Developer Platform organizations only; matches nothing for a Claude
         * Enterprise organization.
         */
        fun serviceTiers(serviceTiers: List<BetaAnalyticsServiceTier>?) = apply {
            this.serviceTiers = serviceTiers?.toMutableList()
        }

        /** Alias for calling [Builder.serviceTiers] with `serviceTiers.orElse(null)`. */
        fun serviceTiers(serviceTiers: Optional<List<BetaAnalyticsServiceTier>>) =
            serviceTiers(serviceTiers.getOrNull())

        /**
         * Adds a single [BetaAnalyticsServiceTier] to [serviceTiers].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addServiceTier(serviceTier: BetaAnalyticsServiceTier) = apply {
            serviceTiers = (serviceTiers ?: mutableListOf()).apply { add(serviceTier) }
        }

        /**
         * Filter to usage incurred by specific Claude Managed Agents sessions, by tagged session ID
         * (`sesn_...`). Use `group_by[]=session_id` to break out per-session values. Claude
         * Developer Platform organizations only.
         */
        fun sessionIds(sessionIds: List<String>?) = apply {
            this.sessionIds = sessionIds?.toMutableList()
        }

        /** Alias for calling [Builder.sessionIds] with `sessionIds.orElse(null)`. */
        fun sessionIds(sessionIds: Optional<List<String>>) = sessionIds(sessionIds.getOrNull())

        /**
         * Adds a single [String] to [sessionIds].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addSessionId(sessionId: String) = apply {
            sessionIds = (sessionIds ?: mutableListOf()).apply { add(sessionId) }
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
        fun speeds(speeds: List<BetaAnalyticsSpeed>?) = apply {
            this.speeds = speeds?.toMutableList()
        }

        /** Alias for calling [Builder.speeds] with `speeds.orElse(null)`. */
        fun speeds(speeds: Optional<List<BetaAnalyticsSpeed>>) = speeds(speeds.getOrNull())

        /**
         * Adds a single [BetaAnalyticsSpeed] to [speeds].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addSpeed(speed: BetaAnalyticsSpeed) = apply {
            speeds = (speeds ?: mutableListOf()).apply { add(speed) }
        }

        /**
         * Time-bucket granularity. When set, rows are returned per time bucket (one per bucket and
         * dimension combination with usage) and each row's `starting_at` and `ending_at` are that
         * bucket's bounds; the time bucket counts toward `limit`. `ending_at` is required when
         * `time_bucket_width` is set, and with `time_bucket_width="1m"` the range may span at most
         * 24 hours. When omitted, each row aggregates the requested range with `starting_at` and
         * `ending_at` each rounded down to the hour.
         */
        fun timeBucketWidth(timeBucketWidth: BetaAnalyticsTimeBucketWidth?) = apply {
            this.timeBucketWidth = timeBucketWidth
        }

        /** Alias for calling [Builder.timeBucketWidth] with `timeBucketWidth.orElse(null)`. */
        fun timeBucketWidth(timeBucketWidth: Optional<BetaAnalyticsTimeBucketWidth>) =
            timeBucketWidth(timeBucketWidth.getOrNull())

        /**
         * Filter to usage attributed to specific user profiles, by tagged user profile ID
         * (`uprof_...`). Use `group_by[]=user_profile_id` to break out per-profile values. Claude
         * Developer Platform organizations only.
         */
        fun userProfileIds(userProfileIds: List<String>?) = apply {
            this.userProfileIds = userProfileIds?.toMutableList()
        }

        /** Alias for calling [Builder.userProfileIds] with `userProfileIds.orElse(null)`. */
        fun userProfileIds(userProfileIds: Optional<List<String>>) =
            userProfileIds(userProfileIds.getOrNull())

        /**
         * Adds a single [String] to [userProfileIds].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addUserProfileId(userProfileId: String) = apply {
            userProfileIds = (userProfileIds ?: mutableListOf()).apply { add(userProfileId) }
        }

        /**
         * Filter to usage in specific workspaces, by tagged workspace ID (`wrkspc_...`). Use
         * `group_by[]=workspace_id` to break out per-workspace values. Claude Developer Platform
         * organizations only; matches nothing for a Claude Enterprise organization.
         */
        fun workspaceIds(workspaceIds: List<String>?) = apply {
            this.workspaceIds = workspaceIds?.toMutableList()
        }

        /** Alias for calling [Builder.workspaceIds] with `workspaceIds.orElse(null)`. */
        fun workspaceIds(workspaceIds: Optional<List<String>>) =
            workspaceIds(workspaceIds.getOrNull())

        /**
         * Adds a single [String] to [workspaceIds].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addWorkspaceId(workspaceId: String) = apply {
            workspaceIds = (workspaceIds ?: mutableListOf()).apply { add(workspaceId) }
        }

        /**
         * This endpoint is in beta: requests must send `analytics-usage-cost-2026-08-24` in this
         * header.
         */
        fun betas(betas: List<AnthropicBeta>?) = apply { this.betas = betas?.toMutableList() }

        /** Alias for calling [Builder.betas] with `betas.orElse(null)`. */
        fun betas(betas: Optional<List<AnthropicBeta>>) = betas(betas.getOrNull())

        /**
         * Adds a single [AnthropicBeta] to [betas].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addBeta(beta: AnthropicBeta) = apply {
            betas = (betas ?: mutableListOf()).apply { add(beta) }
        }

        /**
         * Sets [addBeta] to an arbitrary [String].
         *
         * You should usually call [addBeta] with a well-typed [AnthropicBeta] constant instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun addBeta(value: String) = addBeta(AnthropicBeta.of(value))

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
         * Returns an immutable instance of [CostListParams].
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
        fun build(): CostListParams =
            CostListParams(
                checkRequired("startingAt", startingAt),
                actorIds?.toImmutable(),
                actorTypes?.toImmutable(),
                agentIds?.toImmutable(),
                apiKeyIds?.toImmutable(),
                claudeTagCategories?.toImmutable(),
                claudeTagUserIds?.toImmutable(),
                contextWindows?.toImmutable(),
                deploymentIds?.toImmutable(),
                endingAt,
                environmentIds?.toImmutable(),
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
                serviceTiers?.toImmutable(),
                sessionIds?.toImmutable(),
                slackChannelIds?.toImmutable(),
                speeds?.toImmutable(),
                timeBucketWidth,
                userProfileIds?.toImmutable(),
                workspaceIds?.toImmutable(),
                betas?.toImmutable(),
                additionalHeaders.build(),
                additionalQueryParams.build(),
            )
    }

    override fun _headers(): Headers =
        Headers.builder()
            .apply {
                betas?.let {
                    if (it.isNotEmpty()) {
                        put(
                            "anthropic-beta",
                            it.map { it.toString() }
                                .let { it + (listOf("analytics-usage-cost-2026-08-24") - it) }
                                .joinToString(","),
                        )
                    }
                }
                putAll(additionalHeaders)
            }
            .build()

    override fun _queryParams(): QueryParams =
        QueryParams.builder()
            .apply {
                put("starting_at", DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(startingAt))
                actorIds?.forEach { put("actor_ids[]", it) }
                actorTypes?.forEach { put("actor_types[]", it.toString()) }
                agentIds?.forEach { put("agent_ids[]", it) }
                apiKeyIds?.forEach { put("api_key_ids[]", it) }
                claudeTagCategories?.forEach { put("claude_tag_categories[]", it.toString()) }
                claudeTagUserIds?.forEach { put("claude_tag_user_ids[]", it) }
                contextWindows?.forEach { put("context_windows[]", it.toString()) }
                deploymentIds?.forEach { put("deployment_ids[]", it) }
                endingAt?.let {
                    put("ending_at", DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(it))
                }
                environmentIds?.forEach { put("environment_ids[]", it) }
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
                serviceTiers?.forEach { put("service_tiers[]", it.toString()) }
                sessionIds?.forEach { put("session_ids[]", it) }
                slackChannelIds?.forEach { put("slack_channel_ids[]", it) }
                speeds?.forEach { put("speeds[]", it.toString()) }
                timeBucketWidth?.let { put("time_bucket_width", it.toString()) }
                userProfileIds?.forEach { put("user_profile_ids[]", it) }
                workspaceIds?.forEach { put("workspace_ids[]", it) }
                putAll(additionalQueryParams)
            }
            .build()

    override fun _spanData(): SpanData =
        SpanData(
            name = "anthropic.organization.analytics.cost.list",
            route = "/v1/organizations/analytics/cost",
            queryParams =
                listOf(
                    "actor_ids",
                    "actor_types",
                    "agent_ids",
                    "api_key_ids",
                    "claude_tag_categories",
                    "claude_tag_user_ids",
                    "context_windows",
                    "deployment_ids",
                    "ending_at",
                    "environment_ids",
                    "exclude_deleted_users",
                    "group_by",
                    "inference_geos",
                    "limit",
                    "models",
                    "order",
                    "order_by",
                    "page",
                    "products",
                    "rbac_group_ids",
                    "service_tiers",
                    "session_ids",
                    "slack_channel_ids",
                    "speeds",
                    "starting_at",
                    "time_bucket_width",
                    "user_profile_ids",
                    "workspace_ids",
                ),
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is CostListParams &&
            startingAt == other.startingAt &&
            actorIds == other.actorIds &&
            actorTypes == other.actorTypes &&
            agentIds == other.agentIds &&
            apiKeyIds == other.apiKeyIds &&
            claudeTagCategories == other.claudeTagCategories &&
            claudeTagUserIds == other.claudeTagUserIds &&
            contextWindows == other.contextWindows &&
            deploymentIds == other.deploymentIds &&
            endingAt == other.endingAt &&
            environmentIds == other.environmentIds &&
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
            serviceTiers == other.serviceTiers &&
            sessionIds == other.sessionIds &&
            slackChannelIds == other.slackChannelIds &&
            speeds == other.speeds &&
            timeBucketWidth == other.timeBucketWidth &&
            userProfileIds == other.userProfileIds &&
            workspaceIds == other.workspaceIds &&
            betas == other.betas &&
            additionalHeaders == other.additionalHeaders &&
            additionalQueryParams == other.additionalQueryParams
    }

    override fun hashCode(): Int =
        Objects.hash(
            startingAt,
            actorIds,
            actorTypes,
            agentIds,
            apiKeyIds,
            claudeTagCategories,
            claudeTagUserIds,
            contextWindows,
            deploymentIds,
            endingAt,
            environmentIds,
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
            serviceTiers,
            sessionIds,
            slackChannelIds,
            speeds,
            timeBucketWidth,
            userProfileIds,
            workspaceIds,
            betas,
            additionalHeaders,
            additionalQueryParams,
        )

    override fun toString() =
        "CostListParams{startingAt=$startingAt, actorIds=$actorIds, actorTypes=$actorTypes, agentIds=$agentIds, apiKeyIds=$apiKeyIds, claudeTagCategories=$claudeTagCategories, claudeTagUserIds=$claudeTagUserIds, contextWindows=$contextWindows, deploymentIds=$deploymentIds, endingAt=$endingAt, environmentIds=$environmentIds, excludeDeletedUsers=$excludeDeletedUsers, groupBy=$groupBy, inferenceGeos=$inferenceGeos, limit=$limit, models=$models, order=$order, orderBy=$orderBy, page=$page, products=$products, rbacGroupIds=$rbacGroupIds, serviceTiers=$serviceTiers, sessionIds=$sessionIds, slackChannelIds=$slackChannelIds, speeds=$speeds, timeBucketWidth=$timeBucketWidth, userProfileIds=$userProfileIds, workspaceIds=$workspaceIds, betas=$betas, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
