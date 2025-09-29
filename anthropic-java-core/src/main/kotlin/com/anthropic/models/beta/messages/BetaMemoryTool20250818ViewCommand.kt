// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

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

class BetaMemoryTool20250818ViewCommand
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val command: JsonValue,
    private val path: JsonField<String>,
    private val viewRange: JsonField<List<Long>>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("command") @ExcludeMissing command: JsonValue = JsonMissing.of(),
        @JsonProperty("path") @ExcludeMissing path: JsonField<String> = JsonMissing.of(),
        @JsonProperty("view_range")
        @ExcludeMissing
        viewRange: JsonField<List<Long>> = JsonMissing.of(),
    ) : this(command, path, viewRange, mutableMapOf())

    /**
     * Command type identifier
     *
     * Expected to always return the following:
     * ```java
     * JsonValue.from("view")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("command") @ExcludeMissing fun _command(): JsonValue = command

    /**
     * Path to directory or file to view
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun path(): String = path.getRequired("path")

    /**
     * Optional line range for viewing specific lines
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun viewRange(): Optional<List<Long>> = viewRange.getOptional("view_range")

    /**
     * Returns the raw JSON value of [path].
     *
     * Unlike [path], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("path") @ExcludeMissing fun _path(): JsonField<String> = path

    /**
     * Returns the raw JSON value of [viewRange].
     *
     * Unlike [viewRange], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("view_range") @ExcludeMissing fun _viewRange(): JsonField<List<Long>> = viewRange

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
         * [BetaMemoryTool20250818ViewCommand].
         *
         * The following fields are required:
         * ```java
         * .path()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaMemoryTool20250818ViewCommand]. */
    class Builder internal constructor() {

        private var command: JsonValue = JsonValue.from("view")
        private var path: JsonField<String>? = null
        private var viewRange: JsonField<MutableList<Long>>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaMemoryTool20250818ViewCommand: BetaMemoryTool20250818ViewCommand) =
            apply {
                command = betaMemoryTool20250818ViewCommand.command
                path = betaMemoryTool20250818ViewCommand.path
                viewRange = betaMemoryTool20250818ViewCommand.viewRange.map { it.toMutableList() }
                additionalProperties =
                    betaMemoryTool20250818ViewCommand.additionalProperties.toMutableMap()
            }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("view")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun command(command: JsonValue) = apply { this.command = command }

        /** Path to directory or file to view */
        fun path(path: String) = path(JsonField.of(path))

        /**
         * Sets [Builder.path] to an arbitrary JSON value.
         *
         * You should usually call [Builder.path] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun path(path: JsonField<String>) = apply { this.path = path }

        /** Optional line range for viewing specific lines */
        fun viewRange(viewRange: List<Long>) = viewRange(JsonField.of(viewRange))

        /**
         * Sets [Builder.viewRange] to an arbitrary JSON value.
         *
         * You should usually call [Builder.viewRange] with a well-typed `List<Long>` value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun viewRange(viewRange: JsonField<List<Long>>) = apply {
            this.viewRange = viewRange.map { it.toMutableList() }
        }

        /**
         * Adds a single [Long] to [Builder.viewRange].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addViewRange(viewRange: Long) = apply {
            this.viewRange =
                (this.viewRange ?: JsonField.of(mutableListOf())).also {
                    checkKnown("viewRange", it).add(viewRange)
                }
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
         * Returns an immutable instance of [BetaMemoryTool20250818ViewCommand].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .path()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaMemoryTool20250818ViewCommand =
            BetaMemoryTool20250818ViewCommand(
                command,
                checkRequired("path", path),
                (viewRange ?: JsonMissing.of()).map { it.toImmutable() },
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): BetaMemoryTool20250818ViewCommand = apply {
        if (validated) {
            return@apply
        }

        _command().let {
            if (it != JsonValue.from("view")) {
                throw AnthropicInvalidDataException("'command' is invalid, received $it")
            }
        }
        path()
        viewRange()
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
        command.let { if (it == JsonValue.from("view")) 1 else 0 } +
            (if (path.asKnown().isPresent) 1 else 0) +
            (viewRange.asKnown().getOrNull()?.size ?: 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaMemoryTool20250818ViewCommand &&
            command == other.command &&
            path == other.path &&
            viewRange == other.viewRange &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(command, path, viewRange, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaMemoryTool20250818ViewCommand{command=$command, path=$path, viewRange=$viewRange, additionalProperties=$additionalProperties}"
}
