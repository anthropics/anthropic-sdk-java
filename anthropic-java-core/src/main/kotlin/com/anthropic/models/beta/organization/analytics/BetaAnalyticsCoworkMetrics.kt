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

/** Cowork activity metrics for a single user on a given day. */
class BetaAnalyticsCoworkMetrics
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
     * Number of tool actions completed in Cowork sessions
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun actionCount(): Long = actionCount.getRequired("action_count")

    /**
     * Number of artifacts created in Cowork sessions: an artifact counts once, on the day a session
     * first saves it. Counted from 2026-08-17; 0 on earlier days. Exact in date-range mode: a
     * creation belongs to exactly one day, so the per-day counts never overlap and their sum over
     * the window is the exact count of distinct creations in it.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun artifactsCreatedCount(): Long = artifactsCreatedCount.getRequired("artifacts_created_count")

    /**
     * Total number of connector invocations in Cowork sessions
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun connectorsUsedCount(): Long = connectorsUsedCount.getRequired("connectors_used_count")

    /**
     * Number of Dispatch (background agent) turns completed
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun dispatchTurnCount(): Long = dispatchTurnCount.getRequired("dispatch_turn_count")

    /**
     * Number of distinct connectors used in Cowork sessions. Approximate (HLL, typical error <2%)
     * in date-range mode. Null on aggregated rows where a distinct count cannot be computed.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun distinctConnectorsUsedCount(): Optional<Long> =
        distinctConnectorsUsedCount.getOptional("distinct_connectors_used_count")

    /**
     * Number of distinct Cowork sessions. Approximate (HLL, typical error <2%) in date-range mode.
     * Null on aggregated rows where a distinct count cannot be computed.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun distinctSessionCount(): Optional<Long> =
        distinctSessionCount.getOptional("distinct_session_count")

    /**
     * Number of distinct skills used in Cowork sessions. Approximate (HLL, typical error <2%) in
     * date-range mode. Null on aggregated rows where a distinct count cannot be computed.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun distinctSkillsUsedCount(): Optional<Long> =
        distinctSkillsUsedCount.getOptional("distinct_skills_used_count")

    /**
     * Number of messages sent in Cowork sessions
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun messageCount(): Long = messageCount.getRequired("message_count")

    /**
     * Total number of skill invocations in Cowork sessions
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun skillsUsedCount(): Long = skillsUsedCount.getRequired("skills_used_count")

    /**
     * Number of distinct plugins used in Cowork sessions. Null while Cowork plugin-use metrics are
     * not enabled for this organization. Approximate (HLL, typical error <2%) in date-range mode.
     * Null on aggregated rows where a distinct count cannot be computed.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun distinctPluginsUsedCount(): Optional<Long> =
        distinctPluginsUsedCount.getOptional("distinct_plugins_used_count")

    /**
     * Number of successful Edit tool calls in Cowork sessions. Null while the file-edit metrics are
     * not enabled for this organization.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun editToolCount(): Optional<Long> = editToolCount.getOptional("edit_tool_count")

    /**
     * Number of successful file-edit tool calls (Edit, MultiEdit, Write, NotebookEdit) in Cowork
     * sessions. Null, never 0, while the file-edit metrics are not enabled for this organization.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun fileEditCount(): Optional<Long> = fileEditCount.getOptional("file_edit_count")

    /**
     * Number of successful MultiEdit tool calls in Cowork sessions. Null while the file-edit
     * metrics are not enabled for this organization.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun multiEditToolCount(): Optional<Long> =
        multiEditToolCount.getOptional("multi_edit_tool_count")

    /**
     * Number of successful NotebookEdit tool calls in Cowork sessions. Null while the file-edit
     * metrics are not enabled for this organization.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun notebookEditToolCount(): Optional<Long> =
        notebookEditToolCount.getOptional("notebook_edit_tool_count")

    /**
     * Total number of plugin invocations in Cowork sessions. Null while Cowork plugin-use metrics
     * are not enabled for this organization.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun pluginsUsedCount(): Optional<Long> = pluginsUsedCount.getOptional("plugins_used_count")

    /**
     * Number of distinct Cowork sessions with at least one successful file-edit tool call. Null
     * while the file-edit metrics are not enabled for this organization. Approximate (HLL, typical
     * error <2%) in date-range mode. Null on aggregated rows where a distinct count cannot be
     * computed.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun sessionsWithFileEditsCount(): Optional<Long> =
        sessionsWithFileEditsCount.getOptional("sessions_with_file_edits_count")

    /**
     * Number of successful Write tool calls in Cowork sessions. Null while the file-edit metrics
     * are not enabled for this organization.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun writeToolCount(): Optional<Long> = writeToolCount.getOptional("write_tool_count")

    /**
     * Returns the raw JSON value of [actionCount].
     *
     * Unlike [actionCount], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("action_count") @ExcludeMissing fun _actionCount(): JsonField<Long> = actionCount

    /**
     * Returns the raw JSON value of [artifactsCreatedCount].
     *
     * Unlike [artifactsCreatedCount], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("artifacts_created_count")
    @ExcludeMissing
    fun _artifactsCreatedCount(): JsonField<Long> = artifactsCreatedCount

    /**
     * Returns the raw JSON value of [connectorsUsedCount].
     *
     * Unlike [connectorsUsedCount], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("connectors_used_count")
    @ExcludeMissing
    fun _connectorsUsedCount(): JsonField<Long> = connectorsUsedCount

    /**
     * Returns the raw JSON value of [dispatchTurnCount].
     *
     * Unlike [dispatchTurnCount], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("dispatch_turn_count")
    @ExcludeMissing
    fun _dispatchTurnCount(): JsonField<Long> = dispatchTurnCount

    /**
     * Returns the raw JSON value of [distinctConnectorsUsedCount].
     *
     * Unlike [distinctConnectorsUsedCount], this method doesn't throw if the JSON field has an
     * unexpected type.
     */
    @JsonProperty("distinct_connectors_used_count")
    @ExcludeMissing
    fun _distinctConnectorsUsedCount(): JsonField<Long> = distinctConnectorsUsedCount

    /**
     * Returns the raw JSON value of [distinctSessionCount].
     *
     * Unlike [distinctSessionCount], this method doesn't throw if the JSON field has an unexpected
     * type.
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
     * Unlike [messageCount], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("message_count")
    @ExcludeMissing
    fun _messageCount(): JsonField<Long> = messageCount

    /**
     * Returns the raw JSON value of [skillsUsedCount].
     *
     * Unlike [skillsUsedCount], this method doesn't throw if the JSON field has an unexpected type.
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
     * Unlike [editToolCount], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("edit_tool_count")
    @ExcludeMissing
    fun _editToolCount(): JsonField<Long> = editToolCount

    /**
     * Returns the raw JSON value of [fileEditCount].
     *
     * Unlike [fileEditCount], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("file_edit_count")
    @ExcludeMissing
    fun _fileEditCount(): JsonField<Long> = fileEditCount

    /**
     * Returns the raw JSON value of [multiEditToolCount].
     *
     * Unlike [multiEditToolCount], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("multi_edit_tool_count")
    @ExcludeMissing
    fun _multiEditToolCount(): JsonField<Long> = multiEditToolCount

    /**
     * Returns the raw JSON value of [notebookEditToolCount].
     *
     * Unlike [notebookEditToolCount], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("notebook_edit_tool_count")
    @ExcludeMissing
    fun _notebookEditToolCount(): JsonField<Long> = notebookEditToolCount

    /**
     * Returns the raw JSON value of [pluginsUsedCount].
     *
     * Unlike [pluginsUsedCount], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("plugins_used_count")
    @ExcludeMissing
    fun _pluginsUsedCount(): JsonField<Long> = pluginsUsedCount

    /**
     * Returns the raw JSON value of [sessionsWithFileEditsCount].
     *
     * Unlike [sessionsWithFileEditsCount], this method doesn't throw if the JSON field has an
     * unexpected type.
     */
    @JsonProperty("sessions_with_file_edits_count")
    @ExcludeMissing
    fun _sessionsWithFileEditsCount(): JsonField<Long> = sessionsWithFileEditsCount

    /**
     * Returns the raw JSON value of [writeToolCount].
     *
     * Unlike [writeToolCount], this method doesn't throw if the JSON field has an unexpected type.
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
         * Returns a mutable builder for constructing an instance of [BetaAnalyticsCoworkMetrics].
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

    /** A builder for [BetaAnalyticsCoworkMetrics]. */
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
        internal fun from(betaAnalyticsCoworkMetrics: BetaAnalyticsCoworkMetrics) = apply {
            actionCount = betaAnalyticsCoworkMetrics.actionCount
            artifactsCreatedCount = betaAnalyticsCoworkMetrics.artifactsCreatedCount
            connectorsUsedCount = betaAnalyticsCoworkMetrics.connectorsUsedCount
            dispatchTurnCount = betaAnalyticsCoworkMetrics.dispatchTurnCount
            distinctConnectorsUsedCount = betaAnalyticsCoworkMetrics.distinctConnectorsUsedCount
            distinctSessionCount = betaAnalyticsCoworkMetrics.distinctSessionCount
            distinctSkillsUsedCount = betaAnalyticsCoworkMetrics.distinctSkillsUsedCount
            messageCount = betaAnalyticsCoworkMetrics.messageCount
            skillsUsedCount = betaAnalyticsCoworkMetrics.skillsUsedCount
            distinctPluginsUsedCount = betaAnalyticsCoworkMetrics.distinctPluginsUsedCount
            editToolCount = betaAnalyticsCoworkMetrics.editToolCount
            fileEditCount = betaAnalyticsCoworkMetrics.fileEditCount
            multiEditToolCount = betaAnalyticsCoworkMetrics.multiEditToolCount
            notebookEditToolCount = betaAnalyticsCoworkMetrics.notebookEditToolCount
            pluginsUsedCount = betaAnalyticsCoworkMetrics.pluginsUsedCount
            sessionsWithFileEditsCount = betaAnalyticsCoworkMetrics.sessionsWithFileEditsCount
            writeToolCount = betaAnalyticsCoworkMetrics.writeToolCount
            additionalProperties = betaAnalyticsCoworkMetrics.additionalProperties.toMutableMap()
        }

        /** Number of tool actions completed in Cowork sessions */
        fun actionCount(actionCount: Long) = actionCount(JsonField.of(actionCount))

        /**
         * Sets [Builder.actionCount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.actionCount] with a well-typed [Long] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun actionCount(actionCount: JsonField<Long>) = apply { this.actionCount = actionCount }

        /**
         * Number of artifacts created in Cowork sessions: an artifact counts once, on the day a
         * session first saves it. Counted from 2026-08-17; 0 on earlier days. Exact in date-range
         * mode: a creation belongs to exactly one day, so the per-day counts never overlap and
         * their sum over the window is the exact count of distinct creations in it.
         */
        fun artifactsCreatedCount(artifactsCreatedCount: Long) =
            artifactsCreatedCount(JsonField.of(artifactsCreatedCount))

        /**
         * Sets [Builder.artifactsCreatedCount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.artifactsCreatedCount] with a well-typed [Long] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun artifactsCreatedCount(artifactsCreatedCount: JsonField<Long>) = apply {
            this.artifactsCreatedCount = artifactsCreatedCount
        }

        /** Total number of connector invocations in Cowork sessions */
        fun connectorsUsedCount(connectorsUsedCount: Long) =
            connectorsUsedCount(JsonField.of(connectorsUsedCount))

        /**
         * Sets [Builder.connectorsUsedCount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.connectorsUsedCount] with a well-typed [Long] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun connectorsUsedCount(connectorsUsedCount: JsonField<Long>) = apply {
            this.connectorsUsedCount = connectorsUsedCount
        }

        /** Number of Dispatch (background agent) turns completed */
        fun dispatchTurnCount(dispatchTurnCount: Long) =
            dispatchTurnCount(JsonField.of(dispatchTurnCount))

        /**
         * Sets [Builder.dispatchTurnCount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.dispatchTurnCount] with a well-typed [Long] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun dispatchTurnCount(dispatchTurnCount: JsonField<Long>) = apply {
            this.dispatchTurnCount = dispatchTurnCount
        }

        /**
         * Number of distinct connectors used in Cowork sessions. Approximate (HLL, typical error
         * <2%) in date-range mode. Null on aggregated rows where a distinct count cannot be
         * computed.
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
         * You should usually call [Builder.distinctConnectorsUsedCount] with a well-typed [Long]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun distinctConnectorsUsedCount(distinctConnectorsUsedCount: JsonField<Long>) = apply {
            this.distinctConnectorsUsedCount = distinctConnectorsUsedCount
        }

        /**
         * Number of distinct Cowork sessions. Approximate (HLL, typical error <2%) in date-range
         * mode. Null on aggregated rows where a distinct count cannot be computed.
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
         * You should usually call [Builder.distinctSessionCount] with a well-typed [Long] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun distinctSessionCount(distinctSessionCount: JsonField<Long>) = apply {
            this.distinctSessionCount = distinctSessionCount
        }

        /**
         * Number of distinct skills used in Cowork sessions. Approximate (HLL, typical error <2%)
         * in date-range mode. Null on aggregated rows where a distinct count cannot be computed.
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
         * You should usually call [Builder.distinctSkillsUsedCount] with a well-typed [Long] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun distinctSkillsUsedCount(distinctSkillsUsedCount: JsonField<Long>) = apply {
            this.distinctSkillsUsedCount = distinctSkillsUsedCount
        }

        /** Number of messages sent in Cowork sessions */
        fun messageCount(messageCount: Long) = messageCount(JsonField.of(messageCount))

        /**
         * Sets [Builder.messageCount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.messageCount] with a well-typed [Long] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun messageCount(messageCount: JsonField<Long>) = apply { this.messageCount = messageCount }

        /** Total number of skill invocations in Cowork sessions */
        fun skillsUsedCount(skillsUsedCount: Long) = skillsUsedCount(JsonField.of(skillsUsedCount))

        /**
         * Sets [Builder.skillsUsedCount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.skillsUsedCount] with a well-typed [Long] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun skillsUsedCount(skillsUsedCount: JsonField<Long>) = apply {
            this.skillsUsedCount = skillsUsedCount
        }

        /**
         * Number of distinct plugins used in Cowork sessions. Null while Cowork plugin-use metrics
         * are not enabled for this organization. Approximate (HLL, typical error <2%) in date-range
         * mode. Null on aggregated rows where a distinct count cannot be computed.
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
         * You should usually call [Builder.distinctPluginsUsedCount] with a well-typed [Long] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun distinctPluginsUsedCount(distinctPluginsUsedCount: JsonField<Long>) = apply {
            this.distinctPluginsUsedCount = distinctPluginsUsedCount
        }

        /**
         * Number of successful Edit tool calls in Cowork sessions. Null while the file-edit metrics
         * are not enabled for this organization.
         */
        fun editToolCount(editToolCount: Long?) = editToolCount(JsonField.ofNullable(editToolCount))

        /**
         * Alias for [Builder.editToolCount].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun editToolCount(editToolCount: Long) = editToolCount(editToolCount as Long?)

        /** Alias for calling [Builder.editToolCount] with `editToolCount.orElse(null)`. */
        fun editToolCount(editToolCount: Optional<Long>) = editToolCount(editToolCount.getOrNull())

        /**
         * Sets [Builder.editToolCount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.editToolCount] with a well-typed [Long] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun editToolCount(editToolCount: JsonField<Long>) = apply {
            this.editToolCount = editToolCount
        }

        /**
         * Number of successful file-edit tool calls (Edit, MultiEdit, Write, NotebookEdit) in
         * Cowork sessions. Null, never 0, while the file-edit metrics are not enabled for this
         * organization.
         */
        fun fileEditCount(fileEditCount: Long?) = fileEditCount(JsonField.ofNullable(fileEditCount))

        /**
         * Alias for [Builder.fileEditCount].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun fileEditCount(fileEditCount: Long) = fileEditCount(fileEditCount as Long?)

        /** Alias for calling [Builder.fileEditCount] with `fileEditCount.orElse(null)`. */
        fun fileEditCount(fileEditCount: Optional<Long>) = fileEditCount(fileEditCount.getOrNull())

        /**
         * Sets [Builder.fileEditCount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.fileEditCount] with a well-typed [Long] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun fileEditCount(fileEditCount: JsonField<Long>) = apply {
            this.fileEditCount = fileEditCount
        }

        /**
         * Number of successful MultiEdit tool calls in Cowork sessions. Null while the file-edit
         * metrics are not enabled for this organization.
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
         * Alias for calling [Builder.multiEditToolCount] with `multiEditToolCount.orElse(null)`.
         */
        fun multiEditToolCount(multiEditToolCount: Optional<Long>) =
            multiEditToolCount(multiEditToolCount.getOrNull())

        /**
         * Sets [Builder.multiEditToolCount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.multiEditToolCount] with a well-typed [Long] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun multiEditToolCount(multiEditToolCount: JsonField<Long>) = apply {
            this.multiEditToolCount = multiEditToolCount
        }

        /**
         * Number of successful NotebookEdit tool calls in Cowork sessions. Null while the file-edit
         * metrics are not enabled for this organization.
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
         * You should usually call [Builder.notebookEditToolCount] with a well-typed [Long] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun notebookEditToolCount(notebookEditToolCount: JsonField<Long>) = apply {
            this.notebookEditToolCount = notebookEditToolCount
        }

        /**
         * Total number of plugin invocations in Cowork sessions. Null while Cowork plugin-use
         * metrics are not enabled for this organization.
         */
        fun pluginsUsedCount(pluginsUsedCount: Long?) =
            pluginsUsedCount(JsonField.ofNullable(pluginsUsedCount))

        /**
         * Alias for [Builder.pluginsUsedCount].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun pluginsUsedCount(pluginsUsedCount: Long) = pluginsUsedCount(pluginsUsedCount as Long?)

        /** Alias for calling [Builder.pluginsUsedCount] with `pluginsUsedCount.orElse(null)`. */
        fun pluginsUsedCount(pluginsUsedCount: Optional<Long>) =
            pluginsUsedCount(pluginsUsedCount.getOrNull())

        /**
         * Sets [Builder.pluginsUsedCount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.pluginsUsedCount] with a well-typed [Long] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun pluginsUsedCount(pluginsUsedCount: JsonField<Long>) = apply {
            this.pluginsUsedCount = pluginsUsedCount
        }

        /**
         * Number of distinct Cowork sessions with at least one successful file-edit tool call. Null
         * while the file-edit metrics are not enabled for this organization. Approximate (HLL,
         * typical error <2%) in date-range mode. Null on aggregated rows where a distinct count
         * cannot be computed.
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
         * You should usually call [Builder.sessionsWithFileEditsCount] with a well-typed [Long]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun sessionsWithFileEditsCount(sessionsWithFileEditsCount: JsonField<Long>) = apply {
            this.sessionsWithFileEditsCount = sessionsWithFileEditsCount
        }

        /**
         * Number of successful Write tool calls in Cowork sessions. Null while the file-edit
         * metrics are not enabled for this organization.
         */
        fun writeToolCount(writeToolCount: Long?) =
            writeToolCount(JsonField.ofNullable(writeToolCount))

        /**
         * Alias for [Builder.writeToolCount].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun writeToolCount(writeToolCount: Long) = writeToolCount(writeToolCount as Long?)

        /** Alias for calling [Builder.writeToolCount] with `writeToolCount.orElse(null)`. */
        fun writeToolCount(writeToolCount: Optional<Long>) =
            writeToolCount(writeToolCount.getOrNull())

        /**
         * Sets [Builder.writeToolCount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.writeToolCount] with a well-typed [Long] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
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

        fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
            this.additionalProperties.putAll(additionalProperties)
        }

        fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

        fun removeAllAdditionalProperties(keys: Set<String>) = apply {
            keys.forEach(::removeAdditionalProperty)
        }

        /**
         * Returns an immutable instance of [BetaAnalyticsCoworkMetrics].
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
        fun build(): BetaAnalyticsCoworkMetrics =
            BetaAnalyticsCoworkMetrics(
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
     * Validates that the types of all values in this object match their expected types recursively.
     *
     * This method is _not_ forwards compatible with new types from the API for existing fields.
     *
     * @throws AnthropicInvalidDataException if any value type in this object doesn't match its
     *   expected type.
     */
    fun validate(): BetaAnalyticsCoworkMetrics = apply {
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
     * Returns a score indicating how many valid values are contained in this object recursively.
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

        return other is BetaAnalyticsCoworkMetrics &&
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
        "BetaAnalyticsCoworkMetrics{actionCount=$actionCount, artifactsCreatedCount=$artifactsCreatedCount, connectorsUsedCount=$connectorsUsedCount, dispatchTurnCount=$dispatchTurnCount, distinctConnectorsUsedCount=$distinctConnectorsUsedCount, distinctSessionCount=$distinctSessionCount, distinctSkillsUsedCount=$distinctSkillsUsedCount, messageCount=$messageCount, skillsUsedCount=$skillsUsedCount, distinctPluginsUsedCount=$distinctPluginsUsedCount, editToolCount=$editToolCount, fileEditCount=$fileEditCount, multiEditToolCount=$multiEditToolCount, notebookEditToolCount=$notebookEditToolCount, pluginsUsedCount=$pluginsUsedCount, sessionsWithFileEditsCount=$sessionsWithFileEditsCount, writeToolCount=$writeToolCount, additionalProperties=$additionalProperties}"
}
