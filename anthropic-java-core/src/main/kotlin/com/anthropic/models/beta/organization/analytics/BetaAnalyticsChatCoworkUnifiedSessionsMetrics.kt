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

/** Cowork session activity recorded while members had Chat and Cowork unified turned on. */
class BetaAnalyticsChatCoworkUnifiedSessionsMetrics
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val actionCount: JsonField<Long>,
    private val artifactsCreatedCount: JsonField<Long>,
    private val connectorsUsedCount: JsonField<Long>,
    private val dispatchTurnCount: JsonField<Long>,
    private val distinctConnectorsUsedCount: JsonField<Long>,
    private val distinctPluginsUsedCount: JsonField<Long>,
    private val distinctSessionCount: JsonField<Long>,
    private val distinctSkillsUsedCount: JsonField<Long>,
    private val editToolCount: JsonField<Long>,
    private val fileEditCount: JsonField<Long>,
    private val messageCount: JsonField<Long>,
    private val multiEditToolCount: JsonField<Long>,
    private val notebookEditToolCount: JsonField<Long>,
    private val pluginsUsedCount: JsonField<Long>,
    private val sessionsWithFileEditsCount: JsonField<Long>,
    private val skillsUsedCount: JsonField<Long>,
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
        @JsonProperty("distinct_plugins_used_count")
        @ExcludeMissing
        distinctPluginsUsedCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("distinct_session_count")
        @ExcludeMissing
        distinctSessionCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("distinct_skills_used_count")
        @ExcludeMissing
        distinctSkillsUsedCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("edit_tool_count")
        @ExcludeMissing
        editToolCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("file_edit_count")
        @ExcludeMissing
        fileEditCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("message_count")
        @ExcludeMissing
        messageCount: JsonField<Long> = JsonMissing.of(),
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
        @JsonProperty("skills_used_count")
        @ExcludeMissing
        skillsUsedCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("write_tool_count")
        @ExcludeMissing
        writeToolCount: JsonField<Long> = JsonMissing.of(),
    ) : this(
        actionCount,
        artifactsCreatedCount,
        connectorsUsedCount,
        dispatchTurnCount,
        distinctConnectorsUsedCount,
        distinctPluginsUsedCount,
        distinctSessionCount,
        distinctSkillsUsedCount,
        editToolCount,
        fileEditCount,
        messageCount,
        multiEditToolCount,
        notebookEditToolCount,
        pluginsUsedCount,
        sessionsWithFileEditsCount,
        skillsUsedCount,
        writeToolCount,
        mutableMapOf(),
    )

    /**
     * Same measure as `cowork_metrics.action_count`, for activity recorded while members had Chat
     * and Cowork unified turned on.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun actionCount(): Long = actionCount.getRequired("action_count")

    /**
     * Same measure as `cowork_metrics.artifacts_created_count`, for activity recorded while members
     * had Chat and Cowork unified turned on. Exact in date-range mode: a creation belongs to
     * exactly one day, so the per-day counts never overlap and their sum over the window is the
     * exact count of distinct creations in it.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun artifactsCreatedCount(): Long = artifactsCreatedCount.getRequired("artifacts_created_count")

    /**
     * Same measure as `cowork_metrics.connectors_used_count`, for activity recorded while members
     * had Chat and Cowork unified turned on.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun connectorsUsedCount(): Long = connectorsUsedCount.getRequired("connectors_used_count")

    /**
     * Same measure as `cowork_metrics.dispatch_turn_count`, for activity recorded while members had
     * Chat and Cowork unified turned on.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun dispatchTurnCount(): Long = dispatchTurnCount.getRequired("dispatch_turn_count")

    /**
     * Same measure as `cowork_metrics.distinct_connectors_used_count`, for activity recorded while
     * members had Chat and Cowork unified turned on. Approximate (HLL, typical error <2%) in
     * date-range mode. Null on aggregated rows where a distinct count cannot be computed.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun distinctConnectorsUsedCount(): Optional<Long> =
        distinctConnectorsUsedCount.getOptional("distinct_connectors_used_count")

    /**
     * Same measure as `cowork_metrics.distinct_plugins_used_count`, for activity recorded while
     * members had Chat and Cowork unified turned on. Approximate (HLL, typical error <2%) in
     * date-range mode. Null on aggregated rows where a distinct count cannot be computed.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun distinctPluginsUsedCount(): Optional<Long> =
        distinctPluginsUsedCount.getOptional("distinct_plugins_used_count")

    /**
     * Same measure as `cowork_metrics.distinct_session_count`, for activity recorded while members
     * had Chat and Cowork unified turned on. Approximate (HLL, typical error <2%) in date-range
     * mode. Null on aggregated rows where a distinct count cannot be computed.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun distinctSessionCount(): Optional<Long> =
        distinctSessionCount.getOptional("distinct_session_count")

    /**
     * Same measure as `cowork_metrics.distinct_skills_used_count`, for activity recorded while
     * members had Chat and Cowork unified turned on. Approximate (HLL, typical error <2%) in
     * date-range mode. Null on aggregated rows where a distinct count cannot be computed.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun distinctSkillsUsedCount(): Optional<Long> =
        distinctSkillsUsedCount.getOptional("distinct_skills_used_count")

    /**
     * Same measure as `cowork_metrics.edit_tool_count`, for activity recorded while members had
     * Chat and Cowork unified turned on.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun editToolCount(): Optional<Long> = editToolCount.getOptional("edit_tool_count")

    /**
     * Same measure as `cowork_metrics.file_edit_count`, for activity recorded while members had
     * Chat and Cowork unified turned on.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun fileEditCount(): Optional<Long> = fileEditCount.getOptional("file_edit_count")

    /**
     * Same measure as `cowork_metrics.message_count`, for activity recorded while members had Chat
     * and Cowork unified turned on.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun messageCount(): Long = messageCount.getRequired("message_count")

    /**
     * Same measure as `cowork_metrics.multi_edit_tool_count`, for activity recorded while members
     * had Chat and Cowork unified turned on. Claude no longer has a multi-edit tool, so expect 0
     * when not null; each edit is now a separate Edit tool call, counted in `edit_tool_count` and
     * `file_edit_count`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun multiEditToolCount(): Optional<Long> =
        multiEditToolCount.getOptional("multi_edit_tool_count")

    /**
     * Same measure as `cowork_metrics.notebook_edit_tool_count`, for activity recorded while
     * members had Chat and Cowork unified turned on.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun notebookEditToolCount(): Optional<Long> =
        notebookEditToolCount.getOptional("notebook_edit_tool_count")

    /**
     * Same measure as `cowork_metrics.plugins_used_count`, for activity recorded while members had
     * Chat and Cowork unified turned on.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun pluginsUsedCount(): Optional<Long> = pluginsUsedCount.getOptional("plugins_used_count")

    /**
     * Same measure as `cowork_metrics.sessions_with_file_edits_count`, for activity recorded while
     * members had Chat and Cowork unified turned on. Approximate (HLL, typical error <2%) in
     * date-range mode. Null on aggregated rows where a distinct count cannot be computed.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun sessionsWithFileEditsCount(): Optional<Long> =
        sessionsWithFileEditsCount.getOptional("sessions_with_file_edits_count")

    /**
     * Same measure as `cowork_metrics.skills_used_count`, for activity recorded while members had
     * Chat and Cowork unified turned on.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun skillsUsedCount(): Long = skillsUsedCount.getRequired("skills_used_count")

    /**
     * Same measure as `cowork_metrics.write_tool_count`, for activity recorded while members had
     * Chat and Cowork unified turned on.
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
     * Returns the raw JSON value of [distinctPluginsUsedCount].
     *
     * Unlike [distinctPluginsUsedCount], this method doesn't throw if the JSON field has an
     * unexpected type.
     */
    @JsonProperty("distinct_plugins_used_count")
    @ExcludeMissing
    fun _distinctPluginsUsedCount(): JsonField<Long> = distinctPluginsUsedCount

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
     * Returns the raw JSON value of [messageCount].
     *
     * Unlike [messageCount], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("message_count")
    @ExcludeMissing
    fun _messageCount(): JsonField<Long> = messageCount

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
     * Returns the raw JSON value of [skillsUsedCount].
     *
     * Unlike [skillsUsedCount], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("skills_used_count")
    @ExcludeMissing
    fun _skillsUsedCount(): JsonField<Long> = skillsUsedCount

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
         * Returns a mutable builder for constructing an instance of
         * [BetaAnalyticsChatCoworkUnifiedSessionsMetrics].
         *
         * The following fields are required:
         * ```java
         * .actionCount()
         * .artifactsCreatedCount()
         * .connectorsUsedCount()
         * .dispatchTurnCount()
         * .distinctConnectorsUsedCount()
         * .distinctPluginsUsedCount()
         * .distinctSessionCount()
         * .distinctSkillsUsedCount()
         * .editToolCount()
         * .fileEditCount()
         * .messageCount()
         * .multiEditToolCount()
         * .notebookEditToolCount()
         * .pluginsUsedCount()
         * .sessionsWithFileEditsCount()
         * .skillsUsedCount()
         * .writeToolCount()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaAnalyticsChatCoworkUnifiedSessionsMetrics]. */
    class Builder internal constructor() {

        private var actionCount: JsonField<Long>? = null
        private var artifactsCreatedCount: JsonField<Long>? = null
        private var connectorsUsedCount: JsonField<Long>? = null
        private var dispatchTurnCount: JsonField<Long>? = null
        private var distinctConnectorsUsedCount: JsonField<Long>? = null
        private var distinctPluginsUsedCount: JsonField<Long>? = null
        private var distinctSessionCount: JsonField<Long>? = null
        private var distinctSkillsUsedCount: JsonField<Long>? = null
        private var editToolCount: JsonField<Long>? = null
        private var fileEditCount: JsonField<Long>? = null
        private var messageCount: JsonField<Long>? = null
        private var multiEditToolCount: JsonField<Long>? = null
        private var notebookEditToolCount: JsonField<Long>? = null
        private var pluginsUsedCount: JsonField<Long>? = null
        private var sessionsWithFileEditsCount: JsonField<Long>? = null
        private var skillsUsedCount: JsonField<Long>? = null
        private var writeToolCount: JsonField<Long>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaAnalyticsChatCoworkUnifiedSessionsMetrics:
                BetaAnalyticsChatCoworkUnifiedSessionsMetrics
        ) = apply {
            actionCount = betaAnalyticsChatCoworkUnifiedSessionsMetrics.actionCount
            artifactsCreatedCount =
                betaAnalyticsChatCoworkUnifiedSessionsMetrics.artifactsCreatedCount
            connectorsUsedCount = betaAnalyticsChatCoworkUnifiedSessionsMetrics.connectorsUsedCount
            dispatchTurnCount = betaAnalyticsChatCoworkUnifiedSessionsMetrics.dispatchTurnCount
            distinctConnectorsUsedCount =
                betaAnalyticsChatCoworkUnifiedSessionsMetrics.distinctConnectorsUsedCount
            distinctPluginsUsedCount =
                betaAnalyticsChatCoworkUnifiedSessionsMetrics.distinctPluginsUsedCount
            distinctSessionCount =
                betaAnalyticsChatCoworkUnifiedSessionsMetrics.distinctSessionCount
            distinctSkillsUsedCount =
                betaAnalyticsChatCoworkUnifiedSessionsMetrics.distinctSkillsUsedCount
            editToolCount = betaAnalyticsChatCoworkUnifiedSessionsMetrics.editToolCount
            fileEditCount = betaAnalyticsChatCoworkUnifiedSessionsMetrics.fileEditCount
            messageCount = betaAnalyticsChatCoworkUnifiedSessionsMetrics.messageCount
            multiEditToolCount = betaAnalyticsChatCoworkUnifiedSessionsMetrics.multiEditToolCount
            notebookEditToolCount =
                betaAnalyticsChatCoworkUnifiedSessionsMetrics.notebookEditToolCount
            pluginsUsedCount = betaAnalyticsChatCoworkUnifiedSessionsMetrics.pluginsUsedCount
            sessionsWithFileEditsCount =
                betaAnalyticsChatCoworkUnifiedSessionsMetrics.sessionsWithFileEditsCount
            skillsUsedCount = betaAnalyticsChatCoworkUnifiedSessionsMetrics.skillsUsedCount
            writeToolCount = betaAnalyticsChatCoworkUnifiedSessionsMetrics.writeToolCount
            additionalProperties =
                betaAnalyticsChatCoworkUnifiedSessionsMetrics.additionalProperties.toMutableMap()
        }

        /**
         * Same measure as `cowork_metrics.action_count`, for activity recorded while members had
         * Chat and Cowork unified turned on.
         */
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
         * Same measure as `cowork_metrics.artifacts_created_count`, for activity recorded while
         * members had Chat and Cowork unified turned on. Exact in date-range mode: a creation
         * belongs to exactly one day, so the per-day counts never overlap and their sum over the
         * window is the exact count of distinct creations in it.
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

        /**
         * Same measure as `cowork_metrics.connectors_used_count`, for activity recorded while
         * members had Chat and Cowork unified turned on.
         */
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

        /**
         * Same measure as `cowork_metrics.dispatch_turn_count`, for activity recorded while members
         * had Chat and Cowork unified turned on.
         */
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
         * Same measure as `cowork_metrics.distinct_connectors_used_count`, for activity recorded
         * while members had Chat and Cowork unified turned on. Approximate (HLL, typical error <2%)
         * in date-range mode. Null on aggregated rows where a distinct count cannot be computed.
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
         * Same measure as `cowork_metrics.distinct_plugins_used_count`, for activity recorded while
         * members had Chat and Cowork unified turned on. Approximate (HLL, typical error <2%) in
         * date-range mode. Null on aggregated rows where a distinct count cannot be computed.
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
         * Same measure as `cowork_metrics.distinct_session_count`, for activity recorded while
         * members had Chat and Cowork unified turned on. Approximate (HLL, typical error <2%) in
         * date-range mode. Null on aggregated rows where a distinct count cannot be computed.
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
         * Same measure as `cowork_metrics.distinct_skills_used_count`, for activity recorded while
         * members had Chat and Cowork unified turned on. Approximate (HLL, typical error <2%) in
         * date-range mode. Null on aggregated rows where a distinct count cannot be computed.
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

        /**
         * Same measure as `cowork_metrics.edit_tool_count`, for activity recorded while members had
         * Chat and Cowork unified turned on.
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
         * Same measure as `cowork_metrics.file_edit_count`, for activity recorded while members had
         * Chat and Cowork unified turned on.
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
         * Same measure as `cowork_metrics.message_count`, for activity recorded while members had
         * Chat and Cowork unified turned on.
         */
        fun messageCount(messageCount: Long) = messageCount(JsonField.of(messageCount))

        /**
         * Sets [Builder.messageCount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.messageCount] with a well-typed [Long] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun messageCount(messageCount: JsonField<Long>) = apply { this.messageCount = messageCount }

        /**
         * Same measure as `cowork_metrics.multi_edit_tool_count`, for activity recorded while
         * members had Chat and Cowork unified turned on. Claude no longer has a multi-edit tool, so
         * expect 0 when not null; each edit is now a separate Edit tool call, counted in
         * `edit_tool_count` and `file_edit_count`.
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
         * Same measure as `cowork_metrics.notebook_edit_tool_count`, for activity recorded while
         * members had Chat and Cowork unified turned on.
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
         * Same measure as `cowork_metrics.plugins_used_count`, for activity recorded while members
         * had Chat and Cowork unified turned on.
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
         * Same measure as `cowork_metrics.sessions_with_file_edits_count`, for activity recorded
         * while members had Chat and Cowork unified turned on. Approximate (HLL, typical error <2%)
         * in date-range mode. Null on aggregated rows where a distinct count cannot be computed.
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
         * Same measure as `cowork_metrics.skills_used_count`, for activity recorded while members
         * had Chat and Cowork unified turned on.
         */
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
         * Same measure as `cowork_metrics.write_tool_count`, for activity recorded while members
         * had Chat and Cowork unified turned on.
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
         * Returns an immutable instance of [BetaAnalyticsChatCoworkUnifiedSessionsMetrics].
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
         * .distinctPluginsUsedCount()
         * .distinctSessionCount()
         * .distinctSkillsUsedCount()
         * .editToolCount()
         * .fileEditCount()
         * .messageCount()
         * .multiEditToolCount()
         * .notebookEditToolCount()
         * .pluginsUsedCount()
         * .sessionsWithFileEditsCount()
         * .skillsUsedCount()
         * .writeToolCount()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaAnalyticsChatCoworkUnifiedSessionsMetrics =
            BetaAnalyticsChatCoworkUnifiedSessionsMetrics(
                checkRequired("actionCount", actionCount),
                checkRequired("artifactsCreatedCount", artifactsCreatedCount),
                checkRequired("connectorsUsedCount", connectorsUsedCount),
                checkRequired("dispatchTurnCount", dispatchTurnCount),
                checkRequired("distinctConnectorsUsedCount", distinctConnectorsUsedCount),
                checkRequired("distinctPluginsUsedCount", distinctPluginsUsedCount),
                checkRequired("distinctSessionCount", distinctSessionCount),
                checkRequired("distinctSkillsUsedCount", distinctSkillsUsedCount),
                checkRequired("editToolCount", editToolCount),
                checkRequired("fileEditCount", fileEditCount),
                checkRequired("messageCount", messageCount),
                checkRequired("multiEditToolCount", multiEditToolCount),
                checkRequired("notebookEditToolCount", notebookEditToolCount),
                checkRequired("pluginsUsedCount", pluginsUsedCount),
                checkRequired("sessionsWithFileEditsCount", sessionsWithFileEditsCount),
                checkRequired("skillsUsedCount", skillsUsedCount),
                checkRequired("writeToolCount", writeToolCount),
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
    fun validate(): BetaAnalyticsChatCoworkUnifiedSessionsMetrics = apply {
        if (validated) {
            return@apply
        }

        actionCount()
        artifactsCreatedCount()
        connectorsUsedCount()
        dispatchTurnCount()
        distinctConnectorsUsedCount()
        distinctPluginsUsedCount()
        distinctSessionCount()
        distinctSkillsUsedCount()
        editToolCount()
        fileEditCount()
        messageCount()
        multiEditToolCount()
        notebookEditToolCount()
        pluginsUsedCount()
        sessionsWithFileEditsCount()
        skillsUsedCount()
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
            (if (distinctPluginsUsedCount.asKnown().isPresent) 1 else 0) +
            (if (distinctSessionCount.asKnown().isPresent) 1 else 0) +
            (if (distinctSkillsUsedCount.asKnown().isPresent) 1 else 0) +
            (if (editToolCount.asKnown().isPresent) 1 else 0) +
            (if (fileEditCount.asKnown().isPresent) 1 else 0) +
            (if (messageCount.asKnown().isPresent) 1 else 0) +
            (if (multiEditToolCount.asKnown().isPresent) 1 else 0) +
            (if (notebookEditToolCount.asKnown().isPresent) 1 else 0) +
            (if (pluginsUsedCount.asKnown().isPresent) 1 else 0) +
            (if (sessionsWithFileEditsCount.asKnown().isPresent) 1 else 0) +
            (if (skillsUsedCount.asKnown().isPresent) 1 else 0) +
            (if (writeToolCount.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaAnalyticsChatCoworkUnifiedSessionsMetrics &&
            actionCount == other.actionCount &&
            artifactsCreatedCount == other.artifactsCreatedCount &&
            connectorsUsedCount == other.connectorsUsedCount &&
            dispatchTurnCount == other.dispatchTurnCount &&
            distinctConnectorsUsedCount == other.distinctConnectorsUsedCount &&
            distinctPluginsUsedCount == other.distinctPluginsUsedCount &&
            distinctSessionCount == other.distinctSessionCount &&
            distinctSkillsUsedCount == other.distinctSkillsUsedCount &&
            editToolCount == other.editToolCount &&
            fileEditCount == other.fileEditCount &&
            messageCount == other.messageCount &&
            multiEditToolCount == other.multiEditToolCount &&
            notebookEditToolCount == other.notebookEditToolCount &&
            pluginsUsedCount == other.pluginsUsedCount &&
            sessionsWithFileEditsCount == other.sessionsWithFileEditsCount &&
            skillsUsedCount == other.skillsUsedCount &&
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
            distinctPluginsUsedCount,
            distinctSessionCount,
            distinctSkillsUsedCount,
            editToolCount,
            fileEditCount,
            messageCount,
            multiEditToolCount,
            notebookEditToolCount,
            pluginsUsedCount,
            sessionsWithFileEditsCount,
            skillsUsedCount,
            writeToolCount,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaAnalyticsChatCoworkUnifiedSessionsMetrics{actionCount=$actionCount, artifactsCreatedCount=$artifactsCreatedCount, connectorsUsedCount=$connectorsUsedCount, dispatchTurnCount=$dispatchTurnCount, distinctConnectorsUsedCount=$distinctConnectorsUsedCount, distinctPluginsUsedCount=$distinctPluginsUsedCount, distinctSessionCount=$distinctSessionCount, distinctSkillsUsedCount=$distinctSkillsUsedCount, editToolCount=$editToolCount, fileEditCount=$fileEditCount, messageCount=$messageCount, multiEditToolCount=$multiEditToolCount, notebookEditToolCount=$notebookEditToolCount, pluginsUsedCount=$pluginsUsedCount, sessionsWithFileEditsCount=$sessionsWithFileEditsCount, skillsUsedCount=$skillsUsedCount, writeToolCount=$writeToolCount, additionalProperties=$additionalProperties}"
}
