// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.federation.issuers

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
import java.time.OffsetDateTime
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/**
 * Status of automatic JWKS polling for a federation issuer.
 *
 * Anthropic periodically fetches the issuer's signing keys in the background. These fields
 * summarize the most recent fetches so the health of the JWKS endpoint can be monitored.
 */
class BetaFederationIssuerPollStatus
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val consecutiveFailures: JsonField<Long>,
    private val lastFetchedAt: JsonField<OffsetDateTime>,
    private val nextPollAt: JsonField<OffsetDateTime>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("consecutive_failures")
        @ExcludeMissing
        consecutiveFailures: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("last_fetched_at")
        @ExcludeMissing
        lastFetchedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("next_poll_at")
        @ExcludeMissing
        nextPollAt: JsonField<OffsetDateTime> = JsonMissing.of(),
    ) : this(consecutiveFailures, lastFetchedAt, nextPollAt, mutableMapOf())

    /**
     * Consecutive fetch failures since the last success.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun consecutiveFailures(): Long = consecutiveFailures.getRequired("consecutive_failures")

    /**
     * When the last successful fetch completed.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun lastFetchedAt(): Optional<OffsetDateTime> = lastFetchedAt.getOptional("last_fetched_at")

    /**
     * When the next fetch is scheduled. Null if paused.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun nextPollAt(): Optional<OffsetDateTime> = nextPollAt.getOptional("next_poll_at")

    /**
     * Returns the raw JSON value of [consecutiveFailures].
     *
     * Unlike [consecutiveFailures], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("consecutive_failures")
    @ExcludeMissing
    fun _consecutiveFailures(): JsonField<Long> = consecutiveFailures

    /**
     * Returns the raw JSON value of [lastFetchedAt].
     *
     * Unlike [lastFetchedAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("last_fetched_at")
    @ExcludeMissing
    fun _lastFetchedAt(): JsonField<OffsetDateTime> = lastFetchedAt

    /**
     * Returns the raw JSON value of [nextPollAt].
     *
     * Unlike [nextPollAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("next_poll_at")
    @ExcludeMissing
    fun _nextPollAt(): JsonField<OffsetDateTime> = nextPollAt

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
         * [BetaFederationIssuerPollStatus].
         *
         * The following fields are required:
         * ```java
         * .consecutiveFailures()
         * .lastFetchedAt()
         * .nextPollAt()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaFederationIssuerPollStatus]. */
    class Builder internal constructor() {

        private var consecutiveFailures: JsonField<Long>? = null
        private var lastFetchedAt: JsonField<OffsetDateTime>? = null
        private var nextPollAt: JsonField<OffsetDateTime>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaFederationIssuerPollStatus: BetaFederationIssuerPollStatus) = apply {
            consecutiveFailures = betaFederationIssuerPollStatus.consecutiveFailures
            lastFetchedAt = betaFederationIssuerPollStatus.lastFetchedAt
            nextPollAt = betaFederationIssuerPollStatus.nextPollAt
            additionalProperties =
                betaFederationIssuerPollStatus.additionalProperties.toMutableMap()
        }

        /** Consecutive fetch failures since the last success. */
        fun consecutiveFailures(consecutiveFailures: Long) =
            consecutiveFailures(JsonField.of(consecutiveFailures))

        /**
         * Sets [Builder.consecutiveFailures] to an arbitrary JSON value.
         *
         * You should usually call [Builder.consecutiveFailures] with a well-typed [Long] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun consecutiveFailures(consecutiveFailures: JsonField<Long>) = apply {
            this.consecutiveFailures = consecutiveFailures
        }

        /** When the last successful fetch completed. */
        fun lastFetchedAt(lastFetchedAt: OffsetDateTime?) =
            lastFetchedAt(JsonField.ofNullable(lastFetchedAt))

        /** Alias for calling [Builder.lastFetchedAt] with `lastFetchedAt.orElse(null)`. */
        fun lastFetchedAt(lastFetchedAt: Optional<OffsetDateTime>) =
            lastFetchedAt(lastFetchedAt.getOrNull())

        /**
         * Sets [Builder.lastFetchedAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.lastFetchedAt] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun lastFetchedAt(lastFetchedAt: JsonField<OffsetDateTime>) = apply {
            this.lastFetchedAt = lastFetchedAt
        }

        /** When the next fetch is scheduled. Null if paused. */
        fun nextPollAt(nextPollAt: OffsetDateTime?) = nextPollAt(JsonField.ofNullable(nextPollAt))

        /** Alias for calling [Builder.nextPollAt] with `nextPollAt.orElse(null)`. */
        fun nextPollAt(nextPollAt: Optional<OffsetDateTime>) = nextPollAt(nextPollAt.getOrNull())

        /**
         * Sets [Builder.nextPollAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.nextPollAt] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun nextPollAt(nextPollAt: JsonField<OffsetDateTime>) = apply {
            this.nextPollAt = nextPollAt
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
         * Returns an immutable instance of [BetaFederationIssuerPollStatus].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .consecutiveFailures()
         * .lastFetchedAt()
         * .nextPollAt()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaFederationIssuerPollStatus =
            BetaFederationIssuerPollStatus(
                checkRequired("consecutiveFailures", consecutiveFailures),
                checkRequired("lastFetchedAt", lastFetchedAt),
                checkRequired("nextPollAt", nextPollAt),
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
    fun validate(): BetaFederationIssuerPollStatus = apply {
        if (validated) {
            return@apply
        }

        consecutiveFailures()
        lastFetchedAt()
        nextPollAt()
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
        (if (consecutiveFailures.asKnown().isPresent) 1 else 0) +
            (if (lastFetchedAt.asKnown().isPresent) 1 else 0) +
            (if (nextPollAt.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaFederationIssuerPollStatus &&
            consecutiveFailures == other.consecutiveFailures &&
            lastFetchedAt == other.lastFetchedAt &&
            nextPollAt == other.nextPollAt &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(consecutiveFailures, lastFetchedAt, nextPollAt, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaFederationIssuerPollStatus{consecutiveFailures=$consecutiveFailures, lastFetchedAt=$lastFetchedAt, nextPollAt=$nextPollAt, additionalProperties=$additionalProperties}"
}
