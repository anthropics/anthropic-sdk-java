package com.anthropic.models.beta.vaults.credentials

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
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

/** Parameters for updating an MCP OAuth credential. The `mcp_server_url` is immutable. */
class BetaManagedAgentsMcpOAuthUpdateParams
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val type: JsonValue,
    private val accessToken: JsonField<String>,
    private val expiresAt: JsonField<OffsetDateTime>,
    private val refresh: JsonField<BetaManagedAgentsMcpOAuthRefreshUpdateParams>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("access_token")
        @ExcludeMissing
        accessToken: JsonField<String> = JsonMissing.of(),
        @JsonProperty("expires_at")
        @ExcludeMissing
        expiresAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("refresh")
        @ExcludeMissing
        refresh: JsonField<BetaManagedAgentsMcpOAuthRefreshUpdateParams> = JsonMissing.of(),
    ) : this(type, accessToken, expiresAt, refresh, mutableMapOf())

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("mcp_oauth")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * The new OAuth access token to replace the stored one.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun accessToken(): Optional<String> = accessToken.getOptional("access_token")

    /**
     * When the credential's access token expires, in RFC 3339. Leave it out to keep the stored
     * expiry.
     *
     * If you send a new `access_token`, send its `expires_at` too, or the new token keeps the old
     * expiry.
     *
     * Must be in the future.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun expiresAt(): Optional<OffsetDateTime> = expiresAt.getOptional("expires_at")

    /**
     * Updated refresh token configuration.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun refresh(): Optional<BetaManagedAgentsMcpOAuthRefreshUpdateParams> =
        refresh.getOptional("refresh")

    /**
     * Returns the raw JSON value of [accessToken].
     *
     * Unlike [accessToken], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("access_token")
    @ExcludeMissing
    fun _accessToken(): JsonField<String> = accessToken

    /**
     * Returns the raw JSON value of [expiresAt].
     *
     * Unlike [expiresAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("expires_at")
    @ExcludeMissing
    fun _expiresAt(): JsonField<OffsetDateTime> = expiresAt

    /**
     * Returns the raw JSON value of [refresh].
     *
     * Unlike [refresh], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("refresh")
    @ExcludeMissing
    fun _refresh(): JsonField<BetaManagedAgentsMcpOAuthRefreshUpdateParams> = refresh

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
         * [BetaManagedAgentsMcpOAuthUpdateParams].
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaManagedAgentsMcpOAuthUpdateParams]. */
    class Builder internal constructor() {

        private var type: JsonValue = JsonValue.from("mcp_oauth")
        private var accessToken: JsonField<String> = JsonMissing.of()
        private var expiresAt: JsonField<OffsetDateTime> = JsonMissing.of()
        private var refresh: JsonField<BetaManagedAgentsMcpOAuthRefreshUpdateParams> =
            JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaManagedAgentsMcpOAuthUpdateParams: BetaManagedAgentsMcpOAuthUpdateParams
        ) = apply {
            type = betaManagedAgentsMcpOAuthUpdateParams.type
            accessToken = betaManagedAgentsMcpOAuthUpdateParams.accessToken
            expiresAt = betaManagedAgentsMcpOAuthUpdateParams.expiresAt
            refresh = betaManagedAgentsMcpOAuthUpdateParams.refresh
            additionalProperties =
                betaManagedAgentsMcpOAuthUpdateParams.additionalProperties.toMutableMap()
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("mcp_oauth")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        /** The new OAuth access token to replace the stored one. */
        fun accessToken(accessToken: String?) = accessToken(JsonField.ofNullable(accessToken))

        /** Alias for calling [Builder.accessToken] with `accessToken.orElse(null)`. */
        fun accessToken(accessToken: Optional<String>) = accessToken(accessToken.getOrNull())

        /**
         * Sets [Builder.accessToken] to an arbitrary JSON value.
         *
         * You should usually call [Builder.accessToken] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun accessToken(accessToken: JsonField<String>) = apply { this.accessToken = accessToken }

        /**
         * When the credential's access token expires, in RFC 3339. Leave it out to keep the stored
         * expiry.
         *
         * If you send a new `access_token`, send its `expires_at` too, or the new token keeps the
         * old expiry.
         *
         * Must be in the future.
         */
        fun expiresAt(expiresAt: OffsetDateTime?) = expiresAt(JsonField.ofNullable(expiresAt))

        /** Alias for calling [Builder.expiresAt] with `expiresAt.orElse(null)`. */
        fun expiresAt(expiresAt: Optional<OffsetDateTime>) = expiresAt(expiresAt.getOrNull())

        /**
         * Sets [Builder.expiresAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.expiresAt] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun expiresAt(expiresAt: JsonField<OffsetDateTime>) = apply { this.expiresAt = expiresAt }

        /** Updated refresh token configuration. */
        fun refresh(refresh: BetaManagedAgentsMcpOAuthRefreshUpdateParams?) =
            refresh(JsonField.ofNullable(refresh))

        /** Alias for calling [Builder.refresh] with `refresh.orElse(null)`. */
        fun refresh(refresh: Optional<BetaManagedAgentsMcpOAuthRefreshUpdateParams>) =
            refresh(refresh.getOrNull())

        /**
         * Sets [Builder.refresh] to an arbitrary JSON value.
         *
         * You should usually call [Builder.refresh] with a well-typed
         * [BetaManagedAgentsMcpOAuthRefreshUpdateParams] value instead. This method is primarily
         * for setting the field to an undocumented or not yet supported value.
         */
        fun refresh(refresh: JsonField<BetaManagedAgentsMcpOAuthRefreshUpdateParams>) = apply {
            this.refresh = refresh
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
         * Returns an immutable instance of [BetaManagedAgentsMcpOAuthUpdateParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): BetaManagedAgentsMcpOAuthUpdateParams =
            BetaManagedAgentsMcpOAuthUpdateParams(
                type,
                accessToken,
                expiresAt,
                refresh,
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
    fun validate(): BetaManagedAgentsMcpOAuthUpdateParams = apply {
        if (validated) {
            return@apply
        }

        _type().let {
            if (it != JsonValue.from("mcp_oauth")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        accessToken()
        expiresAt()
        refresh().ifPresent { it.validate() }
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
        type.let { if (it == JsonValue.from("mcp_oauth")) 1 else 0 } +
            (if (accessToken.asKnown().isPresent) 1 else 0) +
            (if (expiresAt.asKnown().isPresent) 1 else 0) +
            (refresh.asKnown().getOrNull()?.validity() ?: 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsMcpOAuthUpdateParams &&
            type == other.type &&
            accessToken == other.accessToken &&
            expiresAt == other.expiresAt &&
            refresh == other.refresh &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(type, accessToken, expiresAt, refresh, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsMcpOAuthUpdateParams{type=$type, accessToken=$accessToken, expiresAt=$expiresAt, refresh=$refresh, additionalProperties=$additionalProperties}"
}
