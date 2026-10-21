package com.anthropic.models.beta.vaults.credentials

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkKnown
import com.anthropic.core.checkRequired
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Collections
import java.util.Objects
import kotlin.jvm.optionals.getOrNull

/** The secret is substituted only on requests to the listed hosts. */
class BetaManagedAgentsLimitedCredentialNetworkingResponse
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val allowedHosts: JsonField<List<String>>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("allowed_hosts")
        @ExcludeMissing
        allowedHosts: JsonField<List<String>> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(allowedHosts, type, mutableMapOf())

    /**
     * The destination hosts of the outbound requests that the credential's secret is substituted
     * into.
     *
     * An entry matches the request host exactly. An entry such as `*.example.com` matches any
     * subdomain of `example.com` but not `example.com` itself.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun allowedHosts(): List<String> = allowedHosts.getRequired("allowed_hosts")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("limited")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Returns the raw JSON value of [allowedHosts].
     *
     * Unlike [allowedHosts], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("allowed_hosts")
    @ExcludeMissing
    fun _allowedHosts(): JsonField<List<String>> = allowedHosts

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
         * [BetaManagedAgentsLimitedCredentialNetworkingResponse].
         *
         * The following fields are required:
         * ```java
         * .allowedHosts()
         * ```
         */
        @JvmStatic fun builder() = Builder()

        /**
         * Returns an immutable instance of [BetaManagedAgentsLimitedCredentialNetworkingResponse]
         * with the required [allowedHosts] set to the given value.
         */
        @JvmStatic fun of(allowedHosts: List<String>) = builder().allowedHosts(allowedHosts).build()
    }

    /** A builder for [BetaManagedAgentsLimitedCredentialNetworkingResponse]. */
    class Builder internal constructor() {

        private var allowedHosts: JsonField<MutableList<String>>? = null
        private var type: JsonValue = JsonValue.from("limited")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaManagedAgentsLimitedCredentialNetworkingResponse:
                BetaManagedAgentsLimitedCredentialNetworkingResponse
        ) = apply {
            allowedHosts =
                betaManagedAgentsLimitedCredentialNetworkingResponse.allowedHosts
                    .map { it.toMutableList() }
                    .takeUnless { it.isMissing() }
            type = betaManagedAgentsLimitedCredentialNetworkingResponse.type
            additionalProperties =
                betaManagedAgentsLimitedCredentialNetworkingResponse.additionalProperties
                    .toMutableMap()
        }

        /**
         * The destination hosts of the outbound requests that the credential's secret is
         * substituted into.
         *
         * An entry matches the request host exactly. An entry such as `*.example.com` matches any
         * subdomain of `example.com` but not `example.com` itself.
         */
        fun allowedHosts(allowedHosts: List<String>) = allowedHosts(JsonField.of(allowedHosts))

        /**
         * Sets [Builder.allowedHosts] to an arbitrary JSON value.
         *
         * You should usually call [Builder.allowedHosts] with a well-typed `List<String>` value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun allowedHosts(allowedHosts: JsonField<List<String>>) = apply {
            this.allowedHosts = allowedHosts.map { it.toMutableList() }
        }

        /**
         * Adds a single [String] to [allowedHosts].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addAllowedHost(allowedHost: String) = apply {
            allowedHosts =
                (allowedHosts ?: JsonField.of(mutableListOf())).also {
                    checkKnown("allowedHosts", it).add(allowedHost)
                }
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("limited")
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
         * Returns an immutable instance of [BetaManagedAgentsLimitedCredentialNetworkingResponse].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .allowedHosts()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaManagedAgentsLimitedCredentialNetworkingResponse =
            BetaManagedAgentsLimitedCredentialNetworkingResponse(
                checkRequired("allowedHosts", allowedHosts).map { it.toImmutable() },
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
    fun validate(): BetaManagedAgentsLimitedCredentialNetworkingResponse = apply {
        if (validated) {
            return@apply
        }

        allowedHosts()
        _type().let {
            if (it != JsonValue.from("limited")) {
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
        (allowedHosts.asKnown().getOrNull()?.size ?: 0) +
            type.let { if (it == JsonValue.from("limited")) 1 else 0 }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsLimitedCredentialNetworkingResponse &&
            allowedHosts == other.allowedHosts &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(allowedHosts, type, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsLimitedCredentialNetworkingResponse{allowedHosts=$allowedHosts, type=$type, additionalProperties=$additionalProperties}"
}
