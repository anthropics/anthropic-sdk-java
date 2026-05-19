// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.agents

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

/**
 * Input payload for the `edit` tool. Performs a string replacement in the named file; by default
 * `old_string` must occur exactly once.
 */
class BetaManagedAgentsAgentToolset20260401EditInput
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val filePath: JsonField<String>,
    private val newString: JsonField<String>,
    private val oldString: JsonField<String>,
    private val replaceAll: JsonField<Boolean>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("file_path") @ExcludeMissing filePath: JsonField<String> = JsonMissing.of(),
        @JsonProperty("new_string") @ExcludeMissing newString: JsonField<String> = JsonMissing.of(),
        @JsonProperty("old_string") @ExcludeMissing oldString: JsonField<String> = JsonMissing.of(),
        @JsonProperty("replace_all")
        @ExcludeMissing
        replaceAll: JsonField<Boolean> = JsonMissing.of(),
    ) : this(filePath, newString, oldString, replaceAll, mutableMapOf())

    /**
     * Path of the file to edit.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun filePath(): String = filePath.getRequired("file_path")

    /**
     * Replacement text.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun newString(): String = newString.getRequired("new_string")

    /**
     * Substring to find and replace.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun oldString(): String = oldString.getRequired("old_string")

    /**
     * When true, replace every occurrence of `old_string` instead of requiring a unique match.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun replaceAll(): Optional<Boolean> = replaceAll.getOptional("replace_all")

    /**
     * Returns the raw JSON value of [filePath].
     *
     * Unlike [filePath], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("file_path") @ExcludeMissing fun _filePath(): JsonField<String> = filePath

    /**
     * Returns the raw JSON value of [newString].
     *
     * Unlike [newString], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("new_string") @ExcludeMissing fun _newString(): JsonField<String> = newString

    /**
     * Returns the raw JSON value of [oldString].
     *
     * Unlike [oldString], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("old_string") @ExcludeMissing fun _oldString(): JsonField<String> = oldString

    /**
     * Returns the raw JSON value of [replaceAll].
     *
     * Unlike [replaceAll], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("replace_all") @ExcludeMissing fun _replaceAll(): JsonField<Boolean> = replaceAll

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
         * [BetaManagedAgentsAgentToolset20260401EditInput].
         *
         * The following fields are required:
         * ```java
         * .filePath()
         * .newString()
         * .oldString()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaManagedAgentsAgentToolset20260401EditInput]. */
    class Builder internal constructor() {

        private var filePath: JsonField<String>? = null
        private var newString: JsonField<String>? = null
        private var oldString: JsonField<String>? = null
        private var replaceAll: JsonField<Boolean> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaManagedAgentsAgentToolset20260401EditInput:
                BetaManagedAgentsAgentToolset20260401EditInput
        ) = apply {
            filePath = betaManagedAgentsAgentToolset20260401EditInput.filePath
            newString = betaManagedAgentsAgentToolset20260401EditInput.newString
            oldString = betaManagedAgentsAgentToolset20260401EditInput.oldString
            replaceAll = betaManagedAgentsAgentToolset20260401EditInput.replaceAll
            additionalProperties =
                betaManagedAgentsAgentToolset20260401EditInput.additionalProperties.toMutableMap()
        }

        /** Path of the file to edit. */
        fun filePath(filePath: String) = filePath(JsonField.of(filePath))

        /**
         * Sets [Builder.filePath] to an arbitrary JSON value.
         *
         * You should usually call [Builder.filePath] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun filePath(filePath: JsonField<String>) = apply { this.filePath = filePath }

        /** Replacement text. */
        fun newString(newString: String) = newString(JsonField.of(newString))

        /**
         * Sets [Builder.newString] to an arbitrary JSON value.
         *
         * You should usually call [Builder.newString] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun newString(newString: JsonField<String>) = apply { this.newString = newString }

        /** Substring to find and replace. */
        fun oldString(oldString: String) = oldString(JsonField.of(oldString))

        /**
         * Sets [Builder.oldString] to an arbitrary JSON value.
         *
         * You should usually call [Builder.oldString] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun oldString(oldString: JsonField<String>) = apply { this.oldString = oldString }

        /**
         * When true, replace every occurrence of `old_string` instead of requiring a unique match.
         */
        fun replaceAll(replaceAll: Boolean) = replaceAll(JsonField.of(replaceAll))

        /**
         * Sets [Builder.replaceAll] to an arbitrary JSON value.
         *
         * You should usually call [Builder.replaceAll] with a well-typed [Boolean] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun replaceAll(replaceAll: JsonField<Boolean>) = apply { this.replaceAll = replaceAll }

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
         * Returns an immutable instance of [BetaManagedAgentsAgentToolset20260401EditInput].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .filePath()
         * .newString()
         * .oldString()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaManagedAgentsAgentToolset20260401EditInput =
            BetaManagedAgentsAgentToolset20260401EditInput(
                checkRequired("filePath", filePath),
                checkRequired("newString", newString),
                checkRequired("oldString", oldString),
                replaceAll,
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
    fun validate(): BetaManagedAgentsAgentToolset20260401EditInput = apply {
        if (validated) {
            return@apply
        }

        filePath()
        newString()
        oldString()
        replaceAll()
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
        (if (filePath.asKnown().isPresent) 1 else 0) +
            (if (newString.asKnown().isPresent) 1 else 0) +
            (if (oldString.asKnown().isPresent) 1 else 0) +
            (if (replaceAll.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsAgentToolset20260401EditInput &&
            filePath == other.filePath &&
            newString == other.newString &&
            oldString == other.oldString &&
            replaceAll == other.replaceAll &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(filePath, newString, oldString, replaceAll, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsAgentToolset20260401EditInput{filePath=$filePath, newString=$newString, oldString=$oldString, replaceAll=$replaceAll, additionalProperties=$additionalProperties}"
}
