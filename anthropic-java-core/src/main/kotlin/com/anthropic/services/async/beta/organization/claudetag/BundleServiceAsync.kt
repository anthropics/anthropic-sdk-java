package com.anthropic.services.async.beta.organization.claudetag

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.organization.claudetag.bundles.BetaClaudeTagBundle
import com.anthropic.models.beta.organization.claudetag.bundles.BundleListPageAsync
import com.anthropic.models.beta.organization.claudetag.bundles.BundleListParams
import com.anthropic.models.beta.organization.claudetag.bundles.BundleRetrieveParams
import com.anthropic.services.async.beta.organization.claudetag.bundles.TargetServiceAsync
import java.util.concurrent.CompletableFuture
import java.util.function.Consumer

interface BundleServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): BundleServiceAsync

    fun targets(): TargetServiceAsync

    /**
     * Get one Bundle. What the Bundle grants (connectors, repositories and credentials) is never
     * returned.
     */
    fun retrieve(bundleId: String): CompletableFuture<BetaClaudeTagBundle> =
        retrieve(bundleId, BundleRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(
        bundleId: String,
        params: BundleRetrieveParams = BundleRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaClaudeTagBundle> =
        retrieve(params.toBuilder().bundleId(bundleId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        bundleId: String,
        params: BundleRetrieveParams = BundleRetrieveParams.none(),
    ): CompletableFuture<BetaClaudeTagBundle> = retrieve(bundleId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: BundleRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BetaClaudeTagBundle>

    /** @see retrieve */
    fun retrieve(params: BundleRetrieveParams): CompletableFuture<BetaClaudeTagBundle> =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        bundleId: String,
        requestOptions: RequestOptions,
    ): CompletableFuture<BetaClaudeTagBundle> =
        retrieve(bundleId, BundleRetrieveParams.none(), requestOptions)

    /**
     * List the organization's Bundles, newest first by `created_at`. A Bundle that Claude Tag
     * manages for itself is not listed.
     */
    fun list(): CompletableFuture<BundleListPageAsync> = list(BundleListParams.none())

    /** @see list */
    fun list(
        params: BundleListParams = BundleListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): CompletableFuture<BundleListPageAsync>

    /** @see list */
    fun list(
        params: BundleListParams = BundleListParams.none()
    ): CompletableFuture<BundleListPageAsync> = list(params, RequestOptions.none())

    /** @see list */
    fun list(requestOptions: RequestOptions): CompletableFuture<BundleListPageAsync> =
        list(BundleListParams.none(), requestOptions)

    /**
     * A view of [BundleServiceAsync] that provides access to raw HTTP responses for each method.
     */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): BundleServiceAsync.WithRawResponse

        fun targets(): TargetServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `get /v1/organizations/claude_tag/bundles/{bundle_id}`,
         * but is otherwise the same as [BundleServiceAsync.retrieve].
         */
        fun retrieve(bundleId: String): CompletableFuture<HttpResponseFor<BetaClaudeTagBundle>> =
            retrieve(bundleId, BundleRetrieveParams.none())

        /** @see retrieve */
        fun retrieve(
            bundleId: String,
            params: BundleRetrieveParams = BundleRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaClaudeTagBundle>> =
            retrieve(params.toBuilder().bundleId(bundleId).build(), requestOptions)

        /** @see retrieve */
        fun retrieve(
            bundleId: String,
            params: BundleRetrieveParams = BundleRetrieveParams.none(),
        ): CompletableFuture<HttpResponseFor<BetaClaudeTagBundle>> =
            retrieve(bundleId, params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            params: BundleRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BetaClaudeTagBundle>>

        /** @see retrieve */
        fun retrieve(
            params: BundleRetrieveParams
        ): CompletableFuture<HttpResponseFor<BetaClaudeTagBundle>> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        fun retrieve(
            bundleId: String,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponseFor<BetaClaudeTagBundle>> =
            retrieve(bundleId, BundleRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /v1/organizations/claude_tag/bundles`, but is
         * otherwise the same as [BundleServiceAsync.list].
         */
        fun list(): CompletableFuture<HttpResponseFor<BundleListPageAsync>> =
            list(BundleListParams.none())

        /** @see list */
        fun list(
            params: BundleListParams = BundleListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): CompletableFuture<HttpResponseFor<BundleListPageAsync>>

        /** @see list */
        fun list(
            params: BundleListParams = BundleListParams.none()
        ): CompletableFuture<HttpResponseFor<BundleListPageAsync>> =
            list(params, RequestOptions.none())

        /** @see list */
        fun list(
            requestOptions: RequestOptions
        ): CompletableFuture<HttpResponseFor<BundleListPageAsync>> =
            list(BundleListParams.none(), requestOptions)
    }
}
