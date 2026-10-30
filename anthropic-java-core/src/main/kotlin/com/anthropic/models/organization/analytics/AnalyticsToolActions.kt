package com.anthropic.models.organization.analytics

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

/** Per-tool accepted/rejected counts for Claude Code file modification tools. */
class AnalyticsToolActions
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val editTool: JsonField<AnalyticsToolActionCounts>,
    private val multiEditTool: JsonField<AnalyticsToolActionCounts>,
    private val notebookEditTool: JsonField<AnalyticsToolActionCounts>,
    private val writeTool: JsonField<AnalyticsToolActionCounts>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("edit_tool")
        @ExcludeMissing
        editTool: JsonField<AnalyticsToolActionCounts> = JsonMissing.of(),
        @JsonProperty("multi_edit_tool")
        @ExcludeMissing
        multiEditTool: JsonField<AnalyticsToolActionCounts> = JsonMissing.of(),
        @JsonProperty("notebook_edit_tool")
        @ExcludeMissing
        notebookEditTool: JsonField<AnalyticsToolActionCounts> = JsonMissing.of(),
        @JsonProperty("write_tool")
        @ExcludeMissing
        writeTool: JsonField<AnalyticsToolActionCounts> = JsonMissing.of(),
    ) : this(editTool, multiEditTool, notebookEditTool, writeTool, mutableMapOf())

    /**
     * Accepted/rejected counts for a single Claude Code tool type.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun editTool(): AnalyticsToolActionCounts = editTool.getRequired("edit_tool")

    /**
     * Accepted/rejected counts for a single Claude Code tool type.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun multiEditTool(): AnalyticsToolActionCounts = multiEditTool.getRequired("multi_edit_tool")

    /**
     * Accepted/rejected counts for a single Claude Code tool type.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun notebookEditTool(): AnalyticsToolActionCounts =
        notebookEditTool.getRequired("notebook_edit_tool")

    /**
     * Accepted/rejected counts for a single Claude Code tool type.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun writeTool(): AnalyticsToolActionCounts = writeTool.getRequired("write_tool")

    /**
     * Returns the raw JSON value of [editTool].
     *
     * Unlike [editTool], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("edit_tool")
    @ExcludeMissing
    fun _editTool(): JsonField<AnalyticsToolActionCounts> = editTool

    /**
     * Returns the raw JSON value of [multiEditTool].
     *
     * Unlike [multiEditTool], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("multi_edit_tool")
    @ExcludeMissing
    fun _multiEditTool(): JsonField<AnalyticsToolActionCounts> = multiEditTool

    /**
     * Returns the raw JSON value of [notebookEditTool].
     *
     * Unlike [notebookEditTool], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("notebook_edit_tool")
    @ExcludeMissing
    fun _notebookEditTool(): JsonField<AnalyticsToolActionCounts> = notebookEditTool

    /**
     * Returns the raw JSON value of [writeTool].
     *
     * Unlike [writeTool], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("write_tool")
    @ExcludeMissing
    fun _writeTool(): JsonField<AnalyticsToolActionCounts> = writeTool

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
         * Returns a mutable builder for constructing an instance of [AnalyticsToolActions].
         *
         * The following fields are required:
         * ```java
         * .editTool()
         * .multiEditTool()
         * .notebookEditTool()
         * .writeTool()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [AnalyticsToolActions]. */
    class Builder internal constructor() {

        private var editTool: JsonField<AnalyticsToolActionCounts>? = null
        private var multiEditTool: JsonField<AnalyticsToolActionCounts>? = null
        private var notebookEditTool: JsonField<AnalyticsToolActionCounts>? = null
        private var writeTool: JsonField<AnalyticsToolActionCounts>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(analyticsToolActions: AnalyticsToolActions) = apply {
            editTool = analyticsToolActions.editTool
            multiEditTool = analyticsToolActions.multiEditTool
            notebookEditTool = analyticsToolActions.notebookEditTool
            writeTool = analyticsToolActions.writeTool
            additionalProperties = analyticsToolActions.additionalProperties.toMutableMap()
        }

        /** Accepted/rejected counts for a single Claude Code tool type. */
        fun editTool(editTool: AnalyticsToolActionCounts) = editTool(JsonField.of(editTool))

        /**
         * Sets [Builder.editTool] to an arbitrary JSON value.
         *
         * You should usually call [Builder.editTool] with a well-typed [AnalyticsToolActionCounts]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun editTool(editTool: JsonField<AnalyticsToolActionCounts>) = apply {
            this.editTool = editTool
        }

        /** Accepted/rejected counts for a single Claude Code tool type. */
        fun multiEditTool(multiEditTool: AnalyticsToolActionCounts) =
            multiEditTool(JsonField.of(multiEditTool))

        /**
         * Sets [Builder.multiEditTool] to an arbitrary JSON value.
         *
         * You should usually call [Builder.multiEditTool] with a well-typed
         * [AnalyticsToolActionCounts] value instead. This method is primarily for setting the field
         * to an undocumented or not yet supported value.
         */
        fun multiEditTool(multiEditTool: JsonField<AnalyticsToolActionCounts>) = apply {
            this.multiEditTool = multiEditTool
        }

        /** Accepted/rejected counts for a single Claude Code tool type. */
        fun notebookEditTool(notebookEditTool: AnalyticsToolActionCounts) =
            notebookEditTool(JsonField.of(notebookEditTool))

        /**
         * Sets [Builder.notebookEditTool] to an arbitrary JSON value.
         *
         * You should usually call [Builder.notebookEditTool] with a well-typed
         * [AnalyticsToolActionCounts] value instead. This method is primarily for setting the field
         * to an undocumented or not yet supported value.
         */
        fun notebookEditTool(notebookEditTool: JsonField<AnalyticsToolActionCounts>) = apply {
            this.notebookEditTool = notebookEditTool
        }

        /** Accepted/rejected counts for a single Claude Code tool type. */
        fun writeTool(writeTool: AnalyticsToolActionCounts) = writeTool(JsonField.of(writeTool))

        /**
         * Sets [Builder.writeTool] to an arbitrary JSON value.
         *
         * You should usually call [Builder.writeTool] with a well-typed [AnalyticsToolActionCounts]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun writeTool(writeTool: JsonField<AnalyticsToolActionCounts>) = apply {
            this.writeTool = writeTool
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
         * Returns an immutable instance of [AnalyticsToolActions].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .editTool()
         * .multiEditTool()
         * .notebookEditTool()
         * .writeTool()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): AnalyticsToolActions =
            AnalyticsToolActions(
                checkRequired("editTool", editTool),
                checkRequired("multiEditTool", multiEditTool),
                checkRequired("notebookEditTool", notebookEditTool),
                checkRequired("writeTool", writeTool),
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
    fun validate(): AnalyticsToolActions = apply {
        if (validated) {
            return@apply
        }

        editTool().validate()
        multiEditTool().validate()
        notebookEditTool().validate()
        writeTool().validate()
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
        (editTool.asKnown().getOrNull()?.validity() ?: 0) +
            (multiEditTool.asKnown().getOrNull()?.validity() ?: 0) +
            (notebookEditTool.asKnown().getOrNull()?.validity() ?: 0) +
            (writeTool.asKnown().getOrNull()?.validity() ?: 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is AnalyticsToolActions &&
            editTool == other.editTool &&
            multiEditTool == other.multiEditTool &&
            notebookEditTool == other.notebookEditTool &&
            writeTool == other.writeTool &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(editTool, multiEditTool, notebookEditTool, writeTool, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "AnalyticsToolActions{editTool=$editTool, multiEditTool=$multiEditTool, notebookEditTool=$notebookEditTool, writeTool=$writeTool, additionalProperties=$additionalProperties}"
}
