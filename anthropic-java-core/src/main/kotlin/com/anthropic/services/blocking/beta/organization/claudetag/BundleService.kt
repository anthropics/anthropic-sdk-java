package com.anthropic.services.blocking.beta.organization.claudetag

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.organization.claudetag.bundles.BetaClaudeTagBundle
import com.anthropic.models.beta.organization.claudetag.bundles.BundleListPage
import com.anthropic.models.beta.organization.claudetag.bundles.BundleListParams
import com.anthropic.models.beta.organization.claudetag.bundles.BundleRetrieveParams
import com.anthropic.models.beta.organization.claudetag.bundles.BundleUpdateParams
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
     *
     * @throws com.anthropic.models.beta.organization.claudetag.BetaApiKeyScopeMissingException
     * @throws
     *   com.anthropic.models.beta.organization.claudetag.BetaClaudeTagWritesNotEnabledException
     * @throws
     *   com.anthropic.models.beta.organization.claudetag.BetaComplianceConfigurationRestrictedException
     * @throws
     *   com.anthropic.models.beta.organization.claudetag.BetaOrganizationLevelScopedApiKeyRequiredException
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
     * Change a Bundle's instructions. The update is partial: a field left out is unchanged, and
     * `instructions` sent as null or as an empty string clears them. The later of two updates wins.
     * Sessions that start after the change use the new instructions; sessions already running keep
     * the ones they started with. Some Bundles are managed in claude.ai, and an update of one
     * returns 400.
     *
     * @throws
     *   com.anthropic.models.beta.organization.claudetag.BetaBundleAttachmentNotSupportedException
     * @throws com.anthropic.models.beta.organization.claudetag.BetaBundleOnCallException
     * @throws com.anthropic.models.beta.organization.claudetag.BetaApiKeyScopeMissingException
     * @throws
     *   com.anthropic.models.beta.organization.claudetag.BetaClaudeTagWritesNotEnabledException
     * @throws
     *   com.anthropic.models.beta.organization.claudetag.BetaComplianceConfigurationRestrictedException
     * @throws
     *   com.anthropic.models.beta.organization.claudetag.BetaOrganizationLevelScopedApiKeyRequiredException
     * @throws
     *   com.anthropic.models.beta.organization.claudetag.BetaSlackChannelSetUpSettingsConflictException
     */
    fun update(bundleId: String): BetaClaudeTagBundle = update(bundleId, BundleUpdateParams.none())

    /** @see update */
    fun update(
        bundleId: String,
        params: BundleUpdateParams = BundleUpdateParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaClaudeTagBundle = update(params.toBuilder().bundleId(bundleId).build(), requestOptions)

    /** @see update */
    fun update(
        bundleId: String,
        params: BundleUpdateParams = BundleUpdateParams.none(),
    ): BetaClaudeTagBundle = update(bundleId, params, RequestOptions.none())

    /** @see update */
    fun update(
        params: BundleUpdateParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): BetaClaudeTagBundle

    /** @see update */
    fun update(params: BundleUpdateParams): BetaClaudeTagBundle =
        update(params, RequestOptions.none())

    /** @see update */
    fun update(bundleId: String, requestOptions: RequestOptions): BetaClaudeTagBundle =
        update(bundleId, BundleUpdateParams.none(), requestOptions)

    /**
     * List the organization's Bundles, newest first by `created_at`. A Bundle that Claude Tag
     * manages for itself is not listed.
     *
     * @throws com.anthropic.models.beta.organization.claudetag.BetaApiKeyScopeMissingException
     * @throws
     *   com.anthropic.models.beta.organization.claudetag.BetaClaudeTagWritesNotEnabledException
     * @throws
     *   com.anthropic.models.beta.organization.claudetag.BetaComplianceConfigurationRestrictedException
     * @throws
     *   com.anthropic.models.beta.organization.claudetag.BetaOrganizationLevelScopedApiKeyRequiredException
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
         * Returns a raw HTTP response for `post /v1/organizations/claude_tag/bundles/{bundle_id}`,
         * but is otherwise the same as [BundleService.update].
         */
        @MustBeClosed
        fun update(bundleId: String): HttpResponseFor<BetaClaudeTagBundle> =
            update(bundleId, BundleUpdateParams.none())

        /** @see update */
        @MustBeClosed
        fun update(
            bundleId: String,
            params: BundleUpdateParams = BundleUpdateParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaClaudeTagBundle> =
            update(params.toBuilder().bundleId(bundleId).build(), requestOptions)

        /** @see update */
        @MustBeClosed
        fun update(
            bundleId: String,
            params: BundleUpdateParams = BundleUpdateParams.none(),
        ): HttpResponseFor<BetaClaudeTagBundle> = update(bundleId, params, RequestOptions.none())

        /** @see update */
        @MustBeClosed
        fun update(
            params: BundleUpdateParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<BetaClaudeTagBundle>

        /** @see update */
        @MustBeClosed
        fun update(params: BundleUpdateParams): HttpResponseFor<BetaClaudeTagBundle> =
            update(params, RequestOptions.none())

        /** @see update */
        @MustBeClosed
        fun update(
            bundleId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<BetaClaudeTagBundle> =
            update(bundleId, BundleUpdateParams.none(), requestOptions)

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
