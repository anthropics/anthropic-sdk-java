package com.anthropic.services.blocking.organization.federation

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.organization.federation.issuers.FederationIssuer
import com.anthropic.models.organization.federation.issuers.IssuerArchiveParams
import com.anthropic.models.organization.federation.issuers.IssuerCreateParams
import com.anthropic.models.organization.federation.issuers.IssuerListPage
import com.anthropic.models.organization.federation.issuers.IssuerListParams
import com.anthropic.models.organization.federation.issuers.IssuerRetrieveParams
import com.anthropic.models.organization.federation.issuers.IssuerUpdateParams
import com.google.errorprone.annotations.MustBeClosed
import java.util.function.Consumer

interface IssuerService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): IssuerService

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
    fun create(params: IssuerCreateParams): FederationIssuer = create(params, RequestOptions.none())

    /** @see create */
    fun create(
        params: IssuerCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): FederationIssuer

    /**
     * **Requires an OAuth access token with the `org:admin` scope**, from `ant auth login --scope
     * org:admin` or a workload identity federation rule; Admin API keys are not accepted. See
     * [Manage WIF with the Admin API](/docs/en/manage-claude/wif-admin-api).
     *
     * Retrieve a federation issuer by its ID (`fdis_...`).
     */
    fun retrieve(federationIssuerId: String): FederationIssuer =
        retrieve(federationIssuerId, IssuerRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(
        federationIssuerId: String,
        params: IssuerRetrieveParams = IssuerRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): FederationIssuer =
        retrieve(params.toBuilder().federationIssuerId(federationIssuerId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        federationIssuerId: String,
        params: IssuerRetrieveParams = IssuerRetrieveParams.none(),
    ): FederationIssuer = retrieve(federationIssuerId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: IssuerRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): FederationIssuer

    /** @see retrieve */
    fun retrieve(params: IssuerRetrieveParams): FederationIssuer =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(federationIssuerId: String, requestOptions: RequestOptions): FederationIssuer =
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
    fun update(federationIssuerId: String): FederationIssuer =
        update(federationIssuerId, IssuerUpdateParams.none())

    /** @see update */
    fun update(
        federationIssuerId: String,
        params: IssuerUpdateParams = IssuerUpdateParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): FederationIssuer =
        update(params.toBuilder().federationIssuerId(federationIssuerId).build(), requestOptions)

    /** @see update */
    fun update(
        federationIssuerId: String,
        params: IssuerUpdateParams = IssuerUpdateParams.none(),
    ): FederationIssuer = update(federationIssuerId, params, RequestOptions.none())

    /** @see update */
    fun update(
        params: IssuerUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): FederationIssuer

    /** @see update */
    fun update(params: IssuerUpdateParams): FederationIssuer = update(params, RequestOptions.none())

    /** @see update */
    fun update(federationIssuerId: String, requestOptions: RequestOptions): FederationIssuer =
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
    fun list(): IssuerListPage = list(IssuerListParams.none())

    /** @see list */
    fun list(
        params: IssuerListParams = IssuerListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): IssuerListPage

    /** @see list */
    fun list(params: IssuerListParams = IssuerListParams.none()): IssuerListPage =
        list(params, RequestOptions.none())

    /** @see list */
    fun list(requestOptions: RequestOptions): IssuerListPage =
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
    fun archive(federationIssuerId: String): FederationIssuer =
        archive(federationIssuerId, IssuerArchiveParams.none())

    /** @see archive */
    fun archive(
        federationIssuerId: String,
        params: IssuerArchiveParams = IssuerArchiveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): FederationIssuer =
        archive(params.toBuilder().federationIssuerId(federationIssuerId).build(), requestOptions)

    /** @see archive */
    fun archive(
        federationIssuerId: String,
        params: IssuerArchiveParams = IssuerArchiveParams.none(),
    ): FederationIssuer = archive(federationIssuerId, params, RequestOptions.none())

    /** @see archive */
    fun archive(
        params: IssuerArchiveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): FederationIssuer

    /** @see archive */
    fun archive(params: IssuerArchiveParams): FederationIssuer =
        archive(params, RequestOptions.none())

    /** @see archive */
    fun archive(federationIssuerId: String, requestOptions: RequestOptions): FederationIssuer =
        archive(federationIssuerId, IssuerArchiveParams.none(), requestOptions)

    /** A view of [IssuerService] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(modifier: Consumer<ClientOptions.Builder>): IssuerService.WithRawResponse

        /**
         * Returns a raw HTTP response for `post /v1/organizations/federation_issuers`, but is
         * otherwise the same as [IssuerService.create].
         */
        @MustBeClosed
        fun create(params: IssuerCreateParams): HttpResponseFor<FederationIssuer> =
            create(params, RequestOptions.none())

        /** @see create */
        @MustBeClosed
        fun create(
            params: IssuerCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<FederationIssuer>

        /**
         * Returns a raw HTTP response for `get
         * /v1/organizations/federation_issuers/{federation_issuer_id}`, but is otherwise the same
         * as [IssuerService.retrieve].
         */
        @MustBeClosed
        fun retrieve(federationIssuerId: String): HttpResponseFor<FederationIssuer> =
            retrieve(federationIssuerId, IssuerRetrieveParams.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            federationIssuerId: String,
            params: IssuerRetrieveParams = IssuerRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<FederationIssuer> =
            retrieve(
                params.toBuilder().federationIssuerId(federationIssuerId).build(),
                requestOptions,
            )

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            federationIssuerId: String,
            params: IssuerRetrieveParams = IssuerRetrieveParams.none(),
        ): HttpResponseFor<FederationIssuer> =
            retrieve(federationIssuerId, params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            params: IssuerRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<FederationIssuer>

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(params: IssuerRetrieveParams): HttpResponseFor<FederationIssuer> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            federationIssuerId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<FederationIssuer> =
            retrieve(federationIssuerId, IssuerRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post
         * /v1/organizations/federation_issuers/{federation_issuer_id}`, but is otherwise the same
         * as [IssuerService.update].
         */
        @MustBeClosed
        fun update(federationIssuerId: String): HttpResponseFor<FederationIssuer> =
            update(federationIssuerId, IssuerUpdateParams.none())

        /** @see update */
        @MustBeClosed
        fun update(
            federationIssuerId: String,
            params: IssuerUpdateParams = IssuerUpdateParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<FederationIssuer> =
            update(
                params.toBuilder().federationIssuerId(federationIssuerId).build(),
                requestOptions,
            )

        /** @see update */
        @MustBeClosed
        fun update(
            federationIssuerId: String,
            params: IssuerUpdateParams = IssuerUpdateParams.none(),
        ): HttpResponseFor<FederationIssuer> =
            update(federationIssuerId, params, RequestOptions.none())

        /** @see update */
        @MustBeClosed
        fun update(
            params: IssuerUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<FederationIssuer>

        /** @see update */
        @MustBeClosed
        fun update(params: IssuerUpdateParams): HttpResponseFor<FederationIssuer> =
            update(params, RequestOptions.none())

        /** @see update */
        @MustBeClosed
        fun update(
            federationIssuerId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<FederationIssuer> =
            update(federationIssuerId, IssuerUpdateParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /v1/organizations/federation_issuers`, but is
         * otherwise the same as [IssuerService.list].
         */
        @MustBeClosed fun list(): HttpResponseFor<IssuerListPage> = list(IssuerListParams.none())

        /** @see list */
        @MustBeClosed
        fun list(
            params: IssuerListParams = IssuerListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<IssuerListPage>

        /** @see list */
        @MustBeClosed
        fun list(
            params: IssuerListParams = IssuerListParams.none()
        ): HttpResponseFor<IssuerListPage> = list(params, RequestOptions.none())

        /** @see list */
        @MustBeClosed
        fun list(requestOptions: RequestOptions): HttpResponseFor<IssuerListPage> =
            list(IssuerListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post
         * /v1/organizations/federation_issuers/{federation_issuer_id}/archive`, but is otherwise
         * the same as [IssuerService.archive].
         */
        @MustBeClosed
        fun archive(federationIssuerId: String): HttpResponseFor<FederationIssuer> =
            archive(federationIssuerId, IssuerArchiveParams.none())

        /** @see archive */
        @MustBeClosed
        fun archive(
            federationIssuerId: String,
            params: IssuerArchiveParams = IssuerArchiveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<FederationIssuer> =
            archive(
                params.toBuilder().federationIssuerId(federationIssuerId).build(),
                requestOptions,
            )

        /** @see archive */
        @MustBeClosed
        fun archive(
            federationIssuerId: String,
            params: IssuerArchiveParams = IssuerArchiveParams.none(),
        ): HttpResponseFor<FederationIssuer> =
            archive(federationIssuerId, params, RequestOptions.none())

        /** @see archive */
        @MustBeClosed
        fun archive(
            params: IssuerArchiveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<FederationIssuer>

        /** @see archive */
        @MustBeClosed
        fun archive(params: IssuerArchiveParams): HttpResponseFor<FederationIssuer> =
            archive(params, RequestOptions.none())

        /** @see archive */
        @MustBeClosed
        fun archive(
            federationIssuerId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<FederationIssuer> =
            archive(federationIssuerId, IssuerArchiveParams.none(), requestOptions)
    }
}
