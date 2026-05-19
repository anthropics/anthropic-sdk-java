// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.agents

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Collections
import java.util.Objects
import java.util.Optional

/**
 * Input payload for the `bash` tool of the `agent_toolset_20260401` toolset. All fields are
 * optional; a normal invocation supplies `command`, while `restart=true` (with no `command`)
 * reboots the runner-side bash session.
 */
class BetaManagedAgentsAgentToolset20260401BashInput
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val command: JsonField<String>,
    private val restart: JsonField<Boolean>,
    private val timeoutMs: JsonField<Long>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("command") @ExcludeMissing command: JsonField<String> = JsonMissing.of(),
        @JsonProperty("restart") @ExcludeMissing restart: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("timeout_ms") @ExcludeMissing timeoutMs: JsonField<Long> = JsonMissing.of(),
    ) : this(command, restart, timeoutMs, mutableMapOf())

    /**
     * Shell command to execute. Omit only when `restart` is true.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun command(): Optional<String> = command.getOptional("command")

    /**
     * When true, restart the persistent bash session instead of running a command. Subsequent calls
     * without `restart` will run against the fresh session.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun restart(): Optional<Boolean> = restart.getOptional("restart")

    /**
     * Per-call timeout in milliseconds. Defaults to the runner-wide tool timeout when omitted or
     * zero.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun timeoutMs(): Optional<Long> = timeoutMs.getOptional("timeout_ms")

    /**
     * Returns the raw JSON value of [command].
     *
     * Unlike [command], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("command") @ExcludeMissing fun _command(): JsonField<String> = command

    /**
     * Returns the raw JSON value of [restart].
     *
     * Unlike [restart], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("restart") @ExcludeMissing fun _restart(): JsonField<Boolean> = restart

    /**
     * Returns the raw JSON value of [timeoutMs].
     *
     * Unlike [timeoutMs], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("timeout_ms") @ExcludeMissing fun _timeoutMs(): JsonField<Long> = timeoutMs

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
         * [BetaManagedAgentsAgentToolset20260401BashInput].
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaManagedAgentsAgentToolset20260401BashInput]. */
    class Builder internal constructor() {

        private var command: JsonField<String> = JsonMissing.of()
        private var restart: JsonField<Boolean> = JsonMissing.of()
        private var timeoutMs: JsonField<Long> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaManagedAgentsAgentToolset20260401BashInput:
                BetaManagedAgentsAgentToolset20260401BashInput
        ) = apply {
            command = betaManagedAgentsAgentToolset20260401BashInput.command
            restart = betaManagedAgentsAgentToolset20260401BashInput.restart
            timeoutMs = betaManagedAgentsAgentToolset20260401BashInput.timeoutMs
            additionalProperties =
                betaManagedAgentsAgentToolset20260401BashInput.additionalProperties.toMutableMap()
        }

        /** Shell command to execute. Omit only when `restart` is true. */
        fun command(command: String) = command(JsonField.of(command))

        /**
         * Sets [Builder.command] to an arbitrary JSON value.
         *
         * You should usually call [Builder.command] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun command(command: JsonField<String>) = apply { this.command = command }

        /**
         * When true, restart the persistent bash session instead of running a command. Subsequent
         * calls without `restart` will run against the fresh session.
         */
        fun restart(restart: Boolean) = restart(JsonField.of(restart))

        /**
         * Sets [Builder.restart] to an arbitrary JSON value.
         *
         * You should usually call [Builder.restart] with a well-typed [Boolean] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun restart(restart: JsonField<Boolean>) = apply { this.restart = restart }

        /**
         * Per-call timeout in milliseconds. Defaults to the runner-wide tool timeout when omitted
         * or zero.
         */
        fun timeoutMs(timeoutMs: Long) = timeoutMs(JsonField.of(timeoutMs))

        /**
         * Sets [Builder.timeoutMs] to an arbitrary JSON value.
         *
         * You should usually call [Builder.timeoutMs] with a well-typed [Long] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun timeoutMs(timeoutMs: JsonField<Long>) = apply { this.timeoutMs = timeoutMs }

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
         * Returns an immutable instance of [BetaManagedAgentsAgentToolset20260401BashInput].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): BetaManagedAgentsAgentToolset20260401BashInput =
            BetaManagedAgentsAgentToolset20260401BashInput(
                command,
                restart,
                timeoutMs,
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
    fun validate(): BetaManagedAgentsAgentToolset20260401BashInput = apply {
        if (validated) {
            return@apply
        }

        command()
        restart()
        timeoutMs()
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
        (if (command.asKnown().isPresent) 1 else 0) +
            (if (restart.asKnown().isPresent) 1 else 0) +
            (if (timeoutMs.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsAgentToolset20260401BashInput &&
            command == other.command &&
            restart == other.restart &&
            timeoutMs == other.timeoutMs &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(command, restart, timeoutMs, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsAgentToolset20260401BashInput{command=$command, restart=$restart, timeoutMs=$timeoutMs, additionalProperties=$additionalProperties}"
}
