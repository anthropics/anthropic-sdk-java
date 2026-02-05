// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

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
import kotlin.jvm.optionals.getOrNull

/** Automatically compact older context when reaching the configured trigger threshold. */
class BetaCompact20260112Edit
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val type: JsonValue,
    private val instructions: JsonField<String>,
    private val pauseAfterCompaction: JsonField<Boolean>,
    private val trigger: JsonField<BetaInputTokensTrigger>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("instructions")
        @ExcludeMissing
        instructions: JsonField<String> = JsonMissing.of(),
        @JsonProperty("pause_after_compaction")
        @ExcludeMissing
        pauseAfterCompaction: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("trigger")
        @ExcludeMissing
        trigger: JsonField<BetaInputTokensTrigger> = JsonMissing.of(),
    ) : this(type, instructions, pauseAfterCompaction, trigger, mutableMapOf())

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("compact_20260112")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Additional instructions for summarization.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun instructions(): Optional<String> = instructions.getOptional("instructions")

    /**
     * Whether to pause after compaction and return the compaction block to the user.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun pauseAfterCompaction(): Optional<Boolean> =
        pauseAfterCompaction.getOptional("pause_after_compaction")

    /**
     * When to trigger compaction. Defaults to 150000 input tokens.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun trigger(): Optional<BetaInputTokensTrigger> = trigger.getOptional("trigger")

    /**
     * Returns the raw JSON value of [instructions].
     *
     * Unlike [instructions], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("instructions")
    @ExcludeMissing
    fun _instructions(): JsonField<String> = instructions

    /**
     * Returns the raw JSON value of [pauseAfterCompaction].
     *
     * Unlike [pauseAfterCompaction], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("pause_after_compaction")
    @ExcludeMissing
    fun _pauseAfterCompaction(): JsonField<Boolean> = pauseAfterCompaction

    /**
     * Returns the raw JSON value of [trigger].
     *
     * Unlike [trigger], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("trigger")
    @ExcludeMissing
    fun _trigger(): JsonField<BetaInputTokensTrigger> = trigger

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

        /** Returns a mutable builder for constructing an instance of [BetaCompact20260112Edit]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaCompact20260112Edit]. */
    class Builder internal constructor() {

        private var type: JsonValue = JsonValue.from("compact_20260112")
        private var instructions: JsonField<String> = JsonMissing.of()
        private var pauseAfterCompaction: JsonField<Boolean> = JsonMissing.of()
        private var trigger: JsonField<BetaInputTokensTrigger> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaCompact20260112Edit: BetaCompact20260112Edit) = apply {
            type = betaCompact20260112Edit.type
            instructions = betaCompact20260112Edit.instructions
            pauseAfterCompaction = betaCompact20260112Edit.pauseAfterCompaction
            trigger = betaCompact20260112Edit.trigger
            additionalProperties = betaCompact20260112Edit.additionalProperties.toMutableMap()
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("compact_20260112")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        /** Additional instructions for summarization. */
        fun instructions(instructions: String?) = instructions(JsonField.ofNullable(instructions))

        /** Alias for calling [Builder.instructions] with `instructions.orElse(null)`. */
        fun instructions(instructions: Optional<String>) = instructions(instructions.getOrNull())

        /**
         * Sets [Builder.instructions] to an arbitrary JSON value.
         *
         * You should usually call [Builder.instructions] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun instructions(instructions: JsonField<String>) = apply {
            this.instructions = instructions
        }

        /** Whether to pause after compaction and return the compaction block to the user. */
        fun pauseAfterCompaction(pauseAfterCompaction: Boolean) =
            pauseAfterCompaction(JsonField.of(pauseAfterCompaction))

        /**
         * Sets [Builder.pauseAfterCompaction] to an arbitrary JSON value.
         *
         * You should usually call [Builder.pauseAfterCompaction] with a well-typed [Boolean] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun pauseAfterCompaction(pauseAfterCompaction: JsonField<Boolean>) = apply {
            this.pauseAfterCompaction = pauseAfterCompaction
        }

        /** When to trigger compaction. Defaults to 150000 input tokens. */
        fun trigger(trigger: BetaInputTokensTrigger?) = trigger(JsonField.ofNullable(trigger))

        /** Alias for calling [Builder.trigger] with `trigger.orElse(null)`. */
        fun trigger(trigger: Optional<BetaInputTokensTrigger>) = trigger(trigger.getOrNull())

        /**
         * Sets [Builder.trigger] to an arbitrary JSON value.
         *
         * You should usually call [Builder.trigger] with a well-typed [BetaInputTokensTrigger]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun trigger(trigger: JsonField<BetaInputTokensTrigger>) = apply { this.trigger = trigger }

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
         * Returns an immutable instance of [BetaCompact20260112Edit].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): BetaCompact20260112Edit =
            BetaCompact20260112Edit(
                type,
                instructions,
                pauseAfterCompaction,
                trigger,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): BetaCompact20260112Edit = apply {
        if (validated) {
            return@apply
        }

        _type().let {
            if (it != JsonValue.from("compact_20260112")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        instructions()
        pauseAfterCompaction()
        trigger().ifPresent { it.validate() }
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
        type.let { if (it == JsonValue.from("compact_20260112")) 1 else 0 } +
            (if (instructions.asKnown().isPresent) 1 else 0) +
            (if (pauseAfterCompaction.asKnown().isPresent) 1 else 0) +
            (trigger.asKnown().getOrNull()?.validity() ?: 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaCompact20260112Edit &&
            type == other.type &&
            instructions == other.instructions &&
            pauseAfterCompaction == other.pauseAfterCompaction &&
            trigger == other.trigger &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(type, instructions, pauseAfterCompaction, trigger, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaCompact20260112Edit{type=$type, instructions=$instructions, pauseAfterCompaction=$pauseAfterCompaction, trigger=$trigger, additionalProperties=$additionalProperties}"
}
