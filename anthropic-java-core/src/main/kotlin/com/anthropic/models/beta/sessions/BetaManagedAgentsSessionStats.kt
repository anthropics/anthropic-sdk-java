// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions

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

/** Timing statistics for a session. */
class BetaManagedAgentsSessionStats
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val activeSeconds: JsonField<Double>,
    private val durationSeconds: JsonField<Double>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("active_seconds")
        @ExcludeMissing
        activeSeconds: JsonField<Double> = JsonMissing.of(),
        @JsonProperty("duration_seconds")
        @ExcludeMissing
        durationSeconds: JsonField<Double> = JsonMissing.of(),
    ) : this(activeSeconds, durationSeconds, mutableMapOf())

    /**
     * Cumulative time in seconds the session spent in running status. Excludes idle time.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun activeSeconds(): Optional<Double> = activeSeconds.getOptional("active_seconds")

    /**
     * Elapsed time since session creation in seconds. For terminated sessions, frozen at the final
     * update.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun durationSeconds(): Optional<Double> = durationSeconds.getOptional("duration_seconds")

    /**
     * Returns the raw JSON value of [activeSeconds].
     *
     * Unlike [activeSeconds], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("active_seconds")
    @ExcludeMissing
    fun _activeSeconds(): JsonField<Double> = activeSeconds

    /**
     * Returns the raw JSON value of [durationSeconds].
     *
     * Unlike [durationSeconds], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("duration_seconds")
    @ExcludeMissing
    fun _durationSeconds(): JsonField<Double> = durationSeconds

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
         * [BetaManagedAgentsSessionStats].
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaManagedAgentsSessionStats]. */
    class Builder internal constructor() {

        private var activeSeconds: JsonField<Double> = JsonMissing.of()
        private var durationSeconds: JsonField<Double> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaManagedAgentsSessionStats: BetaManagedAgentsSessionStats) = apply {
            activeSeconds = betaManagedAgentsSessionStats.activeSeconds
            durationSeconds = betaManagedAgentsSessionStats.durationSeconds
            additionalProperties = betaManagedAgentsSessionStats.additionalProperties.toMutableMap()
        }

        /** Cumulative time in seconds the session spent in running status. Excludes idle time. */
        fun activeSeconds(activeSeconds: Double) = activeSeconds(JsonField.of(activeSeconds))

        /**
         * Sets [Builder.activeSeconds] to an arbitrary JSON value.
         *
         * You should usually call [Builder.activeSeconds] with a well-typed [Double] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun activeSeconds(activeSeconds: JsonField<Double>) = apply {
            this.activeSeconds = activeSeconds
        }

        /**
         * Elapsed time since session creation in seconds. For terminated sessions, frozen at the
         * final update.
         */
        fun durationSeconds(durationSeconds: Double) =
            durationSeconds(JsonField.of(durationSeconds))

        /**
         * Sets [Builder.durationSeconds] to an arbitrary JSON value.
         *
         * You should usually call [Builder.durationSeconds] with a well-typed [Double] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun durationSeconds(durationSeconds: JsonField<Double>) = apply {
            this.durationSeconds = durationSeconds
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
         * Returns an immutable instance of [BetaManagedAgentsSessionStats].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): BetaManagedAgentsSessionStats =
            BetaManagedAgentsSessionStats(
                activeSeconds,
                durationSeconds,
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
    fun validate(): BetaManagedAgentsSessionStats = apply {
        if (validated) {
            return@apply
        }

        activeSeconds()
        durationSeconds()
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
        (if (activeSeconds.asKnown().isPresent) 1 else 0) +
            (if (durationSeconds.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsSessionStats &&
            activeSeconds == other.activeSeconds &&
            durationSeconds == other.durationSeconds &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(activeSeconds, durationSeconds, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsSessionStats{activeSeconds=$activeSeconds, durationSeconds=$durationSeconds, additionalProperties=$additionalProperties}"
}
