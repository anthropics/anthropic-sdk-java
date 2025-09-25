// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkKnown
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

class BetaTextEditorCodeExecutionStrReplaceResultBlockParam
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val type: JsonValue,
    private val lines: JsonField<List<String>>,
    private val newLines: JsonField<Long>,
    private val newStart: JsonField<Long>,
    private val oldLines: JsonField<Long>,
    private val oldStart: JsonField<Long>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("lines") @ExcludeMissing lines: JsonField<List<String>> = JsonMissing.of(),
        @JsonProperty("new_lines") @ExcludeMissing newLines: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("new_start") @ExcludeMissing newStart: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("old_lines") @ExcludeMissing oldLines: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("old_start") @ExcludeMissing oldStart: JsonField<Long> = JsonMissing.of(),
    ) : this(type, lines, newLines, newStart, oldLines, oldStart, mutableMapOf())

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("text_editor_code_execution_str_replace_result")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun lines(): Optional<List<String>> = lines.getOptional("lines")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun newLines(): Optional<Long> = newLines.getOptional("new_lines")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun newStart(): Optional<Long> = newStart.getOptional("new_start")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun oldLines(): Optional<Long> = oldLines.getOptional("old_lines")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun oldStart(): Optional<Long> = oldStart.getOptional("old_start")

    /**
     * Returns the raw JSON value of [lines].
     *
     * Unlike [lines], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("lines") @ExcludeMissing fun _lines(): JsonField<List<String>> = lines

    /**
     * Returns the raw JSON value of [newLines].
     *
     * Unlike [newLines], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("new_lines") @ExcludeMissing fun _newLines(): JsonField<Long> = newLines

    /**
     * Returns the raw JSON value of [newStart].
     *
     * Unlike [newStart], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("new_start") @ExcludeMissing fun _newStart(): JsonField<Long> = newStart

    /**
     * Returns the raw JSON value of [oldLines].
     *
     * Unlike [oldLines], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("old_lines") @ExcludeMissing fun _oldLines(): JsonField<Long> = oldLines

    /**
     * Returns the raw JSON value of [oldStart].
     *
     * Unlike [oldStart], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("old_start") @ExcludeMissing fun _oldStart(): JsonField<Long> = oldStart

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
         * [BetaTextEditorCodeExecutionStrReplaceResultBlockParam].
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaTextEditorCodeExecutionStrReplaceResultBlockParam]. */
    class Builder internal constructor() {

        private var type: JsonValue =
            JsonValue.from("text_editor_code_execution_str_replace_result")
        private var lines: JsonField<MutableList<String>>? = null
        private var newLines: JsonField<Long> = JsonMissing.of()
        private var newStart: JsonField<Long> = JsonMissing.of()
        private var oldLines: JsonField<Long> = JsonMissing.of()
        private var oldStart: JsonField<Long> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaTextEditorCodeExecutionStrReplaceResultBlockParam:
                BetaTextEditorCodeExecutionStrReplaceResultBlockParam
        ) = apply {
            type = betaTextEditorCodeExecutionStrReplaceResultBlockParam.type
            lines =
                betaTextEditorCodeExecutionStrReplaceResultBlockParam.lines.map {
                    it.toMutableList()
                }
            newLines = betaTextEditorCodeExecutionStrReplaceResultBlockParam.newLines
            newStart = betaTextEditorCodeExecutionStrReplaceResultBlockParam.newStart
            oldLines = betaTextEditorCodeExecutionStrReplaceResultBlockParam.oldLines
            oldStart = betaTextEditorCodeExecutionStrReplaceResultBlockParam.oldStart
            additionalProperties =
                betaTextEditorCodeExecutionStrReplaceResultBlockParam.additionalProperties
                    .toMutableMap()
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("text_editor_code_execution_str_replace_result")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        fun lines(lines: List<String>?) = lines(JsonField.ofNullable(lines))

        /** Alias for calling [Builder.lines] with `lines.orElse(null)`. */
        fun lines(lines: Optional<List<String>>) = lines(lines.getOrNull())

        /**
         * Sets [Builder.lines] to an arbitrary JSON value.
         *
         * You should usually call [Builder.lines] with a well-typed `List<String>` value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun lines(lines: JsonField<List<String>>) = apply {
            this.lines = lines.map { it.toMutableList() }
        }

        /**
         * Adds a single [String] to [lines].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addLine(line: String) = apply {
            lines =
                (lines ?: JsonField.of(mutableListOf())).also { checkKnown("lines", it).add(line) }
        }

        fun newLines(newLines: Long?) = newLines(JsonField.ofNullable(newLines))

        /**
         * Alias for [Builder.newLines].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun newLines(newLines: Long) = newLines(newLines as Long?)

        /** Alias for calling [Builder.newLines] with `newLines.orElse(null)`. */
        fun newLines(newLines: Optional<Long>) = newLines(newLines.getOrNull())

        /**
         * Sets [Builder.newLines] to an arbitrary JSON value.
         *
         * You should usually call [Builder.newLines] with a well-typed [Long] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun newLines(newLines: JsonField<Long>) = apply { this.newLines = newLines }

        fun newStart(newStart: Long?) = newStart(JsonField.ofNullable(newStart))

        /**
         * Alias for [Builder.newStart].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun newStart(newStart: Long) = newStart(newStart as Long?)

        /** Alias for calling [Builder.newStart] with `newStart.orElse(null)`. */
        fun newStart(newStart: Optional<Long>) = newStart(newStart.getOrNull())

        /**
         * Sets [Builder.newStart] to an arbitrary JSON value.
         *
         * You should usually call [Builder.newStart] with a well-typed [Long] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun newStart(newStart: JsonField<Long>) = apply { this.newStart = newStart }

        fun oldLines(oldLines: Long?) = oldLines(JsonField.ofNullable(oldLines))

        /**
         * Alias for [Builder.oldLines].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun oldLines(oldLines: Long) = oldLines(oldLines as Long?)

        /** Alias for calling [Builder.oldLines] with `oldLines.orElse(null)`. */
        fun oldLines(oldLines: Optional<Long>) = oldLines(oldLines.getOrNull())

        /**
         * Sets [Builder.oldLines] to an arbitrary JSON value.
         *
         * You should usually call [Builder.oldLines] with a well-typed [Long] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun oldLines(oldLines: JsonField<Long>) = apply { this.oldLines = oldLines }

        fun oldStart(oldStart: Long?) = oldStart(JsonField.ofNullable(oldStart))

        /**
         * Alias for [Builder.oldStart].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun oldStart(oldStart: Long) = oldStart(oldStart as Long?)

        /** Alias for calling [Builder.oldStart] with `oldStart.orElse(null)`. */
        fun oldStart(oldStart: Optional<Long>) = oldStart(oldStart.getOrNull())

        /**
         * Sets [Builder.oldStart] to an arbitrary JSON value.
         *
         * You should usually call [Builder.oldStart] with a well-typed [Long] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun oldStart(oldStart: JsonField<Long>) = apply { this.oldStart = oldStart }

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
         * Returns an immutable instance of [BetaTextEditorCodeExecutionStrReplaceResultBlockParam].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): BetaTextEditorCodeExecutionStrReplaceResultBlockParam =
            BetaTextEditorCodeExecutionStrReplaceResultBlockParam(
                type,
                (lines ?: JsonMissing.of()).map { it.toImmutable() },
                newLines,
                newStart,
                oldLines,
                oldStart,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): BetaTextEditorCodeExecutionStrReplaceResultBlockParam = apply {
        if (validated) {
            return@apply
        }

        _type().let {
            if (it != JsonValue.from("text_editor_code_execution_str_replace_result")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        lines()
        newLines()
        newStart()
        oldLines()
        oldStart()
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
        type.let {
            if (it == JsonValue.from("text_editor_code_execution_str_replace_result")) 1 else 0
        } +
            (lines.asKnown().getOrNull()?.size ?: 0) +
            (if (newLines.asKnown().isPresent) 1 else 0) +
            (if (newStart.asKnown().isPresent) 1 else 0) +
            (if (oldLines.asKnown().isPresent) 1 else 0) +
            (if (oldStart.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaTextEditorCodeExecutionStrReplaceResultBlockParam &&
            type == other.type &&
            lines == other.lines &&
            newLines == other.newLines &&
            newStart == other.newStart &&
            oldLines == other.oldLines &&
            oldStart == other.oldStart &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(type, lines, newLines, newStart, oldLines, oldStart, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaTextEditorCodeExecutionStrReplaceResultBlockParam{type=$type, lines=$lines, newLines=$newLines, newStart=$newStart, oldLines=$oldLines, oldStart=$oldStart, additionalProperties=$additionalProperties}"
}
