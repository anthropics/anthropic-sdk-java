// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.vaults.credentials

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.Enum
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
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

/** Parameters for updating OAuth refresh token configuration. */
class BetaManagedAgentsMcpOAuthRefreshUpdateParams
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val refreshToken: JsonField<String>,
    private val scope: JsonField<String>,
    private val tokenEndpointAuth: JsonField<TokenEndpointAuth>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("refresh_token")
        @ExcludeMissing
        refreshToken: JsonField<String> = JsonMissing.of(),
        @JsonProperty("scope") @ExcludeMissing scope: JsonField<String> = JsonMissing.of(),
        @JsonProperty("token_endpoint_auth")
        @ExcludeMissing
        tokenEndpointAuth: JsonField<TokenEndpointAuth> = JsonMissing.of(),
    ) : this(refreshToken, scope, tokenEndpointAuth, mutableMapOf())

    /**
     * Updated OAuth refresh token.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun refreshToken(): Optional<String> = refreshToken.getOptional("refresh_token")

    /**
     * Updated OAuth scope for the refresh request.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun scope(): Optional<String> = scope.getOptional("scope")

    /**
     * Updated HTTP Basic authentication parameters for the token endpoint.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun tokenEndpointAuth(): Optional<TokenEndpointAuth> =
        tokenEndpointAuth.getOptional("token_endpoint_auth")

    /**
     * Returns the raw JSON value of [refreshToken].
     *
     * Unlike [refreshToken], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("refresh_token")
    @ExcludeMissing
    fun _refreshToken(): JsonField<String> = refreshToken

    /**
     * Returns the raw JSON value of [scope].
     *
     * Unlike [scope], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("scope") @ExcludeMissing fun _scope(): JsonField<String> = scope

    /**
     * Returns the raw JSON value of [tokenEndpointAuth].
     *
     * Unlike [tokenEndpointAuth], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("token_endpoint_auth")
    @ExcludeMissing
    fun _tokenEndpointAuth(): JsonField<TokenEndpointAuth> = tokenEndpointAuth

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
         * [BetaManagedAgentsMcpOAuthRefreshUpdateParams].
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaManagedAgentsMcpOAuthRefreshUpdateParams]. */
    class Builder internal constructor() {

        private var refreshToken: JsonField<String> = JsonMissing.of()
        private var scope: JsonField<String> = JsonMissing.of()
        private var tokenEndpointAuth: JsonField<TokenEndpointAuth> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaManagedAgentsMcpOAuthRefreshUpdateParams:
                BetaManagedAgentsMcpOAuthRefreshUpdateParams
        ) = apply {
            refreshToken = betaManagedAgentsMcpOAuthRefreshUpdateParams.refreshToken
            scope = betaManagedAgentsMcpOAuthRefreshUpdateParams.scope
            tokenEndpointAuth = betaManagedAgentsMcpOAuthRefreshUpdateParams.tokenEndpointAuth
            additionalProperties =
                betaManagedAgentsMcpOAuthRefreshUpdateParams.additionalProperties.toMutableMap()
        }

        /** Updated OAuth refresh token. */
        fun refreshToken(refreshToken: String?) = refreshToken(JsonField.ofNullable(refreshToken))

        /** Alias for calling [Builder.refreshToken] with `refreshToken.orElse(null)`. */
        fun refreshToken(refreshToken: Optional<String>) = refreshToken(refreshToken.getOrNull())

        /**
         * Sets [Builder.refreshToken] to an arbitrary JSON value.
         *
         * You should usually call [Builder.refreshToken] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun refreshToken(refreshToken: JsonField<String>) = apply {
            this.refreshToken = refreshToken
        }

        /** Updated OAuth scope for the refresh request. */
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

        /** Updated HTTP Basic authentication parameters for the token endpoint. */
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

        /**
         * Alias for calling [tokenEndpointAuth] with
         * `TokenEndpointAuth.ofClientSecretBasic(clientSecretBasic)`.
         */
        fun tokenEndpointAuth(
            clientSecretBasic: BetaManagedAgentsTokenEndpointAuthBasicUpdateParam
        ) = tokenEndpointAuth(TokenEndpointAuth.ofClientSecretBasic(clientSecretBasic))

        /**
         * Alias for calling [tokenEndpointAuth] with
         * `TokenEndpointAuth.ofClientSecretPost(clientSecretPost)`.
         */
        fun tokenEndpointAuth(clientSecretPost: BetaManagedAgentsTokenEndpointAuthPostUpdateParam) =
            tokenEndpointAuth(TokenEndpointAuth.ofClientSecretPost(clientSecretPost))

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
         * Returns an immutable instance of [BetaManagedAgentsMcpOAuthRefreshUpdateParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): BetaManagedAgentsMcpOAuthRefreshUpdateParams =
            BetaManagedAgentsMcpOAuthRefreshUpdateParams(
                refreshToken,
                scope,
                tokenEndpointAuth,
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
    fun validate(): BetaManagedAgentsMcpOAuthRefreshUpdateParams = apply {
        if (validated) {
            return@apply
        }

        refreshToken()
        scope()
        tokenEndpointAuth().ifPresent { it.validate() }
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
        (if (refreshToken.asKnown().isPresent) 1 else 0) +
            (if (scope.asKnown().isPresent) 1 else 0) +
            (tokenEndpointAuth.asKnown().getOrNull()?.validity() ?: 0)

    /** Updated HTTP Basic authentication parameters for the token endpoint. */
    @JsonDeserialize(using = TokenEndpointAuth.Deserializer::class)
    @JsonSerialize(using = TokenEndpointAuth.Serializer::class)
    class TokenEndpointAuth
    private constructor(
        private val clientSecretBasic: BetaManagedAgentsTokenEndpointAuthBasicUpdateParam? = null,
        private val clientSecretPost: BetaManagedAgentsTokenEndpointAuthPostUpdateParam? = null,
        private val _json: JsonValue? = null,
    ) {

        fun type(): Type =
            accept(
                object : Visitor<Type> {
                    override fun visitClientSecretBasic(
                        clientSecretBasic: BetaManagedAgentsTokenEndpointAuthBasicUpdateParam
                    ): Type = Type.CLIENT_SECRET_BASIC

                    override fun visitClientSecretPost(
                        clientSecretPost: BetaManagedAgentsTokenEndpointAuthPostUpdateParam
                    ): Type = Type.CLIENT_SECRET_POST

                    override fun unknown(json: JsonValue?): Type =
                        Type.of(json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
                }
            )

        fun clientSecret(): Optional<String> =
            accept(
                object : Visitor<Optional<String>> {
                    override fun visitClientSecretBasic(
                        clientSecretBasic: BetaManagedAgentsTokenEndpointAuthBasicUpdateParam
                    ): Optional<String> = clientSecretBasic.clientSecret()

                    override fun visitClientSecretPost(
                        clientSecretPost: BetaManagedAgentsTokenEndpointAuthPostUpdateParam
                    ): Optional<String> = clientSecretPost.clientSecret()
                }
            )

        /** Updated HTTP Basic authentication parameters for the token endpoint. */
        fun clientSecretBasic(): Optional<BetaManagedAgentsTokenEndpointAuthBasicUpdateParam> =
            Optional.ofNullable(clientSecretBasic)

        /** Updated POST body authentication parameters for the token endpoint. */
        fun clientSecretPost(): Optional<BetaManagedAgentsTokenEndpointAuthPostUpdateParam> =
            Optional.ofNullable(clientSecretPost)

        fun isClientSecretBasic(): Boolean = clientSecretBasic != null

        fun isClientSecretPost(): Boolean = clientSecretPost != null

        /** Updated HTTP Basic authentication parameters for the token endpoint. */
        fun asClientSecretBasic(): BetaManagedAgentsTokenEndpointAuthBasicUpdateParam =
            clientSecretBasic.getOrThrow("clientSecretBasic")

        /** Updated POST body authentication parameters for the token endpoint. */
        fun asClientSecretPost(): BetaManagedAgentsTokenEndpointAuthPostUpdateParam =
            clientSecretPost.getOrThrow("clientSecretPost")

        fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

        /**
         * Maps this instance's current variant to a value of type [T] using the given [visitor].
         *
         * Note that this method is _not_ forwards compatible with new variants from the API, unless
         * [visitor] overrides [Visitor.unknown]. To handle variants not known to this version of
         * the SDK gracefully, consider overriding [Visitor.unknown]:
         * ```java
         * import com.anthropic.core.JsonValue;
         * import java.util.Optional;
         *
         * Optional<String> result = tokenEndpointAuth.accept(new TokenEndpointAuth.Visitor<Optional<String>>() {
         *     @Override
         *     public Optional<String> visitClientSecretBasic(BetaManagedAgentsTokenEndpointAuthBasicUpdateParam clientSecretBasic) {
         *         return Optional.of(clientSecretBasic.toString());
         *     }
         *
         *     // ...
         *
         *     @Override
         *     public Optional<String> unknown(JsonValue json) {
         *         // Or inspect the `json`.
         *         return Optional.empty();
         *     }
         * });
         * ```
         *
         * @throws AnthropicInvalidDataException if [Visitor.unknown] is not overridden in [visitor]
         *   and the current variant is unknown.
         */
        fun <T> accept(visitor: Visitor<T>): T =
            when {
                clientSecretBasic != null -> visitor.visitClientSecretBasic(clientSecretBasic)
                clientSecretPost != null -> visitor.visitClientSecretPost(clientSecretPost)
                else -> visitor.unknown(_json)
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
        fun validate(): TokenEndpointAuth = apply {
            if (validated) {
                return@apply
            }

            accept(
                object : Visitor<Unit> {
                    override fun visitClientSecretBasic(
                        clientSecretBasic: BetaManagedAgentsTokenEndpointAuthBasicUpdateParam
                    ) {
                        clientSecretBasic.validate()
                    }

                    override fun visitClientSecretPost(
                        clientSecretPost: BetaManagedAgentsTokenEndpointAuthPostUpdateParam
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
                    override fun visitClientSecretBasic(
                        clientSecretBasic: BetaManagedAgentsTokenEndpointAuthBasicUpdateParam
                    ) = clientSecretBasic.validity()

                    override fun visitClientSecretPost(
                        clientSecretPost: BetaManagedAgentsTokenEndpointAuthPostUpdateParam
                    ) = clientSecretPost.validity()

                    override fun unknown(json: JsonValue?) = 0
                }
            )

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is TokenEndpointAuth &&
                clientSecretBasic == other.clientSecretBasic &&
                clientSecretPost == other.clientSecretPost
        }

        override fun hashCode(): Int = Objects.hash(clientSecretBasic, clientSecretPost)

        override fun toString(): String =
            when {
                clientSecretBasic != null ->
                    "TokenEndpointAuth{clientSecretBasic=$clientSecretBasic}"
                clientSecretPost != null -> "TokenEndpointAuth{clientSecretPost=$clientSecretPost}"
                _json != null -> "TokenEndpointAuth{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid TokenEndpointAuth")
            }

        companion object {

            /** Updated HTTP Basic authentication parameters for the token endpoint. */
            @JvmStatic
            fun ofClientSecretBasic(
                clientSecretBasic: BetaManagedAgentsTokenEndpointAuthBasicUpdateParam
            ) = TokenEndpointAuth(clientSecretBasic = clientSecretBasic)

            /**
             * Returns an immutable instance of [TokenEndpointAuth] whose [ofClientSecretBasic]
             * variant is built from the given required [type].
             */
            @JvmStatic
            fun ofClientSecretBasic(type: BetaManagedAgentsTokenEndpointAuthBasicUpdateParam.Type) =
                ofClientSecretBasic(BetaManagedAgentsTokenEndpointAuthBasicUpdateParam.of(type))

            /** Updated POST body authentication parameters for the token endpoint. */
            @JvmStatic
            fun ofClientSecretPost(
                clientSecretPost: BetaManagedAgentsTokenEndpointAuthPostUpdateParam
            ) = TokenEndpointAuth(clientSecretPost = clientSecretPost)

            /**
             * Returns an immutable instance of [TokenEndpointAuth] whose [ofClientSecretPost]
             * variant is built from the given required [type].
             */
            @JvmStatic
            fun ofClientSecretPost(type: BetaManagedAgentsTokenEndpointAuthPostUpdateParam.Type) =
                ofClientSecretPost(BetaManagedAgentsTokenEndpointAuthPostUpdateParam.of(type))
        }

        /**
         * An interface that defines how to map each variant of [TokenEndpointAuth] to a value of
         * type [T].
         */
        interface Visitor<out T> {

            /** Updated HTTP Basic authentication parameters for the token endpoint. */
            fun visitClientSecretBasic(
                clientSecretBasic: BetaManagedAgentsTokenEndpointAuthBasicUpdateParam
            ): T

            /** Updated POST body authentication parameters for the token endpoint. */
            fun visitClientSecretPost(
                clientSecretPost: BetaManagedAgentsTokenEndpointAuthPostUpdateParam
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
                    "client_secret_basic" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaManagedAgentsTokenEndpointAuthBasicUpdateParam>(),
                            )
                            ?.let { TokenEndpointAuth(clientSecretBasic = it, _json = json) }
                            ?: TokenEndpointAuth(_json = json)
                    }
                    "client_secret_post" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaManagedAgentsTokenEndpointAuthPostUpdateParam>(),
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
                    value.clientSecretBasic != null ->
                        generator.writeObject(value.clientSecretBasic)
                    value.clientSecretPost != null -> generator.writeObject(value.clientSecretPost)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid TokenEndpointAuth")
                }
            }
        }

        class Type @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

            /**
             * Returns this class instance's raw value.
             *
             * This is usually only useful if this instance was deserialized from data that doesn't
             * match any known member, and you want to know that value. For example, if the SDK is
             * on an older version than the API, then the API may respond with new members that the
             * SDK is unaware of.
             */
            @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

            companion object {

                @JvmField val CLIENT_SECRET_BASIC = of("client_secret_basic")

                @JvmField val CLIENT_SECRET_POST = of("client_secret_post")

                @JvmStatic fun of(value: String) = Type(JsonField.of(value))

                @JvmSynthetic
                internal fun of(value: JsonValue): Type =
                    value.asString().getOrNull()?.let { of(it) } ?: Type(value)
            }

            /** An enum containing [Type]'s known values. */
            enum class Known {
                CLIENT_SECRET_BASIC,
                CLIENT_SECRET_POST,
            }

            /**
             * An enum containing [Type]'s known values, as well as an [_UNKNOWN] member.
             *
             * An instance of [Type] can contain an unknown value in a couple of cases:
             * - It was deserialized from data that doesn't match any known member. For example, if
             *   the SDK is on an older version than the API, then the API may respond with new
             *   members that the SDK is unaware of.
             * - It was constructed with an arbitrary value using the [of] method.
             */
            enum class Value {
                CLIENT_SECRET_BASIC,
                CLIENT_SECRET_POST,
                /** An enum member indicating that [Type] was instantiated with an unknown value. */
                _UNKNOWN,
            }

            /**
             * Returns an enum member corresponding to this class instance's value, or
             * [Value._UNKNOWN] if the class was instantiated with an unknown value.
             *
             * Use the [known] method instead if you're certain the value is always known or if you
             * want to throw for the unknown case.
             */
            fun value(): Value =
                when (this) {
                    CLIENT_SECRET_BASIC -> Value.CLIENT_SECRET_BASIC
                    CLIENT_SECRET_POST -> Value.CLIENT_SECRET_POST
                    else -> Value._UNKNOWN
                }

            /**
             * Returns an enum member corresponding to this class instance's value.
             *
             * Use the [value] method instead if you're uncertain the value is always known and
             * don't want to throw for the unknown case.
             *
             * @throws AnthropicInvalidDataException if this class instance's value is a not a known
             *   member.
             */
            fun known(): Known =
                when (this) {
                    CLIENT_SECRET_BASIC -> Known.CLIENT_SECRET_BASIC
                    CLIENT_SECRET_POST -> Known.CLIENT_SECRET_POST
                    else -> throw AnthropicInvalidDataException("Unknown Type: $value")
                }

            /**
             * Returns this class instance's primitive wire representation.
             *
             * This differs from the [toString] method because that method is primarily for
             * debugging and generally doesn't throw.
             *
             * @throws AnthropicInvalidDataException if this class instance's value does not have
             *   the expected primitive type.
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
             * This method is _not_ forwards compatible with new types from the API for existing
             * fields.
             *
             * @throws AnthropicInvalidDataException if any value type in this object doesn't match
             *   its expected type.
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
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsMcpOAuthRefreshUpdateParams &&
            refreshToken == other.refreshToken &&
            scope == other.scope &&
            tokenEndpointAuth == other.tokenEndpointAuth &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(refreshToken, scope, tokenEndpointAuth, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsMcpOAuthRefreshUpdateParams{refreshToken=$refreshToken, scope=$scope, tokenEndpointAuth=$tokenEndpointAuth, additionalProperties=$additionalProperties}"
}
