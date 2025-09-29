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

class BetaMemoryTool20250818RenameCommand
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val command: JsonValue,
    private val newPath: JsonField<String>,
    private val oldPath: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("command") @ExcludeMissing command: JsonValue = JsonMissing.of(),
        @JsonProperty("new_path") @ExcludeMissing newPath: JsonField<String> = JsonMissing.of(),
        @JsonProperty("old_path") @ExcludeMissing oldPath: JsonField<String> = JsonMissing.of(),
    ) : this(command, newPath, oldPath, mutableMapOf())

    /**
     * Command type identifier
     *
     * Expected to always return the following:
     * ```java
     * JsonValue.from("rename")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("command") @ExcludeMissing fun _command(): JsonValue = command

    /**
     * New path for the file or directory
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun newPath(): String = newPath.getRequired("new_path")

    /**
     * Current path of the file or directory
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun oldPath(): String = oldPath.getRequired("old_path")

    /**
     * Returns the raw JSON value of [newPath].
     *
     * Unlike [newPath], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("new_path") @ExcludeMissing fun _newPath(): JsonField<String> = newPath

    /**
     * Returns the raw JSON value of [oldPath].
     *
     * Unlike [oldPath], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("old_path") @ExcludeMissing fun _oldPath(): JsonField<String> = oldPath

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
         * [BetaMemoryTool20250818RenameCommand].
         *
         * The following fields are required:
         * ```java
         * .newPath()
         * .oldPath()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaMemoryTool20250818RenameCommand]. */
    class Builder internal constructor() {

        private var command: JsonValue = JsonValue.from("rename")
        private var newPath: JsonField<String>? = null
        private var oldPath: JsonField<String>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaMemoryTool20250818RenameCommand: BetaMemoryTool20250818RenameCommand
        ) = apply {
            command = betaMemoryTool20250818RenameCommand.command
            newPath = betaMemoryTool20250818RenameCommand.newPath
            oldPath = betaMemoryTool20250818RenameCommand.oldPath
            additionalProperties =
                betaMemoryTool20250818RenameCommand.additionalProperties.toMutableMap()
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("rename")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun command(command: JsonValue) = apply { this.command = command }

        /** New path for the file or directory */
        fun newPath(newPath: String) = newPath(JsonField.of(newPath))

        /**
         * Sets [Builder.newPath] to an arbitrary JSON value.
         *
         * You should usually call [Builder.newPath] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun newPath(newPath: JsonField<String>) = apply { this.newPath = newPath }

        /** Current path of the file or directory */
        fun oldPath(oldPath: String) = oldPath(JsonField.of(oldPath))

        /**
         * Sets [Builder.oldPath] to an arbitrary JSON value.
         *
         * You should usually call [Builder.oldPath] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun oldPath(oldPath: JsonField<String>) = apply { this.oldPath = oldPath }

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
         * Returns an immutable instance of [BetaMemoryTool20250818RenameCommand].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .newPath()
         * .oldPath()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaMemoryTool20250818RenameCommand =
            BetaMemoryTool20250818RenameCommand(
                command,
                checkRequired("newPath", newPath),
                checkRequired("oldPath", oldPath),
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): BetaMemoryTool20250818RenameCommand = apply {
        if (validated) {
            return@apply
        }

        _command().let {
            if (it != JsonValue.from("rename")) {
                throw AnthropicInvalidDataException("'command' is invalid, received $it")
            }
        }
        newPath()
        oldPath()
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
        command.let { if (it == JsonValue.from("rename")) 1 else 0 } +
            (if (newPath.asKnown().isPresent) 1 else 0) +
            (if (oldPath.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaMemoryTool20250818RenameCommand &&
            command == other.command &&
            newPath == other.newPath &&
            oldPath == other.oldPath &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(command, newPath, oldPath, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaMemoryTool20250818RenameCommand{command=$command, newPath=$newPath, oldPath=$oldPath, additionalProperties=$additionalProperties}"
}
