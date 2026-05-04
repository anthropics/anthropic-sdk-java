// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.vaults.credentials

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.Enum
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkRequired
import com.anthropic.core.getOrThrow
import com.anthropic.core.toImmutable
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
import java.time.OffsetDateTime
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** A credential stored in a vault. Sensitive fields are never returned in responses. */
class BetaManagedAgentsCredential
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val id: JsonField<String>,
    private val archivedAt: JsonField<OffsetDateTime>,
    private val auth: JsonField<Auth>,
    private val createdAt: JsonField<OffsetDateTime>,
    private val metadata: JsonField<Metadata>,
    private val type: JsonField<Type>,
    private val updatedAt: JsonField<OffsetDateTime>,
    private val vaultId: JsonField<String>,
    private val displayName: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("archived_at")
        @ExcludeMissing
        archivedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("auth") @ExcludeMissing auth: JsonField<Auth> = JsonMissing.of(),
        @JsonProperty("created_at")
        @ExcludeMissing
        createdAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("metadata") @ExcludeMissing metadata: JsonField<Metadata> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonField<Type> = JsonMissing.of(),
        @JsonProperty("updated_at")
        @ExcludeMissing
        updatedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("vault_id") @ExcludeMissing vaultId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("display_name")
        @ExcludeMissing
        displayName: JsonField<String> = JsonMissing.of(),
    ) : this(
        id,
        archivedAt,
        auth,
        createdAt,
        metadata,
        type,
        updatedAt,
        vaultId,
        displayName,
        mutableMapOf(),
    )

    /**
     * Unique identifier for the credential.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun id(): String = id.getRequired("id")

    /**
     * A timestamp in RFC 3339 format
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun archivedAt(): Optional<OffsetDateTime> = archivedAt.getOptional("archived_at")

    /**
     * Authentication details for a credential.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun auth(): Auth = auth.getRequired("auth")

    /**
     * A timestamp in RFC 3339 format
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun createdAt(): OffsetDateTime = createdAt.getRequired("created_at")

    /**
     * Arbitrary key-value metadata attached to the credential.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun metadata(): Metadata = metadata.getRequired("metadata")

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
    fun updatedAt(): OffsetDateTime = updatedAt.getRequired("updated_at")

    /**
     * Identifier of the vault this credential belongs to.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun vaultId(): String = vaultId.getRequired("vault_id")

    /**
     * Human-readable name for the credential.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun displayName(): Optional<String> = displayName.getOptional("display_name")

    /**
     * Returns the raw JSON value of [id].
     *
     * Unlike [id], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

    /**
     * Returns the raw JSON value of [archivedAt].
     *
     * Unlike [archivedAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("archived_at")
    @ExcludeMissing
    fun _archivedAt(): JsonField<OffsetDateTime> = archivedAt

    /**
     * Returns the raw JSON value of [auth].
     *
     * Unlike [auth], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("auth") @ExcludeMissing fun _auth(): JsonField<Auth> = auth

    /**
     * Returns the raw JSON value of [createdAt].
     *
     * Unlike [createdAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("created_at")
    @ExcludeMissing
    fun _createdAt(): JsonField<OffsetDateTime> = createdAt

    /**
     * Returns the raw JSON value of [metadata].
     *
     * Unlike [metadata], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("metadata") @ExcludeMissing fun _metadata(): JsonField<Metadata> = metadata

    /**
     * Returns the raw JSON value of [type].
     *
     * Unlike [type], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonField<Type> = type

    /**
     * Returns the raw JSON value of [updatedAt].
     *
     * Unlike [updatedAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("updated_at")
    @ExcludeMissing
    fun _updatedAt(): JsonField<OffsetDateTime> = updatedAt

    /**
     * Returns the raw JSON value of [vaultId].
     *
     * Unlike [vaultId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("vault_id") @ExcludeMissing fun _vaultId(): JsonField<String> = vaultId

    /**
     * Returns the raw JSON value of [displayName].
     *
     * Unlike [displayName], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("display_name")
    @ExcludeMissing
    fun _displayName(): JsonField<String> = displayName

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
         * Returns a mutable builder for constructing an instance of [BetaManagedAgentsCredential].
         *
         * The following fields are required:
         * ```java
         * .id()
         * .archivedAt()
         * .auth()
         * .createdAt()
         * .metadata()
         * .type()
         * .updatedAt()
         * .vaultId()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaManagedAgentsCredential]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var archivedAt: JsonField<OffsetDateTime>? = null
        private var auth: JsonField<Auth>? = null
        private var createdAt: JsonField<OffsetDateTime>? = null
        private var metadata: JsonField<Metadata>? = null
        private var type: JsonField<Type>? = null
        private var updatedAt: JsonField<OffsetDateTime>? = null
        private var vaultId: JsonField<String>? = null
        private var displayName: JsonField<String> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaManagedAgentsCredential: BetaManagedAgentsCredential) = apply {
            id = betaManagedAgentsCredential.id
            archivedAt = betaManagedAgentsCredential.archivedAt
            auth = betaManagedAgentsCredential.auth
            createdAt = betaManagedAgentsCredential.createdAt
            metadata = betaManagedAgentsCredential.metadata
            type = betaManagedAgentsCredential.type
            updatedAt = betaManagedAgentsCredential.updatedAt
            vaultId = betaManagedAgentsCredential.vaultId
            displayName = betaManagedAgentsCredential.displayName
            additionalProperties = betaManagedAgentsCredential.additionalProperties.toMutableMap()
        }

        /** Unique identifier for the credential. */
        fun id(id: String) = id(JsonField.of(id))

        /**
         * Sets [Builder.id] to an arbitrary JSON value.
         *
         * You should usually call [Builder.id] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun id(id: JsonField<String>) = apply { this.id = id }

        /** A timestamp in RFC 3339 format */
        fun archivedAt(archivedAt: OffsetDateTime?) = archivedAt(JsonField.ofNullable(archivedAt))

        /** Alias for calling [Builder.archivedAt] with `archivedAt.orElse(null)`. */
        fun archivedAt(archivedAt: Optional<OffsetDateTime>) = archivedAt(archivedAt.getOrNull())

        /**
         * Sets [Builder.archivedAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.archivedAt] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun archivedAt(archivedAt: JsonField<OffsetDateTime>) = apply {
            this.archivedAt = archivedAt
        }

        /** Authentication details for a credential. */
        fun auth(auth: Auth) = auth(JsonField.of(auth))

        /**
         * Sets [Builder.auth] to an arbitrary JSON value.
         *
         * You should usually call [Builder.auth] with a well-typed [Auth] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun auth(auth: JsonField<Auth>) = apply { this.auth = auth }

        /** Alias for calling [auth] with `Auth.ofMcpOAuth(mcpOAuth)`. */
        fun auth(mcpOAuth: BetaManagedAgentsMcpOAuthAuthResponse) = auth(Auth.ofMcpOAuth(mcpOAuth))

        /**
         * Alias for calling [auth] with the following:
         * ```java
         * BetaManagedAgentsMcpOAuthAuthResponse.builder()
         *     .type(BetaManagedAgentsMcpOAuthAuthResponse.Type.MCP_OAUTH)
         *     .mcpServerUrl(mcpServerUrl)
         *     .build()
         * ```
         */
        fun mcpOAuthAuth(mcpServerUrl: String) =
            auth(
                BetaManagedAgentsMcpOAuthAuthResponse.builder()
                    .type(BetaManagedAgentsMcpOAuthAuthResponse.Type.MCP_OAUTH)
                    .mcpServerUrl(mcpServerUrl)
                    .build()
            )

        /** Alias for calling [auth] with `Auth.ofStaticBearer(staticBearer)`. */
        fun auth(staticBearer: BetaManagedAgentsStaticBearerAuthResponse) =
            auth(Auth.ofStaticBearer(staticBearer))

        /**
         * Alias for calling [auth] with the following:
         * ```java
         * BetaManagedAgentsStaticBearerAuthResponse.builder()
         *     .type(BetaManagedAgentsStaticBearerAuthResponse.Type.STATIC_BEARER)
         *     .mcpServerUrl(mcpServerUrl)
         *     .build()
         * ```
         */
        fun staticBearerAuth(mcpServerUrl: String) =
            auth(
                BetaManagedAgentsStaticBearerAuthResponse.builder()
                    .type(BetaManagedAgentsStaticBearerAuthResponse.Type.STATIC_BEARER)
                    .mcpServerUrl(mcpServerUrl)
                    .build()
            )

        /** A timestamp in RFC 3339 format */
        fun createdAt(createdAt: OffsetDateTime) = createdAt(JsonField.of(createdAt))

        /**
         * Sets [Builder.createdAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.createdAt] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun createdAt(createdAt: JsonField<OffsetDateTime>) = apply { this.createdAt = createdAt }

        /** Arbitrary key-value metadata attached to the credential. */
        fun metadata(metadata: Metadata) = metadata(JsonField.of(metadata))

        /**
         * Sets [Builder.metadata] to an arbitrary JSON value.
         *
         * You should usually call [Builder.metadata] with a well-typed [Metadata] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun metadata(metadata: JsonField<Metadata>) = apply { this.metadata = metadata }

        fun type(type: Type) = type(JsonField.of(type))

        /**
         * Sets [Builder.type] to an arbitrary JSON value.
         *
         * You should usually call [Builder.type] with a well-typed [Type] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun type(type: JsonField<Type>) = apply { this.type = type }

        /** A timestamp in RFC 3339 format */
        fun updatedAt(updatedAt: OffsetDateTime) = updatedAt(JsonField.of(updatedAt))

        /**
         * Sets [Builder.updatedAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.updatedAt] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun updatedAt(updatedAt: JsonField<OffsetDateTime>) = apply { this.updatedAt = updatedAt }

        /** Identifier of the vault this credential belongs to. */
        fun vaultId(vaultId: String) = vaultId(JsonField.of(vaultId))

        /**
         * Sets [Builder.vaultId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.vaultId] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun vaultId(vaultId: JsonField<String>) = apply { this.vaultId = vaultId }

        /** Human-readable name for the credential. */
        fun displayName(displayName: String?) = displayName(JsonField.ofNullable(displayName))

        /** Alias for calling [Builder.displayName] with `displayName.orElse(null)`. */
        fun displayName(displayName: Optional<String>) = displayName(displayName.getOrNull())

        /**
         * Sets [Builder.displayName] to an arbitrary JSON value.
         *
         * You should usually call [Builder.displayName] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun displayName(displayName: JsonField<String>) = apply { this.displayName = displayName }

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
         * Returns an immutable instance of [BetaManagedAgentsCredential].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .id()
         * .archivedAt()
         * .auth()
         * .createdAt()
         * .metadata()
         * .type()
         * .updatedAt()
         * .vaultId()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaManagedAgentsCredential =
            BetaManagedAgentsCredential(
                checkRequired("id", id),
                checkRequired("archivedAt", archivedAt),
                checkRequired("auth", auth),
                checkRequired("createdAt", createdAt),
                checkRequired("metadata", metadata),
                checkRequired("type", type),
                checkRequired("updatedAt", updatedAt),
                checkRequired("vaultId", vaultId),
                displayName,
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
    fun validate(): BetaManagedAgentsCredential = apply {
        if (validated) {
            return@apply
        }

        id()
        archivedAt()
        auth().validate()
        createdAt()
        metadata().validate()
        type().validate()
        updatedAt()
        vaultId()
        displayName()
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
        (if (id.asKnown().isPresent) 1 else 0) +
            (if (archivedAt.asKnown().isPresent) 1 else 0) +
            (auth.asKnown().getOrNull()?.validity() ?: 0) +
            (if (createdAt.asKnown().isPresent) 1 else 0) +
            (metadata.asKnown().getOrNull()?.validity() ?: 0) +
            (type.asKnown().getOrNull()?.validity() ?: 0) +
            (if (updatedAt.asKnown().isPresent) 1 else 0) +
            (if (vaultId.asKnown().isPresent) 1 else 0) +
            (if (displayName.asKnown().isPresent) 1 else 0)

    /** Authentication details for a credential. */
    @JsonDeserialize(using = Auth.Deserializer::class)
    @JsonSerialize(using = Auth.Serializer::class)
    class Auth
    private constructor(
        private val mcpOAuth: BetaManagedAgentsMcpOAuthAuthResponse? = null,
        private val staticBearer: BetaManagedAgentsStaticBearerAuthResponse? = null,
        private val _json: JsonValue? = null,
    ) {

        /** OAuth credential details for an MCP server. */
        fun mcpOAuth(): Optional<BetaManagedAgentsMcpOAuthAuthResponse> =
            Optional.ofNullable(mcpOAuth)

        /** Static bearer token credential details for an MCP server. */
        fun staticBearer(): Optional<BetaManagedAgentsStaticBearerAuthResponse> =
            Optional.ofNullable(staticBearer)

        fun isMcpOAuth(): Boolean = mcpOAuth != null

        fun isStaticBearer(): Boolean = staticBearer != null

        /** OAuth credential details for an MCP server. */
        fun asMcpOAuth(): BetaManagedAgentsMcpOAuthAuthResponse = mcpOAuth.getOrThrow("mcpOAuth")

        /** Static bearer token credential details for an MCP server. */
        fun asStaticBearer(): BetaManagedAgentsStaticBearerAuthResponse =
            staticBearer.getOrThrow("staticBearer")

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
         * Optional<String> result = auth.accept(new Auth.Visitor<Optional<String>>() {
         *     @Override
         *     public Optional<String> visitMcpOAuth(BetaManagedAgentsMcpOAuthAuthResponse mcpOAuth) {
         *         return Optional.of(mcpOAuth.toString());
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
                mcpOAuth != null -> visitor.visitMcpOAuth(mcpOAuth)
                staticBearer != null -> visitor.visitStaticBearer(staticBearer)
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
        fun validate(): Auth = apply {
            if (validated) {
                return@apply
            }

            accept(
                object : Visitor<Unit> {
                    override fun visitMcpOAuth(mcpOAuth: BetaManagedAgentsMcpOAuthAuthResponse) {
                        mcpOAuth.validate()
                    }

                    override fun visitStaticBearer(
                        staticBearer: BetaManagedAgentsStaticBearerAuthResponse
                    ) {
                        staticBearer.validate()
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
                    override fun visitMcpOAuth(mcpOAuth: BetaManagedAgentsMcpOAuthAuthResponse) =
                        mcpOAuth.validity()

                    override fun visitStaticBearer(
                        staticBearer: BetaManagedAgentsStaticBearerAuthResponse
                    ) = staticBearer.validity()

                    override fun unknown(json: JsonValue?) = 0
                }
            )

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Auth && mcpOAuth == other.mcpOAuth && staticBearer == other.staticBearer
        }

        override fun hashCode(): Int = Objects.hash(mcpOAuth, staticBearer)

        override fun toString(): String =
            when {
                mcpOAuth != null -> "Auth{mcpOAuth=$mcpOAuth}"
                staticBearer != null -> "Auth{staticBearer=$staticBearer}"
                _json != null -> "Auth{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid Auth")
            }

        companion object {

            /** OAuth credential details for an MCP server. */
            @JvmStatic
            fun ofMcpOAuth(mcpOAuth: BetaManagedAgentsMcpOAuthAuthResponse) =
                Auth(mcpOAuth = mcpOAuth)

            /** Static bearer token credential details for an MCP server. */
            @JvmStatic
            fun ofStaticBearer(staticBearer: BetaManagedAgentsStaticBearerAuthResponse) =
                Auth(staticBearer = staticBearer)
        }

        /** An interface that defines how to map each variant of [Auth] to a value of type [T]. */
        interface Visitor<out T> {

            /** OAuth credential details for an MCP server. */
            fun visitMcpOAuth(mcpOAuth: BetaManagedAgentsMcpOAuthAuthResponse): T

            /** Static bearer token credential details for an MCP server. */
            fun visitStaticBearer(staticBearer: BetaManagedAgentsStaticBearerAuthResponse): T

            /**
             * Maps an unknown variant of [Auth] to a value of type [T].
             *
             * An instance of [Auth] can contain an unknown variant if it was deserialized from data
             * that doesn't match any known variant. For example, if the SDK is on an older version
             * than the API, then the API may respond with new variants that the SDK is unaware of.
             *
             * @throws AnthropicInvalidDataException in the default implementation.
             */
            fun unknown(json: JsonValue?): T {
                throw AnthropicInvalidDataException("Unknown Auth: $json")
            }
        }

        internal class Deserializer : BaseDeserializer<Auth>(Auth::class) {

            override fun ObjectCodec.deserialize(node: JsonNode): Auth {
                val json = JsonValue.fromJsonNode(node)
                val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

                when (type) {
                    "mcp_oauth" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaManagedAgentsMcpOAuthAuthResponse>(),
                            )
                            ?.let { Auth(mcpOAuth = it, _json = json) } ?: Auth(_json = json)
                    }
                    "static_bearer" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaManagedAgentsStaticBearerAuthResponse>(),
                            )
                            ?.let { Auth(staticBearer = it, _json = json) } ?: Auth(_json = json)
                    }
                }

                return Auth(_json = json)
            }
        }

        internal class Serializer : BaseSerializer<Auth>(Auth::class) {

            override fun serialize(
                value: Auth,
                generator: JsonGenerator,
                provider: SerializerProvider,
            ) {
                when {
                    value.mcpOAuth != null -> generator.writeObject(value.mcpOAuth)
                    value.staticBearer != null -> generator.writeObject(value.staticBearer)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid Auth")
                }
            }
        }
    }

    /** Arbitrary key-value metadata attached to the credential. */
    class Metadata
    @JsonCreator
    private constructor(
        @com.fasterxml.jackson.annotation.JsonValue
        private val additionalProperties: Map<String, JsonValue>
    ) {

        @JsonAnyGetter
        @ExcludeMissing
        fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

        fun toBuilder() = Builder().from(this)

        companion object {

            /** Returns a mutable builder for constructing an instance of [Metadata]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Metadata]. */
        class Builder internal constructor() {

            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(metadata: Metadata) = apply {
                additionalProperties = metadata.additionalProperties.toMutableMap()
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
             * Returns an immutable instance of [Metadata].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             */
            fun build(): Metadata = Metadata(additionalProperties.toImmutable())
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
        fun validate(): Metadata = apply {
            if (validated) {
                return@apply
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
         * Returns a score indicating how many valid values are contained in this object
         * recursively.
         *
         * Used for best match union deserialization.
         */
        @JvmSynthetic
        internal fun validity(): Int =
            additionalProperties.count { (_, value) -> !value.isNull() && !value.isMissing() }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Metadata && additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy { Objects.hash(additionalProperties) }

        override fun hashCode(): Int = hashCode

        override fun toString() = "Metadata{additionalProperties=$additionalProperties}"
    }

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

            @JvmField val VAULT_CREDENTIAL = of("vault_credential")

            @JvmStatic fun of(value: String) = Type(JsonField.of(value))
        }

        /** An enum containing [Type]'s known values. */
        enum class Known {
            VAULT_CREDENTIAL
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
            VAULT_CREDENTIAL,
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
                VAULT_CREDENTIAL -> Value.VAULT_CREDENTIAL
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
                VAULT_CREDENTIAL -> Known.VAULT_CREDENTIAL
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

        return other is BetaManagedAgentsCredential &&
            id == other.id &&
            archivedAt == other.archivedAt &&
            auth == other.auth &&
            createdAt == other.createdAt &&
            metadata == other.metadata &&
            type == other.type &&
            updatedAt == other.updatedAt &&
            vaultId == other.vaultId &&
            displayName == other.displayName &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            id,
            archivedAt,
            auth,
            createdAt,
            metadata,
            type,
            updatedAt,
            vaultId,
            displayName,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsCredential{id=$id, archivedAt=$archivedAt, auth=$auth, createdAt=$createdAt, metadata=$metadata, type=$type, updatedAt=$updatedAt, vaultId=$vaultId, displayName=$displayName, additionalProperties=$additionalProperties}"
}
