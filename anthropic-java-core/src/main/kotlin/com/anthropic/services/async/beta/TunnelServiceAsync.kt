// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.tunnels.BetaTunnel
import com.anthropic.models.beta.tunnels.BetaTunnelToken
import com.anthropic.models.beta.tunnels.TunnelArchiveParams
import com.anthropic.models.beta.tunnels.TunnelCreateParams
import com.anthropic.models.beta.tunnels.TunnelListPageAsync
import com.anthropic.models.beta.tunnels.TunnelListParams
import com.anthropic.models.beta.tunnels.TunnelRetrieveParams
import com.anthropic.models.beta.tunnels.TunnelRevealTokenParams
import com.anthropic.models.beta.tunnels.TunnelRotateTokenParams
import com.anthropic.services.async.beta.tunnels.CertificateServiceAsync
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

interface TunnelServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): TunnelServiceAsync

    fun certificates(): CertificateServiceAsync

    /**
     * The Tunnels API is in research preview. It requires the `anthropic-beta:
     * mcp-tunnels-2026-06-22` header and may change without a deprecation period. It supersedes the
     * Admin API endpoints at `/v1/organizations/tunnels`, which remain available during a migration
     * window.
     *
     * Creates a tunnel. Creation allocates a fresh hostname and provisions the tunnel; it is not
     * idempotent. The new tunnel rejects MCP traffic until at least one CA certificate is added.
     */
    fun create(): CompletableFuture<BetaTunnel> = create(TunnelCreateParams.none())

    /** @see create */
    fun create(
        params: TunnelCreateParams = TunnelCreateParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaTunnel>

    /** @see create */
    fun create(
        params: TunnelCreateParams = TunnelCreateParams.none()
    ): CompletableFuture<BetaTunnel> = create(params, RequestOptions.none())

    /** @see create */
    fun create(requestOptions: RequestOptions): CompletableFuture<BetaTunnel> =
        create(TunnelCreateParams.none(), requestOptions)

    /**
     * The Tunnels API is in research preview. It requires the `anthropic-beta:
     * mcp-tunnels-2026-06-22` header and may change without a deprecation period. It supersedes the
     * Admin API endpoints at `/v1/organizations/tunnels`, which remain available during a migration
     * window.
     *
     * Fetches a tunnel by ID.
     */
    fun retrieve(tunnelId: String): CompletableFuture<BetaTunnel> =
        retrieve(tunnelId, TunnelRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(
        tunnelId: String,
        params: TunnelRetrieveParams = TunnelRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaTunnel> =
        retrieve(params.toBuilder().tunnelId(tunnelId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        tunnelId: String,
        params: TunnelRetrieveParams = TunnelRetrieveParams.none(),
    ): CompletableFuture<BetaTunnel> = retrieve(tunnelId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: TunnelRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaTunnel>

    /** @see retrieve */
    fun retrieve(params: TunnelRetrieveParams): CompletableFuture<BetaTunnel> =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(tunnelId: String, requestOptions: RequestOptions): CompletableFuture<BetaTunnel> =
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
    fun list(): CompletableFuture<TunnelListPageAsync> = list(TunnelListParams.none())

    /** @see list */
    fun list(
        params: TunnelListParams = TunnelListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<TunnelListPageAsync>

    /** @see list */
    fun list(
        params: TunnelListParams = TunnelListParams.none()
    ): CompletableFuture<TunnelListPageAsync> = list(params, RequestOptions.none())

    /** @see list */
    fun list(requestOptions: RequestOptions): CompletableFuture<TunnelListPageAsync> =
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
    fun archive(tunnelId: String): CompletableFuture<BetaTunnel> =
        archive(tunnelId, TunnelArchiveParams.none())

    /** @see archive */
    fun archive(
        tunnelId: String,
        params: TunnelArchiveParams = TunnelArchiveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaTunnel> =
        archive(params.toBuilder().tunnelId(tunnelId).build(), requestOptions)

    /** @see archive */
    fun archive(
        tunnelId: String,
        params: TunnelArchiveParams = TunnelArchiveParams.none(),
    ): CompletableFuture<BetaTunnel> = archive(tunnelId, params, RequestOptions.none())

    /** @see archive */
    fun archive(
        params: TunnelArchiveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaTunnel>

    /** @see archive */
    fun archive(params: TunnelArchiveParams): CompletableFuture<BetaTunnel> =
        archive(params, RequestOptions.none())

    /** @see archive */
    fun archive(tunnelId: String, requestOptions: RequestOptions): CompletableFuture<BetaTunnel> =
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
    fun revealToken(tunnelId: String): CompletableFuture<BetaTunnelToken> =
        revealToken(tunnelId, TunnelRevealTokenParams.none())

    /** @see revealToken */
    fun revealToken(
        tunnelId: String,
        params: TunnelRevealTokenParams = TunnelRevealTokenParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaTunnelToken> =
        revealToken(params.toBuilder().tunnelId(tunnelId).build(), requestOptions)

    /** @see revealToken */
    fun revealToken(
        tunnelId: String,
        params: TunnelRevealTokenParams = TunnelRevealTokenParams.none(),
    ): CompletableFuture<BetaTunnelToken> = revealToken(tunnelId, params, RequestOptions.none())

    /** @see revealToken */
    fun revealToken(
        params: TunnelRevealTokenParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaTunnelToken>

    /** @see revealToken */
    fun revealToken(params: TunnelRevealTokenParams): CompletableFuture<BetaTunnelToken> =
        revealToken(params, RequestOptions.none())

    /** @see revealToken */
    fun revealToken(
        tunnelId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaTunnelToken> =
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
    fun rotateToken(tunnelId: String): CompletableFuture<BetaTunnelToken> =
        rotateToken(tunnelId, TunnelRotateTokenParams.none())

    /** @see rotateToken */
    fun rotateToken(
        tunnelId: String,
        params: TunnelRotateTokenParams = TunnelRotateTokenParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaTunnelToken> =
        rotateToken(params.toBuilder().tunnelId(tunnelId).build(), requestOptions)

    /** @see rotateToken */
    fun rotateToken(
        tunnelId: String,
        params: TunnelRotateTokenParams = TunnelRotateTokenParams.none(),
    ): CompletableFuture<BetaTunnelToken> = rotateToken(tunnelId, params, RequestOptions.none())

    /** @see rotateToken */
    fun rotateToken(
        params: TunnelRotateTokenParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaTunnelToken>

    /** @see rotateToken */
    fun rotateToken(params: TunnelRotateTokenParams): CompletableFuture<BetaTunnelToken> =
        rotateToken(params, RequestOptions.none())

    /** @see rotateToken */
    fun rotateToken(
        tunnelId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaTunnelToken> =
        rotateToken(tunnelId, TunnelRotateTokenParams.none(), requestOptions)

    /**
     * A view of [TunnelServiceAsync] that provides access to raw HTTP responses for each method.
     */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): TunnelServiceAsync.WithRawResponse

        fun certificates(): CertificateServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `post /v1/tunnels?beta=true`, but is otherwise the same
         * as [TunnelServiceAsync.create].
         */
        fun create(): CompletableFuture<HttpResponseFor<BetaTunnel>> =
            create(TunnelCreateParams.none())

        /** @see create */
        fun create(
            params: TunnelCreateParams = TunnelCreateParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaTunnel>>

        /** @see create */
        fun create(
            params: TunnelCreateParams = TunnelCreateParams.none()
        ): CompletableFuture<HttpResponseFor<BetaTunnel>> = create(params, RequestOptions.none())

        /** @see create */
        fun create(requestOptions: RequestOptions): CompletableFuture<HttpResponseFor<BetaTunnel>> =
            create(TunnelCreateParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /v1/tunnels/{tunnel_id}?beta=true`, but is otherwise
         * the same as [TunnelServiceAsync.retrieve].
         */
        fun retrieve(tunnelId: String): CompletableFuture<HttpResponseFor<BetaTunnel>> =
            retrieve(tunnelId, TunnelRetrieveParams.none())

        /** @see retrieve */
        fun retrieve(
            tunnelId: String,
            params: TunnelRetrieveParams = TunnelRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaTunnel>> =
            retrieve(params.toBuilder().tunnelId(tunnelId).build(), requestOptions)

        /** @see retrieve */
        fun retrieve(
            tunnelId: String,
            params: TunnelRetrieveParams = TunnelRetrieveParams.none(),
        ): CompletableFuture<HttpResponseFor<BetaTunnel>> =
            retrieve(tunnelId, params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            params: TunnelRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaTunnel>>

        /** @see retrieve */
        fun retrieve(params: TunnelRetrieveParams): CompletableFuture<HttpResponseFor<BetaTunnel>> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            tunnelId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaTunnel>> =
            retrieve(tunnelId, TunnelRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /v1/tunnels?beta=true`, but is otherwise the same as
         * [TunnelServiceAsync.list].
         */
        fun list(): CompletableFuture<HttpResponseFor<TunnelListPageAsync>> =
            list(TunnelListParams.none())

        /** @see list */
        fun list(
            params: TunnelListParams = TunnelListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<TunnelListPageAsync>>

        /** @see list */
        fun list(
            params: TunnelListParams = TunnelListParams.none()
        ): CompletableFuture<HttpResponseFor<TunnelListPageAsync>> =
            list(params, RequestOptions.none())

        /** @see list */
        fun list(
            requestOptions: RequestOptions
        ): CompletableFuture<HttpResponseFor<TunnelListPageAsync>> =
            list(TunnelListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /v1/tunnels/{tunnel_id}/archive?beta=true`, but is
         * otherwise the same as [TunnelServiceAsync.archive].
         */
        fun archive(tunnelId: String): CompletableFuture<HttpResponseFor<BetaTunnel>> =
            archive(tunnelId, TunnelArchiveParams.none())

        /** @see archive */
        fun archive(
            tunnelId: String,
            params: TunnelArchiveParams = TunnelArchiveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaTunnel>> =
            archive(params.toBuilder().tunnelId(tunnelId).build(), requestOptions)

        /** @see archive */
        fun archive(
            tunnelId: String,
            params: TunnelArchiveParams = TunnelArchiveParams.none(),
        ): CompletableFuture<HttpResponseFor<BetaTunnel>> =
            archive(tunnelId, params, RequestOptions.none())

        /** @see archive */
        fun archive(
            params: TunnelArchiveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaTunnel>>

        /** @see archive */
        fun archive(params: TunnelArchiveParams): CompletableFuture<HttpResponseFor<BetaTunnel>> =
            archive(params, RequestOptions.none())

        /** @see archive */
        fun archive(
            tunnelId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaTunnel>> =
            archive(tunnelId, TunnelArchiveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /v1/tunnels/{tunnel_id}/reveal_token?beta=true`,
         * but is otherwise the same as [TunnelServiceAsync.revealToken].
         */
        fun revealToken(tunnelId: String): CompletableFuture<HttpResponseFor<BetaTunnelToken>> =
            revealToken(tunnelId, TunnelRevealTokenParams.none())

        /** @see revealToken */
        fun revealToken(
            tunnelId: String,
            params: TunnelRevealTokenParams = TunnelRevealTokenParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaTunnelToken>> =
            revealToken(params.toBuilder().tunnelId(tunnelId).build(), requestOptions)

        /** @see revealToken */
        fun revealToken(
            tunnelId: String,
            params: TunnelRevealTokenParams = TunnelRevealTokenParams.none(),
        ): CompletableFuture<HttpResponseFor<BetaTunnelToken>> =
            revealToken(tunnelId, params, RequestOptions.none())

        /** @see revealToken */
        fun revealToken(
            params: TunnelRevealTokenParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaTunnelToken>>

        /** @see revealToken */
        fun revealToken(
            params: TunnelRevealTokenParams
        ): CompletableFuture<HttpResponseFor<BetaTunnelToken>> =
            revealToken(params, RequestOptions.none())

        /** @see revealToken */
        fun revealToken(
            tunnelId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaTunnelToken>> =
            revealToken(tunnelId, TunnelRevealTokenParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /v1/tunnels/{tunnel_id}/rotate_token?beta=true`,
         * but is otherwise the same as [TunnelServiceAsync.rotateToken].
         */
        fun rotateToken(tunnelId: String): CompletableFuture<HttpResponseFor<BetaTunnelToken>> =
            rotateToken(tunnelId, TunnelRotateTokenParams.none())

        /** @see rotateToken */
        fun rotateToken(
            tunnelId: String,
            params: TunnelRotateTokenParams = TunnelRotateTokenParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaTunnelToken>> =
            rotateToken(params.toBuilder().tunnelId(tunnelId).build(), requestOptions)

        /** @see rotateToken */
        fun rotateToken(
            tunnelId: String,
            params: TunnelRotateTokenParams = TunnelRotateTokenParams.none(),
        ): CompletableFuture<HttpResponseFor<BetaTunnelToken>> =
            rotateToken(tunnelId, params, RequestOptions.none())

        /** @see rotateToken */
        fun rotateToken(
            params: TunnelRotateTokenParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaTunnelToken>>

        /** @see rotateToken */
        fun rotateToken(
            params: TunnelRotateTokenParams
        ): CompletableFuture<HttpResponseFor<BetaTunnelToken>> =
            rotateToken(params, RequestOptions.none())

        /** @see rotateToken */
        fun rotateToken(
            tunnelId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaTunnelToken>> =
            rotateToken(tunnelId, TunnelRotateTokenParams.none(), requestOptions)
    }
}
