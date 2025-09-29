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

class BetaMemoryTool20250818CreateCommand
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val command: JsonValue,
    private val fileText: JsonField<String>,
    private val path: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("command") @ExcludeMissing command: JsonValue = JsonMissing.of(),
        @JsonProperty("file_text") @ExcludeMissing fileText: JsonField<String> = JsonMissing.of(),
        @JsonProperty("path") @ExcludeMissing path: JsonField<String> = JsonMissing.of(),
    ) : this(command, fileText, path, mutableMapOf())

    /**
     * Command type identifier
     *
     * Expected to always return the following:
     * ```java
     * JsonValue.from("create")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("command") @ExcludeMissing fun _command(): JsonValue = command

    /**
     * Content to write to the file
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun fileText(): String = fileText.getRequired("file_text")

    /**
     * Path where the file should be created
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun path(): String = path.getRequired("path")

    /**
     * Returns the raw JSON value of [fileText].
     *
     * Unlike [fileText], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("file_text") @ExcludeMissing fun _fileText(): JsonField<String> = fileText

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
         * [BetaMemoryTool20250818CreateCommand].
         *
         * The following fields are required:
         * ```java
         * .fileText()
         * .path()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaMemoryTool20250818CreateCommand]. */
    class Builder internal constructor() {

        private var command: JsonValue = JsonValue.from("create")
        private var fileText: JsonField<String>? = null
        private var path: JsonField<String>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaMemoryTool20250818CreateCommand: BetaMemoryTool20250818CreateCommand
        ) = apply {
            command = betaMemoryTool20250818CreateCommand.command
            fileText = betaMemoryTool20250818CreateCommand.fileText
            path = betaMemoryTool20250818CreateCommand.path
            additionalProperties =
                betaMemoryTool20250818CreateCommand.additionalProperties.toMutableMap()
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("create")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun command(command: JsonValue) = apply { this.command = command }

        /** Content to write to the file */
        fun fileText(fileText: String) = fileText(JsonField.of(fileText))

        /**
         * Sets [Builder.fileText] to an arbitrary JSON value.
         *
         * You should usually call [Builder.fileText] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun fileText(fileText: JsonField<String>) = apply { this.fileText = fileText }

        /** Path where the file should be created */
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
         * Returns an immutable instance of [BetaMemoryTool20250818CreateCommand].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .fileText()
         * .path()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaMemoryTool20250818CreateCommand =
            BetaMemoryTool20250818CreateCommand(
                command,
                checkRequired("fileText", fileText),
                checkRequired("path", path),
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): BetaMemoryTool20250818CreateCommand = apply {
        if (validated) {
            return@apply
        }

        _command().let {
            if (it != JsonValue.from("create")) {
                throw AnthropicInvalidDataException("'command' is invalid, received $it")
            }
        }
        fileText()
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
        command.let { if (it == JsonValue.from("create")) 1 else 0 } +
            (if (fileText.asKnown().isPresent) 1 else 0) +
            (if (path.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaMemoryTool20250818CreateCommand &&
            command == other.command &&
            fileText == other.fileText &&
            path == other.path &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(command, fileText, path, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaMemoryTool20250818CreateCommand{command=$command, fileText=$fileText, path=$path, additionalProperties=$additionalProperties}"
}
