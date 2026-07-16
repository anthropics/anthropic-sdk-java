// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.tunnels.BetaTunnel
import com.anthropic.models.beta.tunnels.BetaTunnelToken
import com.anthropic.models.beta.tunnels.TunnelArchiveParams
import com.anthropic.models.beta.tunnels.TunnelCreateParams
import com.anthropic.models.beta.tunnels.TunnelListPage
import com.anthropic.models.beta.tunnels.TunnelListParams
import com.anthropic.models.beta.tunnels.TunnelRetrieveParams
import com.anthropic.models.beta.tunnels.TunnelRevealTokenParams
import com.anthropic.models.beta.tunnels.TunnelRotateTokenParams
import com.anthropic.services.blocking.beta.tunnels.CertificateService
import com.google.errorprone.annotations.MustBeClosed
import java.util.function.Consumer

interface TunnelService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): TunnelService

    fun certificates(): CertificateService

    /**
     * The Tunnels API is in research preview. It requires the `anthropic-beta:
     * mcp-tunnels-2026-06-22` header and may change without a deprecation period. It supersedes the
     * Admin API endpoints at `/v1/organizations/tunnels`, which remain available during a migration
     * window.
     *
     * Creates a tunnel. Creation allocates a fresh hostname and provisions the tunnel; it is not
     * idempotent. The new tunnel rejects MCP traffic until at least one CA certificate is added.
     */
    fun create(): BetaTunnel = create(TunnelCreateParams.none())

    /** @see create */
    fun create(
        params: TunnelCreateParams = TunnelCreateParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaTunnel

    /** @see create */
    fun create(params: TunnelCreateParams = TunnelCreateParams.none()): BetaTunnel =
        create(params, RequestOptions.none())

    /** @see create */
    fun create(requestOptions: RequestOptions): BetaTunnel =
        create(TunnelCreateParams.none(), requestOptions)

    /**
     * The Tunnels API is in research preview. It requires the `anthropic-beta:
     * mcp-tunnels-2026-06-22` header and may change without a deprecation period. It supersedes the
     * Admin API endpoints at `/v1/organizations/tunnels`, which remain available during a migration
     * window.
     *
     * Fetches a tunnel by ID.
     */
    fun retrieve(tunnelId: String): BetaTunnel = retrieve(tunnelId, TunnelRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(
        tunnelId: String,
        params: TunnelRetrieveParams = TunnelRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaTunnel = retrieve(params.toBuilder().tunnelId(tunnelId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        tunnelId: String,
        params: TunnelRetrieveParams = TunnelRetrieveParams.none(),
    ): BetaTunnel = retrieve(tunnelId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: TunnelRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaTunnel

    /** @see retrieve */
    fun retrieve(params: TunnelRetrieveParams): BetaTunnel = retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(tunnelId: String, requestOptions: RequestOptions): BetaTunnel =
        retrieve(tunnelId, TunnelRetrieveParams.none(), requestOptions)

    /**
     * The Tunnels API is in research preview. It requires the `anthropic-beta:
     * mcp-tunnels-2026-06-22` header and may change without a deprecation period. It supersedes the
     * Admin API endpoints at `/v1/organizations/tunnels`, which remain available during a migration
     * window.
     *
     * Lists tunnels. Results are ordered by creation time, newest first; archived tunnels are
     * excluded unless include_archived is set.
     */
    fun list(): TunnelListPage = list(TunnelListParams.none())

    /** @see list */
    fun list(
        params: TunnelListParams = TunnelListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): TunnelListPage

    /** @see list */
    fun list(params: TunnelListParams = TunnelListParams.none()): TunnelListPage =
        list(params, RequestOptions.none())

    /** @see list */
    fun list(requestOptions: RequestOptions): TunnelListPage =
        list(TunnelListParams.none(), requestOptions)

    /**
     * The Tunnels API is in research preview. It requires the `anthropic-beta:
     * mcp-tunnels-2026-06-22` header and may change without a deprecation period. It supersedes the
     * Admin API endpoints at `/v1/organizations/tunnels`, which remain available during a migration
     * window.
     *
     * Archives a tunnel. Archival is irreversible: every non-archived certificate on the tunnel is
     * archived in the same operation, the hostname is retired and never re-allocated, and the
     * tunnel token is invalidated. Retrying against an already-archived tunnel returns the existing
     * record unchanged.
     */
    fun archive(tunnelId: String): BetaTunnel = archive(tunnelId, TunnelArchiveParams.none())

    /** @see archive */
    fun archive(
        tunnelId: String,
        params: TunnelArchiveParams = TunnelArchiveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaTunnel = archive(params.toBuilder().tunnelId(tunnelId).build(), requestOptions)

    /** @see archive */
    fun archive(
        tunnelId: String,
        params: TunnelArchiveParams = TunnelArchiveParams.none(),
    ): BetaTunnel = archive(tunnelId, params, RequestOptions.none())

    /** @see archive */
    fun archive(
        params: TunnelArchiveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaTunnel

    /** @see archive */
    fun archive(params: TunnelArchiveParams): BetaTunnel = archive(params, RequestOptions.none())

    /** @see archive */
    fun archive(tunnelId: String, requestOptions: RequestOptions): BetaTunnel =
        archive(tunnelId, TunnelArchiveParams.none(), requestOptions)

    /**
     * The Tunnels API is in research preview. It requires the `anthropic-beta:
     * mcp-tunnels-2026-06-22` header and may change without a deprecation period. It supersedes the
     * Admin API endpoints at `/v1/organizations/tunnels`, which remain available during a migration
     * window.
     *
     * Reveals a tunnel's connector token. The value is fetched live on each call; Anthropic does
     * not store it. Repeated calls return the same value until the token is rotated. Exposed as
     * POST so the token does not appear in intermediary access logs.
     */
    fun revealToken(tunnelId: String): BetaTunnelToken =
        revealToken(tunnelId, TunnelRevealTokenParams.none())

    /** @see revealToken */
    fun revealToken(
        tunnelId: String,
        params: TunnelRevealTokenParams = TunnelRevealTokenParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaTunnelToken = revealToken(params.toBuilder().tunnelId(tunnelId).build(), requestOptions)

    /** @see revealToken */
    fun revealToken(
        tunnelId: String,
        params: TunnelRevealTokenParams = TunnelRevealTokenParams.none(),
    ): BetaTunnelToken = revealToken(tunnelId, params, RequestOptions.none())

    /** @see revealToken */
    fun revealToken(
        params: TunnelRevealTokenParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaTunnelToken

    /** @see revealToken */
    fun revealToken(params: TunnelRevealTokenParams): BetaTunnelToken =
        revealToken(params, RequestOptions.none())

    /** @see revealToken */
    fun revealToken(tunnelId: String, requestOptions: RequestOptions): BetaTunnelToken =
        revealToken(tunnelId, TunnelRevealTokenParams.none(), requestOptions)

    /**
     * The Tunnels API is in research preview. It requires the `anthropic-beta:
     * mcp-tunnels-2026-06-22` header and may change without a deprecation period. It supersedes the
     * Admin API endpoints at `/v1/organizations/tunnels`, which remain available during a migration
     * window.
     *
     * Rotates a tunnel's connector token. Rotation invalidates the current token for new
     * connections and returns a fresh value; established connections are not severed. A connector
     * restarted after rotation must use the new value.
     */
    fun rotateToken(tunnelId: String): BetaTunnelToken =
        rotateToken(tunnelId, TunnelRotateTokenParams.none())

    /** @see rotateToken */
    fun rotateToken(
        tunnelId: String,
        params: TunnelRotateTokenParams = TunnelRotateTokenParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaTunnelToken = rotateToken(params.toBuilder().tunnelId(tunnelId).build(), requestOptions)

    /** @see rotateToken */
    fun rotateToken(
        tunnelId: String,
        params: TunnelRotateTokenParams = TunnelRotateTokenParams.none(),
    ): BetaTunnelToken = rotateToken(tunnelId, params, RequestOptions.none())

    /** @see rotateToken */
    fun rotateToken(
        params: TunnelRotateTokenParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaTunnelToken

    /** @see rotateToken */
    fun rotateToken(params: TunnelRotateTokenParams): BetaTunnelToken =
        rotateToken(params, RequestOptions.none())

    /** @see rotateToken */
    fun rotateToken(tunnelId: String, requestOptions: RequestOptions): BetaTunnelToken =
        rotateToken(tunnelId, TunnelRotateTokenParams.none(), requestOptions)

    /** A view of [TunnelService] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(modifier: Consumer<ClientOptions.Builder>): TunnelService.WithRawResponse

        fun certificates(): CertificateService.WithRawResponse

        /**
         * Returns a raw HTTP response for `post /v1/tunnels?beta=true`, but is otherwise the same
         * as [TunnelService.create].
         */
        @MustBeClosed fun create(): HttpResponseFor<BetaTunnel> = create(TunnelCreateParams.none())

        /** @see create */
        @MustBeClosed
        fun create(
            params: TunnelCreateParams = TunnelCreateParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaTunnel>

        /** @see create */
        @MustBeClosed
        fun create(
            params: TunnelCreateParams = TunnelCreateParams.none()
        ): HttpResponseFor<BetaTunnel> = create(params, RequestOptions.none())

        /** @see create */
        @MustBeClosed
        fun create(requestOptions: RequestOptions): HttpResponseFor<BetaTunnel> =
            create(TunnelCreateParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /v1/tunnels/{tunnel_id}?beta=true`, but is otherwise
         * the same as [TunnelService.retrieve].
         */
        @MustBeClosed
        fun retrieve(tunnelId: String): HttpResponseFor<BetaTunnel> =
            retrieve(tunnelId, TunnelRetrieveParams.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            tunnelId: String,
            params: TunnelRetrieveParams = TunnelRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaTunnel> =
            retrieve(params.toBuilder().tunnelId(tunnelId).build(), requestOptions)

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            tunnelId: String,
            params: TunnelRetrieveParams = TunnelRetrieveParams.none(),
        ): HttpResponseFor<BetaTunnel> = retrieve(tunnelId, params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            params: TunnelRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaTunnel>

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(params: TunnelRetrieveParams): HttpResponseFor<BetaTunnel> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            tunnelId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaTunnel> =
            retrieve(tunnelId, TunnelRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /v1/tunnels?beta=true`, but is otherwise the same as
         * [TunnelService.list].
         */
        @MustBeClosed fun list(): HttpResponseFor<TunnelListPage> = list(TunnelListParams.none())

        /** @see list */
        @MustBeClosed
        fun list(
            params: TunnelListParams = TunnelListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<TunnelListPage>

        /** @see list */
        @MustBeClosed
        fun list(
            params: TunnelListParams = TunnelListParams.none()
        ): HttpResponseFor<TunnelListPage> = list(params, RequestOptions.none())

        /** @see list */
        @MustBeClosed
        fun list(requestOptions: RequestOptions): HttpResponseFor<TunnelListPage> =
            list(TunnelListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /v1/tunnels/{tunnel_id}/archive?beta=true`, but is
         * otherwise the same as [TunnelService.archive].
         */
        @MustBeClosed
        fun archive(tunnelId: String): HttpResponseFor<BetaTunnel> =
            archive(tunnelId, TunnelArchiveParams.none())

        /** @see archive */
        @MustBeClosed
        fun archive(
            tunnelId: String,
            params: TunnelArchiveParams = TunnelArchiveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaTunnel> =
            archive(params.toBuilder().tunnelId(tunnelId).build(), requestOptions)

        /** @see archive */
        @MustBeClosed
        fun archive(
            tunnelId: String,
            params: TunnelArchiveParams = TunnelArchiveParams.none(),
        ): HttpResponseFor<BetaTunnel> = archive(tunnelId, params, RequestOptions.none())

        /** @see archive */
        @MustBeClosed
        fun archive(
            params: TunnelArchiveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaTunnel>

        /** @see archive */
        @MustBeClosed
        fun archive(params: TunnelArchiveParams): HttpResponseFor<BetaTunnel> =
            archive(params, RequestOptions.none())

        /** @see archive */
        @MustBeClosed
        fun archive(tunnelId: String, requestOptions: RequestOptions): HttpResponseFor<BetaTunnel> =
            archive(tunnelId, TunnelArchiveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /v1/tunnels/{tunnel_id}/reveal_token?beta=true`,
         * but is otherwise the same as [TunnelService.revealToken].
         */
        @MustBeClosed
        fun revealToken(tunnelId: String): HttpResponseFor<BetaTunnelToken> =
            revealToken(tunnelId, TunnelRevealTokenParams.none())

        /** @see revealToken */
        @MustBeClosed
        fun revealToken(
            tunnelId: String,
            params: TunnelRevealTokenParams = TunnelRevealTokenParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaTunnelToken> =
            revealToken(params.toBuilder().tunnelId(tunnelId).build(), requestOptions)

        /** @see revealToken */
        @MustBeClosed
        fun revealToken(
            tunnelId: String,
            params: TunnelRevealTokenParams = TunnelRevealTokenParams.none(),
        ): HttpResponseFor<BetaTunnelToken> = revealToken(tunnelId, params, RequestOptions.none())

        /** @see revealToken */
        @MustBeClosed
        fun revealToken(
            params: TunnelRevealTokenParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaTunnelToken>

        /** @see revealToken */
        @MustBeClosed
        fun revealToken(params: TunnelRevealTokenParams): HttpResponseFor<BetaTunnelToken> =
            revealToken(params, RequestOptions.none())

        /** @see revealToken */
        @MustBeClosed
        fun revealToken(
            tunnelId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaTunnelToken> =
            revealToken(tunnelId, TunnelRevealTokenParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /v1/tunnels/{tunnel_id}/rotate_token?beta=true`,
         * but is otherwise the same as [TunnelService.rotateToken].
         */
        @MustBeClosed
        fun rotateToken(tunnelId: String): HttpResponseFor<BetaTunnelToken> =
            rotateToken(tunnelId, TunnelRotateTokenParams.none())

        /** @see rotateToken */
        @MustBeClosed
        fun rotateToken(
            tunnelId: String,
            params: TunnelRotateTokenParams = TunnelRotateTokenParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaTunnelToken> =
            rotateToken(params.toBuilder().tunnelId(tunnelId).build(), requestOptions)

        /** @see rotateToken */
        @MustBeClosed
        fun rotateToken(
            tunnelId: String,
            params: TunnelRotateTokenParams = TunnelRotateTokenParams.none(),
        ): HttpResponseFor<BetaTunnelToken> = rotateToken(tunnelId, params, RequestOptions.none())

        /** @see rotateToken */
        @MustBeClosed
        fun rotateToken(
            params: TunnelRotateTokenParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaTunnelToken>

        /** @see rotateToken */
        @MustBeClosed
        fun rotateToken(params: TunnelRotateTokenParams): HttpResponseFor<BetaTunnelToken> =
            rotateToken(params, RequestOptions.none())

        /** @see rotateToken */
        @MustBeClosed
        fun rotateToken(
            tunnelId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaTunnelToken> =
            rotateToken(tunnelId, TunnelRotateTokenParams.none(), requestOptions)
    }
}
