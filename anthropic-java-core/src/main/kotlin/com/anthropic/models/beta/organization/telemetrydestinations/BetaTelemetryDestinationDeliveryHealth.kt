package com.anthropic.models.beta.organization.telemetrydestinations

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
 * The most recent delivery results that the API records for a telemetry destination.
 *
 * Changes to the destination's configuration or state don't reset these fields. Changes to these
 * fields don't change `updated_at`. The values can lag behind the latest deliveries.
 *
 * See the
 * [OpenTelemetry export guide](https://platform.claude.com/docs/en/manage-claude/opentelemetry#delivery-and-health)
 * to learn how the API delivers spans and when it disables a destination.
 */
class BetaTelemetryDestinationDeliveryHealth
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val lastError: JsonField<String>,
    private val lastErrorAt: JsonField<OffsetDateTime>,
    private val lastSuccessAt: JsonField<OffsetDateTime>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("last_error") @ExcludeMissing lastError: JsonField<String> = JsonMissing.of(),
        @JsonProperty("last_error_at")
        @ExcludeMissing
        lastErrorAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("last_success_at")
        @ExcludeMissing
        lastSuccessAt: JsonField<OffsetDateTime> = JsonMissing.of(),
    ) : this(lastError, lastErrorAt, lastSuccessAt, mutableMapOf())

    /**
     * A message that describes the most recent failed delivery to the destination, or `null` if no
     * delivery has failed yet.
     *
     * Examples are `temporary DNS failure`, `TLS failure`, `timeout`, `connection failure`,
     * `invalid address`, `HTTP error (status 503)`, and `redirect (status 301)`. The message is
     * `invalid address` both for a hostname that doesn't exist and for an address that isn't
     * publicly routable. It never contains the endpoint's response body, a header value, a network
     * address, or TLS details.
     *
     * `last_error` and `last_error_at` always change together. A later successful delivery doesn't
     * clear them. If `last_success_at` is later than `last_error_at`, a delivery has succeeded
     * since this failure.
     *
     * See the
     * [OpenTelemetry export guide](https://platform.claude.com/docs/en/manage-claude/opentelemetry#delivery-errors)
     * to learn the likely cause of each error.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun lastError(): Optional<String> = lastError.getOptional("last_error")

    /**
     * Time of the failure that `last_error` describes. Changes together with `last_error`. Null
     * until a delivery first fails.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun lastErrorAt(): Optional<OffsetDateTime> = lastErrorAt.getOptional("last_error_at")

    /**
     * Time of the most recent successful delivery to the destination, or `null` if no delivery has
     * succeeded yet.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun lastSuccessAt(): Optional<OffsetDateTime> = lastSuccessAt.getOptional("last_success_at")

    /**
     * Returns the raw JSON value of [lastError].
     *
     * Unlike [lastError], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("last_error") @ExcludeMissing fun _lastError(): JsonField<String> = lastError

    /**
     * Returns the raw JSON value of [lastErrorAt].
     *
     * Unlike [lastErrorAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("last_error_at")
    @ExcludeMissing
    fun _lastErrorAt(): JsonField<OffsetDateTime> = lastErrorAt

    /**
     * Returns the raw JSON value of [lastSuccessAt].
     *
     * Unlike [lastSuccessAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("last_success_at")
    @ExcludeMissing
    fun _lastSuccessAt(): JsonField<OffsetDateTime> = lastSuccessAt

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
         * [BetaTelemetryDestinationDeliveryHealth].
         *
         * The following fields are required:
         * ```java
         * .lastError()
         * .lastErrorAt()
         * .lastSuccessAt()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaTelemetryDestinationDeliveryHealth]. */
    class Builder internal constructor() {

        private var lastError: JsonField<String>? = null
        private var lastErrorAt: JsonField<OffsetDateTime>? = null
        private var lastSuccessAt: JsonField<OffsetDateTime>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaTelemetryDestinationDeliveryHealth: BetaTelemetryDestinationDeliveryHealth
        ) = apply {
            lastError = betaTelemetryDestinationDeliveryHealth.lastError
            lastErrorAt = betaTelemetryDestinationDeliveryHealth.lastErrorAt
            lastSuccessAt = betaTelemetryDestinationDeliveryHealth.lastSuccessAt
            additionalProperties =
                betaTelemetryDestinationDeliveryHealth.additionalProperties.toMutableMap()
        }

        /**
         * A message that describes the most recent failed delivery to the destination, or `null` if
         * no delivery has failed yet.
         *
         * Examples are `temporary DNS failure`, `TLS failure`, `timeout`, `connection failure`,
         * `invalid address`, `HTTP error (status 503)`, and `redirect (status 301)`. The message is
         * `invalid address` both for a hostname that doesn't exist and for an address that isn't
         * publicly routable. It never contains the endpoint's response body, a header value, a
         * network address, or TLS details.
         *
         * `last_error` and `last_error_at` always change together. A later successful delivery
         * doesn't clear them. If `last_success_at` is later than `last_error_at`, a delivery has
         * succeeded since this failure.
         *
         * See the
         * [OpenTelemetry export guide](https://platform.claude.com/docs/en/manage-claude/opentelemetry#delivery-errors)
         * to learn the likely cause of each error.
         */
        fun lastError(lastError: String?) = lastError(JsonField.ofNullable(lastError))

        /** Alias for calling [Builder.lastError] with `lastError.orElse(null)`. */
        fun lastError(lastError: Optional<String>) = lastError(lastError.getOrNull())

        /**
         * Sets [Builder.lastError] to an arbitrary JSON value.
         *
         * You should usually call [Builder.lastError] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun lastError(lastError: JsonField<String>) = apply { this.lastError = lastError }

        /**
         * Time of the failure that `last_error` describes. Changes together with `last_error`. Null
         * until a delivery first fails.
         */
        fun lastErrorAt(lastErrorAt: OffsetDateTime?) =
            lastErrorAt(JsonField.ofNullable(lastErrorAt))

        /** Alias for calling [Builder.lastErrorAt] with `lastErrorAt.orElse(null)`. */
        fun lastErrorAt(lastErrorAt: Optional<OffsetDateTime>) =
            lastErrorAt(lastErrorAt.getOrNull())

        /**
         * Sets [Builder.lastErrorAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.lastErrorAt] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun lastErrorAt(lastErrorAt: JsonField<OffsetDateTime>) = apply {
            this.lastErrorAt = lastErrorAt
        }

        /**
         * Time of the most recent successful delivery to the destination, or `null` if no delivery
         * has succeeded yet.
         */
        fun lastSuccessAt(lastSuccessAt: OffsetDateTime?) =
            lastSuccessAt(JsonField.ofNullable(lastSuccessAt))

        /** Alias for calling [Builder.lastSuccessAt] with `lastSuccessAt.orElse(null)`. */
        fun lastSuccessAt(lastSuccessAt: Optional<OffsetDateTime>) =
            lastSuccessAt(lastSuccessAt.getOrNull())

        /**
         * Sets [Builder.lastSuccessAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.lastSuccessAt] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun lastSuccessAt(lastSuccessAt: JsonField<OffsetDateTime>) = apply {
            this.lastSuccessAt = lastSuccessAt
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
         * Returns an immutable instance of [BetaTelemetryDestinationDeliveryHealth].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .lastError()
         * .lastErrorAt()
         * .lastSuccessAt()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaTelemetryDestinationDeliveryHealth =
            BetaTelemetryDestinationDeliveryHealth(
                checkRequired("lastError", lastError),
                checkRequired("lastErrorAt", lastErrorAt),
                checkRequired("lastSuccessAt", lastSuccessAt),
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
    fun validate(): BetaTelemetryDestinationDeliveryHealth = apply {
        if (validated) {
            return@apply
        }

        lastError()
        lastErrorAt()
        lastSuccessAt()
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
        (if (lastError.asKnown().isPresent) 1 else 0) +
            (if (lastErrorAt.asKnown().isPresent) 1 else 0) +
            (if (lastSuccessAt.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaTelemetryDestinationDeliveryHealth &&
            lastError == other.lastError &&
            lastErrorAt == other.lastErrorAt &&
            lastSuccessAt == other.lastSuccessAt &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(lastError, lastErrorAt, lastSuccessAt, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaTelemetryDestinationDeliveryHealth{lastError=$lastError, lastErrorAt=$lastErrorAt, lastSuccessAt=$lastSuccessAt, additionalProperties=$additionalProperties}"
}
