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
import java.util.Collections
import java.util.Objects

/** Parameters for creating a static bearer token credential. */
class BetaManagedAgentsStaticBearerCreateParams
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val token: JsonField<String>,
    private val mcpServerUrl: JsonField<String>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("token") @ExcludeMissing token: JsonField<String> = JsonMissing.of(),
        @JsonProperty("mcp_server_url")
        @ExcludeMissing
        mcpServerUrl: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(token, mcpServerUrl, type, mutableMapOf())

    /**
     * The static bearer token to send to the MCP server, such as an API key or personal access
     * token.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun token(): String = token.getRequired("token")

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
     * JsonValue.from("static_bearer")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Returns the raw JSON value of [token].
     *
     * Unlike [token], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("token") @ExcludeMissing fun _token(): JsonField<String> = token

    /**
     * Returns the raw JSON value of [mcpServerUrl].
     *
     * Unlike [mcpServerUrl], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("mcp_server_url")
    @ExcludeMissing
    fun _mcpServerUrl(): JsonField<String> = mcpServerUrl

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
         * [BetaManagedAgentsStaticBearerCreateParams].
         *
         * The following fields are required:
         * ```java
         * .token()
         * .mcpServerUrl()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaManagedAgentsStaticBearerCreateParams]. */
    class Builder internal constructor() {

        private var token: JsonField<String>? = null
        private var mcpServerUrl: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("static_bearer")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaManagedAgentsStaticBearerCreateParams: BetaManagedAgentsStaticBearerCreateParams
        ) = apply {
            token = betaManagedAgentsStaticBearerCreateParams.token
            mcpServerUrl = betaManagedAgentsStaticBearerCreateParams.mcpServerUrl
            type = betaManagedAgentsStaticBearerCreateParams.type
            additionalProperties =
                betaManagedAgentsStaticBearerCreateParams.additionalProperties.toMutableMap()
        }

        /**
         * The static bearer token to send to the MCP server, such as an API key or personal access
         * token.
         */
        fun token(token: String) = token(JsonField.of(token))

        /**
         * Sets [Builder.token] to an arbitrary JSON value.
         *
         * You should usually call [Builder.token] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun token(token: JsonField<String>) = apply { this.token = token }

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
         * JsonValue.from("static_bearer")
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
         * Returns an immutable instance of [BetaManagedAgentsStaticBearerCreateParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .token()
         * .mcpServerUrl()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaManagedAgentsStaticBearerCreateParams =
            BetaManagedAgentsStaticBearerCreateParams(
                checkRequired("token", token),
                checkRequired("mcpServerUrl", mcpServerUrl),
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
    fun validate(): BetaManagedAgentsStaticBearerCreateParams = apply {
        if (validated) {
            return@apply
        }

        token()
        mcpServerUrl()
        _type().let {
            if (it != JsonValue.from("static_bearer")) {
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
        (if (token.asKnown().isPresent) 1 else 0) +
            (if (mcpServerUrl.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("static_bearer")) 1 else 0 }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsStaticBearerCreateParams &&
            token == other.token &&
            mcpServerUrl == other.mcpServerUrl &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(token, mcpServerUrl, type, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsStaticBearerCreateParams{token=$token, mcpServerUrl=$mcpServerUrl, type=$type, additionalProperties=$additionalProperties}"
}
