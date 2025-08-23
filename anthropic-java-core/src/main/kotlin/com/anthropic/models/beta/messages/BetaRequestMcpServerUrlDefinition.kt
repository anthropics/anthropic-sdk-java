// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

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

class BetaRequestMcpServerUrlDefinition
private constructor(
    private val name: JsonField<String>,
    private val type: JsonValue,
    private val url: JsonField<String>,
    private val authorizationToken: JsonField<String>,
    private val toolConfiguration: JsonField<BetaRequestMcpServerToolConfiguration>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("name") @ExcludeMissing name: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("url") @ExcludeMissing url: JsonField<String> = JsonMissing.of(),
        @JsonProperty("authorization_token")
        @ExcludeMissing
        authorizationToken: JsonField<String> = JsonMissing.of(),
        @JsonProperty("tool_configuration")
        @ExcludeMissing
        toolConfiguration: JsonField<BetaRequestMcpServerToolConfiguration> = JsonMissing.of(),
    ) : this(name, type, url, authorizationToken, toolConfiguration, mutableMapOf())

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun name(): String = name.getRequired("name")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("url")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun url(): String = url.getRequired("url")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun authorizationToken(): Optional<String> =
        authorizationToken.getOptional("authorization_token")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun toolConfiguration(): Optional<BetaRequestMcpServerToolConfiguration> =
        toolConfiguration.getOptional("tool_configuration")

    /**
     * Returns the raw JSON value of [name].
     *
     * Unlike [name], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("name") @ExcludeMissing fun _name(): JsonField<String> = name

    /**
     * Returns the raw JSON value of [url].
     *
     * Unlike [url], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("url") @ExcludeMissing fun _url(): JsonField<String> = url

    /**
     * Returns the raw JSON value of [authorizationToken].
     *
     * Unlike [authorizationToken], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("authorization_token")
    @ExcludeMissing
    fun _authorizationToken(): JsonField<String> = authorizationToken

    /**
     * Returns the raw JSON value of [toolConfiguration].
     *
     * Unlike [toolConfiguration], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("tool_configuration")
    @ExcludeMissing
    fun _toolConfiguration(): JsonField<BetaRequestMcpServerToolConfiguration> = toolConfiguration

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
         * [BetaRequestMcpServerUrlDefinition].
         *
         * The following fields are required:
         * ```java
         * .name()
         * .url()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaRequestMcpServerUrlDefinition]. */
    class Builder internal constructor() {

        private var name: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("url")
        private var url: JsonField<String>? = null
        private var authorizationToken: JsonField<String> = JsonMissing.of()
        private var toolConfiguration: JsonField<BetaRequestMcpServerToolConfiguration> =
            JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaRequestMcpServerUrlDefinition: BetaRequestMcpServerUrlDefinition) =
            apply {
                name = betaRequestMcpServerUrlDefinition.name
                type = betaRequestMcpServerUrlDefinition.type
                url = betaRequestMcpServerUrlDefinition.url
                authorizationToken = betaRequestMcpServerUrlDefinition.authorizationToken
                toolConfiguration = betaRequestMcpServerUrlDefinition.toolConfiguration
                additionalProperties =
                    betaRequestMcpServerUrlDefinition.additionalProperties.toMutableMap()
            }

        fun name(name: String) = name(JsonField.of(name))

        /**
         * Sets [Builder.name] to an arbitrary JSON value.
         *
         * You should usually call [Builder.name] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun name(name: JsonField<String>) = apply { this.name = name }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("url")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        fun url(url: String) = url(JsonField.of(url))

        /**
         * Sets [Builder.url] to an arbitrary JSON value.
         *
         * You should usually call [Builder.url] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun url(url: JsonField<String>) = apply { this.url = url }

        fun authorizationToken(authorizationToken: String?) =
            authorizationToken(JsonField.ofNullable(authorizationToken))

        /**
         * Alias for calling [Builder.authorizationToken] with `authorizationToken.orElse(null)`.
         */
        fun authorizationToken(authorizationToken: Optional<String>) =
            authorizationToken(authorizationToken.getOrNull())

        /**
         * Sets [Builder.authorizationToken] to an arbitrary JSON value.
         *
         * You should usually call [Builder.authorizationToken] with a well-typed [String] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun authorizationToken(authorizationToken: JsonField<String>) = apply {
            this.authorizationToken = authorizationToken
        }

        fun toolConfiguration(toolConfiguration: BetaRequestMcpServerToolConfiguration?) =
            toolConfiguration(JsonField.ofNullable(toolConfiguration))

        /** Alias for calling [Builder.toolConfiguration] with `toolConfiguration.orElse(null)`. */
        fun toolConfiguration(toolConfiguration: Optional<BetaRequestMcpServerToolConfiguration>) =
            toolConfiguration(toolConfiguration.getOrNull())

        /**
         * Sets [Builder.toolConfiguration] to an arbitrary JSON value.
         *
         * You should usually call [Builder.toolConfiguration] with a well-typed
         * [BetaRequestMcpServerToolConfiguration] value instead. This method is primarily for
         * setting the field to an undocumented or not yet supported value.
         */
        fun toolConfiguration(toolConfiguration: JsonField<BetaRequestMcpServerToolConfiguration>) =
            apply {
                this.toolConfiguration = toolConfiguration
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
         * Returns an immutable instance of [BetaRequestMcpServerUrlDefinition].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .name()
         * .url()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaRequestMcpServerUrlDefinition =
            BetaRequestMcpServerUrlDefinition(
                checkRequired("name", name),
                type,
                checkRequired("url", url),
                authorizationToken,
                toolConfiguration,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): BetaRequestMcpServerUrlDefinition = apply {
        if (validated) {
            return@apply
        }

        name()
        _type().let {
            if (it != JsonValue.from("url")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        url()
        authorizationToken()
        toolConfiguration().ifPresent { it.validate() }
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
        (if (name.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("url")) 1 else 0 } +
            (if (url.asKnown().isPresent) 1 else 0) +
            (if (authorizationToken.asKnown().isPresent) 1 else 0) +
            (toolConfiguration.asKnown().getOrNull()?.validity() ?: 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaRequestMcpServerUrlDefinition &&
            name == other.name &&
            type == other.type &&
            url == other.url &&
            authorizationToken == other.authorizationToken &&
            toolConfiguration == other.toolConfiguration &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(name, type, url, authorizationToken, toolConfiguration, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaRequestMcpServerUrlDefinition{name=$name, type=$type, url=$url, authorizationToken=$authorizationToken, toolConfiguration=$toolConfiguration, additionalProperties=$additionalProperties}"
}
