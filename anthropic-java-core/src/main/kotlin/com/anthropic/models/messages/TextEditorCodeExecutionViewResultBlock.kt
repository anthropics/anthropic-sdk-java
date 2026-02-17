// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.Enum
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

class TextEditorCodeExecutionViewResultBlock
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val content: JsonField<String>,
    private val fileType: JsonField<FileType>,
    private val numLines: JsonField<Long>,
    private val startLine: JsonField<Long>,
    private val totalLines: JsonField<Long>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("content") @ExcludeMissing content: JsonField<String> = JsonMissing.of(),
        @JsonProperty("file_type") @ExcludeMissing fileType: JsonField<FileType> = JsonMissing.of(),
        @JsonProperty("num_lines") @ExcludeMissing numLines: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("start_line") @ExcludeMissing startLine: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("total_lines") @ExcludeMissing totalLines: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(content, fileType, numLines, startLine, totalLines, type, mutableMapOf())

    fun toParam(): TextEditorCodeExecutionViewResultBlockParam =
        TextEditorCodeExecutionViewResultBlockParam.builder()
            .content(_content())
            .fileType(
                _fileType().map {
                    TextEditorCodeExecutionViewResultBlockParam.FileType.of(it.toString())
                }
            )
            .numLines(_numLines())
            .startLine(_startLine())
            .totalLines(_totalLines())
            .build()

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun content(): String = content.getRequired("content")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun fileType(): FileType = fileType.getRequired("file_type")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun numLines(): Optional<Long> = numLines.getOptional("num_lines")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun startLine(): Optional<Long> = startLine.getOptional("start_line")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun totalLines(): Optional<Long> = totalLines.getOptional("total_lines")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("text_editor_code_execution_view_result")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Returns the raw JSON value of [content].
     *
     * Unlike [content], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("content") @ExcludeMissing fun _content(): JsonField<String> = content

    /**
     * Returns the raw JSON value of [fileType].
     *
     * Unlike [fileType], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("file_type") @ExcludeMissing fun _fileType(): JsonField<FileType> = fileType

    /**
     * Returns the raw JSON value of [numLines].
     *
     * Unlike [numLines], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("num_lines") @ExcludeMissing fun _numLines(): JsonField<Long> = numLines

    /**
     * Returns the raw JSON value of [startLine].
     *
     * Unlike [startLine], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("start_line") @ExcludeMissing fun _startLine(): JsonField<Long> = startLine

    /**
     * Returns the raw JSON value of [totalLines].
     *
     * Unlike [totalLines], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("total_lines") @ExcludeMissing fun _totalLines(): JsonField<Long> = totalLines

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
         * [TextEditorCodeExecutionViewResultBlock].
         *
         * The following fields are required:
         * ```java
         * .content()
         * .fileType()
         * .numLines()
         * .startLine()
         * .totalLines()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [TextEditorCodeExecutionViewResultBlock]. */
    class Builder internal constructor() {

        private var content: JsonField<String>? = null
        private var fileType: JsonField<FileType>? = null
        private var numLines: JsonField<Long>? = null
        private var startLine: JsonField<Long>? = null
        private var totalLines: JsonField<Long>? = null
        private var type: JsonValue = JsonValue.from("text_editor_code_execution_view_result")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            textEditorCodeExecutionViewResultBlock: TextEditorCodeExecutionViewResultBlock
        ) = apply {
            content = textEditorCodeExecutionViewResultBlock.content
            fileType = textEditorCodeExecutionViewResultBlock.fileType
            numLines = textEditorCodeExecutionViewResultBlock.numLines
            startLine = textEditorCodeExecutionViewResultBlock.startLine
            totalLines = textEditorCodeExecutionViewResultBlock.totalLines
            type = textEditorCodeExecutionViewResultBlock.type
            additionalProperties =
                textEditorCodeExecutionViewResultBlock.additionalProperties.toMutableMap()
        }

        fun content(content: String) = content(JsonField.of(content))

        /**
         * Sets [Builder.content] to an arbitrary JSON value.
         *
         * You should usually call [Builder.content] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun content(content: JsonField<String>) = apply { this.content = content }

        fun fileType(fileType: FileType) = fileType(JsonField.of(fileType))

        /**
         * Sets [Builder.fileType] to an arbitrary JSON value.
         *
         * You should usually call [Builder.fileType] with a well-typed [FileType] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun fileType(fileType: JsonField<FileType>) = apply { this.fileType = fileType }

        fun numLines(numLines: Long?) = numLines(JsonField.ofNullable(numLines))

        /**
         * Alias for [Builder.numLines].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun numLines(numLines: Long) = numLines(numLines as Long?)

        /** Alias for calling [Builder.numLines] with `numLines.orElse(null)`. */
        fun numLines(numLines: Optional<Long>) = numLines(numLines.getOrNull())

        /**
         * Sets [Builder.numLines] to an arbitrary JSON value.
         *
         * You should usually call [Builder.numLines] with a well-typed [Long] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun numLines(numLines: JsonField<Long>) = apply { this.numLines = numLines }

        fun startLine(startLine: Long?) = startLine(JsonField.ofNullable(startLine))

        /**
         * Alias for [Builder.startLine].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun startLine(startLine: Long) = startLine(startLine as Long?)

        /** Alias for calling [Builder.startLine] with `startLine.orElse(null)`. */
        fun startLine(startLine: Optional<Long>) = startLine(startLine.getOrNull())

        /**
         * Sets [Builder.startLine] to an arbitrary JSON value.
         *
         * You should usually call [Builder.startLine] with a well-typed [Long] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun startLine(startLine: JsonField<Long>) = apply { this.startLine = startLine }

        fun totalLines(totalLines: Long?) = totalLines(JsonField.ofNullable(totalLines))

        /**
         * Alias for [Builder.totalLines].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun totalLines(totalLines: Long) = totalLines(totalLines as Long?)

        /** Alias for calling [Builder.totalLines] with `totalLines.orElse(null)`. */
        fun totalLines(totalLines: Optional<Long>) = totalLines(totalLines.getOrNull())

        /**
         * Sets [Builder.totalLines] to an arbitrary JSON value.
         *
         * You should usually call [Builder.totalLines] with a well-typed [Long] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun totalLines(totalLines: JsonField<Long>) = apply { this.totalLines = totalLines }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("text_editor_code_execution_view_result")
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
         * Returns an immutable instance of [TextEditorCodeExecutionViewResultBlock].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .content()
         * .fileType()
         * .numLines()
         * .startLine()
         * .totalLines()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): TextEditorCodeExecutionViewResultBlock =
            TextEditorCodeExecutionViewResultBlock(
                checkRequired("content", content),
                checkRequired("fileType", fileType),
                checkRequired("numLines", numLines),
                checkRequired("startLine", startLine),
                checkRequired("totalLines", totalLines),
                type,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): TextEditorCodeExecutionViewResultBlock = apply {
        if (validated) {
            return@apply
        }

        content()
        fileType().validate()
        numLines()
        startLine()
        totalLines()
        _type().let {
            if (it != JsonValue.from("text_editor_code_execution_view_result")) {
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
        (if (content.asKnown().isPresent) 1 else 0) +
            (fileType.asKnown().getOrNull()?.validity() ?: 0) +
            (if (numLines.asKnown().isPresent) 1 else 0) +
            (if (startLine.asKnown().isPresent) 1 else 0) +
            (if (totalLines.asKnown().isPresent) 1 else 0) +
            type.let {
                if (it == JsonValue.from("text_editor_code_execution_view_result")) 1 else 0
            }

    class FileType @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

        /**
         * Returns this class instance's raw value.
         *
         * This is usually only useful if this instance was deserialized from data that doesn't
         * match any known member, and you want to know that value. For example, if the SDK is on an
         * older version than the API, then the API may respond with new members that the SDK is
         * unaware of.
         */
        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            @JvmField val TEXT = of("text")

            @JvmField val IMAGE = of("image")

            @JvmField val PDF = of("pdf")

            @JvmStatic fun of(value: String) = FileType(JsonField.of(value))
        }

        /** An enum containing [FileType]'s known values. */
        enum class Known {
            TEXT,
            IMAGE,
            PDF,
        }

        /**
         * An enum containing [FileType]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [FileType] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            TEXT,
            IMAGE,
            PDF,
            /** An enum member indicating that [FileType] was instantiated with an unknown value. */
            _UNKNOWN,
        }

        /**
         * Returns an enum member corresponding to this class instance's value, or [Value._UNKNOWN]
         * if the class was instantiated with an unknown value.
         *
         * Use the [known] method instead if you're certain the value is always known or if you want
         * to throw for the unknown case.
         */
        fun value(): Value =
            when (this) {
                TEXT -> Value.TEXT
                IMAGE -> Value.IMAGE
                PDF -> Value.PDF
                else -> Value._UNKNOWN
            }

        /**
         * Returns an enum member corresponding to this class instance's value.
         *
         * Use the [value] method instead if you're uncertain the value is always known and don't
         * want to throw for the unknown case.
         *
         * @throws AnthropicInvalidDataException if this class instance's value is a not a known
         *   member.
         */
        fun known(): Known =
            when (this) {
                TEXT -> Known.TEXT
                IMAGE -> Known.IMAGE
                PDF -> Known.PDF
                else -> throw AnthropicInvalidDataException("Unknown FileType: $value")
            }

        /**
         * Returns this class instance's primitive wire representation.
         *
         * This differs from the [toString] method because that method is primarily for debugging
         * and generally doesn't throw.
         *
         * @throws AnthropicInvalidDataException if this class instance's value does not have the
         *   expected primitive type.
         */
        fun asString(): String =
            _value().asString().orElseThrow {
                AnthropicInvalidDataException("Value is not a String")
            }

        private var validated: Boolean = false

        fun validate(): FileType = apply {
            if (validated) {
                return@apply
            }

            known()
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
        @JvmSynthetic internal fun validity(): Int = if (value() == Value._UNKNOWN) 0 else 1

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is FileType && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is TextEditorCodeExecutionViewResultBlock &&
            content == other.content &&
            fileType == other.fileType &&
            numLines == other.numLines &&
            startLine == other.startLine &&
            totalLines == other.totalLines &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(content, fileType, numLines, startLine, totalLines, type, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "TextEditorCodeExecutionViewResultBlock{content=$content, fileType=$fileType, numLines=$numLines, startLine=$startLine, totalLines=$totalLines, type=$type, additionalProperties=$additionalProperties}"
}
