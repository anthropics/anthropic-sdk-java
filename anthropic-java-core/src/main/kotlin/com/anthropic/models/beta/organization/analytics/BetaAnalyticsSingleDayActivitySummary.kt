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
import java.time.OffsetDateTime
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** Per-day entry in the /summaries response. */
class BetaAnalyticsSingleDayActivitySummary
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val assignedSeatCount: JsonField<Long>,
    private val coworkDailyActiveUserCount: JsonField<Long>,
    private val coworkMonthlyActiveUserCount: JsonField<Long>,
    private val coworkWeeklyActiveUserCount: JsonField<Long>,
    private val dailyActiveUserCount: JsonField<Long>,
    private val dailyAdoptionRate: JsonField<Double>,
    private val endingAt: JsonField<OffsetDateTime>,
    private val monthlyActiveUserCount: JsonField<Long>,
    private val monthlyAdoptionRate: JsonField<Double>,
    private val pendingInviteCount: JsonField<Long>,
    private val startingAt: JsonField<OffsetDateTime>,
    private val weeklyActiveUserCount: JsonField<Long>,
    private val weeklyAdoptionRate: JsonField<Double>,
    private val chatCoworkUnifiedDailyActiveUserCount: JsonField<Long>,
    private val chatCoworkUnifiedMonthlyActiveUserCount: JsonField<Long>,
    private val chatCoworkUnifiedWeeklyActiveUserCount: JsonField<Long>,
    private val chatDailyActiveUserCount: JsonField<Long>,
    private val chatMonthlyActiveUserCount: JsonField<Long>,
    private val chatWeeklyActiveUserCount: JsonField<Long>,
    private val claudeCodeDailyActiveUserCount: JsonField<Long>,
    private val claudeCodeMonthlyActiveUserCount: JsonField<Long>,
    private val claudeCodeWeeklyActiveUserCount: JsonField<Long>,
    private val claudeDesignDailyActiveUserCount: JsonField<Long>,
    private val claudeDesignMonthlyActiveUserCount: JsonField<Long>,
    private val claudeDesignWeeklyActiveUserCount: JsonField<Long>,
    private val officeAgentDailyActiveUserCount: JsonField<Long>,
    private val officeAgentMonthlyActiveUserCount: JsonField<Long>,
    private val officeAgentWeeklyActiveUserCount: JsonField<Long>,
    private val scienceDailyActiveUserCount: JsonField<Long>,
    private val scienceEntitledUserCount: JsonField<Long>,
    private val scienceMonthlyActiveUserCount: JsonField<Long>,
    private val scienceWeeklyActiveUserCount: JsonField<Long>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("assigned_seat_count")
        @ExcludeMissing
        assignedSeatCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("cowork_daily_active_user_count")
        @ExcludeMissing
        coworkDailyActiveUserCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("cowork_monthly_active_user_count")
        @ExcludeMissing
        coworkMonthlyActiveUserCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("cowork_weekly_active_user_count")
        @ExcludeMissing
        coworkWeeklyActiveUserCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("daily_active_user_count")
        @ExcludeMissing
        dailyActiveUserCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("daily_adoption_rate")
        @ExcludeMissing
        dailyAdoptionRate: JsonField<Double> = JsonMissing.of(),
        @JsonProperty("ending_at")
        @ExcludeMissing
        endingAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("monthly_active_user_count")
        @ExcludeMissing
        monthlyActiveUserCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("monthly_adoption_rate")
        @ExcludeMissing
        monthlyAdoptionRate: JsonField<Double> = JsonMissing.of(),
        @JsonProperty("pending_invite_count")
        @ExcludeMissing
        pendingInviteCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("starting_at")
        @ExcludeMissing
        startingAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("weekly_active_user_count")
        @ExcludeMissing
        weeklyActiveUserCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("weekly_adoption_rate")
        @ExcludeMissing
        weeklyAdoptionRate: JsonField<Double> = JsonMissing.of(),
        @JsonProperty("chat_cowork_unified_daily_active_user_count")
        @ExcludeMissing
        chatCoworkUnifiedDailyActiveUserCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("chat_cowork_unified_monthly_active_user_count")
        @ExcludeMissing
        chatCoworkUnifiedMonthlyActiveUserCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("chat_cowork_unified_weekly_active_user_count")
        @ExcludeMissing
        chatCoworkUnifiedWeeklyActiveUserCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("chat_daily_active_user_count")
        @ExcludeMissing
        chatDailyActiveUserCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("chat_monthly_active_user_count")
        @ExcludeMissing
        chatMonthlyActiveUserCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("chat_weekly_active_user_count")
        @ExcludeMissing
        chatWeeklyActiveUserCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("claude_code_daily_active_user_count")
        @ExcludeMissing
        claudeCodeDailyActiveUserCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("claude_code_monthly_active_user_count")
        @ExcludeMissing
        claudeCodeMonthlyActiveUserCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("claude_code_weekly_active_user_count")
        @ExcludeMissing
        claudeCodeWeeklyActiveUserCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("claude_design_daily_active_user_count")
        @ExcludeMissing
        claudeDesignDailyActiveUserCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("claude_design_monthly_active_user_count")
        @ExcludeMissing
        claudeDesignMonthlyActiveUserCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("claude_design_weekly_active_user_count")
        @ExcludeMissing
        claudeDesignWeeklyActiveUserCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("office_agent_daily_active_user_count")
        @ExcludeMissing
        officeAgentDailyActiveUserCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("office_agent_monthly_active_user_count")
        @ExcludeMissing
        officeAgentMonthlyActiveUserCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("office_agent_weekly_active_user_count")
        @ExcludeMissing
        officeAgentWeeklyActiveUserCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("science_daily_active_user_count")
        @ExcludeMissing
        scienceDailyActiveUserCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("science_entitled_user_count")
        @ExcludeMissing
        scienceEntitledUserCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("science_monthly_active_user_count")
        @ExcludeMissing
        scienceMonthlyActiveUserCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("science_weekly_active_user_count")
        @ExcludeMissing
        scienceWeeklyActiveUserCount: JsonField<Long> = JsonMissing.of(),
    ) : this(
        assignedSeatCount,
        coworkDailyActiveUserCount,
        coworkMonthlyActiveUserCount,
        coworkWeeklyActiveUserCount,
        dailyActiveUserCount,
        dailyAdoptionRate,
        endingAt,
        monthlyActiveUserCount,
        monthlyAdoptionRate,
        pendingInviteCount,
        startingAt,
        weeklyActiveUserCount,
        weeklyAdoptionRate,
        chatCoworkUnifiedDailyActiveUserCount,
        chatCoworkUnifiedMonthlyActiveUserCount,
        chatCoworkUnifiedWeeklyActiveUserCount,
        chatDailyActiveUserCount,
        chatMonthlyActiveUserCount,
        chatWeeklyActiveUserCount,
        claudeCodeDailyActiveUserCount,
        claudeCodeMonthlyActiveUserCount,
        claudeCodeWeeklyActiveUserCount,
        claudeDesignDailyActiveUserCount,
        claudeDesignMonthlyActiveUserCount,
        claudeDesignWeeklyActiveUserCount,
        officeAgentDailyActiveUserCount,
        officeAgentMonthlyActiveUserCount,
        officeAgentWeeklyActiveUserCount,
        scienceDailyActiveUserCount,
        scienceEntitledUserCount,
        scienceMonthlyActiveUserCount,
        scienceWeeklyActiveUserCount,
        mutableMapOf(),
    )

    /**
     * Number of seats currently assigned to members. Null when the response is scoped to an RBAC
     * group — seat assignment is org-wide and has no per-group analogue.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun assignedSeatCount(): Optional<Long> = assignedSeatCount.getOptional("assigned_seat_count")

    /**
     * Number of users with Cowork activity on the requested day
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun coworkDailyActiveUserCount(): Long =
        coworkDailyActiveUserCount.getRequired("cowork_daily_active_user_count")

    /**
     * Number of users with Cowork activity in the 30-day rolling window
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun coworkMonthlyActiveUserCount(): Long =
        coworkMonthlyActiveUserCount.getRequired("cowork_monthly_active_user_count")

    /**
     * Number of users with Cowork activity in the 7-day rolling window
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun coworkWeeklyActiveUserCount(): Long =
        coworkWeeklyActiveUserCount.getRequired("cowork_weekly_active_user_count")

    /**
     * Number of users with token consumption on the requested day
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun dailyActiveUserCount(): Long = dailyActiveUserCount.getRequired("daily_active_user_count")

    /**
     * Percentage of assigned seats with activity on the requested day (`DAU / assigned_seat_count *
     * 100`). Null when the response is scoped to an RBAC group.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun dailyAdoptionRate(): Optional<Double> = dailyAdoptionRate.getOptional("daily_adoption_rate")

    /**
     * End of the aggregation period (exclusive), UTC midnight in RFC 3339 format (e.g.
     * `2026-01-16T00:00:00Z`).
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun endingAt(): OffsetDateTime = endingAt.getRequired("ending_at")

    /**
     * Number of users with token consumption in the 30-day rolling window
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun monthlyActiveUserCount(): Long =
        monthlyActiveUserCount.getRequired("monthly_active_user_count")

    /**
     * Percentage of assigned seats with activity in the 30-day rolling window (`MAU /
     * assigned_seat_count * 100`). Null when the response is scoped to an RBAC group.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun monthlyAdoptionRate(): Optional<Double> =
        monthlyAdoptionRate.getOptional("monthly_adoption_rate")

    /**
     * Number of pending invitations to join the organization. Null when the response is scoped to
     * an RBAC group.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun pendingInviteCount(): Optional<Long> =
        pendingInviteCount.getOptional("pending_invite_count")

    /**
     * Start of the aggregation period (inclusive), UTC midnight in RFC 3339 format (e.g.
     * `2026-01-15T00:00:00Z`).
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun startingAt(): OffsetDateTime = startingAt.getRequired("starting_at")

    /**
     * Number of users with token consumption in the 7-day rolling window
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun weeklyActiveUserCount(): Long =
        weeklyActiveUserCount.getRequired("weekly_active_user_count")

    /**
     * Percentage of assigned seats with activity in the 7-day rolling window (`WAU /
     * assigned_seat_count * 100`). Null when the response is scoped to an RBAC group.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun weeklyAdoptionRate(): Optional<Double> =
        weeklyAdoptionRate.getOptional("weekly_adoption_rate")

    /**
     * Number of users with activity in Chat and Cowork unified on the requested day. Omitted from
     * the response on deployments that do not offer Chat and Cowork unified.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun chatCoworkUnifiedDailyActiveUserCount(): Optional<Long> =
        chatCoworkUnifiedDailyActiveUserCount.getOptional(
            "chat_cowork_unified_daily_active_user_count"
        )

    /**
     * Number of users with activity in Chat and Cowork unified in the 28-day rolling window (30
     * days when the request filters by `rbac_group_id`). Omitted from the response on deployments
     * that do not offer Chat and Cowork unified.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun chatCoworkUnifiedMonthlyActiveUserCount(): Optional<Long> =
        chatCoworkUnifiedMonthlyActiveUserCount.getOptional(
            "chat_cowork_unified_monthly_active_user_count"
        )

    /**
     * Number of users with activity in Chat and Cowork unified in the 7-day rolling window. Omitted
     * from the response on deployments that do not offer Chat and Cowork unified.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun chatCoworkUnifiedWeeklyActiveUserCount(): Optional<Long> =
        chatCoworkUnifiedWeeklyActiveUserCount.getOptional(
            "chat_cowork_unified_weekly_active_user_count"
        )

    /**
     * Number of users with claude.ai (chat) activity on the requested day. Omitted from the
     * response while the per-product breakdown is not enabled for this organization.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun chatDailyActiveUserCount(): Optional<Long> =
        chatDailyActiveUserCount.getOptional("chat_daily_active_user_count")

    /**
     * Number of users with claude.ai (chat) activity in the 30-day rolling window. Omitted from the
     * response while the per-product breakdown is not enabled for this organization.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun chatMonthlyActiveUserCount(): Optional<Long> =
        chatMonthlyActiveUserCount.getOptional("chat_monthly_active_user_count")

    /**
     * Number of users with claude.ai (chat) activity in the 7-day rolling window. Omitted from the
     * response while the per-product breakdown is not enabled for this organization.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun chatWeeklyActiveUserCount(): Optional<Long> =
        chatWeeklyActiveUserCount.getOptional("chat_weekly_active_user_count")

    /**
     * Number of users with Claude Code activity on the requested day. Omitted from the response
     * while the per-product breakdown is not enabled for this organization.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun claudeCodeDailyActiveUserCount(): Optional<Long> =
        claudeCodeDailyActiveUserCount.getOptional("claude_code_daily_active_user_count")

    /**
     * Number of users with Claude Code activity in the 30-day rolling window. Omitted from the
     * response while the per-product breakdown is not enabled for this organization.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun claudeCodeMonthlyActiveUserCount(): Optional<Long> =
        claudeCodeMonthlyActiveUserCount.getOptional("claude_code_monthly_active_user_count")

    /**
     * Number of users with Claude Code activity in the 7-day rolling window. Omitted from the
     * response while the per-product breakdown is not enabled for this organization.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun claudeCodeWeeklyActiveUserCount(): Optional<Long> =
        claudeCodeWeeklyActiveUserCount.getOptional("claude_code_weekly_active_user_count")

    /**
     * Number of users with Claude Design activity on the requested day. Omitted from the response
     * while the per-product breakdown is not enabled for this organization.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun claudeDesignDailyActiveUserCount(): Optional<Long> =
        claudeDesignDailyActiveUserCount.getOptional("claude_design_daily_active_user_count")

    /**
     * Number of users with Claude Design activity in the 30-day rolling window. Omitted from the
     * response while the per-product breakdown is not enabled for this organization.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun claudeDesignMonthlyActiveUserCount(): Optional<Long> =
        claudeDesignMonthlyActiveUserCount.getOptional("claude_design_monthly_active_user_count")

    /**
     * Number of users with Claude Design activity in the 7-day rolling window. Omitted from the
     * response while the per-product breakdown is not enabled for this organization.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun claudeDesignWeeklyActiveUserCount(): Optional<Long> =
        claudeDesignWeeklyActiveUserCount.getOptional("claude_design_weekly_active_user_count")

    /**
     * Number of users with Claude in Office activity on the requested day. Omitted from the
     * response while the per-product breakdown is not enabled for this organization.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun officeAgentDailyActiveUserCount(): Optional<Long> =
        officeAgentDailyActiveUserCount.getOptional("office_agent_daily_active_user_count")

    /**
     * Number of users with Claude in Office activity in the 30-day rolling window. Omitted from the
     * response while the per-product breakdown is not enabled for this organization.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun officeAgentMonthlyActiveUserCount(): Optional<Long> =
        officeAgentMonthlyActiveUserCount.getOptional("office_agent_monthly_active_user_count")

    /**
     * Number of users with Claude in Office activity in the 7-day rolling window. Omitted from the
     * response while the per-product breakdown is not enabled for this organization.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun officeAgentWeeklyActiveUserCount(): Optional<Long> =
        officeAgentWeeklyActiveUserCount.getOptional("office_agent_weekly_active_user_count")

    /**
     * Number of users with Claude Science activity on the requested day. Omitted from the response
     * while the per-product breakdown is not enabled for this organization.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun scienceDailyActiveUserCount(): Optional<Long> =
        scienceDailyActiveUserCount.getOptional("science_daily_active_user_count")

    /**
     * Number of users with a Claude Science seat entitlement (per-seat RBAC) at the time of the
     * daily snapshot. The funnel top; independent of the org-level Claude Science toggle. Null when
     * the response is scoped to an RBAC group — entitlement is org-wide and has no per-group
     * analogue. Omitted from the response while the per-product breakdown is not enabled for this
     * organization.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun scienceEntitledUserCount(): Optional<Long> =
        scienceEntitledUserCount.getOptional("science_entitled_user_count")

    /**
     * Number of users with Claude Science activity in the 30-day rolling window. Omitted from the
     * response while the per-product breakdown is not enabled for this organization.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun scienceMonthlyActiveUserCount(): Optional<Long> =
        scienceMonthlyActiveUserCount.getOptional("science_monthly_active_user_count")

    /**
     * Number of users with Claude Science activity in the 7-day rolling window. Omitted from the
     * response while the per-product breakdown is not enabled for this organization.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun scienceWeeklyActiveUserCount(): Optional<Long> =
        scienceWeeklyActiveUserCount.getOptional("science_weekly_active_user_count")

    /**
     * Returns the raw JSON value of [assignedSeatCount].
     *
     * Unlike [assignedSeatCount], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("assigned_seat_count")
    @ExcludeMissing
    fun _assignedSeatCount(): JsonField<Long> = assignedSeatCount

    /**
     * Returns the raw JSON value of [coworkDailyActiveUserCount].
     *
     * Unlike [coworkDailyActiveUserCount], this method doesn't throw if the JSON field has an
     * unexpected type.
     */
    @JsonProperty("cowork_daily_active_user_count")
    @ExcludeMissing
    fun _coworkDailyActiveUserCount(): JsonField<Long> = coworkDailyActiveUserCount

    /**
     * Returns the raw JSON value of [coworkMonthlyActiveUserCount].
     *
     * Unlike [coworkMonthlyActiveUserCount], this method doesn't throw if the JSON field has an
     * unexpected type.
     */
    @JsonProperty("cowork_monthly_active_user_count")
    @ExcludeMissing
    fun _coworkMonthlyActiveUserCount(): JsonField<Long> = coworkMonthlyActiveUserCount

    /**
     * Returns the raw JSON value of [coworkWeeklyActiveUserCount].
     *
     * Unlike [coworkWeeklyActiveUserCount], this method doesn't throw if the JSON field has an
     * unexpected type.
     */
    @JsonProperty("cowork_weekly_active_user_count")
    @ExcludeMissing
    fun _coworkWeeklyActiveUserCount(): JsonField<Long> = coworkWeeklyActiveUserCount

    /**
     * Returns the raw JSON value of [dailyActiveUserCount].
     *
     * Unlike [dailyActiveUserCount], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("daily_active_user_count")
    @ExcludeMissing
    fun _dailyActiveUserCount(): JsonField<Long> = dailyActiveUserCount

    /**
     * Returns the raw JSON value of [dailyAdoptionRate].
     *
     * Unlike [dailyAdoptionRate], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("daily_adoption_rate")
    @ExcludeMissing
    fun _dailyAdoptionRate(): JsonField<Double> = dailyAdoptionRate

    /**
     * Returns the raw JSON value of [endingAt].
     *
     * Unlike [endingAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("ending_at") @ExcludeMissing fun _endingAt(): JsonField<OffsetDateTime> = endingAt

    /**
     * Returns the raw JSON value of [monthlyActiveUserCount].
     *
     * Unlike [monthlyActiveUserCount], this method doesn't throw if the JSON field has an
     * unexpected type.
     */
    @JsonProperty("monthly_active_user_count")
    @ExcludeMissing
    fun _monthlyActiveUserCount(): JsonField<Long> = monthlyActiveUserCount

    /**
     * Returns the raw JSON value of [monthlyAdoptionRate].
     *
     * Unlike [monthlyAdoptionRate], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("monthly_adoption_rate")
    @ExcludeMissing
    fun _monthlyAdoptionRate(): JsonField<Double> = monthlyAdoptionRate

    /**
     * Returns the raw JSON value of [pendingInviteCount].
     *
     * Unlike [pendingInviteCount], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("pending_invite_count")
    @ExcludeMissing
    fun _pendingInviteCount(): JsonField<Long> = pendingInviteCount

    /**
     * Returns the raw JSON value of [startingAt].
     *
     * Unlike [startingAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("starting_at")
    @ExcludeMissing
    fun _startingAt(): JsonField<OffsetDateTime> = startingAt

    /**
     * Returns the raw JSON value of [weeklyActiveUserCount].
     *
     * Unlike [weeklyActiveUserCount], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("weekly_active_user_count")
    @ExcludeMissing
    fun _weeklyActiveUserCount(): JsonField<Long> = weeklyActiveUserCount

    /**
     * Returns the raw JSON value of [weeklyAdoptionRate].
     *
     * Unlike [weeklyAdoptionRate], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("weekly_adoption_rate")
    @ExcludeMissing
    fun _weeklyAdoptionRate(): JsonField<Double> = weeklyAdoptionRate

    /**
     * Returns the raw JSON value of [chatCoworkUnifiedDailyActiveUserCount].
     *
     * Unlike [chatCoworkUnifiedDailyActiveUserCount], this method doesn't throw if the JSON field
     * has an unexpected type.
     */
    @JsonProperty("chat_cowork_unified_daily_active_user_count")
    @ExcludeMissing
    fun _chatCoworkUnifiedDailyActiveUserCount(): JsonField<Long> =
        chatCoworkUnifiedDailyActiveUserCount

    /**
     * Returns the raw JSON value of [chatCoworkUnifiedMonthlyActiveUserCount].
     *
     * Unlike [chatCoworkUnifiedMonthlyActiveUserCount], this method doesn't throw if the JSON field
     * has an unexpected type.
     */
    @JsonProperty("chat_cowork_unified_monthly_active_user_count")
    @ExcludeMissing
    fun _chatCoworkUnifiedMonthlyActiveUserCount(): JsonField<Long> =
        chatCoworkUnifiedMonthlyActiveUserCount

    /**
     * Returns the raw JSON value of [chatCoworkUnifiedWeeklyActiveUserCount].
     *
     * Unlike [chatCoworkUnifiedWeeklyActiveUserCount], this method doesn't throw if the JSON field
     * has an unexpected type.
     */
    @JsonProperty("chat_cowork_unified_weekly_active_user_count")
    @ExcludeMissing
    fun _chatCoworkUnifiedWeeklyActiveUserCount(): JsonField<Long> =
        chatCoworkUnifiedWeeklyActiveUserCount

    /**
     * Returns the raw JSON value of [chatDailyActiveUserCount].
     *
     * Unlike [chatDailyActiveUserCount], this method doesn't throw if the JSON field has an
     * unexpected type.
     */
    @JsonProperty("chat_daily_active_user_count")
    @ExcludeMissing
    fun _chatDailyActiveUserCount(): JsonField<Long> = chatDailyActiveUserCount

    /**
     * Returns the raw JSON value of [chatMonthlyActiveUserCount].
     *
     * Unlike [chatMonthlyActiveUserCount], this method doesn't throw if the JSON field has an
     * unexpected type.
     */
    @JsonProperty("chat_monthly_active_user_count")
    @ExcludeMissing
    fun _chatMonthlyActiveUserCount(): JsonField<Long> = chatMonthlyActiveUserCount

    /**
     * Returns the raw JSON value of [chatWeeklyActiveUserCount].
     *
     * Unlike [chatWeeklyActiveUserCount], this method doesn't throw if the JSON field has an
     * unexpected type.
     */
    @JsonProperty("chat_weekly_active_user_count")
    @ExcludeMissing
    fun _chatWeeklyActiveUserCount(): JsonField<Long> = chatWeeklyActiveUserCount

    /**
     * Returns the raw JSON value of [claudeCodeDailyActiveUserCount].
     *
     * Unlike [claudeCodeDailyActiveUserCount], this method doesn't throw if the JSON field has an
     * unexpected type.
     */
    @JsonProperty("claude_code_daily_active_user_count")
    @ExcludeMissing
    fun _claudeCodeDailyActiveUserCount(): JsonField<Long> = claudeCodeDailyActiveUserCount

    /**
     * Returns the raw JSON value of [claudeCodeMonthlyActiveUserCount].
     *
     * Unlike [claudeCodeMonthlyActiveUserCount], this method doesn't throw if the JSON field has an
     * unexpected type.
     */
    @JsonProperty("claude_code_monthly_active_user_count")
    @ExcludeMissing
    fun _claudeCodeMonthlyActiveUserCount(): JsonField<Long> = claudeCodeMonthlyActiveUserCount

    /**
     * Returns the raw JSON value of [claudeCodeWeeklyActiveUserCount].
     *
     * Unlike [claudeCodeWeeklyActiveUserCount], this method doesn't throw if the JSON field has an
     * unexpected type.
     */
    @JsonProperty("claude_code_weekly_active_user_count")
    @ExcludeMissing
    fun _claudeCodeWeeklyActiveUserCount(): JsonField<Long> = claudeCodeWeeklyActiveUserCount

    /**
     * Returns the raw JSON value of [claudeDesignDailyActiveUserCount].
     *
     * Unlike [claudeDesignDailyActiveUserCount], this method doesn't throw if the JSON field has an
     * unexpected type.
     */
    @JsonProperty("claude_design_daily_active_user_count")
    @ExcludeMissing
    fun _claudeDesignDailyActiveUserCount(): JsonField<Long> = claudeDesignDailyActiveUserCount

    /**
     * Returns the raw JSON value of [claudeDesignMonthlyActiveUserCount].
     *
     * Unlike [claudeDesignMonthlyActiveUserCount], this method doesn't throw if the JSON field has
     * an unexpected type.
     */
    @JsonProperty("claude_design_monthly_active_user_count")
    @ExcludeMissing
    fun _claudeDesignMonthlyActiveUserCount(): JsonField<Long> = claudeDesignMonthlyActiveUserCount

    /**
     * Returns the raw JSON value of [claudeDesignWeeklyActiveUserCount].
     *
     * Unlike [claudeDesignWeeklyActiveUserCount], this method doesn't throw if the JSON field has
     * an unexpected type.
     */
    @JsonProperty("claude_design_weekly_active_user_count")
    @ExcludeMissing
    fun _claudeDesignWeeklyActiveUserCount(): JsonField<Long> = claudeDesignWeeklyActiveUserCount

    /**
     * Returns the raw JSON value of [officeAgentDailyActiveUserCount].
     *
     * Unlike [officeAgentDailyActiveUserCount], this method doesn't throw if the JSON field has an
     * unexpected type.
     */
    @JsonProperty("office_agent_daily_active_user_count")
    @ExcludeMissing
    fun _officeAgentDailyActiveUserCount(): JsonField<Long> = officeAgentDailyActiveUserCount

    /**
     * Returns the raw JSON value of [officeAgentMonthlyActiveUserCount].
     *
     * Unlike [officeAgentMonthlyActiveUserCount], this method doesn't throw if the JSON field has
     * an unexpected type.
     */
    @JsonProperty("office_agent_monthly_active_user_count")
    @ExcludeMissing
    fun _officeAgentMonthlyActiveUserCount(): JsonField<Long> = officeAgentMonthlyActiveUserCount

    /**
     * Returns the raw JSON value of [officeAgentWeeklyActiveUserCount].
     *
     * Unlike [officeAgentWeeklyActiveUserCount], this method doesn't throw if the JSON field has an
     * unexpected type.
     */
    @JsonProperty("office_agent_weekly_active_user_count")
    @ExcludeMissing
    fun _officeAgentWeeklyActiveUserCount(): JsonField<Long> = officeAgentWeeklyActiveUserCount

    /**
     * Returns the raw JSON value of [scienceDailyActiveUserCount].
     *
     * Unlike [scienceDailyActiveUserCount], this method doesn't throw if the JSON field has an
     * unexpected type.
     */
    @JsonProperty("science_daily_active_user_count")
    @ExcludeMissing
    fun _scienceDailyActiveUserCount(): JsonField<Long> = scienceDailyActiveUserCount

    /**
     * Returns the raw JSON value of [scienceEntitledUserCount].
     *
     * Unlike [scienceEntitledUserCount], this method doesn't throw if the JSON field has an
     * unexpected type.
     */
    @JsonProperty("science_entitled_user_count")
    @ExcludeMissing
    fun _scienceEntitledUserCount(): JsonField<Long> = scienceEntitledUserCount

    /**
     * Returns the raw JSON value of [scienceMonthlyActiveUserCount].
     *
     * Unlike [scienceMonthlyActiveUserCount], this method doesn't throw if the JSON field has an
     * unexpected type.
     */
    @JsonProperty("science_monthly_active_user_count")
    @ExcludeMissing
    fun _scienceMonthlyActiveUserCount(): JsonField<Long> = scienceMonthlyActiveUserCount

    /**
     * Returns the raw JSON value of [scienceWeeklyActiveUserCount].
     *
     * Unlike [scienceWeeklyActiveUserCount], this method doesn't throw if the JSON field has an
     * unexpected type.
     */
    @JsonProperty("science_weekly_active_user_count")
    @ExcludeMissing
    fun _scienceWeeklyActiveUserCount(): JsonField<Long> = scienceWeeklyActiveUserCount

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
         * [BetaAnalyticsSingleDayActivitySummary].
         *
         * The following fields are required:
         * ```java
         * .assignedSeatCount()
         * .coworkDailyActiveUserCount()
         * .coworkMonthlyActiveUserCount()
         * .coworkWeeklyActiveUserCount()
         * .dailyActiveUserCount()
         * .dailyAdoptionRate()
         * .endingAt()
         * .monthlyActiveUserCount()
         * .monthlyAdoptionRate()
         * .pendingInviteCount()
         * .startingAt()
         * .weeklyActiveUserCount()
         * .weeklyAdoptionRate()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaAnalyticsSingleDayActivitySummary]. */
    class Builder internal constructor() {

        private var assignedSeatCount: JsonField<Long>? = null
        private var coworkDailyActiveUserCount: JsonField<Long>? = null
        private var coworkMonthlyActiveUserCount: JsonField<Long>? = null
        private var coworkWeeklyActiveUserCount: JsonField<Long>? = null
        private var dailyActiveUserCount: JsonField<Long>? = null
        private var dailyAdoptionRate: JsonField<Double>? = null
        private var endingAt: JsonField<OffsetDateTime>? = null
        private var monthlyActiveUserCount: JsonField<Long>? = null
        private var monthlyAdoptionRate: JsonField<Double>? = null
        private var pendingInviteCount: JsonField<Long>? = null
        private var startingAt: JsonField<OffsetDateTime>? = null
        private var weeklyActiveUserCount: JsonField<Long>? = null
        private var weeklyAdoptionRate: JsonField<Double>? = null
        private var chatCoworkUnifiedDailyActiveUserCount: JsonField<Long> = JsonMissing.of()
        private var chatCoworkUnifiedMonthlyActiveUserCount: JsonField<Long> = JsonMissing.of()
        private var chatCoworkUnifiedWeeklyActiveUserCount: JsonField<Long> = JsonMissing.of()
        private var chatDailyActiveUserCount: JsonField<Long> = JsonMissing.of()
        private var chatMonthlyActiveUserCount: JsonField<Long> = JsonMissing.of()
        private var chatWeeklyActiveUserCount: JsonField<Long> = JsonMissing.of()
        private var claudeCodeDailyActiveUserCount: JsonField<Long> = JsonMissing.of()
        private var claudeCodeMonthlyActiveUserCount: JsonField<Long> = JsonMissing.of()
        private var claudeCodeWeeklyActiveUserCount: JsonField<Long> = JsonMissing.of()
        private var claudeDesignDailyActiveUserCount: JsonField<Long> = JsonMissing.of()
        private var claudeDesignMonthlyActiveUserCount: JsonField<Long> = JsonMissing.of()
        private var claudeDesignWeeklyActiveUserCount: JsonField<Long> = JsonMissing.of()
        private var officeAgentDailyActiveUserCount: JsonField<Long> = JsonMissing.of()
        private var officeAgentMonthlyActiveUserCount: JsonField<Long> = JsonMissing.of()
        private var officeAgentWeeklyActiveUserCount: JsonField<Long> = JsonMissing.of()
        private var scienceDailyActiveUserCount: JsonField<Long> = JsonMissing.of()
        private var scienceEntitledUserCount: JsonField<Long> = JsonMissing.of()
        private var scienceMonthlyActiveUserCount: JsonField<Long> = JsonMissing.of()
        private var scienceWeeklyActiveUserCount: JsonField<Long> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaAnalyticsSingleDayActivitySummary: BetaAnalyticsSingleDayActivitySummary
        ) = apply {
            assignedSeatCount = betaAnalyticsSingleDayActivitySummary.assignedSeatCount
            coworkDailyActiveUserCount =
                betaAnalyticsSingleDayActivitySummary.coworkDailyActiveUserCount
            coworkMonthlyActiveUserCount =
                betaAnalyticsSingleDayActivitySummary.coworkMonthlyActiveUserCount
            coworkWeeklyActiveUserCount =
                betaAnalyticsSingleDayActivitySummary.coworkWeeklyActiveUserCount
            dailyActiveUserCount = betaAnalyticsSingleDayActivitySummary.dailyActiveUserCount
            dailyAdoptionRate = betaAnalyticsSingleDayActivitySummary.dailyAdoptionRate
            endingAt = betaAnalyticsSingleDayActivitySummary.endingAt
            monthlyActiveUserCount = betaAnalyticsSingleDayActivitySummary.monthlyActiveUserCount
            monthlyAdoptionRate = betaAnalyticsSingleDayActivitySummary.monthlyAdoptionRate
            pendingInviteCount = betaAnalyticsSingleDayActivitySummary.pendingInviteCount
            startingAt = betaAnalyticsSingleDayActivitySummary.startingAt
            weeklyActiveUserCount = betaAnalyticsSingleDayActivitySummary.weeklyActiveUserCount
            weeklyAdoptionRate = betaAnalyticsSingleDayActivitySummary.weeklyAdoptionRate
            chatCoworkUnifiedDailyActiveUserCount =
                betaAnalyticsSingleDayActivitySummary.chatCoworkUnifiedDailyActiveUserCount
            chatCoworkUnifiedMonthlyActiveUserCount =
                betaAnalyticsSingleDayActivitySummary.chatCoworkUnifiedMonthlyActiveUserCount
            chatCoworkUnifiedWeeklyActiveUserCount =
                betaAnalyticsSingleDayActivitySummary.chatCoworkUnifiedWeeklyActiveUserCount
            chatDailyActiveUserCount =
                betaAnalyticsSingleDayActivitySummary.chatDailyActiveUserCount
            chatMonthlyActiveUserCount =
                betaAnalyticsSingleDayActivitySummary.chatMonthlyActiveUserCount
            chatWeeklyActiveUserCount =
                betaAnalyticsSingleDayActivitySummary.chatWeeklyActiveUserCount
            claudeCodeDailyActiveUserCount =
                betaAnalyticsSingleDayActivitySummary.claudeCodeDailyActiveUserCount
            claudeCodeMonthlyActiveUserCount =
                betaAnalyticsSingleDayActivitySummary.claudeCodeMonthlyActiveUserCount
            claudeCodeWeeklyActiveUserCount =
                betaAnalyticsSingleDayActivitySummary.claudeCodeWeeklyActiveUserCount
            claudeDesignDailyActiveUserCount =
                betaAnalyticsSingleDayActivitySummary.claudeDesignDailyActiveUserCount
            claudeDesignMonthlyActiveUserCount =
                betaAnalyticsSingleDayActivitySummary.claudeDesignMonthlyActiveUserCount
            claudeDesignWeeklyActiveUserCount =
                betaAnalyticsSingleDayActivitySummary.claudeDesignWeeklyActiveUserCount
            officeAgentDailyActiveUserCount =
                betaAnalyticsSingleDayActivitySummary.officeAgentDailyActiveUserCount
            officeAgentMonthlyActiveUserCount =
                betaAnalyticsSingleDayActivitySummary.officeAgentMonthlyActiveUserCount
            officeAgentWeeklyActiveUserCount =
                betaAnalyticsSingleDayActivitySummary.officeAgentWeeklyActiveUserCount
            scienceDailyActiveUserCount =
                betaAnalyticsSingleDayActivitySummary.scienceDailyActiveUserCount
            scienceEntitledUserCount =
                betaAnalyticsSingleDayActivitySummary.scienceEntitledUserCount
            scienceMonthlyActiveUserCount =
                betaAnalyticsSingleDayActivitySummary.scienceMonthlyActiveUserCount
            scienceWeeklyActiveUserCount =
                betaAnalyticsSingleDayActivitySummary.scienceWeeklyActiveUserCount
            additionalProperties =
                betaAnalyticsSingleDayActivitySummary.additionalProperties.toMutableMap()
        }

        /**
         * Number of seats currently assigned to members. Null when the response is scoped to an
         * RBAC group — seat assignment is org-wide and has no per-group analogue.
         */
        fun assignedSeatCount(assignedSeatCount: Long?) =
            assignedSeatCount(JsonField.ofNullable(assignedSeatCount))

        /**
         * Alias for [Builder.assignedSeatCount].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun assignedSeatCount(assignedSeatCount: Long) =
            assignedSeatCount(assignedSeatCount as Long?)

        /** Alias for calling [Builder.assignedSeatCount] with `assignedSeatCount.orElse(null)`. */
        fun assignedSeatCount(assignedSeatCount: Optional<Long>) =
            assignedSeatCount(assignedSeatCount.getOrNull())

        /**
         * Sets [Builder.assignedSeatCount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.assignedSeatCount] with a well-typed [Long] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun assignedSeatCount(assignedSeatCount: JsonField<Long>) = apply {
            this.assignedSeatCount = assignedSeatCount
        }

        /** Number of users with Cowork activity on the requested day */
        fun coworkDailyActiveUserCount(coworkDailyActiveUserCount: Long) =
            coworkDailyActiveUserCount(JsonField.of(coworkDailyActiveUserCount))

        /**
         * Sets [Builder.coworkDailyActiveUserCount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.coworkDailyActiveUserCount] with a well-typed [Long]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun coworkDailyActiveUserCount(coworkDailyActiveUserCount: JsonField<Long>) = apply {
            this.coworkDailyActiveUserCount = coworkDailyActiveUserCount
        }

        /** Number of users with Cowork activity in the 30-day rolling window */
        fun coworkMonthlyActiveUserCount(coworkMonthlyActiveUserCount: Long) =
            coworkMonthlyActiveUserCount(JsonField.of(coworkMonthlyActiveUserCount))

        /**
         * Sets [Builder.coworkMonthlyActiveUserCount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.coworkMonthlyActiveUserCount] with a well-typed [Long]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun coworkMonthlyActiveUserCount(coworkMonthlyActiveUserCount: JsonField<Long>) = apply {
            this.coworkMonthlyActiveUserCount = coworkMonthlyActiveUserCount
        }

        /** Number of users with Cowork activity in the 7-day rolling window */
        fun coworkWeeklyActiveUserCount(coworkWeeklyActiveUserCount: Long) =
            coworkWeeklyActiveUserCount(JsonField.of(coworkWeeklyActiveUserCount))

        /**
         * Sets [Builder.coworkWeeklyActiveUserCount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.coworkWeeklyActiveUserCount] with a well-typed [Long]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun coworkWeeklyActiveUserCount(coworkWeeklyActiveUserCount: JsonField<Long>) = apply {
            this.coworkWeeklyActiveUserCount = coworkWeeklyActiveUserCount
        }

        /** Number of users with token consumption on the requested day */
        fun dailyActiveUserCount(dailyActiveUserCount: Long) =
            dailyActiveUserCount(JsonField.of(dailyActiveUserCount))

        /**
         * Sets [Builder.dailyActiveUserCount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.dailyActiveUserCount] with a well-typed [Long] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun dailyActiveUserCount(dailyActiveUserCount: JsonField<Long>) = apply {
            this.dailyActiveUserCount = dailyActiveUserCount
        }

        /**
         * Percentage of assigned seats with activity on the requested day (`DAU /
         * assigned_seat_count * 100`). Null when the response is scoped to an RBAC group.
         */
        fun dailyAdoptionRate(dailyAdoptionRate: Double?) =
            dailyAdoptionRate(JsonField.ofNullable(dailyAdoptionRate))

        /**
         * Alias for [Builder.dailyAdoptionRate].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun dailyAdoptionRate(dailyAdoptionRate: Double) =
            dailyAdoptionRate(dailyAdoptionRate as Double?)

        /** Alias for calling [Builder.dailyAdoptionRate] with `dailyAdoptionRate.orElse(null)`. */
        fun dailyAdoptionRate(dailyAdoptionRate: Optional<Double>) =
            dailyAdoptionRate(dailyAdoptionRate.getOrNull())

        /**
         * Sets [Builder.dailyAdoptionRate] to an arbitrary JSON value.
         *
         * You should usually call [Builder.dailyAdoptionRate] with a well-typed [Double] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun dailyAdoptionRate(dailyAdoptionRate: JsonField<Double>) = apply {
            this.dailyAdoptionRate = dailyAdoptionRate
        }

        /**
         * End of the aggregation period (exclusive), UTC midnight in RFC 3339 format (e.g.
         * `2026-01-16T00:00:00Z`).
         */
        fun endingAt(endingAt: OffsetDateTime) = endingAt(JsonField.of(endingAt))

        /**
         * Sets [Builder.endingAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.endingAt] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun endingAt(endingAt: JsonField<OffsetDateTime>) = apply { this.endingAt = endingAt }

        /** Number of users with token consumption in the 30-day rolling window */
        fun monthlyActiveUserCount(monthlyActiveUserCount: Long) =
            monthlyActiveUserCount(JsonField.of(monthlyActiveUserCount))

        /**
         * Sets [Builder.monthlyActiveUserCount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.monthlyActiveUserCount] with a well-typed [Long] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun monthlyActiveUserCount(monthlyActiveUserCount: JsonField<Long>) = apply {
            this.monthlyActiveUserCount = monthlyActiveUserCount
        }

        /**
         * Percentage of assigned seats with activity in the 30-day rolling window (`MAU /
         * assigned_seat_count * 100`). Null when the response is scoped to an RBAC group.
         */
        fun monthlyAdoptionRate(monthlyAdoptionRate: Double?) =
            monthlyAdoptionRate(JsonField.ofNullable(monthlyAdoptionRate))

        /**
         * Alias for [Builder.monthlyAdoptionRate].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun monthlyAdoptionRate(monthlyAdoptionRate: Double) =
            monthlyAdoptionRate(monthlyAdoptionRate as Double?)

        /**
         * Alias for calling [Builder.monthlyAdoptionRate] with `monthlyAdoptionRate.orElse(null)`.
         */
        fun monthlyAdoptionRate(monthlyAdoptionRate: Optional<Double>) =
            monthlyAdoptionRate(monthlyAdoptionRate.getOrNull())

        /**
         * Sets [Builder.monthlyAdoptionRate] to an arbitrary JSON value.
         *
         * You should usually call [Builder.monthlyAdoptionRate] with a well-typed [Double] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun monthlyAdoptionRate(monthlyAdoptionRate: JsonField<Double>) = apply {
            this.monthlyAdoptionRate = monthlyAdoptionRate
        }

        /**
         * Number of pending invitations to join the organization. Null when the response is scoped
         * to an RBAC group.
         */
        fun pendingInviteCount(pendingInviteCount: Long?) =
            pendingInviteCount(JsonField.ofNullable(pendingInviteCount))

        /**
         * Alias for [Builder.pendingInviteCount].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun pendingInviteCount(pendingInviteCount: Long) =
            pendingInviteCount(pendingInviteCount as Long?)

        /**
         * Alias for calling [Builder.pendingInviteCount] with `pendingInviteCount.orElse(null)`.
         */
        fun pendingInviteCount(pendingInviteCount: Optional<Long>) =
            pendingInviteCount(pendingInviteCount.getOrNull())

        /**
         * Sets [Builder.pendingInviteCount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.pendingInviteCount] with a well-typed [Long] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun pendingInviteCount(pendingInviteCount: JsonField<Long>) = apply {
            this.pendingInviteCount = pendingInviteCount
        }

        /**
         * Start of the aggregation period (inclusive), UTC midnight in RFC 3339 format (e.g.
         * `2026-01-15T00:00:00Z`).
         */
        fun startingAt(startingAt: OffsetDateTime) = startingAt(JsonField.of(startingAt))

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

        /** Number of users with token consumption in the 7-day rolling window */
        fun weeklyActiveUserCount(weeklyActiveUserCount: Long) =
            weeklyActiveUserCount(JsonField.of(weeklyActiveUserCount))

        /**
         * Sets [Builder.weeklyActiveUserCount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.weeklyActiveUserCount] with a well-typed [Long] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun weeklyActiveUserCount(weeklyActiveUserCount: JsonField<Long>) = apply {
            this.weeklyActiveUserCount = weeklyActiveUserCount
        }

        /**
         * Percentage of assigned seats with activity in the 7-day rolling window (`WAU /
         * assigned_seat_count * 100`). Null when the response is scoped to an RBAC group.
         */
        fun weeklyAdoptionRate(weeklyAdoptionRate: Double?) =
            weeklyAdoptionRate(JsonField.ofNullable(weeklyAdoptionRate))

        /**
         * Alias for [Builder.weeklyAdoptionRate].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun weeklyAdoptionRate(weeklyAdoptionRate: Double) =
            weeklyAdoptionRate(weeklyAdoptionRate as Double?)

        /**
         * Alias for calling [Builder.weeklyAdoptionRate] with `weeklyAdoptionRate.orElse(null)`.
         */
        fun weeklyAdoptionRate(weeklyAdoptionRate: Optional<Double>) =
            weeklyAdoptionRate(weeklyAdoptionRate.getOrNull())

        /**
         * Sets [Builder.weeklyAdoptionRate] to an arbitrary JSON value.
         *
         * You should usually call [Builder.weeklyAdoptionRate] with a well-typed [Double] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun weeklyAdoptionRate(weeklyAdoptionRate: JsonField<Double>) = apply {
            this.weeklyAdoptionRate = weeklyAdoptionRate
        }

        /**
         * Number of users with activity in Chat and Cowork unified on the requested day. Omitted
         * from the response on deployments that do not offer Chat and Cowork unified.
         */
        fun chatCoworkUnifiedDailyActiveUserCount(chatCoworkUnifiedDailyActiveUserCount: Long?) =
            chatCoworkUnifiedDailyActiveUserCount(
                JsonField.ofNullable(chatCoworkUnifiedDailyActiveUserCount)
            )

        /**
         * Alias for [Builder.chatCoworkUnifiedDailyActiveUserCount].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun chatCoworkUnifiedDailyActiveUserCount(chatCoworkUnifiedDailyActiveUserCount: Long) =
            chatCoworkUnifiedDailyActiveUserCount(chatCoworkUnifiedDailyActiveUserCount as Long?)

        /**
         * Alias for calling [Builder.chatCoworkUnifiedDailyActiveUserCount] with
         * `chatCoworkUnifiedDailyActiveUserCount.orElse(null)`.
         */
        fun chatCoworkUnifiedDailyActiveUserCount(
            chatCoworkUnifiedDailyActiveUserCount: Optional<Long>
        ) = chatCoworkUnifiedDailyActiveUserCount(chatCoworkUnifiedDailyActiveUserCount.getOrNull())

        /**
         * Sets [Builder.chatCoworkUnifiedDailyActiveUserCount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.chatCoworkUnifiedDailyActiveUserCount] with a well-typed
         * [Long] value instead. This method is primarily for setting the field to an undocumented
         * or not yet supported value.
         */
        fun chatCoworkUnifiedDailyActiveUserCount(
            chatCoworkUnifiedDailyActiveUserCount: JsonField<Long>
        ) = apply {
            this.chatCoworkUnifiedDailyActiveUserCount = chatCoworkUnifiedDailyActiveUserCount
        }

        /**
         * Number of users with activity in Chat and Cowork unified in the 28-day rolling window (30
         * days when the request filters by `rbac_group_id`). Omitted from the response on
         * deployments that do not offer Chat and Cowork unified.
         */
        fun chatCoworkUnifiedMonthlyActiveUserCount(
            chatCoworkUnifiedMonthlyActiveUserCount: Long?
        ) =
            chatCoworkUnifiedMonthlyActiveUserCount(
                JsonField.ofNullable(chatCoworkUnifiedMonthlyActiveUserCount)
            )

        /**
         * Alias for [Builder.chatCoworkUnifiedMonthlyActiveUserCount].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun chatCoworkUnifiedMonthlyActiveUserCount(chatCoworkUnifiedMonthlyActiveUserCount: Long) =
            chatCoworkUnifiedMonthlyActiveUserCount(
                chatCoworkUnifiedMonthlyActiveUserCount as Long?
            )

        /**
         * Alias for calling [Builder.chatCoworkUnifiedMonthlyActiveUserCount] with
         * `chatCoworkUnifiedMonthlyActiveUserCount.orElse(null)`.
         */
        fun chatCoworkUnifiedMonthlyActiveUserCount(
            chatCoworkUnifiedMonthlyActiveUserCount: Optional<Long>
        ) =
            chatCoworkUnifiedMonthlyActiveUserCount(
                chatCoworkUnifiedMonthlyActiveUserCount.getOrNull()
            )

        /**
         * Sets [Builder.chatCoworkUnifiedMonthlyActiveUserCount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.chatCoworkUnifiedMonthlyActiveUserCount] with a
         * well-typed [Long] value instead. This method is primarily for setting the field to an
         * undocumented or not yet supported value.
         */
        fun chatCoworkUnifiedMonthlyActiveUserCount(
            chatCoworkUnifiedMonthlyActiveUserCount: JsonField<Long>
        ) = apply {
            this.chatCoworkUnifiedMonthlyActiveUserCount = chatCoworkUnifiedMonthlyActiveUserCount
        }

        /**
         * Number of users with activity in Chat and Cowork unified in the 7-day rolling window.
         * Omitted from the response on deployments that do not offer Chat and Cowork unified.
         */
        fun chatCoworkUnifiedWeeklyActiveUserCount(chatCoworkUnifiedWeeklyActiveUserCount: Long?) =
            chatCoworkUnifiedWeeklyActiveUserCount(
                JsonField.ofNullable(chatCoworkUnifiedWeeklyActiveUserCount)
            )

        /**
         * Alias for [Builder.chatCoworkUnifiedWeeklyActiveUserCount].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun chatCoworkUnifiedWeeklyActiveUserCount(chatCoworkUnifiedWeeklyActiveUserCount: Long) =
            chatCoworkUnifiedWeeklyActiveUserCount(chatCoworkUnifiedWeeklyActiveUserCount as Long?)

        /**
         * Alias for calling [Builder.chatCoworkUnifiedWeeklyActiveUserCount] with
         * `chatCoworkUnifiedWeeklyActiveUserCount.orElse(null)`.
         */
        fun chatCoworkUnifiedWeeklyActiveUserCount(
            chatCoworkUnifiedWeeklyActiveUserCount: Optional<Long>
        ) =
            chatCoworkUnifiedWeeklyActiveUserCount(
                chatCoworkUnifiedWeeklyActiveUserCount.getOrNull()
            )

        /**
         * Sets [Builder.chatCoworkUnifiedWeeklyActiveUserCount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.chatCoworkUnifiedWeeklyActiveUserCount] with a
         * well-typed [Long] value instead. This method is primarily for setting the field to an
         * undocumented or not yet supported value.
         */
        fun chatCoworkUnifiedWeeklyActiveUserCount(
            chatCoworkUnifiedWeeklyActiveUserCount: JsonField<Long>
        ) = apply {
            this.chatCoworkUnifiedWeeklyActiveUserCount = chatCoworkUnifiedWeeklyActiveUserCount
        }

        /**
         * Number of users with claude.ai (chat) activity on the requested day. Omitted from the
         * response while the per-product breakdown is not enabled for this organization.
         */
        fun chatDailyActiveUserCount(chatDailyActiveUserCount: Long?) =
            chatDailyActiveUserCount(JsonField.ofNullable(chatDailyActiveUserCount))

        /**
         * Alias for [Builder.chatDailyActiveUserCount].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun chatDailyActiveUserCount(chatDailyActiveUserCount: Long) =
            chatDailyActiveUserCount(chatDailyActiveUserCount as Long?)

        /**
         * Alias for calling [Builder.chatDailyActiveUserCount] with
         * `chatDailyActiveUserCount.orElse(null)`.
         */
        fun chatDailyActiveUserCount(chatDailyActiveUserCount: Optional<Long>) =
            chatDailyActiveUserCount(chatDailyActiveUserCount.getOrNull())

        /**
         * Sets [Builder.chatDailyActiveUserCount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.chatDailyActiveUserCount] with a well-typed [Long] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun chatDailyActiveUserCount(chatDailyActiveUserCount: JsonField<Long>) = apply {
            this.chatDailyActiveUserCount = chatDailyActiveUserCount
        }

        /**
         * Number of users with claude.ai (chat) activity in the 30-day rolling window. Omitted from
         * the response while the per-product breakdown is not enabled for this organization.
         */
        fun chatMonthlyActiveUserCount(chatMonthlyActiveUserCount: Long?) =
            chatMonthlyActiveUserCount(JsonField.ofNullable(chatMonthlyActiveUserCount))

        /**
         * Alias for [Builder.chatMonthlyActiveUserCount].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun chatMonthlyActiveUserCount(chatMonthlyActiveUserCount: Long) =
            chatMonthlyActiveUserCount(chatMonthlyActiveUserCount as Long?)

        /**
         * Alias for calling [Builder.chatMonthlyActiveUserCount] with
         * `chatMonthlyActiveUserCount.orElse(null)`.
         */
        fun chatMonthlyActiveUserCount(chatMonthlyActiveUserCount: Optional<Long>) =
            chatMonthlyActiveUserCount(chatMonthlyActiveUserCount.getOrNull())

        /**
         * Sets [Builder.chatMonthlyActiveUserCount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.chatMonthlyActiveUserCount] with a well-typed [Long]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun chatMonthlyActiveUserCount(chatMonthlyActiveUserCount: JsonField<Long>) = apply {
            this.chatMonthlyActiveUserCount = chatMonthlyActiveUserCount
        }

        /**
         * Number of users with claude.ai (chat) activity in the 7-day rolling window. Omitted from
         * the response while the per-product breakdown is not enabled for this organization.
         */
        fun chatWeeklyActiveUserCount(chatWeeklyActiveUserCount: Long?) =
            chatWeeklyActiveUserCount(JsonField.ofNullable(chatWeeklyActiveUserCount))

        /**
         * Alias for [Builder.chatWeeklyActiveUserCount].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun chatWeeklyActiveUserCount(chatWeeklyActiveUserCount: Long) =
            chatWeeklyActiveUserCount(chatWeeklyActiveUserCount as Long?)

        /**
         * Alias for calling [Builder.chatWeeklyActiveUserCount] with
         * `chatWeeklyActiveUserCount.orElse(null)`.
         */
        fun chatWeeklyActiveUserCount(chatWeeklyActiveUserCount: Optional<Long>) =
            chatWeeklyActiveUserCount(chatWeeklyActiveUserCount.getOrNull())

        /**
         * Sets [Builder.chatWeeklyActiveUserCount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.chatWeeklyActiveUserCount] with a well-typed [Long]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun chatWeeklyActiveUserCount(chatWeeklyActiveUserCount: JsonField<Long>) = apply {
            this.chatWeeklyActiveUserCount = chatWeeklyActiveUserCount
        }

        /**
         * Number of users with Claude Code activity on the requested day. Omitted from the response
         * while the per-product breakdown is not enabled for this organization.
         */
        fun claudeCodeDailyActiveUserCount(claudeCodeDailyActiveUserCount: Long?) =
            claudeCodeDailyActiveUserCount(JsonField.ofNullable(claudeCodeDailyActiveUserCount))

        /**
         * Alias for [Builder.claudeCodeDailyActiveUserCount].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun claudeCodeDailyActiveUserCount(claudeCodeDailyActiveUserCount: Long) =
            claudeCodeDailyActiveUserCount(claudeCodeDailyActiveUserCount as Long?)

        /**
         * Alias for calling [Builder.claudeCodeDailyActiveUserCount] with
         * `claudeCodeDailyActiveUserCount.orElse(null)`.
         */
        fun claudeCodeDailyActiveUserCount(claudeCodeDailyActiveUserCount: Optional<Long>) =
            claudeCodeDailyActiveUserCount(claudeCodeDailyActiveUserCount.getOrNull())

        /**
         * Sets [Builder.claudeCodeDailyActiveUserCount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.claudeCodeDailyActiveUserCount] with a well-typed [Long]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun claudeCodeDailyActiveUserCount(claudeCodeDailyActiveUserCount: JsonField<Long>) =
            apply {
                this.claudeCodeDailyActiveUserCount = claudeCodeDailyActiveUserCount
            }

        /**
         * Number of users with Claude Code activity in the 30-day rolling window. Omitted from the
         * response while the per-product breakdown is not enabled for this organization.
         */
        fun claudeCodeMonthlyActiveUserCount(claudeCodeMonthlyActiveUserCount: Long?) =
            claudeCodeMonthlyActiveUserCount(JsonField.ofNullable(claudeCodeMonthlyActiveUserCount))

        /**
         * Alias for [Builder.claudeCodeMonthlyActiveUserCount].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun claudeCodeMonthlyActiveUserCount(claudeCodeMonthlyActiveUserCount: Long) =
            claudeCodeMonthlyActiveUserCount(claudeCodeMonthlyActiveUserCount as Long?)

        /**
         * Alias for calling [Builder.claudeCodeMonthlyActiveUserCount] with
         * `claudeCodeMonthlyActiveUserCount.orElse(null)`.
         */
        fun claudeCodeMonthlyActiveUserCount(claudeCodeMonthlyActiveUserCount: Optional<Long>) =
            claudeCodeMonthlyActiveUserCount(claudeCodeMonthlyActiveUserCount.getOrNull())

        /**
         * Sets [Builder.claudeCodeMonthlyActiveUserCount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.claudeCodeMonthlyActiveUserCount] with a well-typed
         * [Long] value instead. This method is primarily for setting the field to an undocumented
         * or not yet supported value.
         */
        fun claudeCodeMonthlyActiveUserCount(claudeCodeMonthlyActiveUserCount: JsonField<Long>) =
            apply {
                this.claudeCodeMonthlyActiveUserCount = claudeCodeMonthlyActiveUserCount
            }

        /**
         * Number of users with Claude Code activity in the 7-day rolling window. Omitted from the
         * response while the per-product breakdown is not enabled for this organization.
         */
        fun claudeCodeWeeklyActiveUserCount(claudeCodeWeeklyActiveUserCount: Long?) =
            claudeCodeWeeklyActiveUserCount(JsonField.ofNullable(claudeCodeWeeklyActiveUserCount))

        /**
         * Alias for [Builder.claudeCodeWeeklyActiveUserCount].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun claudeCodeWeeklyActiveUserCount(claudeCodeWeeklyActiveUserCount: Long) =
            claudeCodeWeeklyActiveUserCount(claudeCodeWeeklyActiveUserCount as Long?)

        /**
         * Alias for calling [Builder.claudeCodeWeeklyActiveUserCount] with
         * `claudeCodeWeeklyActiveUserCount.orElse(null)`.
         */
        fun claudeCodeWeeklyActiveUserCount(claudeCodeWeeklyActiveUserCount: Optional<Long>) =
            claudeCodeWeeklyActiveUserCount(claudeCodeWeeklyActiveUserCount.getOrNull())

        /**
         * Sets [Builder.claudeCodeWeeklyActiveUserCount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.claudeCodeWeeklyActiveUserCount] with a well-typed
         * [Long] value instead. This method is primarily for setting the field to an undocumented
         * or not yet supported value.
         */
        fun claudeCodeWeeklyActiveUserCount(claudeCodeWeeklyActiveUserCount: JsonField<Long>) =
            apply {
                this.claudeCodeWeeklyActiveUserCount = claudeCodeWeeklyActiveUserCount
            }

        /**
         * Number of users with Claude Design activity on the requested day. Omitted from the
         * response while the per-product breakdown is not enabled for this organization.
         */
        fun claudeDesignDailyActiveUserCount(claudeDesignDailyActiveUserCount: Long?) =
            claudeDesignDailyActiveUserCount(JsonField.ofNullable(claudeDesignDailyActiveUserCount))

        /**
         * Alias for [Builder.claudeDesignDailyActiveUserCount].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun claudeDesignDailyActiveUserCount(claudeDesignDailyActiveUserCount: Long) =
            claudeDesignDailyActiveUserCount(claudeDesignDailyActiveUserCount as Long?)

        /**
         * Alias for calling [Builder.claudeDesignDailyActiveUserCount] with
         * `claudeDesignDailyActiveUserCount.orElse(null)`.
         */
        fun claudeDesignDailyActiveUserCount(claudeDesignDailyActiveUserCount: Optional<Long>) =
            claudeDesignDailyActiveUserCount(claudeDesignDailyActiveUserCount.getOrNull())

        /**
         * Sets [Builder.claudeDesignDailyActiveUserCount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.claudeDesignDailyActiveUserCount] with a well-typed
         * [Long] value instead. This method is primarily for setting the field to an undocumented
         * or not yet supported value.
         */
        fun claudeDesignDailyActiveUserCount(claudeDesignDailyActiveUserCount: JsonField<Long>) =
            apply {
                this.claudeDesignDailyActiveUserCount = claudeDesignDailyActiveUserCount
            }

        /**
         * Number of users with Claude Design activity in the 30-day rolling window. Omitted from
         * the response while the per-product breakdown is not enabled for this organization.
         */
        fun claudeDesignMonthlyActiveUserCount(claudeDesignMonthlyActiveUserCount: Long?) =
            claudeDesignMonthlyActiveUserCount(
                JsonField.ofNullable(claudeDesignMonthlyActiveUserCount)
            )

        /**
         * Alias for [Builder.claudeDesignMonthlyActiveUserCount].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun claudeDesignMonthlyActiveUserCount(claudeDesignMonthlyActiveUserCount: Long) =
            claudeDesignMonthlyActiveUserCount(claudeDesignMonthlyActiveUserCount as Long?)

        /**
         * Alias for calling [Builder.claudeDesignMonthlyActiveUserCount] with
         * `claudeDesignMonthlyActiveUserCount.orElse(null)`.
         */
        fun claudeDesignMonthlyActiveUserCount(claudeDesignMonthlyActiveUserCount: Optional<Long>) =
            claudeDesignMonthlyActiveUserCount(claudeDesignMonthlyActiveUserCount.getOrNull())

        /**
         * Sets [Builder.claudeDesignMonthlyActiveUserCount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.claudeDesignMonthlyActiveUserCount] with a well-typed
         * [Long] value instead. This method is primarily for setting the field to an undocumented
         * or not yet supported value.
         */
        fun claudeDesignMonthlyActiveUserCount(
            claudeDesignMonthlyActiveUserCount: JsonField<Long>
        ) = apply { this.claudeDesignMonthlyActiveUserCount = claudeDesignMonthlyActiveUserCount }

        /**
         * Number of users with Claude Design activity in the 7-day rolling window. Omitted from the
         * response while the per-product breakdown is not enabled for this organization.
         */
        fun claudeDesignWeeklyActiveUserCount(claudeDesignWeeklyActiveUserCount: Long?) =
            claudeDesignWeeklyActiveUserCount(
                JsonField.ofNullable(claudeDesignWeeklyActiveUserCount)
            )

        /**
         * Alias for [Builder.claudeDesignWeeklyActiveUserCount].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun claudeDesignWeeklyActiveUserCount(claudeDesignWeeklyActiveUserCount: Long) =
            claudeDesignWeeklyActiveUserCount(claudeDesignWeeklyActiveUserCount as Long?)

        /**
         * Alias for calling [Builder.claudeDesignWeeklyActiveUserCount] with
         * `claudeDesignWeeklyActiveUserCount.orElse(null)`.
         */
        fun claudeDesignWeeklyActiveUserCount(claudeDesignWeeklyActiveUserCount: Optional<Long>) =
            claudeDesignWeeklyActiveUserCount(claudeDesignWeeklyActiveUserCount.getOrNull())

        /**
         * Sets [Builder.claudeDesignWeeklyActiveUserCount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.claudeDesignWeeklyActiveUserCount] with a well-typed
         * [Long] value instead. This method is primarily for setting the field to an undocumented
         * or not yet supported value.
         */
        fun claudeDesignWeeklyActiveUserCount(claudeDesignWeeklyActiveUserCount: JsonField<Long>) =
            apply {
                this.claudeDesignWeeklyActiveUserCount = claudeDesignWeeklyActiveUserCount
            }

        /**
         * Number of users with Claude in Office activity on the requested day. Omitted from the
         * response while the per-product breakdown is not enabled for this organization.
         */
        fun officeAgentDailyActiveUserCount(officeAgentDailyActiveUserCount: Long?) =
            officeAgentDailyActiveUserCount(JsonField.ofNullable(officeAgentDailyActiveUserCount))

        /**
         * Alias for [Builder.officeAgentDailyActiveUserCount].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun officeAgentDailyActiveUserCount(officeAgentDailyActiveUserCount: Long) =
            officeAgentDailyActiveUserCount(officeAgentDailyActiveUserCount as Long?)

        /**
         * Alias for calling [Builder.officeAgentDailyActiveUserCount] with
         * `officeAgentDailyActiveUserCount.orElse(null)`.
         */
        fun officeAgentDailyActiveUserCount(officeAgentDailyActiveUserCount: Optional<Long>) =
            officeAgentDailyActiveUserCount(officeAgentDailyActiveUserCount.getOrNull())

        /**
         * Sets [Builder.officeAgentDailyActiveUserCount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.officeAgentDailyActiveUserCount] with a well-typed
         * [Long] value instead. This method is primarily for setting the field to an undocumented
         * or not yet supported value.
         */
        fun officeAgentDailyActiveUserCount(officeAgentDailyActiveUserCount: JsonField<Long>) =
            apply {
                this.officeAgentDailyActiveUserCount = officeAgentDailyActiveUserCount
            }

        /**
         * Number of users with Claude in Office activity in the 30-day rolling window. Omitted from
         * the response while the per-product breakdown is not enabled for this organization.
         */
        fun officeAgentMonthlyActiveUserCount(officeAgentMonthlyActiveUserCount: Long?) =
            officeAgentMonthlyActiveUserCount(
                JsonField.ofNullable(officeAgentMonthlyActiveUserCount)
            )

        /**
         * Alias for [Builder.officeAgentMonthlyActiveUserCount].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun officeAgentMonthlyActiveUserCount(officeAgentMonthlyActiveUserCount: Long) =
            officeAgentMonthlyActiveUserCount(officeAgentMonthlyActiveUserCount as Long?)

        /**
         * Alias for calling [Builder.officeAgentMonthlyActiveUserCount] with
         * `officeAgentMonthlyActiveUserCount.orElse(null)`.
         */
        fun officeAgentMonthlyActiveUserCount(officeAgentMonthlyActiveUserCount: Optional<Long>) =
            officeAgentMonthlyActiveUserCount(officeAgentMonthlyActiveUserCount.getOrNull())

        /**
         * Sets [Builder.officeAgentMonthlyActiveUserCount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.officeAgentMonthlyActiveUserCount] with a well-typed
         * [Long] value instead. This method is primarily for setting the field to an undocumented
         * or not yet supported value.
         */
        fun officeAgentMonthlyActiveUserCount(officeAgentMonthlyActiveUserCount: JsonField<Long>) =
            apply {
                this.officeAgentMonthlyActiveUserCount = officeAgentMonthlyActiveUserCount
            }

        /**
         * Number of users with Claude in Office activity in the 7-day rolling window. Omitted from
         * the response while the per-product breakdown is not enabled for this organization.
         */
        fun officeAgentWeeklyActiveUserCount(officeAgentWeeklyActiveUserCount: Long?) =
            officeAgentWeeklyActiveUserCount(JsonField.ofNullable(officeAgentWeeklyActiveUserCount))

        /**
         * Alias for [Builder.officeAgentWeeklyActiveUserCount].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun officeAgentWeeklyActiveUserCount(officeAgentWeeklyActiveUserCount: Long) =
            officeAgentWeeklyActiveUserCount(officeAgentWeeklyActiveUserCount as Long?)

        /**
         * Alias for calling [Builder.officeAgentWeeklyActiveUserCount] with
         * `officeAgentWeeklyActiveUserCount.orElse(null)`.
         */
        fun officeAgentWeeklyActiveUserCount(officeAgentWeeklyActiveUserCount: Optional<Long>) =
            officeAgentWeeklyActiveUserCount(officeAgentWeeklyActiveUserCount.getOrNull())

        /**
         * Sets [Builder.officeAgentWeeklyActiveUserCount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.officeAgentWeeklyActiveUserCount] with a well-typed
         * [Long] value instead. This method is primarily for setting the field to an undocumented
         * or not yet supported value.
         */
        fun officeAgentWeeklyActiveUserCount(officeAgentWeeklyActiveUserCount: JsonField<Long>) =
            apply {
                this.officeAgentWeeklyActiveUserCount = officeAgentWeeklyActiveUserCount
            }

        /**
         * Number of users with Claude Science activity on the requested day. Omitted from the
         * response while the per-product breakdown is not enabled for this organization.
         */
        fun scienceDailyActiveUserCount(scienceDailyActiveUserCount: Long?) =
            scienceDailyActiveUserCount(JsonField.ofNullable(scienceDailyActiveUserCount))

        /**
         * Alias for [Builder.scienceDailyActiveUserCount].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun scienceDailyActiveUserCount(scienceDailyActiveUserCount: Long) =
            scienceDailyActiveUserCount(scienceDailyActiveUserCount as Long?)

        /**
         * Alias for calling [Builder.scienceDailyActiveUserCount] with
         * `scienceDailyActiveUserCount.orElse(null)`.
         */
        fun scienceDailyActiveUserCount(scienceDailyActiveUserCount: Optional<Long>) =
            scienceDailyActiveUserCount(scienceDailyActiveUserCount.getOrNull())

        /**
         * Sets [Builder.scienceDailyActiveUserCount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.scienceDailyActiveUserCount] with a well-typed [Long]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun scienceDailyActiveUserCount(scienceDailyActiveUserCount: JsonField<Long>) = apply {
            this.scienceDailyActiveUserCount = scienceDailyActiveUserCount
        }

        /**
         * Number of users with a Claude Science seat entitlement (per-seat RBAC) at the time of the
         * daily snapshot. The funnel top; independent of the org-level Claude Science toggle. Null
         * when the response is scoped to an RBAC group — entitlement is org-wide and has no
         * per-group analogue. Omitted from the response while the per-product breakdown is not
         * enabled for this organization.
         */
        fun scienceEntitledUserCount(scienceEntitledUserCount: Long?) =
            scienceEntitledUserCount(JsonField.ofNullable(scienceEntitledUserCount))

        /**
         * Alias for [Builder.scienceEntitledUserCount].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun scienceEntitledUserCount(scienceEntitledUserCount: Long) =
            scienceEntitledUserCount(scienceEntitledUserCount as Long?)

        /**
         * Alias for calling [Builder.scienceEntitledUserCount] with
         * `scienceEntitledUserCount.orElse(null)`.
         */
        fun scienceEntitledUserCount(scienceEntitledUserCount: Optional<Long>) =
            scienceEntitledUserCount(scienceEntitledUserCount.getOrNull())

        /**
         * Sets [Builder.scienceEntitledUserCount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.scienceEntitledUserCount] with a well-typed [Long] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun scienceEntitledUserCount(scienceEntitledUserCount: JsonField<Long>) = apply {
            this.scienceEntitledUserCount = scienceEntitledUserCount
        }

        /**
         * Number of users with Claude Science activity in the 30-day rolling window. Omitted from
         * the response while the per-product breakdown is not enabled for this organization.
         */
        fun scienceMonthlyActiveUserCount(scienceMonthlyActiveUserCount: Long?) =
            scienceMonthlyActiveUserCount(JsonField.ofNullable(scienceMonthlyActiveUserCount))

        /**
         * Alias for [Builder.scienceMonthlyActiveUserCount].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun scienceMonthlyActiveUserCount(scienceMonthlyActiveUserCount: Long) =
            scienceMonthlyActiveUserCount(scienceMonthlyActiveUserCount as Long?)

        /**
         * Alias for calling [Builder.scienceMonthlyActiveUserCount] with
         * `scienceMonthlyActiveUserCount.orElse(null)`.
         */
        fun scienceMonthlyActiveUserCount(scienceMonthlyActiveUserCount: Optional<Long>) =
            scienceMonthlyActiveUserCount(scienceMonthlyActiveUserCount.getOrNull())

        /**
         * Sets [Builder.scienceMonthlyActiveUserCount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.scienceMonthlyActiveUserCount] with a well-typed [Long]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun scienceMonthlyActiveUserCount(scienceMonthlyActiveUserCount: JsonField<Long>) = apply {
            this.scienceMonthlyActiveUserCount = scienceMonthlyActiveUserCount
        }

        /**
         * Number of users with Claude Science activity in the 7-day rolling window. Omitted from
         * the response while the per-product breakdown is not enabled for this organization.
         */
        fun scienceWeeklyActiveUserCount(scienceWeeklyActiveUserCount: Long?) =
            scienceWeeklyActiveUserCount(JsonField.ofNullable(scienceWeeklyActiveUserCount))

        /**
         * Alias for [Builder.scienceWeeklyActiveUserCount].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun scienceWeeklyActiveUserCount(scienceWeeklyActiveUserCount: Long) =
            scienceWeeklyActiveUserCount(scienceWeeklyActiveUserCount as Long?)

        /**
         * Alias for calling [Builder.scienceWeeklyActiveUserCount] with
         * `scienceWeeklyActiveUserCount.orElse(null)`.
         */
        fun scienceWeeklyActiveUserCount(scienceWeeklyActiveUserCount: Optional<Long>) =
            scienceWeeklyActiveUserCount(scienceWeeklyActiveUserCount.getOrNull())

        /**
         * Sets [Builder.scienceWeeklyActiveUserCount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.scienceWeeklyActiveUserCount] with a well-typed [Long]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun scienceWeeklyActiveUserCount(scienceWeeklyActiveUserCount: JsonField<Long>) = apply {
            this.scienceWeeklyActiveUserCount = scienceWeeklyActiveUserCount
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
         * Returns an immutable instance of [BetaAnalyticsSingleDayActivitySummary].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .assignedSeatCount()
         * .coworkDailyActiveUserCount()
         * .coworkMonthlyActiveUserCount()
         * .coworkWeeklyActiveUserCount()
         * .dailyActiveUserCount()
         * .dailyAdoptionRate()
         * .endingAt()
         * .monthlyActiveUserCount()
         * .monthlyAdoptionRate()
         * .pendingInviteCount()
         * .startingAt()
         * .weeklyActiveUserCount()
         * .weeklyAdoptionRate()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaAnalyticsSingleDayActivitySummary =
            BetaAnalyticsSingleDayActivitySummary(
                checkRequired("assignedSeatCount", assignedSeatCount),
                checkRequired("coworkDailyActiveUserCount", coworkDailyActiveUserCount),
                checkRequired("coworkMonthlyActiveUserCount", coworkMonthlyActiveUserCount),
                checkRequired("coworkWeeklyActiveUserCount", coworkWeeklyActiveUserCount),
                checkRequired("dailyActiveUserCount", dailyActiveUserCount),
                checkRequired("dailyAdoptionRate", dailyAdoptionRate),
                checkRequired("endingAt", endingAt),
                checkRequired("monthlyActiveUserCount", monthlyActiveUserCount),
                checkRequired("monthlyAdoptionRate", monthlyAdoptionRate),
                checkRequired("pendingInviteCount", pendingInviteCount),
                checkRequired("startingAt", startingAt),
                checkRequired("weeklyActiveUserCount", weeklyActiveUserCount),
                checkRequired("weeklyAdoptionRate", weeklyAdoptionRate),
                chatCoworkUnifiedDailyActiveUserCount,
                chatCoworkUnifiedMonthlyActiveUserCount,
                chatCoworkUnifiedWeeklyActiveUserCount,
                chatDailyActiveUserCount,
                chatMonthlyActiveUserCount,
                chatWeeklyActiveUserCount,
                claudeCodeDailyActiveUserCount,
                claudeCodeMonthlyActiveUserCount,
                claudeCodeWeeklyActiveUserCount,
                claudeDesignDailyActiveUserCount,
                claudeDesignMonthlyActiveUserCount,
                claudeDesignWeeklyActiveUserCount,
                officeAgentDailyActiveUserCount,
                officeAgentMonthlyActiveUserCount,
                officeAgentWeeklyActiveUserCount,
                scienceDailyActiveUserCount,
                scienceEntitledUserCount,
                scienceMonthlyActiveUserCount,
                scienceWeeklyActiveUserCount,
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
    fun validate(): BetaAnalyticsSingleDayActivitySummary = apply {
        if (validated) {
            return@apply
        }

        assignedSeatCount()
        coworkDailyActiveUserCount()
        coworkMonthlyActiveUserCount()
        coworkWeeklyActiveUserCount()
        dailyActiveUserCount()
        dailyAdoptionRate()
        endingAt()
        monthlyActiveUserCount()
        monthlyAdoptionRate()
        pendingInviteCount()
        startingAt()
        weeklyActiveUserCount()
        weeklyAdoptionRate()
        chatCoworkUnifiedDailyActiveUserCount()
        chatCoworkUnifiedMonthlyActiveUserCount()
        chatCoworkUnifiedWeeklyActiveUserCount()
        chatDailyActiveUserCount()
        chatMonthlyActiveUserCount()
        chatWeeklyActiveUserCount()
        claudeCodeDailyActiveUserCount()
        claudeCodeMonthlyActiveUserCount()
        claudeCodeWeeklyActiveUserCount()
        claudeDesignDailyActiveUserCount()
        claudeDesignMonthlyActiveUserCount()
        claudeDesignWeeklyActiveUserCount()
        officeAgentDailyActiveUserCount()
        officeAgentMonthlyActiveUserCount()
        officeAgentWeeklyActiveUserCount()
        scienceDailyActiveUserCount()
        scienceEntitledUserCount()
        scienceMonthlyActiveUserCount()
        scienceWeeklyActiveUserCount()
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
        (if (assignedSeatCount.asKnown().isPresent) 1 else 0) +
            (if (coworkDailyActiveUserCount.asKnown().isPresent) 1 else 0) +
            (if (coworkMonthlyActiveUserCount.asKnown().isPresent) 1 else 0) +
            (if (coworkWeeklyActiveUserCount.asKnown().isPresent) 1 else 0) +
            (if (dailyActiveUserCount.asKnown().isPresent) 1 else 0) +
            (if (dailyAdoptionRate.asKnown().isPresent) 1 else 0) +
            (if (endingAt.asKnown().isPresent) 1 else 0) +
            (if (monthlyActiveUserCount.asKnown().isPresent) 1 else 0) +
            (if (monthlyAdoptionRate.asKnown().isPresent) 1 else 0) +
            (if (pendingInviteCount.asKnown().isPresent) 1 else 0) +
            (if (startingAt.asKnown().isPresent) 1 else 0) +
            (if (weeklyActiveUserCount.asKnown().isPresent) 1 else 0) +
            (if (weeklyAdoptionRate.asKnown().isPresent) 1 else 0) +
            (if (chatCoworkUnifiedDailyActiveUserCount.asKnown().isPresent) 1 else 0) +
            (if (chatCoworkUnifiedMonthlyActiveUserCount.asKnown().isPresent) 1 else 0) +
            (if (chatCoworkUnifiedWeeklyActiveUserCount.asKnown().isPresent) 1 else 0) +
            (if (chatDailyActiveUserCount.asKnown().isPresent) 1 else 0) +
            (if (chatMonthlyActiveUserCount.asKnown().isPresent) 1 else 0) +
            (if (chatWeeklyActiveUserCount.asKnown().isPresent) 1 else 0) +
            (if (claudeCodeDailyActiveUserCount.asKnown().isPresent) 1 else 0) +
            (if (claudeCodeMonthlyActiveUserCount.asKnown().isPresent) 1 else 0) +
            (if (claudeCodeWeeklyActiveUserCount.asKnown().isPresent) 1 else 0) +
            (if (claudeDesignDailyActiveUserCount.asKnown().isPresent) 1 else 0) +
            (if (claudeDesignMonthlyActiveUserCount.asKnown().isPresent) 1 else 0) +
            (if (claudeDesignWeeklyActiveUserCount.asKnown().isPresent) 1 else 0) +
            (if (officeAgentDailyActiveUserCount.asKnown().isPresent) 1 else 0) +
            (if (officeAgentMonthlyActiveUserCount.asKnown().isPresent) 1 else 0) +
            (if (officeAgentWeeklyActiveUserCount.asKnown().isPresent) 1 else 0) +
            (if (scienceDailyActiveUserCount.asKnown().isPresent) 1 else 0) +
            (if (scienceEntitledUserCount.asKnown().isPresent) 1 else 0) +
            (if (scienceMonthlyActiveUserCount.asKnown().isPresent) 1 else 0) +
            (if (scienceWeeklyActiveUserCount.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaAnalyticsSingleDayActivitySummary &&
            assignedSeatCount == other.assignedSeatCount &&
            coworkDailyActiveUserCount == other.coworkDailyActiveUserCount &&
            coworkMonthlyActiveUserCount == other.coworkMonthlyActiveUserCount &&
            coworkWeeklyActiveUserCount == other.coworkWeeklyActiveUserCount &&
            dailyActiveUserCount == other.dailyActiveUserCount &&
            dailyAdoptionRate == other.dailyAdoptionRate &&
            endingAt == other.endingAt &&
            monthlyActiveUserCount == other.monthlyActiveUserCount &&
            monthlyAdoptionRate == other.monthlyAdoptionRate &&
            pendingInviteCount == other.pendingInviteCount &&
            startingAt == other.startingAt &&
            weeklyActiveUserCount == other.weeklyActiveUserCount &&
            weeklyAdoptionRate == other.weeklyAdoptionRate &&
            chatCoworkUnifiedDailyActiveUserCount == other.chatCoworkUnifiedDailyActiveUserCount &&
            chatCoworkUnifiedMonthlyActiveUserCount ==
                other.chatCoworkUnifiedMonthlyActiveUserCount &&
            chatCoworkUnifiedWeeklyActiveUserCount ==
                other.chatCoworkUnifiedWeeklyActiveUserCount &&
            chatDailyActiveUserCount == other.chatDailyActiveUserCount &&
            chatMonthlyActiveUserCount == other.chatMonthlyActiveUserCount &&
            chatWeeklyActiveUserCount == other.chatWeeklyActiveUserCount &&
            claudeCodeDailyActiveUserCount == other.claudeCodeDailyActiveUserCount &&
            claudeCodeMonthlyActiveUserCount == other.claudeCodeMonthlyActiveUserCount &&
            claudeCodeWeeklyActiveUserCount == other.claudeCodeWeeklyActiveUserCount &&
            claudeDesignDailyActiveUserCount == other.claudeDesignDailyActiveUserCount &&
            claudeDesignMonthlyActiveUserCount == other.claudeDesignMonthlyActiveUserCount &&
            claudeDesignWeeklyActiveUserCount == other.claudeDesignWeeklyActiveUserCount &&
            officeAgentDailyActiveUserCount == other.officeAgentDailyActiveUserCount &&
            officeAgentMonthlyActiveUserCount == other.officeAgentMonthlyActiveUserCount &&
            officeAgentWeeklyActiveUserCount == other.officeAgentWeeklyActiveUserCount &&
            scienceDailyActiveUserCount == other.scienceDailyActiveUserCount &&
            scienceEntitledUserCount == other.scienceEntitledUserCount &&
            scienceMonthlyActiveUserCount == other.scienceMonthlyActiveUserCount &&
            scienceWeeklyActiveUserCount == other.scienceWeeklyActiveUserCount &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            assignedSeatCount,
            coworkDailyActiveUserCount,
            coworkMonthlyActiveUserCount,
            coworkWeeklyActiveUserCount,
            dailyActiveUserCount,
            dailyAdoptionRate,
            endingAt,
            monthlyActiveUserCount,
            monthlyAdoptionRate,
            pendingInviteCount,
            startingAt,
            weeklyActiveUserCount,
            weeklyAdoptionRate,
            chatCoworkUnifiedDailyActiveUserCount,
            chatCoworkUnifiedMonthlyActiveUserCount,
            chatCoworkUnifiedWeeklyActiveUserCount,
            chatDailyActiveUserCount,
            chatMonthlyActiveUserCount,
            chatWeeklyActiveUserCount,
            claudeCodeDailyActiveUserCount,
            claudeCodeMonthlyActiveUserCount,
            claudeCodeWeeklyActiveUserCount,
            claudeDesignDailyActiveUserCount,
            claudeDesignMonthlyActiveUserCount,
            claudeDesignWeeklyActiveUserCount,
            officeAgentDailyActiveUserCount,
            officeAgentMonthlyActiveUserCount,
            officeAgentWeeklyActiveUserCount,
            scienceDailyActiveUserCount,
            scienceEntitledUserCount,
            scienceMonthlyActiveUserCount,
            scienceWeeklyActiveUserCount,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaAnalyticsSingleDayActivitySummary{assignedSeatCount=$assignedSeatCount, coworkDailyActiveUserCount=$coworkDailyActiveUserCount, coworkMonthlyActiveUserCount=$coworkMonthlyActiveUserCount, coworkWeeklyActiveUserCount=$coworkWeeklyActiveUserCount, dailyActiveUserCount=$dailyActiveUserCount, dailyAdoptionRate=$dailyAdoptionRate, endingAt=$endingAt, monthlyActiveUserCount=$monthlyActiveUserCount, monthlyAdoptionRate=$monthlyAdoptionRate, pendingInviteCount=$pendingInviteCount, startingAt=$startingAt, weeklyActiveUserCount=$weeklyActiveUserCount, weeklyAdoptionRate=$weeklyAdoptionRate, chatCoworkUnifiedDailyActiveUserCount=$chatCoworkUnifiedDailyActiveUserCount, chatCoworkUnifiedMonthlyActiveUserCount=$chatCoworkUnifiedMonthlyActiveUserCount, chatCoworkUnifiedWeeklyActiveUserCount=$chatCoworkUnifiedWeeklyActiveUserCount, chatDailyActiveUserCount=$chatDailyActiveUserCount, chatMonthlyActiveUserCount=$chatMonthlyActiveUserCount, chatWeeklyActiveUserCount=$chatWeeklyActiveUserCount, claudeCodeDailyActiveUserCount=$claudeCodeDailyActiveUserCount, claudeCodeMonthlyActiveUserCount=$claudeCodeMonthlyActiveUserCount, claudeCodeWeeklyActiveUserCount=$claudeCodeWeeklyActiveUserCount, claudeDesignDailyActiveUserCount=$claudeDesignDailyActiveUserCount, claudeDesignMonthlyActiveUserCount=$claudeDesignMonthlyActiveUserCount, claudeDesignWeeklyActiveUserCount=$claudeDesignWeeklyActiveUserCount, officeAgentDailyActiveUserCount=$officeAgentDailyActiveUserCount, officeAgentMonthlyActiveUserCount=$officeAgentMonthlyActiveUserCount, officeAgentWeeklyActiveUserCount=$officeAgentWeeklyActiveUserCount, scienceDailyActiveUserCount=$scienceDailyActiveUserCount, scienceEntitledUserCount=$scienceEntitledUserCount, scienceMonthlyActiveUserCount=$scienceMonthlyActiveUserCount, scienceWeeklyActiveUserCount=$scienceWeeklyActiveUserCount, additionalProperties=$additionalProperties}"
}
