// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

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

class BetaMemoryTool20250818InsertCommand
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val command: JsonValue,
    private val insertLine: JsonField<Long>,
    private val insertText: JsonField<String>,
    private val path: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("command") @ExcludeMissing command: JsonValue = JsonMissing.of(),
        @JsonProperty("insert_line") @ExcludeMissing insertLine: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("insert_text")
        @ExcludeMissing
        insertText: JsonField<String> = JsonMissing.of(),
        @JsonProperty("path") @ExcludeMissing path: JsonField<String> = JsonMissing.of(),
    ) : this(command, insertLine, insertText, path, mutableMapOf())

    /**
     * Command type identifier
     *
     * Expected to always return the following:
     * ```java
     * JsonValue.from("insert")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("command") @ExcludeMissing fun _command(): JsonValue = command

    /**
     * Line number where text should be inserted
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun insertLine(): Long = insertLine.getRequired("insert_line")

    /**
     * Text to insert at the specified line
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun insertText(): String = insertText.getRequired("insert_text")

    /**
     * Path to the file where text should be inserted
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun path(): String = path.getRequired("path")

    /**
     * Returns the raw JSON value of [insertLine].
     *
     * Unlike [insertLine], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("insert_line") @ExcludeMissing fun _insertLine(): JsonField<Long> = insertLine

    /**
     * Returns the raw JSON value of [insertText].
     *
     * Unlike [insertText], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("insert_text") @ExcludeMissing fun _insertText(): JsonField<String> = insertText

    /**
     * Returns the raw JSON value of [path].
     *
     * Unlike [path], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("path") @ExcludeMissing fun _path(): JsonField<String> = path

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
         * [BetaMemoryTool20250818InsertCommand].
         *
         * The following fields are required:
         * ```java
         * .insertLine()
         * .insertText()
         * .path()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaMemoryTool20250818InsertCommand]. */
    class Builder internal constructor() {

        private var command: JsonValue = JsonValue.from("insert")
        private var insertLine: JsonField<Long>? = null
        private var insertText: JsonField<String>? = null
        private var path: JsonField<String>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaMemoryTool20250818InsertCommand: BetaMemoryTool20250818InsertCommand
        ) = apply {
            command = betaMemoryTool20250818InsertCommand.command
            insertLine = betaMemoryTool20250818InsertCommand.insertLine
            insertText = betaMemoryTool20250818InsertCommand.insertText
            path = betaMemoryTool20250818InsertCommand.path
            additionalProperties =
                betaMemoryTool20250818InsertCommand.additionalProperties.toMutableMap()
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("insert")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun command(command: JsonValue) = apply { this.command = command }

        /** Line number where text should be inserted */
        fun insertLine(insertLine: Long) = insertLine(JsonField.of(insertLine))

        /**
         * Sets [Builder.insertLine] to an arbitrary JSON value.
         *
         * You should usually call [Builder.insertLine] with a well-typed [Long] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun insertLine(insertLine: JsonField<Long>) = apply { this.insertLine = insertLine }

        /** Text to insert at the specified line */
        fun insertText(insertText: String) = insertText(JsonField.of(insertText))

        /**
         * Sets [Builder.insertText] to an arbitrary JSON value.
         *
         * You should usually call [Builder.insertText] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun insertText(insertText: JsonField<String>) = apply { this.insertText = insertText }

        /** Path to the file where text should be inserted */
        fun path(path: String) = path(JsonField.of(path))

        /**
         * Sets [Builder.path] to an arbitrary JSON value.
         *
         * You should usually call [Builder.path] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun path(path: JsonField<String>) = apply { this.path = path }

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
         * Returns an immutable instance of [BetaMemoryTool20250818InsertCommand].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .insertLine()
         * .insertText()
         * .path()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaMemoryTool20250818InsertCommand =
            BetaMemoryTool20250818InsertCommand(
                command,
                checkRequired("insertLine", insertLine),
                checkRequired("insertText", insertText),
                checkRequired("path", path),
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): BetaMemoryTool20250818InsertCommand = apply {
        if (validated) {
            return@apply
        }

        _command().let {
            if (it != JsonValue.from("insert")) {
                throw AnthropicInvalidDataException("'command' is invalid, received $it")
            }
        }
        insertLine()
        insertText()
        path()
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
        command.let { if (it == JsonValue.from("insert")) 1 else 0 } +
            (if (insertLine.asKnown().isPresent) 1 else 0) +
            (if (insertText.asKnown().isPresent) 1 else 0) +
            (if (path.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaMemoryTool20250818InsertCommand &&
            command == other.command &&
            insertLine == other.insertLine &&
            insertText == other.insertText &&
            path == other.path &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(command, insertLine, insertText, path, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaMemoryTool20250818InsertCommand{command=$command, insertLine=$insertLine, insertText=$insertText, path=$path, additionalProperties=$additionalProperties}"
}
