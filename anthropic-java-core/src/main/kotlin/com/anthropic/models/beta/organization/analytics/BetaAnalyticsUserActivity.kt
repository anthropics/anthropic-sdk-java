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
import java.time.LocalDate
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** Per-user activity data for a given day. */
class BetaAnalyticsUserActivity
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val chatMetrics: JsonField<BetaAnalyticsChatMetrics>,
    private val claudeCodeMetrics: JsonField<BetaAnalyticsClaudeCodeMetrics>,
    private val coworkMetrics: JsonField<BetaAnalyticsCoworkMetrics>,
    private val designMetrics: JsonField<BetaAnalyticsDesignMetrics>,
    private val officeMetrics: JsonField<BetaAnalyticsOfficeMetrics>,
    private val scienceMetrics: JsonField<BetaAnalyticsScienceMetrics>,
    private val webSearchCount: JsonField<Long>,
    private val chatCoworkUnifiedMetrics: JsonField<ChatCoworkUnifiedMetrics>,
    private val distinctUserCount: JsonField<Long>,
    private val lastActivityDate: JsonField<LocalDate>,
    private val rbacGroupId: JsonField<String>,
    private val rbacGroupName: JsonField<String>,
    private val user: JsonField<BetaAnalyticsUser>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("chat_metrics")
        @ExcludeMissing
        chatMetrics: JsonField<BetaAnalyticsChatMetrics> = JsonMissing.of(),
        @JsonProperty("claude_code_metrics")
        @ExcludeMissing
        claudeCodeMetrics: JsonField<BetaAnalyticsClaudeCodeMetrics> = JsonMissing.of(),
        @JsonProperty("cowork_metrics")
        @ExcludeMissing
        coworkMetrics: JsonField<BetaAnalyticsCoworkMetrics> = JsonMissing.of(),
        @JsonProperty("design_metrics")
        @ExcludeMissing
        designMetrics: JsonField<BetaAnalyticsDesignMetrics> = JsonMissing.of(),
        @JsonProperty("office_metrics")
        @ExcludeMissing
        officeMetrics: JsonField<BetaAnalyticsOfficeMetrics> = JsonMissing.of(),
        @JsonProperty("science_metrics")
        @ExcludeMissing
        scienceMetrics: JsonField<BetaAnalyticsScienceMetrics> = JsonMissing.of(),
        @JsonProperty("web_search_count")
        @ExcludeMissing
        webSearchCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("chat_cowork_unified_metrics")
        @ExcludeMissing
        chatCoworkUnifiedMetrics: JsonField<ChatCoworkUnifiedMetrics> = JsonMissing.of(),
        @JsonProperty("distinct_user_count")
        @ExcludeMissing
        distinctUserCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("last_activity_date")
        @ExcludeMissing
        lastActivityDate: JsonField<LocalDate> = JsonMissing.of(),
        @JsonProperty("rbac_group_id")
        @ExcludeMissing
        rbacGroupId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("rbac_group_name")
        @ExcludeMissing
        rbacGroupName: JsonField<String> = JsonMissing.of(),
        @JsonProperty("user") @ExcludeMissing user: JsonField<BetaAnalyticsUser> = JsonMissing.of(),
    ) : this(
        chatMetrics,
        claudeCodeMetrics,
        coworkMetrics,
        designMetrics,
        officeMetrics,
        scienceMetrics,
        webSearchCount,
        chatCoworkUnifiedMetrics,
        distinctUserCount,
        lastActivityDate,
        rbacGroupId,
        rbacGroupName,
        user,
        mutableMapOf(),
    )

    /**
     * Claude.ai activity metrics for a single user on a given day.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun chatMetrics(): BetaAnalyticsChatMetrics = chatMetrics.getRequired("chat_metrics")

    /**
     * Claude Code activity metrics for a single user on a given day.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun claudeCodeMetrics(): BetaAnalyticsClaudeCodeMetrics =
        claudeCodeMetrics.getRequired("claude_code_metrics")

    /**
     * Cowork activity metrics for a single user on a given day.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun coworkMetrics(): BetaAnalyticsCoworkMetrics = coworkMetrics.getRequired("cowork_metrics")

    /**
     * Claude Design activity metrics for a single user on a given day.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun designMetrics(): BetaAnalyticsDesignMetrics = designMetrics.getRequired("design_metrics")

    /**
     * Office Agent activity metrics for a single user on a given day, broken out by Office product.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun officeMetrics(): BetaAnalyticsOfficeMetrics = officeMetrics.getRequired("office_metrics")

    /**
     * Claude Science activity metrics for a single user on a given day.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun scienceMetrics(): BetaAnalyticsScienceMetrics =
        scienceMetrics.getRequired("science_metrics")

    /**
     * Number of web searches performed
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun webSearchCount(): Long = webSearchCount.getRequired("web_search_count")

    /**
     * Activity recorded while the member had Chat and Cowork unified (Cowork's features inside
     * claude.ai chat) turned on, split into `chat` (chat activity) and `sessions` (Cowork
     * activity). Omitted from the response on deployments that do not offer Chat and Cowork
     * unified.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun chatCoworkUnifiedMetrics(): Optional<ChatCoworkUnifiedMetrics> =
        chatCoworkUnifiedMetrics.getOptional("chat_cowork_unified_metrics")

    /**
     * Number of distinct active users represented by this row. Only set for grouped rollups
     * (`group_by[]`); null for per-user rows. In date-range mode, recomputed as an exact distinct
     * count of the group's active members over the requested window, never a sum of per-day values.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun distinctUserCount(): Optional<Long> = distinctUserCount.getOptional("distinct_user_count")

    /**
     * Most recent UTC day (YYYY-MM-DD) on which the user had any counted activity, within the
     * requested window: equal to the requested `date` in single-day mode, and to the latest active
     * day from `starting_date` (inclusive) to `ending_date` (exclusive) in date-range rollup mode —
     * never a day earlier than the window start. On filtered requests (`filter[]`) only days
     * matching the filter count: with `filter[]=rbac_group_id:{id}` it is the last day the user was
     * active while a member of that group, consistent with the row's other metrics. On grouped
     * (`group_by[]`) rows it is the latest day any member of the group was active (the requested
     * `date` in single-day mode). Omitted from the response while last-activity reporting is not
     * enabled for this organization.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun lastActivityDate(): Optional<LocalDate> = lastActivityDate.getOptional("last_activity_date")

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
     * The user this row describes. Null on rows aggregated across users.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun user(): Optional<BetaAnalyticsUser> = user.getOptional("user")

    /**
     * Returns the raw JSON value of [chatMetrics].
     *
     * Unlike [chatMetrics], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("chat_metrics")
    @ExcludeMissing
    fun _chatMetrics(): JsonField<BetaAnalyticsChatMetrics> = chatMetrics

    /**
     * Returns the raw JSON value of [claudeCodeMetrics].
     *
     * Unlike [claudeCodeMetrics], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("claude_code_metrics")
    @ExcludeMissing
    fun _claudeCodeMetrics(): JsonField<BetaAnalyticsClaudeCodeMetrics> = claudeCodeMetrics

    /**
     * Returns the raw JSON value of [coworkMetrics].
     *
     * Unlike [coworkMetrics], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("cowork_metrics")
    @ExcludeMissing
    fun _coworkMetrics(): JsonField<BetaAnalyticsCoworkMetrics> = coworkMetrics

    /**
     * Returns the raw JSON value of [designMetrics].
     *
     * Unlike [designMetrics], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("design_metrics")
    @ExcludeMissing
    fun _designMetrics(): JsonField<BetaAnalyticsDesignMetrics> = designMetrics

    /**
     * Returns the raw JSON value of [officeMetrics].
     *
     * Unlike [officeMetrics], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("office_metrics")
    @ExcludeMissing
    fun _officeMetrics(): JsonField<BetaAnalyticsOfficeMetrics> = officeMetrics

    /**
     * Returns the raw JSON value of [scienceMetrics].
     *
     * Unlike [scienceMetrics], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("science_metrics")
    @ExcludeMissing
    fun _scienceMetrics(): JsonField<BetaAnalyticsScienceMetrics> = scienceMetrics

    /**
     * Returns the raw JSON value of [webSearchCount].
     *
     * Unlike [webSearchCount], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("web_search_count")
    @ExcludeMissing
    fun _webSearchCount(): JsonField<Long> = webSearchCount

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
     * Returns the raw JSON value of [distinctUserCount].
     *
     * Unlike [distinctUserCount], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("distinct_user_count")
    @ExcludeMissing
    fun _distinctUserCount(): JsonField<Long> = distinctUserCount

    /**
     * Returns the raw JSON value of [lastActivityDate].
     *
     * Unlike [lastActivityDate], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("last_activity_date")
    @ExcludeMissing
    fun _lastActivityDate(): JsonField<LocalDate> = lastActivityDate

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
     * Returns the raw JSON value of [user].
     *
     * Unlike [user], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("user") @ExcludeMissing fun _user(): JsonField<BetaAnalyticsUser> = user

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
         * Returns a mutable builder for constructing an instance of [BetaAnalyticsUserActivity].
         *
         * The following fields are required:
         * ```java
         * .chatMetrics()
         * .claudeCodeMetrics()
         * .coworkMetrics()
         * .designMetrics()
         * .officeMetrics()
         * .scienceMetrics()
         * .webSearchCount()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaAnalyticsUserActivity]. */
    class Builder internal constructor() {

        private var chatMetrics: JsonField<BetaAnalyticsChatMetrics>? = null
        private var claudeCodeMetrics: JsonField<BetaAnalyticsClaudeCodeMetrics>? = null
        private var coworkMetrics: JsonField<BetaAnalyticsCoworkMetrics>? = null
        private var designMetrics: JsonField<BetaAnalyticsDesignMetrics>? = null
        private var officeMetrics: JsonField<BetaAnalyticsOfficeMetrics>? = null
        private var scienceMetrics: JsonField<BetaAnalyticsScienceMetrics>? = null
        private var webSearchCount: JsonField<Long>? = null
        private var chatCoworkUnifiedMetrics: JsonField<ChatCoworkUnifiedMetrics> = JsonMissing.of()
        private var distinctUserCount: JsonField<Long> = JsonMissing.of()
        private var lastActivityDate: JsonField<LocalDate> = JsonMissing.of()
        private var rbacGroupId: JsonField<String> = JsonMissing.of()
        private var rbacGroupName: JsonField<String> = JsonMissing.of()
        private var user: JsonField<BetaAnalyticsUser> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaAnalyticsUserActivity: BetaAnalyticsUserActivity) = apply {
            chatMetrics = betaAnalyticsUserActivity.chatMetrics
            claudeCodeMetrics = betaAnalyticsUserActivity.claudeCodeMetrics
            coworkMetrics = betaAnalyticsUserActivity.coworkMetrics
            designMetrics = betaAnalyticsUserActivity.designMetrics
            officeMetrics = betaAnalyticsUserActivity.officeMetrics
            scienceMetrics = betaAnalyticsUserActivity.scienceMetrics
            webSearchCount = betaAnalyticsUserActivity.webSearchCount
            chatCoworkUnifiedMetrics = betaAnalyticsUserActivity.chatCoworkUnifiedMetrics
            distinctUserCount = betaAnalyticsUserActivity.distinctUserCount
            lastActivityDate = betaAnalyticsUserActivity.lastActivityDate
            rbacGroupId = betaAnalyticsUserActivity.rbacGroupId
            rbacGroupName = betaAnalyticsUserActivity.rbacGroupName
            user = betaAnalyticsUserActivity.user
            additionalProperties = betaAnalyticsUserActivity.additionalProperties.toMutableMap()
        }

        /** Claude.ai activity metrics for a single user on a given day. */
        fun chatMetrics(chatMetrics: BetaAnalyticsChatMetrics) =
            chatMetrics(JsonField.of(chatMetrics))

        /**
         * Sets [Builder.chatMetrics] to an arbitrary JSON value.
         *
         * You should usually call [Builder.chatMetrics] with a well-typed
         * [BetaAnalyticsChatMetrics] value instead. This method is primarily for setting the field
         * to an undocumented or not yet supported value.
         */
        fun chatMetrics(chatMetrics: JsonField<BetaAnalyticsChatMetrics>) = apply {
            this.chatMetrics = chatMetrics
        }

        /** Claude Code activity metrics for a single user on a given day. */
        fun claudeCodeMetrics(claudeCodeMetrics: BetaAnalyticsClaudeCodeMetrics) =
            claudeCodeMetrics(JsonField.of(claudeCodeMetrics))

        /**
         * Sets [Builder.claudeCodeMetrics] to an arbitrary JSON value.
         *
         * You should usually call [Builder.claudeCodeMetrics] with a well-typed
         * [BetaAnalyticsClaudeCodeMetrics] value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun claudeCodeMetrics(claudeCodeMetrics: JsonField<BetaAnalyticsClaudeCodeMetrics>) =
            apply {
                this.claudeCodeMetrics = claudeCodeMetrics
            }

        /** Cowork activity metrics for a single user on a given day. */
        fun coworkMetrics(coworkMetrics: BetaAnalyticsCoworkMetrics) =
            coworkMetrics(JsonField.of(coworkMetrics))

        /**
         * Sets [Builder.coworkMetrics] to an arbitrary JSON value.
         *
         * You should usually call [Builder.coworkMetrics] with a well-typed
         * [BetaAnalyticsCoworkMetrics] value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun coworkMetrics(coworkMetrics: JsonField<BetaAnalyticsCoworkMetrics>) = apply {
            this.coworkMetrics = coworkMetrics
        }

        /** Claude Design activity metrics for a single user on a given day. */
        fun designMetrics(designMetrics: BetaAnalyticsDesignMetrics) =
            designMetrics(JsonField.of(designMetrics))

        /**
         * Sets [Builder.designMetrics] to an arbitrary JSON value.
         *
         * You should usually call [Builder.designMetrics] with a well-typed
         * [BetaAnalyticsDesignMetrics] value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun designMetrics(designMetrics: JsonField<BetaAnalyticsDesignMetrics>) = apply {
            this.designMetrics = designMetrics
        }

        /**
         * Office Agent activity metrics for a single user on a given day, broken out by Office
         * product.
         */
        fun officeMetrics(officeMetrics: BetaAnalyticsOfficeMetrics) =
            officeMetrics(JsonField.of(officeMetrics))

        /**
         * Sets [Builder.officeMetrics] to an arbitrary JSON value.
         *
         * You should usually call [Builder.officeMetrics] with a well-typed
         * [BetaAnalyticsOfficeMetrics] value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun officeMetrics(officeMetrics: JsonField<BetaAnalyticsOfficeMetrics>) = apply {
            this.officeMetrics = officeMetrics
        }

        /** Claude Science activity metrics for a single user on a given day. */
        fun scienceMetrics(scienceMetrics: BetaAnalyticsScienceMetrics) =
            scienceMetrics(JsonField.of(scienceMetrics))

        /**
         * Sets [Builder.scienceMetrics] to an arbitrary JSON value.
         *
         * You should usually call [Builder.scienceMetrics] with a well-typed
         * [BetaAnalyticsScienceMetrics] value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun scienceMetrics(scienceMetrics: JsonField<BetaAnalyticsScienceMetrics>) = apply {
            this.scienceMetrics = scienceMetrics
        }

        /** Number of web searches performed */
        fun webSearchCount(webSearchCount: Long) = webSearchCount(JsonField.of(webSearchCount))

        /**
         * Sets [Builder.webSearchCount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.webSearchCount] with a well-typed [Long] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun webSearchCount(webSearchCount: JsonField<Long>) = apply {
            this.webSearchCount = webSearchCount
        }

        /**
         * Activity recorded while the member had Chat and Cowork unified (Cowork's features inside
         * claude.ai chat) turned on, split into `chat` (chat activity) and `sessions` (Cowork
         * activity). Omitted from the response on deployments that do not offer Chat and Cowork
         * unified.
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
         * Number of distinct active users represented by this row. Only set for grouped rollups
         * (`group_by[]`); null for per-user rows. In date-range mode, recomputed as an exact
         * distinct count of the group's active members over the requested window, never a sum of
         * per-day values.
         */
        fun distinctUserCount(distinctUserCount: Long?) =
            distinctUserCount(JsonField.ofNullable(distinctUserCount))

        /**
         * Alias for [Builder.distinctUserCount].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun distinctUserCount(distinctUserCount: Long) =
            distinctUserCount(distinctUserCount as Long?)

        /** Alias for calling [Builder.distinctUserCount] with `distinctUserCount.orElse(null)`. */
        fun distinctUserCount(distinctUserCount: Optional<Long>) =
            distinctUserCount(distinctUserCount.getOrNull())

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
         * Most recent UTC day (YYYY-MM-DD) on which the user had any counted activity, within the
         * requested window: equal to the requested `date` in single-day mode, and to the latest
         * active day from `starting_date` (inclusive) to `ending_date` (exclusive) in date-range
         * rollup mode — never a day earlier than the window start. On filtered requests
         * (`filter[]`) only days matching the filter count: with `filter[]=rbac_group_id:{id}` it
         * is the last day the user was active while a member of that group, consistent with the
         * row's other metrics. On grouped (`group_by[]`) rows it is the latest day any member of
         * the group was active (the requested `date` in single-day mode). Omitted from the response
         * while last-activity reporting is not enabled for this organization.
         */
        fun lastActivityDate(lastActivityDate: LocalDate?) =
            lastActivityDate(JsonField.ofNullable(lastActivityDate))

        /** Alias for calling [Builder.lastActivityDate] with `lastActivityDate.orElse(null)`. */
        fun lastActivityDate(lastActivityDate: Optional<LocalDate>) =
            lastActivityDate(lastActivityDate.getOrNull())

        /**
         * Sets [Builder.lastActivityDate] to an arbitrary JSON value.
         *
         * You should usually call [Builder.lastActivityDate] with a well-typed [LocalDate] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun lastActivityDate(lastActivityDate: JsonField<LocalDate>) = apply {
            this.lastActivityDate = lastActivityDate
        }

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

        /** The user this row describes. Null on rows aggregated across users. */
        fun user(user: BetaAnalyticsUser?) = user(JsonField.ofNullable(user))

        /** Alias for calling [Builder.user] with `user.orElse(null)`. */
        fun user(user: Optional<BetaAnalyticsUser>) = user(user.getOrNull())

        /**
         * Sets [Builder.user] to an arbitrary JSON value.
         *
         * You should usually call [Builder.user] with a well-typed [BetaAnalyticsUser] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun user(user: JsonField<BetaAnalyticsUser>) = apply { this.user = user }

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
         * Returns an immutable instance of [BetaAnalyticsUserActivity].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .chatMetrics()
         * .claudeCodeMetrics()
         * .coworkMetrics()
         * .designMetrics()
         * .officeMetrics()
         * .scienceMetrics()
         * .webSearchCount()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaAnalyticsUserActivity =
            BetaAnalyticsUserActivity(
                checkRequired("chatMetrics", chatMetrics),
                checkRequired("claudeCodeMetrics", claudeCodeMetrics),
                checkRequired("coworkMetrics", coworkMetrics),
                checkRequired("designMetrics", designMetrics),
                checkRequired("officeMetrics", officeMetrics),
                checkRequired("scienceMetrics", scienceMetrics),
                checkRequired("webSearchCount", webSearchCount),
                chatCoworkUnifiedMetrics,
                distinctUserCount,
                lastActivityDate,
                rbacGroupId,
                rbacGroupName,
                user,
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
    fun validate(): BetaAnalyticsUserActivity = apply {
        if (validated) {
            return@apply
        }

        chatMetrics().validate()
        claudeCodeMetrics().validate()
        coworkMetrics().validate()
        designMetrics().validate()
        officeMetrics().validate()
        scienceMetrics().validate()
        webSearchCount()
        chatCoworkUnifiedMetrics().ifPresent { it.validate() }
        distinctUserCount()
        lastActivityDate()
        rbacGroupId()
        rbacGroupName()
        user().ifPresent { it.validate() }
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
            (designMetrics.asKnown().getOrNull()?.validity() ?: 0) +
            (officeMetrics.asKnown().getOrNull()?.validity() ?: 0) +
            (scienceMetrics.asKnown().getOrNull()?.validity() ?: 0) +
            (if (webSearchCount.asKnown().isPresent) 1 else 0) +
            (chatCoworkUnifiedMetrics.asKnown().getOrNull()?.validity() ?: 0) +
            (if (distinctUserCount.asKnown().isPresent) 1 else 0) +
            (if (lastActivityDate.asKnown().isPresent) 1 else 0) +
            (if (rbacGroupId.asKnown().isPresent) 1 else 0) +
            (if (rbacGroupName.asKnown().isPresent) 1 else 0) +
            (user.asKnown().getOrNull()?.validity() ?: 0)

    /**
     * Activity recorded while the member had Chat and Cowork unified (Cowork's features inside
     * claude.ai chat) turned on, split into `chat` (chat activity) and `sessions` (Cowork
     * activity). Omitted from the response on deployments that do not offer Chat and Cowork
     * unified.
     */
    class ChatCoworkUnifiedMetrics
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val chat: JsonField<Chat>,
        private val sessions: JsonField<Sessions>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("chat") @ExcludeMissing chat: JsonField<Chat> = JsonMissing.of(),
            @JsonProperty("sessions")
            @ExcludeMissing
            sessions: JsonField<Sessions> = JsonMissing.of(),
        ) : this(chat, sessions, mutableMapOf())

        /**
         * Chat activity recorded while members had Chat and Cowork unified turned on.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
         *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
         */
        fun chat(): Chat = chat.getRequired("chat")

        /**
         * Cowork session activity recorded while members had Chat and Cowork unified turned on.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
         *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
         */
        fun sessions(): Sessions = sessions.getRequired("sessions")

        /**
         * Returns the raw JSON value of [chat].
         *
         * Unlike [chat], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("chat") @ExcludeMissing fun _chat(): JsonField<Chat> = chat

        /**
         * Returns the raw JSON value of [sessions].
         *
         * Unlike [sessions], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("sessions") @ExcludeMissing fun _sessions(): JsonField<Sessions> = sessions

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

            private var chat: JsonField<Chat>? = null
            private var sessions: JsonField<Sessions>? = null
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(chatCoworkUnifiedMetrics: ChatCoworkUnifiedMetrics) = apply {
                chat = chatCoworkUnifiedMetrics.chat
                sessions = chatCoworkUnifiedMetrics.sessions
                additionalProperties = chatCoworkUnifiedMetrics.additionalProperties.toMutableMap()
            }

            /** Chat activity recorded while members had Chat and Cowork unified turned on. */
            fun chat(chat: Chat) = chat(JsonField.of(chat))

            /**
             * Sets [Builder.chat] to an arbitrary JSON value.
             *
             * You should usually call [Builder.chat] with a well-typed [Chat] value instead. This
             * method is primarily for setting the field to an undocumented or not yet supported
             * value.
             */
            fun chat(chat: JsonField<Chat>) = apply { this.chat = chat }

            /**
             * Cowork session activity recorded while members had Chat and Cowork unified turned on.
             */
            fun sessions(sessions: Sessions) = sessions(JsonField.of(sessions))

            /**
             * Sets [Builder.sessions] to an arbitrary JSON value.
             *
             * You should usually call [Builder.sessions] with a well-typed [Sessions] value
             * instead. This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun sessions(sessions: JsonField<Sessions>) = apply { this.sessions = sessions }

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

        /** Chat activity recorded while members had Chat and Cowork unified turned on. */
        class Chat
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val connectorsUsedCount: JsonField<Long>,
            private val distinctArtifactsCreatedCount: JsonField<Long>,
            private val distinctConnectorsUsedCount: JsonField<Long>,
            private val distinctConversationCount: JsonField<Long>,
            private val distinctFilesUploadedCount: JsonField<Long>,
            private val distinctProjectsCreatedCount: JsonField<Long>,
            private val distinctProjectsUsedCount: JsonField<Long>,
            private val distinctSharedArtifactsViewedCount: JsonField<Long>,
            private val distinctSkillsUsedCount: JsonField<Long>,
            private val messageCount: JsonField<Long>,
            private val sharedConversationsViewedCount: JsonField<Long>,
            private val thinkingMessageCount: JsonField<Long>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("connectors_used_count")
                @ExcludeMissing
                connectorsUsedCount: JsonField<Long> = JsonMissing.of(),
                @JsonProperty("distinct_artifacts_created_count")
                @ExcludeMissing
                distinctArtifactsCreatedCount: JsonField<Long> = JsonMissing.of(),
                @JsonProperty("distinct_connectors_used_count")
                @ExcludeMissing
                distinctConnectorsUsedCount: JsonField<Long> = JsonMissing.of(),
                @JsonProperty("distinct_conversation_count")
                @ExcludeMissing
                distinctConversationCount: JsonField<Long> = JsonMissing.of(),
                @JsonProperty("distinct_files_uploaded_count")
                @ExcludeMissing
                distinctFilesUploadedCount: JsonField<Long> = JsonMissing.of(),
                @JsonProperty("distinct_projects_created_count")
                @ExcludeMissing
                distinctProjectsCreatedCount: JsonField<Long> = JsonMissing.of(),
                @JsonProperty("distinct_projects_used_count")
                @ExcludeMissing
                distinctProjectsUsedCount: JsonField<Long> = JsonMissing.of(),
                @JsonProperty("distinct_shared_artifacts_viewed_count")
                @ExcludeMissing
                distinctSharedArtifactsViewedCount: JsonField<Long> = JsonMissing.of(),
                @JsonProperty("distinct_skills_used_count")
                @ExcludeMissing
                distinctSkillsUsedCount: JsonField<Long> = JsonMissing.of(),
                @JsonProperty("message_count")
                @ExcludeMissing
                messageCount: JsonField<Long> = JsonMissing.of(),
                @JsonProperty("shared_conversations_viewed_count")
                @ExcludeMissing
                sharedConversationsViewedCount: JsonField<Long> = JsonMissing.of(),
                @JsonProperty("thinking_message_count")
                @ExcludeMissing
                thinkingMessageCount: JsonField<Long> = JsonMissing.of(),
            ) : this(
                connectorsUsedCount,
                distinctArtifactsCreatedCount,
                distinctConnectorsUsedCount,
                distinctConversationCount,
                distinctFilesUploadedCount,
                distinctProjectsCreatedCount,
                distinctProjectsUsedCount,
                distinctSharedArtifactsViewedCount,
                distinctSkillsUsedCount,
                messageCount,
                sharedConversationsViewedCount,
                thinkingMessageCount,
                mutableMapOf(),
            )

            /**
             * Same measure as `chat_metrics.connectors_used_count`, for activity recorded while
             * members had Chat and Cowork unified turned on.
             *
             * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
             *   unexpectedly missing or null (e.g. if the server responded with an unexpected
             *   value).
             */
            fun connectorsUsedCount(): Long =
                connectorsUsedCount.getRequired("connectors_used_count")

            /**
             * Same measure as `chat_metrics.distinct_artifacts_created_count`, for activity
             * recorded while members had Chat and Cowork unified turned on. Exact in date-range
             * mode: a creation belongs to exactly one day, so the per-day counts never overlap and
             * their sum over the window is the exact count of distinct creations in it.
             *
             * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
             *   unexpectedly missing or null (e.g. if the server responded with an unexpected
             *   value).
             */
            fun distinctArtifactsCreatedCount(): Long =
                distinctArtifactsCreatedCount.getRequired("distinct_artifacts_created_count")

            /**
             * Same measure as `chat_metrics.distinct_connectors_used_count`, for activity recorded
             * while members had Chat and Cowork unified turned on. Approximate (HLL, typical error
             * <2%) in date-range mode. Null on aggregated rows where a distinct count cannot be
             * computed.
             *
             * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g.
             *   if the server responded with an unexpected value).
             */
            fun distinctConnectorsUsedCount(): Optional<Long> =
                distinctConnectorsUsedCount.getOptional("distinct_connectors_used_count")

            /**
             * Same measure as `chat_metrics.distinct_conversation_count`, for activity recorded
             * while members had Chat and Cowork unified turned on. Approximate (HLL, typical error
             * <2%) in date-range mode. Null on aggregated rows where a distinct count cannot be
             * computed.
             *
             * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g.
             *   if the server responded with an unexpected value).
             */
            fun distinctConversationCount(): Optional<Long> =
                distinctConversationCount.getOptional("distinct_conversation_count")

            /**
             * Same measure as `chat_metrics.distinct_files_uploaded_count`, for activity recorded
             * while members had Chat and Cowork unified turned on. Approximate (HLL, typical error
             * <2%) in date-range mode. Null on aggregated rows where a distinct count cannot be
             * computed.
             *
             * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g.
             *   if the server responded with an unexpected value).
             */
            fun distinctFilesUploadedCount(): Optional<Long> =
                distinctFilesUploadedCount.getOptional("distinct_files_uploaded_count")

            /**
             * Same measure as `chat_metrics.distinct_projects_created_count`, for activity recorded
             * while members had Chat and Cowork unified turned on. Exact in date-range mode: a
             * creation belongs to exactly one day, so the per-day counts never overlap and their
             * sum over the window is the exact count of distinct creations in it.
             *
             * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
             *   unexpectedly missing or null (e.g. if the server responded with an unexpected
             *   value).
             */
            fun distinctProjectsCreatedCount(): Long =
                distinctProjectsCreatedCount.getRequired("distinct_projects_created_count")

            /**
             * Same measure as `chat_metrics.distinct_projects_used_count`, for activity recorded
             * while members had Chat and Cowork unified turned on. Approximate (HLL, typical error
             * <2%) in date-range mode. Null on aggregated rows where a distinct count cannot be
             * computed.
             *
             * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g.
             *   if the server responded with an unexpected value).
             */
            fun distinctProjectsUsedCount(): Optional<Long> =
                distinctProjectsUsedCount.getOptional("distinct_projects_used_count")

            /**
             * Always null: shared-artifact views are not currently measured.
             *
             * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g.
             *   if the server responded with an unexpected value).
             */
            fun distinctSharedArtifactsViewedCount(): Optional<Long> =
                distinctSharedArtifactsViewedCount.getOptional(
                    "distinct_shared_artifacts_viewed_count"
                )

            /**
             * Same measure as `chat_metrics.distinct_skills_used_count`, for activity recorded
             * while members had Chat and Cowork unified turned on. Approximate (HLL, typical error
             * <2%) in date-range mode. Null on aggregated rows where a distinct count cannot be
             * computed.
             *
             * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g.
             *   if the server responded with an unexpected value).
             */
            fun distinctSkillsUsedCount(): Optional<Long> =
                distinctSkillsUsedCount.getOptional("distinct_skills_used_count")

            /**
             * Same measure as `chat_metrics.message_count`, for activity recorded while members had
             * Chat and Cowork unified turned on.
             *
             * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
             *   unexpectedly missing or null (e.g. if the server responded with an unexpected
             *   value).
             */
            fun messageCount(): Long = messageCount.getRequired("message_count")

            /**
             * Same measure as `chat_metrics.shared_conversations_viewed_count`, for activity
             * recorded while members had Chat and Cowork unified turned on.
             *
             * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
             *   unexpectedly missing or null (e.g. if the server responded with an unexpected
             *   value).
             */
            fun sharedConversationsViewedCount(): Long =
                sharedConversationsViewedCount.getRequired("shared_conversations_viewed_count")

            /**
             * Same measure as `chat_metrics.thinking_message_count`, for activity recorded while
             * members had Chat and Cowork unified turned on.
             *
             * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
             *   unexpectedly missing or null (e.g. if the server responded with an unexpected
             *   value).
             */
            fun thinkingMessageCount(): Long =
                thinkingMessageCount.getRequired("thinking_message_count")

            /**
             * Returns the raw JSON value of [connectorsUsedCount].
             *
             * Unlike [connectorsUsedCount], this method doesn't throw if the JSON field has an
             * unexpected type.
             */
            @JsonProperty("connectors_used_count")
            @ExcludeMissing
            fun _connectorsUsedCount(): JsonField<Long> = connectorsUsedCount

            /**
             * Returns the raw JSON value of [distinctArtifactsCreatedCount].
             *
             * Unlike [distinctArtifactsCreatedCount], this method doesn't throw if the JSON field
             * has an unexpected type.
             */
            @JsonProperty("distinct_artifacts_created_count")
            @ExcludeMissing
            fun _distinctArtifactsCreatedCount(): JsonField<Long> = distinctArtifactsCreatedCount

            /**
             * Returns the raw JSON value of [distinctConnectorsUsedCount].
             *
             * Unlike [distinctConnectorsUsedCount], this method doesn't throw if the JSON field has
             * an unexpected type.
             */
            @JsonProperty("distinct_connectors_used_count")
            @ExcludeMissing
            fun _distinctConnectorsUsedCount(): JsonField<Long> = distinctConnectorsUsedCount

            /**
             * Returns the raw JSON value of [distinctConversationCount].
             *
             * Unlike [distinctConversationCount], this method doesn't throw if the JSON field has
             * an unexpected type.
             */
            @JsonProperty("distinct_conversation_count")
            @ExcludeMissing
            fun _distinctConversationCount(): JsonField<Long> = distinctConversationCount

            /**
             * Returns the raw JSON value of [distinctFilesUploadedCount].
             *
             * Unlike [distinctFilesUploadedCount], this method doesn't throw if the JSON field has
             * an unexpected type.
             */
            @JsonProperty("distinct_files_uploaded_count")
            @ExcludeMissing
            fun _distinctFilesUploadedCount(): JsonField<Long> = distinctFilesUploadedCount

            /**
             * Returns the raw JSON value of [distinctProjectsCreatedCount].
             *
             * Unlike [distinctProjectsCreatedCount], this method doesn't throw if the JSON field
             * has an unexpected type.
             */
            @JsonProperty("distinct_projects_created_count")
            @ExcludeMissing
            fun _distinctProjectsCreatedCount(): JsonField<Long> = distinctProjectsCreatedCount

            /**
             * Returns the raw JSON value of [distinctProjectsUsedCount].
             *
             * Unlike [distinctProjectsUsedCount], this method doesn't throw if the JSON field has
             * an unexpected type.
             */
            @JsonProperty("distinct_projects_used_count")
            @ExcludeMissing
            fun _distinctProjectsUsedCount(): JsonField<Long> = distinctProjectsUsedCount

            /**
             * Returns the raw JSON value of [distinctSharedArtifactsViewedCount].
             *
             * Unlike [distinctSharedArtifactsViewedCount], this method doesn't throw if the JSON
             * field has an unexpected type.
             */
            @JsonProperty("distinct_shared_artifacts_viewed_count")
            @ExcludeMissing
            fun _distinctSharedArtifactsViewedCount(): JsonField<Long> =
                distinctSharedArtifactsViewedCount

            /**
             * Returns the raw JSON value of [distinctSkillsUsedCount].
             *
             * Unlike [distinctSkillsUsedCount], this method doesn't throw if the JSON field has an
             * unexpected type.
             */
            @JsonProperty("distinct_skills_used_count")
            @ExcludeMissing
            fun _distinctSkillsUsedCount(): JsonField<Long> = distinctSkillsUsedCount

            /**
             * Returns the raw JSON value of [messageCount].
             *
             * Unlike [messageCount], this method doesn't throw if the JSON field has an unexpected
             * type.
             */
            @JsonProperty("message_count")
            @ExcludeMissing
            fun _messageCount(): JsonField<Long> = messageCount

            /**
             * Returns the raw JSON value of [sharedConversationsViewedCount].
             *
             * Unlike [sharedConversationsViewedCount], this method doesn't throw if the JSON field
             * has an unexpected type.
             */
            @JsonProperty("shared_conversations_viewed_count")
            @ExcludeMissing
            fun _sharedConversationsViewedCount(): JsonField<Long> = sharedConversationsViewedCount

            /**
             * Returns the raw JSON value of [thinkingMessageCount].
             *
             * Unlike [thinkingMessageCount], this method doesn't throw if the JSON field has an
             * unexpected type.
             */
            @JsonProperty("thinking_message_count")
            @ExcludeMissing
            fun _thinkingMessageCount(): JsonField<Long> = thinkingMessageCount

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
                 * Returns a mutable builder for constructing an instance of [Chat].
                 *
                 * The following fields are required:
                 * ```java
                 * .connectorsUsedCount()
                 * .distinctArtifactsCreatedCount()
                 * .distinctConnectorsUsedCount()
                 * .distinctConversationCount()
                 * .distinctFilesUploadedCount()
                 * .distinctProjectsCreatedCount()
                 * .distinctProjectsUsedCount()
                 * .distinctSharedArtifactsViewedCount()
                 * .distinctSkillsUsedCount()
                 * .messageCount()
                 * .sharedConversationsViewedCount()
                 * .thinkingMessageCount()
                 * ```
                 */
                @JvmStatic fun builder() = Builder()
            }

            /** A builder for [Chat]. */
            class Builder internal constructor() {

                private var connectorsUsedCount: JsonField<Long>? = null
                private var distinctArtifactsCreatedCount: JsonField<Long>? = null
                private var distinctConnectorsUsedCount: JsonField<Long>? = null
                private var distinctConversationCount: JsonField<Long>? = null
                private var distinctFilesUploadedCount: JsonField<Long>? = null
                private var distinctProjectsCreatedCount: JsonField<Long>? = null
                private var distinctProjectsUsedCount: JsonField<Long>? = null
                private var distinctSharedArtifactsViewedCount: JsonField<Long>? = null
                private var distinctSkillsUsedCount: JsonField<Long>? = null
                private var messageCount: JsonField<Long>? = null
                private var sharedConversationsViewedCount: JsonField<Long>? = null
                private var thinkingMessageCount: JsonField<Long>? = null
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(chat: Chat) = apply {
                    connectorsUsedCount = chat.connectorsUsedCount
                    distinctArtifactsCreatedCount = chat.distinctArtifactsCreatedCount
                    distinctConnectorsUsedCount = chat.distinctConnectorsUsedCount
                    distinctConversationCount = chat.distinctConversationCount
                    distinctFilesUploadedCount = chat.distinctFilesUploadedCount
                    distinctProjectsCreatedCount = chat.distinctProjectsCreatedCount
                    distinctProjectsUsedCount = chat.distinctProjectsUsedCount
                    distinctSharedArtifactsViewedCount = chat.distinctSharedArtifactsViewedCount
                    distinctSkillsUsedCount = chat.distinctSkillsUsedCount
                    messageCount = chat.messageCount
                    sharedConversationsViewedCount = chat.sharedConversationsViewedCount
                    thinkingMessageCount = chat.thinkingMessageCount
                    additionalProperties = chat.additionalProperties.toMutableMap()
                }

                /**
                 * Same measure as `chat_metrics.connectors_used_count`, for activity recorded while
                 * members had Chat and Cowork unified turned on.
                 */
                fun connectorsUsedCount(connectorsUsedCount: Long) =
                    connectorsUsedCount(JsonField.of(connectorsUsedCount))

                /**
                 * Sets [Builder.connectorsUsedCount] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.connectorsUsedCount] with a well-typed [Long]
                 * value instead. This method is primarily for setting the field to an undocumented
                 * or not yet supported value.
                 */
                fun connectorsUsedCount(connectorsUsedCount: JsonField<Long>) = apply {
                    this.connectorsUsedCount = connectorsUsedCount
                }

                /**
                 * Same measure as `chat_metrics.distinct_artifacts_created_count`, for activity
                 * recorded while members had Chat and Cowork unified turned on. Exact in date-range
                 * mode: a creation belongs to exactly one day, so the per-day counts never overlap
                 * and their sum over the window is the exact count of distinct creations in it.
                 */
                fun distinctArtifactsCreatedCount(distinctArtifactsCreatedCount: Long) =
                    distinctArtifactsCreatedCount(JsonField.of(distinctArtifactsCreatedCount))

                /**
                 * Sets [Builder.distinctArtifactsCreatedCount] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.distinctArtifactsCreatedCount] with a well-typed
                 * [Long] value instead. This method is primarily for setting the field to an
                 * undocumented or not yet supported value.
                 */
                fun distinctArtifactsCreatedCount(distinctArtifactsCreatedCount: JsonField<Long>) =
                    apply {
                        this.distinctArtifactsCreatedCount = distinctArtifactsCreatedCount
                    }

                /**
                 * Same measure as `chat_metrics.distinct_connectors_used_count`, for activity
                 * recorded while members had Chat and Cowork unified turned on. Approximate (HLL,
                 * typical error <2%) in date-range mode. Null on aggregated rows where a distinct
                 * count cannot be computed.
                 */
                fun distinctConnectorsUsedCount(distinctConnectorsUsedCount: Long?) =
                    distinctConnectorsUsedCount(JsonField.ofNullable(distinctConnectorsUsedCount))

                /**
                 * Alias for [Builder.distinctConnectorsUsedCount].
                 *
                 * This unboxed primitive overload exists for backwards compatibility.
                 */
                fun distinctConnectorsUsedCount(distinctConnectorsUsedCount: Long) =
                    distinctConnectorsUsedCount(distinctConnectorsUsedCount as Long?)

                /**
                 * Alias for calling [Builder.distinctConnectorsUsedCount] with
                 * `distinctConnectorsUsedCount.orElse(null)`.
                 */
                fun distinctConnectorsUsedCount(distinctConnectorsUsedCount: Optional<Long>) =
                    distinctConnectorsUsedCount(distinctConnectorsUsedCount.getOrNull())

                /**
                 * Sets [Builder.distinctConnectorsUsedCount] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.distinctConnectorsUsedCount] with a well-typed
                 * [Long] value instead. This method is primarily for setting the field to an
                 * undocumented or not yet supported value.
                 */
                fun distinctConnectorsUsedCount(distinctConnectorsUsedCount: JsonField<Long>) =
                    apply {
                        this.distinctConnectorsUsedCount = distinctConnectorsUsedCount
                    }

                /**
                 * Same measure as `chat_metrics.distinct_conversation_count`, for activity recorded
                 * while members had Chat and Cowork unified turned on. Approximate (HLL, typical
                 * error <2%) in date-range mode. Null on aggregated rows where a distinct count
                 * cannot be computed.
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
                 * You should usually call [Builder.distinctConversationCount] with a well-typed
                 * [Long] value instead. This method is primarily for setting the field to an
                 * undocumented or not yet supported value.
                 */
                fun distinctConversationCount(distinctConversationCount: JsonField<Long>) = apply {
                    this.distinctConversationCount = distinctConversationCount
                }

                /**
                 * Same measure as `chat_metrics.distinct_files_uploaded_count`, for activity
                 * recorded while members had Chat and Cowork unified turned on. Approximate (HLL,
                 * typical error <2%) in date-range mode. Null on aggregated rows where a distinct
                 * count cannot be computed.
                 */
                fun distinctFilesUploadedCount(distinctFilesUploadedCount: Long?) =
                    distinctFilesUploadedCount(JsonField.ofNullable(distinctFilesUploadedCount))

                /**
                 * Alias for [Builder.distinctFilesUploadedCount].
                 *
                 * This unboxed primitive overload exists for backwards compatibility.
                 */
                fun distinctFilesUploadedCount(distinctFilesUploadedCount: Long) =
                    distinctFilesUploadedCount(distinctFilesUploadedCount as Long?)

                /**
                 * Alias for calling [Builder.distinctFilesUploadedCount] with
                 * `distinctFilesUploadedCount.orElse(null)`.
                 */
                fun distinctFilesUploadedCount(distinctFilesUploadedCount: Optional<Long>) =
                    distinctFilesUploadedCount(distinctFilesUploadedCount.getOrNull())

                /**
                 * Sets [Builder.distinctFilesUploadedCount] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.distinctFilesUploadedCount] with a well-typed
                 * [Long] value instead. This method is primarily for setting the field to an
                 * undocumented or not yet supported value.
                 */
                fun distinctFilesUploadedCount(distinctFilesUploadedCount: JsonField<Long>) =
                    apply {
                        this.distinctFilesUploadedCount = distinctFilesUploadedCount
                    }

                /**
                 * Same measure as `chat_metrics.distinct_projects_created_count`, for activity
                 * recorded while members had Chat and Cowork unified turned on. Exact in date-range
                 * mode: a creation belongs to exactly one day, so the per-day counts never overlap
                 * and their sum over the window is the exact count of distinct creations in it.
                 */
                fun distinctProjectsCreatedCount(distinctProjectsCreatedCount: Long) =
                    distinctProjectsCreatedCount(JsonField.of(distinctProjectsCreatedCount))

                /**
                 * Sets [Builder.distinctProjectsCreatedCount] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.distinctProjectsCreatedCount] with a well-typed
                 * [Long] value instead. This method is primarily for setting the field to an
                 * undocumented or not yet supported value.
                 */
                fun distinctProjectsCreatedCount(distinctProjectsCreatedCount: JsonField<Long>) =
                    apply {
                        this.distinctProjectsCreatedCount = distinctProjectsCreatedCount
                    }

                /**
                 * Same measure as `chat_metrics.distinct_projects_used_count`, for activity
                 * recorded while members had Chat and Cowork unified turned on. Approximate (HLL,
                 * typical error <2%) in date-range mode. Null on aggregated rows where a distinct
                 * count cannot be computed.
                 */
                fun distinctProjectsUsedCount(distinctProjectsUsedCount: Long?) =
                    distinctProjectsUsedCount(JsonField.ofNullable(distinctProjectsUsedCount))

                /**
                 * Alias for [Builder.distinctProjectsUsedCount].
                 *
                 * This unboxed primitive overload exists for backwards compatibility.
                 */
                fun distinctProjectsUsedCount(distinctProjectsUsedCount: Long) =
                    distinctProjectsUsedCount(distinctProjectsUsedCount as Long?)

                /**
                 * Alias for calling [Builder.distinctProjectsUsedCount] with
                 * `distinctProjectsUsedCount.orElse(null)`.
                 */
                fun distinctProjectsUsedCount(distinctProjectsUsedCount: Optional<Long>) =
                    distinctProjectsUsedCount(distinctProjectsUsedCount.getOrNull())

                /**
                 * Sets [Builder.distinctProjectsUsedCount] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.distinctProjectsUsedCount] with a well-typed
                 * [Long] value instead. This method is primarily for setting the field to an
                 * undocumented or not yet supported value.
                 */
                fun distinctProjectsUsedCount(distinctProjectsUsedCount: JsonField<Long>) = apply {
                    this.distinctProjectsUsedCount = distinctProjectsUsedCount
                }

                /** Always null: shared-artifact views are not currently measured. */
                fun distinctSharedArtifactsViewedCount(distinctSharedArtifactsViewedCount: Long?) =
                    distinctSharedArtifactsViewedCount(
                        JsonField.ofNullable(distinctSharedArtifactsViewedCount)
                    )

                /**
                 * Alias for [Builder.distinctSharedArtifactsViewedCount].
                 *
                 * This unboxed primitive overload exists for backwards compatibility.
                 */
                fun distinctSharedArtifactsViewedCount(distinctSharedArtifactsViewedCount: Long) =
                    distinctSharedArtifactsViewedCount(distinctSharedArtifactsViewedCount as Long?)

                /**
                 * Alias for calling [Builder.distinctSharedArtifactsViewedCount] with
                 * `distinctSharedArtifactsViewedCount.orElse(null)`.
                 */
                fun distinctSharedArtifactsViewedCount(
                    distinctSharedArtifactsViewedCount: Optional<Long>
                ) =
                    distinctSharedArtifactsViewedCount(
                        distinctSharedArtifactsViewedCount.getOrNull()
                    )

                /**
                 * Sets [Builder.distinctSharedArtifactsViewedCount] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.distinctSharedArtifactsViewedCount] with a
                 * well-typed [Long] value instead. This method is primarily for setting the field
                 * to an undocumented or not yet supported value.
                 */
                fun distinctSharedArtifactsViewedCount(
                    distinctSharedArtifactsViewedCount: JsonField<Long>
                ) = apply {
                    this.distinctSharedArtifactsViewedCount = distinctSharedArtifactsViewedCount
                }

                /**
                 * Same measure as `chat_metrics.distinct_skills_used_count`, for activity recorded
                 * while members had Chat and Cowork unified turned on. Approximate (HLL, typical
                 * error <2%) in date-range mode. Null on aggregated rows where a distinct count
                 * cannot be computed.
                 */
                fun distinctSkillsUsedCount(distinctSkillsUsedCount: Long?) =
                    distinctSkillsUsedCount(JsonField.ofNullable(distinctSkillsUsedCount))

                /**
                 * Alias for [Builder.distinctSkillsUsedCount].
                 *
                 * This unboxed primitive overload exists for backwards compatibility.
                 */
                fun distinctSkillsUsedCount(distinctSkillsUsedCount: Long) =
                    distinctSkillsUsedCount(distinctSkillsUsedCount as Long?)

                /**
                 * Alias for calling [Builder.distinctSkillsUsedCount] with
                 * `distinctSkillsUsedCount.orElse(null)`.
                 */
                fun distinctSkillsUsedCount(distinctSkillsUsedCount: Optional<Long>) =
                    distinctSkillsUsedCount(distinctSkillsUsedCount.getOrNull())

                /**
                 * Sets [Builder.distinctSkillsUsedCount] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.distinctSkillsUsedCount] with a well-typed
                 * [Long] value instead. This method is primarily for setting the field to an
                 * undocumented or not yet supported value.
                 */
                fun distinctSkillsUsedCount(distinctSkillsUsedCount: JsonField<Long>) = apply {
                    this.distinctSkillsUsedCount = distinctSkillsUsedCount
                }

                /**
                 * Same measure as `chat_metrics.message_count`, for activity recorded while members
                 * had Chat and Cowork unified turned on.
                 */
                fun messageCount(messageCount: Long) = messageCount(JsonField.of(messageCount))

                /**
                 * Sets [Builder.messageCount] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.messageCount] with a well-typed [Long] value
                 * instead. This method is primarily for setting the field to an undocumented or not
                 * yet supported value.
                 */
                fun messageCount(messageCount: JsonField<Long>) = apply {
                    this.messageCount = messageCount
                }

                /**
                 * Same measure as `chat_metrics.shared_conversations_viewed_count`, for activity
                 * recorded while members had Chat and Cowork unified turned on.
                 */
                fun sharedConversationsViewedCount(sharedConversationsViewedCount: Long) =
                    sharedConversationsViewedCount(JsonField.of(sharedConversationsViewedCount))

                /**
                 * Sets [Builder.sharedConversationsViewedCount] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.sharedConversationsViewedCount] with a
                 * well-typed [Long] value instead. This method is primarily for setting the field
                 * to an undocumented or not yet supported value.
                 */
                fun sharedConversationsViewedCount(
                    sharedConversationsViewedCount: JsonField<Long>
                ) = apply { this.sharedConversationsViewedCount = sharedConversationsViewedCount }

                /**
                 * Same measure as `chat_metrics.thinking_message_count`, for activity recorded
                 * while members had Chat and Cowork unified turned on.
                 */
                fun thinkingMessageCount(thinkingMessageCount: Long) =
                    thinkingMessageCount(JsonField.of(thinkingMessageCount))

                /**
                 * Sets [Builder.thinkingMessageCount] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.thinkingMessageCount] with a well-typed [Long]
                 * value instead. This method is primarily for setting the field to an undocumented
                 * or not yet supported value.
                 */
                fun thinkingMessageCount(thinkingMessageCount: JsonField<Long>) = apply {
                    this.thinkingMessageCount = thinkingMessageCount
                }

                fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                    this.additionalProperties.clear()
                    putAllAdditionalProperties(additionalProperties)
                }

                fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                    additionalProperties.put(key, value)
                }

                fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) =
                    apply {
                        this.additionalProperties.putAll(additionalProperties)
                    }

                fun removeAdditionalProperty(key: String) = apply {
                    additionalProperties.remove(key)
                }

                fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                    keys.forEach(::removeAdditionalProperty)
                }

                /**
                 * Returns an immutable instance of [Chat].
                 *
                 * Further updates to this [Builder] will not mutate the returned instance.
                 *
                 * The following fields are required:
                 * ```java
                 * .connectorsUsedCount()
                 * .distinctArtifactsCreatedCount()
                 * .distinctConnectorsUsedCount()
                 * .distinctConversationCount()
                 * .distinctFilesUploadedCount()
                 * .distinctProjectsCreatedCount()
                 * .distinctProjectsUsedCount()
                 * .distinctSharedArtifactsViewedCount()
                 * .distinctSkillsUsedCount()
                 * .messageCount()
                 * .sharedConversationsViewedCount()
                 * .thinkingMessageCount()
                 * ```
                 *
                 * @throws IllegalStateException if any required field is unset.
                 */
                fun build(): Chat =
                    Chat(
                        checkRequired("connectorsUsedCount", connectorsUsedCount),
                        checkRequired(
                            "distinctArtifactsCreatedCount",
                            distinctArtifactsCreatedCount,
                        ),
                        checkRequired("distinctConnectorsUsedCount", distinctConnectorsUsedCount),
                        checkRequired("distinctConversationCount", distinctConversationCount),
                        checkRequired("distinctFilesUploadedCount", distinctFilesUploadedCount),
                        checkRequired("distinctProjectsCreatedCount", distinctProjectsCreatedCount),
                        checkRequired("distinctProjectsUsedCount", distinctProjectsUsedCount),
                        checkRequired(
                            "distinctSharedArtifactsViewedCount",
                            distinctSharedArtifactsViewedCount,
                        ),
                        checkRequired("distinctSkillsUsedCount", distinctSkillsUsedCount),
                        checkRequired("messageCount", messageCount),
                        checkRequired(
                            "sharedConversationsViewedCount",
                            sharedConversationsViewedCount,
                        ),
                        checkRequired("thinkingMessageCount", thinkingMessageCount),
                        additionalProperties.toMutableMap(),
                    )
            }

            private var validated: Boolean = false

            /**
             * Validates that the types of all values in this object match their expected types
             * recursively.
             *
             * This method is _not_ forwards compatible with new types from the API for existing
             * fields.
             *
             * @throws AnthropicInvalidDataException if any value type in this object doesn't match
             *   its expected type.
             */
            fun validate(): Chat = apply {
                if (validated) {
                    return@apply
                }

                connectorsUsedCount()
                distinctArtifactsCreatedCount()
                distinctConnectorsUsedCount()
                distinctConversationCount()
                distinctFilesUploadedCount()
                distinctProjectsCreatedCount()
                distinctProjectsUsedCount()
                distinctSharedArtifactsViewedCount()
                distinctSkillsUsedCount()
                messageCount()
                sharedConversationsViewedCount()
                thinkingMessageCount()
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
                (if (connectorsUsedCount.asKnown().isPresent) 1 else 0) +
                    (if (distinctArtifactsCreatedCount.asKnown().isPresent) 1 else 0) +
                    (if (distinctConnectorsUsedCount.asKnown().isPresent) 1 else 0) +
                    (if (distinctConversationCount.asKnown().isPresent) 1 else 0) +
                    (if (distinctFilesUploadedCount.asKnown().isPresent) 1 else 0) +
                    (if (distinctProjectsCreatedCount.asKnown().isPresent) 1 else 0) +
                    (if (distinctProjectsUsedCount.asKnown().isPresent) 1 else 0) +
                    (if (distinctSharedArtifactsViewedCount.asKnown().isPresent) 1 else 0) +
                    (if (distinctSkillsUsedCount.asKnown().isPresent) 1 else 0) +
                    (if (messageCount.asKnown().isPresent) 1 else 0) +
                    (if (sharedConversationsViewedCount.asKnown().isPresent) 1 else 0) +
                    (if (thinkingMessageCount.asKnown().isPresent) 1 else 0)

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is Chat &&
                    connectorsUsedCount == other.connectorsUsedCount &&
                    distinctArtifactsCreatedCount == other.distinctArtifactsCreatedCount &&
                    distinctConnectorsUsedCount == other.distinctConnectorsUsedCount &&
                    distinctConversationCount == other.distinctConversationCount &&
                    distinctFilesUploadedCount == other.distinctFilesUploadedCount &&
                    distinctProjectsCreatedCount == other.distinctProjectsCreatedCount &&
                    distinctProjectsUsedCount == other.distinctProjectsUsedCount &&
                    distinctSharedArtifactsViewedCount ==
                        other.distinctSharedArtifactsViewedCount &&
                    distinctSkillsUsedCount == other.distinctSkillsUsedCount &&
                    messageCount == other.messageCount &&
                    sharedConversationsViewedCount == other.sharedConversationsViewedCount &&
                    thinkingMessageCount == other.thinkingMessageCount &&
                    additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy {
                Objects.hash(
                    connectorsUsedCount,
                    distinctArtifactsCreatedCount,
                    distinctConnectorsUsedCount,
                    distinctConversationCount,
                    distinctFilesUploadedCount,
                    distinctProjectsCreatedCount,
                    distinctProjectsUsedCount,
                    distinctSharedArtifactsViewedCount,
                    distinctSkillsUsedCount,
                    messageCount,
                    sharedConversationsViewedCount,
                    thinkingMessageCount,
                    additionalProperties,
                )
            }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "Chat{connectorsUsedCount=$connectorsUsedCount, distinctArtifactsCreatedCount=$distinctArtifactsCreatedCount, distinctConnectorsUsedCount=$distinctConnectorsUsedCount, distinctConversationCount=$distinctConversationCount, distinctFilesUploadedCount=$distinctFilesUploadedCount, distinctProjectsCreatedCount=$distinctProjectsCreatedCount, distinctProjectsUsedCount=$distinctProjectsUsedCount, distinctSharedArtifactsViewedCount=$distinctSharedArtifactsViewedCount, distinctSkillsUsedCount=$distinctSkillsUsedCount, messageCount=$messageCount, sharedConversationsViewedCount=$sharedConversationsViewedCount, thinkingMessageCount=$thinkingMessageCount, additionalProperties=$additionalProperties}"
        }

        /** Cowork session activity recorded while members had Chat and Cowork unified turned on. */
        class Sessions
        @JsonCreator(mode = JsonCreator.Mode.DISABLED)
        private constructor(
            private val actionCount: JsonField<Long>,
            private val artifactsCreatedCount: JsonField<Long>,
            private val connectorsUsedCount: JsonField<Long>,
            private val dispatchTurnCount: JsonField<Long>,
            private val distinctConnectorsUsedCount: JsonField<Long>,
            private val distinctSessionCount: JsonField<Long>,
            private val distinctSkillsUsedCount: JsonField<Long>,
            private val messageCount: JsonField<Long>,
            private val skillsUsedCount: JsonField<Long>,
            private val distinctPluginsUsedCount: JsonField<Long>,
            private val editToolCount: JsonField<Long>,
            private val fileEditCount: JsonField<Long>,
            private val multiEditToolCount: JsonField<Long>,
            private val notebookEditToolCount: JsonField<Long>,
            private val pluginsUsedCount: JsonField<Long>,
            private val sessionsWithFileEditsCount: JsonField<Long>,
            private val writeToolCount: JsonField<Long>,
            private val additionalProperties: MutableMap<String, JsonValue>,
        ) {

            @JsonCreator
            private constructor(
                @JsonProperty("action_count")
                @ExcludeMissing
                actionCount: JsonField<Long> = JsonMissing.of(),
                @JsonProperty("artifacts_created_count")
                @ExcludeMissing
                artifactsCreatedCount: JsonField<Long> = JsonMissing.of(),
                @JsonProperty("connectors_used_count")
                @ExcludeMissing
                connectorsUsedCount: JsonField<Long> = JsonMissing.of(),
                @JsonProperty("dispatch_turn_count")
                @ExcludeMissing
                dispatchTurnCount: JsonField<Long> = JsonMissing.of(),
                @JsonProperty("distinct_connectors_used_count")
                @ExcludeMissing
                distinctConnectorsUsedCount: JsonField<Long> = JsonMissing.of(),
                @JsonProperty("distinct_session_count")
                @ExcludeMissing
                distinctSessionCount: JsonField<Long> = JsonMissing.of(),
                @JsonProperty("distinct_skills_used_count")
                @ExcludeMissing
                distinctSkillsUsedCount: JsonField<Long> = JsonMissing.of(),
                @JsonProperty("message_count")
                @ExcludeMissing
                messageCount: JsonField<Long> = JsonMissing.of(),
                @JsonProperty("skills_used_count")
                @ExcludeMissing
                skillsUsedCount: JsonField<Long> = JsonMissing.of(),
                @JsonProperty("distinct_plugins_used_count")
                @ExcludeMissing
                distinctPluginsUsedCount: JsonField<Long> = JsonMissing.of(),
                @JsonProperty("edit_tool_count")
                @ExcludeMissing
                editToolCount: JsonField<Long> = JsonMissing.of(),
                @JsonProperty("file_edit_count")
                @ExcludeMissing
                fileEditCount: JsonField<Long> = JsonMissing.of(),
                @JsonProperty("multi_edit_tool_count")
                @ExcludeMissing
                multiEditToolCount: JsonField<Long> = JsonMissing.of(),
                @JsonProperty("notebook_edit_tool_count")
                @ExcludeMissing
                notebookEditToolCount: JsonField<Long> = JsonMissing.of(),
                @JsonProperty("plugins_used_count")
                @ExcludeMissing
                pluginsUsedCount: JsonField<Long> = JsonMissing.of(),
                @JsonProperty("sessions_with_file_edits_count")
                @ExcludeMissing
                sessionsWithFileEditsCount: JsonField<Long> = JsonMissing.of(),
                @JsonProperty("write_tool_count")
                @ExcludeMissing
                writeToolCount: JsonField<Long> = JsonMissing.of(),
            ) : this(
                actionCount,
                artifactsCreatedCount,
                connectorsUsedCount,
                dispatchTurnCount,
                distinctConnectorsUsedCount,
                distinctSessionCount,
                distinctSkillsUsedCount,
                messageCount,
                skillsUsedCount,
                distinctPluginsUsedCount,
                editToolCount,
                fileEditCount,
                multiEditToolCount,
                notebookEditToolCount,
                pluginsUsedCount,
                sessionsWithFileEditsCount,
                writeToolCount,
                mutableMapOf(),
            )

            /**
             * Same measure as `cowork_metrics.action_count`, for activity recorded while members
             * had Chat and Cowork unified turned on.
             *
             * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
             *   unexpectedly missing or null (e.g. if the server responded with an unexpected
             *   value).
             */
            fun actionCount(): Long = actionCount.getRequired("action_count")

            /**
             * Same measure as `cowork_metrics.artifacts_created_count`, for activity recorded while
             * members had Chat and Cowork unified turned on. Exact in date-range mode: a creation
             * belongs to exactly one day, so the per-day counts never overlap and their sum over
             * the window is the exact count of distinct creations in it.
             *
             * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
             *   unexpectedly missing or null (e.g. if the server responded with an unexpected
             *   value).
             */
            fun artifactsCreatedCount(): Long =
                artifactsCreatedCount.getRequired("artifacts_created_count")

            /**
             * Same measure as `cowork_metrics.connectors_used_count`, for activity recorded while
             * members had Chat and Cowork unified turned on.
             *
             * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
             *   unexpectedly missing or null (e.g. if the server responded with an unexpected
             *   value).
             */
            fun connectorsUsedCount(): Long =
                connectorsUsedCount.getRequired("connectors_used_count")

            /**
             * Same measure as `cowork_metrics.dispatch_turn_count`, for activity recorded while
             * members had Chat and Cowork unified turned on.
             *
             * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
             *   unexpectedly missing or null (e.g. if the server responded with an unexpected
             *   value).
             */
            fun dispatchTurnCount(): Long = dispatchTurnCount.getRequired("dispatch_turn_count")

            /**
             * Same measure as `cowork_metrics.distinct_connectors_used_count`, for activity
             * recorded while members had Chat and Cowork unified turned on. Approximate (HLL,
             * typical error <2%) in date-range mode. Null on aggregated rows where a distinct count
             * cannot be computed.
             *
             * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g.
             *   if the server responded with an unexpected value).
             */
            fun distinctConnectorsUsedCount(): Optional<Long> =
                distinctConnectorsUsedCount.getOptional("distinct_connectors_used_count")

            /**
             * Same measure as `cowork_metrics.distinct_session_count`, for activity recorded while
             * members had Chat and Cowork unified turned on. Approximate (HLL, typical error <2%)
             * in date-range mode. Null on aggregated rows where a distinct count cannot be
             * computed.
             *
             * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g.
             *   if the server responded with an unexpected value).
             */
            fun distinctSessionCount(): Optional<Long> =
                distinctSessionCount.getOptional("distinct_session_count")

            /**
             * Same measure as `cowork_metrics.distinct_skills_used_count`, for activity recorded
             * while members had Chat and Cowork unified turned on. Approximate (HLL, typical error
             * <2%) in date-range mode. Null on aggregated rows where a distinct count cannot be
             * computed.
             *
             * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g.
             *   if the server responded with an unexpected value).
             */
            fun distinctSkillsUsedCount(): Optional<Long> =
                distinctSkillsUsedCount.getOptional("distinct_skills_used_count")

            /**
             * Same measure as `cowork_metrics.message_count`, for activity recorded while members
             * had Chat and Cowork unified turned on.
             *
             * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
             *   unexpectedly missing or null (e.g. if the server responded with an unexpected
             *   value).
             */
            fun messageCount(): Long = messageCount.getRequired("message_count")

            /**
             * Same measure as `cowork_metrics.skills_used_count`, for activity recorded while
             * members had Chat and Cowork unified turned on.
             *
             * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
             *   unexpectedly missing or null (e.g. if the server responded with an unexpected
             *   value).
             */
            fun skillsUsedCount(): Long = skillsUsedCount.getRequired("skills_used_count")

            /**
             * Same measure as `cowork_metrics.distinct_plugins_used_count`, for activity recorded
             * while members had Chat and Cowork unified turned on. Approximate (HLL, typical error
             * <2%) in date-range mode. Null on aggregated rows where a distinct count cannot be
             * computed.
             *
             * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g.
             *   if the server responded with an unexpected value).
             */
            fun distinctPluginsUsedCount(): Optional<Long> =
                distinctPluginsUsedCount.getOptional("distinct_plugins_used_count")

            /**
             * Same measure as `cowork_metrics.edit_tool_count`, for activity recorded while members
             * had Chat and Cowork unified turned on.
             *
             * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g.
             *   if the server responded with an unexpected value).
             */
            fun editToolCount(): Optional<Long> = editToolCount.getOptional("edit_tool_count")

            /**
             * Same measure as `cowork_metrics.file_edit_count`, for activity recorded while members
             * had Chat and Cowork unified turned on.
             *
             * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g.
             *   if the server responded with an unexpected value).
             */
            fun fileEditCount(): Optional<Long> = fileEditCount.getOptional("file_edit_count")

            /**
             * Same measure as `cowork_metrics.multi_edit_tool_count`, for activity recorded while
             * members had Chat and Cowork unified turned on.
             *
             * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g.
             *   if the server responded with an unexpected value).
             */
            fun multiEditToolCount(): Optional<Long> =
                multiEditToolCount.getOptional("multi_edit_tool_count")

            /**
             * Same measure as `cowork_metrics.notebook_edit_tool_count`, for activity recorded
             * while members had Chat and Cowork unified turned on.
             *
             * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g.
             *   if the server responded with an unexpected value).
             */
            fun notebookEditToolCount(): Optional<Long> =
                notebookEditToolCount.getOptional("notebook_edit_tool_count")

            /**
             * Same measure as `cowork_metrics.plugins_used_count`, for activity recorded while
             * members had Chat and Cowork unified turned on.
             *
             * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g.
             *   if the server responded with an unexpected value).
             */
            fun pluginsUsedCount(): Optional<Long> =
                pluginsUsedCount.getOptional("plugins_used_count")

            /**
             * Same measure as `cowork_metrics.sessions_with_file_edits_count`, for activity
             * recorded while members had Chat and Cowork unified turned on. Approximate (HLL,
             * typical error <2%) in date-range mode. Null on aggregated rows where a distinct count
             * cannot be computed.
             *
             * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g.
             *   if the server responded with an unexpected value).
             */
            fun sessionsWithFileEditsCount(): Optional<Long> =
                sessionsWithFileEditsCount.getOptional("sessions_with_file_edits_count")

            /**
             * Same measure as `cowork_metrics.write_tool_count`, for activity recorded while
             * members had Chat and Cowork unified turned on.
             *
             * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g.
             *   if the server responded with an unexpected value).
             */
            fun writeToolCount(): Optional<Long> = writeToolCount.getOptional("write_tool_count")

            /**
             * Returns the raw JSON value of [actionCount].
             *
             * Unlike [actionCount], this method doesn't throw if the JSON field has an unexpected
             * type.
             */
            @JsonProperty("action_count")
            @ExcludeMissing
            fun _actionCount(): JsonField<Long> = actionCount

            /**
             * Returns the raw JSON value of [artifactsCreatedCount].
             *
             * Unlike [artifactsCreatedCount], this method doesn't throw if the JSON field has an
             * unexpected type.
             */
            @JsonProperty("artifacts_created_count")
            @ExcludeMissing
            fun _artifactsCreatedCount(): JsonField<Long> = artifactsCreatedCount

            /**
             * Returns the raw JSON value of [connectorsUsedCount].
             *
             * Unlike [connectorsUsedCount], this method doesn't throw if the JSON field has an
             * unexpected type.
             */
            @JsonProperty("connectors_used_count")
            @ExcludeMissing
            fun _connectorsUsedCount(): JsonField<Long> = connectorsUsedCount

            /**
             * Returns the raw JSON value of [dispatchTurnCount].
             *
             * Unlike [dispatchTurnCount], this method doesn't throw if the JSON field has an
             * unexpected type.
             */
            @JsonProperty("dispatch_turn_count")
            @ExcludeMissing
            fun _dispatchTurnCount(): JsonField<Long> = dispatchTurnCount

            /**
             * Returns the raw JSON value of [distinctConnectorsUsedCount].
             *
             * Unlike [distinctConnectorsUsedCount], this method doesn't throw if the JSON field has
             * an unexpected type.
             */
            @JsonProperty("distinct_connectors_used_count")
            @ExcludeMissing
            fun _distinctConnectorsUsedCount(): JsonField<Long> = distinctConnectorsUsedCount

            /**
             * Returns the raw JSON value of [distinctSessionCount].
             *
             * Unlike [distinctSessionCount], this method doesn't throw if the JSON field has an
             * unexpected type.
             */
            @JsonProperty("distinct_session_count")
            @ExcludeMissing
            fun _distinctSessionCount(): JsonField<Long> = distinctSessionCount

            /**
             * Returns the raw JSON value of [distinctSkillsUsedCount].
             *
             * Unlike [distinctSkillsUsedCount], this method doesn't throw if the JSON field has an
             * unexpected type.
             */
            @JsonProperty("distinct_skills_used_count")
            @ExcludeMissing
            fun _distinctSkillsUsedCount(): JsonField<Long> = distinctSkillsUsedCount

            /**
             * Returns the raw JSON value of [messageCount].
             *
             * Unlike [messageCount], this method doesn't throw if the JSON field has an unexpected
             * type.
             */
            @JsonProperty("message_count")
            @ExcludeMissing
            fun _messageCount(): JsonField<Long> = messageCount

            /**
             * Returns the raw JSON value of [skillsUsedCount].
             *
             * Unlike [skillsUsedCount], this method doesn't throw if the JSON field has an
             * unexpected type.
             */
            @JsonProperty("skills_used_count")
            @ExcludeMissing
            fun _skillsUsedCount(): JsonField<Long> = skillsUsedCount

            /**
             * Returns the raw JSON value of [distinctPluginsUsedCount].
             *
             * Unlike [distinctPluginsUsedCount], this method doesn't throw if the JSON field has an
             * unexpected type.
             */
            @JsonProperty("distinct_plugins_used_count")
            @ExcludeMissing
            fun _distinctPluginsUsedCount(): JsonField<Long> = distinctPluginsUsedCount

            /**
             * Returns the raw JSON value of [editToolCount].
             *
             * Unlike [editToolCount], this method doesn't throw if the JSON field has an unexpected
             * type.
             */
            @JsonProperty("edit_tool_count")
            @ExcludeMissing
            fun _editToolCount(): JsonField<Long> = editToolCount

            /**
             * Returns the raw JSON value of [fileEditCount].
             *
             * Unlike [fileEditCount], this method doesn't throw if the JSON field has an unexpected
             * type.
             */
            @JsonProperty("file_edit_count")
            @ExcludeMissing
            fun _fileEditCount(): JsonField<Long> = fileEditCount

            /**
             * Returns the raw JSON value of [multiEditToolCount].
             *
             * Unlike [multiEditToolCount], this method doesn't throw if the JSON field has an
             * unexpected type.
             */
            @JsonProperty("multi_edit_tool_count")
            @ExcludeMissing
            fun _multiEditToolCount(): JsonField<Long> = multiEditToolCount

            /**
             * Returns the raw JSON value of [notebookEditToolCount].
             *
             * Unlike [notebookEditToolCount], this method doesn't throw if the JSON field has an
             * unexpected type.
             */
            @JsonProperty("notebook_edit_tool_count")
            @ExcludeMissing
            fun _notebookEditToolCount(): JsonField<Long> = notebookEditToolCount

            /**
             * Returns the raw JSON value of [pluginsUsedCount].
             *
             * Unlike [pluginsUsedCount], this method doesn't throw if the JSON field has an
             * unexpected type.
             */
            @JsonProperty("plugins_used_count")
            @ExcludeMissing
            fun _pluginsUsedCount(): JsonField<Long> = pluginsUsedCount

            /**
             * Returns the raw JSON value of [sessionsWithFileEditsCount].
             *
             * Unlike [sessionsWithFileEditsCount], this method doesn't throw if the JSON field has
             * an unexpected type.
             */
            @JsonProperty("sessions_with_file_edits_count")
            @ExcludeMissing
            fun _sessionsWithFileEditsCount(): JsonField<Long> = sessionsWithFileEditsCount

            /**
             * Returns the raw JSON value of [writeToolCount].
             *
             * Unlike [writeToolCount], this method doesn't throw if the JSON field has an
             * unexpected type.
             */
            @JsonProperty("write_tool_count")
            @ExcludeMissing
            fun _writeToolCount(): JsonField<Long> = writeToolCount

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
                 * Returns a mutable builder for constructing an instance of [Sessions].
                 *
                 * The following fields are required:
                 * ```java
                 * .actionCount()
                 * .artifactsCreatedCount()
                 * .connectorsUsedCount()
                 * .dispatchTurnCount()
                 * .distinctConnectorsUsedCount()
                 * .distinctSessionCount()
                 * .distinctSkillsUsedCount()
                 * .messageCount()
                 * .skillsUsedCount()
                 * ```
                 */
                @JvmStatic fun builder() = Builder()
            }

            /** A builder for [Sessions]. */
            class Builder internal constructor() {

                private var actionCount: JsonField<Long>? = null
                private var artifactsCreatedCount: JsonField<Long>? = null
                private var connectorsUsedCount: JsonField<Long>? = null
                private var dispatchTurnCount: JsonField<Long>? = null
                private var distinctConnectorsUsedCount: JsonField<Long>? = null
                private var distinctSessionCount: JsonField<Long>? = null
                private var distinctSkillsUsedCount: JsonField<Long>? = null
                private var messageCount: JsonField<Long>? = null
                private var skillsUsedCount: JsonField<Long>? = null
                private var distinctPluginsUsedCount: JsonField<Long> = JsonMissing.of()
                private var editToolCount: JsonField<Long> = JsonMissing.of()
                private var fileEditCount: JsonField<Long> = JsonMissing.of()
                private var multiEditToolCount: JsonField<Long> = JsonMissing.of()
                private var notebookEditToolCount: JsonField<Long> = JsonMissing.of()
                private var pluginsUsedCount: JsonField<Long> = JsonMissing.of()
                private var sessionsWithFileEditsCount: JsonField<Long> = JsonMissing.of()
                private var writeToolCount: JsonField<Long> = JsonMissing.of()
                private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

                @JvmSynthetic
                internal fun from(sessions: Sessions) = apply {
                    actionCount = sessions.actionCount
                    artifactsCreatedCount = sessions.artifactsCreatedCount
                    connectorsUsedCount = sessions.connectorsUsedCount
                    dispatchTurnCount = sessions.dispatchTurnCount
                    distinctConnectorsUsedCount = sessions.distinctConnectorsUsedCount
                    distinctSessionCount = sessions.distinctSessionCount
                    distinctSkillsUsedCount = sessions.distinctSkillsUsedCount
                    messageCount = sessions.messageCount
                    skillsUsedCount = sessions.skillsUsedCount
                    distinctPluginsUsedCount = sessions.distinctPluginsUsedCount
                    editToolCount = sessions.editToolCount
                    fileEditCount = sessions.fileEditCount
                    multiEditToolCount = sessions.multiEditToolCount
                    notebookEditToolCount = sessions.notebookEditToolCount
                    pluginsUsedCount = sessions.pluginsUsedCount
                    sessionsWithFileEditsCount = sessions.sessionsWithFileEditsCount
                    writeToolCount = sessions.writeToolCount
                    additionalProperties = sessions.additionalProperties.toMutableMap()
                }

                /**
                 * Same measure as `cowork_metrics.action_count`, for activity recorded while
                 * members had Chat and Cowork unified turned on.
                 */
                fun actionCount(actionCount: Long) = actionCount(JsonField.of(actionCount))

                /**
                 * Sets [Builder.actionCount] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.actionCount] with a well-typed [Long] value
                 * instead. This method is primarily for setting the field to an undocumented or not
                 * yet supported value.
                 */
                fun actionCount(actionCount: JsonField<Long>) = apply {
                    this.actionCount = actionCount
                }

                /**
                 * Same measure as `cowork_metrics.artifacts_created_count`, for activity recorded
                 * while members had Chat and Cowork unified turned on. Exact in date-range mode: a
                 * creation belongs to exactly one day, so the per-day counts never overlap and
                 * their sum over the window is the exact count of distinct creations in it.
                 */
                fun artifactsCreatedCount(artifactsCreatedCount: Long) =
                    artifactsCreatedCount(JsonField.of(artifactsCreatedCount))

                /**
                 * Sets [Builder.artifactsCreatedCount] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.artifactsCreatedCount] with a well-typed [Long]
                 * value instead. This method is primarily for setting the field to an undocumented
                 * or not yet supported value.
                 */
                fun artifactsCreatedCount(artifactsCreatedCount: JsonField<Long>) = apply {
                    this.artifactsCreatedCount = artifactsCreatedCount
                }

                /**
                 * Same measure as `cowork_metrics.connectors_used_count`, for activity recorded
                 * while members had Chat and Cowork unified turned on.
                 */
                fun connectorsUsedCount(connectorsUsedCount: Long) =
                    connectorsUsedCount(JsonField.of(connectorsUsedCount))

                /**
                 * Sets [Builder.connectorsUsedCount] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.connectorsUsedCount] with a well-typed [Long]
                 * value instead. This method is primarily for setting the field to an undocumented
                 * or not yet supported value.
                 */
                fun connectorsUsedCount(connectorsUsedCount: JsonField<Long>) = apply {
                    this.connectorsUsedCount = connectorsUsedCount
                }

                /**
                 * Same measure as `cowork_metrics.dispatch_turn_count`, for activity recorded while
                 * members had Chat and Cowork unified turned on.
                 */
                fun dispatchTurnCount(dispatchTurnCount: Long) =
                    dispatchTurnCount(JsonField.of(dispatchTurnCount))

                /**
                 * Sets [Builder.dispatchTurnCount] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.dispatchTurnCount] with a well-typed [Long]
                 * value instead. This method is primarily for setting the field to an undocumented
                 * or not yet supported value.
                 */
                fun dispatchTurnCount(dispatchTurnCount: JsonField<Long>) = apply {
                    this.dispatchTurnCount = dispatchTurnCount
                }

                /**
                 * Same measure as `cowork_metrics.distinct_connectors_used_count`, for activity
                 * recorded while members had Chat and Cowork unified turned on. Approximate (HLL,
                 * typical error <2%) in date-range mode. Null on aggregated rows where a distinct
                 * count cannot be computed.
                 */
                fun distinctConnectorsUsedCount(distinctConnectorsUsedCount: Long?) =
                    distinctConnectorsUsedCount(JsonField.ofNullable(distinctConnectorsUsedCount))

                /**
                 * Alias for [Builder.distinctConnectorsUsedCount].
                 *
                 * This unboxed primitive overload exists for backwards compatibility.
                 */
                fun distinctConnectorsUsedCount(distinctConnectorsUsedCount: Long) =
                    distinctConnectorsUsedCount(distinctConnectorsUsedCount as Long?)

                /**
                 * Alias for calling [Builder.distinctConnectorsUsedCount] with
                 * `distinctConnectorsUsedCount.orElse(null)`.
                 */
                fun distinctConnectorsUsedCount(distinctConnectorsUsedCount: Optional<Long>) =
                    distinctConnectorsUsedCount(distinctConnectorsUsedCount.getOrNull())

                /**
                 * Sets [Builder.distinctConnectorsUsedCount] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.distinctConnectorsUsedCount] with a well-typed
                 * [Long] value instead. This method is primarily for setting the field to an
                 * undocumented or not yet supported value.
                 */
                fun distinctConnectorsUsedCount(distinctConnectorsUsedCount: JsonField<Long>) =
                    apply {
                        this.distinctConnectorsUsedCount = distinctConnectorsUsedCount
                    }

                /**
                 * Same measure as `cowork_metrics.distinct_session_count`, for activity recorded
                 * while members had Chat and Cowork unified turned on. Approximate (HLL, typical
                 * error <2%) in date-range mode. Null on aggregated rows where a distinct count
                 * cannot be computed.
                 */
                fun distinctSessionCount(distinctSessionCount: Long?) =
                    distinctSessionCount(JsonField.ofNullable(distinctSessionCount))

                /**
                 * Alias for [Builder.distinctSessionCount].
                 *
                 * This unboxed primitive overload exists for backwards compatibility.
                 */
                fun distinctSessionCount(distinctSessionCount: Long) =
                    distinctSessionCount(distinctSessionCount as Long?)

                /**
                 * Alias for calling [Builder.distinctSessionCount] with
                 * `distinctSessionCount.orElse(null)`.
                 */
                fun distinctSessionCount(distinctSessionCount: Optional<Long>) =
                    distinctSessionCount(distinctSessionCount.getOrNull())

                /**
                 * Sets [Builder.distinctSessionCount] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.distinctSessionCount] with a well-typed [Long]
                 * value instead. This method is primarily for setting the field to an undocumented
                 * or not yet supported value.
                 */
                fun distinctSessionCount(distinctSessionCount: JsonField<Long>) = apply {
                    this.distinctSessionCount = distinctSessionCount
                }

                /**
                 * Same measure as `cowork_metrics.distinct_skills_used_count`, for activity
                 * recorded while members had Chat and Cowork unified turned on. Approximate (HLL,
                 * typical error <2%) in date-range mode. Null on aggregated rows where a distinct
                 * count cannot be computed.
                 */
                fun distinctSkillsUsedCount(distinctSkillsUsedCount: Long?) =
                    distinctSkillsUsedCount(JsonField.ofNullable(distinctSkillsUsedCount))

                /**
                 * Alias for [Builder.distinctSkillsUsedCount].
                 *
                 * This unboxed primitive overload exists for backwards compatibility.
                 */
                fun distinctSkillsUsedCount(distinctSkillsUsedCount: Long) =
                    distinctSkillsUsedCount(distinctSkillsUsedCount as Long?)

                /**
                 * Alias for calling [Builder.distinctSkillsUsedCount] with
                 * `distinctSkillsUsedCount.orElse(null)`.
                 */
                fun distinctSkillsUsedCount(distinctSkillsUsedCount: Optional<Long>) =
                    distinctSkillsUsedCount(distinctSkillsUsedCount.getOrNull())

                /**
                 * Sets [Builder.distinctSkillsUsedCount] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.distinctSkillsUsedCount] with a well-typed
                 * [Long] value instead. This method is primarily for setting the field to an
                 * undocumented or not yet supported value.
                 */
                fun distinctSkillsUsedCount(distinctSkillsUsedCount: JsonField<Long>) = apply {
                    this.distinctSkillsUsedCount = distinctSkillsUsedCount
                }

                /**
                 * Same measure as `cowork_metrics.message_count`, for activity recorded while
                 * members had Chat and Cowork unified turned on.
                 */
                fun messageCount(messageCount: Long) = messageCount(JsonField.of(messageCount))

                /**
                 * Sets [Builder.messageCount] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.messageCount] with a well-typed [Long] value
                 * instead. This method is primarily for setting the field to an undocumented or not
                 * yet supported value.
                 */
                fun messageCount(messageCount: JsonField<Long>) = apply {
                    this.messageCount = messageCount
                }

                /**
                 * Same measure as `cowork_metrics.skills_used_count`, for activity recorded while
                 * members had Chat and Cowork unified turned on.
                 */
                fun skillsUsedCount(skillsUsedCount: Long) =
                    skillsUsedCount(JsonField.of(skillsUsedCount))

                /**
                 * Sets [Builder.skillsUsedCount] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.skillsUsedCount] with a well-typed [Long] value
                 * instead. This method is primarily for setting the field to an undocumented or not
                 * yet supported value.
                 */
                fun skillsUsedCount(skillsUsedCount: JsonField<Long>) = apply {
                    this.skillsUsedCount = skillsUsedCount
                }

                /**
                 * Same measure as `cowork_metrics.distinct_plugins_used_count`, for activity
                 * recorded while members had Chat and Cowork unified turned on. Approximate (HLL,
                 * typical error <2%) in date-range mode. Null on aggregated rows where a distinct
                 * count cannot be computed.
                 */
                fun distinctPluginsUsedCount(distinctPluginsUsedCount: Long?) =
                    distinctPluginsUsedCount(JsonField.ofNullable(distinctPluginsUsedCount))

                /**
                 * Alias for [Builder.distinctPluginsUsedCount].
                 *
                 * This unboxed primitive overload exists for backwards compatibility.
                 */
                fun distinctPluginsUsedCount(distinctPluginsUsedCount: Long) =
                    distinctPluginsUsedCount(distinctPluginsUsedCount as Long?)

                /**
                 * Alias for calling [Builder.distinctPluginsUsedCount] with
                 * `distinctPluginsUsedCount.orElse(null)`.
                 */
                fun distinctPluginsUsedCount(distinctPluginsUsedCount: Optional<Long>) =
                    distinctPluginsUsedCount(distinctPluginsUsedCount.getOrNull())

                /**
                 * Sets [Builder.distinctPluginsUsedCount] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.distinctPluginsUsedCount] with a well-typed
                 * [Long] value instead. This method is primarily for setting the field to an
                 * undocumented or not yet supported value.
                 */
                fun distinctPluginsUsedCount(distinctPluginsUsedCount: JsonField<Long>) = apply {
                    this.distinctPluginsUsedCount = distinctPluginsUsedCount
                }

                /**
                 * Same measure as `cowork_metrics.edit_tool_count`, for activity recorded while
                 * members had Chat and Cowork unified turned on.
                 */
                fun editToolCount(editToolCount: Long?) =
                    editToolCount(JsonField.ofNullable(editToolCount))

                /**
                 * Alias for [Builder.editToolCount].
                 *
                 * This unboxed primitive overload exists for backwards compatibility.
                 */
                fun editToolCount(editToolCount: Long) = editToolCount(editToolCount as Long?)

                /** Alias for calling [Builder.editToolCount] with `editToolCount.orElse(null)`. */
                fun editToolCount(editToolCount: Optional<Long>) =
                    editToolCount(editToolCount.getOrNull())

                /**
                 * Sets [Builder.editToolCount] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.editToolCount] with a well-typed [Long] value
                 * instead. This method is primarily for setting the field to an undocumented or not
                 * yet supported value.
                 */
                fun editToolCount(editToolCount: JsonField<Long>) = apply {
                    this.editToolCount = editToolCount
                }

                /**
                 * Same measure as `cowork_metrics.file_edit_count`, for activity recorded while
                 * members had Chat and Cowork unified turned on.
                 */
                fun fileEditCount(fileEditCount: Long?) =
                    fileEditCount(JsonField.ofNullable(fileEditCount))

                /**
                 * Alias for [Builder.fileEditCount].
                 *
                 * This unboxed primitive overload exists for backwards compatibility.
                 */
                fun fileEditCount(fileEditCount: Long) = fileEditCount(fileEditCount as Long?)

                /** Alias for calling [Builder.fileEditCount] with `fileEditCount.orElse(null)`. */
                fun fileEditCount(fileEditCount: Optional<Long>) =
                    fileEditCount(fileEditCount.getOrNull())

                /**
                 * Sets [Builder.fileEditCount] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.fileEditCount] with a well-typed [Long] value
                 * instead. This method is primarily for setting the field to an undocumented or not
                 * yet supported value.
                 */
                fun fileEditCount(fileEditCount: JsonField<Long>) = apply {
                    this.fileEditCount = fileEditCount
                }

                /**
                 * Same measure as `cowork_metrics.multi_edit_tool_count`, for activity recorded
                 * while members had Chat and Cowork unified turned on.
                 */
                fun multiEditToolCount(multiEditToolCount: Long?) =
                    multiEditToolCount(JsonField.ofNullable(multiEditToolCount))

                /**
                 * Alias for [Builder.multiEditToolCount].
                 *
                 * This unboxed primitive overload exists for backwards compatibility.
                 */
                fun multiEditToolCount(multiEditToolCount: Long) =
                    multiEditToolCount(multiEditToolCount as Long?)

                /**
                 * Alias for calling [Builder.multiEditToolCount] with
                 * `multiEditToolCount.orElse(null)`.
                 */
                fun multiEditToolCount(multiEditToolCount: Optional<Long>) =
                    multiEditToolCount(multiEditToolCount.getOrNull())

                /**
                 * Sets [Builder.multiEditToolCount] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.multiEditToolCount] with a well-typed [Long]
                 * value instead. This method is primarily for setting the field to an undocumented
                 * or not yet supported value.
                 */
                fun multiEditToolCount(multiEditToolCount: JsonField<Long>) = apply {
                    this.multiEditToolCount = multiEditToolCount
                }

                /**
                 * Same measure as `cowork_metrics.notebook_edit_tool_count`, for activity recorded
                 * while members had Chat and Cowork unified turned on.
                 */
                fun notebookEditToolCount(notebookEditToolCount: Long?) =
                    notebookEditToolCount(JsonField.ofNullable(notebookEditToolCount))

                /**
                 * Alias for [Builder.notebookEditToolCount].
                 *
                 * This unboxed primitive overload exists for backwards compatibility.
                 */
                fun notebookEditToolCount(notebookEditToolCount: Long) =
                    notebookEditToolCount(notebookEditToolCount as Long?)

                /**
                 * Alias for calling [Builder.notebookEditToolCount] with
                 * `notebookEditToolCount.orElse(null)`.
                 */
                fun notebookEditToolCount(notebookEditToolCount: Optional<Long>) =
                    notebookEditToolCount(notebookEditToolCount.getOrNull())

                /**
                 * Sets [Builder.notebookEditToolCount] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.notebookEditToolCount] with a well-typed [Long]
                 * value instead. This method is primarily for setting the field to an undocumented
                 * or not yet supported value.
                 */
                fun notebookEditToolCount(notebookEditToolCount: JsonField<Long>) = apply {
                    this.notebookEditToolCount = notebookEditToolCount
                }

                /**
                 * Same measure as `cowork_metrics.plugins_used_count`, for activity recorded while
                 * members had Chat and Cowork unified turned on.
                 */
                fun pluginsUsedCount(pluginsUsedCount: Long?) =
                    pluginsUsedCount(JsonField.ofNullable(pluginsUsedCount))

                /**
                 * Alias for [Builder.pluginsUsedCount].
                 *
                 * This unboxed primitive overload exists for backwards compatibility.
                 */
                fun pluginsUsedCount(pluginsUsedCount: Long) =
                    pluginsUsedCount(pluginsUsedCount as Long?)

                /**
                 * Alias for calling [Builder.pluginsUsedCount] with
                 * `pluginsUsedCount.orElse(null)`.
                 */
                fun pluginsUsedCount(pluginsUsedCount: Optional<Long>) =
                    pluginsUsedCount(pluginsUsedCount.getOrNull())

                /**
                 * Sets [Builder.pluginsUsedCount] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.pluginsUsedCount] with a well-typed [Long] value
                 * instead. This method is primarily for setting the field to an undocumented or not
                 * yet supported value.
                 */
                fun pluginsUsedCount(pluginsUsedCount: JsonField<Long>) = apply {
                    this.pluginsUsedCount = pluginsUsedCount
                }

                /**
                 * Same measure as `cowork_metrics.sessions_with_file_edits_count`, for activity
                 * recorded while members had Chat and Cowork unified turned on. Approximate (HLL,
                 * typical error <2%) in date-range mode. Null on aggregated rows where a distinct
                 * count cannot be computed.
                 */
                fun sessionsWithFileEditsCount(sessionsWithFileEditsCount: Long?) =
                    sessionsWithFileEditsCount(JsonField.ofNullable(sessionsWithFileEditsCount))

                /**
                 * Alias for [Builder.sessionsWithFileEditsCount].
                 *
                 * This unboxed primitive overload exists for backwards compatibility.
                 */
                fun sessionsWithFileEditsCount(sessionsWithFileEditsCount: Long) =
                    sessionsWithFileEditsCount(sessionsWithFileEditsCount as Long?)

                /**
                 * Alias for calling [Builder.sessionsWithFileEditsCount] with
                 * `sessionsWithFileEditsCount.orElse(null)`.
                 */
                fun sessionsWithFileEditsCount(sessionsWithFileEditsCount: Optional<Long>) =
                    sessionsWithFileEditsCount(sessionsWithFileEditsCount.getOrNull())

                /**
                 * Sets [Builder.sessionsWithFileEditsCount] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.sessionsWithFileEditsCount] with a well-typed
                 * [Long] value instead. This method is primarily for setting the field to an
                 * undocumented or not yet supported value.
                 */
                fun sessionsWithFileEditsCount(sessionsWithFileEditsCount: JsonField<Long>) =
                    apply {
                        this.sessionsWithFileEditsCount = sessionsWithFileEditsCount
                    }

                /**
                 * Same measure as `cowork_metrics.write_tool_count`, for activity recorded while
                 * members had Chat and Cowork unified turned on.
                 */
                fun writeToolCount(writeToolCount: Long?) =
                    writeToolCount(JsonField.ofNullable(writeToolCount))

                /**
                 * Alias for [Builder.writeToolCount].
                 *
                 * This unboxed primitive overload exists for backwards compatibility.
                 */
                fun writeToolCount(writeToolCount: Long) = writeToolCount(writeToolCount as Long?)

                /**
                 * Alias for calling [Builder.writeToolCount] with `writeToolCount.orElse(null)`.
                 */
                fun writeToolCount(writeToolCount: Optional<Long>) =
                    writeToolCount(writeToolCount.getOrNull())

                /**
                 * Sets [Builder.writeToolCount] to an arbitrary JSON value.
                 *
                 * You should usually call [Builder.writeToolCount] with a well-typed [Long] value
                 * instead. This method is primarily for setting the field to an undocumented or not
                 * yet supported value.
                 */
                fun writeToolCount(writeToolCount: JsonField<Long>) = apply {
                    this.writeToolCount = writeToolCount
                }

                fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                    this.additionalProperties.clear()
                    putAllAdditionalProperties(additionalProperties)
                }

                fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                    additionalProperties.put(key, value)
                }

                fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) =
                    apply {
                        this.additionalProperties.putAll(additionalProperties)
                    }

                fun removeAdditionalProperty(key: String) = apply {
                    additionalProperties.remove(key)
                }

                fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                    keys.forEach(::removeAdditionalProperty)
                }

                /**
                 * Returns an immutable instance of [Sessions].
                 *
                 * Further updates to this [Builder] will not mutate the returned instance.
                 *
                 * The following fields are required:
                 * ```java
                 * .actionCount()
                 * .artifactsCreatedCount()
                 * .connectorsUsedCount()
                 * .dispatchTurnCount()
                 * .distinctConnectorsUsedCount()
                 * .distinctSessionCount()
                 * .distinctSkillsUsedCount()
                 * .messageCount()
                 * .skillsUsedCount()
                 * ```
                 *
                 * @throws IllegalStateException if any required field is unset.
                 */
                fun build(): Sessions =
                    Sessions(
                        checkRequired("actionCount", actionCount),
                        checkRequired("artifactsCreatedCount", artifactsCreatedCount),
                        checkRequired("connectorsUsedCount", connectorsUsedCount),
                        checkRequired("dispatchTurnCount", dispatchTurnCount),
                        checkRequired("distinctConnectorsUsedCount", distinctConnectorsUsedCount),
                        checkRequired("distinctSessionCount", distinctSessionCount),
                        checkRequired("distinctSkillsUsedCount", distinctSkillsUsedCount),
                        checkRequired("messageCount", messageCount),
                        checkRequired("skillsUsedCount", skillsUsedCount),
                        distinctPluginsUsedCount,
                        editToolCount,
                        fileEditCount,
                        multiEditToolCount,
                        notebookEditToolCount,
                        pluginsUsedCount,
                        sessionsWithFileEditsCount,
                        writeToolCount,
                        additionalProperties.toMutableMap(),
                    )
            }

            private var validated: Boolean = false

            /**
             * Validates that the types of all values in this object match their expected types
             * recursively.
             *
             * This method is _not_ forwards compatible with new types from the API for existing
             * fields.
             *
             * @throws AnthropicInvalidDataException if any value type in this object doesn't match
             *   its expected type.
             */
            fun validate(): Sessions = apply {
                if (validated) {
                    return@apply
                }

                actionCount()
                artifactsCreatedCount()
                connectorsUsedCount()
                dispatchTurnCount()
                distinctConnectorsUsedCount()
                distinctSessionCount()
                distinctSkillsUsedCount()
                messageCount()
                skillsUsedCount()
                distinctPluginsUsedCount()
                editToolCount()
                fileEditCount()
                multiEditToolCount()
                notebookEditToolCount()
                pluginsUsedCount()
                sessionsWithFileEditsCount()
                writeToolCount()
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
                (if (actionCount.asKnown().isPresent) 1 else 0) +
                    (if (artifactsCreatedCount.asKnown().isPresent) 1 else 0) +
                    (if (connectorsUsedCount.asKnown().isPresent) 1 else 0) +
                    (if (dispatchTurnCount.asKnown().isPresent) 1 else 0) +
                    (if (distinctConnectorsUsedCount.asKnown().isPresent) 1 else 0) +
                    (if (distinctSessionCount.asKnown().isPresent) 1 else 0) +
                    (if (distinctSkillsUsedCount.asKnown().isPresent) 1 else 0) +
                    (if (messageCount.asKnown().isPresent) 1 else 0) +
                    (if (skillsUsedCount.asKnown().isPresent) 1 else 0) +
                    (if (distinctPluginsUsedCount.asKnown().isPresent) 1 else 0) +
                    (if (editToolCount.asKnown().isPresent) 1 else 0) +
                    (if (fileEditCount.asKnown().isPresent) 1 else 0) +
                    (if (multiEditToolCount.asKnown().isPresent) 1 else 0) +
                    (if (notebookEditToolCount.asKnown().isPresent) 1 else 0) +
                    (if (pluginsUsedCount.asKnown().isPresent) 1 else 0) +
                    (if (sessionsWithFileEditsCount.asKnown().isPresent) 1 else 0) +
                    (if (writeToolCount.asKnown().isPresent) 1 else 0)

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is Sessions &&
                    actionCount == other.actionCount &&
                    artifactsCreatedCount == other.artifactsCreatedCount &&
                    connectorsUsedCount == other.connectorsUsedCount &&
                    dispatchTurnCount == other.dispatchTurnCount &&
                    distinctConnectorsUsedCount == other.distinctConnectorsUsedCount &&
                    distinctSessionCount == other.distinctSessionCount &&
                    distinctSkillsUsedCount == other.distinctSkillsUsedCount &&
                    messageCount == other.messageCount &&
                    skillsUsedCount == other.skillsUsedCount &&
                    distinctPluginsUsedCount == other.distinctPluginsUsedCount &&
                    editToolCount == other.editToolCount &&
                    fileEditCount == other.fileEditCount &&
                    multiEditToolCount == other.multiEditToolCount &&
                    notebookEditToolCount == other.notebookEditToolCount &&
                    pluginsUsedCount == other.pluginsUsedCount &&
                    sessionsWithFileEditsCount == other.sessionsWithFileEditsCount &&
                    writeToolCount == other.writeToolCount &&
                    additionalProperties == other.additionalProperties
            }

            private val hashCode: Int by lazy {
                Objects.hash(
                    actionCount,
                    artifactsCreatedCount,
                    connectorsUsedCount,
                    dispatchTurnCount,
                    distinctConnectorsUsedCount,
                    distinctSessionCount,
                    distinctSkillsUsedCount,
                    messageCount,
                    skillsUsedCount,
                    distinctPluginsUsedCount,
                    editToolCount,
                    fileEditCount,
                    multiEditToolCount,
                    notebookEditToolCount,
                    pluginsUsedCount,
                    sessionsWithFileEditsCount,
                    writeToolCount,
                    additionalProperties,
                )
            }

            override fun hashCode(): Int = hashCode

            override fun toString() =
                "Sessions{actionCount=$actionCount, artifactsCreatedCount=$artifactsCreatedCount, connectorsUsedCount=$connectorsUsedCount, dispatchTurnCount=$dispatchTurnCount, distinctConnectorsUsedCount=$distinctConnectorsUsedCount, distinctSessionCount=$distinctSessionCount, distinctSkillsUsedCount=$distinctSkillsUsedCount, messageCount=$messageCount, skillsUsedCount=$skillsUsedCount, distinctPluginsUsedCount=$distinctPluginsUsedCount, editToolCount=$editToolCount, fileEditCount=$fileEditCount, multiEditToolCount=$multiEditToolCount, notebookEditToolCount=$notebookEditToolCount, pluginsUsedCount=$pluginsUsedCount, sessionsWithFileEditsCount=$sessionsWithFileEditsCount, writeToolCount=$writeToolCount, additionalProperties=$additionalProperties}"
        }

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

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaAnalyticsUserActivity &&
            chatMetrics == other.chatMetrics &&
            claudeCodeMetrics == other.claudeCodeMetrics &&
            coworkMetrics == other.coworkMetrics &&
            designMetrics == other.designMetrics &&
            officeMetrics == other.officeMetrics &&
            scienceMetrics == other.scienceMetrics &&
            webSearchCount == other.webSearchCount &&
            chatCoworkUnifiedMetrics == other.chatCoworkUnifiedMetrics &&
            distinctUserCount == other.distinctUserCount &&
            lastActivityDate == other.lastActivityDate &&
            rbacGroupId == other.rbacGroupId &&
            rbacGroupName == other.rbacGroupName &&
            user == other.user &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            chatMetrics,
            claudeCodeMetrics,
            coworkMetrics,
            designMetrics,
            officeMetrics,
            scienceMetrics,
            webSearchCount,
            chatCoworkUnifiedMetrics,
            distinctUserCount,
            lastActivityDate,
            rbacGroupId,
            rbacGroupName,
            user,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaAnalyticsUserActivity{chatMetrics=$chatMetrics, claudeCodeMetrics=$claudeCodeMetrics, coworkMetrics=$coworkMetrics, designMetrics=$designMetrics, officeMetrics=$officeMetrics, scienceMetrics=$scienceMetrics, webSearchCount=$webSearchCount, chatCoworkUnifiedMetrics=$chatCoworkUnifiedMetrics, distinctUserCount=$distinctUserCount, lastActivityDate=$lastActivityDate, rbacGroupId=$rbacGroupId, rbacGroupName=$rbacGroupName, user=$user, additionalProperties=$additionalProperties}"
}
