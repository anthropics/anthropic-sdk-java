// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.externalkeys

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

class BetaAzureExternalKeyConfig
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val keyName: JsonField<String>,
    private val tenantId: JsonField<String>,
    private val type: JsonValue,
    private val vaultUri: JsonField<String>,
    private val clientId: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("key_name") @ExcludeMissing keyName: JsonField<String> = JsonMissing.of(),
        @JsonProperty("tenant_id") @ExcludeMissing tenantId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("vault_uri") @ExcludeMissing vaultUri: JsonField<String> = JsonMissing.of(),
        @JsonProperty("client_id") @ExcludeMissing clientId: JsonField<String> = JsonMissing.of(),
    ) : this(keyName, tenantId, type, vaultUri, clientId, mutableMapOf())

    /**
     * Name of the key within the vault.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun keyName(): String = keyName.getRequired("key_name")

    /**
     * Azure AD tenant ID.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun tenantId(): String = tenantId.getRequired("tenant_id")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("azure")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Key Vault data-plane URI — `https://{vault-name}.vault.azure.net` or
     * `https://{hsm-name}.managedhsm.azure.net`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun vaultUri(): String = vaultUri.getRequired("vault_uri")

    /**
     * Azure AD application (client) ID. Omit to use Anthropic's multitenant app. Provide only if
     * using a single-tenant app registration in the customer's directory.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun clientId(): Optional<String> = clientId.getOptional("client_id")

    /**
     * Returns the raw JSON value of [keyName].
     *
     * Unlike [keyName], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("key_name") @ExcludeMissing fun _keyName(): JsonField<String> = keyName

    /**
     * Returns the raw JSON value of [tenantId].
     *
     * Unlike [tenantId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("tenant_id") @ExcludeMissing fun _tenantId(): JsonField<String> = tenantId

    /**
     * Returns the raw JSON value of [vaultUri].
     *
     * Unlike [vaultUri], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("vault_uri") @ExcludeMissing fun _vaultUri(): JsonField<String> = vaultUri

    /**
     * Returns the raw JSON value of [clientId].
     *
     * Unlike [clientId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("client_id") @ExcludeMissing fun _clientId(): JsonField<String> = clientId

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
         * Returns a mutable builder for constructing an instance of [BetaAzureExternalKeyConfig].
         *
         * The following fields are required:
         * ```java
         * .keyName()
         * .tenantId()
         * .vaultUri()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaAzureExternalKeyConfig]. */
    class Builder internal constructor() {

        private var keyName: JsonField<String>? = null
        private var tenantId: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("azure")
        private var vaultUri: JsonField<String>? = null
        private var clientId: JsonField<String> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaAzureExternalKeyConfig: BetaAzureExternalKeyConfig) = apply {
            keyName = betaAzureExternalKeyConfig.keyName
            tenantId = betaAzureExternalKeyConfig.tenantId
            type = betaAzureExternalKeyConfig.type
            vaultUri = betaAzureExternalKeyConfig.vaultUri
            clientId = betaAzureExternalKeyConfig.clientId
            additionalProperties = betaAzureExternalKeyConfig.additionalProperties.toMutableMap()
        }

        /** Name of the key within the vault. */
        fun keyName(keyName: String) = keyName(JsonField.of(keyName))

        /**
         * Sets [Builder.keyName] to an arbitrary JSON value.
         *
         * You should usually call [Builder.keyName] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun keyName(keyName: JsonField<String>) = apply { this.keyName = keyName }

        /** Azure AD tenant ID. */
        fun tenantId(tenantId: String) = tenantId(JsonField.of(tenantId))

        /**
         * Sets [Builder.tenantId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.tenantId] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun tenantId(tenantId: JsonField<String>) = apply { this.tenantId = tenantId }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("azure")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        /**
         * Key Vault data-plane URI — `https://{vault-name}.vault.azure.net` or
         * `https://{hsm-name}.managedhsm.azure.net`.
         */
        fun vaultUri(vaultUri: String) = vaultUri(JsonField.of(vaultUri))

        /**
         * Sets [Builder.vaultUri] to an arbitrary JSON value.
         *
         * You should usually call [Builder.vaultUri] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun vaultUri(vaultUri: JsonField<String>) = apply { this.vaultUri = vaultUri }

        /**
         * Azure AD application (client) ID. Omit to use Anthropic's multitenant app. Provide only
         * if using a single-tenant app registration in the customer's directory.
         */
        fun clientId(clientId: String?) = clientId(JsonField.ofNullable(clientId))

        /** Alias for calling [Builder.clientId] with `clientId.orElse(null)`. */
        fun clientId(clientId: Optional<String>) = clientId(clientId.getOrNull())

        /**
         * Sets [Builder.clientId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.clientId] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun clientId(clientId: JsonField<String>) = apply { this.clientId = clientId }

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
         * Returns an immutable instance of [BetaAzureExternalKeyConfig].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .keyName()
         * .tenantId()
         * .vaultUri()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaAzureExternalKeyConfig =
            BetaAzureExternalKeyConfig(
                checkRequired("keyName", keyName),
                checkRequired("tenantId", tenantId),
                type,
                checkRequired("vaultUri", vaultUri),
                clientId,
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
    fun validate(): BetaAzureExternalKeyConfig = apply {
        if (validated) {
            return@apply
        }

        keyName()
        tenantId()
        _type().let {
            if (it != JsonValue.from("azure")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        vaultUri()
        clientId()
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
        (if (keyName.asKnown().isPresent) 1 else 0) +
            (if (tenantId.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("azure")) 1 else 0 } +
            (if (vaultUri.asKnown().isPresent) 1 else 0) +
            (if (clientId.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaAzureExternalKeyConfig &&
            keyName == other.keyName &&
            tenantId == other.tenantId &&
            type == other.type &&
            vaultUri == other.vaultUri &&
            clientId == other.clientId &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(keyName, tenantId, type, vaultUri, clientId, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaAzureExternalKeyConfig{keyName=$keyName, tenantId=$tenantId, type=$type, vaultUri=$vaultUri, clientId=$clientId, additionalProperties=$additionalProperties}"
}
