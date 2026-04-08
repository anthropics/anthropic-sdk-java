// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.vaults.credentials

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkRequired
import com.anthropic.core.getOrThrow
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.ObjectCodec
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** OAuth refresh token configuration returned in credential responses. */
class BetaManagedAgentsMcpOAuthRefreshResponse
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val clientId: JsonField<String>,
    private val tokenEndpoint: JsonField<String>,
    private val tokenEndpointAuth: JsonField<TokenEndpointAuth>,
    private val resource: JsonField<String>,
    private val scope: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("client_id") @ExcludeMissing clientId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("token_endpoint")
        @ExcludeMissing
        tokenEndpoint: JsonField<String> = JsonMissing.of(),
        @JsonProperty("token_endpoint_auth")
        @ExcludeMissing
        tokenEndpointAuth: JsonField<TokenEndpointAuth> = JsonMissing.of(),
        @JsonProperty("resource") @ExcludeMissing resource: JsonField<String> = JsonMissing.of(),
        @JsonProperty("scope") @ExcludeMissing scope: JsonField<String> = JsonMissing.of(),
    ) : this(clientId, tokenEndpoint, tokenEndpointAuth, resource, scope, mutableMapOf())

    /**
     * OAuth client ID.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun clientId(): String = clientId.getRequired("client_id")

    /**
     * Token endpoint URL used to refresh the access token.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun tokenEndpoint(): String = tokenEndpoint.getRequired("token_endpoint")

    /**
     * Token endpoint requires no client authentication.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun tokenEndpointAuth(): TokenEndpointAuth =
        tokenEndpointAuth.getRequired("token_endpoint_auth")

    /**
     * OAuth resource indicator.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun resource(): Optional<String> = resource.getOptional("resource")

    /**
     * OAuth scope for the refresh request.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun scope(): Optional<String> = scope.getOptional("scope")

    /**
     * Returns the raw JSON value of [clientId].
     *
     * Unlike [clientId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("client_id") @ExcludeMissing fun _clientId(): JsonField<String> = clientId

    /**
     * Returns the raw JSON value of [tokenEndpoint].
     *
     * Unlike [tokenEndpoint], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("token_endpoint")
    @ExcludeMissing
    fun _tokenEndpoint(): JsonField<String> = tokenEndpoint

    /**
     * Returns the raw JSON value of [tokenEndpointAuth].
     *
     * Unlike [tokenEndpointAuth], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("token_endpoint_auth")
    @ExcludeMissing
    fun _tokenEndpointAuth(): JsonField<TokenEndpointAuth> = tokenEndpointAuth

    /**
     * Returns the raw JSON value of [resource].
     *
     * Unlike [resource], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("resource") @ExcludeMissing fun _resource(): JsonField<String> = resource

    /**
     * Returns the raw JSON value of [scope].
     *
     * Unlike [scope], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("scope") @ExcludeMissing fun _scope(): JsonField<String> = scope

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
         * [BetaManagedAgentsMcpOAuthRefreshResponse].
         *
         * The following fields are required:
         * ```java
         * .clientId()
         * .tokenEndpoint()
         * .tokenEndpointAuth()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaManagedAgentsMcpOAuthRefreshResponse]. */
    class Builder internal constructor() {

        private var clientId: JsonField<String>? = null
        private var tokenEndpoint: JsonField<String>? = null
        private var tokenEndpointAuth: JsonField<TokenEndpointAuth>? = null
        private var resource: JsonField<String> = JsonMissing.of()
        private var scope: JsonField<String> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaManagedAgentsMcpOAuthRefreshResponse: BetaManagedAgentsMcpOAuthRefreshResponse
        ) = apply {
            clientId = betaManagedAgentsMcpOAuthRefreshResponse.clientId
            tokenEndpoint = betaManagedAgentsMcpOAuthRefreshResponse.tokenEndpoint
            tokenEndpointAuth = betaManagedAgentsMcpOAuthRefreshResponse.tokenEndpointAuth
            resource = betaManagedAgentsMcpOAuthRefreshResponse.resource
            scope = betaManagedAgentsMcpOAuthRefreshResponse.scope
            additionalProperties =
                betaManagedAgentsMcpOAuthRefreshResponse.additionalProperties.toMutableMap()
        }

        /** OAuth client ID. */
        fun clientId(clientId: String) = clientId(JsonField.of(clientId))

        /**
         * Sets [Builder.clientId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.clientId] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun clientId(clientId: JsonField<String>) = apply { this.clientId = clientId }

        /** Token endpoint URL used to refresh the access token. */
        fun tokenEndpoint(tokenEndpoint: String) = tokenEndpoint(JsonField.of(tokenEndpoint))

        /**
         * Sets [Builder.tokenEndpoint] to an arbitrary JSON value.
         *
         * You should usually call [Builder.tokenEndpoint] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun tokenEndpoint(tokenEndpoint: JsonField<String>) = apply {
            this.tokenEndpoint = tokenEndpoint
        }

        /** Token endpoint requires no client authentication. */
        fun tokenEndpointAuth(tokenEndpointAuth: TokenEndpointAuth) =
            tokenEndpointAuth(JsonField.of(tokenEndpointAuth))

        /**
         * Sets [Builder.tokenEndpointAuth] to an arbitrary JSON value.
         *
         * You should usually call [Builder.tokenEndpointAuth] with a well-typed [TokenEndpointAuth]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun tokenEndpointAuth(tokenEndpointAuth: JsonField<TokenEndpointAuth>) = apply {
            this.tokenEndpointAuth = tokenEndpointAuth
        }

        /** Alias for calling [tokenEndpointAuth] with `TokenEndpointAuth.ofNone(none)`. */
        fun tokenEndpointAuth(none: BetaManagedAgentsTokenEndpointAuthNoneResponse) =
            tokenEndpointAuth(TokenEndpointAuth.ofNone(none))

        /**
         * Alias for calling [tokenEndpointAuth] with
         * `TokenEndpointAuth.ofClientSecretBasic(clientSecretBasic)`.
         */
        fun tokenEndpointAuth(clientSecretBasic: BetaManagedAgentsTokenEndpointAuthBasicResponse) =
            tokenEndpointAuth(TokenEndpointAuth.ofClientSecretBasic(clientSecretBasic))

        /**
         * Alias for calling [tokenEndpointAuth] with
         * `TokenEndpointAuth.ofClientSecretPost(clientSecretPost)`.
         */
        fun tokenEndpointAuth(clientSecretPost: BetaManagedAgentsTokenEndpointAuthPostResponse) =
            tokenEndpointAuth(TokenEndpointAuth.ofClientSecretPost(clientSecretPost))

        /** OAuth resource indicator. */
        fun resource(resource: String?) = resource(JsonField.ofNullable(resource))

        /** Alias for calling [Builder.resource] with `resource.orElse(null)`. */
        fun resource(resource: Optional<String>) = resource(resource.getOrNull())

        /**
         * Sets [Builder.resource] to an arbitrary JSON value.
         *
         * You should usually call [Builder.resource] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun resource(resource: JsonField<String>) = apply { this.resource = resource }

        /** OAuth scope for the refresh request. */
        fun scope(scope: String?) = scope(JsonField.ofNullable(scope))

        /** Alias for calling [Builder.scope] with `scope.orElse(null)`. */
        fun scope(scope: Optional<String>) = scope(scope.getOrNull())

        /**
         * Sets [Builder.scope] to an arbitrary JSON value.
         *
         * You should usually call [Builder.scope] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun scope(scope: JsonField<String>) = apply { this.scope = scope }

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
         * Returns an immutable instance of [BetaManagedAgentsMcpOAuthRefreshResponse].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .clientId()
         * .tokenEndpoint()
         * .tokenEndpointAuth()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaManagedAgentsMcpOAuthRefreshResponse =
            BetaManagedAgentsMcpOAuthRefreshResponse(
                checkRequired("clientId", clientId),
                checkRequired("tokenEndpoint", tokenEndpoint),
                checkRequired("tokenEndpointAuth", tokenEndpointAuth),
                resource,
                scope,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): BetaManagedAgentsMcpOAuthRefreshResponse = apply {
        if (validated) {
            return@apply
        }

        clientId()
        tokenEndpoint()
        tokenEndpointAuth().validate()
        resource()
        scope()
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
        (if (clientId.asKnown().isPresent) 1 else 0) +
            (if (tokenEndpoint.asKnown().isPresent) 1 else 0) +
            (tokenEndpointAuth.asKnown().getOrNull()?.validity() ?: 0) +
            (if (resource.asKnown().isPresent) 1 else 0) +
            (if (scope.asKnown().isPresent) 1 else 0)

    /** Token endpoint requires no client authentication. */
    @JsonDeserialize(using = TokenEndpointAuth.Deserializer::class)
    @JsonSerialize(using = TokenEndpointAuth.Serializer::class)
    class TokenEndpointAuth
    private constructor(
        private val none: BetaManagedAgentsTokenEndpointAuthNoneResponse? = null,
        private val clientSecretBasic: BetaManagedAgentsTokenEndpointAuthBasicResponse? = null,
        private val clientSecretPost: BetaManagedAgentsTokenEndpointAuthPostResponse? = null,
        private val _json: JsonValue? = null,
    ) {

        /** Token endpoint requires no client authentication. */
        fun none(): Optional<BetaManagedAgentsTokenEndpointAuthNoneResponse> =
            Optional.ofNullable(none)

        /** Token endpoint uses HTTP Basic authentication with client credentials. */
        fun clientSecretBasic(): Optional<BetaManagedAgentsTokenEndpointAuthBasicResponse> =
            Optional.ofNullable(clientSecretBasic)

        /** Token endpoint uses POST body authentication with client credentials. */
        fun clientSecretPost(): Optional<BetaManagedAgentsTokenEndpointAuthPostResponse> =
            Optional.ofNullable(clientSecretPost)

        fun isNone(): Boolean = none != null

        fun isClientSecretBasic(): Boolean = clientSecretBasic != null

        fun isClientSecretPost(): Boolean = clientSecretPost != null

        /** Token endpoint requires no client authentication. */
        fun asNone(): BetaManagedAgentsTokenEndpointAuthNoneResponse = none.getOrThrow("none")

        /** Token endpoint uses HTTP Basic authentication with client credentials. */
        fun asClientSecretBasic(): BetaManagedAgentsTokenEndpointAuthBasicResponse =
            clientSecretBasic.getOrThrow("clientSecretBasic")

        /** Token endpoint uses POST body authentication with client credentials. */
        fun asClientSecretPost(): BetaManagedAgentsTokenEndpointAuthPostResponse =
            clientSecretPost.getOrThrow("clientSecretPost")

        fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

        fun <T> accept(visitor: Visitor<T>): T =
            when {
                none != null -> visitor.visitNone(none)
                clientSecretBasic != null -> visitor.visitClientSecretBasic(clientSecretBasic)
                clientSecretPost != null -> visitor.visitClientSecretPost(clientSecretPost)
                else -> visitor.unknown(_json)
            }

        private var validated: Boolean = false

        fun validate(): TokenEndpointAuth = apply {
            if (validated) {
                return@apply
            }

            accept(
                object : Visitor<Unit> {
                    override fun visitNone(none: BetaManagedAgentsTokenEndpointAuthNoneResponse) {
                        none.validate()
                    }

                    override fun visitClientSecretBasic(
                        clientSecretBasic: BetaManagedAgentsTokenEndpointAuthBasicResponse
                    ) {
                        clientSecretBasic.validate()
                    }

                    override fun visitClientSecretPost(
                        clientSecretPost: BetaManagedAgentsTokenEndpointAuthPostResponse
                    ) {
                        clientSecretPost.validate()
                    }
                }
            )
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
        @JvmSynthetic
        internal fun validity(): Int =
            accept(
                object : Visitor<Int> {
                    override fun visitNone(none: BetaManagedAgentsTokenEndpointAuthNoneResponse) =
                        none.validity()

                    override fun visitClientSecretBasic(
                        clientSecretBasic: BetaManagedAgentsTokenEndpointAuthBasicResponse
                    ) = clientSecretBasic.validity()

                    override fun visitClientSecretPost(
                        clientSecretPost: BetaManagedAgentsTokenEndpointAuthPostResponse
                    ) = clientSecretPost.validity()

                    override fun unknown(json: JsonValue?) = 0
                }
            )

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is TokenEndpointAuth &&
                none == other.none &&
                clientSecretBasic == other.clientSecretBasic &&
                clientSecretPost == other.clientSecretPost
        }

        override fun hashCode(): Int = Objects.hash(none, clientSecretBasic, clientSecretPost)

        override fun toString(): String =
            when {
                none != null -> "TokenEndpointAuth{none=$none}"
                clientSecretBasic != null ->
                    "TokenEndpointAuth{clientSecretBasic=$clientSecretBasic}"
                clientSecretPost != null -> "TokenEndpointAuth{clientSecretPost=$clientSecretPost}"
                _json != null -> "TokenEndpointAuth{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid TokenEndpointAuth")
            }

        companion object {

            /** Token endpoint requires no client authentication. */
            @JvmStatic
            fun ofNone(none: BetaManagedAgentsTokenEndpointAuthNoneResponse) =
                TokenEndpointAuth(none = none)

            /** Token endpoint uses HTTP Basic authentication with client credentials. */
            @JvmStatic
            fun ofClientSecretBasic(
                clientSecretBasic: BetaManagedAgentsTokenEndpointAuthBasicResponse
            ) = TokenEndpointAuth(clientSecretBasic = clientSecretBasic)

            /** Token endpoint uses POST body authentication with client credentials. */
            @JvmStatic
            fun ofClientSecretPost(
                clientSecretPost: BetaManagedAgentsTokenEndpointAuthPostResponse
            ) = TokenEndpointAuth(clientSecretPost = clientSecretPost)
        }

        /**
         * An interface that defines how to map each variant of [TokenEndpointAuth] to a value of
         * type [T].
         */
        interface Visitor<out T> {

            /** Token endpoint requires no client authentication. */
            fun visitNone(none: BetaManagedAgentsTokenEndpointAuthNoneResponse): T

            /** Token endpoint uses HTTP Basic authentication with client credentials. */
            fun visitClientSecretBasic(
                clientSecretBasic: BetaManagedAgentsTokenEndpointAuthBasicResponse
            ): T

            /** Token endpoint uses POST body authentication with client credentials. */
            fun visitClientSecretPost(
                clientSecretPost: BetaManagedAgentsTokenEndpointAuthPostResponse
            ): T

            /**
             * Maps an unknown variant of [TokenEndpointAuth] to a value of type [T].
             *
             * An instance of [TokenEndpointAuth] can contain an unknown variant if it was
             * deserialized from data that doesn't match any known variant. For example, if the SDK
             * is on an older version than the API, then the API may respond with new variants that
             * the SDK is unaware of.
             *
             * @throws AnthropicInvalidDataException in the default implementation.
             */
            fun unknown(json: JsonValue?): T {
                throw AnthropicInvalidDataException("Unknown TokenEndpointAuth: $json")
            }
        }

        internal class Deserializer :
            BaseDeserializer<TokenEndpointAuth>(TokenEndpointAuth::class) {

            override fun ObjectCodec.deserialize(node: JsonNode): TokenEndpointAuth {
                val json = JsonValue.fromJsonNode(node)
                val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

                when (type) {
                    "none" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaManagedAgentsTokenEndpointAuthNoneResponse>(),
                            )
                            ?.let { TokenEndpointAuth(none = it, _json = json) }
                            ?: TokenEndpointAuth(_json = json)
                    }
                    "client_secret_basic" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaManagedAgentsTokenEndpointAuthBasicResponse>(),
                            )
                            ?.let { TokenEndpointAuth(clientSecretBasic = it, _json = json) }
                            ?: TokenEndpointAuth(_json = json)
                    }
                    "client_secret_post" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaManagedAgentsTokenEndpointAuthPostResponse>(),
                            )
                            ?.let { TokenEndpointAuth(clientSecretPost = it, _json = json) }
                            ?: TokenEndpointAuth(_json = json)
                    }
                }

                return TokenEndpointAuth(_json = json)
            }
        }

        internal class Serializer : BaseSerializer<TokenEndpointAuth>(TokenEndpointAuth::class) {

            override fun serialize(
                value: TokenEndpointAuth,
                generator: JsonGenerator,
                provider: SerializerProvider,
            ) {
                when {
                    value.none != null -> generator.writeObject(value.none)
                    value.clientSecretBasic != null ->
                        generator.writeObject(value.clientSecretBasic)
                    value.clientSecretPost != null -> generator.writeObject(value.clientSecretPost)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid TokenEndpointAuth")
                }
            }
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsMcpOAuthRefreshResponse &&
            clientId == other.clientId &&
            tokenEndpoint == other.tokenEndpoint &&
            tokenEndpointAuth == other.tokenEndpointAuth &&
            resource == other.resource &&
            scope == other.scope &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            clientId,
            tokenEndpoint,
            tokenEndpointAuth,
            resource,
            scope,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsMcpOAuthRefreshResponse{clientId=$clientId, tokenEndpoint=$tokenEndpoint, tokenEndpointAuth=$tokenEndpointAuth, resource=$resource, scope=$scope, additionalProperties=$additionalProperties}"
}
