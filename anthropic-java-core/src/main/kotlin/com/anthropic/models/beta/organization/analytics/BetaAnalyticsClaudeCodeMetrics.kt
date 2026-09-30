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
import kotlin.jvm.optionals.getOrNull

/** Claude Code activity metrics for a single user on a given day. */
class BetaAnalyticsClaudeCodeMetrics
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val coreMetrics: JsonField<BetaAnalyticsCoreCodeMetrics>,
    private val toolActions: JsonField<BetaAnalyticsToolActions>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("core_metrics")
        @ExcludeMissing
        coreMetrics: JsonField<BetaAnalyticsCoreCodeMetrics> = JsonMissing.of(),
        @JsonProperty("tool_actions")
        @ExcludeMissing
        toolActions: JsonField<BetaAnalyticsToolActions> = JsonMissing.of(),
    ) : this(coreMetrics, toolActions, mutableMapOf())

    /**
     * Core Claude Code activity metrics for a single user on a given day.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun coreMetrics(): BetaAnalyticsCoreCodeMetrics = coreMetrics.getRequired("core_metrics")

    /**
     * Per-tool accepted/rejected counts for Claude Code file modification tools.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun toolActions(): BetaAnalyticsToolActions = toolActions.getRequired("tool_actions")

    /**
     * Returns the raw JSON value of [coreMetrics].
     *
     * Unlike [coreMetrics], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("core_metrics")
    @ExcludeMissing
    fun _coreMetrics(): JsonField<BetaAnalyticsCoreCodeMetrics> = coreMetrics

    /**
     * Returns the raw JSON value of [toolActions].
     *
     * Unlike [toolActions], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("tool_actions")
    @ExcludeMissing
    fun _toolActions(): JsonField<BetaAnalyticsToolActions> = toolActions

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
         * [BetaAnalyticsClaudeCodeMetrics].
         *
         * The following fields are required:
         * ```java
         * .coreMetrics()
         * .toolActions()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaAnalyticsClaudeCodeMetrics]. */
    class Builder internal constructor() {

        private var coreMetrics: JsonField<BetaAnalyticsCoreCodeMetrics>? = null
        private var toolActions: JsonField<BetaAnalyticsToolActions>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaAnalyticsClaudeCodeMetrics: BetaAnalyticsClaudeCodeMetrics) = apply {
            coreMetrics = betaAnalyticsClaudeCodeMetrics.coreMetrics
            toolActions = betaAnalyticsClaudeCodeMetrics.toolActions
            additionalProperties =
                betaAnalyticsClaudeCodeMetrics.additionalProperties.toMutableMap()
        }

        /** Core Claude Code activity metrics for a single user on a given day. */
        fun coreMetrics(coreMetrics: BetaAnalyticsCoreCodeMetrics) =
            coreMetrics(JsonField.of(coreMetrics))

        /**
         * Sets [Builder.coreMetrics] to an arbitrary JSON value.
         *
         * You should usually call [Builder.coreMetrics] with a well-typed
         * [BetaAnalyticsCoreCodeMetrics] value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun coreMetrics(coreMetrics: JsonField<BetaAnalyticsCoreCodeMetrics>) = apply {
            this.coreMetrics = coreMetrics
        }

        /** Per-tool accepted/rejected counts for Claude Code file modification tools. */
        fun toolActions(toolActions: BetaAnalyticsToolActions) =
            toolActions(JsonField.of(toolActions))

        /**
         * Sets [Builder.toolActions] to an arbitrary JSON value.
         *
         * You should usually call [Builder.toolActions] with a well-typed
         * [BetaAnalyticsToolActions] value instead. This method is primarily for setting the field
         * to an undocumented or not yet supported value.
         */
        fun toolActions(toolActions: JsonField<BetaAnalyticsToolActions>) = apply {
            this.toolActions = toolActions
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
         * Returns an immutable instance of [BetaAnalyticsClaudeCodeMetrics].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .coreMetrics()
         * .toolActions()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaAnalyticsClaudeCodeMetrics =
            BetaAnalyticsClaudeCodeMetrics(
                checkRequired("coreMetrics", coreMetrics),
                checkRequired("toolActions", toolActions),
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
    fun validate(): BetaAnalyticsClaudeCodeMetrics = apply {
        if (validated) {
            return@apply
        }

        coreMetrics().validate()
        toolActions().validate()
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
        (coreMetrics.asKnown().getOrNull()?.validity() ?: 0) +
            (toolActions.asKnown().getOrNull()?.validity() ?: 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaAnalyticsClaudeCodeMetrics &&
            coreMetrics == other.coreMetrics &&
            toolActions == other.toolActions &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(coreMetrics, toolActions, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaAnalyticsClaudeCodeMetrics{coreMetrics=$coreMetrics, toolActions=$toolActions, additionalProperties=$additionalProperties}"
}
