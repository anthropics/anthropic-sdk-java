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
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** JWKS fetched from a fixed endpoint. */
class BetaJwksExplicitUrl
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val type: JsonValue,
    private val url: JsonField<String>,
    private val caCertPem: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("url") @ExcludeMissing url: JsonField<String> = JsonMissing.of(),
        @JsonProperty("ca_cert_pem") @ExcludeMissing caCertPem: JsonField<String> = JsonMissing.of(),
    ) : this(type, url, caCertPem, mutableMapOf())

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("explicit_url")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * JWKS endpoint.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun url(): String = url.getRequired("url")

    /**
     * Optional custom CA (PEM) for TLS verification of the JWKS fetch.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun caCertPem(): Optional<String> = caCertPem.getOptional("ca_cert_pem")

    /**
     * Returns the raw JSON value of [url].
     *
     * Unlike [url], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("url") @ExcludeMissing fun _url(): JsonField<String> = url

    /**
     * Returns the raw JSON value of [caCertPem].
     *
     * Unlike [caCertPem], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("ca_cert_pem") @ExcludeMissing fun _caCertPem(): JsonField<String> = caCertPem

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
         * Returns a mutable builder for constructing an instance of [BetaJwksExplicitUrl].
         *
         * The following fields are required:
         * ```java
         * .url()
         * ```
         */
        @JvmStatic fun builder() = Builder()

        /**
         * Returns an immutable instance of [BetaJwksExplicitUrl] with the required [url] set to the
         * given value.
         */
        @JvmStatic fun of(url: String) = builder().url(url).build()
    }

    /** A builder for [BetaJwksExplicitUrl]. */
    class Builder internal constructor() {

        private var type: JsonValue = JsonValue.from("explicit_url")
        private var url: JsonField<String>? = null
        private var caCertPem: JsonField<String> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaJwksExplicitUrl: BetaJwksExplicitUrl) = apply {
            type = betaJwksExplicitUrl.type
            url = betaJwksExplicitUrl.url
            caCertPem = betaJwksExplicitUrl.caCertPem
            additionalProperties = betaJwksExplicitUrl.additionalProperties.toMutableMap()
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("explicit_url")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        /** JWKS endpoint. */
        fun url(url: String) = url(JsonField.of(url))

        /**
         * Sets [Builder.url] to an arbitrary JSON value.
         *
         * You should usually call [Builder.url] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun url(url: JsonField<String>) = apply { this.url = url }

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
         * Returns an immutable instance of [BetaJwksExplicitUrl].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .url()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaJwksExplicitUrl =
            BetaJwksExplicitUrl(
                type,
                checkRequired("url", url),
                caCertPem,
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
    fun validate(): BetaJwksExplicitUrl = apply {
        if (validated) {
            return@apply
        }

        _type().let {
            if (it != JsonValue.from("explicit_url")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        url()
        caCertPem()
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
        type.let { if (it == JsonValue.from("explicit_url")) 1 else 0 } +
            (if (url.asKnown().isPresent) 1 else 0) +
            (if (caCertPem.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaJwksExplicitUrl &&
            type == other.type &&
            url == other.url &&
            caCertPem == other.caCertPem &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(type, url, caCertPem, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaJwksExplicitUrl{type=$type, url=$url, caCertPem=$caCertPem, additionalProperties=$additionalProperties}"
}
