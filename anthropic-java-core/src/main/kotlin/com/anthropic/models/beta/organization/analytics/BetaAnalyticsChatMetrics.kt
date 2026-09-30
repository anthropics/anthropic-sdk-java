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

/** Claude.ai activity metrics for a single user on a given day. */
class BetaAnalyticsChatMetrics
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
     * Number of MCP connector invocations.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun connectorsUsedCount(): Long = connectorsUsedCount.getRequired("connectors_used_count")

    /**
     * Number of distinct artifacts created. Exact in date-range mode: a creation belongs to exactly
     * one day, so the per-day counts never overlap and their sum over the window is the exact count
     * of distinct creations in it.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun distinctArtifactsCreatedCount(): Long =
        distinctArtifactsCreatedCount.getRequired("distinct_artifacts_created_count")

    /**
     * Distinct claude.ai connectors this user used. Excludes calls whose connector could not be
     * identified and all calls from organizations with zero data retention. Approximate (HLL,
     * typical error <2%) in date-range mode. Null on aggregated rows where a distinct count cannot
     * be computed.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun distinctConnectorsUsedCount(): Optional<Long> =
        distinctConnectorsUsedCount.getOptional("distinct_connectors_used_count")

    /**
     * Number of distinct conversations the user participated in. Approximate (HLL, typical error
     * <2%) in date-range mode. Null on aggregated rows where a distinct count cannot be computed.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun distinctConversationCount(): Optional<Long> =
        distinctConversationCount.getOptional("distinct_conversation_count")

    /**
     * Number of distinct files uploaded. Approximate (HLL, typical error <2%) in date-range mode.
     * Null on aggregated rows where a distinct count cannot be computed.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun distinctFilesUploadedCount(): Optional<Long> =
        distinctFilesUploadedCount.getOptional("distinct_files_uploaded_count")

    /**
     * Number of distinct projects created. Exact in date-range mode: a creation belongs to exactly
     * one day, so the per-day counts never overlap and their sum over the window is the exact count
     * of distinct creations in it.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun distinctProjectsCreatedCount(): Long =
        distinctProjectsCreatedCount.getRequired("distinct_projects_created_count")

    /**
     * Number of distinct projects used. Approximate (HLL, typical error <2%) in date-range mode.
     * Null on aggregated rows where a distinct count cannot be computed.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun distinctProjectsUsedCount(): Optional<Long> =
        distinctProjectsUsedCount.getOptional("distinct_projects_used_count")

    /**
     * Number of distinct shared artifacts the user viewed. Approximate (HLL, typical error <2%) in
     * date-range mode. Null on aggregated rows where a distinct count cannot be computed.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun distinctSharedArtifactsViewedCount(): Optional<Long> =
        distinctSharedArtifactsViewedCount.getOptional("distinct_shared_artifacts_viewed_count")

    /**
     * Number of distinct skills used. Approximate (HLL, typical error <2%) in date-range mode. Null
     * on aggregated rows where a distinct count cannot be computed.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun distinctSkillsUsedCount(): Optional<Long> =
        distinctSkillsUsedCount.getOptional("distinct_skills_used_count")

    /**
     * Number of messages sent
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun messageCount(): Long = messageCount.getRequired("message_count")

    /**
     * Number of times the user opened a shared conversation in a project
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun sharedConversationsViewedCount(): Long =
        sharedConversationsViewedCount.getRequired("shared_conversations_viewed_count")

    /**
     * Number of messages that used extended thinking
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun thinkingMessageCount(): Long = thinkingMessageCount.getRequired("thinking_message_count")

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
     * Returns the raw JSON value of [distinctArtifactsCreatedCount].
     *
     * Unlike [distinctArtifactsCreatedCount], this method doesn't throw if the JSON field has an
     * unexpected type.
     */
    @JsonProperty("distinct_artifacts_created_count")
    @ExcludeMissing
    fun _distinctArtifactsCreatedCount(): JsonField<Long> = distinctArtifactsCreatedCount

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
     * Returns the raw JSON value of [distinctConversationCount].
     *
     * Unlike [distinctConversationCount], this method doesn't throw if the JSON field has an
     * unexpected type.
     */
    @JsonProperty("distinct_conversation_count")
    @ExcludeMissing
    fun _distinctConversationCount(): JsonField<Long> = distinctConversationCount

    /**
     * Returns the raw JSON value of [distinctFilesUploadedCount].
     *
     * Unlike [distinctFilesUploadedCount], this method doesn't throw if the JSON field has an
     * unexpected type.
     */
    @JsonProperty("distinct_files_uploaded_count")
    @ExcludeMissing
    fun _distinctFilesUploadedCount(): JsonField<Long> = distinctFilesUploadedCount

    /**
     * Returns the raw JSON value of [distinctProjectsCreatedCount].
     *
     * Unlike [distinctProjectsCreatedCount], this method doesn't throw if the JSON field has an
     * unexpected type.
     */
    @JsonProperty("distinct_projects_created_count")
    @ExcludeMissing
    fun _distinctProjectsCreatedCount(): JsonField<Long> = distinctProjectsCreatedCount

    /**
     * Returns the raw JSON value of [distinctProjectsUsedCount].
     *
     * Unlike [distinctProjectsUsedCount], this method doesn't throw if the JSON field has an
     * unexpected type.
     */
    @JsonProperty("distinct_projects_used_count")
    @ExcludeMissing
    fun _distinctProjectsUsedCount(): JsonField<Long> = distinctProjectsUsedCount

    /**
     * Returns the raw JSON value of [distinctSharedArtifactsViewedCount].
     *
     * Unlike [distinctSharedArtifactsViewedCount], this method doesn't throw if the JSON field has
     * an unexpected type.
     */
    @JsonProperty("distinct_shared_artifacts_viewed_count")
    @ExcludeMissing
    fun _distinctSharedArtifactsViewedCount(): JsonField<Long> = distinctSharedArtifactsViewedCount

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
     * Returns the raw JSON value of [sharedConversationsViewedCount].
     *
     * Unlike [sharedConversationsViewedCount], this method doesn't throw if the JSON field has an
     * unexpected type.
     */
    @JsonProperty("shared_conversations_viewed_count")
    @ExcludeMissing
    fun _sharedConversationsViewedCount(): JsonField<Long> = sharedConversationsViewedCount

    /**
     * Returns the raw JSON value of [thinkingMessageCount].
     *
     * Unlike [thinkingMessageCount], this method doesn't throw if the JSON field has an unexpected
     * type.
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
         * Returns a mutable builder for constructing an instance of [BetaAnalyticsChatMetrics].
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

    /** A builder for [BetaAnalyticsChatMetrics]. */
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
        internal fun from(betaAnalyticsChatMetrics: BetaAnalyticsChatMetrics) = apply {
            connectorsUsedCount = betaAnalyticsChatMetrics.connectorsUsedCount
            distinctArtifactsCreatedCount = betaAnalyticsChatMetrics.distinctArtifactsCreatedCount
            distinctConnectorsUsedCount = betaAnalyticsChatMetrics.distinctConnectorsUsedCount
            distinctConversationCount = betaAnalyticsChatMetrics.distinctConversationCount
            distinctFilesUploadedCount = betaAnalyticsChatMetrics.distinctFilesUploadedCount
            distinctProjectsCreatedCount = betaAnalyticsChatMetrics.distinctProjectsCreatedCount
            distinctProjectsUsedCount = betaAnalyticsChatMetrics.distinctProjectsUsedCount
            distinctSharedArtifactsViewedCount =
                betaAnalyticsChatMetrics.distinctSharedArtifactsViewedCount
            distinctSkillsUsedCount = betaAnalyticsChatMetrics.distinctSkillsUsedCount
            messageCount = betaAnalyticsChatMetrics.messageCount
            sharedConversationsViewedCount = betaAnalyticsChatMetrics.sharedConversationsViewedCount
            thinkingMessageCount = betaAnalyticsChatMetrics.thinkingMessageCount
            additionalProperties = betaAnalyticsChatMetrics.additionalProperties.toMutableMap()
        }

        /** Number of MCP connector invocations. */
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
         * Number of distinct artifacts created. Exact in date-range mode: a creation belongs to
         * exactly one day, so the per-day counts never overlap and their sum over the window is the
         * exact count of distinct creations in it.
         */
        fun distinctArtifactsCreatedCount(distinctArtifactsCreatedCount: Long) =
            distinctArtifactsCreatedCount(JsonField.of(distinctArtifactsCreatedCount))

        /**
         * Sets [Builder.distinctArtifactsCreatedCount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.distinctArtifactsCreatedCount] with a well-typed [Long]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun distinctArtifactsCreatedCount(distinctArtifactsCreatedCount: JsonField<Long>) = apply {
            this.distinctArtifactsCreatedCount = distinctArtifactsCreatedCount
        }

        /**
         * Distinct claude.ai connectors this user used. Excludes calls whose connector could not be
         * identified and all calls from organizations with zero data retention. Approximate (HLL,
         * typical error <2%) in date-range mode. Null on aggregated rows where a distinct count
         * cannot be computed.
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
         * Number of distinct conversations the user participated in. Approximate (HLL, typical
         * error <2%) in date-range mode. Null on aggregated rows where a distinct count cannot be
         * computed.
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
         * You should usually call [Builder.distinctConversationCount] with a well-typed [Long]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun distinctConversationCount(distinctConversationCount: JsonField<Long>) = apply {
            this.distinctConversationCount = distinctConversationCount
        }

        /**
         * Number of distinct files uploaded. Approximate (HLL, typical error <2%) in date-range
         * mode. Null on aggregated rows where a distinct count cannot be computed.
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
         * You should usually call [Builder.distinctFilesUploadedCount] with a well-typed [Long]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun distinctFilesUploadedCount(distinctFilesUploadedCount: JsonField<Long>) = apply {
            this.distinctFilesUploadedCount = distinctFilesUploadedCount
        }

        /**
         * Number of distinct projects created. Exact in date-range mode: a creation belongs to
         * exactly one day, so the per-day counts never overlap and their sum over the window is the
         * exact count of distinct creations in it.
         */
        fun distinctProjectsCreatedCount(distinctProjectsCreatedCount: Long) =
            distinctProjectsCreatedCount(JsonField.of(distinctProjectsCreatedCount))

        /**
         * Sets [Builder.distinctProjectsCreatedCount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.distinctProjectsCreatedCount] with a well-typed [Long]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun distinctProjectsCreatedCount(distinctProjectsCreatedCount: JsonField<Long>) = apply {
            this.distinctProjectsCreatedCount = distinctProjectsCreatedCount
        }

        /**
         * Number of distinct projects used. Approximate (HLL, typical error <2%) in date-range
         * mode. Null on aggregated rows where a distinct count cannot be computed.
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
         * You should usually call [Builder.distinctProjectsUsedCount] with a well-typed [Long]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun distinctProjectsUsedCount(distinctProjectsUsedCount: JsonField<Long>) = apply {
            this.distinctProjectsUsedCount = distinctProjectsUsedCount
        }

        /**
         * Number of distinct shared artifacts the user viewed. Approximate (HLL, typical error <2%)
         * in date-range mode. Null on aggregated rows where a distinct count cannot be computed.
         */
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
        fun distinctSharedArtifactsViewedCount(distinctSharedArtifactsViewedCount: Optional<Long>) =
            distinctSharedArtifactsViewedCount(distinctSharedArtifactsViewedCount.getOrNull())

        /**
         * Sets [Builder.distinctSharedArtifactsViewedCount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.distinctSharedArtifactsViewedCount] with a well-typed
         * [Long] value instead. This method is primarily for setting the field to an undocumented
         * or not yet supported value.
         */
        fun distinctSharedArtifactsViewedCount(
            distinctSharedArtifactsViewedCount: JsonField<Long>
        ) = apply { this.distinctSharedArtifactsViewedCount = distinctSharedArtifactsViewedCount }

        /**
         * Number of distinct skills used. Approximate (HLL, typical error <2%) in date-range mode.
         * Null on aggregated rows where a distinct count cannot be computed.
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

        /** Number of messages sent */
        fun messageCount(messageCount: Long) = messageCount(JsonField.of(messageCount))

        /**
         * Sets [Builder.messageCount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.messageCount] with a well-typed [Long] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun messageCount(messageCount: JsonField<Long>) = apply { this.messageCount = messageCount }

        /** Number of times the user opened a shared conversation in a project */
        fun sharedConversationsViewedCount(sharedConversationsViewedCount: Long) =
            sharedConversationsViewedCount(JsonField.of(sharedConversationsViewedCount))

        /**
         * Sets [Builder.sharedConversationsViewedCount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.sharedConversationsViewedCount] with a well-typed [Long]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun sharedConversationsViewedCount(sharedConversationsViewedCount: JsonField<Long>) =
            apply {
                this.sharedConversationsViewedCount = sharedConversationsViewedCount
            }

        /** Number of messages that used extended thinking */
        fun thinkingMessageCount(thinkingMessageCount: Long) =
            thinkingMessageCount(JsonField.of(thinkingMessageCount))

        /**
         * Sets [Builder.thinkingMessageCount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.thinkingMessageCount] with a well-typed [Long] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
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

        fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
            this.additionalProperties.putAll(additionalProperties)
        }

        fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

        fun removeAllAdditionalProperties(keys: Set<String>) = apply {
            keys.forEach(::removeAdditionalProperty)
        }

        /**
         * Returns an immutable instance of [BetaAnalyticsChatMetrics].
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
        fun build(): BetaAnalyticsChatMetrics =
            BetaAnalyticsChatMetrics(
                checkRequired("connectorsUsedCount", connectorsUsedCount),
                checkRequired("distinctArtifactsCreatedCount", distinctArtifactsCreatedCount),
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
                checkRequired("sharedConversationsViewedCount", sharedConversationsViewedCount),
                checkRequired("thinkingMessageCount", thinkingMessageCount),
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
    fun validate(): BetaAnalyticsChatMetrics = apply {
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
     * Returns a score indicating how many valid values are contained in this object recursively.
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

        return other is BetaAnalyticsChatMetrics &&
            connectorsUsedCount == other.connectorsUsedCount &&
            distinctArtifactsCreatedCount == other.distinctArtifactsCreatedCount &&
            distinctConnectorsUsedCount == other.distinctConnectorsUsedCount &&
            distinctConversationCount == other.distinctConversationCount &&
            distinctFilesUploadedCount == other.distinctFilesUploadedCount &&
            distinctProjectsCreatedCount == other.distinctProjectsCreatedCount &&
            distinctProjectsUsedCount == other.distinctProjectsUsedCount &&
            distinctSharedArtifactsViewedCount == other.distinctSharedArtifactsViewedCount &&
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
        "BetaAnalyticsChatMetrics{connectorsUsedCount=$connectorsUsedCount, distinctArtifactsCreatedCount=$distinctArtifactsCreatedCount, distinctConnectorsUsedCount=$distinctConnectorsUsedCount, distinctConversationCount=$distinctConversationCount, distinctFilesUploadedCount=$distinctFilesUploadedCount, distinctProjectsCreatedCount=$distinctProjectsCreatedCount, distinctProjectsUsedCount=$distinctProjectsUsedCount, distinctSharedArtifactsViewedCount=$distinctSharedArtifactsViewedCount, distinctSkillsUsedCount=$distinctSkillsUsedCount, messageCount=$messageCount, sharedConversationsViewedCount=$sharedConversationsViewedCount, thinkingMessageCount=$thinkingMessageCount, additionalProperties=$additionalProperties}"
}
