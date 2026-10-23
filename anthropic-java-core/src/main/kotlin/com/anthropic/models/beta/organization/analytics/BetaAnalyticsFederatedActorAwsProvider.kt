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

class BetaAnalyticsFederatedActorAwsProvider
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val accountId: JsonField<String>,
    private val signedPrincipal: JsonField<String>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("account_id") @ExcludeMissing accountId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("signed_principal")
        @ExcludeMissing
        signedPrincipal: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(accountId, signedPrincipal, type, mutableMapOf())

    /**
     * The AWS account the organization is bound to.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun accountId(): String = accountId.getRequired("account_id")

    /**
     * The AWS-signed ARN of the IAM principal that made the requests.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun signedPrincipal(): String = signedPrincipal.getRequired("signed_principal")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("aws")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Returns the raw JSON value of [accountId].
     *
     * Unlike [accountId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("account_id") @ExcludeMissing fun _accountId(): JsonField<String> = accountId

    /**
     * Returns the raw JSON value of [signedPrincipal].
     *
     * Unlike [signedPrincipal], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("signed_principal")
    @ExcludeMissing
    fun _signedPrincipal(): JsonField<String> = signedPrincipal

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
         * [BetaAnalyticsFederatedActorAwsProvider].
         *
         * The following fields are required:
         * ```java
         * .accountId()
         * .signedPrincipal()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaAnalyticsFederatedActorAwsProvider]. */
    class Builder internal constructor() {

        private var accountId: JsonField<String>? = null
        private var signedPrincipal: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("aws")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaAnalyticsFederatedActorAwsProvider: BetaAnalyticsFederatedActorAwsProvider
        ) = apply {
            accountId = betaAnalyticsFederatedActorAwsProvider.accountId
            signedPrincipal = betaAnalyticsFederatedActorAwsProvider.signedPrincipal
            type = betaAnalyticsFederatedActorAwsProvider.type
            additionalProperties =
                betaAnalyticsFederatedActorAwsProvider.additionalProperties.toMutableMap()
        }

        /** The AWS account the organization is bound to. */
        fun accountId(accountId: String) = accountId(JsonField.of(accountId))

        /**
         * Sets [Builder.accountId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.accountId] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun accountId(accountId: JsonField<String>) = apply { this.accountId = accountId }

        /** The AWS-signed ARN of the IAM principal that made the requests. */
        fun signedPrincipal(signedPrincipal: String) =
            signedPrincipal(JsonField.of(signedPrincipal))

        /**
         * Sets [Builder.signedPrincipal] to an arbitrary JSON value.
         *
         * You should usually call [Builder.signedPrincipal] with a well-typed [String] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun signedPrincipal(signedPrincipal: JsonField<String>) = apply {
            this.signedPrincipal = signedPrincipal
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("aws")
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
         * Returns an immutable instance of [BetaAnalyticsFederatedActorAwsProvider].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .accountId()
         * .signedPrincipal()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaAnalyticsFederatedActorAwsProvider =
            BetaAnalyticsFederatedActorAwsProvider(
                checkRequired("accountId", accountId),
                checkRequired("signedPrincipal", signedPrincipal),
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
    fun validate(): BetaAnalyticsFederatedActorAwsProvider = apply {
        if (validated) {
            return@apply
        }

        accountId()
        signedPrincipal()
        _type().let {
            if (it != JsonValue.from("aws")) {
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
        (if (accountId.asKnown().isPresent) 1 else 0) +
            (if (signedPrincipal.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("aws")) 1 else 0 }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaAnalyticsFederatedActorAwsProvider &&
            accountId == other.accountId &&
            signedPrincipal == other.signedPrincipal &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(accountId, signedPrincipal, type, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaAnalyticsFederatedActorAwsProvider{accountId=$accountId, signedPrincipal=$signedPrincipal, type=$type, additionalProperties=$additionalProperties}"
}
