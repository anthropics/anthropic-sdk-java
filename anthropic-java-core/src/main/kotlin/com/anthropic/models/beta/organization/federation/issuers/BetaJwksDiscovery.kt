// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.federation.issuers

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

/** JWKS via the issuer's OIDC discovery document. */
class BetaJwksDiscovery
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val type: JsonValue,
    private val caCertPem: JsonField<String>,
    private val discoveryBase: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("ca_cert_pem")
        @ExcludeMissing
        caCertPem: JsonField<String> = JsonMissing.of(),
        @JsonProperty("discovery_base")
        @ExcludeMissing
        discoveryBase: JsonField<String> = JsonMissing.of(),
    ) : this(type, caCertPem, discoveryBase, mutableMapOf())

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("discovery")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Optional custom CA (PEM) for TLS verification of the JWKS fetch.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun caCertPem(): Optional<String> = caCertPem.getOptional("ca_cert_pem")

    /**
     * Set when the discovery URL differs from `issuer_url`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun discoveryBase(): Optional<String> = discoveryBase.getOptional("discovery_base")

    /**
     * Returns the raw JSON value of [caCertPem].
     *
     * Unlike [caCertPem], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("ca_cert_pem") @ExcludeMissing fun _caCertPem(): JsonField<String> = caCertPem

    /**
     * Returns the raw JSON value of [discoveryBase].
     *
     * Unlike [discoveryBase], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("discovery_base")
    @ExcludeMissing
    fun _discoveryBase(): JsonField<String> = discoveryBase

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

        /** Returns a mutable builder for constructing an instance of [BetaJwksDiscovery]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaJwksDiscovery]. */
    class Builder internal constructor() {

        private var type: JsonValue = JsonValue.from("discovery")
        private var caCertPem: JsonField<String> = JsonMissing.of()
        private var discoveryBase: JsonField<String> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaJwksDiscovery: BetaJwksDiscovery) = apply {
            type = betaJwksDiscovery.type
            caCertPem = betaJwksDiscovery.caCertPem
            discoveryBase = betaJwksDiscovery.discoveryBase
            additionalProperties = betaJwksDiscovery.additionalProperties.toMutableMap()
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("discovery")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        /** Optional custom CA (PEM) for TLS verification of the JWKS fetch. */
        fun caCertPem(caCertPem: String?) = caCertPem(JsonField.ofNullable(caCertPem))

        /** Alias for calling [Builder.caCertPem] with `caCertPem.orElse(null)`. */
        fun caCertPem(caCertPem: Optional<String>) = caCertPem(caCertPem.getOrNull())

        /**
         * Sets [Builder.caCertPem] to an arbitrary JSON value.
         *
         * You should usually call [Builder.caCertPem] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun caCertPem(caCertPem: JsonField<String>) = apply { this.caCertPem = caCertPem }

        /** Set when the discovery URL differs from `issuer_url`. */
        fun discoveryBase(discoveryBase: String?) =
            discoveryBase(JsonField.ofNullable(discoveryBase))

        /** Alias for calling [Builder.discoveryBase] with `discoveryBase.orElse(null)`. */
        fun discoveryBase(discoveryBase: Optional<String>) =
            discoveryBase(discoveryBase.getOrNull())

        /**
         * Sets [Builder.discoveryBase] to an arbitrary JSON value.
         *
         * You should usually call [Builder.discoveryBase] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun discoveryBase(discoveryBase: JsonField<String>) = apply {
            this.discoveryBase = discoveryBase
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
         * Returns an immutable instance of [BetaJwksDiscovery].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): BetaJwksDiscovery =
            BetaJwksDiscovery(type, caCertPem, discoveryBase, additionalProperties.toMutableMap())
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
    fun validate(): BetaJwksDiscovery = apply {
        if (validated) {
            return@apply
        }

        _type().let {
            if (it != JsonValue.from("discovery")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        caCertPem()
        discoveryBase()
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
        type.let { if (it == JsonValue.from("discovery")) 1 else 0 } +
            (if (caCertPem.asKnown().isPresent) 1 else 0) +
            (if (discoveryBase.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaJwksDiscovery &&
            type == other.type &&
            caCertPem == other.caCertPem &&
            discoveryBase == other.discoveryBase &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(type, caCertPem, discoveryBase, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaJwksDiscovery{type=$type, caCertPem=$caCertPem, discoveryBase=$discoveryBase, additionalProperties=$additionalProperties}"
}
