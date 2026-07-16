// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta.tunnels

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.tunnels.certificates.BetaTunnelCertificate
import com.anthropic.models.beta.tunnels.certificates.CertificateArchiveParams
import com.anthropic.models.beta.tunnels.certificates.CertificateCreateParams
import com.anthropic.models.beta.tunnels.certificates.CertificateListPage
import com.anthropic.models.beta.tunnels.certificates.CertificateListParams
import com.anthropic.models.beta.tunnels.certificates.CertificateRetrieveParams
import com.google.errorprone.annotations.MustBeClosed
import java.util.function.Consumer

interface CertificateService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): CertificateService

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
    fun create(tunnelId: String, params: CertificateCreateParams): BetaTunnelCertificate =
        create(tunnelId, params, RequestOptions.none())

    /** @see create */
    fun create(
        tunnelId: String,
        params: CertificateCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaTunnelCertificate = create(params.toBuilder().tunnelId(tunnelId).build(), requestOptions)

    /** @see create */
    fun create(params: CertificateCreateParams): BetaTunnelCertificate =
        create(params, RequestOptions.none())

    /** @see create */
    fun create(
        params: CertificateCreateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaTunnelCertificate

    /**
     * The Tunnels API is in research preview. It requires the `anthropic-beta:
     * mcp-tunnels-2026-06-22` header and may change without a deprecation period. It supersedes the
     * Admin API endpoints at `/v1/organizations/tunnels`, which remain available during a migration
     * window.
     *
     * Fetches a tunnel certificate by ID.
     */
    fun retrieve(certificateId: String, params: CertificateRetrieveParams): BetaTunnelCertificate =
        retrieve(certificateId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        certificateId: String,
        params: CertificateRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaTunnelCertificate =
        retrieve(params.toBuilder().certificateId(certificateId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(params: CertificateRetrieveParams): BetaTunnelCertificate =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: CertificateRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaTunnelCertificate

    /**
     * The Tunnels API is in research preview. It requires the `anthropic-beta:
     * mcp-tunnels-2026-06-22` header and may change without a deprecation period. It supersedes the
     * Admin API endpoints at `/v1/organizations/tunnels`, which remain available during a migration
     * window.
     *
     * Lists the certificates registered on a tunnel. Archived certificates are excluded unless
     * include_archived is set.
     */
    fun list(tunnelId: String): CertificateListPage = list(tunnelId, CertificateListParams.none())

    /** @see list */
    fun list(
        tunnelId: String,
        params: CertificateListParams = CertificateListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CertificateListPage = list(params.toBuilder().tunnelId(tunnelId).build(), requestOptions)

    /** @see list */
    fun list(
        tunnelId: String,
        params: CertificateListParams = CertificateListParams.none(),
    ): CertificateListPage = list(tunnelId, params, RequestOptions.none())

    /** @see list */
    fun list(
        params: CertificateListParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CertificateListPage

    /** @see list */
    fun list(params: CertificateListParams): CertificateListPage =
        list(params, RequestOptions.none())

    /** @see list */
    fun list(tunnelId: String, requestOptions: RequestOptions): CertificateListPage =
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
    fun archive(certificateId: String, params: CertificateArchiveParams): BetaTunnelCertificate =
        archive(certificateId, params, RequestOptions.none())

    /** @see archive */
    fun archive(
        certificateId: String,
        params: CertificateArchiveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaTunnelCertificate =
        archive(params.toBuilder().certificateId(certificateId).build(), requestOptions)

    /** @see archive */
    fun archive(params: CertificateArchiveParams): BetaTunnelCertificate =
        archive(params, RequestOptions.none())

    /** @see archive */
    fun archive(
        params: CertificateArchiveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaTunnelCertificate

    /**
     * A view of [CertificateService] that provides access to raw HTTP responses for each method.
     */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): CertificateService.WithRawResponse

        /**
         * Returns a raw HTTP response for `post /v1/tunnels/{tunnel_id}/certificates?beta=true`,
         * but is otherwise the same as [CertificateService.create].
         */
        @MustBeClosed
        fun create(
            tunnelId: String,
            params: CertificateCreateParams,
        ): HttpResponseFor<BetaTunnelCertificate> = create(tunnelId, params, RequestOptions.none())

        /** @see create */
        @MustBeClosed
        fun create(
            tunnelId: String,
            params: CertificateCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaTunnelCertificate> =
            create(params.toBuilder().tunnelId(tunnelId).build(), requestOptions)

        /** @see create */
        @MustBeClosed
        fun create(params: CertificateCreateParams): HttpResponseFor<BetaTunnelCertificate> =
            create(params, RequestOptions.none())

        /** @see create */
        @MustBeClosed
        fun create(
            params: CertificateCreateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaTunnelCertificate>

        /**
         * Returns a raw HTTP response for `get
         * /v1/tunnels/{tunnel_id}/certificates/{certificate_id}?beta=true`, but is otherwise the
         * same as [CertificateService.retrieve].
         */
        @MustBeClosed
        fun retrieve(
            certificateId: String,
            params: CertificateRetrieveParams,
        ): HttpResponseFor<BetaTunnelCertificate> =
            retrieve(certificateId, params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            certificateId: String,
            params: CertificateRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaTunnelCertificate> =
            retrieve(params.toBuilder().certificateId(certificateId).build(), requestOptions)

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(params: CertificateRetrieveParams): HttpResponseFor<BetaTunnelCertificate> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            params: CertificateRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaTunnelCertificate>

        /**
         * Returns a raw HTTP response for `get /v1/tunnels/{tunnel_id}/certificates?beta=true`, but
         * is otherwise the same as [CertificateService.list].
         */
        @MustBeClosed
        fun list(tunnelId: String): HttpResponseFor<CertificateListPage> =
            list(tunnelId, CertificateListParams.none())

        /** @see list */
        @MustBeClosed
        fun list(
            tunnelId: String,
            params: CertificateListParams = CertificateListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<CertificateListPage> =
            list(params.toBuilder().tunnelId(tunnelId).build(), requestOptions)

        /** @see list */
        @MustBeClosed
        fun list(
            tunnelId: String,
            params: CertificateListParams = CertificateListParams.none(),
        ): HttpResponseFor<CertificateListPage> = list(tunnelId, params, RequestOptions.none())

        /** @see list */
        @MustBeClosed
        fun list(
            params: CertificateListParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<CertificateListPage>

        /** @see list */
        @MustBeClosed
        fun list(params: CertificateListParams): HttpResponseFor<CertificateListPage> =
            list(params, RequestOptions.none())

        /** @see list */
        @MustBeClosed
        fun list(
            tunnelId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<CertificateListPage> =
            list(tunnelId, CertificateListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post
         * /v1/tunnels/{tunnel_id}/certificates/{certificate_id}/archive?beta=true`, but is
         * otherwise the same as [CertificateService.archive].
         */
        @MustBeClosed
        fun archive(
            certificateId: String,
            params: CertificateArchiveParams,
        ): HttpResponseFor<BetaTunnelCertificate> =
            archive(certificateId, params, RequestOptions.none())

        /** @see archive */
        @MustBeClosed
        fun archive(
            certificateId: String,
            params: CertificateArchiveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaTunnelCertificate> =
            archive(params.toBuilder().certificateId(certificateId).build(), requestOptions)

        /** @see archive */
        @MustBeClosed
        fun archive(params: CertificateArchiveParams): HttpResponseFor<BetaTunnelCertificate> =
            archive(params, RequestOptions.none())

        /** @see archive */
        @MustBeClosed
        fun archive(
            params: CertificateArchiveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaTunnelCertificate>
    }
}
