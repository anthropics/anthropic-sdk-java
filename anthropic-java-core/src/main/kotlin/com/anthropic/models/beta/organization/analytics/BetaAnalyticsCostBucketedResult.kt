package com.anthropic.models.beta.organization.analytics

import com.anthropic.core.Enum
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

class BetaAnalyticsCostBucketedResult
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val amount: JsonField<String>,
    private val claudeTagCategory: JsonField<BetaAnalyticsClaudeTagCategory>,
    private val claudeTagUserId: JsonField<String>,
    private val contextWindow: JsonField<BetaAnalyticsContextWindow>,
    private val costType: JsonField<BetaAnalyticsCostType>,
    private val currency: JsonField<String>,
    private val inferenceGeo: JsonField<InferenceGeo>,
    private val listAmount: JsonField<String>,
    private val model: JsonField<String>,
    private val product: JsonField<String>,
    private val rbacGroupId: JsonField<String>,
    private val requests: JsonField<Long>,
    private val slackChannelId: JsonField<String>,
    private val speed: JsonField<Speed>,
    private val tokenType: JsonField<BetaAnalyticsTokenType>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("amount") @ExcludeMissing amount: JsonField<String> = JsonMissing.of(),
        @JsonProperty("claude_tag_category")
        @ExcludeMissing
        claudeTagCategory: JsonField<BetaAnalyticsClaudeTagCategory> = JsonMissing.of(),
        @JsonProperty("claude_tag_user_id")
        @ExcludeMissing
        claudeTagUserId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("context_window")
        @ExcludeMissing
        contextWindow: JsonField<BetaAnalyticsContextWindow> = JsonMissing.of(),
        @JsonProperty("cost_type")
        @ExcludeMissing
        costType: JsonField<BetaAnalyticsCostType> = JsonMissing.of(),
        @JsonProperty("currency") @ExcludeMissing currency: JsonField<String> = JsonMissing.of(),
        @JsonProperty("inference_geo")
        @ExcludeMissing
        inferenceGeo: JsonField<InferenceGeo> = JsonMissing.of(),
        @JsonProperty("list_amount")
        @ExcludeMissing
        listAmount: JsonField<String> = JsonMissing.of(),
        @JsonProperty("model") @ExcludeMissing model: JsonField<String> = JsonMissing.of(),
        @JsonProperty("product") @ExcludeMissing product: JsonField<String> = JsonMissing.of(),
        @JsonProperty("rbac_group_id")
        @ExcludeMissing
        rbacGroupId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("requests") @ExcludeMissing requests: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("slack_channel_id")
        @ExcludeMissing
        slackChannelId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("speed") @ExcludeMissing speed: JsonField<Speed> = JsonMissing.of(),
        @JsonProperty("token_type")
        @ExcludeMissing
        tokenType: JsonField<BetaAnalyticsTokenType> = JsonMissing.of(),
    ) : this(
        amount,
        claudeTagCategory,
        claudeTagUserId,
        contextWindow,
        costType,
        currency,
        inferenceGeo,
        listAmount,
        model,
        product,
        rbacGroupId,
        requests,
        slackChannelId,
        speed,
        tokenType,
        mutableMapOf(),
    )

    /**
     * Amount (post-discount, pre-credit) in fractional cents.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun amount(): String = amount.getRequired("amount")

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
     * Cost component when `group_by[]=cost_type`; null otherwise (amount is the combined total).
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun costType(): Optional<BetaAnalyticsCostType> = costType.getOptional("cost_type")

    /**
     * Currency code for the cost amount. Currently always `"USD"`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun currency(): String = currency.getRequired("currency")

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
     * List-price amount (pre-discount) in fractional cents.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun listAmount(): String = listAmount.getRequired("list_amount")

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
     * Number of API requests in this row's scope. Null when `group_by` includes `cost_type` or
     * `token_type` (the count has no per-component attribution; read it from the ungrouped
     * response). For sandbox / code-execution events, this counts execution spans rather than HTTP
     * requests (these rows surface with `product: null`).
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun requests(): Optional<Long> = requests.getOptional("requests")

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
     * Token type when `group_by[]=token_type` and `cost_type=tokens`; null otherwise.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun tokenType(): Optional<BetaAnalyticsTokenType> = tokenType.getOptional("token_type")

    /**
     * Returns the raw JSON value of [amount].
     *
     * Unlike [amount], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("amount") @ExcludeMissing fun _amount(): JsonField<String> = amount

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
     * Returns the raw JSON value of [costType].
     *
     * Unlike [costType], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("cost_type")
    @ExcludeMissing
    fun _costType(): JsonField<BetaAnalyticsCostType> = costType

    /**
     * Returns the raw JSON value of [currency].
     *
     * Unlike [currency], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("currency") @ExcludeMissing fun _currency(): JsonField<String> = currency

    /**
     * Returns the raw JSON value of [inferenceGeo].
     *
     * Unlike [inferenceGeo], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("inference_geo")
    @ExcludeMissing
    fun _inferenceGeo(): JsonField<InferenceGeo> = inferenceGeo

    /**
     * Returns the raw JSON value of [listAmount].
     *
     * Unlike [listAmount], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("list_amount") @ExcludeMissing fun _listAmount(): JsonField<String> = listAmount

    /**
     * Returns the raw JSON value of [model].
     *
     * Unlike [model], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("model") @ExcludeMissing fun _model(): JsonField<String> = model

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
     * Returns the raw JSON value of [tokenType].
     *
     * Unlike [tokenType], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("token_type")
    @ExcludeMissing
    fun _tokenType(): JsonField<BetaAnalyticsTokenType> = tokenType

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
         * [BetaAnalyticsCostBucketedResult].
         *
         * The following fields are required:
         * ```java
         * .amount()
         * .claudeTagCategory()
         * .claudeTagUserId()
         * .contextWindow()
         * .costType()
         * .currency()
         * .inferenceGeo()
         * .listAmount()
         * .model()
         * .product()
         * .rbacGroupId()
         * .requests()
         * .slackChannelId()
         * .speed()
         * .tokenType()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaAnalyticsCostBucketedResult]. */
    class Builder internal constructor() {

        private var amount: JsonField<String>? = null
        private var claudeTagCategory: JsonField<BetaAnalyticsClaudeTagCategory>? = null
        private var claudeTagUserId: JsonField<String>? = null
        private var contextWindow: JsonField<BetaAnalyticsContextWindow>? = null
        private var costType: JsonField<BetaAnalyticsCostType>? = null
        private var currency: JsonField<String>? = null
        private var inferenceGeo: JsonField<InferenceGeo>? = null
        private var listAmount: JsonField<String>? = null
        private var model: JsonField<String>? = null
        private var product: JsonField<String>? = null
        private var rbacGroupId: JsonField<String>? = null
        private var requests: JsonField<Long>? = null
        private var slackChannelId: JsonField<String>? = null
        private var speed: JsonField<Speed>? = null
        private var tokenType: JsonField<BetaAnalyticsTokenType>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaAnalyticsCostBucketedResult: BetaAnalyticsCostBucketedResult) =
            apply {
                amount = betaAnalyticsCostBucketedResult.amount
                claudeTagCategory = betaAnalyticsCostBucketedResult.claudeTagCategory
                claudeTagUserId = betaAnalyticsCostBucketedResult.claudeTagUserId
                contextWindow = betaAnalyticsCostBucketedResult.contextWindow
                costType = betaAnalyticsCostBucketedResult.costType
                currency = betaAnalyticsCostBucketedResult.currency
                inferenceGeo = betaAnalyticsCostBucketedResult.inferenceGeo
                listAmount = betaAnalyticsCostBucketedResult.listAmount
                model = betaAnalyticsCostBucketedResult.model
                product = betaAnalyticsCostBucketedResult.product
                rbacGroupId = betaAnalyticsCostBucketedResult.rbacGroupId
                requests = betaAnalyticsCostBucketedResult.requests
                slackChannelId = betaAnalyticsCostBucketedResult.slackChannelId
                speed = betaAnalyticsCostBucketedResult.speed
                tokenType = betaAnalyticsCostBucketedResult.tokenType
                additionalProperties =
                    betaAnalyticsCostBucketedResult.additionalProperties.toMutableMap()
            }

        /** Amount (post-discount, pre-credit) in fractional cents. */
        fun amount(amount: String) = amount(JsonField.of(amount))

        /**
         * Sets [Builder.amount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.amount] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun amount(amount: JsonField<String>) = apply { this.amount = amount }

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
         * Cost component when `group_by[]=cost_type`; null otherwise (amount is the combined
         * total).
         */
        fun costType(costType: BetaAnalyticsCostType?) = costType(JsonField.ofNullable(costType))

        /** Alias for calling [Builder.costType] with `costType.orElse(null)`. */
        fun costType(costType: Optional<BetaAnalyticsCostType>) = costType(costType.getOrNull())

        /**
         * Sets [Builder.costType] to an arbitrary JSON value.
         *
         * You should usually call [Builder.costType] with a well-typed [BetaAnalyticsCostType]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun costType(costType: JsonField<BetaAnalyticsCostType>) = apply {
            this.costType = costType
        }

        /** Currency code for the cost amount. Currently always `"USD"`. */
        fun currency(currency: String) = currency(JsonField.of(currency))

        /**
         * Sets [Builder.currency] to an arbitrary JSON value.
         *
         * You should usually call [Builder.currency] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun currency(currency: JsonField<String>) = apply { this.currency = currency }

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

        /** List-price amount (pre-discount) in fractional cents. */
        fun listAmount(listAmount: String) = listAmount(JsonField.of(listAmount))

        /**
         * Sets [Builder.listAmount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.listAmount] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun listAmount(listAmount: JsonField<String>) = apply { this.listAmount = listAmount }

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
         * Number of API requests in this row's scope. Null when `group_by` includes `cost_type` or
         * `token_type` (the count has no per-component attribution; read it from the ungrouped
         * response). For sandbox / code-execution events, this counts execution spans rather than
         * HTTP requests (these rows surface with `product: null`).
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

        /** Token type when `group_by[]=token_type` and `cost_type=tokens`; null otherwise. */
        fun tokenType(tokenType: BetaAnalyticsTokenType?) =
            tokenType(JsonField.ofNullable(tokenType))

        /** Alias for calling [Builder.tokenType] with `tokenType.orElse(null)`. */
        fun tokenType(tokenType: Optional<BetaAnalyticsTokenType>) =
            tokenType(tokenType.getOrNull())

        /**
         * Sets [Builder.tokenType] to an arbitrary JSON value.
         *
         * You should usually call [Builder.tokenType] with a well-typed [BetaAnalyticsTokenType]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun tokenType(tokenType: JsonField<BetaAnalyticsTokenType>) = apply {
            this.tokenType = tokenType
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
         * Returns an immutable instance of [BetaAnalyticsCostBucketedResult].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .amount()
         * .claudeTagCategory()
         * .claudeTagUserId()
         * .contextWindow()
         * .costType()
         * .currency()
         * .inferenceGeo()
         * .listAmount()
         * .model()
         * .product()
         * .rbacGroupId()
         * .requests()
         * .slackChannelId()
         * .speed()
         * .tokenType()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaAnalyticsCostBucketedResult =
            BetaAnalyticsCostBucketedResult(
                checkRequired("amount", amount),
                checkRequired("claudeTagCategory", claudeTagCategory),
                checkRequired("claudeTagUserId", claudeTagUserId),
                checkRequired("contextWindow", contextWindow),
                checkRequired("costType", costType),
                checkRequired("currency", currency),
                checkRequired("inferenceGeo", inferenceGeo),
                checkRequired("listAmount", listAmount),
                checkRequired("model", model),
                checkRequired("product", product),
                checkRequired("rbacGroupId", rbacGroupId),
                checkRequired("requests", requests),
                checkRequired("slackChannelId", slackChannelId),
                checkRequired("speed", speed),
                checkRequired("tokenType", tokenType),
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
    fun validate(): BetaAnalyticsCostBucketedResult = apply {
        if (validated) {
            return@apply
        }

        amount()
        claudeTagCategory().ifPresent { it.validate() }
        claudeTagUserId()
        contextWindow().ifPresent { it.validate() }
        costType().ifPresent { it.validate() }
        currency()
        inferenceGeo().ifPresent { it.validate() }
        listAmount()
        model()
        product()
        rbacGroupId()
        requests()
        slackChannelId()
        speed().ifPresent { it.validate() }
        tokenType().ifPresent { it.validate() }
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
        (if (amount.asKnown().isPresent) 1 else 0) +
            (claudeTagCategory.asKnown().getOrNull()?.validity() ?: 0) +
            (if (claudeTagUserId.asKnown().isPresent) 1 else 0) +
            (contextWindow.asKnown().getOrNull()?.validity() ?: 0) +
            (costType.asKnown().getOrNull()?.validity() ?: 0) +
            (if (currency.asKnown().isPresent) 1 else 0) +
            (inferenceGeo.asKnown().getOrNull()?.validity() ?: 0) +
            (if (listAmount.asKnown().isPresent) 1 else 0) +
            (if (model.asKnown().isPresent) 1 else 0) +
            (if (product.asKnown().isPresent) 1 else 0) +
            (if (rbacGroupId.asKnown().isPresent) 1 else 0) +
            (if (requests.asKnown().isPresent) 1 else 0) +
            (if (slackChannelId.asKnown().isPresent) 1 else 0) +
            (speed.asKnown().getOrNull()?.validity() ?: 0) +
            (tokenType.asKnown().getOrNull()?.validity() ?: 0)

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

        return other is BetaAnalyticsCostBucketedResult &&
            amount == other.amount &&
            claudeTagCategory == other.claudeTagCategory &&
            claudeTagUserId == other.claudeTagUserId &&
            contextWindow == other.contextWindow &&
            costType == other.costType &&
            currency == other.currency &&
            inferenceGeo == other.inferenceGeo &&
            listAmount == other.listAmount &&
            model == other.model &&
            product == other.product &&
            rbacGroupId == other.rbacGroupId &&
            requests == other.requests &&
            slackChannelId == other.slackChannelId &&
            speed == other.speed &&
            tokenType == other.tokenType &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            amount,
            claudeTagCategory,
            claudeTagUserId,
            contextWindow,
            costType,
            currency,
            inferenceGeo,
            listAmount,
            model,
            product,
            rbacGroupId,
            requests,
            slackChannelId,
            speed,
            tokenType,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaAnalyticsCostBucketedResult{amount=$amount, claudeTagCategory=$claudeTagCategory, claudeTagUserId=$claudeTagUserId, contextWindow=$contextWindow, costType=$costType, currency=$currency, inferenceGeo=$inferenceGeo, listAmount=$listAmount, model=$model, product=$product, rbacGroupId=$rbacGroupId, requests=$requests, slackChannelId=$slackChannelId, speed=$speed, tokenType=$tokenType, additionalProperties=$additionalProperties}"
}
