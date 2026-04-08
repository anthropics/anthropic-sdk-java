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

/** OAuth credential details for an MCP server. */
class BetaManagedAgentsMcpOAuthAuthResponse
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val mcpServerUrl: JsonField<String>,
    private val type: JsonField<Type>,
    private val expiresAt: JsonField<OffsetDateTime>,
    private val refresh: JsonField<BetaManagedAgentsMcpOAuthRefreshResponse>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("mcp_server_url")
        @ExcludeMissing
        mcpServerUrl: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonField<Type> = JsonMissing.of(),
        @JsonProperty("expires_at")
        @ExcludeMissing
        expiresAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("refresh")
        @ExcludeMissing
        refresh: JsonField<BetaManagedAgentsMcpOAuthRefreshResponse> = JsonMissing.of(),
    ) : this(mcpServerUrl, type, expiresAt, refresh, mutableMapOf())

    /**
     * URL of the MCP server this credential authenticates against.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun mcpServerUrl(): String = mcpServerUrl.getRequired("mcp_server_url")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun type(): Type = type.getRequired("type")

    /**
     * A timestamp in RFC 3339 format
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun expiresAt(): Optional<OffsetDateTime> = expiresAt.getOptional("expires_at")

    /**
     * OAuth refresh token configuration returned in credential responses.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun refresh(): Optional<BetaManagedAgentsMcpOAuthRefreshResponse> =
        refresh.getOptional("refresh")

    /**
     * Returns the raw JSON value of [mcpServerUrl].
     *
     * Unlike [mcpServerUrl], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("mcp_server_url")
    @ExcludeMissing
    fun _mcpServerUrl(): JsonField<String> = mcpServerUrl

    /**
     * Returns the raw JSON value of [type].
     *
     * Unlike [type], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonField<Type> = type

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
    fun _refresh(): JsonField<BetaManagedAgentsMcpOAuthRefreshResponse> = refresh

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
         * [BetaManagedAgentsMcpOAuthAuthResponse].
         *
         * The following fields are required:
         * ```java
         * .mcpServerUrl()
         * .type()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaManagedAgentsMcpOAuthAuthResponse]. */
    class Builder internal constructor() {

        private var mcpServerUrl: JsonField<String>? = null
        private var type: JsonField<Type>? = null
        private var expiresAt: JsonField<OffsetDateTime> = JsonMissing.of()
        private var refresh: JsonField<BetaManagedAgentsMcpOAuthRefreshResponse> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaManagedAgentsMcpOAuthAuthResponse: BetaManagedAgentsMcpOAuthAuthResponse
        ) = apply {
            mcpServerUrl = betaManagedAgentsMcpOAuthAuthResponse.mcpServerUrl
            type = betaManagedAgentsMcpOAuthAuthResponse.type
            expiresAt = betaManagedAgentsMcpOAuthAuthResponse.expiresAt
            refresh = betaManagedAgentsMcpOAuthAuthResponse.refresh
            additionalProperties =
                betaManagedAgentsMcpOAuthAuthResponse.additionalProperties.toMutableMap()
        }

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

        fun type(type: Type) = type(JsonField.of(type))

        /**
         * Sets [Builder.type] to an arbitrary JSON value.
         *
         * You should usually call [Builder.type] with a well-typed [Type] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun type(type: JsonField<Type>) = apply { this.type = type }

        /** A timestamp in RFC 3339 format */
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

        /** OAuth refresh token configuration returned in credential responses. */
        fun refresh(refresh: BetaManagedAgentsMcpOAuthRefreshResponse?) =
            refresh(JsonField.ofNullable(refresh))

        /** Alias for calling [Builder.refresh] with `refresh.orElse(null)`. */
        fun refresh(refresh: Optional<BetaManagedAgentsMcpOAuthRefreshResponse>) =
            refresh(refresh.getOrNull())

        /**
         * Sets [Builder.refresh] to an arbitrary JSON value.
         *
         * You should usually call [Builder.refresh] with a well-typed
         * [BetaManagedAgentsMcpOAuthRefreshResponse] value instead. This method is primarily for
         * setting the field to an undocumented or not yet supported value.
         */
        fun refresh(refresh: JsonField<BetaManagedAgentsMcpOAuthRefreshResponse>) = apply {
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
         * Returns an immutable instance of [BetaManagedAgentsMcpOAuthAuthResponse].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .mcpServerUrl()
         * .type()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaManagedAgentsMcpOAuthAuthResponse =
            BetaManagedAgentsMcpOAuthAuthResponse(
                checkRequired("mcpServerUrl", mcpServerUrl),
                checkRequired("type", type),
                expiresAt,
                refresh,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): BetaManagedAgentsMcpOAuthAuthResponse = apply {
        if (validated) {
            return@apply
        }

        mcpServerUrl()
        type().validate()
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
        (if (mcpServerUrl.asKnown().isPresent) 1 else 0) +
            (type.asKnown().getOrNull()?.validity() ?: 0) +
            (if (expiresAt.asKnown().isPresent) 1 else 0) +
            (refresh.asKnown().getOrNull()?.validity() ?: 0)

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

            @JvmField val MCP_OAUTH = of("mcp_oauth")

            @JvmStatic fun of(value: String) = Type(JsonField.of(value))
        }

        /** An enum containing [Type]'s known values. */
        enum class Known {
            MCP_OAUTH
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
            MCP_OAUTH,
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
                MCP_OAUTH -> Value.MCP_OAUTH
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
                MCP_OAUTH -> Known.MCP_OAUTH
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

        return other is BetaManagedAgentsMcpOAuthAuthResponse &&
            mcpServerUrl == other.mcpServerUrl &&
            type == other.type &&
            expiresAt == other.expiresAt &&
            refresh == other.refresh &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(mcpServerUrl, type, expiresAt, refresh, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsMcpOAuthAuthResponse{mcpServerUrl=$mcpServerUrl, type=$type, expiresAt=$expiresAt, refresh=$refresh, additionalProperties=$additionalProperties}"
}
