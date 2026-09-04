// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.organization.federation.issuers

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.Enum
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.Params
import com.anthropic.core.getOrThrow
import com.anthropic.core.getProperty
import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.beta.AnthropicBeta
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

/**
 * **Requires an OAuth access token with the `org:admin` scope**, from `ant auth login --scope
 * org:admin` or a workload identity federation rule; Admin API keys are not accepted. See
 * [Manage WIF with the Admin API](/docs/en/manage-claude/wif-admin-api).
 *
 * Partially update a federation issuer.
 *
 * Setting `jwks` replaces the full JWKS shape at once. Archived issuers cannot be updated; this
 * returns 400. Create a new issuer instead.
 *
 * Updating an issuer that backs a rule with a scope outside `workspace:developer` or
 * `workspace:inference` requires a Console session.
 */
class IssuerUpdateParams
private constructor(
    private val federationIssuerId: String?,
    private val betas: List<AnthropicBeta>?,
    private val body: Body,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    /** ID of the federation issuer to update. */
    fun federationIssuerId(): Optional<String> = Optional.ofNullable(federationIssuerId)

    /** Optional header to specify the beta version(s) you want to use. */
    fun betas(): Optional<List<AnthropicBeta>> = Optional.ofNullable(betas)

    /**
     * Whether the jwt-bearer exchange enforces JTI single-use (replay protection) for tokens from
     * this issuer. Applies only to assertions carrying a `jti` claim; tokens without one are
     * accepted without single-use enforcement.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun checkJti(): Optional<Boolean> = body.checkJti()

    /**
     * Replaces the `iss` claim value to match against. For discovery-mode issuers without a
     * `discovery_base`, this is also the URL Anthropic fetches the OIDC discovery document and
     * signing keys from, so changing it repoints the JWKS source. Changing the issuer URL to a
     * well-known shared platform is rejected while any live rule under this issuer would not
     * constrain tenant identity.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun issuerUrl(): Optional<String> = body.issuerUrl()

    /**
     * Replaces the entire JWKS configuration.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun jwks(): Optional<Jwks> = body.jwks()

    /**
     * Only `false` is accepted, to re-enable polling after the system pauses it. Polling is paused
     * automatically; sending `true` is rejected.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun jwksPollingDisabled(): Optional<Boolean> = body.jwksPollingDisabled()

    /**
     * Maximum allowed iat→exp spread for assertions from this issuer (1-176400 seconds, i.e. up to
     * 49h). Assertions must carry both `iat` and `exp`; a missing `iat` is rejected.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun maxJwtLifetimeSeconds(): Optional<Long> = body.maxJwtLifetimeSeconds()

    /**
     * Replaces the slug identifier (lowercase, digits, hyphens). Unique within the organization; a
     * duplicate name returns 409.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun name(): Optional<String> = body.name()

    /**
     * Returns the raw JSON value of [checkJti].
     *
     * Unlike [checkJti], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _checkJti(): JsonField<Boolean> = body._checkJti()

    /**
     * Returns the raw JSON value of [issuerUrl].
     *
     * Unlike [issuerUrl], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _issuerUrl(): JsonField<String> = body._issuerUrl()

    /**
     * Returns the raw JSON value of [jwks].
     *
     * Unlike [jwks], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _jwks(): JsonField<Jwks> = body._jwks()

    /**
     * Returns the raw JSON value of [jwksPollingDisabled].
     *
     * Unlike [jwksPollingDisabled], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    fun _jwksPollingDisabled(): JsonField<Boolean> = body._jwksPollingDisabled()

    /**
     * Returns the raw JSON value of [maxJwtLifetimeSeconds].
     *
     * Unlike [maxJwtLifetimeSeconds], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    fun _maxJwtLifetimeSeconds(): JsonField<Long> = body._maxJwtLifetimeSeconds()

    /**
     * Returns the raw JSON value of [name].
     *
     * Unlike [name], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _name(): JsonField<String> = body._name()

    fun _additionalBodyProperties(): Map<String, JsonValue> = body._additionalProperties()

    /** Additional headers to send with the request. */
    fun _additionalHeaders(): Headers = additionalHeaders

    /** Additional query param to send with the request. */
    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun none(): IssuerUpdateParams = builder().build()

        /** Returns a mutable builder for constructing an instance of [IssuerUpdateParams]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [IssuerUpdateParams]. */
    class Builder internal constructor() {

        private var federationIssuerId: String? = null
        private var betas: MutableList<AnthropicBeta>? = null
        private var body: Body.Builder = Body.builder()
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(issuerUpdateParams: IssuerUpdateParams) = apply {
            federationIssuerId = issuerUpdateParams.federationIssuerId
            betas = issuerUpdateParams.betas?.toMutableList()
            body = issuerUpdateParams.body.toBuilder()
            additionalHeaders = issuerUpdateParams.additionalHeaders.toBuilder()
            additionalQueryParams = issuerUpdateParams.additionalQueryParams.toBuilder()
        }

        /** ID of the federation issuer to update. */
        fun federationIssuerId(federationIssuerId: String?) = apply {
            this.federationIssuerId = federationIssuerId
        }

        /**
         * Alias for calling [Builder.federationIssuerId] with `federationIssuerId.orElse(null)`.
         */
        fun federationIssuerId(federationIssuerId: Optional<String>) =
            federationIssuerId(federationIssuerId.getOrNull())

        /** Optional header to specify the beta version(s) you want to use. */
        fun betas(betas: List<AnthropicBeta>?) = apply { this.betas = betas?.toMutableList() }

        /** Alias for calling [Builder.betas] with `betas.orElse(null)`. */
        fun betas(betas: Optional<List<AnthropicBeta>>) = betas(betas.getOrNull())

        /**
         * Adds a single [AnthropicBeta] to [betas].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addBeta(beta: AnthropicBeta) = apply {
            betas = (betas ?: mutableListOf()).apply { add(beta) }
        }

        /**
         * Sets [addBeta] to an arbitrary [String].
         *
         * You should usually call [addBeta] with a well-typed [AnthropicBeta] constant instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun addBeta(value: String) = addBeta(AnthropicBeta.of(value))

        /**
         * Sets the entire request body.
         *
         * This is generally only useful if you are already constructing the body separately.
         * Otherwise, it's more convenient to use the top-level setters instead:
         * - [checkJti]
         * - [issuerUrl]
         * - [jwks]
         * - [jwksPollingDisabled]
         * - [maxJwtLifetimeSeconds]
         * - etc.
         */
        fun body(body: Body) = apply { this.body = body.toBuilder() }

        /**
         * Whether the jwt-bearer exchange enforces JTI single-use (replay protection) for tokens
         * from this issuer. Applies only to assertions carrying a `jti` claim; tokens without one
         * are accepted without single-use enforcement.
         */
        fun checkJti(checkJti: Boolean?) = apply { body.checkJti(checkJti) }

        /**
         * Alias for [Builder.checkJti].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun checkJti(checkJti: Boolean) = checkJti(checkJti as Boolean?)

        /** Alias for calling [Builder.checkJti] with `checkJti.orElse(null)`. */
        fun checkJti(checkJti: Optional<Boolean>) = checkJti(checkJti.getOrNull())

        /**
         * Sets [Builder.checkJti] to an arbitrary JSON value.
         *
         * You should usually call [Builder.checkJti] with a well-typed [Boolean] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun checkJti(checkJti: JsonField<Boolean>) = apply { body.checkJti(checkJti) }

        /**
         * Replaces the `iss` claim value to match against. For discovery-mode issuers without a
         * `discovery_base`, this is also the URL Anthropic fetches the OIDC discovery document and
         * signing keys from, so changing it repoints the JWKS source. Changing the issuer URL to a
         * well-known shared platform is rejected while any live rule under this issuer would not
         * constrain tenant identity.
         */
        fun issuerUrl(issuerUrl: String?) = apply { body.issuerUrl(issuerUrl) }

        /** Alias for calling [Builder.issuerUrl] with `issuerUrl.orElse(null)`. */
        fun issuerUrl(issuerUrl: Optional<String>) = issuerUrl(issuerUrl.getOrNull())

        /**
         * Sets [Builder.issuerUrl] to an arbitrary JSON value.
         *
         * You should usually call [Builder.issuerUrl] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun issuerUrl(issuerUrl: JsonField<String>) = apply { body.issuerUrl(issuerUrl) }

        /** Replaces the entire JWKS configuration. */
        fun jwks(jwks: Jwks?) = apply { body.jwks(jwks) }

        /** Alias for calling [Builder.jwks] with `jwks.orElse(null)`. */
        fun jwks(jwks: Optional<Jwks>) = jwks(jwks.getOrNull())

        /**
         * Sets [Builder.jwks] to an arbitrary JSON value.
         *
         * You should usually call [Builder.jwks] with a well-typed [Jwks] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun jwks(jwks: JsonField<Jwks>) = apply { body.jwks(jwks) }

        /** Alias for calling [jwks] with `Jwks.ofDiscovery(discovery)`. */
        fun jwks(discovery: BetaJwksDiscovery) = apply { body.jwks(discovery) }

        /** Alias for calling [jwks] with `Jwks.ofExplicitUrl(explicitUrl)`. */
        fun jwks(explicitUrl: BetaJwksExplicitUrl) = apply { body.jwks(explicitUrl) }

        /**
         * Alias for calling [jwks] with the following:
         * ```java
         * BetaJwksExplicitUrl.builder()
         *     .url(url)
         *     .build()
         * ```
         */
        fun explicitUrlJwks(url: String) = apply { body.explicitUrlJwks(url) }

        /** Alias for calling [jwks] with `Jwks.ofInline(inline)`. */
        fun jwks(inline: BetaJwksInline) = apply { body.jwks(inline) }

        /**
         * Alias for calling [jwks] with the following:
         * ```java
         * BetaJwksInline.builder()
         *     .keys(keys)
         *     .build()
         * ```
         */
        fun inlineJwks(keys: List<BetaJwksInline.Key>) = apply { body.inlineJwks(keys) }

        /**
         * Only `false` is accepted, to re-enable polling after the system pauses it. Polling is
         * paused automatically; sending `true` is rejected.
         */
        fun jwksPollingDisabled(jwksPollingDisabled: Boolean?) = apply {
            body.jwksPollingDisabled(jwksPollingDisabled)
        }

        /**
         * Alias for [Builder.jwksPollingDisabled].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun jwksPollingDisabled(jwksPollingDisabled: Boolean) =
            jwksPollingDisabled(jwksPollingDisabled as Boolean?)

        /**
         * Alias for calling [Builder.jwksPollingDisabled] with `jwksPollingDisabled.orElse(null)`.
         */
        fun jwksPollingDisabled(jwksPollingDisabled: Optional<Boolean>) =
            jwksPollingDisabled(jwksPollingDisabled.getOrNull())

        /**
         * Sets [Builder.jwksPollingDisabled] to an arbitrary JSON value.
         *
         * You should usually call [Builder.jwksPollingDisabled] with a well-typed [Boolean] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun jwksPollingDisabled(jwksPollingDisabled: JsonField<Boolean>) = apply {
            body.jwksPollingDisabled(jwksPollingDisabled)
        }

        /**
         * Maximum allowed iat→exp spread for assertions from this issuer (1-176400 seconds, i.e. up
         * to 49h). Assertions must carry both `iat` and `exp`; a missing `iat` is rejected.
         */
        fun maxJwtLifetimeSeconds(maxJwtLifetimeSeconds: Long?) = apply {
            body.maxJwtLifetimeSeconds(maxJwtLifetimeSeconds)
        }

        /**
         * Alias for [Builder.maxJwtLifetimeSeconds].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun maxJwtLifetimeSeconds(maxJwtLifetimeSeconds: Long) =
            maxJwtLifetimeSeconds(maxJwtLifetimeSeconds as Long?)

        /**
         * Alias for calling [Builder.maxJwtLifetimeSeconds] with
         * `maxJwtLifetimeSeconds.orElse(null)`.
         */
        fun maxJwtLifetimeSeconds(maxJwtLifetimeSeconds: Optional<Long>) =
            maxJwtLifetimeSeconds(maxJwtLifetimeSeconds.getOrNull())

        /**
         * Sets [Builder.maxJwtLifetimeSeconds] to an arbitrary JSON value.
         *
         * You should usually call [Builder.maxJwtLifetimeSeconds] with a well-typed [Long] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun maxJwtLifetimeSeconds(maxJwtLifetimeSeconds: JsonField<Long>) = apply {
            body.maxJwtLifetimeSeconds(maxJwtLifetimeSeconds)
        }

        /**
         * Replaces the slug identifier (lowercase, digits, hyphens). Unique within the
         * organization; a duplicate name returns 409.
         */
        fun name(name: String?) = apply { body.name(name) }

        /** Alias for calling [Builder.name] with `name.orElse(null)`. */
        fun name(name: Optional<String>) = name(name.getOrNull())

        /**
         * Sets [Builder.name] to an arbitrary JSON value.
         *
         * You should usually call [Builder.name] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun name(name: JsonField<String>) = apply { body.name(name) }

        fun additionalBodyProperties(additionalBodyProperties: Map<String, JsonValue>) = apply {
            body.additionalProperties(additionalBodyProperties)
        }

        fun putAdditionalBodyProperty(key: String, value: JsonValue) = apply {
            body.putAdditionalProperty(key, value)
        }

        fun putAllAdditionalBodyProperties(additionalBodyProperties: Map<String, JsonValue>) =
            apply {
                body.putAllAdditionalProperties(additionalBodyProperties)
            }

        fun removeAdditionalBodyProperty(key: String) = apply { body.removeAdditionalProperty(key) }

        fun removeAllAdditionalBodyProperties(keys: Set<String>) = apply {
            body.removeAllAdditionalProperties(keys)
        }

        fun additionalHeaders(additionalHeaders: Headers) = apply {
            this.additionalHeaders.clear()
            putAllAdditionalHeaders(additionalHeaders)
        }

        fun additionalHeaders(additionalHeaders: Map<String, Iterable<String>>) = apply {
            this.additionalHeaders.clear()
            putAllAdditionalHeaders(additionalHeaders)
        }

        fun putAdditionalHeader(name: String, value: String) = apply {
            additionalHeaders.put(name, value)
        }

        fun putAdditionalHeaders(name: String, values: Iterable<String>) = apply {
            additionalHeaders.put(name, values)
        }

        fun putAllAdditionalHeaders(additionalHeaders: Headers) = apply {
            this.additionalHeaders.putAll(additionalHeaders)
        }

        fun putAllAdditionalHeaders(additionalHeaders: Map<String, Iterable<String>>) = apply {
            this.additionalHeaders.putAll(additionalHeaders)
        }

        fun replaceAdditionalHeaders(name: String, value: String) = apply {
            additionalHeaders.replace(name, value)
        }

        fun replaceAdditionalHeaders(name: String, values: Iterable<String>) = apply {
            additionalHeaders.replace(name, values)
        }

        fun replaceAllAdditionalHeaders(additionalHeaders: Headers) = apply {
            this.additionalHeaders.replaceAll(additionalHeaders)
        }

        fun replaceAllAdditionalHeaders(additionalHeaders: Map<String, Iterable<String>>) = apply {
            this.additionalHeaders.replaceAll(additionalHeaders)
        }

        fun removeAdditionalHeaders(name: String) = apply { additionalHeaders.remove(name) }

        fun removeAllAdditionalHeaders(names: Set<String>) = apply {
            additionalHeaders.removeAll(names)
        }

        fun additionalQueryParams(additionalQueryParams: QueryParams) = apply {
            this.additionalQueryParams.clear()
            putAllAdditionalQueryParams(additionalQueryParams)
        }

        fun additionalQueryParams(additionalQueryParams: Map<String, Iterable<String>>) = apply {
            this.additionalQueryParams.clear()
            putAllAdditionalQueryParams(additionalQueryParams)
        }

        fun putAdditionalQueryParam(key: String, value: String) = apply {
            additionalQueryParams.put(key, value)
        }

        fun putAdditionalQueryParams(key: String, values: Iterable<String>) = apply {
            additionalQueryParams.put(key, values)
        }

        fun putAllAdditionalQueryParams(additionalQueryParams: QueryParams) = apply {
            this.additionalQueryParams.putAll(additionalQueryParams)
        }

        fun putAllAdditionalQueryParams(additionalQueryParams: Map<String, Iterable<String>>) =
            apply {
                this.additionalQueryParams.putAll(additionalQueryParams)
            }

        fun replaceAdditionalQueryParams(key: String, value: String) = apply {
            additionalQueryParams.replace(key, value)
        }

        fun replaceAdditionalQueryParams(key: String, values: Iterable<String>) = apply {
            additionalQueryParams.replace(key, values)
        }

        fun replaceAllAdditionalQueryParams(additionalQueryParams: QueryParams) = apply {
            this.additionalQueryParams.replaceAll(additionalQueryParams)
        }

        fun replaceAllAdditionalQueryParams(additionalQueryParams: Map<String, Iterable<String>>) =
            apply {
                this.additionalQueryParams.replaceAll(additionalQueryParams)
            }

        fun removeAdditionalQueryParams(key: String) = apply { additionalQueryParams.remove(key) }

        fun removeAllAdditionalQueryParams(keys: Set<String>) = apply {
            additionalQueryParams.removeAll(keys)
        }

        /**
         * Returns an immutable instance of [IssuerUpdateParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): IssuerUpdateParams =
            IssuerUpdateParams(
                federationIssuerId,
                betas?.toImmutable(),
                body.build(),
                additionalHeaders.build(),
                additionalQueryParams.build(),
            )
    }

    fun _body(): Body = body

    fun _pathParam(index: Int): String =
        when (index) {
            0 -> federationIssuerId ?: ""
            else -> ""
        }

    override fun _headers(): Headers =
        Headers.builder()
            .apply {
                betas?.forEach { put("anthropic-beta", it.toString()) }
                putAll(additionalHeaders)
            }
            .build()

    override fun _queryParams(): QueryParams = additionalQueryParams

    /**
     * Partial update. Set fields are applied; omitted fields are unchanged.
     *
     * Setting `jwks` replaces the whole JWKS configuration at once.
     */
    class Body
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val checkJti: JsonField<Boolean>,
        private val issuerUrl: JsonField<String>,
        private val jwks: JsonField<Jwks>,
        private val jwksPollingDisabled: JsonField<Boolean>,
        private val maxJwtLifetimeSeconds: JsonField<Long>,
        private val name: JsonField<String>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("check_jti")
            @ExcludeMissing
            checkJti: JsonField<Boolean> = JsonMissing.of(),
            @JsonProperty("issuer_url")
            @ExcludeMissing
            issuerUrl: JsonField<String> = JsonMissing.of(),
            @JsonProperty("jwks") @ExcludeMissing jwks: JsonField<Jwks> = JsonMissing.of(),
            @JsonProperty("jwks_polling_disabled")
            @ExcludeMissing
            jwksPollingDisabled: JsonField<Boolean> = JsonMissing.of(),
            @JsonProperty("max_jwt_lifetime_seconds")
            @ExcludeMissing
            maxJwtLifetimeSeconds: JsonField<Long> = JsonMissing.of(),
            @JsonProperty("name") @ExcludeMissing name: JsonField<String> = JsonMissing.of(),
        ) : this(
            checkJti,
            issuerUrl,
            jwks,
            jwksPollingDisabled,
            maxJwtLifetimeSeconds,
            name,
            mutableMapOf(),
        )

        /**
         * Whether the jwt-bearer exchange enforces JTI single-use (replay protection) for tokens
         * from this issuer. Applies only to assertions carrying a `jti` claim; tokens without one
         * are accepted without single-use enforcement.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun checkJti(): Optional<Boolean> = checkJti.getOptional("check_jti")

        /**
         * Replaces the `iss` claim value to match against. For discovery-mode issuers without a
         * `discovery_base`, this is also the URL Anthropic fetches the OIDC discovery document and
         * signing keys from, so changing it repoints the JWKS source. Changing the issuer URL to a
         * well-known shared platform is rejected while any live rule under this issuer would not
         * constrain tenant identity.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun issuerUrl(): Optional<String> = issuerUrl.getOptional("issuer_url")

        /**
         * Replaces the entire JWKS configuration.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun jwks(): Optional<Jwks> = jwks.getOptional("jwks")

        /**
         * Only `false` is accepted, to re-enable polling after the system pauses it. Polling is
         * paused automatically; sending `true` is rejected.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun jwksPollingDisabled(): Optional<Boolean> =
            jwksPollingDisabled.getOptional("jwks_polling_disabled")

        /**
         * Maximum allowed iat→exp spread for assertions from this issuer (1-176400 seconds, i.e. up
         * to 49h). Assertions must carry both `iat` and `exp`; a missing `iat` is rejected.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun maxJwtLifetimeSeconds(): Optional<Long> =
            maxJwtLifetimeSeconds.getOptional("max_jwt_lifetime_seconds")

        /**
         * Replaces the slug identifier (lowercase, digits, hyphens). Unique within the
         * organization; a duplicate name returns 409.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun name(): Optional<String> = name.getOptional("name")

        /**
         * Returns the raw JSON value of [checkJti].
         *
         * Unlike [checkJti], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("check_jti") @ExcludeMissing fun _checkJti(): JsonField<Boolean> = checkJti

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
         * Returns the raw JSON value of [jwksPollingDisabled].
         *
         * Unlike [jwksPollingDisabled], this method doesn't throw if the JSON field has an
         * unexpected type.
         */
        @JsonProperty("jwks_polling_disabled")
        @ExcludeMissing
        fun _jwksPollingDisabled(): JsonField<Boolean> = jwksPollingDisabled

        /**
         * Returns the raw JSON value of [maxJwtLifetimeSeconds].
         *
         * Unlike [maxJwtLifetimeSeconds], this method doesn't throw if the JSON field has an
         * unexpected type.
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

            /** Returns a mutable builder for constructing an instance of [Body]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Body]. */
        class Builder internal constructor() {

            private var checkJti: JsonField<Boolean> = JsonMissing.of()
            private var issuerUrl: JsonField<String> = JsonMissing.of()
            private var jwks: JsonField<Jwks> = JsonMissing.of()
            private var jwksPollingDisabled: JsonField<Boolean> = JsonMissing.of()
            private var maxJwtLifetimeSeconds: JsonField<Long> = JsonMissing.of()
            private var name: JsonField<String> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(body: Body) = apply {
                checkJti = body.checkJti
                issuerUrl = body.issuerUrl
                jwks = body.jwks
                jwksPollingDisabled = body.jwksPollingDisabled
                maxJwtLifetimeSeconds = body.maxJwtLifetimeSeconds
                name = body.name
                additionalProperties = body.additionalProperties.toMutableMap()
            }

            /**
             * Whether the jwt-bearer exchange enforces JTI single-use (replay protection) for
             * tokens from this issuer. Applies only to assertions carrying a `jti` claim; tokens
             * without one are accepted without single-use enforcement.
             */
            fun checkJti(checkJti: Boolean?) = checkJti(JsonField.ofNullable(checkJti))

            /**
             * Alias for [Builder.checkJti].
             *
             * This unboxed primitive overload exists for backwards compatibility.
             */
            fun checkJti(checkJti: Boolean) = checkJti(checkJti as Boolean?)

            /** Alias for calling [Builder.checkJti] with `checkJti.orElse(null)`. */
            fun checkJti(checkJti: Optional<Boolean>) = checkJti(checkJti.getOrNull())

            /**
             * Sets [Builder.checkJti] to an arbitrary JSON value.
             *
             * You should usually call [Builder.checkJti] with a well-typed [Boolean] value instead.
             * This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun checkJti(checkJti: JsonField<Boolean>) = apply { this.checkJti = checkJti }

            /**
             * Replaces the `iss` claim value to match against. For discovery-mode issuers without a
             * `discovery_base`, this is also the URL Anthropic fetches the OIDC discovery document
             * and signing keys from, so changing it repoints the JWKS source. Changing the issuer
             * URL to a well-known shared platform is rejected while any live rule under this issuer
             * would not constrain tenant identity.
             */
            fun issuerUrl(issuerUrl: String?) = issuerUrl(JsonField.ofNullable(issuerUrl))

            /** Alias for calling [Builder.issuerUrl] with `issuerUrl.orElse(null)`. */
            fun issuerUrl(issuerUrl: Optional<String>) = issuerUrl(issuerUrl.getOrNull())

            /**
             * Sets [Builder.issuerUrl] to an arbitrary JSON value.
             *
             * You should usually call [Builder.issuerUrl] with a well-typed [String] value instead.
             * This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun issuerUrl(issuerUrl: JsonField<String>) = apply { this.issuerUrl = issuerUrl }

            /** Replaces the entire JWKS configuration. */
            fun jwks(jwks: Jwks?) = jwks(JsonField.ofNullable(jwks))

            /** Alias for calling [Builder.jwks] with `jwks.orElse(null)`. */
            fun jwks(jwks: Optional<Jwks>) = jwks(jwks.getOrNull())

            /**
             * Sets [Builder.jwks] to an arbitrary JSON value.
             *
             * You should usually call [Builder.jwks] with a well-typed [Jwks] value instead. This
             * method is primarily for setting the field to an undocumented or not yet supported
             * value.
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
             * Only `false` is accepted, to re-enable polling after the system pauses it. Polling is
             * paused automatically; sending `true` is rejected.
             */
            fun jwksPollingDisabled(jwksPollingDisabled: Boolean?) =
                jwksPollingDisabled(JsonField.ofNullable(jwksPollingDisabled))

            /**
             * Alias for [Builder.jwksPollingDisabled].
             *
             * This unboxed primitive overload exists for backwards compatibility.
             */
            fun jwksPollingDisabled(jwksPollingDisabled: Boolean) =
                jwksPollingDisabled(jwksPollingDisabled as Boolean?)

            /**
             * Alias for calling [Builder.jwksPollingDisabled] with
             * `jwksPollingDisabled.orElse(null)`.
             */
            fun jwksPollingDisabled(jwksPollingDisabled: Optional<Boolean>) =
                jwksPollingDisabled(jwksPollingDisabled.getOrNull())

            /**
             * Sets [Builder.jwksPollingDisabled] to an arbitrary JSON value.
             *
             * You should usually call [Builder.jwksPollingDisabled] with a well-typed [Boolean]
             * value instead. This method is primarily for setting the field to an undocumented or
             * not yet supported value.
             */
            fun jwksPollingDisabled(jwksPollingDisabled: JsonField<Boolean>) = apply {
                this.jwksPollingDisabled = jwksPollingDisabled
            }

            /**
             * Maximum allowed iat→exp spread for assertions from this issuer (1-176400 seconds,
             * i.e. up to 49h). Assertions must carry both `iat` and `exp`; a missing `iat` is
             * rejected.
             */
            fun maxJwtLifetimeSeconds(maxJwtLifetimeSeconds: Long?) =
                maxJwtLifetimeSeconds(JsonField.ofNullable(maxJwtLifetimeSeconds))

            /**
             * Alias for [Builder.maxJwtLifetimeSeconds].
             *
             * This unboxed primitive overload exists for backwards compatibility.
             */
            fun maxJwtLifetimeSeconds(maxJwtLifetimeSeconds: Long) =
                maxJwtLifetimeSeconds(maxJwtLifetimeSeconds as Long?)

            /**
             * Alias for calling [Builder.maxJwtLifetimeSeconds] with
             * `maxJwtLifetimeSeconds.orElse(null)`.
             */
            fun maxJwtLifetimeSeconds(maxJwtLifetimeSeconds: Optional<Long>) =
                maxJwtLifetimeSeconds(maxJwtLifetimeSeconds.getOrNull())

            /**
             * Sets [Builder.maxJwtLifetimeSeconds] to an arbitrary JSON value.
             *
             * You should usually call [Builder.maxJwtLifetimeSeconds] with a well-typed [Long]
             * value instead. This method is primarily for setting the field to an undocumented or
             * not yet supported value.
             */
            fun maxJwtLifetimeSeconds(maxJwtLifetimeSeconds: JsonField<Long>) = apply {
                this.maxJwtLifetimeSeconds = maxJwtLifetimeSeconds
            }

            /**
             * Replaces the slug identifier (lowercase, digits, hyphens). Unique within the
             * organization; a duplicate name returns 409.
             */
            fun name(name: String?) = name(JsonField.ofNullable(name))

            /** Alias for calling [Builder.name] with `name.orElse(null)`. */
            fun name(name: Optional<String>) = name(name.getOrNull())

            /**
             * Sets [Builder.name] to an arbitrary JSON value.
             *
             * You should usually call [Builder.name] with a well-typed [String] value instead. This
             * method is primarily for setting the field to an undocumented or not yet supported
             * value.
             */
            fun name(name: JsonField<String>) = apply { this.name = name }

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
             * Returns an immutable instance of [Body].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             */
            fun build(): Body =
                Body(
                    checkJti,
                    issuerUrl,
                    jwks,
                    jwksPollingDisabled,
                    maxJwtLifetimeSeconds,
                    name,
                    additionalProperties.toMutableMap(),
                )
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
        fun validate(): Body = apply {
            if (validated) {
                return@apply
            }

            checkJti()
            issuerUrl()
            jwks().ifPresent { it.validate() }
            jwksPollingDisabled()
            maxJwtLifetimeSeconds()
            name()
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
            (if (checkJti.asKnown().isPresent) 1 else 0) +
                (if (issuerUrl.asKnown().isPresent) 1 else 0) +
                (jwks.asKnown().getOrNull()?.validity() ?: 0) +
                (if (jwksPollingDisabled.asKnown().isPresent) 1 else 0) +
                (if (maxJwtLifetimeSeconds.asKnown().isPresent) 1 else 0) +
                (if (name.asKnown().isPresent) 1 else 0)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Body &&
                checkJti == other.checkJti &&
                issuerUrl == other.issuerUrl &&
                jwks == other.jwks &&
                jwksPollingDisabled == other.jwksPollingDisabled &&
                maxJwtLifetimeSeconds == other.maxJwtLifetimeSeconds &&
                name == other.name &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(
                checkJti,
                issuerUrl,
                jwks,
                jwksPollingDisabled,
                maxJwtLifetimeSeconds,
                name,
                additionalProperties,
            )
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Body{checkJti=$checkJti, issuerUrl=$issuerUrl, jwks=$jwks, jwksPollingDisabled=$jwksPollingDisabled, maxJwtLifetimeSeconds=$maxJwtLifetimeSeconds, name=$name, additionalProperties=$additionalProperties}"
    }

    /** Replaces the entire JWKS configuration. */
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

        return other is IssuerUpdateParams &&
            federationIssuerId == other.federationIssuerId &&
            betas == other.betas &&
            body == other.body &&
            additionalHeaders == other.additionalHeaders &&
            additionalQueryParams == other.additionalQueryParams
    }

    override fun hashCode(): Int =
        Objects.hash(federationIssuerId, betas, body, additionalHeaders, additionalQueryParams)

    override fun toString() =
        "IssuerUpdateParams{federationIssuerId=$federationIssuerId, betas=$betas, body=$body, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
