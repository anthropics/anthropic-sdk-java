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
 * Input payload for the `glob` tool. Returns paths matching a doublestar glob pattern, newest
 * first.
 */
class BetaManagedAgentsAgentToolset20260401GlobInput
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val pattern: JsonField<String>,
    private val path: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("pattern") @ExcludeMissing pattern: JsonField<String> = JsonMissing.of(),
        @JsonProperty("path") @ExcludeMissing path: JsonField<String> = JsonMissing.of(),
    ) : this(pattern, path, mutableMapOf())

    /**
     * Doublestar glob pattern (e.g. `**&#47;*.go`). Absolute patterns are only permitted when the
     * runner is configured to allow them.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun pattern(): String = pattern.getRequired("pattern")

    /**
     * Optional directory root to search under. Defaults to the runner's working directory.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun path(): Optional<String> = path.getOptional("path")

    /**
     * Returns the raw JSON value of [pattern].
     *
     * Unlike [pattern], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("pattern") @ExcludeMissing fun _pattern(): JsonField<String> = pattern

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
         * [BetaManagedAgentsAgentToolset20260401GlobInput].
         *
         * The following fields are required:
         * ```java
         * .pattern()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaManagedAgentsAgentToolset20260401GlobInput]. */
    class Builder internal constructor() {

        private var pattern: JsonField<String>? = null
        private var path: JsonField<String> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaManagedAgentsAgentToolset20260401GlobInput:
                BetaManagedAgentsAgentToolset20260401GlobInput
        ) = apply {
            pattern = betaManagedAgentsAgentToolset20260401GlobInput.pattern
            path = betaManagedAgentsAgentToolset20260401GlobInput.path
            additionalProperties =
                betaManagedAgentsAgentToolset20260401GlobInput.additionalProperties.toMutableMap()
        }

        /**
         * Doublestar glob pattern (e.g. `**&#47;*.go`). Absolute patterns are only permitted when
         * the runner is configured to allow them.
         */
        fun pattern(pattern: String) = pattern(JsonField.of(pattern))

        /**
         * Sets [Builder.pattern] to an arbitrary JSON value.
         *
         * You should usually call [Builder.pattern] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun pattern(pattern: JsonField<String>) = apply { this.pattern = pattern }

        /** Optional directory root to search under. Defaults to the runner's working directory. */
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
         * Returns an immutable instance of [BetaManagedAgentsAgentToolset20260401GlobInput].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .pattern()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaManagedAgentsAgentToolset20260401GlobInput =
            BetaManagedAgentsAgentToolset20260401GlobInput(
                checkRequired("pattern", pattern),
                path,
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
    fun validate(): BetaManagedAgentsAgentToolset20260401GlobInput = apply {
        if (validated) {
            return@apply
        }

        pattern()
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
        (if (pattern.asKnown().isPresent) 1 else 0) + (if (path.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsAgentToolset20260401GlobInput &&
            pattern == other.pattern &&
            path == other.path &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(pattern, path, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsAgentToolset20260401GlobInput{pattern=$pattern, path=$path, additionalProperties=$additionalProperties}"
}
