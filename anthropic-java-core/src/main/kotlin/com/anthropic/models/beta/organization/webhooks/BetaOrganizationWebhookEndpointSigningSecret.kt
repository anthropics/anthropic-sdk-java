package com.anthropic.models.beta.organization.webhooks

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

/** An organization webhook endpoint's new signing secret. */
class BetaOrganizationWebhookEndpointSigningSecret
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val signingSecret: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("signing_secret")
        @ExcludeMissing
        signingSecret: JsonField<String> = JsonMissing.of()
    ) : this(signingSecret, mutableMapOf())

    /**
     * The new signing secret. Deliveries are signed with it from now on, and no other response
     * returns it.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun signingSecret(): String = signingSecret.getRequired("signing_secret")

    /**
     * Returns the raw JSON value of [signingSecret].
     *
     * Unlike [signingSecret], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("signing_secret")
    @ExcludeMissing
    fun _signingSecret(): JsonField<String> = signingSecret

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
         * [BetaOrganizationWebhookEndpointSigningSecret].
         *
         * The following fields are required:
         * ```java
         * .signingSecret()
         * ```
         */
        @JvmStatic fun builder() = Builder()

        /**
         * Returns an immutable instance of [BetaOrganizationWebhookEndpointSigningSecret] with the
         * required [signingSecret] set to the given value.
         */
        @JvmStatic fun of(signingSecret: String) = builder().signingSecret(signingSecret).build()
    }

    /** A builder for [BetaOrganizationWebhookEndpointSigningSecret]. */
    class Builder internal constructor() {

        private var signingSecret: JsonField<String>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaOrganizationWebhookEndpointSigningSecret:
                BetaOrganizationWebhookEndpointSigningSecret
        ) = apply {
            signingSecret = betaOrganizationWebhookEndpointSigningSecret.signingSecret
            additionalProperties =
                betaOrganizationWebhookEndpointSigningSecret.additionalProperties.toMutableMap()
        }

        /**
         * The new signing secret. Deliveries are signed with it from now on, and no other response
         * returns it.
         */
        fun signingSecret(signingSecret: String) = signingSecret(JsonField.of(signingSecret))

        /**
         * Sets [Builder.signingSecret] to an arbitrary JSON value.
         *
         * You should usually call [Builder.signingSecret] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun signingSecret(signingSecret: JsonField<String>) = apply {
            this.signingSecret = signingSecret
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
         * Returns an immutable instance of [BetaOrganizationWebhookEndpointSigningSecret].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .signingSecret()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaOrganizationWebhookEndpointSigningSecret =
            BetaOrganizationWebhookEndpointSigningSecret(
                checkRequired("signingSecret", signingSecret),
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
    fun validate(): BetaOrganizationWebhookEndpointSigningSecret = apply {
        if (validated) {
            return@apply
        }

        signingSecret()
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
    @JvmSynthetic internal fun validity(): Int = (if (signingSecret.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaOrganizationWebhookEndpointSigningSecret &&
            signingSecret == other.signingSecret &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(signingSecret, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaOrganizationWebhookEndpointSigningSecret{signingSecret=$signingSecret, additionalProperties=$additionalProperties}"
}
