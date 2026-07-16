// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta.tunnels

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.tunnels.certificates.BetaTunnelCertificate
import com.anthropic.models.beta.tunnels.certificates.CertificateArchiveParams
import com.anthropic.models.beta.tunnels.certificates.CertificateCreateParams
import com.anthropic.models.beta.tunnels.certificates.CertificateListPageAsync
import com.anthropic.models.beta.tunnels.certificates.CertificateListParams
import com.anthropic.models.beta.tunnels.certificates.CertificateRetrieveParams
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

interface CertificateServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): CertificateServiceAsync

    /**
     * The Tunnels API is in research preview. It requires the `anthropic-beta:
     * mcp-tunnels-2026-06-22` header and may change without a deprecation period. It supersedes the
     * Admin API endpoints at `/v1/organizations/tunnels`, which remain available during a migration
     * window.
     *
     * Registers a public CA certificate on a tunnel. Anthropic verifies the gateway's server
     * certificate against this CA when it terminates the inner TLS session. A tunnel holds at most
     * two non-archived certificates.
     */
    fun create(
        tunnelId: String,
        params: CertificateCreateParams,
    ): CompletableFuture<BetaTunnelCertificate> = create(tunnelId, params, RequestOptions.none())

    /** @see create */
    fun create(
        tunnelId: String,
        params: CertificateCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaTunnelCertificate> =
        create(params.toBuilder().tunnelId(tunnelId).build(), requestOptions)

    /** @see create */
    fun create(params: CertificateCreateParams): CompletableFuture<BetaTunnelCertificate> =
        create(params, RequestOptions.none())

    /** @see create */
    fun create(
        params: CertificateCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaTunnelCertificate>

    /**
     * The Tunnels API is in research preview. It requires the `anthropic-beta:
     * mcp-tunnels-2026-06-22` header and may change without a deprecation period. It supersedes the
     * Admin API endpoints at `/v1/organizations/tunnels`, which remain available during a migration
     * window.
     *
     * Fetches a tunnel certificate by ID.
     */
    fun retrieve(
        certificateId: String,
        params: CertificateRetrieveParams,
    ): CompletableFuture<BetaTunnelCertificate> =
        retrieve(certificateId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        certificateId: String,
        params: CertificateRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaTunnelCertificate> =
        retrieve(params.toBuilder().certificateId(certificateId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(params: CertificateRetrieveParams): CompletableFuture<BetaTunnelCertificate> =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: CertificateRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaTunnelCertificate>

    /**
     * The Tunnels API is in research preview. It requires the `anthropic-beta:
     * mcp-tunnels-2026-06-22` header and may change without a deprecation period. It supersedes the
     * Admin API endpoints at `/v1/organizations/tunnels`, which remain available during a migration
     * window.
     *
     * Lists the certificates registered on a tunnel. Archived certificates are excluded unless
     * include_archived is set.
     */
    fun list(tunnelId: String): CompletableFuture<CertificateListPageAsync> =
        list(tunnelId, CertificateListParams.none())

    /** @see list */
    fun list(
        tunnelId: String,
        params: CertificateListParams = CertificateListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<CertificateListPageAsync> =
        list(params.toBuilder().tunnelId(tunnelId).build(), requestOptions)

    /** @see list */
    fun list(
        tunnelId: String,
        params: CertificateListParams = CertificateListParams.none(),
    ): CompletableFuture<CertificateListPageAsync> = list(tunnelId, params, RequestOptions.none())

    /** @see list */
    fun list(
        params: CertificateListParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<CertificateListPageAsync>

    /** @see list */
    fun list(params: CertificateListParams): CompletableFuture<CertificateListPageAsync> =
        list(params, RequestOptions.none())

    /** @see list */
    fun list(
        tunnelId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<CertificateListPageAsync> =
        list(tunnelId, CertificateListParams.none(), requestOptions)

    /**
     * The Tunnels API is in research preview. It requires the `anthropic-beta:
     * mcp-tunnels-2026-06-22` header and may change without a deprecation period. It supersedes the
     * Admin API endpoints at `/v1/organizations/tunnels`, which remain available during a migration
     * window.
     *
     * Archives a tunnel certificate, removing it from the set Anthropic trusts for the tunnel. The
     * certificate record is retained. Archiving the last non-archived certificate is permitted; the
     * tunnel rejects MCP traffic until a new certificate is added.
     */
    fun archive(
        certificateId: String,
        params: CertificateArchiveParams,
    ): CompletableFuture<BetaTunnelCertificate> =
        archive(certificateId, params, RequestOptions.none())

    /** @see archive */
    fun archive(
        certificateId: String,
        params: CertificateArchiveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaTunnelCertificate> =
        archive(params.toBuilder().certificateId(certificateId).build(), requestOptions)

    /** @see archive */
    fun archive(params: CertificateArchiveParams): CompletableFuture<BetaTunnelCertificate> =
        archive(params, RequestOptions.none())

    /** @see archive */
    fun archive(
        params: CertificateArchiveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaTunnelCertificate>

    /**
     * A view of [CertificateServiceAsync] that provides access to raw HTTP responses for each
     * method.
     */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): CertificateServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `post /v1/tunnels/{tunnel_id}/certificates?beta=true`,
         * but is otherwise the same as [CertificateServiceAsync.create].
         */
        fun create(
            tunnelId: String,
            params: CertificateCreateParams,
        ): CompletableFuture<HttpResponseFor<BetaTunnelCertificate>> =
            create(tunnelId, params, RequestOptions.none())

        /** @see create */
        fun create(
            tunnelId: String,
            params: CertificateCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaTunnelCertificate>> =
            create(params.toBuilder().tunnelId(tunnelId).build(), requestOptions)

        /** @see create */
        fun create(
            params: CertificateCreateParams
        ): CompletableFuture<HttpResponseFor<BetaTunnelCertificate>> =
            create(params, RequestOptions.none())

        /** @see create */
        fun create(
            params: CertificateCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaTunnelCertificate>>

        /**
         * Returns a raw HTTP response for `get
         * /v1/tunnels/{tunnel_id}/certificates/{certificate_id}?beta=true`, but is otherwise the
         * same as [CertificateServiceAsync.retrieve].
         */
        fun retrieve(
            certificateId: String,
            params: CertificateRetrieveParams,
        ): CompletableFuture<HttpResponseFor<BetaTunnelCertificate>> =
            retrieve(certificateId, params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            certificateId: String,
            params: CertificateRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaTunnelCertificate>> =
            retrieve(params.toBuilder().certificateId(certificateId).build(), requestOptions)

        /** @see retrieve */
        fun retrieve(
            params: CertificateRetrieveParams
        ): CompletableFuture<HttpResponseFor<BetaTunnelCertificate>> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            params: CertificateRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaTunnelCertificate>>

        /**
         * Returns a raw HTTP response for `get /v1/tunnels/{tunnel_id}/certificates?beta=true`, but
         * is otherwise the same as [CertificateServiceAsync.list].
         */
        fun list(tunnelId: String): CompletableFuture<HttpResponseFor<CertificateListPageAsync>> =
            list(tunnelId, CertificateListParams.none())

        /** @see list */
        fun list(
            tunnelId: String,
            params: CertificateListParams = CertificateListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<CertificateListPageAsync>> =
            list(params.toBuilder().tunnelId(tunnelId).build(), requestOptions)

        /** @see list */
        fun list(
            tunnelId: String,
            params: CertificateListParams = CertificateListParams.none(),
        ): CompletableFuture<HttpResponseFor<CertificateListPageAsync>> =
            list(tunnelId, params, RequestOptions.none())

        /** @see list */
        fun list(
            params: CertificateListParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<CertificateListPageAsync>>

        /** @see list */
        fun list(
            params: CertificateListParams
        ): CompletableFuture<HttpResponseFor<CertificateListPageAsync>> =
            list(params, RequestOptions.none())

        /** @see list */
        fun list(
            tunnelId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<CertificateListPageAsync>> =
            list(tunnelId, CertificateListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post
         * /v1/tunnels/{tunnel_id}/certificates/{certificate_id}/archive?beta=true`, but is
         * otherwise the same as [CertificateServiceAsync.archive].
         */
        fun archive(
            certificateId: String,
            params: CertificateArchiveParams,
        ): CompletableFuture<HttpResponseFor<BetaTunnelCertificate>> =
            archive(certificateId, params, RequestOptions.none())

        /** @see archive */
        fun archive(
            certificateId: String,
            params: CertificateArchiveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaTunnelCertificate>> =
            archive(params.toBuilder().certificateId(certificateId).build(), requestOptions)

        /** @see archive */
        fun archive(
            params: CertificateArchiveParams
        ): CompletableFuture<HttpResponseFor<BetaTunnelCertificate>> =
            archive(params, RequestOptions.none())

        /** @see archive */
        fun archive(
            params: CertificateArchiveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaTunnelCertificate>>
    }
}
