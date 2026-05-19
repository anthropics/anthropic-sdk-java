// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.agents

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

/**
 * Input payload for the `read` tool. Reads file contents relative to the runner's working directory
 * (or absolute when the runner permits).
 */
class BetaManagedAgentsAgentToolset20260401ReadInput
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val filePath: JsonField<String>,
    private val viewRange: JsonField<List<Long>>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("file_path") @ExcludeMissing filePath: JsonField<String> = JsonMissing.of(),
        @JsonProperty("view_range")
        @ExcludeMissing
        viewRange: JsonField<List<Long>> = JsonMissing.of(),
    ) : this(filePath, viewRange, mutableMapOf())

    /**
     * Path of the file to read.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun filePath(): String = filePath.getRequired("file_path")

    /**
     * Optional `[start_line, end_line]` 1-indexed inclusive range. When omitted the entire file is
     * returned. `end_line` of 0 or negative means "to end of file".
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun viewRange(): Optional<List<Long>> = viewRange.getOptional("view_range")

    /**
     * Returns the raw JSON value of [filePath].
     *
     * Unlike [filePath], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("file_path") @ExcludeMissing fun _filePath(): JsonField<String> = filePath

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
         * [BetaManagedAgentsAgentToolset20260401ReadInput].
         *
         * The following fields are required:
         * ```java
         * .filePath()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaManagedAgentsAgentToolset20260401ReadInput]. */
    class Builder internal constructor() {

        private var filePath: JsonField<String>? = null
        private var viewRange: JsonField<MutableList<Long>>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaManagedAgentsAgentToolset20260401ReadInput:
                BetaManagedAgentsAgentToolset20260401ReadInput
        ) = apply {
            filePath = betaManagedAgentsAgentToolset20260401ReadInput.filePath
            viewRange =
                betaManagedAgentsAgentToolset20260401ReadInput.viewRange.map { it.toMutableList() }
            additionalProperties =
                betaManagedAgentsAgentToolset20260401ReadInput.additionalProperties.toMutableMap()
        }

        /** Path of the file to read. */
        fun filePath(filePath: String) = filePath(JsonField.of(filePath))

        /**
         * Sets [Builder.filePath] to an arbitrary JSON value.
         *
         * You should usually call [Builder.filePath] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun filePath(filePath: JsonField<String>) = apply { this.filePath = filePath }

        /**
         * Optional `[start_line, end_line]` 1-indexed inclusive range. When omitted the entire file
         * is returned. `end_line` of 0 or negative means "to end of file".
         */
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
         * Returns an immutable instance of [BetaManagedAgentsAgentToolset20260401ReadInput].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .filePath()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaManagedAgentsAgentToolset20260401ReadInput =
            BetaManagedAgentsAgentToolset20260401ReadInput(
                checkRequired("filePath", filePath),
                (viewRange ?: JsonMissing.of()).map { it.toImmutable() },
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
    fun validate(): BetaManagedAgentsAgentToolset20260401ReadInput = apply {
        if (validated) {
            return@apply
        }

        filePath()
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
        (if (filePath.asKnown().isPresent) 1 else 0) + (viewRange.asKnown().getOrNull()?.size ?: 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsAgentToolset20260401ReadInput &&
            filePath == other.filePath &&
            viewRange == other.viewRange &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(filePath, viewRange, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsAgentToolset20260401ReadInput{filePath=$filePath, viewRange=$viewRange, additionalProperties=$additionalProperties}"
}
