// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkKnown
import com.anthropic.core.checkRequired
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

class TextEditorCodeExecutionStrReplaceResultBlock
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val lines: JsonField<List<String>>,
    private val newLines: JsonField<Long>,
    private val newStart: JsonField<Long>,
    private val oldLines: JsonField<Long>,
    private val oldStart: JsonField<Long>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("lines") @ExcludeMissing lines: JsonField<List<String>> = JsonMissing.of(),
        @JsonProperty("new_lines") @ExcludeMissing newLines: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("new_start") @ExcludeMissing newStart: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("old_lines") @ExcludeMissing oldLines: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("old_start") @ExcludeMissing oldStart: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(lines, newLines, newStart, oldLines, oldStart, type, mutableMapOf())

    fun toParam(): TextEditorCodeExecutionStrReplaceResultBlockParam =
        TextEditorCodeExecutionStrReplaceResultBlockParam.builder()
            .lines(_lines())
            .newLines(_newLines())
            .newStart(_newStart())
            .oldLines(_oldLines())
            .oldStart(_oldStart())
            .build()

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
         * [TextEditorCodeExecutionStrReplaceResultBlock].
         *
         * The following fields are required:
         * ```java
         * .lines()
         * .newLines()
         * .newStart()
         * .oldLines()
         * .oldStart()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [TextEditorCodeExecutionStrReplaceResultBlock]. */
    class Builder internal constructor() {

        private var lines: JsonField<MutableList<String>>? = null
        private var newLines: JsonField<Long>? = null
        private var newStart: JsonField<Long>? = null
        private var oldLines: JsonField<Long>? = null
        private var oldStart: JsonField<Long>? = null
        private var type: JsonValue =
            JsonValue.from("text_editor_code_execution_str_replace_result")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            textEditorCodeExecutionStrReplaceResultBlock:
                TextEditorCodeExecutionStrReplaceResultBlock
        ) = apply {
            lines = textEditorCodeExecutionStrReplaceResultBlock.lines.map { it.toMutableList() }
            newLines = textEditorCodeExecutionStrReplaceResultBlock.newLines
            newStart = textEditorCodeExecutionStrReplaceResultBlock.newStart
            oldLines = textEditorCodeExecutionStrReplaceResultBlock.oldLines
            oldStart = textEditorCodeExecutionStrReplaceResultBlock.oldStart
            type = textEditorCodeExecutionStrReplaceResultBlock.type
            additionalProperties =
                textEditorCodeExecutionStrReplaceResultBlock.additionalProperties.toMutableMap()
        }

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
         * Returns an immutable instance of [TextEditorCodeExecutionStrReplaceResultBlock].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .lines()
         * .newLines()
         * .newStart()
         * .oldLines()
         * .oldStart()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): TextEditorCodeExecutionStrReplaceResultBlock =
            TextEditorCodeExecutionStrReplaceResultBlock(
                checkRequired("lines", lines).map { it.toImmutable() },
                checkRequired("newLines", newLines),
                checkRequired("newStart", newStart),
                checkRequired("oldLines", oldLines),
                checkRequired("oldStart", oldStart),
                type,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): TextEditorCodeExecutionStrReplaceResultBlock = apply {
        if (validated) {
            return@apply
        }

        lines()
        newLines()
        newStart()
        oldLines()
        oldStart()
        _type().let {
            if (it != JsonValue.from("text_editor_code_execution_str_replace_result")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
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
        (lines.asKnown().getOrNull()?.size ?: 0) +
            (if (newLines.asKnown().isPresent) 1 else 0) +
            (if (newStart.asKnown().isPresent) 1 else 0) +
            (if (oldLines.asKnown().isPresent) 1 else 0) +
            (if (oldStart.asKnown().isPresent) 1 else 0) +
            type.let {
                if (it == JsonValue.from("text_editor_code_execution_str_replace_result")) 1 else 0
            }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is TextEditorCodeExecutionStrReplaceResultBlock &&
            lines == other.lines &&
            newLines == other.newLines &&
            newStart == other.newStart &&
            oldLines == other.oldLines &&
            oldStart == other.oldStart &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(lines, newLines, newStart, oldLines, oldStart, type, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "TextEditorCodeExecutionStrReplaceResultBlock{lines=$lines, newLines=$newLines, newStart=$newStart, oldLines=$oldLines, oldStart=$oldStart, type=$type, additionalProperties=$additionalProperties}"
}
