package com.anthropic.models.beta.organization.analytics

import com.anthropic.core.Enum
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkRequired
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.beta.messages.BetaCacheCreation
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.OffsetDateTime
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

class BetaAnalyticsUsageUsersItem
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val actor: JsonField<BetaAnalyticsUserActor>,
    private val cacheCreation: JsonField<BetaCacheCreation>,
    private val cacheReadInputTokens: JsonField<Long>,
    private val claudeTagCategory: JsonField<BetaAnalyticsClaudeTagCategory>,
    private val claudeTagUserId: JsonField<String>,
    private val contextWindow: JsonField<BetaAnalyticsContextWindow>,
    private val endingAt: JsonField<OffsetDateTime>,
    private val inferenceGeo: JsonField<InferenceGeo>,
    private val model: JsonField<String>,
    private val outputTokens: JsonField<Long>,
    private val product: JsonField<String>,
    private val rbacGroupId: JsonField<String>,
    private val requests: JsonField<Long>,
    private val serverToolUse: JsonField<BetaAnalyticsServerToolUse>,
    private val slackChannelId: JsonField<String>,
    private val speed: JsonField<Speed>,
    private val startingAt: JsonField<OffsetDateTime>,
    private val totalTokens: JsonField<Long>,
    private val uncachedInputTokens: JsonField<Long>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("actor")
        @ExcludeMissing
        actor: JsonField<BetaAnalyticsUserActor> = JsonMissing.of(),
        @JsonProperty("cache_creation")
        @ExcludeMissing
        cacheCreation: JsonField<BetaCacheCreation> = JsonMissing.of(),
        @JsonProperty("cache_read_input_tokens")
        @ExcludeMissing
        cacheReadInputTokens: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("claude_tag_category")
        @ExcludeMissing
        claudeTagCategory: JsonField<BetaAnalyticsClaudeTagCategory> = JsonMissing.of(),
        @JsonProperty("claude_tag_user_id")
        @ExcludeMissing
        claudeTagUserId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("context_window")
        @ExcludeMissing
        contextWindow: JsonField<BetaAnalyticsContextWindow> = JsonMissing.of(),
        @JsonProperty("ending_at")
        @ExcludeMissing
        endingAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("inference_geo")
        @ExcludeMissing
        inferenceGeo: JsonField<InferenceGeo> = JsonMissing.of(),
        @JsonProperty("model") @ExcludeMissing model: JsonField<String> = JsonMissing.of(),
        @JsonProperty("output_tokens")
        @ExcludeMissing
        outputTokens: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("product") @ExcludeMissing product: JsonField<String> = JsonMissing.of(),
        @JsonProperty("rbac_group_id")
        @ExcludeMissing
        rbacGroupId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("requests") @ExcludeMissing requests: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("server_tool_use")
        @ExcludeMissing
        serverToolUse: JsonField<BetaAnalyticsServerToolUse> = JsonMissing.of(),
        @JsonProperty("slack_channel_id")
        @ExcludeMissing
        slackChannelId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("speed") @ExcludeMissing speed: JsonField<Speed> = JsonMissing.of(),
        @JsonProperty("starting_at")
        @ExcludeMissing
        startingAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("total_tokens")
        @ExcludeMissing
        totalTokens: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("uncached_input_tokens")
        @ExcludeMissing
        uncachedInputTokens: JsonField<Long> = JsonMissing.of(),
    ) : this(
        actor,
        cacheCreation,
        cacheReadInputTokens,
        claudeTagCategory,
        claudeTagUserId,
        contextWindow,
        endingAt,
        inferenceGeo,
        model,
        outputTokens,
        product,
        rbacGroupId,
        requests,
        serverToolUse,
        slackChannelId,
        speed,
        startingAt,
        totalTokens,
        uncachedInputTokens,
        mutableMapOf(),
    )

    /**
     * The user this row's usage or cost is attributed to. Always a `user_actor`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun actor(): BetaAnalyticsUserActor = actor.getRequired("actor")

    /**
     * The number of input tokens for cache creation.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun cacheCreation(): BetaCacheCreation = cacheCreation.getRequired("cache_creation")

    /**
     * The number of input tokens read from the cache.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun cacheReadInputTokens(): Long = cacheReadInputTokens.getRequired("cache_read_input_tokens")

    /**
     * Claude Tag (Claude in Slack) spend category: `engaged` (a person addressed Claude in a
     * channel or thread), `proactive` (Claude responded without being addressed), `scheduled` (a
     * scheduled routine ran), `monitoring` (Claude watching a channel it was asked to monitor), or
     * `dm` (direct messages with Claude). Populated only when `claude_tag_category` is in
     * `group_by[]`; null for usage that is not Claude Tag. Direct-message usage is billed to the
     * individual user and is reported under that user's product, not under `claude-tag`. New
     * categories may be added over time.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun claudeTagCategory(): Optional<BetaAnalyticsClaudeTagCategory> =
        claudeTagCategory.getOptional("claude_tag_category")

    /**
     * Slack user ID (for example `U0123ABCDEF`) of the member the Claude Tag (Claude in Slack)
     * usage is attributed to, not a claude.ai user ID. Populated only when `claude_tag_user_id` is
     * in `group_by[]`; null for usage that is not Claude Tag and for Claude Tag usage that is not
     * attributed to a single user (for example `monitoring`, and `proactive` usage Claude
     * initiated), so per-user rows can sum to less than the Claude Tag total. Cannot be combined
     * with `group_by[]=rbac_group_id` or the `rbac_group_ids[]` filter.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun claudeTagUserId(): Optional<String> = claudeTagUserId.getOptional("claude_tag_user_id")

    /**
     * Context-window pricing tier of the usage or cost. Null unless `context_window` is in
     * `group_by[]`; it can also be null on grouped rows with no context-window tier, such as code
     * execution.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun contextWindow(): Optional<BetaAnalyticsContextWindow> =
        contextWindow.getOptional("context_window")

    /**
     * End of the row's UTC time bucket (exclusive), as an RFC 3339 timestamp; equal to
     * `starting_at` plus one `bucket_width`. Null unless `bucket_width` is set.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun endingAt(): Optional<OffsetDateTime> = endingAt.getOptional("ending_at")

    /**
     * Inference region of the usage or cost. Null unless `inference_geo` is in `group_by[]`; it can
     * also be null on grouped rows where the region is not set (the rows that
     * `inference_geos[]=not_available` matches).
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun inferenceGeo(): Optional<InferenceGeo> = inferenceGeo.getOptional("inference_geo")

    /**
     * Model that produced the usage or cost, as a model name in the form the `models[]` filter
     * accepts (for example, `claude-opus-5`). Null unless `model` is in `group_by[]`; it can also
     * be null on grouped rows whose usage or cost is not attributed to a specific model, such as
     * code execution.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun model(): Optional<String> = model.getOptional("model")

    /**
     * The number of output tokens generated.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun outputTokens(): Long = outputTokens.getRequired("output_tokens")

    /**
     * Product surface that produced the usage or cost. Null unless product is in `group_by[]`; it
     * can also be null on grouped rows whose usage cannot be attributed to a known surface. Values
     * include `chat`, `claude_code`, `cowork`, `office_agent`, `claude_in_chrome`, `claude_design`,
     * and `claude-tag`. `claude-tag` is Claude Tag, the Claude product in Slack. Some unattributed
     * usage is reported as "other".
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun product(): Optional<String> = product.getOptional("product")

    /**
     * RBAC group (team) the usage is attributed to, in the public tagged `rbac_group_...` spelling
     * — the same spelling the activity resources use for this key, so the same team has one id
     * across resources and it round-trips as an `rbac_group_ids[]` filter value. Populated only
     * when `rbac_group_id` is in `group_by[]`. Any-membership semantics: a user in several groups
     * contributes their full usage to each of those groups' rows, so the named-group rows overlap
     * and their sum can exceed the org total. A null value is the single unassigned row: users in
     * no group on that (UTC) day. For the true org total, run the same query without `group_by[]`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun rbacGroupId(): Optional<String> = rbacGroupId.getOptional("rbac_group_id")

    /**
     * Number of API requests in this row's scope. For sandbox / code-execution events, this counts
     * execution spans rather than HTTP requests (these rows surface with `product: null`).
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun requests(): Optional<Long> = requests.getOptional("requests")

    /**
     * Server-side tool usage metrics.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun serverToolUse(): BetaAnalyticsServerToolUse = serverToolUse.getRequired("server_tool_use")

    /**
     * Slack channel the usage originated from. Populated only when `slack_channel_id` is in
     * `group_by[]`; null for usage outside Slack (and for rows recorded before channel attribution
     * was enabled).
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun slackChannelId(): Optional<String> = slackChannelId.getOptional("slack_channel_id")

    /**
     * Inference speed mode of the usage or cost: `fast` or `standard`. Null unless `speed` is in
     * `group_by[]`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun speed(): Optional<Speed> = speed.getOptional("speed")

    /**
     * Start of the row's UTC time bucket (inclusive), as an RFC 3339 timestamp. Null unless
     * `bucket_width` is set; without `bucket_width`, each row aggregates the full requested range.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun startingAt(): Optional<OffsetDateTime> = startingAt.getOptional("starting_at")

    /**
     * Total token count across all token types. This is the value the default `order_by`
     * (`total_tokens`) sorts on.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun totalTokens(): Long = totalTokens.getRequired("total_tokens")

    /**
     * The number of uncached input tokens processed.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun uncachedInputTokens(): Long = uncachedInputTokens.getRequired("uncached_input_tokens")

    /**
     * Returns the raw JSON value of [actor].
     *
     * Unlike [actor], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("actor") @ExcludeMissing fun _actor(): JsonField<BetaAnalyticsUserActor> = actor

    /**
     * Returns the raw JSON value of [cacheCreation].
     *
     * Unlike [cacheCreation], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("cache_creation")
    @ExcludeMissing
    fun _cacheCreation(): JsonField<BetaCacheCreation> = cacheCreation

    /**
     * Returns the raw JSON value of [cacheReadInputTokens].
     *
     * Unlike [cacheReadInputTokens], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("cache_read_input_tokens")
    @ExcludeMissing
    fun _cacheReadInputTokens(): JsonField<Long> = cacheReadInputTokens

    /**
     * Returns the raw JSON value of [claudeTagCategory].
     *
     * Unlike [claudeTagCategory], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("claude_tag_category")
    @ExcludeMissing
    fun _claudeTagCategory(): JsonField<BetaAnalyticsClaudeTagCategory> = claudeTagCategory

    /**
     * Returns the raw JSON value of [claudeTagUserId].
     *
     * Unlike [claudeTagUserId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("claude_tag_user_id")
    @ExcludeMissing
    fun _claudeTagUserId(): JsonField<String> = claudeTagUserId

    /**
     * Returns the raw JSON value of [contextWindow].
     *
     * Unlike [contextWindow], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("context_window")
    @ExcludeMissing
    fun _contextWindow(): JsonField<BetaAnalyticsContextWindow> = contextWindow

    /**
     * Returns the raw JSON value of [endingAt].
     *
     * Unlike [endingAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("ending_at") @ExcludeMissing fun _endingAt(): JsonField<OffsetDateTime> = endingAt

    /**
     * Returns the raw JSON value of [inferenceGeo].
     *
     * Unlike [inferenceGeo], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("inference_geo")
    @ExcludeMissing
    fun _inferenceGeo(): JsonField<InferenceGeo> = inferenceGeo

    /**
     * Returns the raw JSON value of [model].
     *
     * Unlike [model], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("model") @ExcludeMissing fun _model(): JsonField<String> = model

    /**
     * Returns the raw JSON value of [outputTokens].
     *
     * Unlike [outputTokens], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("output_tokens")
    @ExcludeMissing
    fun _outputTokens(): JsonField<Long> = outputTokens

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
     * Returns the raw JSON value of [requests].
     *
     * Unlike [requests], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("requests") @ExcludeMissing fun _requests(): JsonField<Long> = requests

    /**
     * Returns the raw JSON value of [serverToolUse].
     *
     * Unlike [serverToolUse], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("server_tool_use")
    @ExcludeMissing
    fun _serverToolUse(): JsonField<BetaAnalyticsServerToolUse> = serverToolUse

    /**
     * Returns the raw JSON value of [slackChannelId].
     *
     * Unlike [slackChannelId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("slack_channel_id")
    @ExcludeMissing
    fun _slackChannelId(): JsonField<String> = slackChannelId

    /**
     * Returns the raw JSON value of [speed].
     *
     * Unlike [speed], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("speed") @ExcludeMissing fun _speed(): JsonField<Speed> = speed

    /**
     * Returns the raw JSON value of [startingAt].
     *
     * Unlike [startingAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("starting_at")
    @ExcludeMissing
    fun _startingAt(): JsonField<OffsetDateTime> = startingAt

    /**
     * Returns the raw JSON value of [totalTokens].
     *
     * Unlike [totalTokens], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("total_tokens") @ExcludeMissing fun _totalTokens(): JsonField<Long> = totalTokens

    /**
     * Returns the raw JSON value of [uncachedInputTokens].
     *
     * Unlike [uncachedInputTokens], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("uncached_input_tokens")
    @ExcludeMissing
    fun _uncachedInputTokens(): JsonField<Long> = uncachedInputTokens

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
         * Returns a mutable builder for constructing an instance of [BetaAnalyticsUsageUsersItem].
         *
         * The following fields are required:
         * ```java
         * .actor()
         * .cacheCreation()
         * .cacheReadInputTokens()
         * .claudeTagCategory()
         * .claudeTagUserId()
         * .contextWindow()
         * .endingAt()
         * .inferenceGeo()
         * .model()
         * .outputTokens()
         * .product()
         * .rbacGroupId()
         * .requests()
         * .serverToolUse()
         * .slackChannelId()
         * .speed()
         * .startingAt()
         * .totalTokens()
         * .uncachedInputTokens()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaAnalyticsUsageUsersItem]. */
    class Builder internal constructor() {

        private var actor: JsonField<BetaAnalyticsUserActor>? = null
        private var cacheCreation: JsonField<BetaCacheCreation>? = null
        private var cacheReadInputTokens: JsonField<Long>? = null
        private var claudeTagCategory: JsonField<BetaAnalyticsClaudeTagCategory>? = null
        private var claudeTagUserId: JsonField<String>? = null
        private var contextWindow: JsonField<BetaAnalyticsContextWindow>? = null
        private var endingAt: JsonField<OffsetDateTime>? = null
        private var inferenceGeo: JsonField<InferenceGeo>? = null
        private var model: JsonField<String>? = null
        private var outputTokens: JsonField<Long>? = null
        private var product: JsonField<String>? = null
        private var rbacGroupId: JsonField<String>? = null
        private var requests: JsonField<Long>? = null
        private var serverToolUse: JsonField<BetaAnalyticsServerToolUse>? = null
        private var slackChannelId: JsonField<String>? = null
        private var speed: JsonField<Speed>? = null
        private var startingAt: JsonField<OffsetDateTime>? = null
        private var totalTokens: JsonField<Long>? = null
        private var uncachedInputTokens: JsonField<Long>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaAnalyticsUsageUsersItem: BetaAnalyticsUsageUsersItem) = apply {
            actor = betaAnalyticsUsageUsersItem.actor
            cacheCreation = betaAnalyticsUsageUsersItem.cacheCreation
            cacheReadInputTokens = betaAnalyticsUsageUsersItem.cacheReadInputTokens
            claudeTagCategory = betaAnalyticsUsageUsersItem.claudeTagCategory
            claudeTagUserId = betaAnalyticsUsageUsersItem.claudeTagUserId
            contextWindow = betaAnalyticsUsageUsersItem.contextWindow
            endingAt = betaAnalyticsUsageUsersItem.endingAt
            inferenceGeo = betaAnalyticsUsageUsersItem.inferenceGeo
            model = betaAnalyticsUsageUsersItem.model
            outputTokens = betaAnalyticsUsageUsersItem.outputTokens
            product = betaAnalyticsUsageUsersItem.product
            rbacGroupId = betaAnalyticsUsageUsersItem.rbacGroupId
            requests = betaAnalyticsUsageUsersItem.requests
            serverToolUse = betaAnalyticsUsageUsersItem.serverToolUse
            slackChannelId = betaAnalyticsUsageUsersItem.slackChannelId
            speed = betaAnalyticsUsageUsersItem.speed
            startingAt = betaAnalyticsUsageUsersItem.startingAt
            totalTokens = betaAnalyticsUsageUsersItem.totalTokens
            uncachedInputTokens = betaAnalyticsUsageUsersItem.uncachedInputTokens
            additionalProperties = betaAnalyticsUsageUsersItem.additionalProperties.toMutableMap()
        }

        /** The user this row's usage or cost is attributed to. Always a `user_actor`. */
        fun actor(actor: BetaAnalyticsUserActor) = actor(JsonField.of(actor))

        /**
         * Sets [Builder.actor] to an arbitrary JSON value.
         *
         * You should usually call [Builder.actor] with a well-typed [BetaAnalyticsUserActor] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun actor(actor: JsonField<BetaAnalyticsUserActor>) = apply { this.actor = actor }

        /** The number of input tokens for cache creation. */
        fun cacheCreation(cacheCreation: BetaCacheCreation) =
            cacheCreation(JsonField.of(cacheCreation))

        /**
         * Sets [Builder.cacheCreation] to an arbitrary JSON value.
         *
         * You should usually call [Builder.cacheCreation] with a well-typed [BetaCacheCreation]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun cacheCreation(cacheCreation: JsonField<BetaCacheCreation>) = apply {
            this.cacheCreation = cacheCreation
        }

        /** The number of input tokens read from the cache. */
        fun cacheReadInputTokens(cacheReadInputTokens: Long) =
            cacheReadInputTokens(JsonField.of(cacheReadInputTokens))

        /**
         * Sets [Builder.cacheReadInputTokens] to an arbitrary JSON value.
         *
         * You should usually call [Builder.cacheReadInputTokens] with a well-typed [Long] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun cacheReadInputTokens(cacheReadInputTokens: JsonField<Long>) = apply {
            this.cacheReadInputTokens = cacheReadInputTokens
        }

        /**
         * Claude Tag (Claude in Slack) spend category: `engaged` (a person addressed Claude in a
         * channel or thread), `proactive` (Claude responded without being addressed), `scheduled`
         * (a scheduled routine ran), `monitoring` (Claude watching a channel it was asked to
         * monitor), or `dm` (direct messages with Claude). Populated only when
         * `claude_tag_category` is in `group_by[]`; null for usage that is not Claude Tag.
         * Direct-message usage is billed to the individual user and is reported under that user's
         * product, not under `claude-tag`. New categories may be added over time.
         */
        fun claudeTagCategory(claudeTagCategory: BetaAnalyticsClaudeTagCategory?) =
            claudeTagCategory(JsonField.ofNullable(claudeTagCategory))

        /** Alias for calling [Builder.claudeTagCategory] with `claudeTagCategory.orElse(null)`. */
        fun claudeTagCategory(claudeTagCategory: Optional<BetaAnalyticsClaudeTagCategory>) =
            claudeTagCategory(claudeTagCategory.getOrNull())

        /**
         * Sets [Builder.claudeTagCategory] to an arbitrary JSON value.
         *
         * You should usually call [Builder.claudeTagCategory] with a well-typed
         * [BetaAnalyticsClaudeTagCategory] value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun claudeTagCategory(claudeTagCategory: JsonField<BetaAnalyticsClaudeTagCategory>) =
            apply {
                this.claudeTagCategory = claudeTagCategory
            }

        /**
         * Slack user ID (for example `U0123ABCDEF`) of the member the Claude Tag (Claude in Slack)
         * usage is attributed to, not a claude.ai user ID. Populated only when `claude_tag_user_id`
         * is in `group_by[]`; null for usage that is not Claude Tag and for Claude Tag usage that
         * is not attributed to a single user (for example `monitoring`, and `proactive` usage
         * Claude initiated), so per-user rows can sum to less than the Claude Tag total. Cannot be
         * combined with `group_by[]=rbac_group_id` or the `rbac_group_ids[]` filter.
         */
        fun claudeTagUserId(claudeTagUserId: String?) =
            claudeTagUserId(JsonField.ofNullable(claudeTagUserId))

        /** Alias for calling [Builder.claudeTagUserId] with `claudeTagUserId.orElse(null)`. */
        fun claudeTagUserId(claudeTagUserId: Optional<String>) =
            claudeTagUserId(claudeTagUserId.getOrNull())

        /**
         * Sets [Builder.claudeTagUserId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.claudeTagUserId] with a well-typed [String] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun claudeTagUserId(claudeTagUserId: JsonField<String>) = apply {
            this.claudeTagUserId = claudeTagUserId
        }

        /**
         * Context-window pricing tier of the usage or cost. Null unless `context_window` is in
         * `group_by[]`; it can also be null on grouped rows with no context-window tier, such as
         * code execution.
         */
        fun contextWindow(contextWindow: BetaAnalyticsContextWindow?) =
            contextWindow(JsonField.ofNullable(contextWindow))

        /** Alias for calling [Builder.contextWindow] with `contextWindow.orElse(null)`. */
        fun contextWindow(contextWindow: Optional<BetaAnalyticsContextWindow>) =
            contextWindow(contextWindow.getOrNull())

        /**
         * Sets [Builder.contextWindow] to an arbitrary JSON value.
         *
         * You should usually call [Builder.contextWindow] with a well-typed
         * [BetaAnalyticsContextWindow] value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun contextWindow(contextWindow: JsonField<BetaAnalyticsContextWindow>) = apply {
            this.contextWindow = contextWindow
        }

        /**
         * End of the row's UTC time bucket (exclusive), as an RFC 3339 timestamp; equal to
         * `starting_at` plus one `bucket_width`. Null unless `bucket_width` is set.
         */
        fun endingAt(endingAt: OffsetDateTime?) = endingAt(JsonField.ofNullable(endingAt))

        /** Alias for calling [Builder.endingAt] with `endingAt.orElse(null)`. */
        fun endingAt(endingAt: Optional<OffsetDateTime>) = endingAt(endingAt.getOrNull())

        /**
         * Sets [Builder.endingAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.endingAt] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun endingAt(endingAt: JsonField<OffsetDateTime>) = apply { this.endingAt = endingAt }

        /**
         * Inference region of the usage or cost. Null unless `inference_geo` is in `group_by[]`; it
         * can also be null on grouped rows where the region is not set (the rows that
         * `inference_geos[]=not_available` matches).
         */
        fun inferenceGeo(inferenceGeo: InferenceGeo?) =
            inferenceGeo(JsonField.ofNullable(inferenceGeo))

        /** Alias for calling [Builder.inferenceGeo] with `inferenceGeo.orElse(null)`. */
        fun inferenceGeo(inferenceGeo: Optional<InferenceGeo>) =
            inferenceGeo(inferenceGeo.getOrNull())

        /**
         * Sets [Builder.inferenceGeo] to an arbitrary JSON value.
         *
         * You should usually call [Builder.inferenceGeo] with a well-typed [InferenceGeo] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun inferenceGeo(inferenceGeo: JsonField<InferenceGeo>) = apply {
            this.inferenceGeo = inferenceGeo
        }

        /**
         * Model that produced the usage or cost, as a model name in the form the `models[]` filter
         * accepts (for example, `claude-opus-5`). Null unless `model` is in `group_by[]`; it can
         * also be null on grouped rows whose usage or cost is not attributed to a specific model,
         * such as code execution.
         */
        fun model(model: String?) = model(JsonField.ofNullable(model))

        /** Alias for calling [Builder.model] with `model.orElse(null)`. */
        fun model(model: Optional<String>) = model(model.getOrNull())

        /**
         * Sets [Builder.model] to an arbitrary JSON value.
         *
         * You should usually call [Builder.model] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun model(model: JsonField<String>) = apply { this.model = model }

        /** The number of output tokens generated. */
        fun outputTokens(outputTokens: Long) = outputTokens(JsonField.of(outputTokens))

        /**
         * Sets [Builder.outputTokens] to an arbitrary JSON value.
         *
         * You should usually call [Builder.outputTokens] with a well-typed [Long] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun outputTokens(outputTokens: JsonField<Long>) = apply { this.outputTokens = outputTokens }

        /**
         * Product surface that produced the usage or cost. Null unless product is in `group_by[]`;
         * it can also be null on grouped rows whose usage cannot be attributed to a known surface.
         * Values include `chat`, `claude_code`, `cowork`, `office_agent`, `claude_in_chrome`,
         * `claude_design`, and `claude-tag`. `claude-tag` is Claude Tag, the Claude product in
         * Slack. Some unattributed usage is reported as "other".
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
         * RBAC group (team) the usage is attributed to, in the public tagged `rbac_group_...`
         * spelling — the same spelling the activity resources use for this key, so the same team
         * has one id across resources and it round-trips as an `rbac_group_ids[]` filter value.
         * Populated only when `rbac_group_id` is in `group_by[]`. Any-membership semantics: a user
         * in several groups contributes their full usage to each of those groups' rows, so the
         * named-group rows overlap and their sum can exceed the org total. A null value is the
         * single unassigned row: users in no group on that (UTC) day. For the true org total, run
         * the same query without `group_by[]`.
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
         * Number of API requests in this row's scope. For sandbox / code-execution events, this
         * counts execution spans rather than HTTP requests (these rows surface with `product:
         * null`).
         */
        fun requests(requests: Long?) = requests(JsonField.ofNullable(requests))

        /**
         * Alias for [Builder.requests].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun requests(requests: Long) = requests(requests as Long?)

        /** Alias for calling [Builder.requests] with `requests.orElse(null)`. */
        fun requests(requests: Optional<Long>) = requests(requests.getOrNull())

        /**
         * Sets [Builder.requests] to an arbitrary JSON value.
         *
         * You should usually call [Builder.requests] with a well-typed [Long] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun requests(requests: JsonField<Long>) = apply { this.requests = requests }

        /** Server-side tool usage metrics. */
        fun serverToolUse(serverToolUse: BetaAnalyticsServerToolUse) =
            serverToolUse(JsonField.of(serverToolUse))

        /**
         * Sets [Builder.serverToolUse] to an arbitrary JSON value.
         *
         * You should usually call [Builder.serverToolUse] with a well-typed
         * [BetaAnalyticsServerToolUse] value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun serverToolUse(serverToolUse: JsonField<BetaAnalyticsServerToolUse>) = apply {
            this.serverToolUse = serverToolUse
        }

        /**
         * Slack channel the usage originated from. Populated only when `slack_channel_id` is in
         * `group_by[]`; null for usage outside Slack (and for rows recorded before channel
         * attribution was enabled).
         */
        fun slackChannelId(slackChannelId: String?) =
            slackChannelId(JsonField.ofNullable(slackChannelId))

        /** Alias for calling [Builder.slackChannelId] with `slackChannelId.orElse(null)`. */
        fun slackChannelId(slackChannelId: Optional<String>) =
            slackChannelId(slackChannelId.getOrNull())

        /**
         * Sets [Builder.slackChannelId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.slackChannelId] with a well-typed [String] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun slackChannelId(slackChannelId: JsonField<String>) = apply {
            this.slackChannelId = slackChannelId
        }

        /**
         * Inference speed mode of the usage or cost: `fast` or `standard`. Null unless `speed` is
         * in `group_by[]`.
         */
        fun speed(speed: Speed?) = speed(JsonField.ofNullable(speed))

        /** Alias for calling [Builder.speed] with `speed.orElse(null)`. */
        fun speed(speed: Optional<Speed>) = speed(speed.getOrNull())

        /**
         * Sets [Builder.speed] to an arbitrary JSON value.
         *
         * You should usually call [Builder.speed] with a well-typed [Speed] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun speed(speed: JsonField<Speed>) = apply { this.speed = speed }

        /**
         * Start of the row's UTC time bucket (inclusive), as an RFC 3339 timestamp. Null unless
         * `bucket_width` is set; without `bucket_width`, each row aggregates the full requested
         * range.
         */
        fun startingAt(startingAt: OffsetDateTime?) = startingAt(JsonField.ofNullable(startingAt))

        /** Alias for calling [Builder.startingAt] with `startingAt.orElse(null)`. */
        fun startingAt(startingAt: Optional<OffsetDateTime>) = startingAt(startingAt.getOrNull())

        /**
         * Sets [Builder.startingAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.startingAt] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun startingAt(startingAt: JsonField<OffsetDateTime>) = apply {
            this.startingAt = startingAt
        }

        /**
         * Total token count across all token types. This is the value the default `order_by`
         * (`total_tokens`) sorts on.
         */
        fun totalTokens(totalTokens: Long) = totalTokens(JsonField.of(totalTokens))

        /**
         * Sets [Builder.totalTokens] to an arbitrary JSON value.
         *
         * You should usually call [Builder.totalTokens] with a well-typed [Long] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun totalTokens(totalTokens: JsonField<Long>) = apply { this.totalTokens = totalTokens }

        /** The number of uncached input tokens processed. */
        fun uncachedInputTokens(uncachedInputTokens: Long) =
            uncachedInputTokens(JsonField.of(uncachedInputTokens))

        /**
         * Sets [Builder.uncachedInputTokens] to an arbitrary JSON value.
         *
         * You should usually call [Builder.uncachedInputTokens] with a well-typed [Long] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun uncachedInputTokens(uncachedInputTokens: JsonField<Long>) = apply {
            this.uncachedInputTokens = uncachedInputTokens
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
         * Returns an immutable instance of [BetaAnalyticsUsageUsersItem].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .actor()
         * .cacheCreation()
         * .cacheReadInputTokens()
         * .claudeTagCategory()
         * .claudeTagUserId()
         * .contextWindow()
         * .endingAt()
         * .inferenceGeo()
         * .model()
         * .outputTokens()
         * .product()
         * .rbacGroupId()
         * .requests()
         * .serverToolUse()
         * .slackChannelId()
         * .speed()
         * .startingAt()
         * .totalTokens()
         * .uncachedInputTokens()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaAnalyticsUsageUsersItem =
            BetaAnalyticsUsageUsersItem(
                checkRequired("actor", actor),
                checkRequired("cacheCreation", cacheCreation),
                checkRequired("cacheReadInputTokens", cacheReadInputTokens),
                checkRequired("claudeTagCategory", claudeTagCategory),
                checkRequired("claudeTagUserId", claudeTagUserId),
                checkRequired("contextWindow", contextWindow),
                checkRequired("endingAt", endingAt),
                checkRequired("inferenceGeo", inferenceGeo),
                checkRequired("model", model),
                checkRequired("outputTokens", outputTokens),
                checkRequired("product", product),
                checkRequired("rbacGroupId", rbacGroupId),
                checkRequired("requests", requests),
                checkRequired("serverToolUse", serverToolUse),
                checkRequired("slackChannelId", slackChannelId),
                checkRequired("speed", speed),
                checkRequired("startingAt", startingAt),
                checkRequired("totalTokens", totalTokens),
                checkRequired("uncachedInputTokens", uncachedInputTokens),
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
    fun validate(): BetaAnalyticsUsageUsersItem = apply {
        if (validated) {
            return@apply
        }

        actor().validate()
        cacheCreation().validate()
        cacheReadInputTokens()
        claudeTagCategory().ifPresent { it.validate() }
        claudeTagUserId()
        contextWindow().ifPresent { it.validate() }
        endingAt()
        inferenceGeo().ifPresent { it.validate() }
        model()
        outputTokens()
        product()
        rbacGroupId()
        requests()
        serverToolUse().validate()
        slackChannelId()
        speed().ifPresent { it.validate() }
        startingAt()
        totalTokens()
        uncachedInputTokens()
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
        (actor.asKnown().getOrNull()?.validity() ?: 0) +
            (cacheCreation.asKnown().getOrNull()?.validity() ?: 0) +
            (if (cacheReadInputTokens.asKnown().isPresent) 1 else 0) +
            (claudeTagCategory.asKnown().getOrNull()?.validity() ?: 0) +
            (if (claudeTagUserId.asKnown().isPresent) 1 else 0) +
            (contextWindow.asKnown().getOrNull()?.validity() ?: 0) +
            (if (endingAt.asKnown().isPresent) 1 else 0) +
            (inferenceGeo.asKnown().getOrNull()?.validity() ?: 0) +
            (if (model.asKnown().isPresent) 1 else 0) +
            (if (outputTokens.asKnown().isPresent) 1 else 0) +
            (if (product.asKnown().isPresent) 1 else 0) +
            (if (rbacGroupId.asKnown().isPresent) 1 else 0) +
            (if (requests.asKnown().isPresent) 1 else 0) +
            (serverToolUse.asKnown().getOrNull()?.validity() ?: 0) +
            (if (slackChannelId.asKnown().isPresent) 1 else 0) +
            (speed.asKnown().getOrNull()?.validity() ?: 0) +
            (if (startingAt.asKnown().isPresent) 1 else 0) +
            (if (totalTokens.asKnown().isPresent) 1 else 0) +
            (if (uncachedInputTokens.asKnown().isPresent) 1 else 0)

    /**
     * Inference region of the usage or cost. Null unless `inference_geo` is in `group_by[]`; it can
     * also be null on grouped rows where the region is not set (the rows that
     * `inference_geos[]=not_available` matches).
     */
    class InferenceGeo private constructor(private val value: JsonField<String>) : Enum {

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

            @JvmField val GLOBAL = InferenceGeo(JsonField.of("global"))

            @JvmField val US = InferenceGeo(JsonField.of("us"))

            @JvmStatic
            fun of(value: String): InferenceGeo =
                // Intern known values so `==` works
                when (value) {
                    "global" -> GLOBAL
                    "us" -> US
                    else -> InferenceGeo(JsonField.of(value))
                }

            @JsonCreator
            @JvmStatic
            fun of(value: JsonField<String>): InferenceGeo =
                value.asString().getOrNull()?.let { of(it) } ?: InferenceGeo(value)
        }

        /** An enum containing [InferenceGeo]'s known values. */
        enum class Known {
            GLOBAL,
            US,
        }

        /**
         * An enum containing [InferenceGeo]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [InferenceGeo] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            GLOBAL,
            US,
            /**
             * An enum member indicating that [InferenceGeo] was instantiated with an unknown value.
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
                GLOBAL -> Value.GLOBAL
                US -> Value.US
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
                GLOBAL -> Known.GLOBAL
                US -> Known.US
                else -> throw AnthropicInvalidDataException("Unknown InferenceGeo: $value")
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
        fun validate(): InferenceGeo = apply {
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

            return other is InferenceGeo && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    /**
     * Inference speed mode of the usage or cost: `fast` or `standard`. Null unless `speed` is in
     * `group_by[]`.
     */
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

        return other is BetaAnalyticsUsageUsersItem &&
            actor == other.actor &&
            cacheCreation == other.cacheCreation &&
            cacheReadInputTokens == other.cacheReadInputTokens &&
            claudeTagCategory == other.claudeTagCategory &&
            claudeTagUserId == other.claudeTagUserId &&
            contextWindow == other.contextWindow &&
            endingAt == other.endingAt &&
            inferenceGeo == other.inferenceGeo &&
            model == other.model &&
            outputTokens == other.outputTokens &&
            product == other.product &&
            rbacGroupId == other.rbacGroupId &&
            requests == other.requests &&
            serverToolUse == other.serverToolUse &&
            slackChannelId == other.slackChannelId &&
            speed == other.speed &&
            startingAt == other.startingAt &&
            totalTokens == other.totalTokens &&
            uncachedInputTokens == other.uncachedInputTokens &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            actor,
            cacheCreation,
            cacheReadInputTokens,
            claudeTagCategory,
            claudeTagUserId,
            contextWindow,
            endingAt,
            inferenceGeo,
            model,
            outputTokens,
            product,
            rbacGroupId,
            requests,
            serverToolUse,
            slackChannelId,
            speed,
            startingAt,
            totalTokens,
            uncachedInputTokens,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaAnalyticsUsageUsersItem{actor=$actor, cacheCreation=$cacheCreation, cacheReadInputTokens=$cacheReadInputTokens, claudeTagCategory=$claudeTagCategory, claudeTagUserId=$claudeTagUserId, contextWindow=$contextWindow, endingAt=$endingAt, inferenceGeo=$inferenceGeo, model=$model, outputTokens=$outputTokens, product=$product, rbacGroupId=$rbacGroupId, requests=$requests, serverToolUse=$serverToolUse, slackChannelId=$slackChannelId, speed=$speed, startingAt=$startingAt, totalTokens=$totalTokens, uncachedInputTokens=$uncachedInputTokens, additionalProperties=$additionalProperties}"
}
