// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.vaults.credentials

import com.anthropic.core.Enum
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

/** Result of live-probing a credential against its configured MCP server. */
class BetaManagedAgentsCredentialValidation
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val credentialId: JsonField<String>,
    private val hasRefreshToken: JsonField<Boolean>,
    private val mcpProbe: JsonField<BetaManagedAgentsMcpProbe>,
    private val refresh: JsonField<BetaManagedAgentsRefreshObject>,
    private val status: JsonField<BetaManagedAgentsCredentialValidationStatus>,
    private val type: JsonField<Type>,
    private val validatedAt: JsonField<OffsetDateTime>,
    private val vaultId: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("credential_id")
        @ExcludeMissing
        credentialId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("has_refresh_token")
        @ExcludeMissing
        hasRefreshToken: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("mcp_probe")
        @ExcludeMissing
        mcpProbe: JsonField<BetaManagedAgentsMcpProbe> = JsonMissing.of(),
        @JsonProperty("refresh")
        @ExcludeMissing
        refresh: JsonField<BetaManagedAgentsRefreshObject> = JsonMissing.of(),
        @JsonProperty("status")
        @ExcludeMissing
        status: JsonField<BetaManagedAgentsCredentialValidationStatus> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonField<Type> = JsonMissing.of(),
        @JsonProperty("validated_at")
        @ExcludeMissing
        validatedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("vault_id") @ExcludeMissing vaultId: JsonField<String> = JsonMissing.of(),
    ) : this(
        credentialId,
        hasRefreshToken,
        mcpProbe,
        refresh,
        status,
        type,
        validatedAt,
        vaultId,
        mutableMapOf(),
    )

    /**
     * Unique identifier of the credential that was validated.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun credentialId(): String = credentialId.getRequired("credential_id")

    /**
     * Whether the credential has a refresh token configured.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun hasRefreshToken(): Boolean = hasRefreshToken.getRequired("has_refresh_token")

    /**
     * The failing step of an MCP validation probe.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun mcpProbe(): Optional<BetaManagedAgentsMcpProbe> = mcpProbe.getOptional("mcp_probe")

    /**
     * Outcome of a refresh-token exchange attempted during credential validation.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun refresh(): Optional<BetaManagedAgentsRefreshObject> = refresh.getOptional("refresh")

    /**
     * Overall verdict of a credential validation probe.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun status(): BetaManagedAgentsCredentialValidationStatus = status.getRequired("status")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun type(): Type = type.getRequired("type")

    /**
     * A timestamp in RFC 3339 format
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun validatedAt(): OffsetDateTime = validatedAt.getRequired("validated_at")

    /**
     * Identifier of the vault containing the credential.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun vaultId(): String = vaultId.getRequired("vault_id")

    /**
     * Returns the raw JSON value of [credentialId].
     *
     * Unlike [credentialId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("credential_id")
    @ExcludeMissing
    fun _credentialId(): JsonField<String> = credentialId

    /**
     * Returns the raw JSON value of [hasRefreshToken].
     *
     * Unlike [hasRefreshToken], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("has_refresh_token")
    @ExcludeMissing
    fun _hasRefreshToken(): JsonField<Boolean> = hasRefreshToken

    /**
     * Returns the raw JSON value of [mcpProbe].
     *
     * Unlike [mcpProbe], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("mcp_probe")
    @ExcludeMissing
    fun _mcpProbe(): JsonField<BetaManagedAgentsMcpProbe> = mcpProbe

    /**
     * Returns the raw JSON value of [refresh].
     *
     * Unlike [refresh], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("refresh")
    @ExcludeMissing
    fun _refresh(): JsonField<BetaManagedAgentsRefreshObject> = refresh

    /**
     * Returns the raw JSON value of [status].
     *
     * Unlike [status], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("status")
    @ExcludeMissing
    fun _status(): JsonField<BetaManagedAgentsCredentialValidationStatus> = status

    /**
     * Returns the raw JSON value of [type].
     *
     * Unlike [type], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonField<Type> = type

    /**
     * Returns the raw JSON value of [validatedAt].
     *
     * Unlike [validatedAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("validated_at")
    @ExcludeMissing
    fun _validatedAt(): JsonField<OffsetDateTime> = validatedAt

    /**
     * Returns the raw JSON value of [vaultId].
     *
     * Unlike [vaultId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("vault_id") @ExcludeMissing fun _vaultId(): JsonField<String> = vaultId

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
         * [BetaManagedAgentsCredentialValidation].
         *
         * The following fields are required:
         * ```java
         * .credentialId()
         * .hasRefreshToken()
         * .mcpProbe()
         * .refresh()
         * .status()
         * .type()
         * .validatedAt()
         * .vaultId()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaManagedAgentsCredentialValidation]. */
    class Builder internal constructor() {

        private var credentialId: JsonField<String>? = null
        private var hasRefreshToken: JsonField<Boolean>? = null
        private var mcpProbe: JsonField<BetaManagedAgentsMcpProbe>? = null
        private var refresh: JsonField<BetaManagedAgentsRefreshObject>? = null
        private var status: JsonField<BetaManagedAgentsCredentialValidationStatus>? = null
        private var type: JsonField<Type>? = null
        private var validatedAt: JsonField<OffsetDateTime>? = null
        private var vaultId: JsonField<String>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaManagedAgentsCredentialValidation: BetaManagedAgentsCredentialValidation
        ) = apply {
            credentialId = betaManagedAgentsCredentialValidation.credentialId
            hasRefreshToken = betaManagedAgentsCredentialValidation.hasRefreshToken
            mcpProbe = betaManagedAgentsCredentialValidation.mcpProbe
            refresh = betaManagedAgentsCredentialValidation.refresh
            status = betaManagedAgentsCredentialValidation.status
            type = betaManagedAgentsCredentialValidation.type
            validatedAt = betaManagedAgentsCredentialValidation.validatedAt
            vaultId = betaManagedAgentsCredentialValidation.vaultId
            additionalProperties =
                betaManagedAgentsCredentialValidation.additionalProperties.toMutableMap()
        }

        /** Unique identifier of the credential that was validated. */
        fun credentialId(credentialId: String) = credentialId(JsonField.of(credentialId))

        /**
         * Sets [Builder.credentialId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.credentialId] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun credentialId(credentialId: JsonField<String>) = apply {
            this.credentialId = credentialId
        }

        /** Whether the credential has a refresh token configured. */
        fun hasRefreshToken(hasRefreshToken: Boolean) =
            hasRefreshToken(JsonField.of(hasRefreshToken))

        /**
         * Sets [Builder.hasRefreshToken] to an arbitrary JSON value.
         *
         * You should usually call [Builder.hasRefreshToken] with a well-typed [Boolean] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun hasRefreshToken(hasRefreshToken: JsonField<Boolean>) = apply {
            this.hasRefreshToken = hasRefreshToken
        }

        /** The failing step of an MCP validation probe. */
        fun mcpProbe(mcpProbe: BetaManagedAgentsMcpProbe?) =
            mcpProbe(JsonField.ofNullable(mcpProbe))

        /** Alias for calling [Builder.mcpProbe] with `mcpProbe.orElse(null)`. */
        fun mcpProbe(mcpProbe: Optional<BetaManagedAgentsMcpProbe>) = mcpProbe(mcpProbe.getOrNull())

        /**
         * Sets [Builder.mcpProbe] to an arbitrary JSON value.
         *
         * You should usually call [Builder.mcpProbe] with a well-typed [BetaManagedAgentsMcpProbe]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun mcpProbe(mcpProbe: JsonField<BetaManagedAgentsMcpProbe>) = apply {
            this.mcpProbe = mcpProbe
        }

        /** Outcome of a refresh-token exchange attempted during credential validation. */
        fun refresh(refresh: BetaManagedAgentsRefreshObject?) =
            refresh(JsonField.ofNullable(refresh))

        /** Alias for calling [Builder.refresh] with `refresh.orElse(null)`. */
        fun refresh(refresh: Optional<BetaManagedAgentsRefreshObject>) =
            refresh(refresh.getOrNull())

        /**
         * Sets [Builder.refresh] to an arbitrary JSON value.
         *
         * You should usually call [Builder.refresh] with a well-typed
         * [BetaManagedAgentsRefreshObject] value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun refresh(refresh: JsonField<BetaManagedAgentsRefreshObject>) = apply {
            this.refresh = refresh
        }

        /** Overall verdict of a credential validation probe. */
        fun status(status: BetaManagedAgentsCredentialValidationStatus) =
            status(JsonField.of(status))

        /**
         * Sets [Builder.status] to an arbitrary JSON value.
         *
         * You should usually call [Builder.status] with a well-typed
         * [BetaManagedAgentsCredentialValidationStatus] value instead. This method is primarily for
         * setting the field to an undocumented or not yet supported value.
         */
        fun status(status: JsonField<BetaManagedAgentsCredentialValidationStatus>) = apply {
            this.status = status
        }

        fun type(type: Type) = type(JsonField.of(type))

        /**
         * Sets [Builder.type] to an arbitrary JSON value.
         *
         * You should usually call [Builder.type] with a well-typed [Type] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun type(type: JsonField<Type>) = apply { this.type = type }

        /** A timestamp in RFC 3339 format */
        fun validatedAt(validatedAt: OffsetDateTime) = validatedAt(JsonField.of(validatedAt))

        /**
         * Sets [Builder.validatedAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.validatedAt] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun validatedAt(validatedAt: JsonField<OffsetDateTime>) = apply {
            this.validatedAt = validatedAt
        }

        /** Identifier of the vault containing the credential. */
        fun vaultId(vaultId: String) = vaultId(JsonField.of(vaultId))

        /**
         * Sets [Builder.vaultId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.vaultId] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun vaultId(vaultId: JsonField<String>) = apply { this.vaultId = vaultId }

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
         * Returns an immutable instance of [BetaManagedAgentsCredentialValidation].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .credentialId()
         * .hasRefreshToken()
         * .mcpProbe()
         * .refresh()
         * .status()
         * .type()
         * .validatedAt()
         * .vaultId()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaManagedAgentsCredentialValidation =
            BetaManagedAgentsCredentialValidation(
                checkRequired("credentialId", credentialId),
                checkRequired("hasRefreshToken", hasRefreshToken),
                checkRequired("mcpProbe", mcpProbe),
                checkRequired("refresh", refresh),
                checkRequired("status", status),
                checkRequired("type", type),
                checkRequired("validatedAt", validatedAt),
                checkRequired("vaultId", vaultId),
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
    fun validate(): BetaManagedAgentsCredentialValidation = apply {
        if (validated) {
            return@apply
        }

        credentialId()
        hasRefreshToken()
        mcpProbe().ifPresent { it.validate() }
        refresh().ifPresent { it.validate() }
        status().validate()
        type().validate()
        validatedAt()
        vaultId()
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
        (if (credentialId.asKnown().isPresent) 1 else 0) +
            (if (hasRefreshToken.asKnown().isPresent) 1 else 0) +
            (mcpProbe.asKnown().getOrNull()?.validity() ?: 0) +
            (refresh.asKnown().getOrNull()?.validity() ?: 0) +
            (status.asKnown().getOrNull()?.validity() ?: 0) +
            (type.asKnown().getOrNull()?.validity() ?: 0) +
            (if (validatedAt.asKnown().isPresent) 1 else 0) +
            (if (vaultId.asKnown().isPresent) 1 else 0)

    class Type @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

        /**
         * Returns this class instance's raw value.
         *
         * This is usually only useful if this instance was deserialized from data that doesn't
         * match any known member, and you want to know that value. For example, if the SDK is on an
         * older version than the API, then the API may respond with new members that the SDK is
         * unaware of.
         */
        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            @JvmField val VAULT_CREDENTIAL_VALIDATION = of("vault_credential_validation")

            @JvmStatic fun of(value: String) = Type(JsonField.of(value))
        }

        /** An enum containing [Type]'s known values. */
        enum class Known {
            VAULT_CREDENTIAL_VALIDATION
        }

        /**
         * An enum containing [Type]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [Type] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            VAULT_CREDENTIAL_VALIDATION,
            /** An enum member indicating that [Type] was instantiated with an unknown value. */
            _UNKNOWN,
        }

        /**
         * Returns an enum member corresponding to this class instance's value, or [Value._UNKNOWN]
         * if the class was instantiated with an unknown value.
         *
         * Use the [known] method instead if you're certain the value is always known or if you want
         * to throw for the unknown case.
         */
        fun value(): Value =
            when (this) {
                VAULT_CREDENTIAL_VALIDATION -> Value.VAULT_CREDENTIAL_VALIDATION
                else -> Value._UNKNOWN
            }

        /**
         * Returns an enum member corresponding to this class instance's value.
         *
         * Use the [value] method instead if you're uncertain the value is always known and don't
         * want to throw for the unknown case.
         *
         * @throws AnthropicInvalidDataException if this class instance's value is a not a known
         *   member.
         */
        fun known(): Known =
            when (this) {
                VAULT_CREDENTIAL_VALIDATION -> Known.VAULT_CREDENTIAL_VALIDATION
                else -> throw AnthropicInvalidDataException("Unknown Type: $value")
            }

        /**
         * Returns this class instance's primitive wire representation.
         *
         * This differs from the [toString] method because that method is primarily for debugging
         * and generally doesn't throw.
         *
         * @throws AnthropicInvalidDataException if this class instance's value does not have the
         *   expected primitive type.
         */
        fun asString(): String =
            _value().asString().orElseThrow {
                AnthropicInvalidDataException("Value is not a String")
            }

        private var validated: Boolean = false

        /**
         * Validates that the types of all values in this object match their expected types
         * recursively.
         *
         * This method is _not_ forwards compatible with new types from the API for existing fields.
         *
         * @throws AnthropicInvalidDataException if any value type in this object doesn't match its
         *   expected type.
         */
        fun validate(): Type = apply {
            if (validated) {
                return@apply
            }

            known()
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
         * Returns a score indicating how many valid values are contained in this object
         * recursively.
         *
         * Used for best match union deserialization.
         */
        @JvmSynthetic internal fun validity(): Int = if (value() == Value._UNKNOWN) 0 else 1

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Type && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsCredentialValidation &&
            credentialId == other.credentialId &&
            hasRefreshToken == other.hasRefreshToken &&
            mcpProbe == other.mcpProbe &&
            refresh == other.refresh &&
            status == other.status &&
            type == other.type &&
            validatedAt == other.validatedAt &&
            vaultId == other.vaultId &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            credentialId,
            hasRefreshToken,
            mcpProbe,
            refresh,
            status,
            type,
            validatedAt,
            vaultId,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsCredentialValidation{credentialId=$credentialId, hasRefreshToken=$hasRefreshToken, mcpProbe=$mcpProbe, refresh=$refresh, status=$status, type=$type, validatedAt=$validatedAt, vaultId=$vaultId, additionalProperties=$additionalProperties}"
}
