// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta.organization.federation

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.organization.federation.issuers.BetaFederationIssuer
import com.anthropic.models.beta.organization.federation.issuers.IssuerArchiveParams
import com.anthropic.models.beta.organization.federation.issuers.IssuerCreateParams
import com.anthropic.models.beta.organization.federation.issuers.IssuerListPageAsync
import com.anthropic.models.beta.organization.federation.issuers.IssuerListParams
import com.anthropic.models.beta.organization.federation.issuers.IssuerRetrieveParams
import com.anthropic.models.beta.organization.federation.issuers.IssuerUpdateParams
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

interface IssuerServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): IssuerServiceAsync

    /**
     * **Requires an OAuth access token with the `org:admin` scope**, from `ant auth login --scope
     * org:admin` or a workload identity federation rule; Admin API keys are not accepted. See
     * [Manage WIF with the Admin API](/docs/en/manage-claude/wif-admin-api).
     *
     * Register an OIDC issuer that Anthropic will trust for workload identity federation in your
     * organization.
     *
     * The `jwks` field controls how the issuer's signing keys are obtained and takes one of three
     * shapes selected by `type`: `discovery` (resolve keys through OIDC discovery), `explicit_url`
     * (fetch keys from a fixed JWKS URL), or `inline` (provide a static key set). When `jwks.type`
     * is `discovery` and no `discovery_base` is set, the issuer URL must be publicly reachable over
     * HTTPS so Anthropic can fetch the discovery document; for `explicit_url` and `inline` modes
     * the issuer URL is only matched as the JWT's `iss` claim and is not fetched.
     */
    fun create(params: IssuerCreateParams): CompletableFuture<BetaFederationIssuer> =
        create(params, RequestOptions.none())

    /** @see create */
    fun create(
        params: IssuerCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaFederationIssuer>

    /**
     * **Requires an OAuth access token with the `org:admin` scope**, from `ant auth login --scope
     * org:admin` or a workload identity federation rule; Admin API keys are not accepted. See
     * [Manage WIF with the Admin API](/docs/en/manage-claude/wif-admin-api).
     *
     * Retrieve a federation issuer by its ID (`fdis_...`).
     */
    fun retrieve(federationIssuerId: String): CompletableFuture<BetaFederationIssuer> =
        retrieve(federationIssuerId, IssuerRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(
        federationIssuerId: String,
        params: IssuerRetrieveParams = IssuerRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaFederationIssuer> =
        retrieve(params.toBuilder().federationIssuerId(federationIssuerId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        federationIssuerId: String,
        params: IssuerRetrieveParams = IssuerRetrieveParams.none(),
    ): CompletableFuture<BetaFederationIssuer> =
        retrieve(federationIssuerId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: IssuerRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaFederationIssuer>

    /** @see retrieve */
    fun retrieve(params: IssuerRetrieveParams): CompletableFuture<BetaFederationIssuer> =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        federationIssuerId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaFederationIssuer> =
        retrieve(federationIssuerId, IssuerRetrieveParams.none(), requestOptions)

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
    fun update(federationIssuerId: String): CompletableFuture<BetaFederationIssuer> =
        update(federationIssuerId, IssuerUpdateParams.none())

    /** @see update */
    fun update(
        federationIssuerId: String,
        params: IssuerUpdateParams = IssuerUpdateParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaFederationIssuer> =
        update(params.toBuilder().federationIssuerId(federationIssuerId).build(), requestOptions)

    /** @see update */
    fun update(
        federationIssuerId: String,
        params: IssuerUpdateParams = IssuerUpdateParams.none(),
    ): CompletableFuture<BetaFederationIssuer> =
        update(federationIssuerId, params, RequestOptions.none())

    /** @see update */
    fun update(
        params: IssuerUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaFederationIssuer>

    /** @see update */
    fun update(params: IssuerUpdateParams): CompletableFuture<BetaFederationIssuer> =
        update(params, RequestOptions.none())

    /** @see update */
    fun update(
        federationIssuerId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaFederationIssuer> =
        update(federationIssuerId, IssuerUpdateParams.none(), requestOptions)

    /**
     * **Requires an OAuth access token with the `org:admin` scope**, from `ant auth login --scope
     * org:admin` or a workload identity federation rule; Admin API keys are not accepted. See
     * [Manage WIF with the Admin API](/docs/en/manage-claude/wif-admin-api).
     *
     * List federation issuers in your organization.
     *
     * Archived issuers are excluded unless `include_archived=true`.
     */
    fun list(): CompletableFuture<IssuerListPageAsync> = list(IssuerListParams.none())

    /** @see list */
    fun list(
        params: IssuerListParams = IssuerListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<IssuerListPageAsync>

    /** @see list */
    fun list(
        params: IssuerListParams = IssuerListParams.none()
    ): CompletableFuture<IssuerListPageAsync> = list(params, RequestOptions.none())

    /** @see list */
    fun list(requestOptions: RequestOptions): CompletableFuture<IssuerListPageAsync> =
        list(IssuerListParams.none(), requestOptions)

    /**
     * **Requires an OAuth access token with the `org:admin` scope**, from `ant auth login --scope
     * org:admin` or a workload identity federation rule; Admin API keys are not accepted. See
     * [Manage WIF with the Admin API](/docs/en/manage-claude/wif-admin-api).
     *
     * Archive a federation issuer.
     *
     * Idempotent; re-archiving returns the issuer with its original `archived_at`. Rejected with
     * 400 if any live (non-archived) federation rule still references the issuer; archive those
     * rules first (a rule's issuer cannot be changed), or recreate them against another issuer.
     */
    fun archive(federationIssuerId: String): CompletableFuture<BetaFederationIssuer> =
        archive(federationIssuerId, IssuerArchiveParams.none())

    /** @see archive */
    fun archive(
        federationIssuerId: String,
        params: IssuerArchiveParams = IssuerArchiveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaFederationIssuer> =
        archive(params.toBuilder().federationIssuerId(federationIssuerId).build(), requestOptions)

    /** @see archive */
    fun archive(
        federationIssuerId: String,
        params: IssuerArchiveParams = IssuerArchiveParams.none(),
    ): CompletableFuture<BetaFederationIssuer> =
        archive(federationIssuerId, params, RequestOptions.none())

    /** @see archive */
    fun archive(
        params: IssuerArchiveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaFederationIssuer>

    /** @see archive */
    fun archive(params: IssuerArchiveParams): CompletableFuture<BetaFederationIssuer> =
        archive(params, RequestOptions.none())

    /** @see archive */
    fun archive(
        federationIssuerId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaFederationIssuer> =
        archive(federationIssuerId, IssuerArchiveParams.none(), requestOptions)

    /**
     * A view of [IssuerServiceAsync] that provides access to raw HTTP responses for each method.
     */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): IssuerServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `post /v1/organizations/federation_issuers?beta=true`,
         * but is otherwise the same as [IssuerServiceAsync.create].
         */
        fun create(
            params: IssuerCreateParams
        ): CompletableFuture<HttpResponseFor<BetaFederationIssuer>> =
            create(params, RequestOptions.none())

        /** @see create */
        fun create(
            params: IssuerCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaFederationIssuer>>

        /**
         * Returns a raw HTTP response for `get
         * /v1/organizations/federation_issuers/{federation_issuer_id}?beta=true`, but is otherwise
         * the same as [IssuerServiceAsync.retrieve].
         */
        fun retrieve(
            federationIssuerId: String
        ): CompletableFuture<HttpResponseFor<BetaFederationIssuer>> =
            retrieve(federationIssuerId, IssuerRetrieveParams.none())

        /** @see retrieve */
        fun retrieve(
            federationIssuerId: String,
            params: IssuerRetrieveParams = IssuerRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaFederationIssuer>> =
            retrieve(
                params.toBuilder().federationIssuerId(federationIssuerId).build(),
                requestOptions,
            )

        /** @see retrieve */
        fun retrieve(
            federationIssuerId: String,
            params: IssuerRetrieveParams = IssuerRetrieveParams.none(),
        ): CompletableFuture<HttpResponseFor<BetaFederationIssuer>> =
            retrieve(federationIssuerId, params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            params: IssuerRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaFederationIssuer>>

        /** @see retrieve */
        fun retrieve(
            params: IssuerRetrieveParams
        ): CompletableFuture<HttpResponseFor<BetaFederationIssuer>> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            federationIssuerId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaFederationIssuer>> =
            retrieve(federationIssuerId, IssuerRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post
         * /v1/organizations/federation_issuers/{federation_issuer_id}?beta=true`, but is otherwise
         * the same as [IssuerServiceAsync.update].
         */
        fun update(
            federationIssuerId: String
        ): CompletableFuture<HttpResponseFor<BetaFederationIssuer>> =
            update(federationIssuerId, IssuerUpdateParams.none())

        /** @see update */
        fun update(
            federationIssuerId: String,
            params: IssuerUpdateParams = IssuerUpdateParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaFederationIssuer>> =
            update(
                params.toBuilder().federationIssuerId(federationIssuerId).build(),
                requestOptions,
            )

        /** @see update */
        fun update(
            federationIssuerId: String,
            params: IssuerUpdateParams = IssuerUpdateParams.none(),
        ): CompletableFuture<HttpResponseFor<BetaFederationIssuer>> =
            update(federationIssuerId, params, RequestOptions.none())

        /** @see update */
        fun update(
            params: IssuerUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaFederationIssuer>>

        /** @see update */
        fun update(
            params: IssuerUpdateParams
        ): CompletableFuture<HttpResponseFor<BetaFederationIssuer>> =
            update(params, RequestOptions.none())

        /** @see update */
        fun update(
            federationIssuerId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaFederationIssuer>> =
            update(federationIssuerId, IssuerUpdateParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /v1/organizations/federation_issuers?beta=true`, but
         * is otherwise the same as [IssuerServiceAsync.list].
         */
        fun list(): CompletableFuture<HttpResponseFor<IssuerListPageAsync>> =
            list(IssuerListParams.none())

        /** @see list */
        fun list(
            params: IssuerListParams = IssuerListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<IssuerListPageAsync>>

        /** @see list */
        fun list(
            params: IssuerListParams = IssuerListParams.none()
        ): CompletableFuture<HttpResponseFor<IssuerListPageAsync>> =
            list(params, RequestOptions.none())

        /** @see list */
        fun list(
            requestOptions: RequestOptions
        ): CompletableFuture<HttpResponseFor<IssuerListPageAsync>> =
            list(IssuerListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post
         * /v1/organizations/federation_issuers/{federation_issuer_id}/archive?beta=true`, but is
         * otherwise the same as [IssuerServiceAsync.archive].
         */
        fun archive(
            federationIssuerId: String
        ): CompletableFuture<HttpResponseFor<BetaFederationIssuer>> =
            archive(federationIssuerId, IssuerArchiveParams.none())

        /** @see archive */
        fun archive(
            federationIssuerId: String,
            params: IssuerArchiveParams = IssuerArchiveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaFederationIssuer>> =
            archive(
                params.toBuilder().federationIssuerId(federationIssuerId).build(),
                requestOptions,
            )

        /** @see archive */
        fun archive(
            federationIssuerId: String,
            params: IssuerArchiveParams = IssuerArchiveParams.none(),
        ): CompletableFuture<HttpResponseFor<BetaFederationIssuer>> =
            archive(federationIssuerId, params, RequestOptions.none())

        /** @see archive */
        fun archive(
            params: IssuerArchiveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaFederationIssuer>>

        /** @see archive */
        fun archive(
            params: IssuerArchiveParams
        ): CompletableFuture<HttpResponseFor<BetaFederationIssuer>> =
            archive(params, RequestOptions.none())

        /** @see archive */
        fun archive(
            federationIssuerId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaFederationIssuer>> =
            archive(federationIssuerId, IssuerArchiveParams.none(), requestOptions)
    }
}
