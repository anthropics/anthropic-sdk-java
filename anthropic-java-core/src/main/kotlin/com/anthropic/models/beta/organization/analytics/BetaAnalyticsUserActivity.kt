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
        private val chat: JsonField<BetaAnalyticsChatCoworkUnifiedChatMetrics>,
        private val sessions: JsonField<BetaAnalyticsChatCoworkUnifiedSessionsMetrics>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("chat")
            @ExcludeMissing
            chat: JsonField<BetaAnalyticsChatCoworkUnifiedChatMetrics> = JsonMissing.of(),
            @JsonProperty("sessions")
            @ExcludeMissing
            sessions: JsonField<BetaAnalyticsChatCoworkUnifiedSessionsMetrics> = JsonMissing.of(),
        ) : this(chat, sessions, mutableMapOf())

        /**
         * Chat activity recorded while members had Chat and Cowork unified turned on.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
         *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
         */
        fun chat(): BetaAnalyticsChatCoworkUnifiedChatMetrics = chat.getRequired("chat")

        /**
         * Cowork session activity recorded while members had Chat and Cowork unified turned on.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
         *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
         */
        fun sessions(): BetaAnalyticsChatCoworkUnifiedSessionsMetrics =
            sessions.getRequired("sessions")

        /**
         * Returns the raw JSON value of [chat].
         *
         * Unlike [chat], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("chat")
        @ExcludeMissing
        fun _chat(): JsonField<BetaAnalyticsChatCoworkUnifiedChatMetrics> = chat

        /**
         * Returns the raw JSON value of [sessions].
         *
         * Unlike [sessions], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("sessions")
        @ExcludeMissing
        fun _sessions(): JsonField<BetaAnalyticsChatCoworkUnifiedSessionsMetrics> = sessions

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

            private var chat: JsonField<BetaAnalyticsChatCoworkUnifiedChatMetrics>? = null
            private var sessions: JsonField<BetaAnalyticsChatCoworkUnifiedSessionsMetrics>? = null
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(chatCoworkUnifiedMetrics: ChatCoworkUnifiedMetrics) = apply {
                chat = chatCoworkUnifiedMetrics.chat
                sessions = chatCoworkUnifiedMetrics.sessions
                additionalProperties = chatCoworkUnifiedMetrics.additionalProperties.toMutableMap()
            }

            /** Chat activity recorded while members had Chat and Cowork unified turned on. */
            fun chat(chat: BetaAnalyticsChatCoworkUnifiedChatMetrics) = chat(JsonField.of(chat))

            /**
             * Sets [Builder.chat] to an arbitrary JSON value.
             *
             * You should usually call [Builder.chat] with a well-typed
             * [BetaAnalyticsChatCoworkUnifiedChatMetrics] value instead. This method is primarily
             * for setting the field to an undocumented or not yet supported value.
             */
            fun chat(chat: JsonField<BetaAnalyticsChatCoworkUnifiedChatMetrics>) = apply {
                this.chat = chat
            }

            /**
             * Cowork session activity recorded while members had Chat and Cowork unified turned on.
             */
            fun sessions(sessions: BetaAnalyticsChatCoworkUnifiedSessionsMetrics) =
                sessions(JsonField.of(sessions))

            /**
             * Sets [Builder.sessions] to an arbitrary JSON value.
             *
             * You should usually call [Builder.sessions] with a well-typed
             * [BetaAnalyticsChatCoworkUnifiedSessionsMetrics] value instead. This method is
             * primarily for setting the field to an undocumented or not yet supported value.
             */
            fun sessions(sessions: JsonField<BetaAnalyticsChatCoworkUnifiedSessionsMetrics>) =
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
