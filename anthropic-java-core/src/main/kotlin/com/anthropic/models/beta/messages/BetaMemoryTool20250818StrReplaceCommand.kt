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

class BetaMemoryTool20250818StrReplaceCommand
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val command: JsonValue,
    private val newStr: JsonField<String>,
    private val oldStr: JsonField<String>,
    private val path: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("command") @ExcludeMissing command: JsonValue = JsonMissing.of(),
        @JsonProperty("new_str") @ExcludeMissing newStr: JsonField<String> = JsonMissing.of(),
        @JsonProperty("old_str") @ExcludeMissing oldStr: JsonField<String> = JsonMissing.of(),
        @JsonProperty("path") @ExcludeMissing path: JsonField<String> = JsonMissing.of(),
    ) : this(command, newStr, oldStr, path, mutableMapOf())

    /**
     * Command type identifier
     *
     * Expected to always return the following:
     * ```java
     * JsonValue.from("str_replace")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("command") @ExcludeMissing fun _command(): JsonValue = command

    /**
     * Text to replace with
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun newStr(): String = newStr.getRequired("new_str")

    /**
     * Text to search for and replace
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun oldStr(): String = oldStr.getRequired("old_str")

    /**
     * Path to the file where text should be replaced
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun path(): String = path.getRequired("path")

    /**
     * Returns the raw JSON value of [newStr].
     *
     * Unlike [newStr], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("new_str") @ExcludeMissing fun _newStr(): JsonField<String> = newStr

    /**
     * Returns the raw JSON value of [oldStr].
     *
     * Unlike [oldStr], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("old_str") @ExcludeMissing fun _oldStr(): JsonField<String> = oldStr

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
         * [BetaMemoryTool20250818StrReplaceCommand].
         *
         * The following fields are required:
         * ```java
         * .newStr()
         * .oldStr()
         * .path()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaMemoryTool20250818StrReplaceCommand]. */
    class Builder internal constructor() {

        private var command: JsonValue = JsonValue.from("str_replace")
        private var newStr: JsonField<String>? = null
        private var oldStr: JsonField<String>? = null
        private var path: JsonField<String>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaMemoryTool20250818StrReplaceCommand: BetaMemoryTool20250818StrReplaceCommand
        ) = apply {
            command = betaMemoryTool20250818StrReplaceCommand.command
            newStr = betaMemoryTool20250818StrReplaceCommand.newStr
            oldStr = betaMemoryTool20250818StrReplaceCommand.oldStr
            path = betaMemoryTool20250818StrReplaceCommand.path
            additionalProperties =
                betaMemoryTool20250818StrReplaceCommand.additionalProperties.toMutableMap()
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("str_replace")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun command(command: JsonValue) = apply { this.command = command }

        /** Text to replace with */
        fun newStr(newStr: String) = newStr(JsonField.of(newStr))

        /**
         * Sets [Builder.newStr] to an arbitrary JSON value.
         *
         * You should usually call [Builder.newStr] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun newStr(newStr: JsonField<String>) = apply { this.newStr = newStr }

        /** Text to search for and replace */
        fun oldStr(oldStr: String) = oldStr(JsonField.of(oldStr))

        /**
         * Sets [Builder.oldStr] to an arbitrary JSON value.
         *
         * You should usually call [Builder.oldStr] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun oldStr(oldStr: JsonField<String>) = apply { this.oldStr = oldStr }

        /** Path to the file where text should be replaced */
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
         * Returns an immutable instance of [BetaMemoryTool20250818StrReplaceCommand].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .newStr()
         * .oldStr()
         * .path()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaMemoryTool20250818StrReplaceCommand =
            BetaMemoryTool20250818StrReplaceCommand(
                command,
                checkRequired("newStr", newStr),
                checkRequired("oldStr", oldStr),
                checkRequired("path", path),
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): BetaMemoryTool20250818StrReplaceCommand = apply {
        if (validated) {
            return@apply
        }

        _command().let {
            if (it != JsonValue.from("str_replace")) {
                throw AnthropicInvalidDataException("'command' is invalid, received $it")
            }
        }
        newStr()
        oldStr()
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
        command.let { if (it == JsonValue.from("str_replace")) 1 else 0 } +
            (if (newStr.asKnown().isPresent) 1 else 0) +
            (if (oldStr.asKnown().isPresent) 1 else 0) +
            (if (path.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaMemoryTool20250818StrReplaceCommand &&
            command == other.command &&
            newStr == other.newStr &&
            oldStr == other.oldStr &&
            path == other.path &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(command, newStr, oldStr, path, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaMemoryTool20250818StrReplaceCommand{command=$command, newStr=$newStr, oldStr=$oldStr, path=$path, additionalProperties=$additionalProperties}"
}
