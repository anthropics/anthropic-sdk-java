package com.anthropic.services.blocking.beta.organization.claudetag

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.organization.claudetag.bundles.BetaClaudeTagBundle
import com.anthropic.models.beta.organization.claudetag.bundles.BundleListPage
import com.anthropic.models.beta.organization.claudetag.bundles.BundleListParams
import com.anthropic.models.beta.organization.claudetag.bundles.BundleRetrieveParams
import com.anthropic.services.blocking.beta.organization.claudetag.bundles.TargetService
import com.google.errorprone.annotations.MustBeClosed
import java.util.function.Consumer

interface BundleService {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): BundleService

    fun targets(): TargetService

    /**
     * Get one Bundle. What the Bundle grants (connectors, repositories and credentials) is never
     * returned.
     */
    fun retrieve(bundleId: String): BetaClaudeTagBundle =
        retrieve(bundleId, BundleRetrieveParams.none())

    /** @see retrieve */
    fun retrieve(
        bundleId: String,
        params: BundleRetrieveParams = BundleRetrieveParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaClaudeTagBundle = retrieve(params.toBuilder().bundleId(bundleId).build(), requestOptions)

    /** @see retrieve */
    fun retrieve(
        bundleId: String,
        params: BundleRetrieveParams = BundleRetrieveParams.none(),
    ): BetaClaudeTagBundle = retrieve(bundleId, params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(
        params: BundleRetrieveParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaClaudeTagBundle

    /** @see retrieve */
    fun retrieve(params: BundleRetrieveParams): BetaClaudeTagBundle =
        retrieve(params, RequestOptions.none())

    /** @see retrieve */
    fun retrieve(bundleId: String, requestOptions: RequestOptions): BetaClaudeTagBundle =
        retrieve(bundleId, BundleRetrieveParams.none(), requestOptions)

    /**
     * List the organization's Bundles, newest first by `created_at`. A Bundle that Claude Tag
     * manages for itself is not listed.
     */
    fun list(): BundleListPage = list(BundleListParams.none())

    /** @see list */
    fun list(
        params: BundleListParams = BundleListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BundleListPage

    /** @see list */
    fun list(params: BundleListParams = BundleListParams.none()): BundleListPage =
        list(params, RequestOptions.none())

    /** @see list */
    fun list(requestOptions: RequestOptions): BundleListPage =
        list(BundleListParams.none(), requestOptions)

    /** A view of [BundleService] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(modifier: Consumer<ClientOptions.Builder>): BundleService.WithRawResponse

        fun targets(): TargetService.WithRawResponse

        /**
         * Returns a raw HTTP response for `get /v1/organizations/claude_tag/bundles/{bundle_id}`,
         * but is otherwise the same as [BundleService.retrieve].
         */
        @MustBeClosed
        fun retrieve(bundleId: String): HttpResponseFor<BetaClaudeTagBundle> =
            retrieve(bundleId, BundleRetrieveParams.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            bundleId: String,
            params: BundleRetrieveParams = BundleRetrieveParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaClaudeTagBundle> =
            retrieve(params.toBuilder().bundleId(bundleId).build(), requestOptions)

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            bundleId: String,
            params: BundleRetrieveParams = BundleRetrieveParams.none(),
        ): HttpResponseFor<BetaClaudeTagBundle> = retrieve(bundleId, params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            params: BundleRetrieveParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaClaudeTagBundle>

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(params: BundleRetrieveParams): HttpResponseFor<BetaClaudeTagBundle> =
            retrieve(params, RequestOptions.none())

        /** @see retrieve */
        @MustBeClosed
        fun retrieve(
            bundleId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaClaudeTagBundle> =
            retrieve(bundleId, BundleRetrieveParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /v1/organizations/claude_tag/bundles`, but is
         * otherwise the same as [BundleService.list].
         */
        @MustBeClosed fun list(): HttpResponseFor<BundleListPage> = list(BundleListParams.none())

        /** @see list */
        @MustBeClosed
        fun list(
            params: BundleListParams = BundleListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BundleListPage>

        /** @see list */
        @MustBeClosed
        fun list(
            params: BundleListParams = BundleListParams.none()
        ): HttpResponseFor<BundleListPage> = list(params, RequestOptions.none())

        /** @see list */
        @MustBeClosed
        fun list(requestOptions: RequestOptions): HttpResponseFor<BundleListPage> =
            list(BundleListParams.none(), requestOptions)
    }
}
