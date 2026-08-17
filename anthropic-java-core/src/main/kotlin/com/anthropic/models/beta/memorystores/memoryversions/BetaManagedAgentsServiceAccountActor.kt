// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.memorystores.memoryversions

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

/**
 * Attribution for a write made by a workload authenticated as a service account, for example via
 * Workload Identity Federation.
 */
class BetaManagedAgentsServiceAccountActor
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val serviceAccountId: JsonField<String>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("service_account_id")
        @ExcludeMissing
        serviceAccountId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(serviceAccountId, type, mutableMapOf())

    /**
     * ID of the service account that performed the write (a `svac_...` value).
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun serviceAccountId(): String = serviceAccountId.getRequired("service_account_id")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("service_account_actor")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Returns the raw JSON value of [serviceAccountId].
     *
     * Unlike [serviceAccountId], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("service_account_id")
    @ExcludeMissing
    fun _serviceAccountId(): JsonField<String> = serviceAccountId

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
         * [BetaManagedAgentsServiceAccountActor].
         *
         * The following fields are required:
         * ```java
         * .serviceAccountId()
         * ```
         */
        @JvmStatic fun builder() = Builder()

        /**
         * Returns an immutable instance of [BetaManagedAgentsServiceAccountActor] with the required
         * [serviceAccountId] set to the given value.
         */
        @JvmStatic
        fun of(serviceAccountId: String) = builder().serviceAccountId(serviceAccountId).build()
    }

    /** A builder for [BetaManagedAgentsServiceAccountActor]. */
    class Builder internal constructor() {

        private var serviceAccountId: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("service_account_actor")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaManagedAgentsServiceAccountActor: BetaManagedAgentsServiceAccountActor
        ) = apply {
            serviceAccountId = betaManagedAgentsServiceAccountActor.serviceAccountId
            type = betaManagedAgentsServiceAccountActor.type
            additionalProperties =
                betaManagedAgentsServiceAccountActor.additionalProperties.toMutableMap()
        }

        /** ID of the service account that performed the write (a `svac_...` value). */
        fun serviceAccountId(serviceAccountId: String) =
            serviceAccountId(JsonField.of(serviceAccountId))

        /**
         * Sets [Builder.serviceAccountId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.serviceAccountId] with a well-typed [String] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun serviceAccountId(serviceAccountId: JsonField<String>) = apply {
            this.serviceAccountId = serviceAccountId
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("service_account_actor")
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
         * Returns an immutable instance of [BetaManagedAgentsServiceAccountActor].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .serviceAccountId()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaManagedAgentsServiceAccountActor =
            BetaManagedAgentsServiceAccountActor(
                checkRequired("serviceAccountId", serviceAccountId),
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
    fun validate(): BetaManagedAgentsServiceAccountActor = apply {
        if (validated) {
            return@apply
        }

        serviceAccountId()
        _type().let {
            if (it != JsonValue.from("service_account_actor")) {
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
        (if (serviceAccountId.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("service_account_actor")) 1 else 0 }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsServiceAccountActor &&
            serviceAccountId == other.serviceAccountId &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(serviceAccountId, type, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsServiceAccountActor{serviceAccountId=$serviceAccountId, type=$type, additionalProperties=$additionalProperties}"
}
