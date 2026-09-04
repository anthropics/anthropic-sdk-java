// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.federation.issuers

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.Enum
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkRequired
import com.anthropic.core.getOrThrow
import com.anthropic.core.getProperty
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

/**
 * Registered external OIDC identity provider.
 *
 * Records an external IdP the organization trusts for the RFC 7523 jwt-bearer grant. The
 * `issuer_url` must match the JWT `iss` claim exactly.
 */
class BetaFederationIssuer
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val id: JsonField<String>,
    private val archivedAt: JsonField<OffsetDateTime>,
    private val archivedByActorId: JsonField<String>,
    private val checkJti: JsonField<Boolean>,
    private val createdAt: JsonField<OffsetDateTime>,
    private val createdByActorId: JsonField<String>,
    private val issuerUrl: JsonField<String>,
    private val jwks: JsonField<Jwks>,
    private val jwksPollingDisabledAt: JsonField<OffsetDateTime>,
    private val maxJwtLifetimeSeconds: JsonField<Long>,
    private val name: JsonField<String>,
    private val pollStatus: JsonField<BetaFederationIssuerPollStatus>,
    private val type: JsonValue,
    private val updatedAt: JsonField<OffsetDateTime>,
    private val updatedByActorId: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("archived_at")
        @ExcludeMissing
        archivedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("archived_by_actor_id")
        @ExcludeMissing
        archivedByActorId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("check_jti") @ExcludeMissing checkJti: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("created_at")
        @ExcludeMissing
        createdAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("created_by_actor_id")
        @ExcludeMissing
        createdByActorId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("issuer_url") @ExcludeMissing issuerUrl: JsonField<String> = JsonMissing.of(),
        @JsonProperty("jwks") @ExcludeMissing jwks: JsonField<Jwks> = JsonMissing.of(),
        @JsonProperty("jwks_polling_disabled_at")
        @ExcludeMissing
        jwksPollingDisabledAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("max_jwt_lifetime_seconds")
        @ExcludeMissing
        maxJwtLifetimeSeconds: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("name") @ExcludeMissing name: JsonField<String> = JsonMissing.of(),
        @JsonProperty("poll_status")
        @ExcludeMissing
        pollStatus: JsonField<BetaFederationIssuerPollStatus> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("updated_at")
        @ExcludeMissing
        updatedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("updated_by_actor_id")
        @ExcludeMissing
        updatedByActorId: JsonField<String> = JsonMissing.of(),
    ) : this(
        id,
        archivedAt,
        archivedByActorId,
        checkJti,
        createdAt,
        createdByActorId,
        issuerUrl,
        jwks,
        jwksPollingDisabledAt,
        maxJwtLifetimeSeconds,
        name,
        pollStatus,
        type,
        updatedAt,
        updatedByActorId,
        mutableMapOf(),
    )

    /**
     * Tagged ID of the federation issuer.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun id(): String = id.getRequired("id")

    /**
     * If set, all rules referencing this issuer reject token exchange.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun archivedAt(): Optional<OffsetDateTime> = archivedAt.getOptional("archived_at")

    /**
     * Tagged ID (`user_`/`svac_`) of the actor that archived this issuer.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun archivedByActorId(): Optional<String> =
        archivedByActorId.getOptional("archived_by_actor_id")

    /**
     * Whether the jwt-bearer exchange enforces JTI single-use (replay protection) for tokens from
     * this issuer. Applies only to assertions carrying a `jti` claim; tokens without one are
     * accepted without single-use enforcement.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun checkJti(): Boolean = checkJti.getRequired("check_jti")

    /**
     * When this issuer was created.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun createdAt(): OffsetDateTime = createdAt.getRequired("created_at")

    /**
     * Tagged ID (`user_`/`svac_`) of the actor that created this issuer.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun createdByActorId(): Optional<String> = createdByActorId.getOptional("created_by_actor_id")

    /**
     * The `iss` claim value. Incoming JWTs must match exactly.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun issuerUrl(): String = issuerUrl.getRequired("issuer_url")

    /**
     * How signing keys are obtained for signature verification.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun jwks(): Jwks = jwks.getRequired("jwks")

    /**
     * If set, Anthropic's JWKS poller has paused polling for this issuer after repeated fetch
     * failures. Re-enable by sending `jwks_polling_disabled: false` via the issuer update endpoint
     * (POST) once the upstream JWKS endpoint is fixed. An OAuth caller cannot send this when the
     * issuer backs a rule with any scope other than `workspace:developer` or `workspace:inference`;
     * use a Console session.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun jwksPollingDisabledAt(): Optional<OffsetDateTime> =
        jwksPollingDisabledAt.getOptional("jwks_polling_disabled_at")

    /**
     * Maximum allowed iat→exp spread for assertions from this issuer (1-176400 seconds, i.e. up to
     * 49h). Assertions must carry both `iat` and `exp`; a missing `iat` is rejected.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun maxJwtLifetimeSeconds(): Long =
        maxJwtLifetimeSeconds.getRequired("max_jwt_lifetime_seconds")

    /**
     * Admin-chosen slug identifier.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun name(): String = name.getRequired("name")

    /**
     * Status of automatic JWKS polling for a federation issuer.
     *
     * Anthropic periodically fetches the issuer's signing keys in the background. These fields
     * summarize the most recent fetches so the health of the JWKS endpoint can be monitored.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun pollStatus(): Optional<BetaFederationIssuerPollStatus> =
        pollStatus.getOptional("poll_status")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("federation_issuer")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * When this issuer was last updated.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun updatedAt(): OffsetDateTime = updatedAt.getRequired("updated_at")

    /**
     * Tagged ID (`user_`/`svac_`) of the actor that last updated this issuer.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun updatedByActorId(): Optional<String> = updatedByActorId.getOptional("updated_by_actor_id")

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
     * Returns the raw JSON value of [archivedByActorId].
     *
     * Unlike [archivedByActorId], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("archived_by_actor_id")
    @ExcludeMissing
    fun _archivedByActorId(): JsonField<String> = archivedByActorId

    /**
     * Returns the raw JSON value of [checkJti].
     *
     * Unlike [checkJti], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("check_jti") @ExcludeMissing fun _checkJti(): JsonField<Boolean> = checkJti

    /**
     * Returns the raw JSON value of [createdAt].
     *
     * Unlike [createdAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("created_at")
    @ExcludeMissing
    fun _createdAt(): JsonField<OffsetDateTime> = createdAt

    /**
     * Returns the raw JSON value of [createdByActorId].
     *
     * Unlike [createdByActorId], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("created_by_actor_id")
    @ExcludeMissing
    fun _createdByActorId(): JsonField<String> = createdByActorId

    /**
     * Returns the raw JSON value of [issuerUrl].
     *
     * Unlike [issuerUrl], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("issuer_url") @ExcludeMissing fun _issuerUrl(): JsonField<String> = issuerUrl

    /**
     * Returns the raw JSON value of [jwks].
     *
     * Unlike [jwks], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("jwks") @ExcludeMissing fun _jwks(): JsonField<Jwks> = jwks

    /**
     * Returns the raw JSON value of [jwksPollingDisabledAt].
     *
     * Unlike [jwksPollingDisabledAt], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("jwks_polling_disabled_at")
    @ExcludeMissing
    fun _jwksPollingDisabledAt(): JsonField<OffsetDateTime> = jwksPollingDisabledAt

    /**
     * Returns the raw JSON value of [maxJwtLifetimeSeconds].
     *
     * Unlike [maxJwtLifetimeSeconds], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("max_jwt_lifetime_seconds")
    @ExcludeMissing
    fun _maxJwtLifetimeSeconds(): JsonField<Long> = maxJwtLifetimeSeconds

    /**
     * Returns the raw JSON value of [name].
     *
     * Unlike [name], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("name") @ExcludeMissing fun _name(): JsonField<String> = name

    /**
     * Returns the raw JSON value of [pollStatus].
     *
     * Unlike [pollStatus], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("poll_status")
    @ExcludeMissing
    fun _pollStatus(): JsonField<BetaFederationIssuerPollStatus> = pollStatus

    /**
     * Returns the raw JSON value of [updatedAt].
     *
     * Unlike [updatedAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("updated_at")
    @ExcludeMissing
    fun _updatedAt(): JsonField<OffsetDateTime> = updatedAt

    /**
     * Returns the raw JSON value of [updatedByActorId].
     *
     * Unlike [updatedByActorId], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("updated_by_actor_id")
    @ExcludeMissing
    fun _updatedByActorId(): JsonField<String> = updatedByActorId

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
         * Returns a mutable builder for constructing an instance of [BetaFederationIssuer].
         *
         * The following fields are required:
         * ```java
         * .id()
         * .archivedAt()
         * .archivedByActorId()
         * .checkJti()
         * .createdAt()
         * .createdByActorId()
         * .issuerUrl()
         * .jwks()
         * .jwksPollingDisabledAt()
         * .maxJwtLifetimeSeconds()
         * .name()
         * .pollStatus()
         * .updatedAt()
         * .updatedByActorId()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaFederationIssuer]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var archivedAt: JsonField<OffsetDateTime>? = null
        private var archivedByActorId: JsonField<String>? = null
        private var checkJti: JsonField<Boolean>? = null
        private var createdAt: JsonField<OffsetDateTime>? = null
        private var createdByActorId: JsonField<String>? = null
        private var issuerUrl: JsonField<String>? = null
        private var jwks: JsonField<Jwks>? = null
        private var jwksPollingDisabledAt: JsonField<OffsetDateTime>? = null
        private var maxJwtLifetimeSeconds: JsonField<Long>? = null
        private var name: JsonField<String>? = null
        private var pollStatus: JsonField<BetaFederationIssuerPollStatus>? = null
        private var type: JsonValue = JsonValue.from("federation_issuer")
        private var updatedAt: JsonField<OffsetDateTime>? = null
        private var updatedByActorId: JsonField<String>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaFederationIssuer: BetaFederationIssuer) = apply {
            id = betaFederationIssuer.id
            archivedAt = betaFederationIssuer.archivedAt
            archivedByActorId = betaFederationIssuer.archivedByActorId
            checkJti = betaFederationIssuer.checkJti
            createdAt = betaFederationIssuer.createdAt
            createdByActorId = betaFederationIssuer.createdByActorId
            issuerUrl = betaFederationIssuer.issuerUrl
            jwks = betaFederationIssuer.jwks
            jwksPollingDisabledAt = betaFederationIssuer.jwksPollingDisabledAt
            maxJwtLifetimeSeconds = betaFederationIssuer.maxJwtLifetimeSeconds
            name = betaFederationIssuer.name
            pollStatus = betaFederationIssuer.pollStatus
            type = betaFederationIssuer.type
            updatedAt = betaFederationIssuer.updatedAt
            updatedByActorId = betaFederationIssuer.updatedByActorId
            additionalProperties = betaFederationIssuer.additionalProperties.toMutableMap()
        }

        /** Tagged ID of the federation issuer. */
        fun id(id: String) = id(JsonField.of(id))

        /**
         * Sets [Builder.id] to an arbitrary JSON value.
         *
         * You should usually call [Builder.id] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun id(id: JsonField<String>) = apply { this.id = id }

        /** If set, all rules referencing this issuer reject token exchange. */
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

        /** Tagged ID (`user_`/`svac_`) of the actor that archived this issuer. */
        fun archivedByActorId(archivedByActorId: String?) =
            archivedByActorId(JsonField.ofNullable(archivedByActorId))

        /** Alias for calling [Builder.archivedByActorId] with `archivedByActorId.orElse(null)`. */
        fun archivedByActorId(archivedByActorId: Optional<String>) =
            archivedByActorId(archivedByActorId.getOrNull())

        /**
         * Sets [Builder.archivedByActorId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.archivedByActorId] with a well-typed [String] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun archivedByActorId(archivedByActorId: JsonField<String>) = apply {
            this.archivedByActorId = archivedByActorId
        }

        /**
         * Whether the jwt-bearer exchange enforces JTI single-use (replay protection) for tokens
         * from this issuer. Applies only to assertions carrying a `jti` claim; tokens without one
         * are accepted without single-use enforcement.
         */
        fun checkJti(checkJti: Boolean) = checkJti(JsonField.of(checkJti))

        /**
         * Sets [Builder.checkJti] to an arbitrary JSON value.
         *
         * You should usually call [Builder.checkJti] with a well-typed [Boolean] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun checkJti(checkJti: JsonField<Boolean>) = apply { this.checkJti = checkJti }

        /** When this issuer was created. */
        fun createdAt(createdAt: OffsetDateTime) = createdAt(JsonField.of(createdAt))

        /**
         * Sets [Builder.createdAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.createdAt] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun createdAt(createdAt: JsonField<OffsetDateTime>) = apply { this.createdAt = createdAt }

        /** Tagged ID (`user_`/`svac_`) of the actor that created this issuer. */
        fun createdByActorId(createdByActorId: String?) =
            createdByActorId(JsonField.ofNullable(createdByActorId))

        /** Alias for calling [Builder.createdByActorId] with `createdByActorId.orElse(null)`. */
        fun createdByActorId(createdByActorId: Optional<String>) =
            createdByActorId(createdByActorId.getOrNull())

        /**
         * Sets [Builder.createdByActorId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.createdByActorId] with a well-typed [String] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun createdByActorId(createdByActorId: JsonField<String>) = apply {
            this.createdByActorId = createdByActorId
        }

        /** The `iss` claim value. Incoming JWTs must match exactly. */
        fun issuerUrl(issuerUrl: String) = issuerUrl(JsonField.of(issuerUrl))

        /**
         * Sets [Builder.issuerUrl] to an arbitrary JSON value.
         *
         * You should usually call [Builder.issuerUrl] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun issuerUrl(issuerUrl: JsonField<String>) = apply { this.issuerUrl = issuerUrl }

        /** How signing keys are obtained for signature verification. */
        fun jwks(jwks: Jwks) = jwks(JsonField.of(jwks))

        /**
         * Sets [Builder.jwks] to an arbitrary JSON value.
         *
         * You should usually call [Builder.jwks] with a well-typed [Jwks] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun jwks(jwks: JsonField<Jwks>) = apply { this.jwks = jwks }

        /** Alias for calling [jwks] with `Jwks.ofDiscovery(discovery)`. */
        fun jwks(discovery: BetaJwksDiscovery) = jwks(Jwks.ofDiscovery(discovery))

        /** Alias for calling [jwks] with `Jwks.ofExplicitUrl(explicitUrl)`. */
        fun jwks(explicitUrl: BetaJwksExplicitUrl) = jwks(Jwks.ofExplicitUrl(explicitUrl))

        /**
         * Alias for calling [jwks] with the following:
         * ```java
         * BetaJwksExplicitUrl.builder()
         *     .url(url)
         *     .build()
         * ```
         */
        fun explicitUrlJwks(url: String) = jwks(BetaJwksExplicitUrl.builder().url(url).build())

        /** Alias for calling [jwks] with `Jwks.ofInline(inline)`. */
        fun jwks(inline: BetaJwksInline) = jwks(Jwks.ofInline(inline))

        /**
         * Alias for calling [jwks] with the following:
         * ```java
         * BetaJwksInline.builder()
         *     .keys(keys)
         *     .build()
         * ```
         */
        fun inlineJwks(keys: List<BetaJwksInline.Key>) =
            jwks(BetaJwksInline.builder().keys(keys).build())

        /**
         * If set, Anthropic's JWKS poller has paused polling for this issuer after repeated fetch
         * failures. Re-enable by sending `jwks_polling_disabled: false` via the issuer update
         * endpoint (POST) once the upstream JWKS endpoint is fixed. An OAuth caller cannot send
         * this when the issuer backs a rule with any scope other than `workspace:developer` or
         * `workspace:inference`; use a Console session.
         */
        fun jwksPollingDisabledAt(jwksPollingDisabledAt: OffsetDateTime?) =
            jwksPollingDisabledAt(JsonField.ofNullable(jwksPollingDisabledAt))

        /**
         * Alias for calling [Builder.jwksPollingDisabledAt] with
         * `jwksPollingDisabledAt.orElse(null)`.
         */
        fun jwksPollingDisabledAt(jwksPollingDisabledAt: Optional<OffsetDateTime>) =
            jwksPollingDisabledAt(jwksPollingDisabledAt.getOrNull())

        /**
         * Sets [Builder.jwksPollingDisabledAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.jwksPollingDisabledAt] with a well-typed
         * [OffsetDateTime] value instead. This method is primarily for setting the field to an
         * undocumented or not yet supported value.
         */
        fun jwksPollingDisabledAt(jwksPollingDisabledAt: JsonField<OffsetDateTime>) = apply {
            this.jwksPollingDisabledAt = jwksPollingDisabledAt
        }

        /**
         * Maximum allowed iat→exp spread for assertions from this issuer (1-176400 seconds, i.e. up
         * to 49h). Assertions must carry both `iat` and `exp`; a missing `iat` is rejected.
         */
        fun maxJwtLifetimeSeconds(maxJwtLifetimeSeconds: Long) =
            maxJwtLifetimeSeconds(JsonField.of(maxJwtLifetimeSeconds))

        /**
         * Sets [Builder.maxJwtLifetimeSeconds] to an arbitrary JSON value.
         *
         * You should usually call [Builder.maxJwtLifetimeSeconds] with a well-typed [Long] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun maxJwtLifetimeSeconds(maxJwtLifetimeSeconds: JsonField<Long>) = apply {
            this.maxJwtLifetimeSeconds = maxJwtLifetimeSeconds
        }

        /** Admin-chosen slug identifier. */
        fun name(name: String) = name(JsonField.of(name))

        /**
         * Sets [Builder.name] to an arbitrary JSON value.
         *
         * You should usually call [Builder.name] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun name(name: JsonField<String>) = apply { this.name = name }

        /**
         * Status of automatic JWKS polling for a federation issuer.
         *
         * Anthropic periodically fetches the issuer's signing keys in the background. These fields
         * summarize the most recent fetches so the health of the JWKS endpoint can be monitored.
         */
        fun pollStatus(pollStatus: BetaFederationIssuerPollStatus?) =
            pollStatus(JsonField.ofNullable(pollStatus))

        /** Alias for calling [Builder.pollStatus] with `pollStatus.orElse(null)`. */
        fun pollStatus(pollStatus: Optional<BetaFederationIssuerPollStatus>) =
            pollStatus(pollStatus.getOrNull())

        /**
         * Sets [Builder.pollStatus] to an arbitrary JSON value.
         *
         * You should usually call [Builder.pollStatus] with a well-typed
         * [BetaFederationIssuerPollStatus] value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun pollStatus(pollStatus: JsonField<BetaFederationIssuerPollStatus>) = apply {
            this.pollStatus = pollStatus
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("federation_issuer")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        /** When this issuer was last updated. */
        fun updatedAt(updatedAt: OffsetDateTime) = updatedAt(JsonField.of(updatedAt))

        /**
         * Sets [Builder.updatedAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.updatedAt] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun updatedAt(updatedAt: JsonField<OffsetDateTime>) = apply { this.updatedAt = updatedAt }

        /** Tagged ID (`user_`/`svac_`) of the actor that last updated this issuer. */
        fun updatedByActorId(updatedByActorId: String?) =
            updatedByActorId(JsonField.ofNullable(updatedByActorId))

        /** Alias for calling [Builder.updatedByActorId] with `updatedByActorId.orElse(null)`. */
        fun updatedByActorId(updatedByActorId: Optional<String>) =
            updatedByActorId(updatedByActorId.getOrNull())

        /**
         * Sets [Builder.updatedByActorId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.updatedByActorId] with a well-typed [String] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun updatedByActorId(updatedByActorId: JsonField<String>) = apply {
            this.updatedByActorId = updatedByActorId
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
         * Returns an immutable instance of [BetaFederationIssuer].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .id()
         * .archivedAt()
         * .archivedByActorId()
         * .checkJti()
         * .createdAt()
         * .createdByActorId()
         * .issuerUrl()
         * .jwks()
         * .jwksPollingDisabledAt()
         * .maxJwtLifetimeSeconds()
         * .name()
         * .pollStatus()
         * .updatedAt()
         * .updatedByActorId()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaFederationIssuer =
            BetaFederationIssuer(
                checkRequired("id", id),
                checkRequired("archivedAt", archivedAt),
                checkRequired("archivedByActorId", archivedByActorId),
                checkRequired("checkJti", checkJti),
                checkRequired("createdAt", createdAt),
                checkRequired("createdByActorId", createdByActorId),
                checkRequired("issuerUrl", issuerUrl),
                checkRequired("jwks", jwks),
                checkRequired("jwksPollingDisabledAt", jwksPollingDisabledAt),
                checkRequired("maxJwtLifetimeSeconds", maxJwtLifetimeSeconds),
                checkRequired("name", name),
                checkRequired("pollStatus", pollStatus),
                type,
                checkRequired("updatedAt", updatedAt),
                checkRequired("updatedByActorId", updatedByActorId),
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
    fun validate(): BetaFederationIssuer = apply {
        if (validated) {
            return@apply
        }

        id()
        archivedAt()
        archivedByActorId()
        checkJti()
        createdAt()
        createdByActorId()
        issuerUrl()
        jwks().validate()
        jwksPollingDisabledAt()
        maxJwtLifetimeSeconds()
        name()
        pollStatus().ifPresent { it.validate() }
        _type().let {
            if (it != JsonValue.from("federation_issuer")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        updatedAt()
        updatedByActorId()
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
            (if (archivedByActorId.asKnown().isPresent) 1 else 0) +
            (if (checkJti.asKnown().isPresent) 1 else 0) +
            (if (createdAt.asKnown().isPresent) 1 else 0) +
            (if (createdByActorId.asKnown().isPresent) 1 else 0) +
            (if (issuerUrl.asKnown().isPresent) 1 else 0) +
            (jwks.asKnown().getOrNull()?.validity() ?: 0) +
            (if (jwksPollingDisabledAt.asKnown().isPresent) 1 else 0) +
            (if (maxJwtLifetimeSeconds.asKnown().isPresent) 1 else 0) +
            (if (name.asKnown().isPresent) 1 else 0) +
            (pollStatus.asKnown().getOrNull()?.validity() ?: 0) +
            type.let { if (it == JsonValue.from("federation_issuer")) 1 else 0 } +
            (if (updatedAt.asKnown().isPresent) 1 else 0) +
            (if (updatedByActorId.asKnown().isPresent) 1 else 0)

    /** How signing keys are obtained for signature verification. */
    @JsonDeserialize(using = Jwks.Deserializer::class)
    @JsonSerialize(using = Jwks.Serializer::class)
    class Jwks
    private constructor(
        private val discovery: BetaJwksDiscovery? = null,
        private val explicitUrl: BetaJwksExplicitUrl? = null,
        private val inline: BetaJwksInline? = null,
        private val _json: JsonValue? = null,
    ) {

        fun type(): Type =
            accept(
                object : Visitor<Type> {
                    override fun visitDiscovery(discovery: BetaJwksDiscovery): Type = Type.DISCOVERY

                    override fun visitExplicitUrl(explicitUrl: BetaJwksExplicitUrl): Type =
                        Type.EXPLICIT_URL

                    override fun visitInline(inline: BetaJwksInline): Type = Type.INLINE

                    override fun unknown(json: JsonValue?): Type =
                        Type.of(json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
                }
            )

        fun caCertPem(): Optional<String> =
            accept(
                object : Visitor<Optional<String>> {
                    override fun visitDiscovery(discovery: BetaJwksDiscovery): Optional<String> =
                        discovery.caCertPem()

                    override fun visitExplicitUrl(
                        explicitUrl: BetaJwksExplicitUrl
                    ): Optional<String> = explicitUrl.caCertPem()

                    override fun visitInline(inline: BetaJwksInline): Optional<String> =
                        Optional.empty()

                    override fun unknown(json: JsonValue?): Optional<String> =
                        json.getProperty<String>("ca_cert_pem").asKnown()
                }
            )

        /** JWKS via the issuer's OIDC discovery document. */
        fun discovery(): Optional<BetaJwksDiscovery> = Optional.ofNullable(discovery)

        /** JWKS fetched from a fixed endpoint. */
        fun explicitUrl(): Optional<BetaJwksExplicitUrl> = Optional.ofNullable(explicitUrl)

        /** JWKS supplied directly; no network fetch. */
        fun inline(): Optional<BetaJwksInline> = Optional.ofNullable(inline)

        fun isDiscovery(): Boolean = discovery != null

        fun isExplicitUrl(): Boolean = explicitUrl != null

        fun isInline(): Boolean = inline != null

        /** JWKS via the issuer's OIDC discovery document. */
        fun asDiscovery(): BetaJwksDiscovery = discovery.getOrThrow("discovery")

        /** JWKS fetched from a fixed endpoint. */
        fun asExplicitUrl(): BetaJwksExplicitUrl = explicitUrl.getOrThrow("explicitUrl")

        /** JWKS supplied directly; no network fetch. */
        fun asInline(): BetaJwksInline = inline.getOrThrow("inline")

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
         * Optional<String> result = jwks.accept(new Jwks.Visitor<Optional<String>>() {
         *     @Override
         *     public Optional<String> visitDiscovery(BetaJwksDiscovery discovery) {
         *         return Optional.of(discovery.toString());
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
                discovery != null -> visitor.visitDiscovery(discovery)
                explicitUrl != null -> visitor.visitExplicitUrl(explicitUrl)
                inline != null -> visitor.visitInline(inline)
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
        fun validate(): Jwks = apply {
            if (validated) {
                return@apply
            }

            accept(
                object : Visitor<Unit> {
                    override fun visitDiscovery(discovery: BetaJwksDiscovery) {
                        discovery.validate()
                    }

                    override fun visitExplicitUrl(explicitUrl: BetaJwksExplicitUrl) {
                        explicitUrl.validate()
                    }

                    override fun visitInline(inline: BetaJwksInline) {
                        inline.validate()
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
                    override fun visitDiscovery(discovery: BetaJwksDiscovery) = discovery.validity()

                    override fun visitExplicitUrl(explicitUrl: BetaJwksExplicitUrl) =
                        explicitUrl.validity()

                    override fun visitInline(inline: BetaJwksInline) = inline.validity()

                    override fun unknown(json: JsonValue?) = 0
                }
            )

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Jwks &&
                discovery == other.discovery &&
                explicitUrl == other.explicitUrl &&
                inline == other.inline
        }

        override fun hashCode(): Int = Objects.hash(discovery, explicitUrl, inline)

        override fun toString(): String =
            when {
                discovery != null -> "Jwks{discovery=$discovery}"
                explicitUrl != null -> "Jwks{explicitUrl=$explicitUrl}"
                inline != null -> "Jwks{inline=$inline}"
                _json != null -> "Jwks{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid Jwks")
            }

        companion object {

            /** JWKS via the issuer's OIDC discovery document. */
            @JvmStatic fun ofDiscovery(discovery: BetaJwksDiscovery) = Jwks(discovery = discovery)

            /** JWKS fetched from a fixed endpoint. */
            @JvmStatic
            fun ofExplicitUrl(explicitUrl: BetaJwksExplicitUrl) = Jwks(explicitUrl = explicitUrl)

            /**
             * Returns an immutable instance of [Jwks] whose [ofExplicitUrl] variant is built from
             * the given required [url].
             */
            @JvmStatic fun ofExplicitUrl(url: String) = ofExplicitUrl(BetaJwksExplicitUrl.of(url))

            /** JWKS supplied directly; no network fetch. */
            @JvmStatic fun ofInline(inline: BetaJwksInline) = Jwks(inline = inline)

            /**
             * Returns an immutable instance of [Jwks] whose [ofInline] variant is built from the
             * given required [keys].
             */
            @JvmStatic
            fun ofInline(keys: List<BetaJwksInline.Key>) = ofInline(BetaJwksInline.of(keys))
        }

        /** An interface that defines how to map each variant of [Jwks] to a value of type [T]. */
        interface Visitor<out T> {

            /** JWKS via the issuer's OIDC discovery document. */
            fun visitDiscovery(discovery: BetaJwksDiscovery): T

            /** JWKS fetched from a fixed endpoint. */
            fun visitExplicitUrl(explicitUrl: BetaJwksExplicitUrl): T

            /** JWKS supplied directly; no network fetch. */
            fun visitInline(inline: BetaJwksInline): T

            /**
             * Maps an unknown variant of [Jwks] to a value of type [T].
             *
             * An instance of [Jwks] can contain an unknown variant if it was deserialized from data
             * that doesn't match any known variant. For example, if the SDK is on an older version
             * than the API, then the API may respond with new variants that the SDK is unaware of.
             *
             * @throws AnthropicInvalidDataException in the default implementation.
             */
            fun unknown(json: JsonValue?): T {
                throw AnthropicInvalidDataException("Unknown Jwks: $json")
            }
        }

        internal class Deserializer : BaseDeserializer<Jwks>(Jwks::class) {

            override fun ObjectCodec.deserialize(node: JsonNode): Jwks {
                val json = JsonValue.fromJsonNode(node)
                val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

                when (type) {
                    "discovery" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaJwksDiscovery>())?.let {
                            Jwks(discovery = it, _json = json)
                        } ?: Jwks(_json = json)
                    }
                    "explicit_url" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaJwksExplicitUrl>())?.let {
                            Jwks(explicitUrl = it, _json = json)
                        } ?: Jwks(_json = json)
                    }
                    "inline" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaJwksInline>())?.let {
                            Jwks(inline = it, _json = json)
                        } ?: Jwks(_json = json)
                    }
                }

                return Jwks(_json = json)
            }
        }

        internal class Serializer : BaseSerializer<Jwks>(Jwks::class) {

            override fun serialize(
                value: Jwks,
                generator: JsonGenerator,
                provider: SerializerProvider,
            ) {
                when {
                    value.discovery != null -> generator.writeObject(value.discovery)
                    value.explicitUrl != null -> generator.writeObject(value.explicitUrl)
                    value.inline != null -> generator.writeObject(value.inline)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid Jwks")
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

                @JvmField val DISCOVERY = of("discovery")

                @JvmField val EXPLICIT_URL = of("explicit_url")

                @JvmField val INLINE = of("inline")

                @JvmStatic fun of(value: String) = Type(JsonField.of(value))

                @JvmSynthetic
                internal fun of(value: JsonField<String>): Type =
                    value.asString().getOrNull()?.let { of(it) } ?: Type(value)
            }

            /** An enum containing [Type]'s known values. */
            enum class Known {
                DISCOVERY,
                EXPLICIT_URL,
                INLINE,
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
                DISCOVERY,
                EXPLICIT_URL,
                INLINE,
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
                    DISCOVERY -> Value.DISCOVERY
                    EXPLICIT_URL -> Value.EXPLICIT_URL
                    INLINE -> Value.INLINE
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
                    DISCOVERY -> Known.DISCOVERY
                    EXPLICIT_URL -> Known.EXPLICIT_URL
                    INLINE -> Known.INLINE
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

        return other is BetaFederationIssuer &&
            id == other.id &&
            archivedAt == other.archivedAt &&
            archivedByActorId == other.archivedByActorId &&
            checkJti == other.checkJti &&
            createdAt == other.createdAt &&
            createdByActorId == other.createdByActorId &&
            issuerUrl == other.issuerUrl &&
            jwks == other.jwks &&
            jwksPollingDisabledAt == other.jwksPollingDisabledAt &&
            maxJwtLifetimeSeconds == other.maxJwtLifetimeSeconds &&
            name == other.name &&
            pollStatus == other.pollStatus &&
            type == other.type &&
            updatedAt == other.updatedAt &&
            updatedByActorId == other.updatedByActorId &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            id,
            archivedAt,
            archivedByActorId,
            checkJti,
            createdAt,
            createdByActorId,
            issuerUrl,
            jwks,
            jwksPollingDisabledAt,
            maxJwtLifetimeSeconds,
            name,
            pollStatus,
            type,
            updatedAt,
            updatedByActorId,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaFederationIssuer{id=$id, archivedAt=$archivedAt, archivedByActorId=$archivedByActorId, checkJti=$checkJti, createdAt=$createdAt, createdByActorId=$createdByActorId, issuerUrl=$issuerUrl, jwks=$jwks, jwksPollingDisabledAt=$jwksPollingDisabledAt, maxJwtLifetimeSeconds=$maxJwtLifetimeSeconds, name=$name, pollStatus=$pollStatus, type=$type, updatedAt=$updatedAt, updatedByActorId=$updatedByActorId, additionalProperties=$additionalProperties}"
}
