// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.threads

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

/** Timing statistics for a session thread. */
class BetaManagedAgentsSessionThreadStats
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val activeSeconds: JsonField<Double>,
    private val durationSeconds: JsonField<Double>,
    private val startupSeconds: JsonField<Double>,
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
        @JsonProperty("startup_seconds")
        @ExcludeMissing
        startupSeconds: JsonField<Double> = JsonMissing.of(),
    ) : this(activeSeconds, durationSeconds, startupSeconds, mutableMapOf())

    /**
     * Cumulative time in seconds the thread spent actively running. Excludes idle time.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun activeSeconds(): Optional<Double> = activeSeconds.getOptional("active_seconds")

    /**
     * Elapsed time since thread creation in seconds. For archived threads, frozen at the final
     * update.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun durationSeconds(): Optional<Double> = durationSeconds.getOptional("duration_seconds")

    /**
     * Time in seconds for the thread to begin running. Zero for child threads, which start
     * immediately.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun startupSeconds(): Optional<Double> = startupSeconds.getOptional("startup_seconds")

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

    /**
     * Returns the raw JSON value of [startupSeconds].
     *
     * Unlike [startupSeconds], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("startup_seconds")
    @ExcludeMissing
    fun _startupSeconds(): JsonField<Double> = startupSeconds

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
         * [BetaManagedAgentsSessionThreadStats].
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaManagedAgentsSessionThreadStats]. */
    class Builder internal constructor() {

        private var activeSeconds: JsonField<Double> = JsonMissing.of()
        private var durationSeconds: JsonField<Double> = JsonMissing.of()
        private var startupSeconds: JsonField<Double> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaManagedAgentsSessionThreadStats: BetaManagedAgentsSessionThreadStats
        ) = apply {
            activeSeconds = betaManagedAgentsSessionThreadStats.activeSeconds
            durationSeconds = betaManagedAgentsSessionThreadStats.durationSeconds
            startupSeconds = betaManagedAgentsSessionThreadStats.startupSeconds
            additionalProperties =
                betaManagedAgentsSessionThreadStats.additionalProperties.toMutableMap()
        }

        /** Cumulative time in seconds the thread spent actively running. Excludes idle time. */
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
         * Elapsed time since thread creation in seconds. For archived threads, frozen at the final
         * update.
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

        /**
         * Time in seconds for the thread to begin running. Zero for child threads, which start
         * immediately.
         */
        fun startupSeconds(startupSeconds: Double) = startupSeconds(JsonField.of(startupSeconds))

        /**
         * Sets [Builder.startupSeconds] to an arbitrary JSON value.
         *
         * You should usually call [Builder.startupSeconds] with a well-typed [Double] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun startupSeconds(startupSeconds: JsonField<Double>) = apply {
            this.startupSeconds = startupSeconds
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
         * Returns an immutable instance of [BetaManagedAgentsSessionThreadStats].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): BetaManagedAgentsSessionThreadStats =
            BetaManagedAgentsSessionThreadStats(
                activeSeconds,
                durationSeconds,
                startupSeconds,
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
    fun validate(): BetaManagedAgentsSessionThreadStats = apply {
        if (validated) {
            return@apply
        }

        activeSeconds()
        durationSeconds()
        startupSeconds()
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
            (if (durationSeconds.asKnown().isPresent) 1 else 0) +
            (if (startupSeconds.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsSessionThreadStats &&
            activeSeconds == other.activeSeconds &&
            durationSeconds == other.durationSeconds &&
            startupSeconds == other.startupSeconds &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(activeSeconds, durationSeconds, startupSeconds, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsSessionThreadStats{activeSeconds=$activeSeconds, durationSeconds=$durationSeconds, startupSeconds=$startupSeconds, additionalProperties=$additionalProperties}"
}
