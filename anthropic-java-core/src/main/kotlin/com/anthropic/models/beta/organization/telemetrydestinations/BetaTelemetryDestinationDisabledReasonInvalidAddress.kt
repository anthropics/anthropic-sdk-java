package com.anthropic.models.beta.organization.telemetrydestinations

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Collections
import java.util.Objects

/**
 * The API disabled the destination because deliveries to it failed for longer than the same
 * threshold as for `sustained_failure`, and the most recent failed delivery failed with an invalid
 * address: the DNS answer says that the endpoint's hostname doesn't exist or doesn't have an
 * address, or the address that the API would connect to isn't publicly routable or is reserved, for
 * example a loopback, link-local, private-network, or cloud-metadata address. The API doesn't
 * connect to such an address, and retries the delivery a bounded number of times as it does after a
 * timeout. A timeout or a server failure of the DNS lookup is a temporary DNS failure instead, not
 * an invalid address.
 */
class BetaTelemetryDestinationDisabledReasonInvalidAddress
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of()
    ) : this(type, mutableMapOf())

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("invalid_address")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

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
         * [BetaTelemetryDestinationDisabledReasonInvalidAddress].
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaTelemetryDestinationDisabledReasonInvalidAddress]. */
    class Builder internal constructor() {

        private var type: JsonValue = JsonValue.from("invalid_address")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaTelemetryDestinationDisabledReasonInvalidAddress:
                BetaTelemetryDestinationDisabledReasonInvalidAddress
        ) = apply {
            type = betaTelemetryDestinationDisabledReasonInvalidAddress.type
            additionalProperties =
                betaTelemetryDestinationDisabledReasonInvalidAddress.additionalProperties
                    .toMutableMap()
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("invalid_address")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

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
         * Returns an immutable instance of [BetaTelemetryDestinationDisabledReasonInvalidAddress].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): BetaTelemetryDestinationDisabledReasonInvalidAddress =
            BetaTelemetryDestinationDisabledReasonInvalidAddress(
                type,
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
    fun validate(): BetaTelemetryDestinationDisabledReasonInvalidAddress = apply {
        if (validated) {
            return@apply
        }

        _type().let {
            if (it != JsonValue.from("invalid_address")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
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
        type.let { if (it == JsonValue.from("invalid_address")) 1 else 0 }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaTelemetryDestinationDisabledReasonInvalidAddress &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(type, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaTelemetryDestinationDisabledReasonInvalidAddress{type=$type, additionalProperties=$additionalProperties}"
}
