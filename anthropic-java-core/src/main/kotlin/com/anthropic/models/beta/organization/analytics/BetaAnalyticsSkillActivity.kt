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

/** Per-skill activity data for a given day. */
class BetaAnalyticsSkillActivity
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val chatMetrics: JsonField<BetaAnalyticsSkillChatMetrics>,
    private val claudeCodeMetrics: JsonField<BetaAnalyticsSkillClaudeCodeMetrics>,
    private val coworkMetrics: JsonField<BetaAnalyticsSkillCoworkMetrics>,
    private val distinctUserCount: JsonField<Long>,
    private val officeMetrics: JsonField<BetaAnalyticsSkillOfficeMetrics>,
    private val skillName: JsonField<String>,
    private val attributedListPrice: JsonField<String>,
    private val chatCoworkUnifiedMetrics: JsonField<ChatCoworkUnifiedMetrics>,
    private val currency: JsonField<String>,
    private val enableCount: JsonField<Long>,
    private val estimatedOverageSpend: JsonField<String>,
    private val invocationCount: JsonField<Long>,
    private val product: JsonField<String>,
    private val rbacGroupId: JsonField<String>,
    private val rbacGroupName: JsonField<String>,
    private val shareStatus: JsonField<ShareStatus>,
    private val skillDisplayName: JsonField<String>,
    private val userId: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("chat_metrics")
        @ExcludeMissing
        chatMetrics: JsonField<BetaAnalyticsSkillChatMetrics> = JsonMissing.of(),
        @JsonProperty("claude_code_metrics")
        @ExcludeMissing
        claudeCodeMetrics: JsonField<BetaAnalyticsSkillClaudeCodeMetrics> = JsonMissing.of(),
        @JsonProperty("cowork_metrics")
        @ExcludeMissing
        coworkMetrics: JsonField<BetaAnalyticsSkillCoworkMetrics> = JsonMissing.of(),
        @JsonProperty("distinct_user_count")
        @ExcludeMissing
        distinctUserCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("office_metrics")
        @ExcludeMissing
        officeMetrics: JsonField<BetaAnalyticsSkillOfficeMetrics> = JsonMissing.of(),
        @JsonProperty("skill_name") @ExcludeMissing skillName: JsonField<String> = JsonMissing.of(),
        @JsonProperty("attributed_list_price")
        @ExcludeMissing
        attributedListPrice: JsonField<String> = JsonMissing.of(),
        @JsonProperty("chat_cowork_unified_metrics")
        @ExcludeMissing
        chatCoworkUnifiedMetrics: JsonField<ChatCoworkUnifiedMetrics> = JsonMissing.of(),
        @JsonProperty("currency") @ExcludeMissing currency: JsonField<String> = JsonMissing.of(),
        @JsonProperty("enable_count")
        @ExcludeMissing
        enableCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("estimated_overage_spend")
        @ExcludeMissing
        estimatedOverageSpend: JsonField<String> = JsonMissing.of(),
        @JsonProperty("invocation_count")
        @ExcludeMissing
        invocationCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("product") @ExcludeMissing product: JsonField<String> = JsonMissing.of(),
        @JsonProperty("rbac_group_id")
        @ExcludeMissing
        rbacGroupId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("rbac_group_name")
        @ExcludeMissing
        rbacGroupName: JsonField<String> = JsonMissing.of(),
        @JsonProperty("share_status")
        @ExcludeMissing
        shareStatus: JsonField<ShareStatus> = JsonMissing.of(),
        @JsonProperty("skill_display_name")
        @ExcludeMissing
        skillDisplayName: JsonField<String> = JsonMissing.of(),
        @JsonProperty("user_id") @ExcludeMissing userId: JsonField<String> = JsonMissing.of(),
    ) : this(
        chatMetrics,
        claudeCodeMetrics,
        coworkMetrics,
        distinctUserCount,
        officeMetrics,
        skillName,
        attributedListPrice,
        chatCoworkUnifiedMetrics,
        currency,
        enableCount,
        estimatedOverageSpend,
        invocationCount,
        product,
        rbacGroupId,
        rbacGroupName,
        shareStatus,
        skillDisplayName,
        userId,
        mutableMapOf(),
    )

    /**
     * Claude.ai activity metrics for a single skill on a given day.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun chatMetrics(): BetaAnalyticsSkillChatMetrics = chatMetrics.getRequired("chat_metrics")

    /**
     * Claude Code activity metrics for a single skill on a given day.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun claudeCodeMetrics(): BetaAnalyticsSkillClaudeCodeMetrics =
        claudeCodeMetrics.getRequired("claude_code_metrics")

    /**
     * Cowork activity metrics for a single skill on a given day.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun coworkMetrics(): BetaAnalyticsSkillCoworkMetrics =
        coworkMetrics.getRequired("cowork_metrics")

    /**
     * Number of distinct users who used the skill on the requested day, or, in date-range mode,
     * over the requested window — recomputed as an exact distinct count over the window's
     * per-member daily rows, never a sum of per-day values. A skill counts as used only when it is
     * explicitly activated — the model (or the user, via the skill's slash command) invokes it,
     * reading its instructions into context as part of that activation. Skills that are merely
     * installed or listed as available, or whose content reaches the context without an activation
     * (preloaded, hook-injected, or read as a plain file), are not counted.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun distinctUserCount(): Long = distinctUserCount.getRequired("distinct_user_count")

    /**
     * Office Agent activity metrics for a single skill on a given day, broken out by Office
     * product.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun officeMetrics(): BetaAnalyticsSkillOfficeMetrics =
        officeMetrics.getRequired("office_metrics")

    /**
     * Name of the skill
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun skillName(): String = skillName.getRequired("skill_name")

    /**
     * List-price (rate-card) value of the member requests attributed to this skill, as a decimal
     * string in the minor unit of `currency` (cents for USD), from Claude Code, Cowork, and Office
     * Agent request-level attribution — the value of requests that involved the skill, not the
     * skill's incremental cost. Unlike `estimated_overage_spend` this reflects usage value
     * regardless of how it was funded — seat-covered usage counts — but it is undiscounted and does
     * not tie to billed spend or the organization's spend reporting. claude.ai chat usage carries
     * no request-level attribution and contributes nothing: the field is null on `chat` product
     * rows and on `office_agent` product cuts dated before 2026-06-18 (the Office Agent attribution
     * data-start), and on ungrouped rows it covers the Claude Code + Cowork + Office Agent share
     * only (null when no attributable usage exists). Also null under the same conditions as
     * `estimated_overage_spend` (spend reporting not enabled for this organization, `office_agent`
     * product cuts before the 2026-06-18 data-start). "0" means attributable usage existed but none
     * was attributed to this skill. Addable across days: date-range rollup mode returns the
     * window's sum. On `group_by[]` and `filter[]` shapes both amounts can total below the
     * ungrouped value for the same skill over the same date or range: spend attributed to a
     * member–skill pair with no counted usage on that day is excluded from those cuts.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun attributedListPrice(): Optional<String> =
        attributedListPrice.getOptional("attributed_list_price")

    /**
     * Skill use recorded while members had Chat and Cowork unified (Cowork's features inside
     * claude.ai chat) turned on, split into chat conversations and Cowork sessions. A count is null
     * in date-range mode where it cannot be computed. Omitted from the response on deployments that
     * do not offer Chat and Cowork unified.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun chatCoworkUnifiedMetrics(): Optional<ChatCoworkUnifiedMetrics> =
        chatCoworkUnifiedMetrics.getOptional("chat_cowork_unified_metrics")

    /**
     * Currency for this row's monetary fields (`estimated_overage_spend` and
     * `attributed_list_price`), as an uppercase ISO-4217 code. Always "USD" when either amount is
     * populated; null whenever both amounts are null.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun currency(): Optional<String> = currency.getOptional("currency")

    /**
     * Distinct accounts that enabled this skill on the requested day (claude.ai only — the skill
     * analog of plugin `install_count`). The count is org-wide: null when enable reporting is not
     * enabled for this organization, or when the request scopes to `user_id` / `rbac_group_id` /
     * `product` via `group_by[]` or `filter[]` (an org-wide count would be misleading on per-cut
     * rows). A distinct count, not an event count: summing across days double-counts members who
     * enable the skill on more than one day, so it is also null in date-range rollup mode
     * (`starting_date`/`ending_date`).
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun enableCount(): Optional<Long> = enableCount.getOptional("enable_count")

    /**
     * Estimated overage spend attributed to this skill, as a decimal string in the minor unit of
     * `currency` (cents for USD; "1250" is $12.50, fractional cents possible) — an allocation of
     * each member's daily post-discount, pre-credit metered overage spend (the same cost basis as
     * the organization's spend reporting and the Cost & Usage API, so per-skill figures are
     * directly comparable; spend with no skill attribution — including any member-day without skill
     * invocations — is not represented, so skill rows sum to at most those totals) across the
     * skills the member used. Overage only: usage covered by included seat allowances bills nothing
     * and allocates $0 here — see `attributed_list_price` for the funding-independent usage-value
     * companion. Claude Code, Cowork, and Office Agent spend use request-level skill attribution;
     * claude.ai chat spend is approximated proportionally to skill-invoking messages. An estimate,
     * not a billing number — and the cost of the requests/messages that involved the skill, not the
     * skill's incremental cost (the same request would still have cost something without the skill
     * active). "0" means no overage spend was attributed; null when spend reporting is not enabled
     * for this organization, on `office_agent` product cuts dated before 2026-06-18 (the Office
     * Agent attribution data-start). Addable across days: date-range rollup mode
     * (`starting_date`/`ending_date`) returns the window's sum. With `group_by[]=user_id` each row
     * carries the user's own attributed spend. On `group_by[]` and `filter[]` shapes both amounts
     * can total below the ungrouped value for the same skill over the same date or range: spend
     * attributed to a member–skill pair with no counted usage on that day is excluded from those
     * cuts.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun estimatedOverageSpend(): Optional<String> =
        estimatedOverageSpend.getOptional("estimated_overage_spend")

    /**
     * Total number of times this skill was invoked on the requested day (the skill analog of plugin
     * `invocation_count`). Unlike `distinct_user_count` — which answers '# of users' — this is the
     * true '# of uses'. A skill counts as used only when it is explicitly activated — the model (or
     * the user, via the skill's slash command) invokes it, reading its instructions into context as
     * part of that activation. Skills that are merely installed or listed as available, or whose
     * content reaches the context without an activation (preloaded, hook-injected, or read as a
     * plain file), are not counted. Null when invocation reporting is not enabled for this
     * organization. Sum across a date range for total uses in the window — date-range rollup mode
     * (`starting_date`/`ending_date`) returns this sum directly.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun invocationCount(): Optional<Long> = invocationCount.getOptional("invocation_count")

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
     * Skill share status (claude.ai only): one of `private`, `organization`, or `public`. Null for
     * skills used only in Claude Code or Office (no per-skill share-status concept) and when
     * share-status reporting is not yet available for the organization. Filterable via
     * `filter[]=share_status:{value}`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun shareStatus(): Optional<ShareStatus> = shareStatus.getOptional("share_status")

    /**
     * Human-readable display name for rows whose `skill_name` is an opaque skill id
     * (user/organization skill types and plugin-delivered skills, whose user-defined names usage
     * reports generally withhold). Organization-shared skills and skills delivered by the
     * organization's own plugins (its plugin marketplaces and its library) resolve; plugin skill
     * names are shown without their 'plugin:' prefix. The literal 'unknown' bucket row gets a fixed
     * 'Unknown skill' label. For a member's own skill (private or personal-plugin) it is null,
     * except when the skill's owner used it from Claude Code or Cowork in the requested period:
     * then it shows the name that client reported at the time. Apart from that, the names of
     * members' own skills are not disclosed to analytics-key holders. Also null for
     * Anthropic-provided plugin skills (not resolved), for an organization skill or plugin whose
     * name can no longer be found (for example, one since deleted), when `skill_name` is already a
     * display name, or when display-name resolution is not enabled for this organization.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun skillDisplayName(): Optional<String> = skillDisplayName.getOptional("skill_display_name")

    /**
     * Tagged user identifier (e.g. `user_...`). Present only when the request grouped by `user_id`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun userId(): Optional<String> = userId.getOptional("user_id")

    /**
     * Returns the raw JSON value of [chatMetrics].
     *
     * Unlike [chatMetrics], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("chat_metrics")
    @ExcludeMissing
    fun _chatMetrics(): JsonField<BetaAnalyticsSkillChatMetrics> = chatMetrics

    /**
     * Returns the raw JSON value of [claudeCodeMetrics].
     *
     * Unlike [claudeCodeMetrics], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("claude_code_metrics")
    @ExcludeMissing
    fun _claudeCodeMetrics(): JsonField<BetaAnalyticsSkillClaudeCodeMetrics> = claudeCodeMetrics

    /**
     * Returns the raw JSON value of [coworkMetrics].
     *
     * Unlike [coworkMetrics], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("cowork_metrics")
    @ExcludeMissing
    fun _coworkMetrics(): JsonField<BetaAnalyticsSkillCoworkMetrics> = coworkMetrics

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
     * Returns the raw JSON value of [officeMetrics].
     *
     * Unlike [officeMetrics], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("office_metrics")
    @ExcludeMissing
    fun _officeMetrics(): JsonField<BetaAnalyticsSkillOfficeMetrics> = officeMetrics

    /**
     * Returns the raw JSON value of [skillName].
     *
     * Unlike [skillName], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("skill_name") @ExcludeMissing fun _skillName(): JsonField<String> = skillName

    /**
     * Returns the raw JSON value of [attributedListPrice].
     *
     * Unlike [attributedListPrice], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("attributed_list_price")
    @ExcludeMissing
    fun _attributedListPrice(): JsonField<String> = attributedListPrice

    /**
     * Returns the raw JSON value of [chatCoworkUnifiedMetrics].
     *
     * Unlike [chatCoworkUnifiedMetrics], this method doesn't throw if the JSON field has an
     * unexpected type.
     */
    @JsonProperty("chat_cowork_unified_metrics")
    @ExcludeMissing
    fun _chatCoworkUnifiedMetrics(): JsonField<ChatCoworkUnifiedMetrics> = chatCoworkUnifiedMetrics

    /**
     * Returns the raw JSON value of [currency].
     *
     * Unlike [currency], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("currency") @ExcludeMissing fun _currency(): JsonField<String> = currency

    /**
     * Returns the raw JSON value of [enableCount].
     *
     * Unlike [enableCount], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("enable_count") @ExcludeMissing fun _enableCount(): JsonField<Long> = enableCount

    /**
     * Returns the raw JSON value of [estimatedOverageSpend].
     *
     * Unlike [estimatedOverageSpend], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("estimated_overage_spend")
    @ExcludeMissing
    fun _estimatedOverageSpend(): JsonField<String> = estimatedOverageSpend

    /**
     * Returns the raw JSON value of [invocationCount].
     *
     * Unlike [invocationCount], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("invocation_count")
    @ExcludeMissing
    fun _invocationCount(): JsonField<Long> = invocationCount

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
     * Returns the raw JSON value of [shareStatus].
     *
     * Unlike [shareStatus], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("share_status")
    @ExcludeMissing
    fun _shareStatus(): JsonField<ShareStatus> = shareStatus

    /**
     * Returns the raw JSON value of [skillDisplayName].
     *
     * Unlike [skillDisplayName], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("skill_display_name")
    @ExcludeMissing
    fun _skillDisplayName(): JsonField<String> = skillDisplayName

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
         * Returns a mutable builder for constructing an instance of [BetaAnalyticsSkillActivity].
         *
         * The following fields are required:
         * ```java
         * .chatMetrics()
         * .claudeCodeMetrics()
         * .coworkMetrics()
         * .distinctUserCount()
         * .officeMetrics()
         * .skillName()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaAnalyticsSkillActivity]. */
    class Builder internal constructor() {

        private var chatMetrics: JsonField<BetaAnalyticsSkillChatMetrics>? = null
        private var claudeCodeMetrics: JsonField<BetaAnalyticsSkillClaudeCodeMetrics>? = null
        private var coworkMetrics: JsonField<BetaAnalyticsSkillCoworkMetrics>? = null
        private var distinctUserCount: JsonField<Long>? = null
        private var officeMetrics: JsonField<BetaAnalyticsSkillOfficeMetrics>? = null
        private var skillName: JsonField<String>? = null
        private var attributedListPrice: JsonField<String> = JsonMissing.of()
        private var chatCoworkUnifiedMetrics: JsonField<ChatCoworkUnifiedMetrics> = JsonMissing.of()
        private var currency: JsonField<String> = JsonMissing.of()
        private var enableCount: JsonField<Long> = JsonMissing.of()
        private var estimatedOverageSpend: JsonField<String> = JsonMissing.of()
        private var invocationCount: JsonField<Long> = JsonMissing.of()
        private var product: JsonField<String> = JsonMissing.of()
        private var rbacGroupId: JsonField<String> = JsonMissing.of()
        private var rbacGroupName: JsonField<String> = JsonMissing.of()
        private var shareStatus: JsonField<ShareStatus> = JsonMissing.of()
        private var skillDisplayName: JsonField<String> = JsonMissing.of()
        private var userId: JsonField<String> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaAnalyticsSkillActivity: BetaAnalyticsSkillActivity) = apply {
            chatMetrics = betaAnalyticsSkillActivity.chatMetrics
            claudeCodeMetrics = betaAnalyticsSkillActivity.claudeCodeMetrics
            coworkMetrics = betaAnalyticsSkillActivity.coworkMetrics
            distinctUserCount = betaAnalyticsSkillActivity.distinctUserCount
            officeMetrics = betaAnalyticsSkillActivity.officeMetrics
            skillName = betaAnalyticsSkillActivity.skillName
            attributedListPrice = betaAnalyticsSkillActivity.attributedListPrice
            chatCoworkUnifiedMetrics = betaAnalyticsSkillActivity.chatCoworkUnifiedMetrics
            currency = betaAnalyticsSkillActivity.currency
            enableCount = betaAnalyticsSkillActivity.enableCount
            estimatedOverageSpend = betaAnalyticsSkillActivity.estimatedOverageSpend
            invocationCount = betaAnalyticsSkillActivity.invocationCount
            product = betaAnalyticsSkillActivity.product
            rbacGroupId = betaAnalyticsSkillActivity.rbacGroupId
            rbacGroupName = betaAnalyticsSkillActivity.rbacGroupName
            shareStatus = betaAnalyticsSkillActivity.shareStatus
            skillDisplayName = betaAnalyticsSkillActivity.skillDisplayName
            userId = betaAnalyticsSkillActivity.userId
            additionalProperties = betaAnalyticsSkillActivity.additionalProperties.toMutableMap()
        }

        /** Claude.ai activity metrics for a single skill on a given day. */
        fun chatMetrics(chatMetrics: BetaAnalyticsSkillChatMetrics) =
            chatMetrics(JsonField.of(chatMetrics))

        /**
         * Sets [Builder.chatMetrics] to an arbitrary JSON value.
         *
         * You should usually call [Builder.chatMetrics] with a well-typed
         * [BetaAnalyticsSkillChatMetrics] value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun chatMetrics(chatMetrics: JsonField<BetaAnalyticsSkillChatMetrics>) = apply {
            this.chatMetrics = chatMetrics
        }

        /** Claude Code activity metrics for a single skill on a given day. */
        fun claudeCodeMetrics(claudeCodeMetrics: BetaAnalyticsSkillClaudeCodeMetrics) =
            claudeCodeMetrics(JsonField.of(claudeCodeMetrics))

        /**
         * Sets [Builder.claudeCodeMetrics] to an arbitrary JSON value.
         *
         * You should usually call [Builder.claudeCodeMetrics] with a well-typed
         * [BetaAnalyticsSkillClaudeCodeMetrics] value instead. This method is primarily for setting
         * the field to an undocumented or not yet supported value.
         */
        fun claudeCodeMetrics(claudeCodeMetrics: JsonField<BetaAnalyticsSkillClaudeCodeMetrics>) =
            apply {
                this.claudeCodeMetrics = claudeCodeMetrics
            }

        /** Cowork activity metrics for a single skill on a given day. */
        fun coworkMetrics(coworkMetrics: BetaAnalyticsSkillCoworkMetrics) =
            coworkMetrics(JsonField.of(coworkMetrics))

        /**
         * Sets [Builder.coworkMetrics] to an arbitrary JSON value.
         *
         * You should usually call [Builder.coworkMetrics] with a well-typed
         * [BetaAnalyticsSkillCoworkMetrics] value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun coworkMetrics(coworkMetrics: JsonField<BetaAnalyticsSkillCoworkMetrics>) = apply {
            this.coworkMetrics = coworkMetrics
        }

        /**
         * Number of distinct users who used the skill on the requested day, or, in date-range mode,
         * over the requested window — recomputed as an exact distinct count over the window's
         * per-member daily rows, never a sum of per-day values. A skill counts as used only when it
         * is explicitly activated — the model (or the user, via the skill's slash command) invokes
         * it, reading its instructions into context as part of that activation. Skills that are
         * merely installed or listed as available, or whose content reaches the context without an
         * activation (preloaded, hook-injected, or read as a plain file), are not counted.
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
         * Office Agent activity metrics for a single skill on a given day, broken out by Office
         * product.
         */
        fun officeMetrics(officeMetrics: BetaAnalyticsSkillOfficeMetrics) =
            officeMetrics(JsonField.of(officeMetrics))

        /**
         * Sets [Builder.officeMetrics] to an arbitrary JSON value.
         *
         * You should usually call [Builder.officeMetrics] with a well-typed
         * [BetaAnalyticsSkillOfficeMetrics] value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun officeMetrics(officeMetrics: JsonField<BetaAnalyticsSkillOfficeMetrics>) = apply {
            this.officeMetrics = officeMetrics
        }

        /** Name of the skill */
        fun skillName(skillName: String) = skillName(JsonField.of(skillName))

        /**
         * Sets [Builder.skillName] to an arbitrary JSON value.
         *
         * You should usually call [Builder.skillName] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun skillName(skillName: JsonField<String>) = apply { this.skillName = skillName }

        /**
         * List-price (rate-card) value of the member requests attributed to this skill, as a
         * decimal string in the minor unit of `currency` (cents for USD), from Claude Code, Cowork,
         * and Office Agent request-level attribution — the value of requests that involved the
         * skill, not the skill's incremental cost. Unlike `estimated_overage_spend` this reflects
         * usage value regardless of how it was funded — seat-covered usage counts — but it is
         * undiscounted and does not tie to billed spend or the organization's spend reporting.
         * claude.ai chat usage carries no request-level attribution and contributes nothing: the
         * field is null on `chat` product rows and on `office_agent` product cuts dated before
         * 2026-06-18 (the Office Agent attribution data-start), and on ungrouped rows it covers the
         * Claude Code + Cowork + Office Agent share only (null when no attributable usage exists).
         * Also null under the same conditions as `estimated_overage_spend` (spend reporting not
         * enabled for this organization, `office_agent` product cuts before the 2026-06-18
         * data-start). "0" means attributable usage existed but none was attributed to this skill.
         * Addable across days: date-range rollup mode returns the window's sum. On `group_by[]` and
         * `filter[]` shapes both amounts can total below the ungrouped value for the same skill
         * over the same date or range: spend attributed to a member–skill pair with no counted
         * usage on that day is excluded from those cuts.
         */
        fun attributedListPrice(attributedListPrice: String?) =
            attributedListPrice(JsonField.ofNullable(attributedListPrice))

        /**
         * Alias for calling [Builder.attributedListPrice] with `attributedListPrice.orElse(null)`.
         */
        fun attributedListPrice(attributedListPrice: Optional<String>) =
            attributedListPrice(attributedListPrice.getOrNull())

        /**
         * Sets [Builder.attributedListPrice] to an arbitrary JSON value.
         *
         * You should usually call [Builder.attributedListPrice] with a well-typed [String] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun attributedListPrice(attributedListPrice: JsonField<String>) = apply {
            this.attributedListPrice = attributedListPrice
        }

        /**
         * Skill use recorded while members had Chat and Cowork unified (Cowork's features inside
         * claude.ai chat) turned on, split into chat conversations and Cowork sessions. A count is
         * null in date-range mode where it cannot be computed. Omitted from the response on
         * deployments that do not offer Chat and Cowork unified.
         */
        fun chatCoworkUnifiedMetrics(chatCoworkUnifiedMetrics: ChatCoworkUnifiedMetrics?) =
            chatCoworkUnifiedMetrics(JsonField.ofNullable(chatCoworkUnifiedMetrics))

        /**
         * Alias for calling [Builder.chatCoworkUnifiedMetrics] with
         * `chatCoworkUnifiedMetrics.orElse(null)`.
         */
        fun chatCoworkUnifiedMetrics(chatCoworkUnifiedMetrics: Optional<ChatCoworkUnifiedMetrics>) =
            chatCoworkUnifiedMetrics(chatCoworkUnifiedMetrics.getOrNull())

        /**
         * Sets [Builder.chatCoworkUnifiedMetrics] to an arbitrary JSON value.
         *
         * You should usually call [Builder.chatCoworkUnifiedMetrics] with a well-typed
         * [ChatCoworkUnifiedMetrics] value instead. This method is primarily for setting the field
         * to an undocumented or not yet supported value.
         */
        fun chatCoworkUnifiedMetrics(
            chatCoworkUnifiedMetrics: JsonField<ChatCoworkUnifiedMetrics>
        ) = apply { this.chatCoworkUnifiedMetrics = chatCoworkUnifiedMetrics }

        /**
         * Currency for this row's monetary fields (`estimated_overage_spend` and
         * `attributed_list_price`), as an uppercase ISO-4217 code. Always "USD" when either amount
         * is populated; null whenever both amounts are null.
         */
        fun currency(currency: String?) = currency(JsonField.ofNullable(currency))

        /** Alias for calling [Builder.currency] with `currency.orElse(null)`. */
        fun currency(currency: Optional<String>) = currency(currency.getOrNull())

        /**
         * Sets [Builder.currency] to an arbitrary JSON value.
         *
         * You should usually call [Builder.currency] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun currency(currency: JsonField<String>) = apply { this.currency = currency }

        /**
         * Distinct accounts that enabled this skill on the requested day (claude.ai only — the
         * skill analog of plugin `install_count`). The count is org-wide: null when enable
         * reporting is not enabled for this organization, or when the request scopes to `user_id` /
         * `rbac_group_id` / `product` via `group_by[]` or `filter[]` (an org-wide count would be
         * misleading on per-cut rows). A distinct count, not an event count: summing across days
         * double-counts members who enable the skill on more than one day, so it is also null in
         * date-range rollup mode (`starting_date`/`ending_date`).
         */
        fun enableCount(enableCount: Long?) = enableCount(JsonField.ofNullable(enableCount))

        /**
         * Alias for [Builder.enableCount].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun enableCount(enableCount: Long) = enableCount(enableCount as Long?)

        /** Alias for calling [Builder.enableCount] with `enableCount.orElse(null)`. */
        fun enableCount(enableCount: Optional<Long>) = enableCount(enableCount.getOrNull())

        /**
         * Sets [Builder.enableCount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.enableCount] with a well-typed [Long] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun enableCount(enableCount: JsonField<Long>) = apply { this.enableCount = enableCount }

        /**
         * Estimated overage spend attributed to this skill, as a decimal string in the minor unit
         * of `currency` (cents for USD; "1250" is $12.50, fractional cents possible) — an
         * allocation of each member's daily post-discount, pre-credit metered overage spend (the
         * same cost basis as the organization's spend reporting and the Cost & Usage API, so
         * per-skill figures are directly comparable; spend with no skill attribution — including
         * any member-day without skill invocations — is not represented, so skill rows sum to at
         * most those totals) across the skills the member used. Overage only: usage covered by
         * included seat allowances bills nothing and allocates $0 here — see
         * `attributed_list_price` for the funding-independent usage-value companion. Claude Code,
         * Cowork, and Office Agent spend use request-level skill attribution; claude.ai chat spend
         * is approximated proportionally to skill-invoking messages. An estimate, not a billing
         * number — and the cost of the requests/messages that involved the skill, not the skill's
         * incremental cost (the same request would still have cost something without the skill
         * active). "0" means no overage spend was attributed; null when spend reporting is not
         * enabled for this organization, on `office_agent` product cuts dated before 2026-06-18
         * (the Office Agent attribution data-start). Addable across days: date-range rollup mode
         * (`starting_date`/`ending_date`) returns the window's sum. With `group_by[]=user_id` each
         * row carries the user's own attributed spend. On `group_by[]` and `filter[]` shapes both
         * amounts can total below the ungrouped value for the same skill over the same date or
         * range: spend attributed to a member–skill pair with no counted usage on that day is
         * excluded from those cuts.
         */
        fun estimatedOverageSpend(estimatedOverageSpend: String?) =
            estimatedOverageSpend(JsonField.ofNullable(estimatedOverageSpend))

        /**
         * Alias for calling [Builder.estimatedOverageSpend] with
         * `estimatedOverageSpend.orElse(null)`.
         */
        fun estimatedOverageSpend(estimatedOverageSpend: Optional<String>) =
            estimatedOverageSpend(estimatedOverageSpend.getOrNull())

        /**
         * Sets [Builder.estimatedOverageSpend] to an arbitrary JSON value.
         *
         * You should usually call [Builder.estimatedOverageSpend] with a well-typed [String] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun estimatedOverageSpend(estimatedOverageSpend: JsonField<String>) = apply {
            this.estimatedOverageSpend = estimatedOverageSpend
        }

        /**
         * Total number of times this skill was invoked on the requested day (the skill analog of
         * plugin `invocation_count`). Unlike `distinct_user_count` — which answers '# of users' —
         * this is the true '# of uses'. A skill counts as used only when it is explicitly activated
         * — the model (or the user, via the skill's slash command) invokes it, reading its
         * instructions into context as part of that activation. Skills that are merely installed or
         * listed as available, or whose content reaches the context without an activation
         * (preloaded, hook-injected, or read as a plain file), are not counted. Null when
         * invocation reporting is not enabled for this organization. Sum across a date range for
         * total uses in the window — date-range rollup mode (`starting_date`/`ending_date`) returns
         * this sum directly.
         */
        fun invocationCount(invocationCount: Long?) =
            invocationCount(JsonField.ofNullable(invocationCount))

        /**
         * Alias for [Builder.invocationCount].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun invocationCount(invocationCount: Long) = invocationCount(invocationCount as Long?)

        /** Alias for calling [Builder.invocationCount] with `invocationCount.orElse(null)`. */
        fun invocationCount(invocationCount: Optional<Long>) =
            invocationCount(invocationCount.getOrNull())

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
         * Skill share status (claude.ai only): one of `private`, `organization`, or `public`. Null
         * for skills used only in Claude Code or Office (no per-skill share-status concept) and
         * when share-status reporting is not yet available for the organization. Filterable via
         * `filter[]=share_status:{value}`.
         */
        fun shareStatus(shareStatus: ShareStatus?) = shareStatus(JsonField.ofNullable(shareStatus))

        /** Alias for calling [Builder.shareStatus] with `shareStatus.orElse(null)`. */
        fun shareStatus(shareStatus: Optional<ShareStatus>) = shareStatus(shareStatus.getOrNull())

        /**
         * Sets [Builder.shareStatus] to an arbitrary JSON value.
         *
         * You should usually call [Builder.shareStatus] with a well-typed [ShareStatus] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun shareStatus(shareStatus: JsonField<ShareStatus>) = apply {
            this.shareStatus = shareStatus
        }

        /**
         * Human-readable display name for rows whose `skill_name` is an opaque skill id
         * (user/organization skill types and plugin-delivered skills, whose user-defined names
         * usage reports generally withhold). Organization-shared skills and skills delivered by the
         * organization's own plugins (its plugin marketplaces and its library) resolve; plugin
         * skill names are shown without their 'plugin:' prefix. The literal 'unknown' bucket row
         * gets a fixed 'Unknown skill' label. For a member's own skill (private or personal-plugin)
         * it is null, except when the skill's owner used it from Claude Code or Cowork in the
         * requested period: then it shows the name that client reported at the time. Apart from
         * that, the names of members' own skills are not disclosed to analytics-key holders. Also
         * null for Anthropic-provided plugin skills (not resolved), for an organization skill or
         * plugin whose name can no longer be found (for example, one since deleted), when
         * `skill_name` is already a display name, or when display-name resolution is not enabled
         * for this organization.
         */
        fun skillDisplayName(skillDisplayName: String?) =
            skillDisplayName(JsonField.ofNullable(skillDisplayName))

        /** Alias for calling [Builder.skillDisplayName] with `skillDisplayName.orElse(null)`. */
        fun skillDisplayName(skillDisplayName: Optional<String>) =
            skillDisplayName(skillDisplayName.getOrNull())

        /**
         * Sets [Builder.skillDisplayName] to an arbitrary JSON value.
         *
         * You should usually call [Builder.skillDisplayName] with a well-typed [String] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun skillDisplayName(skillDisplayName: JsonField<String>) = apply {
            this.skillDisplayName = skillDisplayName
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
         * Returns an immutable instance of [BetaAnalyticsSkillActivity].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .chatMetrics()
         * .claudeCodeMetrics()
         * .coworkMetrics()
         * .distinctUserCount()
         * .officeMetrics()
         * .skillName()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaAnalyticsSkillActivity =
            BetaAnalyticsSkillActivity(
                checkRequired("chatMetrics", chatMetrics),
                checkRequired("claudeCodeMetrics", claudeCodeMetrics),
                checkRequired("coworkMetrics", coworkMetrics),
                checkRequired("distinctUserCount", distinctUserCount),
                checkRequired("officeMetrics", officeMetrics),
                checkRequired("skillName", skillName),
                attributedListPrice,
                chatCoworkUnifiedMetrics,
                currency,
                enableCount,
                estimatedOverageSpend,
                invocationCount,
                product,
                rbacGroupId,
                rbacGroupName,
                shareStatus,
                skillDisplayName,
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
    fun validate(): BetaAnalyticsSkillActivity = apply {
        if (validated) {
            return@apply
        }

        chatMetrics().validate()
        claudeCodeMetrics().validate()
        coworkMetrics().validate()
        distinctUserCount()
        officeMetrics().validate()
        skillName()
        attributedListPrice()
        chatCoworkUnifiedMetrics().ifPresent { it.validate() }
        currency()
        enableCount()
        estimatedOverageSpend()
        invocationCount()
        product()
        rbacGroupId()
        rbacGroupName()
        shareStatus().ifPresent { it.validate() }
        skillDisplayName()
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
        (chatMetrics.asKnown().getOrNull()?.validity() ?: 0) +
            (claudeCodeMetrics.asKnown().getOrNull()?.validity() ?: 0) +
            (coworkMetrics.asKnown().getOrNull()?.validity() ?: 0) +
            (if (distinctUserCount.asKnown().isPresent) 1 else 0) +
            (officeMetrics.asKnown().getOrNull()?.validity() ?: 0) +
            (if (skillName.asKnown().isPresent) 1 else 0) +
            (if (attributedListPrice.asKnown().isPresent) 1 else 0) +
            (chatCoworkUnifiedMetrics.asKnown().getOrNull()?.validity() ?: 0) +
            (if (currency.asKnown().isPresent) 1 else 0) +
            (if (enableCount.asKnown().isPresent) 1 else 0) +
            (if (estimatedOverageSpend.asKnown().isPresent) 1 else 0) +
            (if (invocationCount.asKnown().isPresent) 1 else 0) +
            (if (product.asKnown().isPresent) 1 else 0) +
            (if (rbacGroupId.asKnown().isPresent) 1 else 0) +
            (if (rbacGroupName.asKnown().isPresent) 1 else 0) +
            (shareStatus.asKnown().getOrNull()?.validity() ?: 0) +
            (if (skillDisplayName.asKnown().isPresent) 1 else 0) +
            (if (userId.asKnown().isPresent) 1 else 0)

    /**
     * Skill use recorded while members had Chat and Cowork unified (Cowork's features inside
     * claude.ai chat) turned on, split into chat conversations and Cowork sessions. A count is null
     * in date-range mode where it cannot be computed. Omitted from the response on deployments that
     * do not offer Chat and Cowork unified.
     */
    class ChatCoworkUnifiedMetrics
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val chat: JsonField<BetaAnalyticsSkillChatCoworkUnifiedChatMetrics>,
        private val sessions: JsonField<BetaAnalyticsSkillChatCoworkUnifiedSessionsMetrics>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("chat")
            @ExcludeMissing
            chat: JsonField<BetaAnalyticsSkillChatCoworkUnifiedChatMetrics> = JsonMissing.of(),
            @JsonProperty("sessions")
            @ExcludeMissing
            sessions: JsonField<BetaAnalyticsSkillChatCoworkUnifiedSessionsMetrics> =
                JsonMissing.of(),
        ) : this(chat, sessions, mutableMapOf())

        /**
         * A skill's use in chat conversations recorded while members had Chat and Cowork unified
         * turned on.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
         *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
         */
        fun chat(): BetaAnalyticsSkillChatCoworkUnifiedChatMetrics = chat.getRequired("chat")

        /**
         * A skill's use in Cowork sessions recorded while members had Chat and Cowork unified
         * turned on.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
         *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
         */
        fun sessions(): BetaAnalyticsSkillChatCoworkUnifiedSessionsMetrics =
            sessions.getRequired("sessions")

        /**
         * Returns the raw JSON value of [chat].
         *
         * Unlike [chat], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("chat")
        @ExcludeMissing
        fun _chat(): JsonField<BetaAnalyticsSkillChatCoworkUnifiedChatMetrics> = chat

        /**
         * Returns the raw JSON value of [sessions].
         *
         * Unlike [sessions], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("sessions")
        @ExcludeMissing
        fun _sessions(): JsonField<BetaAnalyticsSkillChatCoworkUnifiedSessionsMetrics> = sessions

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
             * Returns a mutable builder for constructing an instance of [ChatCoworkUnifiedMetrics].
             *
             * The following fields are required:
             * ```java
             * .chat()
             * .sessions()
             * ```
             */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [ChatCoworkUnifiedMetrics]. */
        class Builder internal constructor() {

            private var chat: JsonField<BetaAnalyticsSkillChatCoworkUnifiedChatMetrics>? = null
            private var sessions: JsonField<BetaAnalyticsSkillChatCoworkUnifiedSessionsMetrics>? =
                null
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(chatCoworkUnifiedMetrics: ChatCoworkUnifiedMetrics) = apply {
                chat = chatCoworkUnifiedMetrics.chat
                sessions = chatCoworkUnifiedMetrics.sessions
                additionalProperties = chatCoworkUnifiedMetrics.additionalProperties.toMutableMap()
            }

            /**
             * A skill's use in chat conversations recorded while members had Chat and Cowork
             * unified turned on.
             */
            fun chat(chat: BetaAnalyticsSkillChatCoworkUnifiedChatMetrics) =
                chat(JsonField.of(chat))

            /**
             * Sets [Builder.chat] to an arbitrary JSON value.
             *
             * You should usually call [Builder.chat] with a well-typed
             * [BetaAnalyticsSkillChatCoworkUnifiedChatMetrics] value instead. This method is
             * primarily for setting the field to an undocumented or not yet supported value.
             */
            fun chat(chat: JsonField<BetaAnalyticsSkillChatCoworkUnifiedChatMetrics>) = apply {
                this.chat = chat
            }

            /**
             * A skill's use in Cowork sessions recorded while members had Chat and Cowork unified
             * turned on.
             */
            fun sessions(sessions: BetaAnalyticsSkillChatCoworkUnifiedSessionsMetrics) =
                sessions(JsonField.of(sessions))

            /**
             * Sets [Builder.sessions] to an arbitrary JSON value.
             *
             * You should usually call [Builder.sessions] with a well-typed
             * [BetaAnalyticsSkillChatCoworkUnifiedSessionsMetrics] value instead. This method is
             * primarily for setting the field to an undocumented or not yet supported value.
             */
            fun sessions(sessions: JsonField<BetaAnalyticsSkillChatCoworkUnifiedSessionsMetrics>) =
                apply {
                    this.sessions = sessions
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
             * Returns an immutable instance of [ChatCoworkUnifiedMetrics].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             *
             * The following fields are required:
             * ```java
             * .chat()
             * .sessions()
             * ```
             *
             * @throws IllegalStateException if any required field is unset.
             */
            fun build(): ChatCoworkUnifiedMetrics =
                ChatCoworkUnifiedMetrics(
                    checkRequired("chat", chat),
                    checkRequired("sessions", sessions),
                    additionalProperties.toMutableMap(),
                )
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
        fun validate(): ChatCoworkUnifiedMetrics = apply {
            if (validated) {
                return@apply
            }

            chat().validate()
            sessions().validate()
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
        @JvmSynthetic
        internal fun validity(): Int =
            (chat.asKnown().getOrNull()?.validity() ?: 0) +
                (sessions.asKnown().getOrNull()?.validity() ?: 0)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is ChatCoworkUnifiedMetrics &&
                chat == other.chat &&
                sessions == other.sessions &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy { Objects.hash(chat, sessions, additionalProperties) }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "ChatCoworkUnifiedMetrics{chat=$chat, sessions=$sessions, additionalProperties=$additionalProperties}"
    }

    /**
     * Skill share status (claude.ai only): one of `private`, `organization`, or `public`. Null for
     * skills used only in Claude Code or Office (no per-skill share-status concept) and when
     * share-status reporting is not yet available for the organization. Filterable via
     * `filter[]=share_status:{value}`.
     */
    class ShareStatus private constructor(private val value: JsonField<String>) : Enum {

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

            @JvmField val ORGANIZATION = ShareStatus(JsonField.of("organization"))

            @JvmField val PRIVATE = ShareStatus(JsonField.of("private"))

            @JvmField val PUBLIC = ShareStatus(JsonField.of("public"))

            @JvmStatic
            fun of(value: String): ShareStatus =
                // Intern known values so `==` works
                when (value) {
                    "organization" -> ORGANIZATION
                    "private" -> PRIVATE
                    "public" -> PUBLIC
                    else -> ShareStatus(JsonField.of(value))
                }

            @JsonCreator
            @JvmStatic
            fun of(value: JsonField<String>): ShareStatus =
                value.asString().getOrNull()?.let { of(it) } ?: ShareStatus(value)
        }

        /** An enum containing [ShareStatus]'s known values. */
        enum class Known {
            ORGANIZATION,
            PRIVATE,
            PUBLIC,
        }

        /**
         * An enum containing [ShareStatus]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [ShareStatus] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            ORGANIZATION,
            PRIVATE,
            PUBLIC,
            /**
             * An enum member indicating that [ShareStatus] was instantiated with an unknown value.
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
                ORGANIZATION -> Value.ORGANIZATION
                PRIVATE -> Value.PRIVATE
                PUBLIC -> Value.PUBLIC
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
                ORGANIZATION -> Known.ORGANIZATION
                PRIVATE -> Known.PRIVATE
                PUBLIC -> Known.PUBLIC
                else -> throw AnthropicInvalidDataException("Unknown ShareStatus: $value")
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
        fun validate(): ShareStatus = apply {
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

            return other is ShareStatus && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaAnalyticsSkillActivity &&
            chatMetrics == other.chatMetrics &&
            claudeCodeMetrics == other.claudeCodeMetrics &&
            coworkMetrics == other.coworkMetrics &&
            distinctUserCount == other.distinctUserCount &&
            officeMetrics == other.officeMetrics &&
            skillName == other.skillName &&
            attributedListPrice == other.attributedListPrice &&
            chatCoworkUnifiedMetrics == other.chatCoworkUnifiedMetrics &&
            currency == other.currency &&
            enableCount == other.enableCount &&
            estimatedOverageSpend == other.estimatedOverageSpend &&
            invocationCount == other.invocationCount &&
            product == other.product &&
            rbacGroupId == other.rbacGroupId &&
            rbacGroupName == other.rbacGroupName &&
            shareStatus == other.shareStatus &&
            skillDisplayName == other.skillDisplayName &&
            userId == other.userId &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            chatMetrics,
            claudeCodeMetrics,
            coworkMetrics,
            distinctUserCount,
            officeMetrics,
            skillName,
            attributedListPrice,
            chatCoworkUnifiedMetrics,
            currency,
            enableCount,
            estimatedOverageSpend,
            invocationCount,
            product,
            rbacGroupId,
            rbacGroupName,
            shareStatus,
            skillDisplayName,
            userId,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaAnalyticsSkillActivity{chatMetrics=$chatMetrics, claudeCodeMetrics=$claudeCodeMetrics, coworkMetrics=$coworkMetrics, distinctUserCount=$distinctUserCount, officeMetrics=$officeMetrics, skillName=$skillName, attributedListPrice=$attributedListPrice, chatCoworkUnifiedMetrics=$chatCoworkUnifiedMetrics, currency=$currency, enableCount=$enableCount, estimatedOverageSpend=$estimatedOverageSpend, invocationCount=$invocationCount, product=$product, rbacGroupId=$rbacGroupId, rbacGroupName=$rbacGroupName, shareStatus=$shareStatus, skillDisplayName=$skillDisplayName, userId=$userId, additionalProperties=$additionalProperties}"
}
