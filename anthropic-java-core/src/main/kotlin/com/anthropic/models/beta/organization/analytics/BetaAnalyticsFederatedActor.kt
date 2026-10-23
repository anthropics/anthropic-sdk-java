package com.anthropic.models.beta.organization.analytics

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

class BetaAnalyticsFederatedActor
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val provider: JsonField<BetaAnalyticsFederatedActorProvider>,
    private val subject: JsonField<String>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("provider")
        @ExcludeMissing
        provider: JsonField<BetaAnalyticsFederatedActorProvider> = JsonMissing.of(),
        @JsonProperty("subject") @ExcludeMissing subject: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(provider, subject, type, mutableMapOf())

    /**
     * The trusted provider that asserted the caller's identity.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun provider(): BetaAnalyticsFederatedActorProvider = provider.getRequired("provider")

    /**
     * The provider's identifier for the caller: on `aws`, the IAM principal ARN (one role is one
     * actor across its sessions); on `gcp`, the caller's IAM principal identifier as Google Cloud
     * reports it, such as `serviceAccount:<email>`, `user:<email>`, or a `principal://...` URI.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun subject(): Optional<String> = subject.getOptional("subject")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("federated_actor")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Returns the raw JSON value of [provider].
     *
     * Unlike [provider], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("provider")
    @ExcludeMissing
    fun _provider(): JsonField<BetaAnalyticsFederatedActorProvider> = provider

    /**
     * Returns the raw JSON value of [subject].
     *
     * Unlike [subject], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("subject") @ExcludeMissing fun _subject(): JsonField<String> = subject

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
         * Returns a mutable builder for constructing an instance of [BetaAnalyticsFederatedActor].
         *
         * The following fields are required:
         * ```java
         * .provider()
         * .subject()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaAnalyticsFederatedActor]. */
    class Builder internal constructor() {

        private var provider: JsonField<BetaAnalyticsFederatedActorProvider>? = null
        private var subject: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("federated_actor")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaAnalyticsFederatedActor: BetaAnalyticsFederatedActor) = apply {
            provider = betaAnalyticsFederatedActor.provider
            subject = betaAnalyticsFederatedActor.subject
            type = betaAnalyticsFederatedActor.type
            additionalProperties = betaAnalyticsFederatedActor.additionalProperties.toMutableMap()
        }

        /** The trusted provider that asserted the caller's identity. */
        fun provider(provider: BetaAnalyticsFederatedActorProvider) =
            provider(JsonField.of(provider))

        /**
         * Sets [Builder.provider] to an arbitrary JSON value.
         *
         * You should usually call [Builder.provider] with a well-typed
         * [BetaAnalyticsFederatedActorProvider] value instead. This method is primarily for setting
         * the field to an undocumented or not yet supported value.
         */
        fun provider(provider: JsonField<BetaAnalyticsFederatedActorProvider>) = apply {
            this.provider = provider
        }

        /** Alias for calling [provider] with `BetaAnalyticsFederatedActorProvider.ofAws(aws)`. */
        fun provider(aws: BetaAnalyticsFederatedActorAwsProvider) =
            provider(BetaAnalyticsFederatedActorProvider.ofAws(aws))

        /**
         * Alias for calling [provider] with `BetaAnalyticsFederatedActorProvider.ofAzure(azure)`.
         */
        fun provider(azure: BetaAnalyticsFederatedActorAzureProvider) =
            provider(BetaAnalyticsFederatedActorProvider.ofAzure(azure))

        /**
         * Alias for calling [provider] with the following:
         * ```java
         * BetaAnalyticsFederatedActorAzureProvider.builder()
         *     .subscriptionId(subscriptionId)
         *     .build()
         * ```
         */
        fun azureProvider(subscriptionId: String) =
            provider(
                BetaAnalyticsFederatedActorAzureProvider.builder()
                    .subscriptionId(subscriptionId)
                    .build()
            )

        /** Alias for calling [provider] with `BetaAnalyticsFederatedActorProvider.ofGcp(gcp)`. */
        fun provider(gcp: BetaAnalyticsFederatedActorGcpProvider) =
            provider(BetaAnalyticsFederatedActorProvider.ofGcp(gcp))

        /**
         * Alias for calling [provider] with the following:
         * ```java
         * BetaAnalyticsFederatedActorGcpProvider.builder()
         *     .projectNumber(projectNumber)
         *     .build()
         * ```
         */
        fun gcpProvider(projectNumber: String) =
            provider(
                BetaAnalyticsFederatedActorGcpProvider.builder()
                    .projectNumber(projectNumber)
                    .build()
            )

        /** Alias for calling [provider] with `BetaAnalyticsFederatedActorProvider.ofOidc(oidc)`. */
        fun provider(oidc: BetaAnalyticsFederatedActorOidcProvider) =
            provider(BetaAnalyticsFederatedActorProvider.ofOidc(oidc))

        /**
         * Alias for calling [provider] with the following:
         * ```java
         * BetaAnalyticsFederatedActorOidcProvider.builder()
         *     .issuer(issuer)
         *     .build()
         * ```
         */
        fun oidcProvider(issuer: String?) =
            provider(BetaAnalyticsFederatedActorOidcProvider.builder().issuer(issuer).build())

        /** Alias for calling [oidcProvider] with `issuer.orElse(null)`. */
        fun oidcProvider(issuer: Optional<String>) = oidcProvider(issuer.getOrNull())

        /**
         * The provider's identifier for the caller: on `aws`, the IAM principal ARN (one role is
         * one actor across its sessions); on `gcp`, the caller's IAM principal identifier as Google
         * Cloud reports it, such as `serviceAccount:<email>`, `user:<email>`, or a
         * `principal://...` URI.
         */
        fun subject(subject: String?) = subject(JsonField.ofNullable(subject))

        /** Alias for calling [Builder.subject] with `subject.orElse(null)`. */
        fun subject(subject: Optional<String>) = subject(subject.getOrNull())

        /**
         * Sets [Builder.subject] to an arbitrary JSON value.
         *
         * You should usually call [Builder.subject] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun subject(subject: JsonField<String>) = apply { this.subject = subject }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("federated_actor")
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
         * Returns an immutable instance of [BetaAnalyticsFederatedActor].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .provider()
         * .subject()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaAnalyticsFederatedActor =
            BetaAnalyticsFederatedActor(
                checkRequired("provider", provider),
                checkRequired("subject", subject),
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
    fun validate(): BetaAnalyticsFederatedActor = apply {
        if (validated) {
            return@apply
        }

        provider().validate()
        subject()
        _type().let {
            if (it != JsonValue.from("federated_actor")) {
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
        (provider.asKnown().getOrNull()?.validity() ?: 0) +
            (if (subject.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("federated_actor")) 1 else 0 }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaAnalyticsFederatedActor &&
            provider == other.provider &&
            subject == other.subject &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(provider, subject, type, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaAnalyticsFederatedActor{provider=$provider, subject=$subject, type=$type, additionalProperties=$additionalProperties}"
}
