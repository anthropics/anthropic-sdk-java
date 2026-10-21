package com.anthropic.models.beta.vaults.credentials

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

/** Parameters for creating an MCP OAuth credential. */
class BetaManagedAgentsMcpOAuthCreateParams
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val accessToken: JsonField<String>,
    private val mcpServerUrl: JsonField<String>,
    private val type: JsonValue,
    private val expiresAt: JsonField<OffsetDateTime>,
    private val refresh: JsonField<BetaManagedAgentsMcpOAuthRefreshParams>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("access_token")
        @ExcludeMissing
        accessToken: JsonField<String> = JsonMissing.of(),
        @JsonProperty("mcp_server_url")
        @ExcludeMissing
        mcpServerUrl: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("expires_at")
        @ExcludeMissing
        expiresAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("refresh")
        @ExcludeMissing
        refresh: JsonField<BetaManagedAgentsMcpOAuthRefreshParams> = JsonMissing.of(),
    ) : this(accessToken, mcpServerUrl, type, expiresAt, refresh, mutableMapOf())

    /**
     * The OAuth access token to send to the MCP server.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun accessToken(): String = accessToken.getRequired("access_token")

    /**
     * URL of the MCP server this credential authenticates against.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun mcpServerUrl(): String = mcpServerUrl.getRequired("mcp_server_url")

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
     * When `access_token` expires, in RFC 3339.
     *
     * Must be in the future.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun expiresAt(): Optional<OffsetDateTime> = expiresAt.getOptional("expires_at")

    /**
     * Refresh token configuration, if the credential supports token refresh.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun refresh(): Optional<BetaManagedAgentsMcpOAuthRefreshParams> = refresh.getOptional("refresh")

    /**
     * Returns the raw JSON value of [accessToken].
     *
     * Unlike [accessToken], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("access_token")
    @ExcludeMissing
    fun _accessToken(): JsonField<String> = accessToken

    /**
     * Returns the raw JSON value of [mcpServerUrl].
     *
     * Unlike [mcpServerUrl], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("mcp_server_url")
    @ExcludeMissing
    fun _mcpServerUrl(): JsonField<String> = mcpServerUrl

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
    fun _refresh(): JsonField<BetaManagedAgentsMcpOAuthRefreshParams> = refresh

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
         * [BetaManagedAgentsMcpOAuthCreateParams].
         *
         * The following fields are required:
         * ```java
         * .accessToken()
         * .mcpServerUrl()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaManagedAgentsMcpOAuthCreateParams]. */
    class Builder internal constructor() {

        private var accessToken: JsonField<String>? = null
        private var mcpServerUrl: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("mcp_oauth")
        private var expiresAt: JsonField<OffsetDateTime> = JsonMissing.of()
        private var refresh: JsonField<BetaManagedAgentsMcpOAuthRefreshParams> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaManagedAgentsMcpOAuthCreateParams: BetaManagedAgentsMcpOAuthCreateParams
        ) = apply {
            accessToken = betaManagedAgentsMcpOAuthCreateParams.accessToken
            mcpServerUrl = betaManagedAgentsMcpOAuthCreateParams.mcpServerUrl
            type = betaManagedAgentsMcpOAuthCreateParams.type
            expiresAt = betaManagedAgentsMcpOAuthCreateParams.expiresAt
            refresh = betaManagedAgentsMcpOAuthCreateParams.refresh
            additionalProperties =
                betaManagedAgentsMcpOAuthCreateParams.additionalProperties.toMutableMap()
        }

        /** The OAuth access token to send to the MCP server. */
        fun accessToken(accessToken: String) = accessToken(JsonField.of(accessToken))

        /**
         * Sets [Builder.accessToken] to an arbitrary JSON value.
         *
         * You should usually call [Builder.accessToken] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun accessToken(accessToken: JsonField<String>) = apply { this.accessToken = accessToken }

        /** URL of the MCP server this credential authenticates against. */
        fun mcpServerUrl(mcpServerUrl: String) = mcpServerUrl(JsonField.of(mcpServerUrl))

        /**
         * Sets [Builder.mcpServerUrl] to an arbitrary JSON value.
         *
         * You should usually call [Builder.mcpServerUrl] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun mcpServerUrl(mcpServerUrl: JsonField<String>) = apply {
            this.mcpServerUrl = mcpServerUrl
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

        /**
         * When `access_token` expires, in RFC 3339.
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

        /** Refresh token configuration, if the credential supports token refresh. */
        fun refresh(refresh: BetaManagedAgentsMcpOAuthRefreshParams?) =
            refresh(JsonField.ofNullable(refresh))

        /** Alias for calling [Builder.refresh] with `refresh.orElse(null)`. */
        fun refresh(refresh: Optional<BetaManagedAgentsMcpOAuthRefreshParams>) =
            refresh(refresh.getOrNull())

        /**
         * Sets [Builder.refresh] to an arbitrary JSON value.
         *
         * You should usually call [Builder.refresh] with a well-typed
         * [BetaManagedAgentsMcpOAuthRefreshParams] value instead. This method is primarily for
         * setting the field to an undocumented or not yet supported value.
         */
        fun refresh(refresh: JsonField<BetaManagedAgentsMcpOAuthRefreshParams>) = apply {
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
         * Returns an immutable instance of [BetaManagedAgentsMcpOAuthCreateParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .accessToken()
         * .mcpServerUrl()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaManagedAgentsMcpOAuthCreateParams =
            BetaManagedAgentsMcpOAuthCreateParams(
                checkRequired("accessToken", accessToken),
                checkRequired("mcpServerUrl", mcpServerUrl),
                type,
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
    fun validate(): BetaManagedAgentsMcpOAuthCreateParams = apply {
        if (validated) {
            return@apply
        }

        accessToken()
        mcpServerUrl()
        _type().let {
            if (it != JsonValue.from("mcp_oauth")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
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
        (if (accessToken.asKnown().isPresent) 1 else 0) +
            (if (mcpServerUrl.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("mcp_oauth")) 1 else 0 } +
            (if (expiresAt.asKnown().isPresent) 1 else 0) +
            (refresh.asKnown().getOrNull()?.validity() ?: 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsMcpOAuthCreateParams &&
            accessToken == other.accessToken &&
            mcpServerUrl == other.mcpServerUrl &&
            type == other.type &&
            expiresAt == other.expiresAt &&
            refresh == other.refresh &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(accessToken, mcpServerUrl, type, expiresAt, refresh, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsMcpOAuthCreateParams{accessToken=$accessToken, mcpServerUrl=$mcpServerUrl, type=$type, expiresAt=$expiresAt, refresh=$refresh, additionalProperties=$additionalProperties}"
}
