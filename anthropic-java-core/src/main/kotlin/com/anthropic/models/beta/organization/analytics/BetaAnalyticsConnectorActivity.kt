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

/** Per-connector activity data for a given day. */
class BetaAnalyticsConnectorActivity
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val chatMetrics: JsonField<BetaAnalyticsConnectorChatMetrics>,
    private val claudeCodeMetrics: JsonField<BetaAnalyticsConnectorClaudeCodeMetrics>,
    private val connectorName: JsonField<String>,
    private val coworkMetrics: JsonField<BetaAnalyticsConnectorCoworkMetrics>,
    private val distinctUserCount: JsonField<Long>,
    private val officeMetrics: JsonField<BetaAnalyticsConnectorOfficeMetrics>,
    private val connectorDisplayName: JsonField<String>,
    private val individualAuthDistinctUserCount: JsonField<Long>,
    private val managedAuthDistinctUserCount: JsonField<Long>,
    private val product: JsonField<String>,
    private val rbacGroupId: JsonField<String>,
    private val rbacGroupName: JsonField<String>,
    private val readCallCount: JsonField<Long>,
    private val unclassifiedCallCount: JsonField<Long>,
    private val userId: JsonField<String>,
    private val writeCallCount: JsonField<Long>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("chat_metrics")
        @ExcludeMissing
        chatMetrics: JsonField<BetaAnalyticsConnectorChatMetrics> = JsonMissing.of(),
        @JsonProperty("claude_code_metrics")
        @ExcludeMissing
        claudeCodeMetrics: JsonField<BetaAnalyticsConnectorClaudeCodeMetrics> = JsonMissing.of(),
        @JsonProperty("connector_name")
        @ExcludeMissing
        connectorName: JsonField<String> = JsonMissing.of(),
        @JsonProperty("cowork_metrics")
        @ExcludeMissing
        coworkMetrics: JsonField<BetaAnalyticsConnectorCoworkMetrics> = JsonMissing.of(),
        @JsonProperty("distinct_user_count")
        @ExcludeMissing
        distinctUserCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("office_metrics")
        @ExcludeMissing
        officeMetrics: JsonField<BetaAnalyticsConnectorOfficeMetrics> = JsonMissing.of(),
        @JsonProperty("connector_display_name")
        @ExcludeMissing
        connectorDisplayName: JsonField<String> = JsonMissing.of(),
        @JsonProperty("individual_auth_distinct_user_count")
        @ExcludeMissing
        individualAuthDistinctUserCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("managed_auth_distinct_user_count")
        @ExcludeMissing
        managedAuthDistinctUserCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("product") @ExcludeMissing product: JsonField<String> = JsonMissing.of(),
        @JsonProperty("rbac_group_id")
        @ExcludeMissing
        rbacGroupId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("rbac_group_name")
        @ExcludeMissing
        rbacGroupName: JsonField<String> = JsonMissing.of(),
        @JsonProperty("read_call_count")
        @ExcludeMissing
        readCallCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("unclassified_call_count")
        @ExcludeMissing
        unclassifiedCallCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("user_id") @ExcludeMissing userId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("write_call_count")
        @ExcludeMissing
        writeCallCount: JsonField<Long> = JsonMissing.of(),
    ) : this(
        chatMetrics,
        claudeCodeMetrics,
        connectorName,
        coworkMetrics,
        distinctUserCount,
        officeMetrics,
        connectorDisplayName,
        individualAuthDistinctUserCount,
        managedAuthDistinctUserCount,
        product,
        rbacGroupId,
        rbacGroupName,
        readCallCount,
        unclassifiedCallCount,
        userId,
        writeCallCount,
        mutableMapOf(),
    )

    /**
     * Claude.ai activity metrics for a single connector on a given day.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun chatMetrics(): BetaAnalyticsConnectorChatMetrics = chatMetrics.getRequired("chat_metrics")

    /**
     * Claude Code activity metrics for a single connector on a given day.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun claudeCodeMetrics(): BetaAnalyticsConnectorClaudeCodeMetrics =
        claudeCodeMetrics.getRequired("claude_code_metrics")

    /**
     * Name of the connector. Some rows carry an opaque connector id here instead of a readable
     * name; `connector_display_name` holds the resolved name for those rows.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun connectorName(): String = connectorName.getRequired("connector_name")

    /**
     * Cowork activity metrics for a single connector on a given day.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun coworkMetrics(): BetaAnalyticsConnectorCoworkMetrics =
        coworkMetrics.getRequired("cowork_metrics")

    /**
     * Number of distinct users who used the connector on the requested day, or, in date-range mode,
     * over the requested window — recomputed as an exact distinct count over the window's
     * per-member daily rows, never a sum of per-day values.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun distinctUserCount(): Long = distinctUserCount.getRequired("distinct_user_count")

    /**
     * Office Agent activity metrics for a single connector on a given day, broken out by Office
     * product.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun officeMetrics(): BetaAnalyticsConnectorOfficeMetrics =
        officeMetrics.getRequired("office_metrics")

    /**
     * Human-readable display name for rows whose `connector_name` is an opaque connector id rather
     * than a readable name, resolved at request time from the organization's connectors (including
     * connectors that have since been removed). `connector_name` remains the row's stable key for
     * sorting and pagination, and `filter[]=connector_name:{value}` also matches these rows by
     * display name. Display names are not unique, and the same connector's claude.ai usage can
     * appear under a separate row with a readable `connector_name`. Null when `connector_name` is
     * already a readable name, when the id cannot be resolved to one of the organization's
     * connectors, or when display-name resolution is not enabled for this organization.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun connectorDisplayName(): Optional<String> =
        connectorDisplayName.getOptional("connector_display_name")

    /**
     * Number of distinct users whose use of this connector on the requested day ran on their own
     * individual credential, connected through their own consent flow. Companion bucket to
     * `managed_auth_distinct_user_count`, which carries the measurement, attribution, and null
     * rules. Users whose requests used no stored credential count in neither bucket.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun individualAuthDistinctUserCount(): Optional<Long> =
        individualAuthDistinctUserCount.getOptional("individual_auth_distinct_user_count")

    /**
     * Number of distinct users whose use of this connector on the requested day ran on Enterprise
     * Managed Auth (an organization-managed credential provisioned through the organization's
     * identity provider), read from the token record each request used. Null, never 0, when
     * managed-auth reporting is not enabled for the organization, the value cannot be attributed to
     * the row, no credentialed requests and no managed-token mint events (a managed credential
     * being provisioned for a user's use of the connector) were observed that day, or the day
     * predates 2026-07-01, the first day the backing data exists (forward-only data, no backfill).
     * When credentialed requests or mint events were observed and attributed, both managed-auth
     * fields populate, reporting 0 for a bucket with no users; the two counts are independent, not
     * a partition — a user whose requests that day used both kinds of credential counts in both.
     * Mint events carry user but not surface attribution, so they count as observed auth activity
     * on `user_id` and `rbac_group_id` cuts — attributed to the user the credential was provisioned
     * for — but never on a cut that references `product` (group or filter). Date-range rollup mode
     * (`starting_date`/`ending_date`) computes both fields exactly over the window — distinct users
     * with at least one qualifying day — when the whole window starts on or after 2026-07-01, with
     * the null-versus-0 and mint-event rules applying with the window in place of the day; a range
     * starting earlier reports every managed-auth field as null, never a partial-window value.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun managedAuthDistinctUserCount(): Optional<Long> =
        managedAuthDistinctUserCount.getOptional("managed_auth_distinct_user_count")

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
     * Number of connector tool calls on the requested day whose trusted read-only annotation marked
     * them read-only. Call count, not distinct users. Every call recorded on a classified surface
     * lands in exactly one of `read_call_count`, `write_call_count`, or `unclassified_call_count`,
     * so the three sum to the day's classified calls. Classification is forward-only per surface:
     * claude.ai from 2026-06-01, Claude Code from 2026-05-30, Claude in Office from 2026-05-29,
     * Cowork from 2026-06-02 (Cowork clients predating annotation forwarding land in
     * `unclassified_call_count`). Null, never 0, when the value cannot be stated: the read/write
     * split is not enabled for this organization, or the day predates 2026-05-29. For a date-range
     * total, sum the per-day values, but treat a window that extends before 2026-05-29 as null
     * rather than summing only its covered days — date-range rollup mode
     * (`starting_date`/`ending_date`) applies both rules server-side.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun readCallCount(): Optional<Long> = readCallCount.getOptional("read_call_count")

    /**
     * Number of connector tool calls on the requested day with no trusted read-only annotation —
     * the annotation is optional in the MCP spec and is discarded when connector access controls
     * are active, so unclassified calls are common. This field shows how much of the day's
     * classified activity the read/write split actually covers. Call count, not distinct users. One
     * of the three call-classification buckets; see `read_call_count` for the per-surface
     * data-start dates, null conditions, and date-range guidance.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun unclassifiedCallCount(): Optional<Long> =
        unclassifiedCallCount.getOptional("unclassified_call_count")

    /**
     * Tagged user identifier (e.g. `user_...`). Present only when the request grouped by `user_id`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun userId(): Optional<String> = userId.getOptional("user_id")

    /**
     * Number of connector tool calls on the requested day whose trusted read-only annotation marked
     * them not read-only. Call count, not distinct users. One of the three call-classification
     * buckets; see `read_call_count` for the per-surface data-start dates, null conditions, and
     * date-range guidance.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun writeCallCount(): Optional<Long> = writeCallCount.getOptional("write_call_count")

    /**
     * Returns the raw JSON value of [chatMetrics].
     *
     * Unlike [chatMetrics], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("chat_metrics")
    @ExcludeMissing
    fun _chatMetrics(): JsonField<BetaAnalyticsConnectorChatMetrics> = chatMetrics

    /**
     * Returns the raw JSON value of [claudeCodeMetrics].
     *
     * Unlike [claudeCodeMetrics], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("claude_code_metrics")
    @ExcludeMissing
    fun _claudeCodeMetrics(): JsonField<BetaAnalyticsConnectorClaudeCodeMetrics> = claudeCodeMetrics

    /**
     * Returns the raw JSON value of [connectorName].
     *
     * Unlike [connectorName], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("connector_name")
    @ExcludeMissing
    fun _connectorName(): JsonField<String> = connectorName

    /**
     * Returns the raw JSON value of [coworkMetrics].
     *
     * Unlike [coworkMetrics], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("cowork_metrics")
    @ExcludeMissing
    fun _coworkMetrics(): JsonField<BetaAnalyticsConnectorCoworkMetrics> = coworkMetrics

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
    fun _officeMetrics(): JsonField<BetaAnalyticsConnectorOfficeMetrics> = officeMetrics

    /**
     * Returns the raw JSON value of [connectorDisplayName].
     *
     * Unlike [connectorDisplayName], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("connector_display_name")
    @ExcludeMissing
    fun _connectorDisplayName(): JsonField<String> = connectorDisplayName

    /**
     * Returns the raw JSON value of [individualAuthDistinctUserCount].
     *
     * Unlike [individualAuthDistinctUserCount], this method doesn't throw if the JSON field has an
     * unexpected type.
     */
    @JsonProperty("individual_auth_distinct_user_count")
    @ExcludeMissing
    fun _individualAuthDistinctUserCount(): JsonField<Long> = individualAuthDistinctUserCount

    /**
     * Returns the raw JSON value of [managedAuthDistinctUserCount].
     *
     * Unlike [managedAuthDistinctUserCount], this method doesn't throw if the JSON field has an
     * unexpected type.
     */
    @JsonProperty("managed_auth_distinct_user_count")
    @ExcludeMissing
    fun _managedAuthDistinctUserCount(): JsonField<Long> = managedAuthDistinctUserCount

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
     * Returns the raw JSON value of [readCallCount].
     *
     * Unlike [readCallCount], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("read_call_count")
    @ExcludeMissing
    fun _readCallCount(): JsonField<Long> = readCallCount

    /**
     * Returns the raw JSON value of [unclassifiedCallCount].
     *
     * Unlike [unclassifiedCallCount], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("unclassified_call_count")
    @ExcludeMissing
    fun _unclassifiedCallCount(): JsonField<Long> = unclassifiedCallCount

    /**
     * Returns the raw JSON value of [userId].
     *
     * Unlike [userId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("user_id") @ExcludeMissing fun _userId(): JsonField<String> = userId

    /**
     * Returns the raw JSON value of [writeCallCount].
     *
     * Unlike [writeCallCount], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("write_call_count")
    @ExcludeMissing
    fun _writeCallCount(): JsonField<Long> = writeCallCount

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
         * [BetaAnalyticsConnectorActivity].
         *
         * The following fields are required:
         * ```java
         * .chatMetrics()
         * .claudeCodeMetrics()
         * .connectorName()
         * .coworkMetrics()
         * .distinctUserCount()
         * .officeMetrics()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaAnalyticsConnectorActivity]. */
    class Builder internal constructor() {

        private var chatMetrics: JsonField<BetaAnalyticsConnectorChatMetrics>? = null
        private var claudeCodeMetrics: JsonField<BetaAnalyticsConnectorClaudeCodeMetrics>? = null
        private var connectorName: JsonField<String>? = null
        private var coworkMetrics: JsonField<BetaAnalyticsConnectorCoworkMetrics>? = null
        private var distinctUserCount: JsonField<Long>? = null
        private var officeMetrics: JsonField<BetaAnalyticsConnectorOfficeMetrics>? = null
        private var connectorDisplayName: JsonField<String> = JsonMissing.of()
        private var individualAuthDistinctUserCount: JsonField<Long> = JsonMissing.of()
        private var managedAuthDistinctUserCount: JsonField<Long> = JsonMissing.of()
        private var product: JsonField<String> = JsonMissing.of()
        private var rbacGroupId: JsonField<String> = JsonMissing.of()
        private var rbacGroupName: JsonField<String> = JsonMissing.of()
        private var readCallCount: JsonField<Long> = JsonMissing.of()
        private var unclassifiedCallCount: JsonField<Long> = JsonMissing.of()
        private var userId: JsonField<String> = JsonMissing.of()
        private var writeCallCount: JsonField<Long> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaAnalyticsConnectorActivity: BetaAnalyticsConnectorActivity) = apply {
            chatMetrics = betaAnalyticsConnectorActivity.chatMetrics
            claudeCodeMetrics = betaAnalyticsConnectorActivity.claudeCodeMetrics
            connectorName = betaAnalyticsConnectorActivity.connectorName
            coworkMetrics = betaAnalyticsConnectorActivity.coworkMetrics
            distinctUserCount = betaAnalyticsConnectorActivity.distinctUserCount
            officeMetrics = betaAnalyticsConnectorActivity.officeMetrics
            connectorDisplayName = betaAnalyticsConnectorActivity.connectorDisplayName
            individualAuthDistinctUserCount =
                betaAnalyticsConnectorActivity.individualAuthDistinctUserCount
            managedAuthDistinctUserCount =
                betaAnalyticsConnectorActivity.managedAuthDistinctUserCount
            product = betaAnalyticsConnectorActivity.product
            rbacGroupId = betaAnalyticsConnectorActivity.rbacGroupId
            rbacGroupName = betaAnalyticsConnectorActivity.rbacGroupName
            readCallCount = betaAnalyticsConnectorActivity.readCallCount
            unclassifiedCallCount = betaAnalyticsConnectorActivity.unclassifiedCallCount
            userId = betaAnalyticsConnectorActivity.userId
            writeCallCount = betaAnalyticsConnectorActivity.writeCallCount
            additionalProperties =
                betaAnalyticsConnectorActivity.additionalProperties.toMutableMap()
        }

        /** Claude.ai activity metrics for a single connector on a given day. */
        fun chatMetrics(chatMetrics: BetaAnalyticsConnectorChatMetrics) =
            chatMetrics(JsonField.of(chatMetrics))

        /**
         * Sets [Builder.chatMetrics] to an arbitrary JSON value.
         *
         * You should usually call [Builder.chatMetrics] with a well-typed
         * [BetaAnalyticsConnectorChatMetrics] value instead. This method is primarily for setting
         * the field to an undocumented or not yet supported value.
         */
        fun chatMetrics(chatMetrics: JsonField<BetaAnalyticsConnectorChatMetrics>) = apply {
            this.chatMetrics = chatMetrics
        }

        /** Claude Code activity metrics for a single connector on a given day. */
        fun claudeCodeMetrics(claudeCodeMetrics: BetaAnalyticsConnectorClaudeCodeMetrics) =
            claudeCodeMetrics(JsonField.of(claudeCodeMetrics))

        /**
         * Sets [Builder.claudeCodeMetrics] to an arbitrary JSON value.
         *
         * You should usually call [Builder.claudeCodeMetrics] with a well-typed
         * [BetaAnalyticsConnectorClaudeCodeMetrics] value instead. This method is primarily for
         * setting the field to an undocumented or not yet supported value.
         */
        fun claudeCodeMetrics(
            claudeCodeMetrics: JsonField<BetaAnalyticsConnectorClaudeCodeMetrics>
        ) = apply { this.claudeCodeMetrics = claudeCodeMetrics }

        /**
         * Name of the connector. Some rows carry an opaque connector id here instead of a readable
         * name; `connector_display_name` holds the resolved name for those rows.
         */
        fun connectorName(connectorName: String) = connectorName(JsonField.of(connectorName))

        /**
         * Sets [Builder.connectorName] to an arbitrary JSON value.
         *
         * You should usually call [Builder.connectorName] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun connectorName(connectorName: JsonField<String>) = apply {
            this.connectorName = connectorName
        }

        /** Cowork activity metrics for a single connector on a given day. */
        fun coworkMetrics(coworkMetrics: BetaAnalyticsConnectorCoworkMetrics) =
            coworkMetrics(JsonField.of(coworkMetrics))

        /**
         * Sets [Builder.coworkMetrics] to an arbitrary JSON value.
         *
         * You should usually call [Builder.coworkMetrics] with a well-typed
         * [BetaAnalyticsConnectorCoworkMetrics] value instead. This method is primarily for setting
         * the field to an undocumented or not yet supported value.
         */
        fun coworkMetrics(coworkMetrics: JsonField<BetaAnalyticsConnectorCoworkMetrics>) = apply {
            this.coworkMetrics = coworkMetrics
        }

        /**
         * Number of distinct users who used the connector on the requested day, or, in date-range
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

        /**
         * Office Agent activity metrics for a single connector on a given day, broken out by Office
         * product.
         */
        fun officeMetrics(officeMetrics: BetaAnalyticsConnectorOfficeMetrics) =
            officeMetrics(JsonField.of(officeMetrics))

        /**
         * Sets [Builder.officeMetrics] to an arbitrary JSON value.
         *
         * You should usually call [Builder.officeMetrics] with a well-typed
         * [BetaAnalyticsConnectorOfficeMetrics] value instead. This method is primarily for setting
         * the field to an undocumented or not yet supported value.
         */
        fun officeMetrics(officeMetrics: JsonField<BetaAnalyticsConnectorOfficeMetrics>) = apply {
            this.officeMetrics = officeMetrics
        }

        /**
         * Human-readable display name for rows whose `connector_name` is an opaque connector id
         * rather than a readable name, resolved at request time from the organization's connectors
         * (including connectors that have since been removed). `connector_name` remains the row's
         * stable key for sorting and pagination, and `filter[]=connector_name:{value}` also matches
         * these rows by display name. Display names are not unique, and the same connector's
         * claude.ai usage can appear under a separate row with a readable `connector_name`. Null
         * when `connector_name` is already a readable name, when the id cannot be resolved to one
         * of the organization's connectors, or when display-name resolution is not enabled for this
         * organization.
         */
        fun connectorDisplayName(connectorDisplayName: String?) =
            connectorDisplayName(JsonField.ofNullable(connectorDisplayName))

        /**
         * Alias for calling [Builder.connectorDisplayName] with
         * `connectorDisplayName.orElse(null)`.
         */
        fun connectorDisplayName(connectorDisplayName: Optional<String>) =
            connectorDisplayName(connectorDisplayName.getOrNull())

        /**
         * Sets [Builder.connectorDisplayName] to an arbitrary JSON value.
         *
         * You should usually call [Builder.connectorDisplayName] with a well-typed [String] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun connectorDisplayName(connectorDisplayName: JsonField<String>) = apply {
            this.connectorDisplayName = connectorDisplayName
        }

        /**
         * Number of distinct users whose use of this connector on the requested day ran on their
         * own individual credential, connected through their own consent flow. Companion bucket to
         * `managed_auth_distinct_user_count`, which carries the measurement, attribution, and null
         * rules. Users whose requests used no stored credential count in neither bucket.
         */
        fun individualAuthDistinctUserCount(individualAuthDistinctUserCount: Long?) =
            individualAuthDistinctUserCount(JsonField.ofNullable(individualAuthDistinctUserCount))

        /**
         * Alias for [Builder.individualAuthDistinctUserCount].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun individualAuthDistinctUserCount(individualAuthDistinctUserCount: Long) =
            individualAuthDistinctUserCount(individualAuthDistinctUserCount as Long?)

        /**
         * Alias for calling [Builder.individualAuthDistinctUserCount] with
         * `individualAuthDistinctUserCount.orElse(null)`.
         */
        fun individualAuthDistinctUserCount(individualAuthDistinctUserCount: Optional<Long>) =
            individualAuthDistinctUserCount(individualAuthDistinctUserCount.getOrNull())

        /**
         * Sets [Builder.individualAuthDistinctUserCount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.individualAuthDistinctUserCount] with a well-typed
         * [Long] value instead. This method is primarily for setting the field to an undocumented
         * or not yet supported value.
         */
        fun individualAuthDistinctUserCount(individualAuthDistinctUserCount: JsonField<Long>) =
            apply {
                this.individualAuthDistinctUserCount = individualAuthDistinctUserCount
            }

        /**
         * Number of distinct users whose use of this connector on the requested day ran on
         * Enterprise Managed Auth (an organization-managed credential provisioned through the
         * organization's identity provider), read from the token record each request used. Null,
         * never 0, when managed-auth reporting is not enabled for the organization, the value
         * cannot be attributed to the row, no credentialed requests and no managed-token mint
         * events (a managed credential being provisioned for a user's use of the connector) were
         * observed that day, or the day predates 2026-07-01, the first day the backing data exists
         * (forward-only data, no backfill). When credentialed requests or mint events were observed
         * and attributed, both managed-auth fields populate, reporting 0 for a bucket with no
         * users; the two counts are independent, not a partition — a user whose requests that day
         * used both kinds of credential counts in both. Mint events carry user but not surface
         * attribution, so they count as observed auth activity on `user_id` and `rbac_group_id`
         * cuts — attributed to the user the credential was provisioned for — but never on a cut
         * that references `product` (group or filter). Date-range rollup mode
         * (`starting_date`/`ending_date`) computes both fields exactly over the window — distinct
         * users with at least one qualifying day — when the whole window starts on or after
         * 2026-07-01, with the null-versus-0 and mint-event rules applying with the window in place
         * of the day; a range starting earlier reports every managed-auth field as null, never a
         * partial-window value.
         */
        fun managedAuthDistinctUserCount(managedAuthDistinctUserCount: Long?) =
            managedAuthDistinctUserCount(JsonField.ofNullable(managedAuthDistinctUserCount))

        /**
         * Alias for [Builder.managedAuthDistinctUserCount].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun managedAuthDistinctUserCount(managedAuthDistinctUserCount: Long) =
            managedAuthDistinctUserCount(managedAuthDistinctUserCount as Long?)

        /**
         * Alias for calling [Builder.managedAuthDistinctUserCount] with
         * `managedAuthDistinctUserCount.orElse(null)`.
         */
        fun managedAuthDistinctUserCount(managedAuthDistinctUserCount: Optional<Long>) =
            managedAuthDistinctUserCount(managedAuthDistinctUserCount.getOrNull())

        /**
         * Sets [Builder.managedAuthDistinctUserCount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.managedAuthDistinctUserCount] with a well-typed [Long]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun managedAuthDistinctUserCount(managedAuthDistinctUserCount: JsonField<Long>) = apply {
            this.managedAuthDistinctUserCount = managedAuthDistinctUserCount
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
         * Number of connector tool calls on the requested day whose trusted read-only annotation
         * marked them read-only. Call count, not distinct users. Every call recorded on a
         * classified surface lands in exactly one of `read_call_count`, `write_call_count`, or
         * `unclassified_call_count`, so the three sum to the day's classified calls. Classification
         * is forward-only per surface: claude.ai from 2026-06-01, Claude Code from 2026-05-30,
         * Claude in Office from 2026-05-29, Cowork from 2026-06-02 (Cowork clients predating
         * annotation forwarding land in `unclassified_call_count`). Null, never 0, when the value
         * cannot be stated: the read/write split is not enabled for this organization, or the day
         * predates 2026-05-29. For a date-range total, sum the per-day values, but treat a window
         * that extends before 2026-05-29 as null rather than summing only its covered days —
         * date-range rollup mode (`starting_date`/`ending_date`) applies both rules server-side.
         */
        fun readCallCount(readCallCount: Long?) = readCallCount(JsonField.ofNullable(readCallCount))

        /**
         * Alias for [Builder.readCallCount].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun readCallCount(readCallCount: Long) = readCallCount(readCallCount as Long?)

        /** Alias for calling [Builder.readCallCount] with `readCallCount.orElse(null)`. */
        fun readCallCount(readCallCount: Optional<Long>) = readCallCount(readCallCount.getOrNull())

        /**
         * Sets [Builder.readCallCount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.readCallCount] with a well-typed [Long] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun readCallCount(readCallCount: JsonField<Long>) = apply {
            this.readCallCount = readCallCount
        }

        /**
         * Number of connector tool calls on the requested day with no trusted read-only annotation
         * — the annotation is optional in the MCP spec and is discarded when connector access
         * controls are active, so unclassified calls are common. This field shows how much of the
         * day's classified activity the read/write split actually covers. Call count, not distinct
         * users. One of the three call-classification buckets; see `read_call_count` for the
         * per-surface data-start dates, null conditions, and date-range guidance.
         */
        fun unclassifiedCallCount(unclassifiedCallCount: Long?) =
            unclassifiedCallCount(JsonField.ofNullable(unclassifiedCallCount))

        /**
         * Alias for [Builder.unclassifiedCallCount].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun unclassifiedCallCount(unclassifiedCallCount: Long) =
            unclassifiedCallCount(unclassifiedCallCount as Long?)

        /**
         * Alias for calling [Builder.unclassifiedCallCount] with
         * `unclassifiedCallCount.orElse(null)`.
         */
        fun unclassifiedCallCount(unclassifiedCallCount: Optional<Long>) =
            unclassifiedCallCount(unclassifiedCallCount.getOrNull())

        /**
         * Sets [Builder.unclassifiedCallCount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.unclassifiedCallCount] with a well-typed [Long] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun unclassifiedCallCount(unclassifiedCallCount: JsonField<Long>) = apply {
            this.unclassifiedCallCount = unclassifiedCallCount
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

        /**
         * Number of connector tool calls on the requested day whose trusted read-only annotation
         * marked them not read-only. Call count, not distinct users. One of the three
         * call-classification buckets; see `read_call_count` for the per-surface data-start dates,
         * null conditions, and date-range guidance.
         */
        fun writeCallCount(writeCallCount: Long?) =
            writeCallCount(JsonField.ofNullable(writeCallCount))

        /**
         * Alias for [Builder.writeCallCount].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun writeCallCount(writeCallCount: Long) = writeCallCount(writeCallCount as Long?)

        /** Alias for calling [Builder.writeCallCount] with `writeCallCount.orElse(null)`. */
        fun writeCallCount(writeCallCount: Optional<Long>) =
            writeCallCount(writeCallCount.getOrNull())

        /**
         * Sets [Builder.writeCallCount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.writeCallCount] with a well-typed [Long] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun writeCallCount(writeCallCount: JsonField<Long>) = apply {
            this.writeCallCount = writeCallCount
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
         * Returns an immutable instance of [BetaAnalyticsConnectorActivity].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .chatMetrics()
         * .claudeCodeMetrics()
         * .connectorName()
         * .coworkMetrics()
         * .distinctUserCount()
         * .officeMetrics()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaAnalyticsConnectorActivity =
            BetaAnalyticsConnectorActivity(
                checkRequired("chatMetrics", chatMetrics),
                checkRequired("claudeCodeMetrics", claudeCodeMetrics),
                checkRequired("connectorName", connectorName),
                checkRequired("coworkMetrics", coworkMetrics),
                checkRequired("distinctUserCount", distinctUserCount),
                checkRequired("officeMetrics", officeMetrics),
                connectorDisplayName,
                individualAuthDistinctUserCount,
                managedAuthDistinctUserCount,
                product,
                rbacGroupId,
                rbacGroupName,
                readCallCount,
                unclassifiedCallCount,
                userId,
                writeCallCount,
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
    fun validate(): BetaAnalyticsConnectorActivity = apply {
        if (validated) {
            return@apply
        }

        chatMetrics().validate()
        claudeCodeMetrics().validate()
        connectorName()
        coworkMetrics().validate()
        distinctUserCount()
        officeMetrics().validate()
        connectorDisplayName()
        individualAuthDistinctUserCount()
        managedAuthDistinctUserCount()
        product()
        rbacGroupId()
        rbacGroupName()
        readCallCount()
        unclassifiedCallCount()
        userId()
        writeCallCount()
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
            (if (connectorName.asKnown().isPresent) 1 else 0) +
            (coworkMetrics.asKnown().getOrNull()?.validity() ?: 0) +
            (if (distinctUserCount.asKnown().isPresent) 1 else 0) +
            (officeMetrics.asKnown().getOrNull()?.validity() ?: 0) +
            (if (connectorDisplayName.asKnown().isPresent) 1 else 0) +
            (if (individualAuthDistinctUserCount.asKnown().isPresent) 1 else 0) +
            (if (managedAuthDistinctUserCount.asKnown().isPresent) 1 else 0) +
            (if (product.asKnown().isPresent) 1 else 0) +
            (if (rbacGroupId.asKnown().isPresent) 1 else 0) +
            (if (rbacGroupName.asKnown().isPresent) 1 else 0) +
            (if (readCallCount.asKnown().isPresent) 1 else 0) +
            (if (unclassifiedCallCount.asKnown().isPresent) 1 else 0) +
            (if (userId.asKnown().isPresent) 1 else 0) +
            (if (writeCallCount.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaAnalyticsConnectorActivity &&
            chatMetrics == other.chatMetrics &&
            claudeCodeMetrics == other.claudeCodeMetrics &&
            connectorName == other.connectorName &&
            coworkMetrics == other.coworkMetrics &&
            distinctUserCount == other.distinctUserCount &&
            officeMetrics == other.officeMetrics &&
            connectorDisplayName == other.connectorDisplayName &&
            individualAuthDistinctUserCount == other.individualAuthDistinctUserCount &&
            managedAuthDistinctUserCount == other.managedAuthDistinctUserCount &&
            product == other.product &&
            rbacGroupId == other.rbacGroupId &&
            rbacGroupName == other.rbacGroupName &&
            readCallCount == other.readCallCount &&
            unclassifiedCallCount == other.unclassifiedCallCount &&
            userId == other.userId &&
            writeCallCount == other.writeCallCount &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            chatMetrics,
            claudeCodeMetrics,
            connectorName,
            coworkMetrics,
            distinctUserCount,
            officeMetrics,
            connectorDisplayName,
            individualAuthDistinctUserCount,
            managedAuthDistinctUserCount,
            product,
            rbacGroupId,
            rbacGroupName,
            readCallCount,
            unclassifiedCallCount,
            userId,
            writeCallCount,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaAnalyticsConnectorActivity{chatMetrics=$chatMetrics, claudeCodeMetrics=$claudeCodeMetrics, connectorName=$connectorName, coworkMetrics=$coworkMetrics, distinctUserCount=$distinctUserCount, officeMetrics=$officeMetrics, connectorDisplayName=$connectorDisplayName, individualAuthDistinctUserCount=$individualAuthDistinctUserCount, managedAuthDistinctUserCount=$managedAuthDistinctUserCount, product=$product, rbacGroupId=$rbacGroupId, rbacGroupName=$rbacGroupName, readCallCount=$readCallCount, unclassifiedCallCount=$unclassifiedCallCount, userId=$userId, writeCallCount=$writeCallCount, additionalProperties=$additionalProperties}"
}
